package com.util.relleno
{
	public class rellenarNroUtil
	{   private static var nroRellenado:String="";
		public function rellenarNroUtil()
		{
		}
		
		
		public static function rellenarAlaIzq(que:String,cantRelleno:Number):String{
		  var cadCero:String ="";
		  
		   for(var i:Number =0 ;i<(cantRelleno-que.length);i++){
		   	   cadCero+="0";
		   }
			return cadCero+que;
		}
	}	
		
   	
}