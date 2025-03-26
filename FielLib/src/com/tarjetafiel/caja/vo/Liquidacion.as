package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.Liquidacion")]
	public class Liquidacion
	{
			public var idLiquidacion:Number;
			public var numeroLiquidacion:Number;
    		public var cuit:String;
    		public var fechaLiquidacion:Date;
    		public var fechaEntrega:Date;
    		public var liqComercios:Array;
    		public var montoTotal:Number;
	}
}