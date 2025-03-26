package managers
{
	import com.tarjetafiel.caja.vo.Operador;
	import com.util.components.alert.AlertError;
	
	import events.OperadorEvent;
	
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.utils.Dictionary;
	
	import mx.rpc.Fault;
	
	[Bindable]
	public class PermisosManager extends EventDispatcher
	{
		public var dispatcher:IEventDispatcher;
		
		public var pantallas:Dictionary = new Dictionary(); 
		
		public function PermisosManager()
		{
		}
		
		public function validarPermiso(nombrePantalla:String,operador:Operador):void{
			var evt:OperadorEvent = new OperadorEvent(OperadorEvent.VALIDAR_PERMISO_PANTALLA);
			evt.operador = operador;
			evt.pantalla = nombrePantalla;
			pantallas[nombrePantalla] = false;
			dispatcher.dispatchEvent(evt);
		}
		
		public function resultPermiso(array:Array):void{
			if(array[0].paginaDenegada == null){
				dispatchEvent(new OperadorEvent(array[0].pagina.pagina.toString()+OperadorEvent.PERMISO_HABILITADO));				
			} else {
				dispatchEvent(new OperadorEvent(array[0].paginaDenegada.toString()+OperadorEvent.PERMISO_DESHABILITADO));
			}
		}
		
		public function faultPermisos(fault:Fault):void{
			AlertError.show(fault.faultString);			
		}

	}
}