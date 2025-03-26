package vo
{
	import mx.collections.ArrayCollection;
			
		[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.ReclamoCabecera")]
	public class ReclamoCabecera
	{
		public var idReclamo:Number;	
		public  var reclamoTipos:ReclamoTipos;
		public var idDestino:Number;		
		public var fechaReclamo:Date;
		public var reclamoCanales:ReclamoCanales;
		public var reclamoFormaResolucion:ReclamoFormaResolucion;
		public var reclamoSucursal:ReclamoSucursales;
		public var reclamoSucursalDestino:ReclamoSucursales;
		public var reclamoIndividuo:ReclamoIndividuo;
		public var reclamoDetalleSet:Array;	
		public var reclamoDocSet:Array;		
		

	}

	
}