package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.PlanVersion;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import events.GenericPlanEvent;
	
	import model.CobranzasModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class EliminarVersionCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("planVersionService");
			var pendingCall:AsyncToken = ro.borrarPlanVersion(event.data as PlanVersion);
			pendingCall.addResponder(this);
	
		}
		
		public function result(data:Object):void {
			
			Alert.show("Versión Eliminada con Exito");
			
			var filtro:Filtro = new Filtro();						
			filtro.campos.push("plan.idPlan");				
			filtro.operadores.push(Filtro.IGUAL);								
			filtro.valores.push(modelo.planSeleccionado.idPlan);
			
			
			modelo.versionSeleccionada = null;
			
			new GenericPlanEvent(GenericPlanEvent.BUSCAR_VERSIONES_EVENT,filtro).dispatch();
			
			ControlBlock.getInstance().remove();
			
		}
		
		public function fault(info:Object):void {
			
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
			
		}
		
	}
}