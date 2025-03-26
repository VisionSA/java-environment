package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.MovimientoCtaCte")]
	public class MovimientoCtaCte 
	{
		public var numeroFila:int;
		public var idTransaccion:Number;
		public var fechaReal:Date;
		public var fechaFacturacion:Date;
	    public var fechaContable:Date;
		public var sucDescripcion:String; 
		public var importeCuota:Number;
		public var debe:Number;
		public var haber:Number;
		public var cantidadCuotas:Number;
		public var saldoAcumulado:Number; 
		public var esImpuesto:String; 
		public var  descripcionConcepto:String; 
	    public var codigoConcepto:Number;
	    public  var  importeTotCuotas:Number;
	    public  var  idTipoConcDetalle:Number;
	   //para composicion de saldo  
	   public var nroCuota:Number;
	   private var _idLiqCliente:Number;
	   public  var esTitular:Boolean;  
	   public var clienteMovimiento:String; // cliente q realizo  el  m~ovimiento
	   public var  comercioMoviento:String; // comercio donde se realizo el movimiento
	   public var idCtaCte:Number;
	    public var  descripcionConceptoDetalle:String;
	    public var cuota:String;
       public var estado:String;
       public var estadoImpacto:String;
	   public var color:Number;
	   /*@F7182*/	public var descripcion_movimiento:String;
       
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