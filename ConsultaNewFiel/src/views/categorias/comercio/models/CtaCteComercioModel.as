package views.categorias.comercio.models
{
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.Empresa;
	import com.tarjetafiel.caja.vo.MovimientoCtaCteComercio;
	import com.tarjetafiel.caja.vo.ComposicionDetalleCtaCte;
	import com.tarjetafiel.caja.vo.MovimientoCtaCteComercioDetalle;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertOk;
	import com.util.components.alert.AlertWarning;
	
	import events.ComercioManagerEvent;
	import events.CtaCteComercioEvent;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	
	import views.categorias.cliente.models.BaseModel;
	
	[Bindable]
	public class CtaCteComercioModel extends BaseModel
	{
		
		private var _saldo:Number;
		public var saldoCalculado:Number;
		public var comercio:CodComercio;
		public var empresa:Empresa;
		public var movimientoctaCteComercioList:ArrayCollection;
		private var _buscarEmpresa:Boolean = true;
		private var campo:String = "codComercio.sucEmpresa.empresa.idEmpresa";
		private var value:Object;
		public var buscasDesde:Date = new Date(new Date().fullYear,new Date().month -1, new Date().date);
		public var ordenarPorFacturacion:Boolean = false;
		
	   private var _codComercio:CodComercio;
	   private var _saldoAnterior:Number;
	   private var _arrayMovientosCtaCteResumen:ArrayCollection;
	   private var _arrayMovientoCtaCteDetalles:ArrayCollection;
/*@F8271*/	   private var _arrayComposicionCtaCte:ArrayCollection;

/*@F8271*/	private var	_arrayMovientosCompSaldo:ArrayCollection;
/*@F8271*/  public var arrayMCSDetallesCtaCte:ArrayCollection;
/*@F8271*/	public var arrayMCSComposicionCtaCte:ArrayCollection;

/*@F8271*/	private var	_arrayMovientosContable:ArrayCollection;
/*@F8271*/	public var	arrayCOMDetallesCtaCte:ArrayCollection;
/*@F8271*/	public var	arrayCOMComposicionCtaCte:ArrayCollection;
/*@F8271*/ private var	_arrayMovientoRELCtaCteDetalles:ArrayCollection;
/*@F8271*/ public var mostrarTransaccionRelacion: Boolean=false ;

	   public var  saldo:Number=0;	
	   public var saldoAcumulResumen:Number=0; 
	   public var acumulado:Number= 0;  
/*@F8271*/	   public var acumuladoCompSaldo:Number= 0;
/*@F8271*/	   public var acumuladoContable:Number= 0;
	   public var 	movimientoCtaCteSeleccionado: MovimientoCtaCteComercio;
	   public var 	detalleMovimientoCtaCteSeleccionado: MovimientoCtaCteComercioDetalle;
	   public var verSaldoHistorio:Boolean = false;
	   public var modoConciliado:int = 4;
	   
	   
		public function CtaCteComercioModel()
		{
		}
		
		public function buscarItemCtaCteComercio():void{
			
			if(movimientoctaCteComercioList){
				movimientoctaCteComercioList.removeAll();
			}
								
			if(!empresa && !comercio){
				AlertWarning.show("Debe seleccionar una empresa o comercio");
				return;
			}
			if(buscarEmpresa){
				value = empresa.idEmpresa;
			} else {
				value = comercio.idCodComercio;
			}
			
			var filtro:Filtro = new Filtro();
			filtro.campos.push(campo);
			filtro.operadores.push(Filtro.IGUAL);
			filtro.valores.push(value);
			
			filtro.campos.push("timestamp");
			filtro.operadores.push(Filtro.MAYOR_IGUAL);
			filtro.valores.push(Filtro.toDate(buscasDesde));
			
			if(ordenarPorFacturacion){
				filtro.orderBy.push("fechaFacturacion");
			}
			else{
				filtro.orderBy.push("timestamp");
			}
				
			filtro.orderBy.push("codComercio.idCodComercio");
			
			var evt:CtaCteComercioEvent = new CtaCteComercioEvent(CtaCteComercioEvent.BUSCAR_CTA_CTE_COMERCIO);
			evt.filtro = filtro;
			evt.fechaDesde = buscasDesde;
			evt.buscarPorEmpresa = buscarEmpresa;
			evt.idValue = value;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		[Bindable (event="changedBuscarEmpresa")]
		public function get buscarEmpresa():Boolean{
			return _buscarEmpresa;
		}
		
		public function set buscarEmpresa(bool:Boolean):void{
			_buscarEmpresa = bool;
			if(_buscarEmpresa){
				campo = "codComercio.sucEmpresa.empresa.idEmpresa";
			} else {
				campo = "codComercio.idCodComercio";
			}
			dispatchEvent(new Event("changedBuscarEmpresa"));
			dispatcher.dispatchEvent(new Event("changedBuscarEmpresa"));
		}
		
		public function set saldos(psaldo:Number):void{
			this.saldo = psaldo;
			saldoCalculado = this.saldo;
			dispatchEvent(new Event("changedSaldo"));
		}		
		
		[Bindable (event="changedSaldo")]
		public function get saldos():Number{
			return this.saldo;
		}
		/*
		public function resultCtaCteComercio(result:Object):void{
			
			saldo = Number(result.saldo);
			movimientoctaCteComercioList = new ArrayCollection(result.array as Array);
			calcularSaldo();
			if(movimientoctaCteComercioList.length == 0){
				AlertOk.show("No se encontraron datos");
			}
			ControlBlock.getInstance().remove();
			dispatcher.dispatchEvent(new Event("changedCtaCteList"));			
		}
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
		
		
		public function calcularSaldo():void{
			for each(var cta:MovimientoCtaCteComercio in movimientoctaCteComercioList){
				saldoCalculado += cta.importe * cta.signo;
				cta.saldo = Number(ObjectUtil.copy(saldoCalculado));
			}
		}
		
	*/	
	   
	   
		//public var _movimientoCtaCteComercio: MovimientoCtaCteComercio;
		
		
      public function resetValues():void{

			if(movimientoctaCteComercioList){
				movimientoctaCteComercioList.removeAll();
				saldo = 0;
			}

	 }
		
	 //Ctacte. Comercios**************************************************************************************
	 public function set codcomercio(cod:CodComercio):void{
			_codComercio = cod;
			if(_codComercio)
			  mostrarPanel= true; 
	  }  	
		
	  public function get codcomercio():CodComercio{
			return _codComercio;
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
       }  	
		
	  public function get saldoAnterior():Number{
			return _saldoAnterior;
	  }
	  
	  	  
/*@I8271*/	  public function  buscarCompSaldo(fechaDesde:Date):void{
		  verSaldoHistorio = false;
		  
		  //if(codcomercio){
		  if(comercio){
			  acumulado = 0; 
			  var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_MOVIMIENTOS_COMP_SALDO_COMERCIO);
			  evt.fechaDesde = fechaDesde;
			  evt.idComercio =  comercio.idCodComercio;
			  //evt.idComercio =  codcomercio.idCodComercio;
			  evt.modoConciliado = modoConciliado;   	                    
			  dispatcher.dispatchEvent(evt);
			  ControlBlock.getInstance().add();
		  }
		  
/*@F8271*/	  }
	  
	  public function  buscarMovimientos(fechaDesde:Date):void{
	  	   verSaldoHistorio = false;
	  	   
	  	   //if(codcomercio){
	  	   if(comercio){
		  	    acumulado = 0; 
		  	   var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_MOVIMIENTOS_CTA_CTE_COMERCIO);
	           evt.fechaDesde = fechaDesde;
	           evt.idComercio =  comercio.idCodComercio;
	           //evt.idComercio =  codcomercio.idCodComercio;
	           evt.modoConciliado = modoConciliado;   	                    
	           dispatcher.dispatchEvent(evt);
	           ControlBlock.getInstance().add();
	      }
	     
	  }
	  
	  /*@I8271*/	  public function  buscarMovimientosContables(fechaDesde:Date):void{
		  verSaldoHistorio = false;		  
		  //if(codcomercio){
		  if(comercio){
			  acumulado = 0; 
			  var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_CONTABLE_CTA_CTE_COMERCIO);
			  evt.fechaDesde = fechaDesde;
			  evt.idComercio =  comercio.idCodComercio;
			  //evt.idComercio =  codcomercio.idCodComercio;
			  evt.modoConciliado = modoConciliado;   	                    
			  dispatcher.dispatchEvent(evt);
			  ControlBlock.getInstance().add();
		  }		  
		
	  /*@F8271*/	  }
	  
	  public function  buscarDetallesMovimiento():void{
		 if(movimientoCtaCteSeleccionado){
            var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_COMERCIO);
            evt.idTransaccion = movimientoCtaCteSeleccionado.idTransaccion;
			evt.idComercio=  comercio.idCodComercio;
         	dispatcher.dispatchEvent(evt);
         }
          ControlBlock.getInstance().add();
	  }
	  
	  
	  public function  buscarDetallesRELMovimiento():void{
		  if(movimientoCtaCteSeleccionado){
			  var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_DETALLES_MOVIMIENTO_REL_COMERCIO);
			  evt.idTransaccion = movimientoCtaCteSeleccionado.idTransaccion;			  
			  dispatcher.dispatchEvent(evt);
		  }
		  ControlBlock.getInstance().add();
	  }
	  
	  
