package com.tarjetafiel.proveedorconexion.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	public class BancoDelegate extends BaseDelegate
	{
		public function BancoDelegate(responder:IResponder )
		{
			super(responder);
		}
		
		public function buscar():void {
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("banco");
			var pendingCall:AsyncToken = ro.getBancos(new Filtro());
			pendingCall.addResponder(responder);
						
		}
	}
}