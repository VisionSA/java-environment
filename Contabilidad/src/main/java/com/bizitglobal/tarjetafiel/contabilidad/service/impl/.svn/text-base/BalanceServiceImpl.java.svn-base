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
import com.bizitglobal.tarjetafiel.contabilidad.dao.BalanceDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.LoteDao;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.BalanceException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteNotFoundException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Lote;
import com.bizitglobal.tarjetafiel.contabilidad.service.BalanceService;
import com.bizitglobal.tarjetafiel.contabilidad.service.LoteService;

/**
*	Implementacion de la interfaz PlanCuentaService.
*/
public class BalanceServiceImpl implements BalanceService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private BalanceDao balanceDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManagerSpring;
    private TransactionTemplate transactionTemplateSpring;

	public List	getBalanceConsultaManualSumasYSaldos(final Ejercicio ejercicio, final Date fechaDesde, final Date fechaHasta) throws BalanceException {
		try {
			transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplateSpring.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = balanceDao.listarBalanceHojasSumasYSaldos(ejercicio, fechaDesde, fechaHasta);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de balances  no pudo ser leida.";
			throw new BalanceException(msg,e);
		}
	}
	
	
	
	
	public List getBalanceConsultaManual(final Ejercicio ejercicio, final Date fechaDesde, final Date fechaHasta) throws BalanceException {
		try {
			transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplateSpring.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = balanceDao.listarBalanceHojas(ejercicio, fechaDesde, fechaHasta);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de balances  no pudo ser leida.";
			throw new BalanceException(msg,e);
		}
	}
	
	public BalanceDao getBalanceDao() {
		return balanceDao;
	}


	public void setBalanceDao(BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}

	public PlatformTransactionManager getTransactionManagerSpring() {
		return transactionManagerSpring;
	}

	public void setTransactionManagerSpring(PlatformTransactionManager transactionManagerSpring) {
		this.transactionManagerSpring = transactionManagerSpring;
		transactionTemplateSpring = new TransactionTemplate(transactionManagerSpring);
	}

	
	


	
}

