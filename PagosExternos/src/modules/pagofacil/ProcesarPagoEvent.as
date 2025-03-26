package modules.pagofacil
{
	import com.tarjetafiel.caja.vo.ArchivoFarmacia;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;
	
	import flash.events.Event;
	
	import vo.ArchivoCobroExternoNegocio;

	public class ProcesarPagoEvent extends Event
	{
		public static const PROCESAR_LISTA_TIPO_COBRO:String = "procesarListaTipoCobroEvent"
		
		public static const BUSCAR_ARCHIVOS:String = "buscarArchivosPagoFacilEvent"; 
		
		public static const BUSCAR_ARCHIVOS_FARMACIA:String = "buscarArchivosFarmaciaEvent";
		
		public static const PROCESAR_PAGO_FARMACIA:String = "procesarPagoFarmaciaEvent";
		
		public static const PROCESAR_PAGO:String = "procesarPagoPagoFacilEvent";
		
		public static const PROCESAR_LISTA_CUENTAS = "procesarListaCuentasEvent";
		
		public static const OBTENER_FECHA_SERVIDOR = "obtenerFechaServidorEvent";
		
		public var filtro:Filtro;
		
		public var paginador:Paginador;
		
		public var archivo:ArchivoCobroExternoNegocio;
		
		public var archivoFarmacia:ArchivoFarmacia;
		
		public var fecha:Date;
		
		public var idArchivo:Number;
		
		public function ProcesarPagoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}