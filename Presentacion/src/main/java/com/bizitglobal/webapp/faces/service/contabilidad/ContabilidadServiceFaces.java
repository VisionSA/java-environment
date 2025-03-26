package com.bizitglobal.webapp.faces.service.contabilidad;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoClienteDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoComercioDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoDetalleDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoProveedorDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.BalanceDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.CentroCostosDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.CentroCostoAsientoDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.DocAdjuntoDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.EjercicioDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.LoteDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.LoteDetalleDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.OrigenDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.PlanCuentaDosDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.RenglonLibroMayorDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.TipoAsientoDao;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoClienteService;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoComercioService;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoProveedorService;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoService;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoDetalleService;
import com.bizitglobal.tarjetafiel.contabilidad.service.BalanceService;
import com.bizitglobal.tarjetafiel.contabilidad.service.CentroCostoAsientoService;
import com.bizitglobal.tarjetafiel.contabilidad.service.CentroCostosService;
import com.bizitglobal.tarjetafiel.contabilidad.service.DocAdjuntoService;
import com.bizitglobal.tarjetafiel.contabilidad.service.LoteService;
import com.bizitglobal.tarjetafiel.contabilidad.service.LoteDetalleService;
import com.bizitglobal.tarjetafiel.contabilidad.service.OrigenService;
import com.bizitglobal.tarjetafiel.contabilidad.service.PlanCuentaDosService;
import com.bizitglobal.tarjetafiel.contabilidad.service.EjercicioService;
import com.bizitglobal.tarjetafiel.contabilidad.service.RenglonLibroMayorService;
import com.bizitglobal.tarjetafiel.contabilidad.service.TipoAsientoService;
import com.bizitglobal.tarjetafiel.contabilidad.service.PasajeService;
import com.bizitglobal.webapp.faces.service.BaseService;


public class ContabilidadServiceFaces extends BaseService {
	private static final Logger log = Logger.getLogger(ContabilidadServiceFaces.class);

	// Definicion de los dao
	private static final String PLAN_CUENTA_DAO_NAME = "planCuentaDosDao";
	private static final String ASIENTO_DAO_NAME = "asientoDao";
	private static final String LOTE_DAO_NAME = "loteDao";
	private static final String BALANCE_DAO_NAME = "balanceDao";
	private static final String ASIENTO_DETALLE_DAO_NAME = "asientoDetalleDao";
	private static final String LOTE_DETALLE_DAO_NAME = "loteDetalleDao";
	private static final String CENTRO_COSTOS_DAO_NAME = "centroCostosDao";
	private static final String CENTRO_COSTO_ASIENTO_DAO_NAME = "centroCostoAsientoDao";
	private static final String ORIGEN_DAO_NAME = "origenDao";
	private static final String TIPO_ASIENTO_DAO_NAME = "tipoAsientoDao";
	private static final String EJERCICIO_DAO_NAME = "ejercicioDao";
	private static final String RENGLON_LIBRO_MAYOR_DAO_NAME = "renglonLibroMayorDao";
	private static final String ASIENTO_PROVEEDOR_DAO_NAME = "asientoProveedorDao";
	private static final String ASIENTO_CLIENTE_DAO_NAME = "asientoClienteDao";
	private static final String ASIENTO_COMERCIO_DAO_NAME = "asientoComercioDao";
	private static final String DOC_ADJUNTO_DAO_NAME = "docAdjuntoDao";

	// Definicion de los Service
	private static final String PLAN_CUENTA_SERVICE_NAME = "planCuentaDosService";
	private static final String ASIENTO_SERVICE_NAME = "asientoService";
	private static final String LOTE_SERVICE_NAME = "loteService";
	private static final String BALANCE_SERVICE_NAME = "balanceService";
	private static final String LOTE_DETALLE_SERVICE_NAME = "loteDetalleService";
	private static final String ASIENTO_DETALLE_SERVICE_NAME = "asientoDetalleService";
	private static final String CENTRO_COSTOS_SERVICE_NAME = "centroCostosService";
	private static final String CENTRO_COSTO_ASIENTO_SERVICE_NAME = "centroCostoAsientoService";
	private static final String ORIGEN_SERVICE_NAME = "origenService";
	private static final String TIPO_ASIENTO_SERVICE_NAME = "tipoAsientoService";
	private static final String EJERCICIO_SERVICE_NAME = "ejercicioService";
	private static final String RENGLON_LIBRO_MAYOR_SERVICE_NAME = "renglonLibroMayorService";
	private static final String ASIENTO_PROVEEDOR_SERVICE_NAME = "asientoProveedorService";
	private static final String ASIENTO_CLIENTE_SERVICE_NAME = "asientoClienteService";
	private static final String ASIENTO_COMERCIO_SERVICE_NAME = "asientoComercioService";
	private static final String DOC_ADJUNTO_SERVICE = "docAdjuntoService";
	/* @F4933 */private static final String PASAJE_SERVICE = "generarPasajeService";

