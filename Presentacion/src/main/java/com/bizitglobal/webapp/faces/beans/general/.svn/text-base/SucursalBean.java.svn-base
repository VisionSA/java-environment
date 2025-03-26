package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.SucursalDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.SucursalFielException;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.general.service.SucursalFielService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Error;


@SuppressWarnings({"rawtypes"})
public class SucursalBean extends BaseBean {
	private static final Logger log = Logger.getLogger(SucursalBean.class);
	private SucursalFiel sucursal;
	private String nombreFiltro;
	private Long idSucursalHidden;
	private String idSucursal;

	// definicion de un list del objeto base
	private List sucursalList;

	private String focoHidden;

	private String descSucursalInicial;


	public SucursalBean() {
		super();
		borrar();

	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public SucursalFiel getSucursal() {
		return sucursal;
	}


	public void setSucursal(SucursalFiel sucursal) {
		this.sucursal = sucursal;
	}


	public Long getIdSucursalHidden() {
		return idSucursalHidden;
	}


	public void setIdSucursalHidden(Long idSucursalHidden) {
		this.idSucursalHidden = idSucursalHidden;
	}


	public List getSucursalList() {
		return sucursalList;
	}


	public void setSucursalList(List object) {
		this.sucursalList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE SUCURSAL
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amSucursal";
	}


	public String editarSucursal() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Sucursal";
		try {
			sucursal = generalService.getSucursalDao().buscarSucursalFiel(idSucursalHidden);
			sucursal.getDomicilio().getBarrio().getLocalidad().getPartido().getProvincia().getPais();

			descSucursalInicial = sucursal.getNombre();

			result = "amSucursal";
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el sucursal");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarSucursal.jsf");
		}
		return result;
	}


	public String eliminarSucursal() {
		try {
			generalService.getSucursalFielService().borrarSucursalFiel(idSucursalHidden);
			sucursalList.remove(new SucursalFiel(idSucursalHidden));
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el sucursal");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarSucursal.jsf");
		return null;
	}


	public String grabar() throws SucursalDuplicateException {
		log.info("domicilio: " + sucursal.getDomicilio().toString());
		try {
			sucursal.setNombre(sucursal.getNombre().trim());
			if (validar()) {
				// Inicio los servis que voy a usar
				SucursalFielService sucursalService = generalService.getSucursalFielService();
				if (alta) {
					sucursalService.grabarSucursalFiel(sucursal);
				}
				else {
					if (!(descSucursalInicial.equals(sucursal.getNombre())))
						sucursalService.actualizarSucursalFiel(sucursal);
				}
				popup.setPopup(popup.ICONO_OK, "El Sucursal ha sido almacenado exitosamente.");
			}
			else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta de la Sucursal.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Sucursal";
		popup.borrar();

		idSucursal = "";

		sucursal = new SucursalFiel();
		sucursal.setDomicilio(new Domicilio());
		sucursalList = new ArrayList();

	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (sucursal.getNombre() == null || sucursal.getNombre().equals(""))
			error.agregar(Error.AMSUCURSAL_NOMBRE_REQUERIDA);

		if (sucursal.getDomicilio() == null)
			error.agregar(Error.AMSUCURSAL_DOMICILIO_REQUERIDA);

		List unaSucursal;
		try {
			unaSucursal = generalService.getSucursalFielService().getSucursales(new Filtro("nombre", Filtro.LIKEXS, sucursal.getNombre().trim()));

			if (alta) {
				if (!unaSucursal.isEmpty())
					error.agregar(Error.AMSUCURSAL_SUCURSAL_REPETIDA);
			}
			else {
				if (!(descSucursalInicial.compareTo(sucursal.getNombre()) == 0))
				{
					if (!unaSucursal.isEmpty())
						error.agregar(Error.AMSUCURSAL_SUCURSAL_REPETIDA);
				}
			}
		} catch (SucursalFielException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoSucursal() {
		return inicializar();
	}


	public String irAModificarSucursal() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Sucursal";
		return null;
	}


	public String irAListarSucursal() {
		borrar();
		tituloCorto = "Listado de Sucursales";
		Session.redirect("/tarjetafiel/general/listarSucursal.jsf");
		return "";
	}


	public String listarSucursal() {
		sucursalList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idSucursal != null && !idSucursal.equals(""))
				filtro.agregarCampoOperValor("idSucursal", Filtro.IGUAL, new Long(idSucursal));

			if (sucursal.getNombre() != null && !sucursal.getNombre().equals(""))
				filtro.agregarCampoOperValor("nombre", Filtro.LIKE, sucursal.getNombre());

			sucursalList = generalService.getSucursalDao().listarTodos(filtro);
			Iterator iter = sucursalList.iterator();
			while (iter.hasNext())
			{
				SucursalFiel element = (SucursalFiel) iter.next();
				element.getDomicilio().toString();
			}

			idSucursal = "";
			sucursal.setNombre("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarSucursal.jsf");
		return null;
	}


	public String getIdSucursal() {
		return idSucursal;
	}


	public void setIdSucursal(String idSucursal) {
		this.idSucursal = idSucursal;
	}


	/*
	 * Este metodo se utiliza para agregar un domicilio al individuo.
	 */
	public String agregarDomicilioPopup() {
		log.info("Ir a agrega nuevo domicilio a la Sucursal!!!");

		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");

		bean.inicializar(DomicilioBean.SUCURSAL, sucursal.getDomicilio());

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");

		return null;
	}


	public String agregarDomicilioDesdePopup() {
		return null;
	}

	/*
	 * Este metodo se utiliza para poder editar un domicilio, se captura el id del domicilio q se desea editar, luego se busca dentro del array de lso
	 * domicilios, una vez q se encuentra se manda al bean de domicilios, donde se cargara y estara lsito para ser editado.
	 */
	// public String editarDomicilio() {
	// log.info("Ejecutando ==> editarDomicilio()");
	//
	// Long idDomicilio = new Long(Session.getRequestParameter("idDomiEdi"));
	// log.info("id a buscar: " + idDomicilio);
	//
	// DomicilioBean bean = (DomicilioBean)Session.getBean("DomicilioBean");
	// Domicilio aux = new Domicilio(SucursalUtil.buscarDomicilio(listDomicilio, idDomicilio));
	//
	// bean.inicializar(DomicilioBean.SUCURSAL, aux);
	//
	// String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	// path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
	// ejecutarJavaScript("popup('"+ path +"',750,400), 'titlebar=no';");
	// return null;
	// }
	//
	// /*
	// * Este metodo se utiliza para capturar la accion del boton de eliminar direccion.
	// * este toma el id del domicilio y luego se lo pasa por parametro junto con la lita de domicilios
	// * a un metodo dentro del util de individuo evaluacion, que se encarga de elimar el domicilio del array.
	// */
	// public String eliminarDomicilio() {
	// log.info(" Ejecutando ==> eliminarDomicilio()");
	// Long idDomicilio = new Long(Session.getRequestParameter("idDomicilio"));
	// log.info("Id Domicilio: " + idDomicilio);
	// listDomicilio = SucursalUtil.eliminarDomicilio(listDomicilio, idDomicilio);
	//
	// return null;
	// }
}
