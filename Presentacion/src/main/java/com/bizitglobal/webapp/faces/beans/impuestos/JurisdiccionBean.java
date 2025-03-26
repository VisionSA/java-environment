package com.bizitglobal.webapp.faces.beans.impuestos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.impuestos.service.JurisdiccionService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


public class JurisdiccionBean extends BaseBean {
	private static final Logger log = Logger.getLogger(JurisdiccionBean.class);
	private Jurisdiccion jurisdiccion;
	private String nombreFiltro;
	private Long idJurisdiccionHidden;
	private String idJurisdiccion;

	// definicion de un list del objeto base
	private List jurisdiccionList;

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;
	private String descJurInicial;


	public JurisdiccionBean() {
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


	public Jurisdiccion getJurisdiccion() {
		return jurisdiccion;
	}


	public void setJurisdiccion(Jurisdiccion jurisdiccion) {
		this.jurisdiccion = jurisdiccion;
	}


	public Long getIdJurisdiccionHidden() {
		return idJurisdiccionHidden;
	}


	public void setIdJurisdiccionHidden(Long idJurisdiccionHidden) {
		this.idJurisdiccionHidden = idJurisdiccionHidden;
	}


	public List getJurisdiccionList() {
		return jurisdiccionList;
	}


	public void setJurisdiccionList(List object) {
		this.jurisdiccionList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE JURISDICCION
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
		return "amJurisdiccion";
	}


	private void cargarItems() {
	}


	public String editarJurisdiccion() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Jurisdicción";
		try {
			jurisdiccion = impuestoService.getJurisdiccionService().leerJurisdiccion(idJurisdiccionHidden);
			descJurInicial = jurisdiccion.getDescripcion();

			result = "amJurisdiccion";
		} catch (JurisdiccionException e1) {
			error.agregar("Ocurrio un error al intentar editar el jurisdiccion");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarJurisdiccion.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el jurisdiccion");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/impuestos/listarJurisdiccion.jsf");
		}
		return result;
	}


	public String eliminarJurisdiccion() {
		try {
			impuestoService.getJurisdiccionService().borrarJurisdiccion(idJurisdiccionHidden);
			jurisdiccionList.remove(new Jurisdiccion(idJurisdiccionHidden, null));
		} catch (JurisdiccionException e1) {
			error.agregar("Imposible borrar el jurisdiccion. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el jurisdiccion");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarJurisdiccion.jsf");
		return null;
	}


	public String grabar() {
		try {
			jurisdiccion.setDescripcion(jurisdiccion.getDescripcion().trim());
			if (validar()) {
				// Inicio los servis que voy a usar
				JurisdiccionService jurisdiccionService = impuestoService.getJurisdiccionService();
				if (alta) {
					// Grabo el nuevo objeto
					jurisdiccionService.grabarJurisdiccion(jurisdiccion);
					jurisdiccion.setDescripcion("");
				}
				else {
					if (!(descJurInicial.equals(jurisdiccion.getDescripcion())))
						jurisdiccionService.actualizarJurisdiccion(jurisdiccion);

				}
				popup.setPopup(popup.ICONO_OK, "La Jurisdicción ha sido almacenada exitosamente.");
			}
			else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (JurisdiccionDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Jurisdicción.");
			e1.printStackTrace();
		} catch (JurisdiccionException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Jurisdicción.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Jurisdicción.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Jurisdicción";
		popup.borrar();

		jurisdiccion = new Jurisdiccion();
		jurisdiccionList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (jurisdiccion.getDescripcion() == null || jurisdiccion.getDescripcion().equals(""))
			error.agregar(Error.AMJURISDICCION_DESCRIPCION_REQUERIDA);

		try {
			List unaJurisdiccion = impuestoService.getJurisdiccionService().getJurisdiccion(
					new Filtro("descripcion", Filtro.LIKEXS, jurisdiccion.getDescripcion()));
			if (alta) {
				if (!unaJurisdiccion.isEmpty())
					error.agregar(Error.AMJURISDICCION_JURISDICCION_REPETIDA);
			}
			else {
				if (!(descJurInicial.compareTo(jurisdiccion.getDescripcion()) == 0))
				{
					if (!unaJurisdiccion.isEmpty())
						error.agregar(Error.AMJURISDICCION_JURISDICCION_REPETIDA);
				}
			}

		} catch (JurisdiccionException e) {
			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoJurisdiccion() {
		return inicializar();
	}


	public String irAModificarJurisdiccion() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Jurisdicción";
		return null;
	}


	public String irAListarJurisdiccion() {
		borrar();
		tituloCorto = "Listado de Jurisdicción";
		cargarItems();
		Session.redirect("/tarjetafiel/impuestos/listarJurisdiccion.jsf");
		return "";
	}


	public String listarJurisdiccion() {
		jurisdiccionList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idJurisdiccion != null && !idJurisdiccion.equals(""))
				filtro.agregarCampoOperValor("idJurisdiccion", Filtro.IGUAL, new Long(idJurisdiccion));

			if (jurisdiccion.getDescripcion() != null && !jurisdiccion.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, jurisdiccion.getDescripcion());

			jurisdiccionList = impuestoService.getJurisdiccionService().getJurisdiccion(filtro);
			Iterator iter = jurisdiccionList.iterator();
			while (iter.hasNext())
			{
				Jurisdiccion element = (Jurisdiccion) iter.next();
			}
			idJurisdiccion = "";
			jurisdiccion.setDescripcion("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/listarJurisdiccion.jsf");
		return null;
	}


	public String getIdJurisdiccion() {
		return idJurisdiccion;
	}


	public void setIdJurisdiccion(String idJurisdiccion) {
		this.idJurisdiccion = idJurisdiccion;
	}
}
