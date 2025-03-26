package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class BuscarVersionesCommand implements ICommand, IResponder {
		
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();		
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("planVersionService");
			var pendingCall:AsyncToken = ro.getPlanesVersionByFiltro(event.data);
			pendingCall.addResponder(this);
			
		}
		
		public function result(data:Object):void {
			var resultado : Array = ResultEvent(data).result as Array;
			if (resultado != null){
				modelo.listaVersiones = new ArrayCollection(resultado);
			}	
			
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void	{
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
	}
}