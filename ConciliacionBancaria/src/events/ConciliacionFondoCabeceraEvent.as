package events {
	
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.util.Filtro;	
	
	public class ConciliacionFondoCabeceraEvent extends CairngormEvent {
		
        public static const TRAER_CONCILIADOS_FONDOS_CABECERA_EVENT : String = "TraerConciliadosFondosCabeceraEvent";
		public static const ARMAR_Y_GRABAR_CONCILIACION_CABECERA_EVENT : String = "ArmarYGrabarConciliacionCabeceraEvent";		
		public static const CONFIRMAR_CONCILIACION_CABECERA_EVENT : String = "ConfirmarConciliacionCabeceraEvent";
		public static const CONFIRMAR_TODOS_CONCILIACION_CABECERA_EVENT : String = "ConfirmarTodosConciliacionCabeceraEvent";
   		public static const DESHACER_CONCILIACION_CABECERA_EVENT : String = "DeshacerConciliacionCabeceraEvent";
   		public static const TRAER_NO_CONCILIADOS_FONDOS_CABECERA_EVENT : String = "TraerNoConciliadosFondosCabeceraEvent";
   		public static const BUSCAR_CANTIDAD_PAGINAS_CONCILIACION : String = "BuscarCantidadPaginasConciliacionCabeceraEvent";
   		public static const BUSCAR_CANTIDAD_PAGINAS_REVERSION : String = "BuscarCantidadPaginasReversionCabeceraEvent";
		
		public var filtro:Filtro;
   		
		public function ConciliacionFondoCabeceraEvent(type:String, param : Object, filtro:Filtro = null) {
                super(type, false, false);
                data = param;
                this.filtro = filtro;
        }    
		
    
	}
}