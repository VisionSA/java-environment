package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.BuscarFormaPagoValorDelegate;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;

	public class BuscarFormaPagoValorComand implements ICommand, IResponder
	{
		public function BuscarFormaPagoValorComand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			new BuscarFormaPagoValorDelegate(this).buscarFormaPagoValores();
		}
		
		public function result(data:Object):void
		{
			ModelLocator.getInstance().arqueoCajaModel.formaPagoValoresList = new ArrayCollection(data.result as Array);
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void
		{
			AlertError.show(FaultEvent(info).fault.faultDetail);
			ControlBlock.getInstance().remove();
		}
		
	}
}