package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.event.RepactacionEvent;
	import com.tarjetafiel.caja.vo.LiqClienteRepactacion;
	import com.tarjetafiel.caja.vo.ValidaImporte;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.formatters.NumberBaseRoundType;
	import mx.formatters.NumberFormatter;
	import mx.controls.Alert;
	
	public class RepactacionModel extends EventDispatcher
	{
		[Bindable]public var validaImporte:ValidaImporte;
		[Bindable]public var arrayRepactaciones:ArrayCollection = new ArrayCollection();
		[Bindable]private var _pagoMinimo:Number = 0;
		[Bindable]public var restaPagar:Number = 0;
		[Bindable]private var _suPago:Number = 0;
		
		[Bindable]private var _simular:Boolean = false;
		[Bindable]private var _simularRef:Boolean = false;
		[Bindable]private var _liqClienteRepactacion:LiqClienteRepactacion;
		[Bindable]public var descripcionRepactacion:ArrayCollection = new ArrayCollection();
		[Bindable]public var importeFinanciar:Number = 0;
		[Bindable]public var importeTotal:Number = 0;
		
		[Bindable]private var _restaPagarVisible:Boolean = false;
		[Bindable]public var repactacionVisible:Boolean = false;
		[Bindable]private var _refinanciacion:Boolean = false;		
		[Bindable]public var title:String = "";
		[Bindable]public var pagoMinimoConPagoCuenta:Number = 0;
		
		private var numberFormat:NumberFormatter = new NumberFormatter();
		
		public function RepactacionModel()
		{
			numberFormat.rounding = NumberBaseRoundType.NEAREST;	
			numberFormat.useThousandsSeparator = false;		
			numberFormat.precision = 2;
		}
		
		[Bindable (event="changedSimular")]
		public function get simular():Boolean{
			return _simular; 
		}
		
		public function set simular(sim:Boolean):void{
			_simular = sim;
			if(_simular){
				title = "Simulación de Repactación";
			} else {
				title = "Repactación";
			}			
			dispatchEvent(new Event("changedSimular"));
		}
		
		[Bindable (event="changedrestaPagarVisible")]
		public function get restaPagarVisible():Boolean{
			return _restaPagarVisible && !refinanciacion;
		}
		
		public function set restaPagarVisible(boo:Boolean):void{		
			_restaPagarVisible = boo;
			dispatchEvent(new Event("changedrestaPagarVisible"));	
		}
		
		[Bindable (event="changedSimularRef")]
		public function get simularRef():Boolean{
			return _simularRef; 
		}
		
		public function set simularRef(sim:Boolean):void{
			_simularRef = sim;
			if(_simularRef){
				title = "Simulación de Refinanciación";
			} else {
				title = "Refinanciación";
			}
			dispatchEvent(new Event("changedSimularRef"));
		}
		
		[Bindable (event="changedRefinanciacion")]
		public function get refinanciacion():Boolean{
			return _refinanciacion;
		}
		
		public function set refinanciacion(boo:Boolean):void{
			_refinanciacion = boo;
			if(boo){
				title = "Refinanciación";
			} else {
				title = "Repactación";
			}					
			dispatchEvent(new Event("changedRefinanciacion"));
		}
		
		[Bindable (event="changedPagoMinimo")]
		public function get pagoMinimo():Number{			
			return _pagoMinimo;
		}
		
		
		
		public function set pagoMinimo(monto:Number):void{
			if(!refinanciacion){							
				_pagoMinimo = Number(numberFormat.format(monto.toString()));
				//restaPagar = _pagoMinimo - (ModelLocator.getInstance().pagoModel.importePgado + ModelLocator.getInstance().liqClienteModel.pagoACuenta);
				restaPagar = _pagoMinimo - (ModelLocator.getInstance().pagoModel.importePgado );
				restaPagarVisible = true;				
			}else 
				_pagoMinimo = 0;
								
			calcularImporteAFinancia();
			dispatchEvent(new Event("changedPagoMinimo"));
			
		}
		
		public function get suPago():Number{
			//_suPago = ModelLocator.getInstance().pagoModel.importePgado + ModelLocator.getInstance().liqClienteModel.pagoACuenta;
			_suPago = ModelLocator.getInstance().pagoModel.importePgado;
			return _suPago;
		} 
		
		private function ordenarArrayRepactaciones():void{
			var sort:Sort = new Sort();
			var sortFields:SortField = new SortField("nroCuota"); 
			sort.fields = [sortFields];
			arrayRepactaciones.sort = sort;
			arrayRepactaciones.refresh();
		}
		
		public function setArrayRepactacionesRecalculo(array:Array):void{
			liqClienteRepactacion = null;
			arrayRepactaciones.source = array;
			restaPagar = 0;
			restaPagarVisible = false;
			ordenarArrayRepactaciones();
			calcularImporteAFinancia();
		}
		
		public function recalcularRepactacion():void{			
			
			var evt:RepactacionEvent = new RepactacionEvent(RepactacionEvent.CALCULAR_REPACTACION);
			evt.cantidadCuotas = getCantidadCuotas();
			evt.montoTotal = Number(numberFormat.format(ModelLocator.getInstance().liqClienteModel.getTotalLiqRepactacion()));					
			evt.pagoMinimo = suPago;
			evt.dispatch();
		}
		
		private function getCantidadCuotas():Array{
			var arrCuotas:Array = new Array();
			for each(var rep:LiqClienteRepactacion in arrayRepactaciones){
				arrCuotas.push(rep.nroCuota);
			}
			
			return arrCuotas;
		}			
		
		public function calcularImporteAFinancia():void{
			importeFinanciar = Number(numberFormat.format(ModelLocator.getInstance().liqClienteModel.getTotalLiqRepactacion() - suPago));
			importeTotal = ModelLocator.getInstance().liqClienteModel.getTotalLiqRepactacion(); 			
		}		
		
		public function set liqClienteRepactacion(repactacion:LiqClienteRepactacion):void{
			_liqClienteRepactacion = repactacion;
			descripcionRepactacion.removeAll();
			if(repactacion != null){
				var obj:Object = new Object();
				obj["descripcion"] = "Total";
				obj["valor"] = importeTotal;
				descripcionRepactacion.addItem(obj);
				obj = new Object();
				obj["descripcion"] = "Importe a financiar";
				obj["valor"] = new Number(numberFormat.format(importeFinanciar));
				descripcionRepactacion.addItem(obj);
				obj = new Object();
				obj["descripcion"] = "Cuotas";
				obj["valor"] = repactacion.nroCuota.toString();
				descripcionRepactacion.addItem(obj);
				obj = new Object();
				obj["descripcion"] = "Importe Cuota";
				obj["valor"] = new Number(repactacion.montoCuota);
				descripcionRepactacion.addItem(obj);
				obj = new Object();
				obj["descripcion"] = "Su Pago";
				obj["valor"] = new Number(ModelLocator.getInstance().pagoModel.importePgado);
				descripcionRepactacion.addItem(obj);
				/*obj = new Object();
				obj["descripcion"] = "Pago a cuenta";
				obj["valor"] = new Number(ModelLocator.getInstance().liqClienteModel.pagoACuenta);
				descripcionRepactacion.addItem(obj);*/
				obj = new Object();
				obj["descripcion"] = "Total pago";
				obj["valor"] = new Number(suPago);
				descripcionRepactacion.addItem(obj);
				repactacionVisible = true;
			} else {
				repactacionVisible = false;
			}
		}
		
		public function get liqClienteRepactacion():LiqClienteRepactacion{
			return _liqClienteRepactacion;
		}

	}
}