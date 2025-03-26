package com.bizitglobal.tarjetafiel.cobranzas.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.cobranzas.dao.TareaPendienteDao;
import com.bizitglobal.tarjetafiel.cobranzas.exception.EjecucionPlanException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EjecucionPlanAsignacionCobrador;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.TareaPendiente;
import com.bizitglobal.tarjetafiel.cobranzas.service.TareaPendienteService;
import com.bizitglobal.tarjetafiel.evaluacion.dao.IndividuoDomicilioDao;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;

public class TareaPendienteServiceImpl implements TareaPendienteService {
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las
	 * operaciones con la base de datos.
	 */
	private TareaPendienteDao tareaPendienteDao;

	private IndividuoDomicilioDao individuoDomiciliosCobranzasDao;

	public IndividuoDomicilioDao getIndividuoDomiciliosCobranzasDao() {
		return individuoDomiciliosCobranzasDao;
	}

	public void setIndividuoDomiciliosCobranzasDao(
			IndividuoDomicilioDao individuoDomiciliosCobranzasDao) {
		this.individuoDomiciliosCobranzasDao = individuoDomiciliosCobranzasDao;
	}

	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las
	 * transPlanes.
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;

	/**
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public List<TareaPendiente> listaTareasPendientes() throws Exception {
		try {
			transactionTemplate
					.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List<TareaPendiente>) transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							@SuppressWarnings("rawtypes")
							List lista = tareaPendienteDao
									.listarTareasPendientes();
							return lista;
						}
					});
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Lista los detalles de una tarea.
	 * 
	 * @param tareaP
	 *            Una tarea pendiente.
	 * @return Un listado de los Objetos EjecucionPlan que forman esa tarea.
	 * */

	@SuppressWarnings("rawtypes")
	public List listarDetallesTarea(final TareaPendiente tareaP,
			final String estado) throws Exception {

		List<EjecucionPlanAsignacionCobrador> resultado = null;

		try {

			if (tareaP.getIdAccion() == TareaPendiente.ASIGNACION_COBRADOR) {
				resultado = tareaPendienteDao
						.listarDetallesTareaAsignacionCobrador(tareaP, estado);
			} else if (tareaP.getIdAccion() == TareaPendiente.PRIMER_ENVIO_CARTA
					|| tareaP.getIdAccion() == TareaPendiente.ENVIO_AVISO_URGENTE
					|| tareaP.getIdAccion() == TareaPendiente.ENVIO_AVISO_DOCUMENTADO
					|| tareaP.getIdAccion() == TareaPendiente.ENVIO_AVISO_PREJUDICIAL) {
				resultado = tareaPendienteDao.listarDetallesTareaEnvioCarta(
						tareaP, estado);
			} else if (tareaP.getIdAccion() == TareaPendiente.LLAMADAS_TELEFONICAS){
				resultado = tareaPendienteDao.listarDetallesTareaLlamadasTelefonicas(tareaP.getFecha(),new Long(tareaP.getIdAccion()), estado);
			} else if (tareaP.getIdAccion() == TareaPendiente.TELEDIRECTO){
				resultado = tareaPendienteDao.listarDetallesTareaTeledirecto(tareaP.getFecha(),new Long(tareaP.getIdAccion()), estado);
			} else if (tareaP.getIdAccion() == TareaPendiente.ASIGNACION_ABOGADO || tareaP.getIdAccion() == TareaPendiente.LIQUIDACION_JUDICIAL){
				resultado = tareaPendienteDao.listarDetallesTareaAsigncionAbogado(tareaP.getFecha(),new Long(tareaP.getIdAccion()),estado);
			}

			boolean b = tareaP.getIdAccion() == TareaPendiente.ASIGNACION_COBRADOR
					|| tareaP.getIdAccion() == TareaPendiente.PRIMER_ENVIO_CARTA
					|| tareaP.getIdAccion() == TareaPendiente.ENVIO_AVISO_URGENTE
					|| tareaP.getIdAccion() == TareaPendiente.ENVIO_AVISO_DOCUMENTADO
					|| tareaP.getIdAccion() == TareaPendiente.ENVIO_AVISO_PREJUDICIAL
					|| tareaP.getIdAccion() == TareaPendiente.ASIGNACION_ABOGADO
					|| tareaP.getIdAccion() == TareaPendiente.LIQUIDACION_JUDICIAL;

			if (b) { // Verifico domicilio valido
				Iterator<EjecucionPlanAsignacionCobrador> iter = resultado
				.iterator();
				while (iter.hasNext()) {
					EjecucionPlanAsignacionCobrador ejecucionPlan = iter.next();

					Domicilio dom = individuoDomiciliosCobranzasDao
							.getDomicilioByIdIndividuo(ejecucionPlan
									.getIdIndividuo());
					if (dom != null) {
						ejecucionPlan.setIsDomicilioValido(dom
								.validateDomicilio());
					} else {
						ejecucionPlan.setIsDomicilioValido(false);
					}

				}
			}

			return resultado;
		} catch (Exception e) {
			String msg = "No se pudo listar los detalles de la tarea.";
			throw new Exception(msg, e);
		}
	}

