package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.model.PagoModel;
	import com.tarjetafiel.caja.vo.Cobrador;
	import com.tarjetafiel.caja.vo.Recibo;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	import mx.utils.ObjectUtil;

	public class GetReciboCobradorCommand implements ICommand, IResponder {
		
		[Bindable]
		private var pagoModel:PagoModel = ModelLocator.getInstance().pagoModel;
		
		public function execute(event:CairngormEvent):void {
			if(Number(event.data.nroRecibo)==0){/* Es un cliente particular */
				pagoModel.recibo = new Recibo();
				pagoModel.recibo.codigoRecibo=0;
				pagoModel.recibo.cobrador = new Cobrador();
				pagoModel.recibo.cobrador.nombre = "Cliente particular";
				pagoModel.recibo.cobrador.apellido ="";
				pagoModel.existeRecibo = true;
			}else {/*  Llamar a servicio para buscar recibo y cobrador */
				ControlBlock.getInstance().add();
				var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("recibo");
				var pendingCall:AsyncToken = ro.getReciboByCodigo(Number(event.data.nroRecibo));
				pendingCall.addResponder(this);
			}			
		}
		
		public function result(data:Object):void {			
			pagoModel.recibo = ResultEvent(data).result as Recibo;			
			pagoModel.existeRecibo = true;			
			ControlBlock.getInstance().remove();			
		}
		
		public function fault(info:Object):void {
			ControlBlock.getInstance().remove();
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
	}
}