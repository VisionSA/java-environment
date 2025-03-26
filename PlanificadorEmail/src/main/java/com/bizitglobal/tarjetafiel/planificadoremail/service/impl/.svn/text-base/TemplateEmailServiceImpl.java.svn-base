package com.bizitglobal.tarjetafiel.planificadoremail.service.impl;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.planificadoremail.dao.TemplateEmailDao;
import com.bizitglobal.tarjetafiel.planificadoremail.exception.TemplateEmailException;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.Template;
import com.bizitglobal.tarjetafiel.planificadoremail.service.TemplateEmailService;


public class TemplateEmailServiceImpl implements TemplateEmailService {

	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private TemplateEmailDao templateEmailDao;
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones.
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;


	@SuppressWarnings("unchecked")
	@Override
	public List<Template> find(final Filtro filtro)
			throws TemplateEmailException {
		transactionTemplate
				.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return (List<Template>) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						List<Template> lista = templateEmailDao.find(filtro);
						return lista;
					}
				});
	}


	public void grabarTemplate(final Template template)
			throws TemplateEmailException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				templateEmailDao.grabarTemplate(template);
			}
		});
	}


	public void actualizarTemplate(final Template template)
			throws TemplateEmailException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				templateEmailDao.actualizarTemplate(template);
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


	public TemplateEmailDao getTemplateEmailDao() {
		return templateEmailDao;
	}


	public void setTemplateEmailDao(TemplateEmailDao templateEmailDao) {
		this.templateEmailDao = templateEmailDao;
	}

}
