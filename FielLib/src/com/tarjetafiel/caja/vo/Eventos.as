package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.Eventos")]	
	public class Eventos
	{
		public var idEvento:Number;
		public var descripcion:String = "";
		public var fecha:Date = new Date();
		public var fechaContableFlex:Date;
/*@I4053*/		public var fechaEvento = new Date();
/*@F4053*/		public var horaEvento:String;
		public var comentario:String = "";
/*@I4053*/		public var idOperadorAsignado:Number;	
public var  operador:Operador;
/*@F4053*/	}
}