package com.tarjetafiel.caja.vo
{
	import mx.collections.ArrayCollection;
	

	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.GestorCuentaNew")]
	public class GestorCuentaNew 
	{	
		public var  fechaEjecucion:Date;	
		public var  accion:Number;
		public var  idCliente:Number;
		public var  tiempoAsig:Number;
		public var  fechaEstado:Date;
		public var  importe:Number;
		public var  domicilio:String;
		public var  telefono:String;
		public var  nombres:String;
		
	}
}