package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.MovimientoCtaCteComercio")]
	public class MovimientoCtaCteComercio
	{
		public var idTransaccion:Number;
		public var fechaReal:Date;
		public var fechaFacturacion:Date;
	    public var fechaContable:Date;
		public var descripcionConcepto:String;
		public var codigoConcepto:Number;
		public var saldoAcumulado:Number;
		public var idTipoConcDetalle:Number;
		
		public var estadoImpacto:String;
		public var debe:Number;
		public var haber:Number;
		public var importe:Number;
		
/*@F8271*/		public var idLiquidacion:String;
		public var descripcionLiquidacion:String;
		public var idSucursal:Number;
		public var descripcionSucursal:String;
/*@F8271*/		public var idOrigen:String;
		public var signo:Number;
		
		public var idCodComercio:Number;
		public var idCtaCteComercio:Number;
		public var timestamp:Date;
		
		public var idConcepto:Number;
		//propiedad extra para mostrar saldo flex
		public var saldo:Number;
		private var _idLiqComercio:Number;
		public var estado:String;
		
		 public function set idLiqComercio(liq:Number):void{
			_idLiqComercio = liq;
			if(_idLiqComercio){
			  estado= "Facturado";
			}else{  
			  estado= "Pendiente de facturar";
			  _idLiqComercio = 0;
			} 
			dispatchEvent(new Event("changedIdLiqCLiente"));
	    }  	
	  
	  [Bindable (event="changedIdLiqCLiente")]
	  public function get idLiqComercio():Number{
			return _idLiqComercio;
	  }
	}
}