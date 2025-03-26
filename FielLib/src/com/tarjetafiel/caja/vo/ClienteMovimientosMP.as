package com.tarjetafiel.caja.vo
{	
	import mx.collections.ArrayCollection;

	[Bindable]
	public class ClienteMovimientosMP
	{
		public var cliente : ClienteTransaccion;
		public var listaMovimientosMP : ArrayCollection;
		public var sumador : SumadorMediosPago;

	}
}