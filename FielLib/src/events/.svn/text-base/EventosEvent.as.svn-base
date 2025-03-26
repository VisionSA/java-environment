package events
{
	import com.tarjetafiel.caja.vo.EventosCliente;
	import com.tarjetafiel.caja.vo.EventosComercio;
	
	import flash.events.Event;

	public class EventosEvent extends Event
	{
		
		public static const BUSCAR_EVENTOS_CLIENTE:String = "buscarEventosClientes";
		public static const GUARDAR_EVENTO:String = "guardarEventosClientes";
		public static const ACTUALIZAR_EVENTO:String = "actualizarEventosClientes";
		public static const ELIMINAR_EVENTO:String = "eliminarEventosClientes";
		public static const CARGAR_CMB_OPERADORES:String = "cargarComboOperadores";
		public static const BUSCAR_EVENTOS_COMERCIO:String = "buscarEventosComercios";
		public static const GUARDAR_EVENTO_COMERCIO:String = "guardarEventosComercios";
		public static const ACTUALIZAR_EVENTO_COMERCIO:String = "actualizarEventosComercios";
		public static const BUSCAR_TODOSEVENTOS_CLIENTE:String = "buscarTodosEventosClientes";
		public static const ELIMINAR_EVENTO_COMERCIO:String = "eliminarEventoComercios";
		public static const GUARDAR_EVENTO_BOLQUEO:String = "guardarEventoBloqueoCliente";
		
		
		public var idComercio:Number;
		public var eventoCom:EventosComercio;		
		public var idCliente:Number;
		public var fechaDesde:Date; 
		public var fechaHasta:Date;
		public var evento:EventosCliente;
		public var operador:Number;
		
		public function EventosEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}