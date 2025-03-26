package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.ArchivoDebitoConfException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ArchivoDebitoConf;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes"})
public class ArchivoDebitoConfBean extends BaseBean {

	private ArchivoDebitoConf archivoDebitoConf;
	private Long idArchivoHidden;
	private List archivosList;
	private String idArchivoFiltro;
	private String descripcionFiltro;


	public void borrar() {
		error.borrar();
		popup.borrar();

	}


	public String inicializar() {
		borrarBaseBean();
		borrar();
		tituloLargo = "TARJETA - FIEL";
		tituloCorto = "Archivo de Configuración de Debitos";
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		this.archivoDebitoConf = new ArchivoDebitoConf();
		return "amArchivoDebitoConf";
	}


	public String editarArchivo() {
		String result = null;
		borrar();
		alta = false;
		tituloCorto = "Modificar Código Comercio";
		try {
			archivoDebitoConf = transaccionesService.getArchivoDebitoConfService().leerArchivoDebitoConf(idArchivoHidden);
			result = "amArchivoDebitoConf";
		} catch (ArchivoDebitoConfException e1) {
			error.agregar("Ocurrió un error al intentar editar el Archivo de Configuración");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarArchivoDebitoConf.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrió un error al intentar editar el Archivo de Configuración");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarArchivoDebitoConf.jsf");
		}
		return result;
	}


	public String eliminarArchivo() {
		try {
			transaccionesService.getArchivoDebitoConfService().borrarArchivoDebitoConf(idArchivoHidden);
			archivosList.remove(new ArchivoDebitoConf(idArchivoHidden));
		} catch (ArchivoDebitoConfException e1) {
			error.agregar("Imposible borrar el Archivo de Configuración. Posee elementos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrió un error al intentar borrar el Archivo de Configuración");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarArchivoDebitoConf.jsf");
		return null;
	}


	public boolean validar() {
		Collection errores = archivoDebitoConf.validarDatosCompletos();
		if (errores.size() > 0) {
			Iterator iterator = errores.iterator();
			while (iterator.hasNext()) {
				error.agregar((String) iterator.next());
			}
			return false;
		} else {
			return true;
		}
	}


	public ArchivoDebitoConf getArchivoDebitoConf() {
		return archivoDebitoConf;
	}


	public String grabar() {
		if (validar()) {
			try {
				if (archivoDebitoConf.getIdArchivoDebitoConf() != null) {
					transaccionesService.getArchivoDebitoConfService().actualizarArchivoDebitoConf(archivoDebitoConf);
				} else {
					transaccionesService.getArchivoDebitoConfService().grabarArchivoDebitoConf(archivoDebitoConf);
				}
				popup.setPopup(popup.ICONO_OK, "El Archivo de configuración de Débitos ha sido almacenado exitosamente.");
			} catch (ArchivoDebitoConfException e) {
				popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Archivo de Configuración de Débitos.");
				e.printStackTrace();
			}
		}
		return null;
	}


	public String irAModificarArchivoDebitoConf() {
		alta = false;
		borrar();
		popup.borrar();
		tituloCorto = "Modificar Archivo Débito Configuración";
		return null;
	}


	public String irANuevoArchivoDebitoConf() {
		return inicializar();
	}


	public String irAListarArchivoDebitoConf() {
		borrar();
		tituloCorto = "Listado de Archivo Débito Configuración";
		idArchivoFiltro = "";
		descripcionFiltro = "";
		Session.redirect("/tarjetafiel/transacciones/listarArchivoDebitoConf.jsf");
		return "";
	}


	public String listarArchivos() {
		archivosList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (descripcionFiltro != null && descripcionFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, descripcionFiltro);
			}
			if (idArchivoFiltro != null && idArchivoFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("idArchivoDebitoConf", Filtro.IGUAL, new Long(idArchivoFiltro));
			}
			archivosList = transaccionesService.getArchivoDebitoConfService().getArchivoDebitoConf(filtro);
			Iterator iter = archivosList.iterator();
			while (iter.hasNext())
			{
				ArchivoDebitoConf element = (ArchivoDebitoConf) iter.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarArchivoDebitoConf.jsf");
		return null;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public Long getIdArchivoHidden() {
		return idArchivoHidden;
	}


	public void setIdArchivoHidden(Long idArchivoHidden) {
		this.idArchivoHidden = idArchivoHidden;
	}


	public List getArchivosList() {
		return archivosList;
	}


	public void setArchivosList(List archivosList) {
		this.archivosList = archivosList;
	}


	public String getDescripcionFiltro() {
		return descripcionFiltro;
	}


	public void setDescripcionFiltro(String descripcionFiltro) {
		this.descripcionFiltro = descripcionFiltro;
	}


	public String getIdArchivoFiltro() {
		return idArchivoFiltro;
	}


	public void setIdArchivoFiltro(String idArchivoFiltro) {
		this.idArchivoFiltro = idArchivoFiltro;
	}

}
