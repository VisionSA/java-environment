package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Concepto;

	public class CalculoCuotaEvent extends CairngormEvent
	{
		
		public static const CALCULO_CUOTA:String = "calculoCuotaAdelantoEfectivo";
		
		public var cliente:ClienteTransaccion;
		
		public var importe:Number;
		
		public var concepto:Concepto;
		
		public function CalculoCuotaEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}