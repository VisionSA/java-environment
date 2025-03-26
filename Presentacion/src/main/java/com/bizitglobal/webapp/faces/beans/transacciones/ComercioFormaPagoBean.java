package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.ComercioFormaPagoDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ComercioFormaPagoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.FormaPagoTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioFormaPago;
import com.bizitglobal.tarjetafiel.transacciones.service.ComercioFormaPagoService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class ComercioFormaPagoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ComercioFormaPagoBean.class);
	private ComercioFormaPago comercioformapago;
	private String nombreFiltro;
	private Long idComercioFormaPagoHidden;

	// definicion de un list del objeto base
	private List comercioformapagoList;

	// Listas para la presentacion(HtmlSelectItems).
	private List comercioItems = new ArrayList();

	private List formapagoList = new ArrayList();
	private List formapagoItems = new ArrayList();

	// Objetos Relacionados.
	private Long idComercioSeleccionada;
	private Long idFormaPagoSeleccionada;

	private String focoHidden;


	public ComercioFormaPagoBean() {
		super();
		borrar();
		try {
			formapagoList = transaccionesService.getFormaPagoTransaccionService().getFormaPago(new Filtro());
		} catch (FormaPagoTransaccionException e1) {
			e1.printStackTrace();
		}
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public ComercioFormaPago getComercioFormaPago() {
		return comercioformapago;
	}


	public void setComercioFormaPago(ComercioFormaPago comercioformapago) {
		this.comercioformapago = comercioformapago;
	}


	public Long getIdComercioFormaPagoHidden() {
		return idComercioFormaPagoHidden;
	}


	public void setIdComercioFormaPagoHidden(Long idComercioFormaPagoHidden) {
		this.idComercioFormaPagoHidden = idComercioFormaPagoHidden;
	}


	public List getComercioItems() {
		return comercioItems;
	}


	public void setComercioItems(List comercioItems) {
		this.comercioItems = comercioItems;
	}


	public Long getIdComercioSeleccionada() {
		return idComercioSeleccionada;
	}


	public void setIdComercioSeleccionada(Long idComercioSeleccionada) {
		this.idComercioSeleccionada = idComercioSeleccionada;
	}


	public List getFormaPagoItems() {
		return formapagoItems;
	}


	public void setFormaPagoItems(List formapagoItems) {
		this.formapagoItems = formapagoItems;
	}


	public Long getIdFormaPagoSeleccionada() {
		return idFormaPagoSeleccionada;
	}


	public void setIdFormaPagoSeleccionada(Long idFormaPagoSeleccionada) {
		this.idFormaPagoSeleccionada = idFormaPagoSeleccionada;
	}


	public List getComercioFormaPagoList() {
		return comercioformapagoList;
	}


	public void setComercioFormaPagoList(List object) {
		this.comercioformapagoList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE COMERCIOFORMAPAGO
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
		return "amComercioFormaPago";
	}


	private void cargarItems() {
		if (formapagoItems.size() != formapagoList.size()) {
			formapagoItems = Util.cargarSelectItem(formapagoList);
		}
	}


	public String editarComercioFormaPago() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar comercioformapago";
		try {
			comercioformapago = transaccionesService.getComercioFormaPagoService().leerComercioFormaPago(idComercioFormaPagoHidden);
			result = "amComercioFormaPago";
		} catch (ComercioFormaPagoException e1) {
			error.agregar("Ocurrio un error al intentar editar el comercioformapago");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarComercioFormaPago.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el comercioformapago");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarComercioFormaPago.jsf");
		}
		return result;
	}


	public String eliminarComercioFormaPago() {
		try {
			transaccionesService.getComercioFormaPagoService().borrarComercioFormaPago(idComercioFormaPagoHidden);
			comercioformapagoList.remove(new ComercioFormaPago(idComercioFormaPagoHidden));
		} catch (ComercioFormaPagoException e1) {
			error.agregar("Imposible borrar el comercioformapago. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el comercioformapago");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/Transacciones/listarComercioFormaPago.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				ComercioFormaPagoService comercioformapagoService = transaccionesService.getComercioFormaPagoService();
				// comercioformapago.setFormaPago((FormaPagoTransaccion)Util.buscarElemento(formapagoList, new
				// FormaPagoTransaccion(idFormaPagoSeleccionada)));
				if (alta) {
					// Grabo el nuevo objeto
					comercioformapagoService.grabarComercioFormaPago(comercioformapago);
				} else {
					comercioformapagoService.actualizarComercioFormaPago(comercioformapago);
				}
				popup.setPopup(popup.ICONO_OK, "El comercioformapago ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ComercioFormaPagoDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del comercioformapago.");
			e1.printStackTrace();
		} catch (ComercioFormaPagoException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del comercioformapago.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del comercioformapago.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de comercioformapago";
		popup.borrar();

		comercioformapago = new ComercioFormaPago();
		comercioformapagoList = new ArrayList();
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


	public String irANuevoComercioFormaPago() {
		return inicializar();
	}


	public String irAModificarComercioFormaPago() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar comercioformapago";
		return null;
	}


	public String irAListarComercioFormaPago() {
		borrar();
		tituloCorto = "Listado de comercioformapago";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarComercioFormaPago.jsf");
		return "";
	}


	public String listarComercioFormaPago() {
		comercioformapagoList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			// filtro.agregarCampoOperValor("", Filtro.IGUAL, comercioformapago.
			comercioformapagoList = transaccionesService.getComercioFormaPagoService().getComercioFormaPago(filtro);
			Iterator iter = comercioformapagoList.iterator();
			while (iter.hasNext())
			{
				ComercioFormaPago element = (ComercioFormaPago) iter.next();
				element.getFormaPago().getLabel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarComercioFormaPago.jsf");
		return null;
	}
}
