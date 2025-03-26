package events {
	
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.util.Filtro;	
	
	public class AcreditacionFondoEvent extends CairngormEvent {
		
        public static const TRAER_ACREDITACION_FONDOS_EVENT : String = "TraerAcreditacionFondosEvent";
   		
   		public var filtro:Filtro;
   		
		public function AcreditacionFondoEvent(type:String, param : Object, filtro:Filtro = null) {
                super(type, false, false);
                data = param;
                this.filtro = filtro;
        }    
		
	}
}