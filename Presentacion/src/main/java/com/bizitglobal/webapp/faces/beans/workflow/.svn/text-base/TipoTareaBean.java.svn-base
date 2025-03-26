package com.bizitglobal.webapp.faces.beans.workflow;

import java.util.Map;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.workflow.exception.TipoTareaDuplicateException;
import com.bizitglobal.workflow.exception.TipoTareaException;
import com.bizitglobal.workflow.negocio.TipoTarea;
import com.bizitglobal.workflow.service.TipoTareaService;


@SuppressWarnings({"rawtypes"})
public class TipoTareaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(TipoTareaBean.class);

	private TipoTarea tipoTarea;

	private String descripcion;
	private String icono;

	private UploadedFile archivoImagen;

	// Nombre de Titulos para la presentacion.
	private String tituloLargo;
	private String tituloCorto;

	private boolean alta = true;


	public TipoTareaBean() {
		super();
		borrar();
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getIcono() {
		return icono;
	}


	public void setIcono(String icono) {
		this.icono = icono;
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


	public UploadedFile getArchivoImagen() {
		return archivoImagen;
	}


	public void setArchivoImagen(UploadedFile archivoImagen) {
		this.archivoImagen = archivoImagen;
	}


	// Metodos para el ABM
	public void borrar() {
		error.borrar();
		this.tipoTarea = null;
		this.descripcion = null;
		this.icono = null;
		this.archivoImagen = null;

		alta = true;

		this.tituloLargo = "Tarjeta Fiel - Administracion de tramites";
		this.tituloCorto = "Alta de tipos de tareas";

	}


	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amTipoTareas";
	}


	public void inicializarParametros(Map param) {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		descripcion = (String) param.get("cuenta");
	}


	public void grabarTipoTareaListener(ActionEvent event) {
		log.info("Grabando el Tipo de tarea");

		try {
			if (validar()) {
				TipoTareaService tipoTareaService = workflowService.getTipoTareaService();
				// icono = Archivo.grabarArchivo(archivoImagen);
				tipoTarea = armarTipoTarea();
				if (alta) {
					tipoTareaService.grabarTipoTarea(tipoTarea);
				} else {
					tipoTareaService.actualizarTipoTarea(tipoTarea);
				}
				log.info("Grabanda tipo de tarea ->" + tipoTarea);
				popup.setPopup(popup.ICONO_OK, "El tipo de tarea ha sido almacenada exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}

		} catch (TipoTareaDuplicateException e1) {
			log.info("No se pudo grabar el tipo de tarea");
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del tipo de tarea.");
			e1.printStackTrace();
		} catch (TipoTareaException e2) {
			log.info("No se pudo grabar el tipo de tarea");
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del tipo de tarea.");
			e2.printStackTrace();
		} catch (Exception e3) {
			log.info("No se pudo grabar el tipo de tarea");
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del tipo de tarea.");
			e3.printStackTrace();
		}

	}


	public String cancelar() {
		log.info("Cancelando el tipo de tarea");
		borrar();
		return null;
	}


	public boolean validar() {
		error.borrar();

		if (descripcion.equals("")) {
			error.agregar(Error.TIPO_TAREA_DESCRIPCION_REQUERIDA);
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoTipoTarea() {
		log.info("irANuevoTipoTarea()");
		return inicializar();
	}


	public String irAModificarTipoTarea() {
		log.info("irAModificarTipoTarea()");
		alta = false;
		popup.borrar();
		return null;
	}


	public String irAListarTipoTarea() {
		log.info("irAListarTipoTarea()");
		popup.borrar();
		return listarTipoTareas();
	}


	public String listarTipoTareas() {
		borrar();
		Session.redirect("/tarjetafiel/proveedores/comprobantes/listarComprobantes.jsf");
		tituloCorto = "Listado de tipos de tareas";
		return null;
	}


	private TipoTarea armarTipoTarea() {
		if (tipoTarea == null) {
			tipoTarea = new TipoTarea();
		}
		tipoTarea.setDescripcion(descripcion);
		tipoTarea.setIcono(icono);
		return tipoTarea;
	}
}
