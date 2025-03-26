package events
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Digital;
	import com.tarjetafiel.caja.vo.Email;
	import com.tarjetafiel.caja.vo.EventosCliente;
	import com.tarjetafiel.caja.vo.GestionCliente;
	import com.tarjetafiel.caja.vo.GestionClienteLog;
	import com.tarjetafiel.caja.vo.Telefono;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;
	
	import flash.events.Event;
	


	public class ClienteManagerEvent extends Event
	{   
		public static const BUSCAR_PLATICO:String = "buscarPlasticoClienteManager";
		public static const BUSCAR_POR_ID :String = "buscarPorIDManager";
		public static const BUSCAR_CLIENTES:String = "buscarClientesManager";
		public static const BUSCAR_POR_DNI:String = "buscarPorDNIManager";
		public static const BUSCAR_TITULAR_ADICIONALES :String = "buscarTitularAdicionalesManager";
		public static const BUSCAR_CLIENTES_LIST:String = "buscarClientesListManager";
		public static const BUSCAR_MOVIMIENTOS_CTA_CTE_CLIENTE:String = "buscarMovimientosCtaCteClienteManager";
		public static const BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_CLIENTE:String = "buscarDetallesMovimientoCtaCteClienteManager";
		public static const BUSCAR_COMPOSICION_DETALLE_CTA_CTE_CLIENTE:String = "buscarComposicionDetalleCtaCteClienteManager";
	    public static const OBTENER_SALDO_ANTERIOR_CTA_CTE:String = "obtenerSaldoAnteriorCtaCteManager";
	    public static const BUSCAR_LIQUIDACIONES_CLIENTES:String = "buscarLiquidacionesClientesManager";
	    public static const OBTENER_SALDO_ANTERIOR_COMPOSICION_SALDO:String = "obtenerSaldoAnteriorComposicionSaldoManager";
	    public static const BUSCAR_COMPOSICIONES_SALDO_CLIENTE:String = "buscarComposicionesSaldoClienteManager";
	    public static const BUSCAR_COMPOSICIONES_SALDO_DETALLE_CLIENTE:String = "buscarComposicionesSaldoDetalleClienteManager";
	    public static const BUSCAR_DETALLES_PAGOS_LIQUIDACIONES_CLIENTES:String = "buscarDetallesPagosLiquidacionesClientesManager";
	    public static const BUSCAR_EVENTOS_CLIENTE:String = "buscarEventosClientesManager";
		public static const BUSCAR_LINEA_TEMPORAL:String = "buscarLineaTemporalManager";
		public static const BUSCAR_REVISTA_BAJA:String = "buscarRevistaBajaManager";
		public static const HACER_REVISTA_BAJA:String = "hacerRevistaBajaManager";
		public static const	REVISTA_BAJA:String = "revistaBajaManager";
		public static const	HACER_BLOQUEO_DESBLOQUE = "hacerBloqueDesbloqueManager";
		public static const	BUSCAR_BLOQUE_DESBLOQUE = "buscarBloqueDesbloqueManager";
		public static const MODIFICAR_EMAIL = "modificarMail";
		public static const ELIMINAR_EMAIL = "eliminarMail";
		public static const HACER_ENVIO_RESUMEN = "haceEnvioResumen";
 		public static const BUSCAR_ENVIO_RESUMEN = "buscarEnvioResumen";
		public static const MODIFICAR_TELEFONO = "modificarTelefono";
		public static const ELIMINAR_TELEFONO = "eliminarTelefono";
		public static const AGREGAR_TELEFONO_LABORAL = "agregarTelefonoLaboral";
		public static const GRABAR_CONTROL_CLIENTE = "grabarControlCliente";
		public static const HACER_DESBLOQUEO_PAGOM = "haceDesvloquePagoM";
		public static const BUSCAR_DESBLOQUEO_REPACTACION = "buscarDesbloqueRepactacion";
		public static const BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_COMERCIO = "buscarDetalleComercioCliente";
		public static const BUSCAR_DETALLES_MOVIMIENTO_PENDIENTE = "buscarDetalleMovimentoPendiente";
		public static const BUSCAR_TRANSACCION_CLI_COM = "buscarTransaccionCliCom";
		public static const BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_COMERCIO_COMP = "buscarDetalleComercioCompos";
		public static const BUSCAR_COMPOSICION_DETALLE_CTA_CTE_CLIENTE_COMP = "buscarDetalleComercioComposCompClienteEvn";
		public static const BUSCAR_DETALLES_MOVIMIENTO_PENDIENTE_COMP = "buscarMovimientoPendientesComp";
		public static const SOLO_ENVIO_RESUMEN = "SoloEnvioResumenEvent";
		public static const BUSCAR_DETALLES_MOVIMIENTO_COMPOS_CLIENTE:String = "buscarDetalleComposCliente";
		public static const BUSCAR_TRANSACCION_CLI_COM_COMP:String = "buscarDetalleComposCompCliente";
		public static const VER_DETALLE_CALCULO_INTERESES = "verDetalleCalculoIntereses";
		public static const HACER_DESBLOQUEO_APP = "hacerBloqueoDesbloqueoApp";
		public static const BUSCAR_DESBLOQUEO_APP = "buscarBloqueoDesbloqueoApp";
		
		
		


		
	    public var filtro:Filtro;
		public var filtro1:Filtro;
		public var nuevoTipoCliente:ClienteTransaccion;
		public var id:Number;
		public var buscarPorPlastico:int; //1 busca por plastico, -1 por otro parametro
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
		public var operador:Number;
		
		
		public static const AGREGAR_EMAIL:String = "agregarEmailManager";
		public static const AGREGAR_TELEFONO:String = "agregarTelefonoManager";
		public static const CARGAR_CMB_TIPO_TEL:String = "cargarCmbTipoTelefonoManager";
		
		public var email:Email;
		public var telefono:Telefono;
		public var idIndividuo:Number;
		public var idMail:Number;
		public var idTelefono:Number;
		public var idSucEmpresa:Number;
		public var arrControlCliente:Array;
		public var evento:EventosCliente;	
		
		
		public function ClienteManagerEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}