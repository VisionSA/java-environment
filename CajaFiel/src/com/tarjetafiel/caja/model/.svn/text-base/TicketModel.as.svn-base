package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.view.caja.UltimoTicketView;
	import com.tarjetafiel.caja.vo.RespuestaImpresion;
	
	import flash.display.DisplayObject;
	
	import mx.core.Application;
	import mx.managers.PopUpManager;
	
	[Bindable]
	public class TicketModel
	{
		
		public var ultimaRespuestaImpresion:RespuestaImpresion;
		
		private var popUpUltimoTicket:UltimoTicketView = new UltimoTicketView();	
		
		public function TicketModel()
		{
		}
		
		public function imprimirUltimoTicket():void{			
			PopUpManager.addPopUp(popUpUltimoTicket,Application.application as DisplayObject,true);
			PopUpManager.centerPopUp(popUpUltimoTicket);
			popUpUltimoTicket.txtTicket.text = ultimaRespuestaImpresion.ticket; 
		}

	}
}