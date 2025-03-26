package com.bizitglobal.webapp.faces.service.transacciones;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.bizitglobal.tarjetafiel.transacciones.service.impl.CalculoCuotaServicesImpl;
import com.bizitglobal.tarjetafiel.transacciones.service.TipoAsesorComercialService;
import com.bizitglobal.tarjetafiel.transacciones.service.ArchivoCuponesService;
import com.bizitglobal.tarjetafiel.transacciones.service.ArchivoDebitoConfService;
import com.bizitglobal.tarjetafiel.transacciones.service.ArchivoDebitoHistService;
import com.bizitglobal.tarjetafiel.transacciones.service.CalculoInteresComService;
import com.bizitglobal.tarjetafiel.transacciones.service.ArchivosAFIPService;
import com.bizitglobal.tarjetafiel.transacciones.service.CargoAnualServices;
import com.bizitglobal.tarjetafiel.transacciones.service.CargoTransferenciaBancariaService;
import com.bizitglobal.tarjetafiel.transacciones.service.CargoTransferenciaChequeService;
import com.bizitglobal.tarjetafiel.transacciones.service.ClienteLiquidacionService;
import com.bizitglobal.tarjetafiel.transacciones.service.ConciliacionConsumosServices;
import com.bizitglobal.tarjetafiel.transacciones.service.EstadoClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.GaranteService;
import com.bizitglobal.tarjetafiel.transacciones.service.InteresMoraService;
import com.bizitglobal.tarjetafiel.transacciones.service.CargoResumenServices;
import com.bizitglobal.tarjetafiel.transacciones.service.CliAdicionalService;
import com.bizitglobal.tarjetafiel.transacciones.service.CliCliente2Service;
import com.bizitglobal.tarjetafiel.transacciones.service.CliClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.CliCobSitLogService;
import com.bizitglobal.tarjetafiel.transacciones.service.CliCredPendService;
import com.bizitglobal.tarjetafiel.transacciones.service.CliGaranteService;
import com.bizitglobal.tarjetafiel.transacciones.service.CliIndividuoService;
import com.bizitglobal.tarjetafiel.transacciones.service.CliMarcaService;
import com.bizitglobal.tarjetafiel.transacciones.service.CliPlasticoService;
import com.bizitglobal.tarjetafiel.transacciones.service.CliSaldoActualService;
import com.bizitglobal.tarjetafiel.transacciones.service.CliSituacionLogService;
import com.bizitglobal.tarjetafiel.transacciones.service.ClienteConceptoService;
import com.bizitglobal.tarjetafiel.transacciones.service.ClienteTransaccionService;
import com.bizitglobal.tarjetafiel.transacciones.service.CodComercioService;
import com.bizitglobal.tarjetafiel.transacciones.service.CodComercioActividadService;
import com.bizitglobal.tarjetafiel.transacciones.service.CodigoAutorizacionService;
import com.bizitglobal.tarjetafiel.transacciones.service.ComercioConceptoService;
import com.bizitglobal.tarjetafiel.transacciones.service.ComercioFormaPagoService;
import com.bizitglobal.tarjetafiel.transacciones.service.ComercioListaPrecioService;
import com.bizitglobal.tarjetafiel.transacciones.service.ConceptoDetalleReglaService;
import com.bizitglobal.tarjetafiel.transacciones.service.ConceptoDetalleService;
import com.bizitglobal.tarjetafiel.transacciones.service.ConceptoDetalleTargetService;
import com.bizitglobal.tarjetafiel.transacciones.service.ConceptoFielVersionService;
import com.bizitglobal.tarjetafiel.transacciones.service.ConceptoService;
import com.bizitglobal.tarjetafiel.transacciones.service.CtaCteClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.CtaCteClienteAuxService;
import com.bizitglobal.tarjetafiel.transacciones.service.CtaCteComercioService;
import com.bizitglobal.tarjetafiel.transacciones.service.ColaboradorService;
import com.bizitglobal.tarjetafiel.transacciones.service.DetalleReglaPFService;
import com.bizitglobal.tarjetafiel.transacciones.service.EventosClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.FormaPagoTransaccionService;
import com.bizitglobal.tarjetafiel.transacciones.service.GestorLiquidacionClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.GestorLiquidacionComService;
import com.bizitglobal.tarjetafiel.transacciones.service.ItemLiquidacionService;
import com.bizitglobal.tarjetafiel.transacciones.service.LineaCredComposService;
import com.bizitglobal.tarjetafiel.transacciones.service.LineaCredHistoricoService;
import com.bizitglobal.tarjetafiel.transacciones.service.LiqClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.LiqComercioLPService;
import com.bizitglobal.tarjetafiel.transacciones.service.LiqComercioService;
import com.bizitglobal.tarjetafiel.transacciones.service.LiquidacionClientesService;
import com.bizitglobal.tarjetafiel.transacciones.service.LiquidacionService;
import com.bizitglobal.tarjetafiel.transacciones.service.LiqComercioDetService;
import com.bizitglobal.tarjetafiel.transacciones.service.LiquidacionListasPreciosService;
import com.bizitglobal.tarjetafiel.transacciones.service.ListaPrecioParaLiquidarService;
import com.bizitglobal.tarjetafiel.transacciones.service.ListaPrecioService;
import com.bizitglobal.tarjetafiel.transacciones.service.ListaPrecioVersionService;
import com.bizitglobal.tarjetafiel.transacciones.service.LoteComercioItemService;
import com.bizitglobal.tarjetafiel.transacciones.service.LoteComercioService;
import com.bizitglobal.tarjetafiel.transacciones.service.NotaCreditoConIvaServices;
import com.bizitglobal.tarjetafiel.transacciones.service.NotaCreditoSinIvaServices;
import com.bizitglobal.tarjetafiel.transacciones.service.NotaDebitoConIvaServices;
import com.bizitglobal.tarjetafiel.transacciones.service.NotaDebitoSinIvaServices;
import com.bizitglobal.tarjetafiel.transacciones.service.OrigenConceptoService;
import com.bizitglobal.tarjetafiel.transacciones.service.OrigenenService;
import com.bizitglobal.tarjetafiel.transacciones.service.PdfLiqComercioService;
import com.bizitglobal.tarjetafiel.transacciones.service.PlasticoClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.PlasticoEstadosService;
import com.bizitglobal.tarjetafiel.transacciones.service.PlasticoHistorialService;
import com.bizitglobal.tarjetafiel.transacciones.service.PlasticoLoteService;
import com.bizitglobal.tarjetafiel.transacciones.service.PlasticoLugarService;
import com.bizitglobal.tarjetafiel.transacciones.service.PlasticoOperacionService;
import com.bizitglobal.tarjetafiel.transacciones.service.EstadoLoteService;
import com.bizitglobal.tarjetafiel.transacciones.service.ReglaPFService;
import com.bizitglobal.tarjetafiel.transacciones.service.RetencionComercioSICOREService;
import com.bizitglobal.tarjetafiel.transacciones.service.MotivoCierreCuentaClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.CierreCuentaClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.RetencionAComercioService;
import com.bizitglobal.tarjetafiel.transacciones.service.SeguroDeVidaService;
import com.bizitglobal.tarjetafiel.transacciones.service.TransaccionService;
import com.bizitglobal.webapp.faces.service.BaseService;
import com.bizitglobal.tarjetafiel.transacciones.dao.TokenDao;
import com.bizitglobal.tarjetafiel.transacciones.service.GeneracionClientesOcaService;


