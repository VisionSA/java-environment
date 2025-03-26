package com.bizitglobal.tarjetafiel.cobranzas.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.cobranzas.dao.EjecucionPlanDao;
import com.bizitglobal.tarjetafiel.cobranzas.exception.EjecucionPlanException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EjecucionPlan;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Moroso;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Plan;
import com.bizitglobal.tarjetafiel.cobranzas.service.EjecucionPlanService;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Abogado;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteLiquidacion;


public class EjecucionPlanServiceImpl implements EjecucionPlanService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private EjecucionPlanDao ejecucionPlanDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transPlanes. 
	*/
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	
	public void grabarEjecucionPlan (final EjecucionPlan pObject) throws EjecucionPlanException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					ejecucionPlanDao.grabarEjecucionPlan(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El EjecucionPlan ya existe en la base de datos.";
			throw new EjecucionPlanException(msg,ex);
		} catch (Exception e) {
			String msg = "El EjecucionPlan no pudo ser grabada.";
			throw new EjecucionPlanException(msg,e);
		}
	}
	
	
	/**
	* Graba un Objeto en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarObjeto (final Object pObject) throws EjecucionPlanException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					ejecucionPlanDao.grabarObjeto(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El Objeto ya existe en la base de datos.";
			throw new EjecucionPlanException(msg,ex);
		} catch (Exception e) {
			String msg = "El objeto no pudo ser grabado.";
			throw new EjecucionPlanException(msg,e);
		}
	}
	
	
	public List getEjecucionPlan(final Filtro filtro) throws EjecucionPlanException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = ejecucionPlanDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de EjecucionPlanes no pudo ser leida.";
			throw new EjecucionPlanException(msg,e);
		}
	}
	
	
	public EjecucionPlan leerEjecucionPlan (final Long id) throws EjecucionPlanException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (EjecucionPlan) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					EjecucionPlan archi = ejecucionPlanDao.buscarEjecucionPlan(id);
				return archi;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El EjecucionPlan no existe en la base de datos.";
			throw new EjecucionPlanException(msg,ex);
		} catch (Exception e) {
			String msg = "El EjecucionPlan no pudo leerse desde la base de datos.";
			throw new EjecucionPlanException(msg,e);
		}
	}
	
	
	public void borrarEjecucionPlan (final Long id) throws EjecucionPlanException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					ejecucionPlanDao.borrarEjecucionPlan(id);
				}
			});
		} catch (Exception e) {
			String msg = "El EjecucionPlan no pudo borrase.";
			throw new EjecucionPlanException(msg,e);
		}
	}
	
	
	public void borrarEjecucionPlan(final EjecucionPlan pObject) throws EjecucionPlanException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					ejecucionPlanDao.borrarEjecucionPlan(pObject);
				}
			});
			ejecucionPlanDao.borrarEjecucionPlan(pObject);
		} catch (Exception e) {
			String msg = "El EjecucionPlan no pudo borrase.";
			throw new EjecucionPlanException(msg,e);
		}
	}
	
	
	public void actualizarEjecucionPlan (final EjecucionPlan pObject) throws EjecucionPlanException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					ejecucionPlanDao.actualizarEjecucionPlan(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El EjecucionPlan no pudo actualizarse.";
			throw new EjecucionPlanException(msg,e);
		}
	}
	
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public EjecucionPlanDao getEjecucionPlanDao() {
		return ejecucionPlanDao;
	}
	
	
	/**
	* Necesario para spring.
	* @param EjecucionPlanDao, Objeto dao a setear.
	*/
	public void setEjecucionPlanDao(EjecucionPlanDao ejecucionPlanDao) {
		this.ejecucionPlanDao = ejecucionPlanDao;
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
	
	
	/**
	 * Lista los clientes que entran en mora. (se identifican porque tienen el c_id_plan_mora en  null.
	 * @param montoDeGracia Un monto de tolerancia, para que no recupere clientes que adeudan centavos nada mas, poniendolos  en mora.
	 * @return Una lista de objetos Long, con el id de cada cliente al cual se lo debe iniciar en la mora,
	 * seteandole el id_plan_mora y la fecha de entrada en mora.
	 * */
	public List<ClienteLiquidacion> listarIdClientesQueEntranEnMora(final Double montoDeGracia) throws EjecucionPlanException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = ejecucionPlanDao.listarIdClientesQueEntranEnMora(montoDeGracia);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de EjecucionPlanes no pudo ser leida.";
			throw new EjecucionPlanException(msg,e);
		}
	}
	
	
	/**
	 * Realiza un update del cliente poniendole el id del plan de mora, 
	 * la fecha del dia como inicio para el id de cliente pasado
	 * */
	public void asociarPlanACliente(final Long idCliente, final Plan plan) {
		ejecucionPlanDao.asociarPlanACliente(idCliente, plan);
	}
	
	
	/**
	 * Devuelve los clientes en mora
	 * */
	public List<Moroso> getClientesEnMora() {
		return ejecucionPlanDao.getClientesEnMora();
	}
	
	
	/**
	* Ejecuta la query que le espasada por parametro.
	* @param query, query a ejecutar.
	*/
	public void ejecutarQuery(String query) throws EjecucionPlanException {
		ejecucionPlanDao.ejecutarQuery(query);
	}
	
	
	public Boolean procesarPagosRealizados(int mesInicial, int mesFinal, int anioInicial, int anioFinal)throws EjecucionPlanException {
		return ejecucionPlanDao.procesarPagosRealizados(mesInicial, mesFinal, anioInicial,anioFinal);
	}
	
	
	@Override
	public void cambiarEstadoYAddFile(final Long idEjecucionPlan, final String confirmoAccion,
			final StringBuffer pathRelativoPDFCobradores)
			throws EjecucionPlanException {

		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					ejecucionPlanDao.cambiarEstadoYAddFile(idEjecucionPlan,confirmoAccion,pathRelativoPDFCobradores);
				}
			});
		} catch (Exception e) {
			String msg = "El EjecucionPlan no pudo actualizarse.";
			throw new EjecucionPlanException(msg,e);
		}
		
	}
	
	
	@Override
	public Long cambiarCobrador(final Long idCobrador,final Long idEjecPlan) throws EjecucionPlanException {	
		try {
			return (Long) transactionTemplate.execute(new TransactionCallback(){
				public Object doInTransaction(TransactionStatus status) {				
					return ejecucionPlanDao.cambiarCobrador(idCobrador,idEjecPlan);
				}
			});
		} catch (Exception e) {
			String msg = "El cobrador no pudo ser modificado";
			throw new EjecucionPlanException(msg,e);
		}
		
	}
	
	
	@Override
	public void cambiarCobradoresTareasPendientes(Long idCobIN, Long idCobOUT, Long idPartido)
			throws EjecucionPlanException {
		try {
			ejecucionPlanDao.cambiarCobradoresTareasPendientes(idCobIN, idCobOUT, idPartido);
		}catch (Exception e) {
			String msg = "El cobrador no pudo ser modificado";
			throw new EjecucionPlanException(msg,e);
		}
		
	}
	
	
	@Override
	public void cambiarAbogadosTareasPendientes(Long idAbogIN, Long idAbogOUT,
			Long idPartido) throws EjecucionPlanException {
		try {
			ejecucionPlanDao.cambiarAbogadosTareasPendientes(idAbogIN, idAbogOUT, idPartido);
		}catch (Exception e) {
			String msg = "El cobrador no pudo ser modificado";
			throw new EjecucionPlanException(msg,e);
		}
	}
	
	
	@Override
	public Abogado buscarAbogadoTarea(Long idEjecucionPlan)
			throws EjecucionPlanException {
		try {
			return ejecucionPlanDao.buscarAbogadoTarea(idEjecucionPlan);
		}catch (Exception e) {
			String msg = "No se pudo obtener el abogado";
			throw new EjecucionPlanException(msg,e);
		}
	}
	
	
	@Override
	public void cambiarAbogadoClienteTarea(Long idEjecucionPlan, Long idAbogIN) throws EjecucionPlanException {
		try {
			ejecucionPlanDao.cambiarAbogadoClienteTarea(idEjecucionPlan, idAbogIN);
		}catch (Exception e) {
			String msg = "El abogado no pudo ser modificado";
			throw new EjecucionPlanException(msg,e);
		}
		
	}
	
	
	@Override
	public void insertarHistoricoMora(Long idCliente) {
		ejecucionPlanDao.insertarHistoricoMora(idCliente);
	}
	
	
	@Override
	public Long getIdEjecPlanCobradorAsignado(Long idCliente) {
		return ejecucionPlanDao.getIdEjecPlanCobradorAsignado(idCliente);
	}
	
	
	@Override
	public void insertarErrorEjecucion(Long idCliente, Long idAccion, String descripcion) {
		ejecucionPlanDao.insertarErrorEjecucion(idCliente, idAccion, descripcion);
	}
	
	
	
}
