package com.fiel.caja.judicial.command.Cheques
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	
	import mx.rpc.IResponder;
	
	public class AgregarChequeCommand implements ICommand, IResponder
	{
	
		[Bindable]
		private var modelo : CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
				
		public function execute(event:CairngormEvent):void
		{
			modelo.listaCheques.addItem(event.data);
		}
		
		public function result(data:Object):void
		{
		}
		
		public function fault(info:Object):void
		{
		}
	}
}