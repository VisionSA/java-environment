package com.util.components.textInput
{
	import com.util.format.FormatUtil;
	
	import flash.events.Event;
	import flash.events.FocusEvent;
	
	import mx.controls.TextInput;
	import mx.events.FlexEvent;
	import mx.formatters.NumberBaseRoundType;
	import mx.utils.StringUtil;
	

	
	public class TextInputMoneda extends TextInput
	{
		public var simbolo:String="$";
		public var presicion:Object=2;
		public var round:String=NumberBaseRoundType.NONE;
		public var separadorMiles:Boolean=true;
		public var separadorDecimales:String = ".";
		public var usarNegativo:Object = true;
		
		public function TextInputMoneda():void{
			super.text = "0";
			this.restrict = "0-9\."
			this.addEventListener(FlexEvent.CREATION_COMPLETE, creationComplete);
		}
		
		private function focusOut(evt:FocusEvent):void {
			format();
		}
		
		private function creationComplete(event:FlexEvent):void
		{
			if (this.editable)
			{
				this.addEventListener(FocusEvent.FOCUS_IN, focusIn);
				this.addEventListener(FocusEvent.FOCUS_OUT, focusOut);
			}
		}
				
		override public function set text(value:String):void{
			if(StringUtil.trim(value) == ""){
				value = "0";
			}
			super.text = value;		
			format();
			dispatchEvent(new Event("changedText"));						
		}
		
		public function format(evt:FocusEvent=null):void{
			if(super.text == "")
				super.text = "0";
			
			var val:String;
			if(super.text.indexOf(",") != -1){
				val = super.text.replace(",","");	
			} else {
				val = super.text;
			}
						
			super.text = FormatUtil.formatMoneda(val,simbolo, presicion, round, separadorDecimales, separadorMiles,usarNegativo);			
		}
		
		public function focusIn(evt:FocusEvent):void{
			super.text = StringUtil.trim(super.text.split(simbolo)[1]);
			super.text = super.text.replace(",","");
			this.selectionBeginIndex = 0;
			this.selectionEndIndex = super.text.length;
		}
		
		[Bindable (event="changedText")]
		override public function get text():String{			
			
			var i:int = super.text.indexOf(simbolo);
			if(i != -1){
				var txt:String = String(super.text.split(simbolo)[1]).replace(",","");
				return txt;	
			} else {
				return super.text;
			}
			
		}
		

	}
}