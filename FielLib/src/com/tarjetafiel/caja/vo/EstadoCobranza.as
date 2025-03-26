package com.tarjetafiel.caja.vo
{
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.EstadoCobranza")]
	public class EstadoCobranza
	{
		public var idEstadoCobranza:Number;
    	public var descripcion:String;
    	public var habilitadoConsumo:String;

	}
}