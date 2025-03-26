package com.util.paginacion
{
	import flash.events.Event;

	public class PaginacionEvent extends Event
	{
		public static const PAGINAR:String = "paginar";
		public static const PAGINAR_COMPLETADO:String = "paginarCompletado";
		public var paginador:Paginador;
		
		public function PaginacionEvent(type:String, paginador:Paginador, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.paginador = paginador;
		}
		
	}
}