	/*
	 * Objetos services para el modulo.
	 */
	private PlanCuentaDosService planCuentaDosService;
	private AsientoService asientoService;
	private LoteService loteService;
	private BalanceService balanceService;
	private CentroCostosService centroCostosService;
	private CentroCostoAsientoService centroCostoAsientoService;
	private OrigenService origenService;
	private TipoAsientoService tipoAsientoService;
	private EjercicioService ejercicioService;
	private AsientoDetalleService asientoDetalleService;
	private LoteDetalleService loteDetalleService;
	private RenglonLibroMayorService renglonLibroMayorService;
	private AsientoProveedorService asientoProveedorService;
	private AsientoClienteService asientoClienteService;
	private AsientoComercioService asientoComercioService;
	private DocAdjuntoService docAdjuntoService;
	/* @F4933 */private PasajeService pasajeService;

	/*
	 * Objetos Daos para el modulo.
	 */
	private PlanCuentaDosDao planCuentaDosDao = null;
	private AsientoDao asientoDao = null;
	private LoteDao loteDao = null;
	private BalanceDao balanceDao = null;
	private CentroCostosDao centroCostosDao = null;
	private CentroCostoAsientoDao centroCostoAsientoDao = null;
	private OrigenDao origenDao = null;
	private TipoAsientoDao tipoAsientoDao = null;
	private EjercicioDao ejercicioDao = null;
	private AsientoDetalleDao asientoDetalleDao = null;
	private LoteDetalleDao loteDetalleDao = null;
	private RenglonLibroMayorDao renglonLibroMayorDao = null;
	private AsientoProveedorDao asientoProveedorDao = null;
	private AsientoClienteDao asientoClienteDao = null;
	private AsientoComercioDao asientoComercioDao = null;
	private DocAdjuntoDao docAdjuntoDao = null;


	public ContabilidadServiceFaces() {

		super();
		log.info("Construyendo el service de contabilidad!!!");
		this.planCuentaDosDao = (PlanCuentaDosDao) this.lookupService(PLAN_CUENTA_DAO_NAME);
		this.planCuentaDosService = (PlanCuentaDosService) this.lookupService(PLAN_CUENTA_SERVICE_NAME);
		this.asientoDao = (AsientoDao) this.lookupService(ASIENTO_DAO_NAME);
		this.loteDao = (LoteDao) this.lookupService(LOTE_DAO_NAME);
		this.balanceDao = (BalanceDao) this.lookupService(BALANCE_DAO_NAME);
		this.asientoDetalleDao = (AsientoDetalleDao) this.lookupService(ASIENTO_DETALLE_DAO_NAME);
		this.asientoService = (AsientoService) this.lookupService(ASIENTO_SERVICE_NAME);
		this.loteService = (LoteService) this.lookupService(LOTE_SERVICE_NAME);
		this.balanceService = (BalanceService) this.lookupService(BALANCE_SERVICE_NAME);
		this.asientoDetalleService = (AsientoDetalleService) this.lookupService(ASIENTO_DETALLE_SERVICE_NAME);
		this.loteDetalleService = (LoteDetalleService) this.lookupService(LOTE_DETALLE_SERVICE_NAME);
		this.centroCostosDao = (CentroCostosDao) this.lookupService(CENTRO_COSTOS_DAO_NAME);
		this.centroCostosService = (CentroCostosService) this.lookupService(CENTRO_COSTOS_SERVICE_NAME);
		this.centroCostoAsientoDao = (CentroCostoAsientoDao) this.lookupService(CENTRO_COSTO_ASIENTO_DAO_NAME);
		this.centroCostoAsientoService = (CentroCostoAsientoService) this.lookupService(CENTRO_COSTO_ASIENTO_SERVICE_NAME);
		this.origenDao = (OrigenDao) this.lookupService(ORIGEN_DAO_NAME);
		this.origenService = (OrigenService) this.lookupService(ORIGEN_SERVICE_NAME);
		this.tipoAsientoDao = (TipoAsientoDao) this.lookupService(TIPO_ASIENTO_DAO_NAME);
		this.tipoAsientoService = (TipoAsientoService) this.lookupService(TIPO_ASIENTO_SERVICE_NAME);
		this.ejercicioDao = (EjercicioDao) this.lookupService(EJERCICIO_DAO_NAME);
		this.ejercicioService = (EjercicioService) this.lookupService(EJERCICIO_SERVICE_NAME);
		this.renglonLibroMayorDao = (RenglonLibroMayorDao) this.lookupService(RENGLON_LIBRO_MAYOR_DAO_NAME);
		this.renglonLibroMayorService = (RenglonLibroMayorService) this.lookupService(RENGLON_LIBRO_MAYOR_SERVICE_NAME);
		this.asientoProveedorDao = (AsientoProveedorDao) this.lookupService(ASIENTO_PROVEEDOR_DAO_NAME);
		this.asientoClienteDao = (AsientoClienteDao) this.lookupService(ASIENTO_CLIENTE_DAO_NAME);
		this.asientoComercioDao = (AsientoComercioDao) this.lookupService(ASIENTO_COMERCIO_DAO_NAME);
		this.asientoProveedorService = (AsientoProveedorService) this.lookupService(ASIENTO_PROVEEDOR_SERVICE_NAME);
		this.asientoClienteService = (AsientoClienteService) this.lookupService(ASIENTO_CLIENTE_SERVICE_NAME);
		this.asientoComercioService = (AsientoComercioService) this.lookupService(ASIENTO_COMERCIO_SERVICE_NAME);
		this.docAdjuntoService = (DocAdjuntoService) this.lookupService(DOC_ADJUNTO_SERVICE);
		/* @F4933 */this.pasajeService = (PasajeService) this.lookupService(PASAJE_SERVICE);
		this.docAdjuntoDao = (DocAdjuntoDao) this.lookupService(DOC_ADJUNTO_DAO_NAME);
	}


