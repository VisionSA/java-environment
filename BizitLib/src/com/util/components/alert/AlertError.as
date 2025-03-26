package com.util.components.alert
{
	import com.util.Imagenes;
	
	import mx.controls.Alert;
	
	public class AlertError extends Alert
	{
		public function AlertError()
		{
			super();
		}

	
		public static function show(text:String = "",
									closeHandler:Function = null,
									title:String = "Error",defaultButton:uint=Alert.OK):Alert
    	{
    		Alert.buttonWidth = 80;
    		Alert.okLabel = "Aceptar";    		    		
    		return Alert.show(text,title,Alert.OK,null,closeHandler,Imagenes.errorImg,defaultButton);
    	}
		
	}
}