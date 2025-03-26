package com.bizit.consulta.web.presentation.usuarioweb
{
    import flash.events.Event;

    import com.bizit.consulta.web.entity.UsuarioWeb;

    public class UsuarioWebEvent extends Event
    {
        public static const CREATE:String = "usuarioWebCreate";
        public static const UPDATE:String = "usuarioWebUpdate";
        public static const DELETE:String = "usuarioWebDelete";

        public var usuarioWeb:UsuarioWeb;
        
        public function UsuarioWebEvent(type:String, usuarioWeb:UsuarioWeb, bubbles:Boolean = true, cancelable:Boolean = false)
        {
            this.usuarioWeb = usuarioWeb;
            super(type, bubbles, cancelable);
        }
    }
}