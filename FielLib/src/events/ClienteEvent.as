package events
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Digital;
	import com.tarjetafiel.caja.vo.Email;
	import com.tarjetafiel.caja.vo.GestionCliente;
	import com.tarjetafiel.caja.vo.GestionClienteLog;
	import com.tarjetafiel.caja.vo.Telefono;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;
	
	import flash.events.Event;

	public class ClienteEvent extends Event
	{  
		public static const BUSCAR_POR_PLASTICO :String = "buscarPorPlastico"; 
		public static const BUSCAR_POR_ID :String = "buscarPorID";
		public static const BUSCAR_POR_DNI:String = "buscarPorDNI";
	    public static const BUSCAR_CLIENTES:String = "buscarClientes";
		public static const BUSCAR_TITULAR_ADICIONALES :String = "buscarTitularAdicionales";
		public static const BUSCAR_CLIENTES_LIST:String = "buscarClientesList";
		public static const BUSCAR_MOVIMIENTOS_CTA_CTE_CLIENTE:String = "buscarMovimientosCtaCteCliente";
		public static const BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_CLIENTE:String = "buscarDetallesMovimientoCtaCteCliente";
		public static const BUSCAR_COMPOSICION_DETALLE_CTA_CTE_CLIENTE:String = "buscarComposicionDetalleCtaCteCliente";
	    public static const OBTENER_SALDO_ANTERIOR_CTA_CTE:String = "obtenerSaldoAnteriorCtaCte";
	    public static const OBTENER_SALDO_ANTERIOR_COMPOSICION_SALDO:String = "obtenerSaldoAnteriorComposicionSaldo";
	    public static const BUSCAR_COMPOSICIONES_SALDO_CLIENTE:String = "buscarComposicionesSaldoCliente";
	    public static const BUSCAR_COMPOSICIONES_SALDO_DETALLE_CLIENTE:String = "buscarComposicionesSaldoDetalleCliente";
	 	public static const BUSCAR_LIQUIDACIONES_CLIENTES:String = "buscarLiquidacionesClientes";
	 	public static const BUSCAR_DETALLES_PAGOS_LIQUIDACIONES_CLIENTES:String = "buscarDetallesPagosLiquidacionesClientes";
	 	public static const RESET_CLIENTES:String = "resetClientesEvent";
		public static const BUSCAR_CLIENTES_PARM:String = "buscarClintesParmEvent";
		public static const AGREGAR_TELEFONO_LABORAL:String = "agregarTelefonoLaboralEvent";
		public static const DESBLOQUEO_PAGOMINIMO:String = "desbloquePagoMinimoEvent";
		public static const BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_COMERCIO:String = "buscarDetalleMovimientoComercioEvent";
		public static const BUSCAR_DETALLES_MOVIMIENTO_PENDIENTE:String = "buscarDetallePendienteEvent";
		public static const BUSCAR_TRANSACCION_CLI_COM:String = "buscarTransaccionCliComEvent";
		public static const BUSCAR_DETALLES_MOVIMIENTO_COMPOS_CLIENTE:String = "buscarDetalleComposEvent";
		public static const BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_COMERCIO_COMP:String = "buscarDetalleComercioComposEvent";
		public static const BUSCAR_TRANSACCION_CLI_COM_COMP:String = "buscarDetalleComercioComposCompEvent";
		public static const BUSCAR_COMPOSICION_DETALLE_CTA_CTE_CLIENTE_COMP:String = "buscarDetalleComercioComposCompClienteEvent";
		public static const REVISTA_BAJA:String = "revistaBajaEvent";
		public static const BLOQUEO_DESBLOQUEO:String = "bloqueoDesbloqueoEvent";
		public static const VER_DETALLE_CALCULO_INTERESES = "verDetalleCalculoIntereses";
		
		

	 	public var filtro:Filtro;
	    public var nroTarjeta:String ;
		public var codSeguridad : String ;	
		public var dni:String;
		public var nuevoTipoCliente:ClienteTransaccion;
		public var id:Number;
		public var idMail:Number;
		public var idTelefono:Number;
		public var idSucEmpresa:Number;
		public var paramBusqueda:String ; 
		public var operador:Number;
		public var tipoBusqueda:String ;
		public var idCliente:Number;
		public var fechaDesde:Date; 
		public var fechaHasta:Date; 
		public var idTransaccion:Number;
		public var idConceptoDetalle:Number; 
		public var tipoConcepto:Number; 
		public var cantLiqHaciaAtras:int;
		public var idLiqCliente:Number;
		public var paginador:Paginador;
		public var nroCuota:int;
		public var fechaFacturacion:Date; 
		public var idTipoConceptoDetalle:int; 
		public var modo:int;
		public var modoConciliado:int;

		
		public static const AGREGAR_EMAIL:String = "agregarEmailEvent";
		public static const MODIFICAR_EMAIL:String = "modificarEmailEvent";
		public static const ELIMINAR_EMAIL:String = "eliminarEmailEvent";
		public static const REALIZAR_ENVIO_RESUMEN:String = "realizarEnvioResumenEvent";
		public static const AGREGAR_TELEFONO:String = "agregarTelefonoEvent";
		public static const CARGAR_CMB_TIPO_TEL:String = "cargarCmbTipoTelefonoEvent";
		public static const MODIFICAR_TELEFONO:String = "modificarTelefonoEvent";
		public static const ELIMINAR_TELEFONO:String = "eliminarTelefonoEvent";
		
		
		public static const DESBLOQUEAR_PAGO:String = "desbloquearPagoEvent";
		public static const DESBLOQUEAR_APP:String = "desbloquearAppEvent";
		
		
		public var email:Email;
		public var telefono:Telefono;
		public var idIndividuo:Number;		
		
		
		
		public function ClienteEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
		
	}
}