package com.tarjetafiel.caja.vo
{
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.AdelantoEnEfectivo")]
	public class AdelantoEnEfectivo
	{
//		public var int caja;
//	    public var String codigoComercio;
//		public var String importe;
//		public var String nroTarjeta;
//		public var String cuotas; //cuotas como String para poder contener por ejemplo la cuota 09 (con ceros a la izquierda) (dos caracteres siempre)
//		public var String listaPrecio; //listaPrecio como String para poder contener por ejemplo la lista precio 09 (con ceros a la izquierda) ya que listaprecio ocupa 2 caracteres
//		public var String usuario;
//		public var String moneda; //moneda como String para poder contener por ejemplo la moneda 035 (con ceros a la izquierda), ya que moneda debe ocupar 3 caracteres
//		public var String token;
		public var cuotasList:Array;
		public var importeAdelanto:Number;	

	}
}