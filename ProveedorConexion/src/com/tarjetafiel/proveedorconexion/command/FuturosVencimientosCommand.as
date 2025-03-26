package com.tarjetafiel.proveedorconexion.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.proveedorconexion.business.ClientesGeneralDelegate;
	import com.tarjetafiel.proveedorconexion.model.ModelLocatorGeneral;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
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
			ModelLocatorGeneral.getInstance().clienteSeleccionado.futurosVencimientos.removeAll();
			ControlBlock.getInstance().add();
			new ClientesGeneralDelegate(this).buscarFuturosVencimientos();
		}
		
		public function result(data:Object):void
		{
			ControlBlock.getInstance().remove();
			ModelLocatorGeneral.getInstance().clienteSeleccionado.futurosVencimientos = 
			new ArrayCollection(ResultEvent(data).result as Array);
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			AlertError.show(FaultEvent(info).fault.faultDetail);
		}
		
	}
}