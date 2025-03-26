package com.util
{
	public class Fecha
	{
		
		public static const millisecondsPerMinute:int = 1000 * 60;
        public static const millisecondsPerHour:int = 1000 * 60 * 60;
        public static const millisecondsPerDay:int = 1000 * 60 * 60 * 24; 
		
		public function Fecha()
		{
		}
		
		public static function agregarDias(date:Date,cant:int):Date{
		  	return new Date(date.getTime() + (cant * millisecondsPerDay)); 
		}
		
		public static function quitarDias(date:Date,cant:int):Date{
		   return 	new Date(date.getTime() - (cant * millisecondsPerDay)); 
		}

	}
}