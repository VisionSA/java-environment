/*@I3820*/package events
{
	import flash.events.Event;
	
	public class CloseCustomMessageEvent extends Event
	{
		public static const CLOSE_FAULT_MESSAGE:String 		= "CLOSE_FAULT_MESSAGE";
		public static const CLOSE_NOTIFY_MESSAGE:String		= "CLOSE_NOTIFY_MESSAGE";
		public static const CLOSE_INFO_MESSAGE:String		= "CLOSE_INFO_MESSAGE";
		
		public function CloseCustomMessageEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
	}
}
/*@F3820*/