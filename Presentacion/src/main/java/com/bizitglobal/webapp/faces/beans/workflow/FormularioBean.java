package com.bizitglobal.webapp.faces.beans.workflow;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.AtributoDinamico;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.TareaDetalleTabla;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.TramiteTabla;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.workflow.exception.AccesoTareaHisException;
import com.bizitglobal.workflow.exception.AtributoValorException;
import com.bizitglobal.workflow.exception.DetalleTramiteException;
import com.bizitglobal.workflow.exception.DetalleTramiteHisException;
import com.bizitglobal.workflow.exception.FormExternoNotFoundException;
import com.bizitglobal.workflow.negocio.AccesoTareaHis;
import com.bizitglobal.workflow.negocio.AtributoProcesoTarea;
import com.bizitglobal.workflow.negocio.AtributoValor;
import com.bizitglobal.workflow.negocio.DetalleTramite;
import com.bizitglobal.workflow.negocio.DetalleTramiteHis;
import com.bizitglobal.workflow.negocio.Estado;
import com.bizitglobal.workflow.negocio.FormExterno;
import com.bizitglobal.workflow.negocio.ProcesoAtributo;
import com.bizitglobal.workflow.negocio.Tarea;
import com.bizitglobal.workflow.negocio.TareaProcesoRol;
import com.bizitglobal.workflow.negocio.Tramite;
import com.bizitglobal.workflow.negocio.TramiteParametro;


