package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.SucursalFiel;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class CajaAperturaDelegate extends BaseDelegate
	{
		public function CajaAperturaDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscarApertura():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("cajaApertura");
			var arr:Array = new Array();
			arr.push(new SucursalFiel());
			var at:AsyncToken = ro.getCajaAperturaFlex(ModelLocator.getInstance().operadorModel.operador,arr);
			at.addResponder(responder);
		}
		
	}
}