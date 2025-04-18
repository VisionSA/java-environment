package com.bizitglobal.webapp.faces.beans.transacciones.popup;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.calendar.HtmlInputCalendar;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Archivo;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ActividadEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.DiaPagoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TclienteException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ActividadEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Bancos;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.DiaPago;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Digital;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Educacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Emails;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoDomicilio;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoVehiculo;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tarjeta;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tcliente;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Telefonos;
import com.bizitglobal.tarjetafiel.general.exception.EmpresaException;
import com.bizitglobal.tarjetafiel.general.exception.OcupacionException;
import com.bizitglobal.tarjetafiel.general.exception.TipoDocumentoException;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Email;
import com.bizitglobal.tarjetafiel.general.negocio.Empresa;
import com.bizitglobal.tarjetafiel.general.negocio.EstadoCivil;
import com.bizitglobal.tarjetafiel.general.negocio.Ocupacion;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistemaDetalle;
import com.bizitglobal.tarjetafiel.general.negocio.Profesion;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmail;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.SucTelefono;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDigital;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDocumento;
import com.bizitglobal.tarjetafiel.general.negocio.Vehiculo;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuitNoValidoException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuitValido;
import com.bizitglobal.tarjetafiel.transacciones.exception.CliIndividuoException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliIndividuo;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecioVersion;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.ProcesarCobroExterno;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.webapp.faces.beans.evaluacion.Util.IndividuoEvaluacionUtil;
import com.bizitglobal.webapp.faces.beans.evaluacion.wrappers.WrapperFile;
import com.bizitglobal.webapp.faces.beans.evaluacion.wrappers.WrappersTcliente;
import com.bizitglobal.webapp.faces.beans.general.DomicilioBean;
import com.bizitglobal.webapp.faces.beans.general.EmpresaBean;
import com.bizitglobal.webapp.faces.beans.general.TelefonoBean;
import com.bizitglobal.webapp.faces.beans.transacciones.ClienteBean;
import com.bizitglobal.webapp.faces.beans.transacciones.CodComercioBean;
import com.bizitglobal.webapp.faces.beans.transacciones.HttpPostMultipart;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;


@SuppressWarnings({"rawtypes","unchecked"})
public class IndividuoPopupBean extends BaseBean {
	private static final Logger log = Logger.getLogger(IndividuoPopupBean.class);
	public static final int CLIENTE = 1;
	public static final int COLABORADOR = 2;
	public static final int COD_COMERCIO = 3;
	private boolean editDatos;
	private boolean editDomicilio;
	private boolean editTelefono;
	private boolean editEmail;
	private boolean editFamilia;
	private boolean editActividad;
	private boolean editFacturacion;
	private boolean editFinanciero;
	private boolean editPropiedades;
	private boolean editDigitales;
	private boolean vengoDeComercio;
	private int origen;
	private String focoHidden;
	private CuitValido cuitValido;
	private boolean empresaExistente;
	private boolean refrescarAct;
	private IndividuoEvaluacion individuoEvaluacion;
	private ActividadEvaluacion actividad;

	// objetos para dar de alta los individuos;
	private boolean altaIndividuo = true;
	// Booleanos para el manejo de los paneles a mostrar en el formulario
	private boolean verDatos = true;
	private boolean verDomicilio = true;
	private boolean verActividad = true;
	private boolean botonAdjuntar;

	private String nota_cred;

	private List listAuxOcupacion;
	private List listTipoDni;
	private List listTipoSexo;
	// private Long idTipoSexoSeleccionado;
	private List listTelefono;
	private List listOcupacion;
	private List listEstadoCivil;
	private List listAuxEstadoCivil;
	private List listEducacion;
	private List listAuxEducacion;
	private List listProfesion;
	private List listAuxProfesion;
	private List listSucursales;
	private List listSucEmp;
	private List listDomicilioPago;
	private List listDiaPago;
	private List listAuxBancos;
	private List listBancos;
	private List listTipoCtas;
	private List listAuxTipoCtas;
	private List listDomicilioInmueble;
	private List listTipoDigital;
	private List listAuxTipoDigital;
	private List listTipoDocumentos;
	private List listaTcliente;
	private HtmlInputCalendar fechaIngreso = new HtmlInputCalendar();
	private HtmlSelectOneMenu ocupacionHtml = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu sucursalHtml = new HtmlSelectOneMenu();
	/* Se utiliza para despues poder validar el mail */
	private String email;
	private String antiguedad;
	private Empresa empresa;
	private SucTelefono sucTelefono;
	private Domicilio domSucursal;
	private Bancos bancos;
	private Tarjeta tarjeta;
	private IndividuoVehiculo individuoVehiculo;
	/* Se utiliza para la fecha de nacimeinto del individuo */
	// private Date fechaNacimiento;
	/* Se utiliza para la fecha de ingreso laboral */
	private Date ingreso;
	private boolean individuoCargado;
	private boolean edicion;
	private boolean botonEmpresa;
	/* Se utiliza para habilitar la lista de Profeciones */
	private boolean habilitarProfesion;
	/* Se utiliza para habilitar la lista de Sucursales */
	private boolean habilitarSucursal;
	// en el caso de grabar una actividad se podria pasar en el constructor la sucEmpresa;
	private SucEmpresa sucEmpresa;
	/* se utiliza para poder buscar la empresa en la cual trabaja */
	private String nroCuit;
	/* Se utiliza para armar la direccion de la sucursal */
	private String direccionSucursal;
	/* Se utiliza para el nro de teledono de la sucursal */
	private String telefono;
	private HtmlSelectOneMenu educacionHtml = new HtmlSelectOneMenu();
	private Long idSucursalSeleccionado;
	private Long idDomicilioPagoSeleccionado;
	private HtmlSelectOneMenu diasPagos = new HtmlSelectOneMenu();
	private Long idDiaPagoSeleccionado;
	private Integer diaPagoSeleccionadoInfo; // Se usa para mostrar al operador el dia de pago que estaba seleccionado.
	private Long idDiaPagoSeleccionadoAnterior; // Se usa para comparar el diaPago que tiene asignado con el seleccionado.
	private HtmlSelectOneMenu bancoSeleccionado;
	private Long idBcoSeleccionado;
	private Long idTipoCuentaSeleccionado;
	private Long idBancoSeleccionado;
	private Long idTipoDocSeleccionado;
	/* Se utiliza para el CBU */
	private boolean esCBU;
	/* nro de cbu */
	private String nroCBU;
	private UploadedFile uploadedFile;
	private Digital docDigital;

	private static Logger logger = Logger.getLogger(ProcesarCobroExterno.class);


