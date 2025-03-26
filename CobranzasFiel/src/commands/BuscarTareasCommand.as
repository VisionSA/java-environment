package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	import mx.utils.ObjectUtil;
	
	import vo.FiltroTarea;

	public class BuscarTareasCommand implements ICommand, IResponder {
		
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		private var esTareaPendiente : Boolean;		
		
		public function execute(event:CairngormEvent):void {
			
			//ANTES DE HACER LA BUSQUEDA ELIMINO LA TAREA SELECCIONADA
			modelo.tareaSeleccionada = null;
			modelo.esReporte = false;
			modelo.esConfirmable = false;
			if (event.data.isConfirmada == "N"){
				esTareaPendiente = true;
			}else {
				esTareaPendiente = false;
			}
					
			
			modelo.filtroBusqueda = new FiltroTarea();
			modelo.filtroBusqueda.isConfirmada = event.data.isConfirmada;		
			modelo.filtroBusqueda.fechaDesde = event.data.fechaDesde;
			modelo.filtroBusqueda.fechaHasta = event.data.fechaHasta;			
			
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("tareaService");
			var pendingCall:AsyncToken = ro.getListaTareasByParam(event.data.isConfirmada,event.data.fechaDesde,event.data.fechaHasta);
			pendingCall.addResponder(this);			
		}
		
		public function result(data:Object):void {
			var resultado : Array = ResultEvent(data).result as Array;
			
			modelo.esConfirmable = esTareaPendiente;
			
			if (resultado != null && resultado.length > 0){
				modelo.listaTareas = new ArrayCollection(resultado);
			}
			
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			
			modelo.listaTareas = null;
			
			ControlBlock.getInstance().remove();
		}
		
	}
}