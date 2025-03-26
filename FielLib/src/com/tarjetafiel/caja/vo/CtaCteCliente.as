package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.CtaCteCliente")]	
	public class CtaCteCliente
	{
		public var idCtacteCliente:Number;
		public var comprobante:String;
		public var contabilizado:String;
		public var ctacontabledebe:String;
		public var ctacontablehaber:String;
		public var estadoImpacto:String;
		public var fecha:Date;
		public var fechaContableFlex:Date;
		public var fechaFacturacionFlex:Date;
		public var fechaFacturacionHastaFlex:Date;		
		public var conceptoDetalle:ConceptoDetalle;		
		public var idOperador:Number;
		public var idOrigen:Number;		
		public var idParent:Number;
		public var idTranascciones:Object;
		public var importe:Number;
		public var nroCuota:Number;
		public var liqCliente:LiqCliente;
		public var clienteTransaccion:ClienteTransaccion;
		public var nroOrigen:String;
		public var signo:Number;
		public var timestampFlex:Date;		
		public var transaccion:Transaccion;		
		public var pagosCliente:Array;
		public var codComercio:CodComercio;
		public var nroPlastico:String;
		public var importeImputado:Number;
	}
}