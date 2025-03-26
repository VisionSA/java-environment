package model
{
	import events.EtapaEvent;
	
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class EtapaModel extends EventDispatcher
	{
		
		public var dispatcher:IEventDispatcher;
		
		public var etapasArray:ArrayCollection;
		//public var etapasVersionArray:ArrayCollection;
        
		
		public function EtapaModel()
		{
		}
		
		public function buscarEtapas():void{
			dispatcher.dispatchEvent(new EtapaEvent(EtapaEvent.BUSCAR_ETAPAS));
		}

	}
}