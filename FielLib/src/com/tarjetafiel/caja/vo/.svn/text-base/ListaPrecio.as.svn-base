package com.tarjetafiel.caja.vo
{
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecio")]
	public class ListaPrecio
	{
		public var versionesAnteriores:Array;
		public var versionVigente:ListaPrecioVersion;
		public var versionesFuturas:Array;
		
	    public var idListaprecios:Number;
	    //public var sucursal:SucursalFiel;
	    public var descripcion:String;
	    public var activo:String;
	    public var fechaUltimaLiquidacion:Date; //indica la fecha de cierre del ultimo periodo liquidado
	    //una cadena de errores. Logramos con esto que sea la propia clase la que decida si esta bien formada por medio de un metodo validar
	    //En el caso de que no, podemos llenar la cadena errores con mensajes, para que lo acceda la clase que quiso validar la estructura
	    public var errores:String;
	    public var versionesListaPrecio:Array; //un set de elementos ListaPrecioVersion
	}
}