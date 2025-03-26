package com.tarjetafiel.caja.vo
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.Pais")]
	public class Pais
	{
		public var idPais:Number; 
		public var nombre:String;
		public var provinciaSet:Array;

	}
}