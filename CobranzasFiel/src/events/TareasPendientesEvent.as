package events
{
	import com.tarjetafiel.caja.vo.TareaPendiente;
	
	import flash.events.Event;
	
	
	public class TareasPendientesEvent extends Event
	{
		
		public static const BUSCAR_TAREAS_PENDIENTES:String = "BUSCAR_TAREAS_PENDIENTES";
		
		public static const REFRESCAR_LISTA:String = "REFRESCAR_LISTA";
		
		public static const BUSCAR_DETALLES_TAREA_PENDIENTE:String = "BUSCAR_DETALLES_TAREA_PENDIENTE";
		
		public static const MOSTRAR_DETALLES_TAREA_PENDIENTE:String = "MOSTRAR_DETALLES_TAREA_PENDIENTE";

		
		public var tareaPendiente:TareaPendiente;

		
		public function TareasPendientesEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}

	}
}