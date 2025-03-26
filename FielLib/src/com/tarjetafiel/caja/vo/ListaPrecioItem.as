package com.tarjetafiel.caja.vo
{
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioItem")]
	public class ListaPrecioItem
	{	public var idListapreciosItem:Number;
	    public var comCuotas:Number;		
		public var listaPrecioVersion:ListaPrecioVersion;
		public var comDiasDiferimiento:Number;
		public var comTNA:Number;
		public var comPorcentajeDirecto:Number;
		public var cliDiasDiferimiento:Number;
		public var cliTNA:Number;
		public var cliTEA:Number;
		public var comTEA:Number;
		public var nroCuotaFiel:Number;
		public var diasDiferimientoFiel:Number;
		public var tnaXCuotaFiel:Number;
		public var temXCuotaFiel:Number;
		public var comTem:Number;
		public var comFactor:Number;
		public var comCoeficiente:Number;
	}
}