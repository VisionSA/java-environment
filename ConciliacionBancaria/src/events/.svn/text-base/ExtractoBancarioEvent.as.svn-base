
	package events {
	import business.ExtractoBancario;
	
	import com.adobe.cairngorm.control.CairngormEvent;	
	
	public class ExtractoBancarioEvent extends CairngormEvent {
		
		
        public static const GUARDAR_EXTRACTO_BANCARIO_EVENT : String = "GuardarExtractoBancarioEvent";
        public static const LEER_EXTRACTO_BANCARIO_EVENT : String = "LeerExtractoBancarioEvent";
        
        public static const BUSCAR_BANCOS_PROPIOS_EVENT : String = "BuscarBancosPropiosEvent";
        
        public static const CALCULAR_SALDO_BCO_FECHACORTE_EVENT : String = "CalcularSaldoBancoFechaCorteEvent";
        public static const CALCULAR_SALDO_MOV_NO_CONCILIADOS_BANCO_EVENT : String = "CalcularSaldoMovNoConciliadosBancoEvent";
   		
		public function ExtractoBancarioEvent(type:String, param : Object) {
                super(type, false, false);
                data = param;
        }    
	}
}