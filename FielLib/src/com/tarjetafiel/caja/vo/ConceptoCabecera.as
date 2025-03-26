package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoCabecera")]
	public class ConceptoCabecera
	{
		public var idConcepto:Number;
		public var descripcion:String;
		public var target:String;
		public var codigoConcepto:Number;
		public var esFiel:String;
	}
}