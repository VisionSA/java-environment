package com.bizitglobal.webapp.faces.service.impuestos;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.impuestos.dao.ActividadDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.CategoriaDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.ExclusionDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.IndividuoDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisdiccionActividadDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisdiccionDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.TipoImpuestoDao;
import com.bizitglobal.tarjetafiel.impuestos.service.ActividadService;
import com.bizitglobal.tarjetafiel.impuestos.service.AplicableService;
import com.bizitglobal.tarjetafiel.impuestos.service.CategoriaService;
import com.bizitglobal.tarjetafiel.impuestos.service.ExclusionService;
import com.bizitglobal.tarjetafiel.impuestos.service.ImpuestoService;
import com.bizitglobal.tarjetafiel.impuestos.service.ImpuestosIndividuoService;
import com.bizitglobal.tarjetafiel.impuestos.service.IndividuoService;
import com.bizitglobal.tarjetafiel.impuestos.service.JurisTipoImpuestoService;
import com.bizitglobal.tarjetafiel.impuestos.service.JurisdiccionActividadService;
import com.bizitglobal.tarjetafiel.impuestos.service.JurisdiccionService;
import com.bizitglobal.tarjetafiel.impuestos.service.RetencionService;
import com.bizitglobal.tarjetafiel.impuestos.service.TipoImpuestoService;
import com.bizitglobal.tarjetafiel.impuestos.service.TramosRetencionService;
import com.bizitglobal.webapp.faces.service.BaseService;


public class ImpuestoServiceFaces extends BaseService {
	private static final Logger log = Logger.getLogger(ImpuestoServiceFaces.class);

	private static final String ACTIVIDAD_DAO_NAME = "actividadDao";
	private static final String TIPO_IMPUESTO_DAO_NAME = "tipoImpuestoDao";
	private static final String INDIVIDUO_DAO_NAME = "individuoDao";
	private static final String INDIVIDUO_SERVICE_NAME = "individuoService";
	private static final String CATEGORIA_SERVICE_NAME = "categoriaService";
	private static final String CATEGORIA_DAO_NAME = "categoriaDao";
	private static final String IMPUESTOS_INDIVIDUO_SERVICE_NAME = "impuestosIndividuoService";
	private static final String EXCLUSION_SERVICE_NAME = "exclusionService";
	private static final String EXCLUSION_DAO_NAME = "exclusionDao";
	private static final String RETENCION_SERVICE_NAME = "retencionService";
	private static final String TRAMO_SERVICE_NAME = "tramosRetencionService";
	private static final String APLICABLE_SERVICE_NAME = "aplicableService";
	private static final String JURISDICCION_ACTIVIDAD_SERVICE_NAME = "jurisdiccionActividadService";
	private static final String JURISDICCION_ACTIVIDAD_DAO_NAME = "jurisdiccionActividadDao";
	private static final String JURISDICCION__DAO_NAME = "jurisdiccionDao";
	private static final String ACTIVIDAD_SERVICE_NAME = "actividadService";
	private static final String IMPUESTO_SERVICE_NAME = "impuestoService";
	private static final String JURISDICCION_SERVICE_NAME = "jurisdiccionService";
	private static final String TIPOIMPUESTO_SERVICE_NAME = "tipoImpuestoService";
	private static final String JURIS_TIPO_IMPUESTO_SERVICE = "jurisTipoImpuestoService";

	/*
	 * Objetos Daos para el modulo impuestos.
	 */
	private ActividadDao actividadDao = null;
	private TipoImpuestoDao tipoImpuestoDao = null;
	private IndividuoDao individuoDao = null;
	private JurisdiccionActividadDao jurisdiccionActividadDao = null;
	private JurisdiccionDao jurisdiccionDao = null;
	private IndividuoService individuoService = null;
	private CategoriaService categoriaService = null;
	private CategoriaDao categoriaDao = null;
	private ImpuestosIndividuoService impuestosIndividuoService = null;
	private ExclusionService exclusionService = null;
	private ExclusionDao exclusionDao = null;
	private RetencionService retencionService = null;
	private TramosRetencionService tramosRetencionService = null;
	private AplicableService aplicableService = null;
	private JurisdiccionActividadService jurisdiccionActividadService = null;
	private ActividadService actividadService;
	private ImpuestoService impuestoService;
	private TipoImpuestoService tipoImpuestoService;
	private JurisdiccionService jurisdiccionService;
	private JurisTipoImpuestoService jurisTipoImpuestoService;


