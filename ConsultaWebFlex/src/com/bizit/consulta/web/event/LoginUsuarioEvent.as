package com.bizit.consulta.web.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.bizit.consulta.web.entity.UsuarioWeb;
	
	public class LoginUsuarioEvent extends CairngormEvent
	{
		public static const LOGIN:String = "LoginEvent";		
		public static const VALIDAR:String = "ValidarDatosEvent";
		public static const REGISTRAR:String = "RegistrarUsuarioEvent";
		public static const RECUPERAR_PASSWORD:String = "RecuperarPasswordEvent";
		public static const REGENERAR_PASSWORD:String = "RegenerarPasswordEvent";
		public static const GET_PERMISOS:String = "GetPermisosEvent";
		public static const LOG_OUT_EVENT:String = "LogOutEvent";
		
		public function LoginUsuarioEvent(type:String, param : Object)
		{
			super(type, false, false);
			data = param;
		}
	}
}