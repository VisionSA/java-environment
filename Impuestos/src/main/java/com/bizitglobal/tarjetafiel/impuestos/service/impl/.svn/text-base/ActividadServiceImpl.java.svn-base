package com.bizitglobal.tarjetafiel.impuestos.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.ActividadDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.ActividadDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ActividadException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ActividadNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Actividad;
import com.bizitglobal.tarjetafiel.impuestos.service.ActividadService;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItemRelacion;

/**@deprecated
 *	Implementacion de la interfaz ActividadService.
 */
public class ActividadServiceImpl implements ActividadService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ActividadDao actividadDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarActividad(final Actividad unaActividad) throws ActividadException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					actividadDao.grabarActividad(unaActividad);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La actividad ya existe en la base de datos.";
			throw new ActividadDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La actividad no pudo ser grabada.";
			throw new ActividadException(msg,e);
		}
	}

	public List getActividades(final  Filtro unFiltro) throws ActividadException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = actividadDao.listarTodos(unFiltro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de actividades no pudo ser leida.";
			throw new ActividadException(msg,e);
		}
	}
	
	public Actividad leerActividad(final Long id) throws ActividadException {
		Actividad result = null;
		
		try {
			  transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (Actividad) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  Actividad actividad = actividadDao.buscarActividad(id);
					return actividad;
					}
				});	
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La actividad no existe en la base de datos.";
			throw new ActividadNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La actividad no pudo leerse desde la base de datos.";
			throw new ActividadException(msg,e);
		}
	}
	
	public void borrarActividad(final Long idActividad) throws ActividadException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					actividadDao.borrarActividad(idActividad);
				}
			});
		} catch (Exception e) {
			String msg = "La actividad no pudo borrase.";
			throw new ActividadException(msg,e);
		}
	}
	
	public void borrarActividad(final Actividad unaActividad) throws ActividadException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					actividadDao.borrarActividad(unaActividad);
				}
			});
		} catch (Exception e) {
			String msg = "La actividad no pudo borrase.";
			throw new ActividadException(msg,e);
		}
	}	
	
	public void actualizarActividad(final Actividad unaActividad) throws ActividadException {
		
		try {
			 transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					actividadDao.actualizarActividad(unaActividad);
				}
			});
			
		} catch (Exception e) {
			String msg = "No se pudo actualizar la actividad.";
			throw new ActividadException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ActividadDao getActividadDao() {
		return actividadDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setActividadDao(ActividadDao actividadDao) {
		this.actividadDao = actividadDao;
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

}
