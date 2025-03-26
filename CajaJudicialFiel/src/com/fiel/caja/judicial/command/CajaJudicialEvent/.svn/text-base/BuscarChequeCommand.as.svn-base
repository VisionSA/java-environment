package com.fiel.caja.judicial.command.CajaJudicialEvent
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.Cheque;
	import com.tarjetafiel.proveedorconexion.delegate.ChequeDelegate;
	
	import mx.controls.Alert;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	
	public class BuscarChequeCommand implements ICommand, IResponder
	{
	
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			ManejadorPantallas.mostrarProgressBar();
			new ChequeDelegate(this).buscarCheque(event.data as Cheque);
		}
		
		public function result(data:Object):void
		{
			var cheque : Cheque = ResultEvent(data).result as Cheque;
			
			if(cheque!=null){
				modelo.estadoActualVAgCheque = ConstantesEstados.ESTADO_VACH_TERCERO_NO_MODIFICABLE;
				modelo.cheque = cheque;
			}else {
				modelo.estadoActualVAgCheque = ConstantesEstados.ESTADO_VACH_TERCERO_MODIFICABLE;
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