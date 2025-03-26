package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteConceptoDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteConcepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.tarjetafiel.transacciones.service.ClienteConceptoService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class ClienteConceptoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ClienteConceptoBean.class);
	private ClienteConcepto clienteconcepto;
	private String nombreFiltro;
	private Long idClienteConceptoHidden;

	// definicion de un list del objeto base
	private List clienteconceptoList;

	// Listas para la presentacion(HtmlSelectItems).
	private List clienteList = new ArrayList();
	private List clienteItems = new ArrayList();

	private List conceptoList = new ArrayList();
	private List conceptoItems = new ArrayList();

	// Objetos Relacionados.
	private Long idClienteSeleccionada;
	private Long idConceptoSeleccionada;

	private String focoHidden;


	public ClienteConceptoBean() {
		super();
		borrar();
		try {
			try {
				clienteList = transaccionesService.getClienteTransaccionService().getCliente(new Filtro());
			} catch (ClienteTransaccionException e1) {
				e1.printStackTrace();
			}
			try {
				conceptoList = transaccionesService.getConceptoService().getConcepto(new Filtro());
			} catch (ConceptoException e1) {
				e1.printStackTrace();
			}
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


	public ClienteConcepto getClienteConcepto() {
		return clienteconcepto;
	}


	public void setClienteConcepto(ClienteConcepto clienteconcepto) {
		this.clienteconcepto = clienteconcepto;
	}


	public Long getIdClienteConceptoHidden() {
		return idClienteConceptoHidden;
	}


	public void setIdClienteConceptoHidden(Long idClienteConceptoHidden) {
		this.idClienteConceptoHidden = idClienteConceptoHidden;
	}


	public List getClienteItems() {
		return clienteItems;
	}


	public void setClienteItems(List clienteItems) {
		this.clienteItems = clienteItems;
	}


	public Long getIdClienteSeleccionada() {
		return idClienteSeleccionada;
	}


	public void setIdClienteSeleccionada(Long idClienteSeleccionada) {
		this.idClienteSeleccionada = idClienteSeleccionada;
	}


	public List getConceptoItems() {
		return conceptoItems;
	}


	public void setConceptoItems(List conceptoItems) {
		this.conceptoItems = conceptoItems;
	}


	public Long getIdConceptoSeleccionada() {
		return idConceptoSeleccionada;
	}


	public void setIdConceptoSeleccionada(Long idConceptoSeleccionada) {
		this.idConceptoSeleccionada = idConceptoSeleccionada;
	}


	public List getClienteConceptoList() {
		return clienteconceptoList;
	}


	public void setClienteConceptoList(List object) {
		this.clienteconceptoList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CLIENTECONCEPTO
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
		return "amClienteConcepto";
	}


	private void cargarItems() {
		if (clienteItems.size() != clienteList.size()) {
			clienteItems = Util.cargarSelectItem(clienteList);
		}
		if (conceptoItems.size() != conceptoList.size()) {
			conceptoItems = Util.cargarSelectItem(conceptoList);
		}
	}


	public String editarClienteConcepto() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar clienteconcepto";
		try {
			clienteconcepto = transaccionesService.getClienteConceptoService().leerClienteConcepto(idClienteConceptoHidden);
			result = "amClienteConcepto";
		} catch (ClienteConceptoException e1) {
			error.agregar("Ocurrio un error al intentar editar el clienteconcepto");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarClienteConcepto.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el clienteconcepto");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/Transacciones/listarClienteConcepto.jsf");
		}
		return result;
	}


	public String eliminarClienteConcepto() {
		try {
			transaccionesService.getClienteConceptoService().borrarClienteConcepto(idClienteConceptoHidden);
			clienteconceptoList.remove(new ClienteConcepto(idClienteConceptoHidden));
		} catch (ClienteConceptoException e1) {
			error.agregar("Imposible borrar el clienteconcepto. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el clienteconcepto");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/Transacciones/listarClienteConcepto.jsf");
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				ClienteConceptoService clienteconceptoService = transaccionesService.getClienteConceptoService();
				clienteconcepto.setCliente((ClienteTransaccion) Util.buscarElemento(clienteList, new ClienteTransaccion(idClienteSeleccionada)));
				clienteconcepto.setConcepto((Concepto) Util.buscarElemento(conceptoList, new Concepto(idConceptoSeleccionada)));
				if (alta) {
					// Grabo el nuevo objeto
					clienteconceptoService.grabarClienteConcepto(clienteconcepto);
				} else {
					clienteconceptoService.actualizarClienteConcepto(clienteconcepto);
				}
				popup.setPopup(popup.ICONO_OK, "El clienteconcepto ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ClienteConceptoDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del clienteconcepto.");
			e1.printStackTrace();
		} catch (ClienteConceptoException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del clienteconcepto.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del clienteconcepto.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de clienteconcepto";
		popup.borrar();

		clienteconcepto = new ClienteConcepto();
		clienteconceptoList = new ArrayList();
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


	public String irANuevoClienteConcepto() {
		return inicializar();
	}


	public String irAModificarClienteConcepto() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar clienteconcepto";
		return null;
	}


	public String irAListarClienteConcepto() {
		borrar();
		tituloCorto = "Listado de clienteconcepto";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarClienteConcepto.jsf");
		return "";
	}


	public String listarClienteConcepto() {
		clienteconceptoList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			// filtro.agregarCampoOperValor("", Filtro.IGUAL, clienteconcepto.
			clienteconceptoList = transaccionesService.getClienteConceptoService().getClienteConcepto(filtro);
			Iterator iter = clienteconceptoList.iterator();
			while (iter.hasNext())
			{
				ClienteConcepto element = (ClienteConcepto) iter.next();
				element.getCliente().getLabel();
				element.getConcepto().getLabel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarClienteConcepto.jsf");
		return null;
	}
}
