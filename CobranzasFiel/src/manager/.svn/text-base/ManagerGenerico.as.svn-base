package manager
{
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.rpc.Fault;
	
	[Bindable]
	public class ManagerGenerico extends EventDispatcher
	{
		
		public var dispatcher:IEventDispatcher;
		
		public function ManagerGenerico()
		{
		}
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultString);
			ControlBlock.getInstance().remove();
		}

	}
}