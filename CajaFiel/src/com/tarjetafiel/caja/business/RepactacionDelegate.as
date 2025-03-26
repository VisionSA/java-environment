package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.event.RepactacionEvent;
	import com.tarjetafiel.caja.model.ConceptosModel;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Concepto;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	import mx.controls.Alert;

	public class RepactacionDelegate extends BaseDelegate
	{
		public function RepactacionDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		
		public function calcularRepactacion(evt:RepactacionEvent):void{
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("calculoCuotas");
			var codConcepto:Number;
			if(ModelLocator.getInstance().repactacionModel.refinanciacion)
				codConcepto = ConceptosModel.REFINANCIACION;
			else 
				codConcepto = ConceptosModel.REPACTACION;
			
			var concepto:Concepto = ModelLocator.getInstance().conceptosModel.getConcepto(codConcepto);			
			/*var cliente:ClienteTransaccion = ModelLocator.getInstance().clienteSeleccionado.cliente;			
			var ask:AsyncToken = ro.recalcularRepactacion(
			evt.montoTotal,
			evt.pagoMinimo,
			concepto,
			cliente,
			ModelLocator.getInstance().liqClienteModel.arrayLiqCliente.toArray());
			ask.addResponder(responder);*/
			
			var cliente:ClienteTransaccion = ModelLocator.getInstance().clienteSeleccionado.cliente;			
			var ask:AsyncToken = ro.simuladorCuotasIntereses(
				evt.montoTotal,
				evt.pagoMinimo,
				concepto,
				cliente,
				//ModelLocator.getInstance().liqClienteModel.arrayLiqCliente.toArray()
			0);
			ask.addResponder(responder);
			
			
		}
		
		public function getPagoMinimo():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("calculoCuotas");
			var ask:AsyncToken = ro.getPagoMinimoRepactacion2(ModelLocator.getInstance().clienteSeleccionado.cliente,
			ModelLocator.getInstance().liqClienteModel.saldo,ModelLocator.getInstance().liqClienteModel.arrayLiqCliente.toArray());
			ask.addResponder(responder);
		}
	}
}