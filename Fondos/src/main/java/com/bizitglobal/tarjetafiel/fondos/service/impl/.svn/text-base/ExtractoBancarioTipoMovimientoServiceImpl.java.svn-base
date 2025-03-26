package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.BancoPropioDao;
import com.bizitglobal.tarjetafiel.fondos.dao.DetalleExtractoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ExtractoBancarioDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ExtractoBancarioTipoMovimientoDao;
import com.bizitglobal.tarjetafiel.fondos.exception.AcreditacionFondoException;
import com.bizitglobal.tarjetafiel.fondos.exception.ExtractoBancarioDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.ExtractoBancarioException;
import com.bizitglobal.tarjetafiel.fondos.exception.ExtractoBancarioNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.exception.ExtractoBancarioTipoMovimientoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.ArchivoAcreditacion;
import com.bizitglobal.tarjetafiel.fondos.negocio.ArchivoExtracto;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.DetalleExtracto;
import com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancario;
import com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancarioTipoMovimiento;
import com.bizitglobal.tarjetafiel.fondos.service.ExtractoBancarioService;
import com.bizitglobal.tarjetafiel.fondos.service.ExtractoBancarioTipoMovimientoService;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class ExtractoBancarioTipoMovimientoServiceImpl implements ExtractoBancarioTipoMovimientoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ExtractoBancarioTipoMovimientoDao extractoBancarioTipoMovimientoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	 
	public void grabarExtractoBancarioTipoMovimiento(final ExtractoBancarioTipoMovimiento unExtractoBancarioTipoMovimiento) throws ExtractoBancarioTipoMovimientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					extractoBancarioTipoMovimientoDao.grabarExtractoBancarioTipoMovimiento(unExtractoBancarioTipoMovimiento);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El Tipo de Movimiento de Extracto Bancario ya existe en la base de datos.";
			throw new ExtractoBancarioTipoMovimientoException(msg,ex);
		} catch (Exception e) {
			String msg = "El Tipo de Movimiento de Extracto  no pudo ser grabado.";
			throw new ExtractoBancarioTipoMovimientoException(msg,e);
		}
		
	}
	
	@Override
	public List listarTodos(final Filtro filtro) throws ExtractoBancarioTipoMovimientoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return extractoBancarioTipoMovimientoDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Tipo de Movimiento de Extracto no pudo ser leida.";
			throw new ExtractoBancarioTipoMovimientoException(msg,e);
		}
	}
	
	public ExtractoBancarioTipoMovimiento leerExtractoBancarioTipoMovimiento(Long id) throws ExtractoBancarioTipoMovimientoException {
		try {
			return extractoBancarioTipoMovimientoDao.buscarExtractoBancarioTipoMovimiento(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El Tipo de Movimiento de Extracto no existe en la base de datos.";
			throw new ExtractoBancarioTipoMovimientoException(msg,ex);
		} catch (Exception e) {
			String msg = "El Tipo de Movimiento de Extracto no pudo leerse desde la base de datos.";
			throw new ExtractoBancarioTipoMovimientoException(msg,e);
		}
	}
	
	public void borrarExtractoBancarioTipoMovimiento(final Long idExtractoBancarioTipoMovimiento) throws ExtractoBancarioTipoMovimientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					extractoBancarioTipoMovimientoDao.borrarExtractoBancarioTipoMovimiento(idExtractoBancarioTipoMovimiento);
				}
			});
		} catch (Exception e) {
			String msg = "La Tipo de Movimiento de Extracto no pudo borrase.";
			throw new ExtractoBancarioTipoMovimientoException(msg,e);
		}
	}
	
	public void borrarExtractoBancarioTipoMovimiento(final ExtractoBancarioTipoMovimiento unExtractoBancarioTipoMovimiento) throws ExtractoBancarioTipoMovimientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					extractoBancarioTipoMovimientoDao.borrarExtractoBancarioTipoMovimiento(unExtractoBancarioTipoMovimiento);
				}
			});
		} catch (Exception e) {
			String msg = "La ExtractoBancario no pudo borrase.";
			throw new ExtractoBancarioTipoMovimientoException(msg,e);
		}
	}
	
	public void actualizarExtractoBancarioTipoMovimiento(final ExtractoBancarioTipoMovimiento unExtractoBancarioTipoMovimiento) throws ExtractoBancarioTipoMovimientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					extractoBancarioTipoMovimientoDao.actualizarExtractoBancarioTipoMovimiento(unExtractoBancarioTipoMovimiento);
				}
			});
		} catch (Exception e) {
			String msg = "La ExtractoBancarioTipoMovimiento no pudo actualizarse.";
			throw new ExtractoBancarioTipoMovimientoException(msg,e);
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

	public ExtractoBancarioTipoMovimientoDao getExtractoBancarioTipoMovimientoDao() {
		return extractoBancarioTipoMovimientoDao;
	}

	public void setExtractoBancarioTipoMovimientoDao(
			ExtractoBancarioTipoMovimientoDao extractoBancarioTipoMovimientoDao) {
		this.extractoBancarioTipoMovimientoDao = extractoBancarioTipoMovimientoDao;
	}

	
	
	

}
