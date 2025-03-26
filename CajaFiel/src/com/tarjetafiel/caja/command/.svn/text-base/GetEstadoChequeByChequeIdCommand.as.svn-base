package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.Cheque;
	import com.tarjetafiel.caja.vo.ChequeEstado;
	import com.util.components.alert.AlertError;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class GetEstadoChequeByChequeIdCommand implements ICommand, IResponder {
		public function execute(event:CairngormEvent):void {
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("chequeHistorial");
			var ask:AsyncToken = ro.getChequeEstadoByIdCheque((event.data as Cheque).idCheque);
			ask.addResponder(this);			
		}
		
		public function result(data:Object):void {
			ModelLocator.getInstance().chequeModel.cheque.chequeEstado = ResultEvent(data).result  as ChequeEstado;
			ModelLocator.getInstance().chequeModel.setearColor(); // Se utiliza para setear el color del label q muestra el estado de un cheque
			ModelLocator.getInstance().chequeModel.validarCheque();
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}		
		
	}
}