package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.empresa.LiqComercioDelagate;
	import com.tarjetafiel.caja.event.LiqComercioEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.CtaCteComercio;
	import com.tarjetafiel.caja.vo.LiqComercioLP;
	import com.tarjetafiel.caja.vo.SucEmpresa;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.core.Application;
	import mx.managers.PopUpManager;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class LiqComercioCommand implements ICommand, IResponder
	{
		private var evt:CairngormEvent;			
		private var cont:int;	
		private var terminoBusqudaLiq:Boolean = false;	
		private var arrayResult:Array;
		
		public function LiqComercioCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			evt = event;
			ControlBlock.getInstance().add();			
			if(event.type == LiqComercioEvent.BUSCAR_LIQ_COMERCIO){
				ModelLocator.getInstance().liqComercioModel.arrayLIquidaciones.removeAll();
				ModelLocator.getInstance().liqComercioModel.totalBrutoLiquidado = 0;
					ModelLocator.getInstance().liqComercioModel.totalNetoLiquidado = 0;
					ModelLocator.getInstance().liqComercioModel.totalPagarLiquidado = 0; 		
				ModelLocator.getInstance().liqComercioModel.arrayCtaCteComercio.removeAll();
				cont = 0;									
				new LiqComercioDelagate(this).buscar( (event as LiqComercioEvent).filtro);
				
			} else if(event.type == LiqComercioEvent.BUSCAR_DETALLES_LIQ){
				new LiqComercioDelagate(this).buscarDetalles( (event as LiqComercioEvent).filtro );
			}
		}
		
		public function result(data:Object):void
		{
			
			if(evt.type == LiqComercioEvent.BUSCAR_LIQ_COMERCIO && terminoBusqudaLiq == false){
														
				arrayResult = ResultEvent(data).result as Array;
				for each(var obj:Object in arrayResult){
					//LiqComercioLP(obj).liqComercio.codComercio.sucEmpresa = SucEmpresa(ModelLocator.getInstance().liqComercioModel.arraySucursalesSeleccionadas.getItemAt(cont));
					LiqComercioLP(obj).sucEmpresa = SucEmpresa(ModelLocator.getInstance().liqComercioModel.arraySucursalesSeleccionadas.getItemAt(cont));
					ModelLocator.getInstance().liqComercioModel.totalBrutoLiquidado += LiqComercioLP(obj).totalBruto;
					ModelLocator.getInstance().liqComercioModel.totalNetoLiquidado += LiqComercioLP(obj).totalNeto;
					ModelLocator.getInstance().liqComercioModel.totalPagarLiquidado += LiqComercioLP(obj).totalPagar; 					
					ModelLocator.getInstance().liqComercioModel.arrayLIquidaciones.addItem(obj);						 
				}
				if(cont < ModelLocator.getInstance().liqComercioModel.arraySucursalesSeleccionadas.length-1){
					LiqComercioEvent(evt).filtro.valores[2] = SucEmpresa(ModelLocator.getInstance().liqComercioModel.arraySucursalesSeleccionadas[++cont]).idSucEmpresa;
					new LiqComercioDelagate(this).buscar( (evt as LiqComercioEvent).filtro);									
				} else {
					
					terminoBusqudaLiq = true;
					cont = 0;					
					(evt as LiqComercioEvent).filtro.campos[0] = "fechaLote";
					(evt as LiqComercioEvent).filtro.campos[1] = "fechaLote";
					(evt as LiqComercioEvent).filtro.campos[2] = "codComercio.sucEmpresa.idSucEmpresa";
					(evt as LiqComercioEvent).filtro.campos[3] = "liqComercio";
					(evt as LiqComercioEvent).filtro.operadores[3] = Filtro.NULL;									
					(evt as LiqComercioEvent).filtro.valores[3] = "";
					(evt as LiqComercioEvent).filtro.valores[2] = SucEmpresa(ModelLocator.getInstance().liqComercioModel.arraySucursalesSeleccionadas[cont]).idSucEmpresa;					
					new LiqComercioDelagate(this).buscarCtaCteComercio((evt as LiqComercioEvent).filtro);
					
				}
				
			} else if(evt.type == LiqComercioEvent.BUSCAR_DETALLES_LIQ){
				
				(evt as LiqComercioEvent).liqComercioLp.detallesLiqComercioDet =  (data as ResultEvent).result as Array; 
				ModelLocator.getInstance().liqComercioModel.liqComercioLPSelect = (evt as LiqComercioEvent).liqComercioLp;
				ControlBlock.getInstance().remove();
				/*PopUpManager.addPopUp(ModelLocator.getInstance().liqComercioDetView, Application.application.contGlobal, true);
				PopUpManager.centerPopUp(ModelLocator.getInstance().liqComercioDetView);
				ModelLocator.getInstance().liqComercioDetView.adg1.setFocus();*/
				
			} else if(evt.type == LiqComercioEvent.BUSCAR_LIQ_COMERCIO && terminoBusqudaLiq){
				
				arrayResult = ResultEvent(data).result as Array;
				for each(var o:Object in arrayResult){
					//LiqComercioLP(obj).liqComercio.codComercio.sucEmpresa = SucEmpresa(ModelLocator.getInstance().liqComercioModel.arraySucursalesSeleccionadas.getItemAt(cont));
					CtaCteComercio(o).sucEmpresa = SucEmpresa(ModelLocator.getInstance().liqComercioModel.arraySucursalesSeleccionadas.getItemAt(cont));
					ModelLocator.getInstance().liqComercioModel.arrayCtaCteComercio.addItem(o);						 
				}
				if(cont < ModelLocator.getInstance().liqComercioModel.arraySucursalesSeleccionadas.length-1){
					(evt as LiqComercioEvent).filtro.valores[2] = SucEmpresa(ModelLocator.getInstance().liqComercioModel.arraySucursalesSeleccionadas[++cont]).idSucEmpresa;					
					new LiqComercioDelagate(this).buscarCtaCteComercio((evt as LiqComercioEvent).filtro);
				} else {
					ControlBlock.getInstance().remove();
				}
				
			}
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();			
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
	}
}