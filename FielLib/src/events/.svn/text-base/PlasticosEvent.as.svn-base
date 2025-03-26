package events
{
	import com.tarjetafiel.caja.vo.PlasticoCliente;
	import com.tarjetafiel.caja.vo.PlasticoHistorial;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import flash.events.Event;

	public class PlasticosEvent extends Event
	{
		
		public static const BUSCAR_PLASTICOS_CLIENTE:String = "buscarPlasticosClienteEvent";
		
		public static const BUSCAR_ESTADOS_PLASTICOS:String = "buscarEstadosPlasticosEvent";
		
		public static const BUSCAR_LUGARES_PLASTICOS:String = "buscarLugaresPlasticosEvent";
		
		public static const CAMBIAR_ESTADO_PLASTICO:String = "cambiarEstadoPlasticoEvent"; 
		
		public static const CAMBIAR_LUGAR_PLASTICO:String = "cambiarLugarPlasticoEvent";
		
		public var idCliente:Number;
		
		public var filtro:Filtro;
		
		public var plasticoPlastico:PlasticoCliente;
		
		public var plasticoHistorial:PlasticoHistorial;
		
		public function PlasticosEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}