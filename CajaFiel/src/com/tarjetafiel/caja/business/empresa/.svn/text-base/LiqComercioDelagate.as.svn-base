package com.tarjetafiel.caja.business.empresa
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class LiqComercioDelagate extends BaseDelegate
	{
		public function LiqComercioDelagate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscar(filtro:Filtro):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("liqComercio");
			var pendingCall:AsyncToken = ro.getLiqComercioLPFlex(filtro);
			pendingCall.addResponder(responder);
		}
		
		public function buscarDetalles(filtro:Filtro):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("liqComercioLpDetalle");
			var pendingCall:AsyncToken = ro.getLiqComercioDetFlex(filtro);
			pendingCall.addResponder(responder);
		}
		
		public function buscarCtaCteComercio(filtro:Filtro):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("ctaCteComercio");
			var pendingCall:AsyncToken = ro.getCtaCteComercioFlex(filtro);
			pendingCall.addResponder(responder);
		}
		
	}
}