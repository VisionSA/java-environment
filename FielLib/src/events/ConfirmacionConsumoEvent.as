package events
{
	import flash.events.Event;

	public class ConfirmacionConsumoEvent extends Event
	{
		public static const CONFIRMAR_CONSUMO:String = "confirmarConsumo";
	
		public var monto:String;
		public var codComercio:String;
		public var nroTarjeta:String;
		public var cuota:String;
		public var nroCupon:String;
		public var  estadoConc:String;
		
		
		public function ConfirmacionConsumoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}