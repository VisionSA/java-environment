package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.CodComercio;

	public class PagoAComercioEvent extends CairngormEvent
	{
		public static const PAGOS_LIST:String = "pagosComercioListEvent";
		
		public static const PAGOS_LIST_COMPLETE:String = "pagosComercioListCompleteEvent";
		
		public static const PAGOS_LIST_UPDATE:String = "pagosComercioListUpdateEvent";
		
		public static const PAGOS_UPDATE_COMPLETE:String = "pagosUpdateCompleteEvent";
		
		public var codComercio:CodComercio;
		
		public function PagoAComercioEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}