package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Concepto;
	import com.tarjetafiel.caja.vo.Cuota;

	public class AdelantoEfectivoEvent extends CairngormEvent
	{
		public static const REGISTRAR_ADELANTO:String = "resgistrarAdelantoEfectivo";
		public static const BUSCAR_DISPONIBLE:String = "buscarDisponibleAdelantoEvent";
		
		public var cuotas:Array;
		public var cliente:ClienteTransaccion;
		public var concepto:Concepto;
		public var monto:Number;
		
		public function AdelantoEfectivoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}