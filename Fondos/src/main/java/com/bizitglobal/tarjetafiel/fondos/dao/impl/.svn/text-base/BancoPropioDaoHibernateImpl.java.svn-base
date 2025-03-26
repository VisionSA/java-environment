package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.dao.BancoPropioDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;

public class BancoPropioDaoHibernateImpl extends HibernateDaoSupport implements BancoPropioDao  {
	public BancoPropioDaoHibernateImpl() {
		super();
	}

	public void grabarBancoPropio (BancoPropio pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public BancoPropio buscarBancoPropio (Long id) {
		return (BancoPropio) this.getHibernateTemplate().get(BancoPropio.class, id);
	}
	public void borrarBancoPropio (Long id) {
		borrarBancoPropio(buscarBancoPropio(id));
	}
	public void borrarBancoPropio (BancoPropio pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarBancoPropio (BancoPropio pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM BancoPropio obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List<BancoPropio> list = query.list();

				for(BancoPropio o : list){
					getHibernateTemplate().initialize(o.getBanco());					
				}
				return list;
			}
		});
	}
	
	public List listarTodosFlex(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM BancoPropio obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				List listResult = new ArrayList();
				Iterator iterator = list.iterator();
				while(iterator.hasNext()){

					BancoPropio bancoPropio = (BancoPropio)iterator.next();
					BancoPropio newBancoPropio = new BancoPropio();
					
					newBancoPropio.setCbu(bancoPropio.getCbu());
					newBancoPropio.setHabilitado(bancoPropio.getHabilitado());
					newBancoPropio.setIdBancoPropio(bancoPropio.getIdBancoPropio());
					newBancoPropio.setNumeroCuenta(bancoPropio.getNumeroCuenta());
					newBancoPropio.setNumeroSucursal(bancoPropio.getNumeroSucursal());
					if(bancoPropio.getBanco() != null){
						newBancoPropio.setBanco(new Banco());
						newBancoPropio.getBanco().setCodigo(bancoPropio.getBanco().getCodigo());
						newBancoPropio.getBanco().setDescripcion(bancoPropio.getBanco().getDescripcion());
						newBancoPropio.getBanco().setIdBanco(bancoPropio.getBanco().getIdBanco());
					}
					if(bancoPropio.getMoneda() != null){
						newBancoPropio.setMoneda(new Moneda());
						newBancoPropio.getMoneda().setDescripcion(bancoPropio.getMoneda().getDescripcion());
						newBancoPropio.getMoneda().setIdMoneda(bancoPropio.getMoneda().getIdMoneda());
						newBancoPropio.getMoneda().setSigno(bancoPropio.getMoneda().getSigno());
					}					
					newBancoPropio.setPlaza(bancoPropio.getPlaza());
					if(bancoPropio.getSucursal() != null){
						newBancoPropio.setSucursal(new SucursalFiel());
						newBancoPropio.getSucursal().setIdSucursal(newBancoPropio.getSucursal().getIdSucursal());
						newBancoPropio.getSucursal().setNombre(bancoPropio.getSucursal().getNombre());
					}
					if(bancoPropio.getPlanCuenta() != null){
						newBancoPropio.setPlanCuenta(new PlanCuentaDos());
						newBancoPropio.getPlanCuenta().setIdPlanCuenta(bancoPropio.getPlanCuenta().getIdPlanCuenta());
						newBancoPropio.getPlanCuenta().setTitulo(bancoPropio.getPlanCuenta().getTitulo());
						
					}
					newBancoPropio.setTipoCuenta(bancoPropio.getTipoCuenta());					
					
					listResult.add(newBancoPropio);
				}
				return listResult;
			}
		});
	}
}

