package model
{
	import com.tarjetafiel.caja.vo.Plan;
	import com.tarjetafiel.caja.vo.PlanVersion;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import events.PlanEvent;
	
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class PlanModel extends EventDispatcher
	{
		
		public var dispatcher:IEventDispatcher;
		
		public var planesArray:ArrayCollection;
		
	    public var conceptosArray:ArrayCollection;
		
		public var plan:Plan;
		
		public var planVersionAlta:PlanVersion;
        
		
		public function PlanModel()
		{
		}
		
		public function buscarPlanes(cadenaBuscada:String):void{
		    var filtro:Filtro = new Filtro();						
			filtro.campos.push("descripcion");				
			filtro.operadores.push(Filtro.LIKE);								
			filtro.valores.push(cadenaBuscada);
			var ev:PlanEvent = new PlanEvent(PlanEvent.BUSCAR_PLANES);
			ev.filtro = filtro;
			dispatcher.dispatchEvent(ev);
		}
		
	    public function buscarConceptos():void{
	    	var ev:PlanEvent = new PlanEvent(PlanEvent.BUSCAR_CONCEPTOS);
	    	//ev.filtro = getFiltroConceptoFiel();
			dispatcher.dispatchEvent(ev);
			
		}
		
		public function darPlanAlta(listaEtapas:ArrayCollection):void {
			var ev:PlanEvent = new PlanEvent(PlanEvent.CREAR_PLAN);
			ev.listaEtapas = listaEtapas;
			dispatcher.dispatchEvent(ev);
		}
		
		public function grabarPlan(planAGrabar:Plan):void {
			var ev:PlanEvent = new PlanEvent(PlanEvent.NEW_PLAN);
			ev.plan = planAGrabar;
			dispatcher.dispatchEvent(ev);
		}
		
		public function borrarPlan(planABorrar:Plan):void {
			var ev:PlanEvent = new PlanEvent(PlanEvent.BORRAR_PLAN);
			ev.plan = planABorrar;
			dispatcher.dispatchEvent(ev);
		}
		
		public function cambiarEstadoPlan(planACambiar:Plan):void {
			var ev:PlanEvent = new PlanEvent(PlanEvent.CAMBIAR_ESTADO_PLAN);
			ev.plan = planACambiar;
			dispatcher.dispatchEvent(ev);
		}
		
		public function marcarPlanPorDefecto(planACambiar:Plan):void {
			var ev:PlanEvent = new PlanEvent(PlanEvent.MARCAR_PLAN_POR_DEFECTO);
			ev.plan = planACambiar;
			dispatcher.dispatchEvent(ev);
		}
		
		public function getFiltroConceptoFiel():Filtro{
			var filtro:Filtro = new Filtro();					
			filtro.campos.push("esFiel");				
			filtro.operadores.push(Filtro.LIKE);								
			filtro.valores.push("S");
			return filtro;
		}

		public function grabarNuevaVersion(plan:Plan, planVersion:PlanVersion):void {
			var ev:PlanEvent = new PlanEvent(PlanEvent.GUARDAR_NUEVA_VERSION);
			ev.plan = plan;
			ev.planVersion = planVersion;
			dispatcher.dispatchEvent(ev);
		}
		
		public function borrarVersion(planVersion:PlanVersion):void {
			var ev:PlanEvent = new PlanEvent(PlanEvent.BORRAR_VERSION);
			ev.planVersion = planVersion;
			dispatcher.dispatchEvent(ev);
		}
		
		public function refrescarPlan(planAEditar:Plan):void {
			var ev:PlanEvent = new PlanEvent(PlanEvent.REFRESCAR_PLAN);
			ev.plan = planAEditar;
			dispatcher.dispatchEvent(ev);
		}
		

	}
}