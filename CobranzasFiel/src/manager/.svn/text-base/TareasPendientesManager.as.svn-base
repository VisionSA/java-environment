package manager
{
	import events.TareasPendientesEvent;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class TareasPendientesManager extends ManagerGenerico
	{
		
		public var tareasPendientesArray:ArrayCollection;
		public var detallesTareasPendientes:ArrayCollection;
		
		public function TareasPendientesManager()
		{
			
		}
		
		public function resultBuscarTareasPendientes(array:Array):void{
			if(!tareasPendientesArray){
				tareasPendientesArray = new ArrayCollection();
			}
			
			tareasPendientesArray.source = array;
			var ev:TareasPendientesEvent = new TareasPendientesEvent(TareasPendientesEvent.REFRESCAR_LISTA);
			dispatcher.dispatchEvent(ev);
		}
		
		public function resultBuscarDetallesTareasPendientes(array:Array):void {
			if (!detallesTareasPendientes) {
				detallesTareasPendientes = new ArrayCollection();
			}
			detallesTareasPendientes.source = array;
			var ev:TareasPendientesEvent = new TareasPendientesEvent(TareasPendientesEvent.MOSTRAR_DETALLES_TAREA_PENDIENTE);
			dispatcher.dispatchEvent(ev);
		}

	}
}