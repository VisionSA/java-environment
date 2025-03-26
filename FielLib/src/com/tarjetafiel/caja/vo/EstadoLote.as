package com.tarjetafiel.caja.vo
/**
* @id: 5394
**/
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.EstadoLote")]
	public class EstadoLote
	{
		public static const PENDIENTE_PROCESAR:String = "1";
		public static const PROCESADO:String = "2";
		public static const CERRADO:String = "3";
		public static const EN_GENERACION:String = "4";

		public var idPlastLoteEstado:Number;
		public var descripcion:String;
	}
}