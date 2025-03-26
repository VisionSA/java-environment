package com.bizitglobal.tarjetafiel.planificadoremail.service.impl;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.planificadoremail.dao.TipoOrigenEmailDao;
import com.bizitglobal.tarjetafiel.planificadoremail.exception.TipoOrigenEmailException;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.TipoOrigen;
import com.bizitglobal.tarjetafiel.planificadoremail.service.TipoOrigenEmailService;


public class TipoOrigenEmailServiceImpl implements TipoOrigenEmailService {

	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private TipoOrigenEmailDao tipoOrigenEmailDao;
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones.
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;


	@SuppressWarnings("unchecked")
	@Override
	public List<TipoOrigen> find(final Filtro filtro)
			throws TipoOrigenEmailException {
		transactionTemplate
				.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return (List<TipoOrigen>) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						List<TipoOrigen> lista = tipoOrigenEmailDao
								.find(filtro);
						return lista;
					}
				});
	}


	/**
	 * Necesario para spring.
	 * 
	 * @return Retorna el objeto transactionManager.
	 */
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}


	/**
	 * Necesario para spring
	 * 
	 * @param transactionManager
	 *            , Objeto a setear.
	 */
	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		transactionTemplate = new TransactionTemplate(transactionManager);
	}


	public TipoOrigenEmailDao getTipoOrigenEmailDao() {
		return tipoOrigenEmailDao;
	}


	public void setTipoOrigenEmailDao(TipoOrigenEmailDao tipoOrigenEmailDao) {
		this.tipoOrigenEmailDao = tipoOrigenEmailDao;
	}

}
