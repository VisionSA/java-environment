package com.tarjetafiel.proveedorconexion.control
{
	import com.adobe.cairngorm.control.FrontController;
	import com.tarjetafiel.proveedorconexion.command.BancoCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarHistAccionesCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarHistEstCobrYComCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarHistEstadoCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarRevisionLineaCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarTramitesCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarDigitalesCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarReclamosCommand;
	
	import com.tarjetafiel.proveedorconexion.command.BuscarRegistroTitularAppCommand;
	import com.tarjetafiel.proveedorconexion.command.GrabarControlClienteCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarHistLineaCreditoCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarLineaCreditoTemCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarBancoCentralCommand;
	
	import com.tarjetafiel.proveedorconexion.command.BuscarLineaCreditoCommand;
	import com.tarjetafiel.proveedorconexion.command.GrabarLineaCreditoCommand;
	import com.tarjetafiel.proveedorconexion.command.FuturosVencimientosCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarControlClienteCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarGestorClienteCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarDetallePagoGestionCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarSaldoCuentaCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarDetalleRefRepCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarCorrienteResumenCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarCorrienteResumenFechaCommand;
	import com.tarjetafiel.proveedorconexion.command.BuscarRegistroAdicionalAppCommand;
	
	import com.tarjetafiel.proveedorconexion.command.BuscarFecha1Documentos;
	import com.tarjetafiel.proveedorconexion.command.BuscarServidorDocumentosCommand;
	
	
	
	import com.tarjetafiel.proveedorconexion.event.BancoEvent;
	import com.tarjetafiel.proveedorconexion.event.ClientesGeneralEvent;
	
	
	
	public class Controller extends FrontController {
		// toda las acciones de la aplicación se gestionan desde esta clase
		public function Controller()
		{
			//Bancos
			addCommand(BancoEvent.BUSCAR_BANCOS, BancoCommand);
			
			//Clientes
			addCommand(ClientesGeneralEvent.BUSCAR_FUTUROS_VENCIMIENTOS,FuturosVencimientosCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_HIST_EST_COBR_Y_COM_EVENT,BuscarHistEstCobrYComCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_HIST_ACCIONES_EVENT,BuscarHistAccionesCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_HIST_LINEA_CREDITO_EVENT,BuscarHistLineaCreditoCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_BANCO_CENTRAL_EVENT,BuscarBancoCentralCommand);
			
			addCommand(ClientesGeneralEvent.BUSCAR_LINEA_CREDITO_TEMPORAL_EVENT,BuscarLineaCreditoTemCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_TITULAR_APP_EVENT,BuscarRegistroTitularAppCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_ADICIONAL_APP_EVENT,BuscarRegistroAdicionalAppCommand);
			
			addCommand(ClientesGeneralEvent.BUSCAR_HIST_ESTADOS_EVENT,BuscarHistEstadoCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_TRAMITES_EVENT,BuscarTramitesCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_DIGITALES_EVENT,BuscarDigitalesCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_RECLAMOS_EVENT,BuscarReclamosCommand);
			addCommand(ClientesGeneralEvent.GRABAR_LINEACREDITO_EVENT,GrabarLineaCreditoCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_LINEACREDITO_EVENT,BuscarLineaCreditoCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_REVISIONCREDITO_EVENT,BuscarRevisionLineaCommand);
			addCommand(ClientesGeneralEvent.GRABAR_CONTROLCLIENTE_EVENT,GrabarControlClienteCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_CONTROLCLIENTE_EVENT,BuscarControlClienteCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_GESTORCUENTA_EVENT,BuscarGestorClienteCommand);
			addCommand(ClientesGeneralEvent.DETALLE_PAGO_GESTION_EVENT,BuscarDetallePagoGestionCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_SALDOCUENTA_EVENT,BuscarSaldoCuentaCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_REFPREP_EVENT,BuscarDetalleRefRepCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_CORRIENTE_RESUMEN_EVENT,BuscarCorrienteResumenCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_CORRIENTE_FECHA_EVENT,BuscarCorrienteResumenFechaCommand);
			
			addCommand(ClientesGeneralEvent.BUSCAR_SERVIDOR_DOCUMENTOS_EVENT,BuscarServidorDocumentosCommand);
			addCommand(ClientesGeneralEvent.BUSCAR_FECHA1_DOCUMENTOS_EVENT,BuscarFecha1Documentos);
			
		}
				
				
	}
}