package com.bizitglobal.webapp.faces.beans.workflow;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.exeption.OperadorException;
import com.bizitglobal.tarjetafiel.operador.exeption.RolException;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.operador.negocio.Rol;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.workflow.exception.AtributoProcesoTareaException;
import com.bizitglobal.workflow.exception.IniParametroException;
import com.bizitglobal.workflow.exception.ParametroFormException;
import com.bizitglobal.workflow.exception.ProcesoAtributoException;
import com.bizitglobal.workflow.exception.ProcesoDuplicateException;
import com.bizitglobal.workflow.exception.ProcesoException;
import com.bizitglobal.workflow.exception.TareaProcesoException;
import com.bizitglobal.workflow.negocio.AtributoProcesoTarea;
import com.bizitglobal.workflow.negocio.IniParametro;
import com.bizitglobal.workflow.negocio.ParametroForm;
import com.bizitglobal.workflow.negocio.Proceso;
import com.bizitglobal.workflow.negocio.ProcesoAtributo;
import com.bizitglobal.workflow.negocio.Tarea;
import com.bizitglobal.workflow.negocio.TareaProceso;
import com.bizitglobal.workflow.negocio.TareaProcesoRol;
import com.bizitglobal.workflow.service.ProcesoService;


@SuppressWarnings({"rawtypes","unchecked"})
public class ProcesoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ProcesoBean.class);
	private Proceso proceso;
	private Proceso procesoListado;
	private TareaProceso tareaProceso;
	private Tarea tarea;
	private IniParametro parametro;
	private String nombreFiltro;
	private Long idProcesoHidden;

	private String nombreFormExt;
	private String nombreDocCont;
	private String nombreJob;
	private boolean usarCU;

	// Listas para la presentacion(HtmlSelectItems).
	private List operadorList;
	private List operadores;
	private List rolList;
	private List roles = new ArrayList();
	private List areas;
	private List tiposCU;

	// Lista de tareas para el proceso.
	private List tareas;
	private List tablaTareas;

	//
	private List atributos;
	private List atributosSeleccionable;
	private List atributosSelecOne;
	private List parametros;
	private List procesos;

	// Indica si nos encontramos en una alta o modificacion.
	private boolean alta;
	private boolean nuevaVersion;
	private boolean edicion;

	private boolean altaTarea;
	private boolean tareaAutomatica;

	HtmlSelectOneMenu rolesHtml = new HtmlSelectOneMenu();
	HtmlSelectOneMenu tareasHtml = new HtmlSelectOneMenu();
	HtmlSelectOneMenu rolTareaHtml = new HtmlSelectOneMenu();

	private List rolesTarea;
	private List rolSel;
	private List paramForm;
	private List paramDoc;


	public ProcesoBean() {
		super();
		borrar();
		try {
			rolList = workflowService.getRolService().getRoles();
			rolList.remove(new Rol(new Long(4)));
		} catch (RolException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		areas = workflowService.getProcesoService().listaItemsAreas();
		tituloLargo = "Tarjeta Fiel - Administración de tramites";
		tituloCorto = "Alta de procesos";

	}


	public HtmlSelectOneMenu getRolTareaHtml() {
		return rolTareaHtml;
	}


	public void setRolTareaHtml(HtmlSelectOneMenu rolTareaHtml) {
		this.rolTareaHtml = rolTareaHtml;
	}


	public Tarea getTarea() {
		return tarea;
	}


	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}


	public boolean isTareaAutomatica() {
		return tareaAutomatica;
	}


	public void setTareaAutomatica(boolean tareaAutomatica) {
		this.tareaAutomatica = tareaAutomatica;
	}


	public String getNombreDocCont() {
		return nombreDocCont;
	}


	public void setNombreDocCont(String nombreDocCont) {
		this.nombreDocCont = nombreDocCont;
	}


	public String getNombreFormExt() {
		return nombreFormExt;
	}


	public void setNombreFormExt(String nombreFormExt) {
		this.nombreFormExt = nombreFormExt;
	}


	public String getNombreJob() {
		return nombreJob;
	}


	public void setNombreJob(String nombreJob) {
		this.nombreJob = nombreJob;
	}


	public List getRolesTarea() {
		return rolesTarea;
	}


	public void setRolesTarea(List rolesTarea) {
		this.rolesTarea = rolesTarea;
	}


	public List getParamDoc() {
		return paramDoc;
	}


	public void setParamDoc(List paramDoc) {
		this.paramDoc = paramDoc;
	}


	public List getParamForm() {
		return paramForm;
	}


	public void setParamForm(List paramForm) {
		this.paramForm = paramForm;
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public boolean getEdicion() {
		return edicion;
	}


	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}


	public Proceso getProceso() {
		return proceso;
	}


	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}


	public Long getIdProcesoHidden() {
		return idProcesoHidden;
	}


	public void setIdProcesoHidden(Long idProcesoHidden) {
		this.idProcesoHidden = idProcesoHidden;
	}


	public List getOperadores() {
		return operadores;
	}


	public void setOperadores(List operadores) {
		this.operadores = operadores;
	}


	public List getRoles() {
		return roles;
	}


	public void setRoles(List roles) {
		this.roles = roles;
	}


	public List getTiposCU() {
		return tiposCU;
	}


	public void setTiposCu(List tiposCU) {
		this.tiposCU = tiposCU;
	}


	public String getTipoCUSeleccionado()
	{
		return proceso.getTipoClave() == null ? "" : proceso.getTipoClave();
	}


	public void setTipoCUSeleccionado(String tipoCUSeleccionado)
	{
		proceso.setTipoClave(tipoCUSeleccionado.compareTo("") == 0 ? null : tipoCUSeleccionado);
	}


	public HtmlSelectOneMenu getRolesHtml() {
		return rolesHtml;
	}


	public void setRolesHtml(HtmlSelectOneMenu rolesHtml) {
		this.rolesHtml = rolesHtml;
	}


	public HtmlSelectOneMenu getTareasHtml() {
		return tareasHtml;
	}


	public void setTareasHtml(HtmlSelectOneMenu tareasHtml) {
		this.tareasHtml = tareasHtml;
	}


	public String getTituloCorto() {
		return tituloCorto;
	}


	public void setTituloCorto(String tituloCorto) {
		this.tituloCorto = tituloCorto;
	}


	public String getTituloLargo() {
		return tituloLargo;
	}


	public void setTituloLargo(String tituloLargo) {
		this.tituloLargo = tituloLargo;
	}


	public List getTareas() {
		return tareas;
	}


	public void setTareas(List tareas) {
		this.tareas = tareas;
	}


	public List getAreas() {
		return areas;
	}


	public void setAreas(List areas) {
		this.areas = areas;
	}


	public List getTareaProcesos() {
		return proceso.getTareaProcesos();
	}


	public void setTareaProcesos(List tareaProcesos) {
		proceso.setTareaProcesos(tareaProcesos);
	}


	public Long getRolTareaSeleccionado() {
		return tareaProceso.getIdRol();
	}


	public void setRolTareaSeleccionado(Long rolSeleccionado) {
		tareaProceso.setIdRol(rolSeleccionado);
	}


	public Long getRolSeleccionado() {
		return proceso.getRol().getIdRol();
	}


	public void setRolSeleccionado(Long rolSeleccionado) {
		proceso.getRol().setIdRol(rolSeleccionado);
	}


	public Long getOperadorSeleccionado() {
		return proceso.getSupervisorDef().getCodigo();
	}


	public void setOperadorSeleccionado(Long operadorSeleccionado) {
		proceso.getSupervisorDef().setCodigo(operadorSeleccionado);
	}


	public Long getTareaSeleccionada() {
		return tareaProceso.getIdTarea();
	}


	public void setTareaSeleccionada(Long tareaSeleccionada) {
		tareaProceso.setIdTarea(tareaSeleccionada);
	}


	public Long getAreaSeleccionada() {
		return proceso.getIdMenu();
	}


	public void setAreaSeleccionada(Long areaSeleccionada) {
		proceso.setIdMenu(areaSeleccionada);
	}


	public TareaProceso getTareaProceso() {
		return tareaProceso;
	}


	public void setTareaProceso(TareaProceso tareaProceso) {
		this.tareaProceso = tareaProceso;
	}


	public boolean getTareaInicial() {
		return (tareaProceso.getOrden().equals(new Integer(1))) ? true : false;
	}


	public void setTareaInicial(boolean tInicial) {
		if (tInicial) {
			tareaProceso.setOrden(new Integer(1));
		}
	}


	public boolean getTareaFinal() {
		return (tareaProceso.getOrden().equals(new Integer(2))) ? true : false;
	}


	public void setTareaFinal(boolean tFinal) {
		if (tFinal) {
			tareaProceso.setOrden(new Integer(2));
		}
	}


	public String getNombreFiltro() {
		return nombreFiltro;
	}


	public void setNombreFiltro(String nombreFiltro) {
		this.nombreFiltro = nombreFiltro;
	}


	public List getProcesos() {
		return procesos;
	}


	public void setProcesos(List procesos) {
		this.procesos = procesos;
	}


	public List getTablaTareas() {
		// tablaTareas = ProcesoUtil.cargarTablaTareas(
		// tablaTareas, proceso.getTareaProcesos(), workflowService.getTareaService());
		return tablaTareas;
	}


	public void setTablaTareas(List tablaTareas) {
		this.tablaTareas = tablaTareas;
	}


	public IniParametro getParametro() {
		return parametro;
	}


	public void setParametro(IniParametro parametro) {
		this.parametro = parametro;
	}


	public Long getIdAtrivutoSelec() {
		return parametro.getIdProcAtributo();
	}


	public void setIdAtrivutoSelec(Long idAtrivutoSelec) {
		parametro.setIdProcAtributo(idAtrivutoSelec);
	}


	public List getRolSel() {
		return rolSel;
	}


	public void setRolSel(List rolSel) {
		this.rolSel = rolSel;
	}


	public boolean getUsarCU()
	{
		return proceso.getRequiereCU() != null && proceso.getRequiereCU().compareTo("S") == 0 ? true : false;
	}


	public void setUsarCU(boolean usarCU)
	{
		proceso.setRequiereCU(usarCU ? "S" : "N");
		this.usarCU = usarCU;
	}


	public List getAtributosSelecOne() {
		atributosSelecOne = new ArrayList();
		if (!proceso.getAtributos().isEmpty()) {
			Iterator iter = proceso.getAtributos().iterator();
			while (iter.hasNext()) {
				ProcesoAtributo atri = (ProcesoAtributo) iter.next();
				SelectItem item = new SelectItem();
				item.setValue(atri.getIdProcesoAtributo());
				item.setLabel(atri.getDescripcion());
				atributos.add(atri);
			}
		}
		return atributosSelecOne;
	}


	public void setAtributosSelecOne(List atributosSelecOne) {
		this.atributosSelecOne = atributosSelecOne;
	}


	public List getAtributosSeleccionable() {
		List auxList = new ArrayList(atributosSeleccionable);
		if (atributos.isEmpty()) {
			atributosSeleccionable = atributos;
		} else {
			if (auxList.size() != atributos.size()) {
				atributosSeleccionable = atributos;
				Iterator iterAS = auxList.iterator();
				while (iterAS.hasNext()) {
					AtributoSelec atSelec = (AtributoSelec) iterAS.next();
					if (atributosSeleccionable.contains(atSelec)) {
						int i = atributosSeleccionable.indexOf(atSelec);
						atributosSeleccionable.set(i, atSelec);
					}
				}
			}
		}
		// atributosSeleccionable = new ArrayList();
		// if (!proceso.getAtributos().isEmpty()) {
		// Iterator iter = proceso.getAtributos().iterator();
		// while (iter.hasNext()) {
		// ProcesoAtributo atri = (ProcesoAtributo) iter.next();
		// AtributoSelec atributoSelec = new AtributoSelec(atri);
		// atributosSeleccionable.add(atributoSelec);
		// }
		// }
		return atributosSeleccionable;
	}


	public void setAtributosSeleccionable(List atributosSeleccionable) {
		this.atributosSeleccionable = atributosSeleccionable;
	}


	public List getAtributos() {
		atributos = new ArrayList();
		if (!proceso.getAtributos().isEmpty()) {
			Iterator iter = proceso.getAtributos().iterator();
			while (iter.hasNext()) {
				ProcesoAtributo atri = (ProcesoAtributo) iter.next();
				if (atri.getVisible()) {
					AtributoSelec atributoSelec = new AtributoSelec(atri);
					atributos.add(atributoSelec);
				}
			}
		}
		return atributos;
	}


	public void setAtributos(List atributos) {
		this.atributos = atributos;
	}


	public List getParametros() {
		parametros = new ArrayList();
		if (!proceso.getParametros().isEmpty()) {
			Iterator iter = proceso.getParametros().iterator();
			while (iter.hasNext()) {
				IniParametro iParam = (IniParametro) iter.next();
				ParametroSelec parametroSelec = new ParametroSelec(iParam);
				parametros.add(parametroSelec);
			}
		}
		return parametros;
	}


	public void setParametros(List parametros) {
		this.parametros = parametros;
	}


	// METODOS PRINCIPALES DEL BEAN
	public void borrar() {
		error.borrar();
		// Cargar al objeto proceso los objetos relacionados.
		proceso = new Proceso();
		proceso.setOperador(Session.getOperador());
		proceso.setSupervisorDef(new Operador(new Long(0)));
		proceso.setRol(new Rol(new Long(0)));
		proceso.setVersion(new Integer(1));
		proceso.setRevision(new Integer(0));
		proceso.setTareaProcesos(new ArrayList());
		proceso.setAtributos(new ArrayList());
		proceso.setParametros(new ArrayList());

		tablaTareas = new ArrayList();
		atributos = new ArrayList();
		atributosSeleccionable = new ArrayList();
		operadores = new ArrayList();
		tiposCU = new ArrayList();
		operadorList = new ArrayList();
		paramDoc = new ArrayList();
		paramForm = new ArrayList();
		rolesTarea = new ArrayList();
		rolSel = new ArrayList();

		alta = true;
		nuevaVersion = false;
		edicion = false;
		altaTarea = true;
		tareaAutomatica = false;

		tareaProceso = new TareaProceso();
		tareaProceso.setIdRol(new Long(0));
		tareaProceso.setIdTarea(new Long(0));
		tareaProceso.setIdProceso(new Long(0));

		tarea = null;
		nombreFormExt = null;
		nombreDocCont = null;
	}


	public String inicializar() {
		borrar();
		popup.borrar();
		tituloCorto = "Alta de procesos";
		roles.clear();
		// roles.add(new SelectItem(new Long(0),"Seleccione"));
		roles.addAll(Util.cargarSelectItem(rolList));
		if (!roles.isEmpty()) {
			try {
				SelectItem item = (SelectItem) roles.get(0);
				operadorList = operadorService.getOperadorService().getOperadores(new Filtro("rol.idRol", Filtro.IGUAL, item.getValue()));
				operadores = ProcesoUtil.cargarListaOperadores(operadorList);
			} catch (OperadorException e) {
				e.printStackTrace();
			}
			Iterator iter = rolList.iterator();
			while (iter.hasNext()) {
				Rol rol = (Rol) iter.next();
				rolSel.add(new RolSelect(rol));
			}
		} else {
			operadorList = new ArrayList();
			operadores = ProcesoUtil.cargarListaOperadores(operadorList);
		}
		tareas = ProcesoUtil.cargarListaTareas(workflowService.getTareaService());
		AtributoBean atributoBean = (AtributoBean) Session.getBean("AtributoBean");
		atributoBean.inicializar();
		tiposCU = new ArrayList();
		tiposCU.add(new SelectItem("", "Otros"));
		tiposCU.add(new SelectItem("T", "Titular"));
		tiposCU.add(new SelectItem("A", "Adicional"));
		tiposCU.add(new SelectItem("G", "Garante"));
		tiposCU.add(new SelectItem("C", "Comercio"));
		return "amProcesos";
	}


	public boolean validar() {
		error.borrar();

		if (Validador.esNulo(proceso.getTitulo()) || proceso.getTitulo().equals("")) {
			error.agregar(Error.PROCESO_TITULO_REQUERIDO);
		}

		if (proceso.getRol().getIdRol().equals(new Long(0))) {
			error.agregar(Error.PROCESO_ROL_REQUERIDO);
		}

		if (proceso.getSupervisorDef().getCodigo() == null || proceso.getSupervisorDef().getCodigo().equals(new Long(0))) {
			error.agregar(Error.PROCESO_SUPER_DEF_REQUERIDO);
		}

		if (proceso.getIdMenu() == null || proceso.getIdMenu().equals(new Integer(0))) {
			error.agregar(Error.PROCESO_ID_MENU_REQUERIDO);
		}

		// if(proceso.getRevision() == null) {
		// error.agregar(Error.PROCESO_REVISION_REQUERIDO);
		// }

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoProceso() {
		log.info("irANuevoProceso()");
		return inicializar();
	}


	public String irAModificarProceso() {
		log.info("irAModificarProceso()");
		alta = false;
		popup.borrar();
		if (Session.getBean("ScrollBean") != null) {
			((ScrollBean) Session.getBean("ScrollBean")).setHiddenScrollY(new Integer(0));
		}
		return null;
	}


	public String irAListarProceso() {
		log.info("irAListarProceso()");
		return inicializarListarProcesos();
	}


	// METODOS PARA EL POPUP DE ATRIBUTOS

	public String insertarAtributo() {
		AtributoBean atributoBean = (AtributoBean) Session.getBean("AtributoBean");
		atributoBean.borrar();
		atributoBean.setProceso(proceso);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/workflow/popups/atributosPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,600), 'titlebar=no';");
		return null;
	}


	public String editarAtributo() {
		Long idProcesoAtributo = new Long(Session.getRequestParameter("idProcesoAtributo"));
		ProcesoAtributo atributo = new ProcesoAtributo();
		try {
			atributo = workflowService.getProcesoAtributoService().leerProcesoAtributo(idProcesoAtributo);
		} catch (ProcesoAtributoException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		AtributoBean atributoBean = (AtributoBean) Session.getBean("AtributoBean");
		log.info("Voy a editar el atributo:" + atributo);
		atributoBean.editar(atributo);

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/workflow/popups/atributosPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,600), 'titlebar=no';");
		return null;
	}


	public String eliminarAtributo() {
		Long idProcesoAtributo = new Long(Session.getRequestParameter("idProcesoAtributo"));
		log.info("Borrando el atributo:" + idProcesoAtributo);
		try {
			workflowService.getProcesoAtributoService().borrarProcesoAtributo(idProcesoAtributo);
			proceso.getAtributos().remove(new ProcesoAtributo(idProcesoAtributo));
		} catch (ProcesoAtributoException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	// METODOS PARA EL POPUP DE ANEXAR ATRIBUTROS A LA TAREA
	public String agregarAtributosATarea() {
		Long idTareaProceso = new Long(Session.getRequestParameter("idTareaProceso"));
		log.info("ID de la tarea proceso seleccionada: " + idTareaProceso);
		TareaTabla tareaTabla = (TareaTabla) Util.buscarElemento(tablaTareas, new TareaTabla(idTareaProceso));
		tareaProceso = tareaTabla.getTareaProceso();

		setNombresForm(tareaTabla.getTarea().getIdTarea());
		// Chequeo los atributos previamente asociados
		List atributosDeTarea = new ArrayList();
		try {
			atributosDeTarea = workflowService.getAtributoProcesoTareaService().listarAtributoProcesoTarea(
					new Filtro(AtributoProcesoTarea.ID_TAREA_PROCESO, Filtro.IGUAL, idTareaProceso));
		} catch (AtributoProcesoTareaException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Cargo la lista de atributos a seleccionar
		atributosSeleccionable = new ArrayList();
		if (!proceso.getAtributos().isEmpty()) {
			Iterator iter = proceso.getAtributos().iterator();
			while (iter.hasNext()) {
				ProcesoAtributo atri = (ProcesoAtributo) iter.next();
				AtributoSelec atributoSelec = new AtributoSelec(atri);
				atributoSelec.setIdTareaProceso(idTareaProceso);
				// Marco los asociados previamente
				if (!atributosDeTarea.isEmpty()) {
					Iterator iterAtributos = atributosDeTarea.iterator();
					while (iterAtributos.hasNext()) {
						AtributoProcesoTarea atribProcTar = (AtributoProcesoTarea) iterAtributos.next();
						if (atributoSelec.getProcesoAtributo().getIdProcesoAtributo().equals(atribProcTar.getIdProcesoAtributo())) {
							atributoSelec.setSeleccionado(true);
							atributoSelec.setImpacta(atribProcTar.getImpactaCliente());
							atributoSelec.setSoloLectura(atribProcTar.getSoloLectura());
							atributoSelec.setVisible(atribProcTar.getVisible());
							atributoSelec.setResetea(atribProcTar.getResetea());
							atributoSelec.setValor(atribProcTar.getValor());
							atributoSelec.setReseteaFinal(atribProcTar.getReseteaFinal());
							atributoSelec.setParamForm(buscarParamForm(atribProcTar.getIdAtribTarPro(), paramForm));
							if (atributoSelec.getParamForm().getIdParametroForm() != null) {
								atributoSelec.setUsaParamForm(true);
							}
							atributoSelec.setParamDoc(buscarParamForm(atribProcTar.getIdAtribTarPro(), paramDoc));
							if (atributoSelec.getParamDoc().getIdParametroForm() != null) {
								atributoSelec.setUsaParamDoc(true);
							}
						}
					}
				}
				atributosSeleccionable.add(atributoSelec);
			}
		}

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/workflow/popups/anexarAtributosTareasPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',1000,450);");
		return null;
	}


	private ParametroForm buscarParamForm(Long idAtriTarePro, List paramList) {
		Iterator iterParam = paramList.iterator();
		// Armar los objetos y controlarlos cuando los grabe
		// siempre devover algo construido
		while (iterParam.hasNext()) {
			ParametroForm element = (ParametroForm) iterParam.next();
			if (element.getIdAtributoTareaPro().equals(idAtriTarePro)) {
				return element;
			}
		}
		return new ParametroForm();
	}


	public String insertarAtributosATarea() {
		if (!atributosSeleccionable.isEmpty()) {
			try {
				// Borro los parametros asociados anteriormente
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor(ParametroForm.TAREA_PROCESO_ID, Filtro.IGUAL, tareaProceso.getIdTareaProceso());
				workflowService.getParametroFormService().borrarTodos(filtro);
				filtro.reset();
				filtro.agregarCampoOperValor(AtributoProcesoTarea.ID_TAREA_PROCESO, Filtro.IGUAL, tareaProceso.getIdTareaProceso());
				workflowService.getAtributoProcesoTareaService().borrarTodos(filtro);
			} catch (AtributoProcesoTareaException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Itero la lista de atributos buscando los seleccionados
			Iterator iterAtriSelec = atributosSeleccionable.iterator();
			while (iterAtriSelec.hasNext()) {
				AtributoSelec atributoSelec = (AtributoSelec) iterAtriSelec.next();
				if (atributoSelec.getSeleccionado()) {
					AtributoProcesoTarea atributoProcesoTarea = new AtributoProcesoTarea();
					atributoProcesoTarea.setIdTareaProceso(tareaProceso.getIdTareaProceso());
					atributoProcesoTarea.setIdProcesoAtributo(atributoSelec.getProcesoAtributo().getIdProcesoAtributo());
					atributoProcesoTarea.setImpactaCliente(atributoSelec.getImpacta());
					atributoProcesoTarea.setSoloLectura(atributoSelec.getSoloLectura());
					atributoProcesoTarea.setVisible(atributoSelec.getVisible());
					atributoProcesoTarea.setResetea(atributoSelec.getResetea());
					atributoProcesoTarea.setValor(atributoSelec.getValor());
					atributoProcesoTarea.setReseteaFinal(atributoSelec.getReseteaFinal());
					try {
						workflowService.getAtributoProcesoTareaService().grabarAtributoProcesoTarea(atributoProcesoTarea);

					} catch (AtributoProcesoTareaException e1) {
						e1.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					// Cargo los parametros a cada formulario asociado
					if (atributoSelec.getUsaParamForm()) {
						ParametroForm parametroForm = atributoSelec.getParamForm();
						parametroForm.setIdAtributoTareaPro(atributoProcesoTarea.getIdAtribTarPro());
						parametroForm.setTareaProcesoId(tareaProceso.getIdTareaProceso());
						parametroForm.setIdFormExterno(tarea.getFormExterno().getIdFormExterno());
						try {
							workflowService.getParametroFormService().grabarParametroForm(parametroForm);
						} catch (ParametroFormException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (atributoSelec.getUsaParamDoc()) {
						ParametroForm parametroForm = atributoSelec.getParamDoc();
						parametroForm.setIdAtributoTareaPro(atributoProcesoTarea.getIdAtribTarPro());
						parametroForm.setTareaProcesoId(tareaProceso.getIdTareaProceso());
						parametroForm.setIdFormExterno(tarea.getDocContractual().getIdFormExterno());
						try {
							workflowService.getParametroFormService().grabarParametroForm(parametroForm);
						} catch (ParametroFormException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.opener.recargar();window.close();";
		AddResource addResource = AddResourceFactory.getInstance(facesContext);
		addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		return null;
	}


	// METODOS PARA EL POPUP DE ANEXAR TAREAS

	public void cambioTarea(ValueChangeEvent event) {
		Long valorSeleccionado = new Long(tareasHtml.getValue().toString());
		setNombresForm(valorSeleccionado);
	}


	private void setNombresForm(Long valorSeleccionado) {
		tarea = null;
		nombreFormExt = null;
		nombreDocCont = null;
		nombreJob = null;
		if (valorSeleccionado.intValue() != 0) {
			try {
				tarea = workflowService.getTareaService().leerTarea(valorSeleccionado);
				paramDoc.clear();
				paramForm.clear();
				if (tarea.getTipoTarea().getIdTipoTarea().equals(new Long(2))) {
					try {
						Rol rol = operadorService.getRolService().leerRol(new Long(4));
						nombreJob = tarea.getJobExterno();
						roles.clear();
						roles.add(new SelectItem(rol.getId(), rol.getLabel()));
						rolTareaHtml.setValue(rol.getId());
						tareaAutomatica = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					if (!tarea.getFormExterno().getIdFormExterno().equals(new Long(0))) {
						tarea.setFormExterno(workflowService.getFormExternoService().leerFormExterno(
								tarea.getFormExterno().getIdFormExterno()));
						nombreFormExt = tarea.getFormExterno().getLabel();
						paramForm = workflowService.getParametroFormService().listarParametroForm(
								new Filtro(ParametroForm.ID_FORM_EXTERNO, Filtro.IGUAL, tarea.getFormExterno().getIdFormExterno()));
					} else {
						nombreFormExt = null;
					}
					if (!tarea.getDocContractual().getIdFormExterno().equals(new Long(0))) {
						tarea.setDocContractual(workflowService.getFormExternoService().leerFormExterno(
								tarea.getDocContractual().getIdFormExterno()));
						nombreDocCont = tarea.getDocContractual().getLabel();
						paramDoc = workflowService.getParametroFormService().listarParametroForm(
								new Filtro(ParametroForm.ID_FORM_EXTERNO, Filtro.IGUAL, tarea.getDocContractual().getIdFormExterno()));
					} else {
						nombreDocCont = null;
					}
					tareaAutomatica = false;
					// roles.clear();
					// roles.add(new SelectItem(new Long(0),"Seleccione"));
					// roles.addAll(Util.cargarSelectItem(rolList));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public String irAInsertarTarea() {
		tareaProceso = new TareaProceso();
		altaTarea = true;
		nombreDocCont = null;
		nombreFormExt = null;
		nombreJob = null;
		tareaAutomatica = false;
		// roles.clear();
		// roles.add(new SelectItem(new Long(0),"Seleccione"));
		// roles.addAll(Util.cargarSelectItem(rolList));
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/workflow/popups/anexarTareasPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}


	public String insertarTarea() {
		log.info("Insertando Tarea");
		boolean valido = true;

		if (tareaProceso.getIdTarea().equals(new Long(0))) {
			valido = false;
		}

		if (!tareaAutomatica && rolesTarea.isEmpty()) {
			valido = false;
		}

		try {
			if (valido) {
				TareaProceso tProceso = null;
				if (altaTarea) {
					tProceso = new TareaProceso(tareaProceso);
					tProceso.setIdTareaProceso(null);
					tProceso.setIdProceso(proceso.getIdProceso());
					if (tProceso.getOrden().equals(new Integer(1))) {
						workflowService.getTareaProcesoService().reiniciarOrden(proceso.getIdProceso(), new Integer(1), new Integer(0));
					}
					if (tareaAutomatica) {
						tProceso.setIdRol(new Long(4));
					} else {
						tProceso.setIdRol(new Long((String) rolesTarea.get(0)));
					}
					workflowService.getTareaProcesoService().grabarTareaProceso(tProceso);
					TareaTabla tareaTabla = new TareaTabla();
					tareaTabla.setTarea(workflowService.getTareaService().leerTarea(tProceso.getIdTarea()));
					tareaTabla.setTareaProceso(tProceso);
					tablaTareas.add(tareaTabla);
					proceso.getTareaProcesos().add(tProceso);
				} else {
					tProceso = new TareaProceso(tareaProceso);
					if (tareaAutomatica) {
						tProceso.setIdRol(new Long(4));
					} else {
						tProceso.setIdRol(new Long((String) rolesTarea.get(0)));
					}
					if (tProceso.getOrden().equals(new Integer(1))) {
						workflowService.getTareaProcesoService().reiniciarOrden(proceso.getIdProceso(), new Integer(1), new Integer(0));
					}
					workflowService.getTareaProcesoService().actualizarTareaProceso(tProceso);
				}
				TareaProcesoRol tareaProcesoRol = null;
				// Tarea tarea = workflowService.getTareaService().leerTarea(tareaProceso.getIdTarea());
				if (tProceso != null) {
					// Borro los roles asociados anteriormente
					Filtro filtro = new Filtro(TareaProcesoRol.ID_TAREA_PROCESO, Filtro.IGUAL, tProceso.getIdTareaProceso());
					workflowService.getTareaProcesoRolService().borrarTodos(filtro);
					if (tareaAutomatica) {
						tareaProcesoRol = new TareaProcesoRol();
						tareaProcesoRol.setIdRol(new Long((4)));
						tareaProcesoRol.setIdTareaProceso(tProceso.getIdTareaProceso());
						workflowService.getTareaProcesoRolService().grabarTareaProcesoRol(tareaProcesoRol);
					} else {
						// Cargo los roles asociado a la tarea
						Iterator iterRol = rolesTarea.iterator();
						while (iterRol.hasNext()) {
							tareaProcesoRol = new TareaProcesoRol();
							tareaProcesoRol.setIdRol(new Long((String) iterRol.next()));
							tareaProcesoRol.setIdTareaProceso(tProceso.getIdTareaProceso());
							workflowService.getTareaProcesoRolService().grabarTareaProcesoRol(tareaProcesoRol);
						}
					}
				}
				rolesTarea.clear();
				// paramDoc.clear();
				// paramForm.clear();
				nombreFormExt = "No Contiene";
				nombreDocCont = "No Contiene";
				tareaProceso = new TareaProceso();
				tareaAutomatica = false;
				FacesContext facesContext = FacesContext.getCurrentInstance();
				String javaScriptText = "window.opener.recargar();window.close();";
				AddResource addResource = AddResourceFactory.getInstance(facesContext);
				addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
			}

		} catch (TareaProcesoException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public String eliminarTarea() {
		Long idTareaTabla = new Long(Session.getRequestParameter("idTareaTabla"));
		try {
			workflowService.getTareaProcesoService().borrarTareaProceso(idTareaTabla);
			TareaProceso tProcesoAux = new TareaProceso(idTareaTabla);
			proceso.getTareaProcesos().remove(tProcesoAux);
			TareaTabla tareaTabla = new TareaTabla();
			tareaTabla.setIdTareaTabla(idTareaTabla);
			tablaTareas.remove(tareaTabla);
		} catch (TareaProcesoException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public String editarTarea() {
		// log.info("Editar la tarea .");
		tareaProceso = new TareaProceso();
		try {
			Long idTareaProceso = new Long(Session.getRequestParameter("idTareaProceso"));
			tareaProceso = workflowService.getTareaProcesoService().leerTareaProceso(idTareaProceso);
			setNombresForm(tareaProceso.getIdTarea());
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor(TareaProcesoRol.ID_TAREA_PROCESO, Filtro.IGUAL, idTareaProceso);
			rolesTarea = ProcesoUtil.cargarRolesTarea(workflowService.getTareaProcesoRolService().listarTareaProcesoRol(filtro));
			// filtro.agregarCampoOperValor(ParametroForm.ID_FORM_EXTERNO, Filtro.IGUAL, tarea.getFormExterno().getIdFormExterno());
			// filtro.agregarCampoOperValor(ParametroForm.PROCESO_ID, Filtro.IGUAL, proceso.getIdProceso());
			// paramForm = ProcesoUtil.cargarParametrosFormularios(workflowService.getParametroFormService().listarParametroForm(filtro));
			// filtro.reset();
			// filtro.agregarCampoOperValor(ParametroForm.ID_FORM_EXTERNO, Filtro.IGUAL, tarea.getDocContractual().getIdFormExterno());
			// filtro.agregarCampoOperValor(ParametroForm.PROCESO_ID, Filtro.IGUAL, proceso.getIdProceso());
			// paramDoc = ProcesoUtil.cargarParametrosFormularios(workflowService.getParametroFormService().listarParametroForm(filtro));
			altaTarea = false;
		} catch (TareaProcesoException e1) {
			e1.printStackTrace();
			altaTarea = true;
		} catch (Exception e) {
			e.printStackTrace();
			altaTarea = true;
		}

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/workflow/popups/anexarTareasPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',700,500), 'titlebar=no';");
		return null;
	}


	public String cancelarTarea() {
		tareaProceso = new TareaProceso();
		log.info("Insercion de la tarea cancelada.");
		return null;
	}


	public void selecRolTarea(ValueChangeEvent evt) {
		List valoresSelec = (ArrayList) evt.getNewValue();
		rolesTarea.clear();
		if (!valoresSelec.isEmpty()) {
			Iterator iterval = valoresSelec.iterator();
			while (iterval.hasNext()) {
				String idRol = (String) iterval.next();
				log.info("id de atributo a param Doc: " + idRol);
				rolesTarea.add(new SelectItem(idRol, ""));
			}

			// for (int i = 0; i < selectedValues.length; i++){
			// log.info("id de atributo a param Doc: "+selectedValues[i]);
			// paramDoc.add(new SelectItem(selectedValues[i],""));
			// }
		}
	}


	// METODO PARA LLAMAR LA ADMINISTRACION DE LOS FLUJOS DE UNA TAREA

	public String administrarFlujosDeTarea() {

		try {
			Long idTareaProceso = new Long(Session.getRequestParameter("idTareaProceso"));
			log.info("ID de la tarea proceso seleccionada para mandar al flujo: " + idTareaProceso);
			TareaProceso tProceso = workflowService.getTareaProcesoService().leerTareaProceso(idTareaProceso);
			log.info("tarea proceso encontrada: " + tProceso);
			FlujosBean flujosBean = (FlujosBean) Session.getBean("FlujosBean");
			return flujosBean.inicializar(tProceso);
		} catch (TareaProcesoException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	// METODOS PARA EL POPUP DE PARAMETROS

	public String insertarParametro() {
		boolean valido = true;

		if (parametro.getNombre().equals(null) || parametro.getNombre().compareTo(new String("")) != 0) {
			valido = false;
		}

		if (parametro.getIdProcAtributo().equals(new Long(0))) {
			valido = false;
		}

		try {
			if (valido) {
				IniParametro iniParam = new IniParametro(parametro);
				iniParam.setIdIniParametro(null);
				iniParam.setIdProceso(proceso.getIdProceso());
				workflowService.getIniParametroService().grabarIniParametro(iniParam);
				proceso.getParametros().add(iniParam);

				FacesContext facesContext = FacesContext.getCurrentInstance();
				String javaScriptText = "window.opener.recargar();window.close();";
				AddResource addResource = AddResourceFactory.getInstance(facesContext);
				addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
			}
		} catch (IniParametroException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	// -----------------------------
	public String inicializarListarProcesos() {
		borrar();
		nombreFiltro = "";
		tituloLargo = "ADMINISTRACION DE TRAMITES";
		tituloCorto = "Listado de Procesos";
		procesos = new ArrayList();
		Session.redirect("/workflow/procesos/listarProcesos.jsf");
		return null;
	}


	public void cambioRol(ValueChangeEvent event) {
		Long valorSeleccionado = new Long(rolesHtml.getValue().toString());
		if (valorSeleccionado.intValue() != 0) {
			try {
				operadorList = operadorService.getOperadorService().getOperadores(new Filtro("rol.idRol", Filtro.IGUAL, valorSeleccionado));
				operadores = ProcesoUtil.cargarListaOperadores(operadorList);
			} catch (OperadorException e) {
				e.printStackTrace();
			}
		} else {
			operadorList = new ArrayList();
			operadores = ProcesoUtil.cargarListaOperadores(operadorList);
		}
	}


	public String listarProcesos() {
		log.info("Ejecutando filtro de Procesos -->");
		List procList = new ArrayList();
		procesos = new ArrayList();
		try {
			procList = workflowService.getProcesoService().listarProceso(
					new Filtro(Proceso.TITULO, Filtro.LIKE, nombreFiltro));
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Procesos encontrados -->" + procList.size());

		if (!procList.isEmpty()) {
			Iterator iterpro = procList.iterator();
			try {
				operadorList = operadorService.getOperadorService().getOperadores();
			} catch (OperadorException e) {
				e.printStackTrace();
			}
			while (iterpro.hasNext()) {
				Proceso elementPro = (Proceso) iterpro.next();
				elementPro.setRol(ProcesoUtil.getRol(rolList, elementPro.getRol().getIdRol()));
				elementPro.setSupervisorDef(ProcesoUtil.getOperador(operadorList, elementPro.getSupervisorDef().getCodigo()));
				procesos.add(elementPro);
			}
		}

		Session.redirect("/workflow/procesos/listarProcesos.jsf");
		return null;
	}


	public String editarProceso() {
		inicializar();
		tituloLargo = "Tarjeta Fiel - Administracion de tramites";
		tituloCorto = "Modificación de proceso";
		alta = false;
		edicion = true;
		try { // primero cargo todos los elementos de el proceso
			proceso = workflowService.getProcesoService().leerProceso(idProcesoHidden);
			if (proceso.getDiscontinuado()) {
				error.agregar("La versión " + proceso.getVersion() + "." + proceso.getRevision() + " del proceso '" + proceso.getTitulo()
						+ "' está discontinuada.");
				Session.redirect("/workflow/procesos/listarProcesos.jsf");
				return "";
			}
			rolesHtml.setValue(proceso.getRol().getIdRol());
			operadorList = operadorService.getOperadorService().getOperadores(new Filtro("rol.idRol", Filtro.IGUAL, proceso.getRol().getIdRol()));
			operadores = ProcesoUtil.cargarListaOperadores(operadorList);

			Filtro filtroAtributo = new Filtro(ProcesoAtributo.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
			/* @I5547 */filtroAtributo.agregarOrderBy(ProcesoAtributo.ID_PROCESO_ATRIBUTO);
			/* @F5547 */proceso.setAtributos(workflowService.getProcesoAtributoService().listarProcesoAtributo(filtroAtributo));

			proceso.setTareaProcesos(workflowService.getTareaProcesoService().listarTareaProceso(
					new Filtro(TareaProceso.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso())));
			if (!proceso.getTareaProcesos().isEmpty()) {
				Iterator iterTP = proceso.getTareaProcesos().iterator();
				while (iterTP.hasNext()) {
					TareaProceso tProceso = (TareaProceso) iterTP.next();
					TareaTabla tareaTabla = new TareaTabla();
					tareaTabla.setTarea(workflowService.getTareaService().leerTarea(tProceso.getIdTarea()));
					tareaTabla.setTareaProceso(tProceso);
					tablaTareas.add(tareaTabla);
				}
			}

			Filtro filtroParam = new Filtro(IniParametro.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
			/* @I5547 */filtroParam.agregarOrderBy(IniParametro.ID_INI_PARAMETRO);
			/* @F5547 */proceso.setParametros(workflowService.getIniParametroService().listarIniParametro(filtroParam));
			// List tramitesList = workflowService.getTramiteService().listarTramite(
			// new Filtro(Tramite.ID_PRCESO, Filtro.IGUAL, proceso.getIdProceso()));
			// si tiene algun tramite ya inicido, creo otra version

			// si ya no esta en test, creo otra version
			if (!proceso.getEnTest()) {
				tituloCorto = "Nueva versión de proceso";
				proceso.setRevision(new Integer(proceso.getRevision().intValue() + 1));
				if (proceso.getRevision().equals(new Integer(11))) {
					proceso.setRevision(new Integer(0));
					proceso.setVersion(new Integer(proceso.getVersion().intValue() + 1));
				}
				alta = true;
				nuevaVersion = true;
			} else {
				proceso.setActivo(false);
				try {
					workflowService.getProcesoService().actualizarProceso(proceso);
				} catch (ProcesoException e1) {
					e1.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (Session.getBean("ScrollBean") != null) {
				ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
				bean.setHiddenScrollY(new Integer(0));
			}

		} catch (ProcesoException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "amProcesos";
	}


	public String cambiarEstado() {
		try {
			proceso = workflowService.getProcesoService().leerProceso(idProcesoHidden);
			List procList = workflowService.getProcesoService().listarProceso(
					new Filtro(Proceso.TITULO, Filtro.LIKEXS, proceso.getTitulo()));
			if (!procList.isEmpty()) {
				Iterator iterPro = procList.iterator();
				while (iterPro.hasNext()) {
					Proceso procAux = (Proceso) iterPro.next();
					if (procAux.equals(proceso)) {
						procAux.setActivo(!proceso.getActivo());
						workflowService.getProcesoService().actualizarProceso(procAux);
					} else {
						if (procAux.getActivo()) {
							procAux.setActivo(false);
							workflowService.getProcesoService().actualizarProceso(procAux);
						}
					}
				}
			}
		} catch (ProcesoException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listarProcesos();
	}


	public String cambiarTest() {
		try {
			proceso = workflowService.getProcesoService().leerProceso(idProcesoHidden);
			proceso.setEnTest(!proceso.getEnTest());
			workflowService.getProcesoService().actualizarProceso(proceso);
		} catch (ProcesoException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listarProcesos();
	}


	public String eliminarProceso() {
		return null;
	}


	public void grabarProcesoListener(ActionEvent event) {
		log.info("Grabando el proceso");

		try {
			armarProceso();
			if (validar()) {
				// Inicio los servis que voy a usar
				ProcesoService procesoService = workflowService.getProcesoService();

				if (alta) {
					if (nuevaVersion) {
						// construyo y grabo una nueva version
						procesoService.grabarProcesoNuevaVersionTran(proceso);
						idProcesoHidden = proceso.getIdProceso();
					} else {
						// Grabo el nuevo proceso
						procesoService.grabarProceso(proceso);
					}
				} else {
					procesoService.actualizarProceso(proceso);
				}
				log.info("Grabando el proceso ->" + proceso);
				popup.setPopup(popup.ICONO_OK, "El proceso ha sido almacenada exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}

		} catch (ProcesoDuplicateException e1) {
			log.info("No se pudo grabar el proceso");
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del proceso.");
			e1.printStackTrace();
		} catch (ProcesoException e2) {
			log.info("No se pudo grabar el proceso");
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del proceso.");
			e2.printStackTrace();
		} catch (Exception e3) {
			log.info("No se pudo grabar el proceso");
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del proceso.");
			e3.printStackTrace();
		}
		if (nuevaVersion) {
			editarProceso();
		}
	}


	public String cancelar() {
		Session.redirect("/workflow/procesos/listarProcesos.jsf");
		return "";
	}


	private void armarProceso() {
		// Cargo los atributos relacionados
		// proceso.setAtributos(atributos);
		proceso.setTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
	}


	// public void selecParamForm(ValueChangeEvent evt){
	// List valoresSelec = (ArrayList) evt.getNewValue();
	// paramForm = new ArrayList();
	// if (!valoresSelec.isEmpty()){
	// Iterator iterval = valoresSelec.iterator();
	// while (iterval.hasNext()) {
	// String idAtributo = (String) iterval.next();
	// log.info("id de atributo a param Form: "+idAtributo);
	// paramForm.add(new SelectItem(idAtributo,""));
	// }
	// // for (int i = 0; i < valoresSelec.length; i++){
	// // log.info("id de atributo a param Form: "+valoresSelec[i]);
	// // paramForm.add(new SelectItem(valoresSelec[i],""));
	// // }
	// }
	// }
	//
	public void selecParamDoc(ValueChangeEvent evt) {
		List valoresSelec = (ArrayList) evt.getNewValue();
		paramDoc = new ArrayList();
		if (!valoresSelec.isEmpty()) {
			Iterator iterval = valoresSelec.iterator();
			while (iterval.hasNext()) {
				String idAtributo = (String) iterval.next();
				log.info("id de atributo a param Doc: " + idAtributo);
				paramDoc.add(new SelectItem(idAtributo, ""));
			}

			// for (int i = 0; i < selectedValues.length; i++){
			// log.info("id de atributo a param Doc: "+selectedValues[i]);
			// paramDoc.add(new SelectItem(selectedValues[i],""));
			// }
		}
	}

	public class AtributoSelec {
		private ProcesoAtributo procesoAtributo;
		private TareaProceso tareaProceso;
		private ParametroForm paramForm;
		private ParametroForm paramDoc;
		private Long idTareaProceso;

		private boolean seleccionado;
		private boolean impacta;
		private boolean soloLectura;
		private boolean visible;
		private boolean resetea;
		private boolean reseteaFinal;
		private boolean usaParamDoc;
		private boolean usaParamForm;
		private String valor;


		public AtributoSelec() {
			super();
		}


		public AtributoSelec(ProcesoAtributo atributo) {
			this.procesoAtributo = atributo;
			paramForm = new ParametroForm();
			paramDoc = new ParametroForm();
		}


		public boolean getUsaParamDoc() {
			return usaParamDoc;
		}


		public void setUsaParamDoc(boolean usaParamDoc) {
			this.usaParamDoc = usaParamDoc;
		}


		public boolean getUsaParamForm() {
			return usaParamForm;
		}


		public void setUsaParamForm(boolean usaParamForm) {
			this.usaParamForm = usaParamForm;
		}


		public ParametroForm getParamDoc() {
			return paramDoc;
		}


		public void setParamDoc(ParametroForm paramDoc) {
			this.paramDoc = paramDoc;
		}


		public ParametroForm getParamForm() {
			return paramForm;
		}


		public void setParamForm(ParametroForm paramForm) {
			this.paramForm = paramForm;
		}


		public boolean getReseteaFinal() {
			return reseteaFinal;
		}


		public void setReseteaFinal(boolean reseteaFinal) {
			this.reseteaFinal = reseteaFinal;
		}


		public boolean getResetea() {
			return resetea;
		}


		public void setResetea(boolean resetea) {
			if (reseteaFinal) {
				resetea = false;
			}
			this.resetea = resetea;
		}


		public String getValor() {
			return valor;
		}


		public void setValor(String valor) {
			this.valor = valor;
		}


		public boolean getVisible() {
			return visible;
		}


		public void setVisible(boolean visible) {
			this.visible = visible;
		}


		public boolean getSoloLectura() {
			return soloLectura;
		}


		public void setSoloLectura(boolean soloLectura) {
			if (reseteaFinal) {
				soloLectura = true;
			}
			this.soloLectura = soloLectura;
		}


		public boolean getImpacta() {
			return impacta;
		}


		public void setImpacta(boolean impacta) {
			if (reseteaFinal) {
				impacta = false;
			}
			this.impacta = impacta;
		}


		public String getNombre() {
			return procesoAtributo.getNombre();
		}


		public void setNombre(String nombre) {
			procesoAtributo.setNombre(nombre);
		}


		public ProcesoAtributo getProcesoAtributo() {
			return procesoAtributo;
		}


		public void setProcesoAtributo(ProcesoAtributo procesoAtributo) {
			this.procesoAtributo = procesoAtributo;
		}


		public boolean getSeleccionado() {
			return seleccionado;
		}


		public void setSeleccionado(boolean seleccionado) {
			this.seleccionado = seleccionado;
		}


		public TareaProceso getTareaProceso() {
			return tareaProceso;
		}


		public void setTareaProceso(TareaProceso tareaProceso) {
			this.tareaProceso = tareaProceso;
		}


		public Long getIdTareaProceso() {
			return this.idTareaProceso;
		}


		public void setIdTareaProceso(Long idTareaProceso) {
			this.idTareaProceso = idTareaProceso;
		}


		public Long getIdProcesoAtributo() {
			return procesoAtributo.getIdProcesoAtributo();
		}


		public void setIdProcesoAtributo(Long idProcesoAtributo) {
			this.procesoAtributo.setIdProcesoAtributo(idProcesoAtributo);
		}


		public String getIdAtributo() {
			return procesoAtributo.getIdProcesoAtributo().toString();
		}


		public void setIdAtributo(String idAtributo) {
			this.procesoAtributo.setIdProcesoAtributo(new Long(idAtributo));
		}


		public boolean equals(Object obj) {
			if (obj instanceof AtributoSelec == false)
				return false;
			AtributoSelec atributoSelec = (AtributoSelec) obj;
			return atributoSelec.getIdProcesoAtributo().equals(getIdProcesoAtributo());
		}

	}

	public class TareaTabla {
		private TareaProceso tareaProceso;
		private Tarea tarea;
		private List atributos;
		private String titulo;


		public TareaTabla() {
			super();
			atributos = new ArrayList();
			tarea = new Tarea();
			tareaProceso = new TareaProceso();
		}


		public TareaTabla(Long id) {
			super();
			atributos = new ArrayList();
			tarea = new Tarea();
			tareaProceso = new TareaProceso();
			setIdTareaTabla(id);
		}


		public String getOrdenText() {
			switch (tareaProceso.getOrden().intValue()) {
			case 1:
				return "(Inicial)";

			case 2:
				return "(Final)";

			default:
				return "";
			}
		}


		public Integer getOrden() {
			return tareaProceso.getOrden();
		}


		public void setOrden(Integer orden) {
			tareaProceso.setOrden(orden);
		}


		public List getAtributos() {
			return atributos;
		}


		public void setAtributos(List atributos) {
			this.atributos = atributos;
		}


		public Long getIdTareaTabla() {
			return tareaProceso.getIdTareaProceso();
		}


		public void setIdTareaTabla(Long idTareaTabla) {
			tareaProceso.setIdTareaProceso(idTareaTabla);
		}


		public Tarea getTarea() {
			return tarea;
		}


		public void setTarea(Tarea tarea) {
			this.tarea = tarea;
		}


		public TareaProceso getTareaProceso() {
			return tareaProceso;
		}


		public void setTareaProceso(TareaProceso tareaProceso) {
			this.tareaProceso = tareaProceso;
		}


		public String getRolAsignado() {
			if (tareaProceso.getIdRol().equals(new Long(4))) {
				return "Automatico";
			} else {
				Rol rol = (Rol) Util.buscarElemento(rolList, new Rol(tareaProceso.getIdRol()));
				return rol.getLabel();
			}
		}


		public boolean equals(Object obj) {
			if (obj instanceof TareaTabla == false)
				return false;
			TareaTabla tareaT = (TareaTabla) obj;
			return tareaT.getIdTareaTabla().equals(getIdTareaTabla());
		}

	}

	public class ParametroSelec {
		private IniParametro parametro;
		private ProcesoAtributo procesoAtributo;

		private boolean seleccionado;


		public ParametroSelec() {
			super();
		}


		public ParametroSelec(IniParametro iniParametro) {
			this.parametro = iniParametro;
		}


		public IniParametro getParametro() {
			return parametro;
		}


		public void setParametro(IniParametro parametro) {
			this.parametro = parametro;
		}


		public String getNombre() {
			return parametro.getNombre();
		}


		public void setNombre(String nombre) {
			parametro.setNombre(nombre);
		}


		public ProcesoAtributo getProcesoAtributo() {
			return procesoAtributo;
		}


		public void setProcesoAtributo(ProcesoAtributo procesoAtributo) {
			this.procesoAtributo = procesoAtributo;
		}


		public boolean getSeleccionado() {
			return seleccionado;
		}


		public void setSeleccionado(boolean seleccionado) {
			this.seleccionado = seleccionado;
		}


		public Long getIdParametro() {
			return this.parametro.getIdIniParametro();
		}


		public void setIdParametro(Long idParametro) {
			this.parametro.setIdIniParametro(idParametro);
		}


		public boolean equals(Object obj) {
			if (obj instanceof ParametroSelec == false)
				return false;
			ParametroSelec parametroSelec = (ParametroSelec) obj;
			return parametroSelec.getIdParametro().equals(getIdParametro());
		}

	}

	public class RolSelect {
		private Rol rol;


		public RolSelect(Rol rol) {
			this.rol = rol;
		}


		public String getIdRol() {
			return rol.getId().toString();
		}


		public String getDescripcion() {
			return rol.getDescripcion();
		}

	}
}
