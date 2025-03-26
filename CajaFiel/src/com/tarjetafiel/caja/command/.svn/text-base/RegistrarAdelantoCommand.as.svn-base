package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.RegistrarAdelantoDelegate;
	import com.tarjetafiel.caja.event.AdelantoEfectivoEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.RespuestaImpresion;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	import com.util.components.alert.AlertWarning;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;

	public class RegistrarAdelantoCommand implements ICommand, IResponder
	{
		public function RegistrarAdelantoCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			
			if(ModelLocator.getInstance().impresorasModel.impresoraDefault == null){
				AlertError.show("La caja no tiene una impresora prdeterminada\nNo podra operar sin impresora");
				return;				
			}
			
			if(ModelLocator.getInstance().impresorasModel.impresoraDefault.habilitado == "S"){
				ControlBlock.getInstance().add();
				try {
					new RegistrarAdelantoDelegate(this).registrarAdelanto(event as AdelantoEfectivoEvent);
				}catch(err:Error){
					AlertError.show("Hubo un error en la aplicación\nEl pago no podra efectuarse\n\n" + err.message);
					ControlBlock.getInstance().remove();
				}
			} else {
				AlertError.show("La impresora por defecto no esta habilitada");
				ControlBlock.getInstance().remove();
			} 
			
			
		}
		
		public function result(data:Object):void
		{			
			var mensaje:String = RespuestaImpresion(data.result).mensaje;
			if(RespuestaImpresion(data.result).falloImpresion){
				AlertWarning.show(mensaje);
			} else {
				ModelLocator.getInstance().ticketModel.ultimaRespuestaImpresion = RespuestaImpresion(data.result);
				AlertOk.show(mensaje);	
			}
			
			ModelLocator.getInstance().clienteSeleccionado.cliente = RespuestaImpresion(data.result).target as ClienteTransaccion
			ModelLocator.getInstance().adelantoEfectivoModel.cuota.removeAll();
			ModelLocator.getInstance().adelantoEfectivoModel.cuotas.removeAll();
			ModelLocator.getInstance().adelantoEfectivoModel.importe = 0;
			new AdelantoEfectivoEvent(AdelantoEfectivoEvent.BUSCAR_DISPONIBLE).dispatch();
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