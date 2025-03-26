package com.bizitglobal.webapp.faces.beans.impuestos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.AplicableDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.AplicableException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Aplicable;
import com.bizitglobal.tarjetafiel.impuestos.service.AplicableService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


public class AplicableBean extends BaseBean {
	private static final Logger log = Logger.getLogger(AplicableBean.class);
	private Aplicable aplicable;
	private String nombreFiltro;
	private Long idAplicableHidden;
	private String idAplicable;

	// definicion de un list del objeto base
	private List aplicableList;

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;


	public AplicableBean() {
		super();
		borrar();
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public Aplicable getAplicable() {
		return aplicable;
	}


	public void setAplicable(Aplicable aplicable) {
		this.aplicable = aplicable;
	}


	public Long getIdAplicableHidden() {
		return idAplicableHidden;
	}


	public void setIdAplicableHidden(Long idAplicableHidden) {
		this.idAplicableHidden = idAplicableHidden;
	}


	public List getAplicableList() {
		return aplicableList;
	}


	public void setAplicableList(List object) {
		this.aplicableList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE APLICABLE
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		cargarItems();
		return "amAplicable";
	}


	private void cargarItems() {
	}


	public String editarAplicable() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar aplicable";
		try {
			aplicable = impuestoService.getAplicableService().leerAplicable(idAplicableHidden);
			result = "amAplicable";
		} catch (AplicableException e1) {
			error.agregar("Ocurrio un error al intentar editar el aplicable");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarAplicable.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el aplicable");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarAplicable.jsf");
		}
		return result;
	}


	public String eliminarAplicable() {
		try {
			impuestoService.getAplicableService().borrarAplicable(idAplicableHidden);
			aplicableList.remove(new Aplicable(idAplicableHidden, null));
		} catch (AplicableException e1) {
			error.agregar("Imposible borrar el aplicable. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el aplicable");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarAplicable.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				AplicableService aplicableService = impuestoService.getAplicableService();
				if (alta) {
					// Grabo el nuevo objeto
					aplicableService.grabarAplicable(aplicable);
				} else {
					aplicableService.actualizarAplicable(aplicable);
				}
				popup.setPopup(popup.ICONO_OK, "Aplicable ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (AplicableDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de Aplicable.");
			e1.printStackTrace();
		} catch (AplicableException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de Aplicable.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de Aplicable.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Aplicable";
		popup.borrar();

		aplicable = new Aplicable();
		aplicableList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (aplicable.getDescripcion() == null || aplicable.getDescripcion().equals(""))
			error.agregar(Error.AMAPLICABLE_DESCRIPCION_REQUERIDO);

		try {
			List unAplicable = impuestoService.getAplicableService().getAplicables(
					new Filtro("descripcion", Filtro.LIKEXS, aplicable.getDescripcion()));
			if (!unAplicable.isEmpty())
				error.agregar(Error.AMAPLICABLE_APLICABLE_REPETIDA);
		} catch (AplicableException e) {
			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoAplicable() {
		return inicializar();
	}


	public String irAModificarAplicable() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Aplicable";
		return null;
	}


	public String irAListarAplicable() {
		borrar();
		tituloCorto = "Listado de Aplicable";
		cargarItems();
		Session.redirect("/tarjetafiel/impuestos/listarAplicable.jsf");
		return "";
	}


	public String listarAplicable() {
		aplicableList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idAplicable != null && !idAplicable.equals(""))
				filtro.agregarCampoOperValor("idAplicable", Filtro.IGUAL, new Long(idAplicable));
			if (aplicable.getDescripcion() != null && !aplicable.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, aplicable.getDescripcion());

			aplicableList = impuestoService.getAplicableService().getAplicables(filtro);
			Iterator iter = aplicableList.iterator();
			while (iter.hasNext())
			{
				Aplicable element = (Aplicable) iter.next();
			}
			idAplicable = "";
			aplicable.setDescripcion("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarAplicable.jsf");
		return null;
	}


	public String getIdAplicable() {
		return idAplicable;
	}


	public void setIdAplicable(String idAplicable) {
		this.idAplicable = idAplicable;
	}
}
