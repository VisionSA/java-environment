package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.vo.CtaCteCliente;
	import com.tarjetafiel.caja.vo.LiqCliente;
	import com.tarjetafiel.caja.vo.MovimientoMP;
	import com.tarjetafiel.caja.vo.Recibo;
	
	import mx.collections.ArrayCollection;
	import mx.formatters.CurrencyFormatter;
	import mx.utils.ObjectUtil;	
	import flash.events.EventDispatcher;
	
	public class PagoModel extends EventDispatcher
	{
		[Bindable]public var arrayLiqPagar:ArrayCollection = new ArrayCollection();
		[Bindable]public var activarRepactacion:Boolean = false;
		[Bindable]public var importeTotal:Number = 0;
		[Bindable]private var _importePgado:Number = 0;
		private var importeRefinanciado:Number = 0;
		public var importePgadoTemp:Number = 0;
		[Bindable]public var pagoACuentaRepactacion:Number = 0;
		[Bindable]public var arrayMedios:ArrayCollection = new ArrayCollection();
		[Bindable]public var pagarTodo:Boolean = false;
		[Bindable]private var _importeEfectivo:Number = 0;
		
		[Bindable]public var chkPagoCheque:Boolean = false;
		[Bindable]public var esModificarCheque:Boolean = false;
		
		
		[Bindable]public var ctaCteCliente:CtaCteCliente = null;		
		[Bindable]public var suVuelto:Number;
		[Bindable]public var funcionPagoRealizado:Function = null;
		[Bindable]public var nroReciboCobrador:String = null;
		
		//Modificadores ViewNumeroRecibo
		[Bindable]public var existeRecibo:Boolean = false;
		[Bindable]public var recibo:Recibo = null;
		
		public function PagoModel()
		{
			
		}
		
		public function calcularImporte():void{
			importeRefinanciado = 0;
			importeEfectivo = 0;
			arrayMedios.removeAll();
			importePgado = 0;
			importeTotal = 0;			
			pagoACuentaRepactacion = 0;
			
			
			for each(var liq:LiqCliente in arrayLiqPagar){
				importeTotal += liq.importeVencimiento;
			}
									
			
		}
		
		
		public function addMedio(obj:MovimientoMP):void{			
			this.arrayMedios.addItem(obj);			
			this.importePgado += obj.monto;			
			if(obj.formaPago.idFormaPago == 5 || obj.formaPago.idFormaPago == 1){
				importeEfectivo += obj.monto; 
			}
			if(obj.formaPago.idFormaPago == 8 || obj.formaPago.idFormaPago == 10){
				importeRefinanciado = obj.monto;
			}
			
			
			ModelLocator.getInstance().liqClienteModel.selectLiqAPagar(importePgado);
			
		}
				
		public function modificarMedio(medioNuevo:MovimientoMP, medioViejo:MovimientoMP):void{			
			
			this.importePgado -= medioViejo.monto; 
			this.importePgado += medioNuevo.monto;
			this.arrayMedios.setItemAt(medioNuevo,arrayMedios.getItemIndex(medioViejo));
			if(medioNuevo.formaPago.idFormaPago == 5 || medioNuevo.formaPago.idFormaPago == 1){
				importeEfectivo -= medioViejo.monto; 
				importeEfectivo += medioNuevo.monto;
			}
			
			ModelLocator.getInstance().liqClienteModel.selectLiqAPagar(importePgado);
		}
		
		public function modificarMontoMedio(medioNuevo:MovimientoMP, medioViejo:MovimientoMP):void{			
			this.importePgado -= medioViejo.monto; 
			this.importePgado += medioNuevo.monto;	
			
			ModelLocator.getInstance().liqClienteModel.selectLiqAPagar(importePgado);		
		}
		
		public function removeMedio(obj:MovimientoMP):void{
			this.arrayMedios.removeItemAt(arrayMedios.getItemIndex(obj));
			this.importePgado -= obj.monto; 	
			if(obj.formaPago.idFormaPago == 5 || obj.formaPago.idFormaPago == 1){
				importeEfectivo -= obj.monto; 
			}
			if(obj.formaPago.idFormaPago == 8 || obj.formaPago.idFormaPago == 10){
				importeRefinanciado = 0;
			}		
			
			ModelLocator.getInstance().liqClienteModel.selectLiqAPagar(importePgado);
		}
		
		public function removeLiqAPagar():void{
			for each(var liq:LiqCliente in ModelLocator.getInstance().liqClienteModel.arrayLiqCliente){
				liq.pagar = false;				
			}
			
			arrayLiqPagar.removeAll();			
			
		}
		
		public function hayPagoEnEfectivo():Boolean{
			for each(var medio:MovimientoMP in arrayMedios){
				//Efectivo o tickets
				if(medio.formaPago.idFormaPago == 1 || medio.formaPago.idFormaPago == 5){
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Verifica que si el pago es completo el importe a pagar coincida
		 * con el importe pagado  
		 **/
		public function importeIngresadoCorrecto(format:CurrencyFormatter):Boolean{
			if(pagarTodo){
				return importeTotal <= importePgado;	
			}			
			else 
				return true;
		}
		
		public function set importePgado(imp:Number):void{
			_importePgado = imp;
			dispatchEvent(new Event("importePgadoChanged"));
		}
		
		[Bindable (event="importePgadoChanged")]
		public function get importePgado():Number{			
			return _importePgado;
		}
		
		public function getImporteSinRefinanciacion():Number{
			return _importePgado - importeRefinanciado;
		}
		
		[Bindable (event="changedImporte")]
		public function get importeEfectivo():Number{
			return _importeEfectivo;
		} 
		
		public function set importeEfectivo(impor:Number):void{
			_importeEfectivo = impor;
			dispatchEvent(new Event("changedImporte"));
		}
		
				
	}
}