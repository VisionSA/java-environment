package com.tarjetafiel.proveedorconexion.command {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.proveedorconexion.model.ModelLocatorGeneral;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	import mx.utils.ArrayUtil;
	import mx.utils.ObjectUtil;
	import mx.controls.Alert;
	
	import com.tarjetafiel.proveedorconexion.event.ClientesGeneralEvent;

	public class BuscarRevisionLineaCommand implements ICommand, IResponder {
		
		
		[Bindable]
		private var modelo : ModelLocatorGeneral = ModelLocatorGeneral.getInstance();
		
		
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("lineaCredito");
			var pendingCall:AsyncToken = ro.getUltimaRevisionLineaCredito(modelo.clienteSeleccionado.titular.idCliente);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			
			
			
			modelo.listaRevisionLinea = new ArrayCollection(ArrayUtil.toArray(ResultEvent(data).result));
			//Alert.show("fecha "+ modelo.listaRevisionLinea.getItemAt(1));
			
			if 	(modelo.clienteSeleccionado.titular.estadoCliente.idEstadoCliente != 4 &&
				modelo.clienteSeleccionado.titular.estadoCobranza.idEstadoCobranza != 10 &&
				(modelo.clienteSeleccionado.titular.domicilioValido == 0 ||
				modelo.clienteSeleccionado.titular.individuo.actividad.sucEmpresa == null ||
				modelo.clienteSeleccionado.titular.individuo.telefonos.length == 0 ||
				modelo.clienteSeleccionado.titular.individuo.mails.length == 0
				|| 	modelo.clienteSeleccionado.titular.individuo.actividad.sucEmpresa.sucTelefonos.length == 0 
				|| (modelo.listaRevisionLinea.getItemAt(0) < modelo.listaRevisionLinea.getItemAt(1) &&
		 modelo.clienteSeleccionado.titular.habilitadoConsumo == "H")) 
			)
			{
				/*var popUpInformacionCliente:InformacionCliente = new InformacionCliente();
				
				popUpInformacionCliente.generalClienteModel= this;
				popUpInformacionCliente.titular= _titular;
				PopUpManager.addPopUp(popUpInformacionCliente,Application.application as  DisplayObject, true);
				PopUpManager.centerPopUp(popUpInformacionCliente);	*/
				modelo.informacionCliente = "SI";
				modelo.revLineaCredito = "NO";
				modelo.revTelefonoLaboral = "NO";
				
				
				new ClientesGeneralEvent(ClientesGeneralEvent.GRABAR_CONTROLCLIENTE_EVENT,null,null).dispatch();
				ControlBlock.getInstance().remove();
				
				
				
				
				
			} else if (modelo.clienteSeleccionado.titular.estadoCobranza.idEstadoCobranza == 10) {
				modelo.informacionCliente = "SI";
				ControlBlock.getInstance().remove();
				
			} else {
				
				modelo.informacionCliente = "NO";
				ControlBlock.getInstance().remove();
			}
			
			
			
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);				
			ControlBlock.getInstance().remove();
		}
		
	}
}