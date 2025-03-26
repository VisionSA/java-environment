package com.bizit.consulta.web.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	
	public class LoginChangeStateEvent extends CairngormEvent
	{
		public static const LOGIN_STATE:String = "LoginState";
		public static const REGISTER_STATE:String = "RegistrarState";
		public static const RECUPERAR_PASS_STATE:String = "RecuperarPassState";
		public static const REGENERAR_PASS_STATE:String = "RegenerarPassState";
		public static const VALIDAR_STATE:String = "ValidarState";
		
		public function LoginChangeStateEvent(type:String)
		{
			super(type, false, false);
		}
	}
}