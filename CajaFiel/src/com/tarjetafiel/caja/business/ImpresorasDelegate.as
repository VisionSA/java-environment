package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class ImpresorasDelegate extends BaseDelegate
	{
		public function ImpresorasDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function listarImpresoras():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("impresoras");
			var ask:AsyncToken = ro.getImpresora(new Filtro());
			ask.addResponder(this.responder);
		}
		
	}
}