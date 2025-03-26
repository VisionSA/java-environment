package com.bizitglobal.webapp.faces.beans.proveedores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.exception.GrupoDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.exception.GrupoException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Grupo;
import com.bizitglobal.tarjetafiel.proveedores.service.GrupoService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes"})
public class GrupoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(GrupoBean.class);
	private Grupo grupo;
	private String nombreFiltro;
	private Long idGrupoHidden;

	// definicion de un list del objeto base
	private List grupoList;

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;


	public GrupoBean() {
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


	public Grupo getGrupo() {
		return grupo;
	}


	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}


	public Long getIdGrupoHidden() {
		return idGrupoHidden;
	}


	public void setIdGrupoHidden(Long idGrupoHidden) {
		this.idGrupoHidden = idGrupoHidden;
	}


	public List getGrupoList() {
		return grupoList;
	}


	public void setGrupoList(List object) {
		this.grupoList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE GRUPO
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
		return "amGrupo";
	}


	private void cargarItems() {
	}


	public String editarGrupo() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar grupo";
		try {
			grupo = proveedoresService.getGrupoService().leerGrupo(idGrupoHidden);
			result = "amGrupo";
		} catch (GrupoException e1) {
			error.agregar("Ocurrio un error al intentar editar el grupo");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/Proveedor/listarGrupo.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el grupo");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/Proveedor/listarGrupo.jsf");
		}
		return result;
	}


	public String eliminarGrupo() {
		try {
			proveedoresService.getGrupoService().borrarGrupo(idGrupoHidden);
			grupoList.remove(new Grupo(idGrupoHidden, null));
		} catch (GrupoException e1) {
			error.agregar("Imposible borrar el grupo. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el grupo");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/Proveedor/listarGrupo.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				GrupoService grupoService = proveedoresService.getGrupoService();
				if (alta) {
					// Grabo el nuevo objeto
					grupoService.grabarGrupo(grupo);
				} else {
					grupoService.actualizarGrupo(grupo);
				}
				popup.setPopup(popup.ICONO_OK, "El grupo ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (GrupoDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del grupo.");
			e1.printStackTrace();
		} catch (GrupoException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del grupo.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del grupo.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de grupo";
		popup.borrar();

		grupo = new Grupo();
		grupoList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		// TODO: recordar definir las validaciones
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoGrupo() {
		return inicializar();
	}


	public String irAModificarGrupo() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar grupo";
		return null;
	}


	public String irAListarGrupo() {
		borrar();
		tituloCorto = "Listado de grupo";
		cargarItems();
		Session.redirect("/tarjetafiel/proveedor/listarGrupo.jsf");
		return "";
	}


	public String listarGrupo() {
		grupoList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			// filtro.agregarCampoOperValor("", Filtro.IGUAL, grupo.
			grupoList = proveedoresService.getGrupoService().getGrupos(filtro);
			Iterator iter = grupoList.iterator();
			while (iter.hasNext())
			{
				Grupo element = (Grupo) iter.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/proveedor/listarGrupo.jsf");
		return null;
	}
}
