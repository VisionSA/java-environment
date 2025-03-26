package com.fiel.caja.judicial.controller {
	
	import com.adobe.cairngorm.control.FrontController;
	import com.fiel.caja.judicial.command.CajaJudicialEvent.AgregarClienteJudicialGrillaCommand;
	import com.fiel.caja.judicial.command.CajaJudicialEvent.BuscarBancoPorIdCommand;
	import com.fiel.caja.judicial.command.CajaJudicialEvent.BuscarCajaAperturaCommand;
	import com.fiel.caja.judicial.command.CajaJudicialEvent.BuscarClienteJudicialCommand;
	import com.fiel.caja.judicial.command.CajaJudicialEvent.BuscarFormaPagoValorComand;
	import com.fiel.caja.judicial.command.CajaJudicialEvent.BuscarListaAbogadosCommand;
	import com.fiel.caja.judicial.command.CajaJudicialEvent.BuscarMediosPagoCajaAperturaCommand;
	import com.fiel.caja.judicial.command.CajaJudicialEvent.BuscarMediosPagoCajaDescargaValoresCommand;
	import com.fiel.caja.judicial.command.CajaJudicialEvent.BuscarOperadorCommand;
	import com.fiel.caja.judicial.command.CajaJudicialEvent.LimpiarGrillaCommand;
	import com.fiel.caja.judicial.command.CajaJudicialEvent.RealizarPagoJudicialCommand;
	import com.fiel.caja.judicial.command.CajaJudicialEvent.VerificarAperturaCajaCommand;
	import com.fiel.caja.judicial.command.Cheques.AgregarChequeCommand;
	import com.fiel.caja.judicial.command.Cheques.AgregarDepositoCommand;
	import com.fiel.caja.judicial.command.Cheques.BuscarChequeEstadoByIdCommand;
	import com.fiel.caja.judicial.command.Cheques.BuscarChequeEstadoPorIdChequeEstadoCommand;
	import com.fiel.caja.judicial.command.Cheques.BuscarChequePropioCommand;
	import com.fiel.caja.judicial.command.Cheques.BuscarChequeTerceroCommand;
	import com.fiel.caja.judicial.command.Cheques.BuscarEstadoChequeCommand;
	import com.fiel.caja.judicial.command.Cheques.ChequesEnCajaCommand;
	import com.fiel.caja.judicial.command.Impresoras.ImpresorasListarCommand;
	import com.fiel.caja.judicial.command.arqueoCaja.EjecutarArqueoCajaCommand;
	import com.fiel.caja.judicial.command.bancoPropio.ListarTodosBancosPropiosCommand;
	import com.fiel.caja.judicial.command.descargaValores.DescargaValoresCommand;
	import com.fiel.caja.judicial.event.ArqueoCajaEvent;
	import com.fiel.caja.judicial.event.CajaJudicialEvent;
	import com.fiel.caja.judicial.event.ChequeEvent;
	import com.fiel.caja.judicial.event.ImpresorasEvent;
	import com.tarjetafiel.proveedorconexion.command.SetModelDataCommand;
	import com.tarjetafiel.proveedorconexion.event.BancoPropioEvent;
	import com.tarjetafiel.proveedorconexion.event.SetModelDataEvent;
	
	public class CajaJudicialController extends FrontController {
		
		public function CajaJudicialController() {
			inicializar();
		}
		
		private function inicializar():void{
			//Para setear datos de forma gen�rica
			addCommand(SetModelDataEvent.SET_DATA, SetModelDataCommand);
			
			addCommand(CajaJudicialEvent.BUSCAR_OPERADOR_EVENT,BuscarOperadorCommand);
			addCommand(CajaJudicialEvent.BUSCAR_CAJA_APERTURA_EVENT,BuscarCajaAperturaCommand);
			addCommand(CajaJudicialEvent.BUSCAR_LISTA_ABOGADOS_EVENT,BuscarListaAbogadosCommand);
			addCommand(CajaJudicialEvent.BUSCAR_CLIENTE_JUDICIAL_EVENT,BuscarClienteJudicialCommand);
			addCommand(CajaJudicialEvent.AGREGAR_CLIENTE_JUDICIAL_GRILLA_EVENT,AgregarClienteJudicialGrillaCommand);
			addCommand(CajaJudicialEvent.BUSCAR_MEDIO_DE_PAGO_CAJA_DESCARGA_VALORES, BuscarMediosPagoCajaDescargaValoresCommand);
			addCommand(CajaJudicialEvent.DESCARGA_VALORES, DescargaValoresCommand);
			addCommand(CajaJudicialEvent.LIMPIAR_GRILLA_EVENT,LimpiarGrillaCommand);
			addCommand(CajaJudicialEvent.VERIFICAR_APERTURA_CAJA_EVENT,VerificarAperturaCajaCommand);
			
			//Cheques
			addCommand(CajaJudicialEvent.BUSCAR_BANCO_POR_ID_EVENT,BuscarBancoPorIdCommand);
			addCommand(ChequeEvent.BUSCAR_CHEQUE_EVENT,BuscarChequeTerceroCommand);
			addCommand(CajaJudicialEvent.BUSCAR_MEDIOS_PAGO_CAJA_APERTURA_EVENT,BuscarMediosPagoCajaAperturaCommand);
			addCommand(ChequeEvent.LIST_CHEQUES_EN_CAJA, ChequesEnCajaCommand);
			addCommand(ChequeEvent.BUSCAR_ESTADO_CHEQUE_EVENT,BuscarEstadoChequeCommand);
			addCommand(ChequeEvent.AGREGAR_CHEQUE_EVENT,AgregarChequeCommand);
			addCommand(ChequeEvent.BUSCAR_CHEQUE_ESTADO_POR_ID_CHEQUE_ESTADO_EVENT,BuscarChequeEstadoPorIdChequeEstadoCommand);
			addCommand(ChequeEvent.BUSCAR_CHEQUE_PROPIO_EVENT,BuscarChequePropioCommand);
			addCommand(ChequeEvent.BUSCAR_CHEQUE_ESTADO_BY_ID_EVENT,BuscarChequeEstadoByIdCommand);

			//Deposito
			addCommand(ChequeEvent.AGREGAR_DEPOSITO_EVENT,AgregarDepositoCommand);
			
			//BancosPropios
			addCommand(BancoPropioEvent.LISTAR_TODOS_EVENT,ListarTodosBancosPropiosCommand);
			
			
			//Impresora
			addCommand(ImpresorasEvent.LISTAR, ImpresorasListarCommand);
			
			//Cierre de Caja
			addCommand(CajaJudicialEvent.BUSCAR_FORMA_DE_PAGO_VALORES, BuscarFormaPagoValorComand);
			addCommand(ArqueoCajaEvent.EJECUTAR_ARQUEO_CAJA, EjecutarArqueoCajaCommand);
			
			//Pago
			addCommand(CajaJudicialEvent.REALIZAR_PAGO_JUDICIAL_EVENT,RealizarPagoJudicialCommand);
		}
		
	}
}