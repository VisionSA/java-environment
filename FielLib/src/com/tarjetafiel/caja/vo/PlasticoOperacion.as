package com.tarjetafiel.caja.vo
{
/**
* @id: 5394
**/
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoOperacion")]
	public class PlasticoOperacion
	{
		public var idPlasticoOperacion:Number; 
		public var cabecera:String; 
		public var cuerpo:String;
		public var texto:String;
		public var descripcion:String;
		public var codConcepto:Number;
		public var cantidadCuotas:Number;
	}
}