package com.tarjetafiel.proveedorconexion.command
{
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
	
	public class BuscarFecha1Documentos implements ICommand, IResponder
	{
		[Bindable]
		private var modelo : ModelLocatorGeneral = ModelLocatorGeneral.getInstance();
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("lineaCredito");
			var pendingCall:AsyncToken = ro.getFecha1Documentos();
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			//Defino fecha a partir de la cual se buscan documentos en servidor, 
			//a partir del parametro tomado del sistema en forma de String
			var year:Number = parseInt(data.result.substr(6,4));  // 4 caracteres desde la pos 6
			var month:Number = parseInt(data.result.substr(3,2)); // 2 caracteres desde pos 3
			var day:Number = parseInt(data.result.substr(0,2));   // 2 caracteres desde pos 0
			modelo.fecha1 = new Date(year,month-1,day,0,0,0,0);
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);				
			ControlBlock.getInstance().remove();
		}
		
	}
}