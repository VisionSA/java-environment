package com.tarjetafiel.caja.vo
{
	import mx.collections.ArrayCollection;

	[Bindable]
	[RemoteClass(alias="com.bizitglobal.tarjetafiel.transacciones.dto.MovimientoClientesDTO")]
	public class MovimientoClientesDTO
	{
		public var clientes : Array;
		public var movimiento : Movimiento;
	}
}