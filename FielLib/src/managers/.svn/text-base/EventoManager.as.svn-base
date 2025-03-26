package managers
{
	import com.tarjetafiel.caja.vo.EventosCliente;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	import events.ClienteManagerEvent;
	import managers.ClienteManager;
	import events.ClienteEvent;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.Fault;
	
	[Bindable]
	public class EventoManager 
	{
		public var dispatcher:IEventDispatcher;
	[Bindable]	public var arrayEventos:ArrayCollection= new ArrayCollection() ;
	
[Bindable] var clienteM:ClienteManager; 

		public function EventoManager()
		{
		}
		
		
      public function resultBuscarEventos(arrayEventos:Array):void{
	   	   if(arrayEventos.length == 0){
	   	   		AlertOk.show("No se encontraron eventos");
	   	   }
	   	   this.arrayEventos = new ArrayCollection(arrayEventos);
	  }
		
public function resultBuscarTodosEventos(arrayEventos:Array):void{
	if(arrayEventos.length == 0){
		
	}
		this.arrayEventos = new ArrayCollection(arrayEventos);
}
		
	  
	  public function resultGuardarEvento(evento:Object):void{
		  arrayEventos.addItemAt(evento,0);
		  arrayEventos = arrayEventos;
	  	 // AlertOk.show("El evento fue registrado con éxito");
		  
		  
		  
	  }
	  public function resultGuardarBolqueoEvento(evento:Object):void{
		  arrayEventos.addItemAt(evento,0);
		  arrayEventos = arrayEventos;
		  AlertOk.show("El evento fue registrado con éxito");
		  
		  var eventoCliente:EventosCliente; 
		  eventoCliente = evento as EventosCliente;
		  
		  /*var eventoCliente:EventosCliente; 
		  eventoCliente = evento as EventosCliente;
		  
		  AlertOk.show("resultGuardarBolqueoEvento oper " + eventoCliente.evento.operador.codigo);
		  clienteM.haceBloqueDesbloqueo(eventoCliente.evento.operador.codigo);*/
		  
		/*var evt:ClienteManagerEvent = new ClienteManagerEvent(ClienteManagerEvent.BUSCAR_BLOQUE_DESBLOQUE);
		  evt.idCliente =  eventoCliente.idCliente; 
		  this.dispatcher.dispatchEvent(evt);*/
		  
		  //this.dispatcher.dispatchEvent(new Event("limpiarDatosCliente"));
		  
		  /*this.clienteM.buscarTitularAdicionales(eventoCliente.idCliente.toString(),"busqPorNroCuenta",eventoCliente.idCliente);*/
		  
		  
		  
	  }
	  
	  public function resultEliminarEvento(evento:Object):void{
	  	
	  	for each(var o:Object in arrayEventos){
			if(evento is EventosCliente){
				if(o.idEventoCliente == evento.idEventoCliente){
		  			arrayEventos.removeItemAt(arrayEventos.getItemIndex(o));
		  			arrayEventos = arrayEventos;
		  			break;
		  		}
			}else{
				if(o.idEventoComercio == evento.idEventoComercio){
					arrayEventos.removeItemAt(arrayEventos.getItemIndex(o));
					arrayEventos = arrayEventos;
					break;
				}
			}
	  		
	  	}

	  	Alert.show("El evento se eliminó con éxito");
	  }	  
	  
	  public function resultActualizarEvento():void{
	  	  AlertOk.show("El evento fue actualizado con éxito");
	  }
	  
      public function fault( fault:Fault ):void{
			//ManagerErrors.getInstance().addPopUpError(fault.faultString,fault.faultDetail);
			AlertError.show(fault.faultString);
			//ControlBlock.getInstance().remove();
	  } 
	  
	  public function resetEvento():void{
	  	arrayEventos = new ArrayCollection();
	  }

	}
}