package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.ReImpresionTicketDelegate;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertOk;
	import com.util.components.alert.AlertWarning;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;

	public class ReImpresionTicketCommand implements ICommand, IResponder
	{
		public function ReImpresionTicketCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();			
			new ReImpresionTicketDelegate(this).reImprimirTicket();
		}
		
		public function result(data:Object):void
		{
			AlertOk.show("El ticket esta siendo reimpreso.");
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void
		{
			AlertWarning.show(FaultEvent(info).fault.faultDetail);
			ControlBlock.getInstance().remove();
		}
		
		
	}
}