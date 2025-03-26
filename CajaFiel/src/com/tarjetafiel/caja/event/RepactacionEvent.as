package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public class RepactacionEvent extends CairngormEvent
	{
		public static const CALCULAR_REPACTACION:String = "calcularRepactacion";
		public static const CANCELAR_REPACTACION:String = "cancelarRepactacion";
		public static const CANCELAR_REFINANCIACION:String = "cancelarRefinanciacion";
		public static const AGREGAR_REPACTACION:String = "agregarRepactacion";
		public static const AGREGAR_REFINANCIACION:String = "agregarRefinanciacion";
		public static const REPACTACION_PAGO_MINIMO:String = "refinanciacionPagoMinimoEvent";
		
		public var montoTotal:Number;
		//public var totalRepactado:Number
		//public var totalIntereses:Number;
		public var pagoMinimo:Number;
		public var cantidadCuotas:Array;
		
		public function RepactacionEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}