package commands
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class ExtractoBancarioCommandGuardar implements ICommand, IResponder {
		
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var obj : Object = event.data;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("extractoService");
			var pendingCall:AsyncToken = ro.grabarExtractoBancarioDesdeArchivo(obj.bytes);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			ControlBlock.getInstance().remove();
			var resultado : String = ResultEvent(data).result as String;
			if (resultado != null){
				Alert.show(resultado);
			}
			//Alert.show("Se guardo el archivo de extracto satisfactoriamente","Conciliacion Bancaria");
			
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();			
		}
		
	}
}