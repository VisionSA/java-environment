package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.ComposicionDetalleCtaCte;
	import com.tarjetafiel.caja.vo.MovimientoCtaCte;
	import com.tarjetafiel.caja.vo.MovimientoCtaCteDetalle;
	import com.util.block.ControlBlock;
	
	import events.ClienteEvent;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;

	[Bindable]
	public class ComposicionSaldoClienteModel extends  BaseModel
	{  
	   
	   public static const MODO_RESUMEN:int = 1;
	   public static const MODO_COMPELTO:int = 2;
	   public static const MODO_CONCILIADO = 3;
	   public static const MODO_TODOS = 4;
	   private var _titular:ClienteTransaccion;
	   private var _saldoAnteriorComposicionSaldo:Number;
	   private var _arrayComposicionSaldo:ArrayCollection;
	   private var _arrayMovientoCtaCteDetalles:ArrayCollection;
	   private var _arrayComposicionSaldoDetalles:ArrayCollection;
	   public var  saldo:Number=0;	
	   public var saldoAcumulResumen:Number=0; 
	   private var primero:Boolean =  true;
	   private var acumulado:Number= 0;  
	   public var 	movimientoComposicionSeleccionado: MovimientoCtaCte;
	   public var 	detalleMovimientoCtaCteSeleccionado: MovimientoCtaCteDetalle;
	   private var composicionDetalleCtaCte:ComposicionDetalleCtaCte;
	   public var modo:int = 1;
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
	
	  
	  public function set saldoAnteriorComposicionSaldo(saldoAnterior:Number):void{
			_saldoAnteriorComposicionSaldo = saldoAnterior;
			if(_saldoAnteriorComposicionSaldo)
			   saldo= saldoAcumulResumen= saldoAnterior;
       }  	
		
	  public function get saldoAnteriorComposicionSaldo():Number{
			return _saldoAnteriorComposicionSaldo;
	  }
	  
	  
	  
	  
	  public function  buscarComposicionSaldo(fechaDesde:Date,fechaHasta:Date):void{
	  	   if(titular){
		  	   var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BUSCAR_COMPOSICIONES_SALDO_CLIENTE);
	           evt.fechaDesde = fechaDesde;
	           evt.fechaHasta = fechaHasta;
	           evt.idCliente=  titular.idCliente; 
	           evt.modo = modo;
	           evt.modoConciliado = modoConciliado;
	           dispatcher.dispatchEvent(evt);
	           ControlBlock.getInstance().add();
	      }
	       
	  }
	  
	  public function  buscarDetallesMovimiento():void{
	  	   var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_CLIENTE);
       
           evt.idCliente=  titular.idCliente; 
           dispatcher.dispatchEvent(evt);
            ControlBlock.getInstance().add();
	  }

	   public function  buscarComposicionSaldoDetalle():void{
	  	   if(titular && movimientoComposicionSeleccionado){
	  	   		var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BUSCAR_COMPOSICIONES_SALDO_DETALLE_CLIENTE);
           		evt.idTransaccion =  movimientoComposicionSeleccionado.idTransaccion;
           		evt.nroCuota =  movimientoComposicionSeleccionado.nroCuota;
           		evt.idCliente=  titular.idCliente; 
           		evt.fechaFacturacion=movimientoComposicionSeleccionado.fechaFacturacion;
           		dispatcher.dispatchEvent(evt);
       		}
       		
       		 ControlBlock.getInstance().add();
           
           
	  }

	  
	  
	  public function  obtenerSaldoAnterior(fechaHasta:Date):void{
	  	   if(titular){
	  	   		/*var evt:ClienteEvent = new ClienteEvent(ClienteEvent.OBTENER_SALDO_ANTERIOR_COMPOSICION_SALDO);
           		evt.fechaHasta = fechaHasta;
           		evt.idCliente=  titular.idCliente; 
           		dispatcher.dispatchEvent(evt);*/
       		}
	  }
	  
	  
	  public function set arrayComposicionSaldo(arrayComposicionSaldo:ArrayCollection):void{
			_arrayComposicionSaldo = arrayComposicionSaldo;
			if (_arrayComposicionSaldo.length > 0 && MovimientoCtaCte(_arrayComposicionSaldo.getItemAt(0)).descripcionConcepto == "RESUMEN ANTERIOR"){
				saldoAnteriorComposicionSaldo = MovimientoCtaCte(_arrayComposicionSaldo.getItemAt(0)).saldoAcumulado; 
			}
			
			if(_arrayComposicionSaldo.length > 0){
				saldo = MovimientoCtaCte(_arrayComposicionSaldo.getItemAt(_arrayComposicionSaldo.length -1)).saldoAcumulado;
			}			
			
			dispatchEvent(new Event("changedListCtaCte"));
		}
		
		[Bindable(event="changedListCtaCte")]
		public function get arrayComposicionSaldo():ArrayCollection{
			return _arrayComposicionSaldo;
		}
		
		public function get arrayMovientoCtaCteDetalles():ArrayCollection{
			return _arrayMovientoCtaCteDetalles;
		}
		
		 public function set arrayMovientoCtaCteDetalles(arrayMovientoCtaCteDetalles:ArrayCollection):void{
			_arrayMovientoCtaCteDetalles = arrayMovientoCtaCteDetalles;
		}
		
		public function get arrayComposicionSaldoDetalles():ArrayCollection{
			return _arrayComposicionSaldoDetalles;
		}
		
		 public function set arrayComposicionSaldoDetalles(arrayComposicionSaldoDetalles:ArrayCollection):void{
			_arrayComposicionSaldoDetalles = arrayComposicionSaldoDetalles;
		}			
			
	}
}