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
import com.bizitglobal.tarjetafiel.impuestos.dao.TramosRetencionDao;

import com.bizitglobal.tarjetafiel.impuestos.exception.TramosRetencionDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TramosRetencionException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TramosRetencionNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TramosRetencion;
import com.bizitglobal.tarjetafiel.impuestos.service.TramosRetencionService;

/**
 *	Implementacion de la interfaz TramosRetencionService.
 */
public class TramosRetencionServiceImpl implements TramosRetencionService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private TramosRetencionDao tramosRetencionDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarTramosRetencion(final TramosRetencion unTramosRetencion) throws TramosRetencionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tramosRetencionDao.grabarTramosRetencion(unTramosRetencion);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El tramo retencion ya existe en la base de datos.";
			throw new TramosRetencionDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El tramo retencion no pudo ser grabado.";
			throw new TramosRetencionException(msg,e);
		}
	}

	public List getTramosRetenciones(final Filtro unFiltro) throws TramosRetencionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = tramosRetencionDao.listarTodos(unFiltro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de retenciones no pudo ser leida.";
			throw new TramosRetencionException(msg,e);
		}
	}
	
	public TramosRetencion leerTramosRetencion(final Long id) throws TramosRetencionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (TramosRetencion) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  TramosRetencion tramosRetencion  = tramosRetencionDao.buscarTramosRetencion(id);
					return tramosRetencion;
					}
			});	
			} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El tramo retencion no existe en la base de datos.";
			throw new TramosRetencionNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El tramo retencion no pudo leerse desde la base de datos.";
			throw new TramosRetencionException(msg,e);
		}
	}
	
	public void borrarTramosRetencion(final Long idTramosRetencion) throws TramosRetencionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tramosRetencionDao.borrarTramosRetencion(idTramosRetencion);
				}
			});
		} catch (Exception e) {
			String msg = "La retencion no pudo borrase.";
			throw new TramosRetencionException(msg,e);
		}
	}
	
	public void borrarTramosRetencion(final TramosRetencion unTramosRetencion) throws TramosRetencionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tramosRetencionDao.borrarTramosRetencion(unTramosRetencion);
				}
			});
		} catch (Exception e) {
			String msg = "La retencion no pudo borrase.";
			throw new TramosRetencionException(msg,e);
		}
	}	
	
	public void actualizarTramosRetencion(final TramosRetencion unTramosRetencion) throws TramosRetencionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tramosRetencionDao.actualizarTramosRetencion(unTramosRetencion);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar el tramo retencion.";
			throw new TramosRetencionException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public TramosRetencionDao getTramosRetencionDao() {
		return tramosRetencionDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setTramosRetencionDao(TramosRetencionDao tramosRetencionDao) {
		this.tramosRetencionDao = tramosRetencionDao;
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