public class TransaccionesServiceFaces extends BaseService {

	private SessionFactory sessionFactory = null;

	private static final Logger log = Logger.getLogger(TransaccionesServiceFaces.class);
	private static final String TIPOASESORCOMERCIAL_SERVICE_NAME = "tipoAsesorComercialService";
	private static final String ARCHIVODEBITOCONF_SERVICE_NAME = "archivoDebitoConfService";
	private static final String PDFLIQCOMERCIO_SERVICE_NAME = "pdfLiqComercioService";
	private static final String ARCHIVODEBITOHIST_SERVICE = "archivoDebitoHistService";
	private static final String CARGO_RESUMEN_SERVICE_NAME = "cargoResumenServices";
	private static final String CARGO_ANUAL_NAME = "cargoAnualServices";
	private static final String NOTA_DEBITO_SIN_IVA_NAME = "notaDebitoSinIvaServices";
	private static final String NOTA_DEBITO_CON_IVA_NAME = "notaDebitoConIvaServices";
	private static final String NOTA_CREDITO_SIN_IVA_NAME = "notaCreditoSinIvaServices";
	private static final String NOTA_CREDITO_CON_IVA_NAME = "notaCreditoConIvaServices";
	private static final String INTERES_COMPENSATORIO_SERVICE_NAME = "interesMoraService";
	private static final String SEGURO_DE_VIDA_SERVICE_NAME = "seguroDeVidaService";
	private static final String CARGO_TRANSFERENCIA_BANCARIA_SERVICE_NAME = "cargoTransferenciaBancariaService";
	private static final String CARGO_TRANSFERENCIA_CHEQUE_SERVICE_NAME = "cargoTransferenciaChequeService";
	private static final String RETENCION_A_COMERCIO_SERVICE_NAME = "retencionAComercioService";
	private static final String CODCOMERCIO_SERVICE_NAME = "codComercioService";
	private static final String CODCOMERCIO_ACTIVIDAD_SERVICE_NAME = "codComercioActividadService";
	private static final String CODIGOAUTORIZACION_SERVICE_NAME = "codigoAutorizacionService";
	private static final String CLIENTECONCEPTO_SERVICE_NAME = "clienteConceptoService";
	private static final String COMERCIOFORMAPAGO_SERVICE_NAME = "comercioFormaPagoService";
	private static final String COMERCIOLISTAPRECIO_SERVICE_NAME = "comercioListaPrecioService";
	private static final String CONCEPTO_SERVICE_NAME = "conceptoService";
	private static final String CONCEPTO_FIEL_VERSION_SERVICE_NAME = "conceptoFielVersionService";
	private static final String CONCEPTODETALLE_SERVICE_NAME = "conceptoDetalleService";
	private static final String CONCEPTODETALLEREGLA_SERVICE_NAME = "conceptoDetalleReglaService";
	private static final String COMERCIOCONCEPTO_SERVICE_NAME = "comercioConceptoService";
	private static final String CONCEPTODETALLETARGET_SERVICE_NAME = "conceptoDetalleTargetService";
	private static final String CTACTECOMERCIO_SERVICE_NAME = "ctaCteComercioService";
	private static final String COLABORADOR_SERVICE_NAME = "colaboradorService";
	private static final String DETALLE_REGLA_PF_SERVICE_NAME = "detalleReglaPFService";
	private static final String GESTOR_LIQUIDACION_CLIENTE = "gestorLiquidacionClienteService";
	private static final String GESTOR_LIQUIDACION_COM = "gestorLiquidacionComService";
	private static final String GARANTE = "garanteServiceTarget";
	private static final String FORMAPAGOTRANSACCION_SERVICE_NAME = "formaPagoTransaccionService";
	private static final String LIQCLIENTE_SERVICE_NAME = "liqClienteService";
	private static final String LIQUIDACION_CLIENTES_SERVICE_NAME = "liquidacionClientesService";
	private static final String LIQCOMERCIO_SERVICE_NAME = "liqComercioService";
	private static final String LIQUIDACION_SERVICE_NAME = "liquidacionService";
	private static final String LIQCOMERCIO_LP_SERVICE_NAME = "liqComercioLPService";
	private static final String LIQCOMERCIODET_SERVICE_NAME = "liqComercioDetService";
	private static final String ITEM_LIQUIDACION_SERVICE_NAME = "itemLiquidacionService";
	private static final String LIQUIDACIONPARAMETRO_SERVICE_NAME = "liquidacionParametroService";
	private static final String LISTAPRECIO_SERVICE_NAME = "listaPrecioService";
	private static final String LISTAPRECIOVERSION_SERVICE_NAME = "listaPrecioVersionService";
	private static final String LISTA_PRECIO_PARA_LIQUIDAR_SERVICE_NAME = "listaPrecioParaLiquidarService";
	private static final String LIQUIDACION_LISTAS_PRECIOS_NAME = "liquidacionListasPreciosService";
	private static final String LOTE_COMERCIO_SERVICE_NAME = "loteComercioService";
	private static final String LOTE_COMERCIO_ITEM_SERVICE_NAME = "loteComercioItemService";
	private static final String ORIGENCONCEPTO_SERVICE_NAME = "origenConceptoService";
	private static final String ORIGENEN_SERVICE_NAME = "origenenService";
	private static final String PLASTICOCLIENTE_SERVICE_NAME = "plasticoClienteService";
	private static final String PLASTICOHISTORIAL_SERVICE_NAME = "plasticoHistorialServiceTarget";
	private static final String PLASTICO_LOTE_SERVICE_NAME = "plasticoLoteServiceTarget";
	private static final String REGLA_PF_SERVICE_NAME = "reglaPFService";
	private static final String TRANSACCION_SERVICE_NAME = "transaccionService";
	private static final String CTACTECLIENTE_SERVICE_NAME = "ctaCteClienteService";
	private static final String CTACTECLIENTEAUX_SERVICE_NAME = "ctaCteClienteAuxService";
	private static final String CLIENTE_TRANSACCION_SERVICE_NAME = "clienteTransaccionService";
	private static final String CLIENTE_LIQUIDACION_SERVICE_NAME = "clienteLiquidacionService";
	private static final String ARCHIVO_CUPONES_SERVICE_NAME = "archivoCuponesServiceTarget";
	private static final String MOTIVO_CIERRE_CUENTA_CLIENTE = "motivoCierreCuentaClienteService";
	private static final String CIERRE_CUENTA_CLIENTE = "cierreCuentaClienteService";
	private static final String ESTADO_CLIENTE = "estadoClienteService";
	private static final String PLASTICO_ESTADOS = "plasticoEstadosServiceTarget";
	// private static final String EVENTOS_CLIENTE_SERVICE_NAME = "eventosClienteService";
	private static final String PLASTICO_OPERACION = "plasticoOperacionService";
	private static final String ESTADO_LOTE = "estadoLoteService";
	private static final String CLIADICIONAL_SERVICE_NAME = "cliAdicionalService";
	private static final String CLIGARANTE_SERVICE_NAME = "cliGaranteService";
	private static final String CLIMARCA_SERVICE_NAME = "cliMarcaService";
	private static final String CLIPLASTICO_SERVICE_NAME = "cliPlasticoService";
	private static final String CLISALDOACTUAL_SERVICE_NAME = "cliSaldoActualService";
	private static final String CLISITUACIONLOG_SERVICE_NAME = "cliSituacionLogService";
	private static final String CLICLIENTE_SERVICE_NAME = "cliClienteService";
	private static final String CLICLIENTE2_SERVICE_NAME = "cliCliente2Service";
	private static final String CLIIDIVIDUO_SERVICE_NAME = "cliIndividuoService";
	private static final String CLICOBSITLOG_SERVICE_NAME = "cliCobSitLogService";
	private static final String CLICCREDPEND_SERVICE_NAME = "cliCredPendService";
	private static final String PLASTICO_LUGAR = "plasticoLugarServiceTarget";
	private static final String CONCILIACIONCONSUMOSSERVICES = "conciliacionConsumosServices";

