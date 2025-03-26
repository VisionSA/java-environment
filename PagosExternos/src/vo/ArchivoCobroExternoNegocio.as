package vo
{
			
		[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.ArchivoCobroExternoNegocio")]
	public class ArchivoCobroExternoNegocio
	{
		public var idArchivo:Number;
		
		public var tipoCobro:Number;
	
		public var fecha:Date;
		
		public var nombreOrigen:String;
		
		public var nombreCliente:String;
		
		public var cantTransacciones:Number;
		
		public var montoTotal:Number;
		
		public var nombreArchivo:String;
		
		//public var lotePagoFacilSet:Array;
		
		public var procesado:String;

	}

	
}