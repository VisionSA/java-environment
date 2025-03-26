package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class BancoPropioDelegate extends BaseDelegate
	{
		public function BancoPropioDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscarBancosPropios():void {
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("bancoPropio");
			var pendingCall:AsyncToken = ro.getBancoPropiosFlex(new Filtro());
			pendingCall.addResponder(responder);
						
		}
		
	}
}