/*@I8271*/	  public function  buscarComposicionMovimientoCliente():void{
		  if(movimientoCtaCteSeleccionado){
			  var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_COMPOSICION_DETALLE_CTA_CTE_CLIENTE);
			  evt.idTransaccion = movimientoCtaCteSeleccionado.idTransaccion;
			  evt.idCliente =  0; 
			  evt.idConceptoDetalle=  0; 
			  this.dispatcher.dispatchEvent(evt); 
		  }
		  ControlBlock.getInstance().add();
/*@F8271*/	  }



/*@I8271*/
public function  buscarDetallesMCSMovimiento():void{
	if(movimientoCtaCteSeleccionado){
		var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_DETALLES_MCS_CTA_CTE_COMERCIO);
		evt.idTransaccion = movimientoCtaCteSeleccionado.idTransaccion;
		evt.idComercio=  comercio.idCodComercio;
		dispatcher.dispatchEvent(evt);
	}
	ControlBlock.getInstance().add();
}


	  public function  buscarComposicionMCSMovimientoCliente():void{
	if(movimientoCtaCteSeleccionado){
		var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_MCS_DETALLE_CTA_CTE_CLIENTE);
		evt.idTransaccion = movimientoCtaCteSeleccionado.idTransaccion;
		evt.idCliente =  0; 
		evt.idConceptoDetalle=  0; 
		this.dispatcher.dispatchEvent(evt); 
	}
	ControlBlock.getInstance().add();
