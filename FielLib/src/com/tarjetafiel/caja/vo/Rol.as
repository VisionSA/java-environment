package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.operador.negocio.Rol")]
	public class Rol
	{
		public var idRol:Number;
		public var descripcion:String;
		public var menuItems:Array = new Array();
		
		[Transient]private var link:Object = [RolMenuItem];
	}
}