	private static final String LINEA_CRED_COMPOS = "lineaCredComposService";
	private static final String LINEA_CRED_HISTORICO = "lineaCredHistoricoService";
	private static final String RETENCION_COMERCIO_SICORE_SERVICE_NAME = "retencionComercioSICOREService";

	private static final String CALCULO_INSTERES_COMPENSATORIO = "calculoInteresComService";
	private static final String CALCULO_CUOTA = "calculoCuotaServicesTarget";

	private static final String ARCHIVOS_AFIP = "archivosAFIPService";

	private static final String GENERARCION_CLIENTES_OCA_SERVICE_NAME = "generacionClientesOcaService";

	private CliAdicionalService cliAdicionalService;
	private CliGaranteService cliGaranteService;
	private CliMarcaService cliMarcaService;
	private CliPlasticoService cliPlasticoService;
	private CliSaldoActualService cliSaldoActualService;
	private CliSituacionLogService cliSituacionLogService;
	private CliClienteService cliClienteService;
	private CliCliente2Service cliCliente2Service;
	private CliIndividuoService cliIndividuoService;
	private CliCobSitLogService cliCobSitLogService;
	private CliCredPendService cliCredPendService;
	private TipoAsesorComercialService tipoAsesorComercialService;

	private ArchivoDebitoConfService archivoDebitoConfService = null;
	private PdfLiqComercioService pdfLiqComercioService = null;
	private ArchivoDebitoHistService archivoDebitoHistService = null;
	private CargoResumenServices cargoResumenService = null;
	private CargoAnualServices cargoAnualService = null;
	private RetencionAComercioService retencionAComercioService = null;
	private NotaDebitoSinIvaServices notaDebitoSinIvaService = null;
	private NotaDebitoConIvaServices notaDebitoConIvaService = null;
	private NotaCreditoSinIvaServices notaCreditoSinIvaService = null;
	private NotaCreditoConIvaServices notaCreditoConIvaService = null;
	private InteresMoraService interesMoraService = null;
	private SeguroDeVidaService seguroDeVidaService = null;
	private CargoTransferenciaBancariaService cargoTransferenciaBancariaService = null;
	private CargoTransferenciaChequeService cargoTransferenciaChequeService = null;
	private CodComercioService codComercioService = null;
	private CodComercioActividadService codComercioActividadService = null;
	private CodigoAutorizacionService codigoAutorizacionService = null;
	private ClienteConceptoService clienteConceptoService = null;
	private ComercioFormaPagoService comercioFormaPagoService = null;
	private ComercioListaPrecioService comercioListaPrecioService = null;
	private ConceptoService conceptoService = null;
	private ConceptoFielVersionService conceptoFielVersionService = null;
	private ConceptoDetalleService conceptoDetalleService = null;
	private ConceptoDetalleReglaService conceptoDetalleReglaService = null;
	private ComercioConceptoService comercioConceptoService = null;
	private ConceptoDetalleTargetService conceptoDetalleTargetService = null;
	private CtaCteComercioService ctaCteComercioService = null;
	private ColaboradorService colaboradorService = null;
	private DetalleReglaPFService detalleReglaPFService = null;
	private FormaPagoTransaccionService formaPagoTransaccionService = null;
	private GestorLiquidacionClienteService gestorLiquidacionClienteService = null;
	private GestorLiquidacionComService gestorLiquidacionComService = null;
	private LiqClienteService liqClienteService = null;
	private LiquidacionClientesService liquidacionClientesService = null;
	private LiqComercioService liqComercioService = null;
	private LiquidacionService liquidacionService = null;
	private LiqComercioLPService liqComercioLPService = null;
	private LiqComercioDetService liqComercioDetService = null;
	private ItemLiquidacionService itemLiquidacionService = null;
	private EventosClienteService eventosClienteService = null;
	private ListaPrecioService listaPrecioService = null;
	private ListaPrecioVersionService listaPrecioVersionService = null;
	private ListaPrecioParaLiquidarService listaPrecioParaLiquidarService = null;
	private LiquidacionListasPreciosService liquidacionListasPreciosService = null;
	private LoteComercioService loteComercioService = null;
	private LoteComercioItemService loteComercioItemService = null;
	private OrigenConceptoService origenConceptoService = null;
	private OrigenenService origenenService = null;
	private PlasticoClienteService plasticoClienteService = null;
	private PlasticoHistorialService plasticoHistorialService = null;
	private ReglaPFService reglaPFService;
	private TransaccionService transaccionService = null;
	private CtaCteClienteService ctaCteClienteService = null;
	private CtaCteClienteAuxService ctaCteClienteAuxService = null;
	private PlasticoLugarService plasticoLugarService = null;
	private PlasticoLoteService plasticoLoteService = null;
	private GaranteService garanteService = null;
	private ClienteTransaccionService clienteTransaccionService = null;
	private ClienteLiquidacionService clienteLiquidacionService = null;
	private ConciliacionConsumosServices conciliacionConsumosServices = null;
	private LineaCredComposService lineaCredComposService = null;
	private LineaCredHistoricoService lineaCredHistoricoService = null;
	private ArchivoCuponesService archivoCuponesService = null;
	private RetencionComercioSICOREService retencionComercioSICOREService = null;
	private CalculoInteresComService calculoInteresComService = null;
	private CalculoCuotaServicesImpl calculoCuotaServicesTarget = null;
	private ArchivosAFIPService archivosAFIPService = null;
	private MotivoCierreCuentaClienteService motivoCierreCuentaClienteService = null;
	private CierreCuentaClienteService cierreCuentaClienteService = null;
	private EstadoClienteService estadoClienteService = null;
	private PlasticoEstadosService plasticoEstadosService = null;

