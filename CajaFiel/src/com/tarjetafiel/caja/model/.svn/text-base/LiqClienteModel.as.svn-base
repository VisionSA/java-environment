package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.event.ClientesEvent;
	import com.tarjetafiel.caja.vo.CtaCteCliente;
	import com.tarjetafiel.caja.vo.LiqCliente;
	import com.tarjetafiel.caja.vo.LiqClientePago;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.components.alert.AlertWarning;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.utils.ObjectUtil;
		
	[Bindable]
	public class LiqClienteModel extends EventDispatcher
	{
		public var liqCliente:LiqCliente = new LiqCliente();
		private var _arrayLiqCliente:ArrayCollection = new ArrayCollection();
		//[Bindable]public var arrayLiqNoPagadas:ArrayCollection = new ArrayCollection();
		public var arrayCtaCte:ArrayCollection = new ArrayCollection();
		public var ctaCteClienteSet:ArrayCollection = new ArrayCollection();
		public var importeLiquidacion:Number = 0;
		public var saldo:Number = 0;
		public var vencida:Boolean = false;
		public var pagoACuenta:Number = 0;
		public var importeTotal:Number = 0;
		public function LiqClienteModel()
		{
		}
						
		public function armarCtaCte():void{
			var ctaCte:CtaCteCliente;
			importeLiquidacion = 0;
			for each(var cta:CtaCteCliente in liqCliente.ctaCteClienteSet){							
				importeLiquidacion += (cta.importe * cta.signo);
				if(ctaCte == null){
					ctaCte = CtaCteCliente(ObjectUtil.copy(cta));					
					this.arrayCtaCte.addItem(ctaCte);	
				} else {
					if(cta.transaccion != null && ctaCte.transaccion != null && cta.transaccion.idTranascciones == ctaCte.transaccion.idTranascciones){
						ctaCte.importe += (cta.importe * cta.signo);										
					} else {
						ctaCte = CtaCteCliente(ObjectUtil.copy(cta));																	
						this.arrayCtaCte.addItem(ctaCte);
					}
				}
			}
		}
			
		public function calcularSaldo():void{
			var c:int = arrayLiqCliente.length;
			var i:int = 1;
			var importe:Number = 0;
			var importePagado:Number = 0;
			importeTotal = 0;
			for each(var liq:LiqCliente in arrayLiqCliente){																
				importe = liq.montoTotal;
				importeTotal += importe; 
				importePagado = liq.importePagado;
				importe = importe - importePagado;				
				var fechaPago:Date = (liq.liqClientePagos != null && liq.liqClientePagos.length > 0) ? liq.liqClientePagos[0].fecha : null;
				liq.fechaPago = fechaPago;
				if(i==c){
					this.isVencida(liq);						
				} 
				i++;
				saldo += importe;
				liq.importeVencimiento = importe;
				liq.saldo = saldo;				
			}			
		}
		
		private function isVencida(liq:LiqCliente):Number{
			var fecha:Date = new Date();
			var importe:Number = 0;			
			for each(var liqPago:LiqClientePago in liq.liqClientePagos){
				importe = liqPago.importe;
				if(fecha > liqPago.fecha){
					vencida = true;
					break;					
				}
			}	
			
			if(importe == 0) vencida = true;		
			return importe;
		}
		
		/**
		 * 
		 * Remueve los items de
		 * liqCliente.ctaCteClienteSet
		 * arrayLiqPendientesCliente
		 * arrayLiqCliente
		 * 
		 **/		  
		public function resetLiquidaciones():void{
			ctaCteClienteSet.removeAll();
			arrayLiqCliente.removeAll();
			ModelLocator.getInstance().pagoModel.arrayLiqPagar.removeAll();			
			vencida = false;
			saldo = 0;									
		}
							
		private function filtrarPagadas(obj:Object):Boolean{
			return LiqCliente(obj).montoTotal > LiqCliente(obj).importePagado;  
		}
		
		private function sortArray(obj1:Object,obj2:Object):int{
			return ObjectUtil.compare(obj1,obj2);
		}
		
		public function getFiltro():Filtro{
			var filtro:Filtro = new Filtro();					
			filtro.campos.push("clienteTransaccion.idCliente");
			filtro.campos.push("liquidacionClientes.confirmada");
			filtro.campos.push("liquidacionClientes.finalizo");					
			filtro.operadores.push(Filtro.IGUAL);
			filtro.operadores.push(Filtro.IGUAL);
			filtro.operadores.push(Filtro.IGUAL);								
			filtro.valores.push(ModelLocator.getInstance().clienteSeleccionado.cliente.idCliente);
			filtro.valores.push("'S'");
			filtro.valores.push("'S'");
			filtro.funcion = " AND importePagado < montoTotal OR ( importePagado < montoTotal AND liquidacionClientes.tipoLiquidacion = 2 and clienteTransaccion.idCliente = " + ModelLocator.getInstance().clienteSeleccionado.cliente.idCliente + ")";
			return filtro;
		}
		
		
		public function buscarLiquidaciones():void{
			if(ModelLocator.getInstance().clienteSeleccionado.cliente != null){
				var filtro:Filtro = getFiltro();							
				var event:ClientesEvent = new ClientesEvent(ClientesEvent.BUSCAR_LIQUIDACIONES,filtro,null);
				event.dispatch();
			} else {
				AlertWarning.show("No hay ningún cliente seleccionado");
			}
		}
		
		public function pagarTodo():void{
			ModelLocator.getInstance().pagoModel.pagarTodo = true;
			ModelLocator.getInstance().pagoModel.arrayLiqPagar.removeAll();
			for each(var liq:LiqCliente in arrayLiqCliente){
				liq.pagar = true;
				ModelLocator.getInstance().pagoModel.arrayLiqPagar.addItem(liq);
			}
		}
		
		public function getRepactaciones():Array{
			var arrayRepactaciones:Array = new Array();
			if(arrayLiqCliente.length>0){
				arrayRepactaciones = LiqCliente(arrayLiqCliente.getItemAt(arrayLiqCliente.length-1)).liqClienteRepactacion;	
			}
			return arrayRepactaciones;
		}			
		

		public function get arrayLiqCliente():ArrayCollection{
			return _arrayLiqCliente;
		}
		
		public function set arrayLiqCliente(target:ArrayCollection):void{
			_arrayLiqCliente = target;
			pagoACuenta = 0;
			for each(var liq:LiqCliente in _arrayLiqCliente){
				pagoACuenta += liq.importePagado;
				getLeyendaVencimiento(liq);
			}	
			
			dispatchEvent(new Event("arrayLiqClienteChanged"));		
		}
		
		public function getLeyendaVencimiento(data:LiqCliente):void{
				
				var fechaCierre:Date = data.fechaCierre == null ? data.fechaLiq : data.fechaCierre;
				var diferencia:Number = new Date().getTime() - fechaCierre.getTime();
				diferencia = diferencia/86400000;
				
				if(diferencia >= 15 && diferencia < 20){
					
					data.leyendaVencimiento = "Vencida 15 dias";
					
				} else if(diferencia >= 20 && diferencia < 25){
					
					data.leyendaVencimiento = "Vencida 20 dias";
					
				} else if(diferencia > 25){
					
					data.leyendaVencimiento = "Vencida 25 dias";
					
				} else {
					
					data.leyendaVencimiento = "Normal";
				
											
				}
				
 
		}
		
		public function getInteresesRepactacion():Number{
			var intereses:Number = 0;
			for each(var liq:LiqCliente in _arrayLiqCliente){
				intereses += liq.totalIntereses;
			}
			
			return intereses;
		}
		
		public function getTotalLiqRepactacion():Number{
			var total:Number = 0;
			for each(var liq:LiqCliente in _arrayLiqCliente){
				total += liq.montoTotal - liq.importePagado;
			}
							
			return total;
		}
		
		public static function ordenarLiq(a:Object, b:Object):Number {
			var acc1 : LiqCliente = LiqCliente(a);
			var acc2 : LiqCliente = LiqCliente(b);
			if (acc1.idLiqCliente > acc2.idLiqCliente){
				return 1;
			}else if (acc1.idLiqCliente < acc2.idLiqCliente){
				return -1;
			}else {
				return 0;
			}
		}
		
		public function getTotalLiqRepactado():Number{
			var total:Number = 0;
			for each(var liq:LiqCliente in _arrayLiqCliente){
				total += liq.totalRepactado;
			}
			
			return total;
		}
		
		
		
		public function selectLiqAPagar(importePagado:Number):void{
			if(ModelLocator.getInstance().pagoModel.pagarTodo == false && arrayLiqCliente && arrayLiqCliente.length > 0){	
			
				ModelLocator.getInstance().pagoModel.arrayLiqPagar.removeAll();
				for each(var liq:LiqCliente in arrayLiqCliente){
					liq.pagar = false;								
				}		
				
				ModelLocator.getInstance().pagoModel.importeTotal = LiqCliente(arrayLiqCliente.getItemAt(0)).saldo;
				
				for(var i:int=0; i<arrayLiqCliente.length; i++){
					ModelLocator.getInstance().pagoModel.arrayLiqPagar.addItem(arrayLiqCliente.getItemAt(i));
					if(LiqCliente(arrayLiqCliente.getItemAt(i)).saldo < importePagado && arrayLiqCliente.length != (i+1)){						
						LiqCliente(arrayLiqCliente.getItemAt(i+1)).pagar = true;					
						ModelLocator.getInstance().pagoModel.importeTotal = LiqCliente(arrayLiqCliente.getItemAt(i+1)).saldo;
					} else {
						break;
					}
				}
				
				LiqCliente(arrayLiqCliente.getItemAt(0)).pagar = true;
			}
			
		}  
				
	}
}