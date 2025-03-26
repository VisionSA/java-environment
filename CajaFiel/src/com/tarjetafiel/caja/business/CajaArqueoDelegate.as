package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.CajaApertura;
	import com.tarjetafiel.caja.vo.CajaCierre;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class CajaArqueoDelegate extends BaseDelegate
	{
		public function CajaArqueoDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function ejecutarCierre():void{
			trace("Caja " + ModelLocator.getInstance().cajaModel.caja.idCaja);
			trace("Apertura " + ModelLocator.getInstance().cajaModel.cajaApertura.idCajaApertura);
			trace("Cierres " + ModelLocator.getInstance().arqueoCajaModel.cajaCierreList);
						
			var cajaApertura:CajaApertura = ModelLocator.getInstance().cajaModel.cajaApertura;
			var cajaCierres:Array = ModelLocator.getInstance().arqueoCajaModel.cajaCierreList.toArray();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("arqueoService");
			
			var ask:AsyncToken = 
			ro.procesarArqueo(cajaApertura,
			cajaCierres,
			ModelLocator.getInstance().impresorasModel.impresoraDefault
			,ModelLocator.getInstance().chequeModel.chequesEnCajaList.toArray());
			
			ask.addResponder(responder);
		}
		
	}
}