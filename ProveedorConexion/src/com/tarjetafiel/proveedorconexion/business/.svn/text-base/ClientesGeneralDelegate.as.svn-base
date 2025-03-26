package com.tarjetafiel.proveedorconexion.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.tarjetafiel.proveedorconexion.model.ModelLocatorGeneral;
	import com.util.paginacion.Paginador;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	public class ClientesGeneralDelegate extends BaseDelegate
	{			
		public function ClientesGeneralDelegate( responder:IResponder ){
			super(responder);		
		}
		
		public function buscarFuturosVencimientos():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("clientes");
			var pendingCall:AsyncToken = ro.buscarFuturosVencimientos(ModelLocatorGeneral.getInstance().clienteSeleccionado.titular);
			pendingCall.addResponder(responder);
		}

				
	}
}