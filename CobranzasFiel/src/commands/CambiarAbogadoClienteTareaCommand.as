package commands
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class CambiarAbogadoClienteTareaCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();	
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var obj : Object = event.data;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("ejecucionPlanService");
			var pendingCall:AsyncToken = ro.cambiarAbogadoClienteTarea(obj.idEjecucionPlan,obj.idAbogIN);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			
			modelo.esAbogadoValido = false;
			
			modelo.abogadoClienteTarea = modelo.abogadoSeleccionadoACambiar;
			
			ControlBlock.getInstance().remove();
			
			Alert.show("Abogado cambiado satisfactoriamente");
			
			
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();			
		}
		
	}
}