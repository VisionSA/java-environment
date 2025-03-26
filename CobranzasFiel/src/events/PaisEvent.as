package events
{
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import flash.events.Event;
	
	
	public class PaisEvent extends Event
	{
		
		public static const BUSCAR_PAISES:String = "buscarPaisesEvent";
	    
	    public var filtro:Filtro;
		
		public function PaisEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}

	}
}