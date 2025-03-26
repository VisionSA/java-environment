package com.fiel.caja.judicial.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Cheque;

	public class ChequeEvent extends CairngormEvent
	{
		public static const EXISTE_CHEQUE:String = "existeCheque";		
		public static const LIST_CHEQUES_EN_CAJA:String = "listChequesEnCaja"; 
		public static const BUSCAR_CHEQUE_EVENT : String = "BuscarChequeEvent";
		public static const BUSCAR_ESTADO_CHEQUE_EVENT : String = "BuscarEstadoChequeEvent";
		public static const AGREGAR_CHEQUE_EVENT : String = "AgregarChequeEvent";
		public static const BUSCAR_CHEQUE_ESTADO_POR_ID_CHEQUE_ESTADO_EVENT : String = "BuscarChequeEstadoPorIdChequeEstadoEvent";
		public static const BUSCAR_CHEQUE_PROPIO_EVENT : String = "BuscarChequePropioEvent";
		public static const AGREGAR_DEPOSITO_EVENT : String = "AgregarDepositoEvent";
		public static const BUSCAR_CHEQUE_ESTADO_BY_ID_EVENT : String = "BuscarChequeEstadoByIdEvent";
		
		
		public function ChequeEvent(type:String, param:Object=null)
		{
			super(type);
			this.data = param;
		}
		
	}
}