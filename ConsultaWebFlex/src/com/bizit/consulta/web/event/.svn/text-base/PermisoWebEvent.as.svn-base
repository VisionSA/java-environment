package com.bizit.consulta.web.event {
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.bizit.consulta.web.entity.PermisoWeb;
	
	public class PermisoWebEvent extends CairngormEvent {		
		
		public static const LIST_ALL:String = "PermisoWebListAllEvent";		
		public static const CREATE:String = "PermisoWebCreateEvent";
		public static const UPDATE:String = "PermisoWebUpdateEvent";
		public static const DELETE:String = "PermisoWebDeleteEvent";
		
		public function PermisoWebEvent(type:String, param : PermisoWeb) {
			super(type, false, false);
			data = param;
		}
	}
}