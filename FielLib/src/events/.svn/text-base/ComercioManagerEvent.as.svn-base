package events
{
	import com.tarjetafiel.caja.vo.Email;
	import com.tarjetafiel.caja.vo.Telefono;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;
	import flash.events.Event;
	


	public class ComercioManagerEvent extends Event
	{   
		public static const BUSCAR_COMERCIO:String = "buscarComercio";
		public static const BUSCAR_COMERCIOS:String = "buscarComerciosManager"; 
		public static const BUSCAR_MOVIMIENTOS_CTA_CTE_COMERCIO:String = "buscarMovimientosCtaCteComercioManager";
		public static const BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_COMERCIO:String = "buscarDetallesMovimientoCtaCteComercioManager";
		public static const OBTENER_SALDO_ANTERIOR_CTA_CTE_COMERCIO:String = "buscarSaldoAnteriorCtaCteComercioManager";
		
/*@F8271*/		public static const BUSCAR_COMPOSICION_DETALLE_CTA_CTE_CLIENTE:String = "buscarComposicionCtaCteComercioManager";
/*@F8271*/	public static const BUSCAR_MOVIMIENTOS_COMP_SALDO_COMERCIO:String = "buscarComposicionComercioManager";
/*@F8271*/ public static const BUSCAR_DETALLES_MOVIMIENTO_REL_COMERCIO:String = "buscarMovimientosRELCtaCteComercioManager";

/*@F8271*/	public static const BUSCAR_CONTABLE_CTA_CTE_COMERCIO:String = "buscarContableComercioManager";

/*@F8271*/	public static const	BUSCAR_DETALLES_MCS_CTA_CTE_COMERCIO:String = "buscarMSCComercioManager";
/*@F8271*/	public static const	BUSCAR_MCS_DETALLE_CTA_CTE_CLIENTE:String = "buscarMSCClienteManager";

/*@F8271*/	public static const	BUSCAR_DETALLES_COM_CTA_CTE_COMERCIO:String = "buscarCOMComercioManager";
/*@F8271*/	public static const	BUSCAR_COM_DETALLE_CTA_CTE_CLIENTE:String = "buscarCOMClienteManager";
		
		public var filtro:Filtro;
		
		public var id:Number;
		public var idComercio:Number;
		public var fechaDesde:Date; 
		public var fechaHasta:Date; 
		public var idTransaccion:Number;
		public var  idConceptoDetalle:Number; 
		public var  tipoConcepto:Number; 
		public var  cantLiqHaciaAtras:int;
		public var  idLiqComercio:Number;
		public var  paginador:Paginador;
		public var  nroCuota:int;
		public var fechaFacturacion:Date; 
		public var idTipoConceptoDetalle:int; 
		public var modo:int;
		public var modoConciliado:int;
/*@I8271*/		public var idCliente:Number; 

		
		
		
/*@I3945*/		public static const AGREGAR_EMAIL:String = "agregarEmailComercioManager";
		public static const AGREGAR_TELEFONO:String = "agregarTelefonoComercioManager";
		public var email:Email;
		public var telefono:Telefono;
		public var idSucEmpresa:Number;
/*@F3945*/		
		
		public function ComercioManagerEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}