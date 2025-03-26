package events
{
	import com.tarjetafiel.caja.vo.Etapa;
	
	import flash.events.Event;
	
	
	public class EtapaEvent extends Event
	{
		
		public static const BUSCAR_ETAPAS:String = "BUSCAR_ETAPAS";
				
	
		public function EtapaEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}

	}
}