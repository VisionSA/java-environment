package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.EventosCliente")]	
	public class EventosCliente
	{
		public var idEventoCliente:Number;
		public var idCliente:Number;
		public var evento:Eventos = new Eventos();
				
	}
}
