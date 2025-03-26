package com.bizitglobal.webapp.faces.beans.proveedores;

/*
 * VER BIEN EL FUNCIONAMIENTO DE LA ALTA BAJA Y EDICION DE LOS TELEFONOS 
 * ESTE ES EL NOMBRE DE LA VIEJA LISTA DE TELEFONOS tablaDeTelefonos.
 * LOS LUGARES DONDE SE USA SE HAN COMENTARIZADO CON LETRA MATUSCULA.
 */
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Email;
import com.bizitglobal.tarjetafiel.general.negocio.ModalidadPago;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Individuo;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteException;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuitDuplicadoException;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuitNoValidoException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuitValido;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorDomicilio;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorEmail;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorFormaPago;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorTelefono;
import com.bizitglobal.tarjetafiel.proveedores.negocio.TipoVencimiento;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.general.DomicilioBean;
import com.bizitglobal.webapp.faces.beans.general.TelefonoBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.service.general.GeneralServiceFaces;
import com.bizitglobal.webapp.faces.service.proveedores.ProveedoresServiceFaces;
import com.bizitglobal.webapp.faces.util.Convertidores;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.ImpuestoEditable;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


@SuppressWarnings({"rawtypes","unchecked"})
public class ProveedorBean extends BaseBean {
	private static final long serialVersionUID = -5480330132332245035L;

	/*
	 * Campos propios del objeto proveedor
	 */
	private Long idProveedor;
	private String cuit;
	private Integer digitoVerificadorCuit;
	private String razonSocial;
	private String alias;
	private String nombreFantasia;
	private String inscripcionDgr;
	private String condIva;
	private BigDecimal limiteCredito;
	private String codIva;
	private Character integranteSoc;
	private Character empleador;
	private Timestamp timestamp;
	private List domicilios;

	private String cuitDni;
	private String cuitVerificador;
	private String cuitIdentificador;

	private Proveedor proveedor = null;

	private List proveedores;
	/*
	 * Campos propios del bean.
	 */
	private ProveedoresServiceFaces service;
	private GeneralServiceFaces serviceGeneral;
	private static final Logger log = Logger.getLogger(ProveedorBean.class);

	private List listaDeSucursales;
	private Long sucursalSeleccionda;

	private List listaDeGrupos;
	private Long grupoSeleccionado;
	private List listaDeJurisdicciones;
	private Long jurisdiccionSeleccionada;

	private List tablaDeEmails;
	private List tablaDeDomicilios;
	// private List tablaDeTelefonos;
	private List tablaDeRelacionConProveedores;
	private List tablaDeRelacionConClientes;
	private List tablaDeImpuestos;
	private List tablaDeComposicionDePago;
	private List tablaDeFormaDePago;
	private List listaTelefonos;

	private Boolean fechaFactura;
	private Boolean diaCC;
	private List listaDeDiasSemanaNombre;
	private List listaDeDiasMes;

	private Integer diaDeLaSemana;
	private String nombreDia;
	private Integer diaDelMes;

	// Propiedades para la busqueda/filtro del popup clientes.
	private String codigoFiltroCliente;
	private String apellidoFiltroCliente;

	// Propiedades para el popup de domicilios.
	private Domicilio domicilio = new Domicilio();

	// Propiedades para el popup de formas de pagos.
	private ProveedorFormaPago formaPago = new ProveedorFormaPago();
	private List temporalFP = new ArrayList(); // Contiene las formas de pago eliminadas.

	// Propiedades para validar el cuit.
	private String cuitInvalido = new String();
	private boolean validado = false;

	// Propiedad que indica si se esta haciendo un alta o una modificacion.
	private boolean alta = true;
	private String tituloLargo = "Tarjeta Fiel - Proveedores";
	private String tituloCorto = "Alta de proveedores";

	// lista de tipos de telefonos para todos los telefonos del sistema.
	private List listaDeTiposDeTelefonos;

	// Propiedades para la edicion y borrado de proveedores.
	private Integer idEmail;
	private Integer idProveedorHidden;

	// Propiedades agregadas para la composición de compra.
	private Boolean fechaF;
	private Boolean cuentaC;

	private List listaDiasDeLaSemana;

	private boolean verModificar = true;


	public ProveedorBean() {
		this(null, null, null, null, null, null, null, null, null, null, null);

	}


	public ProveedorBean(String cuit, Integer digitoVerificadorCuit,
			String razonSocial, String nombreFantasia, String inscripcionDgr,
			String condIva, BigDecimal limiteCredito, String codIva,
			Character integranteSoc, Character empleador, Timestamp timestamp) {
		super();

		// Inicializar el service de proveedores.
		service = new ProveedoresServiceFaces();
		serviceGeneral = new GeneralServiceFaces();

		/*
		 * Inicializa el id interno del proveedor, si falla la inicializacion, entonces de seta en cero y luego al grabar se le dara un valor
		 * correcto.
		 */
		try {
			this.idProveedor = service.getProveedorService().maxIdProveedor();
		} catch (ProveedorException e) {
			this.idProveedor = new Long(0);
		}

		this.cuit = cuit;
		this.digitoVerificadorCuit = digitoVerificadorCuit;
		this.razonSocial = new String();
		this.nombreFantasia = nombreFantasia;
		this.inscripcionDgr = inscripcionDgr;
		this.condIva = condIva;
		this.limiteCredito = limiteCredito;
		this.codIva = codIva;
		this.integranteSoc = integranteSoc;
		this.empleador = empleador;
		this.timestamp = timestamp;

		this.listaDeSucursales = ProveedorUtil.cargarListaSucursales(service.getSucursalFielDao());
		this.sucursalSeleccionda = null;

		this.listaDeGrupos = ProveedorUtil.cargarListaGrupos(service.getGrupoDao());
		this.grupoSeleccionado = null;

		this.listaDeJurisdicciones = ProveedorUtil.cargarListaJurisdicciones(impuestoService.getJurisdiccionDao());
		this.jurisdiccionSeleccionada = null;

		Email auxEmail = new Email();
		auxEmail.setIdEmail(new Long(auxEmail.hashCode() + ""));
		this.tablaDeDomicilios = new ArrayList();
		this.tablaDeEmails = new ArrayList();
		// this.tablaDeEmails.add(new ProveedorEmail(null,auxEmail));

		// this.tablaDeTelefonos = new ArrayList();

		Telefono auxTelefono = new Telefono();
		auxTelefono.setIdTelefono(new Long(auxTelefono.hashCode() + ""));
		// this.tablaDeTelefonos.add(new ProveedorTelefono(auxTelefono,null)); TELEFONO VIEJO;
		this.tablaDeRelacionConProveedores = new ArrayList();
		this.tablaDeRelacionConClientes = new ArrayList();

		this.tablaDeImpuestos = null;

		// this.tablaDeComposicionDePago = new ArrayList();
		// TipoVencimiento tv = new TipoVencimiento();
		// tv.setIdTipoVencimiento(new Long(1));
		// tv.setDias(new Integer(1));
		// tv.setPorcentajeMonto(new Integer(100));
		// this.tablaDeComposicionDePago.add(tv);
		this.tablaDeFormaDePago = new ArrayList();

		this.fechaFactura = new Boolean(false);
		this.diaCC = new Boolean(false);

		this.listaDeDiasSemanaNombre = ProveedorUtil.cargarListaDiasSemanaNombre();
		this.listaDeDiasMes = ProveedorUtil.cargarListaDiasMes();
		this.listaDiasDeLaSemana = ProveedorUtil.cargarListaDiasSemana();

		this.diaDeLaSemana = null;
		this.diaDelMes = null;
		this.nombreDia = null;

		// Cargando la lista de tipos de telefonos.

		try {
			listaDeTiposDeTelefonos = ProveedorUtil.cargarListaDeTiposDeTelefonos(service.getTipoTelefonoDao());
		} catch (Exception e) {
			e.printStackTrace();
		}

		cuitDni = null;
		cuitIdentificador = null;
		cuitVerificador = null;

		log.info("Construyendo!!!");
	}


