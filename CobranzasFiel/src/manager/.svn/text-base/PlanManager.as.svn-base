package manager
{
	import com.tarjetafiel.caja.vo.Etapa;
	import com.tarjetafiel.caja.vo.EtapaVersion;
	import com.tarjetafiel.caja.vo.Plan;
	import com.tarjetafiel.caja.vo.PlanVersion;
	
	import events.PlanEvent;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	[Bindable]
	public class PlanManager extends ManagerGenerico
	{
		
		public var planesArray:ArrayCollection;
		public var conceptosArray:ArrayCollection;
		
		public var resultadoAlta:Boolean;
		
		public var plan:Plan;
		
		public var planVersionAlta:PlanVersion;
		
		public function PlanManager()
		{
			
		}
		
		public function editarPlan(pl:Plan):void {
			this.plan = pl;
		}
		
		public function resultArrayPlanes(array:Array):void{
			if(!planesArray){
				planesArray = new ArrayCollection();
			}
			
			planesArray.source = array;
		}
		
		public function resultArrayConceptos(array:Array):void{
			if(!conceptosArray){
				conceptosArray = new ArrayCollection();
			}
			conceptosArray.source = array;
		}

        public function crearNuevoPlan(listaEtapas:ArrayCollection):void {
        	plan = new Plan();
        	plan.esPlanPorDefecto = "N";
        	plan.habilitado = "S";
			planVersionAlta = new PlanVersion();
			var arr:Array = new Array(planVersionAlta);
			plan.planesVersion = arr;
			planVersionAlta.plan = plan;
			planVersionAlta.etapasVersion = new Array();
			for each (var num:Etapa in listaEtapas) 
			{
				 var etapaVersion:EtapaVersion = new EtapaVersion();
				 etapaVersion.etapa = num;
				 planVersionAlta.etapasVersion.push(etapaVersion);
				 etapaVersion.planVersion = planVersionAlta;
				 etapaVersion.accionesVersion = new Array();
			}
        }
        
		public function resultGrabarPlan(result:Array):void {
			if (result.length == 0) {
				Alert.show("El plan fue dado de alta exitosamente.");
			} else {
				var informeError:String = "";
				for(var i:int = 0; i < result.length; i++) {
					informeError = informeError.concat(result[i]);
					informeError = informeError.concat("\n");
				}
				Alert.show(informeError);
			}
		}
		
		public function resultGrabarNuevaVersion(result:Array):void {
			if (result.length == 0) {
				Alert.show("La nueva version fue dada de alta exitosamente.");
			} else {
				var informeError:String = "";
				for(var i:int = 0; i < result.length; i++) {
					informeError = informeError.concat(result[i]);
					informeError = informeError.concat("\n");
				}
				Alert.show(informeError);
			}
			var ev:PlanEvent = new PlanEvent(PlanEvent.NUEVO_PLAN_GRABADO_CON_EXITO,true);
			dispatcher.dispatchEvent(ev);
		}
		
		public function resultBorrarVersion(result:Array):void {
			 if (result[1])  {
			 	    var nuevoArray:Array = new Array();
			    	for (var num:int = 0; num < plan.planesVersion.length; num++) {
				    	if ((plan.planesVersion[num] as PlanVersion).idPlanVersion != result[1].idPlanVersion){
				    		nuevoArray.push(plan.planesVersion[num]);
				    	} 
			    	}	
			    	plan.planesVersion = nuevoArray;
			 }
			 Alert.show(result[0]);
			 var ev:PlanEvent = new PlanEvent(PlanEvent.CAMBIO_VERSION_EVENT,true);
			 dispatcher.dispatchEvent(ev);
		}
		
		public function resultBorrarPlan(result:Array):void {
			    if (result[1])  {
			    	for (var num:int = 0; num < planesArray.length; num++) {
				    	if ((planesArray[num] as Plan).idPlan == result[1]){
				    		planesArray.removeItemAt(num);
				    		break;
				    	}
			    	}	
			    }
				Alert.show(result[0]);
		}
		
		public function resultCambiarEstadoPlan():void {
			Alert.show("Cambio de estado realizado con éxito.");
		}
		
		
		public function resultMarcarPlanPorDefecto():void {
			Alert.show("El plan por defecto se cambió con éxito.");
		}

	}
}