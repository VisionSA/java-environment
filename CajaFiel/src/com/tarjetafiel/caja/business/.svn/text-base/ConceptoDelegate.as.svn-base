package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class ConceptoDelegate extends BaseDelegate
	{
		public function ConceptoDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function listarConceptos():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("conceptos");
			var pendingCall:AsyncToken = ro.getConceptoFlex(new Filtro());
			pendingCall.addResponder(this.responder);
		}
		
	}
}