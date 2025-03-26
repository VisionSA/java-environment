package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.CtaCteCliente;

	public class PagoEvent extends CairngormEvent
	{
		public static const EFECTUAR_PAGO:String = "efectuarPago";		
				
		private var _ctaCteCliente:CtaCteCliente;	
		public var functionCallPagoRealizado:Function;  
		public var suVuelto:Number;
		 
		public function PagoEvent(type:String, ctaCteCliente:CtaCteCliente, functionCallPagoRealizado:Function, suVuelto:Number, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this._ctaCteCliente = ctaCteCliente;		
			this.functionCallPagoRealizado = functionCallPagoRealizado;
			this.suVuelto = suVuelto;
		}
		
		public function get ctaCteCliente():CtaCteCliente{
			return _ctaCteCliente;
		}
		
		
	}
}