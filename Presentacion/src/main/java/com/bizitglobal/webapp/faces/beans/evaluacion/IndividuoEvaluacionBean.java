package com.bizitglobal.webapp.faces.beans.evaluacion;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.calendar.HtmlInputCalendar;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Archivo;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ActividadEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.DiaPagoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InformeLaboralException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InformeParticularException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TclienteException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TipoIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.VerificadorException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ActividadEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Bancos;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ConfirmacionTelefonica;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.DiaPago;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Digital;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Educacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Emails;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Estados;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoDomicilio;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoVehiculo;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InformeLaboral;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InformeParticular;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.NroVerificador;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Promotor;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicLog;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudIndividuo;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tarjeta;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tcliente;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Telefonos;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.TipoIndividuo;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Verificador;
import com.bizitglobal.tarjetafiel.general.exception.BancoException;
import com.bizitglobal.tarjetafiel.general.exception.EmpresaException;
import com.bizitglobal.tarjetafiel.general.exception.OcupacionException;
import com.bizitglobal.tarjetafiel.general.exception.SucTelefonoException;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Email;
import com.bizitglobal.tarjetafiel.general.negocio.Empresa;
import com.bizitglobal.tarjetafiel.general.negocio.EstadoCivil;
import com.bizitglobal.tarjetafiel.general.negocio.Ocupacion;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistemaDetalle;
import com.bizitglobal.tarjetafiel.general.negocio.Profesion;
import com.bizitglobal.tarjetafiel.general.negocio.Rubro;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmail;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresasXDomicilio;
import com.bizitglobal.tarjetafiel.general.negocio.SucTelefono;
import com.bizitglobal.tarjetafiel.general.negocio.TamEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDigital;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDocumento;
import com.bizitglobal.tarjetafiel.general.negocio.Vehiculo;
import com.bizitglobal.tarjetafiel.general.negocio.Vinculo;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuitNoValidoException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuitValido;
import com.bizitglobal.tarjetafiel.transacciones.exception.CliIndividuoException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CliIndividuo;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.ProcesarCobroExterno;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.Util.DavidCopperfield;
import com.bizitglobal.webapp.faces.beans.evaluacion.Util.IndividuoEvaluacionUtil;
import com.bizitglobal.webapp.faces.beans.evaluacion.wrappers.SolicitudesIndividuosWrapper;
import com.bizitglobal.webapp.faces.beans.evaluacion.wrappers.WrapperFile;
import com.bizitglobal.webapp.faces.beans.evaluacion.wrappers.WrappersTcliente;
import com.bizitglobal.webapp.faces.beans.general.DomicilioBean;
import com.bizitglobal.webapp.faces.beans.general.EmpresaBean;
import com.bizitglobal.webapp.faces.beans.general.TelefonoBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Validador;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class IndividuoEvaluacionBean extends BaseBean {
	private static final Logger log = Logger.getLogger(IndividuoEvaluacionBean.class);

	/* Se utiliza para ver como se inicializaran los componentes */

	private int origen;
	// utilizados por el filtro para encontrar individuos.
	private String idIndividuo;
	private String apellido;
	private List listaIndividuos;
	private String idIndividuoHidden = "";
	private boolean mostrarSolicitudes;
	private boolean habilitada;
	private boolean yaExiste;
	private boolean refrescarAct;
	private List listDeSolicitudesPorIndividuo;
	private boolean verCargaIndividuo = false;
	private String cargaIndi = "Ingreso de cuit";
	private String apel = "";
	private String nom = "";
	private String cuilBuscador = "";
	private boolean habilitarCarga = false;

	public static final int TITULAR = 1;
	public static final int GARANTE = 2;
	public static final int ADICIONAL = 3;
	private int vista;

	// binding para objetos jsf
	private HtmlSelectOneMenu diasPagos;
	private HtmlSelectOneMenu bancoSeleccionado;

	// Boolean para ocultar educacion y profesion en el caso de adicional
	private boolean mostrarEducacion;

	// Referente a las observaciones de la solicitud
	private String observacion = "";
	private String confTelLaboral = "";
	private String confTelParticular = "";

	/*
	 * Dependiendo de que parametro se le pase al inicializar se utilizan estos boolean par poder mostrar los paneles
	 */
	private boolean panelA;
	private boolean panelB;
	private boolean panelC;
	private boolean panelD;
	private boolean panelE;
	private boolean panelF;
	private boolean panelG;
	private boolean panelH;
	private boolean panelI;
	private boolean panelJ;
	private boolean panelK;
	private boolean botonDomicilio;
	private boolean botonTelefono;
	private boolean botonEmpresa;
	private boolean botonAdjuntar;
	private Solicitud solicitud;
	private IndividuoEvaluacion individuoEvaluacion;
	private Promotor promotor;
	private IndividuoDomicilio individuoDomicilio;
	private IndividuoDomicilio titularDomicilio;
	private IndividuoDomicilio garanteDomicilio;
	private IndividuoDomicilio adicionalDomicilio;
	private Estados estados;
	private Bancos bancos;
	private CuitValido cuitValido;
	private Empresa empresa;
	private SucEmpresa sucEmpresa;
	private IndividuoVehiculo individuoVehiculo;
	private IndividuoVehiculo titularVehiculo;
	private IndividuoVehiculo garanteVehiculo;
	private IndividuoVehiculo adicionalVehiculo;
	private ConfirmacionTelefonica confirmacionTelefonica;
	private Tarjeta tarjeta;
	private Digital docDigital;
	private Domicilio domSucursal;
	private SucTelefono sucTelefono;
	private SolicitudIndividuo solicitudIndividuo;
	private Emails emails;
	private Operador operador;
	private Operador operadorTitular;
	private Operador operadorGarante;
	private Operador operadorAdicional;
	private TipoIndividuo tipoIndividuo;
	private DiaPago diaPago;
	private Vehiculo vehiculo;
	private Verificador verificador;
	private DavidCopperfield magia;
	private InformeParticular informeParticular;
	private UploadedFile uploadedFile;

	/* define un filtro general */
	private Filtro filtro;

	/* Titulo para Los diferentes paneles desplegables */
	private String tituloPanleUno = "Datos Solicitud";
	private String tituloPanleDos = "Datos Personales";
	private String tituloPanleTres = "Datos Domicilio";
	private String tituloPanleCuatro = "Gestionar Teléfonos";
	private String tituloPanleCinco = "Información Familiar y Educación";
	private String tituloPanleSeis = "Datos para la Facturación";
	private String tituloPanleSiete = "Datos de su Actividad";
	private String tituloPanleOcho = "Datos Propiedades";
	private String tituloPanleNueve = "Datos Financieros";
	private String tituloPanleDiez = "Observaciones";
	private String tituloPanleOnce = "Documentos Digitales";
	private String tituloPanleDoce = "Alta Garante y Adicional";
	private String tituloPanleTrece = "Generación y Asignación de Informes";

	/*
	 * Listas de objetos que contendran diferentes tipos de objetos segun su nombre
	 */
	private List listTipoDni;
	private List listTipoSexo;
	private List listDomicilio;
	private List listDomicilioTitular;
	private List listDomicilioGarante;
	private List listDomicilioAdicionalUno;
	private List listTelefono;
	private List listTelefonoTitular;
	private List listTelefonoGarante;
	private List listTelefonoAdicional;
	private List listEstadoCivil;
	private List listAuxEstadoCivil;
	private List listEducacion;
	private List listAuxEducacion;
	private List listProfesion;
	private List listAuxProfesion;
	private List listVinculo;
	private List listAuxVinculo;
	private List listDiaPago;
	private List listAuxDiaPago;
	private List listBancos;
	private List listAuxBancos;
	private List listTipoCtas;
	private List listAuxTipoCtas;
	private List listOcupacion;
	private List listAuxOcupacion;
	private List listSucursales;
	private List listSucEmp;
	private List listDomicilioInmueble;
	private List listTarjetas;
	private List listTipoDocumentos;
	private List listTipoDigital;
	private List listAuxTipoDigital;
	private List listGarante = new ArrayList();
	private List listAdicional = new ArrayList();
	private List listDomicilioPago;
	private List listDomicilioPagoTitular;
	private List listDomicilioPagoGarante;
	private List listDomicilioPagoAdicionalUno;
	private List listDomicilioPagoAdicionalDos;
	private List listDomicilioPagoAdicionalTres;
	private List listAuxDomicilioPago;
	private List tCliente;
	private List listaTcliente;

	/* indices que se utilizaran para capturar los id de las listas desplegables */
	private Long idTipoSexoSeleccionado;
	private Long idTipoTelSeleccionado;
	private Long idDomicilioPagoSeleccionado;
	private Long idTipoCuentaSeleccionado;
	private Long idOcupacionSeleccionado;
	private Long idSucursalSeleccionado;
	private Long idTarjetaSeleccionado;
	private Long idBancoSeleccionado;
	private Long idTipoDocSeleccionado;
	private Long idBcoSeleccionado;
	private Long idEducSeleccionado;
	private Long idDiaPagoSeleccionado;
	private Long idTipoDocumentoSeleccionado;
	// private Long idEstadoCivilSeleccionado;
	private Long idProfesionSeleccionado;
	private Long idVinvuloSeleccionado;
	private Set domicilios;

	/*
	 * Se utiliza para filrar el dia de pago dependiendo del partido al en el q vive
	 */
	private Long idPartidoSeleccionado;

	/* se utiliza para poder buscar la empresa en la cual trabaja */
	private String nroCuit;

	/* nro del comprobante */
	private String nroSolicitud;

	private String nota_cred;

	/* nro de la cuenta para realizar el debito */
	private String nroCuenta;

	/* nro de cbu */
	private String nroCBU;

	/* Apellido y Nombre promotor */
	private String nombrePromotor;

	/* Se utiliza para capturar el nombre del banco */
	private String nombreBanco;

	/* Se utiliza para armar la direccion de la sucursal */
	private String direccionSucursal;

	/* Se utiliza para el nro de teledono de la sucursal */
	private String telefono;

	/* Se utiliza para el cuil del individuo */
	private String cuil;

	/* Se utiliza para calcular al antiguedad del individuo */
	private String antiguedad;

	/* Habilita el panel completo */
	private boolean opcionA;

	/* Se utiliza para el CBU */
	private boolean esCBU;

	/* Se utiliza para habilitar la lista de Profeciones */
	private boolean habilitarProfesion;

	/* Se utiliza para habilitar la lista de Sucursales */
	private boolean habilitarSucursal;
	private boolean habilitarNroSolicitud;
	private HtmlSelectOneMenu sucursalHtml = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu ocupacionHtml = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu educacionHtml = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu profesionHtml = new HtmlSelectOneMenu();
	private HtmlOutputText textBanco = new HtmlOutputText();
	private HtmlInputCalendar fechaIngreso = new HtmlInputCalendar();

	/* se utilizan para ver si se emite alfuno de estas verificaciones */
	private Long idInformeParticular;
	private Long idInformeLaboral;
	private boolean particular;
	private boolean laboral;

	/* Se utiliza para la fecha de nacimeinto del individuo */
	private Date fechaNacimiento;

	/* Se utiliza para la fecha de ingreso laboral */
	private Date ingreso;

	/* Se utiliza para el foco dentro de la pagina */
	private String focoHidden;

	/* Se utiliza para saber si es alta o modificacion. */
	private boolean alta;

	/* Se utiliza para saber si tiene vehiculo o no */
	private boolean vehiculoPropio;

	/* Se utiliza para llevar la cuanta de cuantos adiconales se han cargado */
	private static int cantAdicionales;

	/* Se utiliza para despues poder validar el mail */
	private String email;

	/*
	 * Se utiliza con la vista de verificador la cual tendra todos los verificadores que trabajan dentro de la zona de donde vive el individuo o el
	 * garante.
	 */
	private List listaVerificadores;

	private List lstVerificadores;

	private static Logger logger = Logger.getLogger(ProcesarCobroExterno.class);


	public IndividuoEvaluacionBean() {
		super();
	}


	public void limpiarCabecera() {
		nroSolicitud = "";
		nombrePromotor = "";
		solicitud = new Solicitud();
	}


	/*
	 * este metodo se utiliza para limpiar todos los atributos de la clase.
	 * 
	 * @see com.bizitglobal.webapp.faces.beans.BaseBean#borrar()
	 */
	public void borrar() {
		log.info("Ejecutando ==> borrar()");
		borrarBaseBean();
		// error.borrar();
		mostrarSolicitudes = false;
		refrescarAct = true;
		habilitarCarga = false;
		cuilBuscador = "";
		apel = "";
		nom = "";
		listDeSolicitudesPorIndividuo = new ArrayList();
		idTipoSexoSeleccionado = new Long(0);
		idTipoTelSeleccionado = new Long(0);
		idDomicilioPagoSeleccionado = new Long(0);
		idTipoCuentaSeleccionado = new Long(0);
		idOcupacionSeleccionado = new Long(0);
		idSucursalSeleccionado = new Long(0);
		idTarjetaSeleccionado = new Long(0);
		idBancoSeleccionado = new Long(0);
		idBcoSeleccionado = new Long(0);
		idPartidoSeleccionado = new Long(0);
		idDiaPagoSeleccionado = new Long(0);
		idTipoDocSeleccionado = new Long(0);
		// idEstadoCivilSeleccionado = new Long(0);
		idProfesionSeleccionado = new Long(0);
		idVinvuloSeleccionado = new Long(0);
		idEducSeleccionado = new Long(0);

		nroCuit = "";
		nroCuenta = "";
		nroCBU = "";
		nombreBanco = "";
		direccionSucursal = "";
		telefono = "";
		antiguedad = "";
		cuil = "";

		listTipoDni = new ArrayList();
		listTipoSexo = new ArrayList();
		listDomicilio = new ArrayList();
		listTelefono = new ArrayList();
		listEstadoCivil = new ArrayList();
		listAuxEstadoCivil = new ArrayList();
		listEducacion = new ArrayList();
		listAuxEducacion = new ArrayList();
		listProfesion = new ArrayList();
		listAuxProfesion = new ArrayList();
		listVinculo = new ArrayList();
		listAuxVinculo = new ArrayList();
		listDiaPago = new ArrayList();
		listBancos = new ArrayList();
		listTipoCtas = new ArrayList();
		listAuxTipoCtas = new ArrayList();
		listOcupacion = new ArrayList();
		listAuxOcupacion = new ArrayList();
		listSucursales = new ArrayList();
		listSucEmp = new ArrayList();
		listDomicilioInmueble = new ArrayList();
		listTarjetas = new ArrayList();
		listTipoDocumentos = new ArrayList();
		listDomicilioPago = new ArrayList();
		listAuxTipoDigital = new ArrayList();
		listTipoDigital = new ArrayList();
		listAuxDiaPago = new ArrayList();
		listAuxDomicilioPago = new ArrayList();
		listDomicilioTitular = new ArrayList();
		listDomicilioPagoTitular = new ArrayList();
		listDomicilioPagoGarante = new ArrayList();
		listDomicilioPagoAdicionalUno = new ArrayList();
		listDomicilioPagoAdicionalDos = new ArrayList();
		listDomicilioPagoAdicionalTres = new ArrayList();
		listGarante = new ArrayList();
		listAdicional = new ArrayList();

		individuoDomicilio = null;
		titularDomicilio = null;
		garanteDomicilio = null;
		adicionalDomicilio = null;
		estados = new Estados();
		promotor = new Promotor();
		individuoEvaluacion = null;
		bancos = new Bancos();
		empresa = null;
		sucEmpresa = null;
		informeParticular = new InformeParticular();
		individuoVehiculo = null;
		titularVehiculo = null;
		garanteVehiculo = null;
		adicionalVehiculo = null;
		confirmacionTelefonica = new ConfirmacionTelefonica();
		tarjeta = new Tarjeta();
		docDigital = new Digital();
		domSucursal = null;
		sucTelefono = null;
		// solicitudIndividuo = new SolicitudIndividuo();
		emails = new Emails();
		magia = new DavidCopperfield();

		operador = null;
		operadorTitular = null;
		operadorGarante = null;
		operadorAdicional = null;
		tipoIndividuo = new TipoIndividuo();
		diaPago = null;
		vehiculo = new Vehiculo();
		opcionA = false;
		esCBU = false;
		habilitarProfesion = true;
		habilitarSucursal = true;
		diasPagos = new HtmlSelectOneMenu();
		bancoSeleccionado = new HtmlSelectOneMenu();
		ocupacionHtml = new HtmlSelectOneMenu();
		educacionHtml = new HtmlSelectOneMenu();
		profesionHtml = new HtmlSelectOneMenu();

		email = "";
		botonAdjuntar = false;
		botonDomicilio = false;
		botonEmpresa = false;
		botonTelefono = false;
		particular = false;
		laboral = false;
		idInformeParticular = new Long(0);
		idInformeLaboral = new Long(0);
		listaVerificadores = new ArrayList();
		lstVerificadores = new ArrayList();
		fechaNacimiento = null;
		textBanco.setValue("");
		ingreso = null;

		tCliente = new ArrayList();
		listaTcliente = new ArrayList();
		confTelLaboral = "";
		confTelParticular = "";
		observacion = "";
	}


	public void inicializarParametros(Map param) {
		error.borrar();
		Integer paraOrigen = null;
		try {
			this.nota_cred = (String) param.get("nota_cred");
			paraOrigen = new Integer(param.get("origen").toString());
			if (paraOrigen.intValue() > 3 || paraOrigen.intValue() < 1)
				error.agregar(Error.EVA_ORIGEN_INCORRECTO);
		} catch (ClassCastException e) {
			error.agregar(Error.EVA_ORIGEN_INCORRECTO);
		} catch (NumberFormatException e2) {
			error.agregar(Error.EVA_ORIGEN_INCORRECTO);
		} catch (Exception e2) {
			error.agregar(Error.EVA_ORIGEN_INCORRECTO);
		}
		String paraNroSol = null;
		try {
			paraNroSol = (String) param.get("nroSolicitud");
		} catch (ClassCastException e) {
			error.agregar(Error.EVA_NUMERO_SOLICITUD_INCORRECTO);
		} catch (NumberFormatException e2) {
			error.agregar(Error.EVA_NUMERO_SOLICITUD_INCORRECTO);
		} catch (Exception e2) {
			error.agregar(Error.EVA_NUMERO_SOLICITUD_INCORRECTO);
		}
		try {
			vista = new Integer(param.get("vista").toString()).intValue();
			if (vista < 1 || vista > 3)
				error.agregar(Error.EVA_NUMERO_DE_VISTA_INCORRECTO);
		} catch (ClassCastException e) {
			error.agregar(Error.EVA_NUMERO_DE_VISTA_INCORRECTO);
		} catch (NumberFormatException e2) {
			error.agregar(Error.EVA_NUMERO_DE_VISTA_INCORRECTO);
		} catch (Exception e2) {
			error.agregar(Error.EVA_NUMERO_DE_VISTA_INCORRECTO);
		}
		// log.info("Vista: " + vista);
		Long nroSolicIndi = null;
		try {
			String idAdi = (String) param.get("idAdi");
			if (idAdi != null) {
				nroSolicIndi = new Long(idAdi);
			}
		} catch (ClassCastException e) {
			error.agregar(Error.EVA_NUMERO_SOLICITUD_INDIVIDUO_INCORRECTO);
		} catch (NumberFormatException e2) {
			error.agregar(Error.EVA_NUMERO_SOLICITUD_INDIVIDUO_INCORRECTO);
		} catch (Exception e2) {
			error.agregar(Error.EVA_NUMERO_SOLICITUD_INDIVIDUO_INCORRECTO);
		}
		if (!error.hayErrores()) {
			cantAdicionales = 1;
			inicializar(paraOrigen.intValue(), paraNroSol, nroSolicIndi);
		}
		// nuevaSolicitud();

	}


	/*
	 * este metodo se llama para inicializar los componetes del bean
	 * 
	 * @see com.bizitglobal.webapp.faces.beans.BaseBean#inicializar()
	 */
	public String inicializar() {
		log.info("Entrando a amIndividuo");
		return null;
	}


	public boolean getQueVista() {
		if (vista == 2)
			return false;
		if (vista == 1)
			return false;
		return true;
	}


	/*
	 * Este metodo inicializa la pagina para que pueda mostrarse.
	 */
	public void nuevaSolicitud() {
		listAdicional = new ArrayList();
		listGarante = new ArrayList();
		listTelefonoTitular = new ArrayList();
		listTelefono = new ArrayList();
		log.info("Ejecutando ==> inicializar(origen)");
		borrar();
		if (origen == TITULAR || origen == GARANTE) {
			// limpiarCabecera();
			mostrarEducacion = true;
		} else {
			mostrarEducacion = false;
		}
		log.info("Inicializar - Origen: " + origen);
		log.info("Inicializar - This.Origen: " + this.origen);
		tituloLargo = "Tarjeta Fiel - Evaluación";
		cargarDatosMinimosNecesarios();
		cargarListas();
		solicitudIndividuo = new SolicitudIndividuo();
		solicitudIndividuo.setIdSolicitudIndividuo(null);
		solicitudIndividuo.setIndividuoEvaluacion(individuoEvaluacion);

		vista = 1;
		operador = new Operador();
		operador = Session.getOperador();
		focoHidden = "inApellido";
		opcionA = true;
	}


	public void cargarDatosMinimosNecesarios() {
		borrar();
		cuil = "";
		individuoEvaluacion = new IndividuoEvaluacion();
		individuoEvaluacion.setCuil("");
		individuoEvaluacion.setApellido("");
		individuoEvaluacion.setApellidoMaterno("");
		individuoEvaluacion.setNombres("");
		empresa = new Empresa();
		empresa.setRazonSocial("");
		Rubro rubrin = new Rubro();
		rubrin.setIdRubro(new Long(0));
		rubrin.setDescripcion("");
		empresa.setRubro(rubrin);
		individuoEvaluacion.setActividad(new ActividadEvaluacion());
		individuoEvaluacion.getActividad().setCargo("");
		individuoEvaluacion.getActividad().setSueldoNeto(null);
		individuoEvaluacion.getActividad().setEmpleoAnterior("");
		individuoEvaluacion.getActividad().setTelEmpleoAnt("");
		individuoEvaluacion.getActividad().setReferencias("");
		individuoEvaluacion.getActividad().setOtrosIngresosDesc("");
		individuoEvaluacion.getActividad().setOtrosIngresosMonto(null);
		individuoEvaluacion.getActividad().setOcupacion(new Ocupacion(new Long(0)));
		TipoDocumento tipoDoc = new TipoDocumento();
		tipoDoc.setIdTipoDocumento(new Long(0));
		// proceso agregado para el domicilio
		individuoEvaluacion.setDomicilio(new Domicilio());
		individuoEvaluacion.setTipoDocumento(tipoDoc);
		individuoEvaluacion.setNroDocumento("");
		individuoEvaluacion.setHijosCargo(new Long(0));
		individuoEvaluacion.setEstadoCivil(new EstadoCivil(new Long(0)));
		individuoEvaluacion.setEducacion(new Educacion(new Long(0)));
		individuoEvaluacion.setProfesion(new Profesion(new Long(0)));
		individuoEvaluacion.setVinculo(new Vinculo(new Long(0)));
		bancos.setIndividuo(individuoEvaluacion);
		bancos.setVinculacion("");
		tarjeta.setIdTarjeta(null);
		tarjeta.setIndividuoEvaluacion(individuoEvaluacion);
		individuoVehiculo = new IndividuoVehiculo(null, individuoEvaluacion, new Vehiculo());
		docDigital.setDescripcion("");

	}


	/*
	 * Este metodo se utiliza para inicializar el bean teniendo el cuenta el origen de quien lo llama. idAdi para identificar uno de los tres
	 * Adicionales
	 */
	public String inicializar(int origen, String nroSolicitud, Long idAdi) {

		String resultado = "amIndividuos";
		if (error.cantidad() != 0)
			return null;
		listAdicional = new ArrayList();
		listGarante = new ArrayList();
		listTelefonoTitular = new ArrayList();
		listTelefono = new ArrayList();
		log.info("Ejecutando ==> inicializar(origen)");

		borrar();
		if (origen == TITULAR || origen == GARANTE) {
			limpiarCabecera();
			mostrarEducacion = true;
		} else {
			mostrarEducacion = false;
		}

		this.origen = origen;
		log.info("Inicializar - This.Origen: " + this.origen);
		this.nroSolicitud = nroSolicitud;
		log.info("Inicializar - nroSolicitud: " + this.nroSolicitud);
		cargarListas();
		log.info("Inicializar - Origen: " + idAdi);
		String result = cargarSolicitud(idAdi);
		if (result != null && result.compareTo("error") == 0) {
			resultado = null;
		}
		habilitarNroSolicitud = false;
		focoHidden = "inApellido";
		operador = new Operador();
		operador = Session.getOperador();
		listDomicilioInmueble = listDomicilioTitular;
		if (!listDomicilioPagoTitular.isEmpty())
			listDomicilioPago.addAll(listDomicilioPagoTitular);
		if (individuoEvaluacion == null)
			individuoEvaluacion = new IndividuoEvaluacion();
		if (individuoEvaluacion.getDomicilioDoc() != null) {
			idDomicilioPagoSeleccionado = individuoEvaluacion.getDomicilioDoc().getIdDomicilio();
		}
		switch (origen) {
		case TITULAR:
			listTelefono = listTelefonoTitular;
			break;
		case GARANTE:
			listTelefono = listTelefonoGarante;
			break;
		case ADICIONAL:
			listTelefono = listTelefonoAdicional;
			break;
		}
		if (vista == 3) {
			armarVerifiadores();
			botonAdjuntar = true;
			botonDomicilio = true;
			botonEmpresa = true;
			botonTelefono = true;
		}
		return resultado;
	}


	private void armarVerifiadores() {
		if (individuoEvaluacion != null) {
			try {
				// Filtro filtro = new Filtro("individuoEvaluacion.idIndividuo", Filtro.IGUAL, individuoEvaluacion.getIdIndividuo());
				// filtro.agregarCampoOperValor("domicilio.tipoDomicilio.idTipoDomicilio", Filtro.IGUAL, new Long(1));
				// List auxDom = evaluacionService.getIndividuoDomicilioService().getIndividuoDomicilio(filtro);
				// if (!auxDom.isEmpty()) {
				// Iterator iterator = auxDom.iterator();
				// while (iterator.hasNext()) {
				// IndividuoDomicilio element = (IndividuoDomicilio) iterator.next();
				// lstVerificadores = evaluacionService.getVerificadorService().getVerificador(new Filtro("partido.idPartido",Filtro.IGUAL,
				// element.getDomicilio().getLocalidad().getPartido().getIdPartido()));
				// log.info("Tamañno: " + lstVerificadores.size());
				// if (!lstVerificadores.isEmpty()) {
				// listaVerificadores = IndividuoEvaluacionUtil.cargarVerificadores(lstVerificadores);
				// } else {
				// SelectItem item = new SelectItem(new Long(0),"No hay Verificadores Disponibles");
				// listaVerificadores.add(item);
				// }
				// }
				// }
				log.info("Armando lalista de Verificadores");
				// log.info("el partido del individuo es: " + individuoEvaluacion.getDomicilio().getLocalidad().getPartido().getIdPartido());
				// lstVerificadores = evaluacionService.getVerificadorService().getVerificador(new Filtro("partido.idPartido",Filtro.IGUAL,
				// individuoEvaluacion.getDomicilio().getLocalidad().getPartido().getIdPartido()));
				lstVerificadores = evaluacionService.getVerificadorService().getVerificador(new Filtro("estado", Filtro.LIKEXS, "A"));
				if (!lstVerificadores.isEmpty()) {
					listaVerificadores.add(new SelectItem(new Long(0), "Seleccionar Verificador"));
					listaVerificadores.addAll(Util.cargarSelectItem(lstVerificadores));
				} else {
					listaVerificadores.add(new SelectItem(new Long(0), "No hay Verificadores Disponibles"));
				}
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("solicitudIndividuo.idSolicitudIndividuo", Filtro.IGUAL, solicitudIndividuo.getIdSolicIndividuo());
				filtro.agregarCampoOperValor("solicitudIndividuo.individuoEvaluacion.idIndividuo", Filtro.IGUAL, individuoEvaluacion.getIdIndividuo());
				List listPartic = evaluacionService.getInformeParticularService().getInformeParticular(filtro);
				if (!listPartic.isEmpty()) {
					particular = true;
					idInformeParticular = ((InformeParticular) listPartic.get(0)).getVerificador().getIdVerificador();
				}
				List listLabor = evaluacionService.getInformeLaboralService().getInformeLaboral(filtro);
				if (!listLabor.isEmpty()) {
					laboral = true;
					idInformeLaboral = ((InformeLaboral) listLabor.get(0)).getVerificador().getIdVerificador();
				}
			} catch (VerificadorException e) {
				e.printStackTrace();
			} catch (InformeParticularException e) {
				e.printStackTrace();
			} catch (InformeLaboralException e) {
				e.printStackTrace();
			}
		}
	}


	/*
	 * este metodo se utiliza para mostrar los diferentes paneles teniendo en cuenta quien lo llama
	 */
	public void mostarElementos(int origen) {
		log.info("Ejecutando ==> mostrarElementos()");
		log.info("mostrar elementos - Origen: " + origen);
		switch (origen) {
		case TITULAR:
			tituloCorto = "Alta Titular";
			log.info("Mostrar Elementos: Titular");
			panelA = true;
			panelB = false;
			panelC = false;
			panelD = true;
			switch (vista) {
			case 1:
				// panelD= false;
				panelE = false;
				panelF = true;
				break;
			case 2:
				// panelD=true;
				panelE = false;
				panelF = false;
				break;
			case 3:
				// panelD=false;
				panelE = true;
				panelF = true;
			}
			if (alta) {
				panelF = false;
			} else {
				panelF = true;
			}
			panelG = true;
			panelH = true;
			panelI = true;
			panelJ = true;
			panelK = true;

			break;

		case GARANTE:
			log.info("Mostrar Elementos: Garante");
			tituloCorto = "Alta Garante";
			panelA = false;
			panelB = false;
			panelC = false;
			panelD = true;
			switch (vista) {
			case 1:
				// panelD= false;
				panelE = false;
				panelF = false;
				break;
			case 2:
				// panelD=true;
				panelE = true;
				panelF = false;
				break;
			case 3:
				// panelD=false;
				panelE = true;
				panelF = false;
			}

			panelG = false;
			panelH = true;
			panelI = true;
			panelJ = true;
			panelK = true;

			break;

		case ADICIONAL:
			log.info("Mostrar Elementos: Adicional");
			tituloCorto = "Alta Adicional";
			panelA = false;
			panelB = false;
			panelC = false;
			panelD = true;
			switch (vista) {
			case 1:
				// panelD= false;
				panelE = false;
				panelF = false;
				break;
			case 2:
				// panelD=true;
				panelE = false;
				panelF = false;
				break;
			case 3:
				// panelD=false;
				panelE = false;
				panelF = false;
			}
			panelG = false;
			panelH = true;
			panelI = true;
			panelJ = true;
			panelK = true;
			break;

		}

	}


	/*
	 * este metodo se utiliza para cargar las diferentes listas.
	 */
	private void cargarListas() {

		try {
			listTipoDni = IndividuoEvaluacionUtil.cargarTipoDocumeno(generalService.getTipoDocumentoDao());
			listTipoSexo = IndividuoEvaluacionUtil.cargarSexo();
			listAuxEstadoCivil = generalService.getEstadoCivilDao().listarTodos(new Filtro());
			listEstadoCivil = IndividuoEvaluacionUtil.cargarEstadoCivil(listAuxEstadoCivil);
			listAuxEducacion = evaluacionService.getEducacionDao().listarTodos(new Filtro());
			listEducacion = IndividuoEvaluacionUtil.cargarEducacion(listAuxEducacion);
			listAuxProfesion = generalService.getProfesionDao().listarTodos(new Filtro());
			listProfesion = IndividuoEvaluacionUtil.cargarProfesion(listAuxProfesion);
			listAuxVinculo = generalService.getVinculoDao().listarTodos(new Filtro());
			listVinculo = IndividuoEvaluacionUtil.cargarVinculo(listAuxVinculo);
			listAuxBancos = generalService.getBancoDao().listarTodos();
			listBancos = IndividuoEvaluacionUtil.cargarBancos(listAuxBancos);
			log.info("Cargar Domicilio Pago");
			listAuxDomicilioPago = generalService.getTipoDomicilioDao().listarTodos();
			listDomicilioPago.add(new SelectItem(new Long(0), "Seleccionar Domicilio Pago"));
			diasPagos = new HtmlSelectOneMenu();
			diasPagos.setValue(new Long(0));
			bancoSeleccionado = new HtmlSelectOneMenu();
			bancoSeleccionado.setValue(new Long(0));
			listAuxTipoDigital = generalService.getTipoDigitalDao().listarTodos(new Filtro());
			listTipoDigital = IndividuoEvaluacionUtil.cargarTipoDigitales(listAuxTipoDigital);
			listAuxTipoCtas = generalService.getTipoCuentaBancoDao().listarTodos();
			listTipoCtas = IndividuoEvaluacionUtil.cargarTiposCuentas(listAuxTipoCtas);
			listDiaPago.add(new SelectItem(new Long(0), "Seleccionar Dia Pago"));
			listAuxOcupacion = generalService.getOcupacionService().getOcupacion(new Filtro());
			listOcupacion = new ArrayList();
			listOcupacion.add(new SelectItem(new Long(0), "Seleccionar Ocupación"));
			listOcupacion.addAll(Util.cargarSelectItem(listAuxOcupacion));
			ocupacionHtml.setValue(new Long(0));
		} catch (OcupacionException e) {
			e.printStackTrace();
		}
	}


	public void cambiarDiasDePago(ValueChangeEvent event) {
		try {
			listDiaPago.clear();
			listDiaPago.add(new SelectItem(new Long(0), "Selecciones Dia de Pago"));
			Long idDomPagoSeleccionado = new Long(diasPagos.getValue().toString());
			Long idPart = new Long(0);
			if (idDomPagoSeleccionado != new Long(0)) {
				if (individuoEvaluacion.getDomicilio() != null
						&& individuoEvaluacion.getDomicilio().getIdDomicilio() != null
						&& individuoEvaluacion.getDomicilio().getIdDomicilio().equals(idDomPagoSeleccionado)) {
					idPart = individuoEvaluacion.getDomicilio().getBarrio().getLocalidad().getPartido().getIdPartido();
				} else { // sino es el principal busco en el de la acctividad
					if (individuoEvaluacion.getActividad() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio().getIdDomicilio().equals(idDomPagoSeleccionado)) {
						idPart = individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio().getLocalidad().getPartido().getIdPartido();
					}
				}

				if (!idPart.equals(new Long(0))) {
					/* @I13 */Filtro filtro = new Filtro();
					filtro.agregarCampo("partido.idPartido");
					filtro.agregarOperador(Filtro.IGUAL);
					filtro.agregarValor(idPart);
					filtro.agregarCampo("habilitado_lista");
					filtro.agregarOperador(Filtro.IGUAL);
					filtro.agregarValor("'SI'");

					listDiaPago.addAll(Util.cargarSelectItem(
							/* @F13 */evaluacionService.getDiaPagoService().getDiaPago(filtro)));
				}
			}
		} catch (DiaPagoException e) {
			e.printStackTrace();
		}
	}


	/*
	 * Este metodo se utiliza para agregar un domicilio al individuo.
	 */
	public String agregarDomicilioInmueble() {
		log.info("Ir a agrega nuevo domicilio del inmueble al titular!!!");
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		bean.inicializar(DomicilioBean.GARANTIA, new Domicilio());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	/*
	 * Este metodo se utiliza para poder editar un domicilio, se captura el id del domicilio q se desea editar, luego se busca dentro del array de lso
	 * domicilios, una vez q se encuentra se manda al bean de domicilios, donde se cargara y estara lsito para ser editado.
	 */
	public String editarDomicilioInmueble() {
		log.info("Ejecutando ==> editarDomicilio()");
		Long idDomicilio = new Long(Session.getRequestParameter("idDomiEdi"));
		log.info("id a buscar: " + idDomicilio);
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		IndividuoDomicilio domAux = IndividuoEvaluacionUtil.buscarDomicilio(listDomicilioInmueble, idDomicilio);
		bean.inicializar(DomicilioBean.GARANTIA, domAux.getDomicilio());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	/*
	 * Este metodo se utiliza para capturar la accion del boton de eliminar direccion. este toma el id del domicilio y luego se lo pasa por parametro
	 * junto con la lita de domicilios a un metodo dentro del util de individuo evaluacion, que se encarga de elimar el domicilio del array.
	 */
	public String eliminarDomicilioInmueble() {
		log.info(" Ejecutando ==> eliminarDomicilio()");
		Long idDomicilio = new Long(Session.getRequestParameter("idDomicilio"));
		log.info("Id Domicilio: " + idDomicilio);
		IndividuoEvaluacionUtil.eliminarDomicilio(listDomicilioInmueble, idDomicilio);
		IndividuoEvaluacionUtil.eliminarDomicilioDelSet(individuoEvaluacion.getDomicilios(), idDomicilio);
		return null;
	}


	/*
	 * Este metodo se utiliza para agregar un domicilio al individuo.
	 */
	public String agregarDomicilioPopup() {
		log.info("Ir a agrega nuevo domicilio al titular!!!");
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		switch (origen) {
		case TITULAR:
			bean.inicializar(DomicilioBean.TITULAR, individuoEvaluacion.getDomicilio());
			break;
		case GARANTE:
			bean.inicializar(DomicilioBean.GARANTE, individuoEvaluacion.getDomicilio());
			break;
		case ADICIONAL:
			bean.inicializar(DomicilioBean.ADICIONAL, individuoEvaluacion.getDomicilio());
			break;
		}
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	/*
	 * Este metodo se utiliza para poder editar un domicilio, se captura el id del domicilio q se desea editar, luego se busca dentro del array de lso
	 * domicilios, una vez q se encuentra se manda al bean de domicilios, donde se cargara y estara listo para ser editado.
	 */
	public String editarDomicilio() {
		log.info("Ejecutando ==> editarDomicilio()");
		Long idDomicilio = new Long(Session.getRequestParameter("idDomiEdi"));
		log.info("id a buscar: " + idDomicilio);
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		IndividuoDomicilio domAux = IndividuoEvaluacionUtil.buscarDomicilio(listDomicilio, idDomicilio);

		switch (origen) {
		case TITULAR:
			bean.inicializar(DomicilioBean.TITULAR, domAux.getDomicilio());
			break;

		case GARANTE:
			bean.inicializar(DomicilioBean.GARANTE, domAux.getDomicilio());
			break;

		case ADICIONAL:
			bean.inicializar(DomicilioBean.ADICIONAL, domAux.getDomicilio());
			break;
		}
		bean.levantarListaPais(domAux.getDomicilio().getLocalidad().getProvincia().getPais().getIdPais(), domAux.getDomicilio().getLocalidad()
				.getProvincia().getIdProvincia(), domAux.getDomicilio().getLocalidad().getPartido().getIdPartido(), domAux.getDomicilio()
				.getLocalidad().getIdLocalidad(), domAux.getDomicilio().getBarrio().getIdBarrio());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	/*
	 * Este metodo se utiliza para capturar la accion del boton de eliminar direccion. este toma el id del domicilio y luego se lo pasa por parametro
	 * junto con la lita de domicilios a un metodo dentro del util de individuo evaluacion, que se encarga de elimar el domicilio del array.
	 */
	public String eliminarDomicilio() {
		// Logica anterior
		// log.info(" Ejecutando ==> eliminarDomicilio()");
		// Long idDomicilio = new Long(Session.getRequestParameter("idDomicilio"));
		// log.info("Id Domicilio: " + idDomicilio);
		// listDomicilio = IndividuoEvaluacionUtil.eliminarDomicilio(listDomicilio, idDomicilio);
		/* Agregado para compensar los errores provocados por la migracion */
		error.borrar();
		if (individuoEvaluacion.getDomicilio() != null
				&& individuoEvaluacion.getDomicilio().getIdDomicilio() != null
				&& individuoEvaluacion.getDomicilio().getIdDomicilio().equals(new Long(49000))) {
			individuoEvaluacion.setDomicilio(new Domicilio());
		} else {
			error.agregar("Solo se puede borrar el domicilio MIGRACION");
		}
		return null;
	}


	public void grabarDomicilioInmueble() {
		Iterator iterDomicilioInmueble = getListDomicilioInmueble().iterator();
		log.info("La ListaDomicilioInmueble tiene: " + getListDomicilioInmueble().size() + " elementos");
		while (iterDomicilioInmueble.hasNext()) {
			IndividuoDomicilio indDomInm = (IndividuoDomicilio) iterDomicilioInmueble.next();
			if (indDomInm.getDomicilio().getIdDomicilio().equals(new Long(indDomInm.hashCode()))) {
				if (individuoEvaluacion.getDomicilios() == null)
					individuoEvaluacion.setDomicilios(new HashSet());
				indDomInm.setIdIndivDomicilio(null);
				indDomInm.setIndividuoEvaluacion(individuoEvaluacion);
				indDomInm.getDomicilio().setIdDomicilio(null);
				individuoEvaluacion.getDomicilios().add(indDomInm);
			}
		}
		if (individuoEvaluacion.getDomicilios() != null && individuoEvaluacion.getDomicilios().isEmpty())
			individuoEvaluacion.setDomicilios(null);
	}


	/*
	 * * Este metodo elimina, agrega y edita los telefonos del set de individuos, a partir de la listTelefonos
	 */
	public void grabarTelefonos() {
		if (solicitudIndividuo.getIndividuoEvaluacion() != null) {
			if (solicitudIndividuo.getIndividuoEvaluacion().getTelefonos() == null)
				solicitudIndividuo.getIndividuoEvaluacion().setTelefonos(new HashSet());
			if (listTelefono != null) {
				Iterator iterTelefonos = listTelefono.iterator();
				while (iterTelefonos.hasNext()) {
					Telefonos telef = (Telefonos) iterTelefonos.next();
					if (telef.getTelefono().getIdTelefono().equals(new Long(telef.getTelefono().hashCode()))) {
						telef.setIdTelefono(null);
						telef.setIndividuoEvaluacion(individuoEvaluacion);
						telef.getTelefono().setIdTelefono(null);
						telef.getTelefono().setFechaAlta(new Date());
						telef.getTelefono().setOperador(Session.getOperador());
						solicitudIndividuo.getIndividuoEvaluacion().getTelefonos().add(telef);
					}
				}
			} else {
				solicitudIndividuo.getIndividuoEvaluacion().setTelefonos(null);
			}
		}
	}


	/*
	 * este metodo se utiliza para agregar un telefono al individuo
	 */
	public String agregarTelefono() {
		log.info("Ejecutando ==> agragrTelefono()");
		TelefonoBean bean = (TelefonoBean) Session.getBean("TelefonoBean");
		switch (origen) {
		case TITULAR:
			bean.inicializar(TelefonoBean.TITULAR, new Telefono());
			break;

		case GARANTE:
			bean.inicializar(TelefonoBean.GARANTE, new Telefono());
			break;

		case ADICIONAL:
			bean.inicializar(TelefonoBean.ADICIONAL, new Telefono());
			break;
		}

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");
		return null;
	}


	/*
	 * Este metodo se utiliza para poder eliminar un telefono de la lista de telefonos
	 */
	public String eliminarTelefono() {
		log.info("Ejecutando ==> eliminarTelefono()");
		Long idTelefono = new Long(Session.getRequestParameter("idTelefono"));
		IndividuoEvaluacionUtil.eliminarTelefono(listTelefono, idTelefono);
		IndividuoEvaluacionUtil.eliminarTelefonoSet(individuoEvaluacion.getTelefonos(), idTelefono);
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
		Telefono telefono = IndividuoEvaluacionUtil.buscarTelefono(
				listTelefono, idTelefono);

		switch (origen) {
		case TITULAR:
			bean.inicializar(TelefonoBean.TITULAR, telefono);
			break;

		case GARANTE:
			bean.inicializar(TelefonoBean.GARANTE, telefono);
			break;

		case ADICIONAL:
			bean.inicializar(TelefonoBean.ADICIONAL, telefono);
			break;
		}
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");

		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bizitglobal.webapp.faces.beans.BaseBean#validar()
	 */
	public boolean validar() {
		log.info("Ejecuando ==> Validar()");

		if (nroSolicitud == null || nroSolicitud.equals(new String("")))
			error.agregar(Error.EVA_NRO_SOLICITUD_REQUERIDA);

		if (nroSolicitud == null || nroSolicitud.length() != 8)
			error.agregar(Error.EVA_NRO_SOLICITUD_OCHODIGITOS);

		if (!validarDigitoVerificador()) {
			error.agregar(Error.EVA_NRO_SOLICITUD_NO_ENCONTRADA);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	private boolean validarDigitoVerificador() {

		if (nroSolicitud != null && !nroSolicitud.equals(new String("")) && nroSolicitud.length() == 8) {
			String numero = nroSolicitud.substring(0, 7);
			String dv = nroSolicitud.substring(7);
			boolean digitoV = NroVerificador.esValido(numero, dv);
			log.info("Nro Verificador: " + digitoV);
			return digitoV;
		}
		return false;
	}


	public void validarDatosRequeridos() {
		String cuilSexo;
		Long dosDigitos;
		validarCuit(cuil);
		if (individuoEvaluacion.getApellido() == null
				|| individuoEvaluacion.getApellido().equals("")) {
			error.agregar(Error.EVA_INDIVIDUO_APELLIDO_REQUERIDO);
		}
		if (individuoEvaluacion.getNombres() == null
				|| individuoEvaluacion.getNombres().equals("")) {
			error.agregar(Error.EVA_INDIVIDUO_NOMBRE_REQUERIDO);
		}
		if (individuoEvaluacion.getTipoDocumento().getIdTipoDocumento() == null
				|| individuoEvaluacion.getTipoDocumento().getIdTipoDocumento()
						.equals(new Long(0))) {
			error.agregar(Error.EVA_TIPO_DOCUMENTO_REQUERIDO);
		}
		if (individuoEvaluacion.getNroDocumento() == null
				|| individuoEvaluacion.getNroDocumento().equals("")) {
			error.agregar(Error.EVA_NRO_DOCUMENTO_REQUERIDO);
		}
		try {
			if (individuoEvaluacion.getCuil().substring(2, 3).equals("0") && individuoEvaluacion.getNroDocumento().length() < 8) {
				if (individuoEvaluacion.getCuil().substring(3, 10).compareTo(individuoEvaluacion.getNroDocumento()) != 0) {
					error.agregar(Error.EVA_DOCUMENTO_CUIL_INCOHERENTES);
				}
			} else {
				if (individuoEvaluacion.getCuil().substring(2, 10).compareTo(individuoEvaluacion.getNroDocumento()) != 0) {
					error.agregar(Error.EVA_DOCUMENTO_CUIL_INCOHERENTES);
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
			error.agregar("Error al intentar validar el nro. de documento");
			e.printStackTrace();
		}
		if (idTipoSexoSeleccionado == null || idTipoSexoSeleccionado.equals(new Long(0)))
			error.agregar(Error.EVA_TIPO_SEXO_REQUERIDO);

		if (cuil != null && cuil.compareTo("") != 0) {
			cuilSexo = cuil.substring(0, 2);
			log.info("Primeros Dos Digitos: " + cuilSexo);
			dosDigitos = new Long(cuilSexo.toString());
			log.info("Pprimeros Dos Digitos del Cuil: " + cuilSexo);
			if (!dosDigitos.equals(new Long(23))
					&& !dosDigitos.equals(new Long(24))
					&& !dosDigitos.equals(new Long(30))
					&& !dosDigitos.equals(new Long(33))) {
				if (idTipoSexoSeleccionado != null && !idTipoSexoSeleccionado.equals(new Long(0)) && idTipoSexoSeleccionado.equals(new Long(1))
						&& (!dosDigitos.equals(new Long(27))))
					error.agregar(Error.EVA_INDIVIDUO_SEXO_INCONCORDANTE);

				if (idTipoSexoSeleccionado != null && !idTipoSexoSeleccionado.equals(new Long(0)) && idTipoSexoSeleccionado.equals(new Long(2))
						&& !dosDigitos.equals(new Long(20)))
					error.agregar(Error.EVA_INDIVIDUO_SEXO_INCONCORDANTE);
			}
			if (dosDigitos.equals(new Long(30)) || dosDigitos.equals(new Long(33)))
				error.agregar(Error.EVA_NRO_CUIL_INCORRECTO);
		} else {
			error.agregar(Error.EVA_INDIVIDUO_CUIT_REQUERIDO);
		}
		if (email != null && !email.equals("") && !Validador.checkEmail(email))
			error.agregar(Error.EVA_INDIVIDUO_EMAIL_INCORRECTO);
	}


	/*
	 * Este metodo se utiliza para validar los datos antes de guardar
	 */
	public boolean validarGuardar() {
		validarDatosRequeridos();
		if (origen == TITULAR) {
			if ((individuoVehiculo.getVehiculo().getMarca() != null
					&& individuoVehiculo.getVehiculo().getMarca().compareTo("") != 0)
					|| (individuoVehiculo.getVehiculo().getModelo() != null
					&& individuoVehiculo.getVehiculo().getModelo().compareTo("") != 0)
					|| (individuoVehiculo.getVehiculo().getAnio() != null
					&& individuoVehiculo.getVehiculo().getAnio().compareTo("") != 0)) {
				if (individuoVehiculo.getVehiculo().getPatente() == null
						|| individuoVehiculo.getVehiculo().getPatente().compareTo("") == 0) {
					error.agregar("Para agregar un vehiculo es necesaria la patente.");
				}
			}
			if (validarTarjeta() == 2) {
				error.agregar("Para agregar una tarjeta ingrese los tres campos: Tarjeta, número y Banco");
			}
		}
		log.info("Cantidad de errores: " + error.cantidad());
		return (error.cantidad() == 0) ? true : false;
	}


	public boolean controlarAccesoVistas(Long cualAdicional) {
		boolean resultado = false;
		if (origen == 1 && vista == 1) {
			resultado = true;
		} else {
			if ((origen == 1 && vista != 1) || (origen != 1 && vista == 1)) {
				resultado = existeTitular(solicitud.getSolicIndividuos());
				if (!resultado && origen == 1)
					error.agregar(Error.EVA_TITULAR_NECESARIO);
				if (!resultado && origen != 1)
					error.agregar(Error.EVA_GARANTE_TITULAR);
			} else {
				if (origen == 2) {
					resultado = existeGarante(solicitud.getSolicIndividuos());
					if (!resultado)
						error.agregar(Error.EVA_GARANTE_NECESARIO);
				} else {
					if (origen == 3 && vista != 3) {
						if (cualAdicional != null) {
							resultado = existeAdicional(solicitud.getSolicIndividuos(), cualAdicional);
							if (!resultado)
								error.agregar(Error.EVA_ADICIONAL_NECESARIO);
						}
					} else {
						resultado = false;
						error.agregar("No se puede acceder con esta vista al adicional.");
					}
				}
			}
		}
		return resultado;
	}


	public boolean existeTitular(Set setin) {
		boolean resultado = false;
		Iterator i = setin.iterator();
		while (i.hasNext()) {
			SolicitudIndividuo sol = (SolicitudIndividuo) i.next();
			if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(TITULAR))) {
				resultado = true;
				break;
			}
		}
		return resultado;
	}


	public boolean existeGarante(Set setin) {
		boolean resultado = false;
		Iterator i = setin.iterator();
		while (i.hasNext()) {
			SolicitudIndividuo sol = (SolicitudIndividuo) i.next();
			if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(GARANTE))) {
				resultado = true;
				break;
			}
		}
		return resultado;
	}


	public boolean existeAdicional(Set setin, Long ida) {
		boolean resultado = false;
		Iterator i = setin.iterator();
		while (i.hasNext()) {
			SolicitudIndividuo sol = (SolicitudIndividuo) i.next();
			log.info("el id del individuo en la solicitud es: " + sol.getIdSolicIndividuo());
			if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(ADICIONAL)) && sol.getIdSolicIndividuo().equals(ida)) {
				resultado = true;
				break;
			}
		}
		return resultado;
	}


	public boolean controlarSiSonEdiciones(Long idA) {
		log.info("buscando " + idA);
		if (origen == 1)
			return true;
		if (origen == 2 && existeGarante(solicitud.getSolicIndividuos()))
			return true;
		if (idA != null) {
			log.info("El adicional buscado es: (numero de solINdividuo)" + idA);
			log.info("origen = " + origen);
			if (origen == 3 && existeAdicional(solicitud.getSolicIndividuos(), idA))
				return true;
		}
		return false;
	}


	/*
	 * Este metodo se utiliza para cargar los datos de la solicitud ingresando el nro de solicitud. Si la solicitud ya fue argada con anterioridad,
	 * aqui se carga los datos de los titulares, garantes y adicionales.
	 */
	public String cargarSolicitud(Long idAdi) {
		String resultado = null;
		// log.info("Ingreso al metodo cargar solicitud");
		List solicitudesIndividuos = new ArrayList();
		if (validar()) {
			log.info("Nro. Solic. ingresado: " + nroSolicitud);
			try {
				filtro = new Filtro("nroSolicitud", Filtro.LIKEXS, nroSolicitud.substring(0, 7));
				solicitud = null;
				solicitud = evaluacionService.getSolicitudService().busNroComprobante(filtro);
				if (solicitud != null) {
					if (solicitud.getPromotor() != null) {
						if (controlarAccesoVistas(idAdi)) {
							if (controlarSiSonEdiciones(idAdi)) {
								alta = false;
								if (solicitud.getSolicIndividuos().isEmpty()) {
									alta = true;
									yaExiste = true;
									habilitada = true;
								} else {
									yaExiste = false;
									habilitada = false;
								}
								if (solicitud.getFechaRecepcion() == null) {
									solicitud.setFechaRecepcion(new Timestamp(
											new Date().getTime()));
								}
								estados = solicitud.getEstados();
								solicitud.getEstados().getDescripcion();
								promotor = solicitud.getPromotor();
								nombrePromotor = solicitud.getPromotor().getApellido() + ", " + solicitud.getPromotor().getNombre();
								opcionA = true;
								listSucEmp.add(new SelectItem(new Long(0), "Seleccionar Sucursal"));
								log.info("Cargar Solicitud - Origen: " + origen);
								mostarElementos(origen);
								listGarante = new ArrayList();
								listAdicional = new ArrayList();
								Long id = solicitud.getIdSolicitud();
								// solicitud = null;
								// solicitud = evaluacionService.getSolicitudService().leerSolicitud(id);
								// solicitud.getSolicIndividuos();
								Iterator iterCargando = solicitud.getSolicIndividuos().iterator();

								while (iterCargando.hasNext()) {
									SolicitudIndividuo sol = (SolicitudIndividuo) iterCargando.next();
									IndividuoEvaluacion in = sol.getIndividuoEvaluacion();
									if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(2)) && sol.getActivo().compareTo("S") == 0) {
										listGarante.add(in);
										log.info("Agregado un garante a listGarante");
									} else {
										if (sol.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(3)) && sol.getActivo().compareTo("S") == 0) {
											listAdicional.add(in);
											log.info("Agregado un adicional a listAdicional");
											cantAdicionales++;
										}
									}
									in.getIdIndividuo();
									if (in.getDocAdjuntos() != null) {
										Iterator iDoc = in.getDocAdjuntos().iterator();
										while (iDoc.hasNext()) {
											Digital di = (Digital) iDoc.next();
											di.getIndividuoEvaluacion();
											di.getTimestamp();
											di.getTipoDigital().getIdTipoDigital();
										}
									}
									if (in.getActividad() != null) {
										in.getActividad().getIdActividad();
										if (in.getActividad().getSucEmpresa() != null) {
											if (in.getActividad().getSucEmpresa().getEmpresa() != null)
												in.getActividad().getSucEmpresa().getEmpresa().getCuit();
											if (in.getActividad().getSucEmpresa().getDomicilio() != null) {
												in.getActividad().getSucEmpresa().getDomicilio().getCalleNombre();
												in.getActividad().getSucEmpresa().getDomicilio().getLocalidad().getPartido();
											}
											if (in.getActividad().getSucEmpresa().getSucTelefonos() != null
													&& !in.getActividad().getSucEmpresa().getSucTelefonos().isEmpty()) {
												Iterator iter = in.getActividad().getSucEmpresa().getSucTelefonos().iterator();
												while (iter.hasNext()) {
													SucTelefono sucTelefono = (SucTelefono) iter.next();
													sucTelefono.getTelefono().getNroTlefono();
												}
											}
										}
										if (in.getActividad().getOcupacion() != null) {
											in.getActividad().getOcupacion().getIdOcupacion();
										}
									}
									in.getEducacion();
									in.getVinculo();
									if (in.getDomicilio() != null) {
										in.getDomicilio().getTipoDomicilio();
										if (in.getDomicilio().getTipoVivienda() != null) {
											in.getDomicilio().getTipoVivienda().getDescripcion();
										}
										if (in.getDomicilio().getPropVivienda() != null)
											in.getDomicilio().getPropVivienda().getDescripcion();
										if (in.getDomicilio().getBarrio() != null)
											in.getDomicilio().getBarrio().getDescripcion();
										if (in.getDomicilio().getBarrio() != null
												&& in.getDomicilio().getBarrio().getLocalidad() != null
												&& in.getDomicilio().getBarrio().getLocalidad().getPartido() != null
												&& in.getDomicilio().getBarrio().getLocalidad().getPartido().getProvincia() != null
												&& in.getDomicilio().getBarrio().getLocalidad().getPartido().getProvincia().getPais() != null
												&& in.getDomicilio().getBarrio().getLocalidad().getPartido().getProvincia().getPais().getIdPais() != null)
											;
										if (in.getDomicilio().getLocalidad() != null)
											in.getDomicilio().getLocalidad().getCodigoPostal();
										if (in.getDomicilio().getTipoDomicilio() != null)
											in.getDomicilio().getTipoDomicilio().getDescripcion();
										in.getDomicilio().getAntiguedad();
										in.getDomicilio().getTimestamp();
										in.getDomicilio().getCodigoPostal();
										in.getDomicilio().getOperador();
										in.getDomicilio().getOrientacion();
										in.getDomicilio().getCuotaAlquiler();
									}
									if (in.getMails() != null) {
										Iterator iterMail = in.getMails().iterator();
										while (iterMail.hasNext()) {
											Emails maiAuxi = (Emails) iterMail.next();
											maiAuxi.getEmail().getEmail();
											maiAuxi.getIndividuoEvaluacion();
										}
									}
									if (in.getTarjetas() != null) {
										Iterator iterTarj = in.getTarjetas().iterator();
										while (iterTarj.hasNext()) {
											Tarjeta tarAuxi = (Tarjeta) iterTarj.next();
											tarAuxi.getBanco();
											tarAuxi.getIndividuoEvaluacion();
										}
									}
									if (in.getDiaPago() != null) {
										in.getDiaPago().getPartido().getDescripcion();
									}
									if (in.getTelefonos() != null) {
										Iterator iterTelefonos = in.getTelefonos().iterator();
										while (iterTelefonos.hasNext()) {
											Telefonos telAuxi = (Telefonos) iterTelefonos.next();
											telAuxi.getTelefono().getTipo().getDescripcion();
											telAuxi.getIndividuoEvaluacion();
										}
									}
									in.getVehiculos();
									if (in.getVehiculos() != null) {
										Iterator iterVehiculos = in.getVehiculos().iterator();
										while (iterVehiculos.hasNext()) {
											IndividuoVehiculo vehAuxi = (IndividuoVehiculo) iterVehiculos.next();
											vehAuxi.getVehiculo().getPatente();
											vehAuxi.getIndividuoEvaluacion();
										}
									}
									if (in.getBancos() != null) {
										Iterator iterBancos = in.getBancos().iterator();
										while (iterBancos.hasNext()) {
											Bancos banAuxi = (Bancos) iterBancos.next();
											banAuxi.getBanco().getDescripcion();
											banAuxi.getIndividuo();
										}
									}
									in.getDomicilioDoc();
									if (in.getDomicilios() != null) {
										Iterator iterDeDomicilios = in.getDomicilios().iterator();
										while (iterDeDomicilios.hasNext()) {
											IndividuoDomicilio domAuxi = (IndividuoDomicilio) iterDeDomicilios.next();
											if (domAuxi.getDomicilio().getTipoDomicilio() != null)
												domAuxi.getDomicilio().getTipoDomicilio().getDescripcion();
											if (domAuxi.getDomicilio().getTipoVivienda() != null)
												domAuxi.getDomicilio().getTipoVivienda().getDescripcion();
											if (domAuxi.getDomicilio().getPropVivienda() != null)
												domAuxi.getDomicilio().getPropVivienda().getDescripcion();
											domAuxi.getDomicilio().getBarrio().getLocalidad().getPartido().getProvincia().getPais().getNombre();
											domAuxi.getIndividuoEvaluacion();
										}
									}
									in.getProfesion();
								}

								log.info("Origen: " + origen);
								solicitudesIndividuos = Convertidores.setToList(solicitud.getSolicIndividuos());
								log.info("Size solicitudesIndividuos: " + solicitudesIndividuos.size());
								if (!solicitudesIndividuos.isEmpty()) {
									Iterator iterator = solicitudesIndividuos.iterator();
									while (iterator.hasNext()) {
										solicitudIndividuo = (SolicitudIndividuo) iterator.next();
										if (solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(1))
												&& solicitudIndividuo.getIndividuo() != null
												&& origen == 1
												&& solicitudIndividuo.getActivo().compareTo("S") == 0) {
											yaExiste = true;
											habilitada = false;
											individuoEvaluacion = solicitudIndividuo.getIndividuoEvaluacion();
											leerIndividuoEvaluacion();
											break;
										}
										if (solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(2))
												&& solicitudIndividuo.getIndividuo() != null
												&& origen == 2
												&& solicitudIndividuo.getActivo().compareTo("S") == 0) {
											yaExiste = true;
											habilitada = false;
											individuoEvaluacion = solicitudIndividuo.getIndividuoEvaluacion();
											leerIndividuoEvaluacion();
											listGarante.add(individuoEvaluacion);
											break;
										}
										if (solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(3))
												&& solicitudIndividuo.getIndividuo() != null
												&& origen == 3
												&& solicitudIndividuo.getActivo().compareTo("S") == 0) {
											if (solicitudIndividuo.getIdSolicIndividuo().equals(idAdi)) {
												yaExiste = true;
												habilitada = false;
												individuoEvaluacion = solicitudIndividuo.getIndividuoEvaluacion();
												leerIndividuoEvaluacion();
												break;
											}
										}
									}
									if (solicitudIndividuo.getObservacion() != null)
										observacion = solicitudIndividuo.getObservacion();
									if (solicitudIndividuo.getConfTelLaboral() != null)
										confTelLaboral = solicitudIndividuo.getConfTelLaboral();
									if (solicitudIndividuo.getConfTelParticular() != null)
										confTelParticular = solicitudIndividuo.getConfTelParticular();

								} else {
									IndividuoEvaluacion individuoEvaluacion = buscarIndividuoPorCuit();
									if (individuoEvaluacion != null) {
										individuoEvaluacion.getApellido();
										individuoEvaluacion.getApellidoMaterno();
										individuoEvaluacion.getNombres();
										alta = false;
										cargarIndividuo(individuoEvaluacion, titularDomicilio, titularVehiculo);
										cargarDomicilio(individuoEvaluacion, solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo());
										armarListaTelefono(individuoEvaluacion, solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo());
									} else {
										alta = true;
										nuevaSolicitud();
										yaExiste = false;
										habilitada = true;
										TipoIndividuo tip = null;
										try {
											tip = evaluacionService.getTipoIndividuoService().leerTipoIndividuo(new Long(0));
										} catch (TipoIndividuoException e) {
											e.printStackTrace();
										}
										solicitudIndividuo.setTipoIndividuo(tip);
										solicitudIndividuo.setIndividuoEvaluacion(individuoEvaluacion);
										solicitudIndividuo.setActivo("S");
										solicitudIndividuo.setSolicitud(solicitud);
										solicitudIndividuo.setIdSolicitudIndividuo(null);
									}
								}
							} else {
								if (origen == 2) {
									error.agregar("El individuo seleccionado no es un garante.");
									resultado = "error";
									// altaGarante();
								}
								if (origen == 3) {
									error.agregar("El individuo seleccionado no es un adicional.");
									resultado = "error";
									// altaAdicional();
								}
							}
						}
					} else {
						error.agregar(Error.EVA_SOLICITUD_SIN_PROMOTOR);
						resultado = "error";
					}
				} else {
					error.agregar(Error.EVA_NRO_SOLICITUD_NO_ENCONTRADA);
					resultado = "error";
				}
			} catch (SolicitudException e) {
				e.printStackTrace();
			} catch (NullPointerException e2) {
				e2.printStackTrace();
			}
		}
		return resultado;
	}


	public void leerIndividuoEvaluacion() {
		log.info(individuoEvaluacion);
		Domicilio dom = individuoEvaluacion.getDomicilio();
		if (dom != null) {
			individuoEvaluacion.getDomicilio().getTipoDomicilio();
			individuoEvaluacion.getDomicilio().getBarrio();
			individuoEvaluacion.getDomicilio().getLocalidad();
			if (individuoEvaluacion.getDomicilio().getLocalidad() != null) {
				// log.info("RECUPERANDO EL PARTIDO");
				if (individuoEvaluacion.getDomicilio().getLocalidad().getPartido() != null)
					individuoEvaluacion.getDomicilio().getLocalidad().getPartido().getIdPartido();
			}
		} else {
			dom = new Domicilio();
			individuoEvaluacion.setDomicilio(dom);
		}
		if (individuoEvaluacion.getEstadoCivil() == null) {
			individuoEvaluacion.setEstadoCivil(new EstadoCivil(new Long(0)));
		}
		if (individuoEvaluacion.getEducacion() == null) {
			individuoEvaluacion.setEducacion(new Educacion(new Long(0)));
		}
		if (individuoEvaluacion.getDocAdjuntos() != null) {
			Iterator iDocTit = individuoEvaluacion.getDocAdjuntos().iterator();
			while (iDocTit.hasNext()) {
				Digital di = (Digital) iDocTit.next();
				WrapperFile wrapin = new WrapperFile();
				wrapin.setDescripcion(di.getDescripcion());
				wrapin.setIdDocumentoAdjunto(di.getIdDigital());
				wrapin.setIdTipoDoc(di.getTipoDigital().getIdTipoDigital());
				wrapin.setIdWrappers(new Long(di.hashCode()));
				wrapin.setNombreArchivo(di.getUrl());
				wrapin.setPath(di.getUrl());
				wrapin.setTimestamp(di.getTimestamp());
				wrapin.setListaDocumentos(listAuxTipoDigital);
				TipoDigital ti = null;
				Iterator iterTipos = listAuxTipoDigital.iterator();
				while (iterTipos.hasNext()) {
					TipoDigital tip = (TipoDigital) iterTipos.next();
					if (tip.getIdTipoDigital().equals(di.getTipoDigital().getIdTipoDigital())) {
						ti = tip;
						break;
					}
				}
				wrapin.setTipoDocumento(ti.getDescripcion());
				listTipoDocumentos.add(wrapin);
			}

		} else {
			listTipoDocumentos = new ArrayList();
		}
		if (individuoEvaluacion.getActividad() == null) {
			individuoEvaluacion.setActividad(new ActividadEvaluacion());
			individuoEvaluacion.getActividad().setIdActividad(new Long(0));
			individuoEvaluacion.getActividad().setOcupacion(new Ocupacion(new Long(0)));
			individuoEvaluacion.getActividad().setCargo("");
			individuoEvaluacion.getActividad().setSueldoNeto(null);
			individuoEvaluacion.getActividad().setEmpleoAnterior("");
			individuoEvaluacion.getActividad().setTelEmpleoAnt("");
			individuoEvaluacion.getActividad().setReferencias("");
			individuoEvaluacion.getActividad().setOtrosIngresosDesc("");
			individuoEvaluacion.getActividad().setOtrosIngresosMonto(null);
			antiguedad = "0";
		} else {
			if (individuoEvaluacion.getActividad().getOcupacion() == null) {
				individuoEvaluacion.getActividad().setOcupacion(new Ocupacion(new Long(0)));
			} else {
				idOcupacionSeleccionado = individuoEvaluacion.getActividad().getOcupacion().getIdOcupacion();
			}
			if (individuoEvaluacion.getActividad().getSucEmpresa() != null) {
				idSucursalSeleccionado = individuoEvaluacion.getActividad().getSucEmpresa().getIdSucEmpresa();
				empresa = individuoEvaluacion.getActividad().getSucEmpresa().getEmpresa();
				domSucursal = individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio();
				try {
					Iterator i = individuoEvaluacion.getActividad().getSucEmpresa().getSucTelefonos().iterator();
					while (i.hasNext()) {
						sucTelefono = (SucTelefono) i.next();
						sucTelefono.getTelefono().getNroTlefono();
						break;
					}
				} catch (Exception e) {
					log.info("HUBO ERRORES AL INTENTAR CARGAR EL TELEFONO DE LA SUCURSAL");
				}
				sucEmpresa = individuoEvaluacion.getActividad().getSucEmpresa();
				armarSucursal(individuoEvaluacion);
			} else {
				individuoEvaluacion.getActividad().setSucEmpresa(new SucEmpresa());
			}
			if (individuoEvaluacion.getActividad().getCargo() == null)
				individuoEvaluacion.getActividad().setCargo("");
			if (individuoEvaluacion.getActividad().getSueldoNeto() == null)
				individuoEvaluacion.getActividad().setSueldoNeto(null);
			if (individuoEvaluacion.getActividad().getEmpleoAnterior() == null)
				individuoEvaluacion.getActividad().setEmpleoAnterior("");
			if (individuoEvaluacion.getActividad().getTelEmpleoAnt() == null)
				individuoEvaluacion.getActividad().setTelEmpleoAnt("");
			if (individuoEvaluacion.getActividad().getReferencias() == null)
				individuoEvaluacion.getActividad().setReferencias("");
			if (individuoEvaluacion.getActividad().getOtrosIngresosDesc() == null)
				individuoEvaluacion.getActividad().setOtrosIngresosDesc("");
			if (individuoEvaluacion.getActividad().getOtrosIngresosMonto() == null)
				individuoEvaluacion.getActividad().setOtrosIngresosMonto(null);
			if (individuoEvaluacion.getActividad().getFechaIngreso() != null) {
				ingreso = new Date(individuoEvaluacion.getActividad().getFechaIngreso().getTime());
				setAntiguedad(IndividuoEvaluacionUtil.devolverCantidadAnios(new Date(individuoEvaluacion.getActividad().getFechaIngreso().getTime()))
						+ "");
			}
		}
		if (individuoEvaluacion.getDomicilioDoc() != null) {
			diasPagos.setValue(individuoEvaluacion.getDomicilioDoc().getIdDomicilio());
		} else {
			individuoEvaluacion.setDomicilioDoc(new Domicilio());
		}
		if (individuoEvaluacion.getVehiculos() != null && individuoEvaluacion.getVehiculos().size() > 0) {
			Iterator iterVehi = individuoEvaluacion.getVehiculos().iterator();
			while (iterVehi.hasNext()) {
				IndividuoVehiculo indVehi = (IndividuoVehiculo) iterVehi.next();
				indVehi.getVehiculo().getPatente();
				individuoVehiculo = indVehi;
				break;
			}
		} else {
			log.info("Agregando un auto vacio");
			individuoVehiculo = new IndividuoVehiculo();
			Vehiculo vehi = new Vehiculo();
			vehi.setAnio("");
			vehi.setIdVehiculo(null);
			vehi.setMarca("");
			vehi.setModelo("");
			vehi.setPatente("");
			individuoVehiculo.setIdIndiVehiculo(null);
			individuoVehiculo.setIndividuoEvaluacion(individuoEvaluacion);
			individuoVehiculo.setVehiculo(vehi);
		}
		log.info(individuoEvaluacion);
		alta = false;
		cargarIndividuo(individuoEvaluacion, individuoDomicilio, individuoVehiculo);
		cargarDomicilio(individuoEvaluacion, solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo());
		armarListaTelefono(individuoEvaluacion, solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo());
	}


	private void armarListaTelefono(IndividuoEvaluacion elemento, Long idTipoIndividuo) {

		List auxTelefono = Convertidores.setToList(elemento.getTelefonos());
		/* Acá armamos la lista de telefonos del individuo */
		listTelefonoTitular = new ArrayList();
		listTelefonoGarante = new ArrayList();
		listTelefonoAdicional = new ArrayList();
		if (!auxTelefono.isEmpty()) {
			Iterator iterator = auxTelefono.iterator();
			while (iterator.hasNext()) {
				Telefonos elemenTelefonos = (Telefonos) iterator.next();

				if (elemenTelefonos.getIndividuoEvaluacion().getIdIndividuo().equals(elemento.getIdIndividuo())) {
					elemenTelefonos.getTelefono().getNroTlefono();
					elemenTelefonos.getTelefono().getEsCelular();
					elemenTelefonos.getTelefono().getEsFax();
					elemenTelefonos.getTelefono().getTipo().getDescripcion();
					elemenTelefonos.getTelefono().getHorarios();
					elemenTelefonos.getTelefono().getDescripcion();

					switch (idTipoIndividuo.intValue()) {
					case 1:
						listTelefonoTitular.add(elemenTelefonos);
						break;

					case 2:
						listTelefonoGarante.add(elemenTelefonos);
						break;
					case 3:
						listTelefonoAdicional.add(elemenTelefonos);
						break;
					}
				}
			}
		}

	}


	public String irAListarIndividuos() {
		borrar();
		tituloCorto = "Listado de Individuos";
		Session.redirect("/tarjetafiel/evaluacion/listarIndividuos.jsf");
		return "";
	}


	public String traerIndividuo() {
		verCargaIndividuo = false;
		try {
			List listaDeIndividuosExis = new ArrayList();
			Filtro f = new Filtro();
			f.agregarCampoOperValor("cuil", Filtro.LIKEXS, cuilBuscador);
			listaDeIndividuosExis = evaluacionService.getIndividuoEvaluacionService().getIndividuo(f);
			if (!listaDeIndividuosExis.isEmpty()) {
				log.info("Origen ==> " + origen);
				IndividuoEvaluacion in = (IndividuoEvaluacion) listaDeIndividuosExis.get(0);
				f.reset();
				/*
				 * Aqui se arma el filtro para verificar que el individuo que se esta cargando no haya sido ya evaluado con anterioridad para esta
				 * misma solicitud como titular, garante o adicional.
				 */
				f.agregarCampoOperValor("individuoEvaluacion.idIndividuo", Filtro.IGUAL, in.getIdIndividuo());
				f.agregarCampoOperValor("tipoIndividuo.idTipoIndividuo", Filtro.NOTIN, new Long(origen));
				f.agregarCampoOperValor("solicitud.nroSolicitud", Filtro.LIKEXS, nroSolicitud.substring(0, 7));
				f.agregarCampoOperValor("solicitud.DV", Filtro.LIKEXS, nroSolicitud.substring(7));
				f.agregarCampoOperValor("activo", Filtro.LIKEXS, "S");

				List solicIndiv = evaluacionService.getSolicitudIndividuoService().getSolicitudIndividuo(f);
				if (solicIndiv.isEmpty()) {

					f = new Filtro();
					f.agregarCampoOperValor("individuoEvaluacion.idIndividuo", Filtro.IGUAL, in.getIdIndividuo());
					f.agregarCampoOperValor("solicitud.nroSolicitud", Filtro.LIKEXS, nroSolicitud.substring(0, 7));
					f.agregarCampoOperValor("solicitud.DV", Filtro.LIKEXS, nroSolicitud.substring(7));
					f.agregarCampoOperValor("activo", Filtro.LIKEXS, "N");

					solicIndiv = new ArrayList();
					solicIndiv = evaluacionService.getSolicitudIndividuoService().getSolicitudIndividuo(f);

					if (!solicIndiv.isEmpty()) {
						solicitudIndividuo = (SolicitudIndividuo) solicIndiv.get(0);
						solicitudIndividuo.setActivo(Convertidores.getSorN(true));
						alta = false;
					} else {
						apel = "";
						nom = "";
						habilitarCarga = true;
						solicitudIndividuo.setIndividuo(in);
						solicitudIndividuo.setSolicitud(solicitud);

						solicitudIndividuo.setActivo(Convertidores.getSorN(true));
						solicitudIndividuo.setAceptado(Convertidores.getSorN(false));

						if (origen == 1)
							solicitudIndividuo.setTipoIndividuo(new TipoIndividuo(new Long(1)));
						if (origen == 2)
							solicitudIndividuo.setTipoIndividuo(new TipoIndividuo(new Long(2)));
						if (origen == 3)
							solicitudIndividuo.setTipoIndividuo(new TipoIndividuo(new Long(3)));
					}

					if (alta) {
						evaluacionService.getSolicitudIndividuoService().grabarSolicitudIndividuo(solicitudIndividuo);
						evaluacionService.getSolicLogService().grabarSolicLog(
								new SolicLog(solicitud.getEstados(), Session.getOperador().getCodigo(), solicitudIndividuo));
						solicitud.getSolicIndividuos().add(solicitudIndividuo);
					} else {
						evaluacionService.getSolicitudIndividuoService().actualizarSolicitudIndividuo(solicitudIndividuo);
					}

					log.info("Nro Solicitud ==>> " + nroSolicitud);
					if (origen == TITULAR) {
						log.info("soy titular");
						inicializar(1, nroSolicitud, null);
						Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");
					}
					if (origen == GARANTE) {
						log.info("soy garante");
						inicializar(2, nroSolicitud, null);
						Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");
					}
					if (origen == 3) {
						// primero recuperamos el id de la solicitud
						log.info("===> nroSolicitud: " + nroSolicitud);
						log.info("===> nroSolicitud.substring(0, 7): " + nroSolicitud.substring(0, 7));
						log.info("===> nroSolicitud.substring(7): " + nroSolicitud.substring(7));
						Filtro filtroSolicitud = new Filtro("nroSolicitud", Filtro.LIKEXS, nroSolicitud.substring(0, 7));
						filtroSolicitud.agregarCampoOperValor("DV", Filtro.LIKEXS, nroSolicitud.substring(7));
						Solicitud soli = (Solicitud) (evaluacionService.getSolicitudService().getSolicitud(filtroSolicitud)).get(0);
						Long numeroOriginalSolicitud = soli.getIdSolicitud();
						log.info("soy adicional");
						log.info("el numero de solicitud es: " + numeroOriginalSolicitud);
						log.info("El id de individuo es " + solicitudIndividuo.getIndividuoEvaluacion().getIdIndividuo());
						Filtro filtro = new Filtro("solicitud", Filtro.IGUAL, numeroOriginalSolicitud);
						filtro.agregarCampoOperValor("individuoEvaluacion", Filtro.IGUAL, in.getIdIndividuo());
						filtro.agregarCampoOperValor("activo", Filtro.LIKE, "S");
						List solicAdi = evaluacionService.getSolicitudIndividuoService().getSolicitudIndividuo(filtro);
						if (solicAdi.size() > 0) {
							log.info("Hemos recuperado " + solicAdi.size() + "adicional/es");
							SolicitudIndividuo solInd = (SolicitudIndividuo) solicAdi.get(0);
							inicializar(3, nroSolicitud, solInd.getIdSolicitudIndividuo());
						}
						// Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");

					}
				}
				else
					error.agregar(Error.EVA_INDIVIDUO_YA_EVALUADO_EN_LA_MISMA_SOLICITUD);

			} else {
				if (!alta) {
					TipoIndividuo tipoIndio = solicitudIndividuo.getTipoIndividuo();
					Solicitud solIn = solicitudIndividuo.getSolicitud();
					log.info("borro la solicitud existente");
					log.info("La solicitudExistente tiene id: " + solicitudIndividuo.getIdSolicIndividuo());
					evaluacionService.getSolicitudIndividuoService().borrarSolicitudIndividuo(solicitudIndividuo);
					solicitudIndividuo = new SolicitudIndividuo();
					solicitudIndividuo.setIndividuo(new IndividuoEvaluacion());
					solicitudIndividuo.setTipoIndividuo(tipoIndio);
					solicitudIndividuo.setSolicitud(solIn);
					String cuilAuxiliar = cuilBuscador;
					if (origen == 1) {
						log.info("Cambio un individuo no existente por otro en que ya existe titular");
						alta = true;
						nuevaSolicitud();
						yaExiste = false;
						habilitada = false;
						cuil = cuilAuxiliar;
						TipoIndividuo tip = null;
						try {
							tip = evaluacionService.getTipoIndividuoService().leerTipoIndividuo(new Long(0));
						} catch (TipoIndividuoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						solicitudIndividuo.setTipoIndividuo(tip);
						solicitudIndividuo.setIndividuoEvaluacion(individuoEvaluacion);
						solicitudIndividuo.setActivo("S");
						solicitudIndividuo.setSolicitud(solicitud);
						solicitudIndividuo.setIdSolicitudIndividuo(null);
						log.info("Creando nueva solicitud Individuo");
						Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");

					}
					if (origen == 2) {
						String nroSolicitudAuxiliar = nroSolicitud;
						nuevaSolicitud();
						yaExiste = false;
						habilitada = false;
						solicitudIndividuo.setIdSolicitudIndividuo(null);
						solicitudIndividuo.setTipoIndividuo(new TipoIndividuo(new Long(2)));
						cuil = cuilAuxiliar;
						origen = 2;
						mostarElementos(2);
						nroSolicitud = nroSolicitudAuxiliar;
						Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");

					}
					if (origen == 3) {
						String nroSolicitudAuxiliar = nroSolicitud;
						nuevaSolicitud();
						yaExiste = false;
						habilitada = false;
						cuil = cuilAuxiliar;
						solicitudIndividuo.setTipoIndividuo(new TipoIndividuo(new Long(3)));
						origen = 3;
						cantAdicionales++;
						mostarElementos(3);
						nroSolicitud = nroSolicitudAuxiliar;
						Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");

					}
				} else {

					String cuilAuxiliar = cuilBuscador;
					if (origen == 1) {
						log.info("Alta de tituloar sin individuo existente");
						alta = true;
						nuevaSolicitud();
						yaExiste = false;
						habilitada = false;
						cuil = cuilAuxiliar;
						TipoIndividuo tip = null;
						try {
							tip = evaluacionService.getTipoIndividuoService().leerTipoIndividuo(new Long(0));
						} catch (TipoIndividuoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						solicitudIndividuo.setTipoIndividuo(tip);
						solicitudIndividuo.setIndividuoEvaluacion(individuoEvaluacion);
						solicitudIndividuo.setActivo("S");
						solicitudIndividuo.setSolicitud(solicitud);
						solicitudIndividuo.setIdSolicitudIndividuo(null);
						log.info("Creando nueva solicitud Individuo");
						Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");

					}
					if (origen == 2) {
						String nroSolicitudAuxiliar = nroSolicitud;
						nuevaSolicitud();
						yaExiste = false;
						habilitada = false;
						solicitudIndividuo.setIdSolicitudIndividuo(null);
						solicitudIndividuo.setTipoIndividuo(new TipoIndividuo(new Long(2)));
						cuil = cuilAuxiliar;
						origen = 2;
						mostarElementos(2);
						nroSolicitud = nroSolicitudAuxiliar;
						Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");

					}
					if (origen == 3) {
						String nroSolicitudAuxiliar = nroSolicitud;
						nuevaSolicitud();
						yaExiste = false;
						habilitada = false;
						cuil = cuilAuxiliar;
						solicitudIndividuo.setTipoIndividuo(new TipoIndividuo(new Long(3)));
						origen = 3;
						cantAdicionales++;
						mostarElementos(3);
						nroSolicitud = nroSolicitudAuxiliar;
						Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");

					}
				}
				Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");
			}
		} catch (IndividuoEvaluacionException e1) {
			e1.printStackTrace();
		} catch (SolicitudIndividuoException e2) {
			e2.printStackTrace();
		} catch (SolicitudException e3) {
			e3.printStackTrace();
		} catch (org.hibernate.exception.ConstraintViolationException e4) {
			error.agregar(Error.EVA_NO_SE_PUEDE_GRABAR_EL_INDIVIDUO);
			log.info("Se quisieron grabar datos repetidos");
		} catch (org.springframework.transaction.IllegalTransactionStateException e5) {
			error.agregar(Error.EVA_NO_SE_PUEDE_GRABAR_EL_INDIVIDUO);
		} catch (Exception e4) {
			e4.printStackTrace();
		}
		return null;
	}


	public String buscarOtroIndividuo() {
		opcionA = true;
		verCargaIndividuo = false;
		// cuilBuscador = "";
		// apel="";
		// nom="";
		//
		// switch (origen) {
		// case 2:case 1:
		// inicializar(origen,nroSolicitud,null);
		// break;
		//
		// case 3:
		// if(solicitudIndividuo.getIdSolicIndividuo() != null)
		// inicializar(origen,nroSolicitud,solicitudIndividuo.getIdSolicIndividuo() );
		// else
		// inicializar(origen,nroSolicitud,null);
		// break;
		// }
		return "amIndividuo";
	}


	public String irAContinuar() {
		log.info("irContinuar");
		popup.borrar();
		if (Session.getBean("ScrollBean") != null) {
			((ScrollBean) Session.getBean("ScrollBean")).setHiddenScrollY(new Integer(0));
		}
		return null;
	}


	public String listarIndividuos() {
		listaIndividuos = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (idIndividuo != null && !idIndividuo.equals(""))
				filtro.agregarCampoOperValor("idIndividuo", Filtro.IGUAL, new Long(this.idIndividuo));
			if (apellido != null && !apellido.equals(""))
				filtro.agregarCampoOperValor("apellido", Filtro.LIKE, apellido);
			listaIndividuos = evaluacionService.getIndividuoEvaluacionService().getIndividuo(filtro);
			Iterator iter = listaIndividuos.iterator();
			while (iter.hasNext())
			{
				IndividuoEvaluacion element = (IndividuoEvaluacion) iter.next();

				// Agregar aqui todo lo necesario de extraer
			}
			idIndividuo = "";
			apellido = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/evaluacion/listarIndividuos.jsf");
		return null;
	}


	private void cargarDomicilio(IndividuoEvaluacion elemento,
			Long idTipoIndividuo) {
		/* Acá armamos el domicilio */
		listDomicilioTitular = new ArrayList();
		listDomicilioPagoTitular = new ArrayList();
		listDomicilioGarante = new ArrayList();
		listDomicilioPagoGarante = new ArrayList();
		if (elemento.getDomicilio() != null && elemento.getDomicilio().getIdDomicilio() != null
				&& !elemento.getDomicilio().getIdDomicilio().equals(new Long(0))) {
			switch (idTipoIndividuo.intValue()) {
			case 1:
				listDomicilioPagoTitular.add(new SelectItem(individuoEvaluacion.getDomicilio().getIdDomicilio(), individuoEvaluacion.getDomicilio()
						.getCalleNombre() + " " + individuoEvaluacion.getDomicilio().getCalleNumero()));

				break;
			case 2:
				listDomicilioPagoGarante.add(new SelectItem(individuoEvaluacion.getDomicilio().getIdDomicilio(), individuoEvaluacion.getDomicilio()
						.getCalleNombre() + " " + individuoEvaluacion.getDomicilio().getCalleNumero()));

				break;
			}
		}
		if (elemento.getActividad() != null
				&& elemento.getActividad().getIdActividad() != null
				&& !elemento.getActividad().getIdActividad().equals(new Long(0))
				&& elemento.getActividad().getSucEmpresa() != null
				&& elemento.getActividad().getSucEmpresa().getIdSucEmpresa() != null
				&& !elemento.getActividad().getSucEmpresa().getIdSucEmpresa().equals(new Long(0))) {
			Domicilio dom = elemento.getActividad().getSucEmpresa().getDomicilio();
			switch (idTipoIndividuo.intValue()) {
			case 1:
				listDomicilioPagoTitular.add(new SelectItem(dom.getIdDomicilio(), dom.getCalleNombre() + " " + dom.getCalleNumero()));

				break;
			case 2:
				listDomicilioPagoGarante.add(new SelectItem(dom.getIdDomicilio(), dom.getCalleNombre() + " " + dom.getCalleNumero()));

				break;
			}
		}

		if (!individuoEvaluacion.getDomicilios().isEmpty()) {
			Iterator iterator2 = individuoEvaluacion.getDomicilios().iterator();
			while (iterator2.hasNext()) {
				IndividuoDomicilio elementDomicilio = (IndividuoDomicilio) iterator2.next();

				// if (elementDomicilio.getIndividuoEvaluacion().getIdIndividuo().equals(elemento.getIdIndividuo())) {
				elementDomicilio.getDomicilio().getBarrio().getDescripcion();
				elementDomicilio.getDomicilio().getLocalidad().getPartido().getDescripcion();
				elementDomicilio.getDomicilio().getLocalidad().getProvincia().getPais().getNombre();
				elementDomicilio.getDomicilio().getTipoDomicilio().getDescripcion();
				elementDomicilio.getDomicilio().getTipoVivienda().getDescripcion();
				elementDomicilio.getDomicilio().getPropVivienda().getDescripcion();
				switch (idTipoIndividuo.intValue()) {
				case 1:
					// listDomicilioPagoTitular.add(new SelectItem(elementDomicilio.getDomicilio().getIdDomicilio(),
					// elementDomicilio.getDomicilio().getCalleNombre()+ " "+ elementDomicilio.getDomicilio().getCalleNumero()));
					listDomicilioTitular.add(elementDomicilio);
					break;
				// case 2:
				// listDomicilioPagoGarante.add(new SelectItem(elementDomicilio.getDomicilio().getTipoDomicilio().getIdTipoDomicilio(),
				// elementDomicilio.getDomicilio().getTipoDomicilio().getDescripcion()));
				// listDomicilioGarante.add(elementDomicilio);
				// break;
				}
				// }
			}

			// if (elemento.getDomicilioDoc() != null&& elemento.getDomicilioDoc().getIdDomicilio() != null)
			// idDomicilioPagoSeleccionado = elemento.getDomicilioDoc().getIdDomicilio();
		}
	}


	/*
	 * En este metodo, se arma todo el individuo con los datos que se han traido cuando se levantaron los datos de la base de datos.
	 */
	private void cargarIndividuo(IndividuoEvaluacion elemento, IndividuoDomicilio elementDomicilio, IndividuoVehiculo elementVehiculo) {
		individuoEvaluacion = elemento;
		/* Acá armamos el dia de pago */
		if (elemento.getDiaPago() != null) {
			elemento.getDiaPago();
			if (elemento.getDiaPago().getPartido() != null) {
				log.info("Partido: " + elemento.getDiaPago().getPartido().getDescripcion());
				if (elemento.getDiaPago().getPartido().getIdPartido() != null
						&& !elemento.getDiaPago().getPartido().getIdPartido().equals(new Long(0))) {
					try {
						/* @I13 */Filtro filtro = new Filtro();
						filtro.agregarCampo("habilitado_lista");
						filtro.agregarOperador(Filtro.IGUAL);
						filtro.agregarValor("'SI'");

						/* @F13 */listAuxDiaPago = evaluacionService.getDiaPagoService().getDiaPago(filtro);
						if (!listAuxDiaPago.isEmpty()) {
							Iterator iterator = listAuxDiaPago.iterator();
							while (iterator.hasNext()) {
								DiaPago elem = (DiaPago) iterator.next();
								if (elem.getPartido().getIdPartido().equals(elemento.getDiaPago().getPartido().getIdPartido()))
									listDiaPago.add(new SelectItem(elem.getIdDiaPago(), elem.getDiaPago().toString()));
							}
						}
						idDiaPagoSeleccionado = elemento.getDiaPago().getIdDiaPago();
					} catch (DiaPagoException e) {
						e.printStackTrace();
					}
				}
				if (elemento.getDiaPago().getIdDiaPago() != null)
					idDiaPagoSeleccionado = elemento.getDiaPago().getIdDiaPago();
			}
		} else
			elemento.setDiaPago(new DiaPago(new Long(0)));
		/* Aca recuperamos el banco y el cbu en su defecto */
		if (origen == TITULAR) {
			if (individuoEvaluacion.getBancos() == null && !individuoEvaluacion.getCbu().equals(null)) {
				if (individuoEvaluacion.getCbu().compareTo("") != 0) {
					esCBU = true;
				}
			} else {
				try {
					List banq = Convertidores.setToList(individuoEvaluacion.getBancos());
					if (!banq.isEmpty()) {
						Bancos ba = (Bancos) Convertidores.setToList(individuoEvaluacion.getBancos()).get(0);
						nombreBanco = ba.getBanco().getDescripcion();
						idBcoSeleccionado = ba.getIdBanco();
						bancos = ba;
					}
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
		}
		if (elemento.getProfesion() != null) {
			log.info("Profesión: " + elemento.getProfesion().getDescripcion());
		} else {
			elemento.setProfesion(new Profesion());
			elemento.getProfesion().setIdProfesion(new Long(0));
		}
		if (elemento.getTipoDocumento() != null) {
			log.info("Id Tipo Doc: " + elemento.getTipoDocumento().getIdTipoDocumento());
			log.info("Descipción: " + elemento.getTipoDocumento().getDescripcion());
		} else {
			elemento.setTipoDocumento(new TipoDocumento());
			elemento.getTipoDocumento().setIdTipoDocumento(new Long(0));
		}
		if (elemento.getVinculo() != null) {
			log.info("Id Vinculo: " + elemento.getVinculo().getIdVinculo());
			log.info("Descripción Vinculo: " + elemento.getVinculo().getDescripcion());
		} else {
			elemento.setVinculo(new Vinculo(new Long(0)));
		}
		if (elemento.getFechaNacimiento() != null) {
			fechaNacimiento = new Date(elemento.getFechaNacimiento().getTime());
		}
		if (elemento.getSexo() != null) {
			if (elemento.getSexo().equals("M"))
				idTipoSexoSeleccionado = new Long(2);
			else
				idTipoSexoSeleccionado = new Long(1);
		} else {
			idTipoSexoSeleccionado = new Long(0);
		}
		if (elemento.getVehiculoPropio() != null)
			vehiculoPropio = Convertidores.getBoolean(elemento.getVehiculoPropio());
		else {
			vehiculoPropio = false;
		}
		if (elemento.getCuil() != null) {
			cuil = elemento.getCuil();
		}
		if (elemento.getCbu() != null && elemento.getCbu().compareTo("") != 0) {
			esCBU = true;
		} else {
			if (elemento.getBancos() != null && elemento.getBancos().size() > 0) {
				List listaDeBancos = Convertidores.setToList(elemento.getBancos());
				Bancos b = (Bancos) listaDeBancos.get(0);
				idBcoSeleccionado = b.getBanco().getIdBanco();
				bancoSeleccionado.setValue(idBcoSeleccionado);
				log.info("el banco recuperado tiene id: " + idBcoSeleccionado);
				nombreBanco = b.getBanco().getDescripcion();
			}
			if (elemento.getTipoCuenta() != null) {
				idTipoCuentaSeleccionado = Long.valueOf(elemento.getTipoCuenta());
				// Iterator iterCuenta = listAuxTipoCtas.iterator();
				// while (iterCuenta.hasNext()) {
				// TipoCuentaBanco tipoCuenBan = (TipoCuentaBanco) iterCuenta.next();
				// if (tipoCuenBan.getIdCuentaBanco().equals(Long.valueOf(elemento.getTipoCuenta()))) {
				// idTipoCuentaSeleccionado = tipoCuenBan.getIdCuentaBanco();
				// }
				// }
			} else {
				idTipoCuentaSeleccionado = new Long(0);
			}
		}
		if (elemento.getTarjetas() != null && elemento.getTarjetas().size() > 0) {
			List listaTarjetas = Convertidores.setToList(elemento.getTarjetas());
			Tarjeta tarjetaCargada = (Tarjeta) listaTarjetas.get(0);
			tarjeta.setDescripcion(tarjetaCargada.getDescripcion());
			tarjeta.setNro(tarjetaCargada.getNro());
			idBancoSeleccionado = tarjetaCargada.getBanco().getIdBanco();
		}
		if (elemento.getMails() != null && elemento.getMails().size() > 0) {
			List listaMails = Convertidores.setToList(elemento.getMails());
			Emails ma = (Emails) listaMails.get(0);
			email = ma.getEmail().getEmail();
		}
	}


	private void armarSucursal(IndividuoEvaluacion elemento) {
		if (elemento.getActividad().getSucEmpresa().getIdSucEmpresa() != null
				&& !elemento.getActividad().getSucEmpresa().getIdSucEmpresa().equals(new Long(0))) {
			nroCuit = elemento.getActividad().getSucEmpresa().getEmpresa().getCuit().toString();
			listSucursales = new ArrayList();
			Iterator iterSucursal = elemento.getActividad().getSucEmpresa().getEmpresa().getSucEmpresas().iterator();
			while (iterSucursal.hasNext()) {
				try {
					SucEmpresa sucur = (SucEmpresa) iterSucursal.next();
					sucur.getDomicilio().getCalleNombre();
					// sucur.getDomicilio().
					sucur.getSucEmails();
					sucur.getSucTelefonos();
					listSucursales.add(sucur);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			listSucEmp = IndividuoEvaluacionUtil.cargarSucursales(listSucursales);
			habilitarSucursal = false;
			empresa = elemento.getActividad().getSucEmpresa().getEmpresa();
			direccionSucursal = elemento.getActividad().getSucEmpresa().getDomicilio().getCalleNombre() + " "
					+ elemento.getActividad().getSucEmpresa().getDomicilio().getCalleNumero();
		}
	}


	/*
	 * este metodo se utiliza para buscar la empresa en la q trabaja el individuo
	 */
	public String buscarEmpresa() {
		log.info("Ejecutando ==> buscarEmpresa()");
		if (nroCuit != null && !nroCuit.equals("")) {
			filtro = new Filtro("cuit", Filtro.IGUAL, nroCuit);
			try {
				List aux = generalService.getEmpresaService().getEmpresa(filtro);
				listSucursales = new ArrayList();
				if (!aux.isEmpty()) {
					// direccionSucursal = "";
					// telefono = "";
					empresa = (Empresa) aux.get(0);
					Iterator iterSucursal = empresa.getSucEmpresas().iterator();
					while (iterSucursal.hasNext()) {
						try {
							SucEmpresa sucur = (SucEmpresa) iterSucursal.next();
							sucur.getDomicilio().getCalleNombre();
							sucur.getDomicilio().getLocalidad().getPartido().getIdPartido();
							sucur.getSucEmails();
							sucur.getSucTelefonos();
							listSucursales.add(sucur);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					listSucEmp = IndividuoEvaluacionUtil.cargarSucursales(listSucursales);
					direccionSucursal = "";
					telefono = "";
					domSucursal = null;
					sucTelefono = null;
					habilitarSucursal = false;
				} else {
					error.agregar(Error.EVA_INDIVIDUO_EMPRESA);
					habilitarSucursal = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			error.agregar(Error.EVA_NRO_CUIT_REQUERIDO);
		}
		sucursalHtml.setValue(new Long(0));
		return null;
	}


	/*
	 * este metodo se utiliza para poder cargar los datos de la sucursal de la empresa en la que trabaja el individuo.
	 */
	public void cambioSucursal(ValueChangeEvent event) {
		log.info("Ejecutando ==> cambioSucursal()");
		try {
			Long valorSeleccionado = new Long(sucursalHtml.getValue().toString());
			if (valorSeleccionado.intValue() != 0 && !listSucursales.isEmpty()) {
				Iterator iterator = listSucursales.iterator();
				while (iterator.hasNext()) {
					sucEmpresa = (SucEmpresa) iterator.next();
					if (sucEmpresa.getIdSucEmpresa().equals(valorSeleccionado)) {
						domSucursal = sucEmpresa.getDomicilio();
						// listDomicilioPago.add(new SelectItem(domSucursal.getIdDomicilio(),
						// domSucursal.getCalleNombre() + " " + domSucursal.getOrientacion() + " " + domSucursal.getCalleNumero()));
						SelectItem item = new SelectItem(domSucursal.getIdDomicilio(), domSucursal.getCalleNombre() + " "
								+ domSucursal.getCalleNumero());
						if (!listDomicilioPago.contains(item)) {
							listDomicilioPago.add(item);
						}
						try {
							Iterator iter = generalService.getSucTelefonoService()
									.getSucTelefono(new Filtro("sucEmpresa.idSucEmpresa", Filtro.IGUAL, valorSeleccionado)).iterator();
							while (iter.hasNext()) {
								sucTelefono = (SucTelefono) iter.next();
								telefono = sucTelefono.getTelefono().getNroTlefono();
								break;
							}
						} catch (SucTelefonoException e) {
							e.printStackTrace();
						}
						break;
					} else {
						direccionSucursal = "";
						telefono = "";
						sucEmpresa = null;
						domSucursal = null;
					}
				}
			} else {
				sucEmpresa = new SucEmpresa();
				telefono = "";
				direccionSucursal = "";
				sucEmpresa = null;
				domSucursal = null;
			}
			if (sucEmpresa != null
					&& sucEmpresa.getIdSucEmpresa() != null) {
				if (individuoEvaluacion.getActividad() == null) {
					individuoEvaluacion.setActividad(new ActividadEvaluacion());
					individuoEvaluacion.getActividad().setOcupacion(new Ocupacion(new Long(0)));
				} else {
					if (individuoEvaluacion.getActividad().getSucEmpresa() != null) {
						if (individuoEvaluacion.getActividad().getTipo() != null
								&& !individuoEvaluacion.getActividad().getTipo().equals("E")) {
							individuoEvaluacion.setActividad(new ActividadEvaluacion());
							individuoEvaluacion.getActividad().setOcupacion(new Ocupacion(new Long(0)));
						}
					}
				}
				individuoEvaluacion.getActividad().setSucEmpresa(sucEmpresa);
				Filtro filtro = new Filtro("cuil", Filtro.LIKE, cuil);
				filtro.agregarCampoOperValor("sucEmpresa.idSucEmpresa", Filtro.IGUAL, sucEmpresa.getIdSucEmpresa());
				List actiList = evaluacionService.getActividadEvaluacionService().getActividad(filtro);
				if (!actiList.isEmpty()) {
					ActividadEvaluacion actividadEvaluacion = (ActividadEvaluacion) actiList.get(0);
					if (!actividadEvaluacion.getIdActividad().equals(individuoEvaluacion.getActividad().getIdActividad())) {
						actividadEvaluacion.getOcupacion().getIdOcupacion();
						actividadEvaluacion.getSucEmpresa().getEmpresa().getRazonSocial();
						individuoEvaluacion.setActividad(actividadEvaluacion);
						ingreso = new Date(actividadEvaluacion.getFechaIngreso().getTime());
						antiguedad = actividadEvaluacion.getAntiguedad().toString();
						refrescarAct = false;
					}
				}
			}
		} catch (ActividadEvaluacionException e) {
			e.printStackTrace();
		} catch (NullPointerException e1) {
			e1.printStackTrace();
		}
	}


	/*
	 * este me todo se utiliza para poder habilitar La lista e profeciones teneindo en cuenta si el individuo tiene estudios Universitarios Completo.
	 */
	public void cambiarProfesion(ValueChangeEvent event) {
		// if (profesionHtml!=null&&profesionHtml.getValue()!=null && !profesionHtml.getValue().equals("")) {
		if (educacionHtml != null && educacionHtml.getValue() != null && !educacionHtml.getValue().equals("")) {
			idEducSeleccionado = new Long(educacionHtml.getValue().toString());
			if (idEducSeleccionado.equals(new Long(8))) {
				habilitarProfesion = false;
				focoHidden = "lstPorfesion";
			} else {
				habilitarProfesion = true;
				individuoEvaluacion.getProfesion().setIdProfesion(new Long(0));
				focoHidden = "lstPorfesion";
			}
		} else {
			habilitarProfesion = false;
		}
	}


	/*
	 * Este método se utiliza para validar el cuit de una empresa.
	 */
	private boolean validarCuit(String cuit) {
		if (!cuit.equals(null) && !cuit.equals("")) {
			try {
				cuitValido = new CuitValido(cuit);
			} catch (CuitNoValidoException e1) {
				error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
				// e1.printStackTrace();
			} catch (Exception e) {
				error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
				// e.printStackTrace();
			}
		} else {
			error.agregar(Error.PROVEEDOR_CUIT_REQUERIDO);
		}
		return !error.hayErrores();
	}


	public String verSolicitudesIndividuo() {
		listDeSolicitudesPorIndividuo = new ArrayList();
		mostrarSolicitudes = true;
		try {
			individuoEvaluacion = evaluacionService.getIndividuoEvaluacionService().leerIndividuo(new Long(idIndividuoHidden));
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("individuoEvaluacion.idIndividuo", Filtro.IGUAL, individuoEvaluacion.getIdIndividuo());
			List solicitudes = evaluacionService.getSolicitudIndividuoService().getSolicitudIndividuo(filtro);
			Iterator iter = solicitudes.iterator();
			while (iter.hasNext()) {
				SolicitudIndividuo solInd = (SolicitudIndividuo) iter.next();
				SolicitudesIndividuosWrapper elem = new SolicitudesIndividuosWrapper(String.valueOf(solInd.getSolicitud().getNroSolicitud())
						+ String.valueOf(solInd.getSolicitud().getDV()), solInd.getTipoIndividuo().getDescripcion(), solInd.getIdSolicIndividuo());
				listDeSolicitudesPorIndividuo.add(elem);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndividuoEvaluacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolicitudIndividuoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	// metodo para dejar de ver las solicitudes de un solo individuo y ver el listado de individuos.
	public String verListado() {
		mostrarSolicitudes = false;
		return null;
	}


	public void grabarTarjeta() {
		// Armamos la tarjeta o la dejamos vacia;
		switch (validarTarjeta()) {

		case 1:
			log.info("la tarjeta retorno 1");
			individuoEvaluacion.setTarjetas(new HashSet());
			break;
		case 2:
			log.info("la tarjeta retorno 2");
			break;
		case 3:
			log.info("la tarjeta retorno 3");
			// primero encontramos el banco seleccionado
			Banco b = null;
			try {
				List listaDeBancosParaTarjeta = generalService.getBancoService().getBancos(new Filtro());
				Iterator iterBancosTarjeta = listaDeBancosParaTarjeta.iterator();
				while (iterBancosTarjeta.hasNext()) {
					Banco ban = (Banco) iterBancosTarjeta.next();
					log.info("El id Banco es: " + ban.getIdBanco());
					log.info("el bancoSeleccionado es: " + idBancoSeleccionado);
					if (ban.getIdBanco().equals(idBancoSeleccionado)) {
						b = ban;
					}
				}
			} catch (BancoException e1) {
				e1.printStackTrace();
			}
			// Ahora seteamos la tarjeta
			if (individuoEvaluacion.getTarjetas() != null && individuoEvaluacion.getTarjetas().size() > 0) {
				Iterator bancoTar = individuoEvaluacion.getTarjetas().iterator();
				Tarjeta tarj = null;
				while (bancoTar.hasNext()) {
					tarj = (Tarjeta) bancoTar.next();
					break;
				}
				if (tarj == null) {
					tarj.setIdTarjeta(null);
				}
				tarj.setIndividuoEvaluacion(individuoEvaluacion);
				tarj.setDescripcion(tarjeta.getDescripcion());
				tarj.setNro(tarjeta.getNro());
				tarj.setBanco(b);
				break;
			} else {
				Tarjeta tarj = new Tarjeta();
				tarj.setIndividuoEvaluacion(individuoEvaluacion);
				tarj.setDescripcion(tarjeta.getDescripcion());
				tarj.setNro(tarjeta.getNro());
				tarj.setBanco(b);
				individuoEvaluacion.getTarjetas().add(tarj);
			}
			break;
		case 4:
			individuoEvaluacion.setTarjetas(null);
			break;
		}

	}


	public void grabarMail() {
		if (individuoEvaluacion.getMails() != null && individuoEvaluacion.getMails().size() > 0) {
			// aqui editamos el mail
			List listaDeEmail = Convertidores.setToList(individuoEvaluacion.getMails());
			Emails edit = (Emails) listaDeEmail.get(0);
			edit.getEmail().setEmail(email);
			individuoEvaluacion.setMails(new HashSet());
			individuoEvaluacion.getMails().add(edit);
		} else {
			if (email.compareTo("") == 0 || email == null) {
				individuoEvaluacion.setMails(null);
			} else {
				Email mai = new Email(null, email, Session.getOperador(), null);
				Emails mails = new Emails();
				mails.setIdEmails(null);
				mails.setIndividuoEvaluacion(individuoEvaluacion);
				/* @F45 */mai.setFechaAlta(new Date());
				mails.setEmail(mai);
				individuoEvaluacion.setMails(new HashSet());
				individuoEvaluacion.getMails().add(mails);
			}
		}
	}


	public String guardarSolicitud() {
		log.info("Ejecutando ==> Guardar");

		if (alta)
			individuoEvaluacion.setCuil(cuil);

		if (observacion.compareTo("") == 0) {
			solicitudIndividuo.setObservacion(null);
		} else {
			solicitudIndividuo.setObservacion(observacion);
		}
		if (confTelLaboral.compareTo("") == 0) {
			solicitudIndividuo.setConfTelLaboral(null);
		} else {
			solicitudIndividuo.setConfTelLaboral(confTelLaboral);
		}
		if (confTelParticular.compareTo("") == 0) {
			solicitudIndividuo.setConfTelParticular(null);
		} else {
			solicitudIndividuo.setConfTelParticular(confTelParticular);
		}
		if (validarGuardar()) {
			log.info("Valido Bien");

			try {
				armarDiaPago();
				armarIndividuo();
				if (alta)
					armarTipoIndividuo();
				grabarTelefonos();
				grabarDomicilioInmueble();
				grabarTarjeta();
				grabarMail();
				armarVehiculo();
				// Armamos el banco, tipo de cuenta, numero de cuenta o en
				// su defecto el cbu;
				if (esCBU) {
					individuoEvaluacion.setBancos(null);
					individuoEvaluacion.setTipoCuenta(null);
					individuoEvaluacion.setNroCuenta(null);
				} else {
					individuoEvaluacion.setCbu(null);
					if (!idBcoSeleccionado.equals(new Long(0))) {
						try {
							List listaDeBancos = generalService.getBancoService().getBancos(new Filtro());
							Iterator iterBancos = listaDeBancos.iterator();
							while (iterBancos.hasNext()) {
								Banco ban = (Banco) iterBancos.next();
								log.info("El id Banco es: " + ban.getIdBanco());
								log.info("el bancoSeleccionado es: " + idBcoSeleccionado);
								if (ban.getIdBanco().equals(idBcoSeleccionado)) {
									// Aca debemos recuperar el Objeto
									// bancos del individuo. si es nulo lo
									// creamos, si no modificamos
									if (individuoEvaluacion.getBancos() != null && individuoEvaluacion.getBancos().size() > 0) {
										Iterator banquitos = individuoEvaluacion.getBancos().iterator();
										Bancos banc = null;
										while (banquitos.hasNext()) {
											banc = (Bancos) banquitos.next();
											break;
										}
										if (banc == null) {
											banc.setIdBanco(null);
										} else {

										}
										banc.setBanco(ban);
										banc.setIndividuo(individuoEvaluacion);
										banc.setVinculacion(bancos.getVinculacion());
										break;
									} else {
										Bancos banc = new Bancos();
										banc.setIdBanco(null);
										banc.setBanco(ban);
										banc.setVinculacion(bancos.getVinculacion());
										banc.setIndividuo(individuoEvaluacion);
										individuoEvaluacion.setBancos(new HashSet());
										individuoEvaluacion.getBancos().add(banc);
										break;
									}
								}
							}
						} catch (BancoException e1) {
							e1.printStackTrace();
						}
					} else {
						individuoEvaluacion.setBancos(null);
					}
				}
				individuoEvaluacion.setVehiculoPropio(Convertidores.getSorN(vehiculoPropio));
				solicitud.setEstados(evaluacionService.getEstadosService().leerEstados(new Long(3)));
				solicitudIndividuo.setSolicitud(solicitud);

				// proceso para cargar la Profesion del individuo.
				if (solicitudIndividuo.getIndividuoEvaluacion().getProfesion() != null
						&& solicitudIndividuo.getIndividuoEvaluacion().getProfesion().getId() != null
						&& !solicitudIndividuo.getIndividuoEvaluacion().getProfesion().getId().equals(new Long(0))) {
					Iterator iterProfesion = listAuxProfesion.iterator();
					while (iterProfesion.hasNext()) {
						Profesion profesionAux = (Profesion) iterProfesion.next();
						if (profesionAux.getId() == solicitudIndividuo.getIndividuoEvaluacion().getProfesion().getIdProfesion()) {
							solicitudIndividuo.getIndividuoEvaluacion().setProfesion(profesionAux);
						}
					}
				} else {
					solicitudIndividuo.getIndividuoEvaluacion().setProfesion(null);
				}
				// proceso para guardar la Actividad.
				if (solicitudIndividuo.getIndividuoEvaluacion().getActividad() != null
						&& solicitudIndividuo.getIndividuoEvaluacion().getActividad().getIdActividad() != null
						&& solicitudIndividuo.getIndividuoEvaluacion().getActividad().getIdActividad().equals(new Long(0))) {
					solicitudIndividuo.getIndividuoEvaluacion().getActividad().setIdActividad(null);
				}
				// ACA ESTABA LA ASOCIACION DE LA ACTIVIDAD CON LA sucEmpresa
				if (antiguedad.compareTo("") == 0) {
					if (solicitudIndividuo.getIndividuoEvaluacion().getActividad() != null)
						solicitudIndividuo.getIndividuoEvaluacion().getActividad().setAntiguedad(null);
				} else {
					if (solicitudIndividuo.getIndividuoEvaluacion().getActividad() != null)
						solicitudIndividuo.getIndividuoEvaluacion().getActividad().setAntiguedad(new Long(antiguedad));
				}

				if (solicitudIndividuo.getIndividuoEvaluacion().getActividad() != null
						&& solicitudIndividuo.getIndividuoEvaluacion().getActividad().getOcupacion() != null
						&& solicitudIndividuo.getIndividuoEvaluacion().getActividad().getOcupacion().getIdOcupacion() != null
						&& !solicitudIndividuo.getIndividuoEvaluacion().getActividad().getOcupacion().getIdOcupacion().equals(new Long(0))) {
					Iterator iterActividad = listAuxOcupacion.iterator();
					while (iterActividad.hasNext()) {
						Ocupacion ocupa = (Ocupacion) iterActividad.next();
						if (ocupa.getIdOcupacion() == solicitudIndividuo.getIndividuoEvaluacion().getActividad().getOcupacion().getIdOcupacion()) {
							solicitudIndividuo.getIndividuoEvaluacion().getActividad().setOcupacion(ocupa);
							break;
						}
					}
				} else {
					solicitudIndividuo.getIndividuoEvaluacion().getActividad().setOcupacion(null);
				}
				if (individuoEvaluacion.getActividad() != null) { // Se agrego esto al ultimo para adecuarse a las nuevas especificaciones
					individuoEvaluacion.getActividad().setCuil(individuoEvaluacion.getCuil());
				}
				solicitudIndividuo.getIndividuoEvaluacion().setTipoDocumento(
						generalService.getTipoDocumentoService().leerTipoDocumento(individuoEvaluacion.getTipoDocumento().getIdTipoDocumento()));
				if (solicitudIndividuo.getIdSolicIndividuo() != null) {
					evaluacionService.getSolicitudIndividuoService().actualizarSolicitudIndividuo(solicitudIndividuo);
					log.info("Se actualizó con exito una solicitudIndividuo.");
				} else {
					solicitudIndividuo.setActivo("S");
					solicitudIndividuo.setAceptado("N");
					solicitudIndividuo.setIdSolicitudIndividuo(null);
					log.info("Buscando la solicitud nro: " + nroSolicitud);
					filtro = new Filtro("nroSolicitud", Filtro.LIKEXS, nroSolicitud.substring(0, 7));
					// solicitud = null;
					// log.info("Ejecutando la consulta a la base de datos");
					// solicitud = evaluacionService.getSolicitudService().busNroComprobante(filtro);
					if (solicitud != null) {
						log.info("Actualizando una solicitud");
						// solicitudIndividuo.setSolicitud(solicitud);
						evaluacionService.getSolicitudIndividuoService().grabarSolicitudIndividuo(solicitudIndividuo);
						evaluacionService.getSolicLogService().grabarSolicLog(
								new SolicLog(solicitud.getEstados(), Session.getOperador().getCodigo(), solicitudIndividuo));
					}
					alta = false;
				}

				// }
				if (individuoEvaluacion.getProfesion() == null)
					individuoEvaluacion.setProfesion(new Profesion(idProfesionSeleccionado));

				if (individuoEvaluacion.getTipoDocumento() == null) {
					individuoEvaluacion.setTipoDocumento(new TipoDocumento());
					individuoEvaluacion.getTipoDocumento().setIdTipoDocumento(idTipoDocumentoSeleccionado);
				}

				// if (individuoEvaluacion.getEstadoCivil() == null) {
				// individuoEvaluacion.setEstadoCivil(new EstadoCivil());
				// individuoEvaluacion.getEstadoCivil().setIdEstadoCivil(idEstadoCivilSeleccionado);
				// }

				if (individuoEvaluacion.getVinculo() == null) {
					individuoEvaluacion.setVinculo(new Vinculo());
					individuoEvaluacion.getVinculo().setIdVinculo(idVinvuloSeleccionado);
				}

				if (individuoEvaluacion.getEducacion() == null) {
					individuoEvaluacion.setEducacion(new Educacion());
					individuoEvaluacion.getEducacion().setIdEducacion(new Long(0));
				}
				if (individuoEvaluacion.getActividad() != null && individuoEvaluacion.getActividad().getOcupacion() == null) {
					individuoEvaluacion.getActividad().setOcupacion(new Ocupacion(new Long(0)));
				}
				if (idDomicilioPagoSeleccionado != null && idDomicilioPagoSeleccionado.equals(new Long(-1))) {
					Iterator iter = listDomicilioPago.iterator();
					while (iter.hasNext()) {
						SelectItem item = (SelectItem) iter.next();
						if (item.getValue().equals(new Long(-1))) {
							listDomicilioPago.remove(item);
							listDomicilioPago.add(new SelectItem(individuoEvaluacion.getDomicilio().getIdDomicilio(), individuoEvaluacion
									.getDomicilio().getCalleNombre() + " " + individuoEvaluacion.getDomicilio().getCalleNumero()));
							idDomicilioPagoSeleccionado = individuoEvaluacion.getDomicilio().getIdDomicilio();
							diasPagos.setValue(idDomicilioPagoSeleccionado);
							break;
						}
					}
				}
				log.info("Grabo con exito.");
				popup.setPopup(popup.ICONO_OK, "El individuo ha sido almacenada exitosamente.");
			} catch (SolicitudIndividuoException e) {
				popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Individuo.");
				mostarElementos(origen);
				e.printStackTrace();
			} catch (Exception e) {
				popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Individuo.");
				mostarElementos(origen);
				e.printStackTrace();
			}
		} else {
			ScrollBean scroll = (ScrollBean) Session.getBean("ScrollBean");
			scroll.setHiddenScrollY(new Integer(0));
			log.info("Valido muchos errores");
		}

		if (origen == TITULAR) {
			panelA = true;
			panelB = false;
			panelC = false;
			panelD = true;
			panelE = false;
			if (alta) {
				panelF = false;
			} else {
				panelF = true;
			}
			panelG = true;
			panelH = true;
			panelI = true;
			panelJ = true;
			panelK = true;
		}
		if (origen == GARANTE || origen == ADICIONAL) {

			// inicializar(1,nroSolicitud,null);

		}

		return null;
	}


	private void armarVehiculo() {
		if (individuoVehiculo.getIdIndiVehiculo() == null || individuoVehiculo.getIdIndiVehiculo().equals(new Long(0))) {
			individuoVehiculo.setIndividuoEvaluacion(individuoEvaluacion);
			individuoVehiculo.setIdIndiVehiculo(null);
			log.info("Vehiculo: " + individuoVehiculo.getVehiculo().toString());
			individuoEvaluacion.setVehiculos(new HashSet());
			individuoVehiculo.getVehiculo().setIdVehiculo(null);
			individuoEvaluacion.getVehiculos().add(individuoVehiculo);
		}
		if (individuoVehiculo.getVehiculo().getPatente() == null || individuoVehiculo.getVehiculo().getPatente().compareTo("") == 0) {
			individuoEvaluacion.setVehiculos(null);
		}
	}


	/*
	 * Este metodo se utiliza para alrmar un objeto DiaPago
	 */
	private void armarDiaPago() {
		try {
			if (idDiaPagoSeleccionado != null && !idDiaPagoSeleccionado.equals(new Long(0))) {
				diaPago = evaluacionService.getDiaPagoService().leerDiaPago(idDiaPagoSeleccionado);
				diaPago.setPartido(generalService.getPartidoDao().buscarPartido(diaPago.getPartido().getIdPartido()));
				individuoEvaluacion.setDiaPago(diaPago);
			} else {
				individuoEvaluacion.setDiaPago(null);
			}
			if (idDomicilioPagoSeleccionado != null && idDomicilioPagoSeleccionado.equals(new Long(-1))) {
				individuoEvaluacion.getDomicilio().setIdDomicilio(null);
				individuoEvaluacion.setDomicilioDoc(individuoEvaluacion.getDomicilio());
			} else {
				if (individuoEvaluacion.getDomicilio() != null
						&& individuoEvaluacion.getDomicilio().getIdDomicilio() != null
						&& individuoEvaluacion.getDomicilio().getIdDomicilio().equals(idDomicilioPagoSeleccionado)) {
					individuoEvaluacion.setDomicilioDoc(individuoEvaluacion.getDomicilio());
				} else {
					if (individuoEvaluacion.getActividad() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio().getIdDomicilio() != null
							&& individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio().getIdDomicilio().equals(idDomicilioPagoSeleccionado)) {
						Domicilio dom = individuoEvaluacion.getActividad().getSucEmpresa().getDomicilio();
						individuoEvaluacion.setDomicilioDoc(dom);
					} else {
						individuoEvaluacion.setDomicilioDoc(null);
					}
				}
			}
			// if (idDomicilioPagoSeleccionado != null && !idDomicilioPagoSeleccionado.equals(new Long(0))) {
			// Iterator iterDomDoc = listDomicilioInmueble.iterator();
			// while (iterDomDoc.hasNext()) {
			// IndividuoDomicilio domDoc = (IndividuoDomicilio) iterDomDoc.next();
			// if (domDoc.getDomicilio().getIdDomicilio().equals(idDomicilioPagoSeleccionado)) {
			// individuoEvaluacion.setDomicilioDoc(domDoc.getDomicilio());
			// break;
			// }
			// }
			//
			// } else {
			// individuoEvaluacion.setDomicilioDoc(null);
			// }
		} catch (DiaPagoException e) {
			e.printStackTrace();
		}
	}


	/*
	 * Este metodo se utiliza para armar un objeto del Tipo Individuo
	 */
	private void armarTipoIndividuo() {
		try {
			List aux = evaluacionService.getTipoIndividuoService().getTipoIndividuo(new Filtro());
			Iterator iterator = aux.iterator();
			while (iterator.hasNext()) {
				tipoIndividuo = (TipoIndividuo) iterator.next();
				if (tipoIndividuo.getIdTipoIndividuo().equals(new Long(origen))) {
					log.info(tipoIndividuo.toString());
					solicitudIndividuo.setTipoIndividuo(tipoIndividuo);
				}
			}
		} catch (TipoIndividuoException e) {
			e.printStackTrace();
		}
		// SE ME OCURRE PONER ACA LA VALIDACION DEL INDIVIDUO PARA ACTUALIZARLE SI SIGUE ACTIVO O NO
	}


	/*
	 * Este metodo se utiliza para armar un objeto Actividad.
	 */
	private void armarActividad() {
		log.info("Ejecutando ==> armarActividad()");

		log.info("id Sucursal Seleccionado: " + idSucursalSeleccionado);
		if (idSucursalSeleccionado != null && !idSucursalSeleccionado.equals(new Long(0))) {

			if (!listSucursales.isEmpty()) {
				Iterator iterator = listSucursales.iterator();

				while (iterator.hasNext()) {
					SucEmpresa element = (SucEmpresa) iterator.next();

					if (element.getIdSucEmpresa().equals(idSucursalSeleccionado)) {
						individuoEvaluacion.getActividad().setSucEmpresa(element);
					}
				}
			}
		} else {
			individuoEvaluacion.getActividad().setSucEmpresa(null);
		}
		if (ingreso != null && !ingreso.equals(new Date(0)))
			individuoEvaluacion.getActividad().setFechaIngreso(new Timestamp(ingreso.getTime()));
		else
			individuoEvaluacion.getActividad().setFechaIngreso(null);
	}


	/*
	 * este metodo se utiliza para armar el individuoEvaluacion.
	 */
	private void armarIndividuo() {
		solicitudIndividuo.setIndividuo(individuoEvaluacion);
		if (solicitudIndividuo.getIndividuoEvaluacion() != null) {
			if (solicitudIndividuo.getIndividuoEvaluacion().getDomicilio() != null) {
				if (solicitudIndividuo.getIndividuoEvaluacion().getDomicilio().equals(new Domicilio())) {
					solicitudIndividuo.getIndividuoEvaluacion().setDomicilio(null);
				} else {
					if (solicitudIndividuo.getIndividuoEvaluacion().getDomicilio().getIdDomicilio() != null
							&& solicitudIndividuo.getIndividuoEvaluacion().getDomicilio().getIdDomicilio().equals(new Long(-1))) {
						solicitudIndividuo.getIndividuoEvaluacion().getDomicilio().setIdDomicilio(null);
					}
				}
			}
			if (solicitudIndividuo.getIndividuoEvaluacion().getDomicilioDoc() != null
					&& solicitudIndividuo.getIndividuoEvaluacion().getDomicilioDoc().equals(new Domicilio())) {
				solicitudIndividuo.getIndividuoEvaluacion().setDomicilioDoc(null);
			}
			switch (idTipoSexoSeleccionado.intValue()) {
			case 0:
				individuoEvaluacion.setSexo(null);
				break;

			case 1:
				individuoEvaluacion.setSexo("F");
				break;

			case 2:
				individuoEvaluacion.setSexo("M");
				break;
			}

			if (fechaNacimiento != null && !fechaNacimiento.equals(new Date(0))) {
				individuoEvaluacion.setFechaNacimiento(new Timestamp(fechaNacimiento.getTime()));
			} else {
				individuoEvaluacion.setFechaNacimiento(null);
			}
			individuoEvaluacion.setEstadoCivil((EstadoCivil) Util.buscarElemento(listAuxEstadoCivil, individuoEvaluacion.getEstadoCivil()));
			// Remplace todo lo comentado por la linea anterior
			// if (individuoEvaluacion.getEstadoCivil() != null) {
			// if (individuoEvaluacion.getEstadoCivil().getIdEstadoCivil() != null &&
			// !individuoEvaluacion.getEstadoCivil().getIdEstadoCivil().equals(new Long(0))) {
			// Iterator iterator = listAuxEstadoCivil.iterator();
			// while (iterator.hasNext()) {
			// EstadoCivil element = (EstadoCivil) iterator.next();
			// if (element.getIdEstadoCivil().equals(idEstadoCivilSeleccionado)) {
			// individuoEvaluacion.setEstadoCivil(element);
			// }
			// }
			// } else {
			// individuoEvaluacion.setEstadoCivil(null);
			// }
			// } else {
			// individuoEvaluacion.setEstadoCivil(null);
			// }
			if (individuoEvaluacion.getEducacion() != null && individuoEvaluacion.getEducacion().getIdEducacion() != null
					&& !individuoEvaluacion.getEducacion().getIdEducacion().equals(new Long(0))) {
				Iterator iterator = listAuxEducacion.iterator();
				while (iterator.hasNext()) {
					Educacion element = (Educacion) iterator.next();
					if (element.getIdEducacion().equals(individuoEvaluacion.getEducacion().getIdEducacion())) {
						individuoEvaluacion.setEducacion(element);
					}
				}
			} else {
				individuoEvaluacion.setEducacion(null);
			}
			idVinvuloSeleccionado = individuoEvaluacion.getVinculo().getIdVinculo();
			if (idVinvuloSeleccionado != null && !idVinvuloSeleccionado.equals(new Long(0))) {
				Iterator iterator = listAuxVinculo.iterator();
				while (iterator.hasNext()) {
					Vinculo element = (Vinculo) iterator.next();
					if (element.getIdVinculo().equals(idVinvuloSeleccionado)) {
						individuoEvaluacion.setVinculo(element);
					}
				}
			} else {
				individuoEvaluacion.setVinculo(null);
			}
			if (idTipoCuentaSeleccionado != null && !idTipoCuentaSeleccionado.equals(new Long(0))) {
				individuoEvaluacion.setTipoCuenta(idTipoCuentaSeleccionado.toString());
			} else {
				individuoEvaluacion.setTipoCuenta(null);
			}
			log.info("Id Tipo Cuenta Seleccionado: " + idTipoCuentaSeleccionado);
			armarActividad();
			individuoEvaluacion.setCuil(cuil);
			individuoEvaluacion.setTimestamp(new Timestamp(new java.util.Date().getTime()));
			individuoEvaluacion.setIdOperador(operador.getCodigo());
			log.info(individuoEvaluacion.toString());
		}
		armarDocumentosAdj();
	}


	public int validarTarjeta() {
		if (tarjeta != null && tarjeta.getNro() != null && tarjeta.getDescripcion() != null) {
			if ((tarjeta.getNro().equals("") || tarjeta.getNro() == null)
					&& (tarjeta.getDescripcion().equals("") || tarjeta.getDescripcion() == null)) {
				return 1;
			} else {
				if (tarjeta.getNro() == "" || tarjeta.getDescripcion() == "" || idBancoSeleccionado.equals(new Long(0)))
					return 2;
			}
			return 3;
		}
		return 4;
	}


	public void armarDocumentosAdj() {

		if (!listTipoDocumentos.isEmpty()) {
			if (individuoEvaluacion.getDocAdjuntos() == null)
				individuoEvaluacion.setDocAdjuntos(new HashSet());
			Iterator ite = listTipoDocumentos.iterator();
			while (ite.hasNext()) {
				WrapperFile arAd = (WrapperFile) ite.next();
				if (arAd.getIdDocumentoAdjunto() == null) {
					Digital digit = new Digital();
					digit.setDescripcion(arAd.getDescripcion());
					digit.setIdDigital(null);
					digit.setIdOperador(Session.getOperador().getCodigo());
					digit.setIndividuoEvaluacion(individuoEvaluacion);
					digit.setTimestamp(new Timestamp(new Date().getTime()));
					TipoDigital ti = null;
					Iterator iterTipos = listAuxTipoDigital.iterator();
					while (iterTipos.hasNext()) {
						TipoDigital tip = (TipoDigital) iterTipos.next();
						if (tip.getIdTipoDigital().equals(arAd.getIdTipoDoc())) {
							ti = tip;
							break;
						}
					}
					digit.setTipoDigital(ti);
					digit.setUrl(arAd.getPath());
					individuoEvaluacion.getDocAdjuntos().add(digit);
				}
			}
		} else {
			individuoEvaluacion.setDocAdjuntos(null);
		}

	}


	public String salirSolicitud() {
		FacesContext facesContext;
		String javaScriptText;
		// switch (origen) {
		//
		// case TITULAR:
		if (panelF) {
			facesContext = FacesContext.getCurrentInstance();
			javaScriptText = "window.opener.recargar();window.close();";
			ejecutarJavaScript(javaScriptText);
			mostarElementos(TITULAR);
			// aqui el codigo para buscar el titular
			return "amIndividuo";
		}
		facesContext = FacesContext.getCurrentInstance();
		javaScriptText = "window.close();";
		ejecutarJavaScript(javaScriptText);
		popup.borrar();
		// break;
		//
		// case GARANTE:
		// inicializar(1,nroSolicitud,null);
		// return "amIndividuo";
		//
		// case ADICIONAL:
		// inicializar(1,nroSolicitud,null);
		// return "amIndividuo";

		// }
		return null;
	}


	public String altaGarante() {
		log.info("Ejecutando altaGarante()");
		alta = true;
		mostrarEducacion = true;
		// if (!listGarante.isEmpty()) {
		if (listGarante.size() == 0) {
			guardarSolicitud();
			String nroSolicitudAuxiliar = nroSolicitud;
			nuevaSolicitud();
			yaExiste = false;
			habilitada = true;
			solicitudIndividuo.setIdSolicitudIndividuo(null);
			solicitudIndividuo.setTipoIndividuo(new TipoIndividuo(new Long(2)));
			origen = 2;
			mostarElementos(2);
			nroSolicitud = nroSolicitudAuxiliar;
			return "amIndividuo";
		} else {
			error.agregar(Error.EVA_INDIVIDUO_ALTA_GARANTE);
		}
		// } else {
		// listGarante = new ArrayList();
		// log.info("Cantidad de Garantes: " + listGarante.size());
		// if (listGarante.size() == 0) {
		// guardarSolicitud();
		// String nroSolicitudAuxiliar = nroSolicitud;
		// nuevaSolicitud();
		// yaExiste = false;
		// habilitada = true;
		// solicitudIndividuo.setIdSolicitudIndividuo(null);
		// solicitudIndividuo.setTipoIndividuo(new TipoIndividuo(new Long(2)));
		// origen = 2;
		// mostarElementos(2);
		// nroSolicitud = nroSolicitudAuxiliar;
		// return "amIndividuo";
		// } else {
		// error.agregar(Error.EVA_INDIVIDUO_ALTA_GARANTE);
		// }
		// }
		return null;
	}


	public String altaAdicional() {
		log.info("Ejecutando alta Adicional()");
		// if (!listAdicional.isEmpty()) {
		if (listAdicional.size() < 3) {
			guardarSolicitud();
			mostrarEducacion = false;
			alta = true;
			String nroSolicitudAuxiliar = nroSolicitud;
			nuevaSolicitud();
			yaExiste = false;
			habilitada = true;
			solicitudIndividuo.setTipoIndividuo(new TipoIndividuo(new Long(3)));
			origen = 3;
			cantAdicionales++;
			mostarElementos(3);
			nroSolicitud = nroSolicitudAuxiliar;
			return "amIndividuo";
		} else {
			error.agregar(Error.EVA_INDIVIDUO_ALTA_ADICIONAL);
		}
		// } else {
		// listAdicional = new ArrayList();
		// log.info("Cantidad Adicionales: " + listAdicional.size());
		// if (listAdicional.size() < 3) {
		// String nroSolicitudAuxiliar = nroSolicitud;
		// nuevaSolicitud();
		// yaExiste = false;
		// habilitada = true;
		// solicitudIndividuo.setTipoIndividuo(new TipoIndividuo(new Long(3)));
		// origen = 3;
		// cantAdicionales++;
		// mostarElementos(3);
		// nroSolicitud = nroSolicitudAuxiliar;
		// return "amIndividuo";
		// } else {
		// error.agregar(Error.EVA_INDIVIDUO_ALTA_ADICIONAL);
		// }
		// }
		return null;
	}


	/*
	 * Este metodo se utiliza para poder almacenar un archivo digital en un directorio predefinido. Primero creamos la ruta donde se va a almacenar el
	 * archivo.
	 */
	public String saveFile() {
		log.info("Ejecutando ==> saveFile()");
		String path;

		if (idTipoDocSeleccionado != null && !idTipoDocSeleccionado.equals(new Long(0))) {
			path = "No grabo";
			Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
			try {

				String pathAux = "";
				char aux[] = Archivo.archivosDeIndividuos.toCharArray();
				for (int i = 0; i < 10; i++)
				{
					pathAux += aux[i];
				}
				int año = timestamp.getYear() + 1900;
				pathAux += "/" + año + "-";
				int mes = timestamp.getMonth() + 1;
				if (mes < 10) // Si el mes tiene un solo digito, se le agrega un cero a la izquierda.
				{
					pathAux += "0" + mes;
				}
				else
					pathAux += +mes;
				path = Archivo.grabarArchivo(uploadedFile.getInputStream(), uploadedFile.getName(), new Long(uploadedFile.getSize()).intValue(),
						pathAux);
				// }

			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (NullPointerException e2) {
				e2.printStackTrace();
			}
			if (path != null && !path.equals("No grabo")) {

				WrapperFile wrapperFile = new WrapperFile(null, idTipoDocSeleccionado, path, docDigital.getDescripcion(), path, timestamp);
				wrapperFile.setIdWrappers(new Long(wrapperFile.hashCode()));
				wrapperFile.setListaDocumentos(listAuxTipoDigital);
				listTipoDocumentos.add(wrapperFile);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				String javaScriptText = "window.opener.recargar();";
				AddResource addResource = AddResourceFactory.getInstance(facesContext);
				addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
			}

		} else {
			error.agregar(Error.TIPO_DOCUMENTO_ADJUNTO_REQUERIDO);
		}
		return null;
	}


	public String abrirArchivo() {
		WrapperFile element = null;
		Long idAr = new Long(Session.getRequestParameter("idArchiAbrir"));
		Iterator iterFile = listTipoDocumentos.iterator();
		while (iterFile.hasNext()) {
			WrapperFile fi = (WrapperFile) iterFile.next();
			if (fi.getIdWrappers().equals(idAr)) {
				element = fi;
				break;
			}
		}

		try {
			String url = element.getNombreArchivo();
			Date fecha = new Date(element.getTimestamp().getTime());
			Calendar calendar = Calendar.getInstance();
			calendar.set(2015, 9, 5);
			Date fechaControl = calendar.getTime();

			// Obtiene el parámetro del sistema id 28 (fecha1)
			ParametroSistemaDetalle parametroSistemaDetalle = generalService.getParametroSistemaDetalleService().leerParametroSistemaDetalle(28L);
			char[] fechaAux = parametroSistemaDetalle.getValor().toCharArray();
			String diaAux = "" + fechaAux[0] + fechaAux[1];
			String mesAux = "" + fechaAux[3] + fechaAux[4];
			String añoAux = "" + fechaAux[6] + fechaAux[7] + fechaAux[8] + fechaAux[9];
			Timestamp fechaTransicionDirectorios = new Timestamp(Integer.parseInt(añoAux) - 1900, Integer.parseInt(mesAux) - 1,
					Integer.parseInt(diaAux), 0, 0, 0, 0);

			String ruta = "";
			int año = element.getTimestamp().getYear() + 1900;
			ruta += año + "-";
			int mes = element.getTimestamp().getMonth() + 1;
			if (mes < 10) // Si el mes tiene un solo digito, se le agrega un cero a la izquierda.
			{
				ruta += "0" + mes;
			}
			else
				ruta += mes;

			if (element.getTimestamp().after(fechaTransicionDirectorios))
			{
				ejecutarJavaScript("popup('" + "/../archivos/individuos/" + ruta + "/" + element.getNombreArchivo() + "',700,400);");
			}
			else
			{
				parametroSistemaDetalle = generalService.getParametroSistemaDetalleService().leerParametroSistemaDetalle(27L);
				String servidor = parametroSistemaDetalle.getValor();
				ejecutarJavaScript("popup('" + "http://192.168.0." + servidor + ":8080/archivos/individuos/" + ruta + "/"
						+ element.getNombreArchivo() + "',700,400);");
			}

		} catch (Exception e) {
			log.info("Error al intentar leer el archivo");
			e.printStackTrace();
		}
		return null;
	}


	public String borrarArchivo() {
		Long idAr = new Long(Session.getRequestParameter("idArchi"));
		WrapperFile fil = null;
		Iterator iterFile = listTipoDocumentos.iterator();
		while (iterFile.hasNext()) {
			WrapperFile fi = (WrapperFile) iterFile.next();
			if (fi.getIdWrappers().equals(idAr)) {
				fil = fi;
				break;
			}
		}
		if (fil != null) {
			listTipoDocumentos.remove(fil);
			if (fil.getIdDocumentoAdjunto() != null) {
				Digital dig = null;
				Iterator it = individuoEvaluacion.getDocAdjuntos().iterator();
				while (it.hasNext()) {
					Digital d = (Digital) it.next();
					if (d.getIdDigital().equals(fil.getIdDocumentoAdjunto())) {
						dig = d;
						break;
					}
				}
				if (dig != null)
					individuoEvaluacion.getDocAdjuntos().remove(dig);
			}
		}
		return "";
	}


	public void calcularAntiguedad(ValueChangeEvent event) {
		log.info("Ejecutando ==> calcularAntiguedad()");
		ingreso = (Date) fechaIngreso.getValue();
		log.info("Fecha Ingreso: " + ingreso);
		antiguedad = IndividuoEvaluacionUtil.devolverCantidadAnios(ingreso) + "";
		setAntiguedad(antiguedad);
	}


	public String gestionarInformeParticular() {

		try {
			if (validarDomicilioParticular()) {
				if (idInformeParticular != null && !idInformeParticular.equals(new Long(0))) {

					if (!lstVerificadores.isEmpty()) {
						Iterator iterator = lstVerificadores.iterator();
						while (iterator.hasNext()) {
							Verificador element = (Verificador) iterator.next();

							if (element.getIdVerificador().equals(idInformeParticular))
								verificador = element;
						}
						informeParticular.setIdInfoParticular(null);
						informeParticular.setSolicitudIndividuo(solicitudIndividuo);
						informeParticular.setVerificador(verificador);
						log.info("El operador de la sesion es: " + operador);
						informeParticular.setIdOperador(operador.getCodigo());
						informeParticular.setTimestamp(new Timestamp(new Date().getTime()));
						evaluacionService.getInformeParticularService().grabarInformeParticular(informeParticular);
						particular = true;
						popup.setPopup(popup.ICONO_OK, "El informe particular se asigno con exito a dicho verificador.");
					}
				} else {
					error.agregar(Error.EVA_INDIVIDUO_VERIFICADOR);
				}
			} else {
				error.agregar(Error.EVA_INDIVIDUO_DOMICILIO_PARTICULAR_REQUERIDO);
			}
		} catch (InformeParticularException e) {
			popup.setPopup(popup.ICONO_FALLA, "No se pudo agregar un verificador para el informe particular.");
			e.printStackTrace();
		} catch (Exception e) {
			popup.setPopup(popup.ICONO_FALLA, "No se pudo agregar un verificador para el informe particular.");
			e.printStackTrace();
		}

		return null;
	}


	public String gestionarInformeLaboral() {
		try {
			if (verificarEmpresaAsociada()) {
				if (idInformeLaboral != null && !idInformeLaboral.equals(new Long(0))) {
					if (!lstVerificadores.isEmpty()) {
						Iterator iterator = lstVerificadores.iterator();
						while (iterator.hasNext()) {
							Verificador element = (Verificador) iterator.next();

							if (element.getIdVerificador().equals(idInformeLaboral))
								verificador = element;
						}
						InformeLaboral informeLaboral = new InformeLaboral();
						informeLaboral.setIdInfoLaboral(null);
						informeLaboral.setSolicitudIndividuo(solicitudIndividuo);
						informeLaboral.setVerificador(verificador);
						informeLaboral.setSucEmpresa(individuoEvaluacion.getActividad().getSucEmpresa());
						informeLaboral.setTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
						informeLaboral.setIdOperador(Session.getOperador().getCodigo());
						evaluacionService.getInformeLaboralService().grabarInformeLaboral(informeLaboral);
						laboral = true;
						popup.setPopup(popup.ICONO_OK, "El informe laboral se asigno con exito a dicho verificador.");
					}
				} else {
					error.agregar(Error.EVA_INDIVIDUO_VERIFICADOR);
				}
			} else {
				error.agregar(Error.EVA_INDIVIDUO_EMPRESA_REQUERIDO);
			}
		} catch (InformeLaboralException e) {
			popup.setPopup(popup.ICONO_ERROR, "No se pudo agregar un verificador para el informe laboral.");
			e.printStackTrace();
		} catch (Exception e) {
			popup.setPopup(popup.ICONO_ERROR, "No se pudo agregar un verificador para el informe laboral.");
			e.printStackTrace();
		}
		return null;
	}


	private boolean verificarEmpresaAsociada() {
		if (individuoEvaluacion.getActividad() != null &&
				individuoEvaluacion.getActividad().getSucEmpresa() != null &&
				individuoEvaluacion.getActividad().getSucEmpresa().getEmpresa() != null &&
				individuoEvaluacion.getActividad().getSucEmpresa().getEmpresa().getCuit() != null) {
			return true;
		}
		return false;
	}


	private boolean validarDomicilioParticular() {
		if (individuoEvaluacion.getDomicilio() != null &&
				!individuoEvaluacion.getDomicilio().equals(new Domicilio()))
			return true;
		return false;
	}


	public String agregarEmpresa() {
		Empresa emp = null;
		log.info("Ir a agregar empresa al individuo!!!");
		EmpresaBean beanEmpresa = (EmpresaBean) Session.getBean("EmpresaBean");
		if (nroCuit != null && !nroCuit.equals("")) {
			try {
				beanEmpresa.setCuitEditado(nroCuit);
				List empresas = generalService.getEmpresaService().getEmpresa(new Filtro("cuit", Filtro.IGUAL, nroCuit));
				if (!empresas.isEmpty()) {
					Iterator iterEmp = empresas.iterator();
					while (iterEmp.hasNext()) {
						Empresa empresita = (Empresa) iterEmp.next();
						if (empresita.getCuit().equals(new Long(nroCuit))) {
							emp = empresita;
							Iterator iter = emp.getSucEmpresas().iterator();
							while (iter.hasNext()) {
								SucEmpresa sucEmpresa = (SucEmpresa) iter.next();
								leerDomicilio(sucEmpresa.getDomicilio());
								if (sucEmpresa.getAutonomo() != null)
									sucEmpresa.getAutonomo().getDescripcion();

								Iterator it = sucEmpresa.getSucTelefonos().iterator();
								while (it.hasNext()) {
									SucTelefono tel = (SucTelefono) it.next();
									tel.getTelefono().getTipo().getDescripcion();
								}
								Iterator itDos = sucEmpresa.getSucEmails().iterator();
								while (itDos.hasNext()) {
									SucEmail email = (SucEmail) itDos.next();
									email.getEmail().getEmail();
									email.getIdSucEmail();
								}
								Iterator itTres = sucEmpresa.getSucEmpresaXDomicilio().iterator();
								while (itTres.hasNext()) {
									SucEmpresasXDomicilio sucEmpresasXDomicilio = (SucEmpresasXDomicilio) itTres.next();
									leerDomicilio(sucEmpresasXDomicilio.getDomicilio());
								}
							}
							break;
						}
					}
				}
			} catch (EmpresaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassCastException e2) {
				e2.printStackTrace();
			}
			if (emp != null) {
				beanEmpresa.inicializarDesdePopup(emp);
			} else {
				Empresa empr = new Empresa();
				empr.setCuit(new Long(nroCuit));
				empr.setEsRiesgoza(Character.valueOf('N'));
				empr.setSucEmpresas(new HashSet());
				empr.setRubro(new Rubro());
				empr.setTamEmpresa(new TamEmpresa(new Long(0)));
				beanEmpresa.inicializarDesdePopup(empr);
			}

		}
		// else {
		// beanEmpresa.inicializar();
		// }
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/empresa/empresaPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',900,800), 'titlebar=no';");
		return null;
	}


	private void leerDomicilio(Domicilio domi) {
		if (domi.getBarrio() != null
				&& domi.getBarrio().getLocalidad() != null
				&& domi.getBarrio().getLocalidad().getPartido() != null
				&& domi.getBarrio().getLocalidad().getPartido().getProvincia() != null
				&& domi.getBarrio().getLocalidad().getPartido().getProvincia().getPais() != null
				&& domi.getBarrio().getLocalidad().getPartido().getProvincia().getPais().getIdPais() != null)
			domi.getLocalidad().getNombre();
		if (domi.getTipoDomicilio() != null
				&& domi.getTipoDomicilio().getIdTipoDomicilio() != null)
			;
		if (domi.getTipoVivienda() != null
				&& domi.getTipoVivienda().getIdTipoVivienda() != null)
			;
		if (domi.getPropVivienda() != null
				&& domi.getPropVivienda().getIdPropVivienda() != null)
			;
	}


	public String buscarIndividuo() {

		if (buscarIndividuoPorCuit() != null) {
			individuoEvaluacion = buscarIndividuoPorCuit();
		}
		return null;
	}


	public IndividuoEvaluacion buscarIndividuoPorCuit() {
		IndividuoEvaluacion result = null;
		try {
			if (nroCuit != null && !nroCuit.equals("")
					&& nroCuit.length() == 11) {
				if (validarCuit(nroCuit)) {

					List individuos = evaluacionService
							.getIndividuoEvaluacionService().getIndividuo(
									new Filtro("cuil", Filtro.LIKEXS, nroCuit));
					if (!individuos.isEmpty()) {
						Iterator iterator = individuos.iterator();
						while (iterator.hasNext()) {
							IndividuoEvaluacion element = (IndividuoEvaluacion) iterator
									.next();
							result = new IndividuoEvaluacion();
							result = element;
						}
					}
				}
			}
		} catch (IndividuoEvaluacionException e) {
			e.printStackTrace();
		}
		return result;
	}


	public String editarGarante() {
		guardarSolicitud();
		Long numeroSol = null;
		List solicitudesIndiv = new ArrayList();
		solicitudesIndiv = Convertidores.setToList(solicitud.getSolicIndividuos());
		if (!solicitudesIndiv.isEmpty()) {
			Iterator iterator = solicitudesIndiv.iterator();
			while (iterator.hasNext()) {
				solicitudIndividuo = (SolicitudIndividuo) iterator.next();
				if (solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(2))) {
					numeroSol = solicitudIndividuo.getIdSolicIndividuo();
					log.info("SolicitudIndividuoEncontrada");
					break;
				}
			}
		}
		vista = 1;
		individuoEvaluacion = solicitudIndividuo.getIndividuoEvaluacion();
		inicializar(2, nroSolicitud, numeroSol);
		Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");
		return null;
	}


	public void eliminarGarante() {
		List solicitudesIndiv = new ArrayList();
		solicitudesIndiv = Convertidores.setToList(solicitud.getSolicIndividuos());
		if (!solicitudesIndiv.isEmpty()) {
			Iterator iterator = solicitudesIndiv.iterator();
			while (iterator.hasNext()) {
				solicitudIndividuo = (SolicitudIndividuo) iterator.next();
				if (solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(2))) {
					log.info("SolicitudIndividuoEncontrada");
					break;
				}
			}
		}
		listGarante = new ArrayList();
		try {
			solicitudIndividuo.setActivo("N");
			evaluacionService.getSolicitudIndividuoService().actualizarSolicitudIndividuo(solicitudIndividuo);
		} catch (SolicitudIndividuoException e) {
			e.printStackTrace();
		}
	}


	public void eliminarAdicional() {
		Long idAdicional = new Long(Session.getRequestParameter("idAdicionalElim"));
		log.info("id a buscar: " + idAdicional);
		List solicitudesIndiv = new ArrayList();
		solicitudesIndiv = Convertidores.setToList(solicitud.getSolicIndividuos());
		if (!solicitudesIndiv.isEmpty()) {
			Iterator iterator = solicitudesIndiv.iterator();
			while (iterator.hasNext()) {
				solicitudIndividuo = (SolicitudIndividuo) iterator.next();
				if (solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo().equals(new Long(3))
						&& solicitudIndividuo.getIndividuoEvaluacion().getIdIndividuo().equals(idAdicional)) {
					log.info("SolicitudIndividuoEncontrada");
					break;
				}
			}
		}
		listAdicional.remove(solicitudIndividuo.getIndividuoEvaluacion());
		try {
			solicitudIndividuo.setActivo("N");
			evaluacionService.getSolicitudIndividuoService().actualizarSolicitudIndividuo(solicitudIndividuo);
		} catch (SolicitudIndividuoException e) {
			e.printStackTrace();
		}
	}


	public String editarAdicional() {
		log.info("Valor de idAdicionalEdi: " + Session.getRequestParameter("idAdicionalEdi"));
		Long idAdicional = new Long(Session.getRequestParameter("idAdicionalEdi"));
		guardarSolicitud();
		log.info("id a buscar: " + idAdicional);
		List solicitudesIndiv = new ArrayList();
		solicitudesIndiv = Convertidores.setToList(solicitud.getSolicIndividuos());
		if (!solicitudesIndiv.isEmpty()) {
			Iterator iterator = solicitudesIndiv.iterator();
			while (iterator.hasNext()) {
				solicitudIndividuo = (SolicitudIndividuo) iterator.next();
				if (solicitudIndividuo.getIndividuoEvaluacion().getIdIndividuo().equals(idAdicional)) {
					log.info("SolicitudIndividuoEncontrada" + idAdicional);
					break;
				}
			}
		}
		individuoEvaluacion = solicitudIndividuo.getIndividuoEvaluacion();
		vista = 1;
		inicializar(3, nroSolicitud, solicitudIndividuo.getIdSolicIndividuo());
		Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");
		return null;
	}


	public String buscadorDeIndividuo() {
		error.borrar();
		if (validarCuit(cuilBuscador)) {
			try {
				List listaDeIndividuosExis = new ArrayList();
				listaDeIndividuosExis = evaluacionService.getIndividuoEvaluacionService().getIndividuo(
						new Filtro("cuil", Filtro.LIKEXS, cuilBuscador));
				if (!listaDeIndividuosExis.isEmpty()) {
					IndividuoEvaluacion in = (IndividuoEvaluacion) listaDeIndividuosExis.get(0);
					apel = in.getApellido();
					nom = in.getNombres();
					habilitarCarga = true;
				} else {
					alta = true;
					habilitarCarga = true;
					recargarYCerrarPopup();
				}
			} catch (IndividuoEvaluacionException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	public String recargarYCerrarPopup() {
		traerIndividuo();
		if (error.cantidad() == 0) {
			// resultado = "amIndividuos";
			// FacesContext facesContext = FacesContext.getCurrentInstance();
			// String javaScriptText = "window.close();submit();";
			// ejecutarJavaScript("window.close();submit();");
			// AddResource addResource = AddResourceFactory.getInstance(facesContext);
			// addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
			// Session.redirect("/tarjetafiel/evaluacion/amIndividuo.jsf");
			return buscarOtroIndividuo();
		}
		return null;
	}


	public String buscarSiIndividuoExiste() {
		if (!alta) {
			try {
				SolicitudIndividuo sol = evaluacionService.getSolicitudIndividuoService().leerSolicitudIndividuo(
						solicitudIndividuo.getIdSolicIndividuo());
				if (sol != null
						&& sol.getInfoLaborales() != null
						&& sol.getInfoLaborales().size() > 0
						&& sol.getInfoParticulares() != null
						&& sol.getInfoParticulares().size() > 0) {
					error.agregar(Error.EVA_NO_SE_PUEDE_EDITAR_EL_INDIVIDUO);
				} else {
					verCargaIndividuo = true;
					Session.redirect("/tarjetafiel/evaluacion/popup/buscarIndividuo.jsf");
				}

			} catch (SolicitudIndividuoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			verCargaIndividuo = true;
			Session.redirect("/tarjetafiel/evaluacion/popup/buscarIndividuo.jsf");
		}
		// String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		// path += "/tarjetafiel/evaluacion/popup/buscarIndividuo.jsf";
		// ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		// boolean salto = true;
		// if (cuil!=null && !cuil.equals("")&&cuil.length()==11&&validarCuit(nroCuit)) {
		// filtro = new Filtro();
		// filtro.agregarCampoOperValor("cuil", Filtro.IGUAL, cuil);
		// IndividuoEvaluacion indEva = null;
		// try {
		// List listaInd = evaluacionService.getIndividuoEvaluacionService().getIndividuo(filtro);
		// if (listaInd!=null&&listaInd.size()>0) {
		// indEva = (IndividuoEvaluacion) listaInd.get(0);
		// if (indEva!=null) {
		// solicitudIndividuo.setActivo("S");
		// solicitudIndividuo.setIdSolicitudIndividuo(null);
		// log.info("Buscando la solicitud nro: "+nroSolicitud);
		// filtro = new Filtro("nroSolicitud", Filtro.LIKEXS, nroSolicitud.substring(0, 7));
		// solicitud = null;
		// log.info("Ejecutando la consulta a la base de datos");
		// solicitud = evaluacionService.getSolicitudService().busNroComprobante(filtro);
		// if (solicitud!=null) {
		// log.info("Actualizando una solicitud");
		// solicitudIndividuo.setSolicitud(solicitud);
		// solicitudIndividuo.setIndividuoEvaluacion(indEva);
		// solicitudIndividuo.setTipoIndividuo(evaluacionService.getTipoIndividuoService().leerTipoIndividuo(new Long(origen)));
		// String tipoDe = "";
		// if (origen==1) tipoDe= "titular";
		// if (origen==2) tipoDe= "garante";
		// if (origen==3) tipoDe= "adicional";
		// evaluacionService.getSolicitudIndividuoService().grabarSolicitudIndividuo(solicitudIndividuo);
		// }
		// alta = false;
		// }
		// }else {
		// String cuilAx = cuil;
		// int oriAuxi = origen;
		// String nroSolicitudAuxiliar = nroSolicitud;
		// nuevaSolicitud();
		// //cargarDatosMinimosNecesarios();
		// yaExiste = false;
		// habilitada = true;
		// solicitudIndividuo.setIdSolicitudIndividuo(null);
		// origen = oriAuxi;
		// solicitudIndividuo.setTipoIndividuo(new TipoIndividuo(new Long(origen)));
		// mostarElementos(origen);
		// nroSolicitud = nroSolicitudAuxiliar;
		// focoHidden = "inApellido";
		// cuil = cuilAx;
		// yaExiste = true;
		// habilitada = false;
		// salto = false;
		// return "amIndividuo";
		//
		//
		// }
		// } catch (IndividuoEvaluacionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (SolicitudException e2) {
		// e2.printStackTrace();
		// } catch (SolicitudIndividuoException e3) {
		// e3.printStackTrace();
		// } catch (TipoIndividuoException e4) {
		// e4.printStackTrace();
		// }
		// if (salto) {
		// if (origen!=3) {
		// inicializar(origen,nroSolicitud,null);
		// } else {
		// inicializar(origen,nroSolicitud,solicitudIndividuo.getIdSolicIndividuo());
		// }
		// }
		// }
		// else {
		// error.agregar("El número de cuil ingresado no es correcto");
		// }
		return null;
	}


	public IndividuoDomicilio getIndividuoDomicilio() {
		return individuoDomicilio;
	}


	public void setIndividuoDomicilio(IndividuoDomicilio individuoDomicilio) {
		this.individuoDomicilio = individuoDomicilio;
	}


	public boolean getEsCBU() {
		if (esCBU) {
			setIdBcoSeleccionado(new Long(0));
			idTipoCuentaSeleccionado = new Long(0);
			nombreBanco = "";
			textBanco.setValue("");
			magia.setComponente20(esCBU);
		} else {
			nombreBanco = bancos.getBanco().getDescripcion();
			magia.setComponente20(esCBU);
		}

		return esCBU;
	}


	public void setEsCBU(boolean esCBU) {
		this.esCBU = esCBU;

	}


	public Estados getEstados() {
		return estados;
	}


	public void setEstados(Estados estados) {
		this.estados = estados;
	}


	public Long getIdDomicilioPagoSeleccionado() {
		return idDomicilioPagoSeleccionado;
	}


	public void setIdDomicilioPagoSeleccionado(Long idDomicilioPagoSeleccionado) {
		this.idDomicilioPagoSeleccionado = idDomicilioPagoSeleccionado;
	}


	public Long getIdTipoCuentaSeleccionado() {
		return idTipoCuentaSeleccionado;
	}


	public void setIdTipoCuentaSeleccionado(Long idTipoCuentaSeleccionado) {
		this.idTipoCuentaSeleccionado = idTipoCuentaSeleccionado;
	}


	public List getListAuxTipoCtas() {
		return listAuxTipoCtas;
	}


	public void setListAuxTipoCtas(List listAuxTipoCtas) {
		this.listAuxTipoCtas = listAuxTipoCtas;
	}


	public Long getIdTipoSexoSeleccionado() {
		return idTipoSexoSeleccionado;
	}


	public void setIdTipoSexoSeleccionado(Long idTipoSexoSeleccionado) {
		this.idTipoSexoSeleccionado = idTipoSexoSeleccionado;
	}


	public Long getIdTipoTelSeleccionado() {
		return idTipoTelSeleccionado;
	}


	public void setIdTipoTelSeleccionado(Long idTipoTelSeleccionado) {
		this.idTipoTelSeleccionado = idTipoTelSeleccionado;
	}


	public IndividuoEvaluacion getIndividuoEvaluacion() {
		return individuoEvaluacion;
	}


	public void setIndividuoEvaluacion(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}


	public List getListBancos() {
		return listBancos;
	}


	public void setListBancos(List listBancos) {
		this.listBancos = listBancos;
	}


	public List getListDiaPago() {
		return listDiaPago;
	}


	public void setListDiaPago(List listDiaPago) {
		this.listDiaPago = listDiaPago;
	}


	public List getListDomicilio() {
		return listDomicilio;
	}


	public void setListDomicilio(List listDomicilio) {
		this.listDomicilio = listDomicilio;
	}


	public List getListEducacion() {
		return listEducacion;
	}


	public void setListEducacion(List listEducacion) {
		this.listEducacion = listEducacion;
	}


	public List getListEstadoCivil() {
		return listEstadoCivil;
	}


	public void setListEstadoCivil(List listEstadoCivil) {
		this.listEstadoCivil = listEstadoCivil;
	}


	public List getListProfesion() {
		return listProfesion;
	}


	public void setListProfesion(List listProfesion) {
		this.listProfesion = listProfesion;
	}


	public List getListTelefono() {
		return listTelefono;
	}


	public void setListTelefono(List listTelefono) {
		this.listTelefono = listTelefono;
	}


	public List getListTipoCtas() {
		return listTipoCtas;
	}


	public void setListTipoCtas(List listTipoCtas) {
		this.listTipoCtas = listTipoCtas;
	}


	public List getListTipoDni() {
		return listTipoDni;
	}


	public void setListTipoDni(List listTipoDni) {
		this.listTipoDni = listTipoDni;
	}


	public List getListTipoSexo() {
		return listTipoSexo;
	}


	public void setListTipoSexo(List listTipoSexo) {
		this.listTipoSexo = listTipoSexo;
	}


	public List getListVinculo() {
		return listVinculo;
	}


	public void setListVinculo(List listVinculo) {
		this.listVinculo = listVinculo;
	}


	public boolean isMostrarCbu() {
		// log.info("Ejecutando ==>getMostrarCBU(). mostrarCbu: " + mostrarCbu);
		if (esCBU) {
			setIdBcoSeleccionado(new Long(0));
			idTipoCuentaSeleccionado = new Long(0);
			nombreBanco = "";
			textBanco.setValue("");
			magia.setComponente20(esCBU);
		}
		nombreBanco = bancos.getBanco().getDescripcion();
		return esCBU;
	}


	public String getNroCBU() {
		return nroCBU;
	}


	public void setNroCBU(String nroCBU) {
		this.nroCBU = nroCBU;
	}


	public String getNroSolicitud() {
		return nroSolicitud;
	}


	public void setNroSolicitud(String nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}


	public String getNroCuenta() {
		return nroCuenta;
	}


	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}


	public boolean getOpcionA() {
		return opcionA;
	}


	public void setOpcionA(boolean opcionA) {
		this.opcionA = opcionA;
	}


	public Promotor getPromotor() {
		return promotor;
	}


	public void setPromotor(Promotor promotor) {
		this.promotor = promotor;
	}


	public Solicitud getSolicitud() {
		return solicitud;
	}


	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}


	public String getTituloPanleCinco() {
		return tituloPanleCinco;
	}


	public void setTituloPanleCinco(String tituloPanleCinco) {
		this.tituloPanleCinco = tituloPanleCinco;
	}


	public String getTituloPanleCuatro() {
		return tituloPanleCuatro;
	}


	public void setTituloPanleCuatro(String tituloPanleCuatro) {
		this.tituloPanleCuatro = tituloPanleCuatro;
	}


	public String getTituloPanleDos() {
		return tituloPanleDos;
	}


	public void setTituloPanleDos(String tituloPanleDos) {
		this.tituloPanleDos = tituloPanleDos;
	}


	public String getTituloPanleSeis() {
		return tituloPanleSeis;
	}


	public void setTituloPanleSeis(String tituloPanleSeis) {
		this.tituloPanleSeis = tituloPanleSeis;
	}


	public String getTituloPanleTres() {
		return tituloPanleTres;
	}


	public void setTituloPanleTres(String tituloPanleTres) {
		this.tituloPanleTres = tituloPanleTres;
	}


	public String getTituloPanleUno() {
		return tituloPanleUno;
	}


	public void setTituloPanleUno(String tituloPanleUno) {
		this.tituloPanleUno = tituloPanleUno;
	}


	public String getTituloPanleSiete() {
		return tituloPanleSiete;
	}


	public void setTituloPanleSiete(String tituloPanleSiete) {
		this.tituloPanleSiete = tituloPanleSiete;
	}


	public Long getIdOcupacionSeleccionado() {
		return idOcupacionSeleccionado;
	}


	public void setIdOcupacionSeleccionado(Long idOcupacionSeleccionado) {
		this.idOcupacionSeleccionado = idOcupacionSeleccionado;
	}


	public List getListOcupacion() {
		return listOcupacion;
	}


	public void setListOcupacion(List listOcupacion) {
		this.listOcupacion = listOcupacion;
	}


	public String getNombrePromotor() {
		return nombrePromotor;
	}


	public String getTituloPanleOcho() {
		return tituloPanleOcho;
	}


	public void setTituloPanleOcho(String tituloPanleOcho) {
		this.tituloPanleOcho = tituloPanleOcho;
	}


	public Bancos getBancos() {
		return bancos;
	}


	public void setBancos(Bancos bancos) {
		this.bancos = bancos;
	}


	public CuitValido getCuitValido() {
		return cuitValido;
	}


	public void setCuitValido(CuitValido cuitValido) {
		this.cuitValido = cuitValido;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public boolean getHabilitarProfesion() {
		return habilitarProfesion;
	}


	public void setHabilitarProfesion(boolean habilitarProfesion) {
		this.habilitarProfesion = habilitarProfesion;
	}


	public void setNombrePromotor(String nombrePromotor) {
		this.nombrePromotor = nombrePromotor;
	}


	public String getNroCuit() {
		return nroCuit;
	}


	public void setNroCuit(String nroCuit) {
		this.nroCuit = nroCuit;
	}


	public Long getIdSucursalSeleccionado() {
		return idSucursalSeleccionado;
	}


	public void setIdSucursalSeleccionado(Long idSucursalSeleccionado) {
		this.idSucursalSeleccionado = idSucursalSeleccionado;
	}


	public List getListSucEmp() {
		return listSucEmp;
	}


	public void setListSucEmp(List listSucEmp) {
		this.listSucEmp = listSucEmp;
	}


	public List getListSucursales() {
		// log.info("Ejecutando ==> listaSucursales()");
		// log.info("Size: " + listSucursales.size());
		return listSucursales;
	}


	public void setListSucursales(List listSucursales) {
		this.listSucursales = listSucursales;
	}


	public boolean isHabilitarSucursal() {
		return habilitarSucursal;
	}


	public void setHabilitarSucursal(boolean habilitarSucursal) {
		this.habilitarSucursal = habilitarSucursal;
	}


	public SucEmpresa getSucEmpresa() {
		return sucEmpresa;
	}


	public void setSucEmpresa(SucEmpresa sucEmpresa) {
		this.sucEmpresa = sucEmpresa;
	}


	public HtmlSelectOneMenu getSucursalHtml() {
		return sucursalHtml;
	}


	public void setSucursalHtml(HtmlSelectOneMenu sucursalHtml) {
		this.sucursalHtml = sucursalHtml;
	}


	public List getListDomicilioInmueble() {
		return listDomicilioInmueble;
	}


	public void setListDomicilioInmueble(List listDomicilioInmueble) {
		this.listDomicilioInmueble = listDomicilioInmueble;
	}


	public HtmlSelectOneMenu getProfesionHtml() {
		return profesionHtml;
	}


	public void setProfesionHtml(HtmlSelectOneMenu profesionHtml) {
		this.profesionHtml = profesionHtml;
	}


	public String getTituloPanleNueve() {
		return tituloPanleNueve;
	}


	public void setTituloPanleNueve(String tituloPanleNueve) {
		this.tituloPanleNueve = tituloPanleNueve;
	}


	public Long getIdBancoSeleccionado() {
		return idBancoSeleccionado;
	}


	public void setIdBancoSeleccionado(Long idBancoSeleccionado) {
		this.idBancoSeleccionado = idBancoSeleccionado;
	}


	public Long getIdTarjetaSeleccionado() {
		return idTarjetaSeleccionado;
	}


	public void setIdTarjetaSeleccionado(Long idTarjetaSeleccionado) {
		this.idTarjetaSeleccionado = idTarjetaSeleccionado;
	}


	public List getListTarjetas() {
		return listTarjetas;
	}


	public void setListTarjetas(List listTarjetas) {
		this.listTarjetas = listTarjetas;
	}


	public String getTituloPanleDiez() {
		return tituloPanleDiez;
	}


	public void setTituloPanleDiez(String tituloPanleDiez) {
		this.tituloPanleDiez = tituloPanleDiez;
	}


	public String getTituloPanleOnce() {
		return tituloPanleOnce;
	}


	public void setTituloPanleOnce(String tituloPanleOnce) {
		this.tituloPanleOnce = tituloPanleOnce;
	}


	public Long getIdTipoDocSeleccionado() {
		return idTipoDocSeleccionado;
	}


	public void setIdTipoDocSeleccionado(Long idTipoDocSeleccionado) {
		this.idTipoDocSeleccionado = idTipoDocSeleccionado;
	}


	public List getListAdicional() {
		return listAdicional;
	}


	public void setListAdicional(List listAdicional) {
		this.listAdicional = listAdicional;
	}


	public List getListGarante() {
		return listGarante;
	}


	public void setListGarante(List listGarante) {
		this.listGarante = listGarante;
	}


	public List getListTipoDocumentos() {
		return listTipoDocumentos;
	}


	public void setListTipoDocumentos(List listTipoDocumentos) {
		this.listTipoDocumentos = listTipoDocumentos;
	}


	public Long getIdEducacionSeleccionado() {
		return individuoEvaluacion.getEducacion().getIdEducacion();
	}


	public void setIdEducacionSeleccionado(Long idEducacionSeleccionado) {
		individuoEvaluacion.getEducacion().setIdEducacion(
				idEducacionSeleccionado);
	}


	public Long getIdEducSeleccionado() {
		return idEducSeleccionado;
	}


	public void setIdEducSeleccionado(Long idEducSeleccionado) {
		this.idEducSeleccionado = idEducSeleccionado;
	}


	public IndividuoVehiculo getIndividuoVehiculo() {
		return individuoVehiculo;
	}


	public void setIndividuoVehiculo(IndividuoVehiculo individuoVehiculo) {
		this.individuoVehiculo = individuoVehiculo;
	}


	public Long getIdBcoSeleccionado() {
		return idBcoSeleccionado;
	}


	public void setIdBcoSeleccionado(Long idBcoSeleccionado) {

		this.idBcoSeleccionado = idBcoSeleccionado;
		if (this.idBcoSeleccionado != null && !this.idBcoSeleccionado.equals(new Long(0))) {
			if (!listAuxBancos.isEmpty()) {
				Iterator iterator = listAuxBancos.iterator();
				while (iterator.hasNext()) {
					Banco element = (Banco) iterator.next();
					if (element.getIdBanco().equals(idBcoSeleccionado)) {
						textBanco.setValue(element.getDescripcion());
					}
				}
			}
		}
	}


	public String irAVerSolicitudDetalle() {
		SolicitudesBean bean = (SolicitudesBean) Session.getBean("SolicitudesBean");
		bean.setNroSolicitud(Session.getRequestParameter("numSolic"));
		bean.listarSolicitudes();
		bean.setTituloCorto("Listado de Solicitudes");
		Session.redirect("/tarjetafiel/evaluacion/lstSolicitudes.jsf");
		return null;
	}


	public void listaTcliente() {
		if (solicitudIndividuo != null && solicitudIndividuo.getIndividuoEvaluacion() != null &&
				solicitudIndividuo.getIndividuoEvaluacion().getIdIndividuo() != null &&
				!solicitudIndividuo.getIndividuoEvaluacion().getIdIndividuo().equals(new Long(0))) {
			Filtro filtro = new Filtro();
			try {
				filtro.agregarCampoOperValor(CliIndividuo.ID_INDIVIDUO, Filtro.IGUAL, solicitudIndividuo.getIndividuoEvaluacion().getIdIndividuo());
				filtro.agregarCampoOperValor(CliIndividuo.ID_TIPO_INDIVIDUO, Filtro.IGUAL, solicitudIndividuo.getTipoIndividuo().getIdTipoIndividuo());
				List cliIndList = transaccionesService.getCliIndividuoService().getCliIndividuo(filtro);
				if (!cliIndList.isEmpty()) {
					CliIndividuo cliIndividuo = (CliIndividuo) cliIndList.get(0);
					filtro.reset();
					filtro.agregarCampoOperValor("cliente", Filtro.IGUAL, cliIndividuo.getCliente());
					tCliente = evaluacionService.getTclienteService().getTcliente(filtro);
					if (!tCliente.isEmpty()) {
						Iterator iterator = tCliente.iterator();
						while (iterator.hasNext()) {
							Tcliente element = (Tcliente) iterator.next();
							WrappersTcliente wrappersTcliente = new WrappersTcliente(element);
							log.info("cliente: " + element.getCliente());
							listaTcliente.add(wrappersTcliente);
						}
					}

				}
			} catch (CliIndividuoException e) {
			} catch (TclienteException e) {
				e.printStackTrace();
			}
		}
		else {
			error.agregar("Para poder ingresar a ver cliente se necesita cargar un individuo.");
		}
	}


	public String popUpListaTclientes() {
		listaTcliente.clear();
		listaTcliente();
		if (error.cantidad() == 0) {
			String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += "/tarjetafiel/evaluacion/popup/listaTclientes.jsf";
			ejecutarJavaScript("popup('" + path + "',700,300), 'titlebar=no';");
		}

		return "";
	}


	public List getListAuxBancos() {
		return listAuxBancos;
	}


	public void setListAuxBancos(List listAuxBancos) {
		this.listAuxBancos = listAuxBancos;
	}


	public String getNombreBanco() {
		return nombreBanco;
	}


	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}


	public HtmlOutputText getTextBanco() {
		return textBanco;
	}


	public void setTextBanco(HtmlOutputText textBanco) {
		this.textBanco = textBanco;
	}


	public ConfirmacionTelefonica getConfirmacionTelefonica() {
		return confirmacionTelefonica;
	}


	public void setConfirmacionTelefonica(
			ConfirmacionTelefonica confirmacionTelefonica) {
		this.confirmacionTelefonica = confirmacionTelefonica;
	}


	public Tarjeta getTarjeta() {
		return tarjeta;
	}


	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}


	public Digital getDocDigital() {
		return docDigital;
	}


	public void setDocDigital(Digital docDigital) {
		this.docDigital = docDigital;
	}


	public List getListDomicilioPago() {
		return listDomicilioPago;
	}


	public void setListDomicilioPago(List listDomicilioPago) {
		this.listDomicilioPago = listDomicilioPago;
	}


	public String getDireccionSucursal() {
		if (domSucursal != null) {
			direccionSucursal = domSucursal.getCalleNombre() + " "
					+ domSucursal.getCalleNumero();// +
		}
		return direccionSucursal;
	}


	public void setDireccionSucursal(String direccionSucursal) {
		if (domSucursal == null) {
			direccionSucursal = "";
		} else {
			this.direccionSucursal = direccionSucursal;
		}
	}


	public Domicilio getDomSucursal() {
		return domSucursal;
	}


	public void setDomSucursal(Domicilio domSucursal) {
		this.domSucursal = domSucursal;
	}


	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}


	public List getListAuxTipoDigital() {
		return listAuxTipoDigital;
	}


	public void setListAuxTipoDigital(List listAuxTipoDigital) {
		this.listAuxTipoDigital = listAuxTipoDigital;
	}


	public List getListTipoDigital() {
		return listTipoDigital;
	}


	public void setListTipoDigital(List listTipoDigital) {
		this.listTipoDigital = listTipoDigital;
	}


	public boolean isPanelA() {
		return panelA;
	}


	public void setPanelA(boolean panelA) {
		this.panelA = panelA;
	}


	public boolean isPanelB() {
		return panelB;
	}


	public void setPanelB(boolean panelB) {
		this.panelB = panelB;
	}


	public boolean isPanelC() {
		return panelC;
	}


	public void setPanelC(boolean panelC) {
		this.panelC = panelC;
	}


	public boolean isPanelD() {
		return panelD;
	}


	public void setPanelD(boolean panelD) {
		this.panelD = panelD;
	}


	public boolean isPanelE() {
		return panelE;
	}


	public void setPanelE(boolean panelE) {
		this.panelE = panelE;
	}


	public String getTituloPanleDoce() {
		return tituloPanleDoce;
	}


	public void setTituloPanleDoce(String tituloPanleDoce) {
		this.tituloPanleDoce = tituloPanleDoce;
	}


	public boolean isPanelF() {
		return panelF;
	}


	public void setPanelF(boolean panelF) {
		this.panelF = panelF;
	}


	public boolean isPanelG() {
		return panelG;
	}


	public void setPanelG(boolean panelG) {
		this.panelG = panelG;
	}


	public String getTelefono() {
		if (sucTelefono != null) {
			return sucTelefono.getTelefono().getCodArea()
					+ " - " + sucTelefono.getTelefono().getNroTlefono()
					+ " int. " + sucTelefono.getTelefono().getNroInterno();
		} else {
			return "";
		}
	}


	// public void setTelefono(String telefono) {
	// if (sucTelefono==null) {
	// telefono = "";
	// } else {
	// this.telefono = telefono;
	// }
	// }

	public SucTelefono getSucTelefono() {
		return sucTelefono;
	}


	public void setSucTelefono(SucTelefono sucTelefono) {
		this.sucTelefono = sucTelefono;
	}


	public boolean isHabilitarNroSolicitud() {
		return habilitarNroSolicitud;
	}


	public void setHabilitarNroSolicitud(boolean habilitarNroSolicitud) {
		this.habilitarNroSolicitud = habilitarNroSolicitud;
	}


	public int getOrigen() {
		return origen;
	}


	public void setOrigen(int origen) {
		this.origen = origen;
	}


	public String getTituloPanleTrece() {
		return tituloPanleTrece;
	}


	public void setTituloPanleTrece(String tituloPanleTrece) {
		this.tituloPanleTrece = tituloPanleTrece;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public SolicitudIndividuo getSolicitudIndividuo() {
		return solicitudIndividuo;
	}


	public void setSolicitudIndividuo(SolicitudIndividuo solicitudIndividuo) {
		this.solicitudIndividuo = solicitudIndividuo;
	}


	public List getListAuxEstadoCivil() {
		return listAuxEstadoCivil;
	}


	public void setListAuxEstadoCivil(List listAuxEstadoCivil) {
		this.listAuxEstadoCivil = listAuxEstadoCivil;
	}


	public List getListAuxEducacion() {
		return listAuxEducacion;
	}


	public void setListAuxEducacion(List listAuxEducacion) {
		this.listAuxEducacion = listAuxEducacion;
	}


	public List getListAuxProfesion() {
		return listAuxProfesion;
	}


	public void setListAuxProfesion(List listAuxProfesion) {
		this.listAuxProfesion = listAuxProfesion;
	}


	public List getListAuxVinculo() {
		return listAuxVinculo;
	}


	public void setListAuxVinculo(List listAuxVinculo) {
		this.listAuxVinculo = listAuxVinculo;
	}


	public Long getIdPartidoSeleccionado() {
		return idPartidoSeleccionado;
	}


	public void setIdPartidoSeleccionado(Long idPartidoSeleccionado) {
		this.idPartidoSeleccionado = idPartidoSeleccionado;
	}


	public List getListAuxDiaPago() {
		return listAuxDiaPago;
	}


	public void setListAuxDiaPago(List listAuxDiaPago) {
		this.listAuxDiaPago = listAuxDiaPago;
	}


	public List getListAuxDomicilioPago() {
		return listAuxDomicilioPago;
	}


	public void setListAuxDomicilioPago(List listAuxDomicilioPago) {
		this.listAuxDomicilioPago = listAuxDomicilioPago;
	}


	public Long getTipoDocumento() {
		return individuoEvaluacion.getTipoDocumento().getIdTipoDocumento();
	}


	public void setTipoDocumento(Long idTipoDocumento) {
		individuoEvaluacion.getTipoDocumento().setIdTipoDocumento(
				idTipoDocumento);
	}


	public Long getEstadoCivil() {
		return individuoEvaluacion.getEstadoCivil().getIdEstadoCivil();
	}


	public void setEstadoCivil(Long estadoCivil) {
		individuoEvaluacion.getEstadoCivil().setIdEstadoCivil(estadoCivil);
	}


	public Long getProfesion() {
		return individuoEvaluacion.getProfesion().getIdProfesion();
	}


	public void setProfesion(Long profesion) {
		individuoEvaluacion.getProfesion().setIdProfesion(profesion);
	}


	public Long getVinculo() {
		return individuoEvaluacion.getVinculo().getIdVinculo();
	}


	public void setVinculo(Long vinculo) {
		individuoEvaluacion.getVinculo().setIdVinculo(vinculo);
	}


	public Long getDiaPago() {
		return individuoEvaluacion.getDiaPago().getIdDiaPago();
	}


	public void setDiaPago(Long diaPago) {
		individuoEvaluacion.getDiaPago().setIdDiaPago(diaPago);
	}


	public Long getIdDiaPagoSeleccionado() {
		return idDiaPagoSeleccionado;
	}


	public void setIdDiaPagoSeleccionado(Long idDiaPagoSeleccionado) {
		this.idDiaPagoSeleccionado = idDiaPagoSeleccionado;
	}


	public boolean isPanelH() {
		return panelH;
	}


	public void setPanelH(boolean panelH) {
		this.panelH = panelH;
	}


	public boolean isPanelI() {
		return panelI;
	}


	public void setPanelI(boolean panelI) {
		this.panelI = panelI;
	}


	public boolean isPanelJ() {
		return panelJ;
	}


	public void setPanelJ(boolean panelJ) {
		this.panelJ = panelJ;
	}


	public boolean isPanelK() {
		return panelK;
	}


	public void setPanelK(boolean panelK) {
		this.panelK = panelK;
	}


	public HtmlInputCalendar getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(HtmlInputCalendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public Emails getEmails() {
		return emails;
	}


	public void setEmails(Emails emails) {
		this.emails = emails;
	}


	public String getCuil() {
		return cuil;
	}


	public void setCuil(String cuil) {
		this.cuil = cuil;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public void setDiaPago(DiaPago diaPago) {
		this.diaPago = diaPago;
	}


	public Set getDomicilios() {
		return domicilios;
	}


	public void setDomicilios(Set domicilios) {
		this.domicilios = domicilios;
	}


	public Vehiculo getVehiculo() {
		return vehiculo;
	}


	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}


	public List getListAuxOcupacion() {
		return listAuxOcupacion;
	}


	public void setListAuxOcupacion(List listAuxOcupacion) {
		this.listAuxOcupacion = listAuxOcupacion;
	}


	public IndividuoDomicilio getGaranteDomicilio() {
		return garanteDomicilio;
	}


	public void setGaranteDomicilio(IndividuoDomicilio garanteDomicilio) {
		this.garanteDomicilio = garanteDomicilio;
	}


	public IndividuoDomicilio getTitularDomicilio() {
		return titularDomicilio;
	}


	public void setTitularDomicilio(IndividuoDomicilio titularDomicilio) {
		this.titularDomicilio = titularDomicilio;
	}


	public IndividuoVehiculo getGaranteVehiculo() {
		return garanteVehiculo;
	}


	public void setGaranteVehiculo(IndividuoVehiculo garanteVehiculo) {
		this.garanteVehiculo = garanteVehiculo;
	}


	public IndividuoVehiculo getTitularVehiculo() {
		return titularVehiculo;
	}


	public void setTitularVehiculo(IndividuoVehiculo titularVehiculo) {
		this.titularVehiculo = titularVehiculo;
	}


	public boolean isVehiculoPropio() {
		return vehiculoPropio;
	}


	public void setVehiculoPropio(boolean vehiculoPropio) {
		this.vehiculoPropio = vehiculoPropio;
	}


	public DavidCopperfield getMagia() {
		return magia;
	}


	public void setMagia(DavidCopperfield magia) {
		this.magia = magia;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getIdInformeParticular() {
		return idInformeParticular;
	}


	public void setIdInformeParticular(Long idInformeParticular) {
		this.idInformeParticular = idInformeParticular;
	}


	public List getListaVerificadores() {
		return listaVerificadores;
	}


	public void setListaVerificadores(List listaVerificadores) {
		this.listaVerificadores = listaVerificadores;
	}


	public Long getIdInformeLaboral() {
		return idInformeLaboral;
	}


	public void setIdInformeLaboral(Long idInformeLaboral) {
		this.idInformeLaboral = idInformeLaboral;
	}


	public boolean isBotonAdjuntar() {
		return botonAdjuntar;
	}


	public void setBotonAdjuntar(boolean botonAdjuntar) {
		this.botonAdjuntar = botonAdjuntar;
	}


	public boolean isBotonDomicilio() {
		return botonDomicilio;
	}


	public void setBotonDomicilio(boolean botonDomicilio) {
		this.botonDomicilio = botonDomicilio;
	}


	public boolean isBotonEmpresa() {
		return botonEmpresa;
	}


	public void setBotonEmpresa(boolean botonEmpresa) {
		this.botonEmpresa = botonEmpresa;
	}


	public boolean isBotonTelefono() {
		return botonTelefono;
	}


	public void setBotonTelefono(boolean botonTelefono) {
		this.botonTelefono = botonTelefono;
	}


	public InformeParticular getInformeParticular() {
		return informeParticular;
	}


	public void setInformeParticular(InformeParticular informeParticular) {
		this.informeParticular = informeParticular;
	}


	public boolean isLaboral() {
		return laboral;
	}


	public void setLaboral(boolean laboral) {
		this.laboral = laboral;
	}


	public boolean isParticular() {
		return particular;
	}


	public void setParticular(boolean particular) {
		this.particular = particular;
	}


	public Long getIdEstadoCivilSeleccionado() {
		if (individuoEvaluacion.getEstadoCivil() == null
				|| individuoEvaluacion.getEstadoCivil().getIdEstadoCivil() == null) {
			return new Long(0);
		}
		return individuoEvaluacion.getEstadoCivil().getIdEstadoCivil();
	}


	public void setIdEstadoCivilSeleccionado(Long idEstadoCivilSeleccionado) {
		if (individuoEvaluacion.getEstadoCivil() == null) {
			individuoEvaluacion.setEstadoCivil(new EstadoCivil());
		}
		individuoEvaluacion.getEstadoCivil().setIdEstadoCivil(idEstadoCivilSeleccionado);
	}


	public HtmlSelectOneMenu getDiasPagos() {
		return diasPagos;
	}


	public void setDiasPagos(HtmlSelectOneMenu diasPagos) {
		this.diasPagos = diasPagos;
	}


	public HtmlSelectOneMenu getBancoSeleccionado() {
		return bancoSeleccionado;
	}


	public void setBancoSeleccionado(HtmlSelectOneMenu bancoSeleccionado) {
		this.bancoSeleccionado = bancoSeleccionado;
	}


	public boolean isMostrarEducacion() {
		return mostrarEducacion;
	}


	public void setMostrarEducacion(boolean mostrarEducacion) {
		this.mostrarEducacion = mostrarEducacion;
	}


	public HtmlSelectOneMenu getOcupacionHtml() {
		return ocupacionHtml;
	}


	public void setOcupacionHtml(HtmlSelectOneMenu ocupacionHtml) {
		this.ocupacionHtml = ocupacionHtml;
	}


	public HtmlSelectOneMenu getEducacionHtml() {
		return educacionHtml;
	}


	public void setEducacionHtml(HtmlSelectOneMenu educacionHtml) {
		this.educacionHtml = educacionHtml;
	}


	public String getIdIndividuo() {
		return idIndividuo;
	}


	public void setIdIndividuo(String idIndividuo) {
		this.idIndividuo = idIndividuo;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public List getListaIndividuos() {
		return listaIndividuos;
	}


	public void setListaIndividuos(List listaIndividuos) {
		this.listaIndividuos = listaIndividuos;
	}


	public String getIdIndividuoHidden() {
		return idIndividuoHidden;
	}


	public void setIdIndividuoHidden(String idIndividuoHidden) {
		this.idIndividuoHidden = idIndividuoHidden;
	}


	public boolean isMostrarSolicitudes() {
		return mostrarSolicitudes;
	}


	public void setMostrarSolicitudes(boolean mostrarSolicitudes) {
		this.mostrarSolicitudes = mostrarSolicitudes;
	}


	public List getListDeSolicitudesPorIndividuo() {
		return listDeSolicitudesPorIndividuo;
	}


	public void setListDeSolicitudesPorIndividuo(List listDeSolicitudesPorIndividuo) {
		this.listDeSolicitudesPorIndividuo = listDeSolicitudesPorIndividuo;
	}


	public String getConfTelLaboral() {
		return confTelLaboral;
	}


	public void setConfTelLaboral(String confTelLaboral) {
		this.confTelLaboral = confTelLaboral;
	}


	public String getConfTelParticular() {
		return confTelParticular;
	}


	public void setConfTelParticular(String confTelParticular) {
		this.confTelParticular = confTelParticular;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public boolean isYaExiste() {
		return yaExiste;
	}


	public void setYaExiste(boolean yaExiste) {
		this.yaExiste = yaExiste;
	}


	public boolean isHabilitada() {
		return habilitada;
	}


	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}


	public boolean isVerCargaIndividuo() {
		return verCargaIndividuo;
	}


	public void setVerCargaIndividuo(boolean verCargaIndividuo) {
		this.verCargaIndividuo = verCargaIndividuo;
	}


	public String getApel() {
		return apel;
	}


	public void setApel(String apel) {
		this.apel = apel;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getCargaIndi() {
		return cargaIndi;
	}


	public void setCargaIndi(String cargaIndi) {
		this.cargaIndi = cargaIndi;
	}


	public String getCuilBuscador() {
		return cuilBuscador;
	}


	public void setCuilBuscador(String cuilBuscador) {
		this.cuilBuscador = cuilBuscador;
	}


	public boolean isHabilitarCarga() {
		return habilitarCarga;
	}


	public void setHabilitarCarga(boolean habilitarCarga) {
		this.habilitarCarga = habilitarCarga;
	}


	public List getListaTcliente() {
		return listaTcliente;
	}


	public void setListaTcliente(List listaTcliente) {
		this.listaTcliente = listaTcliente;
	}


	// ESTE GRUPO DE GET/SET ESTA DEFINIDO PARA LOS COMPONTES DE LA ACTIVIDAD

	public Date getIngreso() {
		return ingreso;
	}


	public void setIngreso(Date ingreso) {
		if (refrescarAct)
			this.ingreso = ingreso;
	}


	public String getAntiguedad() {
		return antiguedad;
	}


	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}


	public Long getIdOcupacion() {
		try {
			return individuoEvaluacion.getActividad().getOcupacion().getIdOcupacion();
		} catch (NullPointerException e) {
			return new Long(0);
		}
	}


	public void setIdOcupacion(Long idOcupacion) {
		if (refrescarAct) {
			// log.info("Ocupacion: " + individuoEvaluacion.getActividad().getOcupacion());
			// log.info("Ocupacion: " + individuoEvaluacion.getActividad().getOcupacion().getDescripcion());
			Ocupacion ocupa = (Ocupacion) Util.buscarElemento(listAuxOcupacion, new Ocupacion(idOcupacion));
			individuoEvaluacion.getActividad().setOcupacion(ocupa);
		}
	}


	public String getCargo() {
		return individuoEvaluacion.getActividad().getCargo();
	}


	public void setCargo(String cargo) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setCargo(cargo);
	}


	public BigDecimal getSueldoNeto() {
		return individuoEvaluacion.getActividad().getSueldoNeto();
	}


	public void setSueldoNeto(BigDecimal sueldoNeto) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setSueldoNeto(sueldoNeto);
	}


	public String getEmpleoAnterior() {
		return individuoEvaluacion.getActividad().getEmpleoAnterior();
	}


	public void setEmpleoAnterior(String empleoAnterior) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setEmpleoAnterior(empleoAnterior);
	}


	public String getTelEmpleoAnt() {
		return individuoEvaluacion.getActividad().getTelEmpleoAnt();
	}


	public void setTelEmpleoAnt(String telEmpleoAnt) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setTelEmpleoAnt(telEmpleoAnt);
	}


	public String getReferencias() {
		return individuoEvaluacion.getActividad().getReferencias();
	}


	public void setReferencias(String referencias) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setReferencias(referencias);
	}


	public String getOtrosIngresosDesc() {
		return individuoEvaluacion.getActividad().getOtrosIngresosDesc();
	}


	public void setOtrosIngresosDesc(String otrosIngresosDesc) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setOtrosIngresosDesc(otrosIngresosDesc);
	}


	public BigDecimal getOtrosIngresosMonto() {
		return individuoEvaluacion.getActividad().getOtrosIngresosMonto();
	}


	public void setOtrosIngresosMonto(BigDecimal otrosIngresosMonto) {
		if (refrescarAct)
			individuoEvaluacion.getActividad().setOtrosIngresosMonto(otrosIngresosMonto);
		refrescarAct = true;
	}


	public String getNota_cred() {
		return nota_cred;
	}


	public void setNota_cred(String nota_cred) {
		this.nota_cred = nota_cred;
	}

}