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
	import mx.controls.Alert;
	import com.util.block.ControlBlock;
	
	import com.tarjetafiel.proveedorconexion.model.ModelLocatorGeneral;
	
	
	[Bindable]
	public class RepactacionModel extends EventDispatcher
	{
		
		public var dispatcher:IEventDispatcher;
		
		public var pagoMinimo:Number;
		
		public var deuda:Number;
		
			public var pagoNoFacturado:Boolean;
		
		public var pagoCuenta:Number;
		
		private var _suPago:Number; 
		
		public var deudaFinal:Number; 
		
		public var pagoTotal:Number;
		
		public var importeTotal:Number;
		
		public var conceptosManager:ConceptosManager;
		
		public var titular:ClienteTransaccion;
		
		public var arrayRepactaciones:ArrayCollection = new ArrayCollection();
		
		public var arrayRepactaciones1:ArrayCollection = new ArrayCollection();
		
		
		
		[Bindable]
		public var modelo : ModelLocatorGeneral = ModelLocatorGeneral.getInstance();
		
		
		public function RepactacionModel()
		{
		}
		
		public function set suPago(pago:Number):void{
			_suPago = pago;
			deudaFinal = deuda - suPago;
			/*deudaFinal = deudaFinal < 0 ? 0 : deudaFinal;*/
			pagoTotal = _suPago + pagoCuenta;
			importeTotal = pagoTotal + deudaFinal;
			/*dispatchEvent(new Event("changedSuPago"));*/
		}
		
		/*[Bindable(event="changedSuPago")]*/
		public function get suPago():Number{
			return _suPago;
		}
		
		public function setDeuda(number:Number):void{	
			pagoNoFacturado = "false";
			deuda = number 
			deudaFinal = deuda;
			importeTotal = deuda;
		}
		
		public function setPagoMinimo(number:Number):void{
			if (number < 0) {
				number = 0;
			}
			pagoMinimo = number;			
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
			
			pagoNoFacturado = "false";
			
			pagoMinimo = 0;
			
			
					
			arrayRepactaciones.removeAll();
			
			arrayRepactaciones1.removeAll();
		}
		
		public function simular(repactacion:Boolean):void{
			
			if(titular == null){
				AlertWarning.show("No hay un cliente sleccionado");
				return;
			}
			
			
			if(repactacion) {
				var evt:RepactacionEvent = new RepactacionEvent(RepactacionEvent.SIMULARREPACTACION);
				evt.concepto = conceptosManager.getConcepto(ConceptosManager.REPACTACION);
			evt.cliente = titular;
			evt.montoTotal = modelo.importeTotal1;
			evt.suPago = modelo.pagoTotal1;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
			} else {
				var evt:RepactacionEvent = new RepactacionEvent(RepactacionEvent.SIMULAR);
				evt.concepto = conceptosManager.getConcepto(ConceptosManager.REFINANCIACION);
				
			evt.cliente = titular;
			evt.montoTotal = modelo.importeTotal;
			evt.suPago = modelo.pagoTotal;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}	
		}
		
		public function resultSimulacion(array:Array):void{
			arrayRepactaciones.source = array;
			ordenarArrayRepactaciones();
			ControlBlock.getInstance().remove();
		}
		
		public function resultSimulacion1(array:Array):void{
			arrayRepactaciones1.source = array;
			ordenarArrayRepactaciones1();
			ControlBlock.getInstance().remove();
		}
		
		private function ordenarArrayRepactaciones():void{
			var sort:Sort = new Sort();
			var sortFields:SortField = new SortField("nroCuota"); 
			sort.fields = [sortFields];
			arrayRepactaciones.sort = sort;
			arrayRepactaciones.refresh();
		}
		
		private function ordenarArrayRepactaciones1():void{
			var sort:Sort = new Sort();
			var sortFields:SortField = new SortField("nroCuota"); 
			sort.fields = [sortFields];
			arrayRepactaciones1.sort = sort;
			arrayRepactaciones1.refresh();
		}
	}
}