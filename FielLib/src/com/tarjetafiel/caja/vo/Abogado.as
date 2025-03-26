package com.tarjetafiel.caja.vo {
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.Abogado")]
	public class Abogado {
		public var idColaborador : Number;
		public var nombres : String;
		public var apellido : String;
	}
}