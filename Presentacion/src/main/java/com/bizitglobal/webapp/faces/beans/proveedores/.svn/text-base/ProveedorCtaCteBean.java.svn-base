package com.bizitglobal.webapp.faces.beans.proveedores;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Archivo;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteException;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuitNoValidoException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorCtaCteException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorNotFoundException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Comprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuitValido;
import com.bizitglobal.tarjetafiel.proveedores.negocio.DocumentoAdjunto;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorCtaCte;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorCtaCteService;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class ProveedorCtaCteBean extends ComprobanteBean {
	private static final Logger log = Logger.getLogger(ProveedorCtaCteBean.class);

	private String cuitBusqueda;
	public String cuit;
	private Date fechaDesde;
	private Date fechaHasta;

	private CuitValido cuitValido;

	private String nroCteHidden;
	private String tipoCteHidden;
	/* @I5562 */private Long idComprobanteHidden;
	/* @F5562 */
	/* @I5562 */private String popupReport;
	/* @F5562 */public List tablaCtaCte;

	private String tituloLargo = "Tarjeta Fiel - Listar Cuenta Corriente";
	private String tituloCorto = "Listar Cta Cte";

	ProveedorCtaCteService proveedorCtaCteService = proveedoresService.getProveedorCtaCteService();


	public ProveedorCtaCteBean() {
		// borrar();
	}


	public String getNroCteHidden() {
		return nroCteHidden;
	}


	public void setNroCteHidden(String nroCteHidden) {
		this.nroCteHidden = nroCteHidden;
	}


	public String getTipoCteHidden() {
		return tipoCteHidden;
	}


	public void setTipoCteHidden(String tipoCteHidden) {
		this.tipoCteHidden = tipoCteHidden;
	}


	/* @I5562 */public Long getIdComprobanteHidden() {
		return idComprobanteHidden;
	}


	public void setIdComprobanteHidden(Long idComprobanteHidden) {
		this.idComprobanteHidden = idComprobanteHidden;
	}


	/* @F5562 */
	/* @I5562 */public String getPopupReport() {
		String res = popupReport;
		popupReport = null;
		return res;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	/* @F5562 */public String getCuitBusqueda() {
		return cuitBusqueda;
	}


	public void setCuitBusqueda(String cuitBusqueda) {
		this.cuitBusqueda = cuitBusqueda;
	}


	public String getCuit() {
		return cuit;
	}


	public void setCuit(String cuit) {
		if (cuitBusqueda != null) {
			this.cuit = cuitBusqueda;
			cuitBusqueda = null;
		} else {
			this.cuit = cuit;
		}
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	public List getTablaCtaCte() {
		return tablaCtaCte;
	}


	public void setTablaCtaCte(List tablaCtaCte) {
		this.tablaCtaCte = tablaCtaCte;
	}


	public String getTituloCorto() {
		return tituloCorto;
	}


	public void setTituloCorto(String tituloCorto) {
		this.tituloCorto = tituloCorto;
	}


	public String getTituloLargo() {
		return tituloLargo;
	}


	public void setTituloLargo(String tituloLargo) {
		this.tituloLargo = tituloLargo;
	}


	public void borrar() {
		error.borrar();
		fechaHasta = new Timestamp(new java.util.Date().getTime());

		Calendar fecha = Calendar.getInstance();
		Date date = new Date(fechaHasta.getTime());
		fecha.setTime(date);
		fecha.add(Calendar.YEAR, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());

		this.cuit = null;
		this.tablaCtaCte = new ArrayList();
		this.cuitBusqueda = null;

		this.nroCteHidden = "";
		this.tipoCteHidden = "";
		/* @I5562 */this.idComprobanteHidden = new Long(0);
		/* @F5562 */
		/* @I5562 */this.popupReport = null;
		/* @F5562 */tituloLargo = "Tarjeta Fiel - Listar Cuenta Corriente";
		tituloCorto = "Listar Cta Cte";
	}


	public String generar() {
		Long idProv;
		error.borrar();

		if (cuit.equals(null) || cuit.equals("")) {
			error.agregar(Error.PROVEEDOR_CUIT_REQUERIDO);
			return null;
		}

		try {
			cuitValido = new CuitValido(cuit);
		} catch (CuitNoValidoException e1) {
			error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
			e1.printStackTrace();
			return null;
		} catch (Exception e) {
			error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
			e.printStackTrace();
			return null;
		}

		try {
			List proveedores = (proveedoresService.getProveedorService().getProveedores(new Filtro("cuit", Filtro.LIKEXS, cuit)));
			if (proveedores.isEmpty()) {
				error.agregar("El número de CUIT no corresponde a ningun proveedor cargado.");
				return null;
			}
			Proveedor prov = (Proveedor) proveedores.get(0);
			idProv = prov.getIdProveedor();
		} catch (ProveedorNotFoundException e1) {
			e1.printStackTrace();
			return null;
		} catch (Exception e2) {
			e2.printStackTrace();
			return "accesoDenegado";
		}

		try {
			tablaCtaCte = proveedorCtaCteService.obtenerCtaCte(
					idProv, new Timestamp(fechaDesde.getTime()), new Timestamp(fechaHasta.getTime()));
		} catch (ProveedorCtaCteException e1) {
			error.agregar("Error al obtener la cuenta corriente");
			e1.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		try {
			if (!tablaCtaCte.isEmpty()) {

				ProveedorCtaCte p1;
				ProveedorCtaCte pCtaCte = new ProveedorCtaCte();

				p1 = (ProveedorCtaCte) tablaCtaCte.get(0);
				pCtaCte.setTipoCte("Saldo anterior");
				pCtaCte.setSaldo(p1.getSaldoAnterior());

				tablaCtaCte.add(0, pCtaCte);
			}
			else {
				error.agregar("Cta Cte vacia");
				tablaCtaCte = null;
			}
		} catch (Exception e) {
			error.agregar("Cta Cte vacia");
			e.printStackTrace();
			return null;
		}

		Session.redirect("/tarjetafiel/proveedores/listarCtaCte.jsf");
		return null;
	}


	public String buscarProveedorPopup() {
		log.info("Ir a buscar proveedor!!!");
		BuscarProveedorBean bean = (BuscarProveedorBean) Session.getBean("BuscarProveedorBean");
		bean.inicializar(BuscarProveedorBean.PROVEEDOR_CTA_CTE);

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/proveedores/popup/buscarProveedor.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}


	public String mostrarComprobante() {
		log.info("mostrar comprobante: " + this.cuit + ", " + this.tipoCteHidden + ", " + this.nroCteHidden);
		String ret = null;

		if (tipoCteHidden != null && !tipoCteHidden.equals("") &&
				nroCteHidden != null & !nroCteHidden.equals("")) {

			Integer nroCorto = new Integer(nroCteHidden.substring(0, 4));
			Integer nroLargo = new Integer(nroCteHidden.substring(5, 13));
			Proveedor prov;

			try {
				prov = proveedoresService.getProveedorService().buscarProveedor(cuit);
			} catch (ProveedorException e) {
				e.printStackTrace();
				return null;
			}

			List listComp;
			Comprobante comp;
			try {
				Filtro filtroComp = new Filtro();
				filtroComp.agregarCampoOperValor("nroCorto", Filtro.IGUAL, nroCorto);
				filtroComp.agregarCampoOperValor("nroLargo", Filtro.IGUAL, nroLargo);
				filtroComp.agregarCampoOperValor("proveedor.idProveedor", Filtro.IGUAL, prov.getIdProveedor());
				filtroComp.agregarCampoOperValor("tipoComprobante.descripcionLarga", Filtro.LIKE, tipoCteHidden);
				log.info("Buscar Comprobante: " + filtroComp.getHQL());
				listComp = comprobanteDao.listarTodos(filtroComp);
				comp = (Comprobante) listComp.get(0);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			if (comp.getTipoComprobante().getDescripcionCorta().equals("OP") && comp.getCompRevertido() != null) {
				error.agregar("Orden de Pago revertida, no se pueden mostrar detalles.");
			} else if (comp.getTipoComprobante().getDescripcionCorta().equals("ROP")) {
				error.agregar("Reversion de Orden de Pago, no se pueden mostrar detalles.");
			} else if (comp.getTipoComprobante().getDescripcionCorta().equals("OP")) {
				ImputacionBean impBean = (ImputacionBean) Session.getBean("ImputacionBean");
				impBean.setCuitFiltro(cuit);
				impBean.setNroOrdenComprobante(nroLargo.toString());
				impBean.setFechaDesde(fechaDesde);
				impBean.setFechaHasta(fechaHasta);
				impBean.filtrarImputaciones();
			} else {
				ComprobanteBean compBean = (ComprobanteBean) Session.getBean("ComprobanteBean");
				compBean.setIdHidden(new Integer(comp.getIdComprobante().toString()));
				ret = compBean.mostrarComprobante();
			}
		}

		return ret;
	}


	/* @I5562 */public String mostrarAdjunto() {
		log.info("mostrar idComprobante: " + this.idComprobanteHidden);
		if (this.idComprobanteHidden != null && !this.idComprobanteHidden.equals(0L)) {
			try {
				Comprobante comprobante = proveedoresService.getComprobanteService().leerComprobante(this.idComprobanteHidden);
				if (comprobante.getTipoComprobante().getIdTipoComprobante().longValue() == 1) { // si es orden de pago
					mostrarAdjuntoOrdenPago(comprobante);
				} else { // si no es orden de pago
					mostrarAdjuntoFactura(comprobante);

				}
			} catch (ComprobanteException e) {
				error.agregar("Error al intentar leer el comprobante con id " + idComprobanteHidden + " intentar nuevamente");
				e.printStackTrace();
			}
		} else {
			error.agregar("El comprobante seleccionado no posee un documento adjunto.");
		}
		return null;
	}


	private void mostrarAdjuntoFactura(Comprobante comprobante) {
		if (comprobante.getDocAdjuntos().size() != 0) {
			DocumentoAdjunto documentoAdjunto = null;
			for (Object obj : comprobante.getDocAdjuntos()) {
				documentoAdjunto = (DocumentoAdjunto) obj;
				break; // hacemos break por que un comprobante tiene un unico adjunto, la relacion es 1 a 1
			}

			ejecutarJavaScript("popup('" + "/../archivos/" + Archivo.archivosDeProveedores + "/" + documentoAdjunto.getUrl()
					+ "',1000,700), 'titlebar=no';");

		} else {
			error.agregar("El comprobante seleccionado no posee un documento adjunto.");
		}
	}


	private void mostrarAdjuntoOrdenPago(Comprobante comprobante) {
		HttpServletRequest request = Session.getRequest();
		if (comprobante != null && comprobante.getIdComprobante() != null) {
			String p1 = "?id_Comp=" + comprobante.getIdComprobante();
			String p2 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p3 = "&id_operador=" + Session.getOperador().getId();
			String page = request.getContextPath() + "/report/Imprimir_OP.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";
		}
	}


	/* @F5562 */
	public String inicializar() {
		borrar();
		return "listarCtaCte";
	}


	public void inicializar(String cuit, Date fechaDesde, Date fechaHasta) {
		borrar();
		this.cuit = cuit;
		this.cuitBusqueda = cuit;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		// generar();
	}
}