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
import com.bizitglobal.tarjetafiel.evaluacion.dao.EstadosDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EstadosDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EstadosException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EstadosNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Estados;
import com.bizitglobal.tarjetafiel.evaluacion.service.EstadosService;

/**
*	Implementacion de la interfaz EstadosService.
*/
public class EstadosServiceImpl implements EstadosService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private EstadosDao estadosDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarEstados (final Estados pObject) throws EstadosException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				estadosDao.grabarEvaEstados(pObject);

			}
		});
	}
	
	public List getEstados(Filtro filtro) throws EstadosException {
		try {
			return estadosDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaestados no pudo ser leida.";
			throw new EstadosException(msg,e);
		}
	}
	
	public Estados leerEstados (Long id) throws EstadosException {
		Estados result = null;
		try {
			result = estadosDao.buscarEvaEstados(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Estados no existe en la base de datos.";
			throw new EstadosNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaestados no pudo leerse desde la base de datos.";
			throw new EstadosException(msg,e);
		}
		return result;
	}
	
	public void borrarEstados (final Long id) throws EstadosException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				estadosDao.borrarEvaEstados(id);

			}
		});
	}
	
	public void borrarEstados(final Estados pObject) throws EstadosException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				estadosDao.borrarEvaEstados(pObject);

			}
		});
	}
	
	public void actualizarEstados (final Estados pObject) throws EstadosException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				estadosDao.actualizarEvaEstados(pObject);
			}
		});
	}
	
	public EstadosDao getEstadosDao() {
		return estadosDao;
	}

	public void setEstadosDao(EstadosDao estadosDao) {
		this.estadosDao = estadosDao;
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

