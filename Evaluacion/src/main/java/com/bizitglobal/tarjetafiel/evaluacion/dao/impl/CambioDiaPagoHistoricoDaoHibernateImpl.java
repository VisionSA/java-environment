package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.evaluacion.dao.CambioDiaPagoHistoricoDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.DiaPagoDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.CambioDiaPagoHistorico;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.DiaPago;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class CambioDiaPagoHistoricoDaoHibernateImpl extends HibernateDaoSupport implements CambioDiaPagoHistoricoDao {
	public CambioDiaPagoHistoricoDaoHibernateImpl() {
		super();
	}

	@Override
	public void borrarCambioDiaPagoHistorico(Long id) {
		borrarCambioDiaPagoHistorico(buscarCambioDiaPagoHistorico(id));
	}

	@Override
	public void borrarCambioDiaPagoHistorico(CambioDiaPagoHistorico object) {
		this.getHibernateTemplate().delete(object);
	}

	@Override
	public CambioDiaPagoHistorico buscarCambioDiaPagoHistorico(Long id) {
		return (CambioDiaPagoHistorico) this.getHibernateTemplate().get(CambioDiaPagoHistorico.class, id);
	}

	@Override
	public void grabarCambioDiaPagoHistorico(CambioDiaPagoHistorico object) {
		this.getHibernateTemplate().save(object);
	}
	
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM CambioDiaPagoHistorico obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public Long buscarIdDiaPagoUltimaCambio(final Long idCliente) {
		 Long idDiaPago =(Long)getHibernateTemplate().execute(new HibernateCallback(){
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {

					return new Long(session.createSQLQuery("select c_id_dia_pago_cam from t_vis_eva_cambiodia_his where c_id_cambio_dia_his in " +
					"(select max(c_id_cambio_dia_his) from t_vis_eva_cambiodia_his where c_id_cliente=" + idCliente  + ") ").uniqueResult().toString());
				}
			});
			  
	 	    return idDiaPago; 
	}
	
	
}

