package model {
	
	import business.ConciliacionFondoCabecera;
	
	import com.adobe.cairngorm.model.IModelLocator;
	import com.tarjetafiel.caja.vo.BancoPropio;
	
	import mx.collections.ArrayCollection;
	import mx.formatters.NumberFormatter;
	
	[Bindable]
	public class ConciliacionModelLocator implements IModelLocator {
		
		private static var instance: ConciliacionModelLocator;
		
		// Genéricos
		public var listaBancoPropio:ArrayCollection;
		
		public var listaExtracto:ArrayCollection;
		public var listaMovimiento:ArrayCollection;
		public var listaConciliable:ArrayCollection  = new ArrayCollection();
		
		public var cabeceraSeleccionada:ConciliacionFondoCabecera;
		public var cabeceraNueva:ConciliacionFondoCabecera;
		
		public var listaCabeceraConciliable:ArrayCollection = new ArrayCollection();
		public var listaConcTemp:ArrayCollection = new ArrayCollection();
		
		public var concTempVisible:Boolean = false;
		public var btnGrabarConcVisible:Boolean = false;
		
		public var codigoOperador:Number;
		public var bancoPropioSeleccionado:BancoPropio;
		
		public var listaAcreditacionesCargadas = new ArrayCollection();
		
		public var btnSeleccionarTodoSel:Boolean = false;
		
		public var listaCabeceraReversion:ArrayCollection = new ArrayCollection();
		
		public var saldoBancoAFechaCorte:Number=0;
		public var movimientosNoConciliadosBancoAFechaCorte:Number=0;
		public var movimientosNoConciliadosContabilidadAFechaCorte:Number=0;
		public var saldoContableCalculadoAFechaCorte:Number=0;
		public var saldoContableRealMayorAFechaCorte:Number=0;
		public var diferenciaMovimientosConciliados:Number=0;
		
		
		public var saldoBancoAFechaCorteString:String;
		public var movimientosNoConciliadosBancoAFechaCorteString:String;
		public var movimientosNoConciliadosContabilidadAFechaCorteString:String;
		public var saldoContableCalculadoAFechaCorteString:String;
		public var saldoContableRealMayorAFechaCorteString:String;
		public var diferenciaMovimientosConciliadosString:String;
		
/*@I3918*/		public var habilitarFiltrosParaConcicliacion:Boolean = false;
/*@F3918*/		
		//Esta bandera se utiliza para actualizar la lista de revertidos cada vez que cambie el valor.
		public var refreshListRevertidos:Boolean = false;
		//Funciones Generales
		/* public function ordenar(a:Object, b:Object):Number {
			var acc1 : AccionVersion = AccionVersion(a);
			var acc2 : AccionVersion = AccionVersion(b);
			if (acc1.dias > acc2.dias){
				return 1;
			}else if (acc1.dias < acc2.dias){
				return -1;
			}else {
				return 0;
			}
		} */
		
		public var numberFormat:NumberFormatter = new NumberFormatter();
		// Contructor
        public function ConciliacionModelLocator(enforcer:SingletonEnforcer) {
                if (enforcer == null){
                        throw new Error("Solo se puede tener una instancia de ConciliacionModelLocator");
                }               
        }

		public static function getInstance(): ConciliacionModelLocator {
            if (instance == null){
                    instance =  new ConciliacionModelLocator(new SingletonEnforcer());
            }
            return instance;
        }
        
        public function formatearNumero(item:Number):String{
        		numberFormat.precision=2;
				return numberFormat.format(item).toString();
		}
	}
}

// Utility class to deny access to the constructor
class SingletonEnforcer {}