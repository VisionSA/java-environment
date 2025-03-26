package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass(alias="com.bizitglobal.tarjetafiel.transacciones.dto.ClienteMontoDTO")]
	public class ClienteMontoDTO
	{
		public var cliente : ClienteTransaccion;
		public var monto : Number;
/*@I3820*/		public var montoValido : Boolean;
	}
}