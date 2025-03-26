package com.tarjetafiel.caja.model
{
	import flexmdi.containers.MDICanvas;
	import flexmdi.containers.MDIWindow;
	
	import mx.core.Application;
	import mx.core.IContainer;
	
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
				
				
				
			} 
		}

	}
}