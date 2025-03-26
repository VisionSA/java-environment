package com.tarjetafiel.caja.vo
{
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.Banco")]
	public class Banco
	{
		public var idBanco:Number;
		public var descripcion:String;
		public var codigo:String;

	}
}