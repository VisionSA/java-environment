package com.tarjetafiel.caja.command {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.event.GeneralCajaFieldEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.Cheque;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertWarning;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class VerExisteChequeCommand implements ICommand, IResponder	{
				
		public function execute(event:CairngormEvent):void {
			
			ControlBlock.getInstance().add();
					
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("cheque");
			var ask:AsyncToken = ro.buscarChequePorNumero(ModelLocator.getInstance().chequeModel.cheque);
			ask.addResponder(this);			
		}
		
		public function result(data:Object):void {
			var cheque : Cheque = ResultEvent(data).result as Cheque;
			if (cheque != null){
				/* Existe cheque */									
												
				ModelLocator.getInstance().chequeModel.cheque = ResultEvent(data).result as Cheque;
				ModelLocator.getInstance().chequeModel.mp.modificar = false;
				ModelLocator.getInstance().chequeModel.mp.cheque = ModelLocator.getInstance().chequeModel.cheque;
				new GeneralCajaFieldEvent(GeneralCajaFieldEvent.GET_ESTADO_CHEQUE_BY_CHEQUE_ID_EVENT,cheque).dispatch();
				ModelLocator.getInstance().chequeModel.existeCheque = true;
				ModelLocator.getInstance().chequeModel.esModificable = false; 
				AlertWarning.show("El cheque ingresado ya existe.\nNo podrá ser modificado.");
			}else {
				/* No existe cheque */
				ModelLocator.getInstance().chequeModel.esModificable = true;
				ModelLocator.getInstance().chequeModel.existeCheque = false;
			}		
			ModelLocator.getInstance().chequeModel.mostrarDatosCheque = true;
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void {
			ControlBlock.getInstance().remove();
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
	}
}