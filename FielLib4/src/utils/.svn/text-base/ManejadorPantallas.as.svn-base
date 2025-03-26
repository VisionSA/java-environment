package utils
{
	import components.BlockBar;
	
	import flash.display.DisplayObject;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.core.FlexGlobals;
	import mx.core.IFlexDisplayObject;
	import mx.managers.CursorManager;
	import mx.managers.PopUpManager;

	public class ManejadorPantallas
	{
		
		private static var block:BlockBar=null;
		private static var operacionesEnCola:int=0;
		
/*@I3820*/		private static var arrPopUpMostrados:ArrayCollection = new ArrayCollection();
		
		public function ManejadorPantallas()
		{
			super()
		}
		
		public static function getAplicacion():Object
		{
			return FlexGlobals.topLevelApplication;
		}
		
		/**
		 * Abre un popup
		 * **/
		public static function crearPopUp(padre:DisplayObject, popUp:Class, modal:Boolean = true):IFlexDisplayObject
		{
			var popUpCreado:IFlexDisplayObject = PopUpManager.createPopUp(padre, popUp, modal);
			centrarPopUp(popUpCreado);
			arrPopUpMostrados.addItem(popUpCreado);
			return popUpCreado;
		}
		
		/**
		 * Cierra un determinado popup
		 * */
		public static function cerrarPop(popUp:IFlexDisplayObject):void
		{
			PopUpManager.removePopUp(popUp);
/*@I3820*/			if (arrPopUpMostrados.getItemIndex(popUp)>-1)
			{
				arrPopUpMostrados.removeItemAt(arrPopUpMostrados.getItemIndex(popUp));
			}
			popUp = null;
		}
/*@F3820*/		
		/**
		 * Centra el popup
		 * **/
		public static function centrarPopUp(popUp:IFlexDisplayObject):void
		{
			PopUpManager.centerPopUp(popUp);
		}
		
		/**
		 * @id 3820
		 * Verifica si un determinado popup ya se está mostrando
		 * **/
		public static function popUpEstaVisible(popUp:IFlexDisplayObject):Boolean
		{
			if (arrPopUpMostrados.getItemIndex(popUp)==-1)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		
		public static function mostrarProgressBar():void 
		{			
			if (block == null)
			{
				CursorManager.getInstance().setBusyCursor();
				block = PopUpManager.createPopUp(getAplicacion() as DisplayObject, BlockBar, true) as BlockBar
				PopUpManager.centerPopUp(block);
			}
			else
			{
				operacionesEnCola += 1;
			}
		}
		
		public static function cerrarProgressBar():void 
		{
			CursorManager.getInstance().removeAllCursors()
			if (block != null)
			{
				if (operacionesEnCola == 0)
				{
					PopUpManager.removePopUp(block);
					block = null;
				}
				else
				{
					operacionesEnCola -= 1;
				}
			}
		}
	}
}