package com.bizitglobal.tarjetafiel.cobranzas.service.impl;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.cobranzas.dao.ReciboDao;
import com.bizitglobal.tarjetafiel.cobranzas.dao.impl.ReciboDaoImpl;
import com.bizitglobal.tarjetafiel.cobranzas.exception.DataBaseException;
import com.bizitglobal.tarjetafiel.cobranzas.exception.ReciboException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.DTO.ReciboDTO;
import com.bizitglobal.tarjetafiel.cobranzas.service.ReciboService;

public class ReciboServiceImpl implements ReciboService {

	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las
	 * operaciones con la base de datos.
	 */
	private ReciboDao reciboDao;

	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las
	 * transacciones.
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public ReciboDao getReciboDao() {
		return reciboDao;
	}

	public void setReciboDao(ReciboDao reciboDao) {
		this.reciboDao = reciboDao;
	}

	@Override
	public ReciboDTO getReciboByCodigo(final Long codigo)
			throws DataBaseException, ReciboException {
		ReciboDTO result = null;

		try {
			transactionTemplate
					.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			result = (ReciboDTO) transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							return ((ReciboDaoImpl) reciboDao)
									.getReciboByCodigo(codigo);
						}
					});
		} catch (Exception e) {
			throw new DataBaseException("Error en la base de datos");
		}

		if (result == null) {
			throw new ReciboException("No existe el recibo ingresado");
		} else if (result.getIdRecibo() == -1) {
			throw new ReciboException(
					"El numero ingresado pertenece a\nun recibo anulado");
		} else if (result.getIdRecibo() == -2) {
			throw new ReciboException(
					"El recibo ingresado ya ha sido utilizado.");
		}

		return result;
	}

	@Override
	public List<ReciboDTO> getRangosReciboByIDCobrador(final Long idCobrador)
			throws DataBaseException, ReciboException {

		List<ReciboDTO> result = null;

		try {
			result = reciboDao.getRangosReciboByIDCobrador(idCobrador);
		} catch (Exception e) {
			throw new DataBaseException("Error en la base de datos");
		}

		return result;

		// try {
		// transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		// result = (List<ReciboDTO>) transactionTemplate
		// .execute(new TransactionCallback() {
		// public Object doInTransaction(TransactionStatus status) {
		// return ((ReciboDaoImpl) reciboDao)
		// .getRangosReciboByIDCobrador(idCobrador);
		// }
		// });
		// }catch(Exception e){
		// throw new DataBaseException("Error en la base de datos");
		// }

	}

	@Override
	public List<ReciboDTO> getRecibosByParam(ReciboDTO param)
			throws DataBaseException, ReciboException {

		List<ReciboDTO> result = null;

		try {
			result = reciboDao.getRecibosByParam(param);
		} catch (Exception e) {
			throw new DataBaseException("Error en la base de datos");
		}

		return result;
	}

	@Override
	public void anularReciboById(Long idRecibo) throws DataBaseException,
			ReciboException {
		try {
			reciboDao.anularReciboById(idRecibo);
		} catch (Exception e) {
			throw new DataBaseException("Error en la base de datos");
		}

	}

	@Override
	public void insertarRangoRecibosByParam(ReciboDTO param)
			throws DataBaseException, ReciboException {

		List<ReciboDTO> result = null;

		// Buscar rango disponible
		ReciboDTO rec = new ReciboDTO();
		rec.setDesde(param.getDesde());
		rec.setHasta(param.getHasta());

		try {
			result = reciboDao.getRecibosByParam(rec);
		} catch (Exception e) {
			throw new DataBaseException("Error en la base de datos");
		}

		if (result.size() > 0) {
			throw new ReciboException(
					"El rango ingresado no está disponible.\nPor favor modifique el rango.");
		} else {
			try {
				reciboDao.insertarRangoRecibosByParam(param);
			} catch (Exception e) {
				throw new DataBaseException("Error en la base de datos");
			}
		}

	}

}