/*@F8271*/	  }


	  /*@I8271*/
	  public function  buscarDetallesCOMMovimiento():void{
		  if(movimientoCtaCteSeleccionado){
			  var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_DETALLES_COM_CTA_CTE_COMERCIO);
			  evt.idTransaccion = movimientoCtaCteSeleccionado.idTransaccion;
			  evt.idComercio=  comercio.idCodComercio;
			  dispatcher.dispatchEvent(evt);
		  }
		  ControlBlock.getInstance().add();
	  }
	  
	  
	  public function  buscarComposicionCOMMovimientoCliente():void{
		  if(movimientoCtaCteSeleccionado){
			  var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_COM_DETALLE_CTA_CTE_CLIENTE);
			  evt.idTransaccion = movimientoCtaCteSeleccionado.idTransaccion;
			  evt.idCliente =  0; 
			  evt.idConceptoDetalle=  0; 
			  this.dispatcher.dispatchEvent(evt); 
		  }
		  ControlBlock.getInstance().add();
	  /*@F8271*/	  }

	  

	  public function set arrayMovientosCtaCteResumen(arrayMovientosCtaCteResumen:ArrayCollection):void{
	  	    var primero:Boolean =  true;
			_arrayMovientosCtaCteResumen = arrayMovientosCtaCteResumen;
			if(_arrayMovientosCtaCteResumen.length > 0){
				acumulado = MovimientoCtaCteComercio(_arrayMovientosCtaCteResumen.getItemAt(_arrayMovientosCtaCteResumen.length-1)).saldo;
			}			
		   dispatchEvent(new Event("changedArrayMov"));
	  }
		
		[Bindable (event="changedArrayMov")]
		public function get arrayMovientosCtaCteResumen():ArrayCollection{
			return _arrayMovientosCtaCteResumen;
		}
		
