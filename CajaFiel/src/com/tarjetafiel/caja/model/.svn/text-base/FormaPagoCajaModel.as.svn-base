package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.vo.CajaMP;
	import com.tarjetafiel.caja.vo.FormaPago;
	import com.tarjetafiel.caja.vo.PlanCuentaDos;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	public class FormaPagoCajaModel extends EventDispatcher
	{
		[Bindable]private var _arrayFormaPago:ArrayCollection = new ArrayCollection();
		[Bindable]public var arrayFormaPagoArqueo:ArrayCollection = new ArrayCollection();
		
		public static const EFECTIVO:Number = 1;		
		public static const CHEQUE:Number = 2;
		public static const TICKET:Number = 5;
		
		
		public function FormaPagoCajaModel()
		{
		}
		
		/**
		 * Devielve la forma de pago según el id pasado por parametro
		 * Formas de PAGO:
		 * 1- Efectivo
		 * 2-Cheque
		 * 3-Acreditación Bancaria
		 * 4-Acreditación Bancaria en Cta de Terceros
		 * 5-Tickets  
		 * 8-Repactacion
		 * 10-Refinanciacion
		 * 6-Deposito Bancario
		 * 11 - Tarjeta Debito
		 **/
		public function getFormaPago(idFormaPago:Number):FormaPago{
			for each(var forma:CajaMP in arrayFormaPago){
				if(forma.formaPago.idFormaPago == idFormaPago){
					return forma.formaPago;
				}
			}
			
			return null;
		}
		
		public function getPlanCuentaDos(idFormaPago:Number):PlanCuentaDos{
			for each(var forma:CajaMP in arrayFormaPago){
				if(forma.formaPago.idFormaPago == idFormaPago){
					return forma.planCuentaDos;
				}
			}
			
			return null;
		}
		
		public function getIdPlanCuentaDos(idFormaPago:Number):PlanCuentaDos{
			for each(var forma:CajaMP in arrayFormaPago){
				if(forma.formaPago.idFormaPago == idFormaPago){
					return forma.planCuentaDos;
				}
			}
			
			return null;
		}
		
				
		public function set arrayFormaPago(list:ArrayCollection):void{
			_arrayFormaPago = list;
			arrayFormaPagoArqueo.removeAll();
			for each(var cajaMp:CajaMP in _arrayFormaPago){
				/*if(cajaMp.planCuentaDos !=.formaPago.idFormaPago == 1 || cajaMp.formaPago.idFormaPago == 2 || 
					cajaMp.formaPago.idFormaPago == 5){*/
				if(cajaMp.planCuentaDos){
					
					arrayFormaPagoArqueo.addItem(cajaMp);					
							
				}
				cajaMp.caja = ModelLocator.getInstance().cajaModel.caja;
			}
			dispatchEvent(new Event("changedFormaPagoEvent"));			
		}
		
		[Bindable (event="changedFormaPagoEvent")]
		public function get arrayFormaPago():ArrayCollection{
			return _arrayFormaPago;
		}

	}
}