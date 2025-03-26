package controller {
	import com.adobe.cairngorm.control.FrontController;
	
	import commands.AbrirPDFCommand;
	import commands.AddNuevoRangoReciboCommand;
	import commands.AgregarNuevaAccionCommand;
	import commands.AnularReciboCommand;
	import commands.BuscarAbogadoClienteTareaCommand;
	import commands.BuscarAbogadosCommand;
	import commands.BuscarAbogadosParaCambioCommand;
	import commands.BuscarAccionesDisponiblesCommand;
	import commands.BuscarCobradoresCommand;
	import commands.BuscarCobradoresParaCambioCommand;
	import commands.BuscarConceptosCabecerasCommand;
	import commands.BuscarDetalleRecibosCommand;
	import commands.BuscarDetallesTareasCommand;
	import commands.BuscarDomicilioIndividuoCommand;
	import commands.BuscarEtapasCommand;
	import commands.BuscarEtapasVersionCommand;
	import commands.BuscarPartidosAbogadoCommand;
	import commands.BuscarPartidosCobradorCommand;
	import commands.BuscarPlanesCommand;
	import commands.BuscarProvinciasCommand;
	import commands.BuscarRecibosCommand;
	import commands.BuscarTareasCommand;
	import commands.BuscarVersionesCommand;
	import commands.CambiarAbogadoAFuturoCommand;
	import commands.CambiarAbogadoClienteTareaCommand;
	import commands.CambiarAbogadosAsignadosCommand;
	import commands.CambiarCobradorAFuturoCommand;
	import commands.CambiarCobradorCommand;
	import commands.CambiarCobradoresAsignadosCommand;
	import commands.ConfirmarTareaPendienteCommand;
	import commands.CrearNuevoPlanCommand;
	import commands.EliminarAccionVersionCommand;
	import commands.EliminarPlanCommand;
	import commands.EliminarVersionCommand;
	import commands.GenerarCobrosCobradoresCommand;
	import commands.GuardarVersionCommand;
	import commands.InicializarEditPlanModuloCommand;
	import commands.InicializarModuloPlanCommand;
	import commands.InicializarModuloTareasCommand;
	import commands.ModificarAccionCommand;
	import commands.ModificarPlanCommand;
	import commands.SeleccionarAbogadoCambioCommand;
	import commands.SeleccionarAccionDisponibleCommand;
	import commands.SeleccionarAccionVersionCommand;
	import commands.SeleccionarCobradorCommand;
	import commands.SeleccionarDetalleReciboCommand;
	import commands.SeleccionarDetalleTareaCommand;
	import commands.SeleccionarListaAccionPanelCommand;
	import commands.SeleccionarPartidoCommand;
	import commands.SeleccionarPlanCommand;
	import commands.SeleccionarRangoReciboCommand;
	import commands.SeleccionarTareaCommand;
	import commands.SeleccionarVersionCommand;
	import commands.SetearPlanPorDefectoCommand;
	
	import events.GenericAbogadosEvent;
	import events.GenericAsignacionCobradoresEvent;
	import events.GenericPlanEvent;
	import events.GenericTareasPendientesEvent;

	public class CobranzasController extends FrontController {
		
		public function CobranzasController() {
			this.initialize();
		}
		
		public function initialize(): void {
			
			// COMANDOS MODULO TAREAS
			addCommand(GenericTareasPendientesEvent.BUSCAR_TAREAS_EVENT,BuscarTareasCommand);
			addCommand(GenericTareasPendientesEvent.SELECCIONAR_TAREA_EVENT,SeleccionarTareaCommand);
			addCommand(GenericTareasPendientesEvent.CONFIRMAR_TAREA_PENDIENTE_EVENT,ConfirmarTareaPendienteCommand);
			addCommand(GenericTareasPendientesEvent.BUSCAR_DETALLES_TAREAS_EVENT,BuscarDetallesTareasCommand);
			addCommand(GenericTareasPendientesEvent.ABRIR_PDF_EVENT,AbrirPDFCommand);
			addCommand(GenericTareasPendientesEvent.INICIALIZAR_MODULO_TAREAS_EVENT,InicializarModuloTareasCommand);
			addCommand(GenericTareasPendientesEvent.SELECCIONAR_DETALLE_TAREA_EVENT,SeleccionarDetalleTareaCommand);
			addCommand(GenericTareasPendientesEvent.BUSCAR_COBRADORES_PARA_CAMBIO_EVENT,BuscarCobradoresParaCambioCommand);
			addCommand(GenericTareasPendientesEvent.SELECCIONAR_COBRADOR_EVENT,SeleccionarCobradorCommand);
			addCommand(GenericTareasPendientesEvent.CAMBIAR_COBRADOR_EVENT,CambiarCobradorCommand);
			addCommand(GenericTareasPendientesEvent.BUSCAR_DOMICILIO_INDIVIDUO_EVENT,BuscarDomicilioIndividuoCommand);
			
			// COMANDOS MODULO PLANES
			addCommand(GenericPlanEvent.INICIALIZAR_MODULO_PLAN_EVENT,InicializarModuloPlanCommand);			
			addCommand(GenericPlanEvent.BUSCAR_PLANES_EVENT,BuscarPlanesCommand);
			addCommand(GenericPlanEvent.SELECCIONAR_PLAN_EVENT,SeleccionarPlanCommand);
			addCommand(GenericPlanEvent.BUSCAR_VERSIONES_EVENT,BuscarVersionesCommand);
			addCommand(GenericPlanEvent.SELECCIONAR_VERSION_EVENT,SeleccionarVersionCommand);
			addCommand(GenericPlanEvent.INICIALIZAR_EDIT_PLAN_MODULO_EVENT,InicializarEditPlanModuloCommand);
			addCommand(GenericPlanEvent.BUSCAR_ETAPAS_VERSION_EVENT,BuscarEtapasVersionCommand);
			addCommand(GenericPlanEvent.BUSCAR_ACCIONES_DISPONIBLES_EVENT,BuscarAccionesDisponiblesCommand);
			addCommand(GenericPlanEvent.BUSCAR_ETAPAS_EVENT,BuscarEtapasCommand);
			addCommand(GenericPlanEvent.SELECCIONAR_ACCION_DISPONIBLE_EVENT,SeleccionarAccionDisponibleCommand);
			addCommand(GenericPlanEvent.SELECCION_LISTA_ACCION_PANEL_EVENT,SeleccionarListaAccionPanelCommand);
			addCommand(GenericPlanEvent.AGREGAR_NUEVA_ACCION_EVENT,AgregarNuevaAccionCommand);
			addCommand(GenericPlanEvent.BUSCAR_CONCEPTOS_CABECERAS_EVENT,BuscarConceptosCabecerasCommand);
			addCommand(GenericPlanEvent.GUARDAR_VERSION_EVENT,GuardarVersionCommand);
			addCommand(GenericPlanEvent.SELECCIONAR_ACCION_VERSION_EVENT,SeleccionarAccionVersionCommand);
			addCommand(GenericPlanEvent.MODIFICAR_ACCION_EVENT,ModificarAccionCommand);
			addCommand(GenericPlanEvent.ELIMINAR_ACCION_VERSION_EVENT,EliminarAccionVersionCommand);
			addCommand(GenericPlanEvent.ELIMINAR_VERSION_EVENT,EliminarVersionCommand);
			addCommand(GenericPlanEvent.MODIFICAR_PLAN_EVENT,ModificarPlanCommand);
			addCommand(GenericPlanEvent.CREAR_NUEVO_PLAN_EVENT,CrearNuevoPlanCommand);
			addCommand(GenericPlanEvent.ELIMINAR_PLAN_EVENT,EliminarPlanCommand);
			addCommand(GenericPlanEvent.SETEAR_PLAN_POR_DEFECTO_EVENT,SetearPlanPorDefectoCommand);
			
			
			//Modulo AsignacionCobradores
			addCommand(GenericAsignacionCobradoresEvent.BUSCAR_PROVINCIAS_EVENT,BuscarProvinciasCommand);
			addCommand(GenericAsignacionCobradoresEvent.BUSCAR_PARTIDOS_COBRADOR_EVENT,BuscarPartidosCobradorCommand);
			addCommand(GenericAsignacionCobradoresEvent.BUSCAR_COBRADORES_EVENT,BuscarCobradoresCommand);
			addCommand(GenericAsignacionCobradoresEvent.SELECCIONAR_PARTIDO_EVENT,SeleccionarPartidoCommand);
			addCommand(GenericAsignacionCobradoresEvent.CAMBIAR_COBRADOR_A_FUTURO_EVENT,CambiarCobradorAFuturoCommand);
			addCommand(GenericAsignacionCobradoresEvent.CAMBIAR_COBRADORES_ASIGNADOS_EVENT,CambiarCobradoresAsignadosCommand);
			
			
			//Modulo Cobros Cobradores
			addCommand(GenericAsignacionCobradoresEvent.GENERAR_COBROS_COBRADORES_EVENT,GenerarCobrosCobradoresCommand);
			
			//Modulo Recibos
			addCommand(GenericAsignacionCobradoresEvent.BUSCAR_RECIBOS_EVENT,BuscarRecibosCommand);
			addCommand(GenericAsignacionCobradoresEvent.SELECCIONAR_RANGO_RECIBO_EVENT,SeleccionarRangoReciboCommand);
			addCommand(GenericAsignacionCobradoresEvent.BUSCAR_DETALLE_RECIBOS_EVENT,BuscarDetalleRecibosCommand);
			addCommand(GenericAsignacionCobradoresEvent.SELECCIONAR_DETALLE_RECIBO_EVENT,SeleccionarDetalleReciboCommand);
			addCommand(GenericAsignacionCobradoresEvent.ANULAR_RECIBO_EVENT,AnularReciboCommand);
			addCommand(GenericAsignacionCobradoresEvent.ADD_NUEVO_RANGO_RECIBO_EVENT,AddNuevoRangoReciboCommand);
			
			//Modulo Abogados
			addCommand(GenericAbogadosEvent.BUSCAR_ABOGADOS_EVENT,BuscarAbogadosCommand);
			addCommand(GenericAbogadosEvent.BUSCAR_ABOGADOS_PARA_CAMBIO_EVENT,BuscarAbogadosParaCambioCommand);
			addCommand(GenericAbogadosEvent.BUSCAR_PARTIDOS_ABOGADO_EVENT,BuscarPartidosAbogadoCommand);
			addCommand(GenericAbogadosEvent.SELECCIONAR_ABOGADO_CAMBIO_EVENT,SeleccionarAbogadoCambioCommand);
			addCommand(GenericAbogadosEvent.CAMBIAR_ABOGADO_A_FUTURO_EVENT,CambiarAbogadoAFuturoCommand);
			addCommand(GenericAbogadosEvent.CAMBIAR_ABOGADOS_ASIGNADOS_EVENT,CambiarAbogadosAsignadosCommand);
			addCommand(GenericAbogadosEvent.BUSCAR_ABOGADO_CLIENTE_TAREA_EVENT,BuscarAbogadoClienteTareaCommand);
			addCommand(GenericAbogadosEvent.CAMBIAR_ABOGADO_CLIENTE_TAREA_EVENT,CambiarAbogadoClienteTareaCommand);
			
		}
		
		
	}
}