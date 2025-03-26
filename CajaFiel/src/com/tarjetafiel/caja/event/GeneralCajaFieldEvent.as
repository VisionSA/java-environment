package com.tarjetafiel.caja.event {

        import com.adobe.cairngorm.control.CairngormEvent;

        public class GeneralCajaFieldEvent extends CairngormEvent      {
                
                public static const GET_BANCO_BY_ID_EVENT : String = "GetBancoByIdEvent";
                public static const VER_EXITE_CHEQUE_EVENT : String = "VerExisteChequeEvent";
                public static const GET_CHEQUE_BY_FILTRO_EVENT : String = "GetChequeByFiltroEvent";
                public static const GET_ESTADO_CHEQUE_BY_CHEQUE_ID_EVENT : String = "GetEstadoChequeByChequeIdEvent";
                public static const GET_RECIBO_COBRADOR_EVENT: String = "GetReciboCobradorEvent";
                public static const UPDATE_RECIBO_COBRADOR_EVENT: String = "UpdateReciboCobradorEvent";
                
                
                public function GeneralCajaFieldEvent(type: String, param: Object){
                        super(type, false, false);
                        data = param;
                }
        }
}