/*@I8271*/		
		public function set arrayMovientosCompSaldo(arrayMovientosCompSaldo:ArrayCollection):void{
			var primero:Boolean =  true;			
			_arrayMovientosCompSaldo = arrayMovientosCompSaldo;
			if(arrayMovientosCompSaldo.length > 0){
				/*AlertOk.show("arrayMovientosCompSaldo " + MovimientoCtaCteComercio(_arrayMovientosCompSaldo.getItemAt(0).idTransaccion));*/
				acumuladoCompSaldo = MovimientoCtaCteComercio(_arrayMovientosCompSaldo.getItemAt(_arrayMovientosCompSaldo.length-1)).saldo;				
			}			
			dispatchEvent(new Event("changedCompSaldo"));
		}
		
		[Bindable (event="changedCompSaldo")]
		public function get arrayMovientosCompSaldo():ArrayCollection{
			return _arrayMovientosCompSaldo;
/*@F8271*/		}
		
/*@I8271*/		
		public function set arrayMovientosContable(arrayMovientosContable:ArrayCollection):void{
			var primero:Boolean =  true;
			/*AlertOk.show("arrayMovientosContable CtaCteComercioModel" + arrayMovientosContable.length);*/
			_arrayMovientosContable = arrayMovientosContable;
			if(arrayMovientosContable.length > 0){
				/*AlertOk.show("arrayMovientosCompSaldo " + MovimientoCtaCteComercio(_arrayMovientosCompSaldo.getItemAt(0).idTransaccion));*/
				acumuladoContable = MovimientoCtaCteComercio(_arrayMovientosContable.getItemAt(_arrayMovientosContable.length-1)).saldo;
				/*AlertOk.show("acumuladoContable CtaCteComercioModel " + acumuladoCompSaldo);*/
			}			
			dispatchEvent(new Event("changedContable"));
		}
		
		[Bindable (event="changedContable")]
		public function get arrayMovientosContable():ArrayCollection{
			return _arrayMovientosContable;
/*@F8271*/		}
		
		
		/*@I8271*/		
		public function set arrayMovientoRELCtaCteDetalles(arrayMovientoRELCtaCteDetalles:ArrayCollection):void{
			var primero:Boolean =  true;
			_arrayMovientoRELCtaCteDetalles = arrayMovientoRELCtaCteDetalles;
			if (arrayMovientoRELCtaCteDetalles.length > 1) {
				/*AlertOk.show("arrayMovientosCompSaldo " + MovimientoCtaCteComercio(_arrayMovientosCompSaldo.getItemAt(0).idTransaccion));*/
				/*if (_arrayMovientoRELCtaCteDetalles.getItemAt(0)).idTransaccion)
				{
					mostrarTransaccionRelacion = false;
				} else {
					mostrarTransaccionRelacion = true;
				}
			} else {
				mostrarTransaccionRelacion = false;
			}	*/
				mostrarTransaccionRelacion = true;
			} else {
				mostrarTransaccionRelacion = false;		
			
		}
			dispatchEvent(new Event("changedRelacion"));
		}
		
		[Bindable (event="changedRelacion")]
		public function get arrayMovientoRELCtaCteDetalles():ArrayCollection{
			return _arrayMovientoRELCtaCteDetalles;
		/*@F8271*/		}
		
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
		 
		 
		 public function set arrayComposicionCtaCte(arrayComposicionCtaCte:ArrayCollection):void{
			  _arrayComposicionCtaCte = arrayComposicionCtaCte;			 		
			 dispatchEvent(new Event("changedArrayCom"));
		 }
		 
		 [Bindable (event="changedArrayCom")]
		 public function get arrayComposicionCtaCte():ArrayCollection{
			 return _arrayComposicionCtaCte;
		 }
		 
		 
		
	}
}