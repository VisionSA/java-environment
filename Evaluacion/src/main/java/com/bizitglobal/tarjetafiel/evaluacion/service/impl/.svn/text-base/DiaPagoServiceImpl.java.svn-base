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
import com.bizitglobal.tarjetafiel.evaluacion.dao.DiaPagoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.DiaPagoDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.DiaPagoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.DiaPagoNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.DiaPago;
import com.bizitglobal.tarjetafiel.evaluacion.service.DiaPagoService;

/**
*	Implementacion de la interfaz DiaPagoService.
*/
public class DiaPagoServiceImpl implements DiaPagoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private DiaPagoDao diaPagoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarDiaPago (final DiaPago pObject) throws DiaPagoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				diaPagoDao.grabarEvaDiasPago(pObject);

			}
		});
	}
	
	public List getDiaPago(Filtro filtro) throws DiaPagoException {
		try {
			return diaPagoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evadiaspago no pudo ser leida.";
			throw new DiaPagoException(msg,e);
		}
	}
	
	public DiaPago leerDiaPago (Long id) throws DiaPagoException {
		DiaPago result = null;
		try {
			result = diaPagoDao.buscarEvaDiasPago(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La DiaPago no existe en la base de datos.";
			throw new DiaPagoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evadiaspago no pudo leerse desde la base de datos.";
			throw new DiaPagoException(msg,e);
		}
		return result;
	}
	
	public void borrarDiaPago (final Long id) throws DiaPagoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				diaPagoDao.borrarEvaDiasPago(id);

			}
		});
	}
	
	public void borrarDiaPago(final DiaPago pObject) throws DiaPagoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				diaPagoDao.borrarEvaDiasPago(pObject);

			}
		});
	}
	
	public void actualizarDiaPago (final DiaPago pObject) throws DiaPagoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				diaPagoDao.actualizarEvaDiasPago(pObject);

			}
		});
	}
	
	public DiaPagoDao getDiaPagoDao() {
		return diaPagoDao;
	}

	public void setDiaPagoDao(DiaPagoDao diaPagoDao) {
		this.diaPagoDao = diaPagoDao;
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
	public DiaPago getDiaPagoByIdCliente(Long idCliente)
			throws DiaPagoException {
		
		if (idCliente!=null){
			return diaPagoDao.getDiaPagoByIdCliente(idCliente);
		}else {
			throw new DiaPagoDuplicateException("IdCliente null", new Exception());
		}
		
		
	}
}

