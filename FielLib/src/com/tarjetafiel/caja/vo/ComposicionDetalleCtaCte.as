package com.tarjetafiel.caja.vo
{
	import flash.events.EventDispatcher;
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.ComposicionDetalleCtaCte")]
	public class ComposicionDetalleCtaCte extends EventDispatcher
	{
		public var nroCuota:Number;
		private var _idLiqCliente:Number;
		public var importeCuota:Number;
		public var fechaFacturacion:Date; 
		public  var estado:String;
/*@I8271*/	public var idCliente:Number;
			public var estadoCliente:String;
			public var estadoComercio:String;
			public  var apellidoNombre:String;
			public  var nombreConcepto:String;
/*@F8271*/	public var nroPlastico:String;
		
	public function set idLiqCliente(liq:Number):void{
			_idLiqCliente = liq;
			if(_idLiqCliente){
			  estado= "Facturado";
			}else{  
			  estado= "Pendiente de facturar";
			  _idLiqCliente = 0;
			} 
			dispatchEvent(new Event("changedIdLiqCLiente"));
	    }  	
	  
	  [Bindable (event="changedIdLiqCLiente")]
	  public function get idLiqCliente():Number{
			return _idLiqCliente;
	  }
	
		
	}
}