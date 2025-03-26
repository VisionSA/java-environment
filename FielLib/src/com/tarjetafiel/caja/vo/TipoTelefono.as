package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.TipoTelefono")]
	public class TipoTelefono
	{
		public var idTipoTelefono:Number;
		public var descripcion:String;
		public var paraProveedor:String;
		public var paraEvaluacion:String;
		public var paraComercio:String;
		
	}
}