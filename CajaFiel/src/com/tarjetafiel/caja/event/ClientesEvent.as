package com.tarjetafiel.caja.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;

	public class ClientesEvent extends CairngormEvent
	{
		private var _filtro:Filtro;
		private var _cliente:ClienteTransaccion;
		public var paginador:Paginador;
		
		public static var BUSCAR_CLIENTES:String = "buscarClientes";
		public static var BUSCAR_CLIENTE_TITULAR:String = "buscarClienteTitular";
		public static var BUSCAR_CLIENTES_CUENTA:String = "buscarClientesCuenta";
		public static var ELIMINAR_CLIENTES:String = "eliminarClientes";
		public static var BUSCAR_LIQUIDACIONES:String = "buscarLiquidacionesCliente";
		public static var BUSCAR_LIQUIDACIONES_DETALLE:String = "buscarLiquidacionesDetalleCliente";
		public static var BUSCAR_FUTUROS_VENCIMIENTOS:String = "buscarFuturosVencimientosEvent";
		
		public var idCliente:int;
		
		public function ClientesEvent(type:String, filtro:Filtro, paginador:Paginador)
		{
			super(type);
			this._filtro = filtro;
			this.paginador = paginador;
		}
		
		public function get filtro():Filtro{
			return _filtro;
		}
		
		public function get cliente():ClienteTransaccion{
			return _cliente;
		}
		
		public function set cliente(cliente:ClienteTransaccion):void{
			this._cliente = cliente;
		}
		
	}
}