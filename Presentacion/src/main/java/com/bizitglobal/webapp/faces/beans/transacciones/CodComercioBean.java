package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.File;
import java.io.IOException;
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
import java.util.Set;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Archivo;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ActividadEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ActividadEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Digital;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Emails;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Telefonos;
import com.bizitglobal.tarjetafiel.general.exception.EmpresaException;
import com.bizitglobal.tarjetafiel.general.negocio.Empresa;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistemaDetalle;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmail;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresasXDomicilio;
import com.bizitglobal.tarjetafiel.general.negocio.SucTelefono;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDigital;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuitNoValidoException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuitValido;
import com.bizitglobal.tarjetafiel.transacciones.exception.CodComercioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ComercioFormaPagoException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioFormaPago;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioListaPrecio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.DigitalComercio;
import com.bizitglobal.tarjetafiel.transacciones.service.CodComercioService;
import com.bizitglobal.tarjetafiel.transacciones.service.ComercioFormaPagoService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.service.proveedores.ProveedoresServiceFaces;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.ImpuestoEditable;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.webapp.faces.beans.evaluacion.Util.IndividuoEvaluacionUtil;
import com.bizitglobal.webapp.faces.beans.evaluacion.wrappers.WrapperFile;
import com.bizitglobal.webapp.faces.beans.general.EmpresaBean;
import com.bizitglobal.webapp.faces.beans.proveedores.ComprobanteUtil;
import com.bizitglobal.webapp.faces.beans.proveedores.FormaDePagoBean;
import com.bizitglobal.webapp.faces.beans.transacciones.popup.IndividuoPopupBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;


