package events {
	
	import com.adobe.cairngorm.control.CairngormEvent;	
	
	public class GenericAbogadosEvent extends CairngormEvent {
		
		// EVENTOS MODULO
        public static const BUSCAR_ABOGADOS_EVENT : String = "BuscarAbogadosEvent";
        public static const BUSCAR_ABOGADOS_PARA_CAMBIO_EVENT : String = "BuscarAbogadosParaCambioEvent";
        public static const BUSCAR_PARTIDOS_ABOGADO_EVENT : String = "BuscarPartidosAbogadoEvent";
        public static const SELECCIONAR_ABOGADO_CAMBIO_EVENT : String = "SeleccionarAbogadoCambioEvent";
        public static const CAMBIAR_ABOGADO_A_FUTURO_EVENT : String = "CambiarAbogadoAFuturoEvent";
        public static const CAMBIAR_ABOGADOS_ASIGNADOS_EVENT : String = "CambiarAbogadosAsignadosEvent";
        public static const BUSCAR_ABOGADO_CLIENTE_TAREA_EVENT : String = "BuscarAbogadoClienteTareaEvent";
        public static const CAMBIAR_ABOGADO_CLIENTE_TAREA_EVENT : String = "CambiarAbogadoClienteTareaEvent";
   
		public function GenericAbogadosEvent(type:String, param : Object) {
                super(type, false, false);
                data = param;
        }    
		
	}
}