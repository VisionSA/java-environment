package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.dao.CajaMPDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.Caja;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaMP;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;

public class CajaMPDaoHibernateImpl extends HibernateDaoSupport implements CajaMPDao  {
	public CajaMPDaoHibernateImpl() {
		super();
	}

	public void grabarCajaMP (CajaMP pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public CajaMP buscarCajaMP (Long id) {
		return (CajaMP) this.getHibernateTemplate().get(CajaMP.class, id);
	}
	public void borrarCajaMP (Long id) {
		borrarCajaMP(buscarCajaMP(id));
	}
	public void borrarCajaMP (CajaMP pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarCajaMP (CajaMP pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM CajaMP obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
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
				sb.append("FROM CajaMP obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				List listReturn = new ArrayList();
				Iterator iterator = list.iterator();
				while(iterator.hasNext()){
					CajaMP cajaMP = (CajaMP)iterator.next();
					CajaMP newCajaMP = new CajaMP();
					newCajaMP.setFormaPago(new FormaPago());
					newCajaMP.getFormaPago().setDescripcion(cajaMP.getFormaPago().getDescripcion());
					newCajaMP.getFormaPago().setIdFormaPago(cajaMP.getFormaPago().getId());
					newCajaMP.setHabilitado(cajaMP.getHabilitado());
					newCajaMP.setIdCajaMP(cajaMP.getIdCajaMP());
					newCajaMP.setIdPlanCuenta(cajaMP.getIdPlanCuenta());
					if(cajaMP.getPlanCuentaDos() != null){						
						newCajaMP.setPlanCuentaDos(new PlanCuentaDos());											
						newCajaMP.getPlanCuentaDos().setAjusteInflacion(cajaMP.getPlanCuentaDos().getAjusteInflacion());
						newCajaMP.getPlanCuentaDos().setCaja(cajaMP.getPlanCuentaDos().getCaja());
						newCajaMP.getPlanCuentaDos().setCentroCosto(cajaMP.getPlanCuentaDos().getCentroCosto());
						newCajaMP.getPlanCuentaDos().setCodBco(cajaMP.getPlanCuentaDos().getCodBco());
						newCajaMP.getPlanCuentaDos().setCodCtaBco(cajaMP.getPlanCuentaDos().getCodCtaBco());
						newCajaMP.getPlanCuentaDos().setContab(cajaMP.getPlanCuentaDos().getContab());
						newCajaMP.getPlanCuentaDos().setCuenta(cajaMP.getPlanCuentaDos().getCuenta());
						newCajaMP.getPlanCuentaDos().setEstado(cajaMP.getPlanCuentaDos().getEstado());
						newCajaMP.getPlanCuentaDos().setFecha_carga(cajaMP.getPlanCuentaDos().getFecha_carga());
						newCajaMP.getPlanCuentaDos().setFlujoEfectivo(cajaMP.getPlanCuentaDos().getFlujoEfectivo());
						newCajaMP.getPlanCuentaDos().setFondos(cajaMP.getPlanCuentaDos().getFondos());
						newCajaMP.getPlanCuentaDos().setHabilitada(cajaMP.getPlanCuentaDos().getHabilitada());
						newCajaMP.getPlanCuentaDos().setIdPadre(cajaMP.getPlanCuentaDos().getIdPadre());
						newCajaMP.getPlanCuentaDos().setIdPlanCuenta(cajaMP.getPlanCuentaDos().getIdPlanCuenta());
						newCajaMP.getPlanCuentaDos().setImporteMaximo(cajaMP.getPlanCuentaDos().getImporteMaximo());
						newCajaMP.getPlanCuentaDos().setMoneda(cajaMP.getPlanCuentaDos().getMoneda());
						newCajaMP.getPlanCuentaDos().setNivel(cajaMP.getPlanCuentaDos().getNivel());
						newCajaMP.getPlanCuentaDos().setNumeroContable(cajaMP.getPlanCuentaDos().getNumeroContable());
						newCajaMP.getPlanCuentaDos().setOperador(cajaMP.getPlanCuentaDos().getOperador());
						newCajaMP.getPlanCuentaDos().setSaldo(cajaMP.getPlanCuentaDos().getSaldo());
						newCajaMP.getPlanCuentaDos().setSaldoHabitual(cajaMP.getPlanCuentaDos().getSaldoHabitual());
						newCajaMP.getPlanCuentaDos().setSeccion(cajaMP.getPlanCuentaDos().getSeccion());
						newCajaMP.getPlanCuentaDos().setSigno(cajaMP.getPlanCuentaDos().getSigno());
						newCajaMP.getPlanCuentaDos().setTipoCuenta(cajaMP.getPlanCuentaDos().getTipoCuenta());
						newCajaMP.getPlanCuentaDos().setTipoDeCuenta(cajaMP.getPlanCuentaDos().getTipoDeCuenta());
						newCajaMP.getPlanCuentaDos().setTitulo(cajaMP.getPlanCuentaDos().getTitulo());
						newCajaMP.getPlanCuentaDos().setUso(cajaMP.getPlanCuentaDos().getUso());														
						
					}
					
					listReturn.add(newCajaMP);					
				}
				return listReturn;
			}
		});
	}
	
}

