package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.RegistroUploadDao;
import com.bizitglobal.tarjetafiel.fondos.exception.RegistroUploadDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.RegistroUploadException;
import com.bizitglobal.tarjetafiel.fondos.exception.RegistroUploadNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.RegistroUpload;
import com.bizitglobal.tarjetafiel.fondos.service.RegistroUploadService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class RegistroUploadServiceImpl implements RegistroUploadService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private RegistroUploadDao registroUploadDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarRegistroUpload(final RegistroUpload unaRegistroUpload) throws RegistroUploadException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					registroUploadDao.grabarRegistroUpload(unaRegistroUpload);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La RegistroUpload ya existe en la base de datos.";
			throw new RegistroUploadDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La RegistroUpload no pudo ser grabada.";
			throw new RegistroUploadException(msg,e);
		}
		
	}
	
	public List getRegistroUploades() throws RegistroUploadException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return registroUploadDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de RegistroUpload no pudo ser leida.";
			throw new RegistroUploadException(msg,e);
		}
	}
	
	public RegistroUpload leerRegistroUpload(Long id) throws RegistroUploadException {
		try {
			return registroUploadDao.buscarRegistroUpload(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La registroUpload no existe en la base de datos.";
			throw new RegistroUploadNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La RegistroUpload no pudo leerse desde la base de datos.";
			throw new RegistroUploadException(msg,e);
		}
	}
	
	public void borrarRegistroUpload(final Long idRegistroUpload) throws RegistroUploadException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					registroUploadDao.borrarRegistroUpload(idRegistroUpload);
				}
			});
		} catch (Exception e) {
			String msg = "La RegistroUpload no pudo borrase.";
			throw new RegistroUploadException(msg,e);
		}
	}
	
	public void borrarRegistroUpload(final RegistroUpload unaRegistroUpload) throws RegistroUploadException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					registroUploadDao.borrarRegistroUpload(unaRegistroUpload);
				}
			});
		} catch (Exception e) {
			String msg = "La RegistroUpload no pudo borrase.";
			throw new RegistroUploadException(msg,e);
		}
	}
	
	public void actualizarRegistroUpload(final RegistroUpload unaRegistroUpload) throws RegistroUploadException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					registroUploadDao.actualizarRegistroUpload(unaRegistroUpload);
				}
			});
		} catch (Exception e) {
			String msg = "La RegistroUpload no pudo actualizarse.";
			throw new RegistroUploadException(msg,e);
		}
	}
	
	public List getRegistroUploads(final Filtro filtro) throws RegistroUploadException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return registroUploadDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de RegistroUpload no pudo ser leida.";
			throw new RegistroUploadException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public RegistroUploadDao getRegistroUploadDao() {
		return registroUploadDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setRegistroUploadDao(RegistroUploadDao registroUploadDao) {
		this.registroUploadDao = registroUploadDao;
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
