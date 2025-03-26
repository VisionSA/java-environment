package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.PlasticoCliente;
	import com.tarjetafiel.caja.vo.PlasticoEstado;
	import com.tarjetafiel.caja.vo.PlasticoHistorial;
	import com.tarjetafiel.caja.vo.PlasticoLugar;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	import com.util.components.alert.AlertWarning;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
/*@I5394*/	import flash.utils.getTimer;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.controls.Alert;
	import mx.events.DynamicEvent;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	import views.categorias.cliente.maps.PlasticoModelMap;
/*@F5394*/	
	[Bindable]
	public class PlasticosXClienteModel extends EventDispatcher
	{
		
		private var ro:RemoteObject = new RemoteObject();
		
		private var roPlastico:RemoteObject = new RemoteObject();
		
		public var cliente:ClienteTransaccion; 
		
		public var historialList:ArrayCollection = new ArrayCollection();
		
		public var plastico:PlasticoCliente;
		
		public var plasticoEstadoActual:PlasticoEstado;
		
		public var plasticoLugarActual:PlasticoLugar;

		private var roHistorial:RemoteObject = new RemoteObject();
		
		public var plasticosModel:PlasticosModel;
		
		public var indexViewCambioEstadoLugar:int = 0;
		
		public var titleCambio:String;
		
		public function PlasticosXClienteModel()
		{
			ro.destination = "GenericDestination";
			ro.source = "clienteTransaccionServiceTarget";
			
			roPlastico.destination = "GenericDestination";
			roPlastico.source = "plasticoClienteServiceTarget";
			
			roHistorial.destination = "GenericDestination";
			roHistorial.source = "plasticoHistorialServiceTarget"; 
		}
		
		public function buscarPlasticosCliente(cliente:ClienteTransaccion):void{
			this.cliente = cliente; 
/*@I5394*/			ro.buscarPlasticosCliente.addEventListener(FaultEvent.FAULT,onFault);
/*@F5394*/			ro.buscarPlasticosCliente.addEventListener(ResultEvent.RESULT, onResultBusquedaPlastico);
			ro.buscarPlasticosCliente(cliente.idCliente);
		}
		
/*@I5394*/		public function onResultBusquedaPlastico(result:ResultEvent):void{
			cliente.plasticoClienteSet = new ArrayCollection(result.result as Array);
			
			/* *********Ordena los plasticos************* */
			/* Create the SortField object for the "data" field in the ArrayCollection object, and make sure we do a numeric sort. */
			var dataSortField:SortField = new SortField();
			dataSortField.name = "vigenciaDesdeFlex";
			dataSortField.descending = true;
			
			/* Create the Sort object and add the SortField object created earlier to the array of fields to sort on. */
			var dateDataSort:Sort = new Sort();
			dateDataSort.fields = [dataSortField];
			
			/* Set the ArrayCollection object's sort property to our custom sort, and refresh the ArrayCollection. */
			cliente.plasticoClienteSet.sort = dateDataSort;
			cliente.plasticoClienteSet.refresh();
			/* **************************************** */
			
			ro.removeEventListener(FaultEvent.FAULT,onFault);
/*@F5394*/			ro.removeEventListener(ResultEvent.RESULT, onResultBusquedaPlastico);
		}			
		
/*@I5394*/		public function onFault(fault:FaultEvent):void{
			AlertError.show(fault.fault.faultString);
			ro.removeEventListener(FaultEvent.FAULT,onFault);
/*@F5394*/			ro.removeEventListener(ResultEvent.RESULT, onResultBusquedaPlastico);
		}
		
		public function buscarHistorial(plastico:PlasticoCliente):void{
			this.historialList.removeAll();
			roPlastico.buscarPlasticoHistorialPlastico.addEventListener(ResultEvent.RESULT, resultBusquedaHistorial);
/*@I5394*/			roPlastico.buscarPlasticoHistorialPlastico.addEventListener(FaultEvent.FAULT, onFault);
			roPlastico.buscarPlasticoHistorialPlastico(plastico);
		}
		
		public function resultBusquedaHistorial(result:ResultEvent):void{
			this.historialList.removeAll();			
			roPlastico.buscarPlasticoHistorialPlastico.removeEventListener(ResultEvent.RESULT, resultBusquedaHistorial);
/*@F5394*/			roPlastico.buscarPlasticoHistorialPlastico.removeEventListener(FaultEvent.FAULT, onFault);
			historialList.source = result.result as Array;
			ordenarHistorial();
			
		}
		
		public function cambiarDeEstado(estado:PlasticoEstado):void{
			
			if(estado == null){
				AlertWarning.show("Debe seleccionar un estado.");
				return;
			}
			
			if(plastico.estadoPlastico.idPlasticoEstado == estado.idPlasticoEstado){
				AlertWarning.show("El estado al que cambia debe ser distinto al actual.");
				return;
			}
			
			plastico.estadoPlastico = estado;
/*@I4223*/			plastico.fechaEstado = new Date();
/*@F4223*/			
			var plasticoHistorial:PlasticoHistorial = new PlasticoHistorial();
			plasticoHistorial.plasticoCliente = plastico;			
			plasticoHistorial.operador = plasticosModel.operador;
			plasticoHistorial.plasticoLugar = plastico.plasticoLugar;
			plasticoHistorial.plasticoEstado = plastico.estadoPlastico;
			var ask:AsyncToken = roHistorial.guardarPlasticoHistorialFlex(plasticoHistorial);
			ask.addResponder(responderCambioEstado);			
			ControlBlock.getInstance().add();	
		}
		
		public function cambiarDeLugar(lugar:PlasticoLugar):void{
			
			if(lugar == null){
				AlertWarning.show("Debe seleccionar un lugar");
				return;
			}
			
			if(plastico.plasticoLugar.idPlasticoLugar == lugar.idPlasticoLugar){
				AlertWarning.show("El lugar al que cambia debe ser distinto al actual.");
				return;
			}
			
			plastico.plasticoLugar = lugar;
			
			var plasticoHistorial:PlasticoHistorial = new PlasticoHistorial();
			plasticoHistorial.plasticoCliente = plastico;
			plasticoHistorial.operador = plasticosModel.operador;
			plasticoHistorial.plasticoLugar = plastico.plasticoLugar;
			plasticoHistorial.plasticoEstado = plastico.estadoPlastico;
			var ask:AsyncToken = roHistorial.guardarPlasticoHistorialFlex(plasticoHistorial);
			ask.addResponder(responderCambioLugar);			
			ControlBlock.getInstance().add();	
		}
		
		private var responderCambioLugar:Responder = new Responder(resultCambioLugar, faultCambioLugar);
		
		private function faultCambioLugar(fault:Object):void{
			
			ControlBlock.getInstance().remove();	
			AlertError.show(fault.fault.faultString);
			plastico.plasticoLugar = plasticoLugarActual;
			
		}
		
		private function resultCambioLugar(resul:Object):void{

			ControlBlock.getInstance().remove();
			AlertOk.show("El lugar del plástico se cambió con éxito");
			historialList.addItem(resul.result);
			plasticosModel.dispatcher.dispatchEvent(new Event("closePopUpCambioEstadoEvent"));
			ordenarHistorial();
			
		}
		
		private var responderCambioEstado:Responder = new Responder(resultCambioEstado, faultCambioEstado);
		
		private function faultCambioEstado(fault:Object):void{
			
			ControlBlock.getInstance().remove();	
			AlertError.show(fault.fault.faultString);
			plastico.estadoPlastico = plasticoEstadoActual;
			
		}
		
		private function resultCambioEstado(resul:Object):void{

			ControlBlock.getInstance().remove();
			AlertOk.show("El estado del plástico se cambió con éxito");
			historialList.addItem(resul.result);
			plasticosModel.dispatcher.dispatchEvent(new Event("closePopUpCambioEstadoEvent"));
			ordenarHistorial();
			
		}
		
		public function cambiarEstadoPlastico(plastico:PlasticoCliente):void{
/*@I5394*/			var lugarActual:PlasticoLugar = plastico.plasticoLugar;
			if (lugarActual.accion != PlasticoLugar.HABILITA && lugarActual.idPlasticoLugar != 8){
				AlertWarning.show("El plástico se encuentra en el lugar: " + lugarActual.descripcion + " y no permite el cambio de estado");
				return;
			}
			
			if (plastico.estadoPlastico.idPlasticoEstado == 2)//Desactivado
			{
				var fechaActual:Date = new Date();
				if (lugarActual.accion == PlasticoLugar.HABILITA || lugarActual.idPlasticoLugar == 8){
					//Falta para la activacion
					if (plastico.vigenciaDesdeFlex.getTime() > fechaActual.getTime())
					{
						AlertWarning.show("El plastico no se puede cambiar de estado porque la fecha de vigencia desde es posterior a la fecha actual");
						return;
					}
					//Plastico Vencido
					if (plastico.vigenciaHastaFlex.getTime() < fechaActual.getTime())
					{
						AlertWarning.show("El plastico no se puede cambiar de estado porque la fecha de vigencia hasta es anterior a la fecha actual");
						return;
					}
				}
			}
			
			indexViewCambioEstadoLugar = 0;
			titleCambio = "Cambiar Estado Plástico";
			var evt:DynamicEvent = new DynamicEvent("addPopUpCambioEstadoEvent");
			evt.data = this;
			this.plastico = plastico;
/*@F5394*/
			for each(var plas:PlasticoEstado in plasticosModel.plasticoEstadosList){
				if(plas.idPlasticoEstado == plastico.estadoPlastico.idPlasticoEstado){
					this.plasticoEstadoActual = plas;
					break;
				}
			}
			plasticosModel.dispatcher.dispatchEvent(evt);
		}
		
		public function cambiarLugarPlastico(plastico:PlasticoCliente):void{
			indexViewCambioEstadoLugar = 1;
			titleCambio = "Cambiar Lugar Plástico";
			var evt:DynamicEvent = new DynamicEvent("addPopUpCambioEstadoEvent");
			evt.data = this;
			this.plastico = plastico;
			for each(var plas:PlasticoLugar in plasticosModel.plasticoLugarList){
				if(plas.idPlasticoLugar == plastico.plasticoLugar.idPlasticoLugar){
					this.plasticoLugarActual = plas;
					break;
				}
			}
			plasticosModel.dispatcher.dispatchEvent(evt);
		}
		
		private function ordenarHistorial():void{
			historialList.sort = new Sort();
			var sortField:SortField = new SortField("fechaEstado",false,true);
			historialList.sort.fields = [sortField];
			historialList.refresh(); 
		}
		

	}
}