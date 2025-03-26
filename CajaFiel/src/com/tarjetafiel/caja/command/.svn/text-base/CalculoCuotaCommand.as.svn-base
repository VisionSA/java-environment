package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.CalculoCuotaDelegate;
	import com.tarjetafiel.caja.event.CalculoCuotaEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;

	public class CalculoCuotaCommand implements ICommand, IResponder
	{
		public function CalculoCuotaCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			ModelLocator.getInstance().adelantoEfectivoModel.cuotas.removeAll();
			new CalculoCuotaDelegate(this).calcularCuota(event as CalculoCuotaEvent);
		}
		
		public function result(data:Object):void
		{
			ControlBlock.getInstance().remove();
			ModelLocator.getInstance().adelantoEfectivoModel.cuotas = new ArrayCollection(data.result as Array);
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
	}
}