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
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InfoParticularVehiculoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InformeParticularException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ViviendaEstadoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoDomicilio;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InfoParticularVehiculo;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InformeParticular;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudIndividuo;
import com.bizitglobal.tarjetafiel.general.exception.PropViviendaException;
import com.bizitglobal.tarjetafiel.general.exception.TipoViviendaException;
import com.bizitglobal.tarjetafiel.general.exception.TipoZonaException;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.Util.InformeParticularUtil;
import com.bizitglobal.webapp.faces.beans.general.DomicilioBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


/**
 * @author Administrator
 * 
 */
@SuppressWarnings({"rawtypes","unchecked","unused"})
public class InformeParticularBean extends BaseBean {
	private static final Logger log = Logger.getLogger(InformeParticularBean.class);

	private int origen;
	private static final int TITULAR = 1;
	private static final int GARANTE = 2;

	private InformeParticular informeParticular;
	private Solicitud solicitud;
	private IndividuoEvaluacion individuo;
	private IndividuoEvaluacion titular;
	private IndividuoEvaluacion garante;
	private InfoParticularVehiculo informeVehiculo;
	private IndividuoDomicilio individuoDomicilio;

	/* Lista tipo de documento */
	private List listaTipoDocumento;
	private List lstTipoDocumento;

	/* Lista de domicilio */
	private List lstDomicilio;
	private List listaDomicilio;

	/* Lista de tipo vivienda */
	private List listaTipoVivienda;
	private List lstTipoVivienda;

	/* Lista de Propiedades */
	private List listaPropietarioVivienda;
	private List lstPropietarioVivienda;

	/* Lista Estado Vivienda */
	private List listaEstadoVivienda;
	private List lstEstadoVivienda;

	/* Lista Zonas */
	private List listaZona;
	private List lstZona;

	/* Id del tipo de documento seleccionado */
	private Long idTipoDocumentoSeleccionado;

	/* id tipo viviedna seleccionado */
	private Long idTipoViviendaSelecciondo;

	/* id Propietario Vivienda seleccionado */
	private Long idPropietarioVivienda;

	/* id Estado vivienda seleccionado */
	private Long idEstadoViviendaSeleccionado;

	/* id zona seleccionada */
	private Long idZona;

	/* Nro de Solicitud */
	private String nroSolicitud;
	/* Id del individuo a cargar */
	private Long idSolicitudIndividuo;

	private boolean correccionNombre;
	private boolean correccionDomicilio;

	private boolean alta = false;

	/* Lista informe Particular Vehiculo */
	private List vehiculos;

	Domicilio domicilioOriginal = null; // es para detectar si hubo cambios en alguna parte del domicilio mientras se edito.
	// usado por los informes laborales y particulares.
	String apellidoOriginal;
	String apellidoMaternoOriginal;
	String nombreOriginal;


	public InformeParticularBean() {
		super();
	}


	public void borrar() {
		borrarBaseBean();
		individuo = null;
		titular = null;
		garante = null;

		solicitud = new Solicitud();
		informeVehiculo = new InfoParticularVehiculo();

		listaTipoDocumento = new ArrayList();
		listaEstadoVivienda = new ArrayList();
		listaPropietarioVivienda = new ArrayList();
		listaTipoVivienda = new ArrayList();
		listaZona = new ArrayList();
		lstTipoDocumento = new ArrayList();
		lstEstadoVivienda = new ArrayList();
		lstPropietarioVivienda = new ArrayList();
		lstTipoVivienda = new ArrayList();
		lstZona = new ArrayList();

		idTipoDocumentoSeleccionado = new Long(0);
		idEstadoViviendaSeleccionado = new Long(0);
		idPropietarioVivienda = new Long(0);
		idTipoViviendaSelecciondo = new Long(0);

		lstDomicilio = new ArrayList();
		listaDomicilio = new ArrayList();

		correccionDomicilio = false;
		correccionNombre = false;
		informeParticular = null;

		apellidoOriginal = "";
		apellidoMaternoOriginal = "";
		nombreOriginal = "";
	}


