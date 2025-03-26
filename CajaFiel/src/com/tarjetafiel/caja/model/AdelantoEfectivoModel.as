package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.event.AdelantoEfectivoEvent;
	import com.tarjetafiel.caja.event.CalculoCuotaEvent;
	import com.tarjetafiel.caja.vo.Cuota;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertWarning;
	import com.util.components.alert.AlertYesNo;
	import com.util.format.FormatUtil;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.controls.Alert;
	import mx.events.CloseEvent;

	[Bindable]
	public class AdelantoEfectivoModel extends EventDispatcher
	{
		private var _cuotas:ArrayCollection = new ArrayCollection();
		public var cuota:ArrayCollection = new ArrayCollection();
		private var _importe:Number = 0;
		private var _disponibleAdelanto:Number = 0; 		
		
		public function AdelantoEfectivoModel()
		{			
		}
		
		[Bindable(event='changedDisponibleAdelanto')]
		public function get disponibleAdelanto():Number{
			return _disponibleAdelanto;
		}
		
		public function set disponibleAdelanto(value:Number):void{			
			if(value < 0){
				value = 0;
			}
			_disponibleAdelanto = value;
			dispatchEvent(new Event('changedDisponibleAdelanto'));
		}
		
		public function calcularCuota(importe:Number):void{
			cuotas.removeAll();
			cuota.removeAll();
			
			if(importe > disponibleAdelanto){
				AlertError.show("El monto del adelanto supera el disponible");
				return;
			}
			
			if(!ModelLocator.getInstance().clienteSeleccionado.cliente){
				AlertWarning.show("No hay un cliente seleccionado");
				return;
			}
			
			/*if(ModelLocator.getInstance().clienteSeleccionado.disponible < importe){
				AlertWarning.show("El monto ingresado supera el disponible");
				return;
			}*/
			
			_importe = importe;
			
			this.importe = importe;
			var evt:CalculoCuotaEvent = new CalculoCuotaEvent(CalculoCuotaEvent.CALCULO_CUOTA);
			evt.cliente = ModelLocator.getInstance().clienteSeleccionado.cliente;
			evt.concepto = ModelLocator.getInstance().conceptosModel.getConcepto(ConceptosModel.ADELANTO_EFECTIVO);
			evt.importe = importe;
			evt.dispatch();
			
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
		
		public function registrarAdelanto(cuota:Cuota, monto:Number):void{			
			
			if(ModelLocator.getInstance().clienteSeleccionado.clienteAdicional){
				if(ModelLocator.getInstance().clienteSeleccionado.clienteAdicional.habilitadoConsumo == 'N'){
					AlertWarning.show("El cliente no está habilitado para consumo");
					return;
				}
			}
			
			if(ModelLocator.getInstance().clienteSeleccionado.cliente){
				if(ModelLocator.getInstance().clienteSeleccionado.cliente.habilitadoConsumo == 'N'){
					AlertWarning.show("El cliente titular no está habilitado para consumo");
					return;
				}
			}
			
			AlertYesNo.show("¿Confirma el registro del adelanto en efectivo?\n"+
			"Cuotas " + cuota.cantidadCuota + " - Importe Cuota " + FormatUtil.formatMoneda(cuota.capital + cuota.interes),
				function (event:CloseEvent):void{
				if(event.detail == Alert.YES){
					var arrayCuotas:Array = getCuotas(cuota);
					var evt:AdelantoEfectivoEvent = new AdelantoEfectivoEvent(AdelantoEfectivoEvent.REGISTRAR_ADELANTO);
					evt.cliente = ModelLocator.getInstance().clienteSeleccionado.cliente;
					evt.concepto = ModelLocator.getInstance().conceptosModel.getConcepto(ConceptosModel.ADELANTO_EFECTIVO);
					evt.cuotas = arrayCuotas;
					evt.monto = monto;
					evt.dispatch();			
				}	
			});			
			
		}
		
		public function get cuotas():ArrayCollection{
			return _cuotas;
		}
		
		public function set importe(importe:Number):void {
			_importe = importe;
		}
		
		public function get importe():Number{
			return _importe;
		}
	}
}