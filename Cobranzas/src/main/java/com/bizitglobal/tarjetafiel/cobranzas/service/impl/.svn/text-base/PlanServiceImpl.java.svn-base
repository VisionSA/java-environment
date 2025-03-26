package com.bizitglobal.tarjetafiel.cobranzas.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.cobranzas.dao.AccionDao;
import com.bizitglobal.tarjetafiel.cobranzas.dao.EtapaDao;
import com.bizitglobal.tarjetafiel.cobranzas.dao.PlanDao;
import com.bizitglobal.tarjetafiel.cobranzas.dao.PlanVersionDao;
import com.bizitglobal.tarjetafiel.cobranzas.exception.PlanException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.AccionVersion;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.AdministradorDePlanes;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Etapa;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EtapaVersion;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Plan;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.PlanVersion;
import com.bizitglobal.tarjetafiel.cobranzas.service.PlanService;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;


public class PlanServiceImpl implements PlanService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private PlanDao planDao;
	private PlanVersionDao planVersionDao;
	private EtapaDao etapaDao;
	private AccionDao accionDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transPlanes. 
	*/
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	public List<String> grabarPlan (final Plan pObject) throws PlanException {
		try {
			final List<String> errores = new ArrayList<String>();
			if (!(pObject.getIdPlan()==null || pObject.getIdPlan().intValue()==0)) {
				errores.add("El plan ya existe en la base de datos.");
			}
			if (pObject.getDescripcion() == null || pObject.getDescripcion().compareTo("")==0) {
				errores.add("Debe especificar un nombre para el plan.");
			}
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0)   {	
					Iterator iteVersiones = pObject.getPlanesVersion().iterator();
					while (iteVersiones.hasNext()) {
						PlanVersion pv = (PlanVersion)iteVersiones.next();
								try {
									pv.validarConsistenciaPlan();
								} catch (Exception e) {
									errores.add(e.getMessage());
								}
					}
					if (errores.isEmpty()) {
						if (pObject.getEsPlanPorDefecto().compareTo("S")==0) {
							planDao.borrarPlanesPorDefecto();
						}
						planDao.grabarPlan(pObject);
					}
				}
			});
			//if (pObject.getIdPlan()==null) throw new Exception("No se grabo el plan. Datos inconsistentes.");
			return errores;
		} catch (DataIntegrityViolationException ex) {
			String msg = "El Plan ya existe en la base de datos.";
			throw new PlanException(msg,ex);
		} catch (Exception e) {
			String msg = "El Plan no pudo ser grabada.";
			throw new PlanException(msg,e);
		}
	}
	
	public List getPlan(final Filtro filtro) throws PlanException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = planDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Planes no pudo ser leida.";
			throw new PlanException(msg,e);
		}
	}
	
	/**
	 * Cambia el estado del plan. de desabilitado a habilitado y viceversa.
	 * */
	public void cambiarEstadoPlan(final Plan plan) throws PlanException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(TransactionStatus arg0)   {	
					planDao.cambiarEstadoPlan(plan);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo cambiar el estado del plan.";
			throw new PlanException(msg,e);
		}
	}
	
	/**
	 * Cambia el plan que esta marcado por defecto. Desmarca el anterior.
	 * @param plan El nuevo plan por que será aplicado por defecto.
	 * */
	public void marcarPlanPorDefecto(final Plan plan) throws PlanException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {	
					planDao.desmarcarPlanPorDefecto();
					planDao.marcarPlanPorDefecto(plan);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo cambiar el plan por defecto.";
			throw new PlanException(msg,e);
		}
	}
	
	public Plan leerPlan (final Long id) throws PlanException {
		try { 
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Plan) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					Plan archi = planDao.buscarPlan(id);
				return archi;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El Plan no existe en la base de datos.";
			throw new PlanException(msg,ex);
		} catch (Exception e) {
			String msg = "El Plan no pudo leerse desde la base de datos.";
			throw new PlanException(msg,e);
		}
	}
	public void borrarPlan (final Long id) throws PlanException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					planDao.borrarPlan(id);
				}
			});
		} catch (Exception e) {
			String msg = "El Plan no pudo borrase.";
			throw new PlanException(msg,e);
		}
	}
	
	/**
	 * @return Object[2] en la primera posicion un String con un mensaje que describe el resultado de la operacion. en segundo lugar un Integer con el id de plan que se boorro. nulo si no se borro nada.
	 * */
	public Object[] borrarPlan(final Plan pObject) throws PlanException {
		try {
			final Object[] res = new Object[2];
			res[1] = null;
			final List<String> resultado = new ArrayList<String>();
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					if (!pObject.entroEnVigenciaAlgunaVersion()) {
						res[1] = pObject.getIdPlan();
						planDao.borrarPlan(pObject);
						resultado.add("El plan se ha borrado exitosamente");
					} else {
						resultado.add("El plan no puede eliminarse porque ya ha entrado en vigencia.");
					}
				}
			});
			res[0] = resultado.get(0);
			return res;
		} catch (Exception e) { 
			String msg = "El Plan no pudo borrase.";
			throw new PlanException(msg,e);
		}
	}
	public void actualizarPlan (final Plan pObject) throws PlanException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					planDao.actualizarPlan(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El Plan no pudo actualizarse.";
			throw new PlanException(msg,e);
		}
	}
	
	/**
	 * Este metodo devuelve un Plan, preseteado con las etapas, y cada etapa con su accion obligatoria
	 * @return Un nuevo Plan
	 * */
	public Plan crearPlan() throws PlanException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Plan) transactionTemplate.execute(new TransactionCallback( ) {
				public Object doInTransaction(TransactionStatus status) {				
					Plan newPlan = new Plan();
					PlanVersion planV = new PlanVersion();
					newPlan.getPlanesVersion().add(planV);
					List<Etapa> etapasDisponibles = etapaDao.listarTodos(new Filtro());
					Iterator<Etapa> iterE = etapasDisponibles.iterator();
					while (iterE.hasNext()) {
						Etapa et = iterE.next();
						EtapaVersion newEtapaVersion = new EtapaVersion("", 10, et, et.getDescripcion(),new HashSet<AccionVersion>());
						planV.getEtapasVersion().add(newEtapaVersion);
						newEtapaVersion.setPlanVersion(planV);
					}
					newPlan.setHabilitado("S");
					
			        return newPlan;
				}
			});
		} catch (Exception e) {
			String msg = "El Plan no pudo actualizarse.";
			throw new PlanException(msg,e);
		}
	}
	/**
	 * Devuelve una instancia de AdministradorDePlanes configurado con todos los planes con sus clientes correspondientes, y aparte el plan por defecto.
	 * */
	public AdministradorDePlanes getAdministradorDePlanes() throws PlanException {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (AdministradorDePlanes) transactionTemplate.execute(new TransactionCallback( ) {
				public Object doInTransaction(TransactionStatus status) {				
					AdministradorDePlanes administrador = new AdministradorDePlanes();
					Filtro filtro = new Filtro("habilitado", Filtro.LIKE, "S");
					filtro.agregarCampoOperValor("esPlanPorDefecto", Filtro.LIKE, "N");
					List<Plan> planesComunes = planDao.listarTodos(filtro);
					Plan planPorDefecto = planDao.getPlanPorDefecto();
					if (planPorDefecto != null && planPorDefecto.getVersionActual()!=null) {
						planPorDefecto.getVersionActual().armarHashAccionesPlan();
						administrador.setPlanPorDefecto(planPorDefecto);
						administrador.getPlanXIdPlan().put(planPorDefecto.getIdPlan(), planPorDefecto);
					} else {
						return null;
					}
					Iterator<Plan> iterP = planesComunes.iterator();
					while (iterP.hasNext()) {
						Plan p = iterP.next();
						// lo agrego, previa configuracion del plan para su ejecucion posterior.
						if (p!= null && p.getVersionActual()!=null) {
							p.getVersionActual().armarHashAccionesPlan();
							administrador.getPlanXIdPlan().put(p.getIdPlan(), p);
							// para cada uno de estos planes, ejecuto la query de la version actual.
							List<Long> listin = planDao.ejecutarQuery(p);
							//Se lo agregamos al plan
							administrador.getPlanXClientes().put(p, listin);
						} 
					}
					return administrador;
				}
			});
	}
	
	/**
	 * De ser posible, guarda una nueva version del plan.
	 * */
	public List<String> guardarNuevaVersion(final Plan plan, final PlanVersion planVersion) throws PlanException {
		try {
			final List<String> resultado = new ArrayList<String>();
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					try {
						planVersion.validarConsistenciaPlan();
					} catch (Exception e) {
						resultado.add(e.getMessage());
					}
					if (resultado.isEmpty()) {
						if (plan.sePuedeIniciarNuevaVersion(planVersion)) { // algun tipo de control, sobre ver si no hay otra version en la misma fecha.
						    PlanVersion planV = new PlanVersion(planVersion);
							planVersionDao.grabarPlanVersion(planV);
							resultado.add("La nueva versión se ha grabado correctamente");
						} else {
							resultado.add("Seleccione otra fecha de vigencia. Ya existe una versión para el dia especificado.");
						}
					}
				}
			});
			return resultado;
		} catch (Exception e) { 
			String msg = "La nueva versión no pudo grabarse.";
			throw new PlanException(msg,e);
		}
	}
	
	
	/**
	 * De ser posible, borra una version del plan.
	 * @return Object[2] en la primera posicion un String con un mensaje que describe el resultado de la operacion. en segundo lugar el objeto PlanVersion que se boorro. nulo si no se borro nada.
	 * */
	public Object[] borrarVersion(final PlanVersion planVersion) throws PlanException {
		final Object[] res = new Object[2];
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					if (planVersion.getPlan().getPlanesVersion().size()==1) {
						res[0] = "No se puede eliminar la version ya que es la unica.";
						res[1] = null;
					} else {
						planVersionDao.borrarPlanVersion(planVersion);
						res[0] = "La version ha sido eliminada correctamente";
						res[1] = planVersion;
					}
				}
			});
			return res;
		} catch (Exception e) { 
			String msg = "La nueva versión no pudo borrarse.";
			throw new PlanException(msg,e);
		}
	}
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public PlanDao getPlanDao() {
		return planDao;
	}
	/**
	* Necesario para spring.
	* @param PlanDao, Objeto dao a setear.
	*/
	public void setPlanDao(PlanDao planDao) {
		this.planDao = planDao;
	}
	
	
	public EtapaDao getEtapaDao() {
		return etapaDao;
	}
	public void setEtapaDao(EtapaDao etapaDao) {
		this.etapaDao = etapaDao;
	}
	public AccionDao getAccionDao() {
		return accionDao;
	}
	public void setAccionDao(AccionDao accionDao) {
		this.accionDao = accionDao;
	}
	
	
	
	public PlanVersionDao getPlanVersionDao() {
		return planVersionDao;
	}
	public void setPlanVersionDao(PlanVersionDao planVersionDao) {
		this.planVersionDao = planVersionDao;
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto transactionManager.
	*/
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}
	/**
	* Necesario para spring
	* @param transactionManager, Objeto a setear.
	*/
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		transactionTemplate = new TransactionTemplate(transactionManager);
	}

	@Override
	public List getPlanes(final Filtro filtro) throws PlanException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = planDao.getPlanes(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Planes no pudo ser leida.";
			throw new PlanException(msg,e);
		}
	}

	@Override
	public void updatePlan(final Plan plan) throws PlanException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					planDao.updatePlan(plan);
				}
			});
		} catch (Exception e) {
			String msg = "El Plan no pudo actualizarse.";
			throw new PlanException(msg,e);
		}
	}

	@Override
	public void crearPlanNuevo(final Plan plan) throws PlanException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					planDao.grabarPlan(plan);
				}
			});
		} catch (Exception e) {
			String msg = "El Plan no pudo crearse.";
			throw new PlanException(msg,e);
		}
	}	
	
}
