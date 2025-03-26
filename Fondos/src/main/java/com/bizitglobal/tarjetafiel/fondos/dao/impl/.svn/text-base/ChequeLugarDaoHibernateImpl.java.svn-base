package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeLugarDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeLugar;
import com.bizitglobal.tarjetafiel.fondos.negocio.Lugar;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;

public class ChequeLugarDaoHibernateImpl extends HibernateDaoSupport implements ChequeLugarDao  {
	public ChequeLugarDaoHibernateImpl() {
		super();
	}

	public void grabarChequeLugar (ChequeLugar pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public ChequeLugar buscarChequeLugar (Long id) {
		return (ChequeLugar) this.getHibernateTemplate().get(ChequeLugar.class, id);
	}
	public void borrarChequeLugar (Long id) {
		borrarChequeLugar(buscarChequeLugar(id));
	}
	public void borrarChequeLugar (ChequeLugar pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarChequeLugar (ChequeLugar pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ChequeLugar obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
	public List buscarChequesEnLugar(final Lugar lugar){
		
		return getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String sql = "SELECT chl.* FROM t_vis_fon_cheques_lugar chl " + 
					"WHERE chl.C_ID_LUGAR = :idLugar "+  
						"AND chl.c_timestamp = " +
							"(SELECT max(chlaux.c_timestamp) " +
								"FROM t_vis_fon_cheques_lugar chlAux " +
									"WHERE chl.c_id_cheque = chlaux.c_id_cheque)";
				
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.setParameter("idLugar", lugar.getIdLugar());
				sqlQuery.addEntity(ChequeLugar.class);
				List list = sqlQuery.list();
				List<Cheque> listReturn = new ArrayList<Cheque>();
				Iterator iterator = list.iterator();
				
				while(iterator.hasNext()){
					ChequeLugar chequeLugar = (ChequeLugar)iterator.next();
					Cheque cheque = chequeLugar.getCheque();
					Cheque newCheque = new Cheque();
					newCheque.setIdCheque(cheque.getIdCheque());
					newCheque.setNumero(cheque.getNumero());
					newCheque.setBanco(new Banco());
					newCheque.getBanco().setDescripcion(cheque.getBanco().getDescripcion());
					newCheque.getBanco().setIdBanco(cheque.getBanco().getIdBanco());
					newCheque.setImporte(cheque.getImporte());
					newCheque.setTipo(cheque.getTipo());
					listReturn.add(newCheque);
				}
				
				return listReturn;
			}
		});



	}
}

