package com.tarjetafiel.caja.vo
{
		
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.LiqComercio")]
	public class LiqComercio
	{
		public var idLiqComercio:Number;
		public var fechaLiq:Date;		
	    public var codComercio:CodComercio;
	    public var cuit:String;	    	    
	    public var finalizoLiquidado:String;
	    public var disponibleImpresion:String;
	    public var liquidacion:Liquidacion;		    	        	  
	}
}