package com.bizitglobal.tarjetafiel.fondos.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.paginacion.HibernatePage;
import com.bizitglobal.tarjetafiel.commons.paginacion.Paginador;
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;
import com.bizitglobal.tarjetafiel.commons.paginacion2.ScrollPage;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.dao.MovimientoDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoItem;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeEstado;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeHistorial;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeLugar;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;
import com.bizitglobal.tarjetafiel.fondos.negocio.PersistenciaCuentaUnica;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;

public class MovimientoDaoHibernateImpl extends HibernateDaoSupport implements
		MovimientoDao {
	public MovimientoDaoHibernateImpl() {
		super();
	}

	public void grabarMovimiento(Movimiento pObject) {
		this.getHibernateTemplate().save(pObject);
	}

	public Movimiento buscarMovimiento(Long id) {
		return (Movimiento) this.getHibernateTemplate().get(Movimiento.class,
				id);
	}

	public void borrarMovimiento(Long id) {
		borrarMovimiento(buscarMovimiento(id));
	}

	public void borrarMovimiento(Movimiento pObject) {
		this.getHibernateTemplate().delete(pObject);
	}

	public void actualizarMovimiento(Movimiento pObject) {
		this.getHibernateTemplate().update(pObject);
	}

	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT obj ");
				sb.append("FROM Movimiento obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				return query.list();
			}
		});
	}

	public List listarTodosPagina(Filtro filtro, final Paginador paginador) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT obj ");
				sb.append("FROM Movimiento obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				HibernatePage hibernatePage = HibernatePage
						.getHibernatePageInstance(query, paginador.getPagina(),
								paginador.getCantidadRegistros());
				List list = hibernatePage.getThisPageElements();
				paginador.setCantidadPaginas(hibernatePage.getLastPageNumber());
				paginador.setCantidadRegistros(hibernatePage
						.getThisPageLastElementNumber());
				paginador.setPagina(hibernatePage.getPageNumber());

				return list;
			}
		});
	}

	public Page listarTodosPage(Filtro filtro, final int pageNumber,
			final int pageSize) {
		final String hql = filtro.getHQL();
		return (Page) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT obj ");
				sb.append("FROM Movimiento obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				return new ScrollPage(query, pageNumber, pageSize);
			}
		});
	}

	@Override
	public Long insertarMovimiento(Movimiento movimiento) {

		this.grabarMovimiento(movimiento);
		this.grabarMovimientosMP(movimiento);
		return movimiento.getIdMovimiento();
	}

	private void grabarMovimientosMP(Movimiento movimiento) {
		Iterator iterator = movimiento.getMovimientosMP().iterator();

		Integer renglonAsientoItem = 2;

		List chequeLugarList = new ArrayList();

		movimiento.setFecha(new Date());

		while (iterator.hasNext()) {

			MovimientoMP movimientoMP = (MovimientoMP) iterator.next();

			long formaPago = movimientoMP.getFormaPago().getIdFormaPago();			
			if (formaPago != 10 && formaPago != 8) {
				movimientoMP.setMovimiento(movimiento);
				String medio = movimientoMP.getFormaPago().getDescripcion()
						+ "       ";
				medio = medio.substring(0, 10);

				if (movimientoMP.getCheque() != null) {

					AsientoItem asientoItem = movimientoMP.getAsientoItem();

					if (movimientoMP.getCheque().getTipo().toString()
							.equals("P")
							|| movimientoMP.getCheque().getTipo().toString()
									.equals("D")) {
						// tengo que buscar la cuenta segun estado del cheque
						// y cambiar
						PlanCuentaDos planCuentaDos = buscarPlanCuenta(movimientoMP);
						if (planCuentaDos != null) {
							asientoItem.setIdPlanCuenta(planCuentaDos
									.getIdPlanCuenta());
							asientoItem.setLeyenda(planCuentaDos.getTitulo());
						}
						asientoItem.setImporte(movimientoMP.getCheque()
								.getImporte());
						asientoItem.setSigno(1);
					}

					movimientoMP.getAsientoItem().setNroRenglon(
							renglonAsientoItem);

					ChequeHistorial chequeHistorial = new ChequeHistorial();
					chequeHistorial.setAsientoItem(asientoItem);
					/*
					 * if(movimientoMP.getCheque().getIdCheque() == null){
					 * hibernateTemplate.save(movimientoMP.getCheque()); }
					 */

					chequeHistorial.setAsientoItem(movimientoMP
							.getAsientoItem());
					chequeHistorial.setCheque(movimientoMP.getCheque());
					chequeHistorial.setMovimientoMP(movimientoMP);
					chequeHistorial.setTimestamp(new Date());

					if (movimientoMP.getCheque().getTipo().toString()
							.equals("P")) {
						chequeHistorial
								.setChequeEstado((ChequeEstado) this.getHibernateTemplate()
										.get(ChequeEstado.class, new Long(4)));// Anulado
																				// para
																				// cheques
																				// propios
					} else {
						chequeHistorial.setChequeEstado(movimientoMP
								.getCheque().getChequeEstado());
					}

					this.getHibernateTemplate().save(chequeHistorial);
					/*
					 * movimientoMP.setChequeHistorial(new HashSet());
					 * movimientoMP.getChequeHistorial().add(chequeHistorial);
					 */


					ChequeLugar chequeLugar = new ChequeLugar();
					chequeLugar.setCheque(movimientoMP.getCheque());
					chequeLugar.setLugar(movimiento.getCaja().getLugar());
					chequeLugar.setTimestamp(new Date());

					chequeLugarList.add(chequeLugar);

				} else {
					movimientoMP.getAsientoItem().setNroRenglon(
							renglonAsientoItem);
				}

				renglonAsientoItem++;

			}

		}

		new PersistenciaCuentaUnica(ConceptoGen.CODIGO_FONDO_SU_PAGO, movimiento,
				this.getHibernateTemplate());

		if (chequeLugarList.size() > 0) {
			this.getHibernateTemplate().saveOrUpdateAll(chequeLugarList);
		}

		this.getHibernateTemplate().update(movimiento);

	}

	private PlanCuentaDos buscarPlanCuenta(final MovimientoMP movimientoMP) {

		return (PlanCuentaDos) this.getHibernateTemplate()
				.execute(new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						String sql = "Select * From T_VIS_CONT_PLAN_CUENTA WHERE "
								+ "C_ID_ESTADO_CHEQUE = "
								+ movimientoMP.getCheque().getChequeEstado()
										.getIdChequeEstado()
								+ " AND C_COD_BCO = "
								+ movimientoMP.getCheque().getBancoPropio()
										.getBanco().getIdBanco()
								+ " AND C_COD_CTA_BCO = "
								+ movimientoMP.getCheque().getBancoPropio()
										.getNumeroCuenta();
						if (!movimientoMP.getCheque().getChequeEstado()
								.getIdChequeEstado().equals(2L))
							sql += " AND C_ID_FORMA_PAGO = "
									+ movimientoMP.getFormaPago()
											.getIdFormaPago();
						SQLQuery sqlQuery = session.createSQLQuery(sql);
						sqlQuery.addEntity(PlanCuentaDos.class);
						logger.info("codBanco "
								+ movimientoMP.getCheque().getBancoPropio()
										.getBanco().getIdBanco());
						logger.info("estadoCheque "
								+ movimientoMP.getCheque().getChequeEstado()
										.getIdChequeEstado());
						logger.info("codCtaBanco "
								+ movimientoMP.getCheque().getBancoPropio()
										.getNumeroCuenta());

						PlanCuentaDos planCuentaDos = (PlanCuentaDos) sqlQuery
								.uniqueResult();
						return planCuentaDos;
					}
				});

	}

}
