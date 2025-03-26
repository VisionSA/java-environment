package commands {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class AbrirPDFCommand implements ICommand, IResponder {
		
				
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			
			var url: String = event.data;			
			
			navigateToURL(new URLRequest(String(url).replace('webapps','')),'_blank');
			ControlBlock.getInstance().remove();	
		}
		
		public function result(data:Object):void {
			
		}
		
		public function fault(info:Object):void	{
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
			
		}
		
	}
}