	private GeneracionClientesOcaService generacionClientesOcaService = null;
	private PlasticoOperacionService plasticoOperacionService = null;
	private EstadoLoteService estadoLoteService = null;

	private TokenDao tokenDao = null;


	public TransaccionesServiceFaces() {
		super();
		this.sessionFactory = (SessionFactory) this.lookupService("sessionFactory");

		log.info("Construyendo el service de Transacciones!!!");
		this.cliAdicionalService = (CliAdicionalService) this.lookupService(CLIADICIONAL_SERVICE_NAME);
		this.cliGaranteService = (CliGaranteService) this.lookupService(CLIGARANTE_SERVICE_NAME);
		this.cliMarcaService = (CliMarcaService) this.lookupService(CLIMARCA_SERVICE_NAME);
		this.cliPlasticoService = (CliPlasticoService) this.lookupService(CLIPLASTICO_SERVICE_NAME);
		this.cliSaldoActualService = (CliSaldoActualService) this.lookupService(CLISALDOACTUAL_SERVICE_NAME);
		this.cliSituacionLogService = (CliSituacionLogService) this.lookupService(CLISITUACIONLOG_SERVICE_NAME);
		this.cliClienteService = (CliClienteService) this.lookupService(CLICLIENTE_SERVICE_NAME);
		this.cliCliente2Service = (CliCliente2Service) this.lookupService(CLICLIENTE2_SERVICE_NAME);
		this.cliIndividuoService = (CliIndividuoService) this.lookupService(CLIIDIVIDUO_SERVICE_NAME);
		this.cliCobSitLogService = (CliCobSitLogService) this.lookupService(CLICOBSITLOG_SERVICE_NAME);
		this.cliCredPendService = (CliCredPendService) this.lookupService(CLICCREDPEND_SERVICE_NAME);

		this.tipoAsesorComercialService = (TipoAsesorComercialService) this.lookupService(TIPOASESORCOMERCIAL_SERVICE_NAME);
		this.archivoDebitoConfService = (ArchivoDebitoConfService) this.lookupService(ARCHIVODEBITOCONF_SERVICE_NAME);
		this.pdfLiqComercioService = (PdfLiqComercioService) this.lookupService(PDFLIQCOMERCIO_SERVICE_NAME);
		this.archivoDebitoHistService = (ArchivoDebitoHistService) this.lookupService(ARCHIVODEBITOHIST_SERVICE);
		this.cargoResumenService = (CargoResumenServices) this.lookupService(CARGO_RESUMEN_SERVICE_NAME);
		this.cargoAnualService = (CargoAnualServices) this.lookupService(CARGO_ANUAL_NAME);
		this.retencionAComercioService = (RetencionAComercioService) this.lookupService(RETENCION_A_COMERCIO_SERVICE_NAME);

		this.notaCreditoConIvaService = (NotaCreditoConIvaServices) this.lookupService(NOTA_CREDITO_CON_IVA_NAME);
		this.notaCreditoSinIvaService = (NotaCreditoSinIvaServices) this.lookupService(NOTA_CREDITO_SIN_IVA_NAME);
		this.notaDebitoConIvaService = (NotaDebitoConIvaServices) this.lookupService(NOTA_DEBITO_CON_IVA_NAME);
		this.notaDebitoSinIvaService = (NotaDebitoSinIvaServices) this.lookupService(NOTA_DEBITO_SIN_IVA_NAME);

		this.interesMoraService = (InteresMoraService) this.lookupService(INTERES_COMPENSATORIO_SERVICE_NAME);
		this.seguroDeVidaService = (SeguroDeVidaService) this.lookupService(SEGURO_DE_VIDA_SERVICE_NAME);
		this.cargoTransferenciaBancariaService = (CargoTransferenciaBancariaService) this.lookupService(CARGO_TRANSFERENCIA_BANCARIA_SERVICE_NAME);
		this.cargoTransferenciaChequeService = (CargoTransferenciaChequeService) this.lookupService(CARGO_TRANSFERENCIA_CHEQUE_SERVICE_NAME);
		this.codComercioService = (CodComercioService) this.lookupService(CODCOMERCIO_SERVICE_NAME);
		this.codComercioActividadService = (CodComercioActividadService) this.lookupService(CODCOMERCIO_ACTIVIDAD_SERVICE_NAME);
		this.codigoAutorizacionService = (CodigoAutorizacionService) this.lookupService(CODIGOAUTORIZACION_SERVICE_NAME);
		this.clienteConceptoService = (ClienteConceptoService) this.lookupService(CLIENTECONCEPTO_SERVICE_NAME);
		this.comercioFormaPagoService = (ComercioFormaPagoService) this.lookupService(COMERCIOFORMAPAGO_SERVICE_NAME);
		this.comercioListaPrecioService = (ComercioListaPrecioService) this.lookupService(COMERCIOLISTAPRECIO_SERVICE_NAME);
		this.conceptoService = (ConceptoService) this.lookupService(CONCEPTO_SERVICE_NAME);
		this.conceptoFielVersionService = (ConceptoFielVersionService) this.lookupService(CONCEPTO_FIEL_VERSION_SERVICE_NAME);
		this.conceptoDetalleService = (ConceptoDetalleService) this.lookupService(CONCEPTODETALLE_SERVICE_NAME);
		this.conceptoDetalleReglaService = (ConceptoDetalleReglaService) this.lookupService(CONCEPTODETALLEREGLA_SERVICE_NAME);
		this.comercioConceptoService = (ComercioConceptoService) this.lookupService(COMERCIOCONCEPTO_SERVICE_NAME);
		this.conceptoDetalleTargetService = (ConceptoDetalleTargetService) this.lookupService(CONCEPTODETALLETARGET_SERVICE_NAME);
		this.ctaCteComercioService = (CtaCteComercioService) this.lookupService(CTACTECOMERCIO_SERVICE_NAME);
		this.colaboradorService = (ColaboradorService) this.lookupService(COLABORADOR_SERVICE_NAME);
		this.detalleReglaPFService = (DetalleReglaPFService) this.lookupService(DETALLE_REGLA_PF_SERVICE_NAME);
		this.formaPagoTransaccionService = (FormaPagoTransaccionService) this.lookupService(FORMAPAGOTRANSACCION_SERVICE_NAME);
		this.gestorLiquidacionClienteService = (GestorLiquidacionClienteService) this.lookupService(GESTOR_LIQUIDACION_CLIENTE);
		this.gestorLiquidacionComService = (GestorLiquidacionComService) this.lookupService(GESTOR_LIQUIDACION_COM);
		this.liqClienteService = (LiqClienteService) this.lookupService(LIQCLIENTE_SERVICE_NAME);
		this.liqComercioService = (LiqComercioService) this.lookupService(LIQCOMERCIO_SERVICE_NAME);
		this.liquidacionClientesService = (LiquidacionClientesService) this.lookupService(LIQUIDACION_CLIENTES_SERVICE_NAME);
		this.liquidacionService = (LiquidacionService) this.lookupService(LIQUIDACION_SERVICE_NAME);
		this.liqComercioLPService = (LiqComercioLPService) this.lookupService(LIQCOMERCIO_LP_SERVICE_NAME);
		this.liqComercioDetService = (LiqComercioDetService) this.lookupService(LIQCOMERCIODET_SERVICE_NAME);
		this.itemLiquidacionService = (ItemLiquidacionService) this.lookupService(ITEM_LIQUIDACION_SERVICE_NAME);
		this.listaPrecioService = (ListaPrecioService) this.lookupService(LISTAPRECIO_SERVICE_NAME);
		this.listaPrecioVersionService = (ListaPrecioVersionService) this.lookupService(LISTAPRECIOVERSION_SERVICE_NAME);
		this.listaPrecioParaLiquidarService = (ListaPrecioParaLiquidarService) this.lookupService(LISTA_PRECIO_PARA_LIQUIDAR_SERVICE_NAME);
		this.liquidacionListasPreciosService = (LiquidacionListasPreciosService) this.lookupService(LIQUIDACION_LISTAS_PRECIOS_NAME);
		this.loteComercioService = (LoteComercioService) this.lookupService(LOTE_COMERCIO_SERVICE_NAME);
		this.loteComercioItemService = (LoteComercioItemService) this.lookupService(LOTE_COMERCIO_ITEM_SERVICE_NAME);
		this.origenConceptoService = (OrigenConceptoService) this.lookupService(ORIGENCONCEPTO_SERVICE_NAME);
		this.origenenService = (OrigenenService) this.lookupService(ORIGENEN_SERVICE_NAME);
		this.plasticoClienteService = (PlasticoClienteService) this.lookupService(PLASTICOCLIENTE_SERVICE_NAME);
		this.plasticoHistorialService = (PlasticoHistorialService) this.lookupService(PLASTICOHISTORIAL_SERVICE_NAME);
		this.plasticoLoteService = (PlasticoLoteService) this.lookupService(PLASTICO_LOTE_SERVICE_NAME);
		this.reglaPFService = (ReglaPFService) this.lookupService(REGLA_PF_SERVICE_NAME);
		this.transaccionService = (TransaccionService) this.lookupService(TRANSACCION_SERVICE_NAME);
		this.ctaCteClienteService = (CtaCteClienteService) this.lookupService(CTACTECLIENTE_SERVICE_NAME);
		this.ctaCteClienteAuxService = (CtaCteClienteAuxService) this.lookupService(CTACTECLIENTEAUX_SERVICE_NAME);
		this.clienteTransaccionService = (ClienteTransaccionService) this.lookupService(CLIENTE_TRANSACCION_SERVICE_NAME);
		this.clienteLiquidacionService = (ClienteLiquidacionService) this.lookupService(CLIENTE_LIQUIDACION_SERVICE_NAME);
		this.archivoCuponesService = (ArchivoCuponesService) this.lookupService(ARCHIVO_CUPONES_SERVICE_NAME);
		this.plasticoLugarService = (PlasticoLugarService) this.lookupService(PLASTICO_LUGAR);
		this.garanteService = (GaranteService) this.lookupService(GARANTE);
		// this.eventosClienteService = (EventosClienteService)this.lookupService(EVENTOS_CLIENTE_SERVICE_NAME);
		this.lineaCredComposService = (LineaCredComposService) this.lookupService(LINEA_CRED_COMPOS);
		this.lineaCredHistoricoService = (LineaCredHistoricoService) this.lookupService(LINEA_CRED_HISTORICO);
		this.tokenDao = (TokenDao) this.lookupService("TokenDao");
		this.conciliacionConsumosServices = (ConciliacionConsumosServices) this.lookupService(CONCILIACIONCONSUMOSSERVICES);
		this.retencionComercioSICOREService = (RetencionComercioSICOREService) this.lookupService(RETENCION_COMERCIO_SICORE_SERVICE_NAME);
		this.calculoInteresComService = (CalculoInteresComService) this.lookupService(CALCULO_INSTERES_COMPENSATORIO);
		this.calculoCuotaServicesTarget = (CalculoCuotaServicesImpl) this.lookupService(CALCULO_CUOTA);
		this.archivosAFIPService = (ArchivosAFIPService) this.lookupService(ARCHIVOS_AFIP);
		this.motivoCierreCuentaClienteService = (MotivoCierreCuentaClienteService) this.lookupService(MOTIVO_CIERRE_CUENTA_CLIENTE);
		this.cierreCuentaClienteService = (CierreCuentaClienteService) this.lookupService(CIERRE_CUENTA_CLIENTE);
		this.estadoClienteService = (EstadoClienteService) this.lookupService(ESTADO_CLIENTE);
		this.plasticoEstadosService = (PlasticoEstadosService) this.lookupService(PLASTICO_ESTADOS);
		this.generacionClientesOcaService = (GeneracionClientesOcaService) this.lookupService(GENERARCION_CLIENTES_OCA_SERVICE_NAME);
		this.plasticoOperacionService = (PlasticoOperacionService) this.lookupService(PLASTICO_OPERACION);
		this.estadoLoteService = (EstadoLoteService) this.lookupService(ESTADO_LOTE);
	}


