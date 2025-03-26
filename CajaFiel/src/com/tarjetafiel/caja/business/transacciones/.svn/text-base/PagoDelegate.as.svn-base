package com.tarjetafiel.caja.business.transacciones
{	
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.event.PagoEvent;
	import com.tarjetafiel.caja.model.ConceptosModel;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.CtaCteCliente;
	import com.tarjetafiel.caja.vo.LiqClienteRepactacion;
	import com.tarjetafiel.caja.vo.Operador;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	

	public class PagoDelegate extends BaseDelegate 
	{
		//private var posnetParser:PosnetParser;
				
		public function PagoDelegate(responder:mx.rpc.IResponder)
		{
			super(responder);
		}
		
		public function registrarPago(event:PagoEvent):void{
			var cta:CtaCteCliente = event.ctaCteCliente;			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("pagosCliente");
			ModelLocator.getInstance().clienteSeleccionado.cliente
			cta.clienteTransaccion = ModelLocator.getInstance().clienteSeleccionado.cliente;
			cta.fechaFacturacionFlex = new Date();
			cta.fecha = new Date();			
			cta.nroCuota = 1;
			cta.estadoImpacto = "C";			
			cta.timestampFlex = new Date();	
			
			//esto se puso para las pruebas en san juan
			cta.clienteTransaccion.ciclo = 1;
			cta.nroPlastico = "1";
				
			cta.idOperador = ModelLocator.getInstance().operadorModel.operador.codigo;									
			var liqClienteRepactacion:LiqClienteRepactacion = ModelLocator.getInstance().repactacionModel.liqClienteRepactacion;
			var arrayConceptos:Array = new Array();			
			
			if( cta.importe > 0 ){
				arrayConceptos.push(ModelLocator.getInstance().conceptosModel.getConcepto(ConceptosModel.COBRANZAS));
			}
			
			if( liqClienteRepactacion != null ){
				
				var codConcepto:Number;
				if(ModelLocator.getInstance().repactacionModel.refinanciacion)
					codConcepto = ConceptosModel.REFINANCIACION;
				else 
					codConcepto = ConceptosModel.REPACTACION;
				
				arrayConceptos.push(ModelLocator.getInstance().conceptosModel.getConcepto(codConcepto));
				liqClienteRepactacion.saldo = ModelLocator.getInstance().liqClienteModel.saldo;
				liqClienteRepactacion.liqCliente = null;
			}
			
			var arrayLiqPagar:Array = ModelLocator.getInstance().pagoModel.arrayLiqPagar.toArray();
									
			var operador:Operador = ModelLocator.getInstance().operadorModel.operador;
			var pendingCall:AsyncToken = ro.registrarPagoCliente(operador, arrayConceptos, cta,
			arrayLiqPagar,
			ModelLocator.getInstance().movimientoModel.armarMovimiento(cta),
			cta == null ? 0 : cta.importe, liqClienteRepactacion, 
			ModelLocator.getInstance().impresorasModel.impresoraDefault,
			ModelLocator.getInstance().cajaModel.caja,
			ModelLocator.getInstance().cajaModel.cajaApertura.idCajaApertura,
			ModelLocator.getInstance().clienteSeleccionado.clienteAdicional,event.suVuelto,
			ModelLocator.getInstance().pagoModel.pagarTodo,ModelLocator.getInstance().pagoModel.recibo.idRecibo);			
			pendingCall.addResponder(responder);
		}
		
	}
}