	public void ejecutarConfirmacionAsignacionCobradores(
			final TareaPendiente tareaP) throws Exception {
		try {

		} catch (Exception e) {
			String msg = "No se pudo listar los detalles de la tarea.";
			throw new Exception(msg, e);
		}
	}

	/**
	 * Necesario para spring.
	 * 
	 * @return Retorna el objeto transactionManager.
	 */
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	/**
	 * Necesario para spring
	 * 
	 * @param transactionManager
	 *            , Objeto a setear.
	 */
	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public TareaPendienteDao getTareaPendienteDao() {
		return tareaPendienteDao;
	}

	public void setTareaPendienteDao(TareaPendienteDao tareaPendienteDao) {
		this.tareaPendienteDao = tareaPendienteDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public List getListaTareasByParam(String estado, Date fechaDesde,
			Date fechaHasta) throws EjecucionPlanException {
		List resultado = this.tareaPendienteDao.getListaTareasByParam(estado,
				fechaDesde, fechaHasta);

		if (resultado != null && resultado.size() > 0) {
			return resultado;
		} else {
			throw new EjecucionPlanException("No existen tareas");
		}

	}

	@Override
	public void generarPlanillasCobradores(Date fecha) {

		List resultado = this.tareaPendienteDao.getListaDetallesTareasByParam(
				fecha, new Long(8), "N");

	}

	@Override
	public List getListaDetallesTareasByParam(Date fecha, Long idAccion,
			String estado) {

		if (idAccion == TareaPendiente.PRIMER_ENVIO_CARTA
				|| idAccion == TareaPendiente.ENVIO_AVISO_URGENTE
				|| idAccion == TareaPendiente.ENVIO_AVISO_DOCUMENTADO
				|| idAccion == TareaPendiente.ENVIO_AVISO_PREJUDICIAL) { // Detalles
																			// para
																			// envio
																			// carta
			return tareaPendienteDao
					.listarDetallesTareaEnvioCartaParaConfirmar(fecha,
							idAccion, estado);
		} else if (idAccion == TareaPendiente.ASIGNACION_COBRADOR){ // Detalles para confirmar Planilla cobrador idAccion = 8
			return this.tareaPendienteDao.getListaDetallesTareasByParam(fecha,
					idAccion, estado);
		} else if (idAccion == TareaPendiente.LLAMADAS_TELEFONICAS){
			return this.tareaPendienteDao.listarDetallesTareaLlamadasTelefonicas(fecha, idAccion, estado);
		} else if (idAccion == TareaPendiente.TELEDIRECTO){
			return this.tareaPendienteDao.listarDetallesTareaTeledirecto(fecha, idAccion, estado);
		} else if (idAccion == TareaPendiente.ASIGNACION_ABOGADO || idAccion == TareaPendiente.LIQUIDACION_JUDICIAL){
			return this.tareaPendienteDao.listarDetallesTareaAsigncionAbogadoParaConfirmar(fecha,idAccion,estado);
		}
		return null;
	}

	@Override
	public void confirmarTareaPendiente(Date fecha, int tipoTarea, Long idAccion) {

		List tareasPendientes = tareaPendienteDao
				.getListaDetallesTareasByParam(fecha, idAccion, "N");

		switch (tipoTarea) {
		case 1: // Crear PDF envio Carta

			break;
		case 2: // Crear PDF Ficha Cobradores

			break;
		default:
			break;
		}

	}

	@Override
	public List getListaTareasByIdCliente(Long idCliente) throws Exception {
		if (idCliente==null){
			throw new Exception("Cliente nulo");
		}else {
			return tareaPendienteDao.getListaTareasByIdCliente(idCliente);
		}
	}

}
