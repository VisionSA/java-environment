package events
{
	import com.util.paginacion.Paginador;
	
	import flash.events.Event;

	public class TramiteManagerEvent extends Event
	{  
		public static const BUSCAR_TRAMITE_POR_CLIENTE:String = "buscarTramitesPorCliente"; 
		public static const BUSCAR_TAREAS_POR_TRAMITE:String = "buscarTareasPorTramite";
	 	
	 	public var idCliente:Number;
	 	public var idTramite:Number;
	 	public var idProceso:Number;
		public var fechaInicio:Date;
		public var fechaInicioReal:Date;
		public var fechaFin:Date;
		public var fechaFinReal:Date;
 
		   
		public function TramiteManagerEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
		
	}
}