	public String inicializar() {
		return null;
	}


	public void inicializarParametros(Map param) {

		// if(param.get("origen") == null || param.get("nroSolicitud") == null){
		// error.agregar("El atributos recibidos no son valido. Verifique que se este enviando los atributo con nombres correctos. \"origen\", \"nroSolicitud\".");
		// }
		// else{
		// Integer paraOrigen = new Integer(param.get("origen").toString());
		// String paraNroSol = (String)param.get("nroSolicitud");
		//
		// inicializar(paraOrigen.intValue(), paraNroSol);
		// }

		error.borrar();
		if (param.get("idSolicitudIndividuo") == null) {
			error.agregar("Atributo invalido. Verifique que se este enviando el atributo \"idSolicitudIndividuo\". ");
		}
		// if(param.get("origen") == null){
		// error.agregar("Atributo invalido. Verifique que se este enviando el atributo \"idSolicitudIndividuo\". ");
		// }
		if (!error.hayErrores()) {
			try {
				if (param.get("idSolicitudIndividuo") != null) {
					inicializar(new Long(param.get("idSolicitudIndividuo").toString()));
				}
			} catch (NumberFormatException e) {
				error.agregar("Error en el tipo de parametro pasado");
				e.printStackTrace();
			}
		}
	}


	// public String inicializar(int origen, String nroSolicitud){
	// borrar();
	// borrarBaseBean();
	// error.borrar();
	//
	// this.origen = origen;
	// this.nroSolicitud = nroSolicitud;
	//
	// tituloLargo = "Tarjeta Fiel - Evaluación";
	// tituloCorto = "Informe Ambiental Particular";
	// armarListas();
	//
	// cargarInformeParticular();
	//
	// return null;
	// }

	public String inicializar(Long idSolicitudIndividuo) {
		borrar();

		// this.origen = origen;
		// this.nroSolicitud = nroSolicitud;
		this.idSolicitudIndividuo = idSolicitudIndividuo;

		tituloLargo = "Tarjeta Fiel - Evaluación";
		tituloCorto = "Informe Ambiental Particular";
		armarListas();

		cargarInformeParticular();

		return null;
	}


	private void armarListas() {

		try {
			lstTipoDocumento = generalService.getTipoDocumentoDao().listarTodos(new Filtro());
			listaTipoDocumento = InformeParticularUtil.cargarTipoDocumento(lstTipoDocumento);

			lstEstadoVivienda = evaluacionService.getViviendaEstadoService().getViviendaEstado(new Filtro());
			listaEstadoVivienda = InformeParticularUtil.cargarEstadoVivienda(lstEstadoVivienda);

			lstPropietarioVivienda = generalService.getPropietarioViviendaService().getPropietarioVivienda(new Filtro());
			listaPropietarioVivienda = InformeParticularUtil.cargarPropietarioVivienda(lstPropietarioVivienda);

			lstTipoVivienda = generalService.getTipoViviendaDao().listarTodos();
			listaTipoVivienda = InformeParticularUtil.cargarTipoVivienda(lstTipoVivienda);

			lstZona = generalService.getTipoZonaService().getTipoZona(new Filtro());
			listaZona = InformeParticularUtil.cargarZona(lstZona);

		} catch (ViviendaEstadoException e) {
			e.printStackTrace();
		} catch (PropViviendaException e) {
			e.printStackTrace();
		} catch (TipoZonaException e) {
			e.printStackTrace();
		}
	}


	public Long getIdEstadoViviendaSeleccionado() {
		return idEstadoViviendaSeleccionado;
	}


	public void setIdEstadoViviendaSeleccionado(Long idEstadoViviendaSeleccionado) {
		this.idEstadoViviendaSeleccionado = idEstadoViviendaSeleccionado;
	}


	public Long getIdPropietarioVivienda() {
		return idPropietarioVivienda;
	}


	public void setIdPropietarioVivienda(Long idPropietarioVivienda) {
		this.idPropietarioVivienda = idPropietarioVivienda;
	}


	public Long getIdTipoViviendaSelecciondo() {
		return idTipoViviendaSelecciondo;
	}


