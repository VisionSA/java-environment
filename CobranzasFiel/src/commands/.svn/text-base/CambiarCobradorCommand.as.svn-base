package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Cobrador;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	import mx.utils.ObjectUtil;

	public class CambiarCobradorCommand implements ICommand, IResponder {
		
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("ejecucionPlanService");
			var pendingCall:AsyncToken = ro.cambiarCobrador((event.data as Cobrador).idCobrador,modelo.detalleTareaSeleccionada.idEjecucionPlan);
			pendingCall.addResponder(this);
			
		}
		
		public function result(data:Object):void {
			var resultado : uint = ResultEvent(data).result as uint;			
			
			var detalleTarea : Object = modelo.listaDetalleTareas.getItemAt(modelo.listaDetalleTareas.getItemIndex(modelo.detalleTareaSeleccionada));
			
			detalleTarea.cobrador.idColaborador = resultado;
			detalleTarea.cobrador.cobrador.idCobrador = modelo.cobradorSeleccionadoACambiar.idCobrador;
			detalleTarea.apellidoCobrador = modelo.cobradorSeleccionadoACambiar.apellido;
			detalleTarea.nombreCobrador = modelo.cobradorSeleccionadoACambiar.nombre;		
			
			modelo.cobrador.apellido = modelo.cobradorSeleccionadoACambiar.apellido;
			modelo.cobrador.nombre = modelo.cobradorSeleccionadoACambiar.nombre;
			
			modelo.detalleTareaSeleccionada.apellidoCobrador = modelo.cobradorSeleccionadoACambiar.apellido;
			modelo.detalleTareaSeleccionada.nombreCobrador = modelo.cobradorSeleccionadoACambiar.nombre;
			
			
			modelo.esCobradorValido = false;
			
			ControlBlock.getInstance().remove();
			
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			
			ControlBlock.getInstance().remove();
		}
		
	}
}