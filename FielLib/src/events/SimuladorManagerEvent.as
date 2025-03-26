package events
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.ListaPrecio;
	
	import flash.events.Event;

	public class SimuladorManagerEvent extends Event
	{
		public static const CALCULAR_CUOTAS:String = "calcularCuotasManager";
		
		public var cliente:ClienteTransaccion;
		
		public var monto:Number;
		
		public var listaPrecio:ListaPrecio;
		
		public function SimuladorManagerEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}