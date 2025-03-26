package com.bizitglobal.webapp.faces.beans.proveedores;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteImputadoException;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuitNoValidoException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ComprobanteImputado;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuitValido;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuotaComprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorDomicilio;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.service.proveedores.ProveedoresServiceFaces;
import com.bizitglobal.webapp.faces.util.CuotasImputables;
import com.bizitglobal.webapp.faces.util.Imputacion;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Error;


@SuppressWarnings({"rawtypes","unchecked"})
public class ImputacionBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ImputacionBean.class);
	private ProveedoresServiceFaces service = new ProveedoresServiceFaces();

	private Long idProveedor;
	private String razonSocial;
	private String domicilioLegal;
	private String cuit;
	private String cuitBusqueda;
	private String cuitAux;

	private List cuotasComprobates; // Lista de comprobantes aún no cancelados.
	private List cuotasOrdenes; // Lista de ordenes de pago aún no canceladas.

	private boolean validado;
	private String cuitInvalido;

	// Listas para seleccionar los comprobantes.
	private List listaOrdenesPago;
	private List listaComprobantes;

	// Propiedades para las imputaciones.
	private List imputaciones;

	// Lista que almacena las imputaciones eliminadas para luego borrarlas de la base de datos.
	private List borrar;

	// Propiedades para el filtro de imputaciones.
	private String cuitFiltro;
	private String nroOrdenComprobante;
	private Date fechaDesde;
	private Date fechaHasta;

	// Propiedades para el listado de imputaciones.
	private Long idImputacionHidden;

	// Propiedades para poder llamar a la pagina interna de imputaciones desde otra pagina(calendario de pagos).
	private String rutaRedireccion;


	public ImputacionBean() {
		super();
		error.borrar(); // Borrar los errores del bean.
		idProveedor = null;
		razonSocial = null;
		domicilioLegal = null;
		borrar = new ArrayList(); // Inicializamos la lista de imputaciones borradas.
		cuitBusqueda = null;
		cuit = null;
		imputaciones = new ArrayList();
		validado = false;
		tituloLargo = "Tarjeta Fiel - Imputación";
		tituloCorto = "Imputar";
		rutaRedireccion = "/tarjetafiel/proveedores/imputaciones/imputaciones.jsf";
	}


	/**
	 * Llamado desde el menú, permite inicilizar el contenido del bean cada vez que se invoque.
	 */
	public String inicializar() {
		borrar();
		super.borrarBaseBean();

		if (Session.getBean("BuscarProveedorBean") != null) {
			BuscarProveedorBean bean = (BuscarProveedorBean) Session.getBean("BuscarProveedorBean");
			bean.borrar();
		}

		Session.redirect(rutaRedireccion);
		return null;
	}


	public String getDomicilioLegal() {
		return domicilioLegal;
	}


	public void setDomicilioLegal(String domicilioLegal) {
		this.domicilioLegal = domicilioLegal;
	}


	public Long getIdProveedor() {
		return idProveedor;
	}


	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public boolean getValidado() {
		return validado;
	}


	public void setValidado(boolean validado) {
		this.validado = validado;
	}


	public String getCuitInvalido() {
		return cuitInvalido;
	}


	public void setCuitInvalido(String cuitInvalido) {
		this.cuitInvalido = cuitInvalido;
	}


	public List getCuotasComprobates() {
		return cuotasComprobates;
	}


	public void setCuotasComprobates(List cuotasComprobates) {
		this.cuotasComprobates = cuotasComprobates;
	}


	public List getCuotasOrdenes() {
		return cuotasOrdenes;
	}


	public void setCuotasOrdenes(List cuotasOrdenes) {
		this.cuotasOrdenes = cuotasOrdenes;
	}


	public List getImputaciones() {
		return imputaciones;
	}


	public void setImputaciones(List imputaciones) {
		this.imputaciones = imputaciones;
	}


	public List getListaComprobantes() {
		return listaComprobantes;
	}


	public void setListaComprobantes(List listaComprobantes) {
		this.listaComprobantes = listaComprobantes;
	}


	public List getListaOrdenesPago() {
		return listaOrdenesPago;
	}


	public void setListaOrdenesPago(List listaOrdenesPago) {
		this.listaOrdenesPago = listaOrdenesPago;
	}


	public String getCuit() {
		log.info("getCuit(): " + cuit);
		if (cuitAux != null && !cuitAux.equals(""))
			cuit = cuitAux;
		return cuit;
	}


	public void setCuit(String cuit) {
		if (cuitBusqueda != null && !cuitBusqueda.equals("")) {
			this.cuit = cuitBusqueda;
			cuitBusqueda = null;
		} else {
			this.cuit = cuit;
		}
		if (cuit != null && !cuit.equals(""))
			cuitAux = cuit;
		log.info("setCuit(): " + this.cuit);
	}


	public String getCuitBusqueda() {
		return cuitBusqueda;
	}


	public void setCuitBusqueda(String cuitBusqueda) {
		this.cuitBusqueda = cuitBusqueda;
	}


	public String getCuitFiltro() {
		log.info("getCuitFiltro(): " + cuitFiltro);
		if (cuitAux != null && !cuitAux.equals(""))
			cuitFiltro = cuitAux;
		return cuitFiltro;
	}


	public void setCuitFiltro(String cuitFiltro) {
		this.cuitFiltro = cuitFiltro;
		if (cuitFiltro != null && !cuitFiltro.equals(""))
			cuitAux = this.cuitFiltro;
		log.info("setCuitFiltro(): " + this.cuitFiltro);
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


	public Long getIdImputacionHidden() {
		return idImputacionHidden;
	}


	public void setIdImputacionHidden(Long idImputacionHidden) {
		this.idImputacionHidden = idImputacionHidden;
	}


	public String getNroOrdenComprobante() {
		return nroOrdenComprobante;
	}


	public void setNroOrdenComprobante(String nroOrdenComprobante) {
		this.nroOrdenComprobante = nroOrdenComprobante;
	}


	public void borrar() {
		super.borrarBaseBean();
		idProveedor = null;
		cuit = null;
		cuitBusqueda = null;
		razonSocial = null;
		domicilioLegal = null;
		validado = false;
		borrar = new ArrayList();
		cuitInvalido = null;
		imputaciones = new ArrayList();
		cuitFiltro = null;
		nroOrdenComprobante = null;
		Calendar fecha = Calendar.getInstance();
		fechaHasta = new Timestamp(fecha.getTime().getTime());
		fecha.add(Calendar.MONTH, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());

		tituloLargo = "Tarjeta Fiel - Imputación";
		tituloCorto = "Imputar";
		rutaRedireccion = "/tarjetafiel/proveedores/imputaciones/imputaciones.jsf";
		cuitAux = "";
	}


	/*
	 * ACCIONES PARA EL BEAN
	 */

	public void validarCuit(ActionEvent event) {
		log.info("Entrando a validar cuit");
		if (cuit != null) {
			try {
				CuitValido cuitValido = new CuitValido(this.cuit);
				Long cuit = new Long(this.cuit);

				List proveedores = service.getProveedorService().getProveedores(new Filtro("cuit", Filtro.IGUAL, cuit));
				Proveedor proveedor = null;
				if (!proveedores.isEmpty()) {
					proveedor = (Proveedor) proveedores.get(0);
				}

				List domicilios = Convertidores.setToList(proveedor.getDomicilios());
				if (!domicilios.isEmpty()) {
					Domicilio domicilio = ((ProveedorDomicilio) domicilios.get(0)).getDomicilio();
					domicilioLegal = domicilio.getCalleNombre() + " " + domicilio.getCalleNumero();
				}

				razonSocial = proveedor.getRazonSocial();
				idProveedor = proveedor.getIdProveedor();
				this.cuit = proveedor.getCuit().toString();

				log.info("Buscando comprobantes para el proveedor -> " + idProveedor);

				List comprobantes = service.getComprobanteService().getComprobantesNoCanceladosSec(idProveedor).getComprobantes();
				List ordenes = service.getComprobanteService().getOrdenesNoCanceladasSec(idProveedor).getComprobantes();
				log.info("Tamaño Comprobantes->" + comprobantes.size());
				log.info("Tamaño Ordenes->" + ordenes.size());

				Object[] comprobantesResult = ImputacionUtil.cargarCuotasImputables(comprobantes);
				Object[] ordenesResult = ImputacionUtil.cargarCuotasImputables(ordenes);

				cuotasComprobates = (List) comprobantesResult[0];
				cuotasOrdenes = (List) ordenesResult[0];

				listaComprobantes = (List) comprobantesResult[1];
				listaOrdenesPago = (List) ordenesResult[1];

				// List listaImputaciones = (List)comprobantesResult[2];
				// imputaciones = ImputacionUtil.generarImputaciones(listaOrdenesPago,listaComprobantes,listaImputaciones);

				this.cuitBusqueda = this.cuit;

				validado = true;
			} catch (CuitNoValidoException e1) {
				cuitInvalido = "El número de CUIT es invalido.";
				e1.printStackTrace();
			} catch (ProveedorException e2) {
				cuitInvalido = "Error al cargar proveedor.";
				e2.printStackTrace();
			} catch (ComprobanteException e3) {
				cuitInvalido = "Error al cargar comprobante.";
				e3.printStackTrace();
			} catch (Exception e4) {
				cuitInvalido = "Error al convertir.";
				e4.printStackTrace();
			}
		}

		Session.redirect(rutaRedireccion);
	}


	public String cancelarImputado() {
		borrar();
		Session.redirect(rutaRedireccion);
		return null;
	}


	public String grabarImputado() {
		if (!imputaciones.isEmpty()) {
			Iterator iter = imputaciones.iterator();
			try {
				if (validar()) {
					while (iter.hasNext()) {
						Imputacion imp = (Imputacion) iter.next();
						ComprobanteImputado aux = new ComprobanteImputado();
						CuotaComprobante cuotaD = service.getCuotaComprobanteDao().buscarCuotaComprobante(
								imp.getCuotasImpComprobante().getCuota().getIdCuotaComprobante());
						CuotaComprobante cuotaH = service.getCuotaComprobanteDao().buscarCuotaComprobante(
								imp.getCuotasImpOrden().getCuota().getIdCuotaComprobante());

						aux.setIdComprobanteImputado(imp.getIdImputacion());
						aux.setCuotaComprobanteD(cuotaD);
						aux.setCuotaComprobanteH(cuotaH);
						aux.setFechaEmision(new Timestamp(new Date().getTime()));
						aux.setImporteCancela(new BigDecimal(imp.getMonto().toString()));

						if (!imp.getIdImputacion().equals(new Long(0))) {
							service.getComprobanteImputadoService().actualizarComprobanteImputado(aux);
							log.info("Actualizando imputacion!!!");
						} else {
							service.getComprobanteImputadoService().grabarComprobanteImputado(aux);
							log.info("Grabando imputacion!!!");
						}
					}
				} else {
					if (Session.getBean("ScrollBean") != null) {
						ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
						bean.setHiddenScrollY(new Integer(0));
					}
					Session.redirect(rutaRedireccion);
					return null;
				}

				log.info("Grabando las imputaciones");
				popup.setPopup(popup.ICONO_OK, "La imputación ha sido almacenada exitosamente.");

			} catch (Exception e) {
				e.printStackTrace();
				popup.setPopup(popup.ICONO_FALLA, "Falla el alta de la imputación.");
			}
		}

		Session.redirect(rutaRedireccion);
		return null;
	}


	public String eliminarImputacion() {
		log.info("entrando a eliminar imputacion.");
		Long idComprobante = new Long(Session.getRequestParameter("idComprobante"));
		Long idOrden = new Long(Session.getRequestParameter("idOrden"));
		List result = new ArrayList();
		if (!imputaciones.isEmpty()) {
			Iterator iter = imputaciones.iterator();
			while (iter.hasNext()) {
				Imputacion aux = (Imputacion) iter.next();
				if (!(aux.getCuotasImpComprobante().getIdImputable().equals(idComprobante)
				&& aux.getCuotasImpOrden().getIdImputable().equals(idOrden))) {
					result.add(aux);
				}
			}
			imputaciones = result;
		}

		Session.redirect(rutaRedireccion);
		return null;
	}


	public String generar() {
		error.borrar();
		Vector comprobantesSeleccionados = Session.getCheckboxVector("checkComprobantes");
		Vector ordenesSeleccionadas = Session.getCheckboxVector("checkOrdenes");
		imputaciones.clear();
		if ((comprobantesSeleccionados.size() > 0) && (ordenesSeleccionadas.size() > 0)) {
			for (int i = 0; i < comprobantesSeleccionados.size(); i++) {
				for (int j = 0; j < ordenesSeleccionadas.size(); j++) {
					Imputacion aux = new Imputacion();
					// aux.setComprobante(new Long(comprobantesSeleccionados.get(i).toString()));
					int index = cuotasComprobates.indexOf(new CuotasImputables(comprobantesSeleccionados.get(i).toString()));
					aux.setCuotasImpComprobante((CuotasImputables) cuotasComprobates.get(index));
					// aux.setOrden(new Long(ordenesSeleccionadas.get(j).toString()));
					index = cuotasOrdenes.indexOf(new CuotasImputables(ordenesSeleccionadas.get(j).toString()));
					aux.setCuotasImpOrden((CuotasImputables) cuotasOrdenes.get(index));
					imputaciones.add(aux);
				}
			}
		} else {
			error.agregar("Debe seleccionar por lo menos una cuota y una orden");
		}

		Session.redirect(rutaRedireccion);
		return null;
	}


	public boolean validar() {
		error.borrar();
		if (!imputaciones.isEmpty()) {
			Iterator iter = imputaciones.iterator();
			while (iter.hasNext()) {
				Imputacion aux = (Imputacion) iter.next();
				if (aux.getMonto().equals(new Float(0))) {
					error.agregar(Error.IMPUTACION_MONTOS_REQUERIDO);
				}
				float sumaParaComprobantes = 0;
				float sumaParaOrdenes = 0;
				Iterator iter2 = imputaciones.iterator();
				while (iter2.hasNext()) {
					Imputacion aux2 = (Imputacion) iter2.next();
					if (aux.getCuotasImpComprobante().equals(aux2.getCuotasImpComprobante())) {
						sumaParaComprobantes += aux2.getMonto().floatValue();
					}
					if (aux.getCuotasImpOrden().equals(aux2.getCuotasImpOrden())) {
						sumaParaOrdenes += aux2.getMonto().floatValue();
					}
				}

				// while(iter2.hasNext()) {
				// Imputacion aux2 = (Imputacion)iter2.next();
				// if(aux.getComprobante().equals(aux2.getComprobante())) {
				// sumaParaComprobantes += aux.getMonto().floatValue();
				// }
				//
				// if(aux.getOrden().equals(aux2.getOrden())) {
				// sumaParaOrdenes += aux.getMonto().floatValue();
				// }
				// }
				//
				// CuotaComprobante cuotaD = service.getCuotaComprobanteDao().buscarCuotaComprobante(aux.getComprobante());
				// CuotaComprobante cuotaH = service.getCuotaComprobanteDao().buscarCuotaComprobante(aux.getOrden());
				//
				// if(cuotaD.getImporte().floatValue() >= sumaParaComprobantes &&
				// cuotaH.getImporte().floatValue() >= sumaParaOrdenes) {
				// result = result && true;
				// } else {
				// result = false;
				// error.agregar(Error.IMPUTACION_IMPOSIBLE_IMPUTAR+aux.getComprobanteText()+"<->"+aux.getOrdenText());
				// }
				if (aux.getCuotasImpComprobante().getResto().floatValue() < sumaParaComprobantes) {
					error.agregar(Error.IMPUTACION_CUOTA_SUPERADA + ": " + aux.getComprobanteText());
				}
				if (aux.getCuotasImpOrden().getCuenta().floatValue() < sumaParaOrdenes) {
					error.agregar(Error.IMPUTACION_OP_PAGO_A_CUANTA_SUPERADO + ": " + aux.getOrdenText());
				}
			}
			// Validacion que verifica si no se modificaron las imputaciones en otra ventana
			if (error.cantidad() == 0) {
				iter = imputaciones.iterator();
				List imputacionesEchas;
				while (iter.hasNext()) {
					Imputacion imputacion = (Imputacion) iter.next();
					CuotaComprobante cuota = imputacion.getCuotasImpComprobante().getCuota();
					imputacionesEchas = proveedoresService.getComprobanteImputadoDao().listarTodos(
							new Filtro("cuotaComprobanteD.idCuotaComprobante", Filtro.IGUAL, cuota.getIdCuotaComprobante()));
					if (imputacionesEchas.size() != cuota.getCuotaComprobanteD().size()) {
						borrar();
						error.agregar(Error.IMPUTACION_SE_MODIFICO);
						return false;
					}
					cuota = imputacion.getCuotasImpOrden().getCuota();
					imputacionesEchas = proveedoresService.getComprobanteImputadoDao().listarTodos(
							new Filtro("cuotaComprobanteH.idCuotaComprobante", Filtro.IGUAL, cuota.getIdCuotaComprobante()));
					if (imputacionesEchas.size() != cuota.getCuotaComprobanteH().size()) {
						borrar();
						error.agregar(Error.IMPUTACION_SE_MODIFICO);
						return false;
					}
				}
			}
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevaImputacion() {
		borrar();
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		Session.redirect(rutaRedireccion);
		return null;
	}


	public String irAContinuarImputacion() {
		String cuitAux = this.cuit;
		inicializar();
		this.cuit = cuitAux;
		validarCuit(null);
		return null;
	}


	public String irAListarImputaciones() {
		borrar();
		tituloCorto = "Listado de imputaciones";
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		Session.redirect("/tarjetafiel/proveedores/imputaciones/listarImputaciones.jsf");
		return null;
	}


	public String filtrarImputaciones() {
		log.info("filtrando imputaciones!!!");

		Filtro filtro = new Filtro();
		Format dateFormat = new SimpleDateFormat("''dd/MM/yyyy''");
		Format datetimeFormat = new SimpleDateFormat("''dd/MM/yyyy HH:mm:ss''");

		// filtro.agregarCampoOperValor("cuotaComprobanteH.activo", Filtro.IGUAL, "'S'");

		if (nroOrdenComprobante.length() > 0) {
			filtro.agregarCampoOperValor("cuotaComprobanteH.comprobante.nroLargo", Filtro.IGUAL, nroOrdenComprobante);
		}

		if (fechaDesde != null) {
			filtro.agregarCampoOperValor("fechaEmision", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(new Timestamp(fechaDesde.getTime())));
		}

		if (fechaHasta != null) {
			filtro.agregarCampoOperValor("fechaEmision", Filtro.MENOR, Filtro.getTO_DATE(Fecha.addDias(new Timestamp(fechaHasta.getTime()), 1)));
		}

		if (cuitFiltro != null && !cuitFiltro.equals("")) {
			// Filtro para el cuit
			String add = (filtro.estaVacio()) ? "WHERE " : " AND ";
			String cuitFiltroH = "obj.cuotaComprobanteH.comprobante.proveedor.cuit LIKE '%" + cuitFiltro + "%'";
			// String cuitFiltroD = "obj.cuotaComprobanteD.comprobante.proveedor.cuit LIKE '%"+cuitFiltro+"%'";
			String cuitFiltro = add + "(" + cuitFiltroH + ")";// + " OR "+ cuitFiltroD +")";
			filtro.agregarfuncion(cuitFiltro);
		}

		log.info("Filtro -> " + filtro.getHQL());
		imputaciones = service.getComprobanteImputadoDao().listarTodos(filtro);
		log.info("Imputado Ok!!!!");
		imputaciones = ImputacionUtil.generarImputacionesListado(imputaciones);

		Session.redirect("/tarjetafiel/proveedores/imputaciones/listarImputaciones.jsf");
		return null;
	}


	// Elimina una imputacion del listado.
	public String eliminarImputacionListado() {
		error.borrar();
		try {
			ComprobanteImputado compImputado = service.getComprobanteImputadoService().leerComprobanteImputado(idImputacionHidden);
			if (compImputado.getCuotaComprobanteH().getComprobante().getEnFondos().equals(new Character('S'))) {
				error.agregar(Error.IMPUTACION_OP_EN_FONDOS);
				Session.redirect("/tarjetafiel/proveedores/imputaciones/listarImputaciones.jsf");
				return null;
			}
			service.getComprobanteImputadoService().borrarComprobanteImputado(idImputacionHidden);
		} catch (ComprobanteImputadoException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		filtrarImputaciones();
		Session.redirect("/tarjetafiel/proveedores/imputaciones/listarImputaciones.jsf");
		return null;
	}


	public String buscarProveedorPopup() {
		log.info("Buscar proveedor!!!");
		BuscarProveedorBean bean = (BuscarProveedorBean) Session.getBean("BuscarProveedorBean");
		bean.inicializar(BuscarProveedorBean.IMPUTACION);

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/proveedores/popup/buscarProveedor.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}


	public String buscarProvPopup() {
		log.info("Buscar proveedor!!!");
		BuscarProveedorBean prov = (BuscarProveedorBean) Session.getBean("BuscarProveedorBean");
		prov.inicializar(BuscarProveedorBean.IMPUTACION_ALTA);

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/proveedores/popup/buscarProveedor.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}


	public String volverCtaCte() {
		log.info("Volver a cta. cte - cuit: " + cuitFiltro);

		if (!cuitFiltro.equals("")) {
			ProveedorCtaCteBean proveedorCtaCteBean = (ProveedorCtaCteBean) Session.getBean("ProveedorCtaCteBean");
			proveedorCtaCteBean.setTablaCtaCte(new ArrayList());
			proveedorCtaCteBean.setCuit(cuitFiltro);
			proveedorCtaCteBean.setCuitBusqueda(cuitFiltro);
			proveedorCtaCteBean.setFechaDesde(fechaDesde);
			proveedorCtaCteBean.setFechaHasta(fechaHasta);
			// proveedorCtaCteBean.inicializar(cuitFiltro,fechaDesde,fechaHasta);
			Session.redirect("/tarjetafiel/proveedores/listarCtaCte.jsf");
		}
		else
			Session.redirect("/tarjetafiel/proveedores/listarCtaCte.jsf");

		return null;
	}


	public String getRutaRedireccion() {
		return rutaRedireccion;
	}


	public void setRutaRedireccion(String rutaRedireccion) {
		this.rutaRedireccion = rutaRedireccion;
	}
}
