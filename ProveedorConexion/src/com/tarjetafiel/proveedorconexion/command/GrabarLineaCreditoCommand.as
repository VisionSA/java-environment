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
	import com.tarjetafiel.proveedorconexion.event.ClientesGeneralEvent;

	public class GrabarLineaCreditoCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : ModelLocatorGeneral = ModelLocatorGeneral.getInstance();
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("lineaCredito");
			var pendingCall:AsyncToken = ro.aceptarLimiteTemporal(modelo.clienteSeleccionado.titular.idCliente,modelo.operador,modelo.monto,
			modelo.cantidadDias);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			//ControlBlock.getInstance().remove();
			new ClientesGeneralEvent(ClientesGeneralEvent.BUSCAR_HIST_LINEA_CREDITO_EVENT,null,null).dispatch();
			ControlBlock.getInstance().remove();
			
			
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);				
			ControlBlock.getInstance().remove();
		}
		
	}
}