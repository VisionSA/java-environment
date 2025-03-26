package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.ArchivoFarmacia")]
	public class ArchivoFarmacia
	{
		public var idArchivo:Number;
	
		public var fechaPago:Date;
		
		public var sucursal:Number;
		
		public var idCliente:Number;
		
		public var importe:Number;
		
		public var fechaVencimiento:Date;
		
		public var nombreArchivo:String;
		
		public var procesado:Number;
		
		public var fechaCarga:Date;

	}
}