package com.bizitglobal.webapp.faces.service.general;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.general.dao.BancoDao;
import com.bizitglobal.tarjetafiel.general.dao.BarrioDao;
import com.bizitglobal.tarjetafiel.general.dao.ClienteDao;
import com.bizitglobal.tarjetafiel.general.dao.DomicilioDao;
import com.bizitglobal.tarjetafiel.general.dao.EmailDao;
import com.bizitglobal.tarjetafiel.general.dao.EmpresaDao;
import com.bizitglobal.tarjetafiel.general.dao.EstadoCivilDao;
import com.bizitglobal.tarjetafiel.general.dao.FormaPagoDao;
import com.bizitglobal.tarjetafiel.general.dao.IGenericDao;
import com.bizitglobal.tarjetafiel.general.dao.LocalidadDao;
import com.bizitglobal.tarjetafiel.general.dao.NoLaborableDao;
import com.bizitglobal.tarjetafiel.general.dao.PaisDao;
import com.bizitglobal.tarjetafiel.general.dao.PartidoDao;
import com.bizitglobal.tarjetafiel.general.dao.ProfesionDao;
import com.bizitglobal.tarjetafiel.general.dao.PropietarioViviendaDao;
import com.bizitglobal.tarjetafiel.general.dao.ProvinciaDao;
import com.bizitglobal.tarjetafiel.general.dao.SucEmpresaDao;
import com.bizitglobal.tarjetafiel.general.dao.SucEmpresasXDomicilioDao;
import com.bizitglobal.tarjetafiel.general.dao.SucursalFielDao;
import com.bizitglobal.tarjetafiel.general.dao.SucursalFormaPagoDao;
import com.bizitglobal.tarjetafiel.general.dao.TelefonoDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoCuentaBancoDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoDigitalDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoDocumentoDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoDomicilioDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoTelefonoDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoViviendaDao;
import com.bizitglobal.tarjetafiel.general.dao.VinculoDao;
import com.bizitglobal.tarjetafiel.general.dao.viejo.PeriodoEjercicioDao;
import com.bizitglobal.tarjetafiel.general.service.ActividadSucursalService;
import com.bizitglobal.tarjetafiel.general.service.ConceptoDetalleGenService;
import com.bizitglobal.tarjetafiel.general.service.ConceptoGenService;
import com.bizitglobal.tarjetafiel.general.service.AutonomoService;
import com.bizitglobal.tarjetafiel.general.service.ActividadRubroService;
import com.bizitglobal.tarjetafiel.general.service.BancoService;
import com.bizitglobal.tarjetafiel.general.service.BarrioService;
import com.bizitglobal.tarjetafiel.general.service.DomicilioService;
import com.bizitglobal.tarjetafiel.general.service.EmpresaService;
import com.bizitglobal.tarjetafiel.general.service.EsquemaReglaService;
import com.bizitglobal.tarjetafiel.general.service.EsquemaService;
import com.bizitglobal.tarjetafiel.general.service.FormaPagoService;
import com.bizitglobal.tarjetafiel.general.service.ImpresoraService;
import com.bizitglobal.tarjetafiel.general.service.KettleConfigService;
import com.bizitglobal.tarjetafiel.general.service.LocalidadService;
import com.bizitglobal.tarjetafiel.general.service.MonedaService;
import com.bizitglobal.tarjetafiel.general.service.NoLaborableService;
import com.bizitglobal.tarjetafiel.general.service.OcupacionService;
import com.bizitglobal.tarjetafiel.general.service.PaisService;
import com.bizitglobal.tarjetafiel.general.service.ParametroSistemaDetalleService;
import com.bizitglobal.tarjetafiel.general.service.ParametroSistemaService;
import com.bizitglobal.tarjetafiel.general.service.PartidoService;
import com.bizitglobal.tarjetafiel.general.service.PropietarioViviendaService;
import com.bizitglobal.tarjetafiel.general.service.ProvinciaService;
import com.bizitglobal.tarjetafiel.general.service.RankingProveedoresService;
import com.bizitglobal.tarjetafiel.general.service.ReglaService;
import com.bizitglobal.tarjetafiel.general.service.RubroService;
import com.bizitglobal.tarjetafiel.general.service.SucEmpresasXDomicilioService;
import com.bizitglobal.tarjetafiel.general.service.SucTelefonoService;
import com.bizitglobal.tarjetafiel.general.service.SucursalFielService;
import com.bizitglobal.tarjetafiel.general.service.SucursalFormaPagoService;
import com.bizitglobal.tarjetafiel.general.service.TamEmpresaService;
import com.bizitglobal.tarjetafiel.general.service.TipoComprobanteService;
import com.bizitglobal.tarjetafiel.general.service.TipoConceptoGenService;
import com.bizitglobal.tarjetafiel.general.service.TipoDocumentoService;
import com.bizitglobal.tarjetafiel.general.service.TipoDomicilioService;
import com.bizitglobal.tarjetafiel.general.service.TipoViviendaService;
import com.bizitglobal.tarjetafiel.general.service.TipoZonaService;
import com.bizitglobal.tarjetafiel.general.service.viejo.PlanCuentaService;
import com.bizitglobal.webapp.faces.service.BaseService;