	public ArchivoCuponesService getArchivoCuponesService() {
		return archivoCuponesService;
	}


	public void setArchivoCuponesService(ArchivoCuponesService archivoCuponesService) {
		this.archivoCuponesService = archivoCuponesService;
	}


	public ClienteLiquidacionService getClienteLiquidacionService() {
		return clienteLiquidacionService;
	}


	public GaranteService getGaranteService() {
		return garanteService;
	}


	public void setGaranteService(GaranteService garanteService) {
		this.garanteService = garanteService;
	}


	public CliAdicionalService getCliAdicionalService() {
		return this.cliAdicionalService;
	}


	public CliGaranteService getCliGaranteService() {
		return this.cliGaranteService;
	}


	public CliMarcaService getCliMarcaService() {
		return this.cliMarcaService;
	}


	public CliPlasticoService getCliPlasticoService() {
		return this.cliPlasticoService;
	}


	public CliSaldoActualService getCliSaldoActualService() {
		return this.cliSaldoActualService;
	}


	public CliSituacionLogService getCliSituacionLogService() {
		return this.cliSituacionLogService;
	}


	public CliClienteService getCliClienteService() {
		return this.cliClienteService;
	}


	public CliCliente2Service getCliCliente2Service() {
		return this.cliCliente2Service;
	}


