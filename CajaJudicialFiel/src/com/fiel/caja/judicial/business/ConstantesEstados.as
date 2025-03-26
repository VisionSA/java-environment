package com.fiel.caja.judicial.business {
	
	public class ConstantesEstados {
		
		//Estados Aplicacion
		public static const ESTADO_AUTORIZADO : String = "Autorizado";
		public static const ESTADO_NO_AUTORIZADO : String = "NoAutorizado";
		public static const ESTADO_CARGANDO : String = "Cargando";
		public static const ESTADO_CAJA_CERRADA : String = "CajaCerrada";
		
		
		//Estados Vista BuscarCliente
		public static const ESTADO_VBC_ENCONTRADO : String = "Encontrado";
		public static const ESTADO_VBC_NO_ENCONTRADO : String = "NoEncontrado";
		
		//Estados Vista Impresoras
		/*public static const ESTADO_VI_IMPRESORAS : String = "Impresoras";
		public static const ESTADO_VI_REFRESCAR_LISTA : String = "RefrescarLista";*/
		
		//Estados Vista AgregarPago
		public static const ESTADO_VAP_SIN_MEDIOS : String = "SinMedios";
		public static const ESTADO_VAP_CON_MEDIOS : String = "ConMedios";
		
		//Estados Vista AgregarCheque
		public static const ESTADO_VACH_CHEQUE_PROPIO : String = "ChequePropio";
		public static const ESTADO_VACH_CHEQUE_TERCERO : String = "ChequeTercero";
		public static const ESTADO_VACH_TERCERO_MODIFICABLE : String = "ChequeTerceroModificable";
		public static const ESTADO_VACH_TERCERO_NO_MODIFICABLE : String = "ChequeTerceroNoModificable";
		public static const ESTADO_VACH_PROPIO_NO_MODIFICABLE : String = "ChequePropioNoModificable";
		
		
		//Estados vista Menú cajas
		public static const ESTADO_VMC_BASE : String = "Base";
		public static const ESTADO_VMC_DESCARGA_VALORES : String = "DescargaValores";
		public static const ESTADO_VMC_DESCARGA_VALORES_COMPLETE : String = "DescargaValoresComplete";
		public static const ESTADO_VMC_CIERRE_CAJA_X : String = "CierreCajaX";
		public static const ESTADO_VMC_NUEVO_CIERRE_CAJA_X : String = "NuevoCierreCajaX";
		public static const ESTADO_VMC_CIERRE_CAJA_Z : String = "CierreCajaZ";
		public static const ESTADO_VMC_CIERRA_CAJA_Z_COMPLETO : String = "CierraCajaZCompleto";
		
		
		//Estados Vista descarga Valores
		public static const ESTADO_VDV_BASE : String = "Base";
		public static const ESTADO_VDV_ACTUALIZAR : String = "Actualizar";
		
		//Estado General Cheque
		public static const ESTADO_CMP_CHEQUE_NUEVO : String ="ChequeNuevo";
		
		//Estados Cheque
		public static const ESTADO_CMP_CHEQUE_ESTADO_VALIDO : String ="EstadoValido";
		public static const ESTADO_CMP_CHEQUE_ESTADO_INVALIDO : String ="EstadoInvalido";
		public static const ESTADO_CMP_CHEQUE_CONCILIADO : String ="Conciliado";
		public static const ESTADO_CMP_CHEQUE_NO_CONCILIADO : String ="NoConciliado";
		
		
		
	}
}