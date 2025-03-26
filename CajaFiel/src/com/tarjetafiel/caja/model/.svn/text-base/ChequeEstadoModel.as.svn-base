package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.vo.ChequeEstado;
	
	import mx.collections.ArrayCollection;
	
	public class ChequeEstadoModel
	{
		[Bindable]public var chequeEstadosList:ArrayCollection = new ArrayCollection();
		
		public static const ANULADO:Number = 4;
		
		public static const CARTERA:Number = 7;
		
		public function ChequeEstadoModel()
		{
		}
		
		/**
		 *Estados 
		 *    	 4 = Anulado
		 * 		 7 = En cartera
		 **/ 
		public function getChequeEstado(id:Number):ChequeEstado{
			for each(var estado:ChequeEstado in chequeEstadosList){
				if(estado.idChequeEstado == id){
					return estado;
				}
			}
			
			return null;
		}

	}
}