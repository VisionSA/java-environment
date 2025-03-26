package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.vo.Caja;
	import com.tarjetafiel.caja.vo.CajaApertura;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	public class CajaModel extends EventDispatcher
	{
		[Bindable]public var arrayFormaPagoCaja:ArrayCollection = new ArrayCollection();
		[Bindable]public var caja:Caja;
		[Bindable]private var _cajaApertura:CajaApertura;
		public function CajaModel()
		{
		}
		
		[Bindable (event="changedCajaApertura")]
		public function get cajaApertura():CajaApertura{
			return _cajaApertura;
		}
		
		
		public function set cajaApertura(apertura:CajaApertura):void{
			_cajaApertura = apertura;
			_cajaApertura.operador = ModelLocator.getInstance().operadorModel.operador;
			caja = _cajaApertura.caja;
			dispatchEvent(new Event("changedCajaApertura"));
		}

	}
}