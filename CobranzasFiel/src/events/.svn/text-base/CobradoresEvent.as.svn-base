package events
{
	import com.tarjetafiel.caja.vo.Partido;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	
	
	public class CobradoresEvent extends Event
	{
		
		public static const BUSCAR_COBRADORES:String = "BUSCAR_COBRADORES";
		public static const TOMAR_SELECCION_PAIS:String = "TOMAR_SELECCION_PAIS";
		public static const TOMAR_SELECCION_PROVINCIA:String = "TOMAR_SELECCION_PROVINCIA";
		public static const TOMAR_COBRADORES:String = "TOMAR_COBRADORES";		
		public static const GUARDAR_PARTIDOS_MODIFICADOS:String = "GUARDAR_PARTIDOS_MODIFICADOS";		
		
		
		public var filtro:Filtro;
		public var listaPartidosModificados:ArrayCollection;
		public var partidoAActualizar:Partido;
		
		public function CobradoresEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}

	}
}