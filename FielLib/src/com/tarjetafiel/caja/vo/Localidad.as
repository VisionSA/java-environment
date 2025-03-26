package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.Localidad")]
	public class Localidad
	{
		public var idLocalidad:Number;
		public var codigoPostal:String;
		public var partido:Partido;
		public var provincia:Provincia;
		public var nombre:String;
	}
}