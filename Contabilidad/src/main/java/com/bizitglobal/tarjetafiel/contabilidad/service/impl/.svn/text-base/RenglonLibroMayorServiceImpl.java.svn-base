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
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoDetalleDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.RenglonLibroMayorDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDetalleDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDetalleNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.service.AsientoDetalleService;
import com.bizitglobal.tarjetafiel.contabilidad.service.RenglonLibroMayorService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class RenglonLibroMayorServiceImpl implements RenglonLibroMayorService {
	
	private RenglonLibroMayorDao renglonLibroMayorDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManagerSpring;
    private TransactionTemplate transactionTemplateSpring;
	
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public RenglonLibroMayorDao getRenglonLibroMayorDao() {
		return renglonLibroMayorDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setRenglonLibroMayorDao(RenglonLibroMayorDao renglonLibroMayorDao) {
		this.renglonLibroMayorDao = renglonLibroMayorDao;
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
	
	public List getRenglonesLibroMayor(final Long idEmpresa, final Long idEjercicion, final Date inicio, final Date cierre, final Long idCuenta, final Date inicioEjercicio) throws AsientoDetalleException {
		try {
			transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplateSpring.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = renglonLibroMayorDao.listarTodos(idEmpresa,idEjercicion , inicio, cierre, idCuenta, inicioEjercicio);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de LoteDetalle no pudo ser leida.";
			throw new AsientoDetalleException(msg,e);
		}
	}
	
	public List getRenglonesLibroMayorFondos(final Date inicio, final Date cierre,final Long idCuenta,final Date inicioEjercicio)  throws AsientoDetalleException {
		try {
			transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplateSpring.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = renglonLibroMayorDao.listarTodosFondos(inicio, cierre, idCuenta, inicioEjercicio);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AsientosFondosItem no pudo ser leida.";
			throw new AsientoDetalleException(msg,e);
		}
	}

	
	
	
}

