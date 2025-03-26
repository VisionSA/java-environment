package com.tarjetafiel.caja.vo
{
/**
* @id: 5394
**/
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoLote")]
	public class PlasticoLote
	{
		public static const MANUAL:Number = 1;
		public static const AUTOMATICO:Number = 2;
		
		public var idPlastLote:Number; 
		public var vigenciaDesde:Date; 
		public var vigenciaHasta:Date; 
		public var fechaGeneracion:Date; 
		public var fechaEmbozo:Date; 
		public var fechaConfirmacion:Date; 
		public var archivoEmbozadora:String;
		public var archivoAcuses:String;
		public var estadoLote:EstadoLote;
		public var numeroLoteFormateado:String;
	}
}