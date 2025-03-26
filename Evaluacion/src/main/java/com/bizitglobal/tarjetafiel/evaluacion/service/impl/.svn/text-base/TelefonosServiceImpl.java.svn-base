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
import com.bizitglobal.tarjetafiel.evaluacion.dao.TelefonosDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TelefonosDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TelefonosException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TelefonosNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Telefonos;
import com.bizitglobal.tarjetafiel.evaluacion.service.TelefonosService;

/**
*	Implementacion de la interfaz TelefonosService.
*/
public class TelefonosServiceImpl implements TelefonosService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private TelefonosDao telefonosDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarTelefonos (final Telefonos pObject) throws TelefonosException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				telefonosDao.grabarEvaTelefonos(pObject);

			}
		});
	}
	
	public List getTelefonos(Filtro filtro) throws TelefonosException {
		try {
			return telefonosDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evatelefonos no pudo ser leida.";
			throw new TelefonosException(msg,e);
		}
	}
	
	public Telefonos leerTelefonos (Long id) throws TelefonosException {
		Telefonos result = null;
		try {
			result = telefonosDao.buscarEvaTelefonos(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Telefonos no existe en la base de datos.";
			throw new TelefonosNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evatelefonos no pudo leerse desde la base de datos.";
			throw new TelefonosException(msg,e);
		}
		return result;
	}
	
	public void borrarTelefonos (final Long id) throws TelefonosException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				telefonosDao.borrarEvaTelefonos(id);

			}
		});
	}
	
	public void borrarTelefonos(final Telefonos pObject) throws TelefonosException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				telefonosDao.borrarEvaTelefonos(pObject);

			}
		});
	}
	
	public void actualizarTelefonos (final Telefonos pObject) throws TelefonosException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				telefonosDao.actualizarEvaTelefonos(pObject);

			}
		});
	}
	
	public TelefonosDao getTelefonosDao() {
		return telefonosDao;
	}

	public void setTelefonosDao(TelefonosDao telefonoDao) {
		this.telefonosDao = telefonoDao;
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
	public boolean tieneTelefono(Long idCliente) {
		return telefonosDao.tieneTelefono(idCliente);
	}
}

