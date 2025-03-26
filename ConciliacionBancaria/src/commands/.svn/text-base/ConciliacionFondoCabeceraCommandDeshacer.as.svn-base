package commands{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.ConciliacionModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class ConciliacionFondoCabeceraCommandDeshacer implements IResponder, ICommand{
		
		private var modelo : ConciliacionModelLocator = ConciliacionModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void{
 			var listCabeceraDeshacer:Array = (event.data as ArrayCollection).source;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("conciliacionFondoCabeceraService");
			var pendingCall:AsyncToken = ro.deshacerConciliacionFondoCabecera(listCabeceraDeshacer);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void{
			Alert.show("Conciliacion se volvio atras con Exito");
			modelo.cabeceraNueva = null;
			modelo.concTempVisible = false;
			modelo.btnGrabarConcVisible = false;

		}
		
		public function fault(info:Object):void{
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
	}
}