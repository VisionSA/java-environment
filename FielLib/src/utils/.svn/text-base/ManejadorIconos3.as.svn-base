/*@I3943*/
package utils
{
	import mx.core.UIComponent;

	public class ManejadorIconos3
	{
		public function ManejadorIconos3()
		{
			super();
		}
		
		public static function LOGO_FIEL(target:Object, url:String):Class
		{
			var pattern : RegExp = /\/Presentacion\/.*swf.*/;
			var appUrl:String = url.replace(pattern,"/Presentacion");
			var clase:Class = URLIconUtility.getClass(target as UIComponent, appUrl + "/img/fiel/fiel_logo_flex.png",183,80);
			return clase;
		}
		
/*@I3918*/		public static function LOGO_FIEL_CHICO(target:Object, url:String):Class
		{
			var pattern : RegExp = /\/Presentacion\/.*swf.*/;
			var appUrl:String = url.replace(pattern,"/Presentacion");
			var clase:Class = URLIconUtility.getClass(target as UIComponent, appUrl + "/img/fiel/fiel_logo_flex_chico.png",150,50);
			return clase;
/*@F3918*/		}
	}
}
/*@F3943*/