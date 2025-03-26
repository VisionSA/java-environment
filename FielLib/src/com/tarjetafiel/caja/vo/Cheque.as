package com.tarjetafiel.caja.vo
{
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.fondos.negocio.Cheque")]
	public class Cheque
	{
		public var idCheque:Number;
		public var tipo:String;
		public var sucursalBanco:String;
		public var cuenta:String;		
		public var numero:String;
		public var beneficiario:String;
		public var fechaEmision:Date;
		public var fechaPago:Date;
		public var esCruzado:String = "N";
		public var importe:Number;
		public var interbanking:String;
		public var interbankingFecha:Date;
		public var chequeEstado:ChequeEstado;
		public var movimientoMP:MovimientoMP;
		public var banco:Banco;
		public var chequeLugar:ChequeLugar;
		public var bancoPropio:BancoPropio;
		public var asientoItem:AsientoItem;
		public var codigoPostal:String;
		public var noOrden:String = "N";
		public var DV1:int = 0;
		public var DV2:int = 0;
		public var DV3:int = 0;
		public var procesado:String;
		public var conciliado : String;
	}
}