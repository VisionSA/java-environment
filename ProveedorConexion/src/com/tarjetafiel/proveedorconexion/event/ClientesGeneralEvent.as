package com.tarjetafiel.proveedorconexion.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;

	public class ClientesGeneralEvent extends CairngormEvent
	{
		private var _filtro:Filtro;
		private var _cliente:ClienteTransaccion;
		public var paginador:Paginador;
		
		public static var BUSCAR_CLIENTES:String = "buscarClientes";
		public static var BUSCAR_CLIENTE_TITULAR:String = "buscarClienteTitular";
		public static var BUSCAR_CLIENTES_CUENTA:String = "buscarClientesCuenta";
		public static var ELIMINAR_CLIENTES:String = "eliminarClientes";
		public static var BUSCAR_LIQUIDACIONES:String = "buscarLiquidacionesCliente";
		public static var BUSCAR_LIQUIDACIONES_DETALLE:String = "buscarLiquidacionesDetalleCliente";
		public static var BUSCAR_FUTUROS_VENCIMIENTOS:String = "buscarFuturosVencimientosEvent";
		public static var BUSCAR_HIST_EST_COBR_Y_COM_EVENT:String = "BuscarHistEstCobrYComEvent";
		public static var BUSCAR_HIST_ESTADOS_EVENT:String = "BuscarHistEstadoEvent";
		public static var BUSCAR_HIST_ACCIONES_EVENT:String = "BuscarHistAccionesEvent";
		public static var BUSCAR_HIST_LINEA_CREDITO_EVENT:String = "BuscarHistLineaCreditoEvent";
		public static var BUSCAR_TRAMITES_EVENT:String = "BuscarTramitesEvent";
		public static var BUSCAR_DIGITALES_EVENT:String = "BuscarDigitalesEvent";
		public static var BUSCAR_RECLAMOS_EVENT:String = "BuscarReclamosEvent";
		public static var GRABAR_LINEACREDITO_EVENT:String = "GrabarLineaCreditoEvent";
		public static var BUSCAR_LINEACREDITO_EVENT:String = "BuscarLineaCreditoEvent";
		public static var BUSCAR_REVISIONCREDITO_EVENT:String = "BuscarRevisionCreditoEvent";
		public static var GRABAR_CONTROLCLIENTE_EVENT:String = "GrabarControlClienteEvent";
		public static var BUSCAR_CONTROLCLIENTE_EVENT:String = "BuscarControlClienteEvent";		
		public static var BUSCAR_GESTORCUENTA_EVENT:String = "BuscarGestorCuentaEvent";
		public static var DETALLE_PAGO_GESTION_EVENT:String = "detallePagoGestionEvent";
		public static var BUSCAR_SALDOCUENTA_EVENT:String = "buscarSaldoCuentaEvent";		
		public static var BUSCAR_REFPREP_EVENT:String = "buscarRefRepEvent";
		public static var BUSCAR_CORRIENTE_RESUMEN_EVENT:String = "buscarCorrienteResumenEvent";
		public static var BUSCAR_CORRIENTE_FECHA_EVENT:String = "buscarCorrienteFechaEvent";
		public static var BUSCAR_LINEA_CREDITO_TEMPORAL_EVENT:String = "buscarLineaCredTemporalEvent";
		public static var BUSCAR_BANCO_CENTRAL_EVENT:String = "buscarBancoCentralEvent";
		
		public static var BUSCAR_TITULAR_APP_EVENT:String = "buscarRegistroTitularAppEvent";
		public static var BUSCAR_ADICIONAL_APP_EVENT:String = "buscarRegistroAdicionalAppEvent";
		
		public static var BUSCAR_SERVIDOR_DOCUMENTOS_EVENT:String = "buscarServidorDocumentos";
		public static var BUSCAR_FECHA1_DOCUMENTOS_EVENT:String = "buscarFecha1Documentos";
		
		

		public var idCliente:int;
		
		public var idOperador:int;
		
		public function ClientesGeneralEvent(type:String, filtro:Filtro, paginador:Paginador)
		{
			super(type);
			this._filtro = filtro;
			this.paginador = paginador;
		}
		
		public function get filtro():Filtro{
			return _filtro;
		}
		
		public function get cliente():ClienteTransaccion{
			return _cliente;
		}
		
		public function set cliente(cliente:ClienteTransaccion):void{
			this._cliente = cliente;
		}
		
	}
}