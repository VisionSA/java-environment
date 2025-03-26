package com.util.error
{
	import flash.display.DisplayObject;
	
	import mx.core.Application;
	import mx.managers.PopUpManager;
	
	public class ManagerErrors
	{
		private static var _managerErrors:ManagerErrors;			
		
		private var popup:PopUpError = new PopUpError();
		
		public static function getInstance():ManagerErrors{
			if(_managerErrors == null)
				_managerErrors = new ManagerErrors();
			
			return _managerErrors;
		}
		
		public function addPopUpError(tit:String, detail:String):void{		
			PopUpManager.removePopUp(popup);
			popup.detail = detail;
			popup.tit = tit;
			PopUpManager.addPopUp(popup,Application.application as DisplayObject,true);
			PopUpManager.centerPopUp(popup);	
			popup.txtTitle.setFocus();					
		}

	}
}