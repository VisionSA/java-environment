package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.dto.TotalesMPDTO")]
	public class SumadorMediosPago
	{
		
		private var _sumaEfectivo : Number = 0;
		private var _sumaTickets : Number = 0;
		private var _sumaCheques : Number = 0;
		private var _sumaDepositos : Number = 0;
		public var sumaTotal : Number = 0;
	
		public function set sumaEfectivo(value:Number):void
		{
			this.sumaTotal -= _sumaEfectivo;
			_sumaEfectivo = value;
			this.sumaTotal +=_sumaEfectivo;
		}
		
		public function set sumaDepositos(value:Number):void
		{
			this.sumaTotal -= _sumaDepositos;
			_sumaDepositos = value;
			this.sumaTotal += _sumaDepositos;
		}
		
		public function set sumaCheques(value:Number):void
		{
			this.sumaTotal -= _sumaCheques;
			_sumaCheques = value;
			this.sumaTotal += _sumaCheques;
		}
		
		public function set sumaTickets(value:Number):void
		{
			this.sumaTotal -= _sumaTickets;
			_sumaTickets = value;
			this.sumaTotal += _sumaTickets;
		}
		
		public function get sumaDepositos():Number
		{
			return _sumaDepositos;
		}
		
		public function get sumaCheques():Number
		{
			return _sumaCheques;
		}
		
		
		public function get sumaTickets():Number
		{
			return _sumaTickets;
		}
		
		
		
		public function get sumaEfectivo():Number
		{
			return _sumaEfectivo;
		}
		
		
		public function sumar(valor:SumadorMediosPago):void{
			this.sumaCheques += valor.sumaCheques;
			this.sumaDepositos += valor.sumaDepositos;
			this.sumaEfectivo += valor.sumaEfectivo;
			this.sumaTickets += valor.sumaTickets;			
		}
		
		public function restar(valor:SumadorMediosPago):void{
			this.sumaCheques -= valor.sumaCheques;
			this.sumaDepositos -= valor.sumaDepositos;
			this.sumaEfectivo -= valor.sumaEfectivo;
			this.sumaTickets -= valor.sumaTickets;			
		}
		
		
		
		
	}
}