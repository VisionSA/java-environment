package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Plan;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class EliminarPlanCommand implements ICommand, IResponder {
		
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("planService");
			var pendingCall:AsyncToken = ro.borrarPlan((event.data as Plan));
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			Alert.show("Plan Eliminado con Exito");
			
			modelo.listaPlanes.removeItemAt(modelo.listaPlanes.getItemIndex(modelo.planSeleccionado));
			
			modelo.planSeleccionado = null;
			
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void	{
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
	}
}