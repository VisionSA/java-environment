package com.tarjetafiel.caja.vo
{
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.evaluacion.negocio.ActividadEvaluacion")]
	public class ActividadEvaluacion
	{
		public var idActividad:Number;
		public var antiguedad:Number;
		public var cargo:String;
		public var fechaIngresoFlex:Date;
		public var concepto:String;
		public var ocupacion:Ocupacion;
		public var otrosIngresosDesc:String;
		public var otrosIngresosMonto:Number;
		public var sueldoNeto:Number;
		public var sucEmpresa:SucEmpresa;
	
	}
}