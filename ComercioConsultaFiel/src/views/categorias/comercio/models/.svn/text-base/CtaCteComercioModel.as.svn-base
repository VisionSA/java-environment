package views.categorias.comercio.models
{
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.Empresa;
	import com.tarjetafiel.caja.vo.MovimientoCtaCteComercio;
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
	   public var  saldo:Number=0;	
	   public var saldoAcumulResumen:Number=0; 
	   public var acumulado:Number= 0;  
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
	  
	  public function  buscarDetallesMovimiento():void{
		 if(movimientoCtaCteSeleccionado){
            var evt:ComercioManagerEvent = new ComercioManagerEvent(ComercioManagerEvent.BUSCAR_DETALLES_MOVIMIENTO_CTA_CTE_COMERCIO);
            evt.idTransaccion = movimientoCtaCteSeleccionado.idTransaccion;
            evt.idComercio=  comercio.idCodComercio;
         	dispatcher.dispatchEvent(evt);
         }
          ControlBlock.getInstance().add();
	  }

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
	}
}