package com.tarjetafiel.caja.vo
{
	[Bindable]
	public class Operacion
	{
		private var _cantidadCuotas:String;
		
		private var _importe:String;
		
		private var _tasa:String;			
		
		private var _codMoneda:String;
		
		private var _tipoReg:String;			
		
		private var _tarjeta:String;
		
		private var _planCuotas:String;
		
		private var _importeTotal:String;
		
		public function Operacion()
		{
		}
		
		public function get importeTotal():String{
			return _importeTotal;
		}
		
		public function set importeTotal(value:String):void{
			_importeTotal = value;
		}
		
		public function get cantidadCuotas():String{
			return _cantidadCuotas;
		}
		
		public function set cantidadCuotas(value:String):void{
			_cantidadCuotas = value;
		}
		public function get importe():String{
			return _importe;
		}
		
		public function set importe(value:String):void{
			_importe = value;
		}
		
		public function get tasa():String{
			return _tasa;
		}
		
		public function set tasa(value:String):void{
			_tasa = value;
		}			
		
		public function get codMoneda():String{
			return _codMoneda;
		}
		
		public function set codMoneda(value:String):void{
			_codMoneda = value;
		}
		
		public function get tipoReg():String{
			return _tipoReg;
		}
		
		public function set tipoReg(value:String):void{
			_tipoReg = value;
		}			
		
		public function get tarjeta():String{
			return _tarjeta;
		}
		
		public function set tarjeta(value:String):void{
			_tarjeta = value;
		}
		
		public function get planCuotas():String{
			return _planCuotas;
		}
		
		public function set planCuotas(value:String):void{
			_planCuotas = value;
		}

	}
}