package com.bizitglobal.tarjetafiel.cobranzas.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.cobranzas.dao.ReciboDao;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Recibo;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.DTO.ReciboDTO;

public class ReciboDaoImpl extends HibernateDaoSupport implements ReciboDao {

	public ReciboDaoImpl() {
		super();
	}

	@Override
	public ReciboDTO getReciboByCodigo(final Long codigo) {
		Recibo recibo = null;

		recibo = (Recibo) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer(100);
						sb.append("FROM Recibo obj ");
						sb.append("WHERE codigoRecibo=" + codigo);
						Query query = session.createQuery(sb.toString());
						return query.uniqueResult();
					}
				});

		if (recibo != null) {
			if (recibo.getEsEstadoAnulado() == 'S') {/* El recibo ha sido anulado */
				ReciboDTO dto = new ReciboDTO();
				dto.setIdRecibo(new Long(-1));
				return dto;
			} else if (recibo.getCtaCteCliente() != null) { /*
															 * El recibo ya ha
															 * sido utilizado
															 */
				ReciboDTO dto = new ReciboDTO();
				dto.setIdRecibo(new Long(-2));
				return dto;
			} else {
				return reciboToReciboDTO(recibo);
			}
		}
		return null;
	}

	private ReciboDTO reciboToReciboDTO(Recibo recibo) {
		ReciboDTO resultado = null;
		if (recibo != null) {
			resultado = new ReciboDTO();
			resultado.setIdRecibo(recibo.getIdRecibo());
			resultado.setCodigoRecibo(recibo.getCodigoRecibo());
			if (recibo.getCtaCteCliente() != null) {
				resultado.setCtaCteCliente(recibo.getCtaCteCliente().getId());
			}
			resultado.setCobrador(recibo.getCobrador());
			resultado.setDesde(recibo.getDesde());
			resultado.setHasta(recibo.getHasta());
			if (recibo.getEsEstadoAnulado() != null) {
				resultado.setEsEstadoAnulado(recibo.getEsEstadoAnulado() + "");
			}

		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReciboDTO> getRangosReciboByIDCobrador(final Long idCobrador) {

		return (List<ReciboDTO>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer(100);
						sb.append("SELECT new Recibo(rec.desde,rec.hasta,rec.cobrador) ");
						sb.append("FROM Recibo rec ");
						sb.append("WHERE rec.cobrador.idCobrador=:idCobrador ");
						sb.append("GROUP BY rec.desde, rec.hasta, rec.cobrador ");
						sb.append("ORDER BY rec.hasta DESC");
						Query query = session.createQuery(sb.toString());
						query.setLong("idCobrador", idCobrador);
						List<Recibo> lista = query.list();

						Iterator<Recibo> iter = lista.iterator();

						ArrayList<ReciboDTO> res = new ArrayList<ReciboDTO>();

						while (iter.hasNext()) {
							Recibo recibo = iter.next();
							res.add(reciboToReciboDTO(recibo));
						}
						return res;
					}
				});

	}

	@Override
	public List<ReciboDTO> getRecibosByIDCobrador(Long idCobrador) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReciboDTO> getRecibosByParam(final ReciboDTO param) {

		return (List<ReciboDTO>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer(100);
						sb.append("SELECT rec ");
						sb.append("FROM Recibo rec ");
						sb.append("WHERE 1=1 ");
						if (param != null) {
							if (param.getIdRecibo() != null) {
								sb.append("AND rec.idRecibo =:idRecibo ");
							}

							if (param.getCodigoRecibo() != null) {
								sb.append("AND rec.codigoRecibo =:codigoRecibo ");
							}

							if (param.getDesde() != null
									&& param.getHasta() != null) {
								sb.append("AND rec.codigoRecibo between :desde and :hasta ");
							}

							if (param.getEsEstadoAnulado() != null
									&& param.getEsEstadoAnulado().length() > 0) {
								sb.append("AND rec.esEstadoAnulado =:esEstadoAnulado ");
							}

							if (param.getCobrador() != null
									&& param.getCobrador().getIdCobrador() != null) {
								sb.append("AND rec.cobrador.idCobrador =:idCobrador ");
							}

							if (param.getCtaCteCliente() != null) {
								sb.append("AND rec.ctaCteCliente.idCtacteCliente =:idCtacteCliente ");
							}

						}
						sb.append("ORDER BY rec.codigoRecibo ASC");
						Query query = session.createQuery(sb.toString());

						if (param != null) {
							if (param.getIdRecibo() != null) {
								query.setLong("idRecibo", param.getIdRecibo());
							}
							if (param.getCodigoRecibo() != null) {
								query.setLong("codigoRecibo",
										param.getCodigoRecibo());
							}

							if (param.getDesde() != null
									&& param.getHasta() != null) {
								query.setLong("desde", param.getDesde());
								query.setLong("hasta", param.getHasta());
							}

							if (param.getEsEstadoAnulado() != null) {
								query.setCharacter("esEstadoAnulado", param
										.getEsEstadoAnulado().charAt(0));
							}

							if (param.getCobrador() != null
									&& param.getCobrador().getIdCobrador() != null) {
								query.setLong("idCobrador", param.getCobrador()
										.getIdCobrador());
							}

							if (param.getCtaCteCliente() != null) {
								query.setLong("idCtacteCliente",
										param.getCtaCteCliente());
							}

						}

						List<Recibo> lista = query.list();

						Iterator<Recibo> iter = lista.iterator();

						ArrayList<ReciboDTO> res = new ArrayList<ReciboDTO>();

						while (iter.hasNext()) {
							Recibo recibo = iter.next();
							res.add(reciboToReciboDTO(recibo));
						}
						return res;
					}
				});
	}

	@Override
	public void anularReciboById(final Long idRecibo) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Transaction trx = session.beginTransaction();
				StringBuffer hqlUpdate = new StringBuffer();
				hqlUpdate
						.append("UPDATE Recibo rec SET rec.esEstadoAnulado='S' ");
				hqlUpdate.append("WHERE rec.idRecibo =:idRecibo ");
				session.createQuery(hqlUpdate.toString())
						.setLong("idRecibo", idRecibo).executeUpdate();
				trx.commit();
				return null;
			}
		});

	}

	@Override
	public void insertarRangoRecibosByParam(final ReciboDTO param) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				Transaction trx = session.beginTransaction();
				
				for (Long i = param.getDesde(); i <= param.getHasta(); i++) {
					Recibo recibo = new Recibo(i,param.getDesde(),param.getHasta(),param.getCobrador(),'N');
					session.save(recibo);
				}
				
				trx.commit();
				return null;
			}
		});
		
	}

}
