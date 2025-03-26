package com.bizit.consulta.web.utils
{
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;

	public class ManejadorMensajes
	{
		public function ManejadorMensajes()
		{
			super();
		}
		
		public static function mostrarMensajeErrorFaultEvent(event:FaultEvent, inicioMensaje:String):void
		{
			setAlertButtons();
			try
			{
				var mensaje:String = event.fault.rootCause.message;
				Alert.show(inicioMensaje + ": " + mensaje,"Fiel Web",Alert.OK,null,null,ManejadorIconos.ERROR);
			}
			catch (e:Error)
			{
				Alert.show("Error de conexión con el servidor de datos. " +
					"Contactese con el administrador del sistema","Fiel Web",Alert.OK,null,null,ManejadorIconos.ERROR);
			}
			ManejadorPantallas.cerrarProgressBar();
		}
		
		public static function mostrarMensajeError(mensaje:String, titulo:String = "Fiel Web"):void
		{
			setAlertButtons();
			Alert.show(mensaje, titulo, Alert.OK, null,null, ManejadorIconos.ERROR);
			ManejadorPantallas.cerrarProgressBar();
		}
		
		public static function mostrarMensajeInformacion(mensaje:String, titulo:String="Fiel Web"):void
		{
			setAlertButtons();
			Alert.show(mensaje, titulo, Alert.OK, null,null, ManejadorIconos.INFO);
			ManejadorPantallas.cerrarProgressBar();
		}
		
		public static function mostrarMensajeNotificacionMail(mensaje:String, titulo:String="Fiel Web"):void
		{
			setAlertButtons();
			Alert.show(mensaje, titulo, Alert.OK, null, null, ManejadorIconos.MAIL);
			ManejadorPantallas.cerrarProgressBar();
		}
		
		public static function mostrarMensajeOkCancel(mensaje:String, listener:Function, titulo:String = "Fiel Web"):void
		{
			setAlertButtons();
			Alert.show(mensaje,titulo,Alert.OK | Alert.CANCEL, null, listener, ManejadorIconos.QUESTION, Alert.OK);
			ManejadorPantallas.cerrarProgressBar();
		}
		
		private static function setAlertButtons():void
		{
			Alert.noLabel = "No";
			Alert.yesLabel = "Si";
			Alert.okLabel = "Aceptar";
			Alert.cancelLabel = "Cancelar";
		}
	}
}