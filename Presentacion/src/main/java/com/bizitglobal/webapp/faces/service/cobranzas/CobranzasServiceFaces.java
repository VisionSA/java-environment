package com.bizitglobal.webapp.faces.service.cobranzas;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.cobranzas.dao.AccionDao;
import com.bizitglobal.tarjetafiel.cobranzas.dao.AccionVersionDao;
import com.bizitglobal.tarjetafiel.cobranzas.dao.EjecucionPlanDao;
import com.bizitglobal.tarjetafiel.cobranzas.dao.EtapaDao;
import com.bizitglobal.tarjetafiel.cobranzas.dao.EtapaVersionDao;
import com.bizitglobal.tarjetafiel.cobranzas.dao.PlanDao;
import com.bizitglobal.tarjetafiel.cobranzas.dao.PlanVersionDao;
import com.bizitglobal.tarjetafiel.cobranzas.service.AccionService;
import com.bizitglobal.tarjetafiel.cobranzas.service.AccionVersionService;
import com.bizitglobal.tarjetafiel.cobranzas.service.EjecucionPlanService;
import com.bizitglobal.tarjetafiel.cobranzas.service.EtapaService;
import com.bizitglobal.tarjetafiel.cobranzas.service.EtapaVersionService;
import com.bizitglobal.tarjetafiel.cobranzas.service.PlanService;
import com.bizitglobal.tarjetafiel.cobranzas.service.PlanVersionService;

import com.bizitglobal.webapp.faces.service.BaseService;


public class CobranzasServiceFaces extends BaseService {
	private static final Logger log = Logger.getLogger(CobranzasServiceFaces.class);

	// Definicion de los dao
	private static final String ACCION_DAO_NAME = "accionDao";
	private static final String ACCION_VERSION_DAO_NAME = "accionVersionDao";
	private static final String EJECUCION_PLAN_DAO_NAME = "ejecucionPlanDao";
	private static final String ETAPA_DAO_NAME = "etapaDao";
	private static final String ETAPA_VERSION_DAO_NAME = "etapaVersionDao";
	private static final String PLAN_DAO_NAME = "planDao";
	private static final String PLAN_VERSION_DAO_NAME = "planVersionDao";

	// Definicion de los Service
	private static final String ACCION_SERVICE_NAME = "accionService";
	private static final String ACCION_VERSION_SERVICE_NAME = "accionVersionService";
	private static final String EJECUCION_PLAN_SERVICE_NAME = "ejecucionPlanService";
	private static final String ETAPA_SERVICE_NAME = "etapaService";
	private static final String ETAPA_VERSION_SERVICE_NAME = "etapaVersionService";
	private static final String PLAN_SERVICE_NAME = "planService";
	private static final String PLAN_VERSION_SERVICE_NAME = "planVersionService";

	/*
	 * Objetos services para el modulo.
	 */
	private AccionService accionService;
	private AccionVersionService accionVersionService;
	private EjecucionPlanService ejecucionPlanService;
	private EtapaService etapaService;
	private EtapaVersionService etapaVersionService;
	private PlanService planService;
	private PlanVersionService planVersionService;

	/*
	 * Objetos Daos para el modulo.
	 */
	private AccionDao accionDao = null;
	private AccionVersionDao accionVersionDao = null;
	private EjecucionPlanDao ejecucionPlanDao = null;
	private EtapaDao etapaDao = null;
	private EtapaVersionDao etapaVersionDao = null;
	private PlanDao planDao = null;
	private PlanVersionDao planVersionDao = null;


