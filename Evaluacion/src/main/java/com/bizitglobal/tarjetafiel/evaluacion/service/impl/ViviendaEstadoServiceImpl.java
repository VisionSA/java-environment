package com.bizitglobal.tarjetafiel.evaluacion.service.impl;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ViviendaEstadoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ViviendaEstadoDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ViviendaEstadoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ViviendaEstadoNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ViviendaEstado;
import com.bizitglobal.tarjetafiel.evaluacion.service.ViviendaEstadoService;

/**
*	Implementacion de la interfaz ViviendaEstadoService.
*/
public class ViviendaEstadoServiceImpl implements ViviendaEstadoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private ViviendaEstadoDao viviendaEstadoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarViviendaEstado (final ViviendaEstado pObject) throws ViviendaEstadoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				viviendaEstadoDao.grabarEvaVivEstados(pObject);

			}
		});
	}
	public List getViviendaEstado (Filtro filtro) throws ViviendaEstadoException {
		try {
			return viviendaEstadoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evavivestados no pudo ser leida.";
			throw new ViviendaEstadoException(msg,e);
		}
	}
	
	public ViviendaEstado leerViviendaEstado (Long id) throws ViviendaEstadoException {
		ViviendaEstado result = null;
		try {
			result = viviendaEstadoDao.buscarEvaVivEstados(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La ViviendaEstado no existe en la base de datos.";
			throw new ViviendaEstadoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evavivestados no pudo leerse desde la base de datos.";
			throw new ViviendaEstadoException(msg,e);
		}
		return result;
	}
	
	public void borrarViviendaEstado (final Long id) throws ViviendaEstadoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				viviendaEstadoDao.borrarEvaVivEstados(id);

			}
		});
	}
	
	public void borrarViviendaEstado(final ViviendaEstado pObject) throws ViviendaEstadoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				viviendaEstadoDao.borrarEvaVivEstados(pObject);

			}
		});
	}
	
	public void actualizarViviendaEstado (final ViviendaEstado pObject) throws ViviendaEstadoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				viviendaEstadoDao.actualizarEvaVivEstados(pObject);

			}
		});
	}
	
	public ViviendaEstadoDao getViviendaEstadoDao() {
		return viviendaEstadoDao;
	}
	public void setViviendaEstadoDao(ViviendaEstadoDao viviendaEstadoDao) {
		this.viviendaEstadoDao = viviendaEstadoDao;
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

