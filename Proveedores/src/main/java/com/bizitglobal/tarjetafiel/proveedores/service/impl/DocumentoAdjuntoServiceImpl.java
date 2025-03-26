package com.bizitglobal.tarjetafiel.proveedores.service.impl;

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
import com.bizitglobal.tarjetafiel.proveedores.dao.DocumentoAdjuntoDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.DocumentoAdjuntoDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.exception.DocumentoAdjuntoException;
import com.bizitglobal.tarjetafiel.proveedores.exception.DocumentoAdjuntoNotFoundException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.DocumentoAdjunto;
import com.bizitglobal.tarjetafiel.proveedores.service.DocumentoAdjuntoService;

/**
 *	Implementacion de la interfaz DocumentoAdjuntoService.
 */
public class DocumentoAdjuntoServiceImpl implements DocumentoAdjuntoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private DocumentoAdjuntoDao documentoAdjuntoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarDocumentoAdjunto(final DocumentoAdjunto unDocumentoAdjunto) throws DocumentoAdjuntoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					documentoAdjuntoDao.grabarDocumentoAdjunto(unDocumentoAdjunto);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La DocumentoAdjunto ya existe en la base de datos.";
			throw new DocumentoAdjuntoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La DocumentoAdjunto no pudo ser grabado.";
			throw new DocumentoAdjuntoException(msg,e);
		}
		
	}
	
	public List getDocumentoAdjunto() throws DocumentoAdjuntoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return documentoAdjuntoDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de DocumentoAdjunto no pudo ser leida.";
			throw new DocumentoAdjuntoException(msg,e);
		}
	}
	
	public DocumentoAdjunto leerDocumentoAdjunto(Long id) throws DocumentoAdjuntoException {
		DocumentoAdjunto result = null;
		
		try {
			result = documentoAdjuntoDao.buscarDocumentoAdjunto(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La DocumentoAdjunto no existe en la base de datos.";
			throw new DocumentoAdjuntoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La DocumentoAdjunto no pudo leerse desde la base de datos.";
			throw new DocumentoAdjuntoException(msg,e);
		}
		
		return result;
	}
	
	public void borrarDocumentoAdjunto(final Long id) throws DocumentoAdjuntoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					documentoAdjuntoDao.borrarDocumentoAdjunto(id);
				}
			});
		} catch (Exception e) {
			String msg = "La DocumentoAdjunto no pudo borrase.";
			throw new DocumentoAdjuntoException(msg,e);
		}		
	}
	
	public void borrarDocumentoAdjunto(final DocumentoAdjunto unDocumentoAdjunto) throws DocumentoAdjuntoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					documentoAdjuntoDao.borrarDocumentoAdjunto(unDocumentoAdjunto);
				}
			});
		} catch (Exception e) {
			String msg = "La DocumentoAdjunto no pudo borrase.";
			throw new DocumentoAdjuntoException(msg,e);
		}
	}
	
	public void actualizarDocumentoAdjunto(final DocumentoAdjunto unDocumentoAdjunto) throws DocumentoAdjuntoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					documentoAdjuntoDao.actualizarDocumentoAdjunto(unDocumentoAdjunto);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar el DocumentoAdjunto.";
			throw new DocumentoAdjuntoException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public DocumentoAdjuntoDao getDocumentoAdjuntoDao() {
		return documentoAdjuntoDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setDocumentoAdjuntoDao(DocumentoAdjuntoDao documentoAdjuntoDao) {
		this.documentoAdjuntoDao = documentoAdjuntoDao;
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
