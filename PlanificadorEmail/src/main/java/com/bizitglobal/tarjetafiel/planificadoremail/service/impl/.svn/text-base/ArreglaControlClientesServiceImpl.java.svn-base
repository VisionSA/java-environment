package com.bizitglobal.tarjetafiel.planificadoremail.service.impl;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.dao.IGenericDao;
import com.bizitglobal.tarjetafiel.planificadoremail.exception.EnvioEmailException;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.ArreglaControlCliente;
import com.bizitglobal.tarjetafiel.planificadoremail.service.ArreglaControlClientesService;
import com.bizitglobal.tarjetafiel.transacciones.dao.ClienteTransaccionDao;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ControlClientes;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;


/***** @Id:6958 ******/
public class ArreglaControlClientesServiceImpl implements ArreglaControlClientesService {
	private IGenericDao genericDao;
	private ClienteTransaccionDao clienteTransaccionDao;


	public void arreglaControlClientes() throws EnvioEmailException {
		Boolean resultado = true;
		try {

			String sql = "select cliente, sum(c1) c1,sum(c2) c2,sum(c3) c3,sum(c4) c4,sum(c5) c5,sum(c6) c6,dias from "
					+
					"(select c_id_cliente cliente,count(nvl(c_id_tipo_control,0)) c1,0 c2,0 c3 , 0 c4, 0 c5 , 0 c6, (trunc(sysdate) - trunc(c_fecha_desde)) dias "
					+
					"from t_vis_gen_controlclientes where c_id_tipo_control = 1 and c_fecha_resolucion is null   group by c_id_cliente,(trunc(sysdate) - trunc(c_fecha_desde)) "
					+
					"union select c_id_cliente,0 c1,count(nvl(c_id_tipo_control,0)) c2,0 c3 , 0 c4, 0 c5 , 0 c6,(trunc(sysdate) - trunc(c_fecha_desde)) dias "
					+
					" from t_vis_gen_controlclientes where c_id_tipo_control = 2 and c_fecha_resolucion is null group by c_id_cliente,(trunc(sysdate) - trunc(c_fecha_desde)) "
					+
					" union select c_id_cliente,0 c1,0 c2,count(nvl(c_id_tipo_control,0)) c3 , 0 c4, 0 c5 , 0 c6,(trunc(sysdate) - trunc(c_fecha_desde)) dias "
					+
					"from t_vis_gen_controlclientes where c_id_tipo_control = 3 and c_fecha_resolucion is null group by c_id_cliente,(trunc(sysdate) - trunc(c_fecha_desde)) "
					+
					"union select c_id_cliente,0 c1,0 c2, 0 c3 , 0 c4, count(nvl(c_id_tipo_control,0)) c5 , 0 c6,(trunc(sysdate) - trunc(c_fecha_desde)) dias "
					+
					" from t_vis_gen_controlclientes where c_id_tipo_control = 5 and c_fecha_resolucion is null group by c_id_cliente,(trunc(sysdate) - trunc(c_fecha_desde)) "
					+
					" union select c_id_cliente,0 c1, 0 c2, 0 c3 , 0 c4, 0 c5 , count(nvl(c_id_tipo_control,0)) c6,(trunc(sysdate) - trunc(c_fecha_desde)) dias "
					+
					" from t_vis_gen_controlclientes where c_id_tipo_control = 6 and c_fecha_resolucion is null" 
					+
					" group by c_id_cliente,(trunc(sysdate) - trunc(c_fecha_desde))) group by cliente,dias order by cliente";

			List rs = this.genericDao.listarResulsetSql(sql);

			Iterator itRs = rs.iterator();
			while (itRs.hasNext()) {
				Map itemRs = (Map) itRs.next();
				ArreglaControlCliente arrCliente = new ArreglaControlCliente();
				arrCliente.setCliente(new Long(itemRs.get("cliente").toString()));
				arrCliente.setC1(new Integer(itemRs.get("c1").toString()));
				arrCliente.setC2(new Integer(itemRs.get("c2").toString()));
				arrCliente.setC3(new Integer(itemRs.get("c3").toString()));
				arrCliente.setC5(new Integer(itemRs.get("c5").toString()));
				arrCliente.setC6(new Integer(itemRs.get("c6").toString()));
				arrCliente.setDias(new Integer(itemRs.get("dias").toString()));

				// Filtro filtro = new Filtro();
				// filtro.agregarCampoOperValor("idCliente", Filtro.IGUAL, arrCliente.getCliente());
				// ClienteTransaccion titular1 = (ClienteTransaccion)genericDao.listar(filtro, ClienteTransaccion.class.getName()).get(0);

				if (arrCliente.getDias() <= 25) {

					ClienteTransaccion titular = clienteTransaccionDao.buscarClienteFlex(arrCliente.getCliente());

					if (arrCliente.getC1() == 1 && titular.validarDomicilio()) {

						Filtro filtro1 = new Filtro();
						filtro1.agregarCampoOperValor("idCliente", Filtro.IGUAL, arrCliente.getCliente());
						filtro1.agregarCampoOperValor("tipoControl.idTipoControl", Filtro.IGUAL, 1);
						ControlClientes controlCliente = (ControlClientes) genericDao.listar(filtro1, ControlClientes.class.getName()).get(0);
						controlCliente.setFechaResolucion(new Date());

						this.genericDao.modificar(controlCliente);

					}

					if (arrCliente.getC2() == 1 && titular.getIndividuo().getActividad() != null
							&& titular.getIndividuo().getActividad().getSucEmpresa() != null) {

						Filtro filtro1 = new Filtro();
						filtro1.agregarCampoOperValor("idCliente", Filtro.IGUAL, arrCliente.getCliente());
						filtro1.agregarCampoOperValor("tipoControl.idTipoControl", Filtro.IGUAL, 2);
						ControlClientes controlCliente = (ControlClientes) genericDao.listar(filtro1, ControlClientes.class.getName()).get(0);
						controlCliente.setFechaResolucion(new Date());

						this.genericDao.modificar(controlCliente);

					}

					if (arrCliente.getC3() == 1 && titular.getIndividuo().getTelefonos().size() != 0) {

						Filtro filtro1 = new Filtro();
						filtro1.agregarCampoOperValor("idCliente", Filtro.IGUAL, arrCliente.getCliente());
						filtro1.agregarCampoOperValor("tipoControl.idTipoControl", Filtro.IGUAL, 3);
						ControlClientes controlCliente = (ControlClientes) genericDao.listar(filtro1, ControlClientes.class.getName()).get(0);
						controlCliente.setFechaResolucion(new Date());

						this.genericDao.modificar(controlCliente);

					}

					if (arrCliente.getC5() == 1 && titular.getIndividuo().getActividad() != null
							&& titular.getIndividuo().getActividad().getSucEmpresa() != null &&
							titular.getIndividuo().getActividad().getSucEmpresa().getSucTelefonos().size() != 0) {

						Filtro filtro1 = new Filtro();
						filtro1.agregarCampoOperValor("idCliente", Filtro.IGUAL, arrCliente.getCliente());
						filtro1.agregarCampoOperValor("tipoControl.idTipoControl", Filtro.IGUAL, 5);
						ControlClientes controlCliente = (ControlClientes) genericDao.listar(filtro1, ControlClientes.class.getName()).get(0);
						controlCliente.setFechaResolucion(new Date());

						this.genericDao.modificar(controlCliente);

					}

					if (arrCliente.getC6() == 1) {

						Filtro filtro1 = new Filtro();
						filtro1.agregarCampoOperValor("idCliente", Filtro.IGUAL, arrCliente.getCliente());
						filtro1.agregarCampoOperValor("tipoControl.idTipoControl", Filtro.IGUAL, 6);
						ControlClientes controlCliente = (ControlClientes) genericDao.listar(filtro1, ControlClientes.class.getName()).get(0);

						StringBuffer hql = new StringBuffer();

						hql.append(" select (case when (fecha_fin = to_date('01/01/1900','dd/MM/yyyy')) then (select  trunc(max(l.c_ultimamodif))  from t_vis_tra_lineacredcompos l ");
						hql.append(" where  l.c_id_cliente = " + arrCliente.getCliente()
								+ " and l.c_tipo = 'LINEA')	 else fecha_fin	 end)	fecha 	 from ");
						hql.append(" (select  nvl(trunc(max(tra.c_fecha_inicio_real)),to_date('01/01/1900','dd/MM/yyyy')) fecha_fin ");
						hql.append(" from t_biz_wfl_tramites tra ");
						hql.append(" inner join t_biz_wfl_procesos pro on pro.c_id_proceso = tra.c_id_proceso ");
						hql.append(" inner join t_biz_wfl_estados est on est.c_id_estado = tra.c_id_estado ");
						hql.append(" inner join t_vis_eva_individuos ind on ind.c_cuil = tra.c_cu ");
						hql.append(" inner join t_vis_tra_clientes cli on cli.c_id_individuo = ind.c_id_individuo ");
						hql.append(" where cli.c_id_cliente = " + arrCliente.getCliente() + " and pro.c_id_proceso in (3,6))sal ");

						List rs1 = this.genericDao.listarResulsetSql(hql.toString());
						Date fechaRevision = null;

						Iterator itRs1 = rs1.iterator();
						while (itRs1.hasNext()) {
							Map itemRs1 = (Map) itRs1.next();

							if (itemRs1.get("fecha") != null) {
								fechaRevision = new Date(((Timestamp) itemRs1.get("fecha")).getTime());
							}

							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.MONTH, -12);
							if (fechaRevision.after(calendar.getTime())) {
								controlCliente.setFechaResolucion(new Date());

								this.genericDao.modificar(controlCliente);
							}
						}
					}
				} else {

					if (arrCliente.getC1() == 1) {
						Filtro filtro1 = new Filtro();
						filtro1.agregarCampoOperValor("idCliente", Filtro.IGUAL, arrCliente.getCliente());
						filtro1.agregarCampoOperValor("tipoControl.idTipoControl", Filtro.IGUAL, 1);
						ControlClientes controlCliente = (ControlClientes) genericDao.listar(filtro1, ControlClientes.class.getName()).get(0);
						this.genericDao.eliminar(controlCliente);
					}
					if (arrCliente.getC2() == 1) {
						Filtro filtro1 = new Filtro();
						filtro1.agregarCampoOperValor("idCliente", Filtro.IGUAL, arrCliente.getCliente());
						filtro1.agregarCampoOperValor("tipoControl.idTipoControl", Filtro.IGUAL, 2);
						ControlClientes controlCliente = (ControlClientes) genericDao.listar(filtro1, ControlClientes.class.getName()).get(0);
						this.genericDao.eliminar(controlCliente);
					}
					if (arrCliente.getC3() == 1) {
						Filtro filtro1 = new Filtro();
						filtro1.agregarCampoOperValor("idCliente", Filtro.IGUAL, arrCliente.getCliente());
						filtro1.agregarCampoOperValor("tipoControl.idTipoControl", Filtro.IGUAL, 3);
						ControlClientes controlCliente = (ControlClientes) genericDao.listar(filtro1, ControlClientes.class.getName()).get(0);
						this.genericDao.eliminar(controlCliente);
					}

					if (arrCliente.getC5() == 1) {
						Filtro filtro1 = new Filtro();
						filtro1.agregarCampoOperValor("idCliente", Filtro.IGUAL, arrCliente.getCliente());
						filtro1.agregarCampoOperValor("tipoControl.idTipoControl", Filtro.IGUAL, 5);
						ControlClientes controlCliente = (ControlClientes) genericDao.listar(filtro1, ControlClientes.class.getName()).get(0);
						this.genericDao.eliminar(controlCliente);
					}

					if (arrCliente.getC6() == 1) {
						Filtro filtro1 = new Filtro();
						filtro1.agregarCampoOperValor("idCliente", Filtro.IGUAL, arrCliente.getCliente());
						filtro1.agregarCampoOperValor("tipoControl.idTipoControl", Filtro.IGUAL, 6);
						ControlClientes controlCliente = (ControlClientes) genericDao.listar(filtro1, ControlClientes.class.getName()).get(0);
						this.genericDao.eliminar(controlCliente);
					}

				}
			}
			// this.genericDao.modificar(emailInCola);
		} catch (Exception e) {
			resultado = false;
			// TODO: handle exception
		}

	}


	public IGenericDao getGenericDao() {
		return genericDao;
	}


	public void setGenericDao(IGenericDao genericDao) {
		this.genericDao = genericDao;
	}


	public ClienteTransaccionDao getClienteTransaccionDao() {
		return clienteTransaccionDao;
	}


	public void setClienteTransaccionDao(ClienteTransaccionDao clienteTransaccionDao) {
		this.clienteTransaccionDao = clienteTransaccionDao;
	}

}
