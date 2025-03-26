package com.bizitglobal.webapp.faces.service.proveedores;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.general.dao.BancoDao;
import com.bizitglobal.tarjetafiel.general.dao.BarrioDao;
import com.bizitglobal.tarjetafiel.general.dao.FormaPagoDao;
import com.bizitglobal.tarjetafiel.general.dao.LocalidadDao;
import com.bizitglobal.tarjetafiel.general.dao.PaisDao;
import com.bizitglobal.tarjetafiel.general.dao.PartidoDao;
import com.bizitglobal.tarjetafiel.general.dao.ProvinciaDao;
import com.bizitglobal.tarjetafiel.general.dao.SucursalFielDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoDomicilioDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoTelefonoDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoViviendaDao;
import com.bizitglobal.tarjetafiel.general.dao.viejo.PeriodoEjercicioDao;
import com.bizitglobal.tarjetafiel.general.service.TipoComprobanteService;
import com.bizitglobal.tarjetafiel.impuestos.dao.ActividadDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.IndividuoDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.TipoImpuestoDao;
import com.bizitglobal.tarjetafiel.proveedores.dao.ComprobanteDao;
import com.bizitglobal.tarjetafiel.proveedores.dao.ComprobanteImputadoDao;
import com.bizitglobal.tarjetafiel.proveedores.dao.CuotaComprobanteDao;
import com.bizitglobal.tarjetafiel.proveedores.dao.GrupoDao;
import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorCuotaComprobantesDao;
import com.bizitglobal.tarjetafiel.proveedores.reportes.ReporteProvSP;
import com.bizitglobal.tarjetafiel.proveedores.service.ComprobanteImputadoService;
import com.bizitglobal.tarjetafiel.proveedores.service.ComprobanteService;
import com.bizitglobal.tarjetafiel.proveedores.service.GrupoService;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorCtaCteService;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorCuotaComprobantesService;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorFormaPagoService;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorSICOREService;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorService;
import com.bizitglobal.tarjetafiel.proveedores.service.SubDiarioIvaService;
import com.bizitglobal.webapp.faces.service.BaseService;


public class ProveedoresServiceFaces extends BaseService {
	private static final Logger log = Logger.getLogger(ProveedoresServiceFaces.class);

	private static final String COMPROBANTE_SERVICE_NAME = "comprobanteService";
	private static final String PROVEEDOR_SERVICE_NAME = "proveedorService";
	private static final String TIPO_COMPROBANTE_SERVICE_NAME = "tipoComprobanteService";
	private static final String PROVEEDOR_FORMA_PAGO_SERVICE_NAME = "proveedorFormaPagoService";
	private static final String COMPROBANTE_IMPUTADO_SERVICE_NAME = "comprobanteImputadoService";
	private static final String PROVEEDOR_CTA_CTE_SERVICE_NAME = "proveedorCtaCteService";
	private static final String PROVEEDOR_CUOTA_COMPROBANTES_DAO_NAME = "proveedorCuotaComprobantesDao";
	private static final String SUCURSALFIEL_DAO_NAME = "sucursalFielDao";
	private static final String GRUPO_DAO_NAME = "grupoDao";
	private static final String ACTIVIDAD_DAO_NAME = "actividadDao";
	private static final String LOCALIDAD_DAO_NAME = "localidadDao";
	private static final String PARTIDO_DAO_NAME = "partidoDao";
	private static final String TIPO_VIVIENDA_DAO_NAME = "tipoViviendaDao";
	private static final String TIPO_DOMICILIO_DAO_NAME = "tipoDomicilioDao";
	private static final String TIPO_TELEFONO_DAO_NAME = "tipoTelefonoDao";
	private static final String PAIS_DAO_NAME = "paisDao";
	private static final String BARRIO_DAO_NAME = "barrioDao";
	private static final String PROVINCIA_DAO_NAME = "provinciaDao";
	private static final String FORMAPAGO_DAO_NAME = "formaPagoDao";
	private static final String TIPO_IMPUESTO_DAO_NAME = "tipoImpuestoDao";
	private static final String INDIVIDUO_DAO_NAME = "individuoDao";
	private static final String BANCO_DAO_NAME = "bancoNuevoDao";
	private static final String PERIODO_EJERCICIO_DAO_NAME = "periodoEjercicioDao";
	private static final String COMPROBANTE_DAO_NAME = "comprobanteDao";
	private static final String CUOTA_COMPROBANTE_DAO_NAME = "cuotaComprobanteDao";
	private static final String PROVEEDOR_REPORTES_NAME = "proveedorReporteDao";
	private static final String COMPROBANTE_IMPUTADO_DAO_NAME = "comprobanteImputadoDao";
	private static final String GRUPO_SERVICE_NAME = "grupoService";
	private static final String SUB_DIARIO_IVA_SERVICE_NAME = "subDiarioIvaService";
	private static final String PROVEEDOR_SICORE_SERVICE_NAME = "proveedorSICOREService";
	private static final String PROVEEDOR_CUOTA_COMPROBANTES_SERVICE_NAME = "proveedorCuotaComprobantesService";

