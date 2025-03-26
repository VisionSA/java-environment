package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.event.PagoAComercioEvent;
	
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class PagoAComercioModel extends EventDispatcher
	{
		public var liquidacionesList:ArrayCollection = new ArrayCollection();
		
		public var liquidacionesAEntregar:ArrayCollection = new ArrayCollection();	
		
		public function entregarLiquidaciones():void{
			new PagoAComercioEvent(PagoAComercioEvent.PAGOS_LIST_UPDATE).dispatch();			
		}
				
	}
}