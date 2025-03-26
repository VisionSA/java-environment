package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.clientes.ClientesDelegate;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class FuturosVencimientosCommand implements ICommand, IResponder
	{
		public function FuturosVencimientosCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ModelLocator.getInstance().clienteSeleccionado.futurosVencimientos.removeAll();
			ControlBlock.getInstance().add();
			new ClientesDelegate(this).buscarFuturosVencimientos();
		}
		
		public function result(data:Object):void
		{
			ControlBlock.getInstance().remove();
			ModelLocator.getInstance().clienteSeleccionado.futurosVencimientos = 
			new ArrayCollection(ResultEvent(data).result as Array);
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			AlertError.show(FaultEvent(info).fault.faultDetail);
		}
		
	}
}