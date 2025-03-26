package com.util.components.alert
{
	import com.util.Imagenes;
	
	import mx.controls.Alert;

	public class AlertYesNo extends Alert
	{
		public function AlertYesNo()
		{
			super();
		}
		
		public static function show(text:String = "",
									closeHandler:Function = null,
									title:String = "Mensaje",defaultButton:uint=Alert.YES):Alert
    	{
    		Alert.noLabel = "No";
    		Alert.yesLabel = "Si";
    		return Alert.show(text,title,Alert.YES | Alert.NO,null,closeHandler,Imagenes.questionImg,defaultButton);
    	}
		
	}
}