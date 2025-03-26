package com.tarjetafiel.caja.vo {
	
	[Bindable]
 	[RemoteClass(alias="com.bizitglobal.tarjetafiel.transacciones.negocio.LineaCredHistorico")]
	public class LineaCredHistorico {
		
		public var idLineaCreditohistorico : uint;	
		public var cliente : ClienteTransaccion;
		public var porcentaje : uint;
		public var codOper : uint;
		public var importe : uint;	
		public var fechaHasta : Date;
		public var fechaDesde : Date;
		public var fechaUltModif : Date;
		public var tipo : String;
		public var codigo : uint;
		public var idRol : uint;
		public var idClienteViejo : uint;
		public var nombreOperador : String;
	
	}
}