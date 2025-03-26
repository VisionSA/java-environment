package modules.notaCreditoDebitos.events
{
	import com.tarjetafiel.caja.vo.Operador;
	
	import flash.events.Event;

	public class NotaCreditoDebitoEvent extends Event
	{
		public static const BUSCAR_CLIENTE:String = "BUSCAR_CLIENTE_NCND";
		public static const BUSCAR_EMPRESA:String = "BUSCAR_EMPRESA_NCND";
		public static const BUSCAR_CONSUMOS:String = "BUSCAR_CONSUMOS";
		public static const BUSCAR_CONSUMOS_COD_POSNET:String = "BUSCAR_CONSUMOS_COD_POSNET";
		public static const BUSCAR_CONSUMOS_LIQ_CLIENTE:String = "BUSCAR_CONSUMOS_LIQ_CLIENTE";
		public static const BUSCAR_PAGOS:String = "BUSCAR_PAGOS_NCND";
		public static const REALIZAR_NC_CONSUMO:String = "REALIZAR_NC_CONSUMO";
		public static const REALIZAR_ND_PAGO:String = "REALIZAR_ND_PAGO";
		public static const REALIZAR_NC_IVA_CLIENTE:String = "REALIZAR_NC_IVA_CLIENTE";
		public static const REALIZAR_ND_IVA_CLIENTE:String = "REALIZAR_ND_IVA_CLIENTE";
		public static const REALIZAR_ND_SIN_IVA_CLIENTE:String = "REALIZAR_ND_SIN_IVA_CLIENTE";
		public static const REALIZAR_NC_SIN_IVA_CLIENTE:String = "REALIZAR_NC_SIN_IVA_CLIENTE";
		
		public var idCliente:Number;
		
		public var idLiqCliente:Number;
		
		public var codPosnet:Number;
		
		public var idClienteDestino:Number;
		
		public var fechaDesde:Date;
		
		public var fechaHasta:Date;
		
		public var operador:Operador;
		
		public var idTransaccion:Number;
		
		public var importe:Number;
		
		public var estadoImpacto:String;
		
		public function NotaCreditoDebitoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}