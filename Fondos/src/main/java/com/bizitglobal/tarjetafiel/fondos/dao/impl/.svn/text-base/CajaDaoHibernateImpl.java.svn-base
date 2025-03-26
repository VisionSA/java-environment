package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.fondos.dao.CajaDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.Caja;
import com.bizitglobal.tarjetafiel.fondos.negocio.Lugar;
import com.bizitglobal.tarjetafiel.general.dao.MonedaDao;
import com.bizitglobal.tarjetafiel.general.negocio.Impresora;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;

public class CajaDaoHibernateImpl extends HibernateDaoSupport implements CajaDao  {
	public CajaDaoHibernateImpl() {
		super();
	}

	public void grabarCaja (Caja pObject) {
		pObject.setLugar(new Lugar());
		pObject.getLugar().setDescripcion("Caja " + pObject.getDescripcion());
		pObject.getLugar().setTipo('F');
		this.getHibernateTemplate().save(pObject);
	}
	public Caja buscarCaja (Long id) {
		return (Caja) this.getHibernateTemplate().get(Caja.class, id);
	}
	
	public Caja buscarCajaPorOperadorFlex (final Long id) {
		List cajas = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("Select c From Caja c Where c.operador.codigo = ?");								
				Query query = session.createQuery(sb.toString());
				List list = new ArrayList();
				query.setLong(0, id.longValue());
				list = query.list();
				Caja caja = list.size() > 0 ? (Caja)list.get(0) : null;
				Caja newCaja = null;		
				list = new ArrayList();
				if(caja != null){
					newCaja = new Caja();
					newCaja.setDescripcion(caja.getDescripcion());
					newCaja.setFechaModificacion(caja.getFechaModificacion());
					newCaja.setHabilitada(caja.getHabilitada());
					newCaja.setIdCaja(caja.getIdCaja());

					if(caja.getLugar() != null){
						newCaja.setLugar(new Lugar());
						newCaja.getLugar().setDescripcion(caja.getLugar().getDescripcion());
						newCaja.getLugar().setIdLugar(caja.getLugar().getIdLugar());
						newCaja.getLugar().setTipo(caja.getLugar().getTipo());
					}
					
					if(caja.getImpresora() != null){
						newCaja.setImpresora(new Impresora());
						newCaja.getImpresora().setDescripcion(caja.getImpresora().getDescripcion());
						newCaja.getImpresora().setHabilitado(caja.getImpresora().getHabilitado());
						newCaja.getImpresora().setIdImpresora(caja.getImpresora().getIdImpresora());
						newCaja.getImpresora().setPath(caja.getImpresora().getPath());
						newCaja.getImpresora().setPort(caja.getImpresora().getPort());
						newCaja.getImpresora().setNombre(caja.getImpresora().getNombre());
					}

					if(caja.getPlanCuenta() != null){
						newCaja.setPlanCuenta(new PlanCuentaDos());
						newCaja.getPlanCuenta().setAjusteInflacion(caja.getPlanCuenta().getAjusteInflacion());
						newCaja.getPlanCuenta().setCaja(caja.getPlanCuenta().getCaja());
						newCaja.getPlanCuenta().setCentroCosto(caja.getPlanCuenta().getCentroCosto());
						newCaja.getPlanCuenta().setCodBco(caja.getPlanCuenta().getCodBco());
						newCaja.getPlanCuenta().setCodCtaBco(caja.getPlanCuenta().getCodCtaBco());
						newCaja.getPlanCuenta().setContab(caja.getPlanCuenta().getContab());
						newCaja.getPlanCuenta().setCuenta(caja.getPlanCuenta().getCuenta());
						newCaja.getPlanCuenta().setEstado(caja.getPlanCuenta().getEstado());
						newCaja.getPlanCuenta().setFecha_carga(caja.getPlanCuenta().getFecha_carga());
						newCaja.getPlanCuenta().setFlujoEfectivo(caja.getPlanCuenta().getFlujoEfectivo());
						newCaja.getPlanCuenta().setFondos(caja.getPlanCuenta().getFondos());
						newCaja.getPlanCuenta().setHabilitada(caja.getPlanCuenta().getHabilitada());
						newCaja.getPlanCuenta().setIdPadre(caja.getPlanCuenta().getIdPadre());
						newCaja.getPlanCuenta().setIdPlanCuenta(caja.getPlanCuenta().getIdPlanCuenta());
						newCaja.getPlanCuenta().setImporteMaximo(caja.getPlanCuenta().getImporteMaximo());
						newCaja.getPlanCuenta().setMoneda(caja.getPlanCuenta().getMoneda());
						newCaja.getPlanCuenta().setNivel(caja.getPlanCuenta().getNivel());
						newCaja.getPlanCuenta().setNumeroContable(caja.getPlanCuenta().getNumeroContable());
						newCaja.getPlanCuenta().setOperador(caja.getPlanCuenta().getOperador());
						newCaja.getPlanCuenta().setSaldo(caja.getPlanCuenta().getSaldo());
						newCaja.getPlanCuenta().setSaldoHabitual(caja.getPlanCuenta().getSaldoHabitual());
						newCaja.getPlanCuenta().setSeccion(caja.getPlanCuenta().getSeccion());
						newCaja.getPlanCuenta().setSigno(caja.getPlanCuenta().getSigno());
						newCaja.getPlanCuenta().setTipoCuenta(caja.getPlanCuenta().getTipoCuenta());
						newCaja.getPlanCuenta().setTipoDeCuenta(caja.getPlanCuenta().getTipoDeCuenta());
						newCaja.getPlanCuenta().setTitulo(caja.getPlanCuenta().getTitulo());
						newCaja.getPlanCuenta().setUso(caja.getPlanCuenta().getUso());						
					}
					
					if(caja.getSucursal() != null){
						newCaja.setSucursal(new SucursalFiel());
						newCaja.getSucursal().setIdSucursal(caja.getSucursal().getIdSucursal());
						newCaja.getSucursal().setNombre(caja.getSucursal().getNombre());
					}
					
					list.add(newCaja);
					
				}
				
				return list;
				
			}
		});	
		
		if(cajas.size()==1){
			return (Caja)cajas.get(0);
		}
		
		return null;
		
	}
	
	public void borrarCaja (Long id) {
		borrarCaja(buscarCaja(id));
	}
	public void borrarCaja (Caja pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarCaja (Caja pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Caja obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}

