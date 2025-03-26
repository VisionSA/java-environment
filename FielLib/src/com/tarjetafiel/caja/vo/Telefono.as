package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.Telefono")]
	public class Telefono
	{
		public var idTelefono:Number;
		public var nroTlefono:String;		
		public var codArea:String;		
		public var nroInterno:String;		
		public var esFax:String;	
		public var esCelular:String;	
		public var descripcion:String;	
		public var horarios:String;		
		public var tipo:TipoTelefono;		
		public var operador:Operador;		
		public var fechaAlta:Date;	
	}
}