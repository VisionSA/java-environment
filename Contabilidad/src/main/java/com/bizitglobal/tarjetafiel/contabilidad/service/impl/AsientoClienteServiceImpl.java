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
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoClienteException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoCliente;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoClienteService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class AsientoClienteServiceImpl implements AsientoClienteService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private AsientoClienteDao asientoClienteDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManagerSpring;
    private TransactionTemplate transactionTemplateSpring;

	
	public void actualizarAsientoCliente (final AsientoCliente pObject) throws AsientoClienteException {
		try {
			transactionTemplateSpring.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoClienteDao.actualizarAsientoCliente(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "El Asiento no pudo actualizarse.";
			throw new AsientoClienteException(msg,e);
		}
	}
	
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public AsientoClienteDao getAsientoClienteDao() {
		return asientoClienteDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setAsientoClienteDao(AsientoClienteDao asientoClienteDao) {
		this.asientoClienteDao = asientoClienteDao;
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
	* @param transactionManagerSpring, Objeto a setear.
	*/
	public void setTransactionManagerSpring(PlatformTransactionManager transactionManagerSpring) {
		this.transactionManagerSpring = transactionManagerSpring;
		transactionTemplateSpring = new TransactionTemplate(transactionManagerSpring);
	}

	
	public List getAsientoClienteImportables(final Date inicioEjercicio, final Date finEjercicio) throws AsientoClienteException{
		try {
			transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplateSpring.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = asientoClienteDao.listarTodosImportables(inicioEjercicio, finEjercicio);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AsientosProveedores para importar no pudo ser leida.";
			throw new AsientoClienteException(msg,e);
		}
	}


	public List getDetallesAsientoClienteImportado(final Long id) throws AsientoClienteException {
		try {
			transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplateSpring.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = asientoClienteDao.listarDetallesDelImportable(id);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AsientosClienteDetalles para importar no pudo ser leida.";
			throw new AsientoClienteException(msg,e);
		}
	}
	
	
	
}

