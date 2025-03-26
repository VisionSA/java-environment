package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Cheque;

	public class ChequeEvent extends CairngormEvent
	{
		public static const EXISTE_CHEQUE:String = "existeCheque";
		
		public static const LIST_CHEQUES_EN_CAJA:String = "listChequesEnCaja"; 
		
		public var functionExisteCheque:Function;
		public var cheque:Cheque;
		
		public function ChequeEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}