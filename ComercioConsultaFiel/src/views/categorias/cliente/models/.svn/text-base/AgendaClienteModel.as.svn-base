package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.EventosCliente;
	import com.util.components.alert.AlertWarning;
	import com.util.components.alert.AlertYesNo;
	
	import events.EventosEvent;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.events.CloseEvent;

	[Bindable]
	public class AgendaClienteModel extends  BaseModel
	{
		private var _titular:ClienteTransaccion;
		
		private var _arrayEventosClientes:ArrayCollection;
		
		public var evento:EventosCliente;
		
		
		public function AgendaClienteModel()
		{
					
		}
		
		public function buscarEventos(fechaDesde:Date,fechaHasta:Date):void{
		 	if(_titular == null){
		 		AlertWarning.show("No hay ninguún cliente seleccionado");
		 		return;
		 	}
		 	if(fechaDesde && fechaHasta){
		 		var evt:EventosEvent = new EventosEvent(EventosEvent.BUSCAR_EVENTOS_CLIENTE);
				evt.fechaDesde = fechaDesde;
				evt.fechaHasta = fechaHasta;
				evt.idCliente = _titular.idCliente;
				dispatcher.dispatchEvent(evt);
			} else {
				AlertWarning.show("Debe seleccionar un rango de fechas");
			}
		}
		
		
	   public function set titular(tit:ClienteTransaccion):void{
			_titular = tit;
			if(tit)
			    mostrarPanel =  true;
	   }  	
		
		public function get titular():ClienteTransaccion{
			return _titular;
		}
	 
       public function set arrayEventosClientes(arrayEvt:ArrayCollection):void{
			_arrayEventosClientes = arrayEvt;
	   }  	 
		
		public function get arrayEventosClientes():ArrayCollection{
			return _arrayEventosClientes;
		}
		
		public function guardarEvento(comentario:String):void{
			
			evento.idCliente = _titular.idCliente;
			evento.evento.comentario = comentario
			var evt:EventosEvent;
			if(isNaN(evento.idEventoCliente)){
				evt = new EventosEvent(EventosEvent.GUARDAR_EVENTO);
			} else {
				evt = new EventosEvent(EventosEvent.ACTUALIZAR_EVENTO);
			}
			
			evt.evento = evento;
			dispatcher.dispatchEvent(evt);
			
		}
		
		public function removeEvento():void{
			AlertYesNo.show("¿Confirma eliminar el evento seleccionado?",function (evt:CloseEvent):void{
				
				if(evt.detail == Alert.YES){
					var event:EventosEvent = new EventosEvent(EventosEvent.ELIMINAR_EVENTO);
					event.evento = evento;
					dispatcher.dispatchEvent(event);
				}	
			});
			
			
		}
		
	}
}