	/*
	 * Objetos services para el modulo proveedores.
	 */
	private ComprobanteService comprobanteService = null;
	private ProveedorService proveedorService = null;
	private TipoComprobanteService tipoComprobanteService = null;
	private ProveedorFormaPagoService proveedorFormaPagoService;
	private ComprobanteImputadoService comprobanteImputadoService;
	private ProveedorCtaCteService proveedorCtaCteService = null;
	private ProveedorSICOREService proveedorSICOREService = null;
	private ProveedorCuotaComprobantesService proveedorCuotaComprobantesService;

	/*
	 * Objetos Daos para el modulo proveedores.
	 */
	private SucursalFielDao sucursalDao = null;
	private GrupoDao grupoDao = null;
	private ActividadDao actividadDao = null;
	private LocalidadDao localidadDao = null;
	private PartidoDao partidoDao = null;
	private ProvinciaDao provinciaDao = null;
	private TipoViviendaDao tipoViviendaDao = null;
	private TipoDomicilioDao tipoDomicilioDao = null;
	private TipoTelefonoDao tipoTelefonoDao = null;
	private BarrioDao barrioDao = null;
	private PaisDao paisDao = null;
	private FormaPagoDao formaPagoDao = null;
	private TipoImpuestoDao tipoImpuestoDao = null;
	private IndividuoDao individuoDao = null;
	private BancoDao bancoDao;
	private PeriodoEjercicioDao periodoEjercicioDao;
	private ComprobanteDao comprobanteDao = null;
	private CuotaComprobanteDao cuotaComprobanteDao;
	private ReporteProvSP proveedorReporteDao;
	private ComprobanteImputadoDao comprobanteImputadoDao;
	private GrupoService grupoService;
	private SubDiarioIvaService subDiarioIvaService;
	private ProveedorCuotaComprobantesDao proveedorCuotaComprobantesDao;


	public ProveedoresServiceFaces() {
		super();
		log.info("Construyendo el service de proveedores!!!");
		this.comprobanteService = (ComprobanteService) this.lookupService(COMPROBANTE_SERVICE_NAME);
		this.proveedorService = (ProveedorService) this.lookupService(PROVEEDOR_SERVICE_NAME);
		this.tipoComprobanteService = (TipoComprobanteService) this.lookupService(TIPO_COMPROBANTE_SERVICE_NAME);
		this.sucursalDao = (SucursalFielDao) this.lookupService(SUCURSALFIEL_DAO_NAME);
		this.grupoDao = (GrupoDao) this.lookupService(GRUPO_DAO_NAME);
		this.actividadDao = (ActividadDao) this.lookupService(ACTIVIDAD_DAO_NAME);
		this.localidadDao = (LocalidadDao) this.lookupService(LOCALIDAD_DAO_NAME);
		this.partidoDao = (PartidoDao) this.lookupService(PARTIDO_DAO_NAME);
		this.tipoViviendaDao = (TipoViviendaDao) this.lookupService(TIPO_VIVIENDA_DAO_NAME);
		this.tipoDomicilioDao = (TipoDomicilioDao) this.lookupService(TIPO_DOMICILIO_DAO_NAME);
		this.tipoTelefonoDao = (TipoTelefonoDao) this.lookupService(TIPO_TELEFONO_DAO_NAME);
		this.paisDao = (PaisDao) this.lookupService(PAIS_DAO_NAME);
		this.provinciaDao = (ProvinciaDao) this.lookupService(PROVINCIA_DAO_NAME);
		this.barrioDao = (BarrioDao) this.lookupService(BARRIO_DAO_NAME);
		this.formaPagoDao = (FormaPagoDao) this.lookupService(FORMAPAGO_DAO_NAME);
		this.tipoImpuestoDao = (TipoImpuestoDao) this.lookupService(TIPO_IMPUESTO_DAO_NAME);
		this.individuoDao = (IndividuoDao) this.lookupService(INDIVIDUO_DAO_NAME);
		this.bancoDao = (BancoDao) this.lookupService(BANCO_DAO_NAME);
		this.periodoEjercicioDao = (PeriodoEjercicioDao) this.lookupService(PERIODO_EJERCICIO_DAO_NAME);
		this.comprobanteDao = (ComprobanteDao) this.lookupService(COMPROBANTE_DAO_NAME);
		this.cuotaComprobanteDao = (CuotaComprobanteDao) this.lookupService(CUOTA_COMPROBANTE_DAO_NAME);
		this.comprobanteImputadoService = (ComprobanteImputadoService) this.lookupService(COMPROBANTE_IMPUTADO_SERVICE_NAME);
		this.comprobanteImputadoDao = (ComprobanteImputadoDao) this.lookupService(COMPROBANTE_IMPUTADO_DAO_NAME);
		this.proveedorFormaPagoService = (ProveedorFormaPagoService) this.lookupService(PROVEEDOR_FORMA_PAGO_SERVICE_NAME);
		this.proveedorReporteDao = (ReporteProvSP) this.lookupService(PROVEEDOR_REPORTES_NAME);
		this.proveedorCtaCteService = (ProveedorCtaCteService) this.lookupService(PROVEEDOR_CTA_CTE_SERVICE_NAME);
		this.grupoService = (GrupoService) this.lookupService(GRUPO_SERVICE_NAME);
		this.subDiarioIvaService = (SubDiarioIvaService) this.lookupService(SUB_DIARIO_IVA_SERVICE_NAME);
		this.proveedorSICOREService = (ProveedorSICOREService) this.lookupService(PROVEEDOR_SICORE_SERVICE_NAME);
		this.proveedorCuotaComprobantesDao = (ProveedorCuotaComprobantesDao) this.lookupService(PROVEEDOR_CUOTA_COMPROBANTES_DAO_NAME);
		this.proveedorCuotaComprobantesService = (ProveedorCuotaComprobantesService) this.lookupService(PROVEEDOR_CUOTA_COMPROBANTES_SERVICE_NAME);
	}


