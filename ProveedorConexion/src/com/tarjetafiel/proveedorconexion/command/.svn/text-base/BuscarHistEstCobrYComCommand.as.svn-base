package com.tarjetafiel.proveedorconexion.command
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.proveedorconexion.model.ModelLocatorGeneral;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	import mx.utils.ArrayUtil;

	public class BuscarHistEstCobrYComCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : ModelLocatorGeneral = ModelLocatorGeneral.getInstance()
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("estadoCliente");
			var pendingCall:AsyncToken = ro.getAllByIdCliente(modelo.clienteSeleccionado.titular.idCliente);
			pendingCall.addResponder(this);			
		}
		
		public function result(data:Object):void {
			modelo.listaHistoricoEstado = new ArrayCollection(ArrayUtil.toArray(ResultEvent(data).result));
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);				
			ControlBlock.getInstance().remove();
		}
		
	}
}