package com.tarjetafiel.caja.vo
{
	import flash.events.EventDispatcher;
	
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.fondos.negocio.CajaMP")]
	public class CajaMP extends EventDispatcher
	{
		public var idCajaMP:Number;
		public var habilitado:String;
		public var formaPago:FormaPago;
		public var caja:Caja;
		public var idPlanCuenta:Number;
		public var planCuentaDos:PlanCuentaDos;
		//solo para descarga de valores
		public var importeRetiro:Number = 0;	
		public var descargaChequesList:Array = new Array();
		public var chequesEnCajaList:Array = new Array();
		
	}
}