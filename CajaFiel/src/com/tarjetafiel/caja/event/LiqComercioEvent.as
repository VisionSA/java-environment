package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.LiqComercioLP;
	import com.tarjetafiel.caja.vo.util.Filtro;

	public class LiqComercioEvent extends CairngormEvent
	{
		private var _filtro:Filtro;					
		public var liqComercioLp:LiqComercioLP;
		
				
		public static var BUSCAR_LIQ_COMERCIO:String = "buscarLiqComercio";
		public static var BUSCAR_DETALLES_LIQ:String = "buscarDetalleLiqComercio";
		
		
		public function LiqComercioEvent(type:String, filtro:Filtro, liqComercioLp:LiqComercioLP=null)
		{
			super(type);			
			this._filtro = filtro;
			this.liqComercioLp = liqComercioLp;
		}
		
		public function get filtro():Filtro{
			return _filtro;
		}
						
	}
}