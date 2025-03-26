package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.LiqCliente;
	import com.util.block.ControlBlock;
	
	import events.ClienteEvent;
	import mx.controls.Alert;
	
	import mx.collections.ArrayCollection;

	[Bindable]
	public class ResumenCtaClienteModel extends  BaseModel
	{  
	   
	   
	   private var _titular:ClienteTransaccion;
	   private var _arrayLiquidaciones:ArrayCollection;
	   private var _arrayDetallesLiquidaciones:ArrayCollection;
	   public var 	liquidacionSeleccionada: LiqCliente;
	   	
	  public function set titular(tit:ClienteTransaccion):void{
			_titular = tit;
			if(_titular)
			  mostrarPanel= true; 
	  }  	
		
	  public function get titular():ClienteTransaccion{
			return _titular;
	  }
	  
	  
	    public function set arrayLiquidaciones(arrayLiquidaciones:ArrayCollection):void{
			_arrayLiquidaciones = arrayLiquidaciones;
		}
		
		
		public function get arrayLiquidaciones():ArrayCollection{
			return _arrayLiquidaciones;
		}
		
		public function set  arrayDetallesLiquidaciones( arrayDetallesLiquidaciones:ArrayCollection):void{
			 _arrayDetallesLiquidaciones =  arrayDetallesLiquidaciones;
			// dispatchEvent(new Event("changedArrayLiquidaciones"));
		}
		//[Bindable (event="changedArrayLiquidaciones")]
		public function get arrayDetallesLiquidaciones():ArrayCollection{
			return _arrayDetallesLiquidaciones;
		}
	   
	   
	   public function  buscarLiquidaciones(cantLiqHaciaAtras:int):void{
	  	   if(titular){
			   var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BUSCAR_LIQUIDACIONES_CLIENTES);
	           evt.idCliente=  titular.idCliente;
	           evt.cantLiqHaciaAtras = cantLiqHaciaAtras;  
	           dispatcher.dispatchEvent(evt);
	           ControlBlock.getInstance().add();
	      }
	       
	   }
	   
	   
	   public function  buscarDetallesLiquidaciones():void{
	  	   
	  	   if(liquidacionSeleccionada){
		  	   var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BUSCAR_DETALLES_PAGOS_LIQUIDACIONES_CLIENTES);
	           evt.idLiqCliente =  liquidacionSeleccionada.idLiqCliente;
	           evt.idCliente=  titular.idCliente;   
	           dispatcher.dispatchEvent(evt);
	           ControlBlock.getInstance().add();
	      }
	   }
	  
	 	
	}	
		
}