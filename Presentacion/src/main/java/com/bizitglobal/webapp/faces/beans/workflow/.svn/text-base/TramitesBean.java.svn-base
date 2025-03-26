package com.bizitglobal.webapp.faces.beans.workflow;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.navmenu.jscookmenu.HtmlCommandJSCookMenu;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
import com.bizitglobal.tarjetafiel.operador.exeption.OperadorException;
import com.bizitglobal.tarjetafiel.operador.exeption.RolException;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.TramiteWrapper;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.workflow.exception.AtributoValorException;
import com.bizitglobal.workflow.exception.EstadoException;
import com.bizitglobal.workflow.exception.IniParametroException;
import com.bizitglobal.workflow.exception.ProcesoAtributoException;
import com.bizitglobal.workflow.exception.ProcesoException;
import com.bizitglobal.workflow.exception.TareaException;
import com.bizitglobal.workflow.exception.TareaProcesoException;
import com.bizitglobal.workflow.exception.TipoAtributoException;
import com.bizitglobal.workflow.exception.TramiteException;
import com.bizitglobal.workflow.negocio.AtributoValor;
import com.bizitglobal.workflow.negocio.AtributoValorHis;
import com.bizitglobal.workflow.negocio.DetalleTramite;
import com.bizitglobal.workflow.negocio.DetalleTramiteHis;
import com.bizitglobal.workflow.negocio.IniParametro;
import com.bizitglobal.workflow.negocio.Proceso;
import com.bizitglobal.workflow.negocio.ProcesoAtributo;
import com.bizitglobal.workflow.negocio.Tarea;
import com.bizitglobal.workflow.negocio.TareaProceso;
import com.bizitglobal.workflow.negocio.Tramite;
import com.bizitglobal.workflow.negocio.TramiteParametro;
import com.bizitglobal.tarjetafiel.commons.util.CuitValido;
import com.bizitglobal.tarjetafiel.commons.exception.CuitNoValidoException;


@SuppressWarnings({"rawtypes","unchecked"})
public class TramitesBean extends BaseBean {

	private static final Logger log = Logger.getLogger(TramitesBean.class);

	private Proceso proceso;
	private Tramite tramite;
	private String label;
	private DetalleTramite detalleTramite;
	private AtributoValor atributoValor;
	private TramiteParametro tramiteParametro;
	private DetalleTramiteHis detalleTramiteHis;
	private AtributoValorHis atributoValorHis;

	private String supervisor;
	private String tituloCorto = "Tramites";
	private String tituloLargo = "Administración de Tramites";
	private boolean usarCU;
	private String cu;

	private List parametroList;
	private List parametroWrapperList;
	private List parametroWrapperListDos;
	private List tareaProcesoList;
	private List auxTareaProcesoList;

	private Long idSelect;
	private Long duracionTarea;

	private Filtro filtroAtribRestantes;

	private boolean opcionA;


	public TramitesBean() {
		super();

	}


	public Proceso getProceso() {
		return proceso;
	}


	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
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


