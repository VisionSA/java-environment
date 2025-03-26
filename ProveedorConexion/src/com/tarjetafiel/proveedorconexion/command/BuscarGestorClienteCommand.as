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
	import com.tarjetafiel.caja.vo.GestorCuentaNew;
	import com.tarjetafiel.proveedorconexion.event.ClientesGeneralEvent;
	
	import mx.core.Application;

	public class BuscarGestorClienteCommand implements ICommand, IResponder {
		
		
		[Bindable]
		private var modelo : ModelLocatorGeneral = ModelLocatorGeneral.getInstance();
		
		
		
		public function execute(event:CairngormEvent):void {
			
			var arr:Array = new Array();
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("lineaCredito");
			var pendingCall:AsyncToken = ro.getGestorCuenta(modelo.clienteSeleccionado.titular.idCliente);
			pendingCall.addResponder(this);
			ControlBlock.getInstance().add();
		}
		
		public function result(data:Object):void {
			
			modelo.gestorCuentaNew = ((GestorCuentaNew)(ResultEvent(data).result));
			if (modelo.gestorCuentaNew.idCliente) {
				new ClientesGeneralEvent(ClientesGeneralEvent.DETALLE_PAGO_GESTION_EVENT,null,null).dispatch();
				
			} else {
				modelo.listaPagoGestion.removeAll();
			}
			
			
			//dispatcher.dispatchEvent(new Event("getValoresLineaCredito1"));				
			
			
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