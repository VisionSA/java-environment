package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.LiqComercioDet")]
	public class LiqComercioDet
	{
		public var idLiqComercioDet:Number;	    
	    public var fechaPago:Date;
	    public var totalCuotas:Object;
	    public var diasDiferimiento:int;
	    	
	}
}