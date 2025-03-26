package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Caja;

	public class FormaPagoCajaEvent extends CairngormEvent
	{
		public static const BUSCAR_FORMA_PAGO_CAJA:String = "buscarFormaPagoCaja";
				
		public function FormaPagoCajaEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);	
		}
		
	}
}