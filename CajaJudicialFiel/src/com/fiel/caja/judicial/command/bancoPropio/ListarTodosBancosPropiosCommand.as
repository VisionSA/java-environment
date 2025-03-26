package com.fiel.caja.judicial.command.bancoPropio
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.proveedorconexion.delegate.BancoPropioDelegate;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	
	public class ListarTodosBancosPropiosCommand implements ICommand, IResponder
	{
		
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			ManejadorPantallas.mostrarProgressBar();
			new BancoPropioDelegate(this).listarTodos();
		}
		
		public function result(data:Object):void
		{
			modelo.listaBancosPropios = new ArrayCollection(ResultEvent(data).result as Array);
			ManejadorPantallas.cerrarProgressBar();
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeError(FaultEvent(info).fault.faultString);
			ManejadorPantallas.cerrarProgressBar();
		}
	}
}