public class GeneralServiceFaces extends BaseService {
	private static final Logger log = Logger.getLogger(GeneralServiceFaces.class);
	private static final String GENERIC_DAO = "genericDao";
	private static final String CLIENTE_DAO_NAME = "clienteDao";
	private static final String TIPO_COMPROBANTE_SERVICE_NAME = "tipoComprobanteService";
	private static final String PLAN_CUENTA_SERVICE_NAME = "planCuentaService";
	private static final String SUCURSALFIEL_DAO_NAME = "sucursalFielDao";
	private static final String SUCURSAL_FORMA_PAGO_DAO_NAME = "sucursalFormaPagoDao";
	private static final String LOCALIDAD_DAO_NAME = "localidadDao";
	private static final String PARTIDO_DAO_NAME = "partidoDao";
	private static final String TIPO_VIVIENDA_DAO_NAME = "tipoViviendaDao";
	private static final String TIPO_CUENTA_BANCO_DAO_NAME = "tipoCuentaBancoDao";
	private static final String TIPO_DOMICILIO_DAO_NAME = "tipoDomicilioDao";
	private static final String PAIS_DAO_NAME = "paisDao";
	private static final String BARRIO_DAO_NAME = "barrioDao";
	private static final String PROVINCIA_DAO_NAME = "provinciaDao";
	private static final String FORMAPAGO_DAO_NAME = "formaPagoDao";
	private static final String BANCO_DAO_NAME = "bancoNuevoDao";
	private static final String PERIODO_EJERCICIO_DAO_NAME = "periodoEjercicioDao";
	private static final String NO_LABORABLE_DAO_NAME = "noLaborableDao";
	private static final String EMAIL_DAO_NAME = "emailDao";
	private static final String TIPO_DOCUMENTO_DAO_NAME = "tipoDocumentoDao";
	private static final String TELEFONO_DAO_NAME = "telefonoDao";
	private static final String DOMICILIO_DAO_NAME = "domicilioDao";
	private static final String SUC_EMPRESA_X_DOMICILIO_DAO_NAME = "sucEmpresasXDomicilioDao";
	private static final String PROPIETARIO_VIVIENDA_DAO_NAME = "propietarioViviendaDao";
	private static final String TIPO_TELEFONO_DAO_NAME = "tipoTelefonoDao";
	private static final String ESTADO_CIVIL_DAO_NAME = "estadoCivilDao";
	private static final String PROFESION_DAO_NAME = "profesionDao";
	private static final String VINCULO_DAO_NAME = "vinculoDao";
	private static final String EMPRESA_DAO_NAME = "empresaDao";
	private static final String EMPRESA_SERVICE_NAME = "empresaService";
	private static final String SUC_EMPRESA_SERVICE_NAME = "sucEmpresaDao";
	private static final String BANCO_SERVICE_NAME = "bancoNuevoService";
	private static final String TIPO_DIGITALES_SERVICE_NAME = "tipoDigitalDao";
	private static final String SUC_TELEFONO_SERVICE_NAME = "sucTelefonoService";
	private static final String OCUPACION_SERVICE_NAME = "ocupacionService";
	private static final String BARRIO_SERVICE_NAME = "barrioService";
	private static final String FORMAPAGO_SERVICE_NAME = "formaPagoService";
	private static final String LOCALIDAD_SERVICE_NAME = "localidadService";
	private static final String MONEDA_SERVICE_NAME = "monedaService";
	private static final String NOLABORABLE_SERVICE_NAME = "noLaborableService";
	private static final String PAIS_SERVICE_NAME = "paisService";
	private static final String PARAMETRO_SISTEMA_SERVICE_NAME = "parametroSistemaService";
	private static final String PARAMETRO_SISTEMA_DETALLE_SERVICE_NAME = "parametroSistemaDetalleService";
	/* @I4503 */private static final String KETTLE_CONFIG_SERVICE_NAME = "kettleConfigService";
	/* @F4503 */private static final String PARTIDO_SERVICE_NAME = "partidoService";
	private static final String PROPIETARIO_VIVIENDA_SERVICE_NAME = "propietarioViviendaService";
	private static final String PROVINCIA_SERVICE_NAME = "provinciaService";
	private static final String SUCURSAL_SERVICE_NAME = "sucursalFielService";
	private static final String SUCURSAL_FORMA_PAGO_SERVICE_NAME = "sucursalFormaPagoService";
	private static final String TIPODOMICILIO_SERVICE_NAME = "tipoDomicilioService";
	private static final String DOMICILIO_SERVICE_NAME = "domicilioService";
	private static final String RANKING_PROVEEDORES_SERVICE_NAME = "rankingProveedoresService";
	private static final String TIPO_ZONA_SERVICE_NAME = "tipoZonaService";
	private static final String TIPO_VIVIENDA_SERVICE_NAME = "tipoViviendaService";
	private static final String AUTONOMO_SERVICE_NAME = "autonomoService";
	private static final String TIPO_DOCUMENTO_SERVICE_NAME = "tipoDocumentoService";
	private static final String RUBRO_SERVICE_NAME = "rubroService";
	private static final String TAMANIO_EMPRESA_SERVICE_NAME = "tamEmpresaService";
	private static final String REGLA_SERVICE = "reglaService";
	private static final String ESQUEMA_SERVICE = "esquemaService";
	private static final String ESQUEMA_REGLA_SERVICE = "esquemaReglaService";
	private static final String IMPRESORA_SERVICE_NAME = "impresoraService";
	private static final String CONCEPTO_GEN_SERVICE_NAME = "conceptoGenService";
	private static final String CONCEPTO_DETALLE_GEN_SERVICE_NAME = "conceptoDetalleGenService";
	private static final String ACTIVIDAD_RUBRO_SERVICE_NAME = "actividadRubroService";
	private static final String ACTIVIDAD_SUCURSAL_SERVICE_NAME = "actividadSucursalService";
	private static final String TIPO_CONCEPTO_GEN_SERVICE_NAME = "tipoConceptoGenService";
	private static final String SUC_EMPESA_X_DOMICILIO_SERVICE_NAME = "SucEmpresasXDomicilioService";

