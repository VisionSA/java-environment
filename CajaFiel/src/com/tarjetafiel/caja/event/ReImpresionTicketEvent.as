package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public class ReImpresionTicketEvent extends CairngormEvent
	{
		public static const RE_IMPRIMIR_TICKET:String = "reImprimirTicket";
		
		public function ReImpresionTicketEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}