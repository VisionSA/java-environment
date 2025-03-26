package com.bizitglobal.webapp.faces.beans.fondos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.exception.NroBancarioNoValidoException;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
import com.bizitglobal.tarjetafiel.commons.util.NroBancarioValido;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.exception.BancoPropioException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeEstadoException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeHistorialException;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeEstado;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeHistorial;
import com.bizitglobal.tarjetafiel.fondos.negocio.ClaseFondo;
import com.bizitglobal.tarjetafiel.general.exception.BancoException;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.general.negocio.TipoCuentaBanco;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorFormaPago;
import com.bizitglobal.tarjetafiel.transacciones.exception.CodComercioException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioFormaPago;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.fondos.MovimientoBean.WrapperAsientoDetalle;
import com.bizitglobal.webapp.faces.beans.util.PaginadorPorDemanda;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class AdminChequeBean extends BaseBean {
	private static final Logger log = Logger.getLogger(AdminChequeBean.class);
	private DecimalFormat formateador = new DecimalFormat("#0.00;");
	private String[] medioArray = { "Efectivo / Importe directo", "Cheque Propio", "Cheque Tecero", "Acreditación", "Deposito" };

	private Boolean alta;
	private Filtro filtro = new Filtro();
	private Cheque cheque;
	private ChequeHistorial historial;
	private List mediosList;
	private PlanCuentaDos cuenta;
	private int uso;
	private WrapperAsientoDetalle wAsiento;
	private boolean boolNroCheque;
	private boolean boolAltaAcrd;
	private boolean boolBusquedaPropio;
	private boolean mostrarDetalle;
	private boolean mostrarBotonera;
	private boolean esEfectivo;
	private String leyendaNros;
	private String botonNros;

	// Objetos Relacionados.
	private String medioSeleccionado;
	// private HtmlSelectOneMenu medioHtml = new HtmlSelectOneMenu();
	private HtmlSelectOneRadio selectAlta = new HtmlSelectOneRadio();
	private Long idCuentaSeleccionada;
	private String selectFiltro;

	private Long idChequeHidden;
	private Long idCheque;
	private boolean tipoDesabilitado;
	private String idTipoSeleccionada;
	private String beneficiario;
	private String nroChequeDesde;
	private String nroChequeHasta;
	private Double importeDesde;
	private Double importeHasta;
	private Date fechaEmisionDesde;
	private Date fechaEmisionHasta;
	private Date fechaPagoDesde;
	private Date fechaPagoHasta;
	private Date fechaContable;
	private Date fechaEstado;
	private List listadoCheques = new ArrayList(); // Para la busqueda de cheques, acred y debitos
	// //definicion de un list del objeto base
	// private List cuentaList = new ArrayList();
	// private List cuentaItems = new ArrayList();
	// Listas para la presentacion(HtmlSelectItems).
	private List bancosPropios;
	private Long idBancoSeleccionado;
	private List bancoList;
	private List bancoItems = new ArrayList();
	private List estadoList = new ArrayList();
	private List estadoItems = new ArrayList();
	private List historicoList = new ArrayList();
	// private String[] ordenArray;

	// private List operadorItems = new ArrayList();
	//
	private Long idEstadoSeleccionada;
	private Integer idOrdenSeleccionada;

	private String focoHidden;
	private PaginadorPorDemanda pagDeMov;
	private String busquedaHidden;
	private String composicionHidden;

	// Para buscar el Beneficiario
	private Long idTipoCtaSeleccionado;
	private List tipoCtaItems = new ArrayList();
	private List tipoCtaList = new ArrayList();
	private List tipoBusquedaItems = new ArrayList();
	private List cuentaPropiaItems = new ArrayList();
	private Long codigo;
	private String cbu;
	private HtmlSelectOneMenu tipoAccion = new HtmlSelectOneMenu();
	private Long idTipoAccionSeleccionada;
	private Long idCuentaPropiaSeleccionada;
	private boolean boolCodigo;
	private boolean boolCuenta;
	private boolean boolCbu;
	private List formaPagoList = new ArrayList();
	private boolean boolFormaPago;
	private String nroCuenta;

	private int indiceDeMedios = 0;


	public AdminChequeBean() {
		super();
		borrar();
		try {
			try {
				filtro.reset();
				filtro.agregarOrderBy("idChequeEstado");
				estadoList = fondosService.getChequeEstadoService().getChequeEstados(filtro);
			} catch (ChequeEstadoException e1) {
				e1.printStackTrace();
			}
			try {
				filtro.reset();
				filtro.agregarCampoOperValor("habilitado", Filtro.LIKEXS, "S");
				bancosPropios = fondosService.getBancoPropioService().getBancoPropios(filtro);
			} catch (BancoPropioException e) {
				error.agregar("Error al leer las cuentas bancarias.");
				e.printStackTrace();
			}
			tipoCtaList.add(generalService.getTipoCuentaBancoDao().buscarTipoCuentaBanco(new Long(1)));
			tipoCtaList.add(generalService.getTipoCuentaBancoDao().buscarTipoCuentaBanco(new Long(2)));
			tipoCtaItems.addAll(Util.cargarSelectItem(tipoCtaList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String getAltaStr() {
		return (alta == null) ? null : alta.toString();
	}


	public void setAltaStr(String altaStr) {
		// if (altaStr == null) {
		// this.alta = null;
		// } else {
		// // if (altaStr.compareTo("true") == 0)
		// this.alta = new Boolean(altaStr);
		// // else
		// // this.alta = Boolean.FALSE;
		// }
	}


	public Boolean getAlta() {
		return alta;
	}


	public void setAlta(Boolean alta) {
		this.alta = alta;
	}


	public Date getFechaContable() {
		return fechaContable;
	}


	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}


	public Cheque getCheque() {
		return cheque;
	}


	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}


	public List getMediosList() {
		return mediosList;
	}


	public void setMediosList(List mediosList) {
		this.mediosList = mediosList;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public Filtro getFiltro() {
		return filtro;
	}


	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}


	public Long getIdCheque() {
		return idCheque;
	}


	public void setIdCheque(Long idCheque) {
		this.idCheque = idCheque;
	}


	public List getConceptoItems() {
		return estadoItems;
	}


	public void setConceptoItems(List conceptoItems) {
		this.estadoItems = conceptoItems;
	}


	public PaginadorPorDemanda getPagDeMov() {
		return pagDeMov;
	}


	public void setPagDeMov(PaginadorPorDemanda pagDeMov) {
		this.pagDeMov = pagDeMov;
	}


	public List getBancoItems() {
		return bancoItems;
	}


	public void setBancoItems(List bancoItems) {
		this.bancoItems = bancoItems;
	}


	public Long getIdChequeHidden() {
		return idChequeHidden;
	}


	public void setIdChequeHidden(Long idChequeHidden) {
		this.idChequeHidden = idChequeHidden;
	}


	public List getHistoricoList() {
		return historicoList;
	}


	public void setHistoricoList(List historicoList) {
		this.historicoList = historicoList;
	}


	public String getSelectFiltro() {
		return selectFiltro;
	}


	public void setSelectFiltro(String selectFiltro) {
		this.selectFiltro = selectFiltro;
	}


	public Long getIdCuentaSeleccionada() {
		return idCuentaSeleccionada;
	}


	public void setIdCuentaSeleccionada(Long idCuentaSeleccionada) {
		this.idCuentaSeleccionada = idCuentaSeleccionada;
	}


	public String getBeneficiario() {
		return beneficiario;
	}


	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}


	public String getNroChequeDesde() {
		return nroChequeDesde;
	}


	public void setNroChequeDesde(String nroChequeDesde) {
		this.nroChequeDesde = nroChequeDesde;
	}


	public String getNroChequeHasta() {
		return nroChequeHasta;
	}


	public void setNroChequeHasta(String nroChequeHasta) {
		this.nroChequeHasta = nroChequeHasta;
	}


	public Double getImporteDesde() {
		return importeDesde;
	}


	public void setImporteDesde(Double importeDesde) {
		this.importeDesde = importeDesde;
	}


	public Double getImporteHasta() {
		return importeHasta;
	}


	public void setImporteHasta(Double importeHasta) {
		this.importeHasta = importeHasta;
	}


	public List getEstadoItems() {
		return estadoItems;
	}


	public void setEstadoItems(List estadoItems) {
		this.estadoItems = estadoItems;
	}


	public boolean isTipoDesabilitado() {
		return tipoDesabilitado;
	}


	public void setTipoDesabilitado(boolean tipoDesabilitado) {
		this.tipoDesabilitado = tipoDesabilitado;
	}


	public String getIdTipoSeleccionada() {
		return idTipoSeleccionada;
	}


	public void setIdTipoSeleccionada(String idTipoSeleccionada) {
		this.idTipoSeleccionada = idTipoSeleccionada;
	}


	public Date getFechaEmisionDesde() {
		return fechaEmisionDesde;
	}


	public void setFechaEmisionDesde(Date fechaEmisionDesde) {
		this.fechaEmisionDesde = fechaEmisionDesde;
	}


	public Date getFechaEmisionHasta() {
		return fechaEmisionHasta;
	}


	public void setFechaEmisionHasta(Date fechaEmisionHasta) {
		this.fechaEmisionHasta = fechaEmisionHasta;
	}


	public Date getFechaPagoDesde() {
		return fechaPagoDesde;
	}


	public void setFechaPagoDesde(Date fechaPagoDesde) {
		this.fechaPagoDesde = fechaPagoDesde;
	}


	public Date getFechaPagoHasta() {
		return fechaPagoHasta;
	}


	public void setFechaPagoHasta(Date fechaPagoHasta) {
		this.fechaPagoHasta = fechaPagoHasta;
	}


	public Long getIdEstadoSeleccionada() {
		return idEstadoSeleccionada;
	}


	public void setIdEstadoSeleccionada(Long idEstadoSeleccionada) {
		this.idEstadoSeleccionada = idEstadoSeleccionada;
	}


	public Date getFechaEstado() {
		return fechaEstado;
	}


	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}


	public String getBusquedaHidden() {
		return busquedaHidden;
	}


	public void setBusquedaHidden(String busquedaHidden) {
		this.busquedaHidden = busquedaHidden;
	}


	public String getComposicionHidden() {
		return composicionHidden;
	}


	public void setComposicionHidden(String composicionHidden) {
		this.composicionHidden = composicionHidden;
	}


	public Integer getIdOrdenSeleccionada() {
		return idOrdenSeleccionada;
	}


	public void setIdOrdenSeleccionada(Integer idOrdenSeleccionada) {
		this.idOrdenSeleccionada = idOrdenSeleccionada;
	}


	public boolean getBoolNroCheque() {
		return boolNroCheque;
	}


	public void setBoolNroCheque(boolean boolNroCheque) {
		this.boolNroCheque = boolNroCheque;
	}


	public boolean isBoolAltaAcrd() {
		return boolAltaAcrd;
	}


	public void setBoolAltaAcrd(boolean boolAltaAcrd) {
		this.boolAltaAcrd = boolAltaAcrd;
	}


	public boolean isBoolBusquedaPropio() {
		return boolBusquedaPropio;
	}


	public void setBoolBusquedaPropio(boolean boolBusquedaPropio) {
		this.boolBusquedaPropio = boolBusquedaPropio;
	}


	public boolean isMostrarDetalle() {
		return mostrarDetalle;
	}


	public void setMostrarDetalle(boolean mostrarDetalle) {
		this.mostrarDetalle = mostrarDetalle;
	}


	public boolean isMostrarBotonera() {
		return mostrarBotonera;
	}


	public void setMostrarBotonera(boolean mostrarBotonera) {
		this.mostrarBotonera = mostrarBotonera;
	}


	public String getLeyendaNros() {
		return leyendaNros;
	}


	public void setLeyendaNros(String leyendaNros) {
		this.leyendaNros = leyendaNros;
	}


	public String getBotonNros() {
		return botonNros;
	}


	public void setBotonNros(String botonNros) {
		this.botonNros = botonNros;
	}


	public String getPlanCuenta() {
		return cuenta.getIdPlanCuenta() + " - " + cuenta.getTitulo();
	}


	public boolean isEsEfectivo() {
		return esEfectivo;
	}


	public void setEsEfectivo(boolean esEfectivo) {
		this.esEfectivo = esEfectivo;
	}


	public String getBancoPropio() {
		if (cheque.getBancoPropio() == null) {
			return "No aplica";
		} else {
			return cheque.getBancoPropio().getLabel();
		}
	}


	public HtmlSelectOneRadio getSelectAlta() {
		return selectAlta;
	}


	public String getMedioSeleccionado() {
		return medioSeleccionado;
	}


	public void setMedioSeleccionado(String medioSeleccionado) {
		this.medioSeleccionado = medioSeleccionado;
	}


	public void setSelectAlta(HtmlSelectOneRadio selectAlta) {
		this.selectAlta = selectAlta;
	}


	public void setTipoAccion(HtmlSelectOneMenu tipoAccion) {
		this.tipoAccion = tipoAccion;
	}


	public HtmlSelectOneMenu getTipoAccion() {
		return tipoAccion;
	}


	public void setCbu(String cbu) {
		this.cbu = cbu;
	}


	public String getCbu() {
		return cbu;
	}


	public String getNroCuenta() {
		return nroCuenta;
	}


	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}


	public void setIdTipoAccionSeleccionada(Long idTipoAccionSeleccionada) {
		this.idTipoAccionSeleccionada = idTipoAccionSeleccionada;
	}


	public Long getIdTipoAccionSeleccionada() {
		return idTipoAccionSeleccionada;
	}


	public Long getIdCuentaPropiaSeleccionada() {
		return idCuentaPropiaSeleccionada;
	}


	public void setIdCuentaPropiaSeleccionada(Long idCuentaPropiaSeleccionada) {
		this.idCuentaPropiaSeleccionada = idCuentaPropiaSeleccionada;
	}


	public Long getIdTipoCtaSeleccionado() {
		return idTipoCtaSeleccionado;
	}


	public void setIdTipoCtaSeleccionado(Long idTipoCtaSeleccionado) {
		this.idTipoCtaSeleccionado = idTipoCtaSeleccionado;
	}


	public void setTipoBusquedaItems(List tipoBusquedaItems) {
		this.tipoBusquedaItems = tipoBusquedaItems;
	}


	public List getTipoBusquedaItems() {
		return tipoBusquedaItems;
	}


	public List getCuentaPropiaItems() {
		return cuentaPropiaItems;
	}


	public void setCuentaPropiaItems(List cuentaPropiaItems) {
		this.cuentaPropiaItems = cuentaPropiaItems;
	}


	public List getTipoCtaItems() {
		return tipoCtaItems;
	}


	public void setTipoCtaItems(List tipoCtaItems) {
		this.tipoCtaItems = tipoCtaItems;
	}


	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public boolean isBoolCodigo() {
		return boolCodigo;
	}


	public void setBoolCodigo(boolean boolCodigo) {
		this.boolCodigo = boolCodigo;
	}


	public boolean isBoolCuenta() {
		return boolCuenta;
	}


	public void setBoolCuenta(boolean boolCuenta) {
		this.boolCuenta = boolCuenta;
	}


	public boolean isBoolCbu() {
		return boolCbu;
	}


	public void setBoolCbu(boolean boolCbu) {
		this.boolCbu = boolCbu;
	}


	public boolean isBoolFormaPago() {
		return boolFormaPago;
	}


	public void setBoolFormaPago(boolean boolFormaPago) {
		this.boolFormaPago = boolFormaPago;
	}


	public List getFormaPagoList() {
		return formaPagoList;
	}


	public void setFormaPagoList(List formaPagoList) {
		this.formaPagoList = formaPagoList;
	}


	public Long getIdBancoSeleccionado() {
		return idBancoSeleccionado;
	}


	public void setIdBancoSeleccionado(Long idBancoSeleccionado) {
		this.idBancoSeleccionado = idBancoSeleccionado;
	}


	public List getListadoCheques() {
		return listadoCheques;
	}


	public void setListadoCheques(List listadoCheques) {
		this.listadoCheques = listadoCheques;
	}


	/************************************************************************
	 * METODOS PARA EL PAGINADO REESCRITOS
	 ************************************************************************/

	public void cargarPagina(ValueChangeEvent e) {
		historicoList.clear();
		pagDeMov.cargarPagina(e);
	}


	public String primeraPagina() {
		historicoList.clear();
		return pagDeMov.primeraPagina();
	}


	public String ultimaPagina() {
		historicoList.clear();
		return pagDeMov.ultimaPagina();
	}


	public String paginaSiguiente() {
		historicoList.clear();
		return pagDeMov.paginaSiguiente();
	}


	public String paginaAnterior() {
		historicoList.clear();
		return pagDeMov.paginaAnterior();
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CHEQUE
	 ************************************************************************/

	public String inicializar() {
		borrar();
		// popupAltaAsiento = new PopupAltaAsiento();
		return "";
	}


	private void setEfectivo() {
		if (cuenta.getFormaPago().getIdFormaPago().equals(new Long(0))) {
			leyendaNros = "Efectivo / Importe directo";
		} else {
			leyendaNros = cuenta.getFormaPago().getDescripcion();
		}
		cheque.setBeneficiario(leyendaNros);
		cheque.setTipo(Cheque.EFECTIVO);
		botonNros = "Nada";
		boolNroCheque = false;
		boolAltaAcrd = false;
		esEfectivo = true;
	}


	private void setAltaAcreditacion() {
		leyendaNros = "Alta de acreditación";
		cheque.setTipo(Cheque.ACREDITACION);
		cheque.setBancoPropio(buscarBancoPropio());
		cheque.setCodigoPostal(cheque.getBancoPropio().getPlaza() + "");
		cheque.setCodRed(null);
		cheque.setConciliado('N');
		cheque.setProcesado('N');
		botonNros = "Nada";
		boolAltaAcrd = true;
		tipoAccion.setValue("0");
		cambiarTipoAccion(null);
		// mostrarDetalle = true;
	}


	private void setAltaPropio() {
		boolNroCheque = false;
		boolAltaAcrd = false;
		if (!bancosPropios.isEmpty()) {
			// Acomodo el cheque
			setearBancoPropioEnCheque();
			cheque.setCodigoPostal(cheque.getBancoPropio().getPlaza().toString());
			cheque.setTipo(Cheque.PROPIO);
			cheque.setFechaEmision(new Date());
			cheque.setFechaPago(Fecha.addDias(cheque.getFechaEmision(), 1));
			// Acomodo el historial
			historial.setChequeEstado((ChequeEstado) Util.buscarElemento(estadoList, new ChequeEstado(new Long(1))));
			mostrarDetalle = true;
			// beneficiarios = true;
		} else {
			error.agregar("No se encuentan cuentas propias en el sistema.");
		}
	}


	private void setAltaTercero() {
		leyendaNros = "Alta cheques de terceros";
		cheque.setTipo(Cheque.TERCERO);
		botonNros = "Cargar";
		boolAltaAcrd = false;
		boolNroCheque = true;
	}


	private void setAltaDeposito() {
		if (cuenta.getFormaPago().getIdFormaPago().equals(new Long(0))) {
			leyendaNros = "Deposito / Importe directo";
		} else {
			leyendaNros = cuenta.getFormaPago().getDescripcion();
		}
		cheque.setBeneficiario(leyendaNros);
		cheque.setTipo(Cheque.EFECTIVO);
		botonNros = "Nada";
		boolNroCheque = false;
		boolAltaAcrd = false;
		esEfectivo = true;
	}


	private void setBusquedaAcreditacion() {
		leyendaNros = "Busqueda de acreditación";
		botonNros = "Buscar";
		boolBusquedaPropio = true;
		boolAltaAcrd = false;
		boolNroCheque = false;
		tipoDesabilitado = true;
		idTipoSeleccionada = Cheque.ACREDITACION.toString();
		setearBancoPropioEnCheque();
	}


	private void setBusquedaPropio() {
		leyendaNros = "Busqueda cheques propios";
		botonNros = "Buscar";
		// cuenta.get
		boolBusquedaPropio = true;
		boolAltaAcrd = false;
		boolNroCheque = false;
		tipoDesabilitado = true;
		idTipoSeleccionada = Cheque.PROPIO.toString();
		setearBancoPropioEnCheque();
		esEfectivo = true;
	}


	private void setBusquedaTercero() {
		leyendaNros = "Busqueda cheques de terceros";
		botonNros = "Buscar";
		boolAltaAcrd = false;
		boolNroCheque = true;
	}


	private void setBusquedaDeposito() {
		// if (cuenta.getFormaPago().getIdFormaPago().equals(new Long(0))) {
		// leyendaNros = "Deposito / Importe directo";
		// } else {
		// leyendaNros = cuenta.getFormaPago().getDescripcion();
		// }
		leyendaNros = "Busqueda de medios";
		cheque.setBeneficiario(leyendaNros);
		tipoDesabilitado = false;
		idTipoSeleccionada = "0";
		setearBancoPropioEnCheque();
		botonNros = "Buscar";
		boolBusquedaPropio = true;
		boolNroCheque = false;
		boolAltaAcrd = false;
		esEfectivo = true;
	}


	private BancoPropio buscarBancoPropio() {
		if (!bancosPropios.isEmpty()) {
			Iterator iter = bancosPropios.iterator();
			while (iter.hasNext()) {
				BancoPropio bancoPropio = (BancoPropio) iter.next();
				if (bancoPropio.getNumeroCuenta().trim().equals(cuenta.getCodCtaBco().trim())
						&& bancoPropio.getBanco().getIdBanco().equals(cuenta.getCodBco()))
					return bancoPropio;
			}
			error.agregar("No se encuentan cuenta propia correspondiente a la cta. contable nro. " + cuenta.getIdPlanCuenta());
			return null;
		} else {
			error.agregar("No se encuentan cuentas propias en el sistema.");
			return null;
		}
	}


	private void setearBancoPropioEnCheque() {
		cheque.setBancoPropio(buscarBancoPropio());
		cheque.setBanco(cheque.getBancoPropio().getBanco());
		cheque.setCuenta(cheque.getBancoPropio().getNumeroCuenta());
		cheque.setSucursalBanco(cheque.getBancoPropio().getNumeroSucursal().toString());
	}


	public String inicializar(Integer uso, Boolean altaParam, WrapperAsientoDetalle wAsiento) {
		borrar();
		this.wAsiento = wAsiento;
		// this.cheque = wAsiento.getMedio();
		this.mediosList = wAsiento.getMedios();
		this.cuenta = wAsiento.getCuenta();
		this.uso = uso.intValue();
		medioSeleccionado = medioArray[this.uso - 1];
		if (altaParam != null) {
			this.alta = altaParam.booleanValue();
			selectAlta.setValue(this.alta.toString());
			selectAlta.setDisabled(true);
		} else {
			// esEfectivo = true;
			// selectAlta.setDisabled(false);
		}
		return "";
	}


	// public void cambioCuenta(ValueChangeEvent event) {
	// try {
	// int i = new Integer(idCuentaSeleccionada).intValue();
	// switch (i) {
	// case 1:
	// boolNroCheque = false;
	// break;
	// case 2:
	// boolNroCheque = true;
	// break;
	// case 3:
	//
	// break;
	// default:
	// boolNroCheque = false;
	// break;
	// }
	// } catch (NumberFormatException e) {
	// e.printStackTrace();
	// }
	// if (idCuentaSeleccionada.equals(new Long(0)) ) {
	// mostrarDetalle = false;
	// } else {
	// cheque.setBancoPropio((BancoPropio)
	// Util.buscarElemento(cuentaList, new BancoPropio(idCuentaSeleccionada)));
	// mostrarDetalle = true;
	// }
	// }

	public String agregarMedio() {
		error.borrar();
		// Construllo el cheque
		cheque = new Cheque();
		cheque.setBanco(new Banco());
		// cheque.setFechaEmision(new Date());
		// cheque.setFechaPago(new Date());
		// Construllo el historial
		historial = new ChequeHistorial();
		historial.setCheque(cheque);
		alta = new Boolean((String) selectAlta.getValue());
		if (alta.booleanValue()) {
			switch (this.uso) {
			case ClaseFondo.MEDIO_EFECTIVO:
				setEfectivo();
				break;
			case ClaseFondo.MEDIO_CHEQUE_PROPIO:
				setAltaPropio();
				break;
			case ClaseFondo.MEDIO_CHEQUE_TERCERO:
				setAltaTercero();
				break;
			case ClaseFondo.MEDIO_ACREDITACION:
				setAltaAcreditacion();
				break;
			case ClaseFondo.MEDIO_DEPOSITO:
				setAltaDeposito();
				break;
			default:

				break;
			}
			if (!error.hayErrores()) {
				mostrarBotonera = false;
			}
		} else {
			listadoCheques.clear();
			switch (this.uso) {
			case ClaseFondo.MEDIO_EFECTIVO:
				setEfectivo();
				break;
			case ClaseFondo.MEDIO_CHEQUE_PROPIO:
				setBusquedaPropio();
				break;
			case ClaseFondo.MEDIO_CHEQUE_TERCERO:
				setBusquedaTercero();
				break;
			case ClaseFondo.MEDIO_ACREDITACION:
				setBusquedaAcreditacion();
				break;
			case ClaseFondo.MEDIO_DEPOSITO:
				setBusquedaDeposito();
				break;
			default:

				break;
			}
			if (!error.hayErrores()) {
				mostrarBotonera = false;
			}
		}
		// popupAltaAsiento = new PopupAltaAsiento();
		return "";
	}


	public String cargarMedio() {
		if (validar()) {
			Medio medio = new Medio(cheque, historial, alta);
			if (!mediosList.contains(medio)) {
				mediosList.add(medio);
			} else {
				error.agregar("El medio que desea incluir ya esta en la lista.");
			}
			cancelarCargarMedio();
		}
		return null;
	}


	public String cancelarCargarMedio() {
		boolNroCheque = false;
		boolAltaAcrd = false;
		boolBusquedaPropio = false;
		mostrarDetalle = false;
		mostrarBotonera = true;
		boolCodigo = false;
		boolCuenta = false;
		boolCbu = true;
		boolFormaPago = false;
		cbu = "";
		nroCuenta = "";
		codigo = null;
		idBancoSeleccionado = new Long(0);
		esEfectivo = false;
		return null;
	}


	public String grabar() {
		if (mediosList.isEmpty())
			wAsiento.setAceptoMedio(Boolean.FALSE);
		else
			wAsiento.setAceptoMedio(Boolean.TRUE);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.close();";
		AddResource addResource = AddResourceFactory.getInstance(facesContext);
		addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		return "";
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		if (validarGrabar()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar();window.close();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		}
	}


	public void borrar() {
		borrarBaseBean();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Cheques";
		alta = Boolean.TRUE;
		mostrarDetalle = false;
		mostrarBotonera = true;
		esEfectivo = false;
		botonNros = "Nada";
		boolNroCheque = false;
		boolAltaAcrd = false;
		boolBusquedaPropio = false;
		leyendaNros = "Error";
		idCheque = null;
		selectFiltro = "1";
		historicoList.clear();

		fechaContable = new Date();
		fechaEmisionHasta = new Date();
		fechaPagoHasta = new Date();
		Calendar fecha = Calendar.getInstance();
		fecha.add(Calendar.MONTH, -1);
		fechaEmisionDesde = fecha.getTime();
		fechaPagoDesde = fecha.getTime();
		fechaEstado = new Date();

		estadoItems.clear();
		idCuentaSeleccionada = new Long(0);
		idEstadoSeleccionada = new Long(0);
		idBancoSeleccionado = new Long(0);
		idOrdenSeleccionada = new Integer(1);
		beneficiario = "";
		idTipoSeleccionada = "0";
		nroChequeDesde = null;
		nroChequeHasta = null;

		// medioHtml.setValue(new Integer(0));
		selectAlta.setValue("true");
		selectAlta.setDisabled(false);

		busquedaHidden = "";
		composicionHidden = "none";
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		idTipoAccionSeleccionada = new Long(0);
		idCuentaPropiaSeleccionada = new Long(1);
		idTipoCtaSeleccionado = new Long(0);
		tipoBusquedaItems.clear();
		tipoBusquedaItems.add(new SelectItem(new Long(0), "CBU"));
		tipoBusquedaItems.add(new SelectItem(new Long(1), "Comercio"));
		tipoBusquedaItems.add(new SelectItem(new Long(2), "Proveedor"));
		tipoBusquedaItems.add(new SelectItem(new Long(3), "Cuenta Propia"));
		boolFormaPago = false;
		boolCodigo = false;
		boolCuenta = false;
		boolCbu = true;
		codigo = null;
		formaPagoList.clear();
		listadoCheques.clear();
		nroCuenta = "";
		cbu = "";
	}


	public String cancelar() {
		// borrar();
		wAsiento.setAceptoMedio(new Boolean(false));
		return "";
	}


	public String irANuevoCheque() {
		return inicializar();
	}


	// public String irAModificarCaja() {
	// alta = false;
	// popup.borrar();
	// tituloCorto = "Modificar Caja";
	//
	// return null;
	// }

	public String irAListarCheque() {
		borrar();
		tituloCorto = "Listado de Cheques";
		Session.redirect("/tarjetafiel/fondos/listarCheque.jsf");
		return "";
	}


	public String buscarTercero() {
		error.borrar();
		mostrarDetalle = false;
		filtro.reset();
		// log.info("Codigo de banco(antes): " + cheque.getBanco());
		// cheque.setBanco((Banco)Util.buscarElemento(bancoList,cheque.getBanco()));
		try {
			cheque.setBanco(generalService.getBancoService().leerBanco(cheque.getBanco().getIdBanco()));
		} catch (BancoException e1) {
			e1.printStackTrace();
		}
		try {
			if (cheque.validar()) {
				filtro.agregarCampoOperValor("tipo", Filtro.LIKEXS, Cheque.TERCERO);
				filtro.agregarCampoOperValor("numero", Filtro.IGUAL_NUMERO, Long.parseLong(cheque.getNumero()));
				filtro.agregarCampoOperValor("sucursalBanco", Filtro.IGUAL_NUMERO, Integer.parseInt(cheque.getSucursalBanco()));
				filtro.agregarCampoOperValor("banco.idBanco", Filtro.IGUAL, cheque.getBanco().getIdBanco());
				filtro.agregarCampoOperValor("codigoPostal", Filtro.IGUAL_NUMERO, cheque.getCodigoPostal());
				filtro.agregarCampoOperValor("cuenta", Filtro.LIKE, Long.parseLong(cheque.getCuenta()));
				/* @I3942 */filtro.agregarfuncion(" and not exists (from ChequeHistorial as his " +
						"where his.cheque = obj and (his.conciliado like 'S' or his.chequeEstado.idChequeEstado = 9))");
				filtro.agregarfuncion(" and exists (from ChequeHistorial as his " +
						" where his.idChequeHistorial =(select max(idChequeHistorial) from ChequeHistorial where cheque = obj)" +
						" and his.asientoItem.idPlanCuenta = " + this.cuenta.getIdPlanCuenta() + " )");
				/* @F3942 */
				try {
					List chequeList = fondosService.getChequeService().getCheques(filtro);
					if (chequeList.isEmpty()) {
						if (alta.booleanValue()) {
							mostrarDetalle = true;
						} else {
							error.agregar("No se encontro ningun cheque");
						}
					} else {
						if (alta.booleanValue()) {
							error.agregar("El cheque que se desea dar de alta ya existe en el sistema");
						} else {
							cheque = (Cheque) chequeList.get(0);
							ChequeHistorial chequeHistorial = fondosService.getChequeHistorialService().buscarUltimo(cheque);
							historial = new ChequeHistorial(chequeHistorial);
							mostrarDetalle = true;
						}
					}
				} catch (ChequeException e) {
					e.printStackTrace();
					error.agregar("Ocurrio un problema al intentar leer el cheque");
				} catch (ChequeHistorialException e) {
					error.agregar("Ocurrio un problema al intentar leer el historial del cheque");
					e.printStackTrace();
				}
			}
		} catch (NroBancarioNoValidoException e) {
			error.agregar(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}


	public String buscarPropio() {
		error.borrar();
		mostrarDetalle = false;
		filtro.reset();
		// log.info("Codigo de banco(antes): " + cheque.getBanco());
		// cheque.setBanco((Banco)Util.buscarElemento(bancoList,cheque.getBanco()));
		try {
			filtro.agregarCampoOperValor("bancoPropio.idBancoPropio", Filtro.IGUAL_NUMERO, cheque.getBancoPropio().getIdBancoPropio());
			if (idTipoSeleccionada != null && !idTipoSeleccionada.equals("0"))
				filtro.agregarCampoOperValor("tipo", Filtro.LIKEXS, idTipoSeleccionada);
			beneficiario = beneficiario.trim();
			if (!beneficiario.equals(""))
				filtro.agregarCampoOperValor("beneficiario", Filtro.LIKE, beneficiario);
			if (nroChequeDesde != null && !nroChequeDesde.equals(""))
				filtro.agregarCampoOperValor("numero", Filtro.MAYOR_IGUAL, nroChequeDesde);
			if (nroChequeHasta != null && !nroChequeHasta.equals(""))
				filtro.agregarCampoOperValor("numero", Filtro.MENOR_IGUAL, nroChequeHasta);
			if (importeDesde != null && !importeDesde.equals(new Double(0)))
				filtro.agregarCampoOperValor("importe", Filtro.MAYOR_IGUAL, importeDesde);
			if (importeHasta != null && !importeHasta.equals(new Double(0)))
				filtro.agregarCampoOperValor("importe", Filtro.MENOR_IGUAL, importeHasta);
			if (fechaEmisionDesde != null)
				filtro.agregarCampoOperValor("fechaEmision", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(fechaEmisionDesde));
			if (fechaEmisionHasta != null)
				filtro.agregarCampoOperValor("fechaEmision", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(Fecha.addDias(fechaEmisionHasta, 1)));
			if (fechaPagoDesde != null)
				filtro.agregarCampoOperValor("fechaPago", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(fechaPagoDesde));
			if (fechaPagoHasta != null)
				filtro.agregarCampoOperValor("fechaPago", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(Fecha.addDias(fechaPagoHasta, 1)));
			filtro.agregarfuncion(" and not exists (from ChequeHistorial as his " +
					"where his.cheque = obj and (his.conciliado like 'S' or his.chequeEstado.idChequeEstado = 4))");
			filtro.agregarfuncion(" and exists (from ChequeHistorial as his " +
					" where his.idChequeHistorial =(select max(idChequeHistorial) from ChequeHistorial where cheque = obj)" +
					" and his.asientoItem.idPlanCuenta = " + this.cuenta.getIdPlanCuenta() + " )");

			// " where his.cheque = obj and his.asientoItem.idPlanCuenta = " + this.cuenta.getIdPlanCuenta() +
			// " AND his.idChequeHistorial =(select max(idChequeHistorial) from ChequeHistorial where cheque = obj ))");
			// filtro.agregarCampoOperValor("banco.idBanco", Filtro.IGUAL, cheque.getBanco().getIdBanco());
			// filtro.agregarCampoOperValor("cuenta", Filtro.LIKE, Long.parseLong(cheque.getCuenta()));
			// filtro.agregarCampoOperValor("chequeEstado.idChequeEstado", Filtro.IGUAL, cuenta.getIdEstadoCheque());

			try {
				listadoCheques.clear();
				Iterator iter = fondosService.getChequeService().getCheques(filtro).iterator();
				while (iter.hasNext()) {
					Cheque chequeNext = (Cheque) iter.next();
					listadoCheques.add(new ChequeWrapper(chequeNext));
					// Iterator iterHis = chequeNext.getHistorial().iterator();
					// ChequeHistorial ultimoHistorial = (ChequeHistorial) iterHis.next();
					// while (iterHis.hasNext()) {
					// ChequeHistorial historial = (ChequeHistorial) iterHis.next();
					// if (historial.getTimestamp().after(ultimoHistorial.getTimestamp())) {
					// ultimoHistorial = historial;
					// }
					// }
					// if (ultimoHistorial.getConciliado().equals(conciliado)
					// && ultimoHistorial.getAsientoItem().getIdPlanCuenta().equals(this.cuenta.getIdPlanCuenta())
					// && !ultimoHistorial.getChequeEstado().getIdChequeEstado().equals(4l)) {
					// }
				}

				// if (listadoCheques.isEmpty()) {
				// if (alta.booleanValue()) {
				// mostrarDetalle = true;
				// } else {
				// error.agregar("No se encontro ningun cheque");
				// }
				// } else {
				// if (alta.booleanValue()) {
				// error.agregar("El cheque que se desea dar de alta ya existe en el sistema");
				// } else {
				// cheque = (Cheque)listadoCheques.get(0);
				// ChequeHistorial chequeHistorial = fondosService.getChequeHistorialService().buscarUltimo(cheque);
				// if (chequeHistorial.getChequeEstado().getIdChequeEstado().equals(cuenta.getIdEstadoCheque())) {
				// historial = new ChequeHistorial(chequeHistorial);
				// mostrarDetalle = true;
				// }
				// }
				// }
			} catch (ChequeException e) {
				e.printStackTrace();
				error.agregar("Ocurrio un problema al intentar leer el cheque");
				// } catch (ChequeHistorialException e) {
				// error.agregar("Ocurrio un problema al intentar leer el historial del cheque");
				// e.printStackTrace();
			}
		} catch (Exception e) {
			error.agregar(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}


	private boolean validarGrabar() {
		error.borrar();
		if (mediosList.isEmpty()) {
			error.agregar("Debe agregar por lo menos un medio.");
		}
		return !error.hayErrores();
	}


	public boolean validar() {
		error.borrar();
		if (alta.booleanValue()) {
			switch (this.uso) {
			case ClaseFondo.MEDIO_EFECTIVO:
				validarImporte();
				break;
			case ClaseFondo.MEDIO_CHEQUE_PROPIO:
				validarBeneficiario();
				validarFechasPropias();
				validarImporte();
				break;
			case ClaseFondo.MEDIO_CHEQUE_TERCERO:
				validarBeneficiario();
				validarFechas();
				validarImporte();
				break;
			case ClaseFondo.MEDIO_ACREDITACION:
				validarFechasPropias();
				validarImporte();
				break;
			case ClaseFondo.MEDIO_DEPOSITO:
				validarImporte();
				// Cuando entra por aca se debe hacer la carga de una boleta de deposito
				break;
			default:

				break;
			}
		} else {
			switch (this.uso) {
			case ClaseFondo.MEDIO_EFECTIVO:
				validarImporte();
				break;
			case ClaseFondo.MEDIO_CHEQUE_PROPIO:
				break;
			case ClaseFondo.MEDIO_CHEQUE_TERCERO:
				break;
			case ClaseFondo.MEDIO_ACREDITACION:

				break;
			case ClaseFondo.MEDIO_DEPOSITO:

				break;
			default:

				break;
			}
		}

		// if(monto == null || monto.equals(new Double(0)))
		// error.agregar("El monto de movimiento no puede ser 0(cero)");

		return !error.hayErrores();
	}


	/* Eston son metodos genericos de validacion de las partes de un medio */
	private void validarBeneficiario() {
		if (cheque.getBeneficiario() == null || cheque.getBeneficiario().trim().equals(""))
			error.agregar("Debe completar el beneficiario");
	}


	private void validarFechas() {
		if (cheque.getFechaEmision() == null || cheque.getFechaPago() == null) {
			error.agregar("La fecha de emision y de pago son requeridas");
		} else {
			if (cheque.getFechaEmision().after(cheque.getFechaPago()))
				error.agregar("La fecha de emision no puede ser posterior a la de pago");
		}
	}


	private void validarFechasPropias() {
		validarFechas();
		// Valida que las fechas esten en los parametros correctos
		if (!error.hayErrores()) {
			if (cheque.getFechaEmision().after(Fecha.addDias(cheque.getFechaPago(), 360)))
				error.agregar("La fecha de pago no puede ser mayor a 360 dias de emisión");
			else {
				try {
					Ejercicio ejercicio = contabilidadService.getEjercicioService().ejercicioActual();
					if (ejercicio.getFechaPeriodo().compareTo(cheque.getFechaEmision()) > 0)
						error.agregar("La fecha de emisión es menor al periodo contable activo");
					if (cheque.getFechaEmision().after(ejercicio.getFechaCierre()))
						error.agregar("La fecha de emisión es mayor al cierre contable activo");
				} catch (EjercicioException e) {
					error.agregar("Error. No se pudo verificar el periodo contable");
				}
			}
		}
	}


	private void validarImporte() {
		Double d0 = new Double(0);
		if (cheque.getImporte() == null || cheque.getImporte().equals(d0)) {
			error.agregar("El importe es un dato requerido");
		} else {
			if (cheque.getImporte().compareTo(d0) <= 0)
				error.agregar("El importe no puede ser negativo");
		}
	}

	public class Medio {
		private Cheque cheque;
		private ChequeHistorial historial;
		private boolean alta;
		private int indice;


		public Medio(Cheque cheque, ChequeHistorial historial, boolean alta) {
			this.cheque = cheque;
			this.historial = historial;
			this.alta = alta;
			indice = indiceDeMedios;
			indiceDeMedios = indiceDeMedios + 1;
		}


		public Cheque getCheque() {
			return cheque;
		}


		public void setCheque(Cheque cheque) {
			this.cheque = cheque;
		}


		public String getImporte() {
			return formateador.format(cheque.getImporte());
		}


		public ChequeHistorial getHistorial() {
			return historial;
		}


		public void setHistorial(ChequeHistorial historial) {
			this.historial = historial;
		}


		public boolean isAlta() {
			return alta;
		}


		public void setAlta(boolean alta) {
			this.alta = alta;
		}


		public String eliminar() {
			mediosList.remove(this);
			return null;
		}


		public int getIndice() {
			return indice;
		}


		/*
		 * Este equals lo utilizo para verificar si el medio ya fue cargado en la lista. Si el ID se encuentra en null significa que es un medio
		 * nuevo, por ende comparo el indice para saber si es la misma insercion.
		 */
		public boolean equals(Object obj) {
			Medio medioComp = ((Medio) obj);
			if (medioComp.getCheque().getIdCheque() == null) {
				return (indice == medioComp.getIndice());
			}
			return this.cheque.equals(medioComp.getCheque());
		}
	}

	public class ChequeWrapper {
		private Cheque cheque;


		public ChequeWrapper(Cheque cheque) {
			this.cheque = cheque;
		}


		public Cheque getCheque() {
			return cheque;
		}


		public void setCheque(Cheque cheque) {
			this.cheque = cheque;
		}


		public String seleccionar() {
			AdminChequeBean.this.cheque = this.cheque;
			cargarMedio();
			return null;
		}
	}


	public void cambiarTipoAccion(ValueChangeEvent event) {
		idTipoAccionSeleccionada = new Long(tipoAccion.getValue().toString());
		switch (idTipoAccionSeleccionada.intValue()) {
		case 0:
			if (bancoList == null) {
				try {
					Filtro filtro = new Filtro();
					filtro.agregarOrderBy("idBanco");
					bancoList = generalService.getBancoService().getBancos(filtro);
					bancoItems.add(new SelectItem(new Long(0), "Seleccione Banco"));
					bancoItems.addAll(Util.cargarSelectItem(bancoList));
				} catch (BancoException e) {
					error.agregar("Error al leer los bancos");
					e.printStackTrace();
				}
			}
			boolCbu = true;
			boolCodigo = false;
			boolCuenta = false;
			break;
		case 1:
			boolCodigo = true;
			boolCuenta = false;
			boolCbu = false;
			break;
		case 2:
			boolCodigo = true;
			boolCuenta = false;
			boolCbu = false;
			break;
		case 3:
			Iterator<BancoPropio> iter = bancosPropios.iterator();
			while (iter.hasNext()) {
				BancoPropio bancoPropio = (BancoPropio) iter.next();
				if (!bancoPropio.equals(cheque.getBancoPropio())) {
					cuentaPropiaItems.add(new SelectItem(bancoPropio.getId(), bancoPropio.getLabel()));
				}
			}
			boolCuenta = true;
			boolCbu = false;
			boolCodigo = false;
			break;
		default:
			break;
		}
	}


	/*
	 * busca el Beneficiario de acuerdo al codigo
	 */
	public String buscarBeneficiario() {
		error.borrar();
		cheque.setBeneficiario("");
		switch (idTipoAccionSeleccionada.intValue()) {
		case 0: // Por CBU
			if (cbu == null || cbu.length() != 22) {
				error.agregar("La longitud del CBU es incorrecta.");
			} else {
				if (NroBancarioValido.nroBancarioValido(cbu.substring(0, 8), "CBU 1er Bloque")
						&& NroBancarioValido.nroBancarioValido(cbu.substring(8, 22), "CBU 2do Bloque")) {
					if (idBancoSeleccionado.equals(new Long(cbu.substring(0, 3)))) {
						if (!nroCuenta.trim().equals("")) {
							cheque.setCbu(cbu);
							cheque.setBanco((Banco) Util.buscarElemento(bancoList, new Banco(idBancoSeleccionado)));
							cheque.setCuenta(nroCuenta);
							cheque.setTipoCuentaBanco((TipoCuentaBanco) Util.buscarElemento(tipoCtaList, new TipoCuentaBanco(idTipoCtaSeleccionado)));
							mostrarDetalle = true;
							boolAltaAcrd = false;
						} else {
							error.agregar("El nro de cuenta es un dato requerido.");
						}
					} else {
						error.agregar("El banco seleccionado no coincide con el del CBU.");
					}
				} else {
					error.agregar("CBU Invalido.");
				}
			}
			break;
		case 1: // Por COMERCIO
			try {
				Filtro filtro = new Filtro();
				if (codigo == null) {
					error.agregar("Debe ingresar un codigo");
					return "";
				} else {
					filtro.agregarCampoOperValor("codigoPosnet", Filtro.LIKEXS, codigo);
					List<CodComercio> lstCodComercio = transaccionesService.getCodComercioService().getCodComercio(filtro);
					if (lstCodComercio.isEmpty()) {
						error.agregar("El comercio buscado no existe");
					} else {
						CodComercio codComercio = lstCodComercio.get(0);
						Iterator iter = codComercio.getComercioFormaPagoSet().iterator();
						ComercioFormaPago formaPago = null;
						while (iter.hasNext()) {
							formaPago = (ComercioFormaPago) iter.next();
							if (formaPago.getFormaPago().getIdFormaPago().equals(new Long(3))
									&& formaPago.getActivo().equals("S")
									&& formaPago.getNroCuentaFondos().equals(cuenta.getIdPlanCuenta())) {
								break;
							}
						}
						if (formaPago != null) {
							cheque.setTipoCuentaBanco(formaPago.getTipoCuentaBanco());
							cheque.setCuenta(formaPago.getCodCuentaDeposito());
							cheque.setCbu(formaPago.getCbu());
							cheque.setBanco(formaPago.getBanco());
							cheque.setBeneficiario(codComercio.getSucEmpresa().getEmpresa().getRazonSocial());
							mostrarDetalle = true;
							boolAltaAcrd = false;
						} else {
							error.agregar("El comercio seleccionado no posee una forma de pago \"Acreditación\" para esta cuenta");
						}
					}
				}
			} catch (CodComercioException e) {
				error.agregar("Ocurrio un error al intentar leer el comercio");
				e.printStackTrace();
			}
			break;
		case 2: // Por PROVEEDOR
			try {
				if (codigo == null) {
					error.agregar("Debe ingresar un codigo");
					return "";
				} else {
					Proveedor proveedor = proveedoresService.getProveedorService().leerProveedor(codigo);
					if (proveedor == null) {
						error.agregar("El proveedor buscado no existe");
					} else {
						Iterator<ProveedorFormaPago> iter = proveedor.getFormasDePago().iterator();
						while (iter.hasNext()) {
							ProveedorFormaPago formaPago = iter.next();
							if (formaPago.getFormaPago().getIdFormaPago().equals(new Long(3))
									&& formaPago.getNroCuentaFondos().equals(cuenta.getIdPlanCuenta()))
								formaPagoList.add(formaPago);
						}
						if (formaPagoList.isEmpty()) {
							error.agregar("El proveedor seleccionado no posee una forma de pago \"Acreditación\" para esta cuenta");
						} else {
							cheque.setBeneficiario(proveedor.getRazonSocial());
							boolFormaPago = true;
						}
					}
				}
			} catch (ProveedorException e) {
				error.agregar("Ocurrio un error al intentar leer el proveedor");
				e.printStackTrace();
			}
			break;
		case 3: // Por CUENTA PROPIA
			BancoPropio cuentaPropio = (BancoPropio) Util.buscarElemento(bancosPropios, new BancoPropio(idCuentaPropiaSeleccionada));
			cheque.setTipoCuentaBanco(generalService.getTipoCuentaBancoDao().buscarTipoCuentaBanco(new Long(1)));
			cheque.setCuenta(cuentaPropio.getNumeroCuenta());
			cheque.setCbu(cuentaPropio.getCbu());
			cheque.setBanco(cuentaPropio.getBanco());
			cheque.setBeneficiario("Transf. a bco " + cuentaPropio.getBanco().getDescripcion());
			if (cheque.getBeneficiario().length() > 50)
				cheque.setBeneficiario(cheque.getBeneficiario().substring(0, 50));
			mostrarDetalle = true;
			boolAltaAcrd = false;
			break;
		default:
			break;
		}
		return "";
	}


	public String seleccionarFP() {
		if (!formaPagoList.isEmpty()) {
			Long idFP = new Long(Session.getRequestParameter("idFP"));
			ProveedorFormaPago formaPago = (ProveedorFormaPago) Util.buscarElemento(formaPagoList, new ProveedorFormaPago(idFP));
			cheque.setTipoCuentaBanco(formaPago.getTipoCta());
			cheque.setCuenta(formaPago.getCodCtaDeposito());
			cheque.setBanco(formaPago.getBanco());
			cheque.setCbu(formaPago.getCbu());
			mostrarDetalle = true;
			boolAltaAcrd = false;
			boolFormaPago = false;
		}

		return null;
	}

	// //setProveedor(false);
	// }
	// } else if(selection2) {
	//
	// } else {
	// error.agregar("Debe ingresar algún valor de busqueda");
	// }
	// cheque.setBeneficiario(formaPago.getProveedor().getRazonSocial());
	// mostrarDetalle = true;

	// public String listarCheque() {
	// chequeList.clear();
	// historicoList.clear();
	// try {
	// filtro.reset();
	// filtro.agregarJoin("historial his");
	//
	// if(idCuentaSeleccionada != null && !idCuentaSeleccionada.equals("0"))
	// filtro.agregarCampoOperValor("tipo", Filtro.LIKEXS, idCuentaSeleccionada);
	// if(idBancoSeleccionada != null && !idBancoSeleccionada.equals(new Long(0)))
	// filtro.agregarCampoOperValor("banco.idBanco", Filtro.IGUAL, idBancoSeleccionada);
	//
	// if (selectFiltro.equals("1")) {
	// if(idEstadoSeleccionada != null && !idEstadoSeleccionada.equals(new Long(0)))
	// filtro.agregarfuncion(" AND his.chequeEstado.idChequeEstado = " + idEstadoSeleccionada);
	// beneficiario = beneficiario.trim();
	// if(!beneficiario.equals(""))
	// filtro.agregarCampoOperValor("beneficiario", Filtro.LIKE, beneficiario);
	// if(nroChequeDesde != null && !nroChequeDesde.equals(""))
	// filtro.agregarCampoOperValor("numero", Filtro.MAYOR_IGUAL, nroChequeDesde);
	// if(nroChequeHasta != null && !nroChequeHasta.equals(""))
	// filtro.agregarCampoOperValor("numero", Filtro.MENOR_IGUAL, nroChequeHasta);
	// if(fechaEmisionDesde != null)
	// filtro.agregarCampoOperValor("fechaEmision", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(fechaEmisionDesde));
	// if(fechaEmisionHasta != null)
	// filtro.agregarCampoOperValor("fechaEmision", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(Fecha.addDias(fechaEmisionHasta, 1)));
	// if(fechaPagoDesde != null)
	// filtro.agregarCampoOperValor("fechaPago", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(fechaPagoDesde));
	// if(fechaPagoHasta != null)
	// filtro.agregarCampoOperValor("fechaPago", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(Fecha.addDias(fechaPagoHasta, 1)));
	// }else {
	// if(idEstadoSeleccionada != null && !idEstadoSeleccionada.equals(new Long(0)))
	// filtro.agregarfuncion(" AND his.chequeEstado.idChequeEstado = " + idEstadoSeleccionada);
	// else
	// error.agregar("Para la composición se debe seleccionar un estado");
	//
	// if(fechaEstado != null) {
	// filtro.agregarfuncion(" AND his.timestamp = (SELECT MAX(his1.timestamp)" +
	// " FROM obj.historial his1 " +
	// " WHERE his1.timestamp <= " + Filtro.getTO_DATE(fechaEstado) +
	// " AND his1.cheque.idCheque  = obj.idCheque) ");
	// }else
	// error.agregar("Para la composición se debe seleccionar una fecha de estado");
	// }
	//
	//
	// // filtro.agregarCampoOperValor("concepto", Filtro.IGUAL, idConceptoSeleccionada);
	//
	// // filtro.agregarCampoOperValor("idCheque", Filtro.IN, "SELECT MAX(idCheque) FROM Cheque GROUP BY numero");
	//
	// if(filtro.getCampos().size() == 0)
	// filtro.agregarCampoOperValor("idCheque", Filtro.NOTNULL, "");
	// if(idOrdenSeleccionada != null && !idOrdenSeleccionada.equals(new Integer(0))){
	// SelectItem item = (SelectItem)ordenItems.get(idOrdenSeleccionada.intValue());
	// filtro.agregarOrderBy(item.getDescription());
	// }
	// if (!error.hayErrores())
	// pagDeMov = new PaginadorPorDemanda(filtro,(Paginacion)fondosService.getChequeService(),chequeList,4, error,
	// "/tarjetafiel/fondos/listarCheque.jsf");
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// // Session.redirect("/tarjetafiel/fondos/listarCheque.jsf");
	// return null;
	// }

	// class ComparatorCuotas implements Comparator{
	// public int compare(Object arg0, Object arg1) {
	// if(((Cuota)arg0).getTotalCuota() < ((Cuota)arg1).getTotalCuota()){
	// return 1;
	// } else if(((Cuota)arg0).getTotalCuota() > ((Cuota)arg1).getTotalCuota()){
	// return -1;
	// }
	// return 0;
	// }
	//
	// }

}
