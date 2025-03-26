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
import com.bizitglobal.tarjetafiel.proveedores.dao.TipoVencimientoDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.TipoVencimientoDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.exception.TipoVencimientoException;
import com.bizitglobal.tarjetafiel.proveedores.exception.TipoVencimientoNotFoundException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.TipoVencimiento;
import com.bizitglobal.tarjetafiel.proveedores.service.TipoVencimientoService;

/**
 *	Implementacion de la interfaz BancoService.
 */
public class TipoVencimientoServiceImpl implements TipoVencimientoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private TipoVencimientoDao tipoVtoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarTipoVencimiento(final TipoVencimiento unTipoVencimiento) 
			throws TipoVencimientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tipoVtoDao.grabarTipoVencimiento(unTipoVencimiento);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El tipo vencimiento ya existe en la base de datos.";
			throw new TipoVencimientoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El tipo vencimiento no pudo ser grabado.";
			throw new TipoVencimientoException(msg,e);
		}
	}

	public List getTipoVencimientos(final Filtro filtro) throws TipoVencimientoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return tipoVtoDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de tipos de vencimientos no pudo ser leida.";
			throw new TipoVencimientoException(msg,e);
		}
	}
	
	public TipoVencimiento leerTipoVencimiento(Integer id) throws TipoVencimientoException {
		TipoVencimiento result = null;
		
		try {
			result = tipoVtoDao.buscarTipoVencimiento(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El tipo vencimiento no existe en la base de datos.";
			throw new TipoVencimientoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El tipo vencimiento no pudo leerse desde la base de datos.";
			throw new TipoVencimientoException(msg,e);
		}
		
		return result;
	}
	
	public void borrarTipoVencimiento(final Integer idTipoVencimiento) throws TipoVencimientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tipoVtoDao.borrarTipoVencimiento(idTipoVencimiento);
				}
			});
		} catch (Exception e) {
			String msg = "El tipo de vencimiento no pudo borrase.";
			throw new TipoVencimientoException(msg,e);
		}
	}
	
	public void borrarTipoVencimiento(final TipoVencimiento unTipoVencimiento) 
			throws TipoVencimientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tipoVtoDao.borrarTipoVencimiento(unTipoVencimiento);
				}
			});
		} catch (Exception e) {
			String msg = "El tipo vencimiento no pudo borrase.";
			throw new TipoVencimientoException(msg,e);
		}
	}	
	
	public void actualizarTipoVencimiento(final TipoVencimiento unTipoVencimiento) 
			throws TipoVencimientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tipoVtoDao.actualizarTipoVencimiento(unTipoVencimiento);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar el tipo vencimiento.";
			throw new TipoVencimientoException(msg,e);
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

	public TipoVencimientoDao getTipoVtoDao() {
		return tipoVtoDao;
	}

	public void setTipoVtoDao(TipoVencimientoDao tipoVtoDao) {
		this.tipoVtoDao = tipoVtoDao;
	}

}
