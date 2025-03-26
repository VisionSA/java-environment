package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.clientes.ClientesDelegate;
	import com.tarjetafiel.caja.event.ClientesEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.paginacion.Paginador;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.managers.PopUpManager;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class ClientesCommand implements ICommand, IResponder
	{		

		private var evt:CairngormEvent;		
		private var iniciarBusquedaDetalle:int = 0;
		private var filtro:Filtro;
		public function execute(event:CairngormEvent):void
		{
			evt = event;
			ControlBlock.getInstance().add();			
			ModelLocator.getInstance().clienteSeleccionado.futurosVencimientos.removeAll();
			if(event.type == ClientesEvent.BUSCAR_CLIENTES){
											
				ModelLocator.getInstance().liqClienteModel.resetLiquidaciones();
				new ClientesDelegate(this).buscar( (event as ClientesEvent).filtro, (event as ClientesEvent).paginador);
				
			} else if (event.type == ClientesEvent.BUSCAR_CLIENTES_CUENTA){
				
				ModelLocator.getInstance().liqClienteModel.resetLiquidaciones();
				new ClientesDelegate(this).buscar((event as ClientesEvent).filtro, (event as ClientesEvent).paginador);
				
			} else if(event.type == ClientesEvent.BUSCAR_LIQUIDACIONES){
								
				ModelLocator.getInstance().liqClienteModel.resetLiquidaciones();
				new ClientesDelegate(this).buscarLiquidaciones((event as ClientesEvent).filtro);
				
			} else if(event.type == ClientesEvent.BUSCAR_LIQUIDACIONES_DETALLE){
								
				ModelLocator.getInstance().liqClienteModel.ctaCteClienteSet.removeAll();
				ModelLocator.getInstance().liqClienteModel.arrayCtaCte.removeAll();				
				new ClientesDelegate(this).buscarDetalleLiquidaciones((event as ClientesEvent).filtro);
				
			}
		}
		
		public function result(data:Object):void
		{			
			if(evt.type == ClientesEvent.BUSCAR_CLIENTES || evt.type == ClientesEvent.BUSCAR_CLIENTES_CUENTA){				
								
				ModelLocator.getInstance().clientesEncontradosView.arrayClientes.source = (ResultEvent(data).result as Paginador).result;				
				
				if(ModelLocator.getInstance().clientesEncontradosView.arrayClientes.length == 1){
					this.seleccionarCliente(ModelLocator.getInstance().clientesEncontradosView.arrayClientes.getItemAt(0) as ClienteTransaccion);
				} else {
					ClientesEvent(evt).paginador = (ResultEvent(data).result as Paginador);
				
					if(ModelLocator.getInstance().clientesEncontradosView.popUpVisible == false){
						
						PopUpManager.addPopUp(ModelLocator.getInstance().clientesEncontradosView,Application.application.contGlobal,true);
						PopUpManager.centerPopUp(ModelLocator.getInstance().clientesEncontradosView);
						ModelLocator.getInstance().clientesEncontradosView.adg.setFocus();
						ModelLocator.getInstance().clientesEncontradosView.popUpVisible = true;
						
					}
					
					ModelLocator.getInstance().clientesEncontradosView.paginador.paginacionCompleta(ClientesEvent(evt).paginador);					
				}
								
				ControlBlock.getInstance().remove();
				
			} else if(evt.type == ClientesEvent.BUSCAR_LIQUIDACIONES){												
				//item cta cte liquidados
				ModelLocator.getInstance().liqClienteModel.arrayLiqCliente = new ArrayCollection(ResultEvent(data).result as Array);					
				ModelLocator.getInstance().liqClienteModel.calcularSaldo();
				ModelLocator.getInstance().pagoModel.arrayLiqPagar.removeAll();				
				ControlBlock.getInstance().remove();
																								
			} else if(evt.type == ClientesEvent.BUSCAR_LIQUIDACIONES_DETALLE){				
				ModelLocator.getInstance().liqClienteModel.liqCliente.ctaCteClienteSet = ResultEvent(data).result as Array;
				ModelLocator.getInstance().liqClienteModel.ctaCteClienteSet.source = ModelLocator.getInstance().liqClienteModel.liqCliente.ctaCteClienteSet; 
				ModelLocator.getInstance().liqClienteModel.armarCtaCte();
				//PopUpManager.addPopUp(ModelLocator.getInstance().liqClienteDetView,Application.application.contGlobal,true);
				//PopUpManager.centerPopUp(ModelLocator.getInstance().liqClienteDetView);
				//ModelLocator.getInstance().liqClienteDetView.adg1.setFocus();				
				ControlBlock.getInstance().remove();				
			}
			
			
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();			
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
		private function seleccionarCliente(cliente:ClienteTransaccion):void{
				
				
				
				ModelLocator.getInstance().clienteSeleccionado.clienteAdicional = null;
				var evt:ClientesEvent;
				if(cliente && cliente.adicional == 1){
					ModelLocator.getInstance().clienteSeleccionado.clienteAdicional = cliente;
					ModelLocator.getInstance().clienteSeleccionado.clienteSelect = "Adicional: " +
					ModelLocator.getInstance().clienteSeleccionado.clienteAdicional.individuo.apellido + ", " + ModelLocator.getInstance().clienteSeleccionado.clienteAdicional.individuo.nombres + " - ";
					evt = new ClientesEvent(ClientesEvent.BUSCAR_CLIENTE_TITULAR,null,null);
					evt.idCliente = cliente.idTitular;
					evt.dispatch();
					
				} else if(cliente){
					ModelLocator.getInstance().clienteSeleccionado.cliente = cliente;
					ModelLocator.getInstance().clienteSeleccionado.clienteSelect = "Titular: " +
					ModelLocator.getInstance().clienteSeleccionado.cliente.individuo.apellido + ", " + ModelLocator.getInstance().clienteSeleccionado.cliente.individuo.nombres;
					
					evt = new ClientesEvent(ClientesEvent.BUSCAR_CLIENTE_TITULAR,null,null);
					evt.idCliente = cliente.idCliente;
					evt.dispatch();
					
					ModelLocator.getInstance().clienteSeleccionado.labelButtonBusqueda = "Buscar";					
				}
				
			}
				
	}
}