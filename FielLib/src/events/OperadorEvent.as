package events
{
	import com.tarjetafiel.caja.vo.Operador;
	
	import flash.events.Event;

	public class OperadorEvent extends Event
	{
		
		public static const BUSCAR_OPERADOR_LOGUEADO:String = "buscarOperadorLogueadoEvent";
		
		public static const OPERADOR_LOGUEADO:String = "operadorLogueadoEvent";
		
		public static const VALIDAR_PERMISO_PANTALLA:String = "validarPermisoPantallaEvent"
		
		public static const PERMISO_HABILITADO:String = "PERMISO_HABILITADO";
		
		public static const PERMISO_DESHABILITADO:String = "PERMISO_DESHABILITADO";
				
		public var codigoOperador:Number; 
		
		public var operador:Operador;
		
		public var pantalla:String;
		
		public function OperadorEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}