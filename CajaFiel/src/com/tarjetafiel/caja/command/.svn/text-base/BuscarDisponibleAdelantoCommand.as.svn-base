package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.RegistrarAdelantoDelegate;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class BuscarDisponibleAdelantoCommand implements ICommand, IResponder
	{
		public function BuscarDisponibleAdelantoCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			new RegistrarAdelantoDelegate(this).getDisponibleAdelantoEfectivo();
		}
		
		public function result(data:Object):void
		{
			ModelLocator.getInstance().adelantoEfectivoModel.disponibleAdelanto = Number(ResultEvent(data).result);
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void
		{
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
	}
}