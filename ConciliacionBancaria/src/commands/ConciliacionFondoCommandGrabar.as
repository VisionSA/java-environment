package commands{
	import business.BaseConciliacion;
	
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.ConciliacionModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class ConciliacionFondoCommandGrabar implements IResponder, ICommand{
		
		private var modelo : ConciliacionModelLocator = ConciliacionModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void{
 			modelo.cabeceraNueva.comentario = event.data;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("conciliacionFondoCabeceraService");
			modelo.cabeceraNueva.conciliacionFondosFlex = modelo.cabeceraNueva.conciliacionFondos.source;
			modelo.cabeceraNueva.conciliacionFondos = null;
			var pendingCall:AsyncToken = ro.grabarConciliacionFondoCabeceraFlex(modelo.cabeceraNueva);
			pendingCall.addResponder(this);
			
				
		}
		
		public function result(data:Object):void{
			//Alert.show("Conciliacion grabada con Exito");
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