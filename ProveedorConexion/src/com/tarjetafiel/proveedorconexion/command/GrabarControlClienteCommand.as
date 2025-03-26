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
	
	import mx.core.Application;

	public class GrabarControlClienteCommand implements ICommand, IResponder {
		
		
		[Bindable]
		private var modelo : ModelLocatorGeneral = ModelLocatorGeneral.getInstance();
		
		
		
		public function execute(event:CairngormEvent):void {
			
			var arr:Array = new Array();
			
			
			if (modelo.clienteSeleccionado.titular.domicilioValido == 0) {
				
				arr.push(1);
				
			}
			
			if (modelo.clienteSeleccionado.titular.individuo.actividad.sucEmpresa == null) {
				
				arr.push(2);
				
			}
			
			if (modelo.clienteSeleccionado.titular.individuo.telefonos.length == 0) {
				
				arr.push(3);
				
			}
			/*if (modelo.clienteSeleccionado.titular.individuo.mails.length == 0) {
				
				arr.push(4);
				
			}*/
			
			if (modelo.clienteSeleccionado.titular.individuo.actividad.sucEmpresa == null ||
				modelo.clienteSeleccionado.titular.individuo.actividad.sucEmpresa.sucTelefonos.length == 0) {
				
					if (modelo.clienteSeleccionado.titular.individuo.actividad.sucEmpresa == null) {
						arr.push(5);
						modelo.revTelefonoLaboral = "SI";
					} else if (modelo.clienteSeleccionado.titular.individuo.actividad.sucEmpresa.empresa.cuit != 
						Number(modelo.clienteSeleccionado.titular.individuo.cuil)) {
						arr.push(5);
						modelo.revTelefonoLaboral = "SI";
					} else {
						
					}
				
			}
			
			if ((modelo.listaRevisionLinea.getItemAt(0) < modelo.listaRevisionLinea.getItemAt(1) &&
				modelo.clienteSeleccionado.titular.habilitadoConsumo == "H") ) {
				arr.push(6);
				modelo.revLineaCredito = "SI";
			}
			
			
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("lineaCredito");
			var pendingCall:AsyncToken = ro.grabarControlClientes(modelo.clienteSeleccionado.titular.idCliente,
				Application.application.parameters.codigoOperador,
				arr);
			pendingCall.addResponder(this);
			ControlBlock.getInstance().add();
			
			
			
		}
		
		public function result(data:Object):void {
			
			
			
			//modelo.listaRevisionLinea = new ArrayCollection(ArrayUtil.toArray(ResultEvent(data).result));
			//Alert.show("fecha "+ modelo.listaRevisionLinea.getItemAt(1));
			
			
			ControlBlock.getInstance().remove();
				
			
			
			
			
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);				
			ControlBlock.getInstance().remove();
		}
		
	}
}