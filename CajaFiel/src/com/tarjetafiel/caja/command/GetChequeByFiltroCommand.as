package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.event.GeneralCajaFieldEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.BancoPropio;
	import com.tarjetafiel.caja.vo.Cheque;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class GetChequeByFiltroCommand implements ICommand, IResponder {
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("cheque");
			var pendingCall:AsyncToken = ro.getChequesByParam(event.data.bp as BancoPropio,event.data.numero as String, event.data.digVal as int);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			var resultado : Array = ResultEvent(data).result as Array;			
			if (resultado != null && resultado.length > 0){
				var cheque : Cheque = resultado.pop() as Cheque;
				ModelLocator.getInstance().chequeModel.cheque = cheque;
				ModelLocator.getInstance().chequeModel.mp.modificar = false;
				new GeneralCajaFieldEvent(GeneralCajaFieldEvent.GET_ESTADO_CHEQUE_BY_CHEQUE_ID_EVENT,cheque).dispatch();
				ModelLocator.getInstance().chequeModel.existeCheque = true;
				ModelLocator.getInstance().chequeModel.esModificable = false;
				ModelLocator.getInstance().chequeModel.mostrarDatosCheque = true;
			}else{
				Alert.show("No se encontró ningún cheque\ncon la descripción ingresada.");	
			}

			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void {
			ControlBlock.getInstance().remove();
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
	}
}