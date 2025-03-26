package com.application.red5
{
	import com.util.components.alert.AlertError;
	
	import flash.events.AsyncErrorEvent;
	import flash.events.IOErrorEvent;
	import flash.events.NetStatusEvent;
	import flash.events.SecurityErrorEvent;
	import flash.net.NetConnection;
	
	import mx.messaging.ChannelSet;
	import mx.messaging.channels.NetConnectionChannel;
	
	[Bindable]
	public class ConnectionRed5
	{
		public var nc:NetConnection;
		
		public function ConnectionRed5()
		{
		}
		
		public function conectar(app:String,... param):void{
				
			nc = new NetConnection();
			
			nc.addEventListener(AsyncErrorEvent.ASYNC_ERROR, askError);
			nc.addEventListener(IOErrorEvent.IO_ERROR, ioError);
			nc.addEventListener(SecurityErrorEvent.SECURITY_ERROR, scurityError);
			nc.addEventListener(NetStatusEvent.NET_STATUS, status);
			nc.connect(app,"tarjeta","fiel");
		}
		
		public function status(evt:NetStatusEvent):void{
			trace(evt.info.code);
		}
		
		public function scurityError(evt:SecurityErrorEvent):void{
			AlertError.show(evt.text);
		} 
		
		public function askError(evt:AsyncErrorEvent):void{
			AlertError.show(evt.error.message);
		}
		
		public function ioError(evt:IOErrorEvent):void{
			AlertError.show(evt.text);
		}

	}
}