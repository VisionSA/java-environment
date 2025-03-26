package commands
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.TareaPendiente;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import events.GenericTareasPendientesEvent;
	
	import model.CobranzasModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.http.HTTPService;

	public class ConfirmarTareaPendienteCommand implements ICommand, IResponder {
		
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
						
			var tarea : TareaPendiente = event.data as TareaPendiente;
			
			var tipoTarea : int;
			
			
			/*
			 * tipoTarea = 3 (ficha cobros cobradores) 
			 */
			
			switch (tarea.idAccion){				
				case 8: // Asignacion Cobradores
					tipoTarea = 2;
					break;
				case 14: // Proceso LLamadas Telefonicas
					tipoTarea = 4;
					break;
				case 3: // Proceso Teledirecto
					tipoTarea = 5;
					break;
				case 15: //Asignar Abogado
					tipoTarea = 6;
					break;
				case 16: //Proceso Liquidacion Judicial
					tipoTarea = 7;
					break;
				default: // Todas las cartas
					tipoTarea = 1;
					break;
			}
			ControlBlock.getInstance().add();
			var httpService:HTTPService = new HTTPService();
		    httpService.url = CobranzasModelLocator.URL_SERVLET;
		    httpService.method="POST";	
		    
		    var token:AsyncToken=httpService.send({peticion:tipoTarea, diaTarea:event.data.fecha.date, mesTarea:event.data.fecha.month,anioTarea:event.data.fecha.fullYear,idAccion:tarea.idAccion});
		    token.addResponder(this);
		}
		
		public function result(data:Object):void {
			Alert.show("La Tarea Seleccionada ha sido confirmada");
			new GenericTareasPendientesEvent(GenericTareasPendientesEvent.BUSCAR_TAREAS_EVENT,modelo.filtroBusqueda).dispatch();
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
	}
}