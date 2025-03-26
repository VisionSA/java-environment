package model
{
	import com.tarjetafiel.caja.vo.Accion;
	
	import events.AccionEvent;
	
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class AccionModel extends EventDispatcher
	{
		
		public var dispatcher:IEventDispatcher;
		
		public var accionesArray:ArrayCollection;
        
		
		public function AccionModel()
		{
		}
		
		public function buscarAcciones():void{
			dispatcher.dispatchEvent(new AccionEvent(AccionEvent.BUSCAR_ACCIONES));
		}

	}
}