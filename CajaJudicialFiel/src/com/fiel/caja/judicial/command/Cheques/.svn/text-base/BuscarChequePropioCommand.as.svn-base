package com.fiel.caja.judicial.command.Cheques
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.fiel.caja.judicial.event.ChequeEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.Cheque;
	import com.tarjetafiel.proveedorconexion.delegate.ChequeDelegate;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	
	public class BuscarChequePropioCommand implements ICommand, IResponder
	{
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			ManejadorPantallas.mostrarProgressBar();
			new ChequeDelegate(this).buscarChequePropio(event.data.bp,event.data.numero,event.data.digVal);
		}
		
		public function result(data:Object):void
		{
			var resultado : Array = ResultEvent(data).result as Array;			
			if (resultado != null && resultado.length > 0){
				modelo.cheque = resultado.pop() as Cheque;
				modelo.validarConciliado(modelo.cheque);
				var obj : Object = new Object();
				obj.cheque = modelo.cheque;
				obj.esTercero = false;
				modelo.estadoActualVAgCheque = ConstantesEstados.ESTADO_VACH_PROPIO_NO_MODIFICABLE;
				new ChequeEvent(ChequeEvent.BUSCAR_ESTADO_CHEQUE_EVENT,obj).dispatch();
				
			}else{
				ManejadorMensajes.mostrarMensajeInformacion("No se encontró ningún cheque\ncon la descripción ingresada.");	
			}
			
			ManejadorPantallas.cerrarProgressBar();
			
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeError(FaultEvent(info).fault.faultString);
			ManejadorPantallas.cerrarProgressBar();
		}
	}
}