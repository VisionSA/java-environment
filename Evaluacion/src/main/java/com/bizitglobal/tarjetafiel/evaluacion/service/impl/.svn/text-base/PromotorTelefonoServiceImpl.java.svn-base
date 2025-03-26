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
import com.bizitglobal.tarjetafiel.evaluacion.dao.PromotorTelefonoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.PromotorTelefonoDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.PromotorTelefonoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.PromotorTelefonoNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.PromotorTelefono;
import com.bizitglobal.tarjetafiel.evaluacion.service.PromotorTelefonoService;

/**
*	Implementacion de la interfaz PromotorTelefonoService.
*/
public class PromotorTelefonoServiceImpl implements PromotorTelefonoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private PromotorTelefonoDao promotorTelefonoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarPromotorTelefono(final PromotorTelefono pObject) throws PromotorTelefonoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				promotorTelefonoDao.grabarEvaPromoTelefonos(pObject);
			}
		});
	}
	
	public List getPromotorTelefono(Filtro filtro) throws PromotorTelefonoException {
		try {
			return promotorTelefonoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evapromotelefonos no pudo ser leida.";
			throw new PromotorTelefonoException(msg,e);
		}
	}
	
	public PromotorTelefono leerPromotorTelefono(Long id) throws PromotorTelefonoException {
		PromotorTelefono result = null;
		try {
			result = promotorTelefonoDao.buscarEvaPromoTelefonos(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La PromotorTelefono no existe en la base de datos.";
			throw new PromotorTelefonoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evapromotelefonos no pudo leerse desde la base de datos.";
			throw new PromotorTelefonoException(msg,e);
		}
		return result;
	}
	
	public void borrarPromotorTelefono(final Long id) throws PromotorTelefonoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				promotorTelefonoDao.borrarEvaPromoTelefonos(id);

			}
		});
	}
	
	public void borrarPromotorTelefono(final PromotorTelefono pObject) throws PromotorTelefonoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				promotorTelefonoDao.borrarEvaPromoTelefonos(pObject);

			}
		});
	}
	
	public void actualizarPromotorTelefono(final PromotorTelefono pObject) throws PromotorTelefonoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				promotorTelefonoDao.actualizarEvaPromoTelefonos(pObject);

			}
		});
	}
	
	public PromotorTelefonoDao getPromotorTelefonoDao() {
		return promotorTelefonoDao;
	}

	public void setPromotorTelefonoDao(PromotorTelefonoDao promotorTelefonoDao) {
		this.promotorTelefonoDao = promotorTelefonoDao;
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

