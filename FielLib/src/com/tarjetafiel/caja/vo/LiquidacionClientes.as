package com.tarjetafiel.caja.vo
{
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.LiquidacionClientes")]
	public class LiquidacionClientes
	{
		public var idLiquidacionClientes:Number;
	    public var fechaLiquidacion:Date; // esta fecha de liquidacion es en realidad la fecha de cierre del periodo, que es uno por mes.
	    public var montoTotal:Object;
		public var finalizo:String; // flag, que indica si la liquidacion se realizo para todos los clientes que tendria que haberlo hecho.
		public var cantClientes:Number;  // se guarda la cantidad de clientes liquidados para consultas rapidas.....
		public var confirmada:String; // 'S' la liquidacion es definitiva, 'N' la liquidacion es de prueba		
		public var leyendaEstado:String = new String("No existe preliquidación o liquidación para la fecha seleccionada"); // esta leyenda es para informar el estado de la liquidacion, es decir si fue liquidada y
		public var fechaCierrePeriodo:Date;
		public var tipoLiquidacion:int; 

	}
}