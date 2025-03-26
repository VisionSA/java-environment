package com.bizit.consulta.web.command.loginUsuario
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.bizit.consulta.web.entity.UsuarioComercioVO;
	import com.bizit.consulta.web.model.FielWebModelLocator;
	import com.bizit.consulta.web.utils.ConstantesEstados;
	import com.bizit.consulta.web.utils.ManejadorMensajes;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	public class LoginUsuarioRegenerarPasswordCommand implements ICommand, IResponder
	{
		[Bindable]
		private var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			var param:Object = event.data;
			var mail:String = param.mail;
			var usuarioC:UsuarioComercioVO = param.usuarioComercio as UsuarioComercioVO;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("usuarioWebService");
			var pendingCall:AsyncToken = ro.recuperarPassword(usuarioC, mail);
			pendingCall.addResponder(this);	
		}
		
		public function result(data:Object):void
		{
			if (ResultEvent(data).result == true)
			{
				ManejadorMensajes.mostrarMensajeNotificacionMail("Los datos de la cuenta se han enviado por mail a su " +
					"cuenta de correo");
			}
			else
			{
				ManejadorMensajes.mostrarMensajeError("Se ha producido un error en la regeneración de la contraseña. " +
					"Intente nuevamente");
			}
			modelo.estadoLoginUsuario = ConstantesEstados.LOGIN_STATE;
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info),"Error al regenerar contraseña");
		}
	}
}