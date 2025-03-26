package views.categorias
{
	import flash.events.IEventDispatcher;
	
	[Bindable]
	public class EncabezadoModel
	{
		public var alto:Number = 120;
		
		public var labelButtonHeader:String = "Ocultar Busqueda";
		
		public var labelButtonLimpiarHeader:String = "Limpiar cache autocompletar";
		
		
		public var buttonHeaderSelected:Boolean = false;
		
		public var dispatcher:IEventDispatcher;
		
		public function changedSelectedBusqueda():void{
			if(buttonHeaderSelected == false){
				buttonHeaderSelected = true;
				labelButtonHeader = "Mostrar Busqueda";
				alto = 0;
			} else {
				buttonHeaderSelected = false;
				labelButtonHeader = "Ocultar Busqueda";
				alto = 120;
			}
			
			dispatcher.dispatchEvent(new Event("resizeBusqueda"));
		}
	}
}