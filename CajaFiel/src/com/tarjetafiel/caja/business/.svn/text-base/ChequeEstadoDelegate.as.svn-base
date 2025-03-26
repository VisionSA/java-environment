package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class ChequeEstadoDelegate extends BaseDelegate
	{
		public function ChequeEstadoDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscarEstados():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("chequeEstados");
			var ask:AsyncToken = ro.getChequeEstadoes();
			ask.addResponder(responder);
		}
		
	}
}