	public void setIdTipoViviendaSelecciondo(Long idTipoViviendaSelecciondo) {
		this.idTipoViviendaSelecciondo = idTipoViviendaSelecciondo;
	}


	public List getListaEstadoVivienda() {
		return listaEstadoVivienda;
	}


	public void setListaEstadoVivienda(List listaEstadoVivienda) {
		this.listaEstadoVivienda = listaEstadoVivienda;
	}


	public List getListaPropietarioVivienda() {
		return listaPropietarioVivienda;
	}


	public void setListaPropietarioVivienda(List listaPropietarioVivienda) {
		this.listaPropietarioVivienda = listaPropietarioVivienda;
	}


	public List getListaTipoVivienda() {
		return listaTipoVivienda;
	}


	public void setListaTipoVivienda(List listaTipoVivienda) {
		this.listaTipoVivienda = listaTipoVivienda;
	}


	public List getLstEstadoVivienda() {
		return lstEstadoVivienda;
	}


	public void setLstEstadoVivienda(List lstEstadoVivienda) {
		this.lstEstadoVivienda = lstEstadoVivienda;
	}


	public List getLstPropietarioVivienda() {
		return lstPropietarioVivienda;
	}


	public void setLstPropietarioVivienda(List lstPropietarioVivienda) {
		this.lstPropietarioVivienda = lstPropietarioVivienda;
	}


	public List getLstTipoVivienda() {
		return lstTipoVivienda;
	}


	public void setLstTipoVivienda(List lstTipoVivienda) {
		this.lstTipoVivienda = lstTipoVivienda;
	}


	public boolean isCorreccionDomicilio() {
		return correccionDomicilio;
	}


	public void setCorreccionDomicilio(boolean correccionDomicilio) {
		this.correccionDomicilio = correccionDomicilio;
	}


	public boolean isCorreccionNombre() {
		return correccionNombre;
	}


	public void setCorreccionNombre(boolean correccionNombre) {
		this.correccionNombre = correccionNombre;
	}


	public boolean validar() {
		return false;
	}


	public List getListaDomicilio() {
		return listaDomicilio;
	}


	public void setListaDomicilio(List listaDomicilio) {
		this.listaDomicilio = listaDomicilio;
	}


	public List getLstDomicilio() {
		return lstDomicilio;
	}


	public void setLstDomicilio(List lstDomicilio) {
		this.lstDomicilio = lstDomicilio;
	}


	public IndividuoEvaluacion getIndividuo() {
		return individuo;
	}


	public void setIndividuo(IndividuoEvaluacion individuo) {
		this.individuo = individuo;
	}


	public InformeParticular getInformeParticular() {
		return informeParticular;
	}


	public void setInformeParticular(InformeParticular informeParticular) {
		this.informeParticular = informeParticular;
	}


