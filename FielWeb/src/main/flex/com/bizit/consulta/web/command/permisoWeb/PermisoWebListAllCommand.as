package com.bizit.consulta.web.command.permisoWeb
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.bizit.consulta.web.model.FielWebModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	public class PermisoWebListAllCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("permisoWebService");
			var pendingCall:AsyncToken = ro.list();
			pendingCall.addResponder(this);	
		}
		
		public function result(data:Object):void {
			modelo.listaPermisoUsuarios = ResultEvent(data).result as ArrayCollection;			
		}
		
		public function fault(info:Object):void {
			Alert.show(FaultEvent(info).fault.faultString);				
		}
	}
}