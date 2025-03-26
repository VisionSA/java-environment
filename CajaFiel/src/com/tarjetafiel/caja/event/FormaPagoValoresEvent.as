package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public class FormaPagoValoresEvent extends CairngormEvent
	{
		public static const BUSCAR_TODOS:String = "findAllFormaPagoValores";
		
		public function FormaPagoValoresEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}