	public Solicitud getSolicitud() {
		return solicitud;
	}


	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}


	public Long getIdTipoDocumentoSeleccionado() {
		return idTipoDocumentoSeleccionado;
	}


	public void setIdTipoDocumentoSeleccionado(Long idTipoDocumentoSeleccionado) {
		this.idTipoDocumentoSeleccionado = idTipoDocumentoSeleccionado;
	}


	public List getListaTipoDocumento() {
		return listaTipoDocumento;
	}


	public void setListaTipoDocumento(List listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}


	public List getLstTipoDocumento() {
		return lstTipoDocumento;
	}


	public void setLstTipoDocumento(List lstTipoDocumento) {
		this.lstTipoDocumento = lstTipoDocumento;
	}


	public String getNroSolicitud() {
		return nroSolicitud;
	}


	public void setNroSolicitud(String nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}


	public List getListaZona() {
		return listaZona;
	}


	public void setListaZona(List listaZona) {
		this.listaZona = listaZona;
	}


	public List getLstZona() {
		return lstZona;
	}


	public void setLstZona(List lstZona) {
		this.lstZona = lstZona;
	}


	public Long getIdZona() {
		return idZona;
	}


	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}


	public InfoParticularVehiculo getInformeVehiculo() {
		return informeVehiculo;
	}


	public void setInformeVehiculo(InfoParticularVehiculo informeVehiculo) {
		this.informeVehiculo = informeVehiculo;
	}


	private String cargarSolicitud() {
		// List solicitudesIndividuos = new ArrayList();
		try {
			// Filtro filtro = new Filtro("nroSolicitud",Filtro.IGUAL,nroSolicitud.substring(0,7));
			SolicitudIndividuo solicitudIndividuo =
					evaluacionService.getSolicitudIndividuoService().leerSolicitudIndividuo(idSolicitudIndividuo);
			origen = solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().intValue();
			solicitud = solicitudIndividuo.getSolicitud();
			if (solicitud != null) {
				nroSolicitud = solicitud.getNroSolicitud() + solicitud.getDV();
				if (solicitud.getFechaRecepcion() == null)
					solicitud.setFechaRecepcion(new Timestamp(Calendar.getInstance().getTime().getTime()));
				solicitud.getEstados();
				solicitud.getPromotor();
				// solicitudesIndividuos = Convertidores.setToList(solicitud.getSolicIndividuos());
				// if(!solicitudesIndividuos.isEmpty()){
				// Iterator iterator = solicitudesIndividuos.iterator();
				// while (iterator.hasNext()) {
				// SolicitudIndividuo element = (SolicitudIndividuo) iterator.next();
				switch (origen) {
				case TITULAR:
					if (solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(1)) && solicitudIndividuo.getIndividuo() != null) {
						titular = solicitudIndividuo.getIndividuo();
						apellidoOriginal = titular.getApellido();
						apellidoMaternoOriginal = titular.getApellidoMaterno();
						nombreOriginal = titular.getNombres();
						idTipoDocumentoSeleccionado = titular.getTipoDocumento().getIdTipoDocumento();
						if (titular.getDomicilio() != null) {
							titular.getDomicilio().getCalleNombre();
							titular.getDomicilio().getCalleNumero();
							titular.getDomicilio().getBarrio().getDescripcion();
							titular.getDomicilio().getLocalidad().getNombre();
							lstDomicilio.add(titular.getDomicilio());
							domicilioOriginal = new Domicilio(titular.getDomicilio());
						}
						individuo = titular;
					}
					break;
				case GARANTE:
					if (solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(2)) && solicitudIndividuo.getIndividuo() != null) {
						garante = solicitudIndividuo.getIndividuo();
						apellidoOriginal = garante.getApellido();
						apellidoMaternoOriginal = garante.getApellidoMaterno();
						nombreOriginal = garante.getNombres();
						idTipoDocumentoSeleccionado = garante.getTipoDocumento().getIdTipoDocumento();
						if (garante.getDomicilio() != null) {
							garante.getDomicilio().getCalleNombre();
							garante.getDomicilio().getCalleNumero();
							garante.getDomicilio().getBarrio().getDescripcion();
							garante.getDomicilio().getLocalidad().getNombre();
							lstDomicilio.add(garante.getDomicilio());
							domicilioOriginal = new Domicilio(garante.getDomicilio());
						}
						individuo = garante;
					}
					break;
				}
			}
			// }
			// }
		} catch (SolicitudIndividuoException e) {
			e.printStackTrace();
		}
		// catch (IndividuoEvaluacionException e) {
		// e.printStackTrace();
		// }

		return null;
	}


	/*
	 * public void armarDomicilio(IndividuoEvaluacion element){
	 * 
	 * if(element.getDomicilio().getIdDomicilio() != null &&)
	 * 
	 * listaDomicilio = Convertidores.setToList(element.getDomicilios()); if(!listaDomicilio.isEmpty()){ Iterator iterator =
	 * listaDomicilio.iterator(); while (iterator.hasNext()) { individuoDomicilio = (IndividuoDomicilio) iterator.next(); log.info("Domicilio: " +
	 * individuoDomicilio.getDomicilio().toString()); log.info("Tipo Domicilio: " + individuoDomicilio.getDomicilio().getTipoDomicilio());
	 * 
	 * if(individuoDomicilio.getDomicilio().getTipoDomicilio().getIdTipoDomicilio().equals(new Long(1)) &&
	 * individuoDomicilio.getIndividuoEvaluacion().getIdIndividuo().equals(element.getIdIndividuo())){ individuoDomicilio.getDomicilio();
	 * individuoDomicilio.getDomicilio().getCalleNombre(); individuoDomicilio.getDomicilio().getCalleNumero();
	 * individuoDomicilio.getDomicilio().getBarrio().getDescripcion(); individuoDomicilio.getDomicilio().getLocalidad().getNombre();
	 * lstDomicilio.add(individuoDomicilio); } } } }
	 */

	public String guardar() {

		try {
			if (!this.getIndividuo().getDomicilio().sonIdenticos(domicilioOriginal)) {
				setCorreccionDomicilio(true);
			}
			if (apellidoOriginal.compareTo(individuo.getApellido()) != 0 || apellidoMaternoOriginal.compareTo(individuo.getApellidoMaterno()) != 0
					|| nombreOriginal.compareTo(individuo.getNombres()) != 0) {
				correccionNombre = true;
			}
			informeParticular.setDomCorregido(Convertidores.getSorN(correccionDomicilio));
			informeParticular.setNombreCorregido(Convertidores.getSorN(correccionNombre));
			informeParticular.setFechaRecepcion(new Timestamp(Calendar.getInstance().getTime().getTime()));
			if (correccionNombre || correccionDomicilio)
				evaluacionService.getIndividuoEvaluacionService().actualizarIndividuo(individuo);

			if (idEstadoViviendaSeleccionado != null && !idEstadoViviendaSeleccionado.equals(new Long(0)))
				informeParticular.setViviendaEstado(evaluacionService.getViviendaEstadoService().leerViviendaEstado(idEstadoViviendaSeleccionado));

			if (idPropietarioVivienda != null && !idPropietarioVivienda.equals(new Long(0)))
				informeParticular.setPropietarioVivienda(generalService.getPropietarioViviendaService()
						.leerPropietarioVivienda(idPropietarioVivienda));

			if (idTipoViviendaSelecciondo != null && !idTipoViviendaSelecciondo.equals(new Long(0)))
				informeParticular.setTipoVivienda(generalService.getTipoViviendaService().leerTipoVivienda(idTipoViviendaSelecciondo));

			if (idZona != null && !idZona.equals(new Long(0)))
				informeParticular.setTipoZona(generalService.getTipoZonaService().leerTipoZona(idZona));

			informeParticular.setVehiculos(new HashSet());

			evaluacionService.getInformeParticularService().actualizarInformeParticular(informeParticular);

			if (!informeVehiculo.getVehiculo().getMarca().equals("") && !informeVehiculo.getVehiculo().getAnio().equals("")
					|| !informeVehiculo.getVehiculo().getModelo().equals("") || !informeVehiculo.getVehiculo().getPatente().equals("")) {
				informeVehiculo.setInformeParticular(informeParticular);
				if (alta) {
					evaluacionService.getInfoParticularVehiculoService().grabarInfoParticularVehiculo(informeVehiculo);
				} else {
					evaluacionService.getInfoParticularVehiculoService().actualizarInfoParticularVehiculo(informeVehiculo);
				}

			}

			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar();window.close();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);

		} catch (InformeParticularException e) {
			e.printStackTrace();
		} catch (IndividuoEvaluacionException e) {
			e.printStackTrace();
		} catch (ViviendaEstadoException e) {
			e.printStackTrace();
		} catch (PropViviendaException e) {
			e.printStackTrace();
		} catch (TipoZonaException e) {
			e.printStackTrace();
		} catch (TipoViviendaException e) {
			e.printStackTrace();
		} catch (InfoParticularVehiculoException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	private void cargarInformeParticular() {

		try {
			// String nro = nroSolicitud.substring(0, 7);
			// log.info("Nro. Solicitud: " + nro);

			Filtro filtro = new Filtro("solicitudIndividuo.idSolicitudIndividuo", Filtro.IGUAL, idSolicitudIndividuo);
			filtro.agregarfuncion("ORDER BY idInfoParticular DESC");
			List aux = evaluacionService.getInformeParticularService().getInformeParticular(filtro);

			if (!aux.isEmpty()) {
				informeParticular = (InformeParticular) aux.get(0);
				informeParticular.getSolicitudIndividuo();
				informeParticular.getVerificador();
				informeParticular.getVerificador().getApellido();
				informeParticular.getVerificador().getNombre();
				informeParticular.getVerificador().getIdVerificador();

				if (informeParticular.getViviendaEstado() != null)
					idEstadoViviendaSeleccionado = informeParticular.getViviendaEstado().getIdVivEstado();

				if (informeParticular.getPropietarioVivienda() != null)
					idPropietarioVivienda = informeParticular.getPropietarioVivienda().getIdPropVivienda();

				if (informeParticular.getTipoVivienda() != null)
					idTipoViviendaSelecciondo = informeParticular.getTipoVivienda().getIdTipoVivienda();

				if (informeParticular.getTipoZona() != null)
					idZona = informeParticular.getTipoZona().getIdTipoZona();

				if (informeParticular.getDomCorregido() != null)
					correccionDomicilio = Convertidores.getBoolean(informeParticular.getDomCorregido());
				else
					correccionDomicilio = false;

				if (informeParticular.getNombreCorregido() != null)
					correccionNombre = Convertidores.getBoolean(informeParticular.getNombreCorregido());
				else
					correccionNombre = false;

				if (informeParticular.getVehiculos() != null && informeParticular.getVehiculos().size() > 0) {
					vehiculos = new ArrayList();
					vehiculos = Convertidores.setToList(informeParticular.getVehiculos());
					alta = false;
					cargarInformeParticularVehiculos();
				} else {
					alta = true;
				}

				cargarSolicitud();
			}
			else
				error.agregar(Error.AMINFORMEPARTICULAR_NO_ENCONTRADO);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (InformeParticularException e) {
			e.printStackTrace();
		}
	}


	private void cargarInformeParticularVehiculos() {

		if (!vehiculos.isEmpty()) {
			Iterator iterator = vehiculos.iterator();
			while (iterator.hasNext()) {
				informeVehiculo = (InfoParticularVehiculo) iterator.next();
				informeVehiculo.getVehiculo().getAnio();
				informeVehiculo.getVehiculo().getMarca();
				informeVehiculo.getVehiculo().getModelo();
				informeVehiculo.getVehiculo().getPatente();
			}
		}
	}


	/*
	 * Este metodo se utiliza para agregar un domicilio al individuo.
	 */
	/*
	 * public String agregarDomicilioPopup() { log.info("Ir a agrega nuevo domicilio al titular!!!");
	 * 
	 * DomicilioBean bean = (DomicilioBean)Session.getBean("DomicilioBean");
	 * 
	 * bean.inicializar(DomicilioBean.INFORME_PARTICULAR, new Domicilio());
	 * 
	 * String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath(); path +=
	 * "/tarjetafiel/general/domicilio/domicilioPopup.jsf"; ejecutarJavaScript("popup('"+ path +"',750,400), 'titlebar=no';");
	 * 
	 * return null; }
	 */

	/*
	 * Este metodo se utiliza para poder editar un domicilio, se captura el id del domicilio q se desea editar, luego se busca dentro del array de lso
	 * domicilios, una vez q se encuentra se manda al bean de domicilios, donde se cargara y estara lsito para ser editado.
	 */
	public String editarDomicilio() {
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		if (!lstDomicilio.isEmpty()) {
			Domicilio domicilio = (Domicilio) lstDomicilio.get(0);
			if (domicilio != null) {
				bean.inicializar(DomicilioBean.INFORME_PARTICULAR, domicilio);
				String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
				path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
				ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
			} else {
				error.agregar("El individuo no posee un domicilio asociado.");
			}
		} else {
			error.agregar("El individuo no posee un domicilio asociado.");
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
