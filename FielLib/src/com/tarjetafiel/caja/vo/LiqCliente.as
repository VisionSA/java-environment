package com.tarjetafiel.caja.vo
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.LiqCliente")]
	public class LiqCliente implements Negocio
	{
		public var idLiqCliente:Number; // este es el numero de liquidacion
		public var clienteTransaccion:ClienteTransaccion;
		public var fechaCierre:Date;
		public var fechaCierreAnterior:Date;
		public var fechaLiq:Date;
		public var fechaProxCierre:Date;
		public var fechaProximoVto:Date;
		public var fechaVtoAnterior:Date;
		public var intComp:Object;
		public var intPunitorios:Object;
		public var seguroDeudor:Object;
		public var tasaAdelanto:Object;
		public var tasaRefinanc:Object;	
		public var montoTotal:Number;	
		public var importePagado:Number;
		public var liquidacionClientes:LiquidacionClientes;
		public var pagosCliente:Array;		
		public var pagar:Boolean = false;
		public var totalIntereses:Number;
		public var totalRepactado:Number;
	     	
		public var saldo:Number = 0;
		public var importeVencimiento:Number;
		public var fechaPago:Date;
		public var rutaPdf:String;   		
		
		public var ctaCteClienteSet:Array = new Array();
 		//public var ctaCteClienteAuxSet:Array = new Array();  // es la tabla para guardar los ctacteCliente auxiliares que de confirmarse la transaccion pasaran a la ctacteCliente original.
		public var liqClienteFuturosVencimientos:Array;
		public var liqClientePagos:Array = new Array();
		public var liqClienteRepactacion:Array = new Array();
		
		public var leyendaVencimiento:String;
		public var color:String;
		
		public var acumulado:Number;
		
		
		[Transient]private var link:Object = [CtaCteCliente, PagosCliente,LiqClientePago,LiqClienteRepactacion];	
		
		public function LiqCliente(){ 
		
		}
		
		public function getId():Number{return idLiqCliente;}
		public function getLabel():String{return "";}

	}
}