package managers
{
	import com.tarjetafiel.caja.vo.Concepto;
	import com.tarjetafiel.caja.vo.ConceptoGen;
	import com.tarjetafiel.caja.vo.SucursalFiel;
	import com.util.components.alert.AlertError;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.Fault;
	
	[Bindable]
	public class ConceptosManager
	{
		
		public var arrayConceptos:ArrayCollection = new ArrayCollection();
		public static const COBRANZAS:Number = 205;
		public static const COBRANZAS_FONDO:Number = 410;
		public static const REPACTACION:Number = 201;
		public static const REFINANCIACION:Number = 211;
		public static const ADELANTO_EFECTIVO:Number = 380;
		public static const ADELANTO_FONDO_EFECTIVO:Number = 411;
		public static const CIERRE:Number = 401;
		 
		public function ConceptosManager()
		{
		}
		
		public function getConcepto(codigo:Number):Concepto{
			for each(var con:Concepto in arrayConceptos){
				if(con.codigoConcepto == codigo){
					return con;
				}
			}
			
			return null;
		}
		
		public function getConceptoGen(codigo:Number):ConceptoGen{
			var concepto:Concepto;
			var conceptoGen:ConceptoGen = new ConceptoGen();
			for each(var con:Concepto in arrayConceptos){
				if(con.codigoConcepto == codigo){
					concepto = con;
				}
			}
			
			if(concepto != null){
				conceptoGen.calculaDisponible = concepto.calculaDisponible;
				conceptoGen.clase = concepto.clase;
				conceptoGen.codigoConcepto = concepto.codigoConcepto;
				conceptoGen.descripcion = concepto.descripcion;
				conceptoGen.esFiel = concepto.esFiel;
				conceptoGen.idConcepto = concepto.idConcepto;
				conceptoGen.target = concepto.target;
				conceptoGen.sucursal = new SucursalFiel();
				conceptoGen.sucursal.idSucursal = concepto.sucursal.idSucursal;
				conceptoGen.sucursal.nombre = concepto.sucursal.nombre;
				return conceptoGen;
			}
			
			return null;
		}
		
		public function resultConceptos(result:Array):void{
			this.arrayConceptos = new ArrayCollection(result);
		}
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultString);
		}

	}
}