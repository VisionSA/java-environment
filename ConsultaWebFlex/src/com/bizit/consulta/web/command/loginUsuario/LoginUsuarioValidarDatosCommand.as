package com.bizit.consulta.web.command.loginUsuario
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.bizit.consulta.web.entity.UsuarioComercioVO;
	import com.bizit.consulta.web.entity.UsuarioComercioWeb;
	import com.bizit.consulta.web.model.FielWebModelLocator;
	import com.bizit.consulta.web.utils.ConstantesEstados;
	import com.bizit.consulta.web.utils.ManejadorMensajes;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	public class LoginUsuarioValidarDatosCommand implements ICommand, IResponder
	{
		[Bindable]
		private var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			var usuario:UsuarioComercioVO = event.data as UsuarioComercioVO;
			modelo.usuarioComVO = usuario;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("usuarioWebService");
			var pendingCall:AsyncToken = ro.validarUsuarioComercio(usuario);
			pendingCall.addResponder(this);	
		}
		
		public function result(data:Object):void
		{
			modelo.usuarioComercio = ResultEvent(data).result as UsuarioComercioWeb;
			modelo.usuarioComercio.id = -1;
			modelo.estadoLoginUsuario = ConstantesEstados.REGISTER_STATE;
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info), "Error en validación de datos");
		}
	}
}