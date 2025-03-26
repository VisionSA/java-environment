package com.tarjetafiel.caja.vo
{
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen")]
	public class ConceptoGen
	{
		public var idConcepto:Number;
		/*TODO 1 - Mal definido el tipo de atributo
		 * Los Id lo definimos como tipo Long, cuando se cree corregir de la siguiente manera
		 *  public var Long idConcepto = new Long(0);
		 *  no se le agrego el constructor del on}bjeto primitivo.
		 */
		public var calculaDisponible:String;
		public var descripcion:String;
		public var sucursal:SucursalFiel;
		public var target:String;
		public var codigoConcepto:Number;
		//public var clienteConceptoSet:Array;
		//public var comercioConceptoSet:Array;
		public var conceptoDetalleSet:Array;
		//public var origenConceptoSet:Array;
		//public var transaccionSet:Array;
		public var clase:String;
		public var esFiel:String;
		
		[Transient]private var link:Object = [ConceptoDetalleGen];
	}
}