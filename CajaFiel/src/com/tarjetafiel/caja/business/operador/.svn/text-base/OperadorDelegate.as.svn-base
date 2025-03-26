package com.tarjetafiel.caja.business.operador
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	public class OperadorDelegate extends BaseDelegate
	{
		public function OperadorDelegate( iresponder:IResponder )
		{
			super(iresponder);
		}
		
		public function getOperadorLogueado(codigo:Number):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("login");
			var pendingCall:AsyncToken = ro.getOperadorLogueado(codigo);
			pendingCall.addResponder(responder);
		}

	}
}