package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.TipoCuentaBanco")]
	public class TipoCuentaBanco
	{
		public var idCuentaBanco:Number;
		public var codigo:Number;
		public var descripcion:String;
		public var descripcionCorta:String;
	}
}