	/*
	 * Objetos services para el modulo.
	 */
	private TipoComprobanteService tipoComprobanteService;
	private PlanCuentaService planCuentaService;
	private BancoService bancoService;
	/*
	 * Objetos Daos para el modulo.
	 */
	private ClienteDao clienteDao = null;
	private SucursalFielDao sucursalDao = null;
	private LocalidadDao localidadDao = null;
	private PartidoDao partidoDao = null;
	private ProvinciaDao provinciaDao = null;
	private TipoViviendaDao tipoViviendaDao = null;
	private TipoCuentaBancoDao tipoCuentaBancoDao = null;
	private TipoDomicilioDao tipoDomicilioDao = null;
	private BarrioDao barrioDao = null;
	private PaisDao paisDao = null;
	private FormaPagoDao formaPagoDao = null;
	private BancoDao bancoDao;
	private PeriodoEjercicioDao periodoEjercicioDao;
	private NoLaborableDao noLaborableDao;
	private EmailDao emailDao;
	private TelefonoDao telefonoDao;
	private DomicilioDao domicilioDao;
	private SucEmpresasXDomicilioDao sucEmpresasXDomicilioDao;
	private PropietarioViviendaDao propietarioViviendaDao;
	private TipoDocumentoDao tipoDocumentoDao;
	private TipoTelefonoDao tipoTelefonoDao;
	private EstadoCivilDao estadoCivilDao;
	private ProfesionDao profesionDao;
	private VinculoDao vinculoDao;
	private EmpresaDao empresaDao;
	private SucEmpresaDao sucEmpresaDao;
	private TipoDigitalDao tipoDigitalDao;
	private SucursalFormaPagoDao sucursalFormaPagoDao = null;
	private SucTelefonoService sucTelefonoService;
	private OcupacionService ocupacionService;
	private BarrioService barrioService;
	private FormaPagoService formaPagoService;
	private LocalidadService localidadService;
	private MonedaService monedaService;
	private NoLaborableService noLaborableService;
	private PaisService paisService;
	private ParametroSistemaService parametroSistemaService;
	/* @I5636 */private ParametroSistemaDetalleService parametroSistemaDetalleService;
	/* @F5636 *//* @I4503 */private KettleConfigService kettleConfigService;
	/* @F4503 */private PartidoService partidoService;
	private PropietarioViviendaService propietarioViviendaService;
	private ProvinciaService provinciaService;
	private SucursalFielService sucursalFielService;
	private TipoDomicilioService tipoDomicilioService;
	private DomicilioService domicilioService;
	private RankingProveedoresService rankingProveedoresService;
	private TipoZonaService tipoZonaService;
	private EmpresaService empresaService;
	private TipoViviendaService tipoViviendaService;
	private AutonomoService autonomoService;
	private TipoDocumentoService tipoDocumentoService;
	private RubroService rubroService;
	private TamEmpresaService tamanioEmpresaService;
	private ReglaService reglaService;
	private EsquemaService esquemaService;
	private EsquemaReglaService esquemaReglaService;
	private SucursalFormaPagoService sucursalFormaPagoService = null;
	private ImpresoraService impresoraService;

