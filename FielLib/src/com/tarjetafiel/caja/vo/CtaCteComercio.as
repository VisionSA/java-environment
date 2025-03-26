package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.CtaCteComercio")]
	public class CtaCteComercio
	{
		public var idCtacteComercio:Number;
		public var contabilizado:String;
		public var ctacontabledebe:String;
		public var ctacontablehaber:String;
		public var estadoImpacto:String;
		public var fechaContable:Date;
		public var fechaFacturacion:Date;
		public var fechaLote:Date;
		public var conceptoDetalle:ConceptoDetalle;
		public var liqComercio:LiqComercio;
		public var idLoteComercio:Number;
		public var idOperador:Number;
		public var idOrigen:Number;
		public var idParent:Number;
		public var idTranascciones:Number;
		public var importe:Number;
		public var motivoImpacto:Number;
		public var nroCuota:Number;
		/*TODO Mal definido el tipo de dato
		 * le falta el constructor del objeto cuando se definio
		 * public var Integer nroCuota = new Integer(0);
		 */
		public var nroOrigen:String;
		public var signo:Number;
		/*TODO Mal definido el tipo de dato
		 * le falta el constructor del objeto cuando se definio
		 * public var Integer signo = new Integer(0);
		 */
		public var timestamp:Date = null;
		public var codComercio:CodComercio;
		
		public var sucEmpresa:SucEmpresa;
		public var saldo:Number;
		
	}
}