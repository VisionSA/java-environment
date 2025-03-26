package com.bizit.consulta.web.utils
{
	public class ManejadorIconos
	{
		public function ManejadorIconos()
		{
			super();
		}
		
		[Embed(source="com/bizit/consulta/web/assets/icons/Exclamation_32x32.png")]
		[Bindable] public static var ERROR:Class;
		
		[Embed(source="com/bizit/consulta/web/assets/icons/Info_32x32.png")]
		[Bindable] public static var INFO:Class;
		
		[Embed(source="com/bizit/consulta/web/assets/icons/Email_32x32.png")]
		[Bindable] public static var MAIL:Class;
		
		[Embed(source="com/bizit/consulta/web/assets/icons/PDF_22x22.png")]
		[Bindable] public static var PDF:Class;
		
		[Embed(source="com/bizit/consulta/web/assets/icons/PDF_DOWN_22x22.png")]
		[Bindable] public static var PDF_DOWN:Class;
		
		[Embed(source="com/bizit/consulta/web/assets/icons/TXT_22x22.png")]
		[Bindable] public static var TXT:Class;
		
		[Embed(source="com/bizit/consulta/web/assets/icons/TXT_DOWN_22x22.png")]
		[Bindable] public static var TXT_DOWN:Class;
		
		[Embed(source="com/bizit/consulta/web/assets/icons/Question_32x32.png")]
		[Bindable] public static var QUESTION:Class;
		
	}
}