package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.ComposicionDetalleCtaCte;
	import com.tarjetafiel.caja.vo.MovimientoCtaCte;
	import com.tarjetafiel.caja.vo.MovimientoCtaCteDetalle;
	import com.util.block.ControlBlock;
	
	import events.ClienteEvent;
	import events.ClienteManagerEvent;
	
	import mx.collections.ArrayCollection;

	[Bindable]
	public class CtaCteClienteModel extends  BaseModel
	{  
	   
	   private var _titular:ClienteTransaccion;
	   private var _saldoAnterior:Number;
	   private var _arrayMovientosCtaCteResumen:ArrayCollection;
	   private var _arrayMovientoCtaCteDetalles:ArrayCollection;
	   private var _arrayComposicionDetalleCtaCte:ArrayCollection;
	   public var  saldo:Number=0;	
	   public var saldoAcumulResumen:Number=0; 
	   public var acumulado:Number= 0;  
	   public var 	movimientoCtaCteSeleccionado: MovimientoCtaCte;
	   public var 	detalleMovimientoCtaCteSeleccionado: MovimientoCtaCteDetalle;
	   private var composicionDetalleCtaCte:ComposicionDetalleCtaCte;
	   public var verSaldoHistorio:Boolean = false;
	   public var modoConciliado:int = 3;
	   
	   
	  public function set titular(tit:ClienteTransaccion):void{
			_titular = tit;
			if(_titular)
			  mostrarPanel= true; 
	  }  	
		
	  public function get titular():ClienteTransaccion{
			return _titular;
	  }
	  
	
	 public  function getSaldoAcumulado(valor:Number):void{
	 	 saldoAcumulResumen+= valor; 
	 	 
	 }
	
	  
	  public function set saldoAnterior(saldoAnterior:Number):void{
			_saldoAnterior = saldoAnterior;
			
			if(_saldoAnterior){
			   saldo= saldoAcumulResumen= saldoAnterior;
			   if(_saldoAnterior < 0){
					_saldoAnterior = 0;
			   }
			}
			// else 
       }  	
		
	  public function get saldoAnterior():Number{
			return _saldoAnterior;
	  }
	  
	  
	  public function  buscarMovimientos(fechaDesde:Date):void{
	  	   verSaldoHistorio = false;
	  	   if(titular){
		  	    acumulado = 0; 
		  	   var evt:ClienteManagerEvent = new ClienteManagerEvent(ClienteManagerEvent.BUSCAR_MOVIMIENTOS_CTA_CTE_CLIENTE);
	           evt.fechaDesde = fechaDesde;
	           evt.idCliente=  titular.idCliente;
	           evt.modoConciliado = modoConciliado; 	                    
	           dispatcher.dispatchEvent(evt);
	           ControlBlock.getInstance().add();
	      }
	     
	  }
	  
	  public function  buscarDetallesMovimiento():void{
		 if(movimientoCtaCteSeleccionado){
	  	    var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_CLIENTE);
            evt.idTransaccion = movimientoCtaCteSeleccionado.idTransaccion;
            evt.idCliente=  titular.idCliente;
            evt.idTipoConceptoDetalle= movimientoCtaCteSeleccionado.idTipoConcDetalle; 
         	dispatcher.dispatchEvent(evt);
         }
          ControlBlock.getInstance().add();
	  }

	   public function  buscarComposicionDetalle():void{
	  	   if(detalleMovimientoCtaCteSeleccionado && movimientoCtaCteSeleccionado){
	  	   		var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BUSCAR_COMPOSICION_DETALLE_CTA_CTE_CLIENTE);
           		evt.idTransaccion = movimientoCtaCteSeleccionado.idTransaccion;
           		evt.idCliente=  titular.idCliente; 
           		evt.idConceptoDetalle= detalleMovimientoCtaCteSeleccionado.idConceptoDetalle;
     	        dispatcher.dispatchEvent(evt);
	      	}
	      	 ControlBlock.getInstance().add();
	  }	  	 	 
	  
	  public function set arrayMovientosCtaCteResumen(arrayMovientosCtaCteResumen:ArrayCollection):void{
	  	    var primero:Boolean =  true;
			_arrayMovientosCtaCteResumen = arrayMovientosCtaCteResumen;
			if(_arrayMovientosCtaCteResumen.length > 0){
				acumulado = MovimientoCtaCte(_arrayMovientosCtaCteResumen.getItemAt(_arrayMovientosCtaCteResumen.length-1)).saldoAcumulado;
			}			
		   dispatchEvent(new Event("changedArrayMov"));
		}
		[Bindable (event="changedArrayMov")]
		public function get arrayMovientosCtaCteResumen():ArrayCollection{
			return _arrayMovientosCtaCteResumen;
		}
		
		public function get arrayMovientoCtaCteDetalles():ArrayCollection{
			return _arrayMovientoCtaCteDetalles;
		}
		
		 public function set arrayMovientoCtaCteDetalles(arrayMovientoCtaCteDetalles:ArrayCollection):void{
			_arrayMovientoCtaCteDetalles = arrayMovientoCtaCteDetalles;
			/*if(arrayMovientoCtaCteDetalles && arrayMovientoCtaCteDetalles.length>0) {
		     	for each (var element:MovimientoCtaCteDetalle in arrayMovientoCtaCteDetalles){
				    element.importeCuota = element.importeCuota *  movimientoCtaCteSeleccionado.cantidadCuotas;
				}
			}*/
			
		}
		
		public function get arrayComposicionDetalleCtaCte():ArrayCollection{
			return _arrayComposicionDetalleCtaCte;
		}
		
		 public function set arrayComposicionDetalleCtaCte(arrayComposicionDetalleCtaCte:ArrayCollection):void{
			_arrayComposicionDetalleCtaCte = arrayComposicionDetalleCtaCte;
		}
		
		
			
	}
}