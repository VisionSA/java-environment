package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.evaluacion.negocio.Cobrador")]
	public class Cobrador
	{
		public var idCobrador:Number;
        public var apellido:String;
        public var domicilio:Domicilio;
        public var nombre:String;
        public var email:Email;
        public var estado:String;
        public var fechaBaja:Date;
        
	}
}