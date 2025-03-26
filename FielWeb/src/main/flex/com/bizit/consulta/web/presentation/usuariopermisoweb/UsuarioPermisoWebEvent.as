package com.bizit.consulta.web.presentation.usuariopermisoweb
{
    import flash.events.Event;

    import com.bizit.consulta.web.entity.UsuarioPermisoWeb;

    public class UsuarioPermisoWebEvent extends Event
    {
        public static const CREATE:String = "usuarioPermisoWebCreate";
        public static const UPDATE:String = "usuarioPermisoWebUpdate";
        public static const DELETE:String = "usuarioPermisoWebDelete";

        public var usuarioPermisoWeb:UsuarioPermisoWeb;
        
        public function UsuarioPermisoWebEvent(type:String, usuarioPermisoWeb:UsuarioPermisoWeb, bubbles:Boolean = true, cancelable:Boolean = false)
        {
            this.usuarioPermisoWeb = usuarioPermisoWeb;
            super(type, bubbles, cancelable);
        }
    }
}