package com.tarjetafiel.caja.vo
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.cobranzas.negocio.Plan")]
	public class Plan
	{
		public var idPlan:Number;
        public var descripcion:String;
        public var habilitado:String;
        public var sucursal:Number;
        public var planesVersion:Array;
        public var esPlanPorDefecto:String;
	}
}