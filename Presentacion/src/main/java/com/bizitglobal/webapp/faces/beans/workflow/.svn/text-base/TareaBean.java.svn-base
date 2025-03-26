package com.bizitglobal.webapp.faces.beans.workflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.workflow.exception.FormExternoException;
import com.bizitglobal.workflow.exception.TareaException;
import com.bizitglobal.workflow.exception.TipoTareaException;
import com.bizitglobal.workflow.negocio.Tarea;
import com.bizitglobal.workflow.negocio.TipoTarea;
import com.bizitglobal.workflow.service.TareaService;


@SuppressWarnings({"rawtypes","unchecked"})
public class TareaBean extends BaseBean {
	
	private static final Logger log = Logger.getLogger(TareaBean.class);
	private Tarea tarea;
	private TipoTarea tipoTarea;
	
	// Listas para la presentacion(HtmlSelectItems).
	private List tipoTareas;
	private List tipoFormExt;
	private List tareas;
	private List tTareaList;

	// Longo id para las listas desplegables Doc. Comp y Form. Ext.
	private Long tipoDocCompSeleccionado;
	private Long tipoFormExtSeleccionado;

	// Id para capturar el id de la tare seleccionada para eliminar o modificar.
	private Long idTareaHidden;

	// Nombre de Titulos para la presentacion.
	private String tituloLargo = "Tarjeta Fiel - Administracion de tramites";
	private String tituloCorto = "Alta de tareas";

	// String para capturar el titulo para poder realizar el filtrado de las tareas.
	private String tituloFiltro;

	HtmlSelectOneMenu tipoTareaHtml = new HtmlSelectOneMenu();
	private boolean automatica = false;


	public TareaBean() {
		super();

		try {
			tTareaList = workflowService.getTipoTareaService().listarTipoTarea(new Filtro());
		} catch (TipoTareaException e) {
			e.printStackTrace();
		}

		tipoTareas = TareaUtil.cargarListaTipoTareas(tTareaList);

		idTareaHidden = new Long(0);
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


	public TipoTarea getTipoTarea() {
		return getTarea().getTipoTarea();
	}


	public void setTipoTarea(TipoTarea tipoTarea) {
		getTarea().setTipoTarea(tipoTarea);
	}


	public List getTipoTareas() {
		return tipoTareas;
	}


	public void setTipoTareas(List tipoTareas) {
		this.tipoTareas = tipoTareas;
	}


	public Long getTipoTareaSeleccionado() {
		return getTarea().getTipoTarea().getIdTipoTarea();
	}


	public void setTipoTareaSeleccionado(Long tipoTareaSeleccionado) {
		getTarea().getTipoTarea().setIdTipoTarea(tipoTareaSeleccionado);
	}


	public Tarea getTarea() {
		return tarea;
	}


	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}


	public Long getTipoDocCompSeleccionado() {
		return tarea.getDocContractual().getIdFormExterno();
	}


	public void setTipoDocCompSeleccionado(Long tipoDocCompSeleccionado) {
		if (tipoDocCompSeleccionado.equals(new Long(0))) {
			tarea.getDocContractual().setIdFormExterno(null);
		} else {
			tarea.getDocContractual().setIdFormExterno(tipoDocCompSeleccionado);
		}
	}


	public Long getTipoFormExtSeleccionado() {
		return getTarea().getFormExterno().getIdFormExterno();
	}


	public void setTipoFormExtSeleccionado(Long tipoFormExtSeleccionado) {
		if (tipoFormExtSeleccionado.equals(new Long(0))) {
			tarea.getFormExterno().setIdFormExterno(null);
		} else {
			tarea.getFormExterno().setIdFormExterno(tipoFormExtSeleccionado);
		}

	}


	public List getTipoFormExt() {
		return tipoFormExt;
	}


	public void setTipoFormExt(List tipoFormExt) {
		this.tipoFormExt = tipoFormExt;
	}


	public List getTareas() {
		return tareas;
	}


	public void setTareas(List tareas) {
		this.tareas = tareas;
	}


	public String getTituloFiltro() {
		return tituloFiltro;
	}


	public void setTituloFiltro(String tituloFiltro) {
		this.tituloFiltro = tituloFiltro;
	}


	public Long getIdTareaHidden() {
		return idTareaHidden;
	}


	public void setIdTareaHidden(Long idTareaHidden) {
		this.idTareaHidden = idTareaHidden;
	}


	public boolean isAutomatica() {
		return automatica;
	}


	public void setAutomatica(boolean automatica) {
		this.automatica = automatica;
	}


	public HtmlSelectOneMenu getTipoTareaHtml() {
		return tipoTareaHtml;
	}


	public void setTipoTareaHtml(HtmlSelectOneMenu tipoTareaHtml) {
		this.tipoTareaHtml = tipoTareaHtml;
	}


	public String irANuevaTarea() {
		popup.borrar();
		return inicializar();
	}


	public String irAModificarTarea() {
		popup.borrar();
		alta = false;
		return "amTareas";
	}


	public String irAListarTarea() {

		return inicializarListarTarea();
	}


