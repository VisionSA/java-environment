package views.categorias.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.EventosCliente;
	import com.tarjetafiel.caja.vo.EventosComercio;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertWarning;
	import com.util.components.alert.AlertYesNo;
	
	import events.EventosEvent;
	
	import flash.display.DisplayObject;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.controls.ToggleButtonBar;
	import mx.core.Application;
	import mx.events.CloseEvent;
	
	import views.categorias.cliente.models.BaseModel;
	import views.categorias.modules.AgendaModule;

//	import views.categorias.comercio.models.AgendaComercioModel;

	[Bindable]
	public class AgendaModel extends EventDispatcher
	{
		//
		public var mostrarPanel :  Boolean= false;
		public var dispatcher:IEventDispatcher;
		private var _isSelectedCliente:Boolean;
		
		private var _titular:ClienteTransaccion;
		private var _comercio:CodComercio;
		
		public var _arrayEventos:ArrayCollection;
		public var evento:Object;
		
/*@I4053*/		public var arrayOperadores:ArrayCollection;
/*@F4053*/		
		//constructor
		public function AgendaModel()
		{

		}
		
		public function set titular(tit:ClienteTransaccion):void{
			_titular = tit;
			mostrarPanel =  showPanelAgenda();
		}  	
		
		public function get titular():ClienteTransaccion{
			return _titular;
		}
		
		public function set arrayEventos(arrayEvt:ArrayCollection):void{
			_arrayEventos = arrayEvt;
		}  	 
		
		public function get arrayEventos():ArrayCollection{
			return _arrayEventos;
		}
		
		public function set comercio(tit:CodComercio):void{
			_comercio = tit;
			mostrarPanel =  showPanelAgenda();
		}  	
		
		public function get comercio():CodComercio{
			return _comercio;
		}
		
		public function set isSelectedCliente(flag:Boolean):void{
			_isSelectedCliente = flag;
		}  	
		
		public function get isSelectedCliente():Boolean{
			var app:Object = Application.application;
			var obj:ToggleButtonBar = ToggleButtonBar(app.view.selectedChild.tbbCategorias);
			return obj.selectedIndex == 0 ? true : false;
		}
	
		private function showPanelAgenda():Boolean{
			var result:Boolean = false;
			var esCli:Boolean = isSelectedCliente;
			if(_titular != null && esCli){
				result = true;
			}
			if(_comercio != null && !esCli){
				result = true;
			}
			
			return result;
		}
		public function verificarMostrarAgenda():void{
			_arrayEventos = new ArrayCollection();
			this.mostrarPanel =  showPanelAgenda();
		}
		//comienzan los metodos comunes
		public function guardarEvento(comentario:String):void{
			if(evento is EventosCliente){
				evento.idCliente = _titular.idCliente;
				evento.evento.comentario = comentario
				var evt:EventosEvent;
				if(isNaN(evento.idEventoCliente)){
					evt = new EventosEvent(EventosEvent.GUARDAR_EVENTO);
				} else {
					evt = new EventosEvent(EventosEvent.ACTUALIZAR_EVENTO);
				}
				evt.evento = EventosCliente(evento);
				dispatcher.dispatchEvent(evt);
			}else{
				evento.idComercio = _comercio.idCodComercio;
				evento.evento.comentario = comentario;
				var evt:EventosEvent;
				if(isNaN(evento.idEventoComercio)){
					evt = new EventosEvent(EventosEvent.GUARDAR_EVENTO_COMERCIO);
				} else {
					evt = new EventosEvent(EventosEvent.ACTUALIZAR_EVENTO_COMERCIO);
				}
				evt.eventoCom = EventosComercio(evento);
				dispatcher.dispatchEvent(evt);
			}
		}
		
		public function removeEvento():void{
			AlertYesNo.show("¿Confirma eliminar el evento seleccionado?",function (evt:CloseEvent):void{
				if(evt.detail == Alert.YES){
					if(evento is EventosCliente){
						var event:EventosEvent = new EventosEvent(EventosEvent.ELIMINAR_EVENTO);
						event.evento = EventosCliente(evento);
						dispatcher.dispatchEvent(event);
					}else{
						var event:EventosEvent = new EventosEvent(EventosEvent.ELIMINAR_EVENTO_COMERCIO);
						event.eventoCom = EventosComercio(evento);
						dispatcher.dispatchEvent(event);
					}
				}
			});
		}
		
		public function nuevoEvento():void{
			if(isSelectedCliente){
				evento = new EventosCliente();
			}else{
				evento = new EventosComercio();
			}
		}
		
		public function buscarEventos(fechaDesde:Date,fechaHasta:Date):void{
/*@I4053*/			if (fechaDesde.getTime() < fechaHasta.getTime())
			{
				if(isSelectedCliente){
					buscarEventosCliente(fechaDesde,fechaHasta);
				}else{
					buscarEventosComercio(fechaDesde,fechaHasta);
				}
			}else{
				Alert.show("Fecha Desde debe ser menor a Fecha Hasta");
/*@F4053*/			}
		}
		//Comienzan los metodos de cliente
		public function buscarEventosCliente(fechaDesde:Date,fechaHasta:Date):void{
			if(_titular == null){
				AlertWarning.show("No hay ningún cliente seleccionado");
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
		//Comienzan los metodos de comercio
		public function buscarEventosComercio(fechaDesde:Date,fechaHasta:Date):void{
			if(_comercio == null){
				AlertWarning.show("No hay ningún comercio seleccionado");
				return;
			}
			if(fechaDesde && fechaHasta){
				var evt:EventosEvent = new EventosEvent(EventosEvent.BUSCAR_EVENTOS_COMERCIO);
				evt.fechaDesde = fechaDesde;
				evt.fechaHasta = fechaHasta;
				evt.idComercio = _comercio.idCodComercio;
				dispatcher.dispatchEvent(evt);
			} else {
				AlertWarning.show("Debe seleccionar un rango de fechas");
			}
		}
		
		
/*@I4053*/		/**
		 * @id 4053
		 * lanza el evento para cargar el combo de Operadores
		 **/
		public function initComboOperadores():void {
			if(this.arrayOperadores == null || this.arrayOperadores.length == 0)
			{
				var evt:EventosEvent;
				evt = new EventosEvent(EventosEvent.CARGAR_CMB_OPERADORES);
				
				dispatcher.dispatchEvent(evt);
				
				ControlBlock.getInstance().add();
			}
		}
		/**
		 * @id 4053
		 * Recibe la lista de operadores
		 **/
		public function resultCargarCmbOperadores(result:Array):void {
			if(result != null){
				this.arrayOperadores = new ArrayCollection(result);
			}else{
				AlertError.show("No se pudieron cargar los Operadores, intente mas tarde");
			}
			
			ControlBlock.getInstance().remove();
		}
/*@F4053*/		
	}
}