package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.CajaAperturaDao;
import com.bizitglobal.tarjetafiel.fondos.dao.impl.CajaAperturaDaoHibernateImpl;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaAperturaDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaAperturaException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaAperturaNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaException;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaApertura;
import com.bizitglobal.tarjetafiel.fondos.service.CajaAperturaService;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class CajaAperturaServiceImpl implements CajaAperturaService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private CajaAperturaDao cajaAperturaDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private PlatformTransactionManager transactionManagerSpring;
    private TransactionTemplate transactionTemplate;
    private TransactionTemplate transactionTemplateSpring;
    
	
	public void grabarCajaApertura(final CajaApertura unaCajaApertura) throws CajaAperturaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaAperturaDao.grabarCajaApertura(unaCajaApertura);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La CajaApertura ya existe en la base de datos.";
			throw new CajaAperturaDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La CajaApertura no pudo ser grabada.";
			throw new CajaAperturaException(msg,e);
		}
		
	}
	
	public List getCajaAperturaes() throws CajaAperturaException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return cajaAperturaDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de CajaApertura no pudo ser leida.";
			throw new CajaAperturaException(msg,e);
		}
	}
	
	public CajaApertura leerCajaApertura(Long id) throws CajaAperturaException {
		try {
			return cajaAperturaDao.buscarCajaApertura(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La CajaApertura no existe en la base de datos.";
			throw new CajaAperturaNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La CajaApertura no pudo leerse desde la base de datos.";
			throw new CajaAperturaException(msg,e);
		}
	}
	
	public CajaApertura getCajaAperturaFlex(final Operador operador,Set list) throws CajaAperturaException {
		try {
			
			CajaApertura cajaApertura = (CajaApertura) transactionTemplate.execute(new TransactionCallback(){
				public Object doInTransaction(TransactionStatus arg0) {
					CajaApertura cajaApertura = ((CajaAperturaDaoHibernateImpl)cajaAperturaDao).getMaxCajaApertura(operador.getCodigo());
					
					if(cajaApertura != null && cajaApertura.getEstado().toString().equals("A") && cajaApertura.getFechaCierre() == null){									
						return cajaApertura;
					} else {
						return null;
					}
				}
			});
			
			if(cajaApertura == null){
				throw new CajaAperturaException("La caja no tiene una apertura vigente");
			} else {
				return cajaApertura;
			}
			
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La CajaApertura no existe en la base de datos.";
			throw new CajaAperturaNotFoundException(msg,ex);
		} catch (Exception e) {
			//String msg = "La CajaApertura no pudo leerse desde la base de datos.";
			throw new CajaAperturaException(e.getMessage());
		}
	}
	
	public void borrarCajaApertura(final Long idCajaApertura) throws CajaAperturaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaAperturaDao.borrarCajaApertura(idCajaApertura);
				}
			});
		} catch (Exception e) {
			String msg = "La CajaApertura no pudo borrase.";
			throw new CajaAperturaException(msg,e);
		}
	}
	
	public void borrarCajaApertura(final CajaApertura unaCajaApertura) throws CajaAperturaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaAperturaDao.borrarCajaApertura(unaCajaApertura);
				}
			});
		} catch (Exception e) {
			String msg = "La CajaApertura no pudo borrase.";
			throw new CajaAperturaException(msg,e);
		}
	}
	
	public void actualizarCajaApertura(final CajaApertura unaCajaApertura) throws CajaAperturaException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaAperturaDao.actualizarCajaApertura(unaCajaApertura);
				}
			});
		} catch (Exception e) {
			String msg = "La CajaApertura no pudo actualizarse.";
			throw new CajaAperturaException(msg,e);
		}
	}
	
	public List getCajaAperturas(final Filtro filtro) throws CajaAperturaException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return cajaAperturaDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de CajaApertura no pudo ser leida.";
			throw new CajaAperturaException(msg,e);
		}
	}
	
	public String ultimoCierreCajas(final boolean esNvaApertura,final String cajasAbiertas) {
		transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return  (String) transactionTemplateSpring.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				String cajasCerradas = cajaAperturaDao.ultimoCierreCajas(esNvaApertura,cajasAbiertas);
			return cajasCerradas;
			}
		});
	}
	
	
	public String ultimaAperturaCajas() {
		transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return  (String) transactionTemplateSpring.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				String cajasAbiertas = cajaAperturaDao.ultimaAperturaCajas();
			return cajasAbiertas;
			}
		});
	}
	
	public String cajerosAsignadosUltimaAperturaCajas(){
		transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return  (String) transactionTemplateSpring.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				String cajerosAsignados = cajaAperturaDao.cajerosAsignadosUltimaAperturaCajas();
			return cajerosAsignados;
			}
		});
		
	}
	
	
	
	public String getCajasSinAbrir() {
		transactionTemplateSpring.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return  (String) transactionTemplateSpring.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				String cajasNuncaAbiertas = cajaAperturaDao.getCajasSinAbrir();
			return cajasNuncaAbiertas;
			}
		});
	}

	/* @id: 5953
	 * (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.fondos.service.CajaAperturaService#getAperturaVigente(java.lang.Long)
	 */
	public CajaApertura getAperturaVigente(Long idCaja) throws CajaAperturaException {
			return cajaAperturaDao.getAperturaVigente(idCaja);
	}	
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public CajaAperturaDao getCajaAperturaDao() {
		return cajaAperturaDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setCajaAperturaDao(CajaAperturaDao cajaAperturaDao) {
		this.cajaAperturaDao = cajaAperturaDao;
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto transactionManager.
	 */
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}
	
	
	/**
	 * Necesario para spring
	 * @param transactionManager, Objeto a setear.
	 */
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		transactionTemplate = new TransactionTemplate(transactionManager);
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
	
	
	
	
}
