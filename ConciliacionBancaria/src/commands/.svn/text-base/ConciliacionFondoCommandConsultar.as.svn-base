package commands
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.ConciliacionModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class ConciliacionFondoCommandConsultar implements ICommand, IResponder {
		
		private var modelo : ConciliacionModelLocator = ConciliacionModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("conciliacionFondoService");
			var pendingCall:AsyncToken = ro.getConciliacionFondosFlex(event.data);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			modelo.listaConciliable.removeAll();
			var resultado : Array = ResultEvent(data).result as Array;
			if (resultado != null){
				modelo.listaConciliable = new ArrayCollection(resultado);
			}
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();			
		}
		
	}
}