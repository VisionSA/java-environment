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

	public class CambiarAbogadosAsignadosCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			var idAbogIN : Number = event.data.idAbogIN;
			var idAbogOUT : Number = event.data.idAbogOUT;
			
			
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("ejecucionPlanService");
			var pendingCall:AsyncToken = ro.cambiarAbogadosTareasPendientes(idAbogIN,idAbogOUT,modelo.partidoSeleccionado.idPartido);
			pendingCall.addResponder(this);
			
			
		}
		
		public function result(data:Object):void {
			ControlBlock.getInstance().remove();
			Alert.show("El abogado para Tareas pendientes\nse ha modificado con Exito");
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
	}
}