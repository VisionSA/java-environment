package com.bizitglobal.webapp.faces.beans.transacciones;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDetalleException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDetalleReglaException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoDetalleTargetException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoException;

import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalleRegla;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalleTarget;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;

import com.bizitglobal.webapp.faces.beans.contabilidad.PlanCuentaBean;
import com.bizitglobal.webapp.faces.beans.transacciones.ConceptoBean.WrapperDetalle;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;


@SuppressWarnings({"rawtypes","unchecked"})
public class ConceptoDetalleBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ConceptoDetalleBean.class);
	private static int contadorRegla = 0;
	private static int contadorTarget = 0;

	private boolean verComoImpacta;
	private boolean esAltaConcepto;
	private List listReglas;
	private List listTarget;
	private Concepto conOrigi;
	private HtmlSelectBooleanCheckbox incluyeFechaCierre;
	private boolean noIncluyeFechaCierre = true; // el valor true incluye la fecha de cierre

	// String para guardar el numero de cuenta y el nombre de las cuentas en debe y haber.
	private Long cuentaDebe = null;
	private Long cuentaHaber = null;
	// Los siguientes enmascaran la cuenta para presentarla en pantalla
	private String debe = "";
	private String haber = "";
	private String ctacontablehaber;
	private String ctacontabledebe;

	// todos los html one menu con datos fijos
	private HtmlSelectOneMenu menuParent;
	private HtmlSelectOneMenu menuPrioridad;
	private HtmlSelectOneMenu menuSigno;
	private HtmlSelectOneMenu menuTipo;
	// Las listas de datos para los html selectOneMenu
	private List listaParent;
	private List listaPrioridad;
	private List listaSigno;
	private List listaTipo;
	// Los long para guardar sus valores
	private Integer signoElegido;
	private Long prioridadElegida;
	private Long tipoElegido;
	private Long parentElegido;
	private ConceptoDetalle conceptodetalle;
	private String idConceptoDetalleFiltro;
	private String nombreFiltro;
	private Date vigenciaDesdeFiltro;
	private Date vigenciaHastaFiltro;
	private String conceptoFiltro;
	private HtmlSelectOneMenu conceptoElegido;
	private boolean contemplarFechas;
	private Long idConceptoDetalleHidden;

	private HtmlSelectOneMenu conceptoSeleccionado;
	private Date fechaDesde;
	private Date fechaHasta;

	private boolean conceptoSeleccionable = true;
	private boolean estado;
	private boolean accesoPopup;

	// definicion de un list del objeto base
	private List conceptodetalleList;

	// Listas para la presentacion(HtmlSelectItems).
	private List conceptoList = new ArrayList();
	private List conceptoItems = new ArrayList();

	// Objetos Relacionados.
	private Long idConceptoSeleccionada;

	private String focoHidden;


	public ConceptoDetalleBean() {
		super();
		borrar();
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public ConceptoDetalle getConceptoDetalle() {
		return conceptodetalle;
	}


	public void setConceptoDetalle(ConceptoDetalle conceptodetalle) {
		this.conceptodetalle = conceptodetalle;
	}


	public Long getIdConceptoDetalleHidden() {
		return idConceptoDetalleHidden;
	}


	public void setIdConceptoDetalleHidden(Long idConceptoDetalleHidden) {
		this.idConceptoDetalleHidden = idConceptoDetalleHidden;
	}


	public List getConceptoItems() {
		return conceptoItems;
	}


	public void setConceptoItems(List conceptoItems) {
		this.conceptoItems = conceptoItems;
	}


	public Long getIdConceptoSeleccionada() {
		return idConceptoSeleccionada;
	}


	public void setIdConceptoSeleccionada(Long idConceptoSeleccionada) {
		this.idConceptoSeleccionada = idConceptoSeleccionada;
	}


	public List getConceptoDetalleList() {
		return conceptodetalleList;
	}


	public void setConceptoDetalleList(List object) {
		this.conceptodetalleList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CONCEPTODETALLE
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		accesoPopup = false;
		armarMenusDesplegables();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amConceptoDetalle";
	}


	private void cargarItems() {
		if (conceptoItems.size() != conceptoList.size()) {
			conceptoItems = Util.cargarSelectItem(conceptoList);
		}
	}


	public void armarMenusDesplegables() {
		menuParent = new HtmlSelectOneMenu();
		menuPrioridad = new HtmlSelectOneMenu();
		menuSigno = new HtmlSelectOneMenu();
		menuTipo = new HtmlSelectOneMenu();
		// listaSigno
		listaSigno = new ArrayList();
		listaSigno.add(new SelectItem(new Integer(1), "Positivo"));
		listaSigno.add(new SelectItem(new Integer(-1), "Negativo"));
		// listaPrioridad
		listaPrioridad = new ArrayList();
		listaPrioridad.add(new SelectItem(new Long(1), "1"));
		listaPrioridad.add(new SelectItem(new Long(2), "2"));
		// listaTipo
		listaTipo = new ArrayList();
		listaTipo.add(new SelectItem(new Long(1), "CL"));
		listaTipo.add(new SelectItem(new Long(2), "CO"));
		listaParent = new ArrayList();
	}


	private String verificarBase(String query) {
		if (query.indexOf("[") > 0 || query.indexOf("]") > 0) {
			return Error.ATRIBUTO_QUERY_SINTAX_CORCHETE;
		}
		return null;
	}


	public boolean validarTargetYReglas() {
		Iterator iterUno = listReglas.iterator();
		while (iterUno.hasNext()) {
			WrapperRegla wR = (WrapperRegla) iterUno.next();
			if (wR.getConceptoDetalleRegla().getDetalle() == null || wR.getConceptoDetalleRegla().getDetalle().compareTo("") == 0) {
				error.agregar(Error.TRAN_CLASE_PARA_DETALLE_REGLA_REQUERIDO);
				break;
			}
		}
		boolean agregadoUno = false;
		boolean agregadoDos = false;
		Iterator iterDos = listTarget.iterator();
		while (iterDos.hasNext()) {
			WrapperTarget wT = (WrapperTarget) iterDos.next();
			if (!agregadoUno && (wT.getConceptoDetalleTarget().getDetalle() == null || wT.getConceptoDetalleTarget().getDetalle().compareTo("") == 0)) {
				error.agregar(Error.TRAN_DETALLE_PARA_DETALLE_TARGET_REQUERIDO);
				agregadoUno = true;
			}
			if (!agregadoDos && (wT.getConceptoDetalleTarget().getSql() == null || wT.getConceptoDetalleTarget().getSql().compareTo("") == 0)) {
				error.agregar(Error.TRAN_SQL_PARA_DETALLE_TARGET_REQUERIDO);
				agregadoDos = true;
			}
			String query = wT.getConceptoDetalleTarget().getSql().toUpperCase();
			String msjValor = verificarBase(query);
			if (msjValor == null) {
				boolean agregar = false;
				if (query.indexOf("SELECT") < 0) {
					agregar = true;
					msjValor = Error.ATRIBUTO_QUERY_SINTAX_SELECT;
					error.agregar("Error: SQL del Detalle Target número " + wT.getIndice() + " " + msjValor);
				}
				if (query.indexOf("COUNT") < 0) {
					agregar = true;
					msjValor = Error.ATRIBUTO_QUERY_SINTAX_COUNT;
					error.agregar("Error: SQL del Detalle Target número " + wT.getIndice() + " " + msjValor);
				}
				if (query.indexOf("FROM") < 0) {
					agregar = true;
					msjValor = Error.ATRIBUTO_QUERY_SINTAX_FROM;
					error.agregar("Error: SQL del Detalle Target número " + wT.getIndice() + " " + msjValor);
				}
				if (!agregar) {
					if (!transaccionesService.getConceptoDetalleService().checkSql(wT.getConceptoDetalleTarget().getSql())) {
						error.agregar("Error: SQL del Detalle Target número " + wT.getIndice() + " Hay un error en la sentencia SQL");
					}

				}
			} else {

				error.agregar("Error: SQL del Target número " + wT.getIndice() + Error.ATRIBUTO_QUERY_SINTAX_CORCHETE);
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public void inicializarDesdePopup(ConceptoDetalle concep, Concepto conOr, boolean esAltaConcepto) {
		accesoPopup = true;
		conceptoSeleccionable = true;
		this.conceptodetalle = concep;
		this.esAltaConcepto = esAltaConcepto;
		armarMenusDesplegables();
		listaParent.clear();
		this.conOrigi = conOr;
		listaParent.add(new SelectItem(new Long(0), ""));
		if (conOrigi.getConceptoDetalleSet() != null) {
			Iterator iter = conOrigi.getConceptoDetalleSet().iterator();
			while (iter.hasNext()) {
				ConceptoDetalle coDe = (ConceptoDetalle) iter.next();
				if (conceptodetalle.getIdConceptoDetalle() == null) {
					listaParent.add(new SelectItem(coDe.getIdConceptoDetalle(), coDe.getNombre()));
				} else {
					if (coDe.getIdConceptoDetalle().compareTo(conceptodetalle.getIdConceptoDetalle()) != 0) {
						if ((coDe.getParent() != null && coDe.getParent().compareTo(conceptodetalle.getIdConceptoDetalle()) != 0)
								|| coDe.getParent() == null) {
							listaParent.add(new SelectItem(coDe.getIdConceptoDetalle(), coDe.getNombre()));
						}

					}
				}
			}
		}
		if (conceptodetalle.getIdConceptoDetalle() == null)
			estado = true;
		cuentaDebe = conceptodetalle.getCtacontabledebe();
		cuentaHaber = conceptodetalle.getCtacontablehaber();
		if (cuentaDebe != null) {
			log.info("Setearemos la cuenta debe");
			try {
				PlanCuentaDos planDebe = contabilidadService.getPlanCuentaDosService().leerPlanCuenta(cuentaDebe);
				ctacontabledebe = planDebe.getIdPlanCuenta() + " - " + planDebe.getTitulo();
				// setDebe(ctacontabledebe);
			} catch (PlanCuentaDosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (cuentaHaber != null) {
			log.info("Setearemos la cuenta haber");
			try {
				PlanCuentaDos planHaber = contabilidadService.getPlanCuentaDosService().leerPlanCuenta(cuentaHaber);
				ctacontablehaber = planHaber.getIdPlanCuenta() + " - " + planHaber.getTitulo();
				// setHaber(ctacontablehaber);
			} catch (PlanCuentaDosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setDebe(ctacontabledebe);
		setHaber(ctacontablehaber);
		if (conceptodetalle.getFechavigenciahasta() == null) {
			log.info("la fecha de cierre es nula");
			incluyeFechaCierre.setValue(new Boolean(false));
			fechaHasta = null;
			noIncluyeFechaCierre = false;
		} else {
			incluyeFechaCierre.setValue(new Boolean(true));
			noIncluyeFechaCierre = true;
			fechaHasta = conceptodetalle.getFechavigenciahasta();
		}
		fechaDesde = conceptodetalle.getFechavigenciadesde();
		listReglas.clear();
		listTarget.clear();
		List regl = Convertidores.setToList(conceptodetalle.getConceptoDetalleReglaSet());
		List targ = Convertidores.setToList(conceptodetalle.getConceptoDetalleTargetSet());
		Iterator uno = regl.iterator();
		while (uno.hasNext()) {
			ConceptoDetalleRegla con = (ConceptoDetalleRegla) uno.next();
			listReglas.add(new WrapperRegla(con));
		}
		Iterator dos = targ.iterator();
		while (dos.hasNext()) {
			ConceptoDetalleTarget tar = (ConceptoDetalleTarget) dos.next();
			listTarget.add(new WrapperTarget(tar));
		}
		if (conceptodetalle.getIdConceptoDetalle() != null) {
			if (conceptodetalle.getActivo().compareTo("S") == 0) {
				estado = true;
			} else {
				estado = false;
			}
		}
		signoElegido = conceptodetalle.getSigno();
		parentElegido = conceptodetalle.getParent();
		prioridadElegida = conceptodetalle.getPrioridad();
		if (conceptodetalle.getTipo() != null && conceptodetalle.getTipo().compareTo("CL") == 0) {
			tipoElegido = new Long(1);
		} else {
			tipoElegido = new Long(2);
		}
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/transacciones/popup/amConceptoDetallePopup.jsf";
		ejecutarJavaScript("popup('" + path + "',800,600), 'titlebar=no';");
	}


	public String buscarCuentaHaber() {
		log.info("Ir a buscar una cuenta!!!");
		PlanCuentaBean bean = (PlanCuentaBean) Session.getBean("PlanCuentaBean");
		bean.inicializaBusqueda(7);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/contabilidad/buscarPlanesDeCuenta.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	public String buscarCuentaDebe() {
		PlanCuentaBean bean = (PlanCuentaBean) Session.getBean("PlanCuentaBean");
		bean.inicializaBusqueda(8);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/contabilidad/buscarPlanesDeCuenta.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	public String eliminarConceptoDetalle() {
		try {
			transaccionesService.getConceptoDetalleService().borrarConceptoDetalle(idConceptoDetalleHidden);
			conceptodetalleList.remove(new ConceptoDetalle(idConceptoDetalleHidden));
		} catch (ConceptoDetalleException e1) {
			error.agregar("Imposible borrar el conceptodetalle. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el conceptodetalle");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalle.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {

				ConceptoBean bea = (ConceptoBean) Session.getBean("ConceptoBean");
				if (estado) {
					conceptodetalle.setActivo("S");
				} else {
					conceptodetalle.setActivo("N");
				}

				tipoElegido = (Long) menuTipo.getValue();
				conceptodetalle.setParent((Long) menuParent.getValue());
				conceptodetalle.setPrioridad((Long) menuPrioridad.getValue());
				conceptodetalle.setSigno((Integer) menuSigno.getValue());
				if (tipoElegido.compareTo(new Long(1)) == 0) {
					conceptodetalle.setTipo("CL");
				} else {
					conceptodetalle.setTipo("CO");
				}
				conceptodetalle.setCtacontabledebe(cuentaDebe);
				conceptodetalle.setCtacontablehaber(cuentaHaber);
				conceptodetalle.setFechavigenciadesde(new Timestamp(fechaDesde.getTime()));
				if (fechaDesde == null) {
					conceptodetalle.setFechavigenciadesde(null);
				} else {
					conceptodetalle.setFechavigenciadesde(new Timestamp(fechaDesde.getTime()));
				}
				if (fechaHasta == null) {
					conceptodetalle.setFechavigenciahasta(null);
				} else {
					conceptodetalle.setFechavigenciahasta(new Timestamp(fechaHasta.getTime()));
				}
				if (conceptodetalle.getConceptoDetalleReglaSet() == null)
					conceptodetalle.setConceptoDetalleReglaSet(new HashSet());
				if (conceptodetalle.getConceptoDetalleTargetSet() == null)
					conceptodetalle.setConceptoDetalleTargetSet(new HashSet());
				Iterator iterReglas = listReglas.iterator();
				while (iterReglas.hasNext()) {
					WrapperRegla wReglas = (WrapperRegla) iterReglas.next();
					if (wReglas.getConceptoDetalleRegla().getIdConceptosdetallereglas() == null) {
						wReglas.getConceptoDetalleRegla().setConceptoDetalle(conceptodetalle);
						conceptodetalle.getConceptoDetalleReglaSet().add(wReglas.getConceptoDetalleRegla());
					}
				}
				Iterator iterTarget = listTarget.iterator();
				while (iterTarget.hasNext()) {
					WrapperTarget wTarget = (WrapperTarget) iterTarget.next();
					if (wTarget.getConceptoDetalleTarget().getIdConceptostarget() == null) {
						wTarget.getConceptoDetalleTarget().setConceptoDetalle(conceptodetalle);
						conceptodetalle.getConceptoDetalleTargetSet().add(wTarget.getConceptoDetalleTarget());
					}
				}

				// el concepto lo seteo a la hora de guardarlo!!
				if (conceptodetalle.getIdConceptoDetalle() == null) {
					WrapperDetalle wrap = bea.obtenerWrapperDetalle(conceptodetalle);
					bea.getListDetalles().add(wrap);
					if (bea.actualizarDetalles()) {

						// codigo
					}
				} else {
					bea.actualizarDetalles();
				}
				// aca grabo el concepto, para poder cargar el target que este proporciona en los otros
				ejecutarJavaScript("window.opener.recargar();window.close();");

			}

		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del conceptodetalle.");
			e3.printStackTrace();
		}

		return "";
	}


	public void borrar() {
		error.borrar();
		contemplarFechas = true;
		conceptoItems.clear();
		try {
			conceptoList = transaccionesService.getConceptoService().getConcepto(new Filtro());
		} catch (ConceptoException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de conceptodetalle";
		popup.borrar();
		idConceptoSeleccionada = new Long(0);
		conceptoSeleccionado = new HtmlSelectOneMenu();
		conceptoSeleccionado.setValue(new Long(0));
		fechaDesde = new Date();
		fechaHasta = new Date();
		incluyeFechaCierre = new HtmlSelectBooleanCheckbox();
		incluyeFechaCierre.setValue(new Boolean(false));
		noIncluyeFechaCierre = false;
		conceptodetalle = new ConceptoDetalle();
		conceptodetalleList = new ArrayList();
		idConceptoDetalleFiltro = "";
		nombreFiltro = "";
		Calendar fecha = Calendar.getInstance();
		vigenciaHastaFiltro = fecha.getTime();
		fecha.add(Calendar.MONTH, -1);
		vigenciaDesdeFiltro = fecha.getTime();
		conceptoFiltro = "";
		conceptoElegido = new HtmlSelectOneMenu();
		conceptoElegido.setValue(new Long(0));
		idConceptoSeleccionada = new Long(0);
		listReglas = new ArrayList();
		listTarget = new ArrayList();
		menuParent = new HtmlSelectOneMenu();
		menuParent.setValue(null);
		menuPrioridad = new HtmlSelectOneMenu();
		menuPrioridad.setValue(new Long(1));
		menuSigno = new HtmlSelectOneMenu();
		menuSigno.setValue(new Integer(1));
		menuTipo = new HtmlSelectOneMenu();
		menuTipo.setValue(new Long(1));

		signoElegido = new Integer(1);
		prioridadElegida = new Long(1);
		tipoElegido = new Long(1);
		parentElegido = null;

		debe = "";
		haber = "";
		cargarItems();
	}


	public String cancelar() {
		if (accesoPopup) {
			ejecutarJavaScript("window.opener.recargar();window.close();");
		} else {
			borrar();
			return "inicio";
		}
		return null;
	}


	public boolean validar() {
		error.borrar();
		if (conceptodetalle.getNombre() == null || conceptodetalle.getNombre().compareTo("") == 0) {
			error.agregar(Error.TRAN_NOMBRE_CONCEPTO_DETALLE_REQUERIDA);
		}
		if (idConceptoSeleccionada == null) {
			error.agregar(Error.TRAN_CONCEPTO_PARA_DETALLE_REQUERIDO);
		}
		if (fechaDesde == null) {
			error.agregar(Error.TRAN_FECHA_INICIO_DETALLE_REQUERIDA);
		}
		if (!alta) {
			if (fechaDesde != null && fechaDesde.before(Calendar.getInstance().getTime()) && alta) {
				error.agregar(Error.TRAN_FECHA_INICIO_ANTERIOR_A_LA_ACTUAL);
			}
			if (noIncluyeFechaCierre && fechaHasta != null && fechaDesde != null && fechaHasta.before(fechaDesde)) {
				error.agregar(Error.TRAN_FECHA_INICIO_MENOR_FECHA_FIN);
			}
		}
		validarTargetYReglas();
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoConceptoDetalle() {
		return inicializar();
	}


	public String irAModificarConceptoDetalle() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar conceptodetalle";
		return null;
	}


	public String irAListarConceptoDetalle() {
		borrar();
		tituloCorto = "Listado de concepto Detalle";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalle.jsf");
		return "";
	}


	public String listarConceptoDetalle() {
		conceptodetalleList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (idConceptoDetalleFiltro != null && idConceptoDetalleFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("idConceptoDetalle", Filtro.IGUAL, new Long(idConceptoDetalleFiltro));
			}
			if (nombreFiltro != null && nombreFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("nombre", Filtro.LIKE, nombreFiltro);
			}
			// if (conceptoFiltro!=null && conceptoFiltro.compareTo("")!=0) {
			filtro.agregarCampoOperValor("concepto.idConcepto", Filtro.IGUAL, idConceptoSeleccionada);
			// }
			if (contemplarFechas) {
				if (vigenciaDesdeFiltro != null) {
					filtro.agregarCampoOperValor("fechavigenciadesde", Filtro.MAYOR_IGUAL,
							Filtro.getTO_DATE(new Timestamp(vigenciaDesdeFiltro.getTime())));
				}
				if (vigenciaDesdeFiltro != null) {
					filtro.agregarCampoOperValor("fechavigenciahasta", Filtro.MENOR_IGUAL,
							Filtro.getTO_DATE(new Timestamp(vigenciaHastaFiltro.getTime())));
				}
			}
			// filtro.agregarCampoOperValor("", Filtro.IGUAL, conceptodetalle.
			conceptodetalleList = transaccionesService.getConceptoDetalleService().getConceptoDetalle(filtro);
			Iterator iter = conceptodetalleList.iterator();
			while (iter.hasNext())
			{
				ConceptoDetalle element = (ConceptoDetalle) iter.next();
				element.getConcepto().getLabel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarConceptoDetalle.jsf");
		return null;
	}


	public HtmlSelectOneMenu getConceptoSeleccionado() {
		return conceptoSeleccionado;
	}


	public void setConceptoSeleccionado(HtmlSelectOneMenu conceptoSeleccionado) {
		this.conceptoSeleccionado = conceptoSeleccionado;
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public String getConceptoFiltro() {
		return conceptoFiltro;
	}


	public void setConceptoFiltro(String conceptoFiltro) {
		this.conceptoFiltro = conceptoFiltro;
	}


	public String getIdConceptoDetalleFiltro() {
		return idConceptoDetalleFiltro;
	}


	public void setIdConceptoDetalleFiltro(String idConceptoDetalleFiltro) {
		this.idConceptoDetalleFiltro = idConceptoDetalleFiltro;
	}


	public String getNombreFiltro() {
		return nombreFiltro;
	}


	public void setNombreFiltro(String nombreFiltro) {
		this.nombreFiltro = nombreFiltro;
	}


	public Date getVigenciaDesdeFiltro() {
		return vigenciaDesdeFiltro;
	}


	public void setVigenciaDesdeFiltro(Date vigenciaDesdeFiltro) {
		this.vigenciaDesdeFiltro = vigenciaDesdeFiltro;
	}


	public Date getVigenciaHastaFiltro() {
		return vigenciaHastaFiltro;
	}


	public void setVigenciaHastaFiltro(Date vigenciaHastaFiltro) {
		this.vigenciaHastaFiltro = vigenciaHastaFiltro;
	}


	public List getConceptodetalleList() {
		return conceptodetalleList;
	}


	public void setConceptodetalleList(List conceptodetalleList) {
		this.conceptodetalleList = conceptodetalleList;
	}


	public HtmlSelectOneMenu getConceptoElegido() {
		return conceptoElegido;
	}


	public void setConceptoElegido(HtmlSelectOneMenu conceptoElegido) {
		this.conceptoElegido = conceptoElegido;
	}


	public boolean isContemplarFechas() {
		return contemplarFechas;
	}


	public void setContemplarFechas(boolean contemplarFechas) {
		this.contemplarFechas = contemplarFechas;
	}


	public boolean isConceptoSeleccionable() {
		return conceptoSeleccionable;
	}


	public void setConceptoSeleccionable(boolean conceptoSeleccionable) {
		this.conceptoSeleccionable = conceptoSeleccionable;
	}


	public boolean isAccesoPopup() {
		return accesoPopup;
	}


	public void setAccesoPopup(boolean accesoPopup) {
		this.accesoPopup = accesoPopup;
	}


	public String eliminarRegla() {
		int idElim = Integer.valueOf(Session.getRequestParameter("idReglaElim").toString()).intValue();
		WrapperRegla wrap = null;
		Iterator iter = listReglas.iterator();
		while (iter.hasNext()) {
			wrap = (WrapperRegla) iter.next();
			if (wrap.getIndice() == idElim) {
				if (wrap.getConceptoDetalleRegla().getIdConceptosdetallereglas() != null) {
					try {
						transaccionesService.getConceptoDetalleReglaService().borrarConceptoDetalleRegla(wrap.getConceptoDetalleRegla());
						conceptodetalle.getConceptoDetalleReglaSet().remove(wrap.getConceptoDetalleRegla());
					} catch (ConceptoDetalleReglaException e) {
						log.info("problemas al eliminar la regla del detalle");
						e.printStackTrace();
					}
				}
				break;
			}
		}
		listReglas.remove(wrap);
		return null;
	}


	public String agregarRegla() {
		listReglas.add(new WrapperRegla(new ConceptoDetalleRegla()));
		return null;
	}


	public String eliminarTarget() {
		int idElim = Integer.valueOf(Session.getRequestParameter("idTargetElim").toString()).intValue();
		WrapperTarget wrap = null;
		Iterator iter = listTarget.iterator();
		while (iter.hasNext()) {
			wrap = (WrapperTarget) iter.next();
			if (wrap.getIndice() == idElim) {
				if (wrap.getConceptoDetalleTarget().getIdConceptostarget() != null) {
					try {
						transaccionesService.getConceptoDetalleTargetService().borrarConceptoDetalleTarget(wrap.getConceptoDetalleTarget());
						conceptodetalle.getConceptoDetalleTargetSet().remove(wrap.getConceptoDetalleTarget());
					} catch (ConceptoDetalleTargetException e) {
						log.info("problemas al eliminar el target del detalle");
						e.printStackTrace();
					}
				}
				break;
			}
		}
		listTarget.remove(wrap);
		return null;
	}


	public String agregarTarget() {
		listTarget.add(new WrapperTarget(new ConceptoDetalleTarget()));
		return null;
	}

	public class WrapperRegla {

		private ConceptoDetalleRegla conceptoDetalleRegla;
		private int indice;


		public WrapperRegla(ConceptoDetalleRegla conceptoDetalleRegla) {
			this.conceptoDetalleRegla = conceptoDetalleRegla;
			this.indice = contadorRegla++;
		}


		public ConceptoDetalleRegla getConceptoDetalleRegla() {
			return conceptoDetalleRegla;
		}


		public void setConceptoDetalleRegla(ConceptoDetalleRegla conceptoDetalleRegla) {
			this.conceptoDetalleRegla = conceptoDetalleRegla;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}

	}

	public class WrapperTarget {

		private ConceptoDetalleTarget conceptoDetalleTarget;
		private int indice;


		public WrapperTarget(ConceptoDetalleTarget conceptoDetalleTarget) {
			this.conceptoDetalleTarget = conceptoDetalleTarget;
			this.indice = contadorTarget++;
		}


		public ConceptoDetalleTarget getConceptoDetalleTarget() {
			return conceptoDetalleTarget;
		}


		public void setConceptoDetalleTarget(ConceptoDetalleTarget conceptoDetalleTarget) {
			this.conceptoDetalleTarget = conceptoDetalleTarget;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}

	}


	public List getListReglas() {
		return listReglas;
	}


	public void setListReglas(List listReglas) {
		this.listReglas = listReglas;
	}


	public List getListTarget() {
		return listTarget;
	}


	public void setListTarget(List listTarget) {
		this.listTarget = listTarget;
	}


	public HtmlSelectOneMenu getMenuParent() {
		return menuParent;
	}


	public void setMenuParent(HtmlSelectOneMenu menuParent) {
		this.menuParent = menuParent;
	}


	public HtmlSelectOneMenu getMenuPrioridad() {
		return menuPrioridad;
	}


	public void setMenuPrioridad(HtmlSelectOneMenu menuPrioridad) {
		this.menuPrioridad = menuPrioridad;
	}


	public HtmlSelectOneMenu getMenuSigno() {
		return menuSigno;
	}


	public void setMenuSigno(HtmlSelectOneMenu menuSigno) {
		this.menuSigno = menuSigno;
	}


	public HtmlSelectOneMenu getMenuTipo() {
		return menuTipo;
	}


	public void setMenuTipo(HtmlSelectOneMenu menuTipo) {
		this.menuTipo = menuTipo;
	}


	public List getListaParent() {
		return listaParent;
	}


	public void setListaParent(List listaParent) {
		this.listaParent = listaParent;
	}


	public List getListaPrioridad() {
		return listaPrioridad;
	}


	public void setListaPrioridad(List listaPrioridad) {
		this.listaPrioridad = listaPrioridad;
	}


	public List getListaSigno() {
		return listaSigno;
	}


	public void setListaSigno(List listaSigno) {
		this.listaSigno = listaSigno;
	}


	public List getListaTipo() {
		return listaTipo;
	}


	public void setListaTipo(List listaTipo) {
		this.listaTipo = listaTipo;
	}


	public Long getParentElegido() {
		return parentElegido;
	}


	public void setParentElegido(Long parentElegido) {
		this.parentElegido = parentElegido;
	}


	public Long getPrioridadElegida() {
		return prioridadElegida;
	}


	public void setPrioridadElegida(Long prioridadElegida) {
		this.prioridadElegida = prioridadElegida;
	}


	public Integer getSignoElegido() {
		return signoElegido;
	}


	public void setSignoElegido(Integer signoElegido) {
		this.signoElegido = signoElegido;
	}


	public Long getTipoElegido() {
		return tipoElegido;
	}


	public void setTipoElegido(Long tipoElegido) {
		this.tipoElegido = tipoElegido;
	}


	public boolean isNoIncluyeFechaCierre() {
		return noIncluyeFechaCierre;
	}


	public void setNoIncluyeFechaCierre(boolean noIncluyeFechaCierre) {
		this.noIncluyeFechaCierre = noIncluyeFechaCierre;
	}


	public HtmlSelectBooleanCheckbox getIncluyeFechaCierre() {
		return incluyeFechaCierre;
	}


	public void setIncluyeFechaCierre(HtmlSelectBooleanCheckbox incluyeFechaCierre) {
		this.incluyeFechaCierre = incluyeFechaCierre;
	}


	public Long getCuentaDebe() {
		return cuentaDebe;
	}


	public void setCuentaDebe(Long cuentaDebe) {
		this.cuentaDebe = cuentaDebe;
	}


	public Long getCuentaHaber() {
		return cuentaHaber;
	}


	public void setCuentaHaber(Long cuentaHaber) {
		this.cuentaHaber = cuentaHaber;
	}


	public String getDebe() {
		return debe;
	}


	public void setDebe(String debe) {
		this.debe = debe;
		if (ctacontabledebe != null) {
			this.debe = ctacontabledebe;
			ctacontabledebe = null;
		}
	}


	public String getHaber() {
		return haber;
	}


	public void setHaber(String haber) {
		this.haber = haber;
		log.info("aca seteamos el habver");
		if (ctacontablehaber != null) {
			log.info("vamos a poner " + ctacontablehaber);
			this.haber = ctacontablehaber;
			ctacontablehaber = null;
		}
	}


	public String getCtacontabledebe() {
		return ctacontabledebe;
	}


	public void setCtacontabledebe(String ctacontabledebe) {
		this.ctacontabledebe = ctacontabledebe;
	}


	public String getCtacontablehaber() {
		return ctacontablehaber;
	}


	public void setCtacontablehaber(String ctacontablehaber) {
		this.ctacontablehaber = ctacontablehaber;
	}


	public boolean isVerComoImpacta() {
		if (menuParent.getValue() != null)
			return true;
		return false;
	}


	public void setVerComoImpacta(boolean verComoImpacta) {
		this.verComoImpacta = verComoImpacta;
	}


	public boolean isEsAltaConcepto() {
		return esAltaConcepto;
	}


	public void setEsAltaConcepto(boolean esAltaConcepto) {
		this.esAltaConcepto = esAltaConcepto;
	}

}
