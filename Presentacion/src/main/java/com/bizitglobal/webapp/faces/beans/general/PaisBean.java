package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.PaisDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.PaisException;
import com.bizitglobal.tarjetafiel.general.negocio.Pais;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;

import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.tarjetafiel.general.service.PaisService;



@SuppressWarnings({"rawtypes"})
public class PaisBean extends BaseBean {
	private static final Logger log = Logger.getLogger(PaisBean.class);
	private Pais pais;
	private String nombreFiltro;
	private Long idPaisHidden;
	private String idPais;

	// definicion de un list del objeto base
	private List paisList;

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;

	private String descPaisInicial = null;


	public PaisBean() {
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


	public Pais getPais() {
		return pais;
	}


	public void setPais(Pais pais) {
		this.pais = pais;
	}


	public Long getIdPaisHidden() {
		return idPaisHidden;
	}


	public void setIdPaisHidden(Long idPaisHidden) {
		this.idPaisHidden = idPaisHidden;
	}


	public List getPaisList() {
		return paisList;
	}


	public void setPaisList(List object) {
		this.paisList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE PAIS
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
		return "amPais";
	}


	private void cargarItems() {
	}


	public String editarPais() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar País";
		try {
			pais = generalService.getPaisService().leerPais(idPaisHidden);
			descPaisInicial = pais.getNombre();
			result = "amPais";
		} catch (PaisException e1) {
			error.agregar("Ocurrio un error al intentar editar el pais");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarPais.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el pais");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarPais.jsf");
		}
		return result;
	}


	public String eliminarPais() {
		try {
			generalService.getPaisService().borrarPais(idPaisHidden);
			paisList.remove(new Pais(idPaisHidden));
		} catch (PaisException e1) {
			error.agregar("Imposible borrar el pais. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el pais");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarPais.jsf");
		return null;
	}


	public String grabar() {
		try {
			pais.setNombre(pais.getNombre().trim());
			if (validar()) {
				// Inicio los servis que voy a usar
				PaisService paisService = generalService.getPaisService();
				if (alta) {
					// Grabo el nuevo objeto
					paisService.grabarPais(pais);
				} else {
					if (!(descPaisInicial.equals(pais.getNombre())))
						paisService.actualizarPais(pais);
				}
				popup.setPopup(popup.ICONO_OK, "El País ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (PaisDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del País.");
			e1.printStackTrace();
		} catch (PaisException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del País.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del País.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de País";
		popup.borrar();

		idPais = "";

		pais = new Pais();
		paisList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (pais.getNombre() == null || pais.getNombre().equals(""))
			error.agregar(Error.AMPAIS_NOMBRE_REQUERIDA);
		try {
			List unPais = generalService.getPaisService().getPais(new Filtro("nombre", Filtro.LIKEXS, pais.getNombre().trim()));
			if (alta) {
				if (!unPais.isEmpty())
					error.agregar(Error.AMPAIS_PAIS_REPETIDO);

			}
			else {
				if (!(descPaisInicial.compareTo(pais.getNombre()) == 0))
				{
					if (!unPais.isEmpty())
						error.agregar(Error.AMPAIS_PAIS_REPETIDO);
				}

			}
		} catch (PaisException e) {
			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoPais() {
		return inicializar();
	}


	public String irAModificarPais() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar País";
		descPaisInicial = pais.getNombre();
		return null;
	}


	public String irAListarPais() {
		borrar();
		tituloCorto = "Listado de Paises";
		cargarItems();
		Session.redirect("/tarjetafiel/general/listarPais.jsf");
		return "";
	}


	public String listarPais() {
		paisList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (idPais != null && !idPais.equals(""))
				filtro.agregarCampoOperValor("idPais", Filtro.IGUAL, new Long(idPais));
			if (pais.getNombre() != null && !pais.getNombre().equals(""))
				filtro.agregarCampoOperValor("nombre", Filtro.LIKE, pais.getNombre());

			paisList = generalService.getPaisService().getPais(filtro);
			Iterator iter = paisList.iterator();
			while (iter.hasNext())
			{
				Pais element = (Pais) iter.next();
			}
			idPais = "";
			pais.setNombre("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarPais.jsf");
		return null;
	}


	public String getIdPais() {
		return idPais;
	}


	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}
}
