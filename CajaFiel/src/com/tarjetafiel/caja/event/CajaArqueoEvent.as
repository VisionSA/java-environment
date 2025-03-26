package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public class CajaArqueoEvent extends CairngormEvent
	{
		public static const NUEVO_ARQUEO:String = "nuevoArqueoEvent";			
		
		public static const EJECUTAR_ARQUEO:String = "ejecutarArqueoEvent";
		
		public static const ARQUEO_COMPLETE:String = "completeArqueoEvent";
		
		public static const ARQUEO_EJECUTAR_DEFINITIVO:String = "ejecutarArqueoDefinitivoEvent";
		
		public function CajaArqueoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}