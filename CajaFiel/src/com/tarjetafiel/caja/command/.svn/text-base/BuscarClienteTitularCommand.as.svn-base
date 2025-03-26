package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.clientes.ClientesDelegate;
	import com.tarjetafiel.caja.event.AdelantoEfectivoEvent;
	import com.tarjetafiel.caja.event.ClientesEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertWarning;
	import com.util.error.ManagerErrors;
	
	import mx.core.Application;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;

	public class BuscarClienteTitularCommand implements ICommand, IResponder
	{
		public function BuscarClienteTitularCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();		
			new ClientesDelegate(this).buscarClienteTitular(ClientesEvent(event).idCliente);
		}
		
		public function result(data:Object):void
		{
			try{						
				
				if(ModelLocator.getInstance().clienteSeleccionado.cliente == null){
					ModelLocator.getInstance().clienteSeleccionado.cliente = data.result as ClienteTransaccion;
					ModelLocator.getInstance().clienteSeleccionado.clienteSelect += "Titular: " +
					ModelLocator.getInstance().clienteSeleccionado.cliente.idCliente + " - " +
					ModelLocator.getInstance().clienteSeleccionado.cliente.individuo.apellido + ", " + 
					ModelLocator.getInstance().clienteSeleccionado.cliente.individuo.nombres;
				} else {
					ModelLocator.getInstance().clienteSeleccionado.cliente = data.result as ClienteTransaccion;	
				}
				
				if(ModelLocator.getInstance().clienteSeleccionado.clienteAdicional){
					if(ModelLocator.getInstance().clienteSeleccionado.clienteAdicional.platicoClienteHabilitado.estadoPlastico.idPlasticoEstado != 1){
						AlertWarning.show("El plastico no esta habilitado");
					}
				} else {
					if(ModelLocator.getInstance().clienteSeleccionado.cliente.platicoClienteHabilitado && ModelLocator.getInstance().clienteSeleccionado.cliente.platicoClienteHabilitado.estadoPlastico.idPlasticoEstado != 1){
						AlertWarning.show("El plastico no esta habilitado");
					}
				}										
				
				ModelLocator.getInstance().liqClienteModel.buscarLiquidaciones();							
				Application.application.setFocus();
				ModelLocator.getInstance().busquedaClientesNoVisible();
				ModelLocator.getInstance().clienteSeleccionado.labelButtonBusqueda = "Buscar";
				ModelLocator.getInstance().clientesEncontradosView.removePopUp();
								
				new AdelantoEfectivoEvent(AdelantoEfectivoEvent.BUSCAR_DISPONIBLE).dispatch();				
				
			} catch(err:Error){
				ManagerErrors.getInstance().addPopUpError("Error en los datos del titular",
				err.toString());
			}
			ControlBlock.getInstance().remove();			
		}
				
		public function fault(info:Object):void
		{
			AlertError.show(FaultEvent(info).fault.faultDetail);
			ControlBlock.getInstance().remove();
		}
		
	}
}