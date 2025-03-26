package com.tarjetafiel.proveedorconexion.delegate
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	public class ColaboradorDelegate extends BaseDelegate
	{
		public function ColaboradorDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscarListaAbogados():void {		
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("colaboradorService");
			var pendingCall:AsyncToken = ro.listarColaboradoresAbogados();
			pendingCall.addResponder(responder);	
		}
		
	}
	
}