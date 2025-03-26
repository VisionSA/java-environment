package com.fiel.caja.judicial.command.Cheques
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.ChequeEstado;
	import com.tarjetafiel.proveedorconexion.business.ConstantesChequesEstados;
	import com.tarjetafiel.proveedorconexion.delegate.ChequeDelegate;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import utils.ManejadorMensajes;
	
	public class BuscarChequeEstadoPorIdChequeEstadoCommand implements ICommand, IResponder
	{
		
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			new ChequeDelegate(this).buscarChequeEstado(event.data);
		}
		
		public function result(data:Object):void
		{
			modelo.cheque.chequeEstado = ResultEvent(data).result as ChequeEstado;
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeError(FaultEvent(info).fault.faultString);
		}
	}
}