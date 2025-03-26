package com.bizitglobal.webapp.faces.beans.proveedores;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.exception.AsientoFondosException;
import com.bizitglobal.tarjetafiel.fondos.exception.BancoPropioException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaAperturaException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeEstadoException;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoFondos;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoItem;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaApertura;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeHistorial;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;
import com.bizitglobal.tarjetafiel.general.exception.ConceptoGenException;
import com.bizitglobal.tarjetafiel.general.exception.TipoComprobanteException;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoDetalleGen;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;
import com.bizitglobal.tarjetafiel.general.negocio.TipoComprobante;
import com.bizitglobal.tarjetafiel.general.negocio.TipoCuentaBanco;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Exclusion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Retencion;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteException;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuitNoValidoException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorNotFoundException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.AsientoContable;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Comprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ComprobanteImputado;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ComprobantesNoCancelados;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuitValido;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuotaComprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorDomicilio;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorFormaPago;
import com.bizitglobal.tarjetafiel.transacciones.exception.LiqComercioException;
import com.bizitglobal.webapp.faces.beans.proveedores.wrappers.RetencionUtil;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class OrdenPagoBean extends ComprobanteBean {
	private static final Logger log = Logger.getLogger(OrdenPagoBean.class);

	private Timestamp fechaPago;

	private BigDecimal pagoACuenta;
	private BigDecimal importeNeto;
	private BigDecimal importeTotal;
	private BigDecimal totalImpuestos;
	private BigDecimal totalExclusiones;
	private BigDecimal xCienExclusion;
	private BigDecimal totalPagosMes;
	private BigDecimal totalRetencionesMes;
	private float imputableAnt = 0;

	private String nroCortoString;
	private String nroLargoString;

	private List cuotasNoImputadas;
	private List tablaImputaciones;
	private List tablaRetenciones;
	// private List tablaExclusiones;

	private List tablaDeFormaDePago;

	private CuitValido cuitValido;
	private String cuit;
	private String cuitBusqueda;

	private boolean verRetenciones;
	//
	private Comprobante comprobante = null;
	private boolean alta = true;
	private String tituloLargo = "Tarjeta Fiel - Orden de pago";
	private String tituloCorto = "Alta de ordenes de pago";

	private String popupReport = new String("");
	private boolean yaImprimio = true;
	ConceptoGen conceptoFondos;


	public OrdenPagoBean() {
		super();
		borrar();
		try {
			conceptoFondos = (ConceptoGen) (generalService.getConceptoGenService().getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, new Long(
					421)))).get(0);
		} catch (ConceptoGenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tituloLargo = "Tarjeta Fiel - Orden de pago";
		tituloCorto = "Alta de ordenes de pago";
	}


	/**
	 * Llamado desde el menú, permite inicilizar el contenido del bean cada vez que se invoque.
	 */
	public String inicializar() {
		borrarBaseBean();
		borrar();

		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}

		return "altaComprobanteOP";
	}


	// metodos de al clase

	public String getPopupReport() {
		if (yaImprimio) {
			return "";
		}
		yaImprimio = true;
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public BigDecimal getTotalPagosMes() {
		return totalPagosMes;
	}


	public BigDecimal getTotalRetencionesMes() {
		return totalRetencionesMes;
	}


	public List getTablaDeFormaDePago() {
		return tablaDeFormaDePago;
	}


	public void setTablaDeFormaDePago(List tablaDeFormaDePago) {
		this.tablaDeFormaDePago = tablaDeFormaDePago;
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
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


	public boolean getVerRetenciones() {
		return verRetenciones;
	}


	public void setVerRetenciones(boolean verRetenciones) {
		this.verRetenciones = verRetenciones;
	}


	public Integer getNroCompLargo() {
		return super.getNroCompLargo();
	}


	public void setNroCompLargo(Integer nroCompLargo) {
		super.setNroCompLargo(nroCompLargo);
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


	public String getCuitBusqueda() {
		return cuitBusqueda;
	}


	public void setCuitBusqueda(String cuitBusqueda) {
		this.cuitBusqueda = cuitBusqueda;
	}


	public List getTablaImputaciones() {
		return tablaImputaciones;
	}


	public void setTablaImputaciones(List tablaImputaciones) {
		this.tablaImputaciones = tablaImputaciones;
	}


	public List getCuotasNoImputadas() {
		return cuotasNoImputadas;
	}


	public void setCuotasNoImputadas(List cuotasNoImputadas) {
		this.cuotasNoImputadas = cuotasNoImputadas;
	}


	public List getComprobantes() {
		return super.getComprobantes();
	}


	public void setComprobantes(List comprobantes) {
		super.setComprobantes(comprobantes);
	}


	public void setProveedorSeleccionado(Long proveedorSeleccionado) {
		super.setProveedorSeleccionado(proveedorSeleccionado);
	}


	public Long getProveedorSeleccionado() {
		return super.getProveedorSeleccionado();
	}


	public void setProveedor(Proveedor proveedor) {
		super.setProveedor(proveedor);
	}


	public Proveedor getProveedor() {
		return super.getProveedor();
	}


	public void setCuitIdentificador(String cuitIdentificador) {
		super.setCuitIdentificador(cuitIdentificador);
	}


	public String getCuitIdentificador() {
		return super.getCuitIdentificador();
	}


	public void setCuitDni(String cuitDni) {
		super.setCuitDni(cuitDni);
	}


	public String getCuitDni() {
		return super.getCuitDni();
	}


	public void setCuitVerificador(String cuitVerificador) {
		super.setCuitVerificador(cuitVerificador);
	}


	public String getCuitVerificador() {
		return super.getCuitVerificador();
	}


	public void setTipoComprobante(TipoComprobante tipoComprobante) {
		super.setTipoComprobante(tipoComprobante);
	}


	public TipoComprobante getTipoComprobante() {
		return super.getTipoComprobante();
	}


	public String getNroCortoString() {
		return nroCortoString;
	}


	public void setNroCortoString(String nroCortoString) {
		this.nroCortoString = nroCortoString;
	}


	public String getNroLargoString() {
		return nroLargoString;
	}


	public void setNroLargoString(String nroLargoString) {
		this.nroLargoString = nroLargoString;
	}


	public void setFechaEmision(Date fechaEmision) {
		super.setFechaEmision(fechaEmision);
	}


	public Date getFechaEmision() {
		return super.getFechaEmision();
	}


	public Date getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(Date fechaPago) {
		this.fechaPago = new Timestamp(fechaPago.getTime());
	}


	public BigDecimal getPagoACuenta() {
		return pagoACuenta;
	}


	public void setPagoACuenta(BigDecimal pagoACuenta) {
		this.pagoACuenta = pagoACuenta.abs();
	}


	public String getCuitComprobanteFiltro() {
		return super.getCuitComprobanteFiltro();
	}


	public void setCuitComprobanteFiltro(String cuitComprobanteFiltro) {
		super.setCuitComprobanteFiltro(cuitComprobanteFiltro);
	}


	public String getRazonSocialFiltro() {
		return super.getRazonSocialFiltro();
	}


	public void setRazonSocialFiltro(String razonSocialFiltro) {
		super.setRazonSocialFiltro(razonSocialFiltro);
	}


	public String getNombreFantasiaFiltro() {
		return super.getNombreFantasiaFiltro();
	}


	public void setNombreFantasiaFiltro(String nombreFantasiaFiltro) {
		super.setNombreFantasiaFiltro(nombreFantasiaFiltro);
	}


	public String getAliasFiltro() {
		return super.getAliasFiltro();
	}


	public void setAliasFiltro(String aliasFiltro) {
		super.setAliasFiltro(aliasFiltro);
	}


	public Date getFechaDesde() {
		return super.getFechaDesde();
	}


	public void setFechaDesde(Date fechaDesde) {
		super.setFechaDesde(fechaDesde);
	}


	public Date getFechaHasta() {
		return super.getFechaHasta();
	}


	public void setFechaHasta(Date fechaHasta) {
		super.setFechaHasta(fechaHasta);
	}


	public BigDecimal getImporteTotal() {
		this.importeTotal = importeNeto.subtract(totalImpuestos);
		// this.importeTotal = importeTotal.add(totalExclusiones);
		this.importeTotal = importeTotal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		log.info("Importe Total: " + importeTotal);
		return this.importeTotal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}


	public BigDecimal getImporteNeto() {
		importeNeto = new BigDecimal(0);
		if (!tablaImputaciones.isEmpty()) {
			Iterator iterTabla = tablaImputaciones.iterator();
			while (iterTabla.hasNext()) {
				Imputacion imputacion = (Imputacion) iterTabla.next();
				if (imputacion.getSeleccionado()) {
					importeNeto = importeNeto.add(new BigDecimal(imputacion.getMontoAImputar().floatValue()));
				}
			}
		}
		if (pagoACuenta != null) {
			importeNeto = importeNeto.add(pagoACuenta);
		}
		importeNeto = importeNeto.setScale(2, BigDecimal.ROUND_HALF_DOWN);

		return importeNeto;
	}


	public void setTotalImpuestos(BigDecimal totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}


	public BigDecimal getTotalImpuestos() {
		calculoRetencion();
		totalImpuestos = totalImpuestos.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return totalImpuestos;
	}


	public BigDecimal getTotalExclusiones() {
		totalExclusiones = new BigDecimal(0);
		// totalExclusiones = totalImpuestos.multiply(xCienExclusion).divide(new BigDecimal(100));
		if (!tablaRetenciones.isEmpty()) {
			RetencionUtil retencionUtil;
			Iterator iterTReten = tablaRetenciones.iterator();
			while (iterTReten.hasNext()) {
				retencionUtil = (RetencionUtil) iterTReten.next();
				totalExclusiones = totalExclusiones.add(retencionUtil.getTotalExcluido());
			}
		}
		totalExclusiones = totalExclusiones.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return totalExclusiones;
	}


	public List getTablaRetenciones() {
		return tablaRetenciones;
	}


	public void setTablaRetenciones(List tablaRetenciones) {
		this.tablaRetenciones = tablaRetenciones;
	}


	// public List getTablaExclusiones() {
	// return tablaExclusiones;
	// }
	//
	// public void setTablaExclusiones(List tablaExclusiones) {
	// this.tablaExclusiones = tablaExclusiones;
	// }

	public String getDireccionProveedor() {
		String resul = null;
		Iterator iter = getProveedor().getDomicilios().iterator();
		while (iter.hasNext()) {
			ProveedorDomicilio element = (ProveedorDomicilio) iter.next();
			if (element.getDomicilio().getTipoDomicilio().getIdTipoDomicilio().equals(new Long(1))) {
				return element.getDomicilio().getCalleNombre() + " " + element.getDomicilio().getCalleNumero();
			}
		}
		return resul;
	}


	public boolean getVerPopup() {
		if (popup.getMostrar() || verRetenciones)
			return true;
		else
			return false;
	}


	// Metodos para el bean de ordenes de pago

	public String crearOrdenPago() {
		error.borrar();

		String result = null;
		if (cuit.equals(null) || cuit.equals("")) {
			error.agregar(Error.PROVEEDOR_CUIT_REQUERIDO);
			return null;
		}

		try {
			cuitValido = new CuitValido(cuit);
		} catch (CuitNoValidoException e1) {
			error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
			return null;
		} catch (Exception e) {
			error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
			return null;
		}

		// busco el proveedor al que de la cargara la orden de pago
		try {
			Proveedor proveedor = proveedoresService.getProveedorService().buscarProveedor(cuit);
			proveedor.getProvedorCategoria();
			if (!proveedor.getActivo().equals(new Character('S'))) {
				error.agregar(Error.PROVEEDOR_DESACTIVO);
				return null;
			}
			setProveedor(proveedor);
			cuitValido = new CuitValido(getProveedor().getCuit().toString());
			setCuitIdentificador(cuitValido.getIdentificador());
			setCuitDni(cuitValido.getDni());
			setCuitVerificador(cuitValido.getVerificador());

			// Cargo la tabla con las formas de pago
			List formPago = Convertidores.setToList(proveedor.getFormasDePago());
			if (formPago != null && !formPago.isEmpty()) {
				Iterator iter = formPago.iterator();
				boolean select = true;
				while (iter.hasNext()) {
					ProveedorFormaPago formaPago = (ProveedorFormaPago) iter.next();
					if (formaPago.getEsActivo().equals(new String("S"))) {
						formaPago.getFormaPago().getDescripcion();
						if (formaPago.getBanco() == null) {
							formaPago.setBanco(new Banco());
						} else {
							formaPago.getBanco().getDescripcion();
						}
						if (formaPago.getTipoCta() == null) {
							formaPago.setTipoCta(new TipoCuentaBanco());
						} else {
							formaPago.getTipoCta().getDescripcion();
						}
						if (select) {
							tablaDeFormaDePago.add(new FormaPagoOP(formaPago, select));
							select = false;
						} else {
							tablaDeFormaDePago.add(new FormaPagoOP(formaPago, select));
						}
					}
				}
			} else {
				error.agregar(Error.PROVEEDOR_FALTA_FORMA_PAGO);
				return null;
			}

		} catch (ProveedorNotFoundException e1) {
			error.agregar(Error.PROVEEDOR_NO_EXISTE);
			e1.printStackTrace();
			return null;
		} catch (Exception e2) {
			error.agregar(Error.PROVEEDOR_NO_EXISTE);
			e2.printStackTrace();
			return null;
		}

		// cargo el tipo de comprobante en el baen
		try {
			List compList = generalService.getTipoComprobanteService().getTipoComprobantes(new Filtro("descripcionCorta", Filtro.LIKE, "OP"));
			setTipoComprobante((TipoComprobante) compList.get(0));
		} catch (TipoComprobanteException e1) {
			return "errorTipo";
		} catch (Exception e2) {
			return "accesoDenegado";
		}

		setNroCorto("0");

		nroCortoString = Util.completar(getNroCorto(), 4);
		nroLargoString = Util.completar(getNroLargo(), 8);

		// traigo las cuotas de los comprobantes no imputados totalmente, del proveedor selecionado
		try {
			log.info("Buscacando las cuotas no imputadas");
			ComprobantesNoCancelados noCancelados = proveedoresService.getComprobanteService()
					.getComprobantesNoCanceladosSec(getProveedor().getIdProveedor());
			cuotasNoImputadas = noCancelados.getCuotasComprobantes();
			if (!cuotasNoImputadas.isEmpty()) {
				Iterator iterCuota = cuotasNoImputadas.iterator();
				while (iterCuota.hasNext()) {
					CuotaComprobante element = (CuotaComprobante) iterCuota.next();
					Imputacion imputacion = new Imputacion(element);
					log.info("Anterior Item"+ imputacion.getTotalImputado().floatValue());
					imputableAnt += imputacion.getTotalImputado().floatValue();
					tablaImputaciones.add(imputacion);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// busco la suma de todos los pagos que se le realizaron en el mes
		try {

			Calendar hoy = Calendar.getInstance();
			int mes = hoy.get(Calendar.MONTH) + 1;
			int anio = hoy.get(Calendar.YEAR);
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("proveedor.idProveedor", Filtro.IGUAL, getProveedor().getIdProveedor());
			filtro.agregarCampoOperValor("tipoComprobante.descripcionCorta", Filtro.LIKE, "OP");
			filtro.agregarCampoOperValor("fechaEmision", Filtro.MAYOR, "TO_DATE('" + mes + "/" + anio + "','MM/YYYY')");
			if (mes == 12)
				mes = 1;
			else
				mes += 1;
			filtro.agregarCampoOperValor("fechaEmision", Filtro.MENOR, "TO_DATE('" + mes + "/" + anio + "','MM/YYYY')");
			filtro.agregarfuncion("AND obj.compRevertido IS NULL");
			log.info("Query HQL: " + filtro.getHQL());
			List OPMes = comprobanteDao.listarTodos(filtro);
			totalPagosMes = new BigDecimal(0);
			totalRetencionesMes = new BigDecimal(0);
			if (!OPMes.isEmpty()) {
				Iterator iterComp = OPMes.iterator();
				while (iterComp.hasNext()) {
					Comprobante comprobante = (Comprobante) iterComp.next();
					// if (comprobante.getTotalRetencion() != null) {
					// totalRetencionesMes.add(comprobante.getTotalRetencion());
					// totalRetencionesMes.subtract(comprobante.getTotalExclusion());
					// }
					totalRetencionesMes = totalRetencionesMes.add(comprobante.getTotalRetencion());
					totalPagosMes = totalPagosMes.add(comprobante.getImporteTotal());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Busco todas las retenciones que se le aplican a este proveedor y armo la lista
		try {
			List retencionList = proveedorService.getRetenciones(getProveedor());
			if (!retencionList.isEmpty()) {
				Iterator iterRetencion = retencionList.iterator();
				while (iterRetencion.hasNext()) {
					Object[] retComp = (Object[]) iterRetencion.next();
					RetencionUtil ret = new RetencionUtil((Categoria) retComp[0], (Retencion) retComp[1], (Exclusion) retComp[2],
							importeNeto, totalPagosMes, totalRetencionesMes);
					tablaRetenciones.add(ret);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// // Busco la suma de todas las exclusiones
		// try {
		// xCienExclusion = new BigDecimal(0);
		// log.info("Buscando exclusiones...");
		// List exc = proveedorService.getExclusiones(getProveedor());
		// if (!exc.isEmpty()) {
		// Iterator iterExclusion = exc.iterator();
		// while (iterExclusion.hasNext()) {
		// Exclusion exclusion = (Exclusion) iterExclusion.next();
		// xCienExclusion = xCienExclusion.add(new BigDecimal(exclusion.getPorcentaje().floatValue()));
		// tablaExclusiones.add(exclusion);
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return result;
	}


	public void grabarOP() {
		log.info("Grabando la orden de pago");
		try {
			if (validar()) {
				comprobante = armarOrdenPago();
				armarImpactoFondos();
				log.info("Grabando oreden de pago ->" + comprobante);
				if (!error.hayErrores()) {
					comprobante.setNroLargo(proveedoresService.getComprobanteDao().leerNroTipo(getTipoComprobante().getDescripcionCorta()) + 1);
					// seteo el conceptoAsiento con el numero largo del comprobante cuando existen asientos.
					if (comprobante.getAsientos().size() > 0) {
						comprobante.setConceptoAsiento("Retenciones OP: " + comprobante.getNroLargo());
					} else {
						comprobante.setConceptoAsiento("");
					}
					// completo las leyendas de los asientosContables con el numero Largo del comprobante.
					for (Object ob : comprobante.getAsientos()) {
						AsientoContable ac = (AsientoContable) ob;
						ac.setLeyenda(ac.getLeyenda() + comprobante.getNroLargo());
					}
					proveedoresService.getComprobanteService().grabarComprobante(comprobante);
					popup.setPopup(popup.ICONO_OK, "La orden de pago ha sido almacenada exitosamente, con el Nro: " + comprobante.getNroLargo());
				}
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}

		} catch (ComprobanteDuplicateException e1) {
			log.info("No se pudo grabar la orden de pago");
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta de la orden de pago.");
			e1.printStackTrace();
		} catch (ComprobanteException e2) {
			log.info("No se pudo grabar la orden de pago");
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta de la orden de pago.");
			e2.printStackTrace();
		} catch (Exception e3) {
			log.info("No se pudo grabar la orden de pago");
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta de la orden de pago.");
			e3.printStackTrace();
		}
	}


	/**
	 * Impacta el asiento, el asiento item, el movimiento, el movimiento MP, el cheque historial y el cheque.
	 * 
	 * @throws ConceptoGenException
	 * @throws MovimientoException
	 * @throws ChequeEstadoException
	 * @throws LiqComercioException
	 * @throws BancoPropioException
	 * @throws CajaAperturaException
	 * */
	public void armarImpactoFondos() throws ConceptoGenException, NullPointerException, PlanCuentaDosException, AsientoFondosException,
			MovimientoException, ChequeEstadoException, LiqComercioException, BancoPropioException, CajaAperturaException {
		log.info("IMPACTA ORDEN DE PAGO EN FONDOS");

		Set detallesConcepto = conceptoFondos.getConceptoDetalleSet();
		Date fecha = Calendar.getInstance().getTime();
		Operador oper = Session.getOperador();
		ProveedorFormaPago proveedorFormaPago = comprobante.getFormaPago();
		ConceptoDetalleGen detalleConCuentaUnica = null;

		// itero los conceptos detalle y asigno los valores a detalle con cuenta unica
		Iterator iter = detallesConcepto.iterator();
		while (iter.hasNext()) {
			ConceptoDetalleGen det = (ConceptoDetalleGen) iter.next();
			if (det.getOrden().intValue() == 0) {
				detalleConCuentaUnica = det;
				break;
			}
		}

		// creo el asiento Fondos.
		AsientoFondos asientoFondos = new AsientoFondos();
		asientoFondos.setConcepto(conceptoFondos.getDescripcion()); // hablar con Gustavo que descripcion poner.
		asientoFondos.setFecha(getFechaEmision());
		asientoFondos.setOperador(oper);
		asientoFondos.setCotabilizado('N');

		// impactaremos el Movimiento
		Movimiento movimiento = new Movimiento();
		// El movimiento va atener el signo contradio el detalle de la cuenta unica
		movimiento.setSigno(Integer.valueOf(detalleConCuentaUnica.getSigno().intValue() * -1));
		movimiento.setFecha(fecha);
		movimiento.setOperador(oper);
		movimiento.setImporte(comprobante.getImporteNeto().doubleValue());
		movimiento.setConcepto(conceptoFondos);
		movimiento.setEstado('A');
		movimiento.setFechaAsiento(getFechaEmision());

		// creo el asiento item cabecera
		AsientoItem asientoItemCabecera = new AsientoItem();
		asientoItemCabecera.setAsiento(asientoFondos);
		asientoItemCabecera.setNroRenglon(1);
		asientoItemCabecera.setSigno(detalleConCuentaUnica.getSigno());
		asientoItemCabecera.setLeyenda(detalleConCuentaUnica.getNombre());
		asientoItemCabecera.setImporte(comprobante.getImporteNeto().doubleValue());
		asientoItemCabecera.setIdPlanCuenta(detalleConCuentaUnica.getCtacontable());

		// creo el asiento item con el medio de pago de la liquidacion.
		AsientoItem asientoItemMedioPago = new AsientoItem();
		asientoItemMedioPago.setAsiento(asientoFondos);
		asientoItemMedioPago.setNroRenglon(2);
		asientoItemMedioPago.setSigno(movimiento.getSigno());
		PlanCuentaDos planCuenta = (PlanCuentaDos) contabilidadService.getPlanCuentaDosService().leerPlanCuenta(
				proveedorFormaPago.getNroCuentaFondos());
		// Si la cuenta pertenece a una caja, verifico si se encuentra abierta y la asocio al movimiento
		if (planCuenta.getIdCaja() != null) {
			Filtro filtro = new Filtro("estado", Filtro.LIKEXS, "A");
			filtro.agregarCampoOperValor("caja.idCaja", Filtro.IGUAL, planCuenta.getIdCaja());
			List<CajaApertura> listAperturas = fondosService.getCajaAperturaService().getCajaAperturas(filtro);
			if (listAperturas.isEmpty()) {
				error.agregar("La cuenta '" + planCuenta.getIdPlanCuenta() + "' pertenece a una caja que se encuentra cerrada. \n" +
						"No se pueden realizar movimientos en una caja cerrada.");
			} else {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				CajaApertura cajaApertura = listAperturas.get(0);
				try {
					cajaApertura.setFechaApertura(simpleDateFormat.parse(simpleDateFormat.format(cajaApertura.getFechaApertura())));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (cajaApertura.getFechaApertura().after(getFechaEmision())
						|| getFechaEmision().after(new Date()))
					error.agregar("La fecha de emisión no puede ser anterior al día de la apertura de la caja o mayor al día de hoy.");
				else {
					movimiento.setCajaApertura(cajaApertura);
					movimiento.setCaja(cajaApertura.getCaja());
				}
				movimiento.setCajaApertura(listAperturas.get(0));
				movimiento.setCaja(movimiento.getCajaApertura().getCaja());
			}
		}
		asientoItemMedioPago.setLeyenda(planCuenta.getTitulo());
		asientoItemMedioPago.setImporte(comprobante.getImporteNeto().doubleValue());
		asientoItemMedioPago.setPlanCuenta(planCuenta);
		asientoItemMedioPago.setIdPlanCuenta(planCuenta.getIdPlanCuenta());

		if (asientoFondos.getAsientosItems() == null)
			asientoFondos.setAsientosItems(new HashSet());
		asientoFondos.getAsientosItems().add(asientoItemCabecera);
		asientoFondos.getAsientosItems().add(asientoItemMedioPago);

		// impactaremos el movimiento MP
		MovimientoMP movimientoMP = new MovimientoMP();
		movimientoMP.setMovimiento(movimiento);
		movimientoMP.setAsientoItem(asientoItemMedioPago);
		movimientoMP.setMonto(comprobante.getImporteNeto().doubleValue());
		movimientoMP.setFormaPago(proveedorFormaPago.getFormaPago());

		if (movimiento.getMovimientosMP() == null)
			movimiento.setMovimientosMP(new HashSet());
		movimiento.getMovimientosMP().add(movimientoMP);
		// Si el medio es efectivo termina aca
		if (!proveedorFormaPago.getFormaPago().getIdFormaPago().equals(new Long(1))) {
			// .....creo el Cheque en fondos y su respectivo cheque historial.
			Cheque cheque = new Cheque();
			ChequeHistorial chequeHistorial = new ChequeHistorial();

			Filtro filtro = new Filtro("banco.idBanco", Filtro.IGUAL, planCuenta.getCodBco());
			filtro.agregarCampoOperValor("numeroCuenta", Filtro.LIKE, planCuenta.getCodCtaBco().trim());
			BancoPropio bancoPropio = (BancoPropio) fondosService.getBancoPropioService().getBancoPropios(filtro).get(0);
			if (proveedorFormaPago.getFormaPago().getIdFormaPago().equals(new Long(2))) {
				cheque.setTipo('P');
				cheque.setBeneficiario(proveedorFormaPago.getOrdenCheque());
				cheque.setSucursalBanco(bancoPropio.getNumeroSucursal().toString());
				cheque.setCuenta(bancoPropio.getNumeroCuenta());
				cheque.setBanco(bancoPropio.getBanco());
			} else {
				cheque.setTipo('A');
				cheque.setBanco(proveedorFormaPago.getBanco());
				cheque.setBeneficiario(comprobante.getProveedor().getRazonSocial());
				cheque.setTipoCuentaBanco(proveedorFormaPago.getTipoCta());
				cheque.setCuenta(proveedorFormaPago.getCodCtaDeposito());
				cheque.setCbu(proveedorFormaPago.getCbu());
			}
			cheque.setNumero("");

			cheque.setFechaEmision(getFechaEmision());
			cheque.setFechaPago(fechaPago);
			cheque.setEsCruzado(proveedorFormaPago.getEsChequeCruzado().toCharArray()[0]);
			cheque.setNoOrden(proveedorFormaPago.getChequeNoAlaOrden().toCharArray()[0]);
			cheque.setImporte(comprobante.getImporteNeto().doubleValue());
			cheque.setCodRed(null);
			cheque.setConciliado('N');
			cheque.setProcesado('N');
			cheque.setBancoPropio(bancoPropio);
			cheque.setCodigoPostal(bancoPropio.getPlaza().toString());

			chequeHistorial.setCheque(cheque);
			chequeHistorial.setTimestamp(Calendar.getInstance().getTime());
			chequeHistorial.setChequeEstado(fondosService.getChequeEstadoService().leerChequeEstado(new Long(1)));
			chequeHistorial.setMovimientoMP(movimientoMP);
			chequeHistorial.setAsientoItem(asientoItemMedioPago);

			if (movimientoMP.getChequeHistorial() == null)
				movimientoMP.setChequeHistorial(new HashSet());
			movimientoMP.getChequeHistorial().add(chequeHistorial);
		}

		// Grabo el asiento y los asientos item.
		comprobante.setMovimiento(movimiento);
		log.info("IMPACTO ASIENTO Y ASIENTO ITEM, Movimiento, movimientoMp, cheque y cheque historial.");

	}


	public String cancelar() {
		borrar();
		return null;
	}


	public String listarOP() {
		borrar();
		super.setTipoComprobanteSeleccionado(new Long(1));
		Session.redirect("/tarjetafiel/proveedores/comprobantes/listarOrdenPago.jsf");
		tituloCorto = "Listado de ordenes de pago";
		return null;
	}


	// metodos para los botones del popup

	public String irANuevaOP() {
		log.info("irANuevaOP()");
		return inicializar();
	}


	public String irAModificarOP() {
		log.info("irAModificarOP()");
		alta = false;
		popup.borrar();
		return null;
	}


	public String imprimirOP() {
		log.info("imprimirOP()");
		log.info("id de la OP -> " + comprobante.getIdComprobante());
		HttpServletRequest request = Session.getRequest();
		// try {
		// proveedoresService.getComprobanteDao().generarPrintFormaPago(
		// Session.getOperador().getCodigo(), new Long(comprobante.getNroLargo().intValue()));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		if (comprobante != null && comprobante.getIdComprobante() != null) {
			String p1 = "?id_Comp=" + comprobante.getIdComprobante();
			String p2 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p3 = "&id_operador=" + Session.getOperador().getId();
			String page = request.getContextPath() + "/report/Imprimir_OP.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";

			log.info(popupReport);
			yaImprimio = false;
		}
		return null;
	}


	public String irAListarOP() {
		log.info("irAListarOP()");
		popup.borrar();
		return listarOP();
	}


	public String imprimirOPListar() {
		log.info("idhidden: " + getIdHidden());

		if (getIdHidden() != null && !getIdHidden().equals(new Integer(0))) {
			Long idcomp = new Long(getIdHidden().longValue());
			try {
				comprobante = proveedoresService.getComprobanteService().leerComprobante(idcomp);
				imprimirOP();
			} catch (ComprobanteException e) {
				e.printStackTrace();
				return null;
			}
		}
		Session.redirect("/tarjetafiel/proveedores/comprobantes/listarOrdenPago.jsf");
		return null;
	}


	public String filtrarOP() {
		String resul = super.filtrarComprobantes();
		Session.redirect("/tarjetafiel/proveedores/comprobantes/listarOrdenPago.jsf");
		return resul;
	}


	public String buscarProveedorPopup() {
		log.info("Ir a buscar proveedor!!!");
		BuscarProveedorBean bean = (BuscarProveedorBean) Session.getBean("BuscarProveedorBean");
		bean.inicializar(BuscarProveedorBean.ORDEN_PAGO);

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/proveedores/popup/buscarProveedor.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}


	private Comprobante armarOrdenPago() {
		// if (comprobante == null) {
		comprobante = new Comprobante();
		comprobante.setTipoComprobante(getTipoComprobante());
		comprobante.setContabilizado(new Character('N'));
		comprobante.setProvPedidoPor(null);
		comprobante.setMontoGrabado(new BigDecimal(0));
		comprobante.setMontoNoGrabado(null);
		comprobante.setTotalImpuestos(null);
		comprobante.setNroCorto(new Integer(getNroCorto()));
		// comprobante.setNroLargo(new Integer(getNroLargo()));
		comprobante.setObservacion(null);
		comprobante.setOperador(Session.getOperador());
		comprobante.setProveedor(getProveedor());
		comprobante.setTimestamp(new Timestamp(new Date().getTime()));
		comprobante.setConceptoAsiento(null);
		comprobante.setSigno(getTipoComprobante().getSigno());
		// }
		/*
		 * if (!alta) { ComprobanteUtil.modificarCuota(tablaImputaciones, comprobante, pagoACuenta, proveedoresService); }
		 */
		calculoRetencion();
		comprobante.setFechaEmision(new Timestamp(getFechaEmision().getTime()));
		comprobante.setFechaContable(comprobante.getFechaEmision());
		comprobante.setFechaPagado(new Timestamp(getFechaPago().getTime()));
		comprobante.setImporteNeto(getImporteTotal()); // Esto es asi por que los etaba cargando mal

		comprobante.setTotalRetencion(getTotalImpuestos()); // Esto es asi por que los etaba cargando mal
		comprobante.setTotalExclusion(getTotalExclusiones());
		comprobante.setImporteTotal(getImporteNeto()); // Esto es asi por que los etaba cargando mal

		CuotaComprobante cuotaOP;

		cuotaOP = ComprobanteUtil.componerImputados(tablaImputaciones, comprobante, pagoACuenta);
		comprobante.getCuotaComprobantes().add(cuotaOP);

		comprobante.setRetenciones(ComprobanteUtil.componerRetenciones(tablaRetenciones, comprobante, getTotalImpuestos()));
		// comprobante.setExclusiones(ComprobanteUtil.componerExclusiones(tablaExclusiones, comprobante, getTotalImpuestos()));
		comprobante.setFormaPago(ComprobanteUtil.getFPSeleccionada(tablaDeFormaDePago));
		return comprobante;
	}


	public String crearROP() {
		log.info("Crear ROP");
		Comprobante OP = null;
		TipoComprobante tipoROP = null;

		// Creando ROP
		try {
			OP = proveedoresService.getComprobanteService().leerComprobante(new Long(getIdHidden().longValue()));

			List listTipoROP = generalService.getTipoComprobanteService().getTipoComprobantes(new Filtro("descripcionCorta", Filtro.LIKE, "ROP"));
			tipoROP = (TipoComprobante) listTipoROP.get(0);
			if (OP.getMovimiento() != null) {
				// Movimiento movRevertido = fondosService.getMovimientoService(). OP.getMovimiento();
			} else {
				popup.setPopup(popup.ICONO_FALLA, "No se puede generar la ROP, por falta del movimiento.");
				return null;
			}
		} catch (ComprobanteException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// if (OP.getEnFondos().equals(new Character('S'))){
		// log.info("OP en fondos, no se puede generar ROP.");
		// error.agregar(Error.COMPROBANTE_OP_NO_PUEDE_GENERAR_ROP);
		// Session.redirect("/tarjetafiel/proveedores/comprobantes/listarOrdenPago.jsf");
		// return null;
		// }

		Comprobante ROP = OP.copia();
		ROP.setIdComprobante(null);
		ROP.setTipoComprobante(tipoROP);
		ROP.setCompRevertido(OP);
		ROP.setFechaEmision(new Timestamp(Calendar.getInstance().getTime().getTime()));

		Iterator iter = OP.getAsientos().iterator();
		Set asientoROPSet = new HashSet();
		while (iter.hasNext()) {
			AsientoContable asientoOP = (AsientoContable) iter.next();
			// creo un nuevo asientoContable para registrar los asientos opuestos en el ROP
			AsientoContable asientoROP = new AsientoContable();
			asientoROP.setComprobante(asientoOP.getComprobante());
			asientoROP.setNroImputa(asientoOP.getNroImputa());
			String leyendaTemp = asientoOP.getLeyenda().replace(" - OP: ", " - ROP: ");
			asientoROP.setLeyenda(leyendaTemp);
			// seteo los importes contrarios
			asientoROP.setImporteDebe(asientoOP.getImporteHaber());
			asientoROP.setImporteHaber(asientoOP.getImporteDebe());
			// agrego el asiento del ROP
			asientoROPSet.add(asientoROP);
		}
		if (!asientoROPSet.isEmpty()) {
			// asigno el set de asientosContables al comprobante.
			ROP.setAsientos(asientoROPSet);
		}
		ROP.setConceptoAsiento("ROP: " + ROP.getNroLargo());// antes lo llenaba con null, ahora mando un Strig para diferenciar

		try {
			proveedoresService.getComprobanteService().grabarComprobante(ROP);
		} catch (Exception e) {
			log.info("No se pudo generar la ROP.");
			popup.setPopup(popup.ICONO_FALLA, "Falla la generacion de la ROP.");
			e.printStackTrace();
			return null;
		}

		OP.setCompRevertido(ROP);
		// Eliminanado imputaciones
		Set cuotaSet = OP.getCuotaComprobantes();
		CuotaComprobante cuota = null;
		List imputaciones = null;
		if (cuotaSet.size() == 1) {
			Iterator it = cuotaSet.iterator();
			cuota = (CuotaComprobante) it.next();

			imputaciones = new ArrayList(cuota.getCuotaComprobanteH());
			cuota.setCuotaComprobanteH(null);
		}

		try {
			proveedoresService.getComprobanteService().actualizarComprobante(OP);
		} catch (ComprobanteException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		Iterator it = imputaciones.iterator();
		try {
			while (it.hasNext()) {
				ComprobanteImputado imp = (ComprobanteImputado) it.next();
				proveedoresService.getComprobanteImputadoService().borrarComprobanteImputado(imp.getIdComprobanteImputado());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		filtrarOP();

		Session.redirect("/tarjetafiel/proveedores/comprobantes/listarOrdenPago.jsf");
		return null;
	}


	public void borrar() {
		error.borrar();
		super.borrar();
		this.fechaPago = new Timestamp(Fecha.addDias(getFechaEmision(), 1).getTime());
		this.cuitValido = null;
		this.cuotasNoImputadas = new ArrayList();
		this.tablaImputaciones = new ArrayList();
		this.tablaRetenciones = new ArrayList();
		// this.tablaExclusiones = new ArrayList();
		this.pagoACuenta = new BigDecimal(0);
		this.importeNeto = new BigDecimal(0);
		this.totalImpuestos = new BigDecimal(0);
		this.importeTotal = new BigDecimal(0);
		this.cuitBusqueda = null;
		this.cuit = null;
		this.totalExclusiones = new BigDecimal(0);
		this.totalPagosMes = new BigDecimal(0);
		this.totalRetencionesMes = new BigDecimal(0);
		this.verRetenciones = false;
		this.comprobante = null;
		this.alta = true;
		this.popupReport = null;
		this.tituloLargo = "Tarjeta Fiel - Orden de pago";
		this.tituloCorto = "Alta de ordenes de pago";
		this.imputableAnt = 0;
		this.tablaDeFormaDePago = new ArrayList();
	}


	public boolean validar() {
		error.borrar();

		if (getFechaEmision() == null) {
			error.agregar(Error.COMPROBANTE_FECHA_EMISION_REQUERIDO);
		} else {
			try {
				Ejercicio ejercicio = contabilidadService.getEjercicioService().ejercicioActual();
				if (ejercicio.getFechaPeriodo().compareTo(getFechaEmision()) > 0)
					error.agregar(Error.COMPROBANTE_FECHA_EMISION_MENOR_PERIODO);
				if (getFechaEmision().after(ejercicio.getFechaCierre()))
					error.agregar(Error.COMPROBANTE_FECHA_EMISION_MAYOR_CIERRE);
			} catch (EjercicioException e) {
				error.agregar(e.getMessage());
				e.printStackTrace();
			}
		}

		if (getFechaPago() == null) {
			error.agregar(Error.COMPROBANTE_FECHA_PAGO_REQUERIDO);
		}

		Iterator iter = tablaImputaciones.iterator();
		while (iter.hasNext()) {
			Imputacion imputacion = (Imputacion) iter.next();
			if (imputacion.seleccionado) {
				if (imputacion.getMontoRestante().compareTo(imputacion.getMontoAImputar()) < 0) {
					error.agregar(Error.COMPROBANTE_OP_IMPUTACION_MAYOR_RESTANTE);
				}
			}
		}

		if (importeTotal.compareTo(new BigDecimal(0)) <= 0) {
			error.agregar(Error.COMPROBANTE_OP_TOTAL_ES_CERO);
		}

		if (!tablaDeFormaDePago.isEmpty()) {
			Iterator iterPagos = tablaDeFormaDePago.iterator();
			boolean paso = false;
			while (iterPagos.hasNext()) {
				FormaPagoOP formaPagoOP = (FormaPagoOP) iterPagos.next();
				if (formaPagoOP.getSeleccionado()) {
					if (paso) {
						error.agregar(Error.COMPROBANTE_OP_MULTIPLES_FORMAS_PAGO);
						break;
					}
					paso = true;
				}
			}
			if (!paso) {
				error.agregar(Error.COMPROBANTE_OP_FORMAS_PAGO_REQUERIDO);
			}
		}

		// trigo las cuotas de los comprobantes no imputados totalmente, del proveedor selecionado
		// para comparar si hubo modificaciones
		try {
			float imputableActual = 0;
			ComprobantesNoCancelados noCancelados = proveedoresService.getComprobanteService().getComprobantesNoCanceladosSec(
					getProveedor().getIdProveedor());
			cuotasNoImputadas = noCancelados.getCuotasComprobantes();
			if (!cuotasNoImputadas.isEmpty()) {
				Iterator iterCuota = cuotasNoImputadas.iterator();
				while (iterCuota.hasNext()) {
					CuotaComprobante element = (CuotaComprobante) iterCuota.next();
					Imputacion imputacion = new Imputacion(element);
					log.info("Actual Item"+ imputacion.getTotalImputado().floatValue());
					imputableActual += imputacion.getTotalImputado().floatValue();
				}
			}
			log.info("imputableActual "+ imputableActual);
			log.info("imputableAnt "+ imputableAnt);
			
			if (imputableActual != imputableAnt) {
				borrar();
				error.agregar(Error.IMPUTACION_SE_MODIFICO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			borrar();
			error.agregar(Error.IMPUTACION_SE_MODIFICO);
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String verRetencionesPopup() {
		log.info("Ver Retenciones!!!");

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/proveedores/comprobantes/popup/verRetenciones.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}


	public String mostrarRetenciones() {
		calculoRetencion();
		verRetenciones = !verRetenciones;
		log.info("Estado Actual de ver retenciones: " + verRetenciones);
		return null;
	}


	private void calculoRetencion() {
		totalImpuestos = new BigDecimal(0);
		try {
			BigDecimal sumaRetenciones = new BigDecimal(0);
			if (!tablaRetenciones.isEmpty()) {
				log.info("Tengo retenciones");
				Iterator iterTReten = tablaRetenciones.iterator();
				while (iterTReten.hasNext()) {
					RetencionUtil retencionUtil = (RetencionUtil) iterTReten.next();
					retencionUtil.setTotalOP(importeNeto);
					sumaRetenciones = sumaRetenciones.add(retencionUtil.getCalculoRetencion());
				}
			}
			// si la retencion es superior al total de la OP devuelvo el moto de la OP
			if (sumaRetenciones.compareTo(getImporteNeto()) > 0) {
				totalImpuestos = importeNeto;
			} else {
				totalImpuestos = sumaRetenciones;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Estado de totalImpuestos (Retenciones): " + totalImpuestos);
	}

	public class Imputacion {
		private CuotaComprobante cuota;
		private BigDecimal totalImputado;
		private BigDecimal montoAImputar;
		private BigDecimal montoRestante;
		private boolean seleccionado;
		private String nroCortoCuota;
		private String nroLargoCuota;
		private BigDecimal importeCuota;


		public Imputacion(CuotaComprobante cuotaComprobante) {
			super();
			this.cuota = cuotaComprobante;
			this.totalImputado = new BigDecimal(0);
			if (!cuota.getCuotaComprobanteD().isEmpty()) {
				Iterator iterCuota = cuota.getCuotaComprobanteD().iterator();
				while (iterCuota.hasNext()) {
					ComprobanteImputado element = (ComprobanteImputado) iterCuota.next();
					this.totalImputado = new BigDecimal(element.getImporteCancela().floatValue() + totalImputado.floatValue());
				}
			}
			this.montoRestante = new BigDecimal(cuota.getImporte().floatValue() - totalImputado.floatValue());
			this.montoAImputar = montoRestante;
			this.seleccionado = false;

			nroCortoCuota = Util.completar(cuota.getComprobante().getNroCorto().toString(), 4);
			nroLargoCuota = Util.completar(cuota.getComprobante().getNroLargo().toString(), 8);
		}


		public Imputacion(CuotaComprobante cuotaComprobante, BigDecimal totalImputado, BigDecimal montoAImputar, BigDecimal montoRestante) {
			super();
			this.cuota = cuotaComprobante;
			this.totalImputado = totalImputado;
			this.montoAImputar = montoAImputar;
			this.montoRestante = montoRestante;
			this.seleccionado = false;
		}


		public CuotaComprobante getCuota() {
			return cuota;
		}


		public void setCuota(CuotaComprobante cuotaComprobante) {
			this.cuota = cuotaComprobante;
		}


		public BigDecimal getImporteCuota() {
			importeCuota = new BigDecimal(cuota.getImporte().floatValue());
			return importeCuota.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		}


		public void setImporteCuota(BigDecimal importeCuota) {
			this.importeCuota = importeCuota;
		}


		public BigDecimal getMontoAImputar() {
			return montoAImputar.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		}


		public void setMontoAImputar(BigDecimal montoAImputar) {
			this.montoAImputar = montoAImputar;
		}


		public BigDecimal getMontoRestante() {
			return montoRestante.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		}


		public void setMontoRestante(BigDecimal montoRestante) {
			this.montoRestante = montoRestante;
		}


		public boolean getSeleccionado() {
			return seleccionado;
		}


		public void setSeleccionado(boolean seleccionado) {
			this.seleccionado = seleccionado;
		}


		public BigDecimal getTotalImputado() {
			return totalImputado.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		}


		public void setTotalImputado(BigDecimal totalImputado) {
			this.totalImputado = totalImputado;
		}


		public String getNroCortoCuota() {
			return nroCortoCuota;
		}


		public void setNroCortoCuota(String nroCortoCuota) {
			this.nroCortoCuota = nroCortoCuota;
		}


		public String getNroLargoCuota() {
			return nroLargoCuota;
		}


		public void setNroLargoCuota(String nroLargoCuota) {
			this.nroLargoCuota = nroLargoCuota;
		}
	}

	public class FormaPagoOP {
		private ProveedorFormaPago formaPago;
		private boolean seleccionado;


		public FormaPagoOP(ProveedorFormaPago formaPago, boolean seleccionado) {
			this.formaPago = formaPago;
			this.seleccionado = seleccionado;
		}


		public ProveedorFormaPago getFormaPago() {
			return formaPago;
		}


		public void setFormaPago(ProveedorFormaPago formaPago) {
			this.formaPago = formaPago;
		}


		public boolean getSeleccionado() {
			return seleccionado;
		}


		public void setSeleccionado(boolean seleccionado) {
			this.seleccionado = seleccionado;
		}

	}
}
