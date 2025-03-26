package com.bizitglobal.webapp.faces.beans.evaluacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EsquemaIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.EsquemaIndividuo;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudIndividuo;
import com.bizitglobal.tarjetafiel.general.negocio.EsquemaRegla;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.wrappers.SolicitudesIndividuosWrapper;
import com.bizitglobal.webapp.faces.beans.evaluacion.wrappers.WrappersReglas;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class EvaluacionReglasBean extends BaseBean {
	private static final Logger log = Logger.getLogger(EvaluacionReglasBean.class);

	private SolicitudIndividuo solicitudIndividuo;
	private List reglasAutomaticas;
	private List reglasManuales;
	private List estadosIndividuos;
	private boolean validar;
	// tipoEsquema toma los valores Titular, Garante o Adicional
	private String tipoEsquema;

	private boolean aceptar = true;

	private List aux;


	public EvaluacionReglasBean() {

	}


	public void borrar() {
		popup.borrar();
		error.borrar();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Evaluacion de Reglas";
		reglasAutomaticas = new ArrayList();
		reglasManuales = new ArrayList();
		estadosIndividuos = new ArrayList();
		tipoEsquema = "";
		aux = new ArrayList();
		validar = false;
	}


	public void inicializarParametros(Map param) {
		borrar();
		if (param.get("idSolicitudTitular") != null) {
			Long paraIdIndividuo = new Long((String) param.get("idSolicitudTitular"));
			inicializar(paraIdIndividuo);
		} else {
			error.agregar("Valor del parametro incorrecto. Debe llamarse \"idSolicitudTitular\"");
		}
		if (param.get("vista") != null && param.get("vista").equals("4")) {
			validar = true;
		}
	}


	/** Origen se corresponde con tipoIndividuo, y decide el esquema: titular, garante o adicional */
	public String inicializar(Long idSolicitudIndividuo) {
		String result = null;
		try {
			Filtro filtro = new Filtro();
			solicitudIndividuo = evaluacionService.getSolicitudIndividuoService().leerSolicitudIndividuo(idSolicitudIndividuo);

			solicitudIndividuo.getIndividuoEvaluacion().getApellido();
			if (solicitudIndividuo.getIndividuoEvaluacion().getActividad() != null
					&& solicitudIndividuo.getIndividuoEvaluacion().getActividad().getSucEmpresa() != null)
				solicitudIndividuo.getIndividuoEvaluacion().getActividad().getSucEmpresa().getIdSucEmpresa();

			solicitudIndividuo.getSolicitud().getNroSolicitud();
			solicitudIndividuo.getSolicitud().getDV();
			solicitudIndividuo.getTipoIndividuo().getDescripcion();

			setTipoEsquema(solicitudIndividuo.getTipoIndividuo().getDescripcion());
			filtro.agregarCampoOperValor("solicitudIndividuo.idSolicitudIndividuo", Filtro.IGUAL, idSolicitudIndividuo);
			List lista = evaluacionService.getEsquemaIndividuoService().getEsquemaIndividuo(filtro);

			Iterator iterador = lista.iterator();
			while (iterador.hasNext()) {
				EsquemaIndividuo esquemaIndividuo = (EsquemaIndividuo) iterador.next();
				EsquemaRegla esqRegla;
				esqRegla = esquemaIndividuo.getEsquemaRegla();
				esqRegla.getEsquema().getDescripcion();
				esqRegla.getRegla().getDescripcion();
				WrappersReglas wrappersReglas = new WrappersReglas(esquemaIndividuo);
				if (esqRegla.getEsManual().compareTo("S") == 0) {
					reglasManuales.add(wrappersReglas);
				} else {
					reglasAutomaticas.add(wrappersReglas);
				}
			}

			Long idSolicitud = solicitudIndividuo.getSolicitud().getIdSolicitud();
			cargarEstadosIndividuos(idSolicitud, null);

		} catch (EsquemaIndividuoException e1) {
			e1.printStackTrace();
		} catch (SolicitudIndividuoException e2) {
			e2.printStackTrace();
		}
		return result;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.opener.recargar();window.close();";
		AddResource addResource = AddResourceFactory.getInstance(facesContext);
		addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
	}


	public String inicializar() {
		return null;
	}


	public boolean validar() {
		error.borrar();
		if (validar) {
			IndividuoEvaluacion in = solicitudIndividuo.getIndividuoEvaluacion();
			if (in.getFechaNacimiento() == null) {
				error.agregar(Error.EVA_INDIVIDUO_FECHA_NAC_REQ);
			}

			if (in.getDomicilio() == null) {
				error.agregar(Error.EVA_INDIVIDUO_DOMICILIO_REQ);
			}

			switch (solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().intValue()) {
			case IndividuoEvaluacionBean.TITULAR:
				if (in.getEstadoCivil() == null
						|| in.getEstadoCivil().getIdEstadoCivil() == null
						|| in.getEstadoCivil().getIdEstadoCivil().equals(new Long(0))) {
					error.agregar(Error.EVA_INDIVIDUO_ESTADO_CIVIL_REQ);
				}

				if (in.getHijosCargo() == null) {
					error.agregar(Error.EVA_INDIVIDUO_HIJOS_A_CARGO_REQ);
				}

				if (in.getEducacion() == null
						|| in.getEducacion().getIdEducacion() == null
						|| in.getEducacion().getIdEducacion().equals(new Long(0))) {
					error.agregar(Error.EVA_INDIVIDUO_EDUCACION_REQ);
				}

				if (in.getDomicilioDoc() == null
						|| in.getDomicilioDoc().getIdDomicilio() == null
						|| in.getDomicilioDoc().getIdDomicilio().equals(new Long(0))) {
					error.agregar(Error.EVA_INDIVIDUO_DOMICILIO_DOCUM_REQ);
				}

				if (in.getDiaPago() == null
						|| in.getDiaPago().getIdDiaPago() == null
						|| in.getDiaPago().getIdDiaPago().equals(new Long(0))) {
					error.agregar(Error.EVA_INDIVIDUO_DIA_DE_PAGO_REQ);
				}

				if (in.getActividad() == null
						|| in.getActividad().getSucEmpresa() == null
						|| in.getActividad().getSucEmpresa().getIdSucEmpresa().equals(new Long(0))) {
					error.agregar(Error.EVA_INDIVIDUO_SUCURSAL_REQ);
				}
				break;

			case IndividuoEvaluacionBean.GARANTE:
				if (in.getVinculo() == null
						|| in.getVinculo().getIdVinculo() == null
						|| in.getVinculo().getIdVinculo().equals(new Long(0))) {
					error.agregar(Error.EVA_INDIVIDUO_VINCULO_REQ);
				}

				if (in.getHijosCargo() == null) {
					error.agregar(Error.EVA_INDIVIDUO_HIJOS_A_CARGO_REQ);
				}

				if (in.getActividad() == null
						|| in.getActividad().getSucEmpresa() == null
						|| in.getActividad().getSucEmpresa().getIdSucEmpresa().equals(new Long(0))) {
					error.agregar(Error.EVA_INDIVIDUO_SUCURSAL_REQ);
				}
				break;

			case IndividuoEvaluacionBean.ADICIONAL:
				if (in.getVinculo() == null
						|| in.getVinculo().getIdVinculo() == null
						|| in.getVinculo().getIdVinculo().equals(new Long(0))) {
					error.agregar(Error.EVA_INDIVIDUO_VINCULO_REQ);
				}
				break;
			}
		}
		return !error.hayErrores();
	}


	public List getReglasAutomaticas() {
		return reglasAutomaticas;
	}


	public void setReglasAutomaticas(List reglasAutomaticas) {
		this.reglasAutomaticas = reglasAutomaticas;
	}


	public List getReglasManuales() {
		return reglasManuales;
	}


	public void setReglasManuales(List reglasManuales) {
		this.reglasManuales = reglasManuales;
	}


	public String getTipoEsquema() {
		return tipoEsquema;
	}


	public void setTipoEsquema(String tipoEsquema) {
		this.tipoEsquema = tipoEsquema;
	}


	// / Metodos de tuneo para la pagina.

	public String getSubtitulo() {
		return getTipoEsquema() + ": ";
	}


	public String getNombreCompleto() {
		return solicitudIndividuo.getIndividuoEvaluacion().getApellido() + ", " + solicitudIndividuo.getIndividuoEvaluacion().getNombres();
	}


	public SolicitudIndividuo getSolicitudIndividuo() {
		return solicitudIndividuo;
	}


	public void setSolicitudIndividuo(SolicitudIndividuo solicitudIndividuo) {
		this.solicitudIndividuo = solicitudIndividuo;
	}


	public List getEstadosIndividuos() {
		return estadosIndividuos;
	}


	public void setEstadosIndividuos(List estadosIndividuos) {
		this.estadosIndividuos = estadosIndividuos;
	}


	// fin metodos de tuneo

	public String irAIndividuo() {

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		if (solicitudIndividuo.getSolicitud().getIdTipoSolicitud().equals(new Long(1)))
		{
			IndividuoEvaluacionBean bean = (IndividuoEvaluacionBean) Session.getBean("IndividuoEvaluacionBean");
			bean.inicializar(solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().intValue(), rellenarCerosFaltantes(solicitudIndividuo
					.getSolicitud().getNroSolicitud() + solicitudIndividuo.getSolicitud().getDV()), verNumeroSolicitudIndividuo());
			solicitudIndividuo = bean.getSolicitudIndividuo();
			path += "/tarjetafiel/evaluacion/amIndividuo.jsf";
		}
		else
		{
			ModificacionEstructuraCuentaBean bean = (ModificacionEstructuraCuentaBean) Session.getBean("ModificacionEstructuraCuentaBean");
			String nroSolicitud = solicitudIndividuo.getSolicitud().getNroSolicitud() + solicitudIndividuo.getSolicitud().getDV();
			bean.inicializar(solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().intValue(), nroSolicitud, null);
			path += "/tarjetafiel/evaluacion/modificacionEstructuraCuenta.jsf";
		}
		ejecutarJavaScript("popup('" + path + "',1000,600), 'titlebar=no';");
		return null;
	}


	public Long verNumeroSolicitudIndividuo() {
		if (solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().intValue() != 3)
			return null;
		return solicitudIndividuo.getIdSolicIndividuo();
	}


	public String rellenarCerosFaltantes(String num) {
		String aux = "";
		for (int i = 0; i < (8 - num.length()); i++) {
			aux = aux + "0";
		}
		aux = aux + num;
		return aux;
	}


	private void cargarEstadosIndividuos(Long idSolicitud, Long idIndividuo) {
		Filtro filtro = new Filtro();
		try {

			filtro.agregarCampoOperValor("solicitud.idSolicitud", Filtro.IGUAL, idSolicitud);
			filtro.agregarCampoOperValor("activo", Filtro.LIKEXS, "S");
			aux = evaluacionService.getSolicitudIndividuoService().getSolicitudIndividuo(filtro);

			if (!aux.isEmpty()) {
				Iterator iterator = aux.iterator();
				while (iterator.hasNext()) {
					SolicitudIndividuo element = (SolicitudIndividuo) iterator.next();
					element.getIndividuoEvaluacion().getNombres();
					element.getIndividuoEvaluacion().getApellido();
					element.getTipoIndividuo().getDescripcion();
					List esqInd = new ArrayList();
					SolicitudesIndividuosWrapper wrapper;
					if (idIndividuo != null && !idIndividuo.equals(new Long(0))) {
						filtro = new Filtro();
						filtro.agregarCampoOperValor("solicitudIndividuo.idSolicitudIndividuo", Filtro.IGUAL, element.getIdSolicIndividuo());
						filtro.agregarCampoOperValor("esquemaRegla.ponderacion", Filtro.IGUAL, "1");
						filtro.agregarCampoOperValor("resultado", Filtro.LIKEXS, "N");

						esqInd = evaluacionService.getEsquemaIndividuoService().getEsquemaIndividuo(filtro);

						if (esqInd.isEmpty()) {
							wrapper = new SolicitudesIndividuosWrapper(element, true);
							estadosIndividuos.add(wrapper);
						}
						else {
							wrapper = new SolicitudesIndividuosWrapper(element, false);
							estadosIndividuos.add(wrapper);
						}
					} else {
						filtro = new Filtro();
						filtro.agregarCampoOperValor("solicitudIndividuo.idSolicitudIndividuo", Filtro.IGUAL, element.getIdSolicIndividuo());
						filtro.agregarCampoOperValor("esquemaRegla.ponderacion", Filtro.IGUAL, "1");
						filtro.agregarCampoOperValor("resultado", Filtro.LIKEXS, "N");

						esqInd = evaluacionService.getEsquemaIndividuoService().getEsquemaIndividuo(filtro);

						if (esqInd.isEmpty()) {
							wrapper = new SolicitudesIndividuosWrapper(element, true);
							estadosIndividuos.add(wrapper);
						}
						else {
							wrapper = new SolicitudesIndividuosWrapper(element, false);
							estadosIndividuos.add(wrapper);
						}
					}
					getEvaluarSolicitud();
				}
			}
		}

		catch (SolicitudIndividuoException e) {
			e.printStackTrace();
		} catch (EsquemaIndividuoException e) {
			e.printStackTrace();
		}
	}


	public boolean getEvaluarSolicitud() {

		boolean estadoTitular = false;
		if (!aux.isEmpty()) {
			Iterator iterator = aux.iterator();
			while (iterator.hasNext()) {
				SolicitudIndividuo element = (SolicitudIndividuo) iterator.next();
				element.getTipoIndividuo();
				if (element.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(1)))
					estadoTitular = Convertidores.getBoolean(element.getAceptado());
			}
		}

		if (estadoTitular)
			return true;

		return false;
	}


	public String aceptar() {
		refresh();
		try {
			if (!estadosIndividuos.isEmpty()) {
				Iterator iterator = estadosIndividuos.iterator();
				while (iterator.hasNext()) {
					// popup.borrar();
					SolicitudesIndividuosWrapper element = (SolicitudesIndividuosWrapper) iterator.next();

					if (element.getSolicitudIndividuo().getIdSolicIndividuo().equals(solicitudIndividuo.getIdSolicIndividuo()) && element.getEstado()) {
						if (validar()) {
							element.getSolicitudIndividuo().setAceptado("S");
							evaluacionService.getSolicitudIndividuoService().actualizarSolicitudIndividuo(element.getSolicitudIndividuo());
							// cerrar();
						} else {
							element.getSolicitudIndividuo().setAceptado("N");
							evaluacionService.getSolicitudIndividuoService().actualizarSolicitudIndividuo(element.getSolicitudIndividuo());
							ScrollBean scroll = (ScrollBean) Session.getBean("ScrollBean");
							scroll.setHiddenScrollY(new Integer(0));
						}
					}
					if (element.getSolicitudIndividuo().getIdSolicIndividuo().equals(solicitudIndividuo.getIdSolicIndividuo())) {
						popup.setPopup(popup.ICONO_FALLA,
								"Atencion!!! Este individuo todavía tiene reglas Restrictivas no verificadas. Desea aceptarlo de igual manera?");
						break;
					}
				}
			}
		} catch (SolicitudIndividuoException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String aceptarPopup() {
		try {
			if (!estadosIndividuos.isEmpty()) {
				Iterator iterator = estadosIndividuos.iterator();
				while (iterator.hasNext()) {
					popup.borrar();
					SolicitudesIndividuosWrapper element = (SolicitudesIndividuosWrapper) iterator.next();

					if (element.getSolicitudIndividuo().getIdSolicIndividuo().equals(solicitudIndividuo.getIdSolicIndividuo())) {
						if (validar()) {
							element.getSolicitudIndividuo().setAceptado("S");
							evaluacionService.getSolicitudIndividuoService().actualizarSolicitudIndividuo(element.getSolicitudIndividuo());
							// cerrar();
						} else {
							element.getSolicitudIndividuo().setAceptado("N");
							evaluacionService.getSolicitudIndividuoService().actualizarSolicitudIndividuo(element.getSolicitudIndividuo());
							ScrollBean scroll = (ScrollBean) Session.getBean("ScrollBean");
							scroll.setHiddenScrollY(new Integer(0));
						}
						break;
					}
				}
			}
		} catch (SolicitudIndividuoException e) {
			e.printStackTrace();
		}

		return null;
	}


	public String rechazarPopup() {
		popup.borrar();
		try {
			if (!estadosIndividuos.isEmpty()) {
				Iterator iterator = estadosIndividuos.iterator();
				while (iterator.hasNext()) {
					SolicitudesIndividuosWrapper element = (SolicitudesIndividuosWrapper) iterator.next();

					if (element.getSolicitudIndividuo().getIdSolicIndividuo().equals(solicitudIndividuo.getIdSolicIndividuo())) {
						element.getSolicitudIndividuo().setAceptado("N");
						evaluacionService.getSolicitudIndividuoService().actualizarSolicitudIndividuo(element.getSolicitudIndividuo());
						break;
					}
				}
			}
		} catch (SolicitudIndividuoException e) {
			e.printStackTrace();
		}
		// cerrar();
		return null;
	}


	public String refresh() {
		popup.borrar();
		estadosIndividuos = new ArrayList();
		Iterator iter = reglasAutomaticas.iterator();
		while (iter.hasNext()) {
			WrappersReglas wrapercito = (WrappersReglas) iter.next();
			EsquemaIndividuo esqInd = wrapercito.getEsquemaIndividuo();
			try {
				evaluacionService.getEsquemaIndividuoService().actualizarEsquemaIndividuo(esqInd);
			} catch (EsquemaIndividuoException e) {
				e.printStackTrace();
			}
		}
		Iterator iterDos = reglasManuales.iterator();
		while (iterDos.hasNext()) {
			WrappersReglas wrapercito = (WrappersReglas) iterDos.next();
			EsquemaIndividuo esqInd = wrapercito.getEsquemaIndividuo();
			try {
				evaluacionService.getEsquemaIndividuoService().actualizarEsquemaIndividuo(esqInd);
			} catch (EsquemaIndividuoException e) {
				e.printStackTrace();
			}
		}

		Long id = solicitudIndividuo.getSolicitud().getIdSolicitud();
		cargarEstadosIndividuos(id, solicitudIndividuo.getIdSolicIndividuo());

		// getEvaluarSolicitud();
		return null;
	}


	public String rechazar() {
		aceptar = false;
		refresh();
		rechazarPopup();
		return null;
	}


	public String cancelarPopup() {
		popup.borrar();
		if (Session.getBean("ScrollBean") != null) {
			((ScrollBean) Session.getBean("ScrollBean")).setHiddenScrollY(new Integer(0));
		}
		return null;
	}


	public void cerrar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.opener.recargar();window.close();";
		AddResource addResource = AddResourceFactory.getInstance(facesContext);
		addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
	}
}