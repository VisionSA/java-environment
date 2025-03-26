package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Cuota;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertWarning;
	
	import events.AdelantoEfectivoEvent;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import managers.ConceptosManager;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	
	import mx.controls.Alert;
	
	[Bindable]
	public class AdelantoEfectivoModel extends EventDispatcher
	{
		
		public var dispatcher:IEventDispatcher;
		
		public var cuota:ArrayCollection = new ArrayCollection();

		public var cliente:ClienteTransaccion;
		
		public var titular:ClienteTransaccion;
		
		public var conceptoManager:ConceptosManager;
		
		private var _cuotas:ArrayCollection = new ArrayCollection();
		
		private var _importe:Number;		
		
		public function AdelantoEfectivoModel(){
			importe = 0;
		}
		
		public function calcularCuota(importe:Number):void{
			
			if(!titular){
				AlertWarning.show("No hay un cliente seleccionado");
				return;
			}
			
			_importe = importe;
			
			if(isNaN(importe)){
				AlertWarning.show("Debe ingresar un importe");
				return;
			}
			
			if(importe <= 0){
				AlertWarning.show("El importe debe ser mayor a cero");
				return;
			}

			cuotas.removeAll();
			cuota.removeAll();
			
			this.importe = importe;
			var evt:AdelantoEfectivoEvent = new AdelantoEfectivoEvent(AdelantoEfectivoEvent.CALCULAR_CUOTAS);
			evt.cliente = titular;
			evt.concepto = conceptoManager.getConcepto(ConceptosManager.ADELANTO_EFECTIVO);
			evt.importe = importe;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		public function set cuotas(array:ArrayCollection):void{
			_cuotas = array;
			this.cuota.removeAll();
			var cuotaAux:Cuota; 
			for each(var cuotas:Array in _cuotas){
				for each(var cuota:Cuota in cuotas){
					if(cuotaAux == null){
						cuotaAux = cuota;
						this.cuota.addItem(cuota);
					} else {
						if(cuotaAux.cantidadCuota != cuota.cantidadCuota){
							this.cuota.addItem(cuota);
							cuotaAux = cuota;
						}
					}
				}
			}
			sortCuotas();
		}
		
		public function get cuotas():ArrayCollection{
			return _cuotas;
		}
		
		private function getCuotas(cuota:Cuota):Array{
			for each(var arr:Array in _cuotas){
				for each(var cuo:Cuota in arr){
					if(cuo.cantidadCuota == cuota.cantidadCuota){
						return arr;
					}
				}
			}
			
			throw new Error("No se encontraron las cuotas");			
		}
		
		private function sortCuotas():void{
			var sort:Sort = new Sort();
			sort.fields = [new SortField("cantidadCuota")];
			cuota.sort = sort;			
			cuota.refresh();
		}
		
		public function set importe(importe:Number):void {
			_importe = importe;
			dispatchEvent(new Event("changedImporte"));
		}
		
		[Bindable(event="changedImporte")]
		public function get importe():Number{
			return _importe;
		}
		
		public function resetImporte():void{
			importe = 0;			
			cuota.removeAll();
			cuota.refresh();
			
		}

	}
}