package events
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.ListaPrecio;
	
	import flash.events.Event;

	public class ConfirmacionConsumoManagerEvent extends Event
	{
		public static const CONFIRMAR_CONSUMO:String = "confirmarConsumoManager";
		
		public var monto:String;
		public var codComercio:String;
		public var nroTarjeta:String;
		public var cuota:String;
		public var nroCupon:String;
		
		public function ConfirmacionConsumoManagerEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
	}
}