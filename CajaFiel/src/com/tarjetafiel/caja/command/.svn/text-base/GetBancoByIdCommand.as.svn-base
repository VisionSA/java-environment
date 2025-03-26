package com.tarjetafiel.caja.command {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.Banco;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import mx.controls.Alert;
	import mx.core.Application;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class GetBancoByIdCommand implements ICommand, IResponder {
		public function execute(event:CairngormEvent):void {
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("banco");
			var filtro : Filtro = new Filtro();
			filtro.campos.push("idBanco");
			filtro.valores.push(event.data);
			filtro.operadores.push(Filtro.IGUAL);
			var pendingCall:AsyncToken = ro.getBancos(filtro);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			var resultado:Array = ResultEvent(data).result as Array;
			if (resultado!=null && resultado.length>0){
				ModelLocator.getInstance().chequeModel.cheque.banco = resultado.pop() as Banco;
			}else{
				Alert.show("Banco inexistente");
				ModelLocator.getInstance().chequeModel.cheque.banco = null;
			}
			
		}
		
		public function fault(info:Object):void {
			Alert.show("ERROR");
		}
		
	}
}