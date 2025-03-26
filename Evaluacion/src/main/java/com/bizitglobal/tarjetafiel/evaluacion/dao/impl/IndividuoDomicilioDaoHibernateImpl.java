package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import net.sf.cglib.core.Local;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.IndividuoDomicilioDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoDomicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Barrio;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Localidad;
import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.general.negocio.Provincia;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class IndividuoDomicilioDaoHibernateImpl extends HibernateDaoSupport
		implements IndividuoDomicilioDao {
	
	public IndividuoDomicilioDaoHibernateImpl() {
		super();
	}

	public void grabarEvaIndivDomicilios(IndividuoDomicilio pObject) {
		this.getHibernateTemplate().save(pObject);
	}

	public IndividuoDomicilio buscarEvaIndivDomicilios(Long id) {
		return (IndividuoDomicilio) this.getHibernateTemplate().get(
				IndividuoDomicilio.class, id);
	}

	public void borrarEvaIndivDomicilios(Long id) {
		borrarEvaIndivDomicilios(buscarEvaIndivDomicilios(id));
	}

	public void borrarEvaIndivDomicilios(IndividuoDomicilio pObject) {
		this.getHibernateTemplate().delete(pObject);
	}

	public void actualizarEvaIndivDomicilios(IndividuoDomicilio pObject) {
		this.getHibernateTemplate().update(pObject);
	}

	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM IndividuoDomicilio obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public Domicilio getDomicilioByIdIndividuo(final Long idIndividuo) {

		Domicilio resultado = null;

		resultado = (Domicilio) getHibernateTemplate().execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						StringBuffer sb = new StringBuffer("");
						sb.append("SELECT new Domicilio(dom.idDomicilio,dom.areaSector,dom.calleNombre,dom.calleNumero,dom.codigoPostal," +
								"dom.depto,dom.generico,dom.manzana,dom.monoblock,dom.orientacion,dom.piso,dom.localidad,dom.barrio,dom.cpa2) ");
						sb.append("FROM IndividuoEvaluacion as ind ");
						sb.append("INNER JOIN ind.domicilio as dom ");
						sb.append("WHERE ind.idIndividuo = :idIndiv");
						Query query = session.createQuery(sb.toString());
						query.setLong("idIndiv", idIndividuo);
						Domicilio domicilio = (Domicilio) query.uniqueResult();
						
						/* Esto se hace para evitar problemas de lazy*/
						if (domicilio != null && domicilio.getLocalidad() != null){
							Localidad loc = domicilio.getLocalidad();
							domicilio.setLocalidad(new Localidad());
							domicilio.getLocalidad().setIdLocalidad(loc.getIdLocalidad());
							domicilio.getLocalidad().setNombre(loc.getNombre());
							domicilio.getLocalidad().setCodigoPostal(loc.getCodigoPostal());
							
							if (loc.getPartido()!=null){
								domicilio.getLocalidad().setPartido(new Partido(loc.getPartido().getIdPartido(),loc.getPartido().getDescripcion()));
							}
							
							if(loc.getProvincia()!=null){
								domicilio.getLocalidad().setProvincia(new Provincia(loc.getProvincia().getIdProvincia(),loc.getProvincia().getNombre()));
							}
							
						}
						
						if (domicilio != null && domicilio.getBarrio() != null){
							Barrio bar = domicilio.getBarrio();
							domicilio.setBarrio(new Barrio(bar.getIdBarrio(),bar.getDescripcion()));
						}
						
						
						
						return domicilio;
					}
				});

		return resultado;

	}
}
