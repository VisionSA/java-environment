package com.tarjetafiel.caja.business.clientes
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	public class ClientesDelegate extends BaseDelegate
	{			
		public function ClientesDelegate( responder:IResponder ){
			super(responder);		
		}
		
		public function buscar(filtro:Filtro, paginador:Paginador):void {
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("clientes");
			var pendingCall:AsyncToken = ro.getClienteFlex(filtro, paginador);
			pendingCall.addResponder(responder);
			
		}					
		
		public function buscarPorPlastico(filtro:Filtro, paginador:Paginador):void {
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("clientesPlastico");			
			var pendingCall:AsyncToken = ro.getPlasticoClienteFlex(filtro, paginador);
			pendingCall.addResponder(responder);
			
		}
		
		
		
		public function buscarLiquidaciones(filtro:Filtro):void {
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("liquidacionCliente");
			var pendingCall:AsyncToken = ro.getLiqClienteFlex(filtro.valores[0]);
			pendingCall.addResponder(responder);
			
		}
		
		public function buscarDetalleLiquidaciones(filtro:Filtro):void {
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("liquidacionDetalleCliente");
			var pendingCall:AsyncToken = ro.getCtaCteClienteFlex(filtro);
			pendingCall.addResponder(responder);
			
		}
		
		public function buscarClienteTitular(idCliente:int):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("clientes");
			var pendingCall:AsyncToken = ro.buscarClienteFlex(idCliente);
			pendingCall.addResponder(responder);
		}
		
		public function buscarFuturosVencimientos(){
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("clientes");
			var pendingCall:AsyncToken = ro.buscarFuturosVencimientos(ModelLocator.getInstance().clienteSeleccionado.cliente);
			pendingCall.addResponder(responder);
		}

				
	}
}