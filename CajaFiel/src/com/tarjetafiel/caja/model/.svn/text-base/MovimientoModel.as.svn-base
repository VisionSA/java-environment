package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.vo.Asiento;
	import com.tarjetafiel.caja.vo.AsientoItem;
	import com.tarjetafiel.caja.vo.ConceptoDetalleGen;
	import com.tarjetafiel.caja.vo.ConceptoGen;
	import com.tarjetafiel.caja.vo.CtaCteCliente;
	import com.tarjetafiel.caja.vo.Movimiento;
	import com.tarjetafiel.caja.vo.MovimientoMP;
	import com.tarjetafiel.caja.vo.PlanCuentaDos;
	
	import mx.formatters.DateFormatter;
	
	public class MovimientoModel
	{
		
		public function MovimientoModel()
		{
		}
		
		
		public function armarMovimiento(ctaCte:CtaCteCliente):Movimiento{
			try{
				var mov:Movimiento = new Movimiento();
				mov.movimientosMP = new Array();						
				mov.concepto = ModelLocator.getInstance().conceptosModel.getConceptoGen(ConceptosModel.COBRANZAS_FONDO);
				var asiento:Asiento = armarAsiento(mov.concepto);
					
				for each(var movimMP:MovimientoMP in ModelLocator.getInstance().pagoModel.arrayMedios){								
					if(movimMP.formaPago.idFormaPago != 8 && movimMP.formaPago.idFormaPago != 10){
						movimMP.asientoItem = new AsientoItem();				
						movimMP.asientoItem.importe = movimMP.monto;
						var planCuentaDos:PlanCuentaDos = ModelLocator.getInstance().formaPagoCajaModel.getIdPlanCuentaDos(movimMP.formaPago.idFormaPago);
						movimMP.asientoItem.idPlanCuenta = planCuentaDos.idPlanCuenta; 
						movimMP.asientoItem.signo = 1;	
						movimMP.asientoItem.leyenda = planCuentaDos.titulo;
						movimMP.asientoItem.asiento = asiento;																			
						mov.movimientosMP.push(movimMP);		
					}
					
				}
				
				mov.caja = ModelLocator.getInstance().cajaModel.caja;
				mov.cajaApertura = ModelLocator.getInstance().cajaModel.cajaApertura;					
				mov.fecha = new Date();
				mov.importe = ctaCte.importe;					
				mov.estado = "A";
				mov.operador = ModelLocator.getInstance().operadorModel.operador;
				mov.signo = 1;						
				return mov;
			}catch(err:Error){
				throw err;				
			}
			
			return null;
		}
		
		private function armarAsiento(con:ConceptoGen):Asiento{
			var asiento:Asiento = new Asiento();			
			asiento.concepto = new String(con.descripcion);
			asiento.cotabilizado = "N";
			asiento.fecha = new Date();
			var dateFormat:DateFormatter = new DateFormatter();
			dateFormat.formatString = "HHMM";
			asiento.hora = dateFormat.format(new Date());
			asiento.operador = ModelLocator.getInstance().operadorModel.operador;
			return asiento;
		} 
		
		
		public function armarMovimientoAdelanto():Movimiento{
			
			try{
				var mov:Movimiento = new Movimiento();
				mov.movimientosMP = new Array();						
				mov.concepto = ModelLocator.getInstance().conceptosModel.getConceptoGen(ConceptosModel.ADELANTO_FONDO_EFECTIVO);
				var movimMP:MovimientoMP = new MovimientoMP();								
				movimMP.asientoItem = new AsientoItem();				
				movimMP.asientoItem.importe = ModelLocator.getInstance().adelantoEfectivoModel.importe;
				var planCuentaDos:PlanCuentaDos = ModelLocator.getInstance().formaPagoCajaModel.getIdPlanCuentaDos(FormaPagoCajaModel.EFECTIVO);
				movimMP.asientoItem.idPlanCuenta = planCuentaDos.idPlanCuenta;
				movimMP.asientoItem.signo = -1;	
				movimMP.asientoItem.leyenda = planCuentaDos.titulo;
				movimMP.formaPago = ModelLocator.getInstance().formaPagoCajaModel.getFormaPago(FormaPagoCajaModel.EFECTIVO);
				movimMP.monto = ModelLocator.getInstance().adelantoEfectivoModel.importe;			
				var asiento:Asiento = armarAsiento(mov.concepto);
				movimMP.asientoItem.asiento = asiento;
				mov.movimientosMP.push(movimMP);						 							
				
				mov.caja = ModelLocator.getInstance().cajaModel.caja;
				mov.cajaApertura = ModelLocator.getInstance().cajaModel.cajaApertura;					
				mov.fecha = new Date();
				mov.importe = ModelLocator.getInstance().adelantoEfectivoModel.importe;					
				mov.estado = "A";
				mov.operador = ModelLocator.getInstance().operadorModel.operador;
				mov.signo = -1;						
				return mov;
			}catch(err:Error){
				throw err;				
			}
			
			return null;
		}
		
		
		private function getIdPlanCuentaUnica(concepto:ConceptoGen):Number{
			for each(var detalle:ConceptoDetalleGen in concepto.conceptoDetalleSet){
				if(detalle.orden == 0){
					return detalle.ctacontabledebe;
				}
			}
			
			return -1;
		}
				
	}
}