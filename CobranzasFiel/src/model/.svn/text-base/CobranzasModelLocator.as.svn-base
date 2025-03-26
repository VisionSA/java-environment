package model {
	
	import com.adobe.cairngorm.model.IModelLocator;
	import com.tarjetafiel.caja.vo.Abogado;
	import com.tarjetafiel.caja.vo.AccionVersion;
	import com.tarjetafiel.caja.vo.Cobrador;
	import com.tarjetafiel.caja.vo.Domicilio;
	import com.tarjetafiel.caja.vo.EtapaVersion;
	import com.tarjetafiel.caja.vo.Partido;
	import com.tarjetafiel.caja.vo.Plan;
	import com.tarjetafiel.caja.vo.PlanVersion;
	import com.tarjetafiel.caja.vo.Recibo;
	import com.tarjetafiel.caja.vo.TareaPendiente;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.core.Application;
	
	import vo.FiltroTarea;

	[Bindable]
	public class CobranzasModelLocator implements IModelLocator {
		
		private static var instance: CobranzasModelLocator;
		
		// Genéricos
		public var moduloSeleccionado : Object = null; /* Este objeto posee 2 atributos: url, data*/
		
		
		//URL Servlet Asignar Cobradores
		public static const URL_SERVLET : String = getContext();
		
		
		// View TareasPendietesModule
		public var listaEstadoTarea : ArrayCollection = new ArrayCollection(new Array({label:"Pendientes",data:"N"},{label:"Confirmadas",data:"S"}));		
		public var listaTareas : ArrayCollection = null;
		public var tareaSeleccionada : TareaPendiente = null;
		public var listaDetalleTareas : ArrayCollection = null;
		public var filtroBusqueda : FiltroTarea = null;
		public var esConfirmable : Boolean = false;
		public var detalleTareaSeleccionada : Object = null;
		public var listaCobradoresCambio : ArrayCollection = null;
		public var esCobradorValido : Boolean = false;
		public var cobradorSeleccionadoACambiar : Cobrador = null;
		public var cobrador : Cobrador = null;
		public var esReporte : Boolean = false;
		
				
		// View PlanModule
		public var listaPlanes : ArrayCollection = null;
		public var planSeleccionado : Plan;
		public var listaVersiones : ArrayCollection = null;
		public var versionSeleccionada : PlanVersion;
		public var listaEtapasVersion : ArrayCollection = null;
		public var listaAccionesDisponibles : ArrayCollection = null;
		public var esNuevaVersion : Boolean = false;
		public var listaEtapas : ArrayCollection = null;
		public var accionAAgregarSeleccionada : AccionVersion = null;
		public var listaConceptosCabecera : ArrayCollection = null;
		public var etapaVersionSeleccionada : EtapaVersion = null;
		public var esNuevaAccion : Boolean = false;
		public var domicilioIndividuo : Domicilio = null;
		
		
		//View Asignacion Cobradores Module
		public var listaProvincias : ArrayCollection = null;
		public var listaPartidos : ArrayCollection = null;
		public var listaCobradores : ArrayCollection = null;
		public var partidoSeleccionado : Partido = null;
		
		
		public var hBoxPaneles_vv : HBox = null;
		public var hBoxPaneles_nv : HBox = null;
		public var accionesDG : ArrayCollection = null;
		
		
		
		// View Recibos
		public var listaRecibos : ArrayCollection = null;
		public var tituloRango : String = "";
		public var rangoReciboSeleccionado : Recibo = null; 
		public var listaDetalleRecibos : ArrayCollection = null;
		public var detalleReciboSeleccionado : Recibo = null;
		public var esReciboAnulable : Boolean = false;
		
		
		
		// Abogados
		public var listaAbogados : ArrayCollection = null;
		public var esAbogadoValido : Boolean = false;
		public var listaAbogadosCambio : ArrayCollection = null;
		public var abogadoSeleccionadoACambiar : Abogado;
		public var abogadoClienteTarea : Abogado;
		
		
		
		//Funciones Generales
		public function ordenar(a:Object, b:Object):Number {
			var acc1 : AccionVersion = AccionVersion(a);
			var acc2 : AccionVersion = AccionVersion(b);
			if (acc1.dias > acc2.dias){
				return 1;
			}else if (acc1.dias < acc2.dias){
				return -1;
			}else {
				return 0;
			}
		}
		
		
		// Contructor
        public function CobranzasModelLocator(enforcer:SingletonEnforcer) {
                if (enforcer == null){
                        throw new Error("Solo se puede tener una instancia de CobranzasModelLocator");
                }               
        }

		public static function getInstance(): CobranzasModelLocator {
            if (instance == null){
                    instance =  new CobranzasModelLocator(new SingletonEnforcer());
            }
            return instance;
        }
        
        
        private static function getContext() : String {
        	
        	var url : String = Application.application.url;
        	var pattern : RegExp = /\/Cobranzas.swf.*/;
        	return url.replace(pattern,"")+"/AsignacionCobradorServlet";
        }
        

	}
}

// Utility class to deny access to the constructor
class SingletonEnforcer {}