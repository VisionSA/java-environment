package com.tarjetafiel.caja.event
{
	import flash.events.Event;
	
	import mx.core.IContainer;

	public class MenuItemClickEvent extends Event
	{
		public static const MENU_ITEM_CLICK = "menuItemClick";
		
		public var modulo:String;
		
		public var contenedor:String;
		
		public var contenedorUi:IContainer;
		
		public var titulo:String;
		
		public function MenuItemClickEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}