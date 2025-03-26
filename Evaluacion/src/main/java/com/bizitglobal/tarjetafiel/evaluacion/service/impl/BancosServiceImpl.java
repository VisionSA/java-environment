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
import com.bizitglobal.tarjetafiel.evaluacion.dao.BancosDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.BancosDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.BancosException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.BancosNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Bancos;
import com.bizitglobal.tarjetafiel.evaluacion.service.BancosService;

/**
*	Implementacion de la interfaz BancosService.
*/
public class BancosServiceImpl implements BancosService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private BancosDao bancosDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarBancos (final Bancos pObject) throws BancosException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				bancosDao.grabarEvaBancos(pObject);

			}
		});
	}
	
	public List getBancos(Filtro filtro) throws BancosException {
		try {
			return bancosDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evabancos no pudo ser leida.";
			throw new BancosException(msg,e);
		}
	}
	
	public Bancos leerBancos (Long id) throws BancosException {
		Bancos result = null;
		try {
			result = bancosDao.buscarEvaBancos(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Bancos no existe en la base de datos.";
			throw new BancosNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evabancos no pudo leerse desde la base de datos.";
			throw new BancosException(msg,e);
		}
		return result;
	}
	
	public void borrarBancos (final Long id) throws BancosException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				bancosDao.borrarEvaBancos(id);

			}
		});
	}
	
	public void borrarBancos(final Bancos pObject) throws BancosException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				bancosDao.borrarEvaBancos(pObject);

			}
		});
	}
	
	public void actualizarBancos (final Bancos pObject) throws BancosException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				bancosDao.actualizarEvaBancos(pObject);

			}
		});
	}
	

	public BancosDao getBancosDao() {
		return bancosDao;
	}

	public void setBancosDao(BancosDao bancosDao) {
		this.bancosDao = bancosDao;
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

