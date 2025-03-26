package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.CentroCostosDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.CentroCostosException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.CentroCostos;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;

import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.tarjetafiel.contabilidad.service.CentroCostosService;


public class CentroCostosBean extends BaseBean {
	private static final Logger log = Logger.getLogger(CentroCostosBean.class);
	private CentroCostos centroCostos;
	private String descripcionFiltro;
	private Long idCentroCostosHidden;
	private String idCentroCostos;

	// definicion de un list del objeto base
	private List centroCostosList;

	private String descCentroCostosInicial = null;


	public CentroCostosBean() {
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


	public CentroCostos getCentroCostos() {
		return centroCostos;
	}


	public void setCentroCostos(CentroCostos centroCostos) {
		this.centroCostos = centroCostos;
	}


	public Long getIdCentroCostosHidden() {
		return idCentroCostosHidden;
	}


	public void setIdCentroCostosHidden(Long idCentroCostosHidden) {
		this.idCentroCostosHidden = idCentroCostosHidden;
	}


	public List getCentroCostosList() {
		return centroCostosList;
	}


	public void setCentroCostosList(List object) {
		this.centroCostosList = object;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CentroCostos
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amCentroCostos";
	}


	public String editarCentroCostos() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Centro de Costos";
		try {
			centroCostos = contabilidadService.getCentroCostosService().leerCentroCostos(idCentroCostosHidden);
			descCentroCostosInicial = centroCostos.getDescripcion();
			result = "amCentroCostos";
		} catch (CentroCostosException e1) {
			error.agregar("Ocurrio un error al intentar editar el Centro de Costos");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/contabilidad/listarCentroCostos.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el Centro de Costos");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/contabilidad/listarCentroCostos.jsf");
		}
		return result;
	}


	public String eliminarCentroCostos() {
		try {
			contabilidadService.getCentroCostosService().borrarCentroCostos(idCentroCostosHidden);
			centroCostosList.remove(new CentroCostos(idCentroCostosHidden));
		} catch (CentroCostosException e1) {
			error.agregar("Imposible borrar el CentroCostos. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el CentroCostos");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/contabilidad/listarCentroCostos.jsf");
		return null;
	}


	public String grabar() {
		try {
			centroCostos.setDescripcion(centroCostos.getDescripcion().trim());
			if (validar()) {
				// Inicio los servis que voy a usar
				CentroCostosService centroCostosService = contabilidadService.getCentroCostosService();
				if (alta) {
					// Grabo el nuevo objeto
					centroCostosService.grabarCentroCostos(centroCostos);
				} else {
					if (!(descCentroCostosInicial.equals(centroCostos.getDescripcion())))
						centroCostosService.actualizarCentroCostos(centroCostos);
				}
				popup.setPopup(popup.ICONO_OK, "El Centro de Costos ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (CentroCostosDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del Centro de Costos.");
			e1.printStackTrace();
		} catch (CentroCostosException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del Centro de Costos.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del Centro de Costos.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Centro de Costos";
		popup.borrar();

		idCentroCostos = "";

		centroCostos = new CentroCostos();
		centroCostosList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (centroCostos.getDescripcion() == null || centroCostos.getDescripcion().equals(""))
			error.agregar(Error.CONT_CENTROCOSTOS_DESCRIPCION_REQUERIDA);
		try {
			List unCentroCostos = contabilidadService.getCentroCostosService().getCentroCostos(
					new Filtro("descripcion", Filtro.LIKEXS, centroCostos.getDescripcion().trim()));
			if (alta) {
				if (!unCentroCostos.isEmpty())
					error.agregar(Error.CONT_CENTROCOSTOS_REPETIDO);

			}
			else {
				if (!(descCentroCostosInicial.compareTo(centroCostos.getDescripcion()) == 0))
				{
					if (!unCentroCostos.isEmpty())
						error.agregar(Error.CONT_CENTROCOSTOS_REPETIDO);
				}

			}
		} catch (CentroCostosException e) {
			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoCentroCostos() {
		return inicializar();
	}


	public String irAModificarCentroCostos() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Centro de Costos";
		return null;
	}


	public String irAListarCentroCostos() {
		borrar();
		tituloCorto = "Listado de Centro de Costos";
		Session.redirect("/tarjetafiel/contabilidad/listarCentroCostos.jsf");
		return "";
	}


	public String listarCentroCostos() {
		centroCostosList = new ArrayList();
		try {
			Filtro filtro = new Filtro();
			if (idCentroCostos != null && !idCentroCostos.equals(""))
				filtro.agregarCampoOperValor("idCentroCostos", Filtro.IGUAL, new Long(idCentroCostos));
			if (centroCostos.getDescripcion() != null && !centroCostos.getDescripcion().equals("")) {
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, centroCostos.getDescripcion());
			}

			centroCostosList = contabilidadService.getCentroCostosService().getCentroCostos(filtro);
			Iterator iter = centroCostosList.iterator();
			while (iter.hasNext())
			{
				CentroCostos element = (CentroCostos) iter.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/contabilidad/listarCentroCostos.jsf");
		return null;
	}


	public String getIdCentroCostos() {
		return idCentroCostos;
	}


	public void setIdCentroCostos(String idCentroCostos) {
		this.idCentroCostos = idCentroCostos;
	}
}
