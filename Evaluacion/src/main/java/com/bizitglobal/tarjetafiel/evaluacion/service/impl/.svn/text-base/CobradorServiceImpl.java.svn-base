package com.bizitglobal.tarjetafiel.evaluacion.service.impl;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.CobradorDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.CobradorException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.CobradorNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Cobrador;
import com.bizitglobal.tarjetafiel.evaluacion.service.CobradorService;

/**
*	Implementacion de la interfaz CobradorService.
*/
public class CobradorServiceImpl implements CobradorService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private CobradorDao cobradorDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarCobrador(final Cobrador pObject) throws CobradorException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				cobradorDao.grabarEvaCobradores(pObject);

			}
		});
	}
	
	public List getCobrador(Filtro filtro) throws CobradorException {
		try {
			return cobradorDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaCobradores no pudo ser leida.";
			throw new CobradorException(msg,e);
		}
	}
	
	public Cobrador leerCobrador(Long id) throws CobradorException {
		Cobrador result = null;
		try {
			result = cobradorDao.buscarEvaCobradores(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Cobrador no existe en la base de datos.";
			throw new CobradorNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaCobradores no pudo leerse desde la base de datos.";
			throw new CobradorException(msg,e);
		}
		return result;
	}
	
	public void borrarCobrador(final Long id) throws CobradorException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				cobradorDao.borrarEvaCobradores(id);

			}
		});
	}
	
	public void borrarCobrador(final Cobrador pObject) throws CobradorException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				cobradorDao.borrarEvaCobradores(pObject);
			}
		});
	}
	
	public void actualizarCobrador(final Cobrador pObject) throws CobradorException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				cobradorDao.actualizarEvaCobradores(pObject);
			}
		});
	}
	
	public CobradorDao getCobradorDao() {
		return cobradorDao;
	}

	public void setCobradorDao(CobradorDao cobradorDao) {
		this.cobradorDao = cobradorDao;
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

