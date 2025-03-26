package events
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Concepto;
	
	import flash.events.Event;

	public class AdelantoEfectivoEvent extends Event
	{
		
		public static const CALCULAR_CUOTAS:String = "calcularCuotasEvent";
		
		public static const CALCULAR_CUOTAS_MANAGER:String = "calcularCuotasManagerEvent";
		
		public var cliente:ClienteTransaccion;
		public var concepto:Concepto;
		public var importe:Number;
		
		public function AdelantoEfectivoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}