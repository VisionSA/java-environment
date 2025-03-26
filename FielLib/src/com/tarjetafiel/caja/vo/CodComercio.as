package com.tarjetafiel.caja.vo
{	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio")]
	public class CodComercio
	{
		public var idCodComercio:Number;
		public var codigo:String;
		public var codigoPosnet:String;
		public var estado:String;
		public var sucEmpresa:SucEmpresa;
		public var liqCantcierres:Number;
		public var liqDias:Number;
		public var comercioListaPrecioSet:Array;
		public var comercioFormaPagoSet:Array;
		public var ctaCteComercioSet:Array;
		public var loteComercioSet:Array;
		public var transaccionSet:Array;
		public var codComercioActividad:Array;
		public var inscripcionDgr:String;
	//	 Jurisdiccion para verificar el nro de DGR.
		//public var Jurisdiccion jurisdiccion;
	//	 Sucursal donde se registra el cod Comercio.
		//public var SucursalFiel sucursalFiel = null;

		public var url:String;
		public var urlPresenta:String;
		public var debitoAutomatico:String;
		public var presentaCupones:String;	
		
		
		public var nroCupon:String;	
		
	}
}