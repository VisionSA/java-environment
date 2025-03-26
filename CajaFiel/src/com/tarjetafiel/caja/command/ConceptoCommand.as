package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.ConceptoDelegate;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class ConceptoCommand implements ICommand, IResponder
	{
		public function ConceptoCommand()
		{
			
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			new ConceptoDelegate(this).listarConceptos();
		}
		
		public function result(data:Object):void
		{
			ModelLocator.getInstance().conceptosModel.arrayConceptos.source = ResultEvent(data).result as Array;
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