	public ImpuestoServiceFaces() {
		super();
		log.info("Construyendo el service de impuestos!!!");
		this.tipoImpuestoDao = (TipoImpuestoDao) this.lookupService(TIPO_IMPUESTO_DAO_NAME);
		this.individuoDao = (IndividuoDao) this.lookupService(INDIVIDUO_DAO_NAME);
		this.individuoService = (IndividuoService) this.lookupService(INDIVIDUO_SERVICE_NAME);
		this.actividadDao = (ActividadDao) this.lookupService(ACTIVIDAD_DAO_NAME);
		this.categoriaService = (CategoriaService) this.lookupService(CATEGORIA_SERVICE_NAME);
		this.categoriaDao = (CategoriaDao) this.lookupService(CATEGORIA_DAO_NAME);
		this.impuestosIndividuoService = (ImpuestosIndividuoService) this.lookupService(IMPUESTOS_INDIVIDUO_SERVICE_NAME);
		this.exclusionService = (ExclusionService) this.lookupService(EXCLUSION_SERVICE_NAME);
		this.exclusionDao = (ExclusionDao) this.lookupService(EXCLUSION_DAO_NAME);
		this.retencionService = (RetencionService) this.lookupService(RETENCION_SERVICE_NAME);
		this.tramosRetencionService = (TramosRetencionService) this.lookupService(TRAMO_SERVICE_NAME);
		this.aplicableService = (AplicableService) this.lookupService(APLICABLE_SERVICE_NAME);
		this.jurisdiccionActividadService = (JurisdiccionActividadService) this.lookupService(JURISDICCION_ACTIVIDAD_SERVICE_NAME);
		this.jurisdiccionActividadDao = (JurisdiccionActividadDao) this.lookupService(JURISDICCION_ACTIVIDAD_DAO_NAME);
		this.jurisdiccionDao = (JurisdiccionDao) this.lookupService(JURISDICCION__DAO_NAME);
		this.actividadService = (ActividadService) this.lookupService(ACTIVIDAD_SERVICE_NAME);
		this.impuestoService = (ImpuestoService) this.lookupService(IMPUESTO_SERVICE_NAME);
		this.tipoImpuestoService = (TipoImpuestoService) this.lookupService(TIPOIMPUESTO_SERVICE_NAME);
		this.jurisdiccionService = (JurisdiccionService) this.lookupService(JURISDICCION_SERVICE_NAME);
		this.jurisTipoImpuestoService = (JurisTipoImpuestoService) this.lookupService(JURIS_TIPO_IMPUESTO_SERVICE);
	}


	public ActividadDao getActividadDao() {
		return actividadDao;
	}


	public IndividuoDao getIndividuoDao() {
		return individuoDao;
	}


	public TipoImpuestoDao getTipoImpuestoDao() {
		return tipoImpuestoDao;
	}


	public JurisdiccionActividadDao getJurisdiccionActividadDao() {
		return jurisdiccionActividadDao;
	}


	public JurisTipoImpuestoService getJurisTipoImpuestoService() {
		return jurisTipoImpuestoService;
	}


	public JurisdiccionDao getJurisdiccionDao() {
		return jurisdiccionDao;
	}


	public CategoriaService getCategoriaService() {
		return categoriaService;
	}


	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}


	public IndividuoService getIndividuoService() {
		return individuoService;
	}


	public ExclusionService getExclusionService() {
		return exclusionService;
	}


	public ImpuestosIndividuoService getImpuestosIndividuoService() {
		return impuestosIndividuoService;
	}


	public RetencionService getRetencionService() {
		return retencionService;
	}


	public TramosRetencionService getTramosRetencionService() {
		return tramosRetencionService;
	}


	public AplicableService getAplicableService() {
		return aplicableService;
	}


	public JurisdiccionActividadService getJurisdiccionActividadService() {
		return jurisdiccionActividadService;
	}


	public ActividadService getActividadService() {
		return actividadService;
	}


	public ImpuestoService getImpuestoService() {
		return impuestoService;
	}


	public ExclusionDao getExclusionDao() {
		return exclusionDao;
	}


	public TipoImpuestoService getTipoImpuestoService() {
		return this.tipoImpuestoService;
	}


	public JurisdiccionService getJurisdiccionService() {
		return this.jurisdiccionService;
	}
}
