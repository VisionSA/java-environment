package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.model.ModelLocator;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	

	public class CajaDelegate extends BaseDelegate
	{
		public function CajaDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscarCaja():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("caja");			
			var ask:AsyncToken = ro.buscarCajaPorOperadorFlex(ModelLocator.getInstance().operadorModel.operador);
			ask.addResponder(responder);
		}
		
	}
}