	public String getSupervisor() {
		return supervisor;
	}


	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}


	public boolean getOpcionA() {
		return opcionA;
	}


	public void setOpcionA(boolean opcionA) {
		this.opcionA = opcionA;
	}


	public List getParametroList() {
		return parametroList;
	}


	public void setParametroList(List parametroList) {
		this.parametroList = parametroList;
	}


	public List getParametroWrapperList() {
		return parametroWrapperList;
	}


	public void setParametroWrapperList(List parametroWrapperList) {
		this.parametroWrapperList = parametroWrapperList;
	}


	public List getParametroWrapperListDos() {
		return parametroWrapperListDos;
	}


	public void setParametroWrapperListDos(List parametroWrapperListDos) {
		this.parametroWrapperListDos = parametroWrapperListDos;
	}


	public Long getIdSelect() {
		return idSelect;
	}


	public void setIdSelect(Long idSelect) {
		this.idSelect = idSelect;
	}


	public Tramite getTramite() {
		return tramite;
	}


	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}


	public Long getTiempoTarea() {
		return duracionTarea;
	}


	public void setTiempoTarea(Long tiempoTarea) {
		this.duracionTarea = tiempoTarea;
	}


	public DetalleTramite getDetalleTramite() {
		return detalleTramite;
	}


	public void setDetalleTramite(DetalleTramite detalleTramite) {
		this.detalleTramite = detalleTramite;
	}


	public Long getDuracionTarea() {
		return duracionTarea;
	}


	public void setDuracionTarea(Long duracionTarea) {
		this.duracionTarea = duracionTarea;
	}


	public AtributoValor getAtributoValor() {
		return atributoValor;
	}


	public void setAtributoValor(AtributoValor atributoValor) {
		this.atributoValor = atributoValor;
	}


	public List getAuxTareaProcesoList() {
		return auxTareaProcesoList;
	}


	public void setAuxTareaProcesoList(List auxTareaProcesoList) {
		this.auxTareaProcesoList = auxTareaProcesoList;
	}


	public List getTareaProcesoList() {
		return tareaProcesoList;
	}


	public void setTareaProcesoList(List tareaProcesoList) {
		this.tareaProcesoList = tareaProcesoList;
	}


	public TramiteParametro getTramiteParametro() {
		return tramiteParametro;
	}


	public void setTramiteParametro(TramiteParametro tramiteParametro) {
		this.tramiteParametro = tramiteParametro;
	}


	public DetalleTramiteHis getDetalleTramiteHis() {
		return detalleTramiteHis;
	}


	public void setDetalleTramiteHis(DetalleTramiteHis detalleTramiteHis) {
		this.detalleTramiteHis = detalleTramiteHis;
	}


	public AtributoValorHis getAtributoValorHis() {
		return atributoValorHis;
	}


	public void setAtributoValorHis(AtributoValorHis atributoValorHis) {
		this.atributoValorHis = atributoValorHis;
	}


	public boolean getUsarCU()
	{
		return proceso.getRequiereCU() != null && proceso.getRequiereCU().compareTo("S") == 0 ? true : false;
	}


	public String getCu() {
		return cu;
	}


	public void setCu(String cu) {
		this.cu = cu;
	}


	public void borrar() {
		borrarBaseBean();

		proceso = new Proceso();

		parametroList = new ArrayList();
		parametroWrapperList = new ArrayList();
		parametroWrapperListDos = new ArrayList();
		tareaProcesoList = new ArrayList();
		auxTareaProcesoList = new ArrayList();

		duracionTarea = new Long(0);
		cu = "";
		opcionA = false;
	}


	public String inicializar(ActionEvent event) {
		borrar();
		List auxProc = new ArrayList();
		HtmlCommandJSCookMenu command = (HtmlCommandJSCookMenu) event.getSource();
		label = (String) command.getValue();

		boolean lista = false;
		/*
		 * Aca debe ir el metodo que captura el menuItem seleccionado, a parter de ahi sigue el resto.
		 */
		try {
			Filtro filtro = new Filtro(Proceso.TITULO, Filtro.LIKEXS, label);
			filtro.agregarCampoOperValor(Proceso.ACTIVO, Filtro.LIKE, new String("S"));
			// Aca buscamos el proceso que capturamos desde el menu.
			auxProc = workflowService.getProcesoService().listarProceso(filtro);
			if (!auxProc.isEmpty()) {
				proceso = (Proceso) auxProc.get(0);
				// Una vez que tenemos el proceso empezamos a armarlo con los objetos correspondientes
				proceso.setRol(workflowService.getRolService().leerRol(proceso.getRol().getIdRol()));
				proceso.setSupervisorDef(operadorService.getOperadorService().leerOperador(proceso.getSupervisorDef().getCodigo()));
				supervisor = proceso.getSupervisorDef().getApellido() + ", " + proceso.getSupervisorDef().getNombre();

				if (proceso.getActivo())
					opcionA = true;
				// Aqui buscamos todos los objetos IniParametros que estan relacionados con el proeso.
				filtro = new Filtro(IniParametro.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
				/* @I5547 */filtro.agregarOrderBy(IniParametro.ID_INI_PARAMETRO);
				/* @F5547 */parametroList = workflowService.getIniParametroService().listarIniParametro(filtro);

				if (!parametroList.isEmpty()) {
					Iterator parametroIter = parametroList.iterator();
					while (parametroIter.hasNext()) {
						IniParametro parametro = (IniParametro) parametroIter.next();
						/*
						 * Creo un objeto ParametroTramite y cargo todos los objetos asociados al parametro. En este objeto armo el paramtetro.
						 */
						// Traemos el proceso atributo.
						ProcesoAtributo atributo = workflowService.getProcesoAtributoService().leerProcesoAtributo(parametro.getIdProcAtributo());
						// Una vez q tenemos el procesoAtributo le cargamos el objeto de tipo de atributo.
						atributo.setTipoAtributo(workflowService.getTipoAtributoService().leerTipoAtributo(
								atributo.getTipoAtributo().getIdTipoAtributo()));
						List valores = new ArrayList();
						if (atributo.getLista()) {
							try {
								valores = workflowService.getProcesoAtributoService().sqlLista(
										atributo, new Long(0));
							} catch (Exception e) {
								e.printStackTrace();
								error.agregar("No se pudo obtener la lista del atributo '" + atributo.getNombre() + "'.");
							}
						}
						if (!parametro.isBuscaValor())
						{
							TramiteWrapper tramiteWrapper = new TramiteWrapper(parametro, atributo, valores);
							tramiteWrapper.setProceso(proceso);
							if (!lista) {
								parametroWrapperList.add(tramiteWrapper);
								lista = true;
							}
							else {
								parametroWrapperListDos.add(tramiteWrapper);
								lista = false;
							}
						}
					}
					if (parametroWrapperList.size() > parametroWrapperListDos.size()) {
						TramiteWrapper tramiteWrapper = new TramiteWrapper();
						tramiteWrapper.setCompletaPanel(true);
						tramiteWrapper.setBoolNombre(false);
						parametroWrapperListDos.add(tramiteWrapper);
					}
				}
			}
			else {
				error.agregar(Error.PROCESO_NO_EXISTE);
			}
		} catch (ProcesoException e) {
			e.printStackTrace();
		} catch (RolException e) {
			e.printStackTrace();
		} catch (OperadorException e) {
			e.printStackTrace();
		} catch (IniParametroException e) {
			e.printStackTrace();
		} catch (ProcesoAtributoException e) {
			e.printStackTrace();
		} catch (TipoAtributoException e) {
			e.printStackTrace();
		}
		Session.redirect("/workflow/tramites/amTramites.jsf");
		return null;
	}


	public boolean validar() {
		log.info("Ejecutando --> validar()");
		error.borrar();
		Map atributosMap = new HashMap();
		if (!parametroWrapperList.isEmpty()) {
			Iterator iterator = parametroWrapperList.iterator();
			while (iterator.hasNext()) {
				TramiteWrapper wrapper = (TramiteWrapper) iterator.next();
				atributosMap.put(wrapper.getAtributo().getNombre(), wrapper.devolverValor());
			}
		}
		if (!parametroWrapperListDos.isEmpty()) {
			Iterator iterator = parametroWrapperListDos.iterator();
			while (iterator.hasNext()) {
				TramiteWrapper wrapper = (TramiteWrapper) iterator.next();
				if (!wrapper.isCompletaPanel()) {
					atributosMap.put(wrapper.getAtributo().getNombre(), wrapper.devolverValor());
				}
			}
		}
		if (!parametroWrapperList.isEmpty()) {
			log.info("Validando Lista Wrapper Uno");
			Iterator iterator = parametroWrapperList.iterator();
			while (iterator.hasNext()) {
				TramiteWrapper wrapper = (TramiteWrapper) iterator.next();
				String logError = wrapper.validar(workflowService.getProcesoAtributoService(), atributosMap);
				if (logError != null) {
					error.agregar(logError);
				}
			}
		}
		else {
			error.agregar(Error.TRAMITES_WRAPPER_LIST_VACIA);
		}

		if (!parametroWrapperListDos.isEmpty()) {
			log.info("Validando Lisa Wrapper Dos");
			Iterator iterator = parametroWrapperListDos.iterator();
			while (iterator.hasNext()) {
				TramiteWrapper wrapper = (TramiteWrapper) iterator.next();
				String logError = wrapper.validar(workflowService.getProcesoAtributoService(), atributosMap);
				if (logError != null) {
					error.agregar(logError);
				}
			}
		}
		Filtro filtro;
		try {
			log.info("Validando procesos asociados");
			filtro = new Filtro(TareaProceso.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
			auxTareaProcesoList = workflowService.getTareaProcesoService().listarTareaProceso(filtro);
			if (auxTareaProcesoList.isEmpty()) {
				error.agregar(Error.TAREA_PROCESO_NO_EXISTENTE);
			}
		} catch (TareaProcesoException e1) {
			e1.printStackTrace();
		}

		try {
			log.info("Validando que haya una tarea proceso al menos de orden 1");
			filtro = new Filtro(TareaProceso.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
			filtro.agregarCampoOperValor(TareaProceso.ORDEN, Filtro.IGUAL, new Long(1));
			tareaProcesoList = workflowService.getTareaProcesoService().listarTareaProceso(filtro);
			if (tareaProcesoList.isEmpty()) {
				error.agregar(Error.TAREA_PROCESO_NO_INICIAL);
			}
		} catch (TareaProcesoException e) {

			e.printStackTrace();
		}

		try {
			if (this.getUsarCU()) {
				if (cu != null || !cu.equals("")) {
					if (cu.length() == 11) {
						CuitValido cuitValido = new CuitValido(cu);
					} else {
						error.agregar(Error.TRAMITES_CU_LONGITUD);
					}
				}
				else {
					error.agregar(Error.TRAMITES_CU_REQUERIDO);
				}
			}
		} catch (CuitNoValidoException e) {
			error.agregar(Error.TRAMITES_CU_INVALIDO);
		}

		return !error.hayErrores();
	}


	public String cancelar() {

		return "inicio";
	}


	/*
	 * este metodo es el que se lanza para empezar a grabar los tramites, los detalles de tramites, los histotiales de detalle y de atributo valor, el
	 * parametro del tramite, el atributo valor entro otros.
	 */
	public String grabarTramite() throws ProcesoException {
		tramite = new Tramite();
		if (getUsarCU())
		{
			tramite.setCu(this.getCu());
			tramite.setTipoClave(proceso.getTipoClave());
		}
		detalleTramite = new DetalleTramite();
		detalleTramiteHis = new DetalleTramiteHis();
		atributoValorHis = new AtributoValorHis();

		if (validar()) {
			armarDetalleTramite();
			armarValor();
			armarTramiteParametro();
			armarDetalleTramiteHistorico();
			log.info("Ejecutando --> armarTramite()");

			try {
				tramite.setProceso(proceso);

				tramite.setFechaInicio(new Timestamp(Calendar.getInstance().getTime().getTime()));
				log.info("Fecha inicio: " + tramite.getFechaInicio());
				/*
				 * Aca filtramos todas las tareas-procesos para poder calcular la cantidad total de la duracion de la tarea para el tramite.
				 */

				Iterator tarProcIterator = auxTareaProcesoList.iterator();
				while (tarProcIterator.hasNext()) {
					TareaProceso tareaProceso = (TareaProceso) tarProcIterator.next();

					Tarea tarea = workflowService.getTareaService().leerTarea(tareaProceso.getIdTarea());
					duracionTarea = new Long(duracionTarea.intValue() + tarea.getDuracion().intValue());
					log.info("Total horas tarea: " + duracionTarea);
				}

				Calendar auxCalendar = Fecha.calcularFechaFin(new Timestamp(Calendar.getInstance().getTime().getTime()), duracionTarea);
				tramite.setFechaFin(new Timestamp(auxCalendar.getTime().getTime()));
				tramite.setOperadorSup(operadorService.getOperadorService().leerOperador(proceso.getSupervisorDef().getCodigo()));
				tramite.setOperador(Session.getOperador());
				tramite.setEstado(workflowService.getEstadoService().leerEstado(new Long(1)));
				tramite.setTimestamp(tramite.getFechaInicio());
				workflowService.getTramiteService().grabarTramite(tramite);

				List atributosRestantes = new ArrayList();
				try {
					StringBuffer mensaje = new StringBuffer();
					atributosRestantes = workflowService.getProcesoAtributoService().listarProcesoAtributo(filtroAtribRestantes);
					Iterator iter = atributosRestantes.iterator();
					while (iter.hasNext()) {
						ProcesoAtributo atributo = (ProcesoAtributo) iter.next();
						AtributoValor atributoValor = new AtributoValor();
						atributoValor.setProcesoAtributo(atributo);
						// tramite.getAtributoValor().add(atributoValor);
						atributoValor.setTramite(tramite);

						try {
							atributoValor.setValor(atributo.getValorDefecto());
							if (!atributo.getLocal() && atributo.getValor()) {
								atributoValor.setValor(workflowService.getProcesoAtributoService().sqlValor(atributo, tramite.getIdTramite()));
							} else {
								atributoValor.setValor(atributo.getValorDefecto());
							}
						} catch (ProcesoAtributoException e) {
							mensaje.append("\n Error al leer el valor del atributo \"" + atributo.getNombre() + "\".");
							e.printStackTrace();
						}
						try {
							workflowService.getAtributoValorService().grabarOActualizarAtributoValor(atributoValor);
						} catch (AtributoValorException e) {
							mensaje.append("\n Error al intentar grabar el valor del atributo \"" + atributo.getNombre() + "\".");
							e.printStackTrace();
						}
					}

					// Se impactan en la base los parametros que toman su valor con Script SQL.
					this.ImpactarAtributo(this.proceso, tramite.getIdTramite());

					popup.setPopup(popup.ICONO_OK,
							"El tramite a sido iniciado con exito. \n El numero de tramite asignado es: " + tramite.getIdTramite()
									+ mensaje.toString());
				} catch (ProcesoAtributoException e) {
					popup.setPopup(popup.ICONO_OK,
							"El tramite a sido iniciado con exito. \n El numero de tramite asignado es: " + tramite.getIdTramite()
									+ "\n Ocurrio un error al intentar grabar el listado de atributos.");
					e.printStackTrace();
				}

			} catch (TareaException e) {
				popup.setPopup(popup.ICONO_FALLA, "Fallo al iniciar el tramite.");
				e.printStackTrace();
			} catch (OperadorException e) {
				popup.setPopup(popup.ICONO_FALLA, "Fallo al iniciar el tramite.");
				e.printStackTrace();
			} catch (TramiteException e) {
				popup.setPopup(popup.ICONO_FALLA, "Fallo al iniciar el tramite.");
				e.printStackTrace();
			} catch (EstadoException e) {
				popup.setPopup(popup.ICONO_FALLA, "Fallo al iniciar el tramite.");
				e.printStackTrace();
			}
		}
		return null;
	}


	public void ImpactarAtributo(Proceso proceso, Long idTramite)
	{
		Filtro filtro = new Filtro(IniParametro.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
		filtro.agregarCampoOperValor(IniParametro.BUSCA_VALOR, Filtro.IGUAL, "'S'");
		/* @I5547 */filtro.agregarOrderBy(IniParametro.ID_INI_PARAMETRO);
		/* @F5547 */try {
			List iniparametroList = workflowService.getIniParametroService().listarIniParametro(filtro);

			if (!iniparametroList.isEmpty()) {
				Iterator parametroIter = iniparametroList.iterator();
				while (parametroIter.hasNext()) {
					IniParametro parametro = (IniParametro) parametroIter.next();
					ProcesoAtributo atributo = workflowService.getProcesoAtributoService().leerProcesoAtributo(parametro.getIdProcAtributo());
					workflowService.getProcesoAtributoService().sqlGrabar(atributo, idTramite);
				}
			}
		} catch (IniParametroException e) {
			popup.setPopup(popup.ICONO_OK, "El tramite a sido iniciado con exito. \n El numero de tramite asignado es: " + tramite.getIdTramite()
					+ "\n Ocurrio un error al intentar recuperar parametros.");
			e.printStackTrace();
		} catch (ProcesoAtributoException e) {
			popup.setPopup(popup.ICONO_OK, "El tramite a sido iniciado con exito. \n El numero de tramite asignado es: " + tramite.getIdTramite()
					+ "\n Ocurrio un error al intentar recuperar parametros.");
			e.printStackTrace();
		}

	}


	public String irANuevoTramite() {
		HtmlCommandJSCookMenu command = new HtmlCommandJSCookMenu();
		command.setValue(label);
		return inicializar(new ActionEvent(command));
	}


	public String irAEscritorio() {
		EscritorioBean bean = (EscritorioBean) Session.getBean("EscritorioBean");
		return bean.inicializar();
	}


	public String irALInicio() {
		return "inicio";
	}


	/*
	 * este metodo arma el detalle historico y luego lo devuelve para guradarlo en la base.
	 */
	private void armarDetalleTramiteHistorico() {
		log.info("Ejecutando --> armarDetalleTramiteHistorico()");
		detalleTramiteHis = new DetalleTramiteHis();
		detalleTramiteHis.setFechaInicio(detalleTramite.getFechaInicio());
		detalleTramiteHis.setFechaFin(detalleTramite.getFechaFin());
		detalleTramiteHis.setProgreso(detalleTramite.getProgreso());
		detalleTramiteHis.setComentario(detalleTramite.getComentario());
		detalleTramiteHis.setEstado(detalleTramite.getEstado());
		detalleTramiteHis.setTareaProceso(detalleTramite.getTareaProceso());
		detalleTramiteHis.setTimestamp(detalleTramite.getTimestamp());
		detalleTramiteHis.setOperador(Session.getOperador());
		detalleTramite.getDetalleTramiteHis().add(detalleTramiteHis);

	}


	private void armarTramiteParametro() {
		log.info("Ejecutando --> armarTramiteParametro()");
		Iterator tramWrapIterator = parametroWrapperList.iterator();
		while (tramWrapIterator.hasNext()) {
			TramiteWrapper wrapper = (TramiteWrapper) tramWrapIterator.next();
			tramiteParametro = new TramiteParametro();
			tramiteParametro.setIdIniParametro(wrapper.getParametro().getIdIniParametro());
			tramiteParametro.setValor(wrapper.devolverValor());
			tramite.getTramitesParam().add(tramiteParametro);
		}

		tramWrapIterator = parametroWrapperListDos.iterator();
		while (tramWrapIterator.hasNext()) {
			TramiteWrapper wrapper = (TramiteWrapper) tramWrapIterator.next();
			if (!wrapper.isCompletaPanel()) {
				tramiteParametro = new TramiteParametro();
				tramiteParametro.setIdIniParametro(wrapper.getParametro().getIdIniParametro());
				tramiteParametro.setValor(wrapper.devolverValor());
				tramite.getTramitesParam().add(tramiteParametro);
			}
		}
	}


	/*
	 * este metodo se utiliza para armar el atributo valor.
	 */
	private void armarValor() {
		log.info("Ejecutando --> armarValor()");
		filtroAtribRestantes = new Filtro(ProcesoAtributo.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
		Iterator tramWrapIterator = parametroWrapperList.iterator();
		while (tramWrapIterator.hasNext()) {
			TramiteWrapper wrapper = (TramiteWrapper) tramWrapIterator.next();
			AtributoValor atributoValor = new AtributoValor();
			try {
				atributoValor.setProcesoAtributo(workflowService.getProcesoAtributoService().leerProcesoAtributo(
						wrapper.getParametro().getIdProcAtributo()));
				if (wrapper.getParametro().isBuscaValor())
				{
					atributoValor.setValor(workflowService.getProcesoAtributoService().sqlValor(wrapper.getAtributo(), tramite.getIdTramite()));
				} else
				{
					atributoValor.setValor(wrapper.devolverValor());
				}
				tramite.getAtributoValor().add(atributoValor);
				filtroAtribRestantes.agregarCampoOperValor(ProcesoAtributo.ID_PROCESO_ATRIBUTO, Filtro.DISTINTO, atributoValor.getProcesoAtributo()
						.getIdProcesoAtributo());
			} catch (ProcesoAtributoException e) {
				e.printStackTrace();
			}
		}

		tramWrapIterator = parametroWrapperListDos.iterator();
		while (tramWrapIterator.hasNext()) {
			TramiteWrapper wrapper = (TramiteWrapper) tramWrapIterator.next();
			AtributoValor atributoValor = new AtributoValor();
			try {
				if (!wrapper.isCompletaPanel()) {
					atributoValor.setProcesoAtributo(workflowService.getProcesoAtributoService().leerProcesoAtributo(
							wrapper.getParametro().getIdProcAtributo()));
					atributoValor.setValor(wrapper.devolverValor());
					tramite.getAtributoValor().add(atributoValor);
					filtroAtribRestantes.agregarCampoOperValor(ProcesoAtributo.ID_PROCESO_ATRIBUTO, Filtro.DISTINTO, atributoValor
							.getProcesoAtributo().getIdProcesoAtributo());
				}
			} catch (ProcesoAtributoException e) {
				e.printStackTrace();
			}
		}
	}


	/*
	 * este metodo se utiliaza para armar el detalle de tramite y grabarlo.
	 */
	private void armarDetalleTramite() {
		log.info("Ejecutando --> armarDetalleTramite()");
		try {
			TareaProceso tareaProceso = (TareaProceso) tareaProcesoList.get(0);
			Tarea tarea = workflowService.getTareaService().leerTarea(tareaProceso.getIdTarea());
			detalleTramite.setTramite(new Tramite());
			detalleTramite.setFechaInicio(new Timestamp(Calendar.getInstance().getTime().getTime()));
			Calendar fechaFin = Fecha.calcularFechaFin(new Timestamp(Calendar.getInstance().getTime().getTime()), new Long(tarea.getDuracion()
					.longValue()));
			detalleTramite.setFechaFin(new Timestamp(fechaFin.getTime().getTime()));
			detalleTramite.setEstado(workflowService.getEstadoService().leerEstado(new Long(1)));
			detalleTramite.setTareaProceso(tareaProceso);
			detalleTramite.setTimestamp(detalleTramite.getFechaInicio());
			detalleTramite.setProgreso(new Integer(0));
			tramite.getDetalleTramite().add(detalleTramite);

		} catch (EstadoException e) {
			e.printStackTrace();
		} catch (TareaException e) {
			e.printStackTrace();
		}
	}


	public String inicializar() {
		// TODO Auto-generated method stub
		return null;
	}
}
