package com.tarjetafiel.caja.business.transacciones
{
	import mx.collections.ArrayCollection;
	
	public class PosnetParser
	{
					
		public static const CONSULTA:int = 1;
		public static const TRANSACCION:int = 2;
		public static const CONSUMOS:int = 3;
	   
		public static var tipoUltimaTransaccion:int;
		 
		private var _tokens:String;
		private static var relleno:String;
		
		public static const ADELANTO:String = "380";
		public static const PAGOS:String = "205";
		public static const CONSUMO:String = "861";
		
		//13 - 13
		private var _comercio:String = "0000000000000";
		//11 - 24
		private var _carRes:String = "00000000000";
		//4 - 28
		private var _cupon:String = "0000"	
		/**
		 * 380 = Adelanto Efectivo
		 * 111 = Debito
		 * 205 = Pagos 
		 */
		 //3 - 31
		private var _tipoReg:String = "380"
		//20 - 51
		private var _tarjeta:String = "00000000000000000000";
		//6 - 57
		private var _fecha:String = "000000";
		//2 - 59
		private var _cuotas:String = "00";
		
		//2:caja 4:consumo
		private var _origen:String ="4"; 
		
		/**
		 * Ceros a la izquierda
		 * Dos ultimos digitos son decimales
		 */
		//13 - 72
		private var _importe:String = "000000000000000";
		//13 - 85
		private var _importeDesc:String = "000000000000000"
		//8 - 93
		private var _codAut:String = "00000000";
		//3 - 96
		/**
		 * 032 = Pesos
		 * 840 = Dolares
		 * 858 = Pesos Uruguayos
		 */
		private var _codMoneda:String = "032";
		//6 - 102
		private var _hora:String = "000000";
		//9 - 111
		private var _pin:String = "0";
		//40 - 151
		private var _track2:String = "0000000000000000000000000000000000000000";
		//4 - 155
		private var _cuponOrigDev:String = "0000";
		//4 - 159
		private var _fechaOrigDev:String = "0000";
		//1 - 160
		private var _indCinting:String = "0";
		//1 - 161
		private var _anulacion:String = "0";
		//3 - 164
		private var _codRespuesta:String = "000";
		//1 - 165
		private var _planCuotas:String = "6";
		//1 - 166
		private var _acctTyp:String = "0";
		//1 - 167
		private var _acctSource:String = "0";
		//1 - 168
		private var _termTyp:String = "0";
		//4 - 172
		private var _privatee:String = "0000";
		
		private  var  _conciliarTransaccion :String = "P";
		
		
		
		public function get origen():String{
			return _origen;
		}
		
		public function set origen(ori:String):void{
			_origen = ori;
		}
		
		
		public function get comercio():String{
			return _comercio;
		}
		
		public function set comercio(str:String):void{
			
			_comercio = "0000000000000";
			if(str.length < 13){
				_comercio = _comercio.substr(0,_comercio.length - str.length) + str;	
			} else {
				
				if(str.length > 13)
					str = str.substr(0, 13); 
				_comercio = str;
			}					
		}
		
		public function get carRes():String{
			return _carRes;
		}
		
		public function set carRes(str:String):void{
			
			_carRes = "00000000000";
			if(str.length < 11){
				_carRes = _carRes.substr(0,_carRes.length - str.length) + str;	
			} else {
				if(str.length > 11)
					str = str.substr(0, 11);
				_carRes = str;
			}					
		}
		
		public function get cupon():String{
			return _cupon;
		}
		
		public function set cupon(str:String):void{
			
			_cupon = "0000";
			if(str.length < 4){
				_cupon = _cupon.substr(0,_cupon.length - str.length) + str;	
			} else {
				if(str.length > 4)
					str = str.substr(0, 4);
				_cupon = str;
			}					
		}
		
		public function get tipoReg():String{
			return _tipoReg;
		}
		
		public function set tipoReg(str:String):void{
			
			_tipoReg = "000";
			if(str.length < 3){
				_tipoReg = _tipoReg.substr(0,_tipoReg.length - str.length) + str;	
			} else {
				if(str.length > 3)
					str = str.substr(0, 3);
				_tipoReg = str;
			}					
		}
		
		public function get tarjeta():String{
			return _tarjeta;
		}
		
		public function set tarjeta(str:String):void{
			
			_tarjeta = "00000000000000000000";
			if(str.length < 20){
				_tarjeta = str + _tarjeta.substr(0,20 - str.length);	
			} else {
				if(str.length > 20)
					str = str.substr(0, 20);				
				_tarjeta = str;
			}					
		}
		
		public function get fecha():String{
			return _fecha;
		}
		
		public function set fecha(str:String):void{
			
			_fecha = "000000";
			if(str.length < 6){
				_fecha = _fecha.substr(0,_fecha.length - str.length) + str;	
			} else {
				if(str.length > 6)
					str = str.substr(0, 6);
				_fecha = str;
			}					
		}
		
		public function get cuotas():String{
			return _cuotas;
		}
		
		public function set cuotas(str:String):void{
			
			_cuotas = "00";
			if(str.length < 2){
				_cuotas = _cuotas.substr(0,_cuotas.length - str.length) + str;	
			} else {
				if(str.length > 2)
					str = str.substr(0, 2);
				_cuotas = str;
			}					
		}
		
		public function get importe():String{
			return _importe;
		}
		
		public function set importe(str:String):void{
			
			_importe = "000000000000000";
			if(str.length < 15){
				_importe = _importe.substr(0,_importe.length - str.length) + str;	
			} else {
				if(str.length > 15)
					str = str.substr(0, 15);
				_importe = str;
			}					
		}
		
		public function get importeDesc():String{
			return _importeDesc;
		}
		
		public function set importeDesc(str:String):void{
			
			_importeDesc = "000000000000000";
			if(str.length < 15){
				_importeDesc = _importeDesc.substr(0,_importeDesc.length - str.length) + str;	
			} else {
				if(str.length > 15)
					str = str.substr(0, 15);
				_importeDesc = str;
			}					
		}
		
		public function get codAut():String{
			return _codAut;
		}
		
		public function set codAut(str:String):void{
			
			_codAut = "00000000";
			if(str.length < 8){
				_codAut = _codAut.substr(0,_codAut.length - str.length) + str;	
			} else {
				if(str.length > 8)
					str = str.substr(0, 8);
				_codAut = str;
			}					
		}
		
		public function get codMoneda():String{
			return _codMoneda;
		}
		
		public function set codMoneda(str:String):void{
			
			_codMoneda = "000";
			if(str.length < 3){
				_codMoneda = _codMoneda.substr(0,_codMoneda.length - str.length) + str;	
			} else {
				if(str.length > 3)
					str = str.substr(0, 3);
				_codMoneda = str;
			}					
		}		
		
		public function get hora():String{
			return _hora;
		}
		
		public function set hora(str:String):void{
			
			_hora = "000000";
			if(str.length < 6){
				_hora = _hora.substr(0,_hora.length - str.length) + str;	
			} else {
				if(str.length > 6)
					str = str.substr(0, 6);
				_hora = str;
			}					
		}
		
		public function get pin():String{
			return _pin;
		}
		
		public function set pin(str:String):void{
			
			_pin = "0";
			if(str.length < 1){
				_pin = _pin.substr(0,_pin.length - str.length) + str;	
			} else {
				if(str.length > 1)
					str = str.substr(0, 1);
				_pin = str;
			}					
		}
		
		public function get track2():String{
			return _track2;
		}
		
		public function set track2(str:String):void{
			
			_track2 = "0000000000000000000000000000000000000000";
			if(str.length < 40){
				_track2 = _track2.substr(0,_track2.length - str.length) + str;	
			} else {
				if(str.length > 40)
					str = str.substr(0, 40);
				_track2 = str;
			}					
		}
		
		public function get cuponOrigDev():String{
			return _cuponOrigDev;
		}
		
		public function set cuponOrigDev(str:String):void{
			
			_cuponOrigDev = "0000";
			if(str.length < 4){
				_cuponOrigDev = _cuponOrigDev.substr(0,_cuponOrigDev.length - str.length) + str;	
			} else {
				if(str.length > 4)
					str = str.substr(0, 4);
				_cuponOrigDev = str;
			}					
		}
		
		public function get fechaOrigDev():String{
			return _fechaOrigDev;
		}
		
		public function set fechaOrigDev(str:String):void{
			
			_fechaOrigDev = "0000";
			if(str.length < 4){
				_fechaOrigDev = _fechaOrigDev.substr(0,_fechaOrigDev.length - str.length) + str;	
			} else {
				if(str.length > 4)
					str = str.substr(0, 4);
				_fechaOrigDev = str;
			}					
		}
		
		public function get indCinting():String{
			return _indCinting;
		}
		
		public function set indCinting(str:String):void{
			
			_indCinting = "0";
			if(str.length < 1){
				_indCinting = _indCinting.substr(0,_indCinting.length - str.length) + str;	
			} else {
				if(str.length > 1)
					str = str.substr(0, 1);
				_indCinting = str;
			}					
		}
		
		public function get anulacion():String{
			return _anulacion;
		}
		
		public function set anulacion(str:String):void{
			
			_anulacion = "0";
			if(str.length < 1){
				_anulacion = _anulacion.substr(0,_anulacion.length - str.length) + str;	
			} else {
				if(str.length > 1)
					str = str.substr(0, 1);				
				_anulacion = str;
			}					
		}
		
		public function get codRespuesta():String{
			return _codRespuesta;
		}
		
		public function set codRespuesta(str:String):void{
			
			_codRespuesta = "000";
			if(str.length < 3){
				_codRespuesta = _codRespuesta.substr(0,_codRespuesta.length - str.length) + str;	
			} else {
				if(str.length > 3)
					str = str.substr(0, 3);
				_codRespuesta = str;
			}					
		}
		
		public function get planCuotas():String{
			return _planCuotas;
		}
		
		public function set planCuotas(str:String):void{
			
			_planCuotas = "0";
			if(str.length < 1){
				_planCuotas = _planCuotas.substr(0,_planCuotas.length - str.length) + str;	
			} else {
				if(str.length > 1)
					str = str.substr(0, 1);
				_planCuotas = str;				
			}					
		}
		
		public function get acctTyp():String{
			return _acctTyp;
		}
		
		public function set acctTyp(str:String):void{
			
			_acctTyp = "0";
			if(str.length < 1){
				_acctTyp = _acctTyp.substr(0,_acctTyp.length - str.length) + str;	
			} else {
				if(str.length > 1)
					str = str.substr(0, 1);
				_acctTyp = str;
			}					
		}
		
		public function get acctSource():String{
			return _acctSource;
		}
		
		public function set acctSource(str:String):void{
			
			_acctSource = "0";
			if(str.length < 1){
				_acctSource = _acctSource.substr(0,_acctSource.length - str.length) + str;	
			} else {
				if(str.length > 1)
					str = str.substr(0, 1);
				_acctSource = str;
			}					
		}
		
		public function get termTyp():String{
			return _termTyp;
		}
		
		public function set termTyp(str:String):void{
			
			_termTyp = "0";
			if(str.length < 1){
				_termTyp = _termTyp.substr(0,_termTyp.length - str.length) + str;	
			} else {
				if(str.length > 1)
					str = str.substr(0, 1);
				_termTyp = str;
			}					
		}
		
		public function get privatee():String{
			return _privatee;
		}
		
		public function set privatee(str:String):void{
			
			_privatee = "0000";
		 	if(str.length < 4){
				_privatee = _privatee.substr(0,_privatee.length - str.length) + str;	
			} else {
				if(str.length > 4)
					str = str.substr(0, 4);
				_privatee = str;
			}					
		}
		
		public function get conciliarTransaccion():String{
			return _conciliarTransaccion;
		}
		
		public function set conciliarTransaccion(str:String):void{
			_conciliarTransaccion = str;
		}
		
		
		
		
		public function PosnetParser()		
		{				
		}			
		
		/**
		 * tipo = 1 Consulta de cuotas
		 * tipo = 2 Ejecucion Transaccion 
		 **/
		public function formatToken(tipo:int):String{
			
			if(tipo == CONSULTA){
				relleno  = "00000000000200000000000000000001";
			} else { 
				if(tipo==CONSUMOS){
				    // relleno  = "00000000000{%origen%}00000000000000000000";
				     relleno  = "00000000000"+origen+"00"+conciliarTransaccion+"00000000000000000";
				}  
				 else relleno  = "00000000000200000000000000000002";
			}
			
			trace(_comercio)
			trace(_carRes)
			trace(_cupon)
			trace(_tipoReg)
			trace(_tarjeta)
			trace(_fecha)
			trace(_cuotas)
			trace(_importe)
			trace(_importeDesc)
			trace(_codAut)
			trace(_codMoneda)
			trace(_hora)
			trace(_pin)
			trace(_track2)
			trace(_cuponOrigDev)
			trace(_fechaOrigDev)
			trace(_indCinting)
			trace(_anulacion)
			trace(_codRespuesta)
			trace(_planCuotas)
			trace(_acctTyp)
			trace(_acctSource)
			trace(_termTyp)
			trace(_privatee)
			
			this._tokens = 
						   this._comercio +
						   this._carRes +
						   this._cupon +
						   this._tipoReg +
						   this._tarjeta +
						   this._fecha +
						   this._cuotas +
						   this._importe +
						   this._importeDesc +
						   this._codAut +
						   this._codMoneda +
						   this._hora +
						   this._pin +
						   this._track2 +
						   this._cuponOrigDev +
						   this._fechaOrigDev +
						   this._indCinting +
						   this._anulacion +
						   this._codRespuesta +
						   this._planCuotas +
						   this._acctTyp +
						   this._acctSource +
						   this._termTyp +
						   this._privatee; 			
			
			this.rellenarToken();
			
			 _tokens = "111" + "0200" + _tokens;
			
			trace("Lenght Token: " + _tokens.length);
			trace("Token: " + _tokens);
			
			
						 
			
			
			return _tokens;
			
		}
		
		private function rellenarToken():void {
			var i:int = 200 - _tokens.length;		    
		    _tokens += relleno.substring(relleno.length-i,relleno.length);
		}
		
		public static function getError(array:ArrayCollection):String{
			if(array.length>1)array.removeItemAt(0);						
			var rpt:String = "";
			for each(var msj:Object in array){
				rpt += msj;	
			}
			
			return rpt == null ? "Error no definido" : rpt;			
		}
		
		

	}
}