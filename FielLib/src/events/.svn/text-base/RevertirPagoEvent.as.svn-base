package events
{
	import flash.events.Event;
	import com.tarjetafiel.caja.vo.Operador;
	import com.tarjetafiel.caja.vo.Caja;
	import com.tarjetafiel.caja.vo.Impresora;

	public class RevertirPagoEvent extends Event
	{  
	 	
		public var idCliente:Number;
		public var fechaDesde:Date; 
		public var fechaHasta:Date; 
		public var idCtaCte:Number;
		public var operador:Operador;
		public var caja:Caja;
		public var impresora:Impresora;
		public static const BUSCAR_PAGOS_CTE:String = "buscarPagosCteEvent";
		public static const REVERTIR_PAGO:String = "revertirPagoEvent";
		   
		public function RevertirPagoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
				super(type, bubbles, cancelable);
		}
		
		
	}
}