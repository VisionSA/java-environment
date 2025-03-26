package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.event.CalculoCuotaEvent;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class CalculoCuotaDelegate extends BaseDelegate
	{
		public function CalculoCuotaDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function calcularCuota(evt:CalculoCuotaEvent):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("calculoCuotas");
			//var ask:AsyncToken = ro.calculoCuotaAdelanto(evt.importe,evt.cliente);
			/*var ask:AsyncToken = ro.calculoCuotaAdelanto(evt.importe, evt.cliente, evt.concepto);*/
			var ask:AsyncToken = ro.simuladorCuotasIntereses(evt.importe,0, evt.concepto, evt.cliente,0);
			ask.addResponder(responder);
		}
		
	}
}