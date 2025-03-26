package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.RetencionComercio")]
	public class RetencionComercio
	{
		public var periodo:String;
		public var descripcionRetencion:String;
		public var urlPdf:String;
		public var monto:Number;
		
		public function RetencionComercio()
		{
		}

	}
}