package com.util.block
{
	import flash.display.DisplayObject;
	
	import mx.core.Application;
	import mx.managers.CursorManager;
	import mx.managers.PopUpManager;
	
	public class ControlBlock
	{
		private var block:Block = new Block();
		private var addBlock:int = 0;		
		public var activado:Boolean = false;
		private static var controlBlock:ControlBlock;
		
		
		public static function getInstance():ControlBlock
		{
			if(controlBlock == null){
				controlBlock = new ControlBlock();
			}
			
			return controlBlock;
		}
		
		
		
		public function add():void{			
			addBlock++;
			if(activado == false){
				activado = true;	
				PopUpManager.addPopUp(block,Application.application as DisplayObject,true);
				CursorManager.setBusyCursor();
				PopUpManager.centerPopUp(block);
			}
		}
		
		public function remove():void{
			if(addBlock == 1){
				addBlock--;
				activado = false;
				PopUpManager.removePopUp(block);
				CursorManager.removeAllCursors();
			} else if (addBlock > 1) {
				addBlock--;
			}			
		}

	}
}