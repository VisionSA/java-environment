package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.vo.Cheque;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class ChequeDelegate extends BaseDelegate
	{
		public function ChequeDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function existeCheque(cheque:Cheque):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("cheque");
			var ask:AsyncToken = ro.buscarChequePorNumero(cheque);
			ask.addResponder(responder);			
		}
		
	}
}