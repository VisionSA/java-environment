package com.bizit.consulta.web.controller {
	import com.adobe.cairngorm.control.FrontController;
	import com.bizit.consulta.web.command.comercio.BuscarCertRetComercioCommand;
	import com.bizit.consulta.web.command.comercio.BuscarLiqComercioCommand;
	import com.bizit.consulta.web.command.comercio.GuardarArchivoPDFCommand;
	import com.bizit.consulta.web.command.comercio.GuardarArchivoTxtCommand;
	import com.bizit.consulta.web.command.loginUsuario.LogOutCommand;
	import com.bizit.consulta.web.command.loginUsuario.LoginUsuarioGetPermisosCommand;
	import com.bizit.consulta.web.command.loginUsuario.LoginUsuarioLoginCommand;
	import com.bizit.consulta.web.command.loginUsuario.LoginUsuarioRecuperarPasswordCommand;
	import com.bizit.consulta.web.command.loginUsuario.LoginUsuarioRegenerarPasswordCommand;
	import com.bizit.consulta.web.command.loginUsuario.LoginUsuarioRegistrarUsuarioCommand;
	import com.bizit.consulta.web.command.loginUsuario.LoginUsuarioValidarDatosCommand;
	import com.bizit.consulta.web.command.permisoWeb.PermisoWebListAllCommand;
	import com.bizit.consulta.web.command.util.SetModelDataCommand;
	import com.bizit.consulta.web.event.ComercioEvent;
	import com.bizit.consulta.web.event.LoginUsuarioEvent;
	import com.bizit.consulta.web.event.PermisoWebEvent;
	import com.bizit.consulta.web.event.SetModelDataEvent;
	
	public class FielWebController extends FrontController {
		
		
		public function FielWebController() {
			this.inicializar();
			
		}
		
		private function inicializar():void{
			addCommand(PermisoWebEvent.LIST_ALL,PermisoWebListAllCommand);
			
			/* ****
			/* Eventos de Login
			/* **** */
			addCommand(LoginUsuarioEvent.LOGIN, LoginUsuarioLoginCommand);
			addCommand(LoginUsuarioEvent.VALIDAR, LoginUsuarioValidarDatosCommand);
			addCommand(LoginUsuarioEvent.REGISTRAR, LoginUsuarioRegistrarUsuarioCommand);
			addCommand(LoginUsuarioEvent.GET_PERMISOS, LoginUsuarioGetPermisosCommand);
			addCommand(LoginUsuarioEvent.RECUPERAR_PASSWORD, LoginUsuarioRecuperarPasswordCommand);
			addCommand(LoginUsuarioEvent.REGENERAR_PASSWORD, LoginUsuarioRegenerarPasswordCommand);			
			addCommand(LoginUsuarioEvent.LOG_OUT_EVENT,LogOutCommand);
			
			/* ****
			/* Evento para setear datos gen�ricos al modelo
			/* **** */
			addCommand(SetModelDataEvent.SET_DATA, SetModelDataCommand);
			
			/* ****
			/* Evento Comercio
			/* **** */
			addCommand(ComercioEvent.BUSCAR_LIQ_COMERCIO_EVENT,BuscarLiqComercioCommand);
			addCommand(ComercioEvent.BUSCAR_CERT_RET_COMERCIO_EVENT, BuscarCertRetComercioCommand);
			addCommand(ComercioEvent.GUARDAR_ARCHIVO_PDF_COMERCIO, GuardarArchivoPDFCommand);
			addCommand(ComercioEvent.GUARDAR_ARCHIVO_TXT_LIQUIDACION, GuardarArchivoTxtCommand);
		}
		
	}
}