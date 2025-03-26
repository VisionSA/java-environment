package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.CajaArqueoDelegate;
	import com.tarjetafiel.caja.event.CajaArqueoEvent;
	import com.tarjetafiel.caja.model.ArqueoCajaModel;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.RespuestaImpresion;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	import com.util.components.alert.AlertWarning;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class CajaArqueoCommand implements ICommand, IResponder
	{
		public function CajaArqueoCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			new CajaArqueoDelegate(this).ejecutarCierre();
		}
		
		public function result(data:Object):void
		{
			var respuesta:RespuestaImpresion = ResultEvent(data).result as RespuestaImpresion;
			
			if(ModelLocator.getInstance().arqueoCajaModel.tipoDeArqueo == ArqueoCajaModel.ARQUEO_PROVISORIO){				
				ModelLocator.getInstance().arqueoCajaModel.completeArqueoProvisorio(respuesta.target as Array);				
			} else {
				ModelLocator.getInstance().arqueoCajaModel.dispatchEvent(new CajaArqueoEvent(CajaArqueoEvent.ARQUEO_COMPLETE));	
			}
			
			ControlBlock.getInstance().remove();
			
			if(respuesta.falloImpresion){
				AlertWarning.show(respuesta.mensaje);
			} else {
				ModelLocator.getInstance().ticketModel.ultimaRespuestaImpresion = RespuestaImpresion(data.result);
				AlertOk.show(respuesta.mensaje);
			}
			
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();
			AlertError.show(FaultEvent(info).fault.faultDetail);
			trace(info);
		}
		
	}
}