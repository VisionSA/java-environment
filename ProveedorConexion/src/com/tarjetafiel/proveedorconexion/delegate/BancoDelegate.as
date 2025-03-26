package com.tarjetafiel.proveedorconexion.delegate
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	public class BancoDelegate extends BaseDelegate
	{
		public function BancoDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscarBancoPorID(idBanco : Object):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("banco");
			var filtro : Filtro = new Filtro();
			filtro.campos.push("idBanco");
			filtro.valores.push(idBanco);
			filtro.operadores.push(Filtro.IGUAL);
			var pendingCall:AsyncToken = ro.getBancos(filtro);
			pendingCall.addResponder(responder);
		}
		
	}
}