	public CobranzasServiceFaces() {

		super();
		log.info("Construyendo el service de cobranzas!!!");
		this.accionDao = (AccionDao) this.lookupService(ACCION_DAO_NAME);
		this.accionVersionDao = (AccionVersionDao) this.lookupService(ACCION_VERSION_DAO_NAME);
		this.ejecucionPlanDao = (EjecucionPlanDao) this.lookupService(EJECUCION_PLAN_DAO_NAME);
		this.etapaDao = (EtapaDao) this.lookupService(ETAPA_DAO_NAME);
		this.etapaVersionDao = (EtapaVersionDao) this.lookupService(ETAPA_VERSION_DAO_NAME);
		this.planDao = (PlanDao) this.lookupService(PLAN_DAO_NAME);
		this.planVersionDao = (PlanVersionDao) this.lookupService(PLAN_VERSION_DAO_NAME);

		this.accionService = (AccionService) this.lookupService(ACCION_SERVICE_NAME);
		this.accionVersionService = (AccionVersionService) this.lookupService(ACCION_VERSION_SERVICE_NAME);
		this.ejecucionPlanService = (EjecucionPlanService) this.lookupService(EJECUCION_PLAN_SERVICE_NAME);
		this.etapaService = (EtapaService) this.lookupService(ETAPA_SERVICE_NAME);
		this.etapaVersionService = (EtapaVersionService) this.lookupService(ETAPA_VERSION_SERVICE_NAME);
		this.planService = (PlanService) this.lookupService(PLAN_SERVICE_NAME);
		this.planVersionService = (PlanVersionService) this.lookupService(PLAN_VERSION_SERVICE_NAME);
	}


	public AccionService getAccionService() {
		return accionService;
	}


	public void setAccionService(AccionService accionService) {
		this.accionService = accionService;
	}


	public AccionDao getAccionDao() {
		return accionDao;
	}


	public void setAccionDao(AccionDao accionDao) {
		this.accionDao = accionDao;
	}


	public AccionVersionService getAccionVersionService() {
		return accionVersionService;
	}


	public void setAccionVersionService(AccionVersionService accionVersionService) {
		this.accionVersionService = accionVersionService;
	}


	public EjecucionPlanService getEjecucionPlanService() {
		return ejecucionPlanService;
	}


	public void setEjecucionPlanService(EjecucionPlanService ejecucionPlanService) {
		this.ejecucionPlanService = ejecucionPlanService;
	}


	public EtapaVersionService getEtapaVersionService() {
		return etapaVersionService;
	}


	public void setEtapaVersionService(EtapaVersionService etapaVersionService) {
		this.etapaVersionService = etapaVersionService;
	}


	public PlanService getPlanService() {
		return planService;
	}


	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}


	public PlanVersionService getPlanVersionService() {
		return planVersionService;
	}


	public void setPlanVersionService(PlanVersionService planVersionService) {
		this.planVersionService = planVersionService;
	}


	public AccionVersionDao getAccionVersionDao() {
		return accionVersionDao;
	}


	public void setAccionVersionDao(AccionVersionDao accionVersionDao) {
		this.accionVersionDao = accionVersionDao;
	}


	public EjecucionPlanDao getEjecucionPlanDao() {
		return ejecucionPlanDao;
	}


	public void setEjecucionPlanDao(EjecucionPlanDao ejecucionPlanDao) {
		this.ejecucionPlanDao = ejecucionPlanDao;
	}


	public EtapaDao getEtapaDao() {
		return etapaDao;
	}


	public void setEtapaDao(EtapaDao etapaDao) {
		this.etapaDao = etapaDao;
	}


	public EtapaVersionDao getEtapaVersionDao() {
		return etapaVersionDao;
	}


	public void setEtapaVersionDao(EtapaVersionDao etapaVersionDao) {
		this.etapaVersionDao = etapaVersionDao;
	}


	public PlanDao getPlanDao() {
		return planDao;
	}


	public void setPlanDao(PlanDao planDao) {
		this.planDao = planDao;
	}


	public PlanVersionDao getPlanVersionDao() {
		return planVersionDao;
	}


	public void setPlanVersionDao(PlanVersionDao planVersionDao) {
		this.planVersionDao = planVersionDao;
	}


	public EtapaService getEtapaService() {
		return etapaService;
	}


	public void setEtapaService(EtapaService etapaService) {
		this.etapaService = etapaService;
	}

}