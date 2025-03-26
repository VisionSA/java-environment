package events {
	
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.util.Filtro;	
	
	public class ConciliacionFondoEvent extends CairngormEvent {
		
        public static const TRAER_CONCILIACION_FONDOS_EVENT : String = "TraerConciliacionFondosEvent";
        public static const LEER_CONCILIACION_EVENT : String = "LeerConciliacionEvent";
		public static const ARMAR_CONCILIACION_EVENT : String = "ArmarConciliacionEvent";
   		public static const GRABAR_CONCILIACION_EVENT : String = "GrabarConciliacionEvent";
   		public static const TRAER_CONCILIACION_CABECERA_FONDOS_EVENT : String = "TraerConciliacionCabeceraFondosEvent";
   		public static const REVERTIR_CONCILIACION_CABECERA_EVENT : String = "RevertirConciliacionCabeceraEvent";
   		public static const REVERTIR_TODOS_CONCILIACION_CABECERA_EVENT : String = "RevertirTodosConciliacionCabeceraEvent";
   		
   		public var filter:Object;
   		
   		public var filtro:Filtro;
   		
		public function ConciliacionFondoEvent(type:String, param : Object, filtro:Filtro = null, filter:Object = null) {
                super(type, false, false);
                data = param;
                this.filtro = filtro;
                this.filter = filter;
        }    
		
	}
}