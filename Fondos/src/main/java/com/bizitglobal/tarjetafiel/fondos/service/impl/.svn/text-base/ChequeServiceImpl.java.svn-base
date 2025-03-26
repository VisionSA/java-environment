package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.exception.NroBancarioNoValidoException;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.interfaces.Paginacion;
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;
import com.bizitglobal.tarjetafiel.commons.util.NroBancarioValido;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeDao;
import com.bizitglobal.tarjetafiel.fondos.dao.impl.ChequeDaoHibernateImpl;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;
import com.bizitglobal.tarjetafiel.fondos.service.ChequeService;

/**
 * Implementacion de la interfaz FormaPagoService.
 */
public class ChequeServiceImpl implements ChequeService {

	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las
	 * operaciones con la base de datos.
	 */
	private ChequeDao chequeDao;

	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las
	 * transacciones.
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	public void grabarCheque(final Cheque unaCheque) throws ChequeException {
		try {

			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					chequeDao.grabarCheque(unaCheque);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La Cheque ya existe en la base de datos.";
			throw new ChequeDuplicateException(msg, ex);
		} catch (Exception e) {
			String msg = "La Cheque no pudo ser grabada.";
			throw new ChequeException(msg, e);
		}

	}

	public Cheque buscarChequePorNumero(final Cheque cheque)
			throws ChequeException {

		try {
			cheque.validar();
		} catch (NroBancarioNoValidoException e) {
			throw new ChequeException(e.getMessage());
		}

		try {

			transactionTemplate
					.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Cheque) transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							return ((ChequeDaoHibernateImpl) chequeDao)
									.buscarChequePorNumero(cheque.getNumero(),
											cheque.getSucursalBanco(), cheque
													.getBanco().getCodigo(),
											cheque.getCuenta());
						}
					});
		} catch (Exception e) {
			String msg = "La lista de Cheque no pudo ser leida.";
			throw new ChequeException(msg, e);
		}
	}

	public List getChequees() throws ChequeException {
		try {
			transactionTemplate
					.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							return chequeDao.listarTodos(new Filtro());
						}
					});
		} catch (Exception e) {
			String msg = "La lista de Cheque no pudo ser leida.";
			throw new ChequeException(msg, e);
		}
	}

	public Cheque leerCheque(Long id) throws ChequeException {
		try {
			return chequeDao.buscarCheque(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La cheque no existe en la base de datos.";
			throw new ChequeNotFoundException(msg, ex);
		} catch (Exception e) {
			String msg = "La Cheque no pudo leerse desde la base de datos.";
			throw new ChequeException(msg, e);
		}
	}

	public void borrarCheque(final Long idCheque) throws ChequeException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					chequeDao.borrarCheque(idCheque);
				}
			});
		} catch (Exception e) {
			String msg = "La Cheque no pudo borrase.";
			throw new ChequeException(msg, e);
		}
	}

	public void borrarCheque(final Cheque unaCheque) throws ChequeException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					chequeDao.borrarCheque(unaCheque);
				}
			});
		} catch (Exception e) {
			String msg = "La Cheque no pudo borrase.";
			throw new ChequeException(msg, e);
		}
	}

	public void actualizarCheque(final Cheque unaCheque) throws ChequeException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					chequeDao.actualizarCheque(unaCheque);
				}
			});
		} catch (Exception e) {
			String msg = "La Cheque no pudo actualizarse.";
			throw new ChequeException(msg, e);
		}
	}

	public List getCheques(final Filtro filtro) throws ChequeException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Cheque no pudo ser leida.";
			throw new ChequeException(msg, e);
		}
	}

	public Long contarChequesPendiente(final Filtro filtro)
			throws ChequeException {
		try {
			transactionTemplate
					.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Long) transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							return chequeDao.contarChequePendiente(filtro);
						}
					});
		} catch (Exception e) {
			String msg = "La no se pudo controlar la cantidad de cheques por fallas en la base.";
			throw new ChequeException(msg, e);
		}
	}

	public Page getChequePage(final Filtro filtro, final int pageNumber,
			final int pageSize) throws ChequeException {
		try {
			transactionTemplate
					.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Page) transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							return chequeDao.listarTodosPage(filtro,
									pageNumber, pageSize);
						}
					});
		} catch (Exception e) {
			String msg = "La lista de Cheques no pudo ser leida.";
			throw new ChequeException(msg, e);
		}
	}

	public Page getPage(Filtro filtro, int pageNumber, int pageSize)
			throws Exception {
		Page page = getChequePage(filtro, pageNumber, pageSize);
		Iterator iter = page.getThisPageElements().iterator();
		while (iter.hasNext()) {
			Cheque element = (Cheque) iter.next();
			if (element.getBanco() != null)
				element.getBanco().getDescripcion();
			if (element.getBancoPropio() != null)
				element.getBancoPropio().getLabel();
		}
		return page;
	}

	public Map obtenerUpload(final String listINidCheque)
			throws ChequeException {
		try {
			transactionTemplate
					.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (Map) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeDao.obtenerUpload(listINidCheque);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Cheque para Upload no pudo ser leida.";
			throw new ChequeException(msg, e);
		}
	}

	public void actualizarTodosProcesados(final String listINidCheque)
			throws ChequeException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					chequeDao.actualizarTodosProcesados(listINidCheque);
				}
			});
		} catch (Exception e) {
			String msg = "Los Cheque no pudo actualizarse.";
			throw new ChequeException(msg, e);
		}
	}

	/**
	 * Necesario para spring.
	 * 
	 * @return Retorna el objeto dao.
	 */
	public ChequeDao getChequeDao() {
		return chequeDao;
	}

	/**
	 * Necesario para spring.
	 * 
	 * @param monedaDao
	 *            , Objeto dao a setear.
	 */
	public void setChequeDao(ChequeDao chequeDao) {
		this.chequeDao = chequeDao;
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

	@Override
	public List getChequesByParam(BancoPropio bp, String chequeNumero,
			Short digValCheque) throws ChequeException {

		Cheque cheque = new Cheque();
		cheque.setNumero(chequeNumero);
		cheque.setDV2(digValCheque);
		
		try {
			cheque.validarNumero(); // Si el numero de cheque es inválido lanza excepción
			final Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("bancoPropio", Filtro.IGUAL, bp.getIdBancoPropio());			
			filtro.agregarCampoOperValor("numero", Filtro.IGUAL_NUMERO,Long.parseLong(chequeNumero));
			filtro.agregarCampoOperValor("tipo", Filtro.IGUAL,"'P'");
			try {
				transactionTemplate
						.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
				return (List) transactionTemplate
						.execute(new TransactionCallback() {
							public Object doInTransaction(
									TransactionStatus status) {
								return chequeDao.getChequesByParam(filtro);
							}
						});
			} catch (Exception e) {
				String msg = "La lista de Cheque no pudo ser leida.";
				throw new ChequeException(msg, e);
			}
		} catch (NroBancarioNoValidoException e1) {
			String msg = "El cheque ingresado es inválido.";
			throw new ChequeException(msg, e1);
		}
	}

}
