package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.ChequeDelegate;
	import com.tarjetafiel.caja.event.ChequeEvent;
	import com.tarjetafiel.caja.vo.Cheque;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public final class ChequeCommand implements ICommand, IResponder
	{
		private var evt:ChequeEvent;
		public function ChequeCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			evt = event as ChequeEvent;			
			new ChequeDelegate(this).existeCheque(evt.cheque);
		}
		
		public function result(data:Object):void
		{							
			if(evt.type == ChequeEvent.EXISTE_CHEQUE){
				evt.functionExisteCheque.call(this,ResultEvent(data).result as Cheque);				
			}	
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
	}
}