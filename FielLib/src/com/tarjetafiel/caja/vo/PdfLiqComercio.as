package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.PdfLiqComercio")]
	public class PdfLiqComercio
	{
		public var liquidacionNumero:Number;
		public var guardarEn:String;
		public var responsableComercio:String;
		public var urlImagen:String;
	    public var totalTransacciones:Number;
	    public var arancel:Number;
	    public var aceleramiento:Number;
	    public var fechaLiquidacion:String;
	    public var nombreComercial:String;
	    public var domicilioLegal:String;
		public var codigoComercio:Number;
		public var cuit:String;
	    public var categoriaIva:String;
	    public var inscIngBrutos:String;
	    public var iva:Number;
	    public var totalLiquidacion:Number;
	    public var cargosVarios:Number;
	    public var retenciones:String;
	    public var pagos:String;
	    public var periodos:String;
	}
}
