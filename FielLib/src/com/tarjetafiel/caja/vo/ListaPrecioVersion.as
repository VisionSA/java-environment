package com.tarjetafiel.caja.vo
{
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioVersion")]
	public class ListaPrecioVersion
	{
		
		public var idListaPrecioVersion:Number;
		public var itemsListaPrecio:Array; //un set de elementos ListaPrecioVersion
		public var version:Number;
		public var comArancel:Number;
		public var fechaVigencia:Date;
		public var comCiclo:Number;
		public var comDias:Number;
		public var fechaLiquidacion:String;
		public var difiereLiquidacion:String;
	}
}