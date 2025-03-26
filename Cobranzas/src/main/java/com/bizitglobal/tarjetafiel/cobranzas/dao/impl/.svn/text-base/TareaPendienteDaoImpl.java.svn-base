package com.bizitglobal.tarjetafiel.cobranzas.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.cobranzas.dao.TareaPendienteDao;
import com.bizitglobal.tarjetafiel.cobranzas.exception.EjecucionPlanException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Accion;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EjecucionPlan;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EjecucionPlanAsignacionCobrador;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Etapa;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Plan;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.TareaPendiente;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Cobrador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;

public class TareaPendienteDaoImpl extends HibernateDaoSupport implements
		TareaPendienteDao {

	private DataSource dataSource;
	private JdbcTemplate jt;

	public TareaPendienteDaoImpl() {
		super();
	}

	@Override
	public List<TareaPendiente> listarTareasPendientes() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<TareaPendiente> listaTareasPendientes = (List) getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List<TareaPendiente> lista = new ArrayList();
						String sql = "Select ejecPlan.c_fecha_ejecucion as fecha, decode(ejecPlan.c_id_accion, 1, 'Envios de Correo', 8, 'AsignaciÃ³n de Cobrador') as descripcion,ejecPlan.c_id_accion as idAccion, count(c_id_ejecucion_plan) as cantidad from t_vis_cob_ejecucion_plan ejecPlan "
								+ "where c_id_accion IN (1,8) and c_confirmo_accion = 'N' "
								+ "group by ejecPlan.c_fecha_ejecucion, decode(ejecPlan.c_id_accion, 1, 'Envios de Correo', 8, 'AsignaciÃ³n de Cobrador'),ejecPlan.c_id_accion  order by ejecPlan.c_fecha_ejecucion";
						jt = new JdbcTemplate(dataSource);
						List rows = jt.queryForList(sql.toString());
						Iterator iter = rows.iterator();
						while (iter.hasNext()) {
							Map map = (Map) iter.next();
							TareaPendiente tarea = new TareaPendiente();
							if (map.get("FECHA") != null) {
								tarea.setFecha((Date) map.get("FECHA"));
							} else {
								tarea.setFecha(null);
							}
							if (map.get("DESCRIPCION") != null) {
								tarea.setDescripcion(map.get("DESCRIPCION")
										.toString());
							} else {
								tarea.setDescripcion("");
							}
							if (map.get("IDACCION") != null) {
								tarea.setIdAccion(Integer.valueOf(
										map.get("IDACCION").toString())
										.intValue());
							} else {
								tarea.setIdAccion(0);
							}
							if (map.get("CANTIDAD") != null) {
								tarea.setCantidad(Integer.valueOf(
										map.get("CANTIDAD").toString())
										.intValue());
							} else {
								tarea.setCantidad(0);
							}
							lista.add(tarea);
						}
						return lista;
					}
				});
		return listaTareasPendientes;
	}

	@Override
	/*
	 * En base a una tarea pendiente, lista sus detalles y deja ver cada uno de
	 * los objetos plan ejecucion que la conforman
	 */
	public List<EjecucionPlanAsignacionCobrador> listarDetallesTareaAsignacionCobrador(
			final TareaPendiente tareaP, final String estado) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<EjecucionPlanAsignacionCobrador> listadoDetalladoDeTarea = (List) getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List<EjecucionPlanAsignacionCobrador> lista = new ArrayList();

						Calendar cal = Calendar.getInstance();
						cal.setTime(tareaP.getFecha());

						String sql = "select ep.c_id_ejecucion_plan, ep.c_fecha_ejecucion, "
								+ "ep.c_id_plan, ep.c_id_etapa, ep.c_id_accion, ep.c_confirmo_accion, ep.c_comentario, "
								+ "ep.c_url_archivo as URLARCHIVO, ep.c_id_cobrador as IDCOLABORADOR, ep.c_id_usuario, "
								+ "indi.c_apellido as APELLINDI, indi.c_nombres as NOMBREINDI, indi.c_id_individuo as IDINDIVIDUO, "
								+ "cli.c_id_cliente as NROCLIENTE, "
								+ "cobra.c_id_cobrador as IDCOBRADOR, "
								+ "cobra.c_apellido as APELLCOBRA, cobra.c_nombre as NOMBRECOBRA, "
								+ "sum(liq.c_monto_total - liq.c_importe_pagado) as IMPORTEQUEDEBE "
								+ "from t_vis_cob_ejecucion_plan ep inner join t_vis_tra_clientes cli on ep.c_id_usuario = cli.c_id_cliente "
								+ " inner join  t_vis_eva_individuos indi on cli.c_id_individuo = indi.c_id_individuo "
								+ " inner join t_vis_tra_colaboradores colab on ep.c_id_cobrador = colab.c_id_colaborador "
								+ " left outer join t_vis_eva_cobradores cobra on colab.c_id_cobrador = cobra.c_id_cobrador "
								+ " inner join t_vis_tra_liq_clientes liq on cli.c_id_cliente = liq.c_id_cliente "
								+ " where ep.c_id_accion = "
								+ tareaP.getIdAccion()
								+ " and trunc(ep.c_fecha_ejecucion) = to_date('"
								+ cal.get(Calendar.DATE)
								+ "/"
								+ (cal.get(Calendar.MONTH) + 1)
								+ "/"
								+ cal.get(Calendar.YEAR)
								+ "','dd/mm/yyyy') and "
								+ " ep.c_confirmo_accion = '"
								+ estado
								+ "'"
								+ "  group by ep.c_id_ejecucion_plan, ep.c_fecha_ejecucion, ep.c_id_plan, ep.c_id_etapa, ep.c_id_accion, ep.c_confirmo_accion, ep.c_comentario, ep.c_url_archivo, ep.c_id_cobrador, ep.c_id_usuario, "
								+ " indi.c_apellido, indi.c_nombres, indi.c_id_individuo, "
								+ " cli.c_id_cliente, cobra.c_id_cobrador, "
								+ " cobra.c_apellido, cobra.c_nombre";

						// SQLQuery query = session.createSQLQuery(sql);
						jt = new JdbcTemplate(dataSource);
						List rows = jt.queryForList(sql.toString());
						System.out.println(sql.toString());
						Iterator iter = rows.iterator();
						while (iter.hasNext()) {
							Map map = (Map) iter.next();
							EjecucionPlanAsignacionCobrador ejecucionPlan = new EjecucionPlanAsignacionCobrador();
							if (map.get("C_ID_EJECUCION_PLAN") != null) {
								ejecucionPlan.setIdEjecucionPlan(Long
										.valueOf(map.get("C_ID_EJECUCION_PLAN")
												.toString()));
							} else {
								ejecucionPlan.setIdEjecucionPlan(null);
							}
							if (map.get("C_FECHA_EJECUCION") != null) {
								ejecucionPlan.setFechaEjecucion((Date) map
										.get("C_FECHA_EJECUCION"));
							} else {
								ejecucionPlan.setFechaEjecucion(null);
							}
							if (map.get("C_ID_PLAN") != null) {
								Plan p = new Plan();
								p.setIdPlan(Integer.valueOf(map
										.get("C_ID_PLAN").toString()));
								ejecucionPlan.setPlan(p);
							} else {
								ejecucionPlan.setPlan(null);
							}
							if (map.get("C_ID_ETAPA") != null) {
								Etapa e = new Etapa();
								e.setIdEtapa(Integer.valueOf(map.get(
										"C_ID_ETAPA").toString()));
								ejecucionPlan.setEtapa(e);
							} else {
								ejecucionPlan.setEtapa(null);
							}
							if (map.get("C_ID_ACCION") != null) {
								Accion a = new Accion();
								a.setIdAccion(Integer.valueOf(map.get(
										"C_ID_ACCION").toString()));
								ejecucionPlan.setAccion(a);
							} else {
								ejecucionPlan.setAccion(null);
							}
							if (map.get("C_CONFIRMO_ACCION") != null) {
								ejecucionPlan.setConfirmoAccion(map.get(
										"C_CONFIRMO_ACCION").toString());
							} else {
								ejecucionPlan.setConfirmoAccion("");
							}
							if (map.get("C_COMENTARIO") != null) {
								ejecucionPlan.setComentario(map.get(
										"C_COMENTARIO").toString());
							} else {
								ejecucionPlan.setComentario("");
							}
							if (map.get("URLARCHIVO") != null) {
								ejecucionPlan.setUrlArchivo(map.get(
										"URLARCHIVO").toString());
							} else {
								ejecucionPlan.setUrlArchivo("");
							}
							if (map.get("IDCOLABORADOR") != null) {
								Colaborador colabora = new Colaborador();
								colabora.setIdColaborador(Long.valueOf(map.get(
										"IDCOLABORADOR").toString()));
								if (map.get("IDCOBRADOR") != null) {
									colabora.setCobrador(new Cobrador());
									colabora.getCobrador().setIdCobrador(
											Long.valueOf(map.get("IDCOBRADOR")
													.toString()));
								}
								ejecucionPlan.setCobrador(colabora);
							} else {
								ejecucionPlan.setCobrador(null);
							}
							if (map.get("C_ID_USUARIO") != null) {

								ejecucionPlan.setIdUsuario(Long.valueOf(map
										.get("C_ID_USUARIO").toString()));
							} else {
								ejecucionPlan.setIdUsuario(null);
							}

							if (map.get("APELLINDI") != null) {

								ejecucionPlan.setApellidoCliente(map.get(
										"APELLINDI").toString());
							} else {
								ejecucionPlan.setApellidoCliente("");
							}
							if (map.get("NOMBREINDI") != null) {

								ejecucionPlan.setNombreCliente(map.get(
										"NOMBREINDI").toString());
							} else {
								ejecucionPlan.setNombreCliente("");
							}
							if (map.get("IDINDIVIDUO") != null) {
								ejecucionPlan.setIdIndividuo(Long.valueOf(map
										.get("IDINDIVIDUO").toString()));
							} else {
								ejecucionPlan.setIdIndividuo(null);
							}
							if (map.get("NROCLIENTE") != null) {

								ejecucionPlan.setNumeroCliente(Long.valueOf(map
										.get("NROCLIENTE").toString()));
							} else {
								ejecucionPlan.setNumeroCliente(null);
							}
							if (map.get("APELLCOBRA") != null) {

								ejecucionPlan.setApellidoCobrador(map.get(
										"APELLCOBRA").toString());
							} else {
								ejecucionPlan.setApellidoCobrador(null);
							}
							if (map.get("NOMBRECOBRA") != null) {

								ejecucionPlan.setNombreCobrador(map.get(
										"NOMBRECOBRA").toString());
							} else {
								ejecucionPlan.setNombreCobrador(null);
							}
							if (map.get("IMPORTEQUEDEBE") != null) {
								ejecucionPlan.setMontoAdeudado(Double
										.valueOf(map.get("IMPORTEQUEDEBE")
												.toString()));
							} else {
								ejecucionPlan.setMontoAdeudado(null);
							}

							lista.add(ejecucionPlan);
						}
						return lista;
					}
				});
		return listadoDetalladoDeTarea;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getListaTareasByParam(final String estado,
			final Date fechaDesde, final Date fechaHasta)
			throws EjecucionPlanException {

		List<TareaPendiente> listaTareasPendientes = null;

		final Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			listaTareasPendientes = (List) getHibernateTemplate().executeFind(
					new HibernateCallback() {
						public Object doInHibernate(Session session) {
							List<TareaPendiente> lista = new ArrayList();

							StringBuffer sql = new StringBuffer(
									"Select trunc(ejecPlan.c_fecha_ejecucion) as FECHA, ");
							sql.append("decode(ejecPlan.c_id_accion, ");
							sql.append("1, 'Aviso de Mora', ");
							sql.append("3, 'Teledirecto', ");
							sql.append("8, 'Asignación de Cobrador', ");
							sql.append("11, 'Aviso Urgente', ");
							sql.append("12, 'Aviso Documentado', ");
							sql.append("13, 'Aviso Prejudicial', ");
							sql.append("14, 'Llamada Telefónica', ");
							sql.append("15, 'Asignar Abogado', ");
							sql.append("16, 'Liquidacion Judicial' ");
							sql.append(") as DESCRIPCION, ");
							sql.append("ejecPlan.c_id_accion as IDACCION, ");
							sql.append("count(ejecPlan.c_id_accion) as CANTIDAD ");
							sql.append("from t_vis_cob_ejecucion_plan ejecPlan ");
							sql.append("where c_id_accion IN (1,3,8,11,12,13,14,15,16) and c_confirmo_accion = '"
									+ estado + "' ");

//							if (estado != null && estado.equals("S")) {
//								sql.append("and ejecPlan.C_URL_ARCHIVO is not null ");
//							}

							if (fechaDesde != null && fechaHasta != null) {
								sql.append("and to_date(ejecPlan.c_fecha_ejecucion) between to_date('"
										+ dateFormat.format(fechaDesde)
										+ "','yyyy-MM-dd') and to_date('"
										+ dateFormat.format(fechaHasta)
										+ "','yyyy-MM-dd')");
							}
							sql.append(" group by trunc(ejecPlan.c_fecha_ejecucion), ejecPlan.c_id_accion  order by trunc(ejecPlan.c_fecha_ejecucion)");
							jt = new JdbcTemplate(dataSource);
							List rows = jt.queryForList(sql.toString());
							Iterator iter = rows.iterator();
							while (iter.hasNext()) {
								Map map = (Map) iter.next();
								TareaPendiente tarea = new TareaPendiente();
								if (map.get("FECHA") != null) {
									tarea.setFecha((Date) map.get("FECHA"));
								} else {
									tarea.setFecha(null);
								}
								if (map.get("DESCRIPCION") != null) {
									tarea.setDescripcion(map.get("DESCRIPCION")
											.toString());
								} else {
									tarea.setDescripcion("");
								}
								if (map.get("IDACCION") != null) {
									tarea.setIdAccion(Integer.valueOf(
											map.get("IDACCION").toString())
											.intValue());
								} else {
									tarea.setIdAccion(0);
								}
								if (map.get("CANTIDAD") != null) {
									tarea.setCantidad(Integer.valueOf(
											map.get("CANTIDAD").toString())
											.intValue());
								} else {
									tarea.setCantidad(0);
								}
								lista.add(tarea);
							}
							return lista;
						}
					});

		} catch (Exception e) {
			e.printStackTrace();
			throw new EjecucionPlanException("Error en la base de datos");
		}

		return listaTareasPendientes;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaDetallesTareasByParam(final Date fecha,
			final Long idAccion, final String estado) {

		final Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		@SuppressWarnings("unchecked")
		List<EjecucionPlanAsignacionCobrador> listaTareasPendientes = (List) getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						StringBuffer sql = new StringBuffer();
						sql.append("SELECT ep.c_id_ejecucion_plan ID_EJEC_PLAN,"
								+ " cli.c_id_cliente ID_CLIENTE,"
								+ " indi.c_apellido APELLIDO_CLIENTE,"
								+ " indi.c_nombres NOMBRES_CLIENTE,"
								+ " tipodoc.c_descripcion TIPO_DOC,"
								+ " indi.c_nro_documento NRO_DOC,"
								+ " trunc(indi.c_fecha_nacimiento) FECHA_NAC,"
								+ " dom.c_calle_nombre || ' ' || dom.c_calle_numero || ' ' || dom.c_orientacion || DECODE(dom.c_manzana, NULL, '', ' Mz. ' || dom.c_manzana) || DECODE(dom.c_es_monoblock, NULL, '', ' Mb. ' || dom.c_es_monoblock) || DECODE(dom.c_piso, NULL, '', ' Piso ' || dom.c_piso) || DECODE(dom.c_depto, NULL, '', ' Dpto. ' || dom.c_depto) || ' Bo. ' || barr.c_descripcion ||' Loc: '||loc.c_nombre ||' Partido: '||par.c_descripcion ||' Prov: '||prov.c_nombre ||' C.P: '|| dom.c_codigo_postal DOMICILIO,"
								+ " gar.c_id_individuo ID_INDIV_GARANTE,"
								+ " sucemp.c_descripcion DESCRIPCION_EMPRESA,"
								+ " sucemp.c_id_sucursal ID_SUC_EMPRESA,"
								+ " domEmp.c_calle_nombre || ' ' || domEmp.c_calle_numero || ' ' || domEmp.c_orientacion || DECODE(domEmp.c_manzana, NULL, '', ' Mz. ' || domEmp.c_manzana) || DECODE(domEmp.c_es_monoblock, NULL, '', ' Mb. ' || domEmp.c_es_monoblock) || DECODE(domEmp.c_piso, NULL, '', ' Piso ' || domEmp.c_piso) || DECODE(domEmp.c_depto, NULL, '', ' Dpto. ' || domEmp.c_depto) || DECODE(domEmp.c_codigo_postal, NULL, '', ' C.P: '|| domEmp.c_codigo_postal) DOMICILIO_EMPRESA,"
								+ " (select max (trunc(ctacte.c_timestamp)) from t_vis_tra_ctacte_clientes ctacte"
								+ " where ctacte.c_id_cliente = cli.c_id_cliente and ctacte.c_id_concepto_detalle = 31) ULTIMO_PAGO,"
								+ " cob.c_apellido APELLIDO_COBRADOR,"
								+ " cob.c_nombre NOMBRE_COBRADOR"
								+ " FROM t_vis_cob_ejecucion_plan ep"
								+ " INNER JOIN t_vis_tra_clientes cli"
								+ " ON ep.c_id_usuario = cli.c_id_cliente"
								+ " INNER JOIN t_vis_eva_individuos indi"
								+ " ON cli.c_id_individuo = indi.c_id_individuo"
								+ " INNER JOIN t_vis_tra_colaboradores colab"
								+ " ON ep.c_id_cobrador = colab.c_id_colaborador"
								+ " INNER JOIN t_vis_eva_cobradores cob"
								+ " ON colab.c_id_cobrador = cob.c_id_cobrador"
								+ " INNER JOIN t_vis_gen_tipos_documentos tipoDoc"
								+ " ON indi.c_id_tipo_documento = tipodoc.c_id_tipo_documento"
								+ " LEFT OUTER JOIN t_vis_gen_domicilios dom"
								+ " ON indi.c_id_domicilio = dom.c_id_domicilio"
								+ " LEFT OUTER JOIN t_vis_gen_localidades loc"
								+ " ON dom.c_id_localidad = loc.c_id_localidad"
								+ " LEFT OUTER JOIN t_vis_gen_partidos par"
								+ " ON loc.c_id_partido = par.c_id_partido"
								+ " LEFT OUTER JOIN  t_vis_gen_provincias prov"
								+ " ON par.c_id_provincia = prov.c_id_provincia"
								+ " LEFT OUTER JOIN t_vis_gen_barrios barr"
								+ " ON dom.c_id_barrio = barr.c_id_barrio"
								+ " LEFT OUTER JOIN t_vis_tra_garantes gar"
								+ " ON cli.c_id_cliente = gar.c_id_cliente"
								+ " LEFT OUTER JOIN t_vis_eva_actividades act"
								+ " ON indi.c_id_actividad = act.c_id_actividad"
								+ " LEFT OUTER JOIN t_vis_gen_suc_empresas sucEmp"
								+ " ON act.c_id_sucursal = sucemp.c_id_sucursal"
								+ " LEFT OUTER JOIN t_vis_gen_domicilios domEmp"
								+ " ON sucemp.c_id_domicilio = domemp.c_id_domicilio"
								+ " WHERE to_date(ep.c_fecha_ejecucion) = to_date('"
								+ dateFormat.format(fecha)
								+ "','yyyy-MM-dd')"
								+ " AND ep.c_id_accion = "
								+ idAccion
								+ " AND ep.c_confirmo_accion = '"
								+ estado
								+ "'");

						jt = new JdbcTemplate(dataSource);
						List rows = jt.queryForList(sql.toString());

						List<EjecucionPlanAsignacionCobrador> resultado = new ArrayList<EjecucionPlanAsignacionCobrador>();
						Iterator iter = rows.iterator();
						while (iter.hasNext()) {
							Map map = (Map) iter.next();
							EjecucionPlanAsignacionCobrador ep = new EjecucionPlanAsignacionCobrador();

							if (map.get("ID_EJEC_PLAN") != null) {
								ep.setIdEjecucionPlan(new Long(map.get(
										"ID_EJEC_PLAN").toString()));
							}

							if (map.get("ID_CLIENTE") != null) {
								ep.setIdUsuario(new Long(map.get("ID_CLIENTE")
										.toString()));
							}

							if (map.get("APELLIDO_CLIENTE") != null) {
								ep.setApellidoCliente(map.get(
										"APELLIDO_CLIENTE").toString());
							}

							if (map.get("NOMBRES_CLIENTE") != null) {
								ep.setNombreCliente(map.get("NOMBRES_CLIENTE")
										.toString());
							}

							if (map.get("TIPO_DOC") != null) {
								ep.setTipoDocumento(map.get("TIPO_DOC")
										.toString());
							}

							if (map.get("NRO_DOC") != null) {
								ep.setNroDocumento(map.get("NRO_DOC")
										.toString());
							}

							if (map.get("FECHA_NAC") != null) {
								ep.setFechaNacimiento((Date) map
										.get("FECHA_NAC"));
							}

							if (map.get("DOMICILIO") != null
									&& !map.get("DOMICILIO").toString().trim()
											.equals("")) {
								ep.setDomicilioCliente(map.get("DOMICILIO")
										.toString());
							}

							if (map.get("ID_INDIV_GARANTE") != null) {
								ep.setIdIndividuoGarante(new Long(map.get(
										"ID_INDIV_GARANTE").toString()));
							}

							if (map.get("DESCRIPCION_EMPRESA") != null) {
								ep.setDescripcionEmpresa(map.get(
										"DESCRIPCION_EMPRESA").toString());
							}

							if (map.get("ID_SUC_EMPRESA") != null) {
								ep.setIdSucursalEmpresa(new Long(map.get(
										"ID_SUC_EMPRESA").toString()));
							}

							if (map.get("DOMICILIO_EMPRESA") != null
									&& !map.get("DOMICILIO_EMPRESA").toString()
											.trim().equals("")) {
								ep.setDomicilioEmpresa(map.get(
										"DOMICILIO_EMPRESA").toString());
							}

							if (map.get("ULTIMO_PAGO") != null) {
								ep.setUltimoPagoEfectuado((Date) map
										.get("ULTIMO_PAGO"));
							}

							if (map.get("APELLIDO_COBRADOR") != null) {
								ep.setApellidoCobrador(map.get(
										"APELLIDO_COBRADOR").toString());
							}

							if (map.get("NOMBRE_COBRADOR") != null) {
								ep.setNombreCobrador(map.get("NOMBRE_COBRADOR")
										.toString());
							}

							// En esta parte seteo los datos faltantes, como
							// telefonos y ademas los datos no encontrados les
							// asigno un valor

							if (ep.getIdUsuario() != null) {
								ep.setTelefonosCliente(setTelefonosCliente(
										ep.getIdUsuario()).toString());
							}

							if (ep.getDescripcionEmpresa() == null
									|| ep.getDescripcionEmpresa().trim()
											.equals("")) {
								ep.setDescripcionEmpresa("No registra datos");
							}

							if (ep.getDomicilioEmpresa() == null
									|| ep.getDomicilioEmpresa().trim()
											.equals("")) {
								ep.setDomicilioEmpresa("No registra datos");
							}

							if (ep.getIdSucursalEmpresa() == null) {
								ep.setTelefonosEmpresa("No registra Datos");
							} else {
								ep.setTelefonosEmpresa(setearTelefonosEmpresa(
										ep.getIdSucursalEmpresa()).toString());
							}

							if (ep.getIdIndividuoGarante() == null) {
								ep.setNombreApellidoGarante("No registra datos");
								ep.setDomicilioGarante("No registra datos");
								ep.setTelefonosGarante("No registra datos");
							} else {
								// TODO Hacer Consulta para traer datos de
								// garante
							}

							resultado.add(ep);
						}

						return resultado;
					}

					private StringBuffer setearTelefonosEmpresa(
							Long idSucursalEmpresa) {
						StringBuffer resultado = null;

						StringBuffer sql = new StringBuffer();
						sql.append("SELECT gentel.c_cod_area COD_AREA,"
								+ " gentel.c_nro_tlefono NRO_TEL"
								+ " FROM t_vis_gen_suc_telefonos sucTel"
								+ " INNER JOIN t_vis_gen_telefonos genTel"
								+ " ON sucTel.c_id_telefono = genTel.c_id_telefono"
								+ " WHERE suctel.c_id_sucursal = "
								+ idSucursalEmpresa);

						List listaTel = jt.queryForList(sql.toString());

						if (listaTel != null && listaTel.size() > 0) {
							resultado = new StringBuffer("");
							Iterator iter = listaTel.iterator();
							while (iter.hasNext()) {
								Map map = (Map) iter.next();

								if (map.get("COD_AREA") != null) {
									resultado.append("(" + map.get("COD_AREA")
											+ ")");
								}

								if (map.get("NRO_TEL") != null) {
									resultado.append(map.get("NRO_TEL"));
								}

								if (iter.hasNext()) {
									resultado.append(" - ");
								}
							}
						} else {
							resultado = new StringBuffer(
									"No se registran telefonos");
						}

						return resultado;
					}

					private StringBuffer setTelefonosCliente(Long idUsuario) {

						StringBuffer resultado = null;

						StringBuffer sql = new StringBuffer();
						sql.append("SELECT tipotel.c_descripcion TIPO_TEL,"
								+ " gentel.c_cod_area COD_AREA,"
								+ " gentel.c_nro_tlefono NRO_TEL"
								+ " FROM t_vis_tra_clientes cli"
								+ " INNER JOIN t_vis_eva_individuos ind"
								+ " ON cli.c_id_individuo = ind.c_id_individuo"
								+ " INNER JOIN t_vis_eva_telefonos evaTel"
								+ " ON ind.c_id_individuo = evaTel.c_id_individuo"
								+ " INNER JOIN t_vis_gen_telefonos genTel"
								+ " ON evaTel.c_id_telefono = genTel.c_id_telefono"
								+ " INNER JOIN t_vis_gen_tipos_telefonos tipoTel"
								+ " ON gentel.c_id_tipo_telefono = tipotel.c_id_tipo_telefono"
								+ " WHERE cli.c_id_cliente       =" + idUsuario);

						List listaTel = jt.queryForList(sql.toString());

						if (listaTel != null && listaTel.size() > 0) {
							resultado = new StringBuffer("");
							Iterator iter = listaTel.iterator();
							while (iter.hasNext()) {
								Map map = (Map) iter.next();

								if (map.get("COD_AREA") != null) {
									resultado.append("(" + map.get("COD_AREA")
											+ ")");
								}

								if (map.get("NRO_TEL") != null) {
									resultado.append(map.get("NRO_TEL"));
								}

								if (iter.hasNext()) {
									resultado.append(" - ");
								}
							}
						} else {
							resultado = new StringBuffer(
									"No se registran telefonos");
						}

						return resultado;
					}
				});

		return listaTareasPendientes;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<EjecucionPlanAsignacionCobrador> listarDetallesTareaEnvioCarta(
			final TareaPendiente tareaP, final String estado) {

		final Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		@SuppressWarnings("unchecked")
		List<EjecucionPlanAsignacionCobrador> listaTareasPendientes = (List) getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						StringBuffer sql = new StringBuffer();
						sql.append("SELECT ep.c_id_ejecucion_plan AS IDEJECPLAN, ");
						sql.append("ep.c_url_archivo AS URLARCHIVO, ");
						sql.append("indi.C_ID_INDIVIDUO AS IDINDIVIDUO, ");
						sql.append("indi.c_apellido AS APELLINDI, ");
						sql.append("indi.c_nombres AS NOMBREINDI, ");
						sql.append("cli.c_id_cliente AS IDCLIENTE, ");
						sql.append("SUM(liq.c_monto_total - liq.c_importe_pagado) AS IMPORTEQUEDEBE ");
						sql.append("FROM t_vis_cob_ejecucion_plan ep ");
						sql.append("INNER JOIN t_vis_tra_clientes cli ");
						sql.append("ON ep.c_id_usuario = cli.c_id_cliente ");
						sql.append("INNER JOIN t_vis_eva_individuos indi ");
						sql.append("ON cli.c_id_individuo = indi.c_id_individuo ");
						sql.append("INNER JOIN t_vis_tra_liq_clientes liq ");
						sql.append("ON cli.c_id_cliente = liq.c_id_cliente ");
						sql.append("WHERE ep.c_id_accion = "
								+ tareaP.getIdAccion() + " ");
						sql.append("AND TRUNC(ep.c_fecha_ejecucion) = to_date('"
								+ dateFormat.format(tareaP.getFecha())
								+ "','yyyy-MM-dd') ");
						sql.append("AND ep.c_confirmo_accion = '" + estado
								+ "' ");
						sql.append("GROUP BY ep.c_id_ejecucion_plan, ");
						sql.append("ep.c_url_archivo, ");
						sql.append("indi.c_apellido, ");
						sql.append("indi.c_nombres, ");
						sql.append("indi.c_id_individuo, ");
						sql.append("cli.c_id_cliente ");

						jt = new JdbcTemplate(dataSource);
						List rows = jt.queryForList(sql.toString());

						List<EjecucionPlanAsignacionCobrador> resultado = new ArrayList<EjecucionPlanAsignacionCobrador>();
						Iterator iter = rows.iterator();

						while (iter.hasNext()) {
							Map map = (Map) iter.next();
							EjecucionPlanAsignacionCobrador ep = new EjecucionPlanAsignacionCobrador();

							if (map.get("IDEJECPLAN") != null) {
								ep.setIdEjecucionPlan(new Long(map.get(
										"IDEJECPLAN").toString()));
							}

							if (map.get("URLARCHIVO") != null) {
								ep.setUrlArchivo(map.get("URLARCHIVO")
										.toString());
							}

							if (map.get("APELLINDI") != null) {
								ep.setApellidoCliente(map.get("APELLINDI")
										.toString());
							}

							if (map.get("NOMBREINDI") != null) {
								ep.setNombreCliente(map.get("NOMBREINDI")
										.toString());
							}

							if (map.get("IDCLIENTE") != null) {
								ep.setIdUsuario(new Long(map.get("IDCLIENTE")
										.toString()));
							}

							if (map.get("IMPORTEQUEDEBE") != null) {
								ep.setMontoAdeudado(new Double(map.get(
										"IMPORTEQUEDEBE").toString()));
							}

							if (map.get("IDINDIVIDUO") != null) {
								ep.setIdIndividuo(new Long(map.get(
										"IDINDIVIDUO").toString()));
							}

							resultado.add(ep);
						}

						return resultado;
					}
				});
		return listaTareasPendientes;

	}

	@Override
	public List<EjecucionPlanAsignacionCobrador> listarDetallesTareaEnvioCartaParaConfirmar(
			final Date fecha, final Long idAccion, final String estado) {
		final Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		@SuppressWarnings("unchecked")
		List<EjecucionPlanAsignacionCobrador> listaTareasPendientes = (List) getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						StringBuffer sql = new StringBuffer();
						sql.append("SELECT ep.c_id_ejecucion_plan ID_EJEC_PLAN,"
								+ " cli.c_id_cliente ID_CLIENTE,"
								+ " indi.c_apellido APELLIDO_CLIENTE,"
								+ " indi.c_nombres NOMBRES_CLIENTE,"
								+ " dom.c_calle_nombre || ' ' || dom.c_calle_numero || ' ' || dom.c_orientacion || DECODE(dom.c_manzana, NULL, '', ' Mz. ' || dom.c_manzana) || DECODE(dom.c_es_monoblock, NULL, '', ' Mb. ' || dom.c_es_monoblock) || DECODE(dom.c_piso, NULL, '', ' Piso ' || dom.c_piso) || DECODE(dom.c_depto, NULL, '', ' Dpto. ' || dom.c_depto) || DECODE(barr.c_descripcion, NULL, '', ' Bo. ' || barr.c_descripcion) DOMICILIO,"
								+ " 'Loc: '||loc.c_nombre ||' Partido: '||par.c_descripcion ||' Prov: '||prov.c_nombre ||' C.P: '|| dom.c_codigo_postal LOCALIDAD,"
								+ " cli.v_domicilio DOMICILIO1,"
								+ " 'Loc: '||cli.v_localidad || ' CP: ' || v_cod_post || ' Prov: ' || v_provincia LOCALIDAD1,"
								+ " dom.c_id_localidad IDLOCALIDAD"
								+ " FROM t_vis_cob_ejecucion_plan ep"
								+ " INNER JOIN t_vis_tra_clientes cli"
								+ " ON ep.c_id_usuario = cli.c_id_cliente"
								+ " INNER JOIN t_vis_eva_individuos indi"
								+ " ON cli.c_id_individuo = indi.c_id_individuo"
								+ " LEFT OUTER JOIN t_vis_gen_domicilios dom"
								+ " ON indi.c_id_domicilio = dom.c_id_domicilio"
								+ " LEFT OUTER JOIN t_vis_gen_localidades loc"
								+ " ON dom.c_id_localidad = loc.c_id_localidad"
								+ " LEFT OUTER JOIN t_vis_gen_partidos par"
								+ " ON loc.c_id_partido = par.c_id_partido"
								+ " LEFT OUTER JOIN  t_vis_gen_provincias prov"
								+ " ON par.c_id_provincia = prov.c_id_provincia"
								+ " LEFT OUTER JOIN t_vis_gen_barrios barr"
								+ " ON dom.c_id_barrio = barr.c_id_barrio"
								+ " WHERE to_date(ep.c_fecha_ejecucion) = to_date('"
								+ dateFormat.format(fecha)
								+ "','yyyy-MM-dd')"
								+ " AND ep.c_id_accion = "
								+ idAccion
								+ " AND ep.c_confirmo_accion = '"
								+ estado
								+ "'");

						jt = new JdbcTemplate(dataSource);
						List rows = jt.queryForList(sql.toString());

						List<EjecucionPlanAsignacionCobrador> resultado = new ArrayList<EjecucionPlanAsignacionCobrador>();
						Iterator iter = rows.iterator();
						while (iter.hasNext()) {
							Map map = (Map) iter.next();
							EjecucionPlanAsignacionCobrador ep = new EjecucionPlanAsignacionCobrador();

							if (map.get("ID_EJEC_PLAN") != null) {
								ep.setIdEjecucionPlan(new Long(map.get(
										"ID_EJEC_PLAN").toString()));
							}

							if (map.get("ID_CLIENTE") != null) {
								ep.setIdUsuario(new Long(map.get("ID_CLIENTE")
										.toString()));
							}

							if (map.get("APELLIDO_CLIENTE") != null) {
								ep.setApellidoCliente(map.get(
										"APELLIDO_CLIENTE").toString());
							}

							if (map.get("NOMBRES_CLIENTE") != null) {
								ep.setNombreCliente(map.get("NOMBRES_CLIENTE")
										.toString());
							}

							ep.setDomicilioCliente(getDomicilioValido(
									(String) map.get("DOMICILIO"),
									(String) map.get("DOMICILIO1"),
									(BigDecimal) map.get("IDLOCALIDAD")));

							ep.setLocalidadCliente(getLocalidadValida(
									(String) map.get("LOCALIDAD"),
									(String) map.get("LOCALIDAD1"),
									(BigDecimal) map.get("IDLOCALIDAD")));

							resultado.add(ep);
						}

						return resultado;
					}

					private String getLocalidadValida(String loc1, String loc2,
							BigDecimal bigIdLocalidad) {

						Long idLocalidad = (bigIdLocalidad != null) ? bigIdLocalidad
								.longValue() : null;

						if (loc1 == null) {
							return loc2;
						} else if (loc1 == null || idLocalidad == null
								|| idLocalidad == 0 || idLocalidad == 300) {
							return loc2;
						} else {
							return loc1;
						}
					}

					private String getDomicilioValido(String dom1, String dom2,
							BigDecimal bigIdLocalidad) {

						Long idLocalidad = (bigIdLocalidad != null) ? bigIdLocalidad
								.longValue() : null;

						if (dom1 == null) {
							return dom2;
						} else if (idLocalidad == null) {
							return dom2;
						} else if (idLocalidad == null || idLocalidad == 0
								|| idLocalidad == 300) {
							return dom2;
						} else {
							return dom1;
						}

					}

					private StringBuffer setearTelefonosEmpresa(
							Long idSucursalEmpresa) {
						StringBuffer resultado = null;

						StringBuffer sql = new StringBuffer();
						sql.append("SELECT gentel.c_cod_area COD_AREA,"
								+ " gentel.c_nro_tlefono NRO_TEL"
								+ " FROM t_vis_gen_suc_telefonos sucTel"
								+ " INNER JOIN t_vis_gen_telefonos genTel"
								+ " ON sucTel.c_id_telefono = genTel.c_id_telefono"
								+ " WHERE suctel.c_id_sucursal = "
								+ idSucursalEmpresa);

						List listaTel = jt.queryForList(sql.toString());

						if (listaTel != null && listaTel.size() > 0) {
							resultado = new StringBuffer("");
							Iterator iter = listaTel.iterator();
							while (iter.hasNext()) {
								Map map = (Map) iter.next();

								if (map.get("COD_AREA") != null) {
									resultado.append("(" + map.get("COD_AREA")
											+ ")");
								}

								if (map.get("NRO_TEL") != null) {
									resultado.append(map.get("NRO_TEL"));
								}

								if (iter.hasNext()) {
									resultado.append(" - ");
								}
							}
						} else {
							resultado = new StringBuffer(
									"No se registran telefonos");
						}

						return resultado;
					}

					private StringBuffer setTelefonosCliente(Long idUsuario) {

						StringBuffer resultado = null;

						StringBuffer sql = new StringBuffer();
						sql.append("SELECT tipotel.c_descripcion TIPO_TEL,"
								+ " gentel.c_cod_area COD_AREA,"
								+ " gentel.c_nro_tlefono NRO_TEL"
								+ " FROM t_vis_tra_clientes cli"
								+ " INNER JOIN t_vis_eva_individuos ind"
								+ " ON cli.c_id_individuo = ind.c_id_individuo"
								+ " INNER JOIN t_vis_eva_telefonos evaTel"
								+ " ON ind.c_id_individuo = evaTel.c_id_individuo"
								+ " INNER JOIN t_vis_gen_telefonos genTel"
								+ " ON evaTel.c_id_telefono = genTel.c_id_telefono"
								+ " INNER JOIN t_vis_gen_tipos_telefonos tipoTel"
								+ " ON gentel.c_id_tipo_telefono = tipotel.c_id_tipo_telefono"
								+ " WHERE cli.c_id_cliente       =" + idUsuario);

						List listaTel = jt.queryForList(sql.toString());

						if (listaTel != null && listaTel.size() > 0) {
							resultado = new StringBuffer("");
							Iterator iter = listaTel.iterator();
							while (iter.hasNext()) {
								Map map = (Map) iter.next();

								if (map.get("COD_AREA") != null) {
									resultado.append("(" + map.get("COD_AREA")
											+ ")");
								}

								if (map.get("NRO_TEL") != null) {
									resultado.append(map.get("NRO_TEL"));
								}

								if (iter.hasNext()) {
									resultado.append(" - ");
								}
							}
						} else {
							resultado = new StringBuffer(
									"No se registran telefonos");
						}

						return resultado;
					}
				});

		return listaTareasPendientes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EjecucionPlanAsignacionCobrador> listarDetallesTareaLlamadasTelefonicas(
			final Date fecha, final Long idAccion, final String estado) {

		return (List<EjecucionPlanAsignacionCobrador>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer(100);
						sb.append("SELECT ejec.idEjecucionPlan as idEjecucionPlan, "
								+ "ejec.idUsuario as idCliente, "
								+ "cli.individuo.apellido as apellidoCliente, "
								+ "cli.individuo.nombres as nombreCliente, "
								+ "(SELECT SUM(liq.montoTotal - liq.importePagado) FROM LiqCliente liq WHERE liq.clienteTransaccion.idCliente=cli.idCliente) as deuda, "
								+ "tels.telefono.codArea || ' ' || tels.telefono.nroTlefono as nroTelefono, "
								+ "(SELECT MAX (TRUNC(ctacte.timestamp)) FROM CtaCteCliente ctacte WHERE ctacte.clienteTransaccion.idCliente = ejec.idUsuario and ctacte.conceptoDetalle.idConceptoDetalle = 31) as ultimoPago ");
						sb.append("FROM EjecucionPlan ejec, ClienteTransaccion cli ");
						sb.append("LEFT OUTER JOIN cli.individuo.telefonos as tels ");
						sb.append("WHERE ejec.idUsuario = cli.idCliente ");
						sb.append("AND ejec.accion.idAccion=:idAccion ");
						sb.append("AND ejec.fechaEjecucion=:fecha ");
						sb.append("AND ejec.confirmoAccion=:estado ");
						// sb.append("GROUP BY ejec.idEjecucionPlan ");
						Query query = session.createQuery(sb.toString());
						query.setLong("idAccion", idAccion);
						query.setDate("fecha", fecha);
						query.setString("estado", estado);
						List<Object[]> lista = query.list();

						Iterator<Object[]> iter = lista.iterator();

						ArrayList<EjecucionPlanAsignacionCobrador> res = new ArrayList<EjecucionPlanAsignacionCobrador>();

						Long idEjecucionPlan = null;

						EjecucionPlanAsignacionCobrador ejec = null;

						while (iter.hasNext()) {
							Object[] obj = iter.next();
							if (!obj[0].equals(idEjecucionPlan)) {
								idEjecucionPlan = (Long) obj[0];
								ejec = new EjecucionPlanAsignacionCobrador();
								ejec.setIdEjecucionPlan(idEjecucionPlan);
								ejec.setIdUsuario((Long) obj[1]);
								ejec.setApellidoCliente((String) obj[2]);
								ejec.setNombreCliente((String) obj[3]);
								ejec.setMontoAdeudado(((BigDecimal) obj[4])
										.doubleValue());
								ejec.setTelefonosCliente((String) obj[5]);
								
								ejec.setUltimoPagoEfectuado((Date) obj[6]);
								
								res.add(ejec);
							} else {
								ejec.setTelefonosCliente(ejec
										.getTelefonosCliente() + " - " + obj[5]);
							}
						}
						return res;
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EjecucionPlanAsignacionCobrador> listarDetallesTareaTeledirecto(
			final Date fecha, final Long idAccion, final String estado) {

		return (List<EjecucionPlanAsignacionCobrador>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						StringBuffer sb = new StringBuffer(100);
						sb.append("SELECT ejec.idEjecucionPlan as idEjecucionPlan, "
								+ "ejec.idUsuario as idCliente, "
								+ "cli.individuo.apellido as apellidoCliente, "
								+ "cli.individuo.nombres as nombreCliente, "
								+ "(SELECT SUM(liq.montoTotal - liq.importePagado) FROM LiqCliente liq WHERE liq.clienteTransaccion.idCliente=cli.idCliente) as deuda, "
								+ "tels.telefono.codArea || tels.telefono.nroTlefono as nroTelefono, "
								+ "tels.telefono.esCelular as esCelular, "
								+ "(SELECT MAX (TRUNC(ctacte.timestamp)) FROM CtaCteCliente ctacte WHERE ctacte.clienteTransaccion.idCliente = ejec.idUsuario and ctacte.conceptoDetalle.idConceptoDetalle IN (190,31,326) ) as ultimoPago ");
						sb.append("FROM EjecucionPlan ejec, ClienteTransaccion cli ");
						sb.append("LEFT OUTER JOIN cli.individuo.telefonos as tels ");
						sb.append("WHERE ejec.idUsuario = cli.idCliente ");
						sb.append("AND ejec.accion.idAccion=:idAccion ");
						sb.append("AND ejec.fechaEjecucion=:fecha ");
						sb.append("AND ejec.confirmoAccion=:estado ");
						Query query = session.createQuery(sb.toString());
						query.setLong("idAccion", idAccion);
						query.setDate("fecha", fecha);
						query.setString("estado", estado);
						List<Object[]> lista = query.list();

						Iterator<Object[]> iter = lista.iterator();

						ArrayList<EjecucionPlanAsignacionCobrador> res = new ArrayList<EjecucionPlanAsignacionCobrador>();

						Long idEjecucionPlan = null;

						EjecucionPlanAsignacionCobrador ejec = null;

						while (iter.hasNext()) {
							Object[] obj = iter.next();
							if (!obj[0].equals(idEjecucionPlan)) {
								idEjecucionPlan = (Long) obj[0];
								ejec = new EjecucionPlanAsignacionCobrador();
								ejec.setListaTelCelulares(new ArrayList<String>());
								ejec.setListaTelFijos(new ArrayList<String>());
								ejec.setIdEjecucionPlan(idEjecucionPlan);
								ejec.setIdUsuario((Long) obj[1]);
								ejec.setApellidoCliente((String) obj[2]);
								ejec.setNombreCliente((String) obj[3]);
								ejec.setMontoAdeudado(((BigDecimal) obj[4])
										.doubleValue());
								ejec.setTelefonosCliente((String) obj[5]);

								if (((String) obj[6]).equals("S")) {
									ejec.getListaTelCelulares().add((String) obj[5]);
								}else {
									ejec.getListaTelFijos().add((String) obj[5]);
								}
								ejec.setUltimoPagoEfectuado((Date) obj[7]);
								res.add(ejec);
							} else {
								ejec.setTelefonosCliente(ejec
										.getTelefonosCliente() + " - " + obj[5]);
								if (((String) obj[6]).equals("S")) {
									ejec.getListaTelCelulares().add((String) obj[5]);
								}else {
									ejec.getListaTelFijos().add((String) obj[5]);
								}
							}
						}
						return res;
					}
				});
	}

	@Override
	public List<EjecucionPlanAsignacionCobrador> listarDetallesTareaAsigncionAbogado(
			final Date fecha, final Long idAccion, final String estado) {
		return (List<EjecucionPlanAsignacionCobrador>) getHibernateTemplate()
		.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(100);
/*00*/			sb.append("SELECT ejec.idEjecucionPlan as idEjecucionPlan, "
/*01*/					+ "ejec.idUsuario as idCliente, "
/*02*/					+ "ind.idIndividuo as idIndividuo, "
/*03*/					+ "ind.apellido as apellidoCliente, "
/*04*/					+ "ind.nombres as nombreCliente, "
/*05*/					+ "(SELECT SUM(liq.montoTotal - liq.importePagado) FROM LiqCliente liq WHERE liq.clienteTransaccion.idCliente=cli.idCliente) as deuda, "
/*06*/					+ "(SELECT MAX (TRUNC(ctacte.timestamp)) FROM CtaCteCliente ctacte WHERE ctacte.clienteTransaccion.idCliente = ejec.idUsuario and ctacte.conceptoDetalle.idConceptoDetalle IN (190,31,326) ) as ultimoPago, "
/*07*/					+ "ejec.urlArchivo, "
/*08*/					+ "trim(concat(col.individuo.apellido,' ',col.individuo.nombres)) as nombre_abogado ");
				sb.append("FROM EjecucionPlan ejec, Colaborador col, ClienteTransaccion cli ");
				sb.append("INNER JOIN cli.individuo ind ");
				sb.append("WHERE ejec.idUsuario = cli.idCliente ");
				sb.append("AND col.idColaborador=ejec.idAbogado ");
				sb.append("AND ejec.accion.idAccion=:idAccion ");
				sb.append("AND ejec.fechaEjecucion=:fecha ");
				sb.append("AND ejec.confirmoAccion=:estado ");
				Query query = session.createQuery(sb.toString());
				query.setLong("idAccion", idAccion);
				query.setDate("fecha", fecha);
				query.setString("estado", estado);
				List<Object[]> lista = query.list();

				Iterator<Object[]> iter = lista.iterator();

				ArrayList<EjecucionPlanAsignacionCobrador> res = new ArrayList<EjecucionPlanAsignacionCobrador>();

				Long idEjecucionPlan = null;

				EjecucionPlanAsignacionCobrador ejec = null;

				while (iter.hasNext()) {
					Object[] obj = iter.next();
					ejec = new EjecucionPlanAsignacionCobrador();
					ejec.setIdEjecucionPlan((Long) obj[0]);
					ejec.setIdUsuario((Long) obj[1]);
					ejec.setIdIndividuo((Long) obj[2]);
					ejec.setApellidoCliente((String) obj[3]);					
					ejec.setNombreCliente((String) obj[4]);
					ejec.setMontoAdeudado(((BigDecimal) obj[5])
								.doubleValue());
					ejec.setUltimoPagoEfectuado((Date) obj[6]);
					ejec.setUrlArchivo((String)obj[7]);
					ejec.setNombreCobrador((String)obj[8]);
					res.add(ejec);				
				}
				return res;
			}
		});
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<EjecucionPlanAsignacionCobrador> listarDetallesTareaAsigncionAbogadoParaConfirmar(
			final Date fecha, final Long idAccion, final String estado) {
		return (List<EjecucionPlanAsignacionCobrador>) getHibernateTemplate()
		.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(100);
/*00*/			sb.append("SELECT ejec.idEjecucionPlan as idEjecucionPlan, "
/*01*/					+ "ejec.idUsuario as idCliente, "
/*02*/					+ "ind.idIndividuo as idIndividuo, "
/*03*/					+ "ind.apellido as apellidoCliente, "
/*04*/					+ "ind.nombres as nombreCliente, "
/*05*/					+ "(SELECT SUM(liq.montoTotal - liq.importePagado) FROM LiqCliente liq WHERE liq.clienteTransaccion.idCliente=cli.idCliente) as deuda, "
/*06*/					+ "(SELECT MAX (TRUNC(ctacte.timestamp)) FROM CtaCteCliente ctacte WHERE ctacte.clienteTransaccion.idCliente = ejec.idUsuario and ctacte.conceptoDetalle.idConceptoDetalle IN (190,31,326) ) as ultimoPago, "
/*07*/					+ "TRIM(domCli.calleNombre) || ' ' || TRIM(domCli.calleNumero) || ' ' || domCli.orientacion, "
/*08*/					+ "TRIM(domCli.manzana) as mz, "
/*09*/					+ "TRIM(domCli.monoblock) as mb, "
/*10*/					+ "domCli.piso as piso, "
/*11*/					+ "TRIM(domCli.depto), "
/*12*/					+ "TRIM(barrioCli.descripcion), "
/*13*/					+ "'Loc: '||TRIM(locCli.nombre) ||' Partido: '||TRIM(parCli.descripcion) ||' Prov: '||TRIM(provCli.nombre) ||' C.P: '|| TRIM(domCli.codigoPostal) as localidad, "
/*14*/					+ "TRIM(cli.domicilio) as domicilio2, "
/*15*/					+ "'Loc: '||TRIM(cli.localidad) || ' CP: ' || TRIM(cli.codPost) || ' Prov: ' || TRIM(cli.provincia) as localidad1, "
/*16*/					+ "locCli.idLocalidad ");
				sb.append("FROM EjecucionPlan ejec, ClienteTransaccion cli ");
				sb.append("INNER JOIN cli.individuo ind ");
				sb.append("LEFT OUTER JOIN ind.domicilioDoc domCli ");
				sb.append("LEFT OUTER JOIN domCli.barrio barrioCli ");
				sb.append("LEFT OUTER JOIN domCli.localidad locCli ");
				sb.append("LEFT OUTER JOIN locCli.partido parCli ");
				sb.append("LEFT OUTER JOIN locCli.provincia provCli ");
				sb.append("WHERE ejec.idUsuario = cli.idCliente ");
				sb.append("AND ejec.accion.idAccion=:idAccion ");
				sb.append("AND ejec.fechaEjecucion=:fecha ");
				sb.append("AND ejec.confirmoAccion=:estado ");
				Query query = session.createQuery(sb.toString());
				query.setLong("idAccion", idAccion);
				query.setDate("fecha", fecha);
				query.setString("estado", estado);
				List<Object[]> lista = query.list();

				Iterator<Object[]> iter = lista.iterator();

				ArrayList<EjecucionPlanAsignacionCobrador> res = new ArrayList<EjecucionPlanAsignacionCobrador>();

				EjecucionPlanAsignacionCobrador ejec = null;

				while (iter.hasNext()) {
					Object[] obj = iter.next();
					ejec = new EjecucionPlanAsignacionCobrador();
					ejec.setIdEjecucionPlan((Long) obj[0]);
					ejec.setIdUsuario((Long) obj[1]);
					ejec.setIdIndividuo((Long) obj[2]);
					ejec.setApellidoCliente((String) obj[3]);					
					ejec.setNombreCliente((String) obj[4]);
					ejec.setMontoAdeudado(((BigDecimal) obj[5])
								.doubleValue());
					ejec.setUltimoPagoEfectuado((Date) obj[6]);
					
					StringBuffer dom1 = new StringBuffer();
					
					if (obj[7]!=null){
						dom1.append((String)obj[7]);
					}
					
					if (obj[8]!=null){
						dom1.append(" Mz: "+obj[8]);
					}
					
					if (obj[9]!=null){
						dom1.append(" Mb: "+obj[9]);
					}
					
					if (obj[10]!=null){
						dom1.append(" Piso: "+obj[10]);
					}
					
					if (obj[11]!=null){
						dom1.append(" Depto: "+obj[11]);
					}
					
					if (obj[12]!=null){
						dom1.append(" Barrio: "+obj[12]);
					}
					
					StringBuffer dom2 = new StringBuffer();
					dom2.append(obj[14]);
					
					ejec.setDomicilioCliente(getDomicilioValido(dom1.toString(),dom2.toString(),(Long)obj[16]));

					ejec.setLocalidadCliente(getLocalidadValida((String) obj[13],(String) obj[15],(Long)obj[16]));
					
					ejec.setTelefonosCliente(setTelefonosCliente(ejec.getIdUsuario()).toString());
					
					res.add(ejec);				
				}
				return res;
			}

			private String getLocalidadValida(String loc1, String loc2,
					Long idLocalidad) {

				if (loc1 == null || loc1.length()==0) {
					return loc2;
				} else if (loc1 == null || idLocalidad == null
						|| idLocalidad == 0 || idLocalidad == 300) {
					return loc2;
				} else {
					return loc1;
				}
			}

			private String getDomicilioValido(String dom1, String dom2,
					Long idLocalidad) {

				if (dom1 == null || dom1.length()==0) {
					return dom2;
				} else if (idLocalidad == null) {
					return dom2;
				} else if (idLocalidad == null || idLocalidad == 0
						|| idLocalidad == 300) {
					return dom2;
				} else {
					return dom1;
				}

			}
			
			private StringBuffer setTelefonosCliente(Long idUsuario) {

				StringBuffer resultado = null;

				StringBuffer sql = new StringBuffer();
				sql.append("SELECT tipotel.c_descripcion TIPO_TEL,"
						+ " gentel.c_cod_area COD_AREA,"
						+ " gentel.c_nro_tlefono NRO_TEL"
						+ " FROM t_vis_tra_clientes cli"
						+ " INNER JOIN t_vis_eva_individuos ind"
						+ " ON cli.c_id_individuo = ind.c_id_individuo"
						+ " INNER JOIN t_vis_eva_telefonos evaTel"
						+ " ON ind.c_id_individuo = evaTel.c_id_individuo"
						+ " INNER JOIN t_vis_gen_telefonos genTel"
						+ " ON evaTel.c_id_telefono = genTel.c_id_telefono"
						+ " INNER JOIN t_vis_gen_tipos_telefonos tipoTel"
						+ " ON gentel.c_id_tipo_telefono = tipotel.c_id_tipo_telefono"
						+ " WHERE cli.c_id_cliente       =" + idUsuario);

				List listaTel = jt.queryForList(sql.toString());

				if (listaTel != null && listaTel.size() > 0) {
					resultado = new StringBuffer("");
					Iterator iter = listaTel.iterator();
					while (iter.hasNext()) {
						Map map = (Map) iter.next();

						if (map.get("COD_AREA") != null) {
							resultado.append("(" + map.get("COD_AREA")
									+ ")");
						}

						if (map.get("NRO_TEL") != null) {
							resultado.append(map.get("NRO_TEL"));
						}

						if (iter.hasNext()) {
							resultado.append(" - ");
						}
					}
				} else {
					resultado = new StringBuffer(
							"No se registran telefonos");
				}

				return resultado;
			}
			
			
			
		});
	}

	@Override
	public List getListaTareasByIdCliente(final Long idCliente) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				StringBuffer hql = new StringBuffer();
				hql.append("FROM EjecucionPlan ejec ");
				hql.append("WHERE ejec.idUsuario = :idCliente ");
				hql.append("ORDER BY ejec.idEjecucionPlan desc");
				Query query = session.createQuery(hql.toString());
				query.setLong("idCliente", idCliente);
				
				List<EjecucionPlan> list = query.list();
				Iterator<EjecucionPlan> iter = list.iterator();
				
				List<EjecucionPlan> result = new ArrayList<EjecucionPlan>();
				
				while (iter.hasNext()) {
					EjecucionPlan obj = iter.next();
					EjecucionPlan r = new EjecucionPlan();
					
					if (obj.getAccion() != null){
						Accion a = new Accion();
						a.setDescripcion(obj.getAccion().getDescripcion());
						r.setAccion(a);
					}
					
					if (obj.getCobrador()!= null){
						Colaborador colab = new Colaborador();
						colab.setCobrador(new Cobrador());
						colab.getCobrador().setApellido(obj.getCobrador().getIndividuo().getApellido());
						colab.getCobrador().setNombre(obj.getCobrador().getIndividuo().getNombres());
						r.setCobrador(colab);
					}
						
										
					if (obj.getFechaEjecucion()!=null){
						r.setFechaEjecucion(obj.getFechaEjecucion());
					}
					
					/*
					if(obj.getIdAbogado()!=null){
						
					}
					*/
					
					result.add(r);
				}
				
				return result;
			}
		});
	}
	
	
	
}
