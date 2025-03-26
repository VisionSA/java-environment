package com.bizitglobal.tarjetafiel.proveedores.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.ExclusionDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.IndividuoDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.RetencionDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Exclusion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Retencion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TramosRetencion;
import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorCategoriaDao;
import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorNotFoundException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorCategoria;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorService;


/**
 *	Implementacion de la interfaz ProveedorService.
 */
public class ProveedorServiceImpl implements ProveedorService {
	private static final Logger log = Logger.getLogger(ProveedorServiceImpl.class);
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ProveedorDao proveedorDao;
	private ProveedorCategoriaDao proveedorCategoriaDao;
	private RetencionDao retencionDao;
	private IndividuoDao individuoDao;
	private ExclusionDao exclusionDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
    
	public void grabarProveedor(final Proveedor unProveedor) throws ProveedorException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					proveedorDao.grabarProveedor(unProveedor);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El proveedor ya existe en la base de datos.";
			throw new ProveedorDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El proveedor no pudo ser grabado.";
			throw new ProveedorException(msg,e);
		}
	}

	public List getProveedores(final Filtro filtro) throws ProveedorException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return proveedorDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de proveedores no pudo ser leida.";
			throw new ProveedorException(msg,e);
		}
	}
	
	public Proveedor leerProveedor(Long id) throws ProveedorException {
		try {
			return proveedorDao.buscarProveedor(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El proveedor no existe en la base de datos.";
			throw new ProveedorNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El proveedor no pudo leerse desde la base de datos.";
			throw new ProveedorException(msg,e);
		}
	}
	
	public void borrarProveedor(final Long idProveedor) throws ProveedorException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					proveedorDao.borrarProveedor(idProveedor);
				}
			});
		} catch (Exception e) {
			String msg = "El proveedor no pudo borrase.";
			throw new ProveedorException(msg,e);
		}
	}
	
	public void borrarProveedor(final Proveedor unProveedor) throws ProveedorException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					proveedorDao.borrarProveedor(unProveedor);
				}
			});
		} catch (Exception e) {
			String msg = "El proveedor no pudo borrase.";
			throw new ProveedorException(msg,e);
		}
	}	
	
	public Proveedor buscarProveedor(String cuit) throws ProveedorException {
		try {
			List proveedores = proveedorDao.listarTodos(new Filtro("cuit",Filtro.IGUAL,cuit));
			Proveedor proveedor = (Proveedor)proveedores.get(0);
			return leerProveedor(proveedor.getIdProveedor());
		} catch (Exception e) {
			String msg = "No se pudo encontrar el proveedor.";
			throw new ProveedorException(msg,e);
		}
		
	}
	
	public void actualizarProveedor(final Proveedor unProveedor) throws ProveedorException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					proveedorDao.actualizarProveedor(unProveedor);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar el proveedor.";
			throw new ProveedorException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ProveedorDao getProveedorDao() {
		return proveedorDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setProveedorDao(ProveedorDao proveedorDao) {
		this.proveedorDao = proveedorDao;
	}
	
	public ProveedorCategoriaDao getProveedorCategoriaDao() {
		return proveedorCategoriaDao;
	}

	public void setProveedorCategoriaDao(ProveedorCategoriaDao proveedorCategoriaDao) {
		this.proveedorCategoriaDao = proveedorCategoriaDao;
	}

	public RetencionDao getRetencionDao() {
		return retencionDao;
	}

	public void setRetencionDao(RetencionDao retencionDao) {
		this.retencionDao = retencionDao;
	}

	public IndividuoDao getIndividuoDao() {
		return individuoDao;
	}

	public void setIndividuoDao(IndividuoDao individuoDao) {
		this.individuoDao = individuoDao;
	}

	public ExclusionDao getExclusionDao() {
		return exclusionDao;
	}

	public void setExclusionDao(ExclusionDao exclusionDao) {
		this.exclusionDao = exclusionDao;
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
	
	public void grabarYActualizarProveedor(final Proveedor unProveedor) throws ProveedorException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					proveedorDao.grabarYActualizarProveedor(unProveedor);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo grabar o actualizar el proveedor.";
			throw new ProveedorException(msg,e);
		}		
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.proveedores.service.ProveedorService#maxIdProveedor(java.util.Iterator)
	 */
	public Long maxIdProveedor() throws ProveedorException {
		try {
			return proveedorDao.maxIdProveedor();
		} catch (Exception e) {
			String msg = "No se pudo obtener el max() en proveedores.";
			throw new ProveedorException(msg,e);
		}
	}
	
	public List getRetenciones(Proveedor proveedor) throws ProveedorException {
		List resul = new ArrayList();
		try {
//			Filtro catFiltro = new Filtro("activo",Filtro.LIKE, "S");
//			catFiltro.agregarCampoOperValor("proveedor.idProveedor",Filtro.IGUAL, proveedor.getIdProveedor());
//			List categoriasList = proveedorCategoriaDao.listarTodos(catFiltro);
			Timestamp hoy = new Timestamp(Calendar.getInstance().getTime().getTime());
			List categoriasList = new ArrayList(proveedor.getProvedorCategoria());
//			log.info("categoriasList --> " + categoriasList.size());
			Iterator iterCat = categoriasList.iterator();
			while (iterCat.hasNext()) {
				ProveedorCategoria provCateg = (ProveedorCategoria) iterCat.next();
//				log.info("provCateg --> " + provCateg);
				if (provCateg != null) {
					Object[] retExc = new Object[3];
					provCateg.getJurisdiccionActividad().getCategoria().getTipoImpuesto().getDescripcion();
					retExc[0] = provCateg.getJurisdiccionActividad().getCategoria();
					
//						Filtro retFiltro = new Filtro("juridiccionActividad.idJurisdiccionActividad",Filtro.IGUAL, provCateg.getJurisdiccionActividad().getIdJurisdiccionActividad());
//						retFiltro.agregarCampoOperValor("categoria.idCategoria",Filtro.IGUAL, provCateg.getCategoria().getIdCategoria());
//						List aux1 = retencionDao.listarTodos(retFiltro);
//						if (aux1.isEmpty()) {
//							retExc[1] = null;
//						}else {
//							retExc[1] = aux1.get(0);
//						}
//						 Busco las retnciones de esa categoria con con la jurisActividad asociada
					Iterator iterator = provCateg.getJurisdiccionActividad().getRetenciones().iterator();
					Timestamp hastaRetMayor = new Timestamp(hoy.getTime());
					retExc[1] = null;
					while (iterator.hasNext()) {
						Retencion retencion = (Retencion) iterator.next();
						if (retencion.getAplicable().getIdAplicable().equals(new Long(1))) {
							if (hoy.after(retencion.getVigenciaDesde())
									&& retencion.getVigenciaHasta().after(hoy)
									&& retencion.getVigenciaHasta().after(hastaRetMayor)) {
								hastaRetMayor = retencion.getVigenciaHasta();
								retExc[1] = retencion;
							}
						}
					}
					if (retExc[1] != null) {
						Retencion retencion = (Retencion)retExc[1];
						Iterator iter = retencion.getTramosRetenciones().iterator();
						while (iter.hasNext()) {
							TramosRetencion element = (TramosRetencion) iter.next();
							element.getMontoDesde();
						}
					}
					// Busco las exclusiones de el tipo de impuesto asociado a la categoria, con el cuit del proveedor
					Filtro filtroExc = new Filtro("individuo.cuit", Filtro.LIKEXS, proveedor.getCuit().toString());
					filtroExc.agregarCampoOperValor("tipoImpuesto.idTipoImpuesto", Filtro.IGUAL,
							provCateg.getJurisdiccionActividad().getCategoria().getTipoImpuesto().getIdTipoImpuesto());
					List aux2 = exclusionDao.listarTodos(filtroExc);
					retExc[2] = null;
					if (!aux2.isEmpty()) {
						Timestamp hastaMayor = new Timestamp(hoy.getTime());
						Iterator iter = aux2.iterator();
						while (iter.hasNext()) {
							Exclusion exclusion = (Exclusion) iter.next();
							if (hoy.after(exclusion.getFechaDesde())
//									&& exclusion.getFechaHasta().after(hoy)
									&& exclusion.getFechaHasta().after(hastaMayor)) {
								hastaMayor = exclusion.getFechaHasta();
								retExc[2] = exclusion;
							}
						}
					}
					resul.add(retExc);
				}
			}
			 			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

//	public List getExclusiones(Proveedor proveedor) throws ProveedorException {
//		List exclusionList = new ArrayList();
//		try {
//			List individuoList = individuoDao.listarTodos(new Filtro("cuit", Filtro.IGUAL, proveedor.getCuit().toString()));
//			if (individuoList != null && !individuoList.isEmpty()) {
//				Individuo individuo = (Individuo) individuoList.get(0);
//				exclusionList = Convertidores.setToList(individuo.getExclusion());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return exclusionList;
//	}
}
