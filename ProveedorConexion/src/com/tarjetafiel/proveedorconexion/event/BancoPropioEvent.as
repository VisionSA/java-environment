package com.tarjetafiel.proveedorconexion.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	
	public class BancoPropioEvent extends CairngormEvent
	{
		
		public static var LISTAR_TODOS_EVENT:String = "BancosPropiosListarTodosEvent";
		
		
		public function BancoPropioEvent(type:String, param:Object)
		{
			super(type, false, false);
			data = param;
		}
	}
}