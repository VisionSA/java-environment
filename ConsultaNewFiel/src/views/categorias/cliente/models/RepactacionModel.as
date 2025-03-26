package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertWarning;
	
	import events.RepactacionEvent;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import managers.ConceptosManager;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.rpc.Fault;
	
	[Bindable]
	public class RepactacionModel extends EventDispatcher
	{
		
		public var dispatcher:IEventDispatcher;
		
		public var deuda:Number;
		
		public var pagoCuenta:Number;
		
		private var _suPago:Number; 
		
		public var deudaFinal:Number; 
		
		public var pagoTotal:Number;
		
		public var importeTotal:Number;
		
		public var conceptosManager:ConceptosManager;
		
		public var titular:ClienteTransaccion;
		
		public var arrayRepactaciones:ArrayCollection = new ArrayCollection();
		
		public function RepactacionModel()
		{
		}
		
		public function set suPago(pago:Number):void{
			_suPago = pago;
			deudaFinal = deuda - suPago;
			deudaFinal = deudaFinal < 0 ? 0 : deudaFinal;
			pagoTotal = _suPago + pagoCuenta;
			importeTotal = pagoTotal + deudaFinal;
			dispatchEvent(new Event("changedSuPago"));
		}
		
		[Bindable(event="changedSuPago")]
		public function get suPago():Number{
			return _suPago;
		}
		
		public function setDeuda(number:Number):void{			
			deuda = number;
			deudaFinal = deuda;
			importeTotal = deuda;
		}
		
		public function setPagoACuenta(number:Number):void{
			pagoCuenta = number;
			pagoTotal = pagoCuenta;
		} 
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultString);
		}
		
		public function resetValues():void{
			deuda = 0;
		
			pagoCuenta = 0;
		
			suPago = 0; 
		
			deudaFinal = 0; 
		
			pagoTotal = 0;
		
			importeTotal = 0;
			
			arrayRepactaciones.removeAll();
		}
		
		public function simular(repactacion:Boolean):void{
			
			if(titular == null){
				AlertWarning.show("No hay un cliente sleccionado");
				return;
			}
			
			var evt:RepactacionEvent = new RepactacionEvent(RepactacionEvent.SIMULAR);
			if(repactacion)
				evt.concepto = conceptosManager.getConcepto(ConceptosManager.REPACTACION);
			else 
				evt.concepto = conceptosManager.getConcepto(ConceptosManager.REFINANCIACION);
				
			evt.cliente = titular;
			evt.montoTotal = importeTotal;
			evt.suPago = suPago;
			dispatcher.dispatchEvent(evt);
				
		}
		
		public function resultSimulacion(array:Array):void{
			arrayRepactaciones.source = array;
			ordenarArrayRepactaciones();
		}
		
		private function ordenarArrayRepactaciones():void{
			var sort:Sort = new Sort();
			var sortFields:SortField = new SortField("nroCuota"); 
			sort.fields = [sortFields];
			arrayRepactaciones.sort = sort;
			arrayRepactaciones.refresh();
		}
		
	}
}