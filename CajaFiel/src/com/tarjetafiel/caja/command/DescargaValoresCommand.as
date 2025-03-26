package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.DescargaValoresDelegate;
	import com.tarjetafiel.caja.event.DescargaValoresEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.RespuestaImpresion;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	
	import flash.events.Event;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;

	public class DescargaValoresCommand implements ICommand, IResponder
	{
		public function DescargaValoresCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			if(ModelLocator.getInstance().impresorasModel.impresoraDefault == null){
				AlertError.show("La caja no tiene una impresora prdeterminada\nNo podra operar sin impresora");
				return;
			}
			ControlBlock.getInstance().add();
			new DescargaValoresDelegate(this).registrarDescargaValores();
		}
		
		public function result(data:Object):void
		{
			ModelLocator.getInstance().chequeModel.chequesDescargados.removeAll();
			ControlBlock.getInstance().remove();			
			ModelLocator.getInstance().ticketModel.ultimaRespuestaImpresion = data.result as RespuestaImpresion;
			AlertOk.show(ModelLocator.getInstance().ticketModel.ultimaRespuestaImpresion.mensaje,function (evt:Event):void{
				ModelLocator.getInstance().descargaValoresModel.dispatchEvent(new DescargaValoresEvent(DescargaValoresEvent.RETIRO_COMPLETE));
			});
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			AlertError.show(FaultEvent(info).fault.faultDetail);
		}
		
	}
}