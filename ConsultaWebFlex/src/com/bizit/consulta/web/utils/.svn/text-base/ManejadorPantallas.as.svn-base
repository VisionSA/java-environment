package com.bizit.consulta.web.utils
{
	import com.bizit.consulta.web.model.FielWebModelLocator;
	import com.bizit.consulta.web.view.customComponent.BlockBar;
	import com.bizit.consulta.web.vo.SetDataVo;
	
	import flash.display.DisplayObject;
	
	import mx.core.FlexGlobals;
	import mx.core.IFlexDisplayObject;
	import mx.managers.CursorManager;
	import mx.managers.PopUpManager;

	public class ManejadorPantallas
	{
		
		public function ManejadorPantallas()
		{
			super()
		}
		
		public static function getAplicacion():Object
		{
			return FlexGlobals.topLevelApplication;
		}
		
		public static function crearPopUp(padre:DisplayObject, popUp:Class, modal:Boolean = true):IFlexDisplayObject
		{
			var popUpCreado:IFlexDisplayObject = PopUpManager.createPopUp(padre, popUp, modal);
			PopUpManager.centerPopUp(popUpCreado);
			return popUpCreado;
		}
		
		public static function cerrarPop(popUp:IFlexDisplayObject):void
		{
			PopUpManager.removePopUp(popUp);
		}
		
		public static function mostrarProgressBar():void 
		{			
			CursorManager.getInstance().setBusyCursor();
			var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
			var block:BlockBar = PopUpManager.createPopUp(getAplicacion() as DisplayObject, BlockBar, true) as BlockBar
			new SetDataVo("blockBar", block).guardarValor();
			PopUpManager.centerPopUp(modelo.blockBar);
		}
		
		public static function cerrarProgressBar():void 
		{
			CursorManager.getInstance().removeAllCursors()
			var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
			if (modelo.blockBar != null)
			{
				PopUpManager.removePopUp(modelo.blockBar);
				new SetDataVo("blockBar", null).guardarValor();	
			}
		}
	}
}