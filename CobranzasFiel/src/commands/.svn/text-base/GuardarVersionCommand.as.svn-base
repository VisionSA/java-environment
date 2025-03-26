package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.PlanVersion;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.components.alert.AlertError;
	
	import events.GenericPlanEvent;
	
	import model.CobranzasModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class GuardarVersionCommand implements ICommand, IResponder {
		
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			var pv : PlanVersion = event.data as PlanVersion;
			modelo.versionSeleccionada.descripcion = pv.descripcion;
			modelo.versionSeleccionada.fechaDesde = pv.fechaDesde;
			modelo.versionSeleccionada.queryClientesQueAplican = pv.queryClientesQueAplican;
			modelo.versionSeleccionada.plan = modelo.planSeleccionado;
			modelo.listaVersiones.addItem(modelo.versionSeleccionada);
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("planVersionService");
			var pendingCall:AsyncToken = ro.grabarPlanVersion(modelo.versionSeleccionada);
			pendingCall.addResponder(this);
				
		}
		
		public function result(data:Object):void {
			Alert.show("Version creada con Exito");
			modelo.versionSeleccionada = null;
			var filtro:Filtro = new Filtro();						
			filtro.campos.push("plan.idPlan");				
			filtro.operadores.push(Filtro.IGUAL);								
			filtro.valores.push(modelo.planSeleccionado.idPlan);
 			new GenericPlanEvent(GenericPlanEvent.BUSCAR_VERSIONES_EVENT,filtro).dispatch();
			
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
	}
}