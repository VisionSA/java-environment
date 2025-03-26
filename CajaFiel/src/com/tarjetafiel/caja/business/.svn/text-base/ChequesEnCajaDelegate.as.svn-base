package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.model.ModelLocator;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class ChequesEnCajaDelegate extends BaseDelegate
	{
		public function ChequesEnCajaDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscarChequesEnCaja():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("chequeLugarService");
			var ask:AsyncToken = ro.buscarChequesEnLugar(ModelLocator.getInstance().cajaModel.caja.lugar);
			ask.addResponder(responder);
		}
		
	}
}