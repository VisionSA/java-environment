package com.bizitglobal.tarjetafiel.planificadoremail.service.impl;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.planificadoremail.dao.TipoParamTempEmailDao;
import com.bizitglobal.tarjetafiel.planificadoremail.exception.TipoParamTempEmailException;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.TipoParamTemp;
import com.bizitglobal.tarjetafiel.planificadoremail.service.TipoParamTempEmailService;


public class TipoParamTempEmailServiceImpl implements TipoParamTempEmailService {

	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private TipoParamTempEmailDao tipoParamTempEmailDao;
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones.
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;


	@SuppressWarnings("unchecked")
	@Override
	public List<TipoParamTemp> find(final Filtro filtro)
			throws TipoParamTempEmailException {
		transactionTemplate
				.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return (List<TipoParamTemp>) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						List<TipoParamTemp> lista = tipoParamTempEmailDao
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


	public TipoParamTempEmailDao getTipoParamTempEmailDao() {
		return tipoParamTempEmailDao;
	}


	public void setTipoParamTempEmailDao(
			TipoParamTempEmailDao tipoParamTempEmailDao) {
		this.tipoParamTempEmailDao = tipoParamTempEmailDao;
	}

}