	public PlanCuentaDosDao getPlanCuentaDosDao() {
		return planCuentaDosDao;
	}


	public void setPlanCuentaDao(PlanCuentaDosDao planCuentaDosDao) {
		this.planCuentaDosDao = planCuentaDosDao;
	}


	public PlanCuentaDosService getPlanCuentaDosService() {
		return planCuentaDosService;
	}


	public void setPlanCuentaDosService(PlanCuentaDosService planCuentaDosService) {
		this.planCuentaDosService = planCuentaDosService;
	}


	public AsientoDao getAsientoDao() {
		return asientoDao;
	}


	public void setAsientoDao(AsientoDao asientoDao) {
		this.asientoDao = asientoDao;
	}


	public AsientoDetalleDao getAsientoDetalleDao() {
		return asientoDetalleDao;
	}


	public void setAsientoDetalleDao(AsientoDetalleDao asientoDetalleDao) {
		this.asientoDetalleDao = asientoDetalleDao;
	}


	public AsientoService getAsientoService() {
		return asientoService;
	}


	public void setAsientoService(AsientoService asientoService) {
		this.asientoService = asientoService;
	}


	public CentroCostosDao getCentroCostosDao() {
		return centroCostosDao;
	}


	public void setCentroCostosDao(CentroCostosDao centroCostosDao) {
		this.centroCostosDao = centroCostosDao;
	}


	public OrigenDao getOrigenDao() {
		return origenDao;
	}


	public void setOrigenDao(OrigenDao origenDao) {
		this.origenDao = origenDao;
	}


	public CentroCostosService getCentroCostosService() {
		return centroCostosService;
	}


	public void setCentroCostosService(CentroCostosService centroCostosService) {
		this.centroCostosService = centroCostosService;
	}


	public EjercicioService getEjercicioService() {
		return ejercicioService;
	}


	public void setOrigenService(OrigenService origenService) {
		this.origenService = origenService;
	}


	public OrigenService getOrigenService() {
		return origenService;
	}


	public void setEjercicioService(EjercicioService ejercicioService) {
		this.ejercicioService = ejercicioService;
	}


	public AsientoDetalleService getAsientoDetalleService() {
		return asientoDetalleService;
	}


	public void setAsientoDetalleService(AsientoDetalleService asientoDetalleService) {
		this.asientoDetalleService = asientoDetalleService;
	}


	public EjercicioDao getEjercicioDao() {
		return ejercicioDao;
	}


	public void setEjercicioDao(EjercicioDao ejercicioDao) {
		this.ejercicioDao = ejercicioDao;
	}


	public LoteDao getLoteDao() {
		return loteDao;
	}


	public void setLoteDao(LoteDao loteDao) {
		this.loteDao = loteDao;
	}


	public LoteService getLoteService() {
		return loteService;
	}


	public void setLoteService(LoteService loteService) {
		this.loteService = loteService;
	}


	public LoteDetalleDao getLoteDetalleDao() {
		return loteDetalleDao;
	}


