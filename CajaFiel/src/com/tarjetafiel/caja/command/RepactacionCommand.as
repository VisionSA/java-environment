package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.RepactacionDelegate;
	import com.tarjetafiel.caja.event.RepactacionEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import flash.events.Event;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.controls.Alert;

	public class RepactacionCommand implements ICommand, IResponder
	{
		private var evt:RepactacionEvent;
		public function RepactacionCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			
			evt = event as RepactacionEvent;
			ControlBlock.getInstance().add();
			new RepactacionDelegate(this).calcularRepactacion(evt);
		}
		
		public function result(data:Object):void
		{			
			
			if(ResultEvent(data).result is Array){
				ModelLocator.getInstance().repactacionModel.setArrayRepactacionesRecalculo(ResultEvent(data).result as Array);
			} else {
				ModelLocator.getInstance().repactacionModel.arrayRepactaciones.removeAll();
				ModelLocator.getInstance().repactacionModel.pagoMinimo = Number(ResultEvent(data).result); 
			}
			ControlBlock.getInstance().remove();			
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
	}
}