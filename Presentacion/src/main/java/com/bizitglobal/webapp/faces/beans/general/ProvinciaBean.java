package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.PaisException;
import com.bizitglobal.tarjetafiel.general.exception.ProvinciaDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.ProvinciaException;
import com.bizitglobal.tarjetafiel.general.negocio.Pais;
import com.bizitglobal.tarjetafiel.general.negocio.Provincia;
import com.bizitglobal.tarjetafiel.general.service.ProvinciaService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class ProvinciaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ProvinciaBean.class);
	private Provincia provincia;
	private String nombreFiltro;
	private Long idProvinciaHidden;
	private String idProvincia;

	// definicion de un list del objeto base
	private List provinciaList;
	private List unaProvincia;
	// Listas para la presentacion(HtmlSelectItems).
	private List paisList = new ArrayList();
	private List paisItems = new ArrayList();

	// Objetos Relacionados.
	private Long idPaisSeleccionada;

	private String focoHidden;


	public ProvinciaBean() {
		super();
		borrar();
		try {
			try {
				paisList = generalService.getPaisService().getPais(new Filtro());
			} catch (PaisException e1) {
				e1.printStackTrace();
			}
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


	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}


	public Long getIdProvinciaHidden() {
		return idProvinciaHidden;
	}


	public void setIdProvinciaHidden(Long idProvinciaHidden) {
		this.idProvinciaHidden = idProvinciaHidden;
	}


	public List getPaisItems() {
		return paisItems;
	}


	public void setPaisItems(List paisItems) {
		this.paisItems = paisItems;
	}


	public Long getIdPaisSeleccionada() {
		return idPaisSeleccionada;
	}


	public void setIdPaisSeleccionada(Long idPaisSeleccionada) {
		this.idPaisSeleccionada = idPaisSeleccionada;
	}


	public List getProvinciaList() {
		return provinciaList;
	}


	public void setProvinciaList(List object) {
		this.provinciaList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE PROVINCIA
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
		return "amProvincia";
	}


	private void cargarItems() {

		if (paisItems.size() != paisList.size()) {
			paisItems = new ArrayList();
			paisItems.add(new SelectItem(new Long(0), "Seleccionar País"));
			paisItems.addAll(Util.cargarSelectItem(paisList));
		}
	}


	public String editarProvincia() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Provincia";
		try {
			provincia = generalService.getProvinciaService().leerProvincia(idProvinciaHidden);

			idPaisSeleccionada = provincia.getPais().getIdPais();

			result = "amProvincia";
		} catch (ProvinciaException e1) {
			error.agregar("Ocurrio un error al intentar editar el provincia");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarProvincia.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el provincia");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarProvincia.jsf");
		}
		return result;
	}


	public String eliminarProvincia() {
		try {
			generalService.getProvinciaService().borrarProvincia(idProvinciaHidden);
			provinciaList.remove(new Provincia(idProvinciaHidden));
		} catch (ProvinciaException e1) {
			error.agregar("Imposible borrar el provincia. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el provincia");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarProvincia.jsf");
		return null;
	}


	public String grabar() {
		try {
			provincia.setNombre(provincia.getNombre().trim());
			ProvinciaService provinciaService = generalService.getProvinciaService();
			provincia.setPais((Pais) Util.buscarElemento(paisList, new Pais(idPaisSeleccionada)));
			if (validar()) {
				if (alta) {
					provinciaService.grabarProvincia(provincia);
				}
				else {
					provinciaService.actualizarProvincia(provincia);
				}
				// idPaisSeleccionada = new Long(0);
				popup.setPopup(popup.ICONO_OK, "La Provincia ha sido almacenada exitosamente.");
			}
			else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ProvinciaDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta de la Provincia.");
			e1.printStackTrace();
		} catch (ProvinciaException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta de la Provincia.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta de la Provincia.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Provincia";
		popup.borrar();

		idProvincia = "";
		try {
			unaProvincia = generalService.getProvinciaService().getProvincia(new Filtro());
			Iterator provs = unaProvincia.iterator();
			while (provs.hasNext()) {
				Provincia pro = (Provincia) provs.next();
				pro.getPais();
			}
		} catch (ProvinciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		provincia = new Provincia();
		provinciaList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (provincia.getNombre() == null || provincia.getNombre().equals(""))
			error.agregar(Error.AMPROVINCIA_NOMBRE_PROVINCIA_REQUERIDA);

		if (idPaisSeleccionada == null || idPaisSeleccionada.equals(new Long(0)))
			error.agregar(Error.AMPROVINCIA_PAIS_REQUERIDA);

		if (alta) {
			if (!unaProvincia.isEmpty()) {
				Iterator iterator = unaProvincia.iterator();
				while (iterator.hasNext()) {
					Provincia element = (Provincia) iterator.next();
					if (element.getNombre().compareTo(provincia.getNombre()) == 0) {
						if (element.getPais().getIdPais().equals(idPaisSeleccionada) && !element.getIdProvincia().equals(idProvincia))
							error.agregar(Error.AMPROVINCIA_PROVINCIA_REPETIDA);
					}
				}
			}
		} else {
			if (!unaProvincia.isEmpty()) {
				Iterator iterator = unaProvincia.iterator();
				while (iterator.hasNext()) {
					Provincia element = (Provincia) iterator.next();
					if (element.getNombre().compareTo(provincia.getNombre()) == 0 && !element.getIdProvincia().equals(idProvinciaHidden)) {
						if (element.getPais().getIdPais().equals(idPaisSeleccionada))
							error.agregar(Error.AMPROVINCIA_PROVINCIA_REPETIDA);
					}
				}
			}
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoProvincia() {
		idPaisSeleccionada = new Long(0);
		return inicializar();
	}


	public String irAModificarProvincia() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Provincia";

		return null;
	}


	public String irAListarProvincia() {
		borrar();
		tituloCorto = "Listado de Provincias";
		cargarItems();
		Session.redirect("/tarjetafiel/general/listarProvincia.jsf");
		return "";
	}


	public String listarProvincia() {
		provinciaList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (idProvincia != null && !idProvincia.equals(""))
				filtro.agregarCampoOperValor("idProvincia", Filtro.IGUAL, new Long(idProvincia));
			if (idPaisSeleccionada != null && !idPaisSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("pais.idPais", Filtro.IGUAL, idPaisSeleccionada);
			if (provincia.getNombre() != null && !provincia.getNombre().equals(""))
				filtro.agregarCampoOperValor("nombre", Filtro.LIKE, provincia.getNombre());

			provinciaList = generalService.getProvinciaService().getProvincia(filtro);
			Iterator iter = provinciaList.iterator();
			while (iter.hasNext())
			{
				Provincia element = (Provincia) iter.next();
				element.getPais().getLabel();
			}
			// idProvincia = "";
			// idPaisSeleccionada = new Long(0);
			// provincia.setNombre("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarProvincia.jsf");
		return null;
	}


	public String getIdProvincia() {
		return idProvincia;
	}


	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}
}
