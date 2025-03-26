package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Empresa;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;

	public class EmpresaEvent extends CairngormEvent
	{
		private var _filtro:Filtro;
		private var _empresa:Empresa;
		public var paginador:Paginador;
		
		public static var BUSCAR_EMPRESA:String = "buscarEmpresa";
		
		public function EmpresaEvent(type:String, filtro:Filtro=null)
		{
			super(type);
			this._filtro = filtro;
		}
		
		public function get filtro():Filtro{
			return _filtro;
		}
		
		public function get empresa():Empresa{
			return _empresa;
		}
		
	}
}