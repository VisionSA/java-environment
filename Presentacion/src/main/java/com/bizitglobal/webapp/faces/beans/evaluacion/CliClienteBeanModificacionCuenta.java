package com.bizitglobal.webapp.faces.beans.evaluacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Estados;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudIndividuo;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliMarca;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class CliClienteBeanModificacionCuenta extends BaseBean {
	private static final Logger log = Logger.getLogger(CliClienteBeanModificacionCuenta.class);
	private int origen;
	public static final int CAMBIO_LIM = 1;
	public static final int ABM_ADIC = 2;
	private String obsCred;
	private BigDecimal limiteCred;
	private Long idCliClienteHidden;
	private String nroSolicitud;
	private Solicitud solicitud;

	private IndividuoEvaluacion individuoEvaluacion;
	private SolicitudIndividuo solicIndividuoTitular;
	private IndividuoEvaluacion individuoGarante;
	private SolicitudIndividuo solicIndividuoGarante;
	private StringBuffer adicionales = new StringBuffer();

	// definicion de un list del objeto base

	private CliMarca marca;
	private CliCliente titular;

	// private List cliclienteList;
	// private List listAdicional = new ArrayList();
	private List listSolicAdicional = new ArrayList();
	private List listAdicional = new ArrayList();
	private List listGarante = new ArrayList();

	// Lista para objetos del sistema viejo.
	private List cliIndividuosViejos;

	private String focoHidden;
	private boolean verGrabar;
	private String nroCuenta;
	private boolean irVerSolicitud;


	public CliClienteBeanModificacionCuenta() {
		super();
		borrar();
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public String getObsCred() {
		return obsCred;
	}


	public void setObsCred(String obsCred) {
		this.obsCred = obsCred;
	}


	public BigDecimal getLimiteCred() {
		return limiteCred;
	}


	public void setLimiteCred(BigDecimal limiteCred) {
		this.limiteCred = limiteCred;
	}


	public Long getIdCliClienteHidden() {
		return idCliClienteHidden;
	}


	public void setIdCliClienteHidden(Long idCliClienteHidden) {
		this.idCliClienteHidden = idCliClienteHidden;
	}


	public IndividuoEvaluacion getIndividuoEvaluacion() {
		return individuoEvaluacion;
	}


	public void setIndividuoEvaluacion(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}


	public String getNroSolicitud() {
		return nroSolicitud;
	}


	public void setNroSolicitud(String nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}


	public List getCliIndividuosViejos() {
		return cliIndividuosViejos;
	}


	public void setCliIndividuosViejos(List object) {
		this.cliIndividuosViejos = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public String getGarante() {
		if (individuoGarante != null)
			return individuoGarante.getApellido() + ", " + individuoGarante.getNombres();
		return null;
	}


	public List getListAdicional() {
		return listAdicional;
	}


	public void setListAdicional(List listAdicional) {
		this.listAdicional = listAdicional;
	}


	public List getListGarante() {
		return listGarante;
	}


	public void setListGarante(List listGarante) {
		this.listGarante = listGarante;
	}


	public boolean isVerGrabar() {
		return verGrabar;
	}


	public void setVerGrabar(boolean verGrabar) {
		this.verGrabar = verGrabar;
	}


	public String getNroCuenta() {
		return nroCuenta;
	}


	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CLICLIENTE
	 ************************************************************************/

	public String inicializar() {
		borrar();

		return "amCliCliente";
	}


	public void inicializarParametros(Map param) {
		borrar();
		this.verGrabar = true;
		this.irVerSolicitud = false;
		try {
			this.nroSolicitud = (String) param.get("nroSolicitud");
			this.nroCuenta = (String) param.get("nroCuenta");
		} catch (ClassCastException e) {
			error.agregar(Error.EVA_NUMERO_SOLICITUD_INCORRECTO);
		}
		log.info("Modificacion estructura para la solicitud nro: " + nroSolicitud);
		if (!error.hayErrores()) {
			if (validar()) {
				try {
					Filtro filtro = new Filtro("nroSolicitud", Filtro.LIKEXS, nroSolicitud.substring(0, nroSolicitud.length() - 1));
					solicitud = evaluacionService.getSolicitudService().busNroComprobante(filtro);
					if (solicitud != null) {
						if (!solicitud.getEstados().getIdEstado().equals(new Long(5)) && !solicitud.getEstados().getIdEstado().equals(new Long(4))) {
							if (solicitud.getPromotor() != null) {
								alta = false;
								List solicIndList = new ArrayList(solicitud.getSolicIndividuos());
								if (solicIndList.isEmpty()) {
									error.agregar("Error grabe. La solicitud esta vacia");
								} else {
									solicitud.getEstados().getDescripcion();
									Iterator iterCargando = solicitud.getSolicIndividuos().iterator();
									while (iterCargando.hasNext()) {
										SolicitudIndividuo sol = (SolicitudIndividuo) iterCargando.next();
										if (sol.getActivo().equals("S") && sol.getAceptado().equals("S")) {
											if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(1))) {
												individuoEvaluacion = sol.getIndividuoEvaluacion();
												individuoEvaluacion.getApellido();
												individuoEvaluacion.getActividad();
												if (individuoEvaluacion.getActividad().getSucEmpresa() != null)
													individuoEvaluacion.getActividad().getSucEmpresa();
											} else {
												if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(2))) {
													individuoGarante = sol.getIndividuoEvaluacion();
													individuoGarante.getApellido();
													individuoGarante.getActividad();
													individuoGarante.getActividad().getSucEmpresa();
													individuoGarante.getVinculo();
													/* @I4991 */listGarante.add(new WrapperIndividuoEvaluacion(sol.getIndividuoEvaluacion(), sol
															.getNuevo(), sol.getNuevo().equals("S") ? "ALTA" : "-"));
													/* @F4991 */} else {
													if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(3))) {
														sol.getIndividuoEvaluacion().getApellido();
														/* @I4991 */listAdicional.add(new WrapperIndividuoEvaluacion(sol.getIndividuoEvaluacion(),
																sol.getNuevo(), sol.getNuevo().equals("S") ? "ALTA" : "-"));
														/* @F4991 */}
												}
											}
										}

										if (sol.getActivo().equals("N") && sol.getNuevo().equals("N"))
										{
											sol.getIndividuoEvaluacion().getApellido();
											sol.getIndividuoEvaluacion().getNombres();
											IndividuoEvaluacion indi = sol.getIndividuoEvaluacion();
											if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(2))) {
												/* @I4991 */listGarante
														.add(new WrapperIndividuoEvaluacion(sol.getIndividuoEvaluacion(), "N", "BAJA"));
												/* @F4991 */} else {
												if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(3))) {
													/* @I4991 */listAdicional.add(new WrapperIndividuoEvaluacion(sol.getIndividuoEvaluacion(), "N",
															"BAJA"));
													/* @F4991 */}
											}
										}
									}
								}
							} else {
								error.agregar(Error.EVA_SOLICITUD_SIN_PROMOTOR);
							}
						} else
						{
							error.agregar(Error.EVA_SOLICITUD_FINALIZADA);
						}
					} else {
						error.agregar(Error.EVA_NRO_SOLICITUD_NO_ENCONTRADA);
					}
				} catch (SolicitudException e) {
					error.agregar("Error al cargar los datos.");
					e.printStackTrace();
				} catch (NullPointerException e2) {
					error.agregar("Error al cargar los datos.");
					e2.printStackTrace();
				} catch (Exception e) {
					error.agregar("Error al cargar los datos.");
					e.printStackTrace();
				}

			}
		}
	}


	public String grabar() {
		try {
			if (irVerSolicitud)
			{
				Map param = new HashMap();
				param.put("nroSolicitud", this.nroSolicitud);
				param.put("nroCuenta", this.nroCuenta);
				this.inicializarParametros(param);
			}
			if (validarGrabar()) {
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("individuo.idIndividuo", Filtro.IGUAL, individuoEvaluacion.getIdIndividuo());
				List listTitular = transaccionesService.getClienteTransaccionService().getCliente(filtro);
				ClienteTransaccion clienteTransaccionTitular = (ClienteTransaccion) listTitular.get(0);

				if (clienteTransaccionTitular != null)
				{
					transaccionesService.getClienteTransaccionService().modificarEstructuraCuenta(clienteTransaccionTitular, solicitud,
							Session.getOperador());
					Estados estado = evaluacionService.getEstadosService().leerEstados(new Long(5));
					solicitud.setEstados(estado);
					evaluacionService.getSolicitudService().actualizarSolicitud(solicitud);
					verGrabar = false;
				}

				popup.setPopup(popup.ICONO_OK, "El cliente ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ClienteTransaccionException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla la modificacion de estructura de cuenta del cliente.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla la modificacion de estructura de cuenta del cliente.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		borrarBaseBean();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de nuevo cliente";

		obsCred = "";
		limiteCred = new BigDecimal(0);
		idCliClienteHidden = new Long(0);
		nroSolicitud = null;
		solicitud = null;
		individuoEvaluacion = null;
		solicIndividuoTitular = null;
		individuoGarante = null;
		solicIndividuoGarante = null;
		listSolicAdicional.clear();
		listAdicional.clear();
		listGarante.clear();
		marca = null;
		cliIndividuosViejos = new ArrayList();
	}


	public String cancelar() {
		borrar();
		ejecutarJavaScript("window.close();");
		return "";
	}


	public String irAIndividuo() {
		this.irVerSolicitud = true;
		ModificacionEstructuraCuentaBean bean = (ModificacionEstructuraCuentaBean) Session.getBean("ModificacionEstructuraCuentaBean");
		bean.setNroCuenta(this.nroCuenta);
		bean.inicializar(1, solicitud.getNroSolicitud() + solicitud.getDV(), null);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/evaluacion/modificacionEstructuraCuenta.jsf";
		ejecutarJavaScript("popup('" + path + "',1000,600), 'titlebar=no';");
		return null;
	}


	public String irAContinuar() {
		popup.borrar();
		return "";
	}


	public String irASalir() {
		return cancelar();
	}


	public String rellenarCerosFaltantes(String num) {
		String aux = "";
		for (int i = 0; i < (8 - num.length()); i++) {
			aux = aux + "0";
		}
		aux = aux + num;
		return aux;
	}


	public boolean validarGrabar() {

		IndividuoEvaluacion in = individuoEvaluacion;
		if (in.getFechaNacimiento() == null)
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_FECHA_NAC_REQ);

		if (in.getDomicilio() == null)
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_DOMICILIO_REQ);

		if (in.getEstadoCivil() == null
				|| in.getEstadoCivil().getIdEstadoCivil() == null
				|| in.getEstadoCivil().getIdEstadoCivil().equals(new Long(0)))
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_ESTADO_CIVIL_REQ);

		if (in.getHijosCargo() == null)
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_HIJOS_A_CARGO_REQ);

		if (in.getEducacion() == null
				|| in.getEducacion().getIdEducacion() == null
				|| in.getEducacion().getIdEducacion().equals(new Long(0)))
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_EDUCACION_REQ);

		if (in.getDomicilioDoc() == null
				|| in.getDomicilioDoc().getIdDomicilio() == null
				|| in.getDomicilioDoc().getIdDomicilio().equals(new Long(0)))
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_DOMICILIO_DOCUM_REQ);

		if (in.getDiaPago() == null
				|| in.getDiaPago().getIdDiaPago() == null
				|| in.getDiaPago().getIdDiaPago().equals(new Long(0)))
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_DIA_DE_PAGO_REQ);

		try
		{
			if (in.getActividad() == null
					|| in.getActividad().getSucEmpresa() == null
					|| in.getActividad().getSucEmpresa().getIdSucEmpresa().equals(new Long(0)))
				error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_SUCURSAL_REQ);
		} catch (Exception e)
		{
			error.agregar("En el TITULAR: " + Error.EVA_INDIVIDUO_SUCURSAL_REQ);
		}

		if (individuoGarante != null) {
			in = individuoGarante;
			if (in.getVinculo() == null
					|| in.getVinculo().getIdVinculo() == null
					|| in.getVinculo().getIdVinculo().equals(new Long(0)))
				error.agregar("En el GARANTE: " + Error.EVA_INDIVIDUO_VINCULO_REQ);

			if (in.getHijosCargo() == null)
				error.agregar("En el GARANTE: " + Error.EVA_INDIVIDUO_HIJOS_A_CARGO_REQ);

			try
			{
				if (in.getActividad() == null
						|| in.getActividad().getSucEmpresa() == null
						|| in.getActividad().getSucEmpresa().getIdSucEmpresa().equals(new Long(0)))
					error.agregar("En el GARANTE: " + Error.EVA_INDIVIDUO_SUCURSAL_REQ);
			} catch (Exception e)
			{
				error.agregar("En el GARANTE: " + Error.EVA_INDIVIDUO_SUCURSAL_REQ);
			}
		}
		return !error.hayErrores();
	}


	public boolean validar() {
		log.info("Ejecuando ==> Validar()");

		if (nroSolicitud == null || nroSolicitud.equals(new String("")))
			error.agregar(Error.EVA_NRO_SOLICITUD_REQUERIDA);

		return !error.hayErrores();
	}

	public class WrapperIndividuoEvaluacion
	{
		private IndividuoEvaluacion individuoEvaluacion;
		private String nuevo;
		private String accion;


		public WrapperIndividuoEvaluacion(IndividuoEvaluacion individuoEva, String nvo)
		{
			this.individuoEvaluacion = individuoEva;
			this.nuevo = nvo;
			/* @I4991 */this.accion = (nvo.equals("S") ? "ALTA" : "-");
		}


		public WrapperIndividuoEvaluacion(IndividuoEvaluacion individuoEva, String nvo, String accion) {
			this.individuoEvaluacion = individuoEva;
			this.nuevo = nvo;
			this.accion = accion;
			/* @F4991 */}


		public IndividuoEvaluacion getIndividuoEvaluacion() {
			return individuoEvaluacion;
		}


		public void setIndividuoEvaluacion(IndividuoEvaluacion individuoEvaluacion) {
			this.individuoEvaluacion = individuoEvaluacion;
		}


		public String getNuevo() {
			return nuevo;
		}


		public void setNuevo(String nuevo) {
			this.nuevo = nuevo;
		}


		public String getAccion() {
			return accion;
		}


		public void setAccion(String accion) {
			this.accion = accion;
		}

	}

}
