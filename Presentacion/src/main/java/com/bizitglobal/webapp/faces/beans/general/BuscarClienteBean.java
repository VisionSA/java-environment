package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.proveedores.BuscarProveedorBean;
import com.bizitglobal.webapp.faces.beans.proveedores.ProveedorBean;
import com.bizitglobal.webapp.faces.service.general.GeneralServiceFaces;
import com.bizitglobal.webapp.faces.util.ClienteSeleccionable;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


@SuppressWarnings({"rawtypes","unchecked"})
public class BuscarClienteBean extends BaseBean {
	private static final Logger log = Logger.getLogger(BuscarProveedorBean.class);
	private GeneralServiceFaces service = generalService;

	private String nombre;
	private String apellido;
	private Long documento;
	private String cuil;

	private List clientes;
	private String nombreColumna;
	private boolean ascendente;


	public BuscarClienteBean() {
		this(null, null, null, null, null, null, false);
	}


	public BuscarClienteBean(String nombre, String apellido, Long documento,
			String cuil, List clientes, String nombreColumna, boolean ascendente) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.cuil = cuil;
		this.clientes = clientes;
		this.nombreColumna = nombreColumna;
		this.ascendente = ascendente;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public boolean isAscendente() {
		return ascendente;
	}


	public void setAscendente(boolean ascendente) {
		this.ascendente = ascendente;
	}


	public List getClientes() {
		return clientes;
	}


	public void setClientes(List clientes) {
		this.clientes = clientes;
	}


	public String getCuil() {
		return cuil;
	}


	public void setCuil(String cuil) {
		this.cuil = cuil;
	}


	public Long getDocumento() {
		return documento;
	}


	public void setDocumento(Long documento) {
		this.documento = documento;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getNombreColumna() {
		return nombreColumna;
	}


	public void setNombreColumna(String nombreColumna) {
		this.nombreColumna = nombreColumna;
	}


	public String inicializar() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	/*
	 * Acciones del bean buscar clientes.
	 */
	public String filtrarClientes() {
		log.info("filtrando clientes!!!");
		Filtro filtro = new Filtro();
		if (!Validador.esNuloVacio(nombre)) {
			filtro.agregarCampoOperValor("nombre1", Filtro.LIKE, nombre);
		}
		if (!Validador.esNuloVacio(apellido)) {
			filtro.agregarCampoOperValor("apellido", Filtro.LIKE, apellido);
		}
		if (!Validador.esNuloVacio(cuil)) {
			filtro.agregarCampoOperValor("cuil", Filtro.LIKE, cuil);
		}
		if (documento != null && documento.equals(new Long(0))) {
			filtro.agregarCampoOperValor("nroDocumento", Filtro.IGUAL, documento);
		}

		log.info("Filtro -> " + filtro.getHQL());
		clientes = new ArrayList();

		try {
			if (service.getClienteDao() == null) {
				log.info("El cliente dao esta vacio!!!");
			}

			List clientesSeleccionables = service.getClienteDao().listarTodos(filtro);
			clientes = BuscarClienteUtil.getClientesSeleccionables(clientesSeleccionables);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public String aceptarFiltroCliente() {
		log.info("aceptar filtro cliente!!!");
		List clientesList = new ArrayList();

		if (!clientes.isEmpty()) {
			Iterator iter = clientes.iterator();
			while (iter.hasNext()) {
				ClienteSeleccionable aux = (ClienteSeleccionable) iter.next();
				if (aux.getSeleccionado()) {
					clientesList.add(aux.getCliente());
				}
			}
		}

		log.info("Comunicando con el bean de proveedores.");
		ProveedorBean bean = (ProveedorBean) Session.getBean("ProveedorBean");
		bean.setTablaDeRelacionConClientes(clientesList);

		borrar();

		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		final String viewId = "/buscarCliente.jsf";

		FacesContext facesContext = FacesContext.getCurrentInstance();

		ViewHandler viewHandler = facesContext.getApplication().getViewHandler();
		String actionUrl = viewHandler.getActionURL(facesContext, viewId);

		// Script javascript a ejecutar.
		String javaScriptText = "window.opener.recargar();window.close();";

		AddResource addResource = AddResourceFactory.getInstance(facesContext);
		addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
	}


	public void borrar() {
		nombre = null;
		apellido = null;
		documento = null;
		cuil = null;
		clientes = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return null;
	}
}
