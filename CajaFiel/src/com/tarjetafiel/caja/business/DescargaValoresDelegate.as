package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.CajaMP;
	import com.tarjetafiel.caja.vo.Cheque;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class DescargaValoresDelegate extends BaseDelegate
	{
		public function DescargaValoresDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function registrarDescargaValores():void{
			
			for each(var cajaMp:CajaMP in ModelLocator.getInstance().descargaValoresModel.cajaMpList){
				if(cajaMp.formaPago.idFormaPago == 2){
					cajaMp.descargaChequesList = ModelLocator.getInstance().chequeModel.chequesDescargados.toArray();
					cajaMp.importeRetiro = 0;
					for each(var cheque:Cheque in cajaMp.descargaChequesList){
						cajaMp.importeRetiro += cheque.importe;
					}
				}
			}
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("movimientoService");
			var ask:AsyncToken = ro.registrarDescargaValores(ModelLocator.getInstance().descargaValoresModel.cajaMpList.toArray(),
				ModelLocator.getInstance().cajaModel.cajaApertura,
				ModelLocator.getInstance().operadorModel.operador,
				ModelLocator.getInstance().impresorasModel.impresoraDefault);
			ask.addResponder(responder);
		}
		
	}
}