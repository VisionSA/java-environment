package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.FormaPagoDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.FormaPagoException;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;
import com.bizitglobal.tarjetafiel.general.service.FormaPagoService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class FormaPagoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(FormaPagoBean.class);
	private FormaPago formapago;
	private String nombreFiltro;
	private Long idFormaPagoHidden;
	private String idFormaPago;

	// definicion de un list del objeto base
	private List formaPagoList;

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;

	private String descFmaPagoInicial;


	public FormaPagoBean() {
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


	public FormaPago getFormaPago() {
		return formapago;
	}


	public void setFormaPago(FormaPago formapago) {
		this.formapago = formapago;
	}


	public Long getIdFormaPagoHidden() {
		return idFormaPagoHidden;
	}


	public void setIdFormaPagoHidden(Long idFormaPagoHidden) {
		this.idFormaPagoHidden = idFormaPagoHidden;
	}


	public List getFormaPagoList() {
		return formaPagoList;
	}


	public void setFormaPagoList(List object) {
		this.formaPagoList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE FORMAPAGO
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
		return "amFormaPago";
	}


	private void cargarItems() {
	}


	public String editarFormaPago() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Forma de Pago";
		try {
			formapago = generalService.getFormaPagoService().leerFormaPago(idFormaPagoHidden);
			descFmaPagoInicial = formapago.getDescripcion();
			result = "amFormaPago";
		} catch (FormaPagoException e1) {
			error.agregar("Ocurrio un error al intentar editar el formapago");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarFormaPago.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el formapago");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarFormaPago.jsf");
		}
		return result;
	}


	public String eliminarFormaPago() {
		try {
			generalService.getFormaPagoService().borrarFormaPago(idFormaPagoHidden);
			formaPagoList.remove(new FormaPago(idFormaPagoHidden));
		} catch (FormaPagoException e1) {
			error.agregar("Imposible borrar el formapago. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el formapago");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarFormaPago.jsf");
		return null;
	}


	public String grabar() {
		try {
			formapago.setDescripcion(formapago.getDescripcion().trim());
			if (validar()) {
				FormaPagoService formapagoService = generalService.getFormaPagoService();
				if (alta) {
					log.info(formapago.getId() + " " + formapago.getIdFormaPago() + " " + formapago.getDescripcion());
					formapagoService.grabarFormaPago(formapago);
				} else {

					// si cambio, lo guarda
					// if(!(descFmaPagoInicial.compareTo(formapago.getDescripcion())==0))
					if (!(descFmaPagoInicial.equals(formapago.getDescripcion())))
						formapagoService.actualizarFormaPago(formapago);
				}
				popup.setPopup(popup.ICONO_OK, "La Forma de Pago ha sido almacenada exitosamente.");
			}
			else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (FormaPagoDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Forma de Pago.");
			e1.printStackTrace();
		} catch (FormaPagoException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Forma de Pago.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta de la Forma de Pago.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta Forma de Pago";
		popup.borrar();

		idFormaPago = "";

		formapago = new FormaPago();
		formaPagoList = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		if (formapago.getDescripcion() == null || formapago.getDescripcion().equals(""))
			error.agregar(Error.AMFORMAPAGO_DESCRIPCION_REQUERIDA);
		try {
			List unaFormaPago = generalService.getFormaPagoService().getFormaPagos(
					new Filtro("descripcion", Filtro.LIKEXS, formapago.getDescripcion().trim()));
			if (alta) {
				if (!unaFormaPago.isEmpty())
					error.agregar(Error.AMFORMAPAGO_FORMAPAGO_REPETIDA);
			}
			else {
				if (!(descFmaPagoInicial.compareTo(formapago.getDescripcion()) == 0))
				{
					if (!unaFormaPago.isEmpty())
						error.agregar(Error.AMFORMAPAGO_FORMAPAGO_REPETIDA);
				}
			}

		} catch (FormaPagoException e) {
			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoFormaPago() {
		return inicializar();
	}


	public String irAModificarFormaPago() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Forma de Pago";
		return null;
	}


	public String irAListarFormaPago() {
		borrar();
		tituloCorto = "Listado Forma de Pago";
		cargarItems();
		Session.redirect("/tarjetafiel/general/listarFormaPago.jsf");
		return "";
	}


	public String listarFormaPago() {
		formaPagoList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (idFormaPago != null && !idFormaPago.equals(""))
				filtro.agregarCampoOperValor("idFormaPago", Filtro.IGUAL, new Long(idFormaPago));

			if (formapago.getDescripcion() != null && !formapago.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, formapago.getDescripcion());

			formaPagoList = generalService.getFormaPagoService().getFormaPagos(filtro);
			Iterator iter = formaPagoList.iterator();
			while (iter.hasNext())
			{
				FormaPago element = (FormaPago) iter.next();
			}
			idFormaPago = "";
			formapago.setDescripcion("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarFormaPago.jsf");
		return null;
	}


	public String getIdFormaPago() {
		return idFormaPago;
	}


	public void setIdFormaPago(String idFormaPago) {
		this.idFormaPago = idFormaPago;
	}
}
