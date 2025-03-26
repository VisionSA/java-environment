package com.tarjetafiel.proveedorconexion.delegate
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.vo.BancoPropio;
	import com.tarjetafiel.caja.vo.Cheque;
	import com.tarjetafiel.caja.vo.Lugar;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	public class ChequeDelegate extends BaseDelegate
	{
		public function ChequeDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscarCheque(cheque:Cheque):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("cheque");
			var ask:AsyncToken = ro.buscarChequePorNumero(cheque);
			ask.addResponder(responder);
		}
		
		public function listarChequesEnCaja(param:Lugar):void
		{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("chequeLugarService");			
			var ask:AsyncToken = ro.buscarChequesEnLugar(param);
			ask.addResponder(responder);
		}
		
		public function buscarEstadoCheque(cheque:Cheque):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("chequeHistorial");
			var ask:AsyncToken = ro.getChequeEstadoByIdCheque(cheque.idCheque);
			ask.addResponder(responder);	
		}
		
		//ObtenerCheque estado por un id ChequeEstado
		public function buscarChequeEstado(id:Number):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("chequeEstados");
			var ask:AsyncToken = ro.leerChequeEstado(id);
			ask.addResponder(responder);
		}
		
		//Devuelve una lista con un elemento en caso de existir
		public function buscarChequePropio(bancoPropio:BancoPropio,nroCheque:String,digValidador:int):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("cheque");
			var pendingCall:AsyncToken = ro.getChequesByParam(bancoPropio,nroCheque,digValidador);
			pendingCall.addResponder(responder);
		}
		
		
	}
}