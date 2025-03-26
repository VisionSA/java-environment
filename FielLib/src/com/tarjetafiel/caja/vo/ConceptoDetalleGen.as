package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.ConceptoDetalleGen")]
	public class ConceptoDetalleGen
	{
		public var idConceptoDetalle:Number;
		/*TODO 1 - Mal definido el tipo de atributo
		 * Los Id lo definimos como tipo Long, cuando se cree corregir de la siguiente manera
		 *  public var Long idConceptoDetalle = new Long(0);
		 *  no se le agrego el constructor del on}bjeto primitivo.
		 */
		public var activo:String;
		public var ctacontabledebe:Number;
		public var ctacontablehaber:Number;
		public var fechavigenciadesdeFlex:Date;
		public var fechavigenciahastaFlex:Date;
		public var concepto:ConceptoGen;
		public var nombre:String;
		public var orden:Number;
		/*TODO Mal definido el tipo de dato
		 * le falta el constructor del objeto cuando se definio
		 * public var Integer orden = new Integer(0);
		 */
		public var parent:Number;
		public var prioridad:Number;
		public var prioridadImputacion:Number;
		public var signo:Number;
		/*TODO Mal definido el tipo de dato
		 * le falta el constructor del objeto cuando se definio
		 * public var Integer signo = new Integer(0);
		 */
		public var tipo:Number;
		public var impacta:String; // es para saber si el detalle de concepto impacta en cuotas = "C" o en general (que seria una sola cuota) "G"
		//public var Set conceptoDetalleReglaSet;
		//public var Set conceptoDetalleTargetSet;
		//public var Set ctaCteClienteSet;
		//public var Set ctaCteComercioSet;
	}
}