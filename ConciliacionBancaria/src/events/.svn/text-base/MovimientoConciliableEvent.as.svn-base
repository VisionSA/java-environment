package events {
	
	import com.adobe.cairngorm.control.CairngormEvent;	
	
	public class MovimientoConciliableEvent extends CairngormEvent {
		
		
        public static const TRAER_MOVIMIENTOS_CONCILIABLE_EVENT : String = "TraerMovimientoConciliableEvent";
        public static const CALCULAR_SALDO_MOV_NO_CONCILIADOS_CONTABILIDAD_EVENT : String = "CalcularSaldoMovNoConciliadosContabilidadEvent";
        public static const CALCULAR_SALDO_CONTABLE_MAYOR_EVENT : String = "CalcularSaldoContableMayorEvent";
        public static const CALCULAR_SALDOS_CALCULADOS_Y_DIFERENCIAS_EVENT : String = "CalcularSaldosCalculadosYDiferenciasEvent";
   
		public function MovimientoConciliableEvent(type:String, param : Object) {
                super(type, false, false);
                data = param;
        }    
		
	}
}