package events
{
	import com.tarjetafiel.caja.vo.Email;
	import com.tarjetafiel.caja.vo.Telefono;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;
	
	import flash.events.Event;

	public class ComercioEvent extends Event
	{  
		public static const CHANGE_LISTA_PRECIO:String = "CHANGELISTAPRECIO";
		
		public static const BUSCAR_POR_COD_COMERCIO:String = "buscarPorCodigoComercios";
		
		public static const BUSCAR_COMERCIOS:String = "buscarComercios";
		//busca un unico comercio
		public static const BUSCAR_COMERCIO:String = "buscarComercio";
		
		public static const BUSCAR_EMPRESAS:String = "buscarEmpresas";
		
		//franco
		public static const BUSCAR_COMERCIOS_EMPRESAS:String = "buscarEmpresas";
		
		public static const REPORTE_COMERCIO:String = "reporteComercio";
		
		public static const BUSCAR_MOVIMIENTOS_CTA_CTE_COMERCIO:String = "buscarMovimientosCtaCteComercio";
		
		public static const BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_COMERCIO:String = "buscarDetallesMovimientoCtaCteComercio";
		
		public  var codigoPosnet:String;
		
		public  var cuit:String;
		
		public  var razonSocial:String;
		
		public  var sucursal:String;
		
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
		
		public static const AGREGAR_EMAIL:String = "agregarEmailComercioEvent";
		public static const AGREGAR_TELEFONO:String = "agregarTelefonoComercioEvent";
		public static const CARGAR_CMB_TIPO_TEL:String = "cargarCmbTipoTelefonoComercioEvent";
		public static const RECARGAR_DATOS_COMERCIO:String = "recargarDatosComercioEvent";
		public static const RESULT_CARGAR_CMB_TIPO_TEL:String = "resultCargarCmbTipoTelefonoComercioEvent";
		public var email:Email;
		public var telefono:Telefono;
		public var idSucEmpresa:Number;
		public var tipoTelefonosArray:Array;
		   
		public function ComercioEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
		
	}
}
