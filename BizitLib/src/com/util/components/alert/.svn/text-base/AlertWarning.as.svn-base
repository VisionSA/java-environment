package com.util.components.alert
{
	import com.util.Imagenes;
	
	import mx.controls.Alert;
	import mx.styles.StyleManager;
	import mx.styles.CSSStyleDeclaration;

	public class AlertWarning extends Alert
	{
		public  var alertCSS:CSSStyleDeclaration;
		
		public function AlertWarning()
		{
				
			super();
			
		}
		
		
		
		public static function show(text:String = "",
									closeHandler:Function = null,
									title:String = "Mensaje", defaultButton:uint=Alert.OK):Alert
    	{    		
    		Alert.buttonWidth = 80;
    		Alert.okLabel = "Aceptar";
			
    		return Alert.show(text,title,Alert.OK,null,closeHandler,Imagenes.warningImg,defaultButton);
    	}
		
		public function show(text:String = "",
									closeHandler:Function = null,
									title:String = "Mensaje", defaultButton:uint=Alert.OK):Alert
		{    		
			Alert.buttonWidth = 80;
			Alert.okLabel = "Aceptar";
			alertCSS = StyleManager.getStyleDeclaration("mx.controls.Alert");
			/*alertCSS.setStyle("backgroundColor", "red");
			alertCSS.setStyle("fontFamily", "Arial");*/
			
			alertCSS.setStyle("backgroundColor", "red")			
			alertCSS.setStyle('fontFamily', "Arial");
			alertCSS.setStyle('fontSize', 16);
			alertCSS.setStyle('fontWeight', "bold");
			alertCSS.setStyle('fontColor', "white");
			alertCSS.setStyle('color', "white");
			alertCSS.setStyle("fillColors", [ 0xffffff, 0xF5A2A2, 0xF5A2A2, 0xffffff ]);
			
			
			
			
			/*alertCSS.setStyle('themeColor', 0xff0000);*/
			/*fontFamily: Arial;
			fontSize: 16;
			fontWeight: bold;*/
						return Alert.show(text,title,Alert.OK,null,closeHandler,Imagenes.warningImg,defaultButton);
		}
		
		public function setElementos():void {
			alertCSS = StyleManager.getStyleDeclaration("mx.controls.Alert");
		}
		
		
		
		
		
	}
}