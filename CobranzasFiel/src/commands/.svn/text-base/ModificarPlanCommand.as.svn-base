package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Plan;
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

	public class ModificarPlanCommand implements ICommand, IResponder {		
		
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			
			var newPlan : Plan = modelo.planSeleccionado;			
			newPlan.esPlanPorDefecto = (event.data as Plan).esPlanPorDefecto;
			newPlan.habilitado = (event.data as Plan).habilitado;
			
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("planService");
			var pendingCall:AsyncToken = ro.updatePlan(newPlan);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {			
			Alert.show("Plan modificado con Exito");
			
			var filtro:Filtro = new Filtro();						
			filtro.campos.push("descripcion");				
			filtro.operadores.push(Filtro.LIKE);								
			filtro.valores.push("");
			
			new GenericPlanEvent(GenericPlanEvent.BUSCAR_PLANES_EVENT,filtro).dispatch();
			
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
	}
}