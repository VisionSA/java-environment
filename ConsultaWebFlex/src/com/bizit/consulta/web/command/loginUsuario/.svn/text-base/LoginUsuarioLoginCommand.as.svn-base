package com.bizit.consulta.web.command.loginUsuario
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.bizit.consulta.web.entity.UsuarioWeb;
	import com.bizit.consulta.web.event.LoginUsuarioEvent;
	import com.bizit.consulta.web.model.FielWebModelLocator;
	import com.bizit.consulta.web.utils.ManejadorMensajes;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	public class LoginUsuarioLoginCommand implements ICommand, IResponder
	{
		[Bindable]
		private var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			var usuario:UsuarioWeb = event.data as UsuarioWeb;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("usuarioWebService");
			var pendingCall:AsyncToken = ro.logIn(usuario);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void
		{
			modelo.usuarioWeb = ResultEvent(data).result as UsuarioWeb;
			new LoginUsuarioEvent(LoginUsuarioEvent.GET_PERMISOS, modelo.usuarioWeb).dispatch();
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info), "Error de Login");
		}
	}
}