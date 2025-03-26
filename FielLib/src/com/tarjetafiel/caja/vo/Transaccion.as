package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.Transaccion")]
	public class Transaccion
	{
		public var comercioCod:CodComercio;
		public var idTranascciones:Object;
		public var anulacion:String;
		public var cantCuotas:String;
		public var codComercio:String;
		public var codRespuestaOffline:String;
		public var codigoAutorizacion:String;
		public var codigoMoneda:String;
		public var estadoImpacto:String;
		public var fechaConciliadoFlex:Date;
		public var fechaOperacionOriginalFlex:Date;
		public var fechaReal:Date;
		public var wraperFechaReal:String;
		public var finalizoOffline:String;
		public var finalizoOnline:String;
		public var formaIngresoTarjeta:String;
		public var horaReal:String;
		public var operador:Operador;
		//public var operadorConciliado:Operador;
		//public var origen:Origen;
		public var importe:String;
		public var importeSindescuento:String;
		public var indicadorCaptura:String;
		public var nroCupon:String;
		public var nroCuponOriginal:String;
		public var nroLote:String;
		public var nroOrigen:String;
		public var nroTarjeta:String;
		public var planCuotas:String;
	}
}