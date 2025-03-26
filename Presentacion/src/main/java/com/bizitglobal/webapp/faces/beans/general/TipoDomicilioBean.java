package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.TipoDomicilioDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.TipoDomicilioException;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDomicilio;
import com.bizitglobal.tarjetafiel.general.service.TipoDomicilioService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class TipoDomicilioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(TipoDomicilioBean.class);
	private TipoDomicilio tipodomicilio;
	private String nombreFiltro;
	private Long idTipoDomicilioHidden;
	private String idTipoDomicilio;

	// definicion de un list del objeto base
	private List tipoDomicilioList;

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;
	private String descTipoDomInicial;


	public TipoDomicilioBean() {
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


	public TipoDomicilio getTipoDomicilio() {
		return tipodomicilio;
	}


	public void setTipoDomicilio(TipoDomicilio tipodomicilio) {
		this.tipodomicilio = tipodomicilio;
	}


	public Long getIdTipoDomicilioHidden() {
		return idTipoDomicilioHidden;
	}


	public void setIdTipoDomicilioHidden(Long idTipoDomicilioHidden) {
		this.idTipoDomicilioHidden = idTipoDomicilioHidden;
	}


	public List getTipoDomicilioList() {
		return tipoDomicilioList;
	}


	public void setTipoDomicilioList(List object) {
		this.tipoDomicilioList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE TIPODOMICILIO
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
		return "amTipoDomicilio";
	}


	private void cargarItems() {
	}


	public String editarTipoDomicilio() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Tipo Domicilio";
		try {
			tipodomicilio = generalService.getTipoDomicilioService().leerTipoDomicilio(idTipoDomicilioHidden);
			descTipoDomInicial = tipodomicilio.getDescripcion();
			result = "amTipoDomicilio";
		} catch (TipoDomicilioException e1) {
			error.agregar("Ocurrio un error al intentar editar el tipodomicilio");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/General/listarTipoDomicilio.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el tipodomicilio");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarTipoDomicilio.jsf");
		}
		return result;
	}


	public String eliminarTipoDomicilio() {
		try {
			generalService.getTipoDomicilioService().borrarTipoDomicilio(idTipoDomicilioHidden);
			tipoDomicilioList.remove(new TipoDomicilio(idTipoDomicilioHidden));
		} catch (TipoDomicilioException e1) {
			error.agregar("Imposible borrar el tipodomicilio. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el tipodomicilio");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarTipoDomicilio.jsf");
		return null;
	}


	public String grabar() {
		try {
			tipodomicilio.setDescripcion(tipodomicilio.getDescripcion().trim());
			if (validar()) {
				// Inicio los servis que voy a usar
				TipoDomicilioService tipodomicilioService = generalService.getTipoDomicilioService();
				if (alta) {
					// Grabo el nuevo objeto
					tipodomicilioService.grabarTipoDomicilio(tipodomicilio);
				} else {

					if (!(descTipoDomInicial.equals(tipodomicilio.getDescripcion())))
						tipodomicilioService.actualizarTipoDomicilio(tipodomicilio);
				}
				popup.setPopup(popup.ICONO_OK, "El Tipo de Domicilio ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (TipoDomicilioDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Tipo de Domicilio.");
			e1.printStackTrace();
		} catch (TipoDomicilioException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Tipo de Domicilio.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Tipo de Domicilio.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Tipo Domicilio";
		popup.borrar();

		idTipoDomicilio = "";

		tipodomicilio = new TipoDomicilio();
		tipoDomicilioList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (tipodomicilio.getDescripcion() == null || tipodomicilio.getDescripcion().equals(""))
			error.agregar(Error.AMTIPODOMICILIO_DESCRIPCION_REQUERIDA);
		try {
			List unTipoDomicilio = generalService.getTipoDomicilioService().getTipoDomicilio(
					new Filtro("descripcion", Filtro.LIKEXS, tipodomicilio.getDescripcion().trim()));
			if (alta) {
				if (!unTipoDomicilio.isEmpty())
					error.agregar(Error.AMTIPODOMICILIO_TIPODOMICILIO_REPETIDO);
			} else {
				if (!(descTipoDomInicial.compareTo(tipodomicilio.getDescripcion()) == 0))
				{
					if (!unTipoDomicilio.isEmpty())
						error.agregar(Error.AMTIPODOMICILIO_TIPODOMICILIO_REPETIDO);
				}
			}

		} catch (TipoDomicilioException e) {
			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoTipoDomicilio() {
		return inicializar();
	}


	public String irAModificarTipoDomicilio() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Tipo Domicilio";
		return null;
	}


	public String irAListarTipoDomicilio() {
		borrar();
		tituloCorto = "Listado de Tipos de Domicilios";
		cargarItems();
		Session.redirect("/tarjetafiel/general/listarTipoDomicilio.jsf");
		return "";
	}


	public String listarTipoDomicilio() {
		tipoDomicilioList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (idTipoDomicilio != null && !idTipoDomicilio.equals(""))
				filtro.agregarCampoOperValor("idTipoDomicilio", Filtro.IGUAL, new Long(idTipoDomicilio));
			if (tipodomicilio.getDescripcion() != null && !tipodomicilio.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, tipodomicilio.getDescripcion());

			tipoDomicilioList = generalService.getTipoDomicilioService().getTipoDomicilio(filtro);
			Iterator iter = tipoDomicilioList.iterator();
			while (iter.hasNext())
			{
				TipoDomicilio element = (TipoDomicilio) iter.next();
			}
			idTipoDomicilio = "";
			tipodomicilio.setDescripcion("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarTipoDomicilio.jsf");
		return null;
	}


	public String getIdTipoDomicilio() {
		return idTipoDomicilio;
	}


	public void setIdTipoDomicilio(String idTipoDomicilio) {
		this.idTipoDomicilio = idTipoDomicilio;
	}
}
