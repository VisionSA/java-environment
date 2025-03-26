package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Tarea;
	import com.tarjetafiel.caja.vo.Tramite;
	import com.util.block.ControlBlock;
	
	import events.TramiteEvent;
	import flash.events.Event;
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class TramiteWorkflowModel extends  BaseModel
	{  
	   private var _titular:ClienteTransaccion;
	   private var _arrayTramites:ArrayCollection;
	   private var _arrayTramitesDetalle:ArrayCollection;
	   public var 	tramiteSeleccionado: Tramite;
	   public var 	tareaSeleccionado: Tarea;
	   
	   public function set titular(tit:ClienteTransaccion):void{
			_titular = tit;
			if(_titular)
			  mostrarPanel= true; 
	   }  	
		
	  public function get titular():ClienteTransaccion{
			return _titular;
	  }
	  
	  public function  buscarTramites(idProceso:Number, fechaDesde:Date,fechaHasta:Date):void{
	  	   if(titular){
	          var evt:TramiteEvent = new TramiteEvent(TramiteEvent.BUSCAR_TRAMITE_POR_CLIENTE);
	           evt.idCliente =  titular.idCliente; 
	           evt.idProceso = idProceso;
	           dispatcher.dispatchEvent(evt);
	           ControlBlock.getInstance().add();
	      }
	       
	  }
	  
	  public function  buscarTramitesDetalles():void{
	  	if(titular && tramiteSeleccionado)
	  	{
	  	   var evt:TramiteEvent = new TramiteEvent(TramiteEvent.BUSCAR_TAREAS_POR_TRAMITE);
       	   evt.idTramite = tramiteSeleccionado.idTramite;	
           evt.idCliente =  titular.idCliente; 
           dispatcher.dispatchEvent(evt);
    	}
    	ControlBlock.getInstance().add();
	  }

	  public function set arrayTramites(arrayTramites:ArrayCollection):void{
			_arrayTramites = arrayTramites;
			dispatchEvent(new Event("changedListTramites"));
	  }
		
		[Bindable(event="changedListTramites")]
		public function get arrayTramites():ArrayCollection{
			return _arrayTramites;
		}
		
		public function get arrayTramitesDetalle():ArrayCollection{
			return _arrayTramitesDetalle;
		}
		
		 public function set _arrayTramitesDetalle(arrayTramitesDetalle:ArrayCollection):void{
			_arrayTramitesDetalle = arrayTramitesDetalle;
		}
		
	}
}