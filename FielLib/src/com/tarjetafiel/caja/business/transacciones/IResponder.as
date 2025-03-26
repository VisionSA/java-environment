package com.tarjetafiel.caja.business.transacciones
{
	public interface IResponder
	{
		function responderPosnetParser(result:String):void;
		function responderErrorPosnetParser(result:Array):void;
		function responderConsultaPosnetParser(result:Array):void;
		function responderOkPosnetParser(result:Array):void;
	}
}