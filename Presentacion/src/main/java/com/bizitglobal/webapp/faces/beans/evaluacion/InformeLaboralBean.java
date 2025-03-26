package com.bizitglobal.webapp.faces.beans.evaluacion;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InformeLaboralException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoLaboralException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InformeLaboral;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Observo;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ObservoLaboral;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.general.exception.AutonomoException;
import com.bizitglobal.tarjetafiel.general.exception.OcupacionException;
import com.bizitglobal.tarjetafiel.general.exception.RubroException;
import com.bizitglobal.tarjetafiel.general.exception.TamEmpresaException;
import com.bizitglobal.tarjetafiel.general.exception.TipoDocumentoException;
import com.bizitglobal.tarjetafiel.general.negocio.Autonomo;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Ocupacion;
import com.bizitglobal.tarjetafiel.general.negocio.Rubro;
import com.bizitglobal.tarjetafiel.general.negocio.TamEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDocumento;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.Util.InformeLaboralUtil;
import com.bizitglobal.webapp.faces.beans.evaluacion.wrappers.WrappersObservo;
import com.bizitglobal.webapp.faces.beans.general.DomicilioBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class InformeLaboralBean extends BaseBean {
	private static final Logger log = Logger.getLogger(InformeLaboralBean.class);

	private int origen;

	/* Clases de negocio utilizadas en la pagina */
	private InformeLaboral laboral;
	private Solicitud solicitud;

	/* se utiliza para tener un nro de slicitud */
	private String nroSolicitud;

	/* Id del individuo a cargar */
	private Long idSolicitudIndividuo;

	/* Para el documento del individuo */
	private List listaDocumento;
	private List lstDocumento;
	private Long idDocumentoSeleccionado;

	/* Lsita para el Domicilio de la empresa */
	private List listaDomicilioEmpresa;

	/* para la ocupación del individuo */
	private List listaOcupacion;
	private List lstOcupacion;
	private Long idOcupacionSeleccionada;

	/* para el rubro de la empresa */
	private List listaRubro;
	private List lstRubro;
	private Long idRubroSeleccionado;

	/* para el tamaño de la empresa */
	private List listaTamanioEmpresa;
	private List lstTamanioEmpresa;
	private Long idTamanioEmpresaSeleccionado;

	/* para el autonomo */
	private List listaAutonomo;
	private List lstAutonomo;
	private Long idAutonomo;

	/* para la lisata de observo */
	private List listaObservo;
	private List lstObservo;

	/* para la lista de las observicaiones laborales */
	private List listaObservoLaboral;

	private Timestamp fechaRecepcion;

	private boolean corrigioRazonSocail;
	private boolean corrigioDomicilioEmpresa;

	Domicilio domicilioOriginal = null; // es para detectar si hubo cambios en alguna parte del domicilio mientras se edito.
	// usado por los informes laborales y particulares.
	String razonSocialOriginal = ""; // para detectar si se cambio el nombre de la empresa.


	public InformeLaboralBean() {
		super();
	}


	public void borrar() {
		borrarBaseBean();
		error.borrar();

		laboral = new InformeLaboral();

		solicitud = new Solicitud();

		listaAutonomo = new ArrayList();
		listaDocumento = new ArrayList();
		listaDomicilioEmpresa = new ArrayList();
		listaOcupacion = new ArrayList();
		listaRubro = new ArrayList();
		listaTamanioEmpresa = new ArrayList();
		listaObservo = new ArrayList();
		listaObservoLaboral = new ArrayList();

		lstAutonomo = new ArrayList();
		lstDocumento = new ArrayList();
		lstOcupacion = new ArrayList();
		lstRubro = new ArrayList();
		lstTamanioEmpresa = new ArrayList();
		lstObservo = new ArrayList();

		idAutonomo = new Long(0);
		idDocumentoSeleccionado = new Long(0);
		idOcupacionSeleccionada = new Long(0);
		idRubroSeleccionado = new Long(0);
		idTamanioEmpresaSeleccionado = new Long(0);

	}


	public String inicializar() {
		return null;
	}


	public boolean validar() {
		return false;
	}


	public void inicializarParametros(Map param) {

		error.borrar();
		if (param.get("idSolicitudTitular") == null) {
			if (param.get("idSolicitudGarante") == null) {
				error.agregar("Atributo invalido. Verifique que se este enviando el atributo \"idSolicitudTitular\" o el \"idSolicitudGarante\".");
			}
		}
		if (error.cantidad() == 0) {
			try {
				if (param.get("idSolicitudTitular") != null) {
					idSolicitudIndividuo = new Long(param.get("idSolicitudTitular").toString());
					inicializar(1, new Long(param.get("idSolicitudTitular").toString()));
				} else {
					idSolicitudIndividuo = new Long(param.get("idSolicitudGarante").toString());
					inicializar(2, new Long(param.get("idSolicitudGarante").toString()));
				}
			} catch (NumberFormatException e) {
				error.agregar("Error en el tipo de parametro pasado");
				e.printStackTrace();
			}
		}
	}


	public String inicializar(int origen, Long idSolicitudIndividuo) {
		borrar();

		this.origen = origen;
		// this. nroSolicitud = nroSolicitud;
		this.idSolicitudIndividuo = idSolicitudIndividuo;

		tituloCorto = "Informe Ambiental Laboral";
		tituloLargo = "Tarjeta Fiel - Evaluación";

		armarListas();
		cargarInformeLaboral();

		return null;
	}


	public Timestamp getFechaRecepcion() {
		return fechaRecepcion;
	}


	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}


	public Long getIdAutonomo() {
		return idAutonomo;
	}


	public void setIdAutonomo(Long idAutonomo) {
		this.idAutonomo = idAutonomo;
	}


	public List getListaAutonomo() {
		return listaAutonomo;
	}


	public void setListaAutonomo(List listaAutonomo) {
		this.listaAutonomo = listaAutonomo;
	}


	public List getLstAutonomo() {
		return lstAutonomo;
	}


	public void setLstAutonomo(List lstAutonomo) {
		this.lstAutonomo = lstAutonomo;
	}


	public Long getIdOcupacionSeleccionada() {
		return idOcupacionSeleccionada;
	}


	public void setIdOcupacionSeleccionada(Long idOcupacionSeleccionada) {
		this.idOcupacionSeleccionada = idOcupacionSeleccionada;
	}


	public Long getIdRubroSeleccionado() {
		return idRubroSeleccionado;
	}


	public void setIdRubroSeleccionado(Long idRubroSeleccionado) {
		this.idRubroSeleccionado = idRubroSeleccionado;
	}


	public Long getIdTamanioEmpresaSeleccionado() {
		return idTamanioEmpresaSeleccionado;
	}


	public void setIdTamanioEmpresaSeleccionado(Long idTamanioEmpresaSeleccionado) {
		this.idTamanioEmpresaSeleccionado = idTamanioEmpresaSeleccionado;
	}


	public List getListaOcupacion() {
		return listaOcupacion;
	}


	public void setListaOcupacion(List listaOcupacion) {
		this.listaOcupacion = listaOcupacion;
	}


	public List getListaRubro() {
		return listaRubro;
	}


	public void setListaRubro(List listaRubro) {
		this.listaRubro = listaRubro;
	}


	public List getListaTamanioEmpresa() {
		return listaTamanioEmpresa;
	}


	public void setListaTamanioEmpresa(List listaTamanioEmpresa) {
		this.listaTamanioEmpresa = listaTamanioEmpresa;
	}


	public List getLstOcupacion() {
		return lstOcupacion;
	}


	public void setLstOcupacion(List lstOcupacion) {
		this.lstOcupacion = lstOcupacion;
	}


	public List getLstRubro() {
		return lstRubro;
	}


	public void setLstRubro(List lstRubro) {
		this.lstRubro = lstRubro;
	}


	public List getLstTamanioEmpresa() {
		return lstTamanioEmpresa;
	}


	public void setLstTamanioEmpresa(List lstTamanioEmpresa) {
		this.lstTamanioEmpresa = lstTamanioEmpresa;
	}


	public List getListaDomicilioEmpresa() {
		return listaDomicilioEmpresa;
	}


	public void setListaDomicilioEmpresa(List listaDomicilioEmpresa) {
		this.listaDomicilioEmpresa = listaDomicilioEmpresa;
	}


	public Long getIdDocumentoSeleccionado() {
		return idDocumentoSeleccionado;
	}


	public void setIdDocumentoSeleccionado(Long idDocumentoSeleccionado) {
		this.idDocumentoSeleccionado = idDocumentoSeleccionado;
	}


	public List getListaDocumento() {
		return listaDocumento;
	}


	public void setListaDocumento(List listaDocumento) {
		this.listaDocumento = listaDocumento;
	}


	public List getLstDocumento() {
		return lstDocumento;
	}


	public void setLstDocumento(List lstDocumento) {
		this.lstDocumento = lstDocumento;
	}


	public Solicitud getSolicitud() {
		return solicitud;
	}


	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}


	public InformeLaboral getLaboral() {
		return laboral;
	}


	public void setLaboral(InformeLaboral laboral) {
		this.laboral = laboral;
	}


	public boolean isCorrigioDomicilioEmpresa() {
		return corrigioDomicilioEmpresa;
	}


	public void setCorrigioDomicilioEmpresa(boolean corrigioDomicilioEmpresa) {
		this.corrigioDomicilioEmpresa = corrigioDomicilioEmpresa;
	}


	public boolean isCorrigioRazonSocail() {
		return corrigioRazonSocail;
	}


	public void setCorrigioRazonSocail(boolean corrigioRazonSocail) {
		this.corrigioRazonSocail = corrigioRazonSocail;
	}


	public String getNroSolicitud() {
		return nroSolicitud;
	}


	public void setNroSolicitud(String nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}


	public List getListaObservo() {
		return listaObservo;
	}


	public void setListaObservo(List listaObservo) {
		this.listaObservo = listaObservo;
	}


	public List getLstObservo() {
		return lstObservo;
	}


	public void setLstObservo(List lstObservo) {
		this.lstObservo = lstObservo;
	}


	private void armarListas() {

		try {
			lstAutonomo = generalService.getAutonomoService().getAutonomo(new Filtro());
			listaAutonomo = InformeLaboralUtil.cargarAutonomos(lstAutonomo);

			lstDocumento = generalService.getTipoDocumentoService().getTipoDocumento(new Filtro());
			listaDocumento = InformeLaboralUtil.cargarTipoDocumento(lstDocumento);

			lstOcupacion = generalService.getOcupacionService().getOcupacion(new Filtro());
			listaOcupacion = InformeLaboralUtil.cargarOcupacion(lstOcupacion);

			lstRubro = generalService.getRubroService().getRubro(new Filtro());
			listaRubro = InformeLaboralUtil.cargarRubros(lstRubro);

			lstTamanioEmpresa = generalService.getTamanioEmpresaService().getTamEmpresa(new Filtro());
			listaTamanioEmpresa = InformeLaboralUtil.cargarTamanioEmpresa(lstTamanioEmpresa);

			lstObservo = evaluacionService.getObservoService().getObservo(new Filtro());
			if (!lstObservo.isEmpty()) {
				Iterator iterator = lstObservo.iterator();
				while (iterator.hasNext()) {
					Observo element = (Observo) iterator.next();

					WrappersObservo wrappersObservo = new WrappersObservo(element);
					listaObservo.add(wrappersObservo);
				}
			}
		} catch (AutonomoException e) {
			e.printStackTrace();
		} catch (TipoDocumentoException e) {
			e.printStackTrace();
		} catch (OcupacionException e) {
			e.printStackTrace();
		} catch (TamEmpresaException e) {
			e.printStackTrace();
		} catch (RubroException e) {
			e.printStackTrace();
		} catch (ObservoException e) {
			e.printStackTrace();
		}

	}


	private void cargarInformeLaboral() {

		try {
			// String nro = nroSolicitud.substring(0, 7);
			log.info("id. SolicitudIndividuo: " + idSolicitudIndividuo);

			Filtro filtro = new Filtro("solicitudIndividuo.idSolicitudIndividuo", Filtro.IGUAL, idSolicitudIndividuo);
			filtro.agregarfuncion("ORDER BY idInfoLaboral DESC");
			log.info("Filtro: " + filtro.toString());
			List aux = evaluacionService.getInformeLaboralService().getInformeLaboral(filtro);

			if (!aux.isEmpty()) {

				laboral = (InformeLaboral) aux.get(0);

				nroSolicitud = laboral.getSolicitudIndividuo().getSolicitud().getNroSolicitud() +
						laboral.getSolicitudIndividuo().getSolicitud().getDV();

				laboral.getAntiguedad();
				laboral.getSolicitudIndividuo();
				laboral.getSolicitudIndividuo().getIndividuoEvaluacion();
				laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad();
				laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad().getOcupacion();
				laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad().getSucEmpresa();
				laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad().getSucEmpresa().getEmpresa();
				razonSocialOriginal = laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad().getSucEmpresa().getEmpresa()
						.getRazonSocial();
				if (laboral.getAutonomo() != null)
					laboral.getAutonomo().getIdAutonomo();

				laboral.getCargo();
				laboral.getDomCorregido();

				if (laboral.getOcupacion() != null)
					laboral.getOcupacion().getIdOcupacion();

				if (laboral.getRubro() != null)
					laboral.getRubro().getIdRubro();

				if (laboral.getSucEmpresa() != null)
					laboral.getSucEmpresa().getIdSucEmpresa();

				if (laboral.getSucEmpresa().getDomicilio() != null) {
					laboral.getSucEmpresa().getDomicilio().getCalleNombre();
					laboral.getSucEmpresa().getDomicilio().getCalleNumero();
					laboral.getSucEmpresa().getDomicilio().getBarrio().getDescripcion();
					laboral.getSucEmpresa().getDomicilio().getLocalidad().getNombre();
					listaDomicilioEmpresa.add(laboral.getSucEmpresa().getDomicilio());
					domicilioOriginal = new Domicilio(laboral.getSucEmpresa().getDomicilio());
				}

				if (laboral.getSucEmpresa().getEmpresa().getRubro() != null)
					laboral.getSucEmpresa().getEmpresa().getRubro().getIdRubro();

				if (laboral.getSucEmpresa().getEmpresa().getTamEmpresa() != null)
					laboral.getSucEmpresa().getEmpresa().getTamEmpresa().getIdTamanioEmp();

				if (laboral.getSucEmpresa().getEmpresa().getRubro() != null)
					idRubroSeleccionado = laboral.getSucEmpresa().getEmpresa().getRubro().getIdRubro();

				if (laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad().getOcupacion().getIdOcupacion() != null) {
					log.info("laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad().getOcupacion().getIdOcupacion(): " +
							laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad().getOcupacion().getIdOcupacion());
					idOcupacionSeleccionada = laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad().getOcupacion().getIdOcupacion();
				}

				if (laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad().getSucEmpresa().getEmpresa().getTamEmpresa()
						.getIdTamanioEmp() != null)
					idTamanioEmpresaSeleccionado = laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad().getSucEmpresa()
							.getEmpresa().getTamEmpresa().getIdTamanioEmp();

				// if(laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad().getSucEmpresa().getAutonomo().getIdAutonomo() != null)
				// idAutonomo = laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getActividad().getSucEmpresa().getAutonomo().getIdAutonomo();

				if (laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getTipoDocumento() != null)
					idDocumentoSeleccionado = laboral.getSolicitudIndividuo().getIndividuoEvaluacion().getTipoDocumento().getIdTipoDocumento();

				if (laboral.getSolicitudIndividuo().getSolicitud() != null) {
					laboral.getSolicitudIndividuo().getSolicitud().getTimestamp();
					laboral.getSolicitudIndividuo().getSolicitud().getNroSolicitud();
					fechaRecepcion = laboral.getSolicitudIndividuo().getSolicitud().getFechaRecepcion();
				}
				if (laboral.getVerificador() != null) {
					laboral.getVerificador().getIdVerificador();
					laboral.getVerificador().getApellido();
					laboral.getVerificador().getNombre();
				}

				if (laboral.getDomCorregido() != null)
					corrigioDomicilioEmpresa = Convertidores.getBoolean(laboral.getDomCorregido());
				if (laboral.getRsCorregida() != null)
					corrigioRazonSocail = Convertidores.getBoolean(laboral.getRsCorregida());

				listaObservoLaboral = Convertidores.setToList(laboral.getEvaObsLaborales());
				if (!listaObservoLaboral.isEmpty()) {
					Iterator iterator = listaObservoLaboral.iterator();
					while (iterator.hasNext()) {
						ObservoLaboral element = (ObservoLaboral) iterator.next();

						if (!listaObservo.isEmpty()) {
							Iterator iter = listaObservo.iterator();
							while (iter.hasNext()) {
								WrappersObservo elem = (WrappersObservo) iter.next();

								if (elem.getObservo().getIdObservo().equals(element.getObservo().getIdObservo()))
									elem.setSeleccionado(true);
							}
						}
					}
				}
			}
			else {
				error.agregar(Error.AMINFORMELABORAL_NO_ENCONTRADO);
			}
		} catch (InformeLaboralException e) {
			e.printStackTrace();
		}
	}


	public String guardarInformeLaboral() {

		try {
			if (!this.laboral.getSucEmpresa().getDomicilio().sonIdenticos(domicilioOriginal)) {
				setCorrigioDomicilioEmpresa(true);
			}
			if (razonSocialOriginal.compareTo(laboral.getSucEmpresa().getEmpresa().getRazonSocial()) != 0) {
				corrigioRazonSocail = true;
			}
			laboral.setDomCorregido(Convertidores.getSorN(corrigioDomicilioEmpresa));
			laboral.setRsCorregida(Convertidores.getSorN(corrigioRazonSocail));

			if (idAutonomo != null && !idAutonomo.equals(new Long(0))) {
				if (!lstAutonomo.isEmpty()) {
					Iterator iterator = lstAutonomo.iterator();
					while (iterator.hasNext()) {
						Autonomo element = (Autonomo) iterator.next();

						if (element.getIdAutonomo().equals(idAutonomo))
							laboral.setAutonomo(element);
					}
				}
			}

			if (idDocumentoSeleccionado != null && idDocumentoSeleccionado.equals(new Long(0))) {
				if (!lstDocumento.isEmpty()) {
					Iterator iterator = lstDocumento.iterator();
					while (iterator.hasNext()) {
						TipoDocumento element = (TipoDocumento) iterator.next();

						if (element.getIdTipoDocumento().equals(idDocumentoSeleccionado))
							laboral.getSolicitudIndividuo().getIndividuoEvaluacion().setTipoDocumento(element);
					}
				}
			}

			if (idOcupacionSeleccionada != null && !idOcupacionSeleccionada.equals(new Long(0))) {
				if (!lstOcupacion.isEmpty()) {
					Iterator iterator = lstOcupacion.iterator();
					while (iterator.hasNext()) {
						Ocupacion element = (Ocupacion) iterator.next();

						if (element.getIdOcupacion().equals(idOcupacionSeleccionada))
							laboral.setOcupacion(element);
					}
				}
			}

			if (idRubroSeleccionado != null && !idRubroSeleccionado.equals(new Long(0))) {
				if (!lstRubro.isEmpty()) {
					Iterator iterator = lstRubro.iterator();
					while (iterator.hasNext()) {
						Rubro element = (Rubro) iterator.next();

						if (element.getIdRubro().equals(idRubroSeleccionado))
							laboral.setRubro(element);
					}
				}
			}

			if (idTamanioEmpresaSeleccionado != null && !idTamanioEmpresaSeleccionado.equals(new Long(0))) {
				if (!lstTamanioEmpresa.isEmpty()) {
					Iterator iterator = lstTamanioEmpresa.iterator();
					while (iterator.hasNext()) {
						TamEmpresa element = (TamEmpresa) iterator.next();

						if (element.getIdTamanioEmp().equals(idTamanioEmpresaSeleccionado))
							laboral.setTamanioEmpresa(element);
					}
				}
			}

			if (!listaObservoLaboral.isEmpty()) {
				Iterator iterator = listaObservoLaboral.iterator();
				while (iterator.hasNext()) {
					ObservoLaboral element = (ObservoLaboral) iterator.next();

					evaluacionService.getObservoLaboralService().borrarObservoLaboral(element);
				}
			}

			if (!listaObservo.isEmpty()) {
				laboral.setEvaObsLaborales(new HashSet());
				Iterator iterator = listaObservo.iterator();
				while (iterator.hasNext()) {
					WrappersObservo element = (WrappersObservo) iterator.next();

					if (element.getSeleccionado()) {
						ObservoLaboral observoLaboral = new ObservoLaboral();
						observoLaboral.setInformeLaboral(laboral);
						observoLaboral.setObservo(element.getObservo());
						laboral.getEvaObsLaborales().add(observoLaboral);
					}
				}
			}
			laboral.setFechaRecepcion(new Timestamp(Calendar.getInstance().getTime().getTime()));
			evaluacionService.getInformeLaboralService().actualizarInformeLaboral(laboral);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar();window.close();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		} catch (InformeLaboralException e) {
			e.printStackTrace();
		} catch (ObservoLaboralException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public String editarDomicilio() {
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		if (!listaDomicilioEmpresa.isEmpty()) {
			Domicilio domicilio = (Domicilio) listaDomicilioEmpresa.get(0);
			if (domicilio != null) {
				bean.inicializar(DomicilioBean.INFORME_LABORAL, domicilio);
				String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
				path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
				ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
			} else {
				error.agregar("El individuo no posee un domicilio laboral asociado.");
			}
		} else {
			error.agregar("El individuo no posee un domicilio laboral asociado.");
		}
		return null;
	}


	public String salir() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.close();";
		ejecutarJavaScript(javaScriptText);

		return null;
	}
}
