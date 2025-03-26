package events
{
	import com.tarjetafiel.caja.vo.Plan;
	import com.tarjetafiel.caja.vo.PlanVersion;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	
	
	public class PlanEvent extends Event
	{
		
		public static const NEW_PLAN:String = "NEW_PLAN";
		
		public static const CREAR_PLAN:String = "CREAR_PLAN";
		
		public static const BORRAR_PLAN:String = "BORRAR_PLAN";
		
		public static const BUSCAR_PLANES:String = "buscarPLanesEvent";
		
		public static const GUARDAR_NUEVA_VERSION:String = "GUARDAR_NUEVA_VERSION";
		
		public static const BORRAR_VERSION:String = "BORRAR_VERSION";
		
		public static const BUSCAR_CONCEPTOS:String = "buscarConceptosEvent";
		
		public static const REFRESCAR_PLAN:String = "REFRESCAR_PLAN";
		
		public static const CAMBIO_VERSION_EVENT:String = "CAMBIO_VERSION_EVENT";

        public static const NUEVO_PLAN_GRABADO_CON_EXITO:String = "NUEVO_PLAN_GRABADO_CON_EXITO";
        
        public static const CAMBIAR_ESTADO_PLAN:String = "CAMBIAR_ESTADO_PLAN";
        
        public static const MARCAR_PLAN_POR_DEFECTO:String = "MARCAR_PLAN_POR_DEFECTO";
		
		public var listaEtapas:ArrayCollection;
				
	    //variables que hagan falta
	    public var plan:Plan;
	    public var planVersion:PlanVersion;
	    public var filtro:Filtro;
		public function PlanEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}

	}
}