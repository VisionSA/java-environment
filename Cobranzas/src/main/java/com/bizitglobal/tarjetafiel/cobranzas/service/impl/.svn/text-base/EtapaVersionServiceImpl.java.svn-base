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

import com.bizitglobal.tarjetafiel.cobranzas.dao.EtapaVersionDao;
import com.bizitglobal.tarjetafiel.cobranzas.exception.EtapaVersionException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EtapaVersion;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.PlanVersion;
import com.bizitglobal.tarjetafiel.cobranzas.service.EtapaVersionService;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;


public class EtapaVersionServiceImpl implements EtapaVersionService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private EtapaVersionDao etapaVersionDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transPlanes. 
	*/
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	public void grabarEtapaVersion (final EtapaVersion pObject) throws EtapaVersionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					etapaVersionDao.grabarEtapaVersion(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El EtapaVersion ya existe en la base de datos.";
			throw new EtapaVersionException(msg,ex);
		} catch (Exception e) {
			String msg = "El EtapaVersion no pudo ser grabada.";
			throw new EtapaVersionException(msg,e);
		}
	}
	public List getEtapaVersion(final Filtro filtro) throws EtapaVersionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = etapaVersionDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de EtapaVersiones no pudo ser leida.";
			throw new EtapaVersionException(msg,e);
		}
	}
	public EtapaVersion leerEtapaVersion (final Long id) throws EtapaVersionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (EtapaVersion) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					EtapaVersion archi = etapaVersionDao.buscarEtapaVersion(id);
				return archi;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El EtapaVersion no existe en la base de datos.";
			throw new EtapaVersionException(msg,ex);
		} catch (Exception e) {
			String msg = "El EtapaVersion no pudo leerse desde la base de datos.";
			throw new EtapaVersionException(msg,e);
		}
	}
	public void borrarEtapaVersion (final Long id) throws EtapaVersionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					etapaVersionDao.borrarEtapaVersion(id);
				}
			});
		} catch (Exception e) {
			String msg = "El EtapaVersion no pudo borrase.";
			throw new EtapaVersionException(msg,e);
		}
	}
	public void borrarEtapaVersion(final EtapaVersion pObject) throws EtapaVersionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					etapaVersionDao.borrarEtapaVersion(pObject);
				}
			});
			etapaVersionDao.borrarEtapaVersion(pObject);
		} catch (Exception e) {
			String msg = "El EtapaVersion no pudo borrase.";
			throw new EtapaVersionException(msg,e);
		}
	}
	public void actualizarEtapaVersion (final EtapaVersion pObject) throws EtapaVersionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					etapaVersionDao.actualizarEtapaVersion(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El EtapaVersion no pudo actualizarse.";
			throw new EtapaVersionException(msg,e);
		}
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public EtapaVersionDao getEtapaVersionDao() {
		return etapaVersionDao;
	}
	/**
	* Necesario para spring.
	* @param EtapaVersionDao, Objeto dao a setear.
	*/
	public void setEtapaVersionDao(EtapaVersionDao etapaVersionDao) {
		this.etapaVersionDao = etapaVersionDao;
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
	public List getEtapasVersionByFiltro(final PlanVersion planVersion)
			throws EtapaVersionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = etapaVersionDao.getEtapasVersionByFiltro(planVersion);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de EtapaVersiones no pudo ser leida.";
			throw new EtapaVersionException(msg,e);
		}
	}
}
