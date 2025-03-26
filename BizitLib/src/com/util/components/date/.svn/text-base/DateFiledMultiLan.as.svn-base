package com.util.components.date
{
	import com.util.components.alert.AlertError;
	
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;
	import flash.ui.Keyboard;
	
	import mx.controls.DateField;
	import mx.events.CloseEvent;
	import mx.events.FlexEvent;
	import mx.events.ValidationResultEvent;
	import mx.utils.StringUtil;
	import mx.validators.DateValidator;

	public class DateFiledMultiLan extends DateField
	{
				
		private var _language:String = ESP;
		
		private static const ESP:String = "esp";
		
		private static const ENG:String = "eng";
		
		public var edit:Boolean = false;
		
		private var validator:DateValidator = new DateValidator();
			
		
		public function DateFiledMultiLan()
		{
			super();
			this.init();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, onCreationComplete);			
			this.addEventListener(FocusEvent.FOCUS_IN, function (evt:FocusEvent):void{
				if(edit){
					textInput.setFocus();
				}
			});
			this.addEventListener(KeyboardEvent.KEY_DOWN, function (evt:KeyboardEvent):void{
				if(edit && (evt.keyCode == Keyboard.TAB || evt.keyCode == Keyboard.ENTER)){
					validator.validate(textInput.text);
				}
			});
			validator.allowedFormatChars = "/";
			validator.inputFormat = "DD/MM/YYYY";
			validator.source = textInput;	
			validator.property = "text";			
			validator.addEventListener(ValidationResultEvent.INVALID, function (evt:ValidationResultEvent):void{
				if(StringUtil.trim(textInput.text) != ""){
					AlertError.show("La fecha ingresada es incorrecta",function (evt:CloseEvent):void{
						textInput.text = "";
						textInput.setFocus();
					});	
				}
				
				
			});
		}
		
		private function onCreationComplete(evt:FlexEvent):void{
			this.textInput.editable = edit;
			this.removeEventListener(FlexEvent.CREATION_COMPLETE,onCreationComplete);
		}
		
		private function init():void{
			
			if(_language == ESP){
			
				this.monthNames = ["Enero",
								   "Febrero",
								   "Marzo",
								   "Abril",
								   "Mayo",
								   "Junio",
								   "Julio",
								   "Agosto",
								   "Septiembre",
								   "Octubre",
								   "Noviembre",
								   "Diciembre"];
				
				this.dayNames = ["Dom",
								 "Lun",
								 "Mar",
								 "Mie",
								 "Jue",
								 "Vie",
								 "Sab"];
								 
				this.formatString = "DD/MM/YYYY";
								 
			}
			
			this.yearNavigationEnabled = true;
													 	
		}
		
		[Inspectable(category="Lenguaje", enumeration="esp, eng", defaultValue="esp")]
		public function get language():String {
			return _language;
		}
		
		public function set language(value:String):void {
			if(value == null || _language != ESP){
				_language = ESP;
			} else if(_language == ENG){
				_language = ENG;
			}
		}
		
		
	}
}