package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.IndividuosHabilitadosPagosDelegate;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;

	public class IndividuosHabilitadosPagosCommand implements ICommand, IResponder
	{
		public function IndividuosHabilitadosPagosCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			new IndividuosHabilitadosPagosDelegate(this).buscarHabilitadosEmpresa();
		}
		
		public function result(data:Object):void
		{
			ControlBlock.getInstance().remove();
			ModelLocator.getInstance().individuosHabilitadosPagosModel.individuosList = new ArrayCollection(data.result as Array);
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			AlertError.show(FaultEvent(info).fault.faultDetail);
		}
		
	}
}