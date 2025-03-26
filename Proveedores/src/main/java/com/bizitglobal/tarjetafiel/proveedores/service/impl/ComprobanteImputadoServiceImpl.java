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
import com.bizitglobal.tarjetafiel.proveedores.dao.ComprobanteImputadoDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteImputadoDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteImputadoException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteImputadoNotFoundException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ComprobanteImputado;
import com.bizitglobal.tarjetafiel.proveedores.service.ComprobanteImputadoService;

/**
 *	Implementacion de la interfaz ComprobanteImputadoService.
 */
public class ComprobanteImputadoServiceImpl implements ComprobanteImputadoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ComprobanteImputadoDao comprobanteImputadoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarComprobanteImputado(final ComprobanteImputado unComprobanteImputado) 
			throws ComprobanteImputadoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					comprobanteImputadoDao.grabarComprobanteImputado(unComprobanteImputado);
				}
			});
		}
		catch (DataIntegrityViolationException ex) {
			String msg = "El comprobante imputado ya existe en la base de datos.";
			throw new ComprobanteImputadoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El comprobante imputado no pudo ser grabado.";
			throw new ComprobanteImputadoException(msg,e);
		}
	}

	public List getComprobantesImputados(final Filtro filtro) throws ComprobanteImputadoException {
		
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return comprobanteImputadoDao.listarTodos(filtro);
				}
			});
		}
		 catch (Exception e) {
			String msg = "La lista de comprobantes imputado no pudo ser leida.";
			throw new ComprobanteImputadoException(msg,e);
		}
	}
	
	public ComprobanteImputado leerComprobanteImputado(Long id) 
			throws ComprobanteImputadoException {
		ComprobanteImputado result = null;
		
		try {
			result = comprobanteImputadoDao.buscarComprobanteImputado(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El comprobante imputado no existe en la base de datos.";
			throw new ComprobanteImputadoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El comprobante imputado no pudo leerse desde la base de datos.";
			throw new ComprobanteImputadoException(msg,e);
		}
		
		return result;
	}
	
	public void borrarComprobanteImputado(final Long idComprobanteImputado) 
			throws ComprobanteImputadoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				comprobanteImputadoDao.borrarComprobanteImputado(idComprobanteImputado);
				}
			});
		}
		catch (Exception e) {
			String msg = "El comprobante imputado no pudo borrase.";
			throw new ComprobanteImputadoException(msg,e);
		}
	}
	
	public void borrarComprobanteImputado(final ComprobanteImputado unComprobanteImputado) 
			throws ComprobanteImputadoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					comprobanteImputadoDao.borrarComprobanteImputado(unComprobanteImputado);
				}
			});
		} 
		catch (Exception e) {
			String msg = "El comprobante imputado no pudo borrase.";
			throw new ComprobanteImputadoException(msg,e);
		}
	}	
	
	public void actualizarComprobanteImputado(final ComprobanteImputado unComprobanteImputado) 
			throws ComprobanteImputadoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					comprobanteImputadoDao.actualizarComprobanteImputado(unComprobanteImputado);
				}
			});
		}
		catch (Exception e) {
			String msg = "No se pudo actualizar el comprobante imputado.";
			throw new ComprobanteImputadoException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ComprobanteImputadoDao getComprobanteImputadoDao() {
		return comprobanteImputadoDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setComprobanteImputadoDao(ComprobanteImputadoDao comprobanteImputadoDao) {
		this.comprobanteImputadoDao = comprobanteImputadoDao;
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
