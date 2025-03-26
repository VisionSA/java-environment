package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.vo.BancoPropio;
	import com.tarjetafiel.caja.vo.MovimientoMP;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import mx.utils.ObjectUtil;
	
	[Bindable]
	public class TarjetaDebitoModel extends EventDispatcher
	{
		private var _movimMp:MovimientoMP;
		
		private var _movimMpOld:MovimientoMP;
		
		public var modificar:Boolean = false;
		
		public var bancoPropio:BancoPropio;
		
		public function addMedioPago():void{
			addCheque(); 
			movimMp.formaPago = ModelLocator.getInstance().formaPagoCajaModel.getFormaPago(11);
			movimMp.cheque.chequeEstado = ModelLocator.getInstance().chequeEstadoModel.getChequeEstado(2);
			ModelLocator.getInstance().pagoModel.addMedio(movimMp);
		}
		
		private function addCheque():void{
			movimMp.cheque.tipo = "Z";
			movimMp.cheque.importe = movimMp.monto;			
			movimMp.cheque.banco = movimMp.cheque.bancoPropio.banco;
			movimMp.cheque.beneficiario = movimMp.cheque.bancoPropio.sucursal.nombre;
			movimMp.cheque.sucursalBanco = movimMp.cheque.bancoPropio.numeroSucursal.toString();
			movimMp.cheque.fechaEmision = new Date();
			movimMp.cheque.esCruzado = "N";
			movimMp.cheque.noOrden = "N";
			movimMp.cheque.cuenta = movimMp.cheque.bancoPropio.numeroCuenta;
			movimMp.cheque.codigoPostal = movimMp.cheque.bancoPropio.plaza.toString();
			movimMp.cheque.conciliado = "N";
		}
		
		public function modificarMedioPago():void{
			addCheque();			
			ModelLocator.getInstance().pagoModel.modificarMedio(movimMp,movimMpOld);
		}
		
		public function set movimMpOld(value:MovimientoMP):void{
			_movimMpOld = value;
			_movimMp = ObjectUtil.copy(value) as MovimientoMP;
			bancoPropio = value.cheque.bancoPropio;
			for each(var bancoProp:BancoPropio in ModelLocator.getInstance().bancoModel.arrayBancosPropios){
				if(bancoProp.idBancoPropio == bancoPropio.idBancoPropio){
					bancoPropio = bancoProp;
					break;
				}
			}
			
			modificar = true;
			dispatchEvent(new Event("changedTBMovimientoMpOld"));
		}
		
		 [Bindable (event="changedTBMovimientoMpOld")]
		public function get movimMpOld():MovimientoMP{
			return _movimMpOld;
		}
		
		
		[Bindable (event="changedTBMovimMp")]
		public function get movimMp():MovimientoMP{
			return _movimMp;
		}
		
		public function set movimMp(value:MovimientoMP):void{
			_movimMp = value;
			modificar = false;
			dispatchEvent(new Event("changedMovimMp"));
		} 
		
		
		
		
	}
}