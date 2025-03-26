package modules.pagofacil
{
	import com.tarjetafiel.caja.vo.ArchivoFarmacia;
	import com.tarjetafiel.caja.vo.Provincia;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;
	
	import flash.events.Event;
	
	import vo.ArchivoCobroExternoNegocio;
	import vo.ReclamoCabecera;
	import vo.ReclamoDetalle;

	public class ProcesarPagoEvent extends Event
	{
		public static const PROCESAR_LISTA_TIPO_COBRO:String = "procesarListaTipoCobroEvent"
		
		public static const BUSCAR_ARCHIVOS:String = "buscarArchivosPagoFacilEvent"; 
		
		public static const BUSCAR_ARCHIVOS_FARMACIA:String = "buscarArchivosFarmaciaEvent";
		
		public static const PROCESAR_PAGO_FARMACIA:String = "procesarPagoFarmaciaEvent";
		
		public static const PROCESAR_PAGO:String = "procesarPagoPagoFacilEvent";
		
		public static const PROCESAR_LISTA_CUENTAS = "procesarListaCuentasEvent";
		
		public static const PROCESAR_LISTA_RECLAMOS_TIPOS_TOP = "procesarListaTipoTopEvent";
		
/* @I4662 */		public static const OBTENER_FECHA_SERVIDOR = "obtenerFechaServidorEvent";

/* @I8683 */		public static const PROCESAR_LISTA_PROVINCIAS = "listarProvinciasEvent";

/* @I8683 */		public static const PROCESAR_LISTAR_PARTIDOS = "listarPartidosEvent";

/* @I8683 */		public static const PROCESAR_LISTAR_LOCALIDADES = "listarLocalidadEvent";

/* @I8683 */		public static const PROCESAR_BUSCARCLIENTE_INTERESADO = "listarClienteIntEvent";

/* @I8683 */		public static const PROCESAR_LISTAR_BARRIOS = "listarBarriosEvent";

/* @I8683 */		public static const PROCESAR_LISTA_RECLAMOS_TIPOS = "listarRclamosTiposEvent";

/* @I8683 */		public static const GRABAR_RECLAMO = "listarGrabarReclamoEvent";


/* @I8683 */		public static const PROCESAR_BUSCAR_RECLAMO = "listarReclamoEvent";

/* @I8683 */		public static const PROCESAR_LISTA_RECLAMOS_ESTADOS = "listarReclamoEstadoEvent";

/* @I8683 */		public static const PROCESAR_LISTA_RECLAMOS_RESOLUCION = "listarReclamoResolucionEvent";

/* @I8683 */		public static const UPDATE_RECLAMO = "listarUpdateReclamoEvent";

/* @I8683 */		public static const PROCESAR_LISTA_CANALES = "listarCanalesReclamoEvent";

/* @I8683 */		public static const PROCESAR_ENVIO_MAIL = "listarMailReclamoEvent";

/* @I8683 */		public static const PROCESAR_BUSCARCLIENTE_DECLARANTE = "buscarClienteDeclaranteEvent";

/* @I8683 */		public static const PROCESAR_LISTA_SUCURSALES = "buscarSucursalesEvent";

/* @I1 */		public static const PROCESAR_IMPRIMIR_RECLAMO = "imprimirReclamoInicioEvent";

/* @I1 */		public static const PROCESAR_IMPRIMIR_RECLAMO_FIN = "imprimirReclamoFinEvent";

/* @I1 */		public static const PROCESAR_LISTA_RECLAMO_DOC = "listReclamoDocEvent";

/* @I6 */		public static const PROCESAR_BUSCAR_COMERCIO = "listBuscaComercioEvent";





		
		public var filtro:Filtro;
		
		public var paginador:Paginador;
		
		public var archivo:ArchivoCobroExternoNegocio;
		
		public var provincia:Provincia;

		public var archivoReclamo:ReclamoCabecera;

		public var reclamoDetalle:ReclamoDetalle; 

		public var mail:String;

		public var texto:String;

		public var sujeto:String;

		public var msq:String;

		public var msq1:String;

		public var tipoDestino:int;

		
		public var archivoFarmacia:ArchivoFarmacia;
		
		public var fecha:Date;
		
		public var idArchivo:Number;
		
		public var idReclamo:Number;
		
/* @I8683 */	public var idPartido:Number;

/* @I8683 */	public var idLocalidad:Number;

/* @I6 */	public var destino:Number;

public var importe:Number;

public var varTodo:int;
public var varTipo:int;

		
		public function ProcesarPagoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}