package events
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.ListaPrecio;
	
	import flash.events.Event;

	public class SimuladorEvent extends Event
	{
		public static const CALCULAR_CUOTAS:String = "calcularCuotas";
		
		public static const DISABLED_SIMULADOR:String = "disabledSimulador";
		
		public var cliente:ClienteTransaccion;
		
		public var monto:Number;
		
		public var listaPrecio:ListaPrecio;			
		
		public function SimuladorEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}