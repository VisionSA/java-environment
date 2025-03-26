package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.FormaPagoCajaDelegate;
	import com.tarjetafiel.caja.event.FormaPagoValoresEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class FormaPagoCajaCommand implements ICommand, IResponder
	{
		public function FormaPagoCajaCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			new FormaPagoCajaDelegate(this).buscarFormaPagoCaja();
		}
		
		public function result(data:Object):void
		{
			var evt:FormaPagoValoresEvent = new FormaPagoValoresEvent(FormaPagoValoresEvent.BUSCAR_TODOS);
			evt.dispatch();				
			ModelLocator.getInstance().formaPagoCajaModel.arrayFormaPago = new ArrayCollection(ResultEvent(data).result as Array);			
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