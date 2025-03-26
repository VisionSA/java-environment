package com.tarjetafiel.caja.control
{
	import com.adobe.cairngorm.control.FrontController;
	import com.tarjetafiel.caja.command.BancoCommand;
	import com.tarjetafiel.caja.command.BancoPropioCommand;
	import com.tarjetafiel.caja.command.BuscarClienteTitularCommand;
	import com.tarjetafiel.caja.command.BuscarDisponibleAdelantoCommand;
	import com.tarjetafiel.caja.command.BuscarFormaPagoValorComand;
	import com.tarjetafiel.caja.command.CajaAperturaCommand;
	import com.tarjetafiel.caja.command.CajaArqueoCommand;
	import com.tarjetafiel.caja.command.CajaCommand;
	import com.tarjetafiel.caja.command.CalculoCuotaCommand;
	import com.tarjetafiel.caja.command.ChequeCommand;
	import com.tarjetafiel.caja.command.ChequeEstadoCommand;
	import com.tarjetafiel.caja.command.ChequesEnCajaCommand;
	import com.tarjetafiel.caja.command.ClientesCommand;
	import com.tarjetafiel.caja.command.ConceptoCommand;
	import com.tarjetafiel.caja.command.DescargaValoresCommand;
	import com.tarjetafiel.caja.command.EmpresaCommand;
	import com.tarjetafiel.caja.command.EntregarTodosPagosCommand;
	import com.tarjetafiel.caja.command.FormaPagoCajaCommand;
	import com.tarjetafiel.caja.command.FuturosVencimientosCommand;
	import com.tarjetafiel.caja.command.GetBancoByIdCommand;
	import com.tarjetafiel.caja.command.GetChequeByFiltroCommand;
	import com.tarjetafiel.caja.command.GetEstadoChequeByChequeIdCommand;
	import com.tarjetafiel.caja.command.GetReciboCobradorCommand;
	import com.tarjetafiel.caja.command.IndividuosHabilitadosPagosCommand;
	import com.tarjetafiel.caja.command.LiqComercioCommand;
	import com.tarjetafiel.caja.command.ListarImresorasCommand;
	import com.tarjetafiel.caja.command.OperadorCommand;
	import com.tarjetafiel.caja.command.PagoCommand;
	import com.tarjetafiel.caja.command.PagosAComercioCommand;
	import com.tarjetafiel.caja.command.ReImpresionTicketCommand;
	import com.tarjetafiel.caja.command.RegistrarAdelantoCommand;
	import com.tarjetafiel.caja.command.RepactacionCommand;
	import com.tarjetafiel.caja.command.RepactacionPagoMinCommand;
	import com.tarjetafiel.caja.command.VerExisteChequeCommand;
	import com.tarjetafiel.caja.event.AdelantoEfectivoEvent;
	import com.tarjetafiel.caja.event.BancoEvent;
	import com.tarjetafiel.caja.event.CajaArqueoEvent;
	import com.tarjetafiel.caja.event.CajaEvent;
	import com.tarjetafiel.caja.event.CalculoCuotaEvent;
	import com.tarjetafiel.caja.event.ChequeEstadoEvent;
	import com.tarjetafiel.caja.event.ChequeEvent;
	import com.tarjetafiel.caja.event.ClientesEvent;
	import com.tarjetafiel.caja.event.ConceptosEvent;
	import com.tarjetafiel.caja.event.DescargaValoresEvent;
	import com.tarjetafiel.caja.event.EmpresaEvent;
	import com.tarjetafiel.caja.event.FormaPagoCajaEvent;
	import com.tarjetafiel.caja.event.FormaPagoValoresEvent;
	import com.tarjetafiel.caja.event.GeneralCajaFieldEvent;
	import com.tarjetafiel.caja.event.ImpresorasEvent;
	import com.tarjetafiel.caja.event.IndividuosHabilitadosPagosEvent;
	import com.tarjetafiel.caja.event.LiqComercioEvent;
	import com.tarjetafiel.caja.event.OperadorEvent;
	import com.tarjetafiel.caja.event.PagoAComercioEvent;
	import com.tarjetafiel.caja.event.PagoEvent;
	import com.tarjetafiel.caja.event.ReImpresionTicketEvent;
	import com.tarjetafiel.caja.event.RepactacionEvent;
	
	public class Controller extends FrontController
	{
		// toda las acciones de la aplicación se gestionan desde esta clase
		public function Controller()
		{
			//Clientes
			addCommand(ClientesEvent.BUSCAR_CLIENTES, ClientesCommand);			
			addCommand(ClientesEvent.BUSCAR_LIQUIDACIONES, ClientesCommand);			
			addCommand(ClientesEvent.BUSCAR_LIQUIDACIONES_DETALLE, ClientesCommand);			
			addCommand(ClientesEvent.BUSCAR_CLIENTES_CUENTA, ClientesCommand);
			addCommand(ClientesEvent.BUSCAR_CLIENTE_TITULAR, BuscarClienteTitularCommand);
			addCommand(ClientesEvent.BUSCAR_FUTUROS_VENCIMIENTOS,FuturosVencimientosCommand);
			
			//Bancos
			addCommand(BancoEvent.BUSCAR_BANCOS, BancoCommand);
			addCommand(BancoEvent.BUSCAR_BANCOS_PROPIOS, BancoPropioCommand);
			addCommand(GeneralCajaFieldEvent.GET_BANCO_BY_ID_EVENT,GetBancoByIdCommand);
							
			//Comercios o Empresas
			addCommand(EmpresaEvent.BUSCAR_EMPRESA, EmpresaCommand);					
			addCommand(LiqComercioEvent.BUSCAR_LIQ_COMERCIO, LiqComercioCommand);						
			addCommand(LiqComercioEvent.BUSCAR_DETALLES_LIQ , LiqComercioCommand);
			addCommand(IndividuosHabilitadosPagosEvent.BUSCAR_INDIVIDUOS_EMPRESA,IndividuosHabilitadosPagosCommand);
			addCommand(PagoAComercioEvent.PAGOS_LIST_UPDATE,EntregarTodosPagosCommand);
						
			//Operador
			addCommand(OperadorEvent.CONSULTAR_OPERADOR, OperadorCommand); 
			
			//Pagos
			addCommand(PagoEvent.EFECTUAR_PAGO,PagoCommand);
			//addCommand(ConsultarVencimientoEvent.CONSULTAR_VENCIMIENTO,ConsultarVencimientoLiquidacionCommand);
						
			//Conceptos
			addCommand(ConceptosEvent.LIST_CONCEPTOS,ConceptoCommand);
			
			//Caja
			addCommand(FormaPagoCajaEvent.BUSCAR_FORMA_PAGO_CAJA,FormaPagoCajaCommand);
			addCommand(CajaEvent.BUSCAR_CAJA,CajaCommand);
			addCommand(CajaEvent.BUSCAR_CAJA_APERTURA, CajaAperturaCommand);
			addCommand(ImpresorasEvent.LISTAR, ListarImresorasCommand);
			addCommand(FormaPagoValoresEvent.BUSCAR_TODOS,BuscarFormaPagoValorComand);
			addCommand(CajaArqueoEvent.EJECUTAR_ARQUEO,CajaArqueoCommand);
			addCommand(DescargaValoresEvent.REGISTRAR_RETIRO,DescargaValoresCommand);
			addCommand(ChequeEvent.LIST_CHEQUES_EN_CAJA,ChequesEnCajaCommand);
			addCommand(ReImpresionTicketEvent.RE_IMPRIMIR_TICKET, ReImpresionTicketCommand);
			addCommand(PagoAComercioEvent.PAGOS_LIST ,PagosAComercioCommand);
						
			//Cheques
			addCommand(ChequeEstadoEvent.BUSCAR_TODOS, ChequeEstadoCommand);
			addCommand(ChequeEvent.EXISTE_CHEQUE, ChequeCommand);
			addCommand(GeneralCajaFieldEvent.VER_EXITE_CHEQUE_EVENT,VerExisteChequeCommand);
			addCommand(GeneralCajaFieldEvent.GET_CHEQUE_BY_FILTRO_EVENT,GetChequeByFiltroCommand);
			addCommand(GeneralCajaFieldEvent.GET_ESTADO_CHEQUE_BY_CHEQUE_ID_EVENT,GetEstadoChequeByChequeIdCommand);
			
			//Repactacion
			addCommand(RepactacionEvent.CALCULAR_REPACTACION,RepactacionCommand);
			addCommand(RepactacionEvent.REPACTACION_PAGO_MINIMO, RepactacionPagoMinCommand);
			
			//Adelanto Efectivo
			addCommand(CalculoCuotaEvent.CALCULO_CUOTA, CalculoCuotaCommand);
			addCommand(AdelantoEfectivoEvent.REGISTRAR_ADELANTO, RegistrarAdelantoCommand);
			addCommand(AdelantoEfectivoEvent.BUSCAR_DISPONIBLE,BuscarDisponibleAdelantoCommand);
			
			// Recibo Cobrador
			addCommand(GeneralCajaFieldEvent.GET_RECIBO_COBRADOR_EVENT,GetReciboCobradorCommand);
		}
				
				
	}
}