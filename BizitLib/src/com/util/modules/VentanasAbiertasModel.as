package com.util.modules
{
	import flash.display.DisplayObject;
	
	import flexmdi.containers.MDICanvas;
	import flexmdi.containers.MDIWindow;
	
	import mx.containers.TitleWindow;
	import mx.core.Application;
	import mx.core.IContainer;
	import mx.managers.PopUpManager;
	
	public class VentanasAbiertasModel
	{
		
		public var array:Object = new Object();			
		
		public function VentanasAbiertasModel()
		{
		}
		
		public function showVentana(idModulo:String, contenedor:IContainer):void{
			
			if(this.array[idModulo] is MDIWindow){
				if(MDICanvas(contenedor).windowManager.windowList.indexOf(MDIWindow(this.array[idModulo]))){
															
					MDIWindow(this.array[idModulo]).percentHeight = 100;
					MDIWindow(this.array[idModulo]).percentWidth = 100;
					MDICanvas(contenedor).windowManager.addCenter(MDIWindow(this.array[idModulo]));
					MDIWindow(this.array[idModulo]).x = 0;
					MDIWindow(this.array[idModulo]).y = 0;		
					MDIWindow(this.array[idModulo]).setFocus();							
					
				} else {
					
					MDIWindow(this.array[idModulo]).maximize();
					MDIWindow(this.array[idModulo]).validateDisplayList();
					MDIWindow(this.array[idModulo]).validateNow();
					MDICanvas(contenedor).windowManager.bringToFront(MDIWindow(this.array[idModulo]));
					MDIWindow(this.array[idModulo]).setFocus();
							
				}
				
				
			} else if(this.array[idModulo] is mx.containers.TitleWindow){				
				TitleWindow(this.array[idModulo]).percentHeight = 100;
				TitleWindow(this.array[idModulo]).percentWidth = 100;				
				PopUpManager.addPopUp(TitleWindow(this.array[idModulo]),Application.application as DisplayObject,true);
				PopUpManager.centerPopUp(TitleWindow(this.array[idModulo]));
			}
		}

	}
}