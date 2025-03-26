package com.bizitglobal.tarjetafiel.contabilidad.service.impl;
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
import com.bizitglobal.tarjetafiel.contabilidad.dao.EjercicioDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;
import com.bizitglobal.tarjetafiel.contabilidad.service.EjercicioService;

/**
*	Implementacion de la interfaz EjercicioService.
*/
public class EjercicioServiceImpl implements EjercicioService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private EjercicioDao ejercicioDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarEjercicio (final Ejercicio pObject) throws EjercicioException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					ejercicioDao.grabarEjercicio(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El Ejercicio ya existe en la base de datos.";
			throw new EjercicioDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El  Ejercicio no pudo ser grabado.";
			throw new EjercicioException(msg,e);
		}
	}
	public List getEjercicio(final Filtro filtro) throws EjercicioException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = ejercicioDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Ejercicio no pudo ser leida.";
			throw new EjercicioException(msg,e);
		}
	}
	public Ejercicio leerEjercicio (final Integer id) throws EjercicioException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Ejercicio) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					Ejercicio ejercicio = ejercicioDao.buscarEjercicio(id);
				return ejercicio;
				}
			});
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El Ejercicio no existe en la base de datos.";
			throw new EjercicioNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El Ejercicio no pudo leerse desde la base de datos.";
			throw new EjercicioException(msg,e);
		}
	}
	
	public void borrarEjercicio (final Integer id) throws EjercicioException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					ejercicioDao.borrarEjercicio(id);
				}
			});
		} catch (Exception e) {
			String msg = "El Ejercicio no pudo borrase.";
			throw new EjercicioException(msg,e);
		}
	}
	public void borrarEjercicio(final Ejercicio pObject) throws EjercicioException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					ejercicioDao.borrarEjercicio(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El PlanCuenta no pudo borrase.";
			throw new EjercicioException(msg,e);
		}
	}
	public void actualizarEjercicio (final Ejercicio pObject) throws EjercicioException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					ejercicioDao.actualizarEjercicio(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El Ejercicio no pudo actualizarse.";
			throw new EjercicioException(msg,e);
		}
	}
	public Ejercicio ejercicioActual() throws EjercicioException {
		try {
			List lista;
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			lista = (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					Filtro filtro = new Filtro("estado",Filtro.LIKEXS, "A");
					filtro.agregarCampoOperValor("actual", Filtro.LIKEXS, "S");
				return ejercicioDao.listarTodos(filtro);
				}
			});
			return (Ejercicio)lista.get(0);
		} catch (Exception e) {
			String msg = "No se encontro ningun ejercicio activo.";
			throw new EjercicioException(msg,e);
		}
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public EjercicioDao getEjercicioDao() {
		return ejercicioDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setEjercicioDao(EjercicioDao ejercicioDao) {
		this.ejercicioDao = ejercicioDao;
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

