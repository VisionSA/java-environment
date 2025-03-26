package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.FormaPagoTransaccionDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.FormaPagoTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.FormaPagoTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.service.FormaPagoTransaccionService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes"})
public class FormaPagoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(FormaPagoBean.class);
	private FormaPagoTransaccion formapago;
	private String nombreFiltro;
	private Long idFormaPagoHidden;

	// definicion de un list del objeto base
	private List formapagoList;

	// Listas para la presentacion(HtmlSelectItems).
	// Objetos Relacionados.

	private String focoHidden;


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


	public FormaPagoTransaccion getFormapago() {
		return formapago;
	}


	public void setFormapago(FormaPagoTransaccion formapago) {
		this.formapago = formapago;
	}


	public Long getIdFormaPagoHidden() {
		return idFormaPagoHidden;
	}


	public void setIdFormaPagoHidden(Long idFormaPagoHidden) {
		this.idFormaPagoHidden = idFormaPagoHidden;
	}


	public List getFormaPagoList() {
		return formapagoList;
	}


	public void setFormaPagoList(List object) {
		this.formapagoList = object;
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
		tituloCorto = "Modificar formapago";
		try {
			formapago = transaccionesService.getFormaPagoTransaccionService().leerFormaPago(idFormaPagoHidden);
			result = "amFormaPago";
		} catch (FormaPagoTransaccionException e1) {
			error.agregar("Ocurrio un error al intentar editar el formapago");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarFormaPago.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el formapago");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarFormaPago.jsf");
		}
		return result;
	}


	public String eliminarFormaPago() {
		try {
			transaccionesService.getFormaPagoTransaccionService().borrarFormaPago(idFormaPagoHidden);
			formapagoList.remove(new FormaPagoTransaccion(idFormaPagoHidden));
		} catch (FormaPagoTransaccionException e1) {
			error.agregar("Imposible borrar el formapago. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el formapago");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/Transacciones/listarFormaPago.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				FormaPagoTransaccionService formapagoService = transaccionesService.getFormaPagoTransaccionService();
				if (alta) {
					// Grabo el nuevo objeto
					formapagoService.grabarFormaPago(formapago);
				} else {
					formapagoService.actualizarFormaPago(formapago);
				}
				popup.setPopup(popup.ICONO_OK, "El formapago ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (FormaPagoTransaccionDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del formapago.");
			e1.printStackTrace();
		} catch (FormaPagoTransaccionException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del formapago.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del formapago.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de formapago";
		popup.borrar();

		formapago = new FormaPagoTransaccion();
		formapagoList = new ArrayList();
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


	public String irANuevoFormaPago() {
		return inicializar();
	}


	public String irAModificarFormaPago() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar formapago";
		return null;
	}


	public String irAListarFormaPago() {
		borrar();
		tituloCorto = "Listado de formapago";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarFormaPago.jsf");
		return "";
	}


	public String listarFormaPago() {
		formapagoList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			// filtro.agregarCampoOperValor("", Filtro.IGUAL, formapago.
			formapagoList = transaccionesService.getFormaPagoTransaccionService().getFormaPago(filtro);
			Iterator iter = formapagoList.iterator();
			while (iter.hasNext())
			{
				FormaPagoTransaccion element = (FormaPagoTransaccion) iter.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarFormaPago.jsf");
		return null;
	}
}
