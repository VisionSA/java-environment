package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.ChequesEnCajaDelegate;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;

	public class ChequesEnCajaCommand implements ICommand, IResponder
	{
		public function ChequesEnCajaCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			new ChequesEnCajaDelegate(this).buscarChequesEnCaja();
		}
		
		public function result(data:Object):void
		{
			ControlBlock.getInstance().remove();
			ModelLocator.getInstance().chequeModel.chequesEnCajaList = new ArrayCollection(data.result as Array);
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			AlertError.show(FaultEvent(info).fault.faultDetail);
		}
		
	}
}