	/**
	 * Llamado desde el menú, permite inicilizar el contenido del bean cada vez que se invoque.
	 */
	public String inicializar() {
		borrar();

		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}

		if (Session.getBean("BuscarProveedorBean") != null) {
			BuscarProveedorBean bean = (BuscarProveedorBean) Session.getBean("BuscarProveedorBean");
			bean.borrar();
		}

		return "altaProveedores";
	}


	public List getListaTelefonos() {
		return listaTelefonos;
	}


	public void setListaTelefonos(List listaTelefonos) {
		this.listaTelefonos = listaTelefonos;
	}


	public boolean getVerModificar() {
		log.info("VerModificar: " + verModificar);
		return verModificar;
	}


	public void setVerModificar(boolean verModificar) {
		this.verModificar = verModificar;
	}


	public List getListaDiasDeLaSemana() {
		return listaDiasDeLaSemana;
	}


	public void setListaDiasDeLaSemana(List listaDiasDeLaSemana) {
		this.listaDiasDeLaSemana = listaDiasDeLaSemana;
	}


	public String getCodIva() {
		return codIva;
	}


	public void setCodIva(String codIva) {
		this.codIva = codIva;
	}


	public String getCondIva() {
		return condIva;
	}


	public void setCondIva(String condIva) {
		this.condIva = condIva;
	}


	public String getCuit() {
		return cuit;
	}


	public void setCuit(String cuit) {
		this.cuit = cuit;
	}


	public Integer getDigitoVerificadorCuit() {
		return digitoVerificadorCuit;
	}


	public void setDigitoVerificadorCuit(Integer digitoVerificadorCuit) {
		this.digitoVerificadorCuit = digitoVerificadorCuit;
	}


	public Character getEmpleador() {
		return empleador;
	}


	public void setEmpleador(Character empleador) {
		this.empleador = empleador;
	}


	public Long getIdProveedor() {
		return idProveedor;
	}


	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}


	public String getInscripcionDgr() {
		return inscripcionDgr;
	}


	public void setInscripcionDgr(String inscripcionDgr) {
		this.inscripcionDgr = inscripcionDgr;
	}


	public Character getIntegranteSoc() {
		return integranteSoc;
	}


	public void setIntegranteSoc(Character integranteSoc) {
		this.integranteSoc = integranteSoc;
	}


	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}


	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}


	public String getNombreFantasia() {
		return nombreFantasia;
	}


	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public List getListaDeSucursales() {
		return listaDeSucursales;
	}


	public void setListaDeSucursales(List listaDeSucursales) {
		this.listaDeSucursales = listaDeSucursales;
	}


	public Long getSucursalSeleccionda() {
		return sucursalSeleccionda;
	}


	public void setSucursalSeleccionda(Long sucursalSeleccionda) {
		this.sucursalSeleccionda = sucursalSeleccionda;
	}


	public Long getGrupoSeleccionado() {
		return grupoSeleccionado;
	}


	public void setGrupoSeleccionado(Long grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}


	public List getListaDeGrupos() {
		return listaDeGrupos;
	}


	public void setListaDeGrupos(List listaDeGrupos) {
		this.listaDeGrupos = listaDeGrupos;
	}


	public List getTablaDeDomicilios() {
		return tablaDeDomicilios;
	}


	public void setTablaDeDomicilios(List tablaDeDomicilios) {
		this.tablaDeDomicilios = tablaDeDomicilios;
	}


	public List getTablaDeEmails() {
		return tablaDeEmails;
	}


	public void setTablaDeEmails(List tablaDeEmails) {
		this.tablaDeEmails = tablaDeEmails;
	}


	// public List getTablaDeTelefonos() {
	// return tablaDeTelefonos;
	// }
	//
	// public void setTablaDeTelefonos(List tablaDeTelefonos) {
	// this.tablaDeTelefonos = tablaDeTelefonos;
	// }

	public List getTablaDeRelacionConProveedores() {
		return tablaDeRelacionConProveedores;
	}


	public void setTablaDeRelacionConProveedores(List tablaDeRelacionConProveedores) {
		this.tablaDeRelacionConProveedores = tablaDeRelacionConProveedores;
	}


	public List getTablaDeRelacionConClientes() {
		return tablaDeRelacionConClientes;
	}


	public void setTablaDeRelacionConClientes(List tablaDeRelacionConClientes) {
		this.tablaDeRelacionConClientes = tablaDeRelacionConClientes;
	}


	public List getTablaDeComposicionDePago() {
		return tablaDeComposicionDePago;
	}


	public void setTablaDeComposicionDePago(List tablaDeComposicionDePago) {
		this.tablaDeComposicionDePago = tablaDeComposicionDePago;
	}


	public List getTablaDeFormaDePago() {
		return tablaDeFormaDePago;
	}


	public void setTablaDeFormaDePago(List tablaDeFormaDePago) {
		this.tablaDeFormaDePago = tablaDeFormaDePago;
	}


	public Boolean getDiaCC() {
		return diaCC;
	}


	public void setDiaCC(Boolean diaCC) {
		this.diaCC = diaCC;
	}


	public Integer getDiaDeLaSemana() {
		return diaDeLaSemana;
	}


	public void setDiaDeLaSemana(Integer diaDeLaSemana) {
		this.diaDeLaSemana = diaDeLaSemana;
	}


	public Integer getDiaDelMes() {
		return diaDelMes;
	}


	public void setDiaDelMes(Integer diaDelMes) {
		this.diaDelMes = diaDelMes;
	}


	public Boolean getFechaFactura() {
		return fechaFactura;
	}


	public void setFechaFactura(Boolean fechaFactura) {
		this.fechaFactura = fechaFactura;
	}


	public String getNombreDia() {
		return nombreDia;
	}


	public void setNombreDia(String nombreDia) {
		this.nombreDia = nombreDia;
	}


	public String getApellidoFiltroCliente() {
		return apellidoFiltroCliente;
	}


	public void setApellidoFiltroCliente(String apellidoFiltroCliente) {
		this.apellidoFiltroCliente = apellidoFiltroCliente;
	}


	public String getCodigoFiltroCliente() {
		return codigoFiltroCliente;
	}


	public void setCodigoFiltroCliente(String codigoFiltroCliente) {
		this.codigoFiltroCliente = codigoFiltroCliente;
	}


	public Domicilio getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}


	public List getDomicilios() {
		return domicilios;
	}


	public void setDomicilios(List domicilios) {
		this.domicilios = domicilios;
	}


	public void setFormaPago(ProveedorFormaPago formaPago) {
		this.formaPago = formaPago;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public ProveedorFormaPago getFormaPago() {
		return formaPago;
	}


	public Long getJurisdiccionSeleccionada() {
		return jurisdiccionSeleccionada;
	}


	public void setJurisdiccionSeleccionada(Long jurisdiccionSeleccionada) {
		this.jurisdiccionSeleccionada = jurisdiccionSeleccionada;
	}


	public List getListaDeJurisdicciones() {
		return listaDeJurisdicciones;
	}


	public void setListaDeJurisdicciones(List listaDeJurisdicciones) {
		this.listaDeJurisdicciones = listaDeJurisdicciones;
	}


	public List getTablaDeImpuestos() {
		return tablaDeImpuestos;
	}


	public void setTablaDeImpuestos(List tablaDeImpuestos) {
		this.tablaDeImpuestos = tablaDeImpuestos;
	}


	public List getListaDeDiasMes() {
		return listaDeDiasMes;
	}


	public void setListaDeDiasMes(List listaDeDiasMes) {
		this.listaDeDiasMes = listaDeDiasMes;
	}


	public List getListaDeDiasSemanaNombre() {
		return listaDeDiasSemanaNombre;
	}


	public void setListaDeDiasSemanaNombre(List listaDeDiasSemanaNombre) {
		this.listaDeDiasSemanaNombre = listaDeDiasSemanaNombre;
	}


	public String getCuitInvalido() {
		return cuitInvalido;
	}


	public void setCuitInvalido(String cuitInvalido) {
		this.cuitInvalido = cuitInvalido;
	}


	public boolean getValidado() {
		return validado;
	}


	public void setValidado(boolean validado) {
		this.validado = validado;
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


	public Proveedor getProveedor() {
		return proveedor;
	}


	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}


	public List getListaDeTiposDeTelefonos() {
		return listaDeTiposDeTelefonos;
	}


	public void setListaDeTiposDeTelefonos(List listaDeTiposDeTelefonos) {
		this.listaDeTiposDeTelefonos = listaDeTiposDeTelefonos;
	}


	public String getCuitDni() {
		return cuitDni;
	}


	public void setCuitDni(String cuitDni) {
		this.cuitDni = cuitDni;
	}


	public String getCuitIdentificador() {
		return cuitIdentificador;
	}


	public void setCuitIdentificador(String cuitIdentificador) {
		this.cuitIdentificador = cuitIdentificador;
	}


	public String getCuitVerificador() {
		return cuitVerificador;
	}


	public void setCuitVerificador(String cuitVerificador) {
		this.cuitVerificador = cuitVerificador;
	}


	public Integer getIdEmail() {
		return idEmail;
	}


	public void setIdEmail(Integer idEmail) {
		this.idEmail = idEmail;
	}


	public Integer getIdProveedorHidden() {
		return idProveedorHidden;
	}


	public void setIdProveedorHidden(Integer idProveedorHidden) {
		this.idProveedorHidden = idProveedorHidden;
	}


	public Boolean getCuentaC() {
		return cuentaC;
	}


	public void setCuentaC(Boolean cuentaC) {
		this.cuentaC = cuentaC;
	}


	public Boolean getFechaF() {
		return fechaF;
	}


	public void setFechaF(Boolean fechaF) {
		this.fechaF = fechaF;
		if (fechaF.booleanValue()) {
			limpiarCompDePagos();
		}
	}


	public List getProveedores() {
		return proveedores;
	}


	public void setProveedores(List proveedores) {
		this.proveedores = proveedores;
	}


	private String limpiarCompDePagos() {
		tablaDeComposicionDePago = new ArrayList();
		TipoVencimiento tp = new TipoVencimiento();
		tp.setIdTipoVencimiento(new Long(1));
		tp.setDias(new Integer(0));
		tp.setPorcentajeMonto(new Integer(100));
		tablaDeComposicionDePago.add(tp);
		return "";
	}


	/*
	 * ************************************************************************************
	 * ********************** ACCIONES PARA EL BEAN DE PROVEEDORES ************************
	 * ************************************************************************************
	 */

	public String agregarEmail() {
		log.info("Agregando email!!!");
		if (!tablaDeEmails.equals(null)) {
			Email ultimo;
			if (tablaDeEmails.isEmpty()) {
				ultimo = new Email();
				ultimo.setEmail("algo");
			} else {
				ultimo = ((ProveedorEmail) tablaDeEmails.get(tablaDeEmails.size() - 1)).getEmail();
			}
			if (!ultimo.getEmail().equals("") && ultimo.getEmail() != null) {
				Email aux = new Email();
				aux.setIdEmail(new Long("" + aux.hashCode()));
				tablaDeEmails.add(new ProveedorEmail(null, aux));
			}
		}
		log.info("Long: " + tablaDeEmails.size());
		return null;
	}


	public String eliminarEmail() {
		if (tablaDeEmails.size() > 0) {
			Long idEmail = new Long(Session.getRequestParameter("idEmail"));
			log.info("id email a eliminar: " + idEmail);
			tablaDeEmails = ProveedorUtil.eliminarEmail(tablaDeEmails, idEmail);
		}

		return null;
	}


	public String agregarDomicilio() {
		return null;
	}


	public String agregarRelacionConProveedores() {
		return null;
	}


	public String agregarRelacionConClientes() {
		return null;
	}


	public String agregarImpuestoNacional() {
		return null;
	}


	public String agregarImpuestoProvincial() {
		return null;
	}


	public String agregarComposicionDePago() {
		log.info("Agregando composición de pago!!!");
		error.borrar();
		int suma = 0;
		if (!tablaDeComposicionDePago.isEmpty()) { // Valida que los campos anteriores esten cargados
			Iterator iterCompPago = tablaDeComposicionDePago.iterator();
			while (iterCompPago.hasNext()) {
				TipoVencimiento aux = (TipoVencimiento) iterCompPago.next();
				if (aux.getDias() == null || aux.getPorcentajeMonto() == null || aux.getPorcentajeMonto().equals(new Integer(0))) {
					error.agregar("Los campos se deben completar para seguir agregando cuotas(el procentaje no puede ser 0)");
					return null;
				}
				suma += aux.getPorcentajeMonto().intValue();
			}
			if (suma >= 100) {
				return null;
			}
		}

		if ((tablaDeComposicionDePago.size() > 0)) {
			TipoVencimiento aux = new TipoVencimiento();
			TipoVencimiento ultimo = (TipoVencimiento) tablaDeComposicionDePago.get(tablaDeComposicionDePago.size() - 1);
			aux.setIdTipoVencimiento(new Long(ultimo.getIdTipoVencimiento().intValue() + 1));
			tablaDeComposicionDePago.add(aux);
		}
		log.info("Long: " + tablaDeComposicionDePago.size());
		return null;
	}


	public String eliminarComposicionDePago() {
		if (tablaDeComposicionDePago.size() > 1) {
			Long idCompPago = new Long(Session.getRequestParameter("idCompPago"));
			tablaDeComposicionDePago = ProveedorUtil.eliminarCompPago(tablaDeComposicionDePago, idCompPago);
		}
		return null;
	}


	public String editarDomicilio() {
		Long idDomicilio = new Long(Session.getRequestParameter("idDomiEdi"));
		log.info("id a buscar: " + idDomicilio);
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		Domicilio domAux = ProveedorUtil.buscarDomicilio(tablaDeDomicilios, idDomicilio);
		log.info(domAux);
		bean.inicializar(DomicilioBean.PROVEEDOR, domAux);
		bean.levantarListaPais(domAux.getLocalidad().getProvincia().getPais().getIdPais(),
				domAux.getLocalidad().getProvincia().getIdProvincia(), domAux.getLocalidad().getPartido().getIdPartido(),
				domAux.getLocalidad().getIdLocalidad(), domAux.getBarrio().getIdBarrio());

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	public String eliminarDomicilio() {
		Long idDomicilio = new Long(Session.getRequestParameter("idDomicilio"));
		tablaDeDomicilios = ProveedorUtil.eliminarDomicilio(tablaDeDomicilios, idDomicilio);

		return null;
	}


	public String eliminarRelacionConProveedor() {
		Long idProveedor = new Long(Session.getRequestParameter("idProveedor"));
		tablaDeRelacionConProveedores = ProveedorUtil.eliminarRCP(tablaDeRelacionConProveedores, idProveedor);

		return null;
	}


	public String eliminarRelacionConCliente() {
		Long idCliente = new Long(Session.getRequestParameter("idCliente"));
		tablaDeRelacionConClientes = ProveedorUtil.eliminarRCC(tablaDeRelacionConClientes, idCliente);

		return null;
	}


	public String grabarProveedor() {
		log.info("Entrando a grabar proveedor!!!");

		try {
			if (validar()) {
				Proveedor proveedor = generarProveedor();
				if (alta) {
					service.getProveedorService().grabarProveedor(proveedor);
					this.alta = false;
				} else {
					service.getProveedorService().actualizarProveedor(proveedor);
				}

				// tablaDeFormaDePago = proveedoresService.getProveedorFormaPagoService().getProveedorFormaPagos(
				// new Filtro("proveedor.idProveedor",Filtro.IGUAL,proveedor.getIdProveedor()));

				log.info("Grabando proveedor!!!");
				popup.setPopup(popup.ICONO_OK, "El proveedor ha sido almacenado exitosamente.");
			} else {
				ScrollBean scroll = (ScrollBean) Session.getBean("ScrollBean");
				scroll.setHiddenScrollY(new Integer(0));
			}
			verModificar = true;
		} catch (ProveedorDuplicateException e1) {
			verModificar = false;
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del proveedor.");
			e1.printStackTrace();
		} catch (ProveedorException e2) {
			verModificar = false;
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del proveedor.");
			e2.printStackTrace();
		} catch (Exception e3) {
			verModificar = false;
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del proveedor.");
			e3.printStackTrace();
		}
		ejecutarJavaScript("viewDialog();");
		return "";
	}


	public String cancelarProveedor() {
		log.info("Se cancela el proveedor");
		return listarProveedores();
	}


	public String filtrarClientes() {
		log.info("filtrando clientes!!!");
		return null;
	}


	public String aceptarFiltroCliente() {
		log.info("aceptar filtro clientes!!!");
		return null;
	}


	public String agregarDomicilioPopup() {
		log.info("Ir a agrega nuevo domicilio al proveedor!!!");
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		bean.inicializar(DomicilioBean.PROVEEDOR, new Domicilio());

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	public String agregarRelProvPopup() {
		log.info("Ir a agrega relacion con proveedor!!!");
		BuscarProveedorBean bean = (BuscarProveedorBean) Session.getBean("BuscarProveedorBean");
		bean.inicializar(BuscarProveedorBean.PROVEEDOR);

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/proveedores/popup/buscarProveedor.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}


	public String agregarRelClientePopup() {
		log.info("Ir a agrega relacion con cliente!!!");

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/proveedores/popup/buscarCliente.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}


	public String irAgregarFormaDePago() {
		log.info("Ir a agrega nueva forma de pago!!!");
		FormaDePagoBean bean = (FormaDePagoBean) Session.getBean("FormaDePagoBean");
		bean.inicializar(razonSocial, sucursalSeleccionda.toString());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/proveedores/popup/formaPago.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}


	// Retorna a la página llamadora del popup.
	public String volver() {
		return null;
	}


	public String agregarFormaPago() {
		return null;
	}


	public void borrar() {
		super.borrarBaseBean(); // // Borramos las propiedades de la super clase.
		proveedor = new Proveedor();
		idProveedor = new Long(0);
		cuit = null;
		digitoVerificadorCuit = null;
		razonSocial = null;
		alias = null;
		nombreFantasia = null;
		grupoSeleccionado = new Long(0);
		jurisdiccionSeleccionada = new Long(0);
		inscripcionDgr = null;
		condIva = null;
		limiteCredito = null;
		codIva = null;
		integranteSoc = null;
		empleador = null;
		timestamp = null;
		domicilios = new ArrayList();
		temporalFP = new ArrayList();
		tablaDeEmails = new ArrayList();
		Email auxEmail = new Email();
		auxEmail.setIdEmail(new Long(auxEmail.hashCode() + ""));
		// tablaDeEmails.add(new ProveedorEmail(null,auxEmail));
		// tablaDeTelefonos = new ArrayList();
		// Telefono auxTelefono = new Telefono();
		// auxTelefono.setIdTelefono(new Long(auxTelefono.hashCode()+""));
		// tablaDeTelefonos.add(new ProveedorTelefono(auxTelefono,null));
		limpiarCompDePagos();
		tablaDeDomicilios = new ArrayList();
		tablaDeRelacionConClientes = new ArrayList();
		tablaDeRelacionConProveedores = new ArrayList();
		tablaDeFormaDePago = new ArrayList();
		fechaFactura = new Boolean(false);
		diaCC = new Boolean(false);
		diaDeLaSemana = null;
		nombreDia = null;
		diaDelMes = null;
		fechaF = new Boolean(true);
		cuentaC = new Boolean(false);
		alta = true;
		cuitInvalido = "";
		tituloLargo = "Tarjeta Fiel - Alta de proveedores";
		tituloCorto = "Alta de proveedores";
		validado = false;
		proveedores = new ArrayList();
		listaTelefonos = new ArrayList();

	}


	public Proveedor generarProveedor() {
		log.info("Generando proveedor --> " + cuitIdentificador + cuitDni + cuitVerificador);

		// Crear el proveedor para grabar en la base de datos.
		log.info("Construyendo el proveedor.");

		if (proveedor == null) {
			proveedor = new Proveedor();
		}

		// proveedor.setIdProveedor(idProveedor);

		// Crear una jurisdiccion en base a la jurisdiccion seleccionada en al presentacion y setearla a el proveedor.
		log.info("Contruyendo una Jurisdiccion.");
		Jurisdiccion jurisdiccion = impuestoService.getJurisdiccionDao().buscarJurisdiccion(jurisdiccionSeleccionada);
		proveedor.setJurisdiccion(jurisdiccion);

		// Agrega relaciones con clientes a el objeto relacional.
		log.info("Agregando relacion con clientes.");
		proveedor.setClieRelaciones(ProveedorUtil.cargarRelacionConClientes(tablaDeRelacionConClientes, proveedor));

		// Crear un set vacio para comprobantes y agregarlo al proveedor.
		// log.info("Setea una lista de comprobantes vacios.");
		// proveedor.setComprobantes(new HashSet());

		// Setea un cuit valido al proveedor.
		log.info("Setea un cuit valido para el proveedor.");
		try {
			proveedor.setCuit(new CuitValido(cuitIdentificador + cuitDni + cuitVerificador).getCuit());
		} catch (CuitNoValidoException e) {
			e.printStackTrace();
		}

		// Setea inscripcion en dgr.
		log.info("Setea inscripcion en dgr.");
		proveedor.setInscripcionDgr(inscripcionDgr);

		// Setea una lista de domiciolios al proveedor.
		log.info("Setea una lista de domicilios para el proveedor.");
		proveedor.setDomicilios(ProveedorUtil.cargarDomicilios(tablaDeDomicilios, proveedor));

		// Setea una lista de emails para el proveedor.
		log.info("Setea una lista de email para el proveedor.");
		proveedor.setEmails(ProveedorUtil.cargarEmails(tablaDeEmails, proveedor));

		// Setea el campo es empleador.
		log.info("Setea el campo es empleador.");
		proveedor.setEmpleador(new Character('S'));

		proveedor.setFechaFactura(fechaF.booleanValue() ? new Character('S') : new Character('N'));

		// Setea el grupo seleccionado en la presenacion y lo agrega al proveedor.
		log.info("Setea el grupo seleccionado.");
		// Grupo unGrupo = new Grupo();
		// unGrupo.setIdGrupo(new Long(grupoSeleccionado.toString()));
		proveedor.setGrupo(proveedoresService.getGrupoDao().buscarGrupo(grupoSeleccionado));

		// proveedor.setInscripcionDgr(proveedor.getInscripcionDgr());

		// Setea el limite de credito.
		log.info("Setea el limito de credito.");
		if (Validador.esNulo(limiteCredito)) {
			proveedor.setLimiteCredito(new BigDecimal(0));
		} else {
			proveedor.setLimiteCredito(limiteCredito);
		}
		// Setea la modalidad de pago para el proveedor.
		log.info("Setea la modalidad de pago para el proveedor");
		if (proveedor.getModalidadPago() == null) {
			proveedor.setModalidadPago(new ModalidadPago());
		}
		proveedor.getModalidadPago().setEsDiaCalendario(diaCC.booleanValue() ? "S" : "N");
		proveedor.getModalidadPago().setEsDiaSemana(fechaFactura.booleanValue() ? "S" : "N");
		proveedor.getModalidadPago().setNombreDia(fechaFactura.booleanValue() ? nombreDia : null);
		proveedor.getModalidadPago().setNroDia(fechaFactura.booleanValue() ? diaDeLaSemana : diaDelMes);

		// Setea la moneda para el proveedor.
		Moneda moneda = new Moneda();
		moneda.setIdMoneda(new Long(1));
		moneda.setDescripcion("Pesos");
		proveedor.setMoneda(moneda);

		// Setea datos varios.
		log.info("Setea datos varios");
		proveedor.setNombreFantasia(nombreFantasia);
		proveedor.setOperador(Session.getOperador());
		proveedor.setRazonSocial(razonSocial);
		proveedor.setAlias(alias);

		// Agregar la relacion del proveedor con la actividad para el impuesto segun su categoria y jurisdiccion.
		log.info("Setes la relacion del proveedor con la categoria del impuesto.");
		proveedor.setProvedorCategoria(ProveedorUtil.cargarImpuestos(tablaDeImpuestos, proveedor,
				impuestoService.getCategoriaDao(), impuestoService.getJurisdiccionActividadDao()));

		// Agregar las relaciones con los proveedores del sistema.
		log.info("Agregar las relaciones con proveedores.");
		proveedor.setRelaciones(ProveedorUtil.cargarRelacionConProveedores(tablaDeRelacionConProveedores, proveedor));

		// Seteando la sucursal(si es distinta de cero) para el proveedor.
		log.info("Seteando la sucursal para el proveedor.");
		if (!sucursalSeleccionda.equals(new Long(0))) {
			proveedor.setSucursalFiel(generalService.getSucursalDao().buscarSucursalFiel(sucursalSeleccionda));
		}

		grabarTelefonos();

		proveedor.setTimestamp(new Timestamp(new Date().getTime()));

		// Seteando la lista de composicion de pagos.
		proveedor.setTiposVtos(ProveedorUtil.recargarComposicionDePagos(tablaDeComposicionDePago, proveedor));

		if (proveedor.getFormasDePago() == null)
			proveedor.setFormasDePago(new HashSet());

		List formaDePagoAnterires = new ArrayList();
		Iterator iter2 = tablaDeFormaDePago.iterator();
		while (iter2.hasNext()) {
			formaPagoWraper wraper = (formaPagoWraper) iter2.next();
			if (wraper.getFormaPago().getIdProvFormaPago() == null) {
				wraper.getFormaPago().setProveedor(proveedor);
				proveedor.getFormasDePago().add(wraper.getFormaPago());
			} else
				formaDePagoAnterires.add(wraper.getFormaPago());
		}
		Iterator iter = proveedor.getFormasDePago().iterator();
		while (iter.hasNext()) {
			ProveedorFormaPago fp = (ProveedorFormaPago) iter.next();
			if (fp.getIdProvFormaPago() != null) {
				// Ahora verifico si algun obleto fue borrado o estaba borrado de antes
				if (!formaDePagoAnterires.contains(fp))
					fp.setEsActivo("N");
			}
		}

		// ProveedorUtil.grabarFormasDePagos(proveedoresService.getProveedorFormaPagoService(), tablaDeFormaDePago, proveedor, temporalFP);

		log.info("Generación terminada!!!");
		return proveedor;
	}


	/*
	 * Este metodo se utiliza para poder eliminar un telefono de la lista de telefonos
	 */
	public String eliminarTelefono() {
		log.info("Ejecutando ==> eliminarTelefono()");
		Long idTelefono = new Long(Session.getRequestParameter("idTelefono"));
		// if(listaTelefonos.size() > 1){
		listaTelefonos = ProveedorUtil.eliminarTelefonos(listaTelefonos, idTelefono);
		// ProveedorUtil.eliminarTelefonoSet(proveedor.getTelefonos(), idTelefono);
		// }
		// else{
		// error.agregar(Error.PROVEEDOR_TELEFONO_AL_MENOS_UNO);
		// }
		return null;
	}


	/*
	 * Este metodo se utiliza para editar el telefono.
	 */
	public String editarTelefono() {
		log.info("Ejecutando ==> editarTelefono()");

		Long idTelefono = new Long(Session.getRequestParameter("idTelEdi"));
		log.info("id a buscar: " + idTelefono);

		TelefonoBean bean = (TelefonoBean) Session.getBean("TelefonoBean");
		Telefono telefono = ProveedorUtil.buscarTelefono(listaTelefonos, idTelefono);

		bean.inicializar(TelefonoBean.PROVEEDOR, telefono);

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");

		return null;
	}


	/*
	 * * Este metodo elimina, agrega y edita los telefonos del set de individuos, a partir de la listTelefonos
	 */
	public void grabarTelefonos() {
		if (proveedor.getTelefonos() == null)
			proveedor.setTelefonos(new HashSet());

		Iterator iterTelefonos = listaTelefonos.iterator();
		while (iterTelefonos.hasNext()) {
			ProveedorTelefono telef = (ProveedorTelefono) iterTelefonos.next();
			if (telef.getTelefono().getIdTelefono().equals(new Long(telef.getTelefono().hashCode()))) {
				telef.setIdTelefono(null);
				telef.setProveedor(proveedor);
				telef.getTelefono().setIdTelefono(null);
				telef.getTelefono().setFechaAlta(new Date());
				telef.getTelefono().setOperador(Session.getOperador());
				proveedor.getTelefonos().add(telef);
			}
		}
	}


	public String filtrarProveedores() {
		Filtro filtro = new Filtro();
		filtro.agregarJoin("sucursalFiel");
		filtro.agregarJoin("grupo");
		if (idProveedor != null && !idProveedor.equals(new Long(0))) {
			filtro.agregarCampoOperValor("idProveedor", Filtro.IGUAL, idProveedor);
		}
		if (!Validador.esNuloVacio(cuit)) {
			filtro.agregarCampoOperValor("cuit", Filtro.LIKE, cuit);
		}
		if (!Validador.esNuloVacio(razonSocial)) {
			filtro.agregarCampoOperValor("razonSocial", Filtro.LIKE, razonSocial);
		}
		log.info("Filtro listado Proveedores -> " + filtro.getHQL());

		proveedores = new ArrayList();

		try {
			proveedores = service.getProveedorService().getProveedores(filtro);
		} catch (ProveedorException e) {
			e.printStackTrace();
		}

		Session.redirect("/tarjetafiel/proveedores/listarProveedores.jsf");
		return null;
	}


	/**
	 * Verifica que el cuit sea valido.
	 * 
	 * @param event
	 */
	public void validarCuit(ActionEvent event) {
		if (cuit != null) {
			try {
				CuitValido cuitValido = new CuitValido(this.cuit);
				cuitDni = cuitValido.getDni();
				cuitIdentificador = cuitValido.getIdentificador();
				cuitVerificador = cuitValido.getVerificador();

				Long cuitLong = new Long(this.cuit);

				verificarCuitDuplicado(cuitLong);

				Individuo individuo = ProveedorUtil.getIndividuo(impuestoService.getIndividuoDao(), cuitLong);
				tablaDeImpuestos = ProveedorUtil.generarListaImpuestos(impuestoService.getTipoImpuestoDao(),
						impuestoService.getJurisdiccionActividadDao());

				if (individuo != null) {
					razonSocial = individuo.getDenominacion();
					empleador = individuo.getEmpleador();
					integranteSoc = individuo.getIntegranteSoc();
					tablaDeImpuestos = ProveedorUtil.marcarListaDesdeImpuestos(impuestoService.getIndividuoDao(), cuitLong, tablaDeImpuestos);
				}

				cuitInvalido = new String();
				validado = true;
			} catch (CuitDuplicadoException e1) {
				cuitInvalido = "El número de CUIT ya existe.";
			} catch (CuitNoValidoException e2) {
				cuitInvalido = "El número de CUIT es invalido.";
				razonSocial = null;
			} catch (Exception e3) {
				cuitInvalido = "Error al convertir.";
				razonSocial = null;
			}
		}
	}


	private boolean setProveedorEditar(Proveedor proveedor) {

		log.info("UNO!!!!!");

		try {
			CuitValido cuitValido = new CuitValido(proveedor.getCuit().toString());
			cuitDni = cuitValido.getDni();
			cuitIdentificador = cuitValido.getIdentificador();
			cuitVerificador = cuitValido.getVerificador();
			digitoVerificadorCuit = new Integer(cuitValido.getVerificador());
		} catch (CuitNoValidoException e1) {
			e1.printStackTrace();
			error.agregar(Error.PROVEEDOR_ROTO);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			error.agregar(Error.PROVEEDOR_ROTO);
			return false;
		}

		idProveedor = proveedor.getIdProveedor();
		razonSocial = proveedor.getRazonSocial();
		nombreFantasia = proveedor.getNombreFantasia();
		alias = proveedor.getAlias();
		cuit = proveedor.getCuit().toString();

		log.info("DOS!!!!!");

		// Setear las posibles categorias para los impuestos.
		tablaDeImpuestos = ProveedorUtil.generarListaImpuestos(impuestoService.getTipoImpuestoDao(), impuestoService.getJurisdiccionActividadDao());
		tablaDeImpuestos = ProveedorUtil.marcarListaDesdeProveedores(proveedor.getProvedorCategoria(), tablaDeImpuestos);

		log.info("TRES!!!!!");

		// Setea las relaciones con otros proveedores.
		if (!proveedor.getRelaciones().isEmpty()) {
			tablaDeRelacionConProveedores = ProveedorUtil.leerRelacionConProveedores(proveedor.getRelaciones(),
					service.getProveedorService());
		}

		// Seteamos el numero de inscripcion DGR.
		inscripcionDgr = proveedor.getInscripcionDgr();

		log.info("CUATRO!!!!!");

		// Setea la relaciones con otros clientes.
		if (!proveedor.getClieRelaciones().isEmpty()) {
			tablaDeRelacionConClientes = ProveedorUtil.leerRelacionConClientes(proveedor.getClieRelaciones(), serviceGeneral.getClienteDao());
		}

		log.info("CINCO!!!!!");

		// setea los domicilios para el proveedor.
		if (!proveedor.getDomicilios().isEmpty()) {
			tablaDeDomicilios = Convertidores.setToList(proveedor.getDomicilios());
			Iterator iter = tablaDeDomicilios.iterator();
			while (iter.hasNext()) {
				ProveedorDomicilio dom = (ProveedorDomicilio) iter.next();
				log.info("ID=" + dom.getIdProvDomicilio());
				log.info("DOMICILIO=" + dom.getDomicilio());
				log.info("BARRIO=" + dom.getDomicilio().getBarrio().getDescripcion());
				log.info("PAIS=" + dom.getDomicilio().getLocalidad().getProvincia().getPais().getNombre());
				// dom.getDomicilio().getTipoVivienda().getDescripcion();
			}
		}

		jurisdiccionSeleccionada = proveedor.getJurisdiccion().getIdJurisdiccion();
		grupoSeleccionado = proveedor.getGrupo().getIdGrupo();
		sucursalSeleccionda = proveedor.getSucursalFiel().getIdSucursal();
		empleador = proveedor.getEmpleador();
		limiteCredito = proveedor.getLimiteCredito();

		log.info("SEIS!!!!!");

		// Seteando la modalidad de pago del proveedor.
		ModalidadPago modalidad = proveedor.getModalidadPago();
		fechaFactura = new Boolean(modalidad.getEsDiaSemana().equals("S") ? true : false);
		diaCC = new Boolean(modalidad.getEsDiaCalendario().equals("S") ? true : false);
		if (fechaFactura.booleanValue()) {
			nombreDia = modalidad.getNombreDia();
			diaDeLaSemana = modalidad.getNroDia();
		} else {
			diaDelMes = modalidad.getNroDia();
		}

		// Seteando la fecha factura para el proveedor.
		fechaF = new Boolean(proveedor.getFechaFactura().equals(new Character('S')) ? true : false);
		cuentaC = new Boolean(!fechaF.booleanValue());

		log.info("SIETE!!!!");

		// Setear la tablas desde el proveedor leido.
		tablaDeEmails = Convertidores.setToList(proveedor.getEmails());
		// if(tablaDeEmails.isEmpty()) {
		// tablaDeEmails.add(new ProveedorEmail(null,new Email(new Long(1),null,null)));
		// }

		// VER ACA EL FUNCIONAMIENTO DE ESTO PARA CUANDO SE VA A EDITAR UN PROVEEDOR.
		// tablaDeTelefonos = Convertidores.setToList(proveedor.getTelefonos());
		// if(tablaDeTelefonos.isEmpty()) {
		// tablaDeTelefonos.add(new ProveedorTelefono(new Telefono(new Long(1),null,null,null,null,null,new TipoTelefono()),null));
		// }else {
		// Iterator iter = tablaDeTelefonos.iterator();
		// while(iter.hasNext()) {
		// ProveedorTelefono tel = (ProveedorTelefono)iter.next();
		// log.info("ID="+tel.getIdTelefono());
		// log.info("TELEFONO="+tel.getTelefono());
		// log.info("es celular: " + tel.getTelefono().getEsCelular());
		//
		// }
		// }

		List aux = Convertidores.setToList(proveedor.getTelefonos());
		if (!aux.isEmpty()) {
			Iterator iterator = aux.iterator();
			while (iterator.hasNext()) {
				ProveedorTelefono element = (ProveedorTelefono) iterator.next();
				element.getTelefono().getTipo();
				listaTelefonos.add(element);
			}
		}

		log.info("OCHO!!!!!");

		if (!proveedor.getTiposVtos().isEmpty()) {
			tablaDeComposicionDePago = Convertidores.setToList(proveedor.getTiposVtos());
		}

		if (!proveedor.getFormasDePago().isEmpty()) {
			Iterator iter = proveedor.getFormasDePago().iterator();
			while (iter.hasNext()) {
				ProveedorFormaPago formaPago = (ProveedorFormaPago) iter.next();
				if (formaPago.getEsActivo().equals("S")) {
					tablaDeFormaDePago.add(new formaPagoWraper(formaPago));
				}
			}
			// tablaDeFormaDePago = ProveedorUtil.getFormasDePagoActivas(proveedor.getFormasDePago());
		}

		log.info("NUEVE!!!!!");

		// Tomar el proveedor leido desde la base de datos como valido.
		validado = true;

		return true;
	}


	private void verificarCuitDuplicado(Long cuit) throws CuitDuplicadoException {
		Filtro filtro = new Filtro("cuit", Filtro.IGUAL, cuit);

		List proveedores = new ArrayList();
		try {
			proveedores = service.getProveedorService().getProveedores(filtro);
		} catch (ProveedorException e) {
			e.printStackTrace();
		}

		if (proveedores.size() != 0) {
			throw new CuitDuplicadoException("El número de CUIT ya existe.");
		}
	}


	public void agregarFormaDePago(ProveedorFormaPago unaFormaDePago) {
		tablaDeFormaDePago.add(new formaPagoWraper(unaFormaDePago));
	}


	public String volverProveedores() {
		borrar();
		return "altaProveedores";
	}


	/*
	 * OPCIONES DEL LISTADO DE PROVEEDORES (EDITAR PROVEEDOR - ELIMINAR PROVEEDOR)
	 */

	public String editarProveedor() {
		String cuitAux = cuit;
		String razonSocialAux = razonSocial;
		List proveedoresAux = new ArrayList(proveedores);
		borrar();
		log.info("Entrando a editar proveedor");
		String result = "";
		try {
			proveedor = service.getProveedorService().leerProveedor(new Long(idProveedorHidden.longValue()));
			if (Session.getBean("ScrollBean") != null) {
				ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
				bean.setHiddenScrollY(new Integer(0));
			}
			if (setProveedorEditar(proveedor)) {
				BuscarProveedorBean bean = (BuscarProveedorBean) Session.getBean("BuscarProveedorBean");
				bean.borrar(); // Borrar el bean de busca de proveedor para poder hacer el filtro mas adelante.
				tituloLargo = "Tarjeta Fiel - Modificación de proveedores";
				tituloCorto = "Modificación de proveedores";
				result = "altaProveedores";
				alta = false;
			} else {
				List errores = new ArrayList(error.getErrores());
				borrar();
				error.setErrores(errores);
				cuit = cuitAux;
				razonSocial = razonSocialAux;
				proveedores = proveedoresAux;
				Session.redirect("/tarjetafiel/proveedores/listarProveedores.jsf");
			}
		} catch (ProveedorException e) {
			e.printStackTrace();
		}

		return result;
	}


	public String desactivarProveedor() {
		Long idProveedor = new Long(idProveedorHidden.longValue());
		try {
			List comprobantes = service.getComprobanteService().getComprobantes(
					new Filtro("proveedor.idProveedor", Filtro.IGUAL, idProveedor));
			if (comprobantes.isEmpty()) {
				proveedor = service.getProveedorService().leerProveedor(idProveedor);
				proveedor.setActivo(new Character('N'));
				service.getProveedorService().actualizarProveedor(proveedor);
				filtrarProveedores();
			} else {
				error.agregar("El proveedor tiene comprobantes relacionados, no se puede desactivar.");
			}
		} catch (ProveedorException e) {
			e.printStackTrace();
		} catch (ComprobanteException e) {
			e.printStackTrace();
		}

		return null;
	}


	public void grabarProveedorListener(ActionEvent event) {
		grabarProveedor();
	}


	public String irANuevoProveedor() {
		log.info("irANuevoProveedor()");

		return inicializar();
	}


	public String irAModificarProveedor() {
		log.info("irAModificarProveedor()");
		if (verModificar) {
			idProveedorHidden = new Integer(proveedor.getIdProveedor().intValue());
			editarProveedor();
		} else {
			irAListarProveedor();
		}
		// alta = false;
		// popup.borrar();
		// if(Session.getBean("ScrollBean")!=null) {
		// ((ScrollBean)Session.getBean("ScrollBean")).setHiddenScrollY(new Integer(0));
		// }
		return null;
	}


	public String irAListarProveedor() {
		log.info("irAListarProveedor()");
		popup.borrar();
		return listarProveedores();
	}


	public String listarProveedores() {
		borrar();
		tituloCorto = "Listado de Proveedores";
		Session.redirect("/tarjetafiel/proveedores/listarProveedores.jsf");
		return null;
	}


	public boolean validar() {
		error.borrar();

		if (Validador.esNulo(razonSocial) || razonSocial.equals("")) {
			error.agregar(Error.PROVEEDOR_RAZONSOCIAL_REQUERIDO);
		}

		if (Validador.esNulo(nombreFantasia) || nombreFantasia.equals("")) {
			error.agregar(Error.PROVEEDOR_NOMBREFANTASIA_REQUERIDO);
		}

		if (grupoSeleccionado.equals(new Long(0))) {
			error.agregar(Error.PROVEEDOR_GRUPO_REQUERIDO);
		}

		// if(Validador.esNulo(limiteCredito)) {
		// error.agregar(Error.PROVEEDOR_LIMITECREDITO_REQUERIDO);
		// }

		// if(Validador.emailRequerido(tablaDeEmails)) {
		// error.agregar(Error.PROVEEDOR_EMAIL_REQUERIDO);
		// } else

		if (Validador.emailInvalido(tablaDeEmails)) {
			error.agregar(Error.PROVEEDOR_EMAIL_INCORRECTO);
		}

		if (Validador.esVacio(tablaDeDomicilios)) {
			error.agregar(Error.PROVEEDOR_DOMICILIO_REQUERIDO);
		}

		// if(Validador.telefonoRequerido(tablaDeTelefonos)) {
		// error.agregar(Error.PROVEEDOR_TELEFONO_REQUERIDO);
		// }
		if (Validador.telefonoRequerido(listaTelefonos)) {
			error.agregar(Error.PROVEEDOR_TELEFONO_REQUERIDO);
		}

		if (!tablaDeImpuestos.isEmpty()) {
			Iterator iter = tablaDeImpuestos.iterator();
			while (iter.hasNext()) {
				ImpuestoEditable element = (ImpuestoEditable) iter.next();
				if (!element.getCategoriaSeleccionada().equals(new Long(0))) {
					if (element.getJurisSeleccionada().equals(new Long(0))) {
						error.agregar(Error.PROVEEDOR_JURIS_IMPU_REQUERIDA + element.getTipoImpuesto().getDescripcion() + " (ver solapa Retenciones)");
					} else {
						if (element.getJurisActividadSeleccionada().equals(new Long(0))) {
							error.agregar(Error.PROVEEDOR_ACTIVIDAD_IMPU_REQUERIDA + element.getTipoImpuesto().getDescripcion()
									+ " (ver solapa Retenciones)");
						}
					}
				}
			}
		}

		if (Validador.esNulo(jurisdiccionSeleccionada) || jurisdiccionSeleccionada.equals(new Long(0))) {
			error.agregar(Error.PROVEEDOR_JURISDICCION_REQUERIDA);
		} else {
			if (!Validador.esNuloVacio(inscripcionDgr)) {
				// try {
				// Jurisdiccion juri = impuestoService.getJurisdiccionDao().buscarJurisdiccion(jurisdiccionSeleccionada);
				// DgrValido dgr = new DgrValido(inscripcionDgr, juri.getIdJurisdiccion().intValue());
				// } catch (DgrNoValidoException e) {
				// error.agregar(Error.PROVEEDOR_NUMERO_DGR_INVALIDO);
				// }
			} else {
				error.agregar(Error.PROVEEDOR_NUMERO_DGR_REQUERIDO);
			}
		}

		if (Validador.esNulo(tablaDeFormaDePago) || tablaDeFormaDePago.size() == 0) {
			error.agregar(Error.PROVEEDOR_FORMAPAGO_REQUERIDO);
		}

		if (!diaCC.booleanValue() && !fechaFactura.booleanValue()) {
			error.agregar(Error.PROVEEDOR_EMISIONPAGO_REQUERIDO);
		}

		if (diaCC.booleanValue() && fechaFactura.booleanValue()) {
			error.agregar(Error.PROVEEDOR_DIAEMISIONPAGO_UNICO);
		}

		if (!fechaF.booleanValue() && !cuentaC.booleanValue()) {
			error.agregar(Error.PROVEEDOR_COMPPAGO_REQUERIDO);
		}

		if (fechaF.booleanValue()) {
			if (tablaDeComposicionDePago.size() > 1) {
				error.agregar(Error.PROVEEDOR_COMPPAGO_TAMFF);
			}
		}

		boolean[] result = Validador.composicionPagoValidarCero(tablaDeComposicionDePago);

		if (result[0]) {
			error.agregar(Error.PROVEEDOR_COMPPAGO_DIAS_CERO);
		} else if (result[1]) {
			error.agregar(Error.PROVEEDOR_COMPPAGO_PORCENTAJE_CERO);
		} else if (Validador.composicionPagoValidar(tablaDeComposicionDePago)) {
			error.agregar(Error.PROVEEDOR_COMPPAGO_FALLAPORCENTAJES);
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public void mostrarPopup(ActionEvent event) {
		popup.setPopup(popup.ICONO_OK, "El proveedor ha sido almacenado exitosamente.");
	}


	public String agregarTelefono() {
		log.info("Ejecutando ==> agragrTelefono()");

		TelefonoBean bean = (TelefonoBean) Session.getBean("TelefonoBean");

		bean.inicializar(TelefonoBean.PROVEEDOR, new Telefono());

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");

		return null;
	}

	// /*
	// * Este metodo se utiliza para editar el telefono.
	// */
	// public String editarTelefono() {
	// log.info("Ejecutando ==> editarTelefono()");
	//
	// Long idTelefono = new Long(Session.getRequestParameter("idTelEdi"));
	// log.info("id a buscar: " + idTelefono);
	//
	// TelefonoBean bean = (TelefonoBean)Session.getBean("TelefonoBean");
	// Telefono telefono = ProveedorUtil.buscarTelefono(listaTelefonos, idTelefono);
	//
	// bean.inicializar(TelefonoBean.PROVEEDOR, telefono);
	//
	// String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	// path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
	// ejecutarJavaScript("popup('"+ path +"',650,250), 'titlebar=no';");
	//
	// return null;
	// }

	public class formaPagoWraper {
		private ProveedorFormaPago formaPago;
		private int indice;
		private int contador = 1;


		public formaPagoWraper(ProveedorFormaPago formaPago) {
			indice = contador;
			this.formaPago = formaPago;
			contador++;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public ProveedorFormaPago getFormaPago() {
			return formaPago;
		}


		public void setFormaPago(ProveedorFormaPago formaPago) {
			this.formaPago = formaPago;
		}


		public String eliminar() {
			tablaDeFormaDePago.remove(this);
			// Long idProvFormaPago = new Long(Session.getRequestParameter("idProvFormaPago"));
			// Object[] result = ProveedorUtil.eliminarFP(tablaDeFormaDePago, idProvFormaPago);
			// tablaDeFormaDePago = (List)result[0];
			// temporalFP.add(result[1]);

			return null;
		}


		public boolean equals(Object unObjeto) {
			if (unObjeto instanceof formaPagoWraper) {
				formaPagoWraper aux = (formaPagoWraper) unObjeto;
				if (indice == aux.getIndice()) {
					return true;
				}
			}
			return false;
		}

	}

}