@SuppressWarnings({"rawtypes","unchecked"})
public class FormularioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(FormularioBean.class);
	private DetalleTramite detalleTramite;
	private DetalleTramiteHis detalleHistorico;
	private Tramite tramite;
	private String nombreTramite;
	private Tarea tarea;
	private Integer progreso;
	private boolean usarCU;

	private List estados;
	private List allAtributos;
	private List atributosListUno;
	private List atributosListDos;
	private List atributosListNoVisibles;
	private List operadorList = new ArrayList();
	private List operadorItems = new ArrayList();

	private List tablaHistorico = new ArrayList();

	private boolean vistaSuper;
	private boolean vistaAdmin;
	private boolean automatica;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");


	public FormularioBean() {
		super();
		setTituloLargo("Tarjeta Fiel - Tramites");
		setTituloCorto("Formulario Tarea");
	}


	public boolean isVistaAdmin() {
		return vistaAdmin;
	}


	public void setVistaAdmin(boolean vistaAdmin) {
		this.vistaAdmin = vistaAdmin;
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public List getTablaHistorico() {
		return tablaHistorico;
	}


	public void setTablaHistorico(List tablaHistorico) {
		this.tablaHistorico = tablaHistorico;
	}


	public DetalleTramite getDetalleTramite() {
		return detalleTramite;
	}


	public void setDetalleTramite(DetalleTramite detalleTramite) {
		this.detalleTramite = detalleTramite;
	}


	public Tramite getTramite() {
		return tramite;
	}


	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}


	public String getTipoClave() {
		String ret = "Cod. Unico: ";
		if (this.tramite.getProceso() != null && this.tramite.getProceso().getTipoClave().compareTo("C") == 0)
			ret = "CUIT: ";
		else if (this.tramite != null)
			ret = "CUIL: ";
		return ret;
	}


	public boolean getUsarCU()
	{
		return this.tramite.getProceso().getRequiereCU() != null && this.tramite.getProceso().getRequiereCU().compareTo("S") == 0 ? true : false;
	}


	public String getNombreTramite() {
		return nombreTramite;
	}


	public void setNombreTramite(String nombreTramite) {
		this.nombreTramite = nombreTramite;
	}


	public Integer getProgreso() {
		return progreso;
	}


	public void setProgreso(Integer progreso) {
		this.progreso = progreso;
	}


	public Tarea getTarea() {
		return tarea;
	}


	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}


	public List getAtributosListDos() {
		return atributosListDos;
	}


	public void setAtributosListDos(List atributosListDos) {
		this.atributosListDos = atributosListDos;
	}


	public List getAtributosListUno() {
		return atributosListUno;
	}


	public void setAtributosListUno(List atributosListUno) {
		this.atributosListUno = atributosListUno;
	}


	public boolean getVistaSuper() {
		return vistaSuper;
	}


	public void setVistaSuper(boolean vistaSuper) {
		this.vistaSuper = vistaSuper;
	}


	public boolean isAutomatica() {
		return automatica;
	}


	public void setAutomatica(boolean automatica) {
		this.automatica = automatica;
	}


	public List getOperadorItems() {
		return operadorItems;
	}


	public void setOperadorItems(List operadorItems) {
		this.operadorItems = operadorItems;
	}


	public Long getOperadorSeleccionado() {
		if (detalleTramite.getOperadorResponsable() != null && detalleTramite.getOperadorResponsable().getCodigo() != null) {
			return detalleTramite.getOperadorResponsable().getCodigo();
		} else {
			return new Long(0);
		}
	}


	public void setOperadorSeleccionado(Long operadorSeleccionado) {
		detalleTramite.setOperadorResponsable((Operador)
				Util.buscarElemento(operadorList, new Operador(operadorSeleccionado)));
	}


	// METODOS PRINCIPALES DEL BEAN
	public void borrar() {
		error.borrar();
		alta = true;
		detalleTramite = new DetalleTramite();
		detalleHistorico = new DetalleTramiteHis(detalleTramite);
		tramite = new Tramite();
		tarea = new Tarea();
		nombreTramite = "";
		progreso = new Integer(0);
		popupReport = "";
		vistaSuper = false;
		automatica = false;
		allAtributos = new ArrayList();
		atributosListUno = new ArrayList();
		atributosListDos = new ArrayList();
		atributosListNoVisibles = new ArrayList();
		tablaHistorico = new ArrayList();
		cargarItems();
	}


	private void cargarItems() {
		operadorItems.clear();
		operadorItems.add(new SelectItem(new Long(0), "Seleccione"));
		operadorItems.addAll(Util.cargarSelectItem(operadorList));
	}


	public String inicializar() {
		borrar();
		popup.borrar();
		return "";
	}


	public String inicializarSuperTarea(TareaDetalleTabla detalleTabla, List estados) {
		inicializar(detalleTabla, estados);
		vistaSuper = true;
		if (detalleTabla.getTarea().getTipoTarea().getIdTipoTarea().equals(new Long(2))) {
			automatica = true; // ver la posibilidad de no cargar los roles cuando es automatica
		}
		try {
			Filtro filtro = new Filtro();
			StringBuffer where = new StringBuffer();
			boolean flag = true;
			Iterator iter = workflowService.getTareaProcesoRolService().listarTareaProcesoRol(
					new Filtro(TareaProcesoRol.ID_TAREA_PROCESO, Filtro.IGUAL, detalleTramite.getTareaProceso().getIdTareaProceso())).iterator();
			TareaProcesoRol tpr;
			while (iter.hasNext()) {
				tpr = (TareaProcesoRol) iter.next();
				if (flag) {
					filtro.agregarCampoOperValor("rol.idRol", Filtro.IGUAL, tpr.getIdRol());
					flag = false;
				} else
					where.append(" OR obj.rol.idRol = " + tpr.getIdRol());
			}
			filtro.agregarfuncion(where.toString());
			// log.info("Filtro Oper -> " + filtro.getHQL());
			operadorList = operadorService.getOperadorService().getOperadores(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cargarItems();
		return "";
	}


	public String inicializarSuperTramite(TramiteTabla tramiteTabla, List estados) {
		inicializar();
		tramite = tramiteTabla.getTramite();
		vistaSuper = true;
		try {
			Filtro filtroParam = new Filtro(TramiteParametro.ID_TRAMITE, Filtro.IGUAL, tramite.getIdTramite());
			/* @I5547 */filtroParam.agregarOrderBy(TramiteParametro.ID_INI_PARAMETRO);
			/* @F5547 */List parametroList = workflowService.getTramiteParametroService().listarTramiteParametro(filtroParam);
			TramiteParametro parametroNombre = (TramiteParametro) parametroList.get(0);
			nombreTramite = parametroNombre.getValor();
			tramite.setOperadorSup(operadorService.getOperadorService().leerOperador(tramite.getOperadorSup().getCodigo()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		tablaHistorico = FormularioUtil.cargarListaTramites(tramite, workflowService, operadorService.getOperadorService(), estados);
		Session.redirect("/workflow/escritorios/formularioTarea.jsf");
		return "";
	}


	public String inicializar(TareaDetalleTabla detalleTabla, List estados) {
		inicializar();
		this.estados = estados;
		detalleTramite = detalleTabla.getDetalleTramite();
		nombreTramite = detalleTabla.getNombreTramite();
		tramite = detalleTramite.getTramite();
		Filtro filtroHis = new Filtro(DetalleTramiteHis.ID_DETALLE_TRAMITE, Filtro.IGUAL, detalleTramite.getIdDetalleTramite());
		filtroHis.agregarOrderBy(DetalleTramiteHis.ID_DETALLE_TRAMITE_HIS);
		try {
			List histori = workflowService.getDetalleTramiteHisService().listarDetalleTramiteHis(filtroHis);
			if (!histori.isEmpty()) {
				detalleHistorico = (DetalleTramiteHis) histori.get(histori.size() - 1);
			}
			AccesoTareaHis accesoTareaHis = new AccesoTareaHis();
			accesoTareaHis.setIdDetalleTramiteHis(detalleHistorico.getIdDetalleTramiteHis());
			accesoTareaHis.setIdOperador(Session.getOperador().getCodigo());
			accesoTareaHis.setTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
			workflowService.getAccesoTareaHisService().grabarAccesoTareaHis(accesoTareaHis);
		} catch (DetalleTramiteHisException e1) {
			e1.printStackTrace();
		} catch (AccesoTareaHisException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try { // le cargo el operador
			detalleTramite.setOperadorResponsable(
					operadorService.getOperadorService().leerOperador(
							detalleTramite.getOperadorResponsable().getCodigo()));
			// busco el proceso que le corresponde al tramite
			tramite.setProceso(workflowService.getProcesoService().leerProceso(
					tramite.getProceso().getIdProceso()));
			Tramite auxTramite = (Tramite) workflowService.getTramiteService().leerTramite(tramite.getIdTramite());
			tramite.setCu(auxTramite.getCu());
			tramite.setTipoClave(auxTramite.getTipoClave());
			// busco la tarea que representa
			tarea = workflowService.getTareaService().leerTarea(
					detalleTramite.getTareaProceso().getIdTarea());
			// le cargo los formularios asociados a la tarea
			try {
				tarea.setDocContractual(workflowService.getFormExternoService().leerFormExterno(
						tarea.getDocContractual().getIdFormExterno()));
			} catch (FormExternoNotFoundException e) {
				// e.printStackTrace();
			}
			try {
				tarea.setFormExterno(workflowService.getFormExternoService().leerFormExterno(
						tarea.getFormExterno().getIdFormExterno()));
			} catch (FormExternoNotFoundException e) {
				// e.printStackTrace();
			}
			// cargo los atributos para la parte dinamica
			allAtributos = workflowService.getAtributoProcesoTareaService().listarAtributoProcesoTarea(
					new Filtro(AtributoProcesoTarea.ID_TAREA_PROCESO, Filtro.IGUAL, detalleTramite.getTareaProceso().getIdTareaProceso()));
			if (!allAtributos.isEmpty()) {
				Iterator iterAux = allAtributos.iterator();
				boolean par = false;
				while (iterAux.hasNext()) {
					AtributoProcesoTarea atp = (AtributoProcesoTarea) iterAux.next();
					ProcesoAtributo atributo = workflowService.getProcesoAtributoService().leerProcesoAtributo(atp.getIdProcesoAtributo());
					Filtro filtro = new Filtro(AtributoValor.ID_PROCESO_ATRIBUTO, Filtro.IGUAL, atributo.getIdProcesoAtributo());
					filtro.agregarCampoOperValor(AtributoValor.ID_TRAMITE, Filtro.IGUAL, tramite.getIdTramite());
					List listValor = workflowService.getAtributoValorService().listarAtributoValor(filtro);
					AtributoValor aValor = new AtributoValor();

					if (!listValor.isEmpty()) {
						aValor = (AtributoValor) listValor.get(0);
					} else {
						// Si el atributo no tiene un valor cargado en el workflow busco si tiene valor
						if (atributo.getValor()) {
							aValor.setValor(workflowService.getProcesoAtributoService().sqlValor(atributo, tramite.getIdTramite()));
						}
					}
					List valores = new ArrayList();
					if (atributo.getLista()) {
						try {
							valores = workflowService.getProcesoAtributoService().sqlLista(
									atributo, tramite.getIdTramite());
						} catch (Exception e) {
							e.printStackTrace();
							error.agregar("No se pudo obtener la lista del atributo '" + atributo.getNombre() + "'.");
						}
					}

					if (atp.getVisible()) {
						AtributoDinamico aDinamico = new AtributoDinamico(atributo, aValor, atp.getSoloLectura(), valores);
						if (par) {
							atributosListDos.add(aDinamico);
							par = !par;
						} else {
							atributosListUno.add(aDinamico);
							par = !par;
						}
					}
					else
					{
						// Atributos no visibles, pero si usados solo para validaciones.
						AtributoDinamico aDinamico = new AtributoDinamico(atributo, aValor, atp.getSoloLectura(), valores);
						atributosListNoVisibles.add(aDinamico);
					}
				}
				if (atributosListUno.size() > atributosListDos.size()) {
					AtributoDinamico aDinamico = new AtributoDinamico();
					aDinamico.setAtributo(new ProcesoAtributo());
					aDinamico.setCompletaPanel(true);
					aDinamico.setBoolNombre(false);
					atributosListDos.add(aDinamico);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/workflow/escritorios/formularioTarea.jsf");
		return "";
	}


	public String restaurarValor() {
		error.borrar();
		popupReport = "";
		Long idAtributo = new Long(Session.getRequestParameter("idAtributo"));
		int indice = atributosListUno.indexOf(new AtributoDinamico(idAtributo));
		AtributoDinamico aDinamico = null;
		if (indice < 0) {
			indice = atributosListDos.indexOf(new AtributoDinamico(idAtributo));
			aDinamico = (AtributoDinamico) atributosListDos.get(indice);
		} else {
			aDinamico = (AtributoDinamico) atributosListUno.get(indice);
		}
		if (aDinamico != null) {
			try {
				aDinamico.getAtributoValor().setValor(workflowService.getProcesoAtributoService().sqlValor(
						aDinamico.getAtributo(), tramite.getIdTramite()));
			} catch (Exception e) {
				e.printStackTrace();
				error.agregar("No se pudo obtener el valor del  atributo '" + aDinamico.getNombre() + "'.");
			}
			// String query = aDinamico.getAtributo().getSqlValor();
			// int iPos = 0;
			// // Tiene que tener si o si la cadena "VALOR"
			// if (query.indexOf("VALOR") >= 0) {
			// iPos = query.indexOf("@{");
			// while (iPos >= 0) {
			// String nomBuscado = query.substring(iPos + 2, query.indexOf("}"));
			// String valorAtri = null;
			// try {
			// // busco el valor del atributo que me indica en la query
			// valorAtri = workflowService.getAtributoValorService().buscarValorTramite(
			// tramite.getIdTramite(), nomBuscado);
			// } catch (AtributoValorException e) {
			// e.printStackTrace();
			// }
			// if (valorAtri != null) {
			// // reemplazo la ocurrencia de ese atributo en la query
			// query = query.replace("@{"+ nomBuscado +"}", valorAtri);
			// iPos = query.indexOf("@{");
			// }else {
			// error.agregar("No se pudo obtener el valor con el nombre de atributo '" + nomBuscado + "'.");
			// return "";
			// }
			// }
			//
			// }else {
			// // Hacer algo si no tiene 'VALOR'
			// }
		}
		return "";
	}


	public String irAEscritorio() {
		Session.redirect("/workflow/escritorios/escritorio.jsf");
		return "";
	}


	public String irAEscritorioRefresc() {
		EscritorioBean escritorioBean = (EscritorioBean) Session.getBean("EscritorioBean");
		return escritorioBean.volver();
	}


	public boolean validar() {
		error.borrar();
		if (!vistaSuper
				&& (detalleTramite.getEstado().getIdEstado().equals(new Long(6))
				|| detalleTramite.getEstado().getIdEstado().equals(new Long(4)))) {
			error.agregar("No se pueden realizar acciones sobre tareas finalizadas o canceladas");
			return false;
		}
		if (detalleTramite.getOperadorResponsable() == null
				|| detalleTramite.getOperadorResponsable().getCodigo() == null
				|| detalleTramite.getOperadorResponsable().getCodigo().equals(new Long(0))) {
			error.agregar("No se puede grabar una tarea que no tenga un operador responsable.");
			return false;
		}

		if (!atributosListUno.isEmpty()) {
			Iterator iter = atributosListUno.iterator();
			while (iter.hasNext()) {
				AtributoDinamico element = (AtributoDinamico) iter.next();
				String logError = element.validar(workflowService.getProcesoAtributoService(),
						detalleTramite.getTramite().getIdTramite());
				if (logError != null) {
					error.agregar(logError);
				}
			}
		}

		if (!atributosListDos.isEmpty()) {
			Iterator iter = atributosListDos.iterator();
			while (iter.hasNext()) {
				AtributoDinamico element = (AtributoDinamico) iter.next();
				String logError = element.validar(workflowService.getProcesoAtributoService(),
						detalleTramite.getTramite().getIdTramite());
				if (logError != null) {
					error.agregar(logError);
				}
			}
		}

		if (!atributosListNoVisibles.isEmpty()) {
			Iterator iter = atributosListNoVisibles.iterator();
			while (iter.hasNext()) {
				AtributoDinamico element = (AtributoDinamico) iter.next();
				String logError = element.validar(workflowService.getProcesoAtributoService(),
						detalleTramite.getTramite().getIdTramite());
				if (logError != null) {
					error.agregar(logError);
				}
			}
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public boolean validarFinalizar() {
		validar();
		if (!detalleTramite.getEstado().getIdEstado().equals(new Long(2))) {
			if (!detalleTramite.getEstado().getIdEstado().equals(new Long(3))) {
				error.agregar("No se puede finalizar una tarea que no este en proceso.");
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String cancelar() {
		// Session.redirect("/workflow/escritorios/escritorio.jsf");
		// EscritorioBean escritorioBean = (EscritorioBean)Session.getBean("EscritorioBean");
		// escritorioBean.setIndexTab(new Integer(0));
		// escritorioBean.setTabTatea(true);
		if (!vistaAdmin)
			return irAEscritorio();
		else {
			Session.redirect("/workflow/escritorios/adminTramites.jsf");
			return "";
		}
	}


	public String grabar() {
		if (validar()) {
			// primero actualizo los valores de los atributos que me muestra
			grabarAtributos();
			// seteo los estados y el operador que realiza la operacion
			if (detalleTramite.getEstado().getIdEstado().equals(new Long(2))
					|| detalleTramite.getEstado().getIdEstado().equals(new Long(1)))
				detalleTramite.setEstado(EscritorioUtil.buscarEstado(new Estado(new Long(3)), estados));
			if (detalleTramite.getFechaInicioReal() == null) {
				detalleTramite.setFechaInicioReal(new Timestamp(Calendar.getInstance().getTime().getTime()));
				if (detalleTramite.getTramite().getFechaInicioReal() == null)
					detalleTramite.getTramite().setFechaInicioReal(new Timestamp(Calendar.getInstance().getTime().getTime()));
			}
			detalleTramite.setOperador(Session.getOperador());
			try { // si el estado del tramite no esta "en proceso" lo actualizo
				if (!tramite.getEstado().getIdEstado().equals(new Long(3))) {
					tramite.setEstado(detalleTramite.getEstado());
					workflowService.getTramiteService().actualizarTramite(tramite);
				}
				// actualizo el detalle tamite y todos sus historicos
				workflowService.getDetalleTramiteService().actualizarDetalleTramite(detalleTramite);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (alta) {
				return irAEscritorioRefresc();
			} else {
				return "";
			}
		}
		return "";
	}


	public void grabarAtributos() {
		try {
			if (!atributosListUno.isEmpty()) {
				Iterator iterlist1 = atributosListUno.iterator();
				while (iterlist1.hasNext()) {
					AtributoDinamico aDinamico = (AtributoDinamico) iterlist1.next();
					AtributoValor aValor = aDinamico.getAtributoValor();
					aValor.setProcesoAtributo(aDinamico.getAtributo());
					aValor.setTramite(tramite);
					if (Validador.esNulo(aValor.getIdAtributoValor())) {
						workflowService.getAtributoValorService().grabarAtributoValor(aValor);
					} else {
						AtributoValor aValorBase = workflowService.getAtributoValorService().leerAtributoValor(aValor.getIdAtributoValor());
						if (!aValor.getValor().equals(aValorBase.getValor())) {
							workflowService.getAtributoValorService().actualizarAtributoValor(aValor);
						}
					}
				}
				if (!atributosListDos.isEmpty()) {
					Iterator iterlist2 = atributosListDos.iterator();
					while (iterlist2.hasNext()) {
						AtributoDinamico aDinamico = (AtributoDinamico) iterlist2.next();
						if (!aDinamico.isCompletaPanel()) {
							AtributoValor aValor = aDinamico.getAtributoValor();
							aValor.setProcesoAtributo(aDinamico.getAtributo());
							aValor.setTramite(tramite);
							if (Validador.esNulo(aValor.getIdAtributoValor())) {
								workflowService.getAtributoValorService().grabarAtributoValor(aValor);
							} else {
								AtributoValor aValorBase = workflowService.getAtributoValorService().leerAtributoValor(aValor.getIdAtributoValor());
								if (!aValor.getValor().equals(aValorBase.getValor())) {
									workflowService.getAtributoValorService().actualizarAtributoValor(aValor);
								}
							}
						}
					}
				}
			}
		} catch (AtributoValorException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// Llamar el metodo del service
	public String finalizar() {
		popupReport = "";
		if (validarFinalizar()) {
			grabarAtributos();
			try {
				StringBuffer mensaje = new StringBuffer(100);
				detalleTramite.setOperador(Session.getOperador());
				if (workflowService.getDetalleTramiteService().finalizarDetalleTramite(detalleTramite, mensaje)) {
					popup.setPopup(popup.ICONO_OK, mensaje.toString());
				} else {
					popup.setPopup(popup.ICONO_FALLA, mensaje.toString());
				}
			} catch (DetalleTramiteException e) {
				// TODO: handle exception
				e.printStackTrace();
				popup.setPopup(popup.ICONO_FALLA, "Error general.");
			}
		}
		return "";
	}


	public String rechazar() {
		error.borrar();
		if (detalleTramite.getEstado().getIdEstado().equals(new Long(2))) {
			// seteo los estados y el operador que realiza la operacion
			detalleTramite.setEstado(EscritorioUtil.buscarEstado(new Estado(new Long(5)), estados));
			detalleTramite.setOperador(Session.getOperador());
			detalleTramite.setOperadorResponsable(new Operador(null));
			try {
				// actualizo el detalle tamite y todos sus historicos
				workflowService.getDetalleTramiteService().actualizarDetalleTramite(detalleTramite);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return irAEscritorio();
		} else {
			error.agregar("No se puede rechazar la tarea una vez en proceso");
		}
		return "";
	}


	public String irFormExterno() {
		llamarFormExt(tarea.getFormExterno());
		return null;
	}


	public String irDocCont() {
		HttpServletRequest request = Session.getRequest();
		String path = request.getContextPath() + "/report/";
		grabar();
		if (error.cantidad() == 0) {
			Map param = FormularioUtil.cargarMapParam(tarea.getDocContractual(), detalleTramite, workflowService);
			path += tarea.getDocContractual().getUrl();
			Set s = param.keySet();
			if (!s.isEmpty()) {
				path += "?";
				boolean flag = false;
				Iterator iterator = s.iterator();
				while (iterator.hasNext()) {
					if (flag)
						path += "&";
					String clave = (String) iterator.next();
					path += clave + "=" + (String) param.get(clave);
					flag = true;
				}
			}
			popupReport = "popup('" + path + "',1000,600)";
			log.info(popupReport);
		}
		return null;
	}


	public void llamarFormExt(FormExterno formExterno) {
		popupReport = "";
		alta = false;
		grabar();
		alta = true;

		Map param = FormularioUtil.cargarMapParam(formExterno, detalleTramite, workflowService);
		String path = new String();
		if (formExterno.getLocal()) {
			path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += formExterno.getUrl();
			BaseBean objetoBase = (BaseBean) Session.getBean(formExterno.getBean());
			if (objetoBase != null) {
				objetoBase.inicializarParametros(param);
			} else {
				error.borrar();
				error.agregar("Error al intentar iniciar el formulario externo. No se encotro el Bean");
			}
		} else {
			path += formExterno.getUrl();
			Set s = param.keySet();
			if (!s.isEmpty()) {
				path += "?";
				boolean flag = false;
				Iterator iterator = s.iterator();
				while (iterator.hasNext()) {
					if (flag)
						path += "&";
					String clave = (String) iterator.next();
					path += clave + "=" + (String) param.get(clave);
					flag = true;
				}
			}
		}

		if (!error.hayErrores()) {
			log.info("Llamada a scrip ==> popup('" + path + "',1000,800), 'titlebar=no';");
			ejecutarJavaScript("popup('" + path + "',1000,800);");
		}

	}
}
