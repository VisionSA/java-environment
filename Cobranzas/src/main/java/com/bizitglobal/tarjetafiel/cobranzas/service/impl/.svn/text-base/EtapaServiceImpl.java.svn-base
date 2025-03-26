package com.bizitglobal.tarjetafiel.cobranzas.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.cobranzas.dao.EtapaDao;
import com.bizitglobal.tarjetafiel.cobranzas.exception.EtapaException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Etapa;
import com.bizitglobal.tarjetafiel.cobranzas.service.EtapaService;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;


public class EtapaServiceImpl implements EtapaService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private EtapaDao etapaDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transEtapaes. 
	*/
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	public void grabarEtapa (final Etapa pObject) throws EtapaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					etapaDao.grabarEtapa(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El Etapa ya existe en la base de datos.";
			throw new EtapaException(msg,ex);
		} catch (Exception e) {
			String msg = "El Etapa no pudo ser grabada.";
			throw new EtapaException(msg,e);
		}
	}
	public List getEtapa(final Filtro filtro) throws EtapaException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = etapaDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Etapas no pudo ser leida.";
			throw new EtapaException(msg,e);
		}
	}
	public Etapa leerEtapa (final Long id) throws EtapaException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Etapa) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					Etapa archi = etapaDao.buscarEtapa(id);
				return archi;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El Etapa no existe en la base de datos.";
			throw new EtapaException(msg,ex);
		} catch (Exception e) {
			String msg = "El Etapa no pudo leerse desde la base de datos.";
			throw new EtapaException(msg,e);
		}
	}
	public void borrarEtapa (final Long id) throws EtapaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					etapaDao.borrarEtapa(id);
				}
			});
		} catch (Exception e) {
			String msg = "El Etapa no pudo borrase.";
			throw new EtapaException(msg,e);
		}
	}
	public void borrarEtapa(final Etapa pObject) throws EtapaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					etapaDao.borrarEtapa(pObject);
				}
			});
			etapaDao.borrarEtapa(pObject);
		} catch (Exception e) {
			String msg = "El Etapa no pudo borrase.";
			throw new EtapaException(msg,e);
		}
	}
	public void actualizarEtapa (final Etapa pObject) throws EtapaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					etapaDao.actualizarEtapa(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El Etapa no pudo actualizarse.";
			throw new EtapaException(msg,e);
		}
	}
	public List listarEtapas() throws EtapaException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = etapaDao.listarTodos(new Filtro());
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Etapaes no pudo ser leida.";
			throw new EtapaException(msg,e);
		}
	}
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public EtapaDao getEtapaDao() {
		return etapaDao;
	}
	/**
	* Necesario para spring.
	* @param EtapaDao, Objeto dao a setear.
	*/
	public void setEtapaDao(EtapaDao etapaDao) {
		this.etapaDao = etapaDao;
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
