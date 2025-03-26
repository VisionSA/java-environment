package com.bizitglobal.tarjetafiel.contabilidad.service.impl;
import java.util.Date;
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

import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoClienteDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoComercioDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoClienteException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoComercioException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoCliente;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoComercio;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoClienteService;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoComercioService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class AsientoComercioServiceImpl implements AsientoComercioService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private AsientoComercioDao asientoComercioDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManagerSpring;
    private TransactionTemplate transactionTemplateSpring;

	
	public void actualizarAsientoComercio (final AsientoComercio  pObject) throws AsientoComercioException {
		try {
			transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoComercioDao.actualizarAsientoComercio(pObject);
				}
			});
			asientoComercioDao.actualizarAsientoComercio(pObject);
		} catch (Exception e) {
			String msg = "El Asiento no pudo actualizarse.";
			throw new AsientoComercioException(msg,e);
		}
	}
	
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public AsientoComercioDao getAsientoComercioDao() {
		return asientoComercioDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setAsientoComercioDao(AsientoComercioDao asientoComercioDao) {
		this.asientoComercioDao = asientoComercioDao;
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto transactionManager.
	*/
	public PlatformTransactionManager getTransactionManagerSpring() {
		return transactionManagerSpring;
	}
	/**
	* Necesario para spring
	* @param transactionManager, Objeto a setear.
	*/
	public void setTransactionManagerSpring(PlatformTransactionManager transactionManagerSpring) {
		this.transactionManagerSpring = transactionManagerSpring;
		transactionTemplateSpring = new TransactionTemplate(transactionManagerSpring);
	}

	
	public List getAsientoComercioImportables(final Date inicioEjercicio, final Date finEjercicio) throws AsientoComercioException{
		try {
			transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplateSpring.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = asientoComercioDao.listarTodosImportables(inicioEjercicio, finEjercicio);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AsientosComercio para importar no pudo ser leida.";
			throw new AsientoComercioException(msg,e);
		}
	}


	public List getDetallesAsientoComercioImportado(final Long id) throws AsientoComercioException {
		try {
			transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplateSpring.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = asientoComercioDao.listarDetallesDelImportable(id);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AsientosComercioDetalles para importar no pudo ser leida.";
			throw new AsientoComercioException(msg,e);
		}
	}
	
	
	
}

