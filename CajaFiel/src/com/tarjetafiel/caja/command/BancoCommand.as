package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.BancoDelegate;
	import com.tarjetafiel.caja.event.BancoEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class BancoCommand implements ICommand, IResponder
	{
		private var evt:CairngormEvent;
		
		public function BancoCommand()
		{			
		}

		public function execute(event:CairngormEvent):void
		{
			evt = event;
			if(event.type == BancoEvent.BUSCAR_BANCOS){
											
				ControlBlock.getInstance().add();
				new BancoDelegate(this).buscar();
							
			}
		}
		
		public function result(data:Object):void
		{
			if(evt.type == BancoEvent.BUSCAR_BANCOS){
				ModelLocator.getInstance().bancoModel.arrayBancos.source = ResultEvent(data).result as Array; 
				ControlBlock.getInstance().remove();
			}		
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
	}
}