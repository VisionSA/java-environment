package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;
import com.bizitglobal.tarjetafiel.general.service.MonedaService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes"})
public class MonedaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(MonedaBean.class);
	private Moneda moneda;
	private String nombreFiltro;
	private Long idMonedaHidden;
	private String idMoneda;

	// definicion de un list del objeto base
	private List monedaList;
	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;
	private String descMonedaInic;


	public MonedaBean() {
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


	public Moneda getMoneda() {
		return moneda;
	}


	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}


	public Long getIdMonedaHidden() {
		return idMonedaHidden;
	}


	public void setIdMonedaHidden(Long idMonedaHidden) {
		this.idMonedaHidden = idMonedaHidden;
	}


	public List getMonedaList() {
		return monedaList;
	}


	public void setMonedaList(List object) {
		this.monedaList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE MONEDA
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
		return "amMoneda";
	}


	private void cargarItems() {
	}


	public String editarMoneda() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Moneda";
		try {
			moneda = generalService.getMonedaService().leerMoneda(idMonedaHidden);
			descMonedaInic = moneda.getDescripcion();
			result = "amMoneda";
		} catch (MonedaException e1) {
			error.agregar("Ocurrio un error al intentar editar el moneda");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarMoneda.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el moneda");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarMoneda.jsf");
		}
		return result;
	}


	public String eliminarMoneda() {
		try {
			generalService.getMonedaService().borrarMoneda(idMonedaHidden);
			monedaList.remove(new Moneda(idMonedaHidden));
		} catch (MonedaException e1) {
			error.agregar("Imposible borrar el moneda. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el moneda");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarMoneda.jsf");
		return null;
	}


	public String grabar() {
		try {
			moneda.setDescripcion(moneda.getDescripcion().trim());
			if (validar()) {
				// Inicio los servis que voy a usar
				MonedaService monedaService = generalService.getMonedaService();
				if (alta) {
					// Grabo el nuevo objeto
					monedaService.grabarMoneda(moneda);
				} else {
					if (!(descMonedaInic.equals(moneda.getDescripcion())))
						monedaService.actualizarMoneda(moneda);
				}
				popup.setPopup(popup.ICONO_OK, "La Moneda ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (MonedaDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Moneda.");
			e1.printStackTrace();
		} catch (MonedaException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Moneda.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Moneda.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Moneda";
		popup.borrar();

		idMoneda = "";

		moneda = new Moneda();
		monedaList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (moneda.getSigno() == null || moneda.getSigno().equals(""))
			error.agregar(Error.AMMONEDA_SIGNO_REQUERIDA);
		try {
			List unaMoneda = generalService.getMonedaService().getMonedas(new Filtro("descripcion", Filtro.LIKEXS, moneda.getDescripcion().trim()));
			if (alta) {
				if (!unaMoneda.isEmpty())
					error.agregar(Error.AMMONEDA_MONEDA_REPETIDA);
			}

			else {
				if (!(descMonedaInic.compareTo(moneda.getDescripcion()) == 0))
				{
					if (!unaMoneda.isEmpty())
						error.agregar(Error.AMFORMAPAGO_FORMAPAGO_REPETIDA);
				}
			}

		} catch (MonedaException e) {
			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoMoneda() {
		return inicializar();
	}


	public String irAModificarMoneda() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Moneda";
		return null;
	}


	public String irAListarMoneda() {
		borrar();
		tituloCorto = "Listado de Monedas";
		cargarItems();
		Session.redirect("/tarjetafiel/general/listarMoneda.jsf");
		return "";
	}


	public String listarMoneda() {
		monedaList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idMoneda != null && !idMoneda.equals(""))
				filtro.agregarCampoOperValor("idMoneda", Filtro.IGUAL, new Long(idMoneda));
			if (moneda.getDescripcion() != null && !moneda.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, moneda.getDescripcion());
			if (moneda.getSigno() != null && !moneda.getSigno().equals(""))
				filtro.agregarCampoOperValor("signo", Filtro.LIKE, moneda.getSigno());

			monedaList = generalService.getMonedaService().getMonedas(filtro);
			Iterator iter = monedaList.iterator();
			while (iter.hasNext())
			{
				Moneda element = (Moneda) iter.next();
			}
			idMoneda = "";
			moneda.setDescripcion("");
			moneda.setSigno("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarMoneda.jsf");
		return null;
	}


	public String getIdMoneda() {
		return idMoneda;
	}


	public void setIdMoneda(String idMoneda) {
		this.idMoneda = idMoneda;
	}
}