	public IndividuoPopupBean() {
		super();
		// cargarItems();
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE INDIVIDUOPOPUP
	 ************************************************************************/

	public String inicializar() {
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return null;
	}


	public void inicializarParametros(Map param) {
		inicializar();
		Long idIndividuo = null;
		try {
			this.nota_cred = (String) param.get("nota_cred");
			Object origenObj = param.get("origen");
			Object idIndividuoObj = param.get("idIndividuo");
			if (origenObj != null) {
				origen = new Integer(origenObj.toString()).intValue();
				switch (origen) {
				case CLIENTE:
					editDatos = Boolean.parseBoolean((String) param.get("editDatos"));
					editDomicilio = Boolean.parseBoolean((String) param.get("editDomicilio"));
					editTelefono = Boolean.parseBoolean((String) param.get("editTelefono"));
					editEmail = Boolean.parseBoolean((String) param.get("editEmail"));
					editFamilia = Boolean.parseBoolean((String) param.get("editFamilia"));
					/* @I13 */editActividad = Boolean.parseBoolean((String) param.get("editActividad"));
					/* @F13 */editFacturacion = Boolean.parseBoolean((String) param.get("editFacturacion"));
					// editFacturacion = false;
					editFinanciero = Boolean.parseBoolean((String) param.get("editFinanciero"));
					editPropiedades = Boolean.parseBoolean((String) param.get("editPropiedades"));
					editDigitales = Boolean.parseBoolean((String) param.get("editDigitales"));
					break;
				case COD_COMERCIO:
					editDatos = false;
					editDomicilio = false;
					editTelefono = false;
					editEmail = false;
					editFamilia = false;
					editActividad = false;
					editFacturacion = true;
					editFinanciero = false;
					editPropiedades = false;
					editDigitales = false;
					break;
				default:
					break;
				}
			}
			else
				error.agregar("Falta parametro -> \"origen\"");
			if (idIndividuoObj != null)
				idIndividuo = new Long(idIndividuoObj.toString());
			else
				error.agregar("Falta parametro -> \"idIndividuo\"");
		} catch (NumberFormatException e) {
			error.agregar("Error al leer los parametros.");
			e.printStackTrace();
		}
		if (!error.hayErrores()) {
			try {
				individuoEvaluacion = evaluacionService.getIndividuoEvaluacionService().leerIndividuo(idIndividuo);
				if (individuoEvaluacion != null) {
					alta = false;
					cargarListas();
					if (individuoEvaluacion.getDomicilio() != null) {
						individuoEvaluacion.getDomicilio().getCalleNombre();
						if (individuoEvaluacion.getDomicilio().getTipoDomicilio() != null)
							individuoEvaluacion.getDomicilio().getTipoDomicilio().getDescripcion();
						if (individuoEvaluacion.getDomicilio().getPropVivienda() != null)
							individuoEvaluacion.getDomicilio().getPropVivienda().getDescripcion();
						if (individuoEvaluacion.getDomicilio().getTipoVivienda() != null)
							individuoEvaluacion.getDomicilio().getTipoVivienda().getDescripcion();
						individuoEvaluacion.getDomicilio().getBarrio().getLocalidad().getPartido().getProvincia().getPais().getIdPais();
						individuoEvaluacion.getDomicilio().getLocalidad().getNombre();
						listDomicilioPago.add(IndividuoPopupUtil.armarDomPago(individuoEvaluacion.getDomicilio()));
					}
					individuoEvaluacion.getTipoDocumento().getIdTipoDocumento();
					actividad = individuoEvaluacion.getActividad();
					if (actividad == null)
						actividad = new ActividadEvaluacion();
					if (actividad.getOcupacion() == null)
						actividad.setOcupacion(new Ocupacion());
					else
						actividad.getOcupacion().getDescripcion();
					if (actividad.getSucEmpresa() != null
							&& actividad.getSucEmpresa().getEmpresa() != null
							&& actividad.getSucEmpresa().getEmpresa().getCuit() != null) {
						nroCuit = actividad.getSucEmpresa().getEmpresa().getCuit().toString();
						buscarEmpresa();
						idSucursalSeleccionado = actividad.getSucEmpresa().getIdSucEmpresa();
						sucursalHtml.setValue(idSucursalSeleccionado);
						cambioSucursal(null);
					}
					if (actividad.getFechaIngreso() != null) {
						ingreso = actividad.getFechaIngreso();
						fechaIngreso.setValue(ingreso);
						calcularAntiguedad(null);
					}
					individuoEvaluacion.setActividad(actividad);
					if (individuoEvaluacion.getDomicilioDoc() != null) {
						idDomicilioPagoSeleccionado = individuoEvaluacion.getDomicilioDoc().getIdDomicilio();
						diasPagos.setValue(idDomicilioPagoSeleccionado);
						cambiarDiasDePago(null);
						if (individuoEvaluacion.getDiaPago() != null) {
							idDiaPagoSeleccionado = individuoEvaluacion.getDiaPago().getIdDiaPago();
						}
					}
					if (individuoEvaluacion.getDiaPago() != null)
						idDiaPagoSeleccionadoAnterior = individuoEvaluacion.getDiaPago().getIdDiaPago();
					// Solo para mostrar informacion al operador.
					if (individuoEvaluacion.getDiaPago() != null) {
						diaPagoSeleccionadoInfo = individuoEvaluacion.getDiaPago().getDiaPago();
					}
					if (individuoEvaluacion.getBancos() != null) {
						if (!individuoEvaluacion.getBancos().isEmpty()) {
							bancos = (Bancos) Convertidores.setToList(individuoEvaluacion.getBancos()).get(0);
							// nombreBanco = ba.getBanco().getDescripcion();
							idBcoSeleccionado = bancos.getBanco().getIdBanco();

						}
					}
					if (individuoEvaluacion.getTipoCuenta() != null) {
						idTipoCuentaSeleccionado = Long.valueOf(individuoEvaluacion.getTipoCuenta());
					}
				}
			} catch (IndividuoEvaluacionException e) {
				error.agregar("Error en la carga del individuo.");
				e.printStackTrace();
			} catch (Exception e) {
				error.agregar("Error en la carga del individuo.");
				e.printStackTrace();
			}
			if (individuoEvaluacion.getMails() != null && individuoEvaluacion.getMails().size() > 0) {
				List listaMails = Convertidores.setToList(individuoEvaluacion.getMails());
				Emails ma = (Emails) listaMails.get(0);
				email = ma.getEmail().getEmail();
			}
			if (individuoEvaluacion.getEducacion() != null
					&& individuoEvaluacion.getEducacion().getIdEducacion() != null
					&& individuoEvaluacion.getEducacion().getIdEducacion().equals(new Long(8))) {
				habilitarProfesion = false;
			}
			if (individuoEvaluacion.getDocAdjuntos() != null) {
				
				List arrayDocAdjuntos = Convertidores.setToList(individuoEvaluacion.getDocAdjuntos());
				
				Collections.sort(arrayDocAdjuntos, new Comparator() 
				 {
					@Override
					public int compare(Object o1, Object o2) {
						return ((Digital) o1).getTimestamp().compareTo(((Digital) o2).getTimestamp());
					}
				});
				
				for (int i = (arrayDocAdjuntos.size() - 1); i > -1; i--) {
					Digital di = (Digital) arrayDocAdjuntos.get(i);
//				Iterator iDocTit = individuoEvaluacion.getDocAdjuntos().iterator();
//				while (iDocTit.hasNext()) {
//					Digital di = (Digital) iDocTit.next();
					WrapperFile wrapin = new WrapperFile();
					wrapin.setDescripcion(di.getDescripcion());
					wrapin.setIdDocumentoAdjunto(di.getIdDigital());
					wrapin.setIdTipoDoc(di.getTipoDigital().getIdTipoDigital());
					wrapin.setIdWrappers(new Long(di.hashCode()));
					wrapin.setNombreArchivo(di.getUrl());
					wrapin.setPath(di.getUrl());
					wrapin.setTimestamp(di.getTimestamp());
					wrapin.setListaDocumentos(listAuxTipoDigital);
					TipoDigital ti = null;
					Iterator iterTipos = listAuxTipoDigital.iterator();
					while (iterTipos.hasNext()) {
						TipoDigital tip = (TipoDigital) iterTipos.next();
						if (tip.getIdTipoDigital().equals(di.getTipoDigital().getIdTipoDigital())) {
							ti = tip;
							break;
						}
					}
					wrapin.setTipoDocumento(ti.getDescripcion());
					listTipoDocumentos.add(wrapin);
				}
			} else {
				listTipoDocumentos = new ArrayList();
			}
			armarListaTelefono(individuoEvaluacion);
			edicion = true;
		}
	}


	/**
	 * individuoEvaluacion = el individuo a editar o a dar de alta origen = el IndividuoPopup se comportará diferente segun el origen (que es quien lo
	 * llama, clientes, colaboradores o codComercio) sucEmpresa puede ser null, solo debe ser diferente de null si origen en COD_COMERCIO
	 * */
	public String inicializar(IndividuoEvaluacion individuoEvaluacion, int origen, SucEmpresa sucEmpresa) {
		if (origen != COD_COMERCIO || (origen == COD_COMERCIO && (sucEmpresa != null || sucEmpresa.getIdSucEmpresa() != null))) {
			borrar();
			this.individuoEvaluacion = individuoEvaluacion;
			this.origen = origen;
			this.sucEmpresa = sucEmpresa;
			if (individuoEvaluacion != null && individuoEvaluacion.getId() != null) {
				edicion = true;
				alta = false;
				organizarPaneles();
				try {
					leerIndividuoEvaluacion();
				} catch (Exception e) {
					log.info(e.getMessage());
					e.printStackTrace();
				}
			} else {
				edicion = false;
				editDatos = true;
				organizarPaneles();
				verDatos = true;
				actividad = new ActividadEvaluacion();
			}
			cargarListas();
			if (Session.getBean("ScrollBean") != null) {
				ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
				bean.setHiddenScrollY(new Integer(0));
			}
			// String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			// path += "/tarjetafiel/transacciones/popup/IndividuoPopup.jsf";
			// ejecutarJavaScript("popup('" + path + "',900,700), 'titlebar=no';");
			Session.redirect("/tarjetafiel/transacciones/popup/IndividuoPopup.jsf");
		} else {
			log.info("Mal llamado el metodo inicializar. Los parámetros no son correctos");
		}
		return null;
	}


	public void organizarPaneles() {
		if (!edicion) {
			if (individuoEvaluacion == null || (individuoEvaluacion != null && individuoEvaluacion.getIdIndividuo() == null)) {
				individuoCargado = false;
			}
			if (individuoCargado) {
				verDatos = true;
				verDomicilio = true;
				verActividad = false;
				if (origen == COD_COMERCIO) {
					verActividad = true;
				}
			} else {
				verDatos = false;
				verDomicilio = false;
				verActividad = false;
			}
		} else {
			switch (origen) {
			case CLIENTE:
				verDatos = true;
				verDomicilio = true;
				verActividad = false;
				break;
			case COLABORADOR:
				verDatos = true;
				verDomicilio = true;
				verActividad = false;
				break;
			case COD_COMERCIO:
				log.info("Edicion de un individuo desde codComercio");
				verDatos = true;
				verDomicilio = true;
				verActividad = true;
				break;
			}
			individuoCargado = true;
		}

	}


	public void calcularAntiguedad(ValueChangeEvent event) {
		ingreso = (Date) fechaIngreso.getValue();
		antiguedad = IndividuoEvaluacionUtil.devolverCantidadAnios(ingreso) + "";
	}


	public String cancelarDesdePopup() {
		// si falta definir algo desde que se cancela el popup va aca
		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		if (validar()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar(); window.close();";
			ejecutarJavaScript(javaScriptText);
		}
	}


	public String habilitarNuevaCarga() {
		log.info("Se habilitará nueva");
		borrar();
		individuoCargado = false;
		return null;
	}


	public String grabar() {

		return "";
	}


	/*
	 * Este metodo se utiliza para agregar un domicilio al individuo.
	 */
	public String agregarDomicilioPopup() {
		log.info("Ir a agrega nuevo domicilio al IndividuoPopup!!!");
		focoHidden = "agregarDomicilioLink";
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		if (individuoEvaluacion.getDomicilio() == null) {
			individuoEvaluacion.setDomicilio(new Domicilio());
		}
		bean.inicializar(DomicilioBean.INDIVIDUO_POPUP, individuoEvaluacion.getDomicilio());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	/* Agregado para compensar los errores provocados por la migracion */
	public String eliminarDomicilio() {
		error.borrar();
		if (individuoEvaluacion.getDomicilio() != null
				&& individuoEvaluacion.getDomicilio().getIdDomicilio() != null
				&& individuoEvaluacion.getDomicilio().getIdDomicilio().equals(new Long(49000))) {
			individuoEvaluacion.setDomicilio(new Domicilio());
		} else {
			error.agregar("Solo se puede borrar el domicilio MIGRACION");
		}
		return null;
	}


	public String darAltaNuevoIndividuo() {
		error.borrar();
		// presenta el panel de alta individuo
		listTipoDni = new ArrayList();
		listTipoSexo = new ArrayList();
		listTelefono = new ArrayList();
		listOcupacion = new ArrayList();
		cargarListas();
		individuoEvaluacion = new IndividuoEvaluacion();
		actividad = new ActividadEvaluacion(new Long(0));
		actividad.setOcupacion(new Ocupacion(new Long(0)));
		ingreso = null;
		antiguedad = "";
		// fechaNacimiento = null;
		email = "";
		altaIndividuo = true;
		verDatos = false;
		verDomicilio = false;
		verActividad = false;
		individuoCargado = false;
		return null;
	}


	/**
	 * A continuacion, los metodos que trabajan con los telefono del individuo
	 * */
	/*
	 * * Este metodo elimina, agrega y edita los telefonos del set de individuos, a partir de la listTelefonos
	 */
	public void grabarTelefonos() {
		if (individuoEvaluacion != null) {
			if (individuoEvaluacion.getTelefonos() == null)
				individuoEvaluacion.setTelefonos(new HashSet());
			if (listTelefono != null) {
				Iterator iterTelefonos = listTelefono.iterator();
				while (iterTelefonos.hasNext()) {
					Telefonos telef = (Telefonos) iterTelefonos.next();
					if (telef.getTelefono().getIdTelefono().equals(new Long(telef.getTelefono().hashCode()))) {
						telef.setIdTelefono(null);
						telef.setIndividuoEvaluacion(individuoEvaluacion);
						telef.getTelefono().setIdTelefono(null);
						telef.getTelefono().setFechaAlta(new Date());
						telef.getTelefono().setOperador(Session.getOperador());
						individuoEvaluacion.getTelefonos().add(telef);
					}
				}
			} else {
				individuoEvaluacion.setTelefonos(null);
			}
		}
	}


	/*
	 * este metodo se utiliza para agregar un telefono al individuo
	 */
	public String agregarTelefono() {
		log.info("Ejecutando ==> agragrTelefono()");
		focoHidden = "agregarTelefonoLink";
		TelefonoBean bean = (TelefonoBean) Session.getBean("TelefonoBean");
		bean.inicializar(TelefonoBean.INDIVIDUO_POPUP, new Telefono());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");
		return null;
	}


	/*
	 * Este metodo se utiliza para poder eliminar un telefono de la lista de telefonos
	 */
	public String eliminarTelefono() {
		log.info("Ejecutando ==> eliminarTelefono()");
		Long idTelefono = new Long(Session.getRequestParameter("idTelefono"));
		IndividuoEvaluacionUtil.eliminarTelefono(listTelefono, idTelefono);
		IndividuoEvaluacionUtil.eliminarTelefonoSet(individuoEvaluacion.getTelefonos(), idTelefono);
		return null;
	}


	/*
	 * Este metodo se utiliza para editar el telefono.
	 */
	public String editarTelefono() {
		log.info("Ejecutando ==> editarTelefono()");

		Long idTelefono = new Long(Session.getRequestParameter("idTelEdi"));
		log.info("id a buscar: " + idTelefono);

		TelefonoBean bean = (TelefonoBean) Session.getBean("TelefonoBean");
		Telefono telefono = IndividuoEvaluacionUtil.buscarTelefono(listTelefono, idTelefono);
		bean.inicializar(TelefonoBean.INDIVIDUO_POPUP, telefono);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");

		return null;
	}


	/*
	 * este metodo se utiliza para cargar las diferentes listas.
	 */
	private void cargarListas() {

		try {
			listAuxBancos = generalService.getBancoDao().listarTodos();
			listBancos.clear();
			listBancos.add(new SelectItem(new Long(0), "Seleccionar Banco"));
			listBancos.addAll(Util.cargarSelectItem(listAuxBancos));
			listTipoDni = IndividuoEvaluacionUtil.cargarTipoDocumeno(generalService.getTipoDocumentoDao());

			listTipoSexo = IndividuoEvaluacionUtil.cargarSexo();
			listAuxOcupacion = generalService.getOcupacionService().getOcupacion(new Filtro());
			listOcupacion.clear();
			listOcupacion.add(new SelectItem(new Long(0), "Seleccionar Ocupación"));
			listOcupacion.addAll(Util.cargarSelectItem(listAuxOcupacion));
			listAuxEstadoCivil = generalService.getEstadoCivilDao().listarTodos(new Filtro());
			listEstadoCivil.clear();
			listEstadoCivil.add(new SelectItem(new Long(0), "Seleccionar Estado Civil"));
			listEstadoCivil.addAll(Util.cargarSelectItem(listAuxEstadoCivil));
			listAuxEducacion = evaluacionService.getEducacionDao().listarTodos(new Filtro());
			listEducacion.clear();
			listEducacion.add(new SelectItem(new Long(0), "Seleccionar Educación"));
			listEducacion.addAll(Util.cargarSelectItem(listAuxEducacion));
			listAuxProfesion = generalService.getProfesionDao().listarTodos(new Filtro());
			listProfesion.clear();
			listProfesion.add(new SelectItem(new Long(0), "Seleccionar Profesión"));
			listProfesion.addAll(Util.cargarSelectItem(listAuxProfesion));
			listDomicilioPago.add(new SelectItem(new Long(0), "Seleccionar Domicilio Pago"));
			listDiaPago.add(new SelectItem(new Long(0), "Seleccionar Dia Pago"));
			listAuxTipoCtas = generalService.getTipoCuentaBancoDao().listarTodos();
			listTipoCtas.clear();
			listTipoCtas.add(new SelectItem(new Long(0), "Seleccionar Tipo Cuenta"));
			listTipoCtas.addAll(Util.cargarSelectItem((listAuxTipoCtas)));
			listAuxTipoDigital = generalService.getTipoDigitalDao().listarTodos(new Filtro());
			listTipoDigital.clear();
			listTipoDigital.add(new SelectItem(new Long(0), "Tipo Documento Digital"));
			listTipoDigital.addAll(Util.cargarSelectItem((listAuxTipoDigital)));
		} catch (OcupacionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String grabarIndividuo() {
		// codigo para grabar el nuevo individuo y agregarlo a la lista de individuos disponibles
		error.borrar();
		if (validar()) {
			if (individuoEvaluacion.getIdIndividuo() == null) {
				individuoEvaluacion.setBancos(null);
				individuoEvaluacion.setCbu(null);
				individuoEvaluacion.setDiaPago(null);
				individuoEvaluacion.setDocAdjuntos(null);
				individuoEvaluacion.setDomicilioDoc(null);
				individuoEvaluacion.setDomicilios(null);
				individuoEvaluacion.setEducacion(null);
				individuoEvaluacion.setEstadoCivil(null);
				individuoEvaluacion.setHijosCargo(null);
				individuoEvaluacion.setIdOperador(Session.getOperador().getCodigo());
				individuoEvaluacion.setNroCuenta(null);
				individuoEvaluacion.setProfesion(null);
				individuoEvaluacion.setTarjetas(null);
				individuoEvaluacion.setTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				individuoEvaluacion.setVehiculos(null);
				individuoEvaluacion.setVinculo(null);
				individuoEvaluacion.setActividad(null);
				individuoEvaluacion.getDomicilio().setTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				individuoEvaluacion.getDomicilio().setOperador(Session.getOperador());
				individuoEvaluacion.getDomicilio().setIdDomicilio(null);
				log.info("el barrio es: " + individuoEvaluacion.getDomicilio().getBarrio().getDescripcion()
						+ individuoEvaluacion.getDomicilio().getBarrio().getId());
				log.info("el timestamp es " + individuoEvaluacion.getDomicilio().getTimestamp());
				log.info("el operador es: " + individuoEvaluacion.getDomicilio().getOperador().getClave());
			}
			if (ingreso != null)
				individuoEvaluacion.setFechaNacimiento(new Timestamp(ingreso.getTime()));
			armarIndividuo();
			try {
				if (individuoEvaluacion.getIdIndividuo() == null) {

					evaluacionService.getIndividuoEvaluacionService().grabarIndividuo(individuoEvaluacion);
					altaIndividuo = false;
				} else {
					evaluacionService.getIndividuoEvaluacionService().actualizarIndividuo(individuoEvaluacion);
					altaIndividuo = false;
				}
				switch (origen) {
				case CLIENTE:
					ClienteBean clienteBean = (ClienteBean) Session.getBean("ClienteBean");
					clienteBean.setIndividuo(individuoEvaluacion);
					clienteBean.buscarClienteRelacionadoAIndividuo();
					break;
				case COLABORADOR:
					break;
				case COD_COMERCIO:

					CodComercioBean codComercioBean = (CodComercioBean) Session.getBean("CodComercioBean");
					if (actividad.getId() == null) {
						actividad.setCuil(individuoEvaluacion.getCuil());
						actividad.setSucEmpresa(sucEmpresa);
						evaluacionService.getActividadEvaluacionService().grabarActividad(actividad);
						codComercioBean.agregarIndiDisponible(individuoEvaluacion, actividad);
					} else {
						actividad.setCuil(individuoEvaluacion.getCuil());
						evaluacionService.getActividadEvaluacionService().actualizarActividad(actividad);
					}
					// codComercioBean.mostrarPopupDeCarga();

					if (validar()) {
						FacesContext facesContext = FacesContext.getCurrentInstance();
						String javaScriptText = "window.opener.recargar(); window.close();";
						ejecutarJavaScript(javaScriptText);
					}
					Session.redirect("/tarjetafiel/transacciones/popup/amAutorizadosResponsablesComerciosPopup.jsf");
					break;
				}
			} catch (IndividuoEvaluacionException e) {
				error.agregar("Hubo un error al intentar guardar el individuo");
				e.printStackTrace();
			} catch (Exception e2) {
				error.agregar("Ya existe un individuo con ese cuit en la base de datos.");
				log.info("Ya existe individuo con ese cuit");
				darAltaNuevoIndividuo();
				error.agregar("Ya existe un individuo con ese cuit en la base de datos.");
				e2.printStackTrace();
			}
		}
		return null;
	}


	public boolean validarCasosOrigen() {
		boolean result = true;
		switch (origen) {
		case CLIENTE:
			// verificar si no existe ya como cliente (o sea asociado a un cliente) si ya existe retornar false
			break;
		case COLABORADOR:
			break;
		case COD_COMERCIO:
			// controlar que no este en la lista ya de individuos relacionados
			// si esta retornar false
			break;
		}
		return result;
	}


	public String habilitarCarga() {
		if (validarCuit(individuoEvaluacion.getCuil())) {
			try {
				verDatos = true;
				verDomicilio = true;
				if (origen == COD_COMERCIO)
					verActividad = true;
				try {
					List individuos = evaluacionService.getIndividuoEvaluacionService().getIndividuo(
							new Filtro("cuil", Filtro.LIKEXS, individuoEvaluacion.getCuil()));
					if (!individuos.isEmpty()) {
						IndividuoEvaluacion indi = (IndividuoEvaluacion) individuos.get(0);
						if (validarCasosOrigen()) {
							indi.getNombres();
							indi.getApellido();
							// indi.getActividad().getCargo();
							indi.getNroDocumento();
							indi.getCuil();
							if (indi.getDomicilio() != null && indi.getDomicilio().getBarrio() != null) {
								indi.getDomicilio().getBarrio().getDescripcion();
								indi.getDomicilio().getOperador();
								indi.getDomicilio().getTimestamp();
							}
							indi.getTipoDocumento().getDescripcion();
							Iterator ite = indi.getTelefonos().iterator();
							while (ite.hasNext()) {
								Telefonos tel = (Telefonos) ite.next();
								tel.getTelefono().getNroTlefono();
								tel.getTelefono().getTipo().getDescripcion();
							}
							Iterator iterDeMails = indi.getMails().iterator();
							while (iterDeMails.hasNext()) {
								Emails tel = (Emails) ite.next();
								tel.getEmail().getEmail();
								tel.getEmail().getDescripcion();
							}
							individuoEvaluacion = indi;
							leerIndividuoEvaluacion();
						}
					}
				} catch (IndividuoEvaluacionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				individuoCargado = true;
				if (origen == COD_COMERCIO) {
					actividad = new ActividadEvaluacion();
					actividad.setIdActividad(new Long(0));
					actividad.setOcupacion(new Ocupacion(new Long(0)));
				}
			} catch (Exception e2) {
				verDatos = false;
				verDomicilio = false;
				error.agregar(e2.getMessage());
			}
		}
		return null;
	}


	public void leerIndividuoEvaluacion() throws Exception {
		// log.info(individuoEvaluacion);
		Domicilio dom = individuoEvaluacion.getDomicilio();
		if (dom != null) {
			individuoEvaluacion.getDomicilio().getTipoDomicilio();
			individuoEvaluacion.getDomicilio().getBarrio();
			individuoEvaluacion.getDomicilio().getLocalidad();
			if (individuoEvaluacion.getDomicilio().getLocalidad() != null) {
				if (individuoEvaluacion.getDomicilio().getLocalidad().getPartido() != null)
					individuoEvaluacion.getDomicilio().getLocalidad().getPartido().getIdPartido();
			}
		} else {
			dom = new Domicilio();
			individuoEvaluacion.setDomicilio(dom);
		}
		if (origen == COD_COMERCIO) {
			log.info("recuperamos la actividad");
			cargarActividad();
		}
		log.info(individuoEvaluacion);
		cargarIndividuo(individuoEvaluacion);
		armarListaTelefono(individuoEvaluacion);
	}


	/*
	 * En este metodo, se arma todo el individuo con los datos que se han traido cuando se levantaron los datos de la base de datos.
	 */
	private void cargarIndividuo(IndividuoEvaluacion elemento) throws Exception {

		if (elemento.getTipoDocumento() != null) {
			log.info("Id Tipo Doc: " + elemento.getTipoDocumento().getIdTipoDocumento());
			log.info("Descipción: " + elemento.getTipoDocumento().getDescripcion());
		} else {
			elemento.setTipoDocumento(new TipoDocumento());
			elemento.getTipoDocumento().setIdTipoDocumento(new Long(0));
		}
		// if (elemento.getFechaNacimiento() != null) {
		// fechaNacimiento = new Date(elemento.getFechaNacimiento().getTime());
		// }
		// if (elemento.getSexo() != null) {
		// if (elemento.getSexo().equals("M"))
		// idTipoSexoSeleccionado = new Long(2);
		// else
		// idTipoSexoSeleccionado = new Long(1);
		// } else {
		// idTipoSexoSeleccionado = new Long(0);
		// }
		if (elemento.getMails() != null && elemento.getMails().size() > 0) {
			List listaMails = Convertidores.setToList(elemento.getMails());
			Emails ma = (Emails) listaMails.get(0);
			email = ma.getEmail().getEmail();
		}
		if (origen == COD_COMERCIO) {
			cargarActividad();
		}
	}


	private void armarListaTelefono(IndividuoEvaluacion elemento) {

		List auxTelefono = Convertidores.setToList(elemento.getTelefonos());
		/* Acá armamos la lista de telefonos del individuo */
		listTelefono.clear();
		if (!auxTelefono.isEmpty()) {
			Iterator iterator = auxTelefono.iterator();
			while (iterator.hasNext()) {
				Telefonos elemenTelefonos = (Telefonos) iterator.next();
				if (elemenTelefonos.getIndividuoEvaluacion().getIdIndividuo().equals(elemento.getIdIndividuo())) {
					elemenTelefonos.getTelefono().getNroTlefono();
					elemenTelefonos.getTelefono().getEsCelular();
					elemenTelefonos.getTelefono().getEsFax();
					elemenTelefonos.getTelefono().getTipo().getDescripcion();
					elemenTelefonos.getTelefono().getHorarios();
					elemenTelefonos.getTelefono().getDescripcion();
					listTelefono.add(elemenTelefonos);
				}
			}
		}

	}


	public void grabarMail() {
		if (individuoEvaluacion.getMails() != null && individuoEvaluacion.getMails().size() > 0) {
			// aqui editamos el mail
			List listaDeEmail = Convertidores.setToList(individuoEvaluacion.getMails());
			Emails edit = (Emails) listaDeEmail.get(0);
			edit.getEmail().setEmail(email);
			individuoEvaluacion.setMails(new HashSet());
			individuoEvaluacion.getMails().add(edit);
		} else {
			if (email == null || email.compareTo("") == 0) {
				individuoEvaluacion.setMails(null);
			} else {
				/* @F35 */Email mai = new Email(null, email, Session.getOperador(), null);
				Emails mails = new Emails();
				mails.setIdEmails(null);
				mails.setIndividuoEvaluacion(individuoEvaluacion);
				/* @F45 */mai.setFechaAlta(new Date());
				mails.setEmail(mai);
				individuoEvaluacion.setMails(new HashSet());
				individuoEvaluacion.getMails().add(mails);
			}
		}
	}


	/*
	 * este metodo se utiliza para armar el individuoEvaluacion.
	 */
	private void armarIndividuo() {
		if (individuoEvaluacion.getDomicilio() != null) {
			if (individuoEvaluacion.getDomicilio().equals(new Domicilio())) {
				individuoEvaluacion.setDomicilio(null);
			} else {
				if (individuoEvaluacion.getDomicilio() != null && individuoEvaluacion.getDomicilio().getIdDomicilio().equals(new Long(-1))) {
					individuoEvaluacion.getDomicilio().setIdDomicilio(null);
				}
			}
		}
		// switch (idTipoSexoSeleccionado.intValue()) {
		// case 0:
		// individuoEvaluacion.setSexo(null);
		// break;
		//
		// case 1:
		// individuoEvaluacion.setSexo("F");
		// break;
		//
		// case 2:
		// individuoEvaluacion.setSexo("M");
		// break;
		// }
		try {
			individuoEvaluacion.setTipoDocumento(generalService.getTipoDocumentoService().leerTipoDocumento(
					individuoEvaluacion.getTipoDocumento().getIdTipoDocumento()));
		} catch (TipoDocumentoException e) {
			e.printStackTrace();
		}
		// if (fechaNacimiento != null && !fechaNacimiento.equals(new Date(0))) {
		// individuoEvaluacion.setFechaNacimiento(new Timestamp(fechaNacimiento.getTime()));
		// } else {
		// individuoEvaluacion.setFechaNacimiento(null);
		// }
		if (origen == COD_COMERCIO) {
			armarActividad();
		} else {
			if (individuoEvaluacion.getId() == null) {
				individuoEvaluacion.setActividad(null);
			}

		}
		if (individuoEvaluacion.getActividad() != null) {
			if (individuoEvaluacion.getActividad().getOcupacion() != null
					&& individuoEvaluacion.getActividad().getOcupacion().getIdOcupacion() != null
					&& individuoEvaluacion.getActividad().getOcupacion().getIdOcupacion().equals(new Long(0))) {
				individuoEvaluacion.getActividad().setOcupacion(null);
			}
		}
		grabarMail();
		grabarTelefonos();
		armarDocumentosAdj();
		armarDiaPago();
		individuoEvaluacion.setTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
		log.info(individuoEvaluacion.toString());
	}


	public void armarDocumentosAdj() {

		if (!listTipoDocumentos.isEmpty()) {
			if (individuoEvaluacion.getDocAdjuntos() == null)
				individuoEvaluacion.setDocAdjuntos(new HashSet());
			Iterator ite = listTipoDocumentos.iterator();
			while (ite.hasNext()) {
				WrapperFile arAd = (WrapperFile) ite.next();
				if (arAd.getIdDocumentoAdjunto() == null) {
					Digital digit = new Digital();
					digit.setDescripcion(arAd.getDescripcion());
					digit.setIdDigital(null);
					digit.setIdOperador(Session.getOperador().getCodigo());
					digit.setIndividuoEvaluacion(individuoEvaluacion);
					digit.setTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
					TipoDigital ti = null;
					Iterator iterTipos = listAuxTipoDigital.iterator();
					while (iterTipos.hasNext()) {
						TipoDigital tip = (TipoDigital) iterTipos.next();
						if (tip.getIdTipoDigital().equals(arAd.getIdTipoDoc())) {
							ti = tip;
							break;
						}
					}
					digit.setTipoDigital(ti);
					digit.setUrl(arAd.getPath());
					individuoEvaluacion.getDocAdjuntos().add(digit);
				}
			}
		} else {
			individuoEvaluacion.setDocAdjuntos(null);
		}

	}


	/*
	 * Este metodo se utiliza para armar un objeto Actividad.
	 */
	private void cargarActividad() throws Exception {
		log.info("Ejecutando ==> armarActividad()");
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("cuil", Filtro.LIKEXS, individuoEvaluacion.getCuil());
		filtro.agregarCampoOperValor("sucEmpresa.idSucEmpresa", Filtro.IGUAL, sucEmpresa.getIdSucEmpresa());
		try {
			List actividades = evaluacionService.getActividadEvaluacionService().getActividad(filtro);
			if (!actividades.isEmpty()) {
				log.info("la actividad ya existe");
				if (individuoEvaluacion.getId() != null && !edicion)
					throw new Exception("El individuo seleccionado ya esta relacionado a la empresa.");
				ActividadEvaluacion acti = (ActividadEvaluacion) actividades.get(0);
				// if (individuoEvaluacion.getActividad()!=null &&individuoEvaluacion.getActividad().getIdActividad()!=null &&
				// acti.getIdActividad()==individuoEvaluacion.getActividad().getIdActividad()) {
				// log.info("La primera actividad encontrada corresponde a evaluación, tomaremos la segunda");
				// acti = (ActividadEvaluacion)actividades.get(1);
				// }
				actividad = acti;
				actividad.getCargo();
				if (actividad.getOcupacion() != null) {
					actividad.getOcupacion().getDescripcion();
				} else {
					actividad.setOcupacion(new Ocupacion());
					actividad.getOcupacion().setIdOcupacion(new Long(0));
				}
				ingreso = actividad.getFechaIngreso();
				if (actividad.getAntiguedad() != null) {
					antiguedad = actividad.getAntiguedad().toString();
				}
			} else {
				log.info("seteamos nueva actividad");
				actividad = new ActividadEvaluacion();
				actividad.setIdActividad(new Long(0));
				actividad.setOcupacion(new Ocupacion());
				actividad.getOcupacion().setIdOcupacion(new Long(0));
			}
		} catch (ActividadEvaluacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actividad.setCuil(individuoEvaluacion.getCuil());
		actividad.setSucEmpresa(sucEmpresa);
		actividad.setEvaIndividuos(null);
		if (!edicion) {
			actividad.setTipo("E");
		}
	}


	public void armarActividad() {
		// proceso para guardar la Actividad.
		if (actividad != null && actividad.getIdActividad() != null
				&& actividad.getIdActividad().equals(new Long(0))) {
			actividad.setIdActividad(null);
		}
		if (antiguedad != null && antiguedad.compareTo("") == 0) {
			if (actividad != null)
				actividad.setAntiguedad(null);
		} else {
			if (actividad != null && antiguedad != null)
				actividad.setAntiguedad(new Long(antiguedad));
		}
		if (actividad != null
				&& actividad.getOcupacion() != null
				&& actividad.getOcupacion().getIdOcupacion() != null
				&& !actividad.getOcupacion().getIdOcupacion().equals(new Long(0))) {
			actividad.setOcupacion((Ocupacion) Util.buscarElemento(listAuxOcupacion, actividad.getOcupacion()));
			// Iterator iterActividad = listAuxOcupacion.iterator();
			// while (iterActividad.hasNext()) {
			// Ocupacion ocupa = (Ocupacion) iterActividad.next();
			// if (ocupa.getId() == actividad.getOcupacion().getIdOcupacion()) {
			// actividad.setOcupacion(ocupa);
			// }
			// }
		}
	}


	public String cancelarIndividuoNuevo() {
		// refrescar el individuo y cancelar

		return null;
	}


	/*
	 * Este método se utiliza para validar el cuit de un individuo.
	 */
	private boolean validarCuit(String cuit) {
		error.borrar();
		if (!cuit.equals(null) && !cuit.equals("")) {
			try {
				cuitValido = new CuitValido(cuit);
			} catch (CuitNoValidoException e1) {
				error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
				e1.printStackTrace();
			} catch (Exception e) {
				error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
				e.printStackTrace();
			}
		} else {
			error.agregar("Debe ingresar un número de Cuil");
		}
		return (error.cantidad() == 0) ? true : false;
	}


	/*
	 * este me todo se utiliza para poder habilitar La lista e profeciones teneindo en cuenta si el individuo tiene estudios Universitarios Completo.
	 */
	public void cambiarProfesion(ValueChangeEvent event) {
		if (educacionHtml != null && educacionHtml.getValue() != null && !educacionHtml.getValue().equals("")) {
			if (educacionHtml.getValue().toString().equals("8")) {
				habilitarProfesion = false;
				focoHidden = "lstPorfesion";
			} else {
				habilitarProfesion = true;
				setIdProfesionSeleccionado(new Long(0));
				focoHidden = "lstPorfesion";
			}
		} else {
			habilitarProfesion = false;
		}
	}


	public String agregarEmpresa() {
		Empresa emp = null;
		log.info("Ir a agregar empresa al individuo!!!");
		EmpresaBean beanEmpresa = (EmpresaBean) Session.getBean("EmpresaBean");
		beanEmpresa.inicializar();
		if (nroCuit != null && !nroCuit.equals("")) {
			beanEmpresa.setCuitEditado(nroCuit);
			log.info("Buscando si empresa existente coincide con cuit");
			try {
				List empresas = generalService.getEmpresaService().getEmpresa(new Filtro("cuit", Filtro.IGUAL, nroCuit));
				if (!empresas.isEmpty()) {
					emp = (Empresa) empresas.get(0);
					Iterator iter = emp.getSucEmpresas().iterator();
					while (iter.hasNext()) {
						SucEmpresa sucEmpresa = (SucEmpresa) iter.next();
						sucEmpresa.getDomicilio().getLocalidad().getNombre();
						sucEmpresa.getDomicilio().getBarrio().getLocalidad().getProvincia().getPais();
						if (sucEmpresa.getDomicilio().getTipoDomicilio() != null)
							sucEmpresa.getDomicilio().getTipoDomicilio().getDescripcion();
						if (sucEmpresa.getDomicilio().getPropVivienda() != null)
							sucEmpresa.getDomicilio().getPropVivienda().getDescripcion();
						if (sucEmpresa.getDomicilio().getTipoVivienda() != null)
							sucEmpresa.getDomicilio().getTipoVivienda().getDescripcion();
						if (sucEmpresa.getAutonomo() != null)
							sucEmpresa.getAutonomo().getDescripcion();
						Iterator it = sucEmpresa.getSucTelefonos().iterator();
						while (it.hasNext()) {
							SucTelefono tel = (SucTelefono) it.next();
							tel.getTelefono().getTipo().getDescripcion();
						}
						Iterator itDos = sucEmpresa.getSucEmails().iterator();
						while (itDos.hasNext()) {
							SucEmail email = (SucEmail) itDos.next();
							email.getEmail().getEmail();
						}
					}
				}
			} catch (EmpresaException e) {
				e.printStackTrace();
			}
			if (emp != null)
				beanEmpresa.inicializarDesdePopup(emp);
			else
			{
				/*
				 * Empresa empr = new Empresa(); empr.setCuit(new Long(nroCuit)); empr.setEsRiesgoza(Character.valueOf('N')); empr.setSucEmpresas(new
				 * HashSet()); empr.setRubro(new Rubro()); empr.setTamEmpresa(new TamEmpresa(new Long(0))); beanEmpresa.inicializarDesdePopup(empr);
				 */
				beanEmpresa.inicializarNuevaEmpresa(new Long(nroCuit), 2);
			}
		}
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/empresa/empresaPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',900,700), 'titlebar=no';");
		return null;
	}


	/*
	 * este metodo se utiliza para buscar la empresa en la q trabaja el individuo
	 */
	public String buscarEmpresa() {
		if (nroCuit != null && !nroCuit.equals("")) {
			Filtro filtro = new Filtro("cuit", Filtro.IGUAL, nroCuit);
			try {
				List aux = generalService.getEmpresaService().getEmpresa(filtro);
				listSucursales.clear();
				listSucEmp.clear();
				if (!aux.isEmpty()) {
					// direccionSucursal = "";
					// telefono = "";
					empresa = (Empresa) aux.get(0);
					Iterator iterSucursal = empresa.getSucEmpresas().iterator();
					while (iterSucursal.hasNext()) {
						try {
							SucEmpresa sucur = (SucEmpresa) iterSucursal.next();
							sucur.getDomicilio().getCalleNombre();
							sucur.getDomicilio().getLocalidad().getPartido().getIdPartido();
							sucur.getSucEmails();
							sucur.getSucTelefonos();
							Iterator iterator = sucur.getSucTelefonos().iterator();
							while (iterator.hasNext()) {
								SucTelefono sucTelefono = (SucTelefono) iterator.next();
								sucTelefono.getTelefono().getNroTlefono();
							}
							listSucursales.add(sucur);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					listSucEmp.add(new SelectItem(new Long(0), "Seleccionar Sucursal"));
					listSucEmp.addAll(Util.cargarSelectItem(listSucursales));
					direccionSucursal = "";
					telefono = "";
					domSucursal = null;
					sucTelefono = null;
					habilitarSucursal = false;
				} else {
					error.agregar(Error.EVA_INDIVIDUO_EMPRESA);
					habilitarSucursal = true;
				}
			} catch (Exception e) {
				// e.printStackTrace();
				error.agregar("Error al buscar la empresa.");
			}
		} else {
			error.agregar(Error.EVA_NRO_CUIT_REQUERIDO);
		}
		sucursalHtml.setValue(new Long(0));
		return null;
	}


	/*
	 * este metodo se utiliza para poder cargar los datos de la sucursal de la empresa en la que trabaja el individuo.
	 */
	public void cambioSucursal(ValueChangeEvent event) {
		log.info("Ejecutando ==> cambioSucursal()");
		try {
			Long valorSeleccionado = new Long(sucursalHtml.getValue().toString());
			if (valorSeleccionado.intValue() != 0 && !listSucursales.isEmpty()) {
				direccionSucursal = "";
				telefono = "";
				domSucursal = null;
				sucTelefono = null;
				Iterator iterator = listSucursales.iterator();
				sucEmpresa = (SucEmpresa) Util.buscarElemento(listSucursales, new SucEmpresa(valorSeleccionado));
				// while (iterator.hasNext()) {
				// sucEmpresa = (SucEmpresa) iterator.next();
				if (sucEmpresa != null) {
					domSucursal = sucEmpresa.getDomicilio();
					// SelectItem item = new SelectItem(sucEmpresa.getDomicilio().getIdDomicilio(),
					// sucEmpresa.getDomicilio().getCalleNombre()+ " "+ sucEmpresa.getDomicilio().getCalleNumero());
					// SelectItem item = new SelectItem(domSucursal.getIdDomicilio(), domSucursal.getCalleNombre()+ " "+
					// domSucursal.getCalleNumero());
					SelectItem item = IndividuoPopupUtil.armarDomPago(domSucursal);
					if (!listDomicilioPago.contains(item)) {
						listDomicilioPago.add(item);
					}
					// try {
					// Iterator iter = generalService.getSucTelefonoService().getSucTelefono(new Filtro("sucEmpresa.idSucEmpresa", Filtro.IGUAL,
					// valorSeleccionado)).iterator();
					// while (iter.hasNext()) {
					// SucTelefono sucTelefono = (SucTelefono) iter.next();
					ArrayList auxSucTel = new ArrayList(sucEmpresa.getSucTelefonos());
					if (!auxSucTel.isEmpty()) {
						sucTelefono = (SucTelefono) auxSucTel.get(0);
					}
					// telefono = sucTelefono.getTelefono().getNroTlefono();
					// break;
				}
				// } catch (SucTelefonoException e) {
				// e.printStackTrace();
				// }
				// break;
			} else {
				sucEmpresa = new SucEmpresa();
				telefono = "";
				direccionSucursal = "";
				sucTelefono = null;
				domSucursal = null;
			}
			// }

			if (sucEmpresa != null
					&& sucEmpresa.getIdSucEmpresa() != null) {
				if (individuoEvaluacion.getActividad() == null) {
					individuoEvaluacion.setActividad(new ActividadEvaluacion());
					individuoEvaluacion.getActividad().setOcupacion(new Ocupacion(new Long(0)));
				} else {
					if (individuoEvaluacion.getActividad().getSucEmpresa() != null) {
						if (individuoEvaluacion.getActividad().getTipo() != null
								&& !individuoEvaluacion.getActividad().getTipo().equals("E")) {
							individuoEvaluacion.setActividad(new ActividadEvaluacion());
							individuoEvaluacion.getActividad().setOcupacion(new Ocupacion(new Long(0)));
						}
					}
				}
				individuoEvaluacion.getActividad().setSucEmpresa(sucEmpresa);
				Filtro filtro = new Filtro("cuil", Filtro.LIKE, individuoEvaluacion.getCuil());
				filtro.agregarCampoOperValor("sucEmpresa.idSucEmpresa", Filtro.IGUAL, sucEmpresa.getIdSucEmpresa());
				List actiList = evaluacionService.getActividadEvaluacionService().getActividad(filtro);
				if (!actiList.isEmpty()) {
					ActividadEvaluacion actividadEvaluacion = (ActividadEvaluacion) actiList.get(0);
					if (!actividadEvaluacion.getIdActividad().equals(individuoEvaluacion.getActividad().getIdActividad())) {
						actividadEvaluacion.getOcupacion().getIdOcupacion();
						actividadEvaluacion.getSucEmpresa().getEmpresa().getRazonSocial();
						individuoEvaluacion.setActividad(actividadEvaluacion);
						ingreso = new Date(actividadEvaluacion.getFechaIngreso().getTime());
						antiguedad = actividadEvaluacion.getAntiguedad().toString();
						refrescarAct = false;
					}
				}
			}
		} catch (ActividadEvaluacionException e) {
			e.printStackTrace();
		} catch (NullPointerException e1) {
			e1.printStackTrace();
		}
	}


	public void borrar() {
		borrarBaseBean();
		alta = true;
		individuoCargado = false;
		botonEmpresa = false;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Individuo";
		listTelefono = new ArrayList();
		listEstadoCivil = new ArrayList();
		listAuxEstadoCivil = new ArrayList();
		listEducacion = new ArrayList();
		listAuxEducacion = new ArrayList();
		listProfesion = new ArrayList();
		listAuxProfesion = new ArrayList();
		listSucursales = new ArrayList();
		listSucEmp = new ArrayList();
		listDomicilioPago = new ArrayList();
		listDiaPago = new ArrayList();
		listBancos = new ArrayList();
		listOcupacion = new ArrayList();
		listTipoCtas = new ArrayList();
		listDomicilioInmueble = new ArrayList();
		listTipoDigital = new ArrayList();
		listTipoDocumentos = new ArrayList();
		listaTcliente = new ArrayList();
		empresa = new Empresa();
		bancos = new Bancos();
		tarjeta = new Tarjeta();
		focoHidden = "inApellido";
		habilitarProfesion = true;
		habilitarSucursal = true;
		botonAdjuntar = false;
		nroCuit = "";
		email = "";
		idSucursalSeleccionado = new Long(0);
		refrescarAct = true;
		docDigital = new Digital();
		docDigital.setDescripcion("");
		diasPagos.setValue(new Long(0));
		idDiaPagoSeleccionado = new Long(0);
		idTipoCuentaSeleccionado = new Long(0);
		idBancoSeleccionado = new Long(0);
		idTipoDocSeleccionado = new Long(0);
		nroCBU = "";
		// sucEmpresa = null;
		sucTelefono = null;
		domSucursal = null;
		individuoVehiculo = new IndividuoVehiculo(null, individuoEvaluacion, new Vehiculo());

		editDatos = true;
		editDomicilio = true;
		editTelefono = true;
		editEmail = true;
		editFamilia = true;
		editActividad = true;
		editFacturacion = true;
		editFinanciero = true;
		editPropiedades = true;
		editDigitales = true;

	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		String cuilSexo;
		// Long dosDigitos;
		validarCuit(individuoEvaluacion.getCuil());
		if (individuoEvaluacion.getApellido() == null
				|| individuoEvaluacion.getApellido().equals("")) {
			error.agregar(Error.EVA_INDIVIDUO_APELLIDO_REQUERIDO);
		}
		if (individuoEvaluacion.getNombres() == null
				|| individuoEvaluacion.getNombres().equals("")) {
			error.agregar(Error.EVA_INDIVIDUO_NOMBRE_REQUERIDO);
		}
		if (individuoEvaluacion.getTipoDocumento().getIdTipoDocumento() == null
				|| individuoEvaluacion.getTipoDocumento().getIdTipoDocumento()
						.equals(new Long(0))) {
			error.agregar(Error.EVA_TIPO_DOCUMENTO_REQUERIDO);
		}
		if (individuoEvaluacion.getNroDocumento() == null
				|| individuoEvaluacion.getNroDocumento().equals("")) {
			error.agregar(Error.EVA_NRO_DOCUMENTO_REQUERIDO);
		}

		if (edicion)
		{
			if (idDiaPagoSeleccionado == null || idDiaPagoSeleccionado.equals(new Long(0))) {
				error.agregar(Error.EVA_INDIVIDUO_DIA_DE_PAGO_REQ);
				if (!(listDiaPago.size() > 1))
					error.agregar(Error.EVA_INDIVIDUO_CARGAR_DOMICILIO_DOCUM);
			}
			else
			{
				try {
					DiaPago diaPagoSelec = evaluacionService.getDiaPagoService().leerDiaPago(idDiaPagoSeleccionado);
					if (!diaPagoSelec.getDiaPago().equals(diaPagoSeleccionadoInfo))
					{
						error.agregar(Error.EVA_INDIVIDUO_DIA_DE_PAGO_SET);
					}
				} catch (DiaPagoException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			if (individuoEvaluacion.getNroDocumento() != null) {
				if (individuoEvaluacion.getCuil().substring(2, 10).compareTo(individuoEvaluacion.getNroDocumento()) != 0) {
					error.agregar(Error.EVA_DOCUMENTO_CUIL_INCOHERENTES);
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		if (individuoEvaluacion.getCuil() != null && individuoEvaluacion.getCuil().compareTo("") != 0) {
			cuilSexo = individuoEvaluacion.getCuil().substring(0, 2);
			// log.info("Primeros Dos Digitos: " + cuilSexo);
			// dosDigitos = new Long(individuoEvaluacion.getCuil().substring(0, 2));
			// log.info("Pprimeros Dos Digitos del Cuil: " + cuilSexo);
			if (cuilSexo.equals("20") || cuilSexo.equals("27")) {
				switch (getIdTipoSexoSeleccionado().intValue()) {
				case 0:
					error.agregar(Error.EVA_TIPO_SEXO_REQUERIDO);
					break;
				case 1:
					if (cuilSexo.equals("20"))
						error.agregar(Error.EVA_INDIVIDUO_SEXO_INCONCORDANTE);
					break;
				case 2:
					if (cuilSexo.equals("27"))
						error.agregar(Error.EVA_INDIVIDUO_SEXO_INCONCORDANTE);
					break;
				}
			} else {
				if (cuilSexo.equals(new Long(30)) || cuilSexo.equals(new Long(33)))
					error.agregar(Error.EVA_NRO_CUIL_INCORRECTO);
			}
		} else {
			error.agregar(Error.EVA_INDIVIDUO_CUIT_REQUERIDO);
		}
		if (email != null && !email.equals("") && !Validador.checkEmail(email))
			error.agregar(Error.EVA_INDIVIDUO_EMAIL_INCORRECTO);
		return (error.cantidad() == 0) ? true : false;
	}


	public void cambiarDiasDePago(ValueChangeEvent event) {
		try {
			listDiaPago.clear();
			listDiaPago.add(new SelectItem(new Long(0), "Selecciones Dia de Pago"));
			Long idDomPagoSeleccionado = new Long(diasPagos.getValue().toString());
			Long idPart = new Long(0);
			if (idDomPagoSeleccionado != new Long(0)) {
				if (individuoEvaluacion.getDomicilio() != null
						&& individuoEvaluacion.getDomicilio().getIdDomicilio() != null
						&& individuoEvaluacion.getDomicilio().getIdDomicilio().equals(idDomPagoSeleccionado)) {
					idPart = individuoEvaluacion.getDomicilio().getBarrio().getLocalidad().getPartido().getIdPartido();
				} else { // sino es el principal busco en el de la acctividad
					if (individuoEvaluacion.getActividad() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio().getIdDomicilio().equals(idDomPagoSeleccionado)) {
						idPart = individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio().getLocalidad().getPartido().getIdPartido();
					}
				}

				if (!idPart.equals(new Long(0))) {
					/* @I13 */Filtro filtro = new Filtro();
					filtro.agregarCampo("partido.idPartido");
					filtro.agregarOperador(Filtro.IGUAL);
					filtro.agregarValor(idPart);

					listDiaPago.addAll(Util.cargarSelectItem(
							/* @F13 */evaluacionService.getDiaPagoService().getDiaPago(filtro)));
				}
			}
		} catch (DiaPagoException e) {
			e.printStackTrace();
		}
	}


	/*
	 * Este metodo se utiliza para poder editar un domicilio, se captura el id del domicilio q se desea editar, luego se busca dentro del array de lso
	 * domicilios, una vez q se encuentra se manda al bean de domicilios, donde se cargara y estara lsito para ser editado.
	 */
	public String editarDomicilioInmueble() {
		log.info("Ejecutando ==> editarDomicilio()");
		Long idDomicilio = new Long(Session.getRequestParameter("idDomiEdi"));
		log.info("id a buscar: " + idDomicilio);
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		IndividuoDomicilio domAux = IndividuoEvaluacionUtil.buscarDomicilio(listDomicilioInmueble, idDomicilio);
		bean.inicializar(DomicilioBean.GARANTIA, domAux.getDomicilio());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	/*
	 * Este metodo se utiliza para capturar la accion del boton de eliminar direccion. este toma el id del domicilio y luego se lo pasa por parametro
	 * junto con la lita de domicilios a un metodo dentro del util de individuo evaluacion, que se encarga de elimar el domicilio del array.
	 */
	public String eliminarDomicilioInmueble() {
		log.info(" Ejecutando ==> eliminarDomicilio()");
		Long idDomicilio = new Long(Session.getRequestParameter("idDomicilio"));
		log.info("Id Domicilio: " + idDomicilio);
		IndividuoEvaluacionUtil.eliminarDomicilio(listDomicilioInmueble, idDomicilio);
		IndividuoEvaluacionUtil.eliminarDomicilioDelSet(individuoEvaluacion.getDomicilios(), idDomicilio);
		return null;
	}


	/*
	 * Este metodo se utiliza para agregar un domicilio al individuo.
	 */
	public String agregarDomicilioInmueble() {
		log.info("Ir a agrega nuevo domicilio del inmueble al titular!!!");
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		bean.inicializar(DomicilioBean.GARANTIA, new Domicilio());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	/*
	 * Este metodo se utiliza para poder almacenar un archivo digital en un directorio predefinido. Primero creamos la ruta donde se va a almacenar el
	 * archivo.
	 */
//	public String saveFile() {
//		log.info("Ejecutando ==> saveFile()");
//		String path;
//
//		if (idTipoDocSeleccionado != null && !idTipoDocSeleccionado.equals(new Long(0))) {
//			path = "No grabo";
//			Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
//			try {
//
//				String pathAux = "";
//				char aux[] = Archivo.archivosDeIndividuos.toCharArray();
//				for (int i = 0; i < 10; i++)
//				{
//					pathAux += aux[i];
//				}
//				int año = timestamp.getYear() + 1900;
//				pathAux += "/" + año + "-";
//				int mes = timestamp.getMonth() + 1;
//				if (mes < 10) // Si el mes tiene un solo digito, se le agrega un cero a la izquierda.
//				{
//					pathAux += "0" + mes;
//				}
//				else
//					pathAux += +mes;
//				logger.info("Directorio archivo: " + pathAux);
//				path = Archivo.grabarArchivo(uploadedFile.getInputStream(), uploadedFile.getName(), new Long(uploadedFile.getSize()).intValue(),
//						pathAux);
//				// }
//
//			} catch (IOException e) {
//				e.printStackTrace();
//				return null;
//			} catch (NullPointerException e2) {
//				e2.printStackTrace();
//			}
//			if (path != null && !path.equals("No grabo")) {
//
//				WrapperFile wrapperFile = new WrapperFile(null, idTipoDocSeleccionado, path, docDigital.getDescripcion(), path, timestamp);
//				wrapperFile.setIdWrappers(new Long(wrapperFile.hashCode()));
//				wrapperFile.setListaDocumentos(listAuxTipoDigital);
//				listTipoDocumentos.add(wrapperFile);
//				FacesContext facesContext = FacesContext.getCurrentInstance();
//				String javaScriptText = "window.opener.recargar();";
//				AddResource addResource = AddResourceFactory.getInstance(facesContext);
//				addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
//			}
//
//		} else {
//			error.agregar(Error.TIPO_DOCUMENTO_ADJUNTO_REQUERIDO);
//		}
//		return null;
//	}
	
	
	public String saveFile() {
		log.info("Ejecutando ==> saveFile()");
		String path;
		String nombreAdelante = null;

		if (idTipoDocSeleccionado != null && !idTipoDocSeleccionado.equals(new Long(0))) {
			path = "No grabo";
			Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
			String fechaFormato = formatoFecha.format(timestamp);

			
			try {
				//String archivosDeComercios = "comercios/docAdjuntos";
				String pathAux = "tramites";
				//char aux[] = Archivo.archivosDeIndividuos.toCharArray();
//				char aux[] = archivosDeComercios.toCharArray();
//				for (int i = 0; i < 10; i++)
//				{
//					pathAux += aux[i];
//				}
				
				
				int año = timestamp.getYear() + 1900;
				pathAux += "/" + año + "-";
				int mes = timestamp.getMonth() + 1;
				if (mes < 10) // Si el mes tiene un solo digito, se le agrega un cero a la izquierda.
				{
					pathAux += "0" + mes;
				}
				else
					pathAux += +mes;
				
				
				log.info("Directorio archivo: " + pathAux);
				String codPosnetStr= null;
				
				String codPosnet = "AmazonAWS";
//				if (codPosnet.length() < 4) {
//					 codPosnetStr = ("0000" + codPosnet).substring(("0000" + codPosnet).length() - 4);
//				}else {
//					 codPosnetStr = codPosnet;
//				}
				
				 nombreAdelante = fechaFormato+"-"+codPosnet;
				
				path = Archivo.grabarArchivoComercio(uploadedFile.getInputStream(), uploadedFile.getName(), new Long(uploadedFile.getSize()).intValue(),
						pathAux,nombreAdelante);
				
			//	https://tarjetafiel.s3.sa-east-1.amazonaws.com/liquidacionesClientes/Comercioscomercios/docAdjuntos/2023-01/1010-10-01-2023-540015-07-2022_72196.pdf
				
				
				try {
					//SimpleDateFormat formatoFechaS3 = new SimpleDateFormat("dd-MM-yyyy");
					String bucket = pathAux;
//					String bucket = "Atencion/"+formatoFechaS3.format(fechaCierreActual);
					
					
//					if (comunicado) {
//						 fileAmazonCliente = nombreLiq.trim()+"T.pdf";
//						
//					} else {
//						 fileAmazonCliente = nombrePdfLiqJunto;
//						
//					}
				String	 fileAmazonCliente = path;
					
					
						
					Map<String, String> headers = new HashMap<String, String>();
			    	     			    	    
			    	    HttpPostMultipart multipart = new HttpPostMultipart("http://192.168.0.76:8080/s3_bucket/storage/uploadFile", "utf-8", headers);
   
			    	    multipart.addFormField("bucket", bucket);
			    	    // Add file
			    	    multipart.addFilePart("file", new File(fileAmazonCliente));
			    	    // Print result
			    	    String response = multipart.finish();
			    	    System.out.println(response); 
			    	    log.info("archivo pdf subido al Amazon: " + fileAmazonCliente);
			    	    
			    	    File file = new File(path);
			    	    file.delete();
				
				
			    	    
			    	    
					} catch (Exception e) {
						log.info("Error en subir archivo al Amazon: " + path);
						
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
				
				
				
				
				// }

			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (NullPointerException e2) {
				e2.printStackTrace();
			}
			if (path != null && !path.equals("No grabo")) {
				
				

				WrapperFile wrapperFile = new WrapperFile(null, idTipoDocSeleccionado, nombreAdelante + "-" + uploadedFile.getName(), docDigital.getDescripcion(), nombreAdelante + "-" + uploadedFile.getName(), timestamp);
				wrapperFile.setIdWrappers(new Long(wrapperFile.hashCode()));
				wrapperFile.setListaDocumentos(listAuxTipoDigital);
				listTipoDocumentos.add(wrapperFile);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				String javaScriptText = "window.opener.recargar();";
				AddResource addResource = AddResourceFactory.getInstance(facesContext);
				addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
			}
			
		} else {
			error.agregar(Error.TIPO_DOCUMENTO_ADJUNTO_REQUERIDO);
		}

		
		return null;
	}

	


	public String abrirArchivo() {
		WrapperFile element = null;
		Long idAr = new Long(Session.getRequestParameter("idArchiAbrir"));
		Iterator iterFile = listTipoDocumentos.iterator();
		while (iterFile.hasNext()) {
			WrapperFile fi = (WrapperFile) iterFile.next();
			if (fi.getIdWrappers().equals(idAr)) {
				element = fi;
				break;
			}
		}
		try {
			String url = element.getNombreArchivo();
			Date fecha = new Date(element.getTimestamp().getTime());
			Calendar calendar = Calendar.getInstance();
			calendar.set(2015, 9, 5);
			Date fechaControl = calendar.getTime();

			// Obtiene el parámetro del sistema id 28 (fecha1)
			ParametroSistemaDetalle parametroSistemaDetalle = generalService.getParametroSistemaDetalleService().leerParametroSistemaDetalle(28L);
			char[] fechaAux = parametroSistemaDetalle.getValor().toCharArray();
			String diaAux = "" + fechaAux[0] + fechaAux[1];
			String mesAux = "" + fechaAux[3] + fechaAux[4];
			String añoAux = "" + fechaAux[6] + fechaAux[7] + fechaAux[8] + fechaAux[9];
			Timestamp fechaTransicionDirectorios = new Timestamp(Integer.parseInt(añoAux) - 1900, Integer.parseInt(mesAux) - 1,
					Integer.parseInt(diaAux), 0, 0, 0, 0);

			String ruta = "";
			int año = element.getTimestamp().getYear() + 1900;
			ruta += año + "-";
			int mes = element.getTimestamp().getMonth() + 1;
			if (mes < 10) // Si el mes tiene un solo digito, se le agrega un cero a la izquierda.
			{
				ruta += "0" + mes;
			}
			else
				ruta += mes;
			
			String amazonS3 = "https://s3-sa-east-1.amazonaws.com/tarjetafiel/tramites";		
			

		    if  (element.getNombreArchivo().indexOf("/lineaCredito/") > -1) {
		    	log.info("lineaCredito " +   amazonS3   + element.getNombreArchivo());
//		    	log.info("lineaCredito " +  element.getNombreArchivo());
		    	ejecutarJavaScript("popup('" + amazonS3   + element.getNombreArchivo() + "',700,400);");
//		    	ejecutarJavaScript("popup('" + element.getNombreArchivo() + "',700,400);");
		    } else if  (element.getNombreArchivo().indexOf("-AmazonAWS-") > -1) {
		    	log.info("-AmazonAWS- " +  amazonS3  + "/"  + ruta + "/" + element.getNombreArchivo());
		    	ejecutarJavaScript("popup('" + amazonS3  + "/"  + ruta + "/" + element.getNombreArchivo() + "',700,400);");
		    }
		    
		    else {	
			

			if (element.getTimestamp().after(fechaTransicionDirectorios))
			{
				ejecutarJavaScript("popup('" + "/../archivos/individuos/" + ruta + "/" + element.getNombreArchivo() + "',700,400);");
			}
			else
			{
				parametroSistemaDetalle = generalService.getParametroSistemaDetalleService().leerParametroSistemaDetalle(27L);
				String servidor = parametroSistemaDetalle.getValor();
				ejecutarJavaScript("popup('" + "http://192.168.0." + servidor + ":8080/archivos/individuos/" + ruta + "/"
						+ element.getNombreArchivo() + "',700,400);");
			}
			
			}

		} catch (Exception e) {
			log.info("Error al intentar leer el archivo");
			e.printStackTrace();
		}
		return null;
	}


	public String borrarArchivo() {
		Long idAr = new Long(Session.getRequestParameter("idArchi"));
		WrapperFile fil = null;
		Iterator iterFile = listTipoDocumentos.iterator();
		while (iterFile.hasNext()) {
			WrapperFile fi = (WrapperFile) iterFile.next();
			if (fi.getIdWrappers().equals(idAr)) {
				fil = fi;
				break;
			}
		}
		if (fil != null) {
			listTipoDocumentos.remove(fil);
			if (fil.getIdDocumentoAdjunto() != null) {
				Digital dig = null;
				Iterator it = individuoEvaluacion.getDocAdjuntos().iterator();
				while (it.hasNext()) {
					Digital d = (Digital) it.next();
					if (d.getIdDigital().equals(fil.getIdDocumentoAdjunto())) {
						dig = d;
						break;
					}
				}
				if (dig != null)
					individuoEvaluacion.getDocAdjuntos().remove(dig);
			}
		}
		return "";
	}


	public String salir() {
		FacesContext facesContext;
		String javaScriptText;
		// switch (origen) {
		//
		// case TITULAR:
		// if (panelF) {
		// facesContext = FacesContext.getCurrentInstance();
		// javaScriptText = "window.opener.recargar();window.close();";
		// ejecutarJavaScript(javaScriptText);
		// mostarElementos(TITULAR);
		// //aqui el codigo para buscar el titular
		// return "amIndividuo";
		// }
		if (origen != COD_COMERCIO) {
			facesContext = FacesContext.getCurrentInstance();
			javaScriptText = "window.close();";
			ejecutarJavaScript(javaScriptText);
			popup.borrar();
		}
		else {
			Session.redirect("/tarjetafiel/transacciones/popup/amAutorizadosResponsablesComerciosPopup.jsf");

		}
		// break;
		//
		// case GARANTE:
		// inicializar(1,nroSolicitud,null);
		// return "amIndividuo";
		//
		// case ADICIONAL:
		// inicializar(1,nroSolicitud,null);
		// return "amIndividuo";

		// }
		return null;
	}


	public String guardar() {
		if (validar()) {
			log.info("Ejecutando ==> Guardar");
			if (individuoEvaluacion.getActividad() != null) { // Se agrego esto para adecuarse a las nuevas especificaciones
				individuoEvaluacion.getActividad().setCuil(individuoEvaluacion.getCuil());
			}
			armarIndividuo();
			// Ver esto de los emails xq creo que ya se esta haciendo en un metodo llamado en "armarIndividuo()"
			// if (individuoEvaluacion.getMails() != null && individuoEvaluacion.getMails().size() > 0) {
			// List listaMails = Convertidores.setToList(individuoEvaluacion.getMails());
			// Emails ma = (Emails) listaMails.get(0);
			// email = ma.getEmail().getEmail();
			// }
			try {
				if (alta) {
					evaluacionService.getIndividuoEvaluacionService().grabarIndividuo(individuoEvaluacion);
				} else {
					evaluacionService.getIndividuoEvaluacionService().actualizarIndividuo(individuoEvaluacion);
				}
				popup.setPopup(popup.ICONO_OK, "El individuo ha sido almacenada exitosamente.");
			} catch (IndividuoEvaluacionException e) {
				popup.setPopup(popup.ICONO_FALLA, "Falló el alta/modificación del Individuo.");
				e.printStackTrace();
			} catch (Exception e) {
				popup.setPopup(popup.ICONO_FALLA, "Falló el alta/modificación del Individuo.");
				e.printStackTrace();
			}
		}
		return null;
	}


	public String irAContinuar() {
		log.info("irContinuar");
		popup.borrar();
		if (Session.getBean("ScrollBean") != null) {
			((ScrollBean) Session.getBean("ScrollBean")).setHiddenScrollY(new Integer(0));
		}
		return null;
	}


	public String popUpListaTclientes() {
		listaTcliente.clear();
		listaTcliente();
		if (error.cantidad() == 0) {
			String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += "/tarjetafiel/evaluacion/popup/listaTclientes.jsf";
			ejecutarJavaScript("popup('" + path + "',700,300), 'titlebar=no';");
		}

		return "";
	}


	public void listaTcliente() {
		if (individuoEvaluacion != null
				&& individuoEvaluacion.getIdIndividuo() != null) {
			Filtro filtro = new Filtro();
			try {
				filtro.agregarCampoOperValor(CliIndividuo.ID_INDIVIDUO, Filtro.IGUAL, individuoEvaluacion.getIdIndividuo());
				filtro.agregarCampoOperValor(CliIndividuo.ID_TIPO_INDIVIDUO, Filtro.IGUAL, "1");
				List cliIndList = transaccionesService.getCliIndividuoService().getCliIndividuo(filtro);
				if (!cliIndList.isEmpty()) {
					CliIndividuo cliIndividuo = (CliIndividuo) cliIndList.get(0);
					filtro.reset();
					filtro.agregarCampoOperValor("cliente", Filtro.IGUAL, cliIndividuo.getCliente());
					List tCliente = evaluacionService.getTclienteService().getTcliente(filtro);
					if (!tCliente.isEmpty()) {
						Iterator iterator = tCliente.iterator();
						while (iterator.hasNext()) {
							Tcliente element = (Tcliente) iterator.next();
							WrappersTcliente wrappersTcliente = new WrappersTcliente(element);
							log.info("cliente: " + element.getCliente());
							listaTcliente.add(wrappersTcliente);
						}
					}

				}
			} catch (CliIndividuoException e) {
			} catch (TclienteException e) {
				e.printStackTrace();
			}
		}
		else {
			error.agregar("Para poder ingresar a ver cliente se necesita cargar un individuo.");
		}
	}


	/*
	 * Este metodo se utiliza para alrmar un objeto DiaPago
	 */
	private void armarDiaPago() {
		try {
			if (idDiaPagoSeleccionado != null && !idDiaPagoSeleccionado.equals(new Long(0))) {
				DiaPago diaPago = evaluacionService.getDiaPagoService().leerDiaPago(idDiaPagoSeleccionado);
				diaPago.setPartido(generalService.getPartidoDao().buscarPartido(diaPago.getPartido().getIdPartido()));
				individuoEvaluacion.setDiaPago(diaPago);
			} else {
				individuoEvaluacion.setDiaPago(null);
			}
			if (idDomicilioPagoSeleccionado != null && idDomicilioPagoSeleccionado.equals(new Long(-1))) {
				individuoEvaluacion.getDomicilio().setIdDomicilio(null);
				individuoEvaluacion.setDomicilioDoc(individuoEvaluacion.getDomicilio());
			} else {
				if (individuoEvaluacion.getDomicilio() != null
						&& individuoEvaluacion.getDomicilio().getIdDomicilio() != null
						&& individuoEvaluacion.getDomicilio().getIdDomicilio().equals(idDomicilioPagoSeleccionado)) {
					individuoEvaluacion.setDomicilioDoc(individuoEvaluacion.getDomicilio());
				} else {
					if (individuoEvaluacion.getActividad() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio().getIdDomicilio() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio().getIdDomicilio().equals(idDomicilioPagoSeleccionado)) {
						Domicilio dom = individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio();
						individuoEvaluacion.setDomicilioDoc(dom);
					} else {
						individuoEvaluacion.setDomicilioDoc(null);
					}
				}
			}
			// if (idDomicilioPagoSeleccionado != null && !idDomicilioPagoSeleccionado.equals(new Long(0))) {
			// Iterator iterDomDoc = listDomicilioInmueble.iterator();
			// while (iterDomDoc.hasNext()) {
			// IndividuoDomicilio domDoc = (IndividuoDomicilio) iterDomDoc.next();
			// if (domDoc.getDomicilio().getIdDomicilio().equals(idDomicilioPagoSeleccionado)) {
			// individuoEvaluacion.setDomicilioDoc(domDoc.getDomicilio());
			// break;
			// }
			// }
			//
			// } else {
			// individuoEvaluacion.setDomicilioDoc(null);
			// }
		} catch (DiaPagoException e) {
			e.printStackTrace();
		}
	}


	public boolean BotonEmpresa() {
		return botonEmpresa;
	}


	public void setBotonEmpresa(boolean botonEmpresa) {
		this.botonEmpresa = botonEmpresa;
	}


	public SucEmpresa getSucEmpresa() {
		return sucEmpresa;
	}


	public void setSucEmpresa(SucEmpresa sucEmpresa) {
		this.sucEmpresa = sucEmpresa;
	}


	public boolean isEmpresaExistente() {
		return empresaExistente;
	}


	public void setEmpresaExistente(boolean empresaExistente) {
		this.empresaExistente = empresaExistente;
	}


	public boolean isAltaIndividuo() {
		return altaIndividuo;
	}


	public void setAltaIndividuo(boolean altaIndividuo) {
		this.altaIndividuo = altaIndividuo;
	}


	public IndividuoEvaluacion getIndividuoEvaluacion() {
		return individuoEvaluacion;
	}


	public void setIndividuoEvaluacion(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}


	public Long getIdTipoSexoSeleccionado() {
		if (individuoEvaluacion.getSexo() != null) {
			if (individuoEvaluacion.getSexo().equals("M"))
				return new Long(2);
			else
				return new Long(1);
		} else {
			return new Long(0);
		}
		// return idTipoSexoSeleccionado;
	}


	public void setIdTipoSexoSeleccionado(Long idTipoSexoSeleccionado) {
		switch (idTipoSexoSeleccionado.intValue()) {
		case 0:
			individuoEvaluacion.setSexo(null);
			break;
		case 1:
			individuoEvaluacion.setSexo("F");
			break;
		case 2:
			individuoEvaluacion.setSexo("M");
			break;
		}
		// this.idTipoSexoSeleccionado = idTipoSexoSeleccionado;
	}


	public List getListOcupacion() {
		return listOcupacion;
	}


	public void setListOcupacion(List listOcupacion) {
		this.listOcupacion = listOcupacion;
	}


	public List getListTelefono() {
		return listTelefono;
	}


	public void setListTelefono(List listTelefono) {
		this.listTelefono = listTelefono;
	}


	public List getListTipoSexo() {
		return listTipoSexo;
	}


	public void setListTipoSexo(List listTipoSexo) {
		this.listTipoSexo = listTipoSexo;
	}


	public HtmlInputCalendar getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(HtmlInputCalendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public HtmlSelectOneMenu getOcupacionHtml() {
		return ocupacionHtml;
	}


	public void setOcupacionHtml(HtmlSelectOneMenu ocupacionHtml) {
		this.ocupacionHtml = ocupacionHtml;
	}


	public HtmlSelectOneMenu getSucursalHtml() {
		return sucursalHtml;
	}


	public void setSucursalHtml(HtmlSelectOneMenu sucursalHtml) {
		this.sucursalHtml = sucursalHtml;
	}


	public List getListTipoDni() {
		return listTipoDni;
	}


	public void setListTipoDni(List listTipoDni) {
		this.listTipoDni = listTipoDni;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getFechaNacimiento() {
		return individuoEvaluacion.getFechaNacimiento();
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		if (fechaNacimiento != null)
			individuoEvaluacion.setFechaNacimiento(new Timestamp(fechaNacimiento.getTime()));
		else
			individuoEvaluacion.setFechaNacimiento(null);
	}


	public Date getIngreso() {
		return ingreso;
	}


	public void setIngreso(Date ingreso) {
		if (refrescarAct)
			this.ingreso = ingreso;
	}


	public String getAntiguedad() {
		return antiguedad;
	}


	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}


	public boolean isIndividuoCargado() {
		return individuoCargado;
	}


	public void setIndividuoCargado(boolean individuoCargado) {
		this.individuoCargado = individuoCargado;
	}


	public boolean isVerActividad() {
		return verActividad;
	}


	public void setVerActividad(boolean verActividad) {
		this.verActividad = verActividad;
	}


	public boolean isVerDatos() {
		return verDatos;
	}


	public void setVerDatos(boolean verDatos) {
		this.verDatos = verDatos;
	}


	public boolean isVerDomicilio() {
		return verDomicilio;
	}


	public void setVerDomicilio(boolean verDomicilio) {
		this.verDomicilio = verDomicilio;
	}


	public ActividadEvaluacion getActividad() {
		return actividad;
	}


	public void setActividad(ActividadEvaluacion actividad) {
		this.actividad = actividad;
	}


	public boolean isEdicion() {
		return edicion;
	}


	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}


	public List getListEstadoCivil() {
		return listEstadoCivil;
	}


	public void setListEstadoCivil(List listEstadoCivil) {
		this.listEstadoCivil = listEstadoCivil;
	}


	public List getListEducacion() {
		return listEducacion;
	}


	public void setListEducacion(List listEducacion) {
		this.listEducacion = listEducacion;
	}


	public List getListProfesion() {
		return listProfesion;
	}


	public void setListProfesion(List listProfesion) {
		this.listProfesion = listProfesion;
	}


	public List getListDomicilioPago() {
		return listDomicilioPago;
	}


	public void setListDomicilioPago(List listDomicilioPago) {
		this.listDomicilioPago = listDomicilioPago;
	}


	public boolean isRefrescarAct() {
		return refrescarAct;
	}


	public void setRefrescarAct(boolean refrescarAct) {
		this.refrescarAct = refrescarAct;
	}


	public Long getIdEstadoCivilSeleccionado() {
		if (individuoEvaluacion.getEstadoCivil() != null) {
			return individuoEvaluacion.getEstadoCivil().getIdEstadoCivil();
		} else {
			return new Long(0);
		}
	}


	public void setIdEstadoCivilSeleccionado(Long idEstadoCivilSeleccionado) {
		if (idEstadoCivilSeleccionado.equals(new Long(0))) {
			individuoEvaluacion.setEstadoCivil(null);
		} else {
			if (individuoEvaluacion.getEstadoCivil() == null
					|| individuoEvaluacion.getEstadoCivil().getIdEstadoCivil() == null
					|| !individuoEvaluacion.getEstadoCivil().getIdEstadoCivil().equals(idEstadoCivilSeleccionado)) {
				individuoEvaluacion.setEstadoCivil((EstadoCivil) Util.buscarElemento(
						listAuxEstadoCivil, new EstadoCivil(idEstadoCivilSeleccionado)));
			}
		}
	}


	public Long getIdEducacionSeleccionado() {
		if (individuoEvaluacion.getEducacion() != null) {
			return individuoEvaluacion.getEducacion().getIdEducacion();
		} else {
			return new Long(0);
		}
	}


	public void setIdEducacionSeleccionado(Long idEducacionSeleccionado) {
		if (idEducacionSeleccionado.equals(new Long(0))) {
			individuoEvaluacion.setEducacion(null);
		} else {
			if (individuoEvaluacion.getEducacion() == null
					|| individuoEvaluacion.getEducacion().getIdEducacion() == null
					|| !individuoEvaluacion.getEducacion().getIdEducacion().equals(idEducacionSeleccionado)) {
				individuoEvaluacion.setEducacion((Educacion) Util.buscarElemento(
						listAuxEducacion, new Educacion(idEducacionSeleccionado)));
			}
		}
	}


	public Long getIdProfesionSeleccionado() {
		if (individuoEvaluacion.getProfesion() != null) {
			return individuoEvaluacion.getProfesion().getIdProfesion();
		} else {
			return new Long(0);
		}
	}


	public void setIdProfesionSeleccionado(Long idProfesionSeleccionado) {
		if (idProfesionSeleccionado.equals(new Long(0))) {
			individuoEvaluacion.setProfesion(null);
		} else {
			if (individuoEvaluacion.getProfesion() == null
					|| individuoEvaluacion.getProfesion().getIdProfesion() == null
					|| !individuoEvaluacion.getProfesion().getIdProfesion().equals(idProfesionSeleccionado)) {
				individuoEvaluacion.setProfesion((Profesion) Util.buscarElemento(
						listAuxProfesion, new Profesion(idProfesionSeleccionado)));
			}
		}
	}


	public Long getIdSucursalSeleccionado() {
		return idSucursalSeleccionado;
	}


	public void setIdSucursalSeleccionado(Long idSucursalSeleccionado) {
		this.idSucursalSeleccionado = idSucursalSeleccionado;
	}


	public HtmlSelectOneMenu getEducacionHtml() {
		return educacionHtml;
	}


	public void setEducacionHtml(HtmlSelectOneMenu educacionHtml) {
		this.educacionHtml = educacionHtml;
	}


	public boolean isHabilitarProfesion() {
		if (editFamilia)
			return habilitarProfesion;
		else
			return true;
	}


	public void setHabilitarProfesion(boolean habilitarProfesion) {
		this.habilitarProfesion = habilitarProfesion;
	}


	public boolean isHabilitarSucursal() {
		if (editActividad)
			return habilitarSucursal;
		else
			return true;
	}


	public void setHabilitarSucursal(boolean habilitarSucursal) {
		this.habilitarSucursal = habilitarSucursal;
	}


	public List getListSucEmp() {
		return listSucEmp;
	}


	public void setListSucEmp(List listSucEmp) {
		this.listSucEmp = listSucEmp;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public String getNroCuit() {
		return nroCuit;
	}


	public void setNroCuit(String nroCuit) {
		this.nroCuit = nroCuit;
	}


	public String getDireccionSucursal() {
		if (domSucursal != null) {
			direccionSucursal = domSucursal.getCalleNombre() + " "
					+ domSucursal.getCalleNumero();
		}
		return direccionSucursal;
	}


	//
	// public void setDireccionSucursal(String direccionSucursal) {
	// if (domSucursal==null) {
	// direccionSucursal = "";
	// } else {
	// this.direccionSucursal = direccionSucursal;
	// }
	// }
	public String getTelefono() {
		if (sucTelefono != null) {
			return sucTelefono.getTelefono().getCodArea()
					+ " - " + sucTelefono.getTelefono().getNroTlefono()
					+ " int. " + sucTelefono.getTelefono().getNroInterno();
		} else {
			return "";
		}
	}


	// public void setTelefono(String telefono) {
	// this.telefono = telefono;
	// }
	public Long getIdOcupacion() {
		try {
			getIndividuoEvaluacion();
			individuoEvaluacion.getActividad();

			individuoEvaluacion.getActividad().getReferencias();
			individuoEvaluacion.getActividad().getSucEmpresa();
			// individuoEvaluacion.getActividad().getOcupacion();
			individuoEvaluacion.getActividad().getOcupacion();
			return individuoEvaluacion.getActividad().getOcupacion().getIdOcupacion();
		} catch (NullPointerException e) {
			return new Long(0);
		}
	}


	public void setIdOcupacion(Long idOcupacion) {
		if (refrescarAct) {
			// if (individuoEvaluacion.getActividad().getOcupacion() != null) {
			// individuoEvaluacion.getActividad().getOcupacion().setIdOcupacion(idOcupacion);
			// }
			individuoEvaluacion.getActividad().setOcupacion(
					(Ocupacion) Util.buscarElemento(listAuxOcupacion, new Ocupacion(idOcupacion)));
			// log.info("Ocupacion: " + individuoEvaluacion.getActividad().getOcupacion());
			// log.info("Ocupacion: " + individuoEvaluacion.getActividad().getOcupacion().getDescripcion());
		}
	}


	public String getCargo() {
		if (individuoEvaluacion.getActividad() != null)
			return individuoEvaluacion.getActividad().getCargo();
		return null;
	}


	public void setCargo(String cargo) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setCargo(cargo);
	}


	public BigDecimal getSueldoNeto() {
		if (individuoEvaluacion.getActividad() != null)
			return individuoEvaluacion.getActividad().getSueldoNeto();
		return null;
	}


	public void setSueldoNeto(BigDecimal sueldoNeto) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setSueldoNeto(sueldoNeto);
	}


	public String getEmpleoAnterior() {
		if (individuoEvaluacion.getActividad() != null)
			return individuoEvaluacion.getActividad().getEmpleoAnterior();
		return null;
	}


	public void setEmpleoAnterior(String empleoAnterior) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setEmpleoAnterior(empleoAnterior);
	}


	public String getTelEmpleoAnt() {
		if (individuoEvaluacion.getActividad() != null)
			return individuoEvaluacion.getActividad().getTelEmpleoAnt();
		return null;
	}


	public void setTelEmpleoAnt(String telEmpleoAnt) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setTelEmpleoAnt(telEmpleoAnt);
	}


	public String getReferencias() {
		if (individuoEvaluacion.getActividad() != null)
			return individuoEvaluacion.getActividad().getReferencias();
		return null;
	}


	public void setReferencias(String referencias) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setReferencias(referencias);
	}


	public String getOtrosIngresosDesc() {
		if (individuoEvaluacion.getActividad() != null)
			return individuoEvaluacion.getActividad().getOtrosIngresosDesc();
		return null;
	}


	public void setOtrosIngresosDesc(String otrosIngresosDesc) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setOtrosIngresosDesc(otrosIngresosDesc);
	}


	public BigDecimal getOtrosIngresosMonto() {
		if (individuoEvaluacion.getActividad() != null)
			return individuoEvaluacion.getActividad().getOtrosIngresosMonto();
		return null;
	}


	public void setOtrosIngresosMonto(BigDecimal otrosIngresosMonto) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setOtrosIngresosMonto(otrosIngresosMonto);
		refrescarAct = true;
	}


	public Long getIdDomicilioPagoSeleccionado() {
		return idDomicilioPagoSeleccionado;
	}


	public void setIdDomicilioPagoSeleccionado(Long idDomicilioPagoSeleccionado) {
		this.idDomicilioPagoSeleccionado = idDomicilioPagoSeleccionado;
	}


	public HtmlSelectOneMenu getDiasPagos() {
		return diasPagos;
	}


	public void setDiasPagos(HtmlSelectOneMenu diasPagos) {
		this.diasPagos = diasPagos;
	}


	public Long getIdDiaPagoSeleccionado() {
		return idDiaPagoSeleccionado;
	}


	public void setIdDiaPagoSeleccionado(Long idDiaPagoSeleccionado) {
		this.idDiaPagoSeleccionado = idDiaPagoSeleccionado;
	}


	public List getListDiaPago() {
		return listDiaPago;
	}


	public void setListDiaPago(List listDiaPago) {
		this.listDiaPago = listDiaPago;
	}


	public Long getIdBcoSeleccionado() {
		return idBcoSeleccionado;
	}


	public void setIdBcoSeleccionado(Long idBcoSeleccionado) {
		this.idBcoSeleccionado = idBcoSeleccionado;
		if (this.idBcoSeleccionado != null && !this.idBcoSeleccionado.equals(new Long(0))) {
			if (!listAuxBancos.isEmpty()) {
				Iterator iterator = listAuxBancos.iterator();
				while (iterator.hasNext()) {
					Banco element = (Banco) iterator.next();
					if (element.getIdBanco().equals(idBcoSeleccionado)) {
						// textBanco.setValue(element.getDescripcion());
					}
				}
			}
		}
	}


	public List getListBancos() {
		return listBancos;
	}


	public void setListBancos(List listBancos) {
		this.listBancos = listBancos;
	}


	public boolean isMostrarCbu() {
		// log.info("Ejecutando ==>getMostrarCBU(). mostrarCbu: " + mostrarCbu);
		// if (esCBU) {
		// setIdBcoSeleccionado(new Long(0));
		// idTipoCuentaSeleccionado = new Long(0);
		// nombreBanco = "";
		// textBanco.setValue("");
		// magia.setComponente20(esCBU);
		// }
		// nombreBanco = bancos.getBanco().getDescripcion();
		if (editFinanciero)
			return esCBU;
		else
			return true;
	}


	public String getNroCBU() {
		return nroCBU;
	}


	public void setNroCBU(String nroCBU) {
		this.nroCBU = nroCBU;
	}


	public boolean getEsCBU() {
		if (esCBU) {
			// setIdBcoSeleccionado(new Long(0));
			// idTipoCuentaSeleccionado = new Long(0);
			// nombreBanco = "";
			// textBanco.setValue("");
			// magia.setComponente20(esCBU);
		} else {
			// nombreBanco = bancos.getBanco().getDescripcion();
			// magia.setComponente20(esCBU);
		}

		return esCBU;
	}


	public void setEsCBU(boolean esCBU) {
		this.esCBU = esCBU;
	}


	public Long getIdTipoCuentaSeleccionado() {
		return idTipoCuentaSeleccionado;
	}


	public void setIdTipoCuentaSeleccionado(Long idTipoCuentaSeleccionado) {
		this.idTipoCuentaSeleccionado = idTipoCuentaSeleccionado;
	}


	public List getListTipoCtas() {
		return listTipoCtas;
	}


	public void setListTipoCtas(List listTipoCtas) {
		this.listTipoCtas = listTipoCtas;
	}


	public Bancos getBancos() {
		return bancos;
	}


	public void setBancos(Bancos bancos) {
		this.bancos = bancos;
	}


	public Tarjeta getTarjeta() {
		return tarjeta;
	}


	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}


	public Long getIdBancoSeleccionado() {
		return idBancoSeleccionado;
	}


	public void setIdBancoSeleccionado(Long idBancoSeleccionado) {
		this.idBancoSeleccionado = idBancoSeleccionado;
	}


	public List getListDomicilioInmueble() {
		return listDomicilioInmueble;
	}


	public void setListDomicilioInmueble(List listDomicilioInmueble) {
		this.listDomicilioInmueble = listDomicilioInmueble;
	}


	public boolean isVehiculoPropio() {
		if (individuoEvaluacion.getVehiculoPropio() != null)
			return Convertidores.getBoolean(individuoEvaluacion.getVehiculoPropio());
		else
			return false;
	}


	public void setVehiculoPropio(boolean vehiculoPropio) {
		individuoEvaluacion.setVehiculoPropio(Convertidores.getSorN(vehiculoPropio));
	}


	public IndividuoVehiculo getIndividuoVehiculo() {
		return individuoVehiculo;
	}


	public void setIndividuoVehiculo(IndividuoVehiculo individuoVehiculo) {
		this.individuoVehiculo = individuoVehiculo;
	}


	public List getListTipoDigital() {
		return listTipoDigital;
	}


	public void setListTipoDigital(List listTipoDigital) {
		this.listTipoDigital = listTipoDigital;
	}


	public Long getIdTipoDocSeleccionado() {
		return idTipoDocSeleccionado;
	}


	public void setIdTipoDocSeleccionado(Long idTipoDocSeleccionado) {
		this.idTipoDocSeleccionado = idTipoDocSeleccionado;
	}


	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}


	public Digital getDocDigital() {
		return docDigital;
	}


	public void setDocDigital(Digital docDigital) {
		this.docDigital = docDigital;
	}


	public boolean isBotonAdjuntar() {
		if (editDigitales)
			return botonAdjuntar;
		else
			return true;
	}


	public void setBotonAdjuntar(boolean botonAdjuntar) {
		this.botonAdjuntar = botonAdjuntar;
	}


	public List getListTipoDocumentos() {
		return listTipoDocumentos;
	}


	public void setListTipoDocumentos(List listTipoDocumentos) {
		this.listTipoDocumentos = listTipoDocumentos;
	}


	/** Accesos a los parametro de visualizacion */

	public boolean isEditDomicilio() {
		return editDomicilio;
	}


	public boolean isEditDatos() {
		return editDatos;
	}


	public void setEditDatos(boolean editDatos) {
		this.editDatos = editDatos;
	}


	public void setEditDomicilio(boolean editDomicilio) {
		this.editDomicilio = editDomicilio;
	}


	public boolean isEditTelefono() {
		return editTelefono;
	}


	public void setEditTelefono(boolean editTelefono) {
		this.editTelefono = editTelefono;
	}


	public boolean isEditEmail() {
		return editEmail;
	}


	public void setEditEmail(boolean editEmail) {
		this.editEmail = editEmail;
	}


	public boolean isEditFamilia() {
		return editFamilia;
	}


	public void setEditFamilia(boolean editFamilia) {
		this.editFamilia = editFamilia;
	}


	public boolean isEditActividad() {
		return editActividad;
	}


	public void setEditActividad(boolean editActividad) {
		this.editActividad = editActividad;
	}


	public boolean isEditFacturacion() {
		return editFacturacion;
	}


	public void setEditFacturacion(boolean editFacturacion) {
		this.editFacturacion = editFacturacion;
	}


	public boolean isEditFinanciero() {
		return editFinanciero;
	}


	public void setEditFinanciero(boolean editFinanciero) {
		this.editFinanciero = editFinanciero;
	}


	public boolean isEditPropiedades() {
		return editPropiedades;
	}


	public void setEditPropiedades(boolean editPropiedades) {
		this.editPropiedades = editPropiedades;
	}


	public boolean isEditDigitales() {
		return editDigitales;
	}


	public void setEditDigitales(boolean editDigitales) {
		this.editDigitales = editDigitales;
	}


	public int getOrigen() {
		return origen;
	}


	public void setOrigen(int origen) {
		this.origen = origen;
	}


	public boolean isVengoDeComercio() {
		if (origen == COD_COMERCIO)
			return true;
		return false;
	}


	public void setVengoDeComercio(boolean vengoDeComercio) {
		this.vengoDeComercio = vengoDeComercio;
	}


	public Integer getDiaPagoSeleccionadoInfo() {
		return diaPagoSeleccionadoInfo;
	}


	public void setDiaPagoSeleccionadoInfo(Integer diaPagoSeleccionadoInfo) {
		this.diaPagoSeleccionadoInfo = diaPagoSeleccionadoInfo;
	}


	public void setIdDiaPagoSeleccionadoAnterior(
			Long idDiaPagoSeleccionadoAnterior) {
		this.idDiaPagoSeleccionadoAnterior = idDiaPagoSeleccionadoAnterior;
	}


	public Long getIdDiaPagoSeleccionadoAnterior() {
		return idDiaPagoSeleccionadoAnterior;
	}


	public String getNota_cred() {
		return nota_cred;
	}


	public void setNota_cred(String nota_cred) {
		this.nota_cred = nota_cred;
	}

}
