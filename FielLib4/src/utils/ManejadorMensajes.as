package utils
{
	import events.CloseCustomMessageEvent;
	
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.ui.Keyboard;
	
	import mx.controls.Alert;
	import mx.controls.Button;
	import mx.core.IFlexDisplayObject;
	import mx.events.CloseEvent;
	import mx.events.FlexEvent;
	import mx.managers.FocusManager;
	import mx.managers.IFocusManagerComponent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.FaultEvent;

/*@I3820*/
	public class ManejadorMensajes extends EventDispatcher
	{
		
		public static const staticDispatcher:EventDispatcher = new EventDispatcher();
		
		public static var alert:Alert = null;
		
		public function ManejadorMensajes()
		{
			super();
		}

		public static function mostrarMensajeErrorFaultEvent(event:FaultEvent, inicioMensaje:String, closeFunction:Function=null):void
		{
			setAlertButtons();
			closeFunction = closeFunction == null ? onErrorMessageClose : closeFunction;
			try
			{
				var mensaje:String = event.fault.rootCause.message !=null?event.fault.rootCause.message:event.fault.faultString;
				alert = Alert.show(inicioMensaje + ": " + mensaje,"Fiel",Alert.OK,ManejadorPantallas.getAplicacion() as Sprite,closeFunction,
					ManejadorIconos.ERROR,Alert.OK);
				//alert.addEventListener(KeyboardEvent.KEY_DOWN, onKeyPress);
				//alert.addEventListener(FlexEvent.CREATION_COMPLETE, creationComplete);
			}
			catch (e:Error)
			{
				alert = Alert.show("Error de conexión con el servidor de datos. " +
					"Contactese con el administrador del sistema","Fiel",Alert.OK,ManejadorPantallas.getAplicacion() as Sprite,
					closeFunction,ManejadorIconos.ERROR,Alert.OK);
				//alert.addEventListener(KeyboardEvent.KEY_DOWN, onKeyPress);
				//alert.addEventListener(FlexEvent.CREATION_COMPLETE, creationComplete);
			}
		}
		
		public static function mostrarMensajeError(mensaje:String, titulo:String = "Fiel", closeFunction:Function=null):void
		{
			setAlertButtons();
			closeFunction = closeFunction == null ? onErrorMessageClose : closeFunction;
			alert = Alert.show(mensaje, titulo, Alert.OK, ManejadorPantallas.getAplicacion() as Sprite, closeFunction, ManejadorIconos.ERROR,Alert.OK);
			//alert.addEventListener(KeyboardEvent.KEY_DOWN, onKeyPress);
			//alert.addEventListener(FlexEvent.CREATION_COMPLETE, creationComplete);
		}
		
		public static function mostrarMensajeInformacion(mensaje:String, titulo:String="Fiel", closeFunction:Function=null):void
		{
			setAlertButtons();
			closeFunction = closeFunction == null ? onInfoMessageClose : closeFunction;
			alert = Alert.show(mensaje, titulo, Alert.OK, ManejadorPantallas.getAplicacion() as Sprite,closeFunction, ManejadorIconos.INFO,Alert.OK);
			//alert.addEventListener(KeyboardEvent.KEY_DOWN, onKeyPress);
			//alert.addEventListener(FlexEvent.CREATION_COMPLETE, creationComplete);
		}
		
		public static function mostrarMensajeNotificacionMail(mensaje:String, titulo:String="Fiel", closeFunction:Function=null):void
		{
			setAlertButtons();
			closeFunction = closeFunction == null ? onNotifyMessageClose : closeFunction;
			alert = Alert.show(mensaje, titulo, Alert.OK, ManejadorPantallas.getAplicacion() as Sprite, closeFunction, ManejadorIconos.MAIL,Alert.OK);
			//alert.addEventListener(KeyboardEvent.KEY_DOWN, onKeyPress);
			//alert.addEventListener(FlexEvent.CREATION_COMPLETE, creationComplete);
		}
		
		public static function mostrarMensajeNotificacion(mensaje:String, titulo:String="Fiel",closeFunction:Function=null):void
		{
			setAlertButtons();
			alert = Alert.show(mensaje, titulo, Alert.OK, ManejadorPantallas.getAplicacion() as Sprite, onNotifyMessageClose, ManejadorIconos.WARNING,Alert.OK);
			//alert.addEventListener(KeyboardEvent.KEY_UP, onKeyPress);
			//alert.addEventListener(FlexEvent.CREATION_COMPLETE, creationComplete);
		}
		
		/**
		 * Funciones que dispran eventos al cerrarse los popups de mensajes
		 * */
		
		private static function onErrorMessageClose(event:Event):void
		{
			event.stopPropagation();
			dispararEvento(CloseCustomMessageEvent.CLOSE_FAULT_MESSAGE);
		}
		
		private static function onInfoMessageClose(event:Event):void
		{
			event.stopPropagation();
			dispararEvento(CloseCustomMessageEvent.CLOSE_INFO_MESSAGE);
		}
		
		private static function onNotifyMessageClose(event:Event):void
		{
			event.stopPropagation();
			dispararEvento(CloseCustomMessageEvent.CLOSE_NOTIFY_MESSAGE);
		}
		
		private static function dispararEvento(type:String):void
		{
			staticDispatcher.dispatchEvent(new CloseCustomMessageEvent(type));
		}
		/**************************************************************/
		
		/*private static function onKeyPress(event:KeyboardEvent):void
		{
			if (event.keyCode == Keyboard.ENTER ||
				event.keyCode == Keyboard.ESCAPE)
			{
				Button(Alert(event.currentTarget).defaultButton).dispatchEvent(new MouseEvent(MouseEvent.CLICK));
			}
			event.stopPropagation();
		}
		
		private static function creationComplete(event:FlexEvent):void
		{
			Button(Alert(event.currentTarget).defaultButton).setFocus();
		}*/
		
		public static function mostrarMensajeOkCancel(mensaje:String, listener:Function, titulo:String = "Fiel"):void
		{
			setAlertButtons();
			alert = Alert.show(mensaje,titulo,Alert.OK | Alert.CANCEL, ManejadorPantallas.getAplicacion() as Sprite, listener, ManejadorIconos.QUESTION, Alert.OK);
			//alert.addEventListener(KeyboardEvent.KEY_UP, onKeyPress);
			//alert.addEventListener(FlexEvent.CREATION_COMPLETE, creationComplete);
		}
		
		public static function mostrarMensajeYesNo(mensaje:String, listener:Function, titulo:String = "Fiel"):void
		{
			setAlertButtons();
			alert = Alert.show(mensaje,titulo,Alert.YES | Alert.NO, ManejadorPantallas.getAplicacion() as Sprite, listener, ManejadorIconos.QUESTION, Alert.YES);
			//alert.addEventListener(KeyboardEvent.KEY_UP, onKeyPress);
			//alert.addEventListener(FlexEvent.CREATION_COMPLETE, creationComplete);
		}
		
		
		private static function setAlertButtons():void
		{
			Alert.noLabel = "No";
			Alert.yesLabel = "Si";
			Alert.okLabel = "Aceptar";
			Alert.cancelLabel = "Cancelar";
		}
	}
/*@I3820*/
}