	public void setLoteDetalleDao(LoteDetalleDao loteDetalleDao) {
		this.loteDetalleDao = loteDetalleDao;
	}


	public LoteDetalleService getLoteDetalleService() {
		return loteDetalleService;
	}


	public void setLoteDetalleService(LoteDetalleService loteDetalleService) {
		this.loteDetalleService = loteDetalleService;
	}


	public static String getTIPO_ASIENTO_SERVICE_NAME() {
		return TIPO_ASIENTO_SERVICE_NAME;
	}


	public TipoAsientoService getTipoAsientoService() {
		return tipoAsientoService;
	}


	public void setTipoAsientoService(TipoAsientoService tipoAsientoService) {
		this.tipoAsientoService = tipoAsientoService;
	}


	public TipoAsientoDao getTipoAsientoDao() {
		return tipoAsientoDao;
	}


	public void setTipoAsientoDao(TipoAsientoDao tipoAsientoDao) {
		this.tipoAsientoDao = tipoAsientoDao;
	}


	public RenglonLibroMayorService getRenglonLibroMayorService() {
		return renglonLibroMayorService;
	}


	public void setRenglonLibroMayorService(
			RenglonLibroMayorService renglonLibroMayorService) {
		this.renglonLibroMayorService = renglonLibroMayorService;
	}


	public RenglonLibroMayorDao getRenglonLibroMayorDao() {
		return renglonLibroMayorDao;
	}


	public void setRenglonLibroMayorDao(RenglonLibroMayorDao renglonLibroMayorDao) {
		this.renglonLibroMayorDao = renglonLibroMayorDao;
	}


	public AsientoComercioDao getAsientoComercioDao() {
		return asientoComercioDao;
	}


	public void setAsientoComercioDao(AsientoComercioDao asientoComercioDao) {
		this.asientoComercioDao = asientoComercioDao;
	}


	public AsientoClienteDao getAsientoClienteDao() {
		return asientoClienteDao;
	}


	public void setAsientoClienteDao(AsientoClienteDao asientoClienteDao) {
		this.asientoClienteDao = asientoClienteDao;
	}


	public AsientoProveedorDao getAsientoProveedorDao() {
		return asientoProveedorDao;
	}


	public void setAsientoProveedorDao(AsientoProveedorDao asientoProveedorDao) {
		this.asientoProveedorDao = asientoProveedorDao;
	}


	public AsientoComercioService getAsientoComercioService() {
		return asientoComercioService;
	}


	public void setAsientoComercioService(
			AsientoComercioService asientoComercioService) {
		this.asientoComercioService = asientoComercioService;
	}


	public AsientoClienteService getAsientoClienteService() {
		return asientoClienteService;
	}


	public void setAsientoClienteService(
			AsientoClienteService asientoClienteService) {
		this.asientoClienteService = asientoClienteService;
	}


	public AsientoProveedorService getAsientoProveedorService() {
		return asientoProveedorService;
	}


	public void setAsientoProveedorService(
			AsientoProveedorService asientoProveedorService) {
		this.asientoProveedorService = asientoProveedorService;
	}


	public CentroCostoAsientoService getCentroCostoAsientoService() {
		return centroCostoAsientoService;
	}


	public void setCentroCostoAsientoService(CentroCostoAsientoService centroCostoAsientoService) {
		this.centroCostoAsientoService = centroCostoAsientoService;
	}


	public CentroCostoAsientoDao getCentroCostoAsientoDao() {
		return centroCostoAsientoDao;
	}


	public void setCentroCostoAsientoDao(CentroCostoAsientoDao centroCostoAsientoDao) {
		this.centroCostoAsientoDao = centroCostoAsientoDao;
	}


	public BalanceDao getBalanceDao() {
		return balanceDao;
	}


	public void setBalanceDao(BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}


	public BalanceService getBalanceService() {
		return balanceService;
	}


	public void setBalanceService(BalanceService balanceService) {
		this.balanceService = balanceService;
	}


	public DocAdjuntoService getDocAdjuntoService() {
		return docAdjuntoService;
	}


	public void setDocAdjuntoService(DocAdjuntoService docAdjuntoService) {
		this.docAdjuntoService = docAdjuntoService;
	}


	/* @I4933 */
	public PasajeService getPasajeService() {
		return pasajeService;
	}


	public void setPasajeService(PasajeService pasajeService) {
		this.pasajeService = pasajeService;
	}


	/* @F4933 */

	public DocAdjuntoDao getDocAdjuntoDao() {
		return docAdjuntoDao;
	}


	public void setDocAdjuntoDao(DocAdjuntoDao docAdjuntoDao) {
		this.docAdjuntoDao = docAdjuntoDao;
	}

}