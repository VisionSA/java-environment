package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.GestionCliente")]
	public class GestionCliente
	{
		public var idCliente:Number;
		public var idTipoGestion:Number;
		public var idOperador:Number;
	}
}
