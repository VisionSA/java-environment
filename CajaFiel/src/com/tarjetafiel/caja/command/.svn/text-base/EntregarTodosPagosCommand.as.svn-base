package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.PagoAComercioDelegate;
	import com.tarjetafiel.caja.event.PagoAComercioEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;

	public class EntregarTodosPagosCommand implements ICommand, IResponder
	{
		public function EntregarTodosPagosCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			
			if(ModelLocator.getInstance().impresorasModel.impresoraDefault == null){
				AlertError.show("La caja no tiene una impresora prdeterminada\nNo podra operar sin impresora");
				return;
			}
			ControlBlock.getInstance().add();
			new PagoAComercioDelegate(this).entregarTodosLosPagos();
		}
		
		public function result(data:Object):void
		{
			ModelLocator.getInstance().pagosAComercios.dispatchEvent(new PagoAComercioEvent(PagoAComercioEvent.PAGOS_UPDATE_COMPLETE));		
			ControlBlock.getInstance().remove();
			var evt:PagoAComercioEvent = new PagoAComercioEvent(PagoAComercioEvent.PAGOS_LIST);
			evt.dispatch();
			AlertOk.show(data.result.toString());
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			AlertError.show(FaultEvent(info).fault.faultDetail);
		}
		
	}
}