	private ConceptoGenService conceptoGenService;
	private ConceptoDetalleGenService conceptoDetalleGenService;
	private TipoConceptoGenService tipoConceptoGenService;
	private SucEmpresasXDomicilioService sucEmpresasXDomicilioService;

	private ActividadRubroService actividadRubroService;
	private ActividadSucursalService actividadSucursalService;
	
	private IGenericDao genericDao;


	// private ParametroSistemaDetalleService parametroSistemaDetalleService;

	public GeneralServiceFaces() {
		super();
		log.info("Construyendo el service de general!!!");
		this.genericDao = (IGenericDao) this.lookupService(GENERIC_DAO);
		this.clienteDao = (ClienteDao) this.lookupService(CLIENTE_DAO_NAME);
		this.tipoComprobanteService = (TipoComprobanteService) this.lookupService(TIPO_COMPROBANTE_SERVICE_NAME);
		this.planCuentaService = (PlanCuentaService) this.lookupService(PLAN_CUENTA_SERVICE_NAME);
		this.sucursalDao = (SucursalFielDao) this.lookupService(SUCURSALFIEL_DAO_NAME);
		this.localidadDao = (LocalidadDao) this.lookupService(LOCALIDAD_DAO_NAME);
		this.partidoDao = (PartidoDao) this.lookupService(PARTIDO_DAO_NAME);
		this.tipoViviendaDao = (TipoViviendaDao) this.lookupService(TIPO_VIVIENDA_DAO_NAME);
		this.tipoCuentaBancoDao = (TipoCuentaBancoDao) this.lookupService(TIPO_CUENTA_BANCO_DAO_NAME);
		this.tipoDomicilioDao = (TipoDomicilioDao) this.lookupService(TIPO_DOMICILIO_DAO_NAME);
		this.paisDao = (PaisDao) this.lookupService(PAIS_DAO_NAME);
		this.provinciaDao = (ProvinciaDao) this.lookupService(PROVINCIA_DAO_NAME);
		this.barrioDao = (BarrioDao) this.lookupService(BARRIO_DAO_NAME);
		this.formaPagoDao = (FormaPagoDao) this.lookupService(FORMAPAGO_DAO_NAME);
		this.bancoDao = (BancoDao) this.lookupService(BANCO_DAO_NAME);
		this.periodoEjercicioDao = (PeriodoEjercicioDao) this.lookupService(PERIODO_EJERCICIO_DAO_NAME);
		this.noLaborableDao = (NoLaborableDao) this.lookupService(NO_LABORABLE_DAO_NAME);
		this.emailDao = (EmailDao) this.lookupService(EMAIL_DAO_NAME);
		this.telefonoDao = (TelefonoDao) this.lookupService(TELEFONO_DAO_NAME);
		this.sucEmpresasXDomicilioDao = (SucEmpresasXDomicilioDao) this.lookupService(SUC_EMPRESA_X_DOMICILIO_DAO_NAME);
		this.domicilioDao = (DomicilioDao) this.lookupService(DOMICILIO_DAO_NAME);
		this.propietarioViviendaDao = (PropietarioViviendaDao) this.lookupService(PROPIETARIO_VIVIENDA_DAO_NAME);
		this.tipoDocumentoDao = (TipoDocumentoDao) this.lookupService(TIPO_DOCUMENTO_DAO_NAME);
		this.tipoTelefonoDao = (TipoTelefonoDao) this.lookupService(TIPO_TELEFONO_DAO_NAME);
		this.estadoCivilDao = (EstadoCivilDao) this.lookupService(ESTADO_CIVIL_DAO_NAME);
		this.profesionDao = (ProfesionDao) this.lookupService(PROFESION_DAO_NAME);
		this.vinculoDao = (VinculoDao) this.lookupService(VINCULO_DAO_NAME);
		this.empresaDao = (EmpresaDao) this.lookupService(EMPRESA_DAO_NAME);
		this.empresaService = (EmpresaService) this.lookupService(EMPRESA_SERVICE_NAME);
		this.sucursalFormaPagoDao = (SucursalFormaPagoDao) this.lookupService(SUCURSAL_FORMA_PAGO_DAO_NAME);
		this.sucursalFormaPagoService = (SucursalFormaPagoService) this.lookupService(SUCURSAL_FORMA_PAGO_SERVICE_NAME);
		this.actividadRubroService = (ActividadRubroService) this.lookupService(ACTIVIDAD_RUBRO_SERVICE_NAME);
		this.actividadSucursalService = (ActividadSucursalService) this.lookupService(ACTIVIDAD_SUCURSAL_SERVICE_NAME);
		this.sucEmpresasXDomicilioService = (SucEmpresasXDomicilioService) this.lookupService(SUC_EMPESA_X_DOMICILIO_SERVICE_NAME);
		this.sucEmpresaDao = (SucEmpresaDao) this.lookupService(SUC_EMPRESA_SERVICE_NAME);
		this.bancoService = (BancoService) this.lookupService(BANCO_SERVICE_NAME);
		this.tipoDigitalDao = (TipoDigitalDao) this.lookupService(TIPO_DIGITALES_SERVICE_NAME);
		this.sucTelefonoService = (SucTelefonoService) this.lookupService(SUC_TELEFONO_SERVICE_NAME);
		this.ocupacionService = (OcupacionService) this.lookupService(OCUPACION_SERVICE_NAME);
		this.barrioService = (BarrioService) this.lookupService(BARRIO_SERVICE_NAME);
		this.formaPagoService = (FormaPagoService) this.lookupService(FORMAPAGO_SERVICE_NAME);
		this.localidadService = (LocalidadService) this.lookupService(LOCALIDAD_SERVICE_NAME);
		this.monedaService = (MonedaService) this.lookupService(MONEDA_SERVICE_NAME);
		this.noLaborableService = (NoLaborableService) this.lookupService(NOLABORABLE_SERVICE_NAME);
		this.paisService = (PaisService) this.lookupService(PAIS_SERVICE_NAME);
		this.partidoService = (PartidoService) this.lookupService(PARTIDO_SERVICE_NAME);
		this.propietarioViviendaService = (PropietarioViviendaService) this.lookupService(PROPIETARIO_VIVIENDA_SERVICE_NAME);
		this.provinciaService = (ProvinciaService) this.lookupService(PROVINCIA_SERVICE_NAME);
		this.sucursalFielService = (SucursalFielService) this.lookupService(SUCURSAL_SERVICE_NAME);
		this.tipoDomicilioService = (TipoDomicilioService) this.lookupService(TIPODOMICILIO_SERVICE_NAME);
		this.domicilioService = (DomicilioService) this.lookupService(DOMICILIO_SERVICE_NAME);
		this.rankingProveedoresService = (RankingProveedoresService) this.lookupService(RANKING_PROVEEDORES_SERVICE_NAME);
		this.tipoZonaService = (TipoZonaService) this.lookupService(TIPO_ZONA_SERVICE_NAME);
		this.tipoViviendaService = (TipoViviendaService) this.lookupService(TIPO_VIVIENDA_SERVICE_NAME);
		this.autonomoService = (AutonomoService) this.lookupService(AUTONOMO_SERVICE_NAME);
		this.tipoDocumentoService = (TipoDocumentoService) this.lookupService(TIPO_DOCUMENTO_SERVICE_NAME);
		this.tamanioEmpresaService = (TamEmpresaService) this.lookupService(TAMANIO_EMPRESA_SERVICE_NAME);
		this.rubroService = (RubroService) this.lookupService(RUBRO_SERVICE_NAME);
		this.reglaService = (ReglaService) this.lookupService(REGLA_SERVICE);
		this.esquemaService = (EsquemaService) this.lookupService(ESQUEMA_SERVICE);
		this.esquemaReglaService = (EsquemaReglaService) this.lookupService(ESQUEMA_REGLA_SERVICE);
		this.impresoraService = (ImpresoraService) this.lookupService(IMPRESORA_SERVICE_NAME);
		this.conceptoGenService = (ConceptoGenService) this.lookupService(CONCEPTO_GEN_SERVICE_NAME);
		this.conceptoDetalleGenService = (ConceptoDetalleGenService) this.lookupService(CONCEPTO_DETALLE_GEN_SERVICE_NAME);
		this.tipoConceptoGenService = (TipoConceptoGenService) this.lookupService(TIPO_CONCEPTO_GEN_SERVICE_NAME);
		this.parametroSistemaService = (ParametroSistemaService) this.lookupService(PARAMETRO_SISTEMA_SERVICE_NAME);
		/* @I5636 */this.parametroSistemaDetalleService = (ParametroSistemaDetalleService) this.lookupService(PARAMETRO_SISTEMA_DETALLE_SERVICE_NAME);
		/* @F5636 *//* @I4503 */this.kettleConfigService = (KettleConfigService) this.lookupService(KETTLE_CONFIG_SERVICE_NAME);
		/* @F4503 */}


