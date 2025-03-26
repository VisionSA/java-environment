package commands {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.EtapaVersion;
	
	import mx.rpc.IResponder;

	public class BuscarAccionVersionCommand implements ICommand, IResponder {
		
		[Bindable]
		private var ev : EtapaVersion;
		
		public function execute(event:CairngormEvent):void {
			ev = event.data;
		}
		
		public function result(data:Object):void {
		}
		
		public function fault(info:Object):void {
		}
		
	}
}