	public CtaCteClienteAuxService getCtaCteClienteAuxService() {
		return ctaCteClienteAuxService;
	}


	public CliIndividuoService getCliIndividuoService() {
		return cliIndividuoService;
	}


	public CliCobSitLogService getCliCobSitLogService() {
		return cliCobSitLogService;
	}


	public CliCredPendService getCliCredPendService() {
		return cliCredPendService;
	}


	public CodComercioService getCodComercioService() {
		return codComercioService;
	}


	public CodigoAutorizacionService getCodigoAutorizacionService() {
		return this.codigoAutorizacionService;
	}


	public ClienteConceptoService getClienteConceptoService() {
		return this.clienteConceptoService;
	}


	public ComercioFormaPagoService getComercioFormaPagoService() {
		return this.comercioFormaPagoService;
	}


	public ComercioListaPrecioService getComercioListaPrecioService() {
		return this.comercioListaPrecioService;
	}


	public ConceptoService getConceptoService() {
		return this.conceptoService;
	}


	public ConceptoDetalleService getConceptoDetalleService() {
		return this.conceptoDetalleService;
	}


	public ConceptoDetalleReglaService getConceptoDetalleReglaService() {
		return this.conceptoDetalleReglaService;
	}


	public ComercioConceptoService getComercioConceptoService() {
		return this.comercioConceptoService;
	}


