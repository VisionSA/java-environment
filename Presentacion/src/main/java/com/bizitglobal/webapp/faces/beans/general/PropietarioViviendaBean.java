package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.PropViviendaDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.PropViviendaException;
import com.bizitglobal.tarjetafiel.general.negocio.PropietarioVivienda;
import com.bizitglobal.tarjetafiel.general.service.PropietarioViviendaService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Error;



@SuppressWarnings({"rawtypes"})
public class PropietarioViviendaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(PropietarioViviendaBean.class);
	private PropietarioVivienda propietarioVivienda;
	private String nombreFiltro;
	private Long idPropViviendaHidden;
	private String idPropViv;

	// definicion de un list del objeto base
	private List propViviendaList;

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;

	private String descPropInicial;


	public PropietarioViviendaBean() {
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


	public Long getIdPropViviendaHidden() {
		return idPropViviendaHidden;
	}


	public void setIdPropViviendaHidden(Long idPropViviendaHidden) {
		this.idPropViviendaHidden = idPropViviendaHidden;
	}


	public List getPropViviendaList() {
		return propViviendaList;
	}


	public void setPropViviendaList(List object) {
		this.propViviendaList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE PROPVIVIENDA
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
		return "amPropVivienda";
	}


	private void cargarItems() {
	}


	public String editarPropVivienda() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Propietario Vivienda";
		try {
			propietarioVivienda = generalService.getPropietarioViviendaService().leerPropietarioVivienda(idPropViviendaHidden);
			descPropInicial = propietarioVivienda.getDescripcion();
			result = "amPropVivienda";
		} catch (PropViviendaException e1) {
			error.agregar("Ocurrio un error al intentar editar el propvivienda");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarPropVivienda.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el propvivienda");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarPropVivienda.jsf");
		}
		return result;
	}


	public String eliminarPropVivienda() {
		try {
			generalService.getPropietarioViviendaService().borrarPropietarioVivienda(idPropViviendaHidden);
			propViviendaList.remove(new PropietarioVivienda(idPropViviendaHidden));
		} catch (PropViviendaException e1) {
			error.agregar("Imposible borrar el propvivienda. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el propvivienda");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarPropVivienda.jsf");
		return null;
	}


	public String grabar() {
		try {
			propietarioVivienda.setDescripcion(propietarioVivienda.getDescripcion().trim());
			if (validar()) {
				PropietarioViviendaService propviviendaService = generalService.getPropietarioViviendaService();
				if (alta) {
					log.info("EL id es " + propietarioVivienda.getId());
					log.info("EL id es " + propietarioVivienda.getDescripcion());
					propietarioVivienda = new PropietarioVivienda();
					propietarioVivienda.setDescripcion("otros");
					log.info("EL id es " + propietarioVivienda.getId());
					log.info("EL id es " + propietarioVivienda.getDescripcion());

					propviviendaService.grabarPropietarioVivienda(propietarioVivienda);
				} else
				{
					if (!(descPropInicial.equals(propietarioVivienda.getDescripcion())))
						propviviendaService.actualizarPropietarioVivienda(propietarioVivienda);
				}
				popup.setPopup(popup.ICONO_OK, "El Propietario Vivienda ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (PropViviendaDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del propvivienda.");
			e1.printStackTrace();
		} catch (PropViviendaException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del propvivienda.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del Propietario Vivienda.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Propietario Vivienda";
		popup.borrar();

		idPropViv = "";

		propietarioVivienda = new PropietarioVivienda();

		propViviendaList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (propietarioVivienda.getDescripcion() == null || propietarioVivienda.getDescripcion().equals(""))
			error.agregar(Error.AMPROPIETARIOVIVIENDA_DESCRIPCION_REQUERIDA);
		try {
			List unPropietarioVivienda = generalService.getPropietarioViviendaService().getPropietarioVivienda(
					new Filtro("descripcion", Filtro.LIKEXS, propietarioVivienda.getDescripcion().trim()));
			if (alta) {
				if (!unPropietarioVivienda.isEmpty())
					error.agregar(Error.AMPROPIETARIOVIVIENDA_PROPIETARIOVIVIENDA_REPETIDO);
			}
			else {
				if (!(descPropInicial.compareTo(propietarioVivienda.getDescripcion()) == 0))
				{
					if (!unPropietarioVivienda.isEmpty())
						error.agregar(Error.AMPROPIETARIOVIVIENDA_PROPIETARIOVIVIENDA_REPETIDO);
				}
			}

		} catch (PropViviendaException e) {

			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoPropVivienda() {
		return inicializar();
	}


	public String irAModificarPropVivienda() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Propietario Vivienda";
		return null;
	}


	public String irAListarPropVivienda() {
		borrar();
		tituloCorto = "Listado de Propietarios Vivienda";
		cargarItems();
		Session.redirect("/tarjetafiel/general/listarPropVivienda.jsf");
		return "";
	}


	public String listarPropVivienda() {
		propViviendaList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idPropViv != null && !idPropViv.equals(""))
				filtro.agregarCampoOperValor("idPropVivienda", Filtro.IGUAL, idPropViv);
			if (propietarioVivienda.getDescripcion() != null && !propietarioVivienda.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, propietarioVivienda.getDescripcion());

			propViviendaList = generalService.getPropietarioViviendaService().getPropietarioVivienda(filtro);
			Iterator iter = propViviendaList.iterator();
			while (iter.hasNext())
			{
				PropietarioVivienda element = (PropietarioVivienda) iter.next();
			}
			idPropViv = "";
			propietarioVivienda.setDescripcion("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarPropVivienda.jsf");
		return null;
	}


	public String getIdPropViv() {
		return idPropViv;
	}


	public void setIdPropViv(String idPropViv) {
		this.idPropViv = idPropViv;
	}


	public PropietarioVivienda getPropietarioVivienda() {
		return propietarioVivienda;
	}


	public void setPropietarioVivienda(PropietarioVivienda propietarioVivienda) {
		this.propietarioVivienda = propietarioVivienda;
	}
}
