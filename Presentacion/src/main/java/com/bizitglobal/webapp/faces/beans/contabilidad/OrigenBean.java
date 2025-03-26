package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.OrigenDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.OrigenException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Origen;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.tarjetafiel.contabilidad.service.OrigenService;


@SuppressWarnings({"rawtypes","unused"})
public class OrigenBean extends BaseBean {
	private static final Logger log = Logger.getLogger(OrigenBean.class);
	private Origen origen;
	private String descripcionFiltro;
	private Long idOrigenHidden;
	private String idOrigen;

	// definicion de un list del objeto base
	private List origenList;

	private String descOrigenInicial = null;


	public OrigenBean() {
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


	public Origen getOrigen() {
		return origen;
	}


	public void setOrigen(Origen origen) {
		this.origen = origen;
	}


	public Long getIdOrigenHidden() {
		return idOrigenHidden;
	}


	public void setIdOrigenHidden(Long idOrigenHidden) {
		this.idOrigenHidden = idOrigenHidden;
	}


	public List getOrigenList() {
		return origenList;
	}


	public void setOrigenList(List object) {
		this.origenList = object;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE Origen
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amOrigen";
	}


	public String editarOrigen() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Origen";
		try {
			origen = contabilidadService.getOrigenService().leerOrigen(idOrigenHidden);
			descOrigenInicial = origen.getDescripcion();
			result = "amOrigen";
		} catch (OrigenException e1) {
			error.agregar("Ocurrio un error al intentar editar el Origen");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/contabilidad/listarOrigen.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el Origen");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/contabilidad/listarOrigen.jsf");
		}
		return result;
	}


	public String eliminarOrigen() {
		try {
			contabilidadService.getOrigenService().borrarOrigen(idOrigenHidden);
			origenList.remove(new Origen(idOrigenHidden));
		} catch (OrigenException e1) {
			error.agregar("Imposible borrar el Origen. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el Origen");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/contabilidad/listarOrigen.jsf");
		return null;
	}


	public String grabar() {
		try {
			origen.setDescripcion(origen.getDescripcion().trim());
			if (validar()) {
				// Inicio los servis que voy a usar
				OrigenService origenService = contabilidadService.getOrigenService();
				if (alta) {
					// Grabo el nuevo objeto
					origenService.grabarOrigen(origen);
				} else {
					if (!(descOrigenInicial.equals(origen.getDescripcion())))
						origenService.actualizarOrigen(origen);
				}
				popup.setPopup(popup.ICONO_OK, "El Origen ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (OrigenDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del Origen.");
			e1.printStackTrace();
		} catch (OrigenException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del Origen.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del Origen.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Origen";
		popup.borrar();

		idOrigen = "";

		origen = new Origen();
		origenList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (origen.getDescripcion() == null || origen.getDescripcion().equals(""))
			error.agregar(Error.CONT_ORIGEN_DESCRIPCION_REQUERIDA);
		try {
			List unOrigen = contabilidadService.getOrigenService()
					.getOrigen(new Filtro("descripcion", Filtro.LIKEXS, origen.getDescripcion().trim()));
			if (alta) {
				if (!unOrigen.isEmpty())
					error.agregar(Error.CONT_ORIGEN_REPETIDO);

			}
			else {
				if (!(descOrigenInicial.compareTo(origen.getDescripcion()) == 0))
				{
					if (!unOrigen.isEmpty())
						error.agregar(Error.CONT_ORIGEN_REPETIDO);
				}

			}
		} catch (OrigenException e) {
			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoOrigen() {
		return inicializar();
	}


	public String irAModificarOrigen() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Origen";
		return null;
	}


	public String irAListarOrigen() {
		borrar();
		tituloCorto = "Listado de Origenes";
		Session.redirect("/tarjetafiel/contabilidad/listarOrigen.jsf");
		return "";
	}


	public String listarOrigen() {
		origenList = new ArrayList();
		try {
			Filtro filtro = new Filtro();
			if (idOrigen != null && !idOrigen.equals(""))
				filtro.agregarCampoOperValor("idOrigen", Filtro.IGUAL, new Long(idOrigen));
			if (origen.getDescripcion() != null && !origen.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, origen.getDescripcion());

			origenList = contabilidadService.getOrigenService().getOrigen(filtro);
			Iterator iter = origenList.iterator();
			while (iter.hasNext())
			{
				Origen element = (Origen) iter.next();
			}
			idOrigen = "";
			origen.setDescripcion("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/contabilidad/listarOrigen.jsf");
		return null;
	}


	public String getIdOrigen() {
		return idOrigen;
	}


	public void setIdOrigen(String idOrigen) {
		this.idOrigen = idOrigen;
	}
}