	public ConceptoDetalleTargetService getConceptoDetalleTargetService() {
		return this.conceptoDetalleTargetService;
	}


	public CtaCteComercioService getCtaCteComercioService() {
		return this.ctaCteComercioService;
	}


	public ColaboradorService getColaboradorService() {
		return this.colaboradorService;
	}


	public FormaPagoTransaccionService getFormaPagoTransaccionService() {
		return formaPagoTransaccionService;
	}


	public LiqClienteService getLiqClienteService() {
		return this.liqClienteService;
	}


	public LiqComercioService getLiqComercioService() {
		return this.liqComercioService;
	}


	public LiqComercioDetService getLiqComercioDetService() {
		return this.liqComercioDetService;
	}


	public ListaPrecioService getListaPrecioService() {
		return this.listaPrecioService;
	}


	public LoteComercioService getLoteComercioService() {
		return this.loteComercioService;
	}


	public OrigenConceptoService getOrigenConceptoService() {
		return this.origenConceptoService;
	}


	public OrigenenService getOrigenenService() {
		return this.origenenService;
	}


	public PlasticoClienteService getPlasticoClienteService() {
		return this.plasticoClienteService;
	}


	public TransaccionService getTransaccionService() {
		return this.transaccionService;
	}


	public CtaCteClienteService getCtaCteClienteService() {
		return this.ctaCteClienteService;
	}


	public PlasticoHistorialService getPlasticoHistorialService() {
		return plasticoHistorialService;
	}


	public void setPlasticoHistorialService(
			PlasticoHistorialService plasticoHistorialService) {
		this.plasticoHistorialService = plasticoHistorialService;
	}


	public ClienteTransaccionService getClienteTransaccionService() {
		return clienteTransaccionService;
	}


	public TokenDao getTokenDao() {
		return tokenDao;
	}


	public ListaPrecioVersionService getListaPrecioVersionService() {
		return listaPrecioVersionService;
	}


	public LoteComercioItemService getLoteComercioItemService() {
		return loteComercioItemService;
	}


	public TipoAsesorComercialService getTipoAsesorComercialService() {
		return tipoAsesorComercialService;
	}


	public ArchivoDebitoConfService getArchivoDebitoConfService() {
		return archivoDebitoConfService;
	}


	public ArchivoDebitoHistService getArchivoDebitoHistService() {
		return archivoDebitoHistService;
	}


