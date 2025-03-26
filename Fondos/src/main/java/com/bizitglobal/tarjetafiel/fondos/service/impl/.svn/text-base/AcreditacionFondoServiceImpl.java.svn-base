package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.AcreditacionFondoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeDao;
import com.bizitglobal.tarjetafiel.fondos.exception.AcreditacionFondoException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaAperturaException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.ArchivoAcreditacion;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionBancaria;
import com.bizitglobal.tarjetafiel.fondos.service.AcreditacionFondoService;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public class AcreditacionFondoServiceImpl implements AcreditacionFondoService {

	private AcreditacionFondoDao acreditacionFondoDao;
	private ChequeDao chequeDao;

	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las
	 * transacciones.
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	private static Logger logger = Logger.getLogger(AcreditacionFondoServiceImpl.class);

	@Override
	public void actualizarAcreditacionFondo(
			final AcreditacionFondo acreditacionFondo)
			throws AcreditacionFondoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					acreditacionFondoDao
							.actualizarAcreditacionFondo(acreditacionFondo);
				}
			});
		} catch (Exception e) {
			String msg = "La acreditacion no pudo actualizarse.";
			throw new AcreditacionFondoException(msg, e);
		}

	}

	@Override
	public void borrarAcreditacionFondo(final Long id)
			throws AcreditacionFondoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					acreditacionFondoDao.borrarAcreditacionFondo(id);
				}
			});
		} catch (Exception e) {
			String msg = "La acreditacion no pudo borrase.";
			throw new AcreditacionFondoException(msg, e);
		}

	}

	@Override
	public void borrarAcreditacionFondo(
			final AcreditacionFondo acreditacionFondo)
			throws AcreditacionFondoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					acreditacionFondoDao
							.borrarAcreditacionFondo(acreditacionFondo);
				}
			});
		} catch (Exception e) {
			String msg = "La acreditacion no pudo borrase.";
			throw new AcreditacionFondoException(msg, e);
		}

	}

	@Override
	public List getAcreditaciones() throws AcreditacionFondoException {
		try {
			transactionTemplate
					.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							return acreditacionFondoDao
									.listarTodos(new Filtro());
						}
					});
		} catch (Exception e) {
			String msg = "La lista de AcreditacionFondo no pudo ser leida.";
			throw new AcreditacionFondoException(msg, e);
		}
	}

	@Override
	public List getAcreditaciones(final Filtro filtro)
			throws AcreditacionFondoException {
		try {
			transactionTemplate
					.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							return acreditacionFondoDao.listarTodos(filtro);
						}
					});
		} catch (Exception e) {
			String msg = "La lista de acreditacion no pudo ser leida.";
			throw new AcreditacionFondoException(msg, e);
		}
	}

	@Override
	public void grabarAcreditacionFondo(
			final AcreditacionFondo acreditacionFondo)
			throws AcreditacionFondoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(
						TransactionStatus arg0) {
					acreditacionFondoDao
							.grabarAcreditacionFondo(acreditacionFondo);
				}
			});

		} catch (Exception e) {
			String msg = "La AcreditacionFondo no pudo ser grabada.";
			throw new AcreditacionFondoException(msg, e);
		}

	}

	@Override
	public AcreditacionFondo leerAcreditacionFondo(Long id)
			throws AcreditacionFondoException {
		try {
			return acreditacionFondoDao.buscarAcreditacionFondo(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La acreditacion no existe en la base de datos.";
			throw new AcreditacionFondoException(msg, ex);
		} catch (Exception e) {
			String msg = "La acreditacion no pudo leerse desde la base de datos.";
			throw new AcreditacionFondoException(msg, e);
		}
	}

	
	public ChequeDao getChequeDao() {
		return chequeDao;
	}

	public void setChequeDao(ChequeDao chequeDao) {
		this.chequeDao = chequeDao;
	}

	public AcreditacionFondoDao getAcreditacionFondoDao() {
		return acreditacionFondoDao;
	}

	public void setAcreditacionFondoDao(
			AcreditacionFondoDao acreditacionFondoDao) {
		this.acreditacionFondoDao = acreditacionFondoDao;
	}

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		transactionTemplate = new TransactionTemplate(transactionManager);
	}

	@Override
	public ConciliacionBancaria conciliarAcreditaciones(final ArchivoAcreditacion archivoAcreditacion,final Operador operador) throws AcreditacionFondoException {
		ConciliacionBancaria result = (ConciliacionBancaria) transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus arg0) {

						ConciliacionBancaria conciliacionBancaria = new ConciliacionBancaria();
						try {
							Filtro filtro = new Filtro();
							filtro.agregarCampoOperValor("tipo", Filtro.LIKE,"A");
							filtro.agregarCampoOperValor("idCheque",Filtro.MAYOR_IGUAL, archivoAcreditacion.getIdChequeMinimo());
							filtro.agregarCampoOperValor("idCheque",Filtro.MENOR_IGUAL, archivoAcreditacion.getIdChequeMaximo());
							List cheques = chequeDao.getChequesByParam(filtro);

							ArchivoAcreditacion archivoResultado = conciliacionBancaria.conciliar(cheques, archivoAcreditacion);

							if (archivoResultado != null) {
								String idConciliados = "";
								String idNoConciliados = "";
								
								//Grabamos el resultado de la conciliacion en la tabla de acreditaciones.
								acreditacionFondoDao.grabarAcreditacionFondo(archivoResultado.getAcreditacionFondo());
								
								//Actualiza los cheques que fueron conciliados en "S".
								chequeDao.actualizarAcreditacionesConciliadas();	
							}

						} catch (Exception e) {
							logger.error("ERROR EN ACREDITACION BANCARIA: " + e.getMessage() + "\n\n\t " + e.getStackTrace().toString());
						}
						return conciliacionBancaria;
					}

				});

		return result;
	}

	@Override
	public List getUltimasAcreditacionesCargadas()throws AcreditacionFondoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return acreditacionFondoDao.getUltimasAcreditacionesCargadas();
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Ultimas Acreditaciones Cargadas no pudo ser leida.";
			throw new AcreditacionFondoException(msg,e);
		}
	}

}
