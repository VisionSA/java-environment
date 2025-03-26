package model
{
	import com.tarjetafiel.caja.vo.TareaPendiente;
	
	import events.TareasPendientesEvent;
	
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class TareasPendientesModel extends EventDispatcher
	{
		
		public var dispatcher:IEventDispatcher;
		
		public var tareasPendientesArray:ArrayCollection;
		
		public var detallesTareasPendientes:ArrayCollection;
        
		
		public function TareasPendientesModel()
		{
		}
		
		public function buscarTareasPendientes():void{
			dispatcher.dispatchEvent(new TareasPendientesEvent(TareasPendientesEvent.BUSCAR_TAREAS_PENDIENTES));
		}
		
		public function buscarDetallesDeTarea(tp:TareaPendiente):void {
			var ev:TareasPendientesEvent = new TareasPendientesEvent(TareasPendientesEvent.BUSCAR_DETALLES_TAREA_PENDIENTE);
			ev.tareaPendiente = tp;
			dispatcher.dispatchEvent(ev);
		}
		
		

	}
}