	public LiquidacionListasPreciosService getLiquidacionListasPreciosService() {
		return liquidacionListasPreciosService;
	}


	public GestorLiquidacionComService getGestorLiquidacionComService() {
		return gestorLiquidacionComService;
	}


	public LiqComercioLPService getLiqComercioLPService() {
		return liqComercioLPService;
	}


	public CodComercioActividadService getCodComercioActividadService() {
		return codComercioActividadService;
	}


	public LiquidacionService getLiquidacionService() {
		return liquidacionService;
	}


	public GestorLiquidacionClienteService getGestorLiquidacionClienteService() {
		return gestorLiquidacionClienteService;
	}


	public LiquidacionClientesService getLiquidacionClientesService() {
		return liquidacionClientesService;
	}


	public ConceptoFielVersionService getConceptoFielVersionService() {
		return conceptoFielVersionService;
	}


	public DetalleReglaPFService getDetalleReglaPFService() {
		return detalleReglaPFService;
	}


	public ReglaPFService getReglaPFService() {
		return reglaPFService;
	}


	public CargoResumenServices getCargoResumenService() {
		return cargoResumenService;
	}


	public ItemLiquidacionService getItemLiquidacionService() {
		return itemLiquidacionService;
	}


	public void setItemLiquidacionService(
			ItemLiquidacionService itemLiquidacionService) {
		this.itemLiquidacionService = itemLiquidacionService;
	}


	public InteresMoraService getInteresMoraService() {
		return interesMoraService;
	}


	public SeguroDeVidaService getSeguroDeVidaService() {
		return seguroDeVidaService;
	}


	public CargoTransferenciaBancariaService getCargoTransferenciaBancariaService() {
		return cargoTransferenciaBancariaService;
	}


	public CargoTransferenciaChequeService getCargoTransferenciaChequeService() {
		return cargoTransferenciaChequeService;
	}


	public NotaDebitoSinIvaServices getNotaDebitoSinIvaService() {
		return notaDebitoSinIvaService;
	}


	public NotaDebitoConIvaServices getNotaDebitoConIvaService() {
		return notaDebitoConIvaService;
	}


	public NotaCreditoSinIvaServices getNotaCreditoSinIvaService() {
		return notaCreditoSinIvaService;
	}


	public NotaCreditoConIvaServices getNotaCreditoConIvaService() {
		return notaCreditoConIvaService;
	}


	public ListaPrecioParaLiquidarService getListaPrecioParaLiquidarService() {
		return listaPrecioParaLiquidarService;
	}


	public PlasticoLugarService getPlasticoLugarService() {
		return plasticoLugarService;
	}


	public ConciliacionConsumosServices getConciliacionConsumosServices() {
		return conciliacionConsumosServices;
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setLineaCredComposService(LineaCredComposService lineaCredComposService) {
		this.lineaCredComposService = lineaCredComposService;
	}


	public LineaCredComposService getLineaCredComposService() {
		return lineaCredComposService;
	}


	public void setLineaCredHistoricoService(LineaCredHistoricoService lineaCredHistoricoService) {
		this.lineaCredHistoricoService = lineaCredHistoricoService;
	}


	public LineaCredHistoricoService getLineaCredHistoricoService() {
		return lineaCredHistoricoService;
	}


	public PdfLiqComercioService getPdfLiqComercioService() {
		return pdfLiqComercioService;
	}


	public RetencionComercioSICOREService getRetencionComercioSICOREService() {
		return retencionComercioSICOREService;
	}


	public RetencionAComercioService getRetencionAComercioService() {
		return retencionAComercioService;
	}


	/**
	 * @param archivosAFIPService
	 *            the archivosAFIPService to set
	 */
	public void setArchivosAFIPService(ArchivosAFIPService archivosAFIPService) {
		this.archivosAFIPService = archivosAFIPService;
	}


	/**
	 * @return the archivosAFIPService
	 */
	public ArchivosAFIPService getArchivosAFIPService() {
		return archivosAFIPService;
	}


	public CalculoInteresComService getCalculoInteresComService() {
		return calculoInteresComService;
	}

	public CalculoCuotaServicesImpl getCalculoCuotaServicesTarget() {
		return calculoCuotaServicesTarget;
	}

	// get para acceso
	public GeneracionClientesOcaService getGeneracionClientesOcaService() {
		return generacionClientesOcaService;
	}


	/*
	 * public EventosClienteService getEventosClienteService() { return eventosClienteService; }
	 */

	public CargoAnualServices getCargoAnualService() {
		return cargoAnualService;
	}


	public void setCargoAnualService(CargoAnualServices cargoAnualService) {
		this.cargoAnualService = cargoAnualService;
	}


	public PlasticoLoteService getPlasticoLoteService() {
		return plasticoLoteService;
	}


	public void setPlasticoLoteService(PlasticoLoteService plasticoLoteService) {
		this.plasticoLoteService = plasticoLoteService;
	}


	public MotivoCierreCuentaClienteService getMotivoCierreCuentaClienteService() {
		return motivoCierreCuentaClienteService;
	}


	public CierreCuentaClienteService getCierreCuentaClienteService() {
		return cierreCuentaClienteService;
	}


	public EstadoClienteService getEstadoClienteService() {
		return estadoClienteService;
	}


	public PlasticoEstadosService getPlasticoEstadoService() {
		return plasticoEstadosService;
	}


	public PlasticoOperacionService getPlasticoOperacionService() {
		return plasticoOperacionService;
	}


	public EstadoLoteService getEstadoLoteService() {
		return estadoLoteService;
	}

}
