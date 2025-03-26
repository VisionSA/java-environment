package com.bizitglobal.webapp.faces.beans.general;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.NoLaborableDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.NoLaborableException;
import com.bizitglobal.tarjetafiel.general.negocio.NoLaborable;
import com.bizitglobal.tarjetafiel.general.service.NoLaborableService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Error;



@SuppressWarnings({"rawtypes"})
public class NoLaborableBean extends BaseBean {
	private static final Logger log = Logger.getLogger(NoLaborableBean.class);
	private NoLaborable nolaborable;
	private String nombreFiltro;
	private Long idNoLaborableHidden;
	private Date fecha;
	private boolean esFeriado;
	private boolean esProvincial;
	private boolean esNacional;
	private boolean esBancario;

	private String idNoLaboral;

	// definicion de un list del objeto base
	private List noLaborableList;

	// lista para reconocer si ya hay fechas no laborables, para no repetirlas
	private List diasNoLaborablesExistentes;

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;


	public NoLaborableBean() {
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


	public NoLaborable getNoLaborable() {
		return nolaborable;
	}


	public void setNoLaborable(NoLaborable nolaborable) {
		this.nolaborable = nolaborable;
	}


	public Long getIdNoLaborableHidden() {
		return idNoLaborableHidden;
	}


	public void setIdNoLaborableHidden(Long idNoLaborableHidden) {
		this.idNoLaborableHidden = idNoLaborableHidden;
	}


	public List getNoLaborableList() {
		return noLaborableList;
	}


	public void setNoLaborableList(List object) {
		this.noLaborableList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE NOLABORABLE
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
		return "amNoLaborable";
	}


	private void cargarItems() {
	}


	public String editarNoLaborable() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Día No Laborable";
		try {
			nolaborable = generalService.getNoLaborableService().leerNoLaborable(idNoLaborableHidden);
			fecha = new Date(nolaborable.getFecha().getTime());
			if (nolaborable.getEsFeriado().equals(new Character('S')))
				esFeriado = true;

			if (nolaborable.getEsFeriado().equals(new Character('S')))
				esFeriado = true;

			if (nolaborable.getEsBancario().equals(new Character('S')))
				esBancario = true;

			if (nolaborable.getEsProvincial().equals(new Character('S')))
				esProvincial = true;

			if (nolaborable.getEsNacional().equals(new Character('S')))
				esNacional = true;

			diasNoLaborablesExistentes = generalService.getNoLaborableService().getNoLaborable(new Filtro());
			result = "amNoLaborable";
		} catch (NoLaborableException e1) {
			error.agregar("Ocurrio un error al intentar editar el nolaborable");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarNoLaborable.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el nolaborable");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarNoLaborable.jsf");
		}
		return result;
	}


	public String eliminarNoLaborable() {
		try {
			generalService.getNoLaborableService().borrarNoLaborable(idNoLaborableHidden);
			noLaborableList.remove(new NoLaborable(idNoLaborableHidden));
		} catch (NoLaborableException e1) {
			error.agregar("Imposible borrar el nolaborable. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el nolaborable");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarNoLaborable.jsf");
		return null;
	}


	public String grabar() {
		try {
			NoLaborableService nolaborableService = generalService.getNoLaborableService();
			if (fecha != null && !fecha.equals(new Date(0))) {
				nolaborable.setFecha(new Timestamp(fecha.getTime()));
			}
			if (esFeriado) {
				nolaborable.setEsFeriado(new Character('S'));
			}
			else {
				nolaborable.setEsFeriado(new Character('N'));
			}
			if (esBancario) {
				nolaborable.setEsBancario(new Character('S'));
			}
			else {
				nolaborable.setEsBancario(new Character('N'));
			}
			if (esNacional) {
				nolaborable.setEsNacional(new Character('S'));
			}
			else {
				nolaborable.setEsNacional(new Character('N'));
			}
			if (esProvincial) {
				nolaborable.setEsProvincial(new Character('S'));
			}
			else {
				nolaborable.setEsProvincial(new Character('N'));
			}

			if (validar()) {
				if (alta) {
					nolaborableService.grabarNoLaborable(nolaborable);
				}
				else {

					nolaborableService.actualizarNoLaborable(nolaborable);

				}
				fecha = null;
				esFeriado = false;
				esBancario = false;
				esProvincial = false;
				esNacional = false;

				popup.setPopup(popup.ICONO_OK, "El Día No Laborable ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (NoLaborableDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del Día No Laborable.");
			e1.printStackTrace();
		} catch (NoLaborableException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del Día No Laborable.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del Día No Laborable.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta Día No Laborable";
		popup.borrar();

		idNoLaboral = "";
		try {
			diasNoLaborablesExistentes = generalService.getNoLaborableService().getNoLaborable(new Filtro());
			Iterator it = diasNoLaborablesExistentes.iterator();
			while (it.hasNext()) {
				NoLaborable noLab = (NoLaborable) it.next();
				noLab.getFecha();
			}
		} catch (NoLaborableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nolaborable = new NoLaborable();
		noLaborableList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (fecha == null || fecha.equals(new Date(0)))
			error.agregar(Error.AMNOLABORABLE_FECHA_REQUERIDA);

		if (alta) {
			try {
				List unDia = generalService.getNoLaborableService().getNoLaborable(
						new Filtro("fecha", Filtro.IGUAL, Filtro.getTO_DATE(new Timestamp(fecha.getTime()))));
				if (!unDia.isEmpty())
					error.agregar(Error.AMNOLABORABLE_NOLABORABLE_REPETIDO);
			} catch (NoLaborableException e) {
				e.printStackTrace();
			}
		} else {
			Iterator iterDias = diasNoLaborablesExistentes.iterator();
			while (iterDias.hasNext()) {
				NoLaborable noLab = (NoLaborable) iterDias.next();
				if (noLab.getIdNoLaborable() != idNoLaborableHidden) {
					if (noLab.getFecha().equals(nolaborable.getFecha())) {
						error.agregar(Error.AMNOLABORABLE_NOLABORABLE_REPETIDO);
						break;
					}
				}
			}
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoNoLaborable() {
		return inicializar();
	}


	public String irAModificarNoLaborable() {
		alta = false;
		popup.borrar();
		return null;
	}


	public String irAListarNoLaborable() {
		borrar();
		tituloCorto = "Listado Días No Laborables";
		cargarItems();
		Session.redirect("/tarjetafiel/general/listarNoLaborable.jsf");
		return "";
	}


	public String listarNoLaborable() {
		noLaborableList = new ArrayList();
		try {
			Filtro filtro = new Filtro();
			if (idNoLaboral != null && !idNoLaboral.equals(""))
				filtro.agregarCampoOperValor("idNoLaborable", Filtro.IGUAL, new Long(idNoLaboral));

			if (fecha != null && !fecha.equals(new Date(0)) && !fecha.toString().equals("DD/MM/YYYY"))
				filtro.agregarCampoOperValor("fecha", Filtro.IGUAL, Filtro.getTO_DATE(new Timestamp(fecha.getTime())));

			if (esFeriado)
				filtro.agregarCampoOperValor("esFeriado", Filtro.LIKEXS, new Character('S'));

			noLaborableList = generalService.getNoLaborableService().getNoLaborable(filtro);
			Iterator iter = noLaborableList.iterator();
			while (iter.hasNext())
			{
				NoLaborable element = (NoLaborable) iter.next();
			}
			idNoLaboral = "";
			fecha = null;
			esFeriado = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarNoLaborable.jsf");
		return null;
	}


	// public Date getFecha() {
	// return new Date(nolaborable.getFecha().getTime());
	// }
	// public void setFecha(Date fecha) {
	// nolaborable.setFecha(new Timestamp(fecha.getTime()));
	// }

	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public boolean isEsFeriado() {
		return esFeriado;
	}


	public void setEsFeriado(boolean esFeriado) {
		this.esFeriado = esFeriado;
	}


	public String getIdNoLaboral() {
		return idNoLaboral;
	}


	public void setIdNoLaboral(String idNoLaboral) {
		this.idNoLaboral = idNoLaboral;
	}


	public boolean isEsBancario() {
		return esBancario;
	}


	public void setEsBancario(boolean esBancario) {
		this.esBancario = esBancario;
	}


	public boolean isEsNacional() {
		return esNacional;
	}


	public void setEsNacional(boolean esNacional) {
		this.esNacional = esNacional;
	}


	public boolean isEsProvincial() {
		return esProvincial;
	}


	public void setEsProvincial(boolean esProvincial) {
		this.esProvincial = esProvincial;
	}


	public NoLaborable getNolaborable() {
		return nolaborable;
	}


	public void setNolaborable(NoLaborable nolaborable) {
		this.nolaborable = nolaborable;
	}
}
