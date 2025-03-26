package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.Empresa")]
	public class Empresa
	{
		public var idEmpresa:Number;

		public var cuit:Number;

		public var razonSocial:String;

		public var esRiesgoza:String;

		// Objetos relacionados

		public var rubro:Rubro;
	
		//public var tamEmpresa:TamEmpresa;
	
		public var sucEmpresas:Array = new Array();
		
		[Transient]private var link:Object = [SucEmpresa, Telefono];
		
		public var tipoLiquidacion:Number;
		
		public var domicilioLegal:Domicilio;
		
		[Transient]public static const LIQUIDA_CUIT:int = 1;
		
		[Transient]public static const LIQUIDA_COMERCIO:int = 2;
		
		public var imprimirLiquidacion:String;
		
		public var generarPDF:String;
		
		public var enviarMail:String;
	}
}