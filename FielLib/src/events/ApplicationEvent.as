package events
{
	import flash.events.Event;

	public class ApplicationEvent extends Event
	{
		
		public static const CAMBIAR_VISTA:String = "cambiarVistaEvent";
		
		public var index:int;
		
		public function ApplicationEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
	}
}