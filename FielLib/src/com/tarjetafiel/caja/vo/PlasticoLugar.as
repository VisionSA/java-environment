package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoLugar")]
	public class PlasticoLugar
	{
/*@I5394*/		public static const SIN_ACCION:Number = 0;
		public static const CONFIRMA:Number = 1;
		public static const HABILITA:Number = 2;
		public static const DESHABILITA:Number = 3;
/*@F5394*/		
		public var idPlasticoLugar:Number;
	
		public var descripcion:String;
/*@I5394*/		
/*@F5394*/		public var accion:Number;

	}
}