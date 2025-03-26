package com.tarjetafiel.caja.vo
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.SucEmpresa")]
	public class SucEmpresa
	{
		public var idSucEmpresa:Number;
		public var descripcion:String;
		// Objetos relacionados
		public var domicilio:Domicilio;
		public var empresa:Empresa;
		public var autonomo:Autonomo;		
		public var sucTelefonos:Array;
		public var sucEmails:Array;
		public var codComercio:CodComercio;
		public var individuosFlex:Array;
		
		[Transient]private var link:Object = [Telefono, SucEmail, SucTelefono,CodComercio];
		
		[Transient]public var calcularLiquidacion:Boolean = true;			
				
	}
}