package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.util.Filtro;

	public class BancoEvent extends CairngormEvent
	{		
		
		public static var BUSCAR_BANCOS:String = "buscarBancos";
		public static var BUSCAR_BANCOS_PROPIOS:String = "buscarBancosPropios";
		
		
		public function BancoEvent(type:String)
		{
			super(type);			
		}
		
	
		
		
		
	}
}