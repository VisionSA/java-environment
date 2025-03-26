package com.tarjetafiel.caja.view.clientes.controles
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.controls.CheckBox;

	public class CheckBoxCustom extends CheckBox
	{
				
		private var _value:String = "N"; 
		
		public function CheckBoxCustom()
		{
			super();
		}
					
		override protected function clickHandler(event:MouseEvent):void{
			super.clickHandler(event);
			if(selected){
				value = "S";
			} else {
				value = "N";
			}
		}
		
		[Bindable (event="changedValue")]
		public function get value():String{
			return _value;
		}    
		
		public function set value(es:String):void{
			_value = es;
			dispatchEvent(new Event("changedValue"));
			
		}
		
	}
}