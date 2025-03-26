package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.LiqComercioLP")]
	public class LiqComercioLP
	{
		public var idLiqComercioLp:Number;
		public var periodoDesde:Date;
		public var periodoHasta:Date;
	    public var totalBruto:Number;
	    public var totalNeto:Number;
	    public var totalPagar:Number;
	    public var listaPrecio:ListaPrecio;
	    public var liqComercio:LiqComercio;
	    public var sucEmpresa:SucEmpresa;
		public var detallesLiqComercioDet:Array //array de objetos LiqComercioDet;
		public var rutaPdf:String;		
		[Transient]private var link:Object = [LiqComercioDet, CtaCteComercio];
		
	}
}