package com.bizit.consulta.web.command.loginUsuario
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.bizit.consulta.web.entity.UsuarioWeb;
	import com.bizit.consulta.web.model.FielWebModelLocator;
	import com.bizit.consulta.web.utils.ConstantesEstados;
	import com.bizit.consulta.web.utils.ManejadorMensajes;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	public class LoginUsuarioRegistrarUsuarioCommand implements IResponder, ICommand
	{
		[Bindable]
		private var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			var usuario:UsuarioWeb = event.data as UsuarioWeb;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("usuarioWebService");
			var pendingCall:AsyncToken = ro.registrarUsuarioComercio(usuario);
			pendingCall.addResponder(this);	
		}
		
		public function result(data:Object):void
		{
			ManejadorMensajes.mostrarMensajeNotificacionMail("El usuario se ha registrado correctamente. " +
				"Se ha enviado un mail con los datos de su cuenta");
			modelo.estadoLoginUsuario = ConstantesEstados.LOGIN_STATE;
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info), "Error en registro de usuario");
		}
	}
}