package com.tarjetafiel.proveedorconexion.delegate
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.vo.Abogado;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	public class ClienteTransaccionDelegate extends BaseDelegate
	{
		public function ClienteTransaccionDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscarClienteJudicial(cliente:ClienteTransaccion,abogado:Abogado):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("clientes");			
			var at:AsyncToken = ro.buscarClienteJudicial(cliente,abogado);
			at.addResponder(responder);
		}
		
	}
}