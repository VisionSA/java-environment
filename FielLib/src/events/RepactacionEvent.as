package events
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Concepto;
	import com.tarjetafiel.caja.vo.LiqCliente;
	
	import flash.events.Event;

	public class RepactacionEvent extends Event
	{
		
		public static const SIMULAR:String = "simularEvent";
		
		public static const SIMULARREPACTACION:String = "simularRepactacionEvent";
		
		
		public var montoTotal:Number;
		
		public var suPago:Number;
		
		public var concepto:Concepto;
		
		public var cliente:ClienteTransaccion;
		
		public function RepactacionEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}