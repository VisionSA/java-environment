package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.BancoPropioDao;
import com.bizitglobal.tarjetafiel.fondos.dao.LoteInterbankDao;
import com.bizitglobal.tarjetafiel.fondos.exception.LoteInterbankDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.LoteInterbankException;
import com.bizitglobal.tarjetafiel.fondos.exception.LoteInterbankNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.LoteInterbank;
import com.bizitglobal.tarjetafiel.fondos.service.LoteInterbankService;
import com.bizitglobal.tarjetafiel.general.dao.BancoDao;
import com.bizitglobal.tarjetafiel.general.dao.impl.BancoDaoHibernateImpl;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.operador.dao.OperadorDao;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class LoteInterbankServiceImpl implements LoteInterbankService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private LoteInterbankDao loteInterbankDao;
	private BancoPropioDao bancoPropioDao;
	private OperadorDao operadorDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarLoteInterbank(final LoteInterbank unaLoteInterbank) throws LoteInterbankException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					loteInterbankDao.grabarLoteInterbank(unaLoteInterbank);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La LoteInterbank ya existe en la base de datos.";
			throw new LoteInterbankDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La LoteInterbank no pudo ser grabada.";
			throw new LoteInterbankException(msg,e);
		}
		
	}
	
	public List getLoteInterbankes() throws LoteInterbankException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return loteInterbankDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de LoteInterbank no pudo ser leida.";
			throw new LoteInterbankException(msg,e);
		}
	}
	
	public LoteInterbank leerLoteInterbank(Long id) throws LoteInterbankException {
		try {
			return loteInterbankDao.buscarLoteInterbank(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La loteInterbank no existe en la base de datos.";
			throw new LoteInterbankNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La LoteInterbank no pudo leerse desde la base de datos.";
			throw new LoteInterbankException(msg,e);
		}
	}
	
	public void borrarLoteInterbank(final Long idLoteInterbank) throws LoteInterbankException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					loteInterbankDao.borrarLoteInterbank(idLoteInterbank);
				}
			});
		} catch (Exception e) {
			String msg = "La LoteInterbank no pudo borrase.";
			throw new LoteInterbankException(msg,e);
		}
	}
	
	public void borrarLoteInterbank(final LoteInterbank unaLoteInterbank) throws LoteInterbankException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					loteInterbankDao.borrarLoteInterbank(unaLoteInterbank);
				}
			});
		} catch (Exception e) {
			String msg = "La LoteInterbank no pudo borrase.";
			throw new LoteInterbankException(msg,e);
		}
	}
	
	public void actualizarLoteInterbank(final LoteInterbank unaLoteInterbank) throws LoteInterbankException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					loteInterbankDao.actualizarLoteInterbank(unaLoteInterbank);
				}
			});
		} catch (Exception e) {
			String msg = "La LoteInterbank no pudo actualizarse.";
			throw new LoteInterbankException(msg,e);
		}
	}
	
	public List getLoteInterbanks(final Filtro filtro) throws LoteInterbankException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return loteInterbankDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de LoteInterbank no pudo ser leida.";
			throw new LoteInterbankException(msg,e);
		}
	}
	@Override
	public List getLoteInterbanks(final Date fechaGenerado,final Date fechaSolicitud, final Long idBanco) throws LoteInterbankException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					Iterator iter = loteInterbankDao.listarTodos(fechaGenerado, fechaSolicitud, idBanco).iterator();
					List listLoteInterBank = new ArrayList();
					LoteInterbank loteInterbank;
					SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
					
					while(iter.hasNext()){
						Map map = (Map) iter.next();
						loteInterbank = new LoteInterbank();
						if(map.get(LoteInterbank.C_ID_LOTE_INTERBANK)!=null){
							loteInterbank.setIdLoteInterbank(new Long(map.get(LoteInterbank.C_ID_LOTE_INTERBANK).toString()));  
						}
						if(map.get(LoteInterbank.C_CABECERA)!=null){
							loteInterbank.setCabecera(map.get(LoteInterbank.C_CABECERA).toString());  
						}
						if(map.get(LoteInterbank.C_FECHA_GENERADO)!=null){
							//System.out.println((map.get(LoteInterbank.C_FECHA_GENERADO).toString()));
							//Date fecha_generado = (Date)map.get(LoteInterbank.C_FECHA_GENERADO);
							loteInterbank.setFechaGenerado((Date)map.get(LoteInterbank.C_FECHA_GENERADO));  
						}
						if(map.get(LoteInterbank.C_FECHA_SOLICITUD)!=null){
							loteInterbank.setFechaSolicitud((Date)map.get(LoteInterbank.C_FECHA_SOLICITUD));  
						}
						if(map.get(LoteInterbank.C_ID_BANCO_PROPIO)!=null){
							loteInterbank.setBancoPropio((bancoPropioDao.buscarBancoPropio(new Long(map.get(LoteInterbank.C_ID_BANCO_PROPIO).toString()))));  
						}
						if(map.get(LoteInterbank.C_ID_OPERADOR)!=null){
							loteInterbank.setOperador((operadorDao.buscarOperador(new Long(map.get(LoteInterbank.C_ID_OPERADOR).toString()))));  
						}
						listLoteInterBank.add(loteInterbank);
						//loteInterbank = ((LoteInterbank) iter.next());
						//listLoteInterBank.add(loteInterbank);
					}				
					return listLoteInterBank;
				}
		});
		}catch (Exception e) {
			String msg = "La lista de LoteInterbank no pudo ser leida.";
			throw new LoteInterbankException(msg,e);
		}
	}
	@Override
	public List generarlistaInterbank(final Long id_lote_interbank) throws LoteInterbankException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					Iterator iter = loteInterbankDao.generarlistaInterbank(id_lote_interbank).iterator();
					List listLoteInterBank = new ArrayList();
					
					while(iter.hasNext()){
						Map map = (Map) iter.next();
						listLoteInterBank.add(map.get(LoteInterbank.C_CABECERA).toString());						
					}				
					return listLoteInterBank;
				}
		});
		}catch (Exception e) {
			String msg = "La lista de LoteInterbank no pudo ser leida.";
			throw new LoteInterbankException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public LoteInterbankDao getLoteInterbankDao() {
		return loteInterbankDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setLoteInterbankDao(LoteInterbankDao loteInterbankDao) {
		this.loteInterbankDao = loteInterbankDao;
	}
	
	public BancoPropioDao getBancoPropioDao() {
		return bancoPropioDao;
	}

	public void setBancoPropioDao(BancoPropioDao bancoPropioDao) {
		this.bancoPropioDao = bancoPropioDao;
	}
	
	public OperadorDao getOperadorDao() {
		return operadorDao;
	}

	public void setOperadorDao(OperadorDao operadorDao) {
		this.operadorDao = operadorDao;
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

	

	
	
}
