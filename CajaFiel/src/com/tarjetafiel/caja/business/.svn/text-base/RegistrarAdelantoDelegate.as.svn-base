package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.event.AdelantoEfectivoEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.AdelantoEnEfectivo;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class RegistrarAdelantoDelegate extends BaseDelegate
	{
		public function RegistrarAdelantoDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function registrarAdelanto( evt:AdelantoEfectivoEvent ):void {
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("adelantoEfectivo");
			
			var adelanto:AdelantoEnEfectivo = new AdelantoEnEfectivo();
			adelanto.cuotasList = evt.cuotas;
			adelanto.importeAdelanto = ModelLocator.getInstance().adelantoEfectivoModel.importe;
			
			var ask:AsyncToken = ro.registrarAdelanto(adelanto
			 , evt.cliente, ModelLocator.getInstance().clienteSeleccionado.clienteAdicional,
			 evt.concepto, evt.monto,
			 ModelLocator.getInstance().operadorModel.operador,
			 ModelLocator.getInstance().cajaModel.caja,
			 ModelLocator.getInstance().impresorasModel.impresoraDefault,
			 ModelLocator.getInstance().movimientoModel.armarMovimientoAdelanto());
			 ask.addResponder(responder);			 
		}
		
		public function getDisponibleAdelantoEfectivo():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("adelantoEfectivo");
			var ask:AsyncToken = ro.getDisponibleAdelanto(ModelLocator.getInstance().clienteSeleccionado.cliente);
			ask.addResponder(responder);
		}
		
		
	}
}