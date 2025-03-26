package com.tarjetafiel.caja.vo
{
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.fondos.negocio.ChequeEstado")]
	public class ChequeEstado
	{
		public var idChequeEstado:Number;
		public var descripcion:String;
		public var orden:Number;
		public var tipo:String;

	}
}