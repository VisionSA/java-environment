package views.models
{
	import com.tarjetafiel.caja.vo.CodComercio;
	
	import events.ComercioEvent;
	
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class BusquedaComercioViewModel
	{
		public var dispatcher:IEventDispatcher;
		
		
		
		
		private var _comercioSeleccionado:CodComercio; 
		
		
		private var _comercios:ArrayCollection;
		
		public function BusquedaComercioViewModel()
		{
			
		}
		
		public function buscarComercio(cuit:String,razonSocial:String,sucursal:String):void{
			var evt:ComercioEvent = new ComercioEvent(ComercioEvent.BUSCAR_COMERCIOS);
			evt.cuit = cuit;
			evt.razonSocial =razonSocial ;
			evt.sucursal = sucursal;
			dispatcher.dispatchEvent(evt);
		}
		
		
		public function set comercios(array:ArrayCollection):void{
			_comercios = array;
		}
		
		public function get comercios():ArrayCollection{
			return _comercios;
		}
		
		public function set comercioSeleccionado(comercio:CodComercio):void{
			_comercioSeleccionado = comercio;
			
		}
		
		public function get comercioSeleccionado():CodComercio{
			return _comercioSeleccionado;
		}
	}	

}