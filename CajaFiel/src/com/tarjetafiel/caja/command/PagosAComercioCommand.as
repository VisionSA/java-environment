package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.PagoAComercioDelegate;
	import com.tarjetafiel.caja.event.PagoAComercioEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;

	public class PagosAComercioCommand implements ICommand, IResponder
	{
		public function PagosAComercioCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			if(ModelLocator.getInstance().empresaModel.empresa){
				
				ModelLocator.getInstance().pagosAComercios.liquidacionesList.removeAll();
				ControlBlock.getInstance().add();
				new PagoAComercioDelegate(this).buscarPagos();		
			}	
		}
		
		public function result(data:Object):void
		{
			ModelLocator.getInstance().pagosAComercios.liquidacionesList = new ArrayCollection(data.result as Array);
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			AlertError.show(FaultEvent(info).fault.faultDetail);
		}
		
	}
}