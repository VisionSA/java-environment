package com.tarjetafiel.proveedorconexion.delegate
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	public class ImpresorasDelegate extends BaseDelegate
	{
		public function ImpresorasDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function listarImpresoras():void
		{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("impresoras");			
			var at:AsyncToken = ro.getImpresora(new Filtro());
			at.addResponder(responder);
		}
		
		public function setearImpresoraPredeterminada():void
		{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("impresoras");			
			var at:AsyncToken = ro.getImpresora(new Filtro());
			at.addResponder(responder);
		}
	}
}