@SuppressWarnings({"rawtypes","unchecked"})
public class CodComercioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(CodComercioBean.class);
	private static int posicionListaAutorizados = 0;
	private static int posicionListaResponsables = 0;
	private static int posicionListaPosibles = 0;
	private static int posicionListaPrecio = 0;
	private static int posicionFormaPago = 0;
	private static int LIQUIDA_POR_CUIT = 1; // 1:liquidacion por cuit;
	private static int LIQUIDA_POR_COD_COM = 2; // 2:liquidacion por codComercio

	private Long idCodComercioHidden;
	private CodComercio codComercio;
	private CodComercio codcomercioFiltro;
	// definicion de un list del objeto base
	private List codcomercioList;

	private ProveedoresServiceFaces service;

	// Listas para la presentacion(HtmlSelectItems).
	private List comercioList = new ArrayList();
	private List comercioItems = new ArrayList();
	private List estadoItems = new ArrayList();
	private List listaDeSucursalesFiel;
	private Long cuitIndividuoAAgregar;

	// listas de autorizados y responsables, contienen objetos de este mismo nombre
	private List individuosRelacionados; // todos los individuos que pueden o ya son responsables o autorizados
	private List listaAutorizados; // wrapperAutorizados
	private List listaResponsables; // wrapperResponsables
	private List listaPosiblesIndividuos;

	// Objetos Relacionados.
	private Long idSucursalSeleccionada;

	private Long idTipoLiqSeleccionada;

	private boolean empresaCargada = false;
	private Empresa empresa;
	private List sucursales, listSucEmp;
	private SucEmpresa sucEmpresa;
	private HtmlSelectOneMenu sucursalHtml;
	private HtmlSelectOneMenu tipoLiqHtml;
	private String telefonoSucursal;
	private boolean sucursalCargada = false;
	private String focoHidden;

	// objetos necesarios para los metodos de pago
	private List tablaDeFormaDePago;
	// private List temporalFP = new ArrayList(); // Contiene las formas de pago eliminadas.
	private CuitValido cuitValido;
	private boolean empresaExistente;
	private IndividuoEvaluacion individuoEvaluacion;
	// objetos necesarios para las listas de precios
	private List listaDePrecios; // contiene wrapperListaPrecios;
	// objetos para impuestos
	private Long jurisdiccionSeleccionada;
	private HtmlSelectOneMenu jurisSelecItem;
	private List tablaDeImpuestos;
	private String inscripcionDgr;
	private List listaDeJurisdicciones;
	// para habilitar boton agregar fma Pago
	private boolean habilitaAgregar;
	private boolean habilitaQuitar;

	private boolean mostrarMsgConfCambioFmaPago;
	
	private List listTipoDocumentos;
	
	// objetos para cargar archivos con documentacion
	private Long sucursalFielSeleccionda;
	private boolean debitoAutomatico;
	private boolean presentaCupones;
	/*
	 * @I4039 private boolean excluyeCargos; Codigo vuelto atras por pedido del cliente
	 * 
	 * @F4039
	 */private String urlArchivo;
	private String urlArchivoPresenta;
	private UploadedFile imagen;
	
	private UploadedFile uploadedFile;
	
	private UploadedFile imagenDebito;
	private UploadedFile imagenPresenta;
	private String pathDebito;
	private boolean mostraArchivosAdjuntos;
	private boolean mostraTablaArchivosAdjuntosDebito;
	private List listArchivosAdjuntosDebito;

	private boolean confirmaCambioLiq;
	private boolean refrescarSucursal;

	// atributos necesarios para los parametros de liquidacon metodos de pago
	private boolean imprimirLiquidacion;
	private boolean generarPDF;
	private boolean enviarMail;
	private String tipoLiquidacion;
	private List listTipoLiq = new ArrayList();
	private boolean listaVaciaFmaPagoCuit;// indica si una empresa que liquida por cuit(y por lo tanto todas sus sucursales) ya tienen asociada una
											// fma de pago.
	private boolean mostrarFmaPagoCuit;

	private ComercioListaPrecio comercioListaSelected;

	private boolean edicion = false;
	private boolean altaNavegacion = false;


	// //objetos para dar de alta los individuos;
	// private boolean altaIndividuo = false;
	// private List listTipoDni;
	// private List listTipoSexo;
	// private Long idTipoSexoSeleccionado;
	// private List listTelefono;
	// private List listOcupacion;
	// private HtmlInputCalendar fechaIngreso = new HtmlInputCalendar();
	// private HtmlSelectOneMenu ocupacionHtml = new HtmlSelectOneMenu();
	// /* Se utiliza para despues poder validar el mail */
	// private String email;
	// /* Se utiliza para la fecha de nacimeinto del individuo */
	// private Date fechaNacimiento;
	// /* Se utiliza para la fecha de ingreso laboral */
	// private Date ingreso;
	// /* Se utiliza para calcular al antiguedad del individuo */
	// private String antiguedad;
	// private List listAuxOcupacion;

	public Long getSucursalFielSeleccionda() {
		return sucursalFielSeleccionda;
	}


	public void setSucursalFielSeleccionda(Long sucursalFielSeleccionda) {
		this.sucursalFielSeleccionda = sucursalFielSeleccionda;
	}


	public String getInscripcionDgr() {
		return inscripcionDgr;
	}


	public void setInscripcionDgr(String inscripcionDgr) {
		if (refrescarSucursal)
			this.inscripcionDgr = inscripcionDgr;
		refrescarSucursal = true;
	}


	public CodComercioBean() {
		super();
		estadoItems = Util.cargarSelectItemMascara(CodComercio.estadoStaticList);
		this.tablaDeImpuestos = null;
		// cargarItems();
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public CodComercio getCodComercio() {
		return codComercio;
	}


	public void setCodComercio(CodComercio codComercio) {
		this.codComercio = codComercio;
	}


	public Long getIdCodComercioHidden() {
		return idCodComercioHidden;
	}


	public void setIdCodComercioHidden(Long idCodComercioHidden) {
		this.idCodComercioHidden = idCodComercioHidden;
	}


	public List getComercioItems() {
		return comercioItems;
	}


	public void setComercioItems(List comercioItems) {
		this.comercioItems = comercioItems;
	}


	public Long getIdSucursalSeleccionada() {
		return idSucursalSeleccionada;
	}


	public void setIdSucursalSeleccionada(Long idSucursalSeleccionada) {
		this.idSucursalSeleccionada = idSucursalSeleccionada;
	}


	public List getCodComercioList() {
		return codcomercioList;
	}


	public void setCodComercioList(List object) {
		this.codcomercioList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public HtmlSelectOneMenu getJurisSelecItem() {
		return jurisSelecItem;
	}


	public void setJurisSelecItem(HtmlSelectOneMenu jurisSelecItem) {
		this.jurisSelecItem = jurisSelecItem;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CODCOMERCIO
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		service = new ProveedoresServiceFaces();
		this.tablaDeFormaDePago = new ArrayList();
		this.listaDeJurisdicciones = CodComercioUtil.cargarListaJurisdicciones(impuestoService.getJurisdiccionDao());
		this.listaDeSucursalesFiel = CodComercioUtil.cargarListaSucursalesFiel(generalService.getSucursalDao());

		return "amCodComercio";
	}


	private void cargarItems() {
		if (comercioItems.size() != comercioList.size()) {
			comercioItems = new ArrayList();
			comercioItems.add(new SelectItem(new Long(0), "Seleccione Comercio"));
			comercioItems.addAll(Util.cargarSelectItem(comercioList));
		}
	}


	public String irAgregarListaPrecio() {
		ComercioListaPrecioBean bean = (ComercioListaPrecioBean) Session.getBean("ComercioListaPrecioBean");
		bean.inicializarDesdePopup(new ComercioListaPrecio(), listaDePrecios, true);
		return null;
	}


	public String grabarDesdePopup() {
		try {
			Iterator iter = listaPosiblesIndividuos.iterator();
			while (iter.hasNext()) {
				WrapperIndividuoLibre ind = (WrapperIndividuoLibre) iter.next();
				IndividuoEvaluacion individuo = null;
				boolean fueModificado = false;
				individuo = recuperarIndividuo(ind.getCuil());
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("cuil", Filtro.LIKEXS, individuo.getCuil());
				filtro.agregarCampoOperValor("sucEmpresa.idSucEmpresa", Filtro.IGUAL, sucEmpresa.getIdSucEmpresa());
				List actividades = evaluacionService.getActividadEvaluacionService().getActividad(filtro);
				ActividadEvaluacion actividad = (ActividadEvaluacion) actividades.get(0);
				if (ind.isPermitoAutorizado() && ind.isParaAutorizado() && ind.isPermitoResponsable() && ind.isParaResponsable()) {
					actividad.setTipo("X");
					listaResponsables.add(new WrapperIndividuo(individuo, WrapperIndividuo.RESPONSABLE, true, actividad));
					listaAutorizados.add(new WrapperIndividuo(individuo, WrapperIndividuo.AUTORIZADO, true, actividad));
					fueModificado = true;
				} else {
					if (ind.isPermitoAutorizado() && ind.isParaAutorizado()) {
						fueModificado = true;
						if (ind.isPermitoResponsable()) {
							actividad.setTipo("A");
						} else {
							actividad.setTipo("X");
						}
						listaAutorizados.add(new WrapperIndividuo(individuo, WrapperIndividuo.AUTORIZADO, true, actividad));
					}
					if (ind.isPermitoResponsable() && ind.isParaResponsable()) {
						fueModificado = true;
						if (ind.isPermitoAutorizado()) {
							actividad.setTipo("R");
						} else {
							actividad.setTipo("X");
						}
						listaResponsables.add(new WrapperIndividuo(individuo, WrapperIndividuo.RESPONSABLE, true, actividad));
					}
				}
				if (fueModificado) {
					evaluacionService.getActividadEvaluacionService().actualizarActividad(actividad);
				}
			}
		} catch (ActividadEvaluacionException e) {
			e.printStackTrace();
		}
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.opener.recargar(); window.close();";
		ejecutarJavaScript(javaScriptText);
		return null;
	}


	public String cancelarDesdePopup() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.opener.recargar(); window.close();";
		ejecutarJavaScript(javaScriptText);

		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.opener.recargar(); window.close();";
		ejecutarJavaScript(javaScriptText);
	}


	public String eliminarAutorizado() {
		String result = null;
		error.borrar();
		log.info(" Ejecutando ==> eliminarAutorizado()");
		Long idAutorizado = new Long(Session.getRequestParameter("idAutorizadoElim"));
		Iterator iterAutorizados = listaAutorizados.iterator();
		while (iterAutorizados.hasNext()) {
			WrapperIndividuo ind = (WrapperIndividuo) iterAutorizados.next();
			if (ind.getIndice() == idAutorizado.intValue()) {
				IndividuoEvaluacion indAModificar = recuperarIndividuo(ind.getCuil());
				ActividadEvaluacion actividad = null;
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("cuil", Filtro.LIKEXS, indAModificar.getCuil());
				filtro.agregarCampoOperValor("sucEmpresa.idSucEmpresa", Filtro.IGUAL, sucEmpresa.getIdSucEmpresa());
				try {
					List actividades = evaluacionService.getActividadEvaluacionService().getActividad(filtro);
					actividad = (ActividadEvaluacion) actividades.get(0);
				} catch (ActividadEvaluacionException e) {
					e.printStackTrace();
				}
				if (actividad.getTipo().compareTo("X") == 0) {
					actividad.setTipo("R");
				} else {
					actividad.setTipo("E");
				}
				try {
					if (!ind.isSoyNuevo()) {
						evaluacionService.getActividadEvaluacionService().actualizarActividad(actividad);
					}
					listaAutorizados.remove(ind);
				} catch (ActividadEvaluacionException e2) {
					e2.printStackTrace();
				}
				break;
			}
		}
		log.info("Id Autorizado Eliminado: " + idAutorizado);
		return result;
	}


	public String eliminarResponsable() {
		String result = null;
		error.borrar();
		log.info(" Ejecutando ==> eliminarResponsable()");
		Long idResponsable = new Long(Session.getRequestParameter("idResponsableElim"));
		Iterator iterResponsables = listaResponsables.iterator();
		while (iterResponsables.hasNext()) {
			WrapperIndividuo ind = (WrapperIndividuo) iterResponsables.next();
			if (ind.getIndice() == idResponsable.intValue()) {
				IndividuoEvaluacion indAModificar = recuperarIndividuo(ind.getCuil());
				ActividadEvaluacion actividad = null;
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("cuil", Filtro.LIKEXS, indAModificar.getCuil());
				filtro.agregarCampoOperValor("sucEmpresa.idSucEmpresa", Filtro.IGUAL, sucEmpresa.getIdSucEmpresa());
				try {
					List actividades = evaluacionService.getActividadEvaluacionService().getActividad(filtro);
					actividad = (ActividadEvaluacion) actividades.get(0);
				} catch (ActividadEvaluacionException e) {
					e.printStackTrace();
				}
				if (actividad.getTipo().compareTo("X") == 0) {
					actividad.setTipo("A");
				} else {
					actividad.setTipo("E");
				}
				try {
					if (!ind.isSoyNuevo()) {
						evaluacionService.getActividadEvaluacionService().actualizarActividad(actividad);
					}
					listaResponsables.remove(ind);
				} catch (ActividadEvaluacionException e2) {
					e2.printStackTrace();
				}
				break;
			}
		}
		log.info("Id Responsable Eliminado: " + idResponsable);
		return result;
	}


	public IndividuoEvaluacion recuperarIndividuo(String nroCuil) {
		IndividuoEvaluacion indBuscado = null;
		Iterator i = individuosRelacionados.iterator();
		while (i.hasNext()) {
			IndividuoEvaluacion ind = (IndividuoEvaluacion) i.next();
			if (ind.getCuil().compareTo(nroCuil) == 0) {
				indBuscado = ind;
				break;
			}
		}
		return indBuscado;
	}


	public String modificarDatosEmpresa() {
		Empresa emp = null;
		EmpresaBean beanEmpresa = (EmpresaBean) Session.getBean("EmpresaBean");			
		
			
		try {
			beanEmpresa.setOrigen(1);
			beanEmpresa.setCuitEditado(empresa.getCuit().toString());
			beanEmpresa.setAltaNavegacion(altaNavegacion);
			List empresas = generalService.getEmpresaService().getEmpresa(new Filtro("cuit", Filtro.IGUAL, empresa.getCuit()));
			if (!empresas.isEmpty()) {
				Iterator iterEmp = empresas.iterator();
				while (iterEmp.hasNext()) {
					Empresa empresita = (Empresa) iterEmp.next();
					if (empresita.getCuit().equals(empresa.getCuit())) {
						emp = empresita;
						// emp.getRubro();
						emp.getTamEmpresa();
						Iterator iter = emp.getSucEmpresas().iterator();
						while (iter.hasNext()) {
							SucEmpresa sucEmpresa = (SucEmpresa) iter.next();
							log.info("La sucEmpresa seleccionada tiene id: " + sucEmpresa.getIdSucEmpresa());
							
							if ( sucEmpresa.getDomicilio() != null) {
								sucEmpresa.getDomicilio().getLocalidad().getNombre();
								sucEmpresa.getDomicilio().getBarrio().getLocalidad().getPartido().getProvincia().getPais();
								sucEmpresa.getDomicilio().getTipoDomicilio();
								sucEmpresa.getDomicilio().getTipoVivienda();
								sucEmpresa.getDomicilio().getPropVivienda();
								sucEmpresa.getAutonomo();								
							}
							
							Iterator itDom = sucEmpresa.getSucEmpresaXDomicilio().iterator();
							while (itDom.hasNext()) {
								SucEmpresasXDomicilio sucXDom = (SucEmpresasXDomicilio) itDom.next();
								sucXDom.getDomicilio().getBarrio().getDescripcion();
								sucXDom.getDomicilio().getLocalidad().getPartido().getProvincia().getPais();
								sucXDom.getDomicilio().getTipoVivienda();
								sucXDom.getDomicilio().getPropVivienda();
							}
							Iterator it = sucEmpresa.getSucTelefonos().iterator();
							while (it.hasNext()) {
								SucTelefono tel = (SucTelefono) it.next();
								tel.getTelefono().getTipo().getDescripcion();
							}
							Iterator itDos = sucEmpresa.getSucEmails().iterator();
							while (itDos.hasNext()) {
								SucEmail email = (SucEmail) itDos.next();
								email.getEmail().getEmail();
								email.getIdSucEmail();
							}
						}
						break;
					}
				}
			}
		} catch (EmpresaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassCastException e2) {
			e2.printStackTrace();
		}
		if (emp != null)
			beanEmpresa.inicializarDesdePopup(emp);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/empresa/empresaPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',900,700), 'titlebar=no';");
		return null;
	}


	public String verCtaCteCodComercio() {
		String result = null;
		CtaCteComercioBean ctaCteCodComercioBean = (CtaCteComercioBean) Session.getBean("CtaCteComercioBean");
		ctaCteCodComercioBean.listarCtaCteComercio(idCodComercioHidden);
		return result;
	}


	public String habilitarCargaDesdePopup() {
		// controlar sea numero de cuit válido
		error.borrar();

		if (sucEmpresa != null) {
			idSucursalSeleccionada = sucEmpresa.getIdSucEmpresa();
			sucursalHtml.setValue(idSucursalSeleccionada);
		}
		
////////////////////////////////////////////////////////
		
if (codComercio.getDocAdjuntos() != null) {

List arrayDocAdjuntos = Convertidores.setToList(codComercio.getDocAdjuntos());

Collections.sort(arrayDocAdjuntos, new Comparator() 
{
@Override
public int compare(Object o1, Object o2) {
return ((DigitalComercio) o1).getTimestamp().compareTo(((DigitalComercio) o2).getTimestamp());
}
});

for (int i = (arrayDocAdjuntos.size() - 1); i > -1; i--) {
DigitalComercio di = (DigitalComercio) arrayDocAdjuntos.get(i);
//Iterator iDocTit = individuoEvaluacion.getDocAdjuntos().iterator();
//while (iDocTit.hasNext()) {
//Digital di = (Digital) iDocTit.next();
WrapperFile wrapin = new WrapperFile();
wrapin.setDescripcion(di.getFechaFormateado());
wrapin.setIdDocumentoAdjunto(di.getIdDigital());
wrapin.setIdWrappers(new Long(di.hashCode()));
wrapin.setNombreArchivo(di.getUrl());
wrapin.setPath(di.getUrl());
wrapin.setTimestamp(di.getTimestamp());



listTipoDocumentos.add(wrapin);
}
} else {
listTipoDocumentos = new ArrayList();
}

////////////////////////////////////////////////

		
		
		
		
		
		idTipoLiqSeleccionada = empresa.getTipoLiquidacion();
		tipoLiqHtml.setValue(idTipoLiqSeleccionada);
		mostrarMsgConfCambioFmaPago = false;
		// sucursalHtml.setValue(new Long(idSucursalSeleccionada.longValue()));
		empresa.getRazonSocial();
		// empresa.getRubro().getDescripcion();
		sucursales = Convertidores.setToList(empresa.getSucEmpresas());
		/* @I5013 */SucEmpresa sucEmpresaLista = new SucEmpresa();
		if (!sucursales.isEmpty()) {
			Iterator iterator = sucursales.iterator();
			while (iterator.hasNext()) {
				sucEmpresaLista = (SucEmpresa) iterator.next();
				if (sucEmpresaLista.getDomicilio() != null) {
					sucEmpresaLista.getDomicilio().getCalleNombre();
					sucEmpresaLista.getDomicilio().getCalleNumero();
					sucEmpresaLista.getDomicilio().getBarrio().getDescripcion();
					sucEmpresaLista.getDomicilio().getLocalidad().getNombre();					
				}
				
				List listaTelefonos = Convertidores.setToList(sucEmpresaLista.getSucTelefonos());
				Iterator ite = listaTelefonos.iterator();
				while (ite.hasNext()) {
					telefonoSucursal = ((SucTelefono) (ite.next())).getTelefono().getNroTlefono();
				}
			}
			telefonoSucursal = "";
			try {
				CodComercio codComercioRefresh = transaccionesService.getCodComercioService().leerCodComercio(idCodComercioHidden);
				SucEmpresa sucEmpresaRefresh = codComercioRefresh.getSucEmpresa();
				sucEmpresa = sucEmpresaRefresh;
				List listaTelefonos = Convertidores.setToList(sucEmpresa.getSucTelefonos());
				Iterator ite = listaTelefonos.iterator();
				while (ite.hasNext()) {
					if (telefonoSucursal.trim() == "")
						telefonoSucursal = ((SucTelefono) (ite.next())).getTelefono().getNroTlefono();
					else
					{
						telefonoSucursal += " - " + ((SucTelefono) (ite.next())).getTelefono().getNroTlefono();
					}
				}
			} catch (CodComercioException e1) {
				error.agregar("Ocurrió un error al intentar refescar el Código Comercio");
				e1.printStackTrace();
				/* @F5013 */}
		} else {
			sucEmpresa = new SucEmpresa();
			telefonoSucursal = "";
		}
		if (edicion)
		{
			listSucEmp = IndividuoEvaluacionUtil.cargarSucursales(sucursales);
		}
		else
		{
			if (altaNavegacion)
			{
				List sucursalesSinPostent = this.cargarSucursalesSinPosnet();
				listSucEmp = IndividuoEvaluacionUtil.cargarSucursales(sucursalesSinPostent);
			}
			else
			{
				listSucEmp = IndividuoEvaluacionUtil.cargarSucursales(sucursales);
			}
		}
		empresaCargada = true;
		if (idTipoLiqSeleccionada.intValue() == LIQUIDA_POR_CUIT) {
			mostrarFmaPagoCuit = true;
			// una empresa que liquide por cuit sera aquella cuya forma de pago tenga el codComercio en null, esa fma de
			// pago sera aplicable a todas las sucursales q esten asociadas a la empresa.
			Filtro f = new Filtro();
			f.agregarCampoOperValor("codComercio.idCodComercio", Filtro.NULL, "");
			f.agregarCampoOperValor("empresa.idEmpresa", Filtro.IGUAL, empresa.getIdEmpresa());

			List fmaPago;
			try {
				fmaPago = transaccionesService.getComercioFormaPagoService().getComercioFormaPago(f);
				// ahora buscamos en la tabla fma de pagos si la empresa ya tiene una asociada, de ser asi esa fma de pago
				// sera mostrada y en caso de modificarla sera modificado ese mismo registro no creando una nuevo
				Iterator iter = fmaPago.iterator();
				while (iter.hasNext()) {
					ComercioFormaPago comForPago = (ComercioFormaPago) iter.next();
					comForPago.getFormaPago().getIdFormaPago();
					Util.limpiarLista(tablaDeFormaDePago);
					tablaDeFormaDePago.add(new WrapperFormaPago(comForPago, false, empresa));
					System.out.println("hay una forma de pago cargada asociada a la empresa");
					listaVaciaFmaPagoCuit = false;
				}

			} catch (ComercioFormaPagoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (tablaDeFormaDePago == null) {
				habilitaAgregar = false;
				habilitaQuitar = true;

			}
			else {
				if (tablaDeFormaDePago != null) {
					if (tablaDeFormaDePago.size() > 0) {
						habilitaAgregar = true;
						habilitaQuitar = false;

					} else {
						habilitaAgregar = false;
						habilitaQuitar = true;

					}
				}
			}

		}

		return null;
	}


	public String habilitarCarga() {
		// controlar sea numero de cuit válido
		error.borrar();
		listaVaciaFmaPagoCuit = true;
		if (empresa.getCuit() != null && empresa.getCuit().toString().compareTo("") != 0 && empresa.getCuit().toString().length() == 11) {
			if (validarCuit(empresa.getCuit().toString())) {
				try {

					idSucursalSeleccionada = new Long(0);
					sucursalHtml.setValue(new Long(0));
					List listaEmpresa = generalService.getEmpresaService().getEmpresa(new Filtro("cuit", Filtro.IGUAL, empresa.getCuit()));
					empresa = (Empresa) listaEmpresa.get(0);
					empresa.getRazonSocial();
					// empresa.getRubro().getDescripcion();
					idTipoLiqSeleccionada = empresa.getTipoLiquidacion();
					tipoLiqHtml.setValue(idTipoLiqSeleccionada);
					if (empresa.getImprimirLiquidacion() != null && empresa.getImprimirLiquidacion().charValue() == 'S')
						imprimirLiquidacion = true;
					else
						imprimirLiquidacion = false;
					if (empresa.getImprimirLiquidacion() != null && empresa.getGenerarPDF().charValue() == 'S')
						generarPDF = true;
					else
						generarPDF = false;
					if (empresa.getEnviarMail() != null && empresa.getEnviarMail().charValue() == 'S')
						enviarMail = true;
					else
						enviarMail = false;

					sucursales = Convertidores.setToList(empresa.getSucEmpresas());
					if (!sucursales.isEmpty()) {
						Iterator iterator = sucursales.iterator();
						while (iterator.hasNext()) {
							sucEmpresa = (SucEmpresa) iterator.next();
							if (sucEmpresa.getDomicilio() != null) {
							sucEmpresa.getDomicilio().getCalleNombre();
							sucEmpresa.getDomicilio().getCalleNumero();
							sucEmpresa.getDomicilio().getBarrio().getDescripcion();
							sucEmpresa.getDomicilio().getLocalidad().getNombre();
							sucEmpresa.getDescripcion();
							}
							
							List listaTelefonos = Convertidores.setToList(sucEmpresa.getSucTelefonos());
							Iterator ite = listaTelefonos.iterator();
							while (ite.hasNext()) {
								telefonoSucursal = ((SucTelefono) (ite.next())).getTelefono().getNroTlefono();
							}
						}
					} else {
						sucEmpresa = new SucEmpresa();
						telefonoSucursal = "";
					}
					List sucursalesSinPostent = this.cargarSucursalesSinPosnet();
					listSucEmp = IndividuoEvaluacionUtil.cargarSucursales(sucursalesSinPostent);
					// listSucEmp = IndividuoEvaluacionUtil.cargarSucursales(sucursales);

					empresaCargada = true;
					edicion = false;
					altaNavegacion = true;
					// aca cargamos los datos de la forma de pago que seran aplicadas a todos los codComercio de la Empresa
					// en cuestion, en el caso de que se liquide por cuit
					if (idTipoLiqSeleccionada.intValue() == LIQUIDA_POR_CUIT) {
						mostrarFmaPagoCuit = true;
						// una empresa que liquide por cuit sera aquella cuya forma de pago tenga el codComercio en null, esa fma de
						// pago sera aplicable a todas las sucursales q esten asociadas a la empresa.
						Filtro f = new Filtro();
						f.agregarCampoOperValor("codComercio.idCodComercio", Filtro.NULL, "");
						f.agregarCampoOperValor("empresa.idEmpresa", Filtro.IGUAL, empresa.getIdEmpresa());

						List fmaPago;
						try {
							fmaPago = transaccionesService.getComercioFormaPagoService().getComercioFormaPago(f);
							// ahora buscamos en la tabla fma de pagos si la empresa ya tiene una asociada, de ser asi esa fma de pago
							// sera mostrada y en caso de modificarla sera modificado ese mismo registro no creando una nuevo
							Iterator iter = fmaPago.iterator();
							while (iter.hasNext()) {
								ComercioFormaPago comForPago = (ComercioFormaPago) iter.next();
								comForPago.getFormaPago().getIdFormaPago();
								Util.limpiarLista(tablaDeFormaDePago);
								tablaDeFormaDePago.add(new WrapperFormaPago(comForPago, false, empresa));
								System.out.println("hay una forma de pago cargada asociada a la empresa");
								listaVaciaFmaPagoCuit = false;
							}

						} catch (ComercioFormaPagoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (tablaDeFormaDePago == null) {
							habilitaAgregar = false;
							habilitaQuitar = true;

						}
						else {
							if (tablaDeFormaDePago != null) {
								if (tablaDeFormaDePago.size() > 0) {
									habilitaAgregar = true;
									habilitaQuitar = false;

								} else {
									habilitaAgregar = false;
									habilitaQuitar = true;

								}
							}
						}

					}

				} catch (EmpresaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IndexOutOfBoundsException e2) {
					empresaExistente = true;

				}
			}
		} else {
			error.agregar("El número de cuit debe tener 11 dígitos");
		}
		return null;
	}


	public String habilitarNuevaCarga() {
		borrar();
		empresaCargada = false;
		edicion = false;
		return null;
	}


	public String cambioLiquidacion() {
		confirmaCambioLiq = false;

		idTipoLiqSeleccionada = (Long) tipoLiqHtml.getValue();
		if (idTipoLiqSeleccionada.longValue() != 0) {

			try {
				empresa.setTipoLiquidacion(idTipoLiqSeleccionada);
				generalService.getEmpresaService().actualizarEmpresa(empresa);
			} catch (EmpresaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (idTipoLiqSeleccionada.longValue() == CodComercioBean.LIQUIDA_POR_COD_COM) {
				if (tablaDeFormaDePago != null && idSucursalSeleccionada.longValue() == 0)
					tablaDeFormaDePago.clear();
				mostrarFmaPagoCuit = false;
				if (idSucursalSeleccionada.longValue() != 0)
					sucursalCargada = true;
			}

			if (idTipoLiqSeleccionada.longValue() == CodComercioBean.LIQUIDA_POR_CUIT) {
				listaVaciaFmaPagoCuit = true;
				Filtro f = new Filtro();
				f.agregarCampoOperValor("empresa.idEmpresa", Filtro.IGUAL, empresa.getIdEmpresa());
				mostrarFmaPagoCuit = true;
				List fmaPago;
				try {
					fmaPago = transaccionesService.getComercioFormaPagoService().getComercioFormaPago(f);

					Iterator iter = fmaPago.iterator();
					while (iter.hasNext()) {
						ComercioFormaPago comForPago = (ComercioFormaPago) iter.next();
						comForPago.getFormaPago().getIdFormaPago();
						if (tablaDeFormaDePago == null)
							tablaDeFormaDePago = new ArrayList();
						else
							tablaDeFormaDePago.clear();
						tablaDeFormaDePago.add(new WrapperFormaPago(comForPago, false, empresa));
						System.out.println("hay una forma de pago cargada asociada a la empresa");
						listaVaciaFmaPagoCuit = false;
					}

				} catch (ComercioFormaPagoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return null;
	}


	public String cancelarCambioLiquidacion() {
		idTipoLiqSeleccionada = (Long) tipoLiqHtml.getValue();
		if (idTipoLiqSeleccionada.longValue() != 0)
			confirmaCambioLiq = false;
		if (idTipoLiqSeleccionada.longValue() == 1)
			tipoLiqHtml.setValue(new Long(2));
		else
			tipoLiqHtml.setValue(new Long(1));
		return null;
	}


	public String darAltaEmpresa() {
		empresaExistente = false;
		altaNavegacion = true;
		EmpresaBean beanEmpresa = (EmpresaBean) Session.getBean("EmpresaBean");
		beanEmpresa.inicializarNuevaEmpresa(empresa.getCuit(), EmpresaBean.CODCOMERCIO);
		return null;
	}


	public String cancelarAltaEmpresa() {
		empresaExistente = false;
		return null;
	}


	public String editarCodComercio() {
		String result = null;
		inicializar();
		alta = false;
		altaNavegacion = false;
		refrescarSucursal = true;
		tituloCorto = "Modificar Código Comercio";
		edicion = true;
		try {
			cargarItems();
			codComercio = transaccionesService.getCodComercioService().leerCodComercio(idCodComercioHidden);
			sucEmpresa = codComercio.getSucEmpresa();
			empresa = sucEmpresa.getEmpresa();
			if (empresa.getImprimirLiquidacion() != null && empresa.getImprimirLiquidacion().charValue() == 'S')
				imprimirLiquidacion = true;
			else
				imprimirLiquidacion = false;
			if (empresa.getImprimirLiquidacion() != null && empresa.getGenerarPDF().charValue() == 'S')
				generarPDF = true;
			else
				generarPDF = false;
			if (empresa.getEnviarMail() != null && empresa.getEnviarMail().charValue() == 'S')
				enviarMail = true;
			else
				enviarMail = false;

			habilitarCargaDesdePopup();
			/*
			 * if(codComercio.getDebitoAutomatico()!=null && codComercio.getDebitoAutomatico().toString().equals("S")) debitoAutomatico=true;
			 * if(codComercio.getPresentaCupones()!=null && codComercio.getPresentaCupones().toString().equals("S")) presentaCupones=true;
			 */
			urlArchivo = codComercio.getUrl();
			urlArchivoPresenta = codComercio.getUrlPresenta();

			this.listaDeSucursalesFiel = CodComercioUtil.cargarListaSucursalesFiel(generalService.getSucursalDao());
			cambioSucursal();
			result = "amCodComercio";
		} catch (CodComercioException e1) {
			error.agregar("Ocurrió un error al intentar editar el Código Comercio");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarCodComercio.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrió un error al intentar editar el Código Comercio");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarCodComercio.jsf");
		}
		return result;
	}


	public String eliminarCodComercio() {
		try {
			transaccionesService.getCodComercioService().borrarCodComercio(idCodComercioHidden);
			codcomercioList.remove(new CodComercio(idCodComercioHidden));
		} catch (CodComercioException e1) {
			error.agregar("Imposible borrar el Código Comercio. Posee elementos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrió un error al intentar borrar el Código Comercio");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarCodComercio.jsf");
		return null;
	}


	public String grabar() {
		error.borrar();
		try {
			refrescarSucursal = true;
			
			
			if (validar()) {

				// seteo los datos correspondientes a la empresa
				if (imprimirLiquidacion)
					empresa.setImprimirLiquidacion(Character.valueOf('S'));
				else
					empresa.setImprimirLiquidacion(new Character('N'));
				if (generarPDF)
					empresa.setGenerarPDF(new Character('S'));
				else
					empresa.setGenerarPDF(new Character('N'));
				if (enviarMail)
					empresa.setEnviarMail(new Character('S'));
				else
					empresa.setEnviarMail(new Character('N'));
				empresa.setTipoLiquidacion(idTipoLiqSeleccionada);
				generalService.getEmpresaService().actualizarEmpresa(empresa);

				// Inicio los servis que voy a usar
				CodComercioService codcomercioService = transaccionesService.getCodComercioService();

				if (codComercio.getComercioListaPrecioSet() == null)
					codComercio.setComercioListaPrecioSet(new HashSet());
				Iterator iterListaPrecio = listaDePrecios.iterator();
				while (iterListaPrecio.hasNext()) {
					WrapperListaPrecio wrap = (WrapperListaPrecio) iterListaPrecio.next();
					if (wrap.getComercioListaPrecio().getId() == null) {
						codComercio.getComercioListaPrecioSet().add(wrap.getComercioListaPrecio());
					}
				}
				if (idTipoLiqSeleccionada.intValue() == LIQUIDA_POR_CUIT) {
					Iterator iterFormaPago = tablaDeFormaDePago.iterator();
					while (iterFormaPago.hasNext()) {
						WrapperFormaPago wrap = (WrapperFormaPago) iterFormaPago.next();
						ComercioFormaPago comercioFormaPago = wrap.getComercioFormaPago();
						comercioFormaPago.setCodComercio(null);
						comercioFormaPago.setEmpresa(empresa);
						/*
						 * if (comercioFormaPago.getId()==null) { comercioFormaPago.setEmpresa(empresa); }
						 */
						// si la empresa no tiene una forma de pago asociada se crea una y se inserta el registro en la BD
						if (listaVaciaFmaPagoCuit) {
							transaccionesService.getComercioFormaPagoService().grabarComercioFormaPago(comercioFormaPago);
							System.out.println("se grabo una nva forma de pago por cuit a la empresa  " + empresa.getIdEmpresa());
						}
						else { // ya hay una forma de pago asociada a la empresa, que liquida por cuit, ahora la recupemos y posteriormente la
								// actualizamos
							Filtro f = new Filtro();
							f.agregarCampoOperValor("empresa.idEmpresa", Filtro.IGUAL, empresa.getIdEmpresa());
							f.agregarCampoComparacionNulo("codComercio", Filtro.NULL);
							List fmaPagoList = transaccionesService.getComercioFormaPagoService().getComercioFormaPago(f);

							// ComercioFormaPago com= new ComercioFormaPago((ComercioFormaPago) fmaPagoList.get(0));
							ComercioFormaPago com = (ComercioFormaPago) fmaPagoList.get(0);
							com.setCodComercio(comercioFormaPago.getCodComercio());
							com.setEmpresa(comercioFormaPago.getEmpresa());
							com.setEsChequeCruzado(comercioFormaPago.getEsChequeCruzado());
							com.setOrdenCheque(comercioFormaPago.getOrdenCheque());
							com.setActivo(comercioFormaPago.getActivo());
							com.setCodCuentaDeposito(comercioFormaPago.getCodCuentaDeposito());
							com.setCbu(comercioFormaPago.getCbu());
							// com.getSucursalFormaPago().setNroCuentaFondos(comercioFormaPago.getSucursalFormaPago().getNroCuentaFondos());
							// com.getSucursalFormaPago().setFormaPago(comercioFormaPago.getSucursalFormaPago().getFormaPago());
							com.setTipoCuentaBanco(comercioFormaPago.getTipoCuentaBanco());
							com.setBanco(comercioFormaPago.getBanco());
							com.setFormaPago(comercioFormaPago.getFormaPago());
							com.setChequeNoAlaOrden(comercioFormaPago.getChequeNoAlaOrden());
							transaccionesService.getComercioFormaPagoService().actualizarComercioFormaPago(com);
							System.out.println("se actualizo  forma de pago por cuit a la empresa  " + empresa.getIdEmpresa());
						}
					}
				} // si liquida por cod comercio
				else {
					if (codComercio.getComercioFormaPagoSet() == null)
						codComercio.setComercioFormaPagoSet(new HashSet());
					Iterator iterFormaPago = tablaDeFormaDePago.iterator();
					while (iterFormaPago.hasNext()) {
						WrapperFormaPago wrap = (WrapperFormaPago) iterFormaPago.next();
						if (wrap.getComercioFormaPago().getId() == null) {
							codComercio.getComercioFormaPagoSet().add(wrap.getComercioFormaPago());
						}
					}

				}
				codComercio.setSucEmpresa(sucEmpresa);
				// codComercio.getSucEmpresa().setEmpresa(empresa);
				// generalService.getEmpresaService().actualizarEmpresa(empresa);

				/*
				 * if(debitoAutomatico){ codComercio.setDebitoAutomatico(new Character('S')); } else{ codComercio.setDebitoAutomatico(new
				 * Character('N')); } if(presentaCupones){ codComercio.setPresentaCupones(new Character('S')); } else{
				 * codComercio.setPresentaCupones(new Character('N')); }
				 */
				// codComercio.setCodigoPosnet(codigoPosnet);
				codComercio.setUrl(urlArchivo);
				codComercio.setUrlPresenta(urlArchivoPresenta);
				// Crear una jurisdiccion en base a la jurisdiccion seleccionada en al presentacion y setearla a el proveedor.
				log.info("Contruyendo una Jurisdiccion.");
				
				if (alta) {
					Jurisdiccion jurisdiccion = impuestoService.getJurisdiccionDao().buscarJurisdiccion(jurisdiccionSeleccionada);
					codComercio.setJurisdiccion(jurisdiccion);
					// Setea inscripcion en dgr.
					log.info("Setea inscripcion en dgr. alta");
					codComercio.setInscripcionDgr(inscripcionDgr);
					// Agregar la relacion del comercio con la actividad para el impuesto segun su categoria y jurisdiccion.
					log.info("Setes la relacion del proveedor con la categoria del impuesto.");
					if (tablaDeImpuestos != null)
						codComercio.setCodComercioActividad(CodComercioUtil.cargarImpuestos(tablaDeImpuestos, codComercio,
								impuestoService.getCategoriaDao(), impuestoService.getJurisdiccionActividadDao()));
					
				}
				
				
			//	log.info("compraAliasCodigoPosnet "+ codcomercioService.compraAliasCodigoPosnet(codComercio.getIdCodComercio().toString()) );
				if (!alta && codcomercioService.compraAliasCodigoPosnet(codComercio.getIdCodComercio().toString()) == null) {
					Jurisdiccion jurisdiccion = impuestoService.getJurisdiccionDao().buscarJurisdiccion(jurisdiccionSeleccionada);
					codComercio.setJurisdiccion(jurisdiccion);
					// Setea inscripcion en dgr.
					log.info("Setea inscripcion en dgr. modificacion");
					codComercio.setInscripcionDgr(inscripcionDgr);
					// Agregar la relacion del comercio con la actividad para el impuesto segun su categoria y jurisdiccion.
					log.info("Setes la relacion del proveedor con la categoria del impuesto.");
					if (tablaDeImpuestos != null)
						codComercio.setCodComercioActividad(CodComercioUtil.cargarImpuestos(tablaDeImpuestos, codComercio,
								impuestoService.getCategoriaDao(), impuestoService.getJurisdiccionActividadDao()));
					
				}
				
				
				if (!alta && codcomercioService.compraAliasCodigoPosnet(codComercio.getIdCodComercio().toString()) != null) {
					if (Validador.esNulo(jurisdiccionSeleccionada) || jurisdiccionSeleccionada.equals(new Long(0)))  {
						codComercio.setJurisdiccion(null);
						
					} else {
						Jurisdiccion jurisdiccion = impuestoService.getJurisdiccionDao().buscarJurisdiccion(jurisdiccionSeleccionada);
						codComercio.setJurisdiccion(jurisdiccion);
					}
					
					// Setea inscripcion en dgr.
					log.info("Setea inscripcion en dgr. modificacion");
					if (Validador.esNuloVacio(inscripcionDgr) ) {
						codComercio.setInscripcionDgr(null);
					} else {
						codComercio.setInscripcionDgr(inscripcionDgr);
					}
					
					// Agregar la relacion del comercio con la actividad para el impuesto segun su categoria y jurisdiccion.
					log.info("Setes la relacion del proveedor con la categoria del impuesto.");
					if (tablaDeImpuestos != null)
						codComercio.setCodComercioActividad(CodComercioUtil.cargarImpuestos(tablaDeImpuestos, codComercio,
								impuestoService.getCategoriaDao(), impuestoService.getJurisdiccionActividadDao()));
					
				}
				
				
				grabarListaPrecio();
				
				armarDocumentosAdj();
				
				if (alta) {
				this.setCodigoPosnet(codcomercioService.sequenciaCodigoPosnet());
					// Grabo el nuevo objeto
					codcomercioService.grabarCodComercio(codComercio);
				} else {
					codcomercioService.actualizarCodComercio(codComercio);
				}
				// grabarFormasDePagos(transaccionesService.getComercioFormaPagoService(), tablaDeFormaDePago, codComercio, temporalFP);
				popup.setPopup(popup.ICONO_OK, "El Código Comercio ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (Exception e3) {
			error.agregar("Ocurrio un error al Guardar");
			// popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Código Comercio.");
			e3.printStackTrace();
		}
		return "";
	}


	public String eliminarListaPrecio() {
		String result = null;
		error.borrar();
		log.info(" Ejecutando ==> eliminarListaPrecios()");
		Long idListaPrecio = new Long(Session.getRequestParameter("idComercioListaPrecioElim"));
		if (idListaPrecio.intValue() != 0) {// && listaDePrecios.size() > 1) {
			Iterator ite = listaDePrecios.iterator();
			while (ite.hasNext()) {
				WrapperListaPrecio wrap = (WrapperListaPrecio) ite.next();
				if (wrap.getIndice() == idListaPrecio.intValue()) {
					try {
						wrap.getComercioListaPrecio().setFechaBaja(new Timestamp(new Date().getTime()));
						wrap.getComercioListaPrecio().setCodigoPosnet(new Long(-1));
						listaDePrecios.remove(wrap);
						break;
					} catch (Exception e) {
						log.info("Error: " + e.getMessage());
					}
				}
			}
		} else {
			error.agregar("No se puede Eliminar esta la Lista de Precio");
		}
		return result;
	}


	public String reemplazarListaPrecio() {
		log.info(" Ejecutando ==> reemplazarListaPrecio()");
		error.borrar();
		String result = null;
		Long numeroAEliminar = null;
		Long idListaPrecio = new Long(Session.getRequestParameter("idComercioListaPrecioReemp"));
		Iterator ite = listaDePrecios.iterator();
		while (ite.hasNext()) {
			WrapperListaPrecio wrap = (WrapperListaPrecio) ite.next();
			if (wrap.getIndice() == idListaPrecio.intValue()) {
				if (wrap.getComercioListaPrecio().getIdComercioListaprecio() != null) {
					comercioListaSelected = new ComercioListaPrecio();
					comercioListaSelected = wrap.getComercioListaPrecio();
					ComercioListaPrecioBean bean = (ComercioListaPrecioBean) Session.getBean("ComercioListaPrecioBean");
					bean.inicializarDesdePopup(new ComercioListaPrecio(), listaDePrecios, false);
				}
				break;
			}
		}
		return result;
	}


	public void reemplazarLista(ComercioListaPrecio comercioListaPrecio) throws Exception {
		try {
			comercioListaPrecio.setCodigoPosnet(comercioListaSelected.getCodigoPosnet());
			Iterator ite = listaDePrecios.iterator();
			while (ite.hasNext()) {
				// elimino la vieja
				WrapperListaPrecio wrap = (WrapperListaPrecio) ite.next();
				if (wrap.getIndice() == comercioListaPrecio.getCodigoPosnet().intValue()) {
					wrap.getComercioListaPrecio().setFechaBaja(new Timestamp(new Date().getTime()));
					wrap.getComercioListaPrecio().setCodigoPosnet(new Long(-1));
					listaDePrecios.remove(wrap);
					break;
				}
			}
			// agrego la nueva
			WrapperListaPrecio wrap = new WrapperListaPrecio(comercioListaPrecio, true);
			codComercio.getComercioListaPrecioSet().add(wrap.getComercioListaPrecio());
			listaDePrecios.add(wrap);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw new Exception("Error al reemplazar la lista de precio");
		}
	}


	public void grabarListaPrecio() {
		Iterator ite = listaDePrecios.iterator();
		while (ite.hasNext()) {
			WrapperListaPrecio wrap = (WrapperListaPrecio) ite.next();
			if (wrap.getComercioListaPrecio().getIdComercioListaprecio() == null || wrap.soyNuevo) {
				wrap.getComercioListaPrecio().setCodComercio(codComercio);
				codComercio.getComercioListaPrecioSet().add(wrap.getComercioListaPrecio());
			}
		}
	}


	/**
	 * Graba o actualiza la lista de formas de pago en la base de datos.
	 * 
	 * @param dao
	 *            , dao para ejecutar las operaciones.
	 * @param formas
	 *            , lista de formas de pago a grabar o actualizar.
	 * @param codComercio
	 *            , codComercio para setear a las formas de pago.
	 * @param listaParaBorrar
	 *            , lista de formas de pago a eliminar.
	 */
	public static void grabarFormasDePagos(ComercioFormaPagoService dao, List formas,
			CodComercio codComercio, List listaParaBorrar) {

		if (!listaParaBorrar.isEmpty()) {
			Iterator iter = listaParaBorrar.iterator();
			while (iter.hasNext()) {
				ComercioFormaPago aux = (ComercioFormaPago) iter.next();
				aux.setActivo("N");
				try {
					dao.actualizarComercioFormaPago(aux);
				} catch (ComercioFormaPagoException e) {
					e.printStackTrace();
				}
			}
		}

		listaParaBorrar = new ArrayList();

		if (!formas.isEmpty()) {
			Iterator iter = formas.iterator();
			while (iter.hasNext()) {
				ComercioFormaPago aux = (ComercioFormaPago) iter.next();
				aux.setCodComercio(codComercio);

				try {
					dao.grabarComercioFormaPago(aux);
				} catch (ComercioFormaPagoException e) {
					e.printStackTrace();
				}
			}
		}
	}


	private void cambioSucursal() {
		log.info("Ejecutando ==> cambioSucursal()");
		if (!sucursales.isEmpty()) {
			List listaTelefonos = Convertidores.setToList(sucEmpresa.getSucTelefonos());
			if (!listaTelefonos.isEmpty()) {
				telefonoSucursal = ((SucTelefono) (listaTelefonos.get(0))).getTelefono().getNroTlefono();
			}
			cargarActividadesRelacionadas(sucEmpresa.getIdSucEmpresa());
			// recuperarCodComercio(sucEmpresa.getIdSucEmpresa());
			sucursalCargada = true;
			
			
			
			if (tablaDeFormaDePago != null && idTipoLiqSeleccionada.intValue() != LIQUIDA_POR_CUIT)
				tablaDeFormaDePago.clear();
			if (listaDePrecios != null)
				listaDePrecios.clear();
			alta = false;
			if (listaDePrecios == null)
				listaDePrecios = new ArrayList();
			Iterator ite = codComercio.getComercioListaPrecioSet().iterator();
			while (ite.hasNext()) {
				ComercioListaPrecio comLisPre = (ComercioListaPrecio) ite.next();
				comLisPre.getListaPrecio().getIdListaprecios();
				if (comLisPre.getCodigoPosnet() >= 0) {
					listaDePrecios.add(new WrapperListaPrecio(comLisPre, false));
				}
			}
			// si no liquida por cuit cargo la forma de pago habilitada para el codComercio
			if (idTipoLiqSeleccionada.intValue() == LIQUIDA_POR_COD_COM) {
				Iterator iteDos = codComercio.getComercioFormaPagoSet().iterator();
				while (iteDos.hasNext()) {
					ComercioFormaPago comForPago = (ComercioFormaPago) iteDos.next();
					// comForPago.getSucursalFormaPago().getFormaPago().getIdFormaPago();
					if (tablaDeFormaDePago == null)
						tablaDeFormaDePago = new ArrayList();
					tablaDeFormaDePago.add(new WrapperFormaPago(comForPago, false, empresa));
					habilitaAgregar = true;
					habilitaQuitar = false;
				}
			}

			tablaDeImpuestos = CodComercioUtil.generarListaImpuestos(impuestoService.getTipoImpuestoDao(),
					impuestoService.getJurisdiccionActividadDao());
			tablaDeImpuestos = CodComercioUtil.marcarListaDesdeCodComercio(codComercio.getCodComercioActividad(), tablaDeImpuestos);
			// filtro.agregarCampoOperValor("aplicable.idAplicable",Filtro.IGUAL,"2");
			// Seteamos el numero de inscripcion DGR.
			inscripcionDgr = codComercio.getInscripcionDgr();
			jurisSelecItem = new HtmlSelectOneMenu();
			jurisSelecItem.setValue(codComercio.getJurisdiccion().getIdJurisdiccion());
			jurisdiccionSeleccionada = (Long) jurisSelecItem.getValue();
			codComercio.getInscripcionDgr();
			codComercio.getSucEmpresa().getIdSucEmpresa();
			/*
			 * if(codComercio.getDebitoAutomatico()!=null && codComercio.getDebitoAutomatico().toString().equals("S")){ debitoAutomatico= true; }
			 * if(codComercio.getPresentaCupones()!=null && codComercio.getPresentaCupones().toString().equals("S")){ presentaCupones= true; }
			 */
			urlArchivo = codComercio.getUrl();
			urlArchivoPresenta = codComercio.getUrlPresenta();
			if (codComercio.getSucursalFiel() != null)
				sucursalFielSeleccionda = codComercio.getSucursalFiel().getId();
		} else {
			sucEmpresa = new SucEmpresa();
			telefonoSucursal = "";
			listaAutorizados = new ArrayList();
			listaResponsables = new ArrayList();
			individuosRelacionados = new ArrayList();
			listaDePrecios = new ArrayList();
			tablaDeFormaDePago = new ArrayList();
			sucursalCargada = false;
		}
	}


	public void cambioTipoLiquidacion(ValueChangeEvent event) {
		if (mostrarMsgConfCambioFmaPago) {
			idTipoLiqSeleccionada = (Long) tipoLiqHtml.getValue();
			if (idTipoLiqSeleccionada.longValue() != 0)
				confirmaCambioLiq = true;
			else
				confirmaCambioLiq = false;
		} else
			mostrarMsgConfCambioFmaPago = true;
	}


	/*
	 * este método se utiliza para poder cargar los datos de la sucursal de la empresa.
	 */
	public void cambioSucursal(ValueChangeEvent event) {
		log.info("Ejecutando ==> cambioSucursal()");
		try {
			Long valorSeleccionado = new Long(sucursalHtml.getValue().toString());
			if (valorSeleccionado.intValue() != 0 && !sucursales.isEmpty()) {
				Iterator iterator = sucursales.iterator();
				while (iterator.hasNext()) {
					sucEmpresa = (SucEmpresa) iterator.next();
					if (sucEmpresa.getIdSucEmpresa().equals(valorSeleccionado)) {
						List listaTelefonos = Convertidores.setToList(sucEmpresa.getSucTelefonos());
						if (!listaTelefonos.isEmpty()) {
							telefonoSucursal = ((SucTelefono) (listaTelefonos.get(0))).getTelefono().getNroTlefono();
						}
						break;
					}
				}
				cargarActividadesRelacionadas(sucEmpresa.getIdSucEmpresa());
				recuperarCodComercio(sucEmpresa.getIdSucEmpresa());
				sucursalCargada = true;
				refrescarSucursal = false;
				codComercio.getCodigoPosnet();
				codComercio.getDebitoAutomatico();
				if (tablaDeFormaDePago == null) {
					habilitaAgregar = false;
					habilitaQuitar = true;

				}
				else {
					if (tablaDeFormaDePago != null) {
						if (tablaDeFormaDePago.size() > 0) {
							habilitaAgregar = true;
							habilitaQuitar = false;

						} else {
							habilitaAgregar = false;
							habilitaQuitar = true;

						}

					}
				}
			} else {
				sucEmpresa = new SucEmpresa();
				telefonoSucursal = "";
				listaAutorizados = new ArrayList();
				listaResponsables = new ArrayList();
				individuosRelacionados = new ArrayList();
				listaDePrecios = new ArrayList();
				tablaDeFormaDePago = new ArrayList();
				sucursalCargada = false;
			}
		} catch (NullPointerException e) {
			log.info("Hubo un error al querer agregar el domicilio de la empresa a la lista de pagos");
			e.printStackTrace();
		}
	}


	public void recuperarCodComercio(Long idSucursal) {
		try {

			if (tablaDeFormaDePago != null && idTipoLiqSeleccionada.intValue() != LIQUIDA_POR_CUIT)
				tablaDeFormaDePago.clear();
			if (listaDePrecios != null)
				listaDePrecios.clear();
			List listaCodComercio = transaccionesService.getCodComercioService().getCodComercio(
					new Filtro("sucEmpresa.idSucEmpresa", Filtro.IGUAL, idSucursal));
			if (listaCodComercio.isEmpty()) {
				alta = true;
				codComercio = new CodComercio();
				codComercio.setComercioListaPrecioSet(new HashSet());
				codComercio.setComercioFormaPagoSet(new HashSet());
				// codComercio.getJurisdiccion().getIdJurisdiccion();
				tablaDeImpuestos = CodComercioUtil.generarListaImpuestos(impuestoService.getTipoImpuestoDao(),
						impuestoService.getJurisdiccionActividadDao());
				tablaDeImpuestos = CodComercioUtil.marcarListaDesdeCodComercio(codComercio.getCodComercioActividad(), tablaDeImpuestos);
			} else {
				alta = false;
				codComercio = (CodComercio) listaCodComercio.get(0);
				if (listaDePrecios == null)
					listaDePrecios = new ArrayList();
				Iterator ite = codComercio.getComercioListaPrecioSet().iterator();
				while (ite.hasNext()) {
					ComercioListaPrecio comLisPre = (ComercioListaPrecio) ite.next();
					comLisPre.getListaPrecio().getIdListaprecios();
					if (comLisPre.getCodigoPosnet() >= 0) {
						listaDePrecios.add(new WrapperListaPrecio(comLisPre, false));
					}
				}
				// si no liquida por cuit cargo la forma de pago habilitada para el codComercio
				if (idTipoLiqSeleccionada.intValue() == LIQUIDA_POR_COD_COM) {
					Iterator iteDos = codComercio.getComercioFormaPagoSet().iterator();
					while (iteDos.hasNext()) {
						ComercioFormaPago comForPago = (ComercioFormaPago) iteDos.next();
						comForPago.getFormaPago().getIdFormaPago();
						if (tablaDeFormaDePago == null)
							tablaDeFormaDePago = new ArrayList();
						tablaDeFormaDePago.add(new WrapperFormaPago(comForPago, false, empresa));
						habilitaAgregar = true;
						habilitaQuitar = false;
					}
				}

				tablaDeImpuestos = CodComercioUtil.generarListaImpuestos(impuestoService.getTipoImpuestoDao(),
						impuestoService.getJurisdiccionActividadDao());
				tablaDeImpuestos = CodComercioUtil.marcarListaDesdeCodComercio(codComercio.getCodComercioActividad(), tablaDeImpuestos);
				// filtro.agregarCampoOperValor("aplicable.idAplicable",Filtro.IGUAL,"2");
				// Seteamos el numero de inscripcion DGR.
				inscripcionDgr = codComercio.getInscripcionDgr();
				jurisSelecItem = new HtmlSelectOneMenu();
				jurisSelecItem.setValue(codComercio.getJurisdiccion().getIdJurisdiccion());
				jurisdiccionSeleccionada = (Long) jurisSelecItem.getValue();
				codComercio.getInscripcionDgr();
				codComercio.getSucEmpresa().getIdSucEmpresa();
				/*
				 * if(codComercio.getDebitoAutomatico()!=null && codComercio.getDebitoAutomatico().toString().equals("S")){ debitoAutomatico= true; }
				 * if(codComercio.getPresentaCupones()!=null && codComercio.getPresentaCupones().toString().equals("S")){ presentaCupones= true; }
				 */
				urlArchivo = codComercio.getUrl();
				urlArchivoPresenta = codComercio.getUrlPresenta();
				if (codComercio.getSucursalFiel() != null)
					sucursalFielSeleccionda = codComercio.getSucursalFiel().getId();

			}
		} catch (CodComercioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void cargarActividadesRelacionadas(Long idSucursal) {
		log.info("Ejecutando Método CargarActividades Relacionadas");
		Set idDeIndividuosABuscar = new HashSet();
		try {
			Filtro filtro = new Filtro("sucEmpresa", Filtro.IGUAL, idSucursal);
			List listsucu = evaluacionService.getActividadEvaluacionService().getActividad(filtro);
			Iterator iter = listsucu.iterator();
			log.info("recuperamos " + listsucu.size() + " actividades");
			while (iter.hasNext()) {
				ActividadEvaluacion act = (ActividadEvaluacion) iter.next();
				idDeIndividuosABuscar.add(act.getCuil());
			}
		} catch (ActividadEvaluacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Los individuos a buscar deben tener estos cuiles: ");
		idDeIndividuosABuscar.toString();
		buscarLosIndividuosRespectivos(idDeIndividuosABuscar);

	}


	public void buscarLosIndividuosRespectivos(Set idisABuscar) {
		listaAutorizados.clear();
		listaResponsables.clear();
		log.info("Ejecutando método buscarLosIndividuosRespectivos");
		individuosRelacionados = new ArrayList();
		Iterator iterHas = idisABuscar.iterator();
		while (iterHas.hasNext()) {
			try {
				Long activBuscada = new Long(iterHas.next().toString());
				log.info("La activ buscada es: " + activBuscada);
				List indivAuxi = evaluacionService.getIndividuoEvaluacionService().getIndividuo(
						new Filtro("cuil", Filtro.LIKE, String.valueOf(activBuscada)));
				// codigo que recupera actividad
				ActividadEvaluacion actividad = null;
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("cuil", Filtro.LIKEXS, activBuscada.toString());
				filtro.agregarCampoOperValor("sucEmpresa.idSucEmpresa", Filtro.IGUAL, sucEmpresa.getIdSucEmpresa());
				try {
					List actividades = evaluacionService.getActividadEvaluacionService().getActividad(filtro);
					actividad = (ActividadEvaluacion) actividades.get(0);
				} catch (ActividadEvaluacionException e) {
					log.info("Error en el acceso a al actividad");
					e.printStackTrace();
				}
				log.info("La cantidad de individuosRecuperados es: " + indivAuxi.size());
				if (indivAuxi.size() > 0) {
					IndividuoEvaluacion indiv = new IndividuoEvaluacion();
					Iterator itePrime = indivAuxi.iterator();
					while (itePrime.hasNext()) {
						indiv = (IndividuoEvaluacion) itePrime.next();
						break;
					}
					indiv.getNombres();
					indiv.getApellido();
					// indiv.getActividad().getCargo();
					indiv.getNroDocumento();
					indiv.getCuil();
					Iterator ite = indiv.getTelefonos().iterator();
					while (ite.hasNext()) {
						Telefonos tel = (Telefonos) ite.next();
						tel.getTelefono().getNroTlefono();
					}
					individuosRelacionados.add(indiv);
					if (actividad.getTipo() != null && actividad.getTipo().compareTo("X") == 0) {
						listaAutorizados.add(new WrapperIndividuo(indiv, WrapperIndividuo.AUTORIZADO, false, actividad));
						listaResponsables.add(new WrapperIndividuo(indiv, WrapperIndividuo.RESPONSABLE, false, actividad));
					} else {
						if (actividad.getTipo() != null && actividad.getTipo().compareTo("A") == 0) {
							listaAutorizados.add(new WrapperIndividuo(indiv, WrapperIndividuo.AUTORIZADO, false, actividad));
						}
						if (actividad.getTipo() != null && actividad.getTipo().compareTo("R") == 0) {
							listaResponsables.add(new WrapperIndividuo(indiv, WrapperIndividuo.RESPONSABLE, false, actividad));
						}
					}
				}
			} catch (IndividuoEvaluacionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IndexOutOfBoundsException e2) {
				log.info("Paso un error en el acceso al individuo");
				e2.printStackTrace();
			}
		}
	}


	public String mostrarPopupDeCarga() {
		String result = null;
		listaPosiblesIndividuos = new ArrayList();
		Iterator iterat = individuosRelacionados.iterator();
		while (iterat.hasNext()) {
			IndividuoEvaluacion indi = (IndividuoEvaluacion) iterat.next();
			ActividadEvaluacion actividad = null;
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("cuil", Filtro.LIKEXS, indi.getCuil());
			filtro.agregarCampoOperValor("sucEmpresa.idSucEmpresa", Filtro.IGUAL, sucEmpresa.getIdSucEmpresa());
			try {
				List actividades = evaluacionService.getActividadEvaluacionService().getActividad(filtro);
				actividad = (ActividadEvaluacion) actividades.get(0);
			} catch (ActividadEvaluacionException e) {
				e.printStackTrace();
			}

			if (actividad.getTipo() != null && actividad.getTipo().compareTo("X") == 0) {
				log.info("No se agrega el individuo");
			} else {
				if ((actividad.getTipo() != null && actividad.getTipo().compareTo("E") == 0) || actividad.getTipo() == null) {
					log.info("Se permiten las dos cajas");
					listaPosiblesIndividuos.add(new WrapperIndividuoLibre(indi, true, true, actividad));
				} else {
					if (actividad.getTipo() != null && actividad.getTipo().compareTo("A") == 0) {
						log.info("Solo el responsable");
						listaPosiblesIndividuos.add(new WrapperIndividuoLibre(indi, true, false, actividad));
					}
					if (actividad.getTipo() != null && actividad.getTipo().compareTo("R") == 0) {
						log.info("Solo el autorizado");
						listaPosiblesIndividuos.add(new WrapperIndividuoLibre(indi, false, true, actividad));
					}
				}
			}
		}
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/transacciones/popup/amAutorizadosResponsablesComerciosPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',900,700), 'titlebar=no';");
		return result;
	}


	public String darAltaNuevoIndividuo() {
		error.borrar();
		// presenta el popup de alta individuo
		try {
			if (cuitIndividuoAAgregar.toString().length() == 11) {
				List individuoList = evaluacionService.getIndividuoEvaluacionService().getIndividuo(
						new Filtro("cuil", Filtro.LIKE, cuitIndividuoAAgregar));
				if (individuoList.isEmpty()) {
					CuitValido cuitValido = new CuitValido(cuitIndividuoAAgregar.toString());
					IndividuoPopupBean indPopupBean = (IndividuoPopupBean) Session.getBean("IndividuoPopupBean");
					IndividuoEvaluacion individuoEva = new IndividuoEvaluacion();
					individuoEva.setCuil(cuitIndividuoAAgregar.toString());
					indPopupBean.inicializar(individuoEva, indPopupBean.COD_COMERCIO, this.sucEmpresa);
				}
				else {
					// el individuo ya existe, entonces le agregamos una actividad.
					individuoEvaluacion = (IndividuoEvaluacion) individuoList.get(0);
					ActividadEvaluacion actividad = new ActividadEvaluacion();
					actividad.setCuil(individuoEvaluacion.getCuil());
					actividad.setSucEmpresa(sucEmpresa);
					try {
						evaluacionService.getActividadEvaluacionService().grabarActividad(actividad);
						agregarIndiDisponible(individuoEvaluacion, actividad);
					} catch (ActividadEvaluacionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} else {
				error.agregar("El cuit debe tener once dígitos. ");
			}
		} catch (CuitNoValidoException e) {
			error.agregar(Error.NUMERO_DE_CUIT_NO_VALIDO);
			e.printStackTrace();
		} catch (StringIndexOutOfBoundsException ae) {
			error.agregar(Error.NUMERO_DE_CUIT_NO_VALIDO);
		} catch (IndividuoEvaluacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


	public String editarIndi() {
		error.borrar();
		Long individuoBuscado = null;
		darAltaNuevoIndividuo();
		if (Session.getRequestParameter("idIndiEdi") != null && Session.getRequestParameter("idIndiEdi").compareTo("") != 0) {
			Long indic = new Long(Session.getRequestParameter("idIndiEdi"));
			Iterator iter = listaPosiblesIndividuos.iterator();
			while (iter.hasNext()) {
				WrapperIndividuoLibre in = (WrapperIndividuoLibre) iter.next();
				if (in.getIndice() == indic.intValue()) {
					individuoBuscado = Long.valueOf(in.getCuil());
					break;
				}
			}
		} else
		{
			individuoBuscado = cuitIndividuoAAgregar;
		}
		try {
			List individuos = evaluacionService.getIndividuoEvaluacionService().getIndividuo(new Filtro("cuil", Filtro.IGUAL, individuoBuscado));
			if (!individuos.isEmpty()) {
				individuoEvaluacion = (IndividuoEvaluacion) individuos.get(0);
				if (individuoEvaluacion.getDomicilio() != null && individuoEvaluacion.getDomicilio().getBarrio() != null) {
					individuoEvaluacion.getDomicilio().getBarrio().getDescripcion();
				}
				if (individuoEvaluacion.getTelefonos() != null) {
					Iterator iterTele = individuoEvaluacion.getTelefonos().iterator();
					while (iterTele.hasNext()) {
						Telefonos tel = (Telefonos) iterTele.next();
						tel.getTelefono().getTipo().getDescripcion();
						tel.getTelefono().getNroTlefono();
					}
				}
				if (individuoEvaluacion.getMails() != null) {
					Iterator iterMai = individuoEvaluacion.getMails().iterator();
					while (iterMai.hasNext()) {
						Emails mai = (Emails) iterMai.next();
						mai.getEmail().getDescripcion();
						mai.getEmail().getEmail();
					}
				}
			}
		} catch (IndividuoEvaluacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// presenta el popup de alta individuo
		IndividuoPopupBean indPopupBean = (IndividuoPopupBean) Session.getBean("IndividuoPopupBean");
		indPopupBean.inicializar(individuoEvaluacion, indPopupBean.COD_COMERCIO, this.sucEmpresa);
		return null;
	}


	/*
	 * Este método se utiliza para validar el cuit de una empresa.
	 */
	private boolean validarCuit(String cuit) {

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
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public void borrar() {
		error.borrar();
		alta = true;
		refrescarSucursal = true;
		empresaExistente = false;
		tituloLargo = "TARJETA FIEL";
		habilitaAgregar = false;
		habilitaQuitar = true;
		tituloCorto = "Alta de Código Comercio";
		popup.borrar();
		empresa = new Empresa();
		codComercio = new CodComercio();
		codcomercioList = new ArrayList();
		sucursalCargada = false;
		listSucEmp = new ArrayList();
		listaResponsables = new ArrayList();
		listaAutorizados = new ArrayList();
		listaDePrecios = new ArrayList();
		tablaDeFormaDePago = new ArrayList();
		listaPosiblesIndividuos = new ArrayList();
		this.tablaDeFormaDePago = new ArrayList();
		tablaDeImpuestos = new ArrayList();
		
		listTipoDocumentos = new ArrayList();
		
		empresaCargada = false;
		edicion = false;
		codcomercioFiltro = new CodComercio();
		jurisdiccionSeleccionada = new Long(0);
		inscripcionDgr = null;
		debitoAutomatico = false;
		presentaCupones = false;
		/*
		 * @I4039 excluyeCargos=false; Codigo vuelto atras por pedido del cliente
		 * 
		 * @F4039
		 */urlArchivo = "";
		urlArchivoPresenta = "";
		enviarMail = false;
		generarPDF = false;
		imprimirLiquidacion = false;
		listTipoLiq.clear();
		listTipoLiq.add(new SelectItem(new Long(0), "Seleccione Tipo Liquidacion"));
		listTipoLiq.add(new SelectItem(new Long(1), "CUIT"));
		listTipoLiq.add(new SelectItem(new Long(2), "Cod. Comercio"));
		confirmaCambioLiq = false;
		listaVaciaFmaPagoCuit = true;
		mostrarFmaPagoCuit = false;
		listArchivosAdjuntosDebito = new ArrayList();
		mostraTablaArchivosAdjuntosDebito = false;
		sucursalFielSeleccionda = new Long(1);
		idSucursalSeleccionada = new Long(0);
		idTipoLiqSeleccionada = new Long(0);
		sucursalHtml = new HtmlSelectOneMenu();
		tipoLiqHtml = new HtmlSelectOneMenu();
		mostrarMsgConfCambioFmaPago = true;
		cuitIndividuoAAgregar = new Long(0);
		/* @F5013 */telefonoSucursal = null;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public String eliminarFormaPagoCuit() {
		Iterator iter = tablaDeFormaDePago.iterator();
		ComercioFormaPago comerFormaPagoElim = null;
		while (iter.hasNext()) {
			WrapperFormaPago wrap = (WrapperFormaPago) iter.next();
			if (wrap.getComercioFormaPago().getIdComercioFormapago() == null) {
				comerFormaPagoElim = wrap.getComercioFormaPago();
			}
			tablaDeFormaDePago.remove(wrap);
			break;
		}
		if (comerFormaPagoElim != null) {
			// if(idTipoLiqSeleccionada.intValue() != LIQUIDA_POR_CUIT && idTipoLiqSeleccionada.intValue()!=0 )
			codComercio.getComercioFormaPagoSet().remove(comerFormaPagoElim);
		}
		habilitaAgregar = false;
		habilitaQuitar = true;
		return null;

	}


	public String eliminarFormaPago() {
		Long idComercioFormaPago = new Long(Session.getRequestParameter("idComercioFormapago"));
		Iterator iter = tablaDeFormaDePago.iterator();
		ComercioFormaPago comerFormaPagoElim = null;
		while (iter.hasNext()) {
			WrapperFormaPago wrap = (WrapperFormaPago) iter.next();
			if (wrap.getIndice() == idComercioFormaPago.intValue()) {
				if (wrap.getComercioFormaPago().getIdComercioFormapago() != null) {
					comerFormaPagoElim = wrap.getComercioFormaPago();
				}
				tablaDeFormaDePago.remove(wrap);
				break;
			}
		}
		if (comerFormaPagoElim != null) {
			if (idTipoLiqSeleccionada.intValue() != LIQUIDA_POR_CUIT && idTipoLiqSeleccionada.intValue() != 0)
				codComercio.getComercioFormaPagoSet().remove(comerFormaPagoElim);
		}
		habilitaAgregar = false;
		habilitaQuitar = true;
		return null;
	}


	public void agregarFormaDePago(ComercioFormaPago unaFormaDePago) {
		tablaDeFormaDePago.add(new WrapperFormaPago(unaFormaDePago, true, empresa));
		habilitaAgregar = true;
		habilitaQuitar = false;

	}


	public String irAgregarFormaDePago() {
		log.info("Ir a agrega nueva forma de pago!!!");
		FormaDePagoBean bean = (FormaDePagoBean) Session.getBean("FormaDePagoBean");
		bean.inicializar(sucEmpresa.getEmpresa().getRazonSocial(), 1);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/proveedores/popup/formaPago.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}


	public boolean validar() {
		error.borrar();
		idSucursalSeleccionada = (Long) sucursalHtml.getValue();
		idTipoLiqSeleccionada = (Long) tipoLiqHtml.getValue();
		
		
		if (idSucursalSeleccionada.equals(new Long(0))
				&&	alta)
			error.agregar(Error.TRAN_CODCOMERCIO_SUCURSAL_INVALIDA);
		

		if (idSucursalSeleccionada.equals(new Long(0))
				&& !alta
				&&	transaccionesService.getCodComercioService().compraAliasCodigoPosnet(codComercio.getIdCodComercio().toString()) == null)
			error.agregar(Error.TRAN_CODCOMERCIO_SUCURSAL_INVALIDA);
//		if (codComercio.getCodigoPosnet() == null || codComercio.getCodigoPosnet().compareTo("") == 0)
//			error.agregar(Error.TRAN_CODCOMERCIO_CODIGOPOSNET_REQUERIDO);
		if (tablaDeImpuestos != null && !tablaDeImpuestos.isEmpty()) {
			Iterator iter = tablaDeImpuestos.iterator();
			while (iter.hasNext()) {
				ImpuestoEditable element = (ImpuestoEditable) iter.next();
				if (!element.getCategoriaSeleccionada().equals(new Long(0))) {
					if (element.getJurisSeleccionada().equals(new Long(0))) {
						error.agregar(Error.PROVEEDOR_JURIS_IMPU_REQUERIDA + element.getTipoImpuesto().getDescripcion());
					} else {
						if (element.getJurisActividadSeleccionada().equals(new Long(0))) {
							error.agregar(Error.PROVEEDOR_ACTIVIDAD_IMPU_REQUERIDA + element.getTipoImpuesto().getDescripcion());
						}
					}
				}
			}
		}

		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("codigoPosnet", Filtro.IGUAL, codComercio.getCodigoPosnet());
		if (!alta)
			filtro.agregarCampoOperValor("idCodComercio", Filtro.NOTIN, codComercio.getIdCodComercio());
		try {
			List codComList = transaccionesService.getCodComercioService().getCodComercio(filtro);
			if (codComList != null && codComList.size() > 0)
				error.agregar(Error.TRAN_CODCOMERCIO_COD_POSNET_REPETIDO);
		} catch (CodComercioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (urlArchivoPresenta != null && urlArchivoPresenta.compareTo("") != 0 && !presentaCupones)
			error.agregar(Error.TRAN_CODCOMERCIO_PRESENTA_NO_TILDADO);

		if (urlArchivo != null && urlArchivo.compareTo("") != 0 && !debitoAutomatico)
			error.agregar(Error.TRAN_CODCOMERCIO_DEBITO_NO_TILDADO);

//		if (alta && (Validador.esNulo(jurisdiccionSeleccionada) || jurisdiccionSeleccionada.equals(new Long(0)))) {
//			error.agregar(Error.PROVEEDOR_JURISDICCION_REQUERIDA);
//		} else {
//			if (!Validador.esNuloVacio(inscripcionDgr)) {
//				// que?
//			} else if (jurisdiccionSeleccionada.equals(new Long(0))) {
//				error.agregar(Error.PROVEEDOR_NUMERO_DGR_REQUERIDO);
//			}
//		}
		
		log.info("inscripcionDgr "+ inscripcionDgr);
		log.info("Validador.esNuloVacio(inscripcionDgr "+ Validador.esNuloVacio(inscripcionDgr));
		
		
		if (alta && (Validador.esNulo(jurisdiccionSeleccionada) || jurisdiccionSeleccionada.equals(new Long(0))) ) {
					
			error.agregar(Error.PROVEEDOR_JURISDICCION_REQUERIDA);
		}		
		if (!alta 
		&& transaccionesService.getCodComercioService().compraAliasCodigoPosnet(codComercio.getIdCodComercio().toString()) == null
		&& (Validador.esNulo(jurisdiccionSeleccionada) || jurisdiccionSeleccionada.equals(new Long(0)))  ) {
					
			error.agregar(Error.PROVEEDOR_JURISDICCION_REQUERIDA);
		}
		
		
		if (alta && (Validador.esNuloVacio(inscripcionDgr) )) {
			
			error.agregar(Error.PROVEEDOR_NUMERO_DGR_REQUERIDO);
		}		
		if (!alta && transaccionesService.getCodComercioService().compraAliasCodigoPosnet(codComercio.getIdCodComercio().toString()) == null				
				&& (Validador.esNuloVacio(inscripcionDgr) ) ) {
			
			error.agregar(Error.PROVEEDOR_NUMERO_DGR_REQUERIDO);
		}
						
		
//		if (!alta 
//				&& transaccionesService.getCodComercioService().compraAliasCodigoPosnet(codComercio.getIdCodComercio().toString()) == null
//				&& (Validador.esNulo(jurisdiccionSeleccionada) || jurisdiccionSeleccionada.equals(new Long(0)))) {
//			error.agregar(Error.PROVEEDOR_JURISDICCION_REQUERIDA);
//		} else {
//			if (!Validador.esNuloVacio(inscripcionDgr)) {
//				// que?
//			} else if (jurisdiccionSeleccionada.equals(new Long(0))) {
//				error.agregar(Error.PROVEEDOR_NUMERO_DGR_REQUERIDO);
//			}
//		}

		if (tablaDeFormaDePago != null && tablaDeFormaDePago.size() == 0) {
			error.agregar(Error.TRAN_CODCOMERCIO_FORMA_PAGO_REQUERIDA);
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoCodComercio() {
		return inicializar();
	}


	public String irAModificarCodComercio() {
		alta = false;
		borrar();
		popup.borrar();
		tituloCorto = "Modificar Código Comercio";
		return null;
	}


	public String irAListarCodComercio() {
		borrar();
		tituloCorto = "Listado de Código Comercio";
		codcomercioFiltro = new CodComercio();
		Session.redirect("/tarjetafiel/transacciones/listarCodComercio.jsf");
		return "";
	}


	public void agregarIndiDisponible(IndividuoEvaluacion indi, ActividadEvaluacion acti) {
		individuosRelacionados.add(indi);
		listaPosiblesIndividuos.add(new WrapperIndividuoLibre(indi, true, true, acti));
	}


	public String listarCodComercio() {
		error.borrar();
		codcomercioList = new ArrayList();
		try {
			if (codcomercioFiltro.getCodigoPosnet() != null && !codcomercioFiltro.getCodigoPosnet().equals("")) {
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("codigoPosnet", Filtro.LIKE, codcomercioFiltro.getCodigoPosnet());
				filtro.agregarCampoOperValor("estado", Filtro.LIKE, codcomercioFiltro.getEstado());
				codcomercioList = transaccionesService.getCodComercioService().getCodComercio(filtro);
				Iterator iter = codcomercioList.iterator();
				while (iter.hasNext())
				{
					CodComercio element = (CodComercio) iter.next();
					element.getSucEmpresa().getDescripcion();
				}
			} else {
				error.agregar("Debe ingresar un Código Posnet");
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarCodComercio.jsf");
		return null;
	}


	public String eliminarArchivoAdjunto() {
		urlArchivo = "";
		return null;
	}


	public String eliminarArchivoAdjuntoPresenta() {
		urlArchivoPresenta = "";
		return null;
	}


//	public String saveFile() {
//		try {
//
//			int size = new Long(imagen.getSize()).intValue();
//			String path = Archivo.grabarArchivo(imagen.getInputStream(), imagen.getName(), size, Archivo.archivosDeProveedores);
//			log.info(path);
//
//			if (path != null && !path.equals("No grabo")) {
//				urlArchivo = path;
//			}
//		} catch (Exception x) {
//			x.printStackTrace();
//			return null;
//		}
//		return null;
//	}
	
	
	/*
	 * Este metodo se utiliza para poder almacenar un archivo digital en un directorio predefinido. Primero creamos la ruta donde se va a almacenar el
	 * archivo.
	 */
	public String saveFile() {
		log.info("Ejecutando ==> saveFile()");
		String path;
		String nombreAdelante = null;

		
			path = "No grabo";
			Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
			String fechaFormato = formatoFecha.format(timestamp);

			
			try {
				//String archivosDeComercios = "comercios/docAdjuntos";
				String pathAux = "Comercios";
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
				
				String codPosnet = codComercio.getCodigoPosnet().trim();
				if (codPosnet.length() < 4) {
					 codPosnetStr = ("0000" + codPosnet).substring(("0000" + codPosnet).length() - 4);
				}else {
					 codPosnetStr = codPosnet;
				}
				
				 nombreAdelante = codPosnetStr+"-"+fechaFormato;
				
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
				
				

				WrapperFile wrapperFile = new WrapperFile(null, null, nombreAdelante + "-" + uploadedFile.getName(), fechaFormato, nombreAdelante + "-" + uploadedFile.getName(), timestamp);
				wrapperFile.setIdWrappers(new Long(wrapperFile.hashCode()));
				//wrapperFile.setListaDocumentos(listAuxTipoDigital);
				listTipoDocumentos.add(wrapperFile);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				String javaScriptText = "window.opener.recargar();";
				AddResource addResource = AddResourceFactory.getInstance(facesContext);
				addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
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
				DigitalComercio dig = null;
				Iterator it = codComercio.getDocAdjuntos().iterator();
				while (it.hasNext()) {
					DigitalComercio d = (DigitalComercio) it.next();
					if (d.getIdDigital().equals(fil.getIdDocumentoAdjunto())) {
						dig = d;
						break;
					}
				}
				if (dig != null)
					codComercio.getDocAdjuntos().remove(dig);
			}
		}
		return "";
	}
	
	
	public void armarDocumentosAdj() {

		if (!listTipoDocumentos.isEmpty()) {
			if (codComercio.getDocAdjuntos() == null)
				codComercio.setDocAdjuntos(new HashSet());
			Iterator ite = listTipoDocumentos.iterator();
			while (ite.hasNext()) {
				WrapperFile arAd = (WrapperFile) ite.next();
				if (arAd.getIdDocumentoAdjunto() == null) {
					DigitalComercio digit = new DigitalComercio();
					digit.setDescripcion(arAd.getDescripcion());
					digit.setIdDigital(null);
					digit.setIdOperador(Session.getOperador().getCodigo());
					digit.setCodComercio(codComercio);
					digit.setTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
					
					digit.setUrl(arAd.getPath());
					codComercio.getDocAdjuntos().add(digit);
				}
			}
		} else {
			codComercio.setDocAdjuntos(null);
		}

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

			
			String amazonS3 = "https://s3-sa-east-1.amazonaws.com/tarjetafiel/Comercios/";
			
		//	String ruta = "";
		//	String servidor = "77";"/../archivos/"
			
		    	ejecutarJavaScript("popup('" + amazonS3  + ruta + "/"  + element.getNombreArchivo() + "',700,400);");
			
						
			//	ejecutarJavaScript("popup('" + "/../archivos/comercios/docAdjuntos/" + ruta + "/"
			//			+ element.getNombreArchivo() + "',700,400);");
			

		} catch (Exception e) {
			log.info("Error al intentar leer el archivo");
			e.printStackTrace();
		}
		return null;
	}



	


	public String eliminarArchivoAdjuntoDebito() {
		if (alta) {
			Long id = new Long(Session.getRequestParameter("idProcesoAtributoDebito"));
			listArchivosAdjuntosDebito = ComprobanteUtil.eliminarArchivoAdjunto(listArchivosAdjuntosDebito, id);

			if (listArchivosAdjuntosDebito == null || listArchivosAdjuntosDebito.size() == 0)
				mostraTablaArchivosAdjuntosDebito = false;
		}
		return null;
	}


	/*
	 * public String abrirArchivoAdjunto(){ if(!listArchivosAdjuntosDebito.isEmpty()){ try { ArchivoAdjunto element = (ArchivoAdjunto)
	 * listArchivosAdjuntosDebito.get(0); String url = element.getURL(); log.info(url); //
	 * ejecutarJavaScript("popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);)");
	 * // ejecutarJavaScript("popupWindow=open(" + url + ", window, param);" + // "if (popupWindow.opener == null) popupWindow.opener = self;");
	 * ejecutarJavaScript("popup('" + "/../archivos/"+ Archivo.archivosDeProveedores +"/" + element.getNombreArchivo() +
	 * "',1000,700), 'titlebar=no';"); } catch (Exception e) { log.info("Error al intentar leer el archivo"); e.printStackTrace(); } } return null; }
	 */

	/*
	 * public void armarDocumentosAdj() { Iterator ite = listArchivosAdjuntos.iterator(); while (ite.hasNext()) { ArchivoAdjunto arAd =
	 * (ArchivoAdjunto)ite.next(); try { codComercio.agregarDocumentoAdjunto(new DocumentoAdjunto(arAd.getNombreArchivo())); } catch
	 * (DocumentoAdjuntoDuplicateException e) { error.agregar("Ha ocurrido un error al intentar grabar el comprobante"); e.printStackTrace(); } } }
	 */

	public String saveFilePresenta() {
		try {

			int size = new Long(imagenPresenta.getSize()).intValue();
			String path = Archivo.grabarArchivo(imagenPresenta.getInputStream(), imagenPresenta.getName(), size, Archivo.archivosDeProveedores);
			if (path != null && !path.equals("No grabo")) {
				urlArchivoPresenta = path;
			}
		} catch (Exception x) {
			x.printStackTrace();
			return null;
		}
		return null;
	}


	public String abrirArchivoAdjuntoPresenta() {
		if (urlArchivoPresenta != null && urlArchivoPresenta != "") {
			try {
				ejecutarJavaScript("popup('" + "/../archivos/" + Archivo.archivosDeProveedores + "/" + urlArchivoPresenta
						+ "',1000,700), 'titlebar=no';");
			} catch (Exception e) {
				log.info("Error al intentar leer el archivo");
				e.printStackTrace();
			}
		}
		return null;
	}


	public String abrirArchivoAdjunto() {
		if (urlArchivo != null && urlArchivo != "") {
			try {
				ejecutarJavaScript("popup('" + "/../archivos/" + Archivo.archivosDeProveedores + "/" + urlArchivo + "',1000,700), 'titlebar=no';");
			} catch (Exception e) {
				log.info("Error al intentar leer el archivo");
				e.printStackTrace();
			}
		}
		return null;
	}


	public List getEstadoItems() {
		return estadoItems;
	}


	public void setEstadoItems(List estadoItems) {
		this.estadoItems = estadoItems;
	}


	public List getCodcomercioList() {
		return codcomercioList;
	}


	public void setCodcomercioList(List codcomercioList) {
		this.codcomercioList = codcomercioList;
	}


	public boolean isEmpresaCargada() {
		return empresaCargada;
	}


	public void setEmpresaCargada(boolean empresaCargada) {
		this.empresaCargada = empresaCargada;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public SucEmpresa getSucEmpresa() {
		return sucEmpresa;
	}


	public void setSucEmpresa(SucEmpresa sucEmpresa) {
		this.sucEmpresa = sucEmpresa;
	}


	public List getListSucEmp() {
		return listSucEmp;
	}


	public void setListSucEmp(List listSucEmp) {
		this.listSucEmp = listSucEmp;
	}


	public HtmlSelectOneMenu getSucursalHtml() {
		return sucursalHtml;
	}


	public void setSucursalHtml(HtmlSelectOneMenu sucursalHtml) {
		this.sucursalHtml = sucursalHtml;
	}


	public String getTelefonoSucursal() {
		return telefonoSucursal;
	}


	public void setTelefonoSucursal(String telefonoSucursal) {
		this.telefonoSucursal = telefonoSucursal;
	}


	public boolean isSucursalCargada() {
		return sucursalCargada;
	}


	public void setSucursalCargada(boolean sucursalCargada) {
		this.sucursalCargada = sucursalCargada;
	}


	public List getListaAutorizados() {
		return listaAutorizados;
	}


	public void setListaAutorizados(List listaAutorizados) {
		this.listaAutorizados = listaAutorizados;
	}


	public List getListaResponsables() {
		return listaResponsables;
	}


	public void setListaResponsables(List listaResponsables) {
		this.listaResponsables = listaResponsables;
	}


	public List getListaPosiblesIndividuos() {
		return listaPosiblesIndividuos;
	}


	public void setListaPosiblesIndividuos(List listaPosiblesIndividuos) {
		this.listaPosiblesIndividuos = listaPosiblesIndividuos;
	}


	public CodComercio getCodcomercioFiltro() {
		return codcomercioFiltro;
	}


	public void setCodcomercioFiltro(CodComercio codcomercioFiltro) {
		this.codcomercioFiltro = codcomercioFiltro;
	}


	// public List getTemporalFP() {
	// return temporalFP;
	// }
	// public void setTemporalFP(List temporalFP) {
	// this.temporalFP = temporalFP;
	// }
	public List getTablaDeFormaDePago() {
		return tablaDeFormaDePago;
	}


	public void setTablaDeFormaDePago(List tablaDeFormaDePago) {
		this.tablaDeFormaDePago = tablaDeFormaDePago;
	}


	public void agregarUnaListaPrecio(ComercioListaPrecio comercioListaPrecio) {
		listaDePrecios.add(new WrapperListaPrecio(comercioListaPrecio, true));
	}


	public List getListaDePrecios() {
		return listaDePrecios;
	}


	public void setListaDePrecios(List listaDePrecios) {
		this.listaDePrecios = listaDePrecios;
	}


	public boolean isEmpresaExistente() {
		return empresaExistente;
	}


	public void setEmpresaExistente(boolean empresaExistente) {
		this.empresaExistente = empresaExistente;
	}


	public IndividuoEvaluacion getIndividuoEvaluacion() {
		return individuoEvaluacion;
	}


	public void setIndividuoEvaluacion(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}


	public List getTablaDeImpuestos() {
		return tablaDeImpuestos;
	}


	public void setTablaDeImpuestos(List tablaDeImpuestos) {
		this.tablaDeImpuestos = tablaDeImpuestos;
	}


	public Long getJurisdiccionSeleccionada() {
		return jurisdiccionSeleccionada;
	}


	public void setJurisdiccionSeleccionada(Long jurisdiccionSeleccionada) {
		this.jurisdiccionSeleccionada = jurisdiccionSeleccionada;
	}


	public List getListaDeJurisdicciones() {
		return listaDeJurisdicciones;
	}


	public void setListaDeJurisdicciones(List listaDeJurisdicciones) {
		this.listaDeJurisdicciones = listaDeJurisdicciones;
	}

	public class WrapperIndividuo {
		private int indice;
		private String nombre, cuil, documento, cargo, telefono = "";
		private boolean soyNuevo;
		private static final int AUTORIZADO = 1;
		private static final int RESPONSABLE = 2;


		/**
		 * tipoIndividuo puede ser Autorizado o Responsable. Intente con WrapperIndividuo.AUTORIZADO o ..... soyNuevo indica si ya figura como
		 * responsable o autorizado en la base de datos
		 * */
		public WrapperIndividuo(IndividuoEvaluacion individuo, int tipoIndividuo, boolean soyNuevo, ActividadEvaluacion actividad) {
			switch (tipoIndividuo) {
			case AUTORIZADO:
				this.indice = ++posicionListaAutorizados;
				break;
			case RESPONSABLE:
				this.indice = ++posicionListaResponsables;
				break;
			}
			this.nombre = individuo.getApellido() + ", " + individuo.getNombres();
			this.documento = individuo.getNroDocumento();
			this.cargo = actividad.getCargo();
			this.cuil = individuo.getCuil();
			List telef = Convertidores.setToList(individuo.getTelefonos());
			if (!telef.isEmpty()) {
				this.telefono = ((Telefonos) telef.get(0)).getTelefono().getNroTlefono();
			}

		}


		public String getCargo() {
			return cargo;
		}


		public void setCargo(String cargo) {
			this.cargo = cargo;
		}


		public String getDocumento() {
			return documento;
		}


		public void setDocumento(String documento) {
			this.documento = documento;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public boolean isSoyNuevo() {
			return soyNuevo;
		}


		public void setSoyNuevo(boolean soyNuevo) {
			this.soyNuevo = soyNuevo;
		}


		public String getTelefono() {
			return telefono;
		}


		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}


		public String getCuil() {
			return cuil;
		}


		public void setCuil(String cuil) {
			this.cuil = cuil;
		}

	}

	public class WrapperIndividuoLibre {
		private int indice;
		private String nombre, documento, cargo, telefono = "";
		private String cuil;
		private boolean soyNuevo, paraResponsable = false, paraAutorizado = false;
		public boolean permitoResponsable;
		public boolean permitoAutorizado;


		/**
		 * tipoIndividuo puede ser Autorizado o Responsable. Intente con WrapperIndividuo.AUTORIZADO o ..... soyNuevo indica si ya figura como
		 * responsable o autorizado en la base de datos
		 * */
		public WrapperIndividuoLibre(IndividuoEvaluacion individuo, boolean permitoResponsable, boolean permitoAutorizado,
				ActividadEvaluacion actividad) {
			this.indice = ++posicionListaPosibles;
			this.nombre = individuo.getApellido() + ", " + individuo.getNombres();
			this.documento = individuo.getNroDocumento();
			this.cargo = actividad.getCargo();
			this.cuil = individuo.getCuil();
			List telef = Convertidores.setToList(individuo.getTelefonos());
			if (!telef.isEmpty()) {
				this.telefono = ((Telefonos) telef.get(0)).getTelefono().getNroTlefono();
			}
			this.permitoAutorizado = permitoAutorizado;
			this.permitoResponsable = permitoResponsable;
		}


		public String getCargo() {
			return cargo;
		}


		public void setCargo(String cargo) {
			this.cargo = cargo;
		}


		public String getDocumento() {
			return documento;
		}


		public void setDocumento(String documento) {
			this.documento = documento;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public boolean isParaAutorizado() {
			return paraAutorizado;
		}


		public void setParaAutorizado(boolean paraAutorizado) {
			this.paraAutorizado = paraAutorizado;
		}


		public boolean isParaResponsable() {
			return paraResponsable;
		}


		public void setParaResponsable(boolean paraResponsable) {
			this.paraResponsable = paraResponsable;
		}


		public boolean isSoyNuevo() {
			return soyNuevo;
		}


		public void setSoyNuevo(boolean soyNuevo) {
			this.soyNuevo = soyNuevo;
		}


		public String getTelefono() {
			return telefono;
		}


		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}


		public String getCuil() {
			return cuil;
		}


		public void setCuil(String cuil) {
			this.cuil = cuil;
		}


		public boolean isPermitoAutorizado() {
			return permitoAutorizado;
		}


		public void setPermitoAutorizado(boolean permitoAutorizado) {
			this.permitoAutorizado = permitoAutorizado;
		}


		public boolean isPermitoResponsable() {
			return permitoResponsable;
		}


		public void setPermitoResponsable(boolean permitoResponsable) {
			this.permitoResponsable = permitoResponsable;
		}

	}

	public class WrapperFormaPago {
		private int indice;
		private ComercioFormaPago comercioFormaPago;
		private boolean soyNuevo;


		/**
		 * 
		 * */
		public WrapperFormaPago(ComercioFormaPago comercioFormaPago, boolean soyNuevo, Empresa empresa) {
			this.comercioFormaPago = comercioFormaPago;
			this.soyNuevo = soyNuevo;
			this.indice = ++posicionFormaPago;
			this.comercioFormaPago.setEmpresa(empresa);
		}


		public ComercioFormaPago getComercioFormaPago() {
			return comercioFormaPago;
		}


		public void setComercioFormaPago(ComercioFormaPago comercioFormaPago) {
			this.comercioFormaPago = comercioFormaPago;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public boolean isSoyNuevo() {
			return soyNuevo;
		}


		public void setSoyNuevo(boolean soyNuevo) {
			this.soyNuevo = soyNuevo;
		}

	}

	public class WrapperListaPrecio {
		private int indice;
		private ComercioListaPrecio comercioListaPrecio;
		private boolean soyNuevo;


		/**
		 * 
		 * */
		public WrapperListaPrecio(ComercioListaPrecio comercioListaPrecio, boolean soyNuevo) {
			this.comercioListaPrecio = comercioListaPrecio;
			this.soyNuevo = soyNuevo;
			this.indice = comercioListaPrecio.getCodigoPosnet().intValue();
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public ComercioListaPrecio getComercioListaPrecio() {
			return comercioListaPrecio;
		}


		public void setComercioListaPrecio(ComercioListaPrecio comercioListaPrecio) {
			this.comercioListaPrecio = comercioListaPrecio;
		}


		public boolean isSoyNuevo() {
			return soyNuevo;
		}


		public void setSoyNuevo(boolean soyNuevo) {
			this.soyNuevo = soyNuevo;
		}

	}

	public class WrapperTablaDeImpuestos {
		private int indice;
		private ComercioListaPrecio comercioListaPrecio;
		private boolean soyNuevo;


		/**
		 * 
		 * */
		public WrapperTablaDeImpuestos(ComercioListaPrecio comercioListaPrecio, boolean soyNuevo) {
			this.comercioListaPrecio = comercioListaPrecio;
			this.soyNuevo = soyNuevo;
			this.indice = ++posicionListaPrecio;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public ComercioListaPrecio getComercioListaPrecio() {
			return comercioListaPrecio;
		}


		public void setComercioListaPrecio(ComercioListaPrecio comercioListaPrecio) {
			this.comercioListaPrecio = comercioListaPrecio;
		}


		public boolean isSoyNuevo() {
			return soyNuevo;
		}


		public void setSoyNuevo(boolean soyNuevo) {
			this.soyNuevo = soyNuevo;
		}

	}


	public boolean isHabilitaAgregar() {
		return habilitaAgregar;
	}


	public void setHabilitaAgregar(boolean habilitaAgregar) {
		this.habilitaAgregar = habilitaAgregar;
	}


	public boolean isHabilitaQuitar() {
		return habilitaQuitar;
	}


	public void setHabilitaQuitar(boolean habilitaQuitar) {
		this.habilitaQuitar = habilitaQuitar;
	}


	public List getListaDeSucursalesFiel() {
		return listaDeSucursalesFiel;
	}


	public void setListaDeSucursalesFiel(List listaDeSucursalesFiel) {
		this.listaDeSucursalesFiel = listaDeSucursalesFiel;
	}


	public UploadedFile getImagen() {
		return imagen;
	}


	public void setImagen(UploadedFile imagen) {
		this.imagen = imagen;
	}


	public boolean isMostraArchivosAdjuntos() {
		return mostraArchivosAdjuntos;
	}


	public void setMostraArchivosAdjuntos(boolean mostraArchivosAdjuntos) {
		this.mostraArchivosAdjuntos = mostraArchivosAdjuntos;
	}


	public String getUrlArchivo() {
		return urlArchivo;
	}


	public void setUrlArchivo(String urlArchivo) {
		this.urlArchivo = urlArchivo;
	}


	public String getUrlArchivoPresenta() {
		return urlArchivoPresenta;
	}


	public void setUrlArchivoPresenta(String urlArchivoPresenta) {
		this.urlArchivoPresenta = urlArchivoPresenta;
	}


	public UploadedFile getImagenPresenta() {
		return imagenPresenta;
	}


	public void setImagenPresenta(UploadedFile imagenPresenta) {
		this.imagenPresenta = imagenPresenta;
	}


	public CuitValido getCuitValido() {
		return cuitValido;
	}


	public void setCuitValido(CuitValido cuitValido) {
		this.cuitValido = cuitValido;
	}


	public boolean isEnviarMail() {
		return enviarMail;
	}


	public void setEnviarMail(boolean enviarMail) {
		this.enviarMail = enviarMail;
	}


	public boolean isGenerarPDF() {
		return generarPDF;
	}


	public void setGenerarPDF(boolean generarPDF) {
		this.generarPDF = generarPDF;
	}


	public boolean isImprimirLiquidacion() {
		return imprimirLiquidacion;
	}


	public void setImprimirLiquidacion(boolean imprimirLiquidacion) {
		this.imprimirLiquidacion = imprimirLiquidacion;
	}


	public String getTipoLiquidacion() {
		return tipoLiquidacion;
	}


	public void setTipoLiquidacion(String tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
	}


	public Long getIdTipoLiqSeleccionada() {
		return idTipoLiqSeleccionada;
	}


	public void setIdTipoLiqSeleccionada(Long idTipoLiqSeleccionada) {
		this.idTipoLiqSeleccionada = idTipoLiqSeleccionada;
	}


	public HtmlSelectOneMenu getTipoLiqHtml() {
		return tipoLiqHtml;
	}


	public void setTipoLiqHtml(HtmlSelectOneMenu tipoLiqHtml) {
		this.tipoLiqHtml = tipoLiqHtml;
	}


	public List getListTipoLiq() {
		return listTipoLiq;
	}


	public void setListTipoLiq(List listTipoLiq) {
		this.listTipoLiq = listTipoLiq;
	}


	public boolean isConfirmaCambioLiq() {
		return confirmaCambioLiq;
	}


	public void setConfirmaCambioLiq(boolean confirmaCambioLiq) {
		this.confirmaCambioLiq = confirmaCambioLiq;
	}


	public boolean isListaVaciaFmaPagoCuit() {
		return listaVaciaFmaPagoCuit;
	}


	public void setListaVaciaFmaPagoCuit(boolean listaVaciaFmaPagoCuit) {
		this.listaVaciaFmaPagoCuit = listaVaciaFmaPagoCuit;
	}


	public boolean isMostrarFmaPagoCuit() {
		return mostrarFmaPagoCuit;
	}


	public void setMostrarFmaPagoCuit(boolean mostrarFmaPagoCuit) {
		this.mostrarFmaPagoCuit = mostrarFmaPagoCuit;
	}


	public String getPathDebito() {
		return pathDebito;
	}


	public void setPathDebito(String pathDebito) {
		this.pathDebito = pathDebito;
	}


	public UploadedFile getImagenDebito() {
		return imagenDebito;
	}


	public void setImagenDebito(UploadedFile imagenDebito) {
		this.imagenDebito = imagenDebito;
	}


	public boolean isMostraTablaArchivosAdjuntosDebito() {
		return mostraTablaArchivosAdjuntosDebito;
	}


	public void setMostraTablaArchivosAdjuntosDebito(
			boolean mostraTablaArchivosAdjuntosDebito) {
		this.mostraTablaArchivosAdjuntosDebito = mostraTablaArchivosAdjuntosDebito;
	}


	public List getListArchivosAdjuntosDebito() {
		return listArchivosAdjuntosDebito;
	}


	public void setListArchivosAdjuntosDebito(List listArchivosAdjuntosDebito) {
		this.listArchivosAdjuntosDebito = listArchivosAdjuntosDebito;
	}


	public Long getCuitIndividuoAAgregar() {
		return cuitIndividuoAAgregar;
	}


	public void setCuitIndividuoAAgregar(Long cuitIndividuoAAgregar) {
		this.cuitIndividuoAAgregar = cuitIndividuoAAgregar;
	}


	public String getCodigoPosnet() {
		return codComercio.getCodigoPosnet();
	}


	public void setCodigoPosnet(String codigoPosnet) {
		if (refrescarSucursal)
			log.info("codigo de posnet codcomercio" + codigoPosnet );
			codComercio.setCodigoPosnet(codigoPosnet);
	}


	public boolean isDebitoAutomatico() {
		if (codComercio.getDebitoAutomatico() != null)
			return Convertidores.getBoolean(codComercio.getDebitoAutomatico().toString());
		return false;
	}


	public void setDebitoAutomatico(boolean debitoAutomatico) {
		if (refrescarSucursal) {
			codComercio.setDebitoAutomatico(Character.valueOf(Convertidores.getSorN(debitoAutomatico).charAt(0)));
		}
	}


	public boolean isPresentaCupones() {
		if (codComercio.getPresentaCupones() != null) {
			return Convertidores.getBoolean(codComercio.getPresentaCupones().toString());

		}
		return false;
	}


	public void setPresentaCupones(boolean presentaCupones) {
		if (refrescarSucursal) {
			codComercio.setPresentaCupones(Character.valueOf(Convertidores.getSorN(presentaCupones).charAt(0)));
		}
	}


	/*
	 * @I4039 public boolean isExcluyeCargos() { Codigo vuelto atras por pedido del cliente if(codComercio.getExcluyeCargoPago()!=null){ return
	 * Convertidores.getBoolean(codComercio.getExcluyeCargoPago().toString());
	 * 
	 * } return false; }
	 * 
	 * public void setExcluyeCargos(boolean excluyeCargos) { if(refrescarSucursal){
	 * codComercio.setExcluyeCargoPago(Convertidores.getSorN(excluyeCargos)); } }
	 * 
	 * @F4039
	 *//*
		 * Set flag para saber si se esta editando el codigo de comercio.
		 */
	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}


	/*
	 * Get flag para saber si se esta editando el codigo de comercio.
	 */
	public boolean isEdicion() {
		return edicion;
	}


	private List cargarSucursalesSinPosnet()
	{
		Filtro filtroCodCom = new Filtro();
		filtroCodCom.agregarCampoOperValor("sucEmpresa.empresa.idEmpresa", Filtro.IGUAL, empresa.getIdEmpresa());
		List sucursalesSinPostent = new ArrayList();

		try {
			// codComercio con posnet
			List listCodComercio = transaccionesService.getCodComercioService().getCodComercio(filtroCodCom);

			// Sucursales grabadas en la empresa.
			for (Iterator suc = sucursales.iterator(); suc.hasNext();)
			{
				SucEmpresa itemSucEmpresa = (SucEmpresa) suc.next();

				if (listCodComercio != null && listCodComercio.size() > 0)
				{
					for (Iterator iter = listCodComercio.iterator(); iter.hasNext();)
					{
						CodComercio itemCodComercio = (CodComercio) iter.next();
						if (itemCodComercio.getSucEmpresa().getIdSucEmpresa() != itemSucEmpresa.getIdSucEmpresa())
						{
							sucursalesSinPostent.add(itemSucEmpresa);
							/*
							 * if(itemCodComercio.getCodigoPosnet() == null) { sucursalesSinPostent.add(itemSucEmpresa); break; }
							 */
						}

					}
				}
				else
				{
					sucursalesSinPostent.add(itemSucEmpresa);
				}

			}

		} catch (CodComercioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return sucursalesSinPostent;
	}


	public void setAltaNavegacion(boolean altaNavegacion) {
		this.altaNavegacion = altaNavegacion;
	}


	public boolean isAltaNavegacion() {
		return altaNavegacion;
	}


	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}


	public List getListTipoDocumentos() {
		return listTipoDocumentos;
	}


	public void setListTipoDocumentos(List listTipoDocumentos) {
		this.listTipoDocumentos = listTipoDocumentos;
	}
	
	

}
