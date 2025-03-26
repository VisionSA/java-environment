package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.CtaCteClienteAux")]
	public class CtaCteClienteAux
	{
		public var idCtacteCliente:Number;
		public var comprobante:String;
		public var contabilizado:String;
		public var ctacontabledebe:String;
		public var ctacontablehaber:String;
		public var estadoImpacto:String;
		public var fechaContable:Date;
		public var fechaFacturacion:Date;
		public var fechaFacturacionHasta:Date;		
		public var conceptoDetalle:ConceptoDetalle;		
		public var idOperador:Number;
		public var idOrigen:Number;
		public var idParent:Number;
		public var idTranascciones:Number;
		public var importe:Object;
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
	}
}