package model
{
	import com.tarjetafiel.caja.vo.Partido;
	import com.tarjetafiel.caja.vo.Provincia;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import events.CobradoresEvent;
	import events.PaisEvent;
	import events.ProvinciaEvent;
	
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	import views.components.ArrayCollectionSinRepetidos;
	
	[Bindable]
	public class CobradoresModel extends EventDispatcher
	{
		
		public var dispatcher:IEventDispatcher;
		
		public var cobradoresArray:ArrayCollection;
		public var paisesArray:ArrayCollection;
		public var provinciasArray:ArrayCollection;
		public var partidosArray:ArrayCollection;
		
		public var partidosACambiar:ArrayCollectionSinRepetidos;
        
		
		public function CobradoresModel()
		{
		}
		
		public function cancelarCambios():void {
			partidosACambiar  = new ArrayCollectionSinRepetidos();
		}
		
		public function buscarCobradores():void {
			var ev:CobradoresEvent = new CobradoresEvent(CobradoresEvent.BUSCAR_COBRADORES);
			ev.filtro = new Filtro();
			dispatcher.dispatchEvent(ev);
		}
		
		public function buscarPaises():void {					
			var ev:PaisEvent = new PaisEvent(PaisEvent.BUSCAR_PAISES);
			dispatcher.dispatchEvent(ev);
		}
		
		public function buscarProvincias(prov:Provincia):void {
            var ev:ProvinciaEvent = new ProvinciaEvent(ProvinciaEvent.BUSCAR_PROVINCIA);			
			ev.provincia = prov;
			dispatcher.dispatchEvent(ev);
		}
		
		public function listarPartidosXProvincia(prov:Provincia) {
			var ev:ProvinciaEvent = new ProvinciaEvent(ProvinciaEvent.BUSCAR_PARTIDOS_X_PROVINCIA);
			ev.provincia = prov;
			dispatcher.dispatchEvent(ev);
		}
		
		public function agregarPartidoAActualizar(part:Partido):void {
			if (!partidosACambiar) {
				partidosACambiar = new ArrayCollectionSinRepetidos;
			}
			partidosACambiar.agregarElemento(part);
		}
		
		public function guardarPartidosModificados():void {
			for each (var item in  partidosACambiar.getArrayCollection())
			{
			    var ev:CobradoresEvent = new CobradoresEvent(CobradoresEvent.GUARDAR_PARTIDOS_MODIFICADOS);
				ev.partidoAActualizar = item as Partido;
				dispatcher.dispatchEvent(ev);
			}
		}

	}
}