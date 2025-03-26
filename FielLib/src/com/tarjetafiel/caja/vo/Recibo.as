package com.tarjetafiel.caja.vo {
	
	[Bindable]
 	[RemoteClass(alias="com.bizitglobal.tarjetafiel.cobranzas.negocio.DTO.ReciboDTO")]	
	public class Recibo {
		public var idRecibo:Number;
		public var codigoRecibo:Number;
		public var ctaCteCliente:Number;
		public var cobrador:Cobrador;		
		public var desde:Number;
		public var hasta:Number;
		public var esEstadoAnulado : String;
	}
}