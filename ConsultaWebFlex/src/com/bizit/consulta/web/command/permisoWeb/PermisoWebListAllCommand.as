package com.bizit.consulta.web.command.permisoWeb
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.bizit.consulta.web.entity.UsuarioWeb;
	import com.bizit.consulta.web.model.FielWebModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	import mx.utils.ObjectUtil;
	
	public class PermisoWebListAllCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			var usuario:UsuarioWeb = event.data as UsuarioWeb;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("emailService");
			var pendingCall:AsyncToken = ro.sendMessage("bizit.global.test@gmail.com", "test", "bizit.global.test@gmail.com", "Esto es una prueba")
			pendingCall.addResponder(this);	
		}
		
		public function result(data:Object):void {
			modelo.usuarioWeb = ResultEvent(data).result as UsuarioWeb;			
		}
		
		public function fault(info:Object):void {
			Alert.show(ObjectUtil.toString(info));				
		}
	}
}