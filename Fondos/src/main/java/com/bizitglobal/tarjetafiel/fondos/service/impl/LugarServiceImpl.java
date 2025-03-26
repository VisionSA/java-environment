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
import com.bizitglobal.tarjetafiel.fondos.dao.LugarDao;
import com.bizitglobal.tarjetafiel.fondos.exception.LugarDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.LugarException;
import com.bizitglobal.tarjetafiel.fondos.exception.LugarNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Lugar;
import com.bizitglobal.tarjetafiel.fondos.service.LugarService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class LugarServiceImpl implements LugarService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private LugarDao lugarDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarLugar(final Lugar unaLugar) throws LugarException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					lugarDao.grabarLugar(unaLugar);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La Lugar ya existe en la base de datos.";
			throw new LugarDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La Lugar no pudo ser grabada.";
			throw new LugarException(msg,e);
		}
		
	}
	
	public List getLugares() throws LugarException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return lugarDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Lugar no pudo ser leida.";
			throw new LugarException(msg,e);
		}
	}
	
	public Lugar leerLugar(Long id) throws LugarException {
		try {
			return lugarDao.buscarLugar(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La lugar no existe en la base de datos.";
			throw new LugarNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La Lugar no pudo leerse desde la base de datos.";
			throw new LugarException(msg,e);
		}
	}
	
	public void borrarLugar(final Long idLugar) throws LugarException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					lugarDao.borrarLugar(idLugar);
				}
			});
		} catch (Exception e) {
			String msg = "La Lugar no pudo borrase.";
			throw new LugarException(msg,e);
		}
	}
	
	public void borrarLugar(final Lugar unaLugar) throws LugarException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					lugarDao.borrarLugar(unaLugar);
				}
			});
		} catch (Exception e) {
			String msg = "La Lugar no pudo borrase.";
			throw new LugarException(msg,e);
		}
	}
	
	public void actualizarLugar(final Lugar unaLugar) throws LugarException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					lugarDao.actualizarLugar(unaLugar);
				}
			});
		} catch (Exception e) {
			String msg = "La Lugar no pudo actualizarse.";
			throw new LugarException(msg,e);
		}
	}
	
	public List getLugars(final Filtro filtro) throws LugarException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return lugarDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Lugar no pudo ser leida.";
			throw new LugarException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public LugarDao getLugarDao() {
		return lugarDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setLugarDao(LugarDao lugarDao) {
		this.lugarDao = lugarDao;
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
