package managers
{
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	
	import events.TramiteManagerEvent;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.Fault;
	
	
	public class TramiteManager extends EventDispatcher
	{   
		public var dispatcher:IEventDispatcher;
		[Bindable]public var arrayTramites:ArrayCollection= new ArrayCollection();
		[Bindable]public var arrayTramitesDetalle:ArrayCollection= new ArrayCollection();
		
		public static const BUSCAR_TRAMITE_POR_CLIENTE:String = "buscarTramitesPorCliente"; 
		public static const BUSCAR_TAREAS_POR_TRAMITE:String = "buscarTareasPorTramite";
		
		public function TramiteManager()
		{			
		}
         
		public function  buscarTramites(idCliente:Number, idProceso:Number):void {
          var evt:TramiteManagerEvent = new TramiteManagerEvent(TramiteManagerEvent.BUSCAR_TRAMITE_POR_CLIENTE);
		  evt.idCliente =  idCliente;
		  evt.idProceso =  idProceso;
		  this.dispatcher.dispatchEvent(evt);
        } 
        
       public function  buscarTramitesDetalles(idCliente:Number,idTramite:Number):void {
          var evt:TramiteManagerEvent = new TramiteManagerEvent(TramiteManagerEvent.BUSCAR_TAREAS_POR_TRAMITE);
		  evt.idTramite = idTramite;	
          evt.idCliente =  idCliente;
		  this.dispatcher.dispatchEvent(evt);
        } 
        
	//**************************************RESULTS***************************************************//
	
	   public function resultBuscarTramites(arrayTramitesResult:Array):void{
	   	   this.arrayTramites = new ArrayCollection(arrayTramitesResult);
	   	   if(arrayTramites.length == 0){
	   	   		AlertOk.show("No se encontraron datos");
	   	   }
	   	    ControlBlock.getInstance().remove();
	   }
	   
	   public function resultBuscarTramitesDetalles(arrayTramitesDetalleResult:Array):void{
	   	   this.arrayTramitesDetalle = new ArrayCollection(arrayTramitesDetalleResult);
	   	   if(arrayTramitesDetalle.length == 0){
	   	   		AlertOk.show("No se encontraron datos");
	   	   }
	   	    ControlBlock.getInstance().remove();
	  }

		public function fault( fault:Fault ):void{
			AlertError.show(fault.faultString);
			ControlBlock.getInstance().remove();
		}

	}
}