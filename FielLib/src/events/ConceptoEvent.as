package events
{
	import flash.events.Event;

	public class ConceptoEvent extends Event
	{
		
		public static const BUSCAR_CONCEPTOS:String = "buscarConceptosEvent";
		
		public function ConceptoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}