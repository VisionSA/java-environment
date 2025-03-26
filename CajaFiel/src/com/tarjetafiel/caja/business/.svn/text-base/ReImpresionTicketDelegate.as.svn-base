package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertWarning;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class ReImpresionTicketDelegate extends BaseDelegate
	{
		public function ReImpresionTicketDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function reImprimirTicket():void{
			
			if(!ModelLocator.getInstance().ticketModel.ultimaRespuestaImpresion){
				AlertWarning.show("No hay ningún ticket para reimprimir.");
				return;
			}
			
			var ticket:String = ModelLocator.getInstance().ticketModel.ultimaRespuestaImpresion.ticket;
			
			if(ticket && ticket != ""){
				var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("reImpresionService");
				var ask:AsyncToken = ro.reImprimirTicket(ticket,ModelLocator.getInstance().impresorasModel.impresoraDefault);
				ask.addResponder(responder);	
			} else {
				AlertError.show("El ticket a reimprimir esta vacio");
			}
			
		}
		
	}
}