	public SubDiarioIvaService getSubDiarioIvaService() {
		return subDiarioIvaService;
	}


	public ProveedorCtaCteService getProveedorCtaCteService() {
		return proveedorCtaCteService;
	}


	public ComprobanteService getComprobanteService() {
		return this.comprobanteService;
	}


	public ProveedorService getProveedorService() {
		return proveedorService;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de GeneralService
	 */
	public TipoComprobanteService getTipoComprobanteService() {
		return tipoComprobanteService;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de GeneralService
	 */
	public SucursalFielDao getSucursalFielDao() {
		return sucursalDao;
	}


	public GrupoDao getGrupoDao() {
		return grupoDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de ImpuestoService
	 */
	public ActividadDao getActividadDao() {
		return actividadDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de GeneralService
	 */
	public LocalidadDao getLocalidadDao() {
		return localidadDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de GeneralService
	 */
	public ProvinciaDao getProvinciaDao() {
		return provinciaDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de GeneralService
	 */
	public PaisDao getPaisDao() {
		return paisDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de GeneralService
	 */
	public BarrioDao getBarrioDao() {
		return barrioDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de GeneralService
	 */
	public TipoViviendaDao getTipoViviendaDao() {
		return tipoViviendaDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de GeneralService
	 */
	public FormaPagoDao getFormaPagoDao() {
		return formaPagoDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de ImpuestoService
	 */
	public TipoImpuestoDao getTipoImpuestoDao() {
		return tipoImpuestoDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de ImpuestoService
	 */
	public IndividuoDao getIndividuoDao() {
		return individuoDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de GeneralService
	 */
	public TipoDomicilioDao getTipoDomicilioDao() {
		return tipoDomicilioDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de GeneralService
	 */
	public BancoDao getBancoDao() {
		return bancoDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de GeneralService
	 */
	public PeriodoEjercicioDao getPeriodoEjercicioDao() {
		return periodoEjercicioDao;
	}


	/**
	 * @return
	 * @deprecated Se debe usar el de GeneralService
	 */
	public PartidoDao getPartidoDao() {
		return partidoDao;
	}


	public ComprobanteDao getComprobanteDao() {
		return comprobanteDao;
	}


	public CuotaComprobanteDao getCuotaComprobanteDao() {
		return cuotaComprobanteDao;
	}


	public TipoTelefonoDao getTipoTelefonoDao() {
		return tipoTelefonoDao;
	}


	public ReporteProvSP getProveedorReporteDao() {
		return proveedorReporteDao;
	}


	public ProveedorFormaPagoService getProveedorFormaPagoService() {
		return proveedorFormaPagoService;
	}


	public ComprobanteImputadoService getComprobanteImputadoService() {
		return comprobanteImputadoService;
	}


	public ComprobanteImputadoDao getComprobanteImputadoDao() {
		return comprobanteImputadoDao;
	}


	public GrupoService getGrupoService() {
		return this.grupoService;
	}


	public SucursalFielDao getSucursalDao() {
		return sucursalDao;
	}


	public ProveedorSICOREService getProveedorSICOREService() {
		return proveedorSICOREService;
	}


	public ProveedorCuotaComprobantesDao getProveedorCuotaComprobantesDao() {
		return proveedorCuotaComprobantesDao;
	}


	public ProveedorCuotaComprobantesService getProveedorCuotaComprobantesService() {
		return proveedorCuotaComprobantesService;
	}

}
