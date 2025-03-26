package com.tarjetafiel.proveedorconexion.util
{
	import com.tarjetafiel.caja.vo.CajaMP;
	import com.tarjetafiel.caja.vo.FormaPago;
	import com.tarjetafiel.proveedorconexion.business.ConstantesFormaPago;
	import com.tarjetafiel.proveedorconexion.vo.MediosPagoPosibles;
	
	import mx.collections.ArrayCollection;

	public class FuncionesUtiles
	{
		public static function obtenerFormaPago(lista:ArrayCollection,idFormaPago:int):FormaPago{
			
			for each(var cmp:CajaMP in lista){				
				if (cmp.formaPago.idFormaPago == idFormaPago){
					return cmp.formaPago;
				}
			}
			return null;
		}
		
		public static function obtenerMediosPagosPosibles(lista:ArrayCollection):MediosPagoPosibles {
			
			var resultado : MediosPagoPosibles = new MediosPagoPosibles();
			
			for each(var cmp:CajaMP in lista){
				switch(cmp.formaPago.idFormaPago){
					case ConstantesFormaPago.EFECTIVO://Efectivo
						resultado.efectivo = true;
						break;
					case ConstantesFormaPago.CHEQUE://Cheques							
						resultado.cheques = true;						
						break;
					case ConstantesFormaPago.TICKET://Tickets
						resultado.tickets = true;
						break;
					case ConstantesFormaPago.DEPOSITO_BANCARIO://Deposito Bancario
						resultado.depositos = true;
						break;
				}
			}
			
			return resultado;
			
		}
		
		
	}
}