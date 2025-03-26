package managers
{
	
	
	import com.util.components.alert.AlertError;
	import com.util.block.ControlBlock;
	import events.LineaCreditoManagerEvent;
	
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.Fault;
	
	[Bindable]
	public class LineaCreditoManager extends EventDispatcher
	{   
		public var dispatcher:IEventDispatcher;
        public var arrayValoresLineaCredito:ArrayCollection= new ArrayCollection();
			
		
				
		public function LineaCreditoManager()
		{			
		}
		
				
	    public function  buscarValoresAdelantoEfectivo(idCliente:Number):void {
              var evt:LineaCreditoManagerEvent = new LineaCreditoManagerEvent(LineaCreditoManagerEvent.BUSCAR_VALORES_ADELANTO_EFECTIVO);
			  evt.idCliente= idCliente;
 			  	  
        	  this.dispatcher.dispatchEvent(evt);
	    } 		    	   	   
	
      public function resultBuscarValoresAdelantoEfectivo(arrayValoresLineaCredito:Array):void{
	   	   this.arrayValoresLineaCredito = new ArrayCollection(arrayValoresLineaCredito);
	   	  
	   }
	   
	  
		
		public function fault( fault:Fault ):void{
			//ManagerErrors.getInstance().addPopUpError(fault.faultString,fault.faultDetail);
			AlertError.show(fault.faultString);
			ControlBlock.getInstance().remove();
		}
		

	}
}