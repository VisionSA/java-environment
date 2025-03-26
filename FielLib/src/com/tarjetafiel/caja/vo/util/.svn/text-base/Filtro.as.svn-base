package com.tarjetafiel.caja.vo.util
{
	import mx.formatters.DateFormatter;
	
	
 	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.commons.filtros.Filtro")] 	
	public class Filtro
	{
		public static const IGUAL:int = 1;
		public static const LIKE:int = 2;
		public static const MAYOR:int = 3;
		public static const MENOR:int = 4;
		public static const MAYOR_IGUAL:int = 5;
		public static const MENOR_IGUAL:int = 6;
		public static const DISTINTO:int = 7;
		public static const LIKEXS:int = 8;
		public static const NOT_LIKE:int = 9;
		public static const IN:int = 10;
		public static const NOT_IN:int = 11;
		public static const NOT_NULL:int = 12;
		public static const NULL:int = 13;
		public static const LIKE_IZQ:int = 14;
		public static const LIKE_DER:int = 15;
		
		public static const IGUAL_MANO:int = 18;
		
		public var campos:Array = new Array();
		public var valores:Array = new Array();
		public var operadores:Array = new Array();
		public var join:Array = new Array();
		public var funcion:String = "";
		public var orderBy:Array = new Array();
		public var consultaAMano:String = "";
		
		public function Filtro(){
			this.campos = new Array();
			this.valores = new Array();
			this.operadores = new Array();
			this.join = new Array();
		}
		
		public function agregarCampoOperValor(unCampo:String, unOperador:int, unValor:Object):void {
			this.campos.push(unCampo);				
			this.operadores.push(unOperador);								
			this.valores.push(unValor);
		} 
		
		public static function toDate(date:Date):String{			
			var format:DateFormatter = new DateFormatter();
			format.formatString = "DD/MM/YYYY";
			var ret:String = "TO_DATE('" + format.format(date) + "','DD/MM/YYYY')";
			return ret;
		}
				
	}
}