package com.fiel.caja.judicial.command.CajaJudicialEvent
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.Banco;
	import com.tarjetafiel.proveedorconexion.delegate.BancoDelegate;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
	import utils.ManejadorMensajes;
	
	public class BuscarBancoPorIdCommand implements ICommand, IResponder
	{
		
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			new BancoDelegate(this).buscarBancoPorID(event.data);
		}
		
		public function result(data:Object):void
		{
			var resultado:Array = ResultEvent(data).result as Array;
			if (resultado!=null && resultado.length>0){
				modelo.banco = resultado.pop() as Banco;
			}else{
				ManejadorMensajes.mostrarMensajeError("El banco no existe");				
				modelo.banco = null;
			}
		}
		
		public function fault(info:Object):void
		{
		}
	}
}