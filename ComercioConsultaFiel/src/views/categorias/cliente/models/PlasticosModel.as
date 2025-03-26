package views.categorias.cliente.models
{
	
	import com.tarjetafiel.caja.vo.Operador;
	import com.tarjetafiel.caja.vo.PlasticoCliente;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertWarning;
	
	import events.OperadorEvent;
	import events.PlasticosEvent;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.Fault;

	[Bindable]
	public class PlasticosModel extends EventDispatcher
	{  
		public var mostrarPanel :  Boolean= false;	
	   
	    private var _dispatcher:IEventDispatcher;	 
		
		private var  _arrayTitularAdiscionales:ArrayCollection;
		
		public var habilitarCambioEstado:Boolean = false;
		
		public var plasticoEstadosList:ArrayCollection = new ArrayCollection();
		
		public var plasticoLugarList:ArrayCollection = new ArrayCollection();
		
		public var plastico:PlasticoCliente;

		private var _operador:Operador;
		
		public function PlasticoModel():void{
			
		}
		
		
		[Bindable(event="changedArrayTitAdic")]
		public function get arrayTitularAdiscionales():ArrayCollection{
			return _arrayTitularAdiscionales;
		}
		
		public function set arrayTitularAdiscionales(value:ArrayCollection):void{
			_arrayTitularAdiscionales = value;
			mostrarPanel = true;
			dispatchEvent(new Event("changedArrayTitAdic"));
		}
		
		public function resultPermiso(array:Array):void{
			if(array[0].paginaDenegada == null){
				habilitarCambioEstado = true;
			}
		}
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultString);
		}
		
		public function validarPermisoCambioEstado():void{
			var evt:OperadorEvent = new OperadorEvent(OperadorEvent.VALIDAR_PERMISO_PANTALLA);
			evt.operador = operador;
			evt.pantalla = "cambiarEstadoPlasticoFlex";
			dispatcher.dispatchEvent(evt);
		}
		
		public function buscarEstados():void{
			ControlBlock.getInstance().add();
			var evt:PlasticosEvent = new PlasticosEvent(PlasticosEvent.BUSCAR_ESTADOS_PLASTICOS);
			evt.filtro = new Filtro();
			dispatcher.dispatchEvent(evt);
		}
		
		public function buscarLugares():void{
			ControlBlock.getInstance().add();
			var evt:PlasticosEvent = new PlasticosEvent(PlasticosEvent.BUSCAR_LUGARES_PLASTICOS);
			evt.filtro = new Filtro();
			dispatcher.dispatchEvent(evt);
		}  
		
		public function resultEstadosPlastico(array:AsyncToken):void{
			plasticoEstadosList.source = array.result as Array;
			ControlBlock.getInstance().remove();
		}
		
		public function resultLugaresPlastico(array:AsyncToken):void{
			plasticoLugarList.source = array.result as Array;
			ControlBlock.getInstance().remove();
		}
		
		public function faultPermisos(fault:Fault):void{
			AlertWarning.show(fault.faultString);
		}					
		
		private var validopermisos:Boolean = false;
		
		public function set dispatcher(value:IEventDispatcher):void{
			_dispatcher = value;			
			dispatchEvent(new Event("changedDispatcher"));
		}
		
		[Bindable (event="changedDispatcher")]
		public function get dispatcher():IEventDispatcher{
			return _dispatcher;
		}
		
		[Bindable(event="operadorChanged")]
		public function get operador():Operador{
			return _operador;
		}
						    
		public function set operador(value:Operador):void{
			_operador = value;	
			if(_operador)
				validarPermisoCambioEstado();		
			dispatchEvent(new Event("operadorChanged"));
		}
		
	}	
		
}