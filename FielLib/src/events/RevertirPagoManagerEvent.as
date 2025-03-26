package events
{
	import com.tarjetafiel.caja.vo.Impresora;
	import com.tarjetafiel.caja.vo.Operador;
	import com.tarjetafiel.caja.vo.Caja;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import flash.events.Event;
	


	public class RevertirPagoManagerEvent extends Event
	{   
		public var filtro:Filtro; 
		public var idCliente:Number;
		public var fechaDesde:Date; 
		public var fechaHasta:Date; 
		public var idCtaCte:Number;
		public var operador:Operador;
		public var caja:Caja;
		public var impresora:Impresora;
		public static const BUSCAR_PAGOS_CTE:String = "buscarPagosCteManagerEvent";
		public static const REVERTIR_PAGO:String = "revertirPagoManagerEvent";
		
		   
		public function RevertirPagoManagerEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}		
	}
}