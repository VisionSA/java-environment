package com.tarjetafiel.caja.vo
{
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.LiqClienteRepactacion")]
	public class LiqClienteRepactacion
	{
		public var idLiqClienteRepactacion:Number;
		public var montoMinimo:Number;
		public var nroCuota:int;
		public var montoCuota:Number;
		public var liqCliente:LiqCliente;
		private var _descripcion:String;
		public var cuotas:Array;
		public var saldo:Number;
		public var importeRefinanciar:Number;
		
		private var link:Object = [Cuota];
		
		public var montoTotal:Number; 
		public var suPago:Number;
		public var idConcepto:Number;
	}
}