	public void borrar() {
		log.info("Ejecutando--> borrar()");

		super.borrarBaseBean();

		error.borrar();

		popup.borrar();

		setTarea(new Tarea());
		setTipoTarea(new TipoTarea());
		setTareas(new ArrayList());
		setTipoDocCompSeleccionado(new Long(0));
		setTipoFormExtSeleccionado(new Long(0));
		alta = true;
		automatica = false;
		tipoFormExt = TareaUtil.caragarListaFormulario(workflowService.getFormExternoService());
	}


	public String inicializar() {
		log.info("Ejecutando--> inicializar()");
		borrar();
		return "amTareas";
	}


	public String inicializarListarTarea() {
		borrar();
		Session.redirect("/workflow/tareas/listarTareas.jsf");
		return null;
	}


	public void cambioTipoTarea(ValueChangeEvent event) {
		Long valorSeleccionado = new Long(tipoTareaHtml.getValue().toString());
		if (valorSeleccionado.equals(new Long(2)))
			automatica = true;
		else
			automatica = false;
	}


	public boolean validar() {

		error.borrar();

		if (Validador.esNuloVacio(getTarea().getTitulo())) {
			error.agregar(Error.PROCESO_TITULO_REQUERIDO);
		}
		if (getTarea().getTipoTarea().getIdTipoTarea().equals(new Long(0))) {
			error.agregar(Error.TIPO_TAREA_REQUERIDA);
		}
		if (Validador.esNuloVacio(getTarea().getComentario())) {
			error.agregar(Error.COMENTARIO_REQUERIDO);
		}
		if (Validador.esNulo(getTarea().getDuracion())
				|| getTarea().getDuracion().equals(new Long(0))) {
			error.agregar(Error.DURACION_DE_TAREA_REQUERIDA);
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public void grabarTareaListener(ActionEvent event) {
		log.info("Grabando la tarea--> " + tarea);
		try {
			if (validar()) {
				TareaService tareaService = workflowService.getTareaService();
				if (alta) {
					tareaService.grabarTarea(tarea);
				} else {
					tareaService.actualizarTarea(tarea);
				}
				log.info("Grabanda tarea ->");
				popup.setPopup(popup.ICONO_OK,
						"La tarea ha sido almacenada exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session
						.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (TareaException e2) {
			log.info("No se pudo grabar la tarea.");
			popup.setPopup(popup.ICONO_FALLA,
					"Falla el alta del tipo de tarea.");
			e2.printStackTrace();
		} catch (Exception e3) {
			log.info("No se pudo grabar la tarea, por error fatal.");
			popup.setPopup(popup.ICONO_FALLA,
					"Falla el alta del tipo de tarea.");
			e3.printStackTrace();
		}
	}


	public String filtarTareas() {
		log.info("Ejecutando filtrar Tareas -->");

		try {
			Filtro filtro = new Filtro();
			if (!Validador.esNuloVacio(tituloFiltro)) {
				filtro.agregarCampoOperValor(Tarea.TITULO, Filtro.LIKE, tituloFiltro);
			}
			List aux = workflowService.getTareaService().listarTarea(filtro);

			if (!aux.isEmpty()) {
				tareas = new ArrayList();
				Iterator auxTarea = aux.iterator();
				while (auxTarea.hasNext()) {
					Tarea tar = (Tarea) auxTarea.next();
					tar.setTipoTarea(TareaUtil.getTipoTarea(tTareaList, tar.getTipoTarea().getIdTipoTarea()));
					tareas.add(tar);
				}
			} else {
				log.info("La lista de tareas esta vacia");
			}
		} catch (TareaException e) {
			// TODO Auto-generated catch block
			log.info("Error.");
			e.printStackTrace();
		}

		Session.redirect("/workflow/tareas/listarTareas.jsf");
		return null;
	}


	public String editarTarea() {

		log.info("Entrando a editar tarea");
		String result = "amTareas";
		borrar();
		tituloLargo = "Tarjeta Fiel - Administracion de tramites";
		tituloCorto = "Modificación de tarea";
		alta = false;

		try {
			tarea = workflowService.getTareaService().leerTarea(idTareaHidden);
			if (!tarea.getDocContractual().getIdFormExterno().equals(new Long(0))) {
				tarea.setDocContractual(workflowService.getFormExternoService().leerFormExterno(tarea.getDocContractual().getIdFormExterno()));
			}
			if (!tarea.getFormExterno().getIdFormExterno().equals(new Long(0))) {
				tarea.setFormExterno(workflowService.getFormExternoService().leerFormExterno(tarea.getFormExterno().getIdFormExterno()));
			}
			if (tarea.getTipoTarea().getIdTipoTarea().equals(new Long(2))) {
				automatica = true;
			}
			if (Session.getBean("ScrollBean") != null) {
				ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
				bean.setHiddenScrollY(new Integer(0));
			}

		} catch (TareaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormExternoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}


	public String eliminarTarea() {

		log.info("<-- Eliminando Tarea -->");
		error.borrar();
		popup.borrar();
		// borrar();

		Long tareaId = new Long(idTareaHidden.longValue());
		// Tarea tarea = new Tarea(tareaId);
		try {
			workflowService.getTareaService().borrarTarea(tareaId);
			// tareas.remove(tarea);
		} catch (TareaException e) {

			log.info("No se pudo eliminar.");
			popup.setPopup(popup.ICONO_FALLA,
					"Falla la baja de tarea.");
			e.printStackTrace();
		}

		return filtarTareas();

	}
}
