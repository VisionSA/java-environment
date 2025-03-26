package com.bizitglobal.tarjetafiel.contabilidad.service.impl;
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
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Asiento;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class AsientoServiceImpl implements AsientoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private AsientoDao asientoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
	private PlatformTransactionManager transactionManagerSpring;
	private TransactionTemplate transactionTemplate;
    private TransactionTemplate transactionTemplateSpring;

	public void grabarAsiento(final Asiento pObject) throws AsientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoDao.grabarAsiento(pObject);
				}
			});
			asientoDao.grabarAsiento(pObject);
		} catch (DataIntegrityViolationException ex) {
			String msg = "el asiento ya existe en la base de datos.";
			throw new AsientoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El  Asiento no pudo ser grabado.";
			throw new AsientoException(msg,e);
		}
	}
	public List getAsiento(final Filtro filtro) throws AsientoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				return asientoDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Asiento no pudo ser leida.";
			throw new AsientoException(msg,e);
		}
	}
	
	public Long contarAsientos(final Filtro filtro) throws AsientoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Long) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				return asientoDao.contarAsientos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "Error al intentar contar asientos.";
			throw new AsientoException(msg,e);
		}
	}
	
	public Asiento leerAsiento(final Long id) throws AsientoException {
		Asiento result = null;
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Asiento) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				Asiento asiento = asientoDao.buscarAsiento(id);
				return asiento;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El Asiento no existe en la base de datos.";
			throw new AsientoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El Asiento no pudo leerse desde la base de datos.";
			throw new AsientoException(msg,e);
		}
	}
	public void borrarAsiento(final Long id) throws AsientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoDao.borrarAsiento(id);
				}
			});
		} catch (Exception e) {
			String msg = "El Asiento no pudo borrase.";
			throw new AsientoException(msg,e);
		}
	}
	
	public void borrarAsiento(final Asiento pObject) throws AsientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoDao.borrarAsiento(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El PlanCuenta no pudo borrase.";
			throw new AsientoException(msg,e);
		}
	}
	
	public void actualizarAsiento(final Asiento pObject) throws AsientoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoDao.actualizarAsiento(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El Asiento no pudo actualizarse.";
			throw new AsientoException(msg,e);
		}
	}
	
	public Long getLastIdDeAsientos(final Long idEjercicio, final Long idEmpresa) {
		transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return (Long) transactionTemplateSpring.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
			Long id = asientoDao.getLastIdDeAsientos(idEjercicio, idEmpresa);
			return id;
			}
		});
	}
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public AsientoDao getAsientoDao() {
		return asientoDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setAsientoDao(AsientoDao asientoDao) {
		this.asientoDao = asientoDao;
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

	
	public List getAsientoConsultaManual(final Filtro filtro) throws AsientoException {
		try {
			transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplateSpring.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = asientoDao.listarTodosConsultaEspecial(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de LoteDetalle no pudo ser leida.";
			throw new AsientoException(msg,e);
		}
	}
	
	public PlatformTransactionManager getTransactionManagerSpring() {
		return transactionManagerSpring;
	}

	public void setTransactionManagerSpring(PlatformTransactionManager transactionManagerSpring) {
		this.transactionManagerSpring = transactionManagerSpring;
		transactionTemplateSpring = new TransactionTemplate(transactionManagerSpring);
	}
}

