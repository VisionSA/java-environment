package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.empresa.EmpresaDelagate;
	import com.tarjetafiel.caja.event.EmpresaEvent;
	import com.tarjetafiel.caja.event.PagoAComercioEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.Empresa;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.paginacion.Paginador;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class EmpresaCommand implements ICommand, IResponder
	{
		public var evt:CairngormEvent;
		
		public function EmpresaCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			evt = event;
			ControlBlock.getInstance().add();
			if(event.type == EmpresaEvent.BUSCAR_EMPRESA){
												
				new EmpresaDelagate(this).buscar( (event as EmpresaEvent).filtro, (event as EmpresaEvent).paginador);
				
			}
		}
		
		public function result(data:Object):void
		{
			ControlBlock.getInstance().remove();
			if(evt.type == EmpresaEvent.BUSCAR_EMPRESA){
																	
				if(ResultEvent(data).result is Empresa){
					ModelLocator.getInstance().empresaModel.empresa = null;
					ModelLocator.getInstance().empresaModel.empresasList.removeAll();
					ModelLocator.getInstance().pagosAComercios.liquidacionesList.removeAll();
					ModelLocator.getInstance().liqComercioModel.arrayLIquidaciones.removeAll();
					ModelLocator.getInstance().liqComercioModel.arrayCtaCteComercio.removeAll();
					ModelLocator.getInstance().liqComercioModel.arraySucursalesSeleccionadas.removeAll();
					ModelLocator.getInstance().empresaModel.empresa = ResultEvent(data).result as Empresa;
					ModelLocator.getInstance().empresaModel.labelEmpresaSeleccionada = this.getLabelEmpresaSeleccionada();
					ModelLocator.getInstance().busquedaComerciosNoVisible(); 
					ModelLocator.getInstance().empresaModel.labelButtonBusqueda = "Buscar";
					ModelLocator.getInstance().pagosAComercios.liquidacionesList.removeAll();
					var evt:PagoAComercioEvent = new PagoAComercioEvent(PagoAComercioEvent.PAGOS_LIST);
					evt.dispatch();
					/*if(ModelLocator.getInstance().empresasEncontradasView.popUpLoad){
						ModelLocator.getInstance().empresasEncontradasView.unLoadPopUp();
					}*/
				} else {
					var paginador:Paginador = ResultEvent(data).result as Paginador;
					ModelLocator.getInstance().empresaModel.empresasList = new ArrayCollection(paginador.result);
										
					/*ModelLocator.getInstance().empresasEncontradasView.loadPopUp();
					
					ModelLocator.getInstance().empresasEncontradasView.paginador.paginacionCompleta(paginador);*/
					
				}					
							
			}	
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();			
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
		private function getLabelEmpresaSeleccionada():String{
			
			var label:String = "Empresa: "+ModelLocator.getInstance().empresaModel.empresa.razonSocial + " - C.U.I.T.: " +
			+ ModelLocator.getInstance().empresaModel.empresa.cuit;		
			
			if(ModelLocator.getInstance().empresaModel.empresa.tipoLiquidacion == Empresa.LIQUIDA_COMERCIO){
				label += " - Liquida por Código de Comercio"; 			
			} else {
				label += " - Liquida por C.U.I.T.";
			}
			
			return label;
								
		}
		
	}
}