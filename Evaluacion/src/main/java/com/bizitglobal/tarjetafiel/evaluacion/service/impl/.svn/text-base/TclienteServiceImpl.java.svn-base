package com.bizitglobal.tarjetafiel.evaluacion.service.impl;
import java.util.Iterator;
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
import com.bizitglobal.tarjetafiel.evaluacion.dao.TclienteDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tcliente;
import com.bizitglobal.tarjetafiel.evaluacion.service.TclienteService;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TclienteException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TclienteDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TclienteNotFoundException;

/**
*	Implementacion de la interfaz TclienteService.
*/
public class TclienteServiceImpl implements TclienteService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private TclienteDao tclienteDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarTcliente (final Tcliente pObject) throws TclienteException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tclienteDao.grabarTcliente(pObject);

			}
		});
	}
	public List getTcliente(Filtro filtro) throws TclienteException {
		try {
			return tclienteDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de tcliente no pudo ser leida.";
			throw new TclienteException(msg,e);
		}
	}
	public Tcliente leerTcliente (Long id) throws TclienteException {
		Tcliente result = null;
		try {
			result = tclienteDao.buscarTcliente(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Tcliente no existe en la base de datos.";
			throw new TclienteNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La tcliente no pudo leerse desde la base de datos.";
			throw new TclienteException(msg,e);
		}
		return result;
	}
	public void borrarTcliente (final Long id) throws TclienteException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tclienteDao.borrarTcliente(id);

			}
		});
	}
	public void borrarTcliente(final Tcliente pObject) throws TclienteException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tclienteDao.borrarTcliente(pObject);

			}
		});
	}
	public void actualizarTcliente (final Tcliente pObject) throws TclienteException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				tclienteDao.actualizarTcliente(pObject);

			}
		});
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public TclienteDao getTclienteDao() {
		return tclienteDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setTclienteDao(TclienteDao tclienteDao) {
		this.tclienteDao = tclienteDao;
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

