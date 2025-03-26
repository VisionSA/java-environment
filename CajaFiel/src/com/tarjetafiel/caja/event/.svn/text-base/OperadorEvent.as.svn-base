package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public class OperadorEvent extends CairngormEvent
	{
		public static const CONSULTAR_OPERADOR:String = "consultarOperador";
		
		public var codOperador:Number; 
		
		public function OperadorEvent(type:String, codigoOperador:Number, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.codOperador = codigoOperador;
		}
		
	}
}