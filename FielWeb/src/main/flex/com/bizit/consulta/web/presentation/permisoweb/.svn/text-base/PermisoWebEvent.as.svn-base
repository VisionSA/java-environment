package com.bizit.consulta.web.presentation.permisoweb
{
    import flash.events.Event;

    import com.bizit.consulta.web.entity.PermisoWeb;

    public class PermisoWebEvent extends Event
    {
        public static const CREATE:String = "permisoWebCreate";
        public static const UPDATE:String = "permisoWebUpdate";
        public static const DELETE:String = "permisoWebDelete";

        public var permisoWeb:PermisoWeb;
        
        public function PermisoWebEvent(type:String, permisoWeb:PermisoWeb, bubbles:Boolean = true, cancelable:Boolean = false)
        {
            this.permisoWeb = permisoWeb;
            super(type, bubbles, cancelable);
        }
    }
}