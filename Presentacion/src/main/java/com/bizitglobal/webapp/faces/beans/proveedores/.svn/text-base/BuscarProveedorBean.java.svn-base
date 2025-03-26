package com.bizitglobal.webapp.faces.beans.proveedores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuitValido;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.service.proveedores.ProveedoresServiceFaces;
import com.bizitglobal.webapp.faces.util.ProveedorSeleccionable;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


/**
 * Bean de fondo para la página que filtra los proveedores.
 * 
 * @author Daniel
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class BuscarProveedorBean extends BaseBean {
	private static final Logger log = Logger.getLogger(BuscarProveedorBean.class);
	private ProveedoresServiceFaces service = new ProveedoresServiceFaces();

	private String codigoFiltroProveedor;
	private String cuitFiltroProveedor;
	private String razonSocialFiltroProveedor;
	private String nombreFantasiaFiltroProveedor;
	private String aliasFiltroProveedor;

	// Objetos para inicializar desde distintos origenes
	private int origen = 0;
	public static final int PROVEEDOR = 1;
	public static final int COMPROBANTE = 2;
	public static final int ORDEN_PAGO = 3;
	public static final int REPORTE_PROV_CTA_CTE = 4;
	public static final int REPORTE_PROV_COMP_SALDO = 5;
	public static final int IMPUTACION = 6;
	public static final int PROVEEDOR_CTA_CTE = 7;
	public static final int IMPUTACION_ALTA = 8;

	private List proveedores;
	private String nombreColumna;
	private boolean ascendente;


	public BuscarProveedorBean() {
		this(null, null, null, null, null, new ArrayList(), "cuit", true);
	}


	public BuscarProveedorBean(String codigoFiltroProveedor, String cuitFiltroProveedor, String razonSocialFiltroProveedor,
			String nombreFantasiaFiltroProveedor, String aliasFiltroProveedor, List proveedores, String nombreColumna, boolean ascendente) {
		super();
		this.codigoFiltroProveedor = codigoFiltroProveedor;
		this.cuitFiltroProveedor = cuitFiltroProveedor;
		this.razonSocialFiltroProveedor = razonSocialFiltroProveedor;
		this.nombreFantasiaFiltroProveedor = nombreFantasiaFiltroProveedor;
		this.aliasFiltroProveedor = aliasFiltroProveedor;
		this.proveedores = proveedores;
		this.nombreColumna = nombreColumna;
		this.ascendente = ascendente;
	}


	/**
	 * Llamado desde el menú, permite inicilizar el contenido del bean cada vez que se invoque.
	 */
	public String inicializar() {
		borrar();
		return "listarProveedores";
	}


	public String inicializar(int origen) {
		borrar();
		this.origen = origen;
		return "";
	}


	public String getCodigoFiltroProveedor() {
		return codigoFiltroProveedor;
	}


	public void setCodigoFiltroProveedor(String codigoFiltroProveedor) {
		this.codigoFiltroProveedor = codigoFiltroProveedor;
	}


	public String getNombreFantasiaFiltroProveedor() {
		return nombreFantasiaFiltroProveedor;
	}


	public void setNombreFantasiaFiltroProveedor(
			String nombreFantasiaFiltroProveedor) {
		this.nombreFantasiaFiltroProveedor = nombreFantasiaFiltroProveedor;
	}


	public String getCuitFiltroProveedor() {
		return cuitFiltroProveedor;
	}


	public void setCuitFiltroProveedor(String cuitFiltroProveedor) {
		this.cuitFiltroProveedor = cuitFiltroProveedor;
	}


	public String getRazonSocialFiltroProveedor() {
		return razonSocialFiltroProveedor;
	}


	public void setRazonSocialFiltroProveedor(String razonSocialFiltroProveedor) {
		this.razonSocialFiltroProveedor = razonSocialFiltroProveedor;
	}


	public List getProveedores() {
		return proveedores;
	}


	public void setProveedores(List proveedores) {
		this.proveedores = proveedores;
	}


	public boolean isAscendente() {
		return ascendente;
	}


	public void setAscendente(boolean ascendente) {
		this.ascendente = ascendente;
	}


	public String getNombreColumna() {
		return nombreColumna;
	}


	public void setNombreColumna(String nombreColumna) {
		this.nombreColumna = nombreColumna;
	}


	public String getAliasFiltroProveedor() {
		return aliasFiltroProveedor;
	}


	public void setAliasFiltroProveedor(String aliasFiltroProveedor) {
		this.aliasFiltroProveedor = aliasFiltroProveedor;
	}


	// Acciones para el bean BuscarProveedorBean.
	public String filtrarProveedores() {
		log.info("filtrando proveedores!!!");
		Filtro filtro = new Filtro();
		if (!Validador.esNuloVacio(codigoFiltroProveedor)) {
			filtro.agregarCampoOperValor("idProveedor", Filtro.IGUAL,
					codigoFiltroProveedor);
		}
		if (!Validador.esNuloVacio(cuitFiltroProveedor)) {
			filtro.agregarCampoOperValor("cuit", Filtro.LIKE,
					cuitFiltroProveedor);
		}
		if (!Validador.esNuloVacio(razonSocialFiltroProveedor)) {
			filtro.agregarCampoOperValor("razonSocial", Filtro.LIKE,
					razonSocialFiltroProveedor);
		}
		if (!Validador.esNuloVacio(nombreFantasiaFiltroProveedor)) {
			filtro.agregarCampoOperValor("nombreFantasia", Filtro.LIKE,
					nombreFantasiaFiltroProveedor);
		}
		if (!Validador.esNuloVacio(aliasFiltroProveedor)) {
			filtro.agregarCampoOperValor("alias", Filtro.LIKE,
					aliasFiltroProveedor);
		}

		filtro.agregarCampoOperValor("activo", Filtro.LIKE,
				"S");

		log.info("Filtro -> " + filtro.getHQL());
		proveedores = new ArrayList();
		try {
			List proveedoresSeleccionables = service.getProveedorService().getProveedores(filtro);
			proveedores = BuscarProveedorUtil.getProveedoresSeleccionables(proveedoresSeleccionables);
		} catch (ProveedorException e) {
			e.printStackTrace();
		}
		return null;
	}


	private List cargarListProveedor() {
		log.info("aceptar filtro proveedor!!!");
		List proveedoresList = new ArrayList();

		if (!proveedores.isEmpty()) {
			Iterator iter = proveedores.iterator();
			while (iter.hasNext()) {
				ProveedorSeleccionable aux = (ProveedorSeleccionable) iter.next();
				if (aux.getSeleccionado()) {
					proveedoresList.add(aux.getProveedor());
				}
			}
		}
		return proveedoresList;
	}


	public String aceptarFiltroProveedor() {
		switch (origen) {
		case PROVEEDOR:
			if (!cargarListProveedor().isEmpty()) {
				log.info("Comunicando con el bean de proveedores.");
				ProveedorBean bean = (ProveedorBean) Session.getBean("ProveedorBean");
				bean.getTablaDeRelacionConProveedores().addAll(cargarListProveedor());
			}
			borrar(); // borramos el bean ya que se ha grabado en el bean de proveedores.
			break;

		case COMPROBANTE:
			try {
				if (!cargarListProveedor().isEmpty()) {
					log.info("Comunicando con el bean de comprobantes.");
					Proveedor provAux = (Proveedor) cargarListProveedor().get(0);
					CuitValido cuit = new CuitValido(provAux.getCuit().toString());
					ComprobanteBean beanComprobanteBean = (ComprobanteBean) Session.getBean("ComprobanteBean");
					beanComprobanteBean.setCuitBusqueda(cuit.getCuit().toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			borrar();
			break;

		case PROVEEDOR_CTA_CTE:
			try {
				if (!cargarListProveedor().isEmpty()) {
					log.info("Comunicando con el bean de cuenta corriente proveedores.");
					Proveedor provAux = (Proveedor) cargarListProveedor().get(0);
					CuitValido cuit = new CuitValido(provAux.getCuit().toString());
					ProveedorCtaCteBean beanProveedorCtaCte = (ProveedorCtaCteBean) Session.getBean("ProveedorCtaCteBean");
					beanProveedorCtaCte.setCuitBusqueda(cuit.getCuit().toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			borrar();
			break;

		case ORDEN_PAGO:
			try {
				if (!cargarListProveedor().isEmpty()) {
					Proveedor provAux = (Proveedor) cargarListProveedor().get(0);
					log.info("Proveedor encontrado: " + provAux);
					CuitValido cuit = new CuitValido(provAux.getCuit().toString());
					OrdenPagoBean opBean = (OrdenPagoBean) Session.getBean("OrdenPagoBean");
					opBean.setCuitBusqueda(cuit.getCuit().toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			borrar();
			break;

		case REPORTE_PROV_CTA_CTE:
			try {
				if (!cargarListProveedor().isEmpty()) {
					Proveedor provAux = (Proveedor) cargarListProveedor().get(0);
					log.info("Proveedor encontrado: " + provAux);
					CuitValido cuit = new CuitValido(provAux.getCuit().toString());
					ReporteProveedorCtaCteBean beanUno = (ReporteProveedorCtaCteBean) Session.getBean("ReporteProveedorCtaCteBean");
					beanUno.setCuitBusqueda(cuit.getCuit().toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			borrar();
			break;

		case REPORTE_PROV_COMP_SALDO:
			try {
				if (!cargarListProveedor().isEmpty()) {
					Proveedor provAux = (Proveedor) cargarListProveedor().get(0);
					log.info("Proveedor encontrado: " + provAux);
					String idProveedor = provAux.getIdProveedor().toString();
					ReporteProveedorCompSaldosBean beanDos = (ReporteProveedorCompSaldosBean) Session.getBean("ReporteProveedorCompSaldosBean");
					beanDos.setIdProveedorBusqueda(idProveedor);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			borrar();
			break;

		case IMPUTACION:
			try {
				if (!cargarListProveedor().isEmpty()) {
					Proveedor provAux = (Proveedor) cargarListProveedor().get(0);
					log.info("Proveedor encontrado: " + provAux);
					CuitValido cuit = new CuitValido(provAux.getCuit().toString());
					ImputacionBean imputacion = (ImputacionBean) Session.getBean("ImputacionBean");
					imputacion.setCuitFiltro(cuit.getCuit().toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			borrar();
			break;

		case IMPUTACION_ALTA:
			try {
				if (!cargarListProveedor().isEmpty()) {
					Proveedor provAux = (Proveedor) cargarListProveedor().get(0);
					log.info("Proveedor encontrado: " + provAux);
					CuitValido cuit = new CuitValido(provAux.getCuit().toString());
					ImputacionBean imputacion = (ImputacionBean) Session.getBean("ImputacionBean");
					imputacion.setCuit(cuit.getCuit().toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			borrar();
			break;
		}

		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		log.info("Actionlistener de buscarProveedor: " + event);
		ejecutarJavaScript("window.opener.recargar();window.close();");
	}


	public void borrar() {
		codigoFiltroProveedor = null;
		aliasFiltroProveedor = null;
		cuitFiltroProveedor = null;
		razonSocialFiltroProveedor = null;
		nombreFantasiaFiltroProveedor = null;
		proveedores = new ArrayList();
	}


	public String cancelar() {
		borrar();
		return null;
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}

}
