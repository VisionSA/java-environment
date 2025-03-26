package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.operador.negocio.MenuItem")]
	public class MenuItem
	{
		public var idMenuItem:Number
		public var idItem:String;
		public var label:String;
		public var icon:String;
		public var action:String;
		public var actionListener:String;
		public var split:String;
		public var esPadre:String;
		public var hijos:Array;
		public var pagina:Pagina;
	}
}