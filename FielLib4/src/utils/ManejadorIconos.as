package utils
{
	import mx.controls.Alert;
	import mx.controls.Image;
	import mx.core.FlexGlobals;
	import mx.core.UIComponent;

	public class ManejadorIconos
	{
		public function ManejadorIconos()
		{
			super();
		}
		
		[Embed(source="assets/images/icons/Exclamation_32x32.png")]
		[Bindable] public static var ERROR:Class;
		
		[Embed(source="assets/images/icons/Info_32x32.png")]
		[Bindable] public static var INFO:Class;
		
		[Embed(source="assets/images/icons/Email_32x32.png")]
		[Bindable] public static var MAIL:Class;
		
		[Embed(source="assets/images/icons/Question_32x32.png")]
		[Bindable] public static var QUESTION:Class;
		
		[Embed(source="assets/images/icons/Warning_32x32.png")]
		[Bindable] public static var WARNING:Class;
		
		[Embed(source="assets/images/icons/Printer_64x64.png")]
		[Bindable] public static var PRINTER:Class;
		
		[Embed(source="assets/images/icons/Default_Printer_64x64.png")]
		[Bindable] public static var DEFAULT_PRINTER:Class;
		
		[Embed(source="assets/images/icons/Cheque_64x64.png")]
		[Bindable] public static var CHEQUE:Class;
		
		[Embed(source="assets/images/icons/PrinterIcon_32x32.png")]
		[Bindable] public static var PRINTER_ICON:Class;
		
		[Embed(source="assets/images/icons/Banco_64x64.png")]
		[Bindable] public static var BANCO:Class;

		[Embed(source="assets/images/fiel/Barra.png")]
		[Bindable] public static var BARRA:Class;
		
		[Embed(source="assets/images/icons/Delete_64x64.png")]
		[Bindable] public static var DELETE_ICON:Class;
		
		/**********/
		
		[Embed(source="assets/images/icons/Add_MP_64x64.png")]
		[Bindable] public static var AGREGAR_MP_ICON:Class;
		
		[Embed(source="assets/images/icons/Delete_Recicler_64x64.png")]
		[Bindable] public static var BORRAR_TODO_ICON:Class;
		
		[Embed(source="assets/images/icons/Lupa_48x48.png")]
		[Bindable] public static var LUPA_ICON:Class;
		
		[Embed(source="assets/images/icons/Money_64x64.png")]
		[Bindable] public static var MONEDA_ICON:Class;
		
		[Embed(source="assets/images/icons/Users_64x64.png")]
		[Bindable] public static var ADD_USUARIO_ICON:Class;
		
		[Embed(source="assets/images/icons/Add_64x64.png")]
		[Bindable] public static var ADD_ICON:Class;
		
		[Embed(source="assets/images/icons/Cancel_64x64.png")]
		[Bindable] public static var CANCEL_ICON:Class;
		
		[Embed(source="assets/images/icons/Ok_64x64.png")]
		[Bindable] public static var OK_ICON:Class;
		
		[Embed(source="assets/images/icons/Stop_128x128.png")]
		[Bindable] public static var ACCESO_DENEGADO_ICON:Class;
		
		//[Bindable]public static var logo_fiel:Image = newLogo();
		
		public static function LOGO_FIEL(target:Object, url:String):Class
		{
			var pattern : RegExp = /\/Presentacion\/.*swf.*/;
			var appUrl:String = url.replace(pattern,"/Presentacion");
			var clase:Class = URLIconUtility.getClass(target as UIComponent, appUrl + "/img/fiel/fiel_logo_flex.png",183,80);
			return clase;
		}
		
	}
}