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
import com.bizitglobal.tarjetafiel.proveedores.dao.CuotaComprobanteDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuotaComprobanteDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuotaComprobanteException;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuotaComprobanteNotFoundException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuotaComprobante;
import com.bizitglobal.tarjetafiel.proveedores.service.CuotaComprobanteService;

/**
 *	Implementacion de la interfaz ComprobanteService.
 */
public class CuotaComprobanteServiceImpl implements CuotaComprobanteService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private CuotaComprobanteDao cuotaComprobanteDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarCuotaComprobante(final CuotaComprobante unaCuotaComprobante) throws CuotaComprobanteException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cuotaComprobanteDao.grabarCuotaComprobante(unaCuotaComprobante);
				}
			});
		}
		catch (DataIntegrityViolationException ex) {
			String msg = "La cuota de comprobante ya existe en la base de datos.";
			throw new CuotaComprobanteDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La cuota de comprobante no pudo ser grabado.";
			throw new CuotaComprobanteException(msg,e);
		}
		
	}
	
	public List getCuotaComprobante() throws CuotaComprobanteException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return cuotaComprobanteDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de cuotas de comprobantes no pudo ser leida.";
			throw new CuotaComprobanteException(msg,e);
		}
	}
	
	public CuotaComprobante leerCuotaComprobante(Long id) throws CuotaComprobanteException {
		CuotaComprobante result = null;
		
		try {
			result = cuotaComprobanteDao.buscarCuotaComprobante(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La cuota de comprobante no existe en la base de datos.";
			throw new CuotaComprobanteNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La cuota de comprobante no pudo leerse desde la base de datos.";
			throw new CuotaComprobanteException(msg,e);
		}
		
		return result;
	}
	
	public void borrarCuotaComprobante(final Long id) throws CuotaComprobanteException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cuotaComprobanteDao.borrarCuotaComprobante(id);
				}
			});
		} catch (Exception e) {
			String msg = "La cuota de comprobante no pudo borrase.";
			throw new CuotaComprobanteException(msg,e);
		}		
	}
	
	public void borrarCuotaComprobante(final CuotaComprobante unaCuotaComprobante) throws CuotaComprobanteException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cuotaComprobanteDao.borrarCuotaComprobante(unaCuotaComprobante);
				}
			});
		} catch (Exception e) {
			String msg = "La cuota de comprobante no pudo borrase.";
			throw new CuotaComprobanteException(msg,e);
		}
	}
	
	public void actualizarCuotaComprobante(final CuotaComprobante unaCuotaComprobante) throws CuotaComprobanteException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cuotaComprobanteDao.actualizarCuotaComprobante(unaCuotaComprobante);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar la cuota de comprobante.";
			throw new CuotaComprobanteException(msg,e);
		}
	}
	
	public Long countCuotaComprobante(String cuit) throws CuotaComprobanteException {
		Long result = null;
		
		try {
			result = cuotaComprobanteDao.countCuotaComprobante(cuit);
		} catch (Exception e) {
			String msg = "No se pudo obtener la cantidad de cuotas por cuit.";
			throw new CuotaComprobanteException(msg,e);
		}
		
		return result;
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public CuotaComprobanteDao getCuotaComprobanteDao() {
		return cuotaComprobanteDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setCuotaComprobanteDao(CuotaComprobanteDao cuotaComprobanteDao) {
		this.cuotaComprobanteDao = cuotaComprobanteDao;
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
