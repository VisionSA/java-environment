package events
{
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.tarjetafiel.caja.vo.Empresa;
	
	import flash.events.Event;

	public class LiquidacionComEvent extends Event
	{
		public static const BUSCAR_LIQUIDACIONES:String = "buscarLiquidacionesEvent";
		
		public var filtro:Filtro;
		
		public function LiquidacionComEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}
