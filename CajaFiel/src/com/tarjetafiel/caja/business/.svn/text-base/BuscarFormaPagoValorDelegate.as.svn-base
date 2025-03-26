package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class BuscarFormaPagoValorDelegate extends BaseDelegate
	{
		public function BuscarFormaPagoValorDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscarFormaPagoValores():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("formaPagoValor");
			var ask:AsyncToken = ro.getFormaPagoValoresFlex(new Filtro());
			ask.addResponder(responder);
		}
		
	}
}