package modules.principal
{
	import com.tarjetafiel.caja.vo.GestionCliente;
	import com.tarjetafiel.caja.vo.GestionClienteLog;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;
	
	import flash.events.Event;
	import flash.utils.ByteArray;
	
	import mx.collections.ArrayCollection;
	

	public class GestionClienteEvent extends Event
	{
				
		public static const BUSCAR_GESTION_CLIENTE = "buscarGestionCliente";
		public static const BUSCAR_GESTION_CLIENTE_BY_ID_OP = "buscarGestionClienteByIdOp";
		public static const BUSCAR_NOMBRE_CLIENTE = "buscarNombreCliente";
		public static const GUARDAR_GESTION_CLIENTE = "guardarGestionCliente";
		public static const GUARDAR_GESTION_CLIENTE_LOG = "guardarGestionClienteLog";
		public static const GUARDAR_LOG = "guardarLog";
		public static const GENERAR_INFORME = "generarInforme";
		
		public static const BUSCAR_GESTION_VIGENTE = "buscarGestionVigente";
		public static const BUSCAR_GESTION_VENCIDA = "buscarGestionVencida";
		public static const BUSCAR_GESTION_CONCONSUMO = "buscarGestionConconsumo";
		public static const BUSCAR_GESTION_SINCONCUMO = "buscarGestionSinconsumo";
		
		
		public var filtro:Filtro;
		public var paginador:Paginador;
		
		public var idCliente:Number;
		public var gestionCliente:GestionCliente;
		public var gestionClienteLog:GestionClienteLog;
		public var log:String;
		public var fDesde:Date;
		public var fHasta:Date;
		
		public var idOperador:Number;
		public var idOp:Number;
		public var idOpInforme:Number;
		
		public function GestionClienteEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}