package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	
	import com.tarjetafiel.caja.event.ConsultarVencimientoEvent;
	
	import mx.rpc.IResponder;

	public class ConsultarVencimientoLiquidacionCommand implements ICommand, IResponder
	{
		public function ConsultarVencimientoLiquidacionCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			//new ConsultarVencimientoLiquidacionDelegate(this).consultarVencimiento(ConsultarVencimientoEvent(event).liquidacion); 
		}
		
		public function result(data:Object):void
		{
		}
		
		public function fault(info:Object):void
		{
		}
		
	}
}