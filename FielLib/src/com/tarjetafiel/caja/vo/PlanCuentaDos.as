package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos")]
	public class PlanCuentaDos
	{
		public var idPlanCuenta:Number;   // DECIMAL(10,0) NOT NULL,
	    public var idPadre:Number;        // DECIMAL(10,0),
	    public var seccion:Number;         // DECIMAL(2,0),
	    public var numeroContable:String;   // DECIMAL(13,0),
	    public var estado:String;          // CHAR(1),
	    public var titulo:String;         // VARCHAR(40),
	    public var cuenta:Number;         // DECIMAL(6,0),
	    public var habilitada:String;     	// CHAR(1),
	    public var operador:Number;        	// /DECIMAL(3,0),
	    public var fecha_cargaFlex:Date;     	// DATE,
	    public var contab:String;        // CHAR(1),
	    public var fondos:String;        	// CHAR(1),
	    public var tipoCuenta:Number;   // DECIMAL(2,0),
	    public var moneda:Number;        	// DECIMAL(1,0),
	    public var codBco:Number;       // DECIMAL(3,0),
	    public var codCtaBco:String;   	// CHAR(17),
	    public var signo:Number;        // SMALLINT,
	    public var saldo:Number;         // DECIMAL(10,2),
	    public var importeMaximo:Number;	// DECIMAL(10,2),
	    public var caja:Number;         //	DECIMAL(4,0) 
	    public var uso:String;
	    public var saldoHabitual:String;
	    public var centroCosto:String;
	    public var tipoDeCuenta:String;
	    public var ajusteInflacion:String;
	    public var flujoEfectivo:String;

	}
}