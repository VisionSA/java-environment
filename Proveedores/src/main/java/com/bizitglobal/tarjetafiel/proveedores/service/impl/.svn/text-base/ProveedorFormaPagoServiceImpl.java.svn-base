package com.bizitglobal.tarjetafiel.proveedores.service.impl;

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
import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorFormaPagoDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorFormaPagoDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorFormaPagoException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorFormaPagoNotFoundException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorFormaPago;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorFormaPagoService;

/**
 *	Implementacion de la interfaz ProveedorFormaPagoService.
 */
public class ProveedorFormaPagoServiceImpl implements ProveedorFormaPagoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ProveedorFormaPagoDao proveedorFormaPagoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarProveedorFormaPago(final ProveedorFormaPago unProveedorFormaPago) 
			throws ProveedorFormaPagoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					proveedorFormaPagoDao.grabarProveedorFormaPago(unProveedorFormaPago);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El comprobante imputado ya existe en la base de datos.";
			throw new ProveedorFormaPagoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El comprobante imputado no pudo ser grabado.";
			throw new ProveedorFormaPagoException(msg,e);
		}
	}

	public List getProveedorFormaPagos(Filtro filtro) throws ProveedorFormaPagoException {
		try {
			return proveedorFormaPagoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de formaas de pago no pudo ser leida.";
			throw new ProveedorFormaPagoException(msg,e);
		}
	}
	
	public ProveedorFormaPago leerProveedorFormaPago(Integer id) 
			throws ProveedorFormaPagoException {
		try {
			return proveedorFormaPagoDao.buscarProveedorFormaPago(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El comprobante imputado no existe en la base de datos.";
			throw new ProveedorFormaPagoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El comprobante imputado no pudo leerse desde la base de datos.";
			throw new ProveedorFormaPagoException(msg,e);
		}
	}
	
	public void borrarProveedorFormaPago(final Integer idProveedorFormaPago) 
			throws ProveedorFormaPagoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					proveedorFormaPagoDao.borrarProveedorFormaPago(idProveedorFormaPago);
				}
			});
		} catch (Exception e) {
			String msg = "El comprobante imputado no pudo borrase.";
			throw new ProveedorFormaPagoException(msg,e);
		}
	}
	
	public void borrarProveedorFormaPago(final ProveedorFormaPago unProveedorFormaPago) 
			throws ProveedorFormaPagoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					proveedorFormaPagoDao.borrarProveedorFormaPago(unProveedorFormaPago);
				}
			});
		} catch (Exception e) {
			String msg = "El comprobante imputado no pudo borrase.";
			throw new ProveedorFormaPagoException(msg,e);
		}
	}	
	
	public void actualizarProveedorFormaPago(final ProveedorFormaPago unProveedorFormaPago) 
			throws ProveedorFormaPagoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					proveedorFormaPagoDao.actualizarProveedorFormaPago(unProveedorFormaPago);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar el comprobante imputado.";
			throw new ProveedorFormaPagoException(msg,e);
		}
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

	public ProveedorFormaPagoDao getProveedorFormaPagoDao() {
		return proveedorFormaPagoDao;
	}

	public void setProveedorFormaPagoDao(ProveedorFormaPagoDao proveedorFormaPagoDao) {
		this.proveedorFormaPagoDao = proveedorFormaPagoDao;
	}

}
