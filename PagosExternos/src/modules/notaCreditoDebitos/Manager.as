package modules.notaCreditoDebitos
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.Empresa;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	import com.util.components.alert.AlertWarning;
	import com.util.components.alert.AlertYesNo;
	import com.util.format.FormatUtil;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import models.ApplicationModel;
	
	import modules.notaCreditoDebitos.events.NotaCreditoDebitoEvent;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.events.CloseEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.Fault;
	
	[Bindable]
	public class Manager extends EventDispatcher
	{
		
		public var dispatcher:IEventDispatcher;
		
		public var cliente:ClienteTransaccion;
		
		public var comercio:CodComercio;		
		
		public var busquedaConsumosVisible:Boolean = false;
		
		public var consumos:ArrayCollection = new ArrayCollection();
		
		public var pagos:ArrayCollection = new ArrayCollection();
		
		private var fechaDesde:Date;
		
		private var fechaHasta:Date;
		
		private var estadoImpacto:String;
		
		public var labelBotonBusquedaCtaCte:String = "Buscar Consumos / Cargos";
		
		public var soloPagos:Boolean = false;
		
		public var busquedaClienteDestinoVis:Boolean = true;
		
		public var clienteDestino:ClienteTransaccion;
		
		private var _pagoAnular:Object;
		
		public var pagoRevertirStr:String;
		
		public var buscarClienteDestino:Boolean;

		public var empresa:Empresa;
		
		public var codPosnet:String;

		public var applicationModel:ApplicationModel;
		
		private var typeDispatchCompleteNCND:String;
		
		public var clienteVisible:Boolean = true;
			
		public var comercioVisibleVisible:Boolean = false;
			
		public var liquidacionVisibleVisible:Boolean = false;
		
		public var buscar:int = 0;
		
		public var idLiqCliente:String;
		
		public function Manager()
		{
		}
		
		public function buscarCliente(idCliente:String,clienteDestino:Boolean=false):void{
			buscar = 0;
			this.buscarClienteDestino = clienteDestino;
			
			if(idCliente.length > 0){
				consumos.removeAll();
				
				if(clienteDestino == false){
					pagos.removeAll();		
				}
						
				var evt:NotaCreditoDebitoEvent = new NotaCreditoDebitoEvent(NotaCreditoDebitoEvent.BUSCAR_CLIENTE);
				evt.idCliente = Number(idCliente);
				dispatcher.dispatchEvent(evt);
				ControlBlock.getInstance().add();
			} else {
				AlertWarning.show("Debe ingresar un numero de cuenta para realizar la busqueda");
			}
		}
		
		public function buscarPorCodPosnet(codPosnet:String):void{
			buscar = 1;
			this.codPosnet = codPosnet;
			var evt:NotaCreditoDebitoEvent = new NotaCreditoDebitoEvent(NotaCreditoDebitoEvent.BUSCAR_EMPRESA);
			evt.codPosnet = Number(codPosnet);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		public function buscarPorLiquidacion(idLiqCliente:String):void{
			buscar = 2;
			this.idLiqCliente = idLiqCliente;
			var evt:NotaCreditoDebitoEvent = new NotaCreditoDebitoEvent(NotaCreditoDebitoEvent.BUSCAR_CONSUMOS_LIQ_CLIENTE);
			evt.idLiqCliente = Number(idLiqCliente);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		
		public function resetList():void{
			this.consumos.removeAll();
			this.cliente = null;
			this.clienteDestino = null;
		}
		
		public function resultBusquedaCliente(cliente:ClienteTransaccion):void{
			
			ControlBlock.getInstance().remove();
			if(!isNaN(cliente.idTitular)){
				AlertWarning.show("Debe ingresar una cuenta titular");
				return;
			}
			
			if(this.buscarClienteDestino){
				this.clienteDestino = cliente;
				
				if(clienteDestino.idCliente == this.cliente.idCliente){
					AlertWarning.show("El cliente de origen y destino no pueden ser el mismo");
					return;
				}
				
				if(clienteDestino == null){
					busquedaClienteDestinoVis = true;
					AlertWarning.show("No se encontró ningún cliente.");
				} else {
					busquedaClienteDestinoVis = false;
				}
				
			} else {
				this.cliente = cliente;	
				
				if(cliente == null){
					busquedaConsumosVisible = false;
					AlertWarning.show("No se encontró ningún cliente.");
				} else {
					busquedaConsumosVisible = true;
				}
			}
										
		}
		
		public function resultBusquedaEmpresa(empresa:Empresa):void{
			
			ControlBlock.getInstance().remove();
						
			this.empresa = empresa;	
				
			if(empresa == null){
				busquedaConsumosVisible = false;
				AlertWarning.show("No se encontró ninguna Empresa.");
			} else {
					busquedaConsumosVisible = true;
			}
										
		}
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
		public function buscarConsumos(fechaDesde:Date,fechaHasta:Date,estado:String,soloPagos:Boolean=false):void{
				
			var evt:NotaCreditoDebitoEvent;
			this.fechaDesde = fechaDesde;
			this.fechaHasta = fechaHasta;
			this.estadoImpacto = estado;
			if(clienteVisible){
			
				this.soloPagos = soloPagos;
				
							
				if(soloPagos){
					pagos.removeAll();
					evt = new NotaCreditoDebitoEvent(NotaCreditoDebitoEvent.BUSCAR_PAGOS);	
				} else {
					consumos.removeAll();
					evt = new NotaCreditoDebitoEvent(NotaCreditoDebitoEvent.BUSCAR_CONSUMOS);
				}
				
				evt.fechaDesde = fechaDesde;
				evt.fechaHasta = fechaHasta;
				evt.estadoImpacto = estado;
				evt.idCliente = cliente.idCliente;
				dispatcher.dispatchEvent(evt);
				ControlBlock.getInstance().add();
			} else {
				evt = new NotaCreditoDebitoEvent(NotaCreditoDebitoEvent.BUSCAR_CONSUMOS_COD_POSNET);
				evt.codPosnet = Number(codPosnet);
				evt.fechaDesde = fechaDesde;
				evt.fechaHasta = fechaHasta;
				evt.estadoImpacto = estado;
				dispatcher.dispatchEvent(evt);
				ControlBlock.getInstance().add();
			}
		}
		
		public function resultBuscarConsumos(result:Object):void{					
			if(soloPagos){
				this.pagos.source = result as Array;
			} else {
				this.consumos.source = result as Array;	
			}
									
			ControlBlock.getInstance().remove();
		}

		public function realizarNCConsumo(consumo:Object):void{
			AlertYesNo.show("¿Confirma la realización de la nota de crédito del Consumo/Cargo " + consumo.transaccion + "?",
			function (event:CloseEvent):void{
				if(event.detail == Alert.YES){
					var evt:NotaCreditoDebitoEvent = new NotaCreditoDebitoEvent(NotaCreditoDebitoEvent.REALIZAR_NC_CONSUMO);
					evt.idCliente = consumo.cliente;
					evt.idTransaccion = consumo.transaccion;
					evt.operador = applicationModel.operador;
					evt.estadoImpacto = consumo.estadoImpacto;
					dispatcher.dispatchEvent(evt);
					ControlBlock.getInstance().add();
				}	
			})
			
		}
		
		public function resultNCConsumo():void{
			ControlBlock.getInstance().remove();
			AlertOk.show("La nota de Crédito se realizó con exito.",function(evt:CloseEvent):void{
				if(buscar == 2){
					buscarPorLiquidacion(idLiqCliente);
				} else {
					buscarConsumos(fechaDesde,fechaHasta,estadoImpacto);	
				}
				
			});					
		}
		
		public function changedChkSloPagos(value:Boolean):void{
			soloPagos = value;
			labelBotonBusquedaCtaCte = value == false ? "Buscar Consumos / Cargos" : "Buscar Pagos";
		}
		
		public function set pagoAnular(value:Object):void{
			_pagoAnular = value;
			var frm:DateFormatter = new DateFormatter();
			frm.formatString = "DD/MM/YYYY";			
			pagoRevertirStr =  frm.format(_pagoAnular.fecha) + " - " + _pagoAnular.transaccion + " - " + FormatUtil.formatMoneda(Number(_pagoAnular.importe) * -1);			
			dispatchEvent(new Event("pagoAnularChanged"));
		}
		
		[Bindable (event="pagoAnularChanged")]
		public function get pagoAnular():Object{
			return _pagoAnular; 
		}
		
		public function realizarNDPago(consumo:Object):void{
			AlertYesNo.show("¿Confirma la realización de la nota de débito del pago " + consumo.transaccion + "?",
			function (event:CloseEvent):void{
				if(event.detail == Alert.YES){
					var evt:NotaCreditoDebitoEvent = new NotaCreditoDebitoEvent(NotaCreditoDebitoEvent.REALIZAR_ND_PAGO);
					evt.idCliente = cliente.idCliente;
					evt.idClienteDestino = clienteDestino.idCliente;
					evt.idTransaccion = consumo.transaccion;
					evt.operador = applicationModel.operador;
					dispatcher.dispatchEvent(evt);
					ControlBlock.getInstance().add();
				}	
			})
			
		}
		
		public function resultNDPago():void{
			ControlBlock.getInstance().remove();
			AlertOk.show("La nota de Débito se realizó con exito.",function(evt:CloseEvent):void{
				buscarConsumos(fechaDesde,fechaHasta,estadoImpacto,true);				
			});
			dispatcher.dispatchEvent(new Event("closePopUpConfirmacionAnuPago"));
		}
		
		public function realizarNotaCreditoIvaCliente(importe:Number,stringType:String,typeEvent:String,strTypeDispatch:String):void{
			
			
			if(importe <= 0){
				AlertWarning.show("El importe debe ser mayor a 0");
				return;
			}
			
			AlertYesNo.show("¿Confirma la realización de la " + stringType + "?",function (event:CloseEvent):void{
				if(event.detail == Alert.YES){
					var evt:NotaCreditoDebitoEvent = new NotaCreditoDebitoEvent(typeEvent);
					evt.idCliente = cliente.idCliente;		
					evt.importe = importe;				
					evt.operador = applicationModel.operador;
					dispatcher.dispatchEvent(evt);
					typeDispatchCompleteNCND = strTypeDispatch;
					ControlBlock.getInstance().add();	
				}
					
			});
			
		}
		
		public function resultNotaCreditoIvaCliente():void{
			ControlBlock.getInstance().remove();
			dispatcher.dispatchEvent(new Event(typeDispatchCompleteNCND));
			AlertOk.show("La nota de Crédito se realizó con exito.");
		}
		
		
		public function resultNotaDebitoIvaCliente():void{
			ControlBlock.getInstance().remove();
			dispatcher.dispatchEvent(new Event(typeDispatchCompleteNCND));
			AlertOk.show("La nota de Débito se realizó con exito.");
		}
		
		
	}
}