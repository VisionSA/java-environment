package com.tarjetafiel.caja.vo
{
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.fondos.negocio.FormaPagoValor")]
	public class FormaPagoValor
	{
		public var idFormaPagoValor:Number;
		public var descripcion:String;
		public var formaPago:FormaPago;
		public var multiplo:Number;

	}
}