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
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.events.DynamicEvent;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
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
			ro.buscarPlasticosCliente.addEventListener(FaultEvent.FAULT,fault);
			ro.buscarPlasticosCliente.addEventListener(ResultEvent.RESULT, resultBusquedaPlastico);
			ro.buscarPlasticosCliente(cliente.idCliente);
		}
		
		public function resultBusquedaPlastico(result:ResultEvent):void{
			cliente.plasticoClienteSet = new ArrayCollection(result.result as Array);
			ro.removeEventListener(FaultEvent.FAULT,fault);
			ro.removeEventListener(ResultEvent.RESULT, resultBusquedaPlastico);
		}			
		
		public function fault(faul:FaultEvent):void{
			AlertError.show(faul.fault.faultString);
			ro.removeEventListener(FaultEvent.FAULT,fault);
			ro.removeEventListener(ResultEvent.RESULT, resultBusquedaPlastico);
		}
		
		public function buscarHistorial(plastico:PlasticoCliente):void{
			this.historialList.removeAll();
			roPlastico.buscarPlasticoHistorialPlastico.addEventListener(ResultEvent.RESULT, resultBusquedaHistorial);
			roPlastico.buscarPlasticoHistorialPlastico.addEventListener(FaultEvent.FAULT, fault);
			roPlastico.buscarPlasticoHistorialPlastico(plastico);
		}
		
		public function resultBusquedaHistorial(result:ResultEvent):void{
			this.historialList.removeAll();			
			roPlastico.buscarPlasticoHistorialPlastico.removeEventListener(ResultEvent.RESULT, resultBusquedaHistorial);
			roPlastico.buscarPlasticoHistorialPlastico.removeEventListener(FaultEvent.FAULT, fault);
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
			indexViewCambioEstadoLugar = 0;
			titleCambio = "Cambiar Estado Plástico";
			var evt:DynamicEvent = new DynamicEvent("addPopUpCambioEstadoEvent");
			evt.data = this;
			this.plastico = plastico;
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