	public TipoConceptoGenService getTipoConceptoGenService() {
		return tipoConceptoGenService;
	}


	public OcupacionService getOcupacionService() {
		return ocupacionService;
	}


	public IGenericDao getGenericDao() {
		return genericDao;
	}


	public void setGenericDao(IGenericDao genericDao) {
		this.genericDao = genericDao;
	}


	public BancoService getBancoService() {
		return bancoService;
	}


	public ClienteDao getClienteDao() {
		return clienteDao;
	}


	public BancoDao getBancoDao() {
		return bancoDao;
	}


	public ActividadRubroService getActividadRubroService() {
		return actividadRubroService;
	}


	public BarrioDao getBarrioDao() {
		return barrioDao;
	}


	public FormaPagoDao getFormaPagoDao() {
		return formaPagoDao;
	}


	public LocalidadDao getLocalidadDao() {
		return localidadDao;
	}


	public PaisDao getPaisDao() {
		return paisDao;
	}


	public PartidoDao getPartidoDao() {
		return partidoDao;
	}


	public PeriodoEjercicioDao getPeriodoEjercicioDao() {
		return periodoEjercicioDao;
	}


	public PlanCuentaService getPlanCuentaService() {
		return planCuentaService;
	}


	public ProvinciaDao getProvinciaDao() {
		return provinciaDao;
	}


	public SucursalFielDao getSucursalDao() {
		return sucursalDao;
	}


	public TipoComprobanteService getTipoComprobanteService() {
		return tipoComprobanteService;
	}


	public TipoDomicilioDao getTipoDomicilioDao() {
		return tipoDomicilioDao;
	}


	public TipoViviendaDao getTipoViviendaDao() {
		return tipoViviendaDao;
	}


	public NoLaborableDao getNoLaborableDao() {
		return noLaborableDao;
	}


	public EmailDao getEmailDao() {
		return emailDao;
	}


	public DomicilioDao getDomicilioDao() {
		return domicilioDao;
	}


	public TelefonoDao getTelefonoDao() {
		return telefonoDao;
	}


	public TipoCuentaBancoDao getTipoCuentaBancoDao() {
		return tipoCuentaBancoDao;
	}


	public PropietarioViviendaDao getPropietarioViviendaDao() {
		return propietarioViviendaDao;
	}


	public TipoDocumentoDao getTipoDocumentoDao() {
		return tipoDocumentoDao;
	}


	public TipoTelefonoDao getTipoTelefonoDao() {
		return tipoTelefonoDao;
	}


	public EstadoCivilDao getEstadoCivilDao() {
		return estadoCivilDao;
	}


	public ProfesionDao getProfesionDao() {
		return profesionDao;
	}


	public VinculoDao getVinculoDao() {
		return vinculoDao;
	}


	/**
	 * @return the empresaDao
	 */
	public EmpresaDao getEmpresaDao() {
		return empresaDao;
	}


	public EmpresaService getEmpresaService() {
		return empresaService;
	}


	public SucEmpresaDao getSucEmpresaDao() {
		return sucEmpresaDao;
	}


	/**
	 * @return the tipoDigitalDao
	 */
	public TipoDigitalDao getTipoDigitalDao() {
		return tipoDigitalDao;
	}


	/**
	 * @return the sucTelefonoService
	 */
	public SucTelefonoService getSucTelefonoService() {
		return sucTelefonoService;
	}


	public BarrioService getBarrioService() {
		return this.barrioService;
	}


	public FormaPagoService getFormaPagoService() {
		return this.formaPagoService;
	}


	public LocalidadService getLocalidadService() {
		return this.localidadService;
	}


	public MonedaService getMonedaService() {
		return this.monedaService;
	}


	public NoLaborableService getNoLaborableService() {
		return this.noLaborableService;
	}


	public PaisService getPaisService() {
		return this.paisService;
	}


	public PartidoService getPartidoService() {
		return this.partidoService;
	}


	public PropietarioViviendaService getPropietarioViviendaService() {
		return propietarioViviendaService;
	}


	public ProvinciaService getProvinciaService() {
		return this.provinciaService;
	}


	public SucursalFielService getSucursalFielService() {
		return this.sucursalFielService;
	}


	public TipoDomicilioService getTipoDomicilioService() {
		return this.tipoDomicilioService;
	}


	public DomicilioService getDomicilioService() {
		return domicilioService;
	}


	public RankingProveedoresService getRankingProveedoresService() {
		return rankingProveedoresService;
	}


	public TipoZonaService getTipoZonaService() {
		return tipoZonaService;
	}


	public AutonomoService getAutonomoService() {
		return autonomoService;
	}


	public TipoViviendaService getTipoViviendaService() {
		return tipoViviendaService;
	}


	public TipoDocumentoService getTipoDocumentoService() {
		return tipoDocumentoService;
	}


	public TamEmpresaService getTamanioEmpresaService() {
		return tamanioEmpresaService;
	}


	public RubroService getRubroService() {
		return rubroService;
	}


	public EsquemaReglaService getEsquemaReglaService() {
		return esquemaReglaService;
	}


	/* @I4503 */
	/* @F4503 */
	public EsquemaService getEsquemaService() {
		return esquemaService;
	}


	/* @I4503 */
	/* @F4503 */
	public ReglaService getReglaService() {
		return reglaService;
	}


	public SucursalFormaPagoDao getSucursalFormaPagoDao() {
		return sucursalFormaPagoDao;
	}


	/* @I4503 */
	/* @F4503 */
	public SucursalFormaPagoService getSucursalFormaPagoService() {
		return sucursalFormaPagoService;
	}


	/* @I4503 */
	/* @F4503 */
	public ImpresoraService getImpresoraService() {
		return this.impresoraService;
	}


	public ConceptoGenService getConceptoGenService() {
		return conceptoGenService;
	}


	public ConceptoDetalleGenService getConceptoDetalleGenService() {
		return conceptoDetalleGenService;
	}


	public ParametroSistemaService getParametroSistemaService() {
		return parametroSistemaService;
	}


	/* @I5636 */public ParametroSistemaDetalleService getParametroSistemaDetalleService() {
		return parametroSistemaDetalleService;
	}


	/* @F5636 */
	public ActividadSucursalService getActividadSucursalService() {
		return actividadSucursalService;
	}


	public SucEmpresasXDomicilioDao getSucEmpresasXDomicilioDao() {
		return sucEmpresasXDomicilioDao;
	}


	/**
	 * @id: 4503 Method: getKettleConfigService Description:getter del atributo
	 * @return
	 */
	public KettleConfigService getKettleConfigService() {
		return kettleConfigService;
	}


	/* @I4503 */
	/* @F4503 */
	public SucEmpresasXDomicilioService getSucEmpresasXDomicilioService() {
		return sucEmpresasXDomicilioService;
	}

	/* @I4503 */
	/* @F4503 */

}
