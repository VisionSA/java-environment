package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.LiqCliente;

	public class ConsultarVencimientoEvent extends CairngormEvent
	{
		public static const CONSULTAR_VENCIMIENTO:String = "consultarVencimiento";
		
		public var liquidacion:LiqCliente;
		
		public function ConsultarVencimientoEvent(type:String, liq:LiqCliente,bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.liquidacion = liq;
		}
		
	}
}