package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.visionis.transaccionador.negocio.Cuota")]
	public class Cuota
	{
		public var nroCuota:int;
		public var cantidadCuota:int;
		public var capital:Number;
		public var interes:Number;
		public var iva:Number;

	}
}