package com.tarjetafiel.caja.business.transacciones
{
	import mx.collections.ArrayCollection;
	
	public class ResponseTransParser
	{
					
		 
		private var _token:String;
		//0 - 3
		private var _version:String ;
		//3 -7 
	    private var _longPaquete:String;
		//7 - 11	
		private var _tipoMensaje:String ;
		//11 - 17
		private var _codigoProcesamiento:String ;
		//17 - 19
		private var _codigoRespuesta:String ;
		//19 - 25
		private var _codigoAutorizacion:String ;
		//25 - 30
		private var _rnn:String;
		//30 - 70 
		private var _mensajeRespuesta:String ; 
		
		
		public function ResponseTransParser(resp: String){
			this.token= resp;
		}
		
		
		
		public function get token():String{
			return _token;
		}
		
		public function set token(token:String):void{
			_token=  token;
		}
	   
	   	public function get version():String{
			return token.substring(0,3);
		}
		
		public function get longPaquete():String{
			return token.substring(3,7);
		}
		
		 public function get tipoMensaje():String{
			return "";
		}
		
		 public function get codigoProcesamiento():String{
			return "";
		}
		
		 public function get codigoRespuesta():String{
			return token.substring(18,20);
		}
		
		public function get codigoAutorizacion():String{
			return token.substring(9,15);
		}
		
		public function get rnn():String{
			return "";
		}
		
		public function get mensajeRespuesta():String{
			return token.substring(15,70);
		}

	}
}