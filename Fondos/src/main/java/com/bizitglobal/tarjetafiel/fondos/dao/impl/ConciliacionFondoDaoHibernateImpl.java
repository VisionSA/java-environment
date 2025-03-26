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
import com.bizitglobal.tarjetafiel.fondos.dao.ConciliacionFondoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ExtractoBancarioDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondoCabecera;
import com.bizitglobal.tarjetafiel.fondos.negocio.DetalleExtracto;
import com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancario;

public class ConciliacionFondoDaoHibernateImpl extends HibernateDaoSupport implements ConciliacionFondoDao  {
	public ConciliacionFondoDaoHibernateImpl() {
		super();
	}
	
	@Override
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ConciliacionFondo obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = new ArrayList();
				Iterator iterator = query.list().iterator();
				while(iterator.hasNext()){
					ConciliacionFondo concFondo = (ConciliacionFondo)iterator.next();
					ConciliacionFondoCabecera cabecera = new ConciliacionFondoCabecera();
					cabecera.setIdCabeceraConciliacion(concFondo.getConciliacionFondoCabecera().getIdCabeceraConciliacion());
					cabecera.setComentario(concFondo.getConciliacionFondoCabecera().getComentario());
					cabecera.setFechaGeneracion(concFondo.getConciliacionFondoCabecera().getFechaGeneracion());
					cabecera.setFechaConfirmacion(concFondo.getConciliacionFondoCabecera().getFechaConfirmacion());
					cabecera.setConciliado(concFondo.getConciliacionFondoCabecera().getConciliado());
					BancoPropio bancoPropio = new BancoPropio();
					bancoPropio.setIdBancoPropio(concFondo.getConciliacionFondoCabecera().getBancoPropio().getIdBancoPropio());
					cabecera.setBancoPropio(bancoPropio);
					cabecera.setOperadorConfirmo(null);//concFondo.getConciliacionFondoCabecera().getOperadorConfirmo());
					cabecera.setOperadorReversion(null);//concFondo.getConciliacionFondoCabecera().getOperadorReversion());
					concFondo.setConciliacionFondoCabecera(cabecera);
					list.add(concFondo);
				}
				return list;
			}
		});
	}

	@Override
	public void actualizarConciliacionFondo(ConciliacionFondo object) {
		this.getHibernateTemplate().update(object);
	}

	@Override
	public void borrarConciliacionFondo(Long id) {
		borrarConciliacionFondo(buscarConciliacionFondo(id));
		
	}

	@Override
	public void borrarConciliacionFondo(ConciliacionFondo object) {
		this.getHibernateTemplate().delete(object);
	}

	@Override
	public ConciliacionFondo buscarConciliacionFondo(Long id) {
		return (ConciliacionFondo) this.getHibernateTemplate().get(ConciliacionFondo.class, id);
	}

	@Override
	public void grabarConciliacionFondo(ConciliacionFondo object) {
		this.getHibernateTemplate().save(object);
	}
}

