package controller {
	import com.adobe.cairngorm.control.FrontController;
	
	import commands.AcreditacionFondoCommandLeer;
	import commands.ConciliacionFondoCommandArmarYGrabar;
	import commands.BancosPropiosCommandBuscar;
	import commands.CalcularSaldoContabilidadCommand;
	import commands.CalcularSaldoContableMayorCommand;
	import commands.CalcularSaldosExtractoBancarioCommand;
	import commands.ConciliacionFondoCabeceraCommandConsultar;
	import commands.ConciliacionFondoCabeceraCommandDeshacer;
	import commands.ConciliacionFondoCommandConsultar;
	import commands.ConciliacionFondoCabeceraCommandConfirmar;
	import commands.ConciliacionFondoCommandGrabar;
	import commands.ExtractoBancarioCommandGuardar;
	import commands.DetallesConciliacionCommandLeer;
	import commands.ExtractoBancarioCommandLeer;
	import commands.MovimientoConciliableCommand;
	import commands.ConciliacionFondoCommandRevertir;
	
	import events.AcreditacionFondoEvent;
	import events.ConciliacionFondoCabeceraEvent;
	import events.ConciliacionFondoEvent;
	import events.ExtractoBancarioEvent;
	import events.MovimientoConciliableEvent;

	public class ConciliacionController extends FrontController {
		
		public function ConciliacionController() {
			this.initialize();
		}
		
		public function initialize(): void {
			addCommand(ExtractoBancarioEvent.GUARDAR_EXTRACTO_BANCARIO_EVENT,ExtractoBancarioCommandGuardar);
			addCommand(ExtractoBancarioEvent.LEER_EXTRACTO_BANCARIO_EVENT,ExtractoBancarioCommandLeer);
			addCommand(ExtractoBancarioEvent.BUSCAR_BANCOS_PROPIOS_EVENT,BancosPropiosCommandBuscar);
			addCommand(ExtractoBancarioEvent.CALCULAR_SALDO_BCO_FECHACORTE_EVENT,CalcularSaldosExtractoBancarioCommand);
			addCommand(ExtractoBancarioEvent.CALCULAR_SALDO_MOV_NO_CONCILIADOS_BANCO_EVENT,CalcularSaldosExtractoBancarioCommand);

			
			addCommand(MovimientoConciliableEvent.TRAER_MOVIMIENTOS_CONCILIABLE_EVENT,MovimientoConciliableCommand);
			addCommand(MovimientoConciliableEvent.CALCULAR_SALDO_MOV_NO_CONCILIADOS_CONTABILIDAD_EVENT,CalcularSaldoContabilidadCommand);
			addCommand(MovimientoConciliableEvent.CALCULAR_SALDO_CONTABLE_MAYOR_EVENT,CalcularSaldoContableMayorCommand);
			addCommand(MovimientoConciliableEvent.CALCULAR_SALDOS_CALCULADOS_Y_DIFERENCIAS_EVENT,CalcularSaldoContableMayorCommand);
			

			addCommand(ConciliacionFondoEvent.TRAER_CONCILIACION_FONDOS_EVENT,ConciliacionFondoCommandConsultar);
			addCommand(ConciliacionFondoEvent.LEER_CONCILIACION_EVENT,DetallesConciliacionCommandLeer);
			addCommand(ConciliacionFondoEvent.ARMAR_CONCILIACION_EVENT,ConciliacionFondoCommandArmarYGrabar);
			addCommand(ConciliacionFondoEvent.GRABAR_CONCILIACION_EVENT,ConciliacionFondoCommandGrabar);
			addCommand(ConciliacionFondoEvent.REVERTIR_CONCILIACION_CABECERA_EVENT, ConciliacionFondoCommandRevertir);
			addCommand(ConciliacionFondoEvent.REVERTIR_TODOS_CONCILIACION_CABECERA_EVENT, ConciliacionFondoCommandRevertir);
			
			
			addCommand(ConciliacionFondoCabeceraEvent.TRAER_CONCILIADOS_FONDOS_CABECERA_EVENT,ConciliacionFondoCabeceraCommandConsultar);
			addCommand(ConciliacionFondoCabeceraEvent.TRAER_NO_CONCILIADOS_FONDOS_CABECERA_EVENT,ConciliacionFondoCabeceraCommandConsultar);
			addCommand(ConciliacionFondoCabeceraEvent.BUSCAR_CANTIDAD_PAGINAS_CONCILIACION,ConciliacionFondoCabeceraCommandConsultar);
			addCommand(ConciliacionFondoCabeceraEvent.BUSCAR_CANTIDAD_PAGINAS_REVERSION,ConciliacionFondoCabeceraCommandConsultar);
			
			
			addCommand(ConciliacionFondoCabeceraEvent.CONFIRMAR_CONCILIACION_CABECERA_EVENT,ConciliacionFondoCabeceraCommandConfirmar);
			addCommand(ConciliacionFondoCabeceraEvent.CONFIRMAR_TODOS_CONCILIACION_CABECERA_EVENT,ConciliacionFondoCabeceraCommandConfirmar);
			addCommand(ConciliacionFondoCabeceraEvent.DESHACER_CONCILIACION_CABECERA_EVENT,ConciliacionFondoCabeceraCommandDeshacer);
			
			addCommand(AcreditacionFondoEvent.TRAER_ACREDITACION_FONDOS_EVENT,AcreditacionFondoCommandLeer);
			
			
						
		}
		
		
	}
}