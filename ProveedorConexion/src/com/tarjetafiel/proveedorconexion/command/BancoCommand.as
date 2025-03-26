package com.tarjetafiel.proveedorconexion.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.proveedorconexion.business.BancoDelegate;
	import com.tarjetafiel.proveedorconexion.event.BancoEvent;
	import com.tarjetafiel.proveedorconexion.model.ModelLocatorGeneral;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class BancoCommand implements ICommand, IResponder
	{
		private var evt:CairngormEvent;
		
		public function BancoCommand()
		{			
		}

		public function execute(event:CairngormEvent):void
		{
			evt = event;
			if(event.type == BancoEvent.BUSCAR_BANCOS){
											
				ControlBlock.getInstance().add();
				ModelLocatorGeneral.getInstance().bancoModel.arrayBancos = new ArrayCollection();
				new BancoDelegate(this).buscar();
							
			}
		}
		
		public function result(data:Object):void
		{
			if(evt.type == BancoEvent.BUSCAR_BANCOS){
				ModelLocatorGeneral.getInstance().bancoModel.arrayBancos.source = ResultEvent(data).result as Array; 
				ControlBlock.getInstance().remove();
			}		
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
	}
}