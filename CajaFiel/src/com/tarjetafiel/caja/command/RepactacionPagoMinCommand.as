package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.RepactacionDelegate;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class RepactacionPagoMinCommand implements IResponder, ICommand
	{
		public function RepactacionPagoMinCommand()
		{
			
		}

		public function result(data:Object):void
		{
			ControlBlock.getInstance().remove();
			ModelLocator.getInstance().repactacionModel.pagoMinimoConPagoCuenta = ResultEvent(data).result as Number;
			if (ModelLocator.getInstance().repactacionModel.pagoMinimoConPagoCuenta < 0) {
				ModelLocator.getInstance().repactacionModel.pagoMinimoConPagoCuenta = 0;
			}
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			AlertError.show(FaultEvent(info).fault.faultDetail);
		}
		
		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			new RepactacionDelegate(this).getPagoMinimo();
			 
			
		}
		
	}
}