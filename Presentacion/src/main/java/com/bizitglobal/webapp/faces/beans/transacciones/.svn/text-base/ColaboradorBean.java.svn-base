package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Cobrador;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Emails;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Promotor;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Telefonos;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Verificador;
import com.bizitglobal.tarjetafiel.general.exception.PartidoException;
import com.bizitglobal.tarjetafiel.general.exception.SucursalFielException;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDocumento;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.transacciones.exception.ColaboradorDuplicateException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ColaboradorException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;
import com.bizitglobal.tarjetafiel.transacciones.service.ColaboradorService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.Util.IndividuoEvaluacionUtil;
import com.bizitglobal.webapp.faces.beans.general.DomicilioBean;
import com.bizitglobal.webapp.faces.beans.general.DomicilioUtil;
import com.bizitglobal.webapp.faces.beans.general.TelefonoBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.service.general.GeneralServiceFaces;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class ColaboradorBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ColaboradorBean.class);
	GeneralServiceFaces service = generalService;
	private Colaborador colaborador;
	private Long idColaboradorHidden;

	// definicion de un list del objeto base
	private List colaboradorList;

	private List sucursalList;
	private List sucursalItems;
	private List listTipoDni;
	private List listTipoSexo;
	private List listEstadoColaborador;

	private List listTelefono;

	private List listaLocalidadesColaborador;

	// Objetos Relacionados.
	private Long idSucursalSeleccionada;
	private boolean individuoCargado = false;
	private String focoHidden;

	// para trabajar el email que va en el set
	private String email = "";
	private String descripcion = "";
	private boolean esPromotor = false;
	private boolean esVerificador = false;
	private boolean esCobrador = false;
	private boolean esAbogado = true;
	private boolean esCajero = false;

	private String idTipoSexoSeleccionado;
	private Date fechaNacimiento;

	// todos los objetos del filtro
	private String estadoFiltro = "A";
	private boolean promotorFiltro = false;
	private boolean verificadorFiltro = false;
	private boolean cobradorFiltro = false;
	private boolean abogadoFiltro = false;
	private boolean cajeroFiltro = false;

	private Date fechaAltaFiltro = new Date();
	private Date fechaBajaFiltro = new Date();
	private String legajoFiltro = "";
	private boolean incluyeFechasFiltro = false;

	// objetos para el partido del verificador
	private List listaAuxProvincias;
	private List listaAuxPartidos;
	private List listaAuxPaises;
	private Long idPaisSeleccionado;
	private Long provinciaSeleccionada;
	private Long idPartidoSeleccionado;
	private HtmlSelectOneMenu paises;
	private HtmlSelectOneMenu provincias;
	private HtmlSelectOneMenu partidos;
	private List listaPaises;
	private List listaProvincias;
	private List listaPartidos;


	public ColaboradorBean() {
		super();
		listaAuxPartidos = service.getPartidoDao().listarTodos();
		listaAuxProvincias = service.getProvinciaDao().listarTodos();
		listaAuxPaises = service.getPaisDao().listarTodos();
		borrar();
		try {

			try {
				sucursalList = generalService.getSucursalFielService().getSucursales(new Filtro());

			} catch (SucursalFielException e1) {

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


	public Colaborador getColaborador() {
		return colaborador;
	}


	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}


	public Long getIdColaboradorHidden() {
		return idColaboradorHidden;
	}


	public void setIdColaboradorHidden(Long idColaboradorHidden) {
		this.idColaboradorHidden = idColaboradorHidden;
	}


	public List getSucursalItems() {
		return sucursalItems;
	}


	public void setSucursalItems(List sucursalItems) {
		this.sucursalItems = sucursalItems;
	}


	public Long getIdSucursalSeleccionada() {
		return idSucursalSeleccionada;
	}


	public void setIdSucursalSeleccionada(Long idSucursalSeleccionada) {
		this.idSucursalSeleccionada = idSucursalSeleccionada;
	}


	public List getColaboradorList() {
		return colaboradorList;
	}


	public void setColaboradorList(List object) {
		this.colaboradorList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE Colaborador
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
		cargarListas();
		setearListasPorDefecto();
		return "amColaborador";
	}


	private void cargarItems() {
		try {
			sucursalList = generalService.getSucursalFielService().getSucursales(new Filtro());
		} catch (SucursalFielException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sucursalItems == null)
			sucursalItems = new ArrayList();
		if (sucursalItems.size() != sucursalList.size()) {
			log.info("Cargaremos " + sucursalList.size() + " sucursales");
			sucursalItems = Util.cargarSelectItem(sucursalList);
		}
		idSucursalSeleccionada = new Long(1);
		log.info("Cargado Tipo DNI");
		listTipoDni = IndividuoEvaluacionUtil.cargarTipoDocumeno(generalService.getTipoDocumentoDao());
		listTipoSexo = Util.cargarSelectItemMascara(IndividuoEvaluacion.sexoStaticList);
		listEstadoColaborador = Util.cargarSelectItemMascara(Colaborador.estadoStaticList);

	}


	public void cargarDatosPromotorVerificador() {
		if (colaborador.getPromotor() != null) {
			log.info("tenemos un promotor");
			esPromotor = true;
			log.info(colaborador.getPromotor().getApellido());
		}
		if (colaborador.getVerificador() != null) {
			log.info("tenemos un verificador");
			esVerificador = true;
			log.info(colaborador.getVerificador().getApellido());
			colaborador.getVerificador().getPartido().getProvincia().getPais().getIdPais();
			provinciaSeleccionada = colaborador.getVerificador().getPartido().getProvincia().getId();
			idPartidoSeleccionado = colaborador.getVerificador().getPartido().getIdPartido();
			cargarListas();
		}
		if (colaborador.getCobrador() != null) {
			log.info("Es cobrador");
			esCobrador = true;
			log.info(colaborador.getCobrador().getApellido());
		}

		if (colaborador.getEsAbogado() != null) {
			log.info("Es abogado");
			esAbogado = true;
		}

		if (colaborador.getFuncion() != null && colaborador.getFuncion().equals("CAJERO")) {
			log.info("Es cajero");
			esCajero = true;
		}

	}


	private void borrarListas() {
		listaAuxPaises = new ArrayList();
		listaAuxPartidos = new ArrayList();
		listaAuxProvincias = new ArrayList();
		listaPaises = new ArrayList();
		listaPartidos = new ArrayList();
		listaProvincias = new ArrayList();
	}


	public void cambiarPartidos(ValueChangeEvent event) {
		log.info("Ejecutando ==> cambiarPartidos");
		Long idProvinciaSeleccionada = new Long(provincias.getValue().toString());

		if (!idProvinciaSeleccionada.equals(new Long(0))) {
			listaPartidos.clear();
			listaPartidos.add(new SelectItem(new Long(0), "Seleccione Partido"));
			listaPartidos.addAll(DomicilioUtil.filtrarPartidos(listaAuxPartidos, idProvinciaSeleccionada));
		} else {
			setearListasPorDefecto();
		}
	}


	public void cambiarProvincias(ValueChangeEvent event) {
		log.info("Ejecutando ==> cambiarProvincias");
		idPaisSeleccionado = new Long(paises.getValue().toString());
		// if(!idPaisSeleccionado.equals(new Long(0))){
		if (!paises.getValue().equals(new Long(0))) {
			listaProvincias.clear();
			listaProvincias.add(new SelectItem(new Long(0), "Seleccione Provincia"));
			listaProvincias.addAll(DomicilioUtil.filtrarProvincias(listaAuxProvincias, idPaisSeleccionado));
			listaPartidos.clear();
			listaPartidos.add(new SelectItem(new Long(0), "Seleccione Partido"));
			partidos.setValue(new Long(0));
		}
		else {
			setearListasPorDefecto();
		}
	}


	private void setearListasPorDefecto() {
		paises.setValue(new Long(0));
		partidos.setValue(new Long(0));
		provincias.setValue(new Long(0));
	}


	private void cargarListas() {
		borrarListas();
		listaAuxPartidos = service.getPartidoDao().listarTodos();
		listaAuxProvincias = service.getProvinciaDao().listarTodos();
		listaAuxPaises = service.getPaisDao().listarTodos();
		listaPartidos.add(new SelectItem(new Long(0), "Seleccionar Partido"));
		listaProvincias.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
		listaPaises.add(new SelectItem(new Long(0), "Seleccionar País"));
		listaPaises.addAll(DomicilioUtil.cargarListaPaises(listaAuxPaises));
		provincias.setValue(new Long(0));
		paises.setValue(new Long(0));
		if (!alta) {

			idPaisSeleccionado = colaborador.getVerificador().getPartido().getProvincia().getPais().getIdPais();
			paises.setValue(idPaisSeleccionado);
			// cambiarProvincias();
			// cambiarPartidos();
			listaProvincias.addAll(DomicilioUtil.filtrarProvincias(listaAuxProvincias, idPaisSeleccionado));
			provincias.setValue(getProvinciaSeleccionada());
			listaPartidos.clear();
			listaPartidos.addAll(DomicilioUtil.filtrarPartidos(listaAuxPartidos, getProvinciaSeleccionada()));
			idPartidoSeleccionado = colaborador.getVerificador().getPartido().getIdPartido();
			partidos.setValue(idPartidoSeleccionado);
		}
	}


	public String editarColaborador() {
		log.info("editando.......");
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Colaborador";
		try {
			log.info("Editaremos un colaborador");
			this.inicializar();
			colaborador = transaccionesService.getColaboradorService().leerColaborador(idColaboradorHidden);
			colaborador.getIndividuo().getNroDocumento();
			cargarDatosPromotorVerificador();
			habilitarCarga();
			result = "amColaborador";
		} catch (ColaboradorException e1) {
			error.agregar("Ocurrió un error al intentar editar el colaborador");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarColaborador.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrió un error al intentar editar el colaborador");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/transacciones/listarColaborador.jsf");
		}
		return result;
	}


	public String eliminarColaborador() {
		try {
			transaccionesService.getColaboradorService().borrarColaborador(idColaboradorHidden);
			colaboradorList.remove(new Colaborador(idColaboradorHidden));
		} catch (ColaboradorException e1) {
			error.agregar("Imposible borrar el Colaborador. Posee elementos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrió un error al intentar borrar el Colaborador");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarColaborador.jsf");
		return null;
	}


	/*
	 * * Este metodo elimina, agrega y edita los telefonos del set de individuos, a partir de la listTelefonos
	 */
	public void grabarTelefonos() {
		if (colaborador.getIndividuo() != null) {
			if (colaborador.getIndividuo().getTelefonos() == null)
				colaborador.getIndividuo().setTelefonos(new HashSet());
			if (listTelefono != null) {
				Iterator iterTelefonos = listTelefono.iterator();
				while (iterTelefonos.hasNext()) {
					Telefonos telef = (Telefonos) iterTelefonos.next();
					if (telef.getTelefono().getIdTelefono().equals(new Long(telef.getTelefono().hashCode()))) {
						telef.setIdTelefono(null);
						telef.setIndividuoEvaluacion(colaborador.getIndividuo());
						telef.getTelefono().setIdTelefono(null);
						telef.getTelefono().setFechaAlta(new Date());
						telef.getTelefono().setOperador(Session.getOperador());
						colaborador.getIndividuo().getTelefonos().add(telef);
					}
				}
			} else {
				colaborador.getIndividuo().setTelefonos(null);
			}
		}
	}


	public void grabarMail() {
		if (colaborador.getIndividuo().getMails() != null && colaborador.getIndividuo().getMails().size() > 0) {
			// aqui editamos el mail
			// List listaDeEmail = Convertidores.setToList(colaborador.getIndividuo().getMails());
			// Emails edit = (Emails) listaDeEmail.get(0);
			// edit.getEmail().setEmail(email);
			// colaborador.getIndividuo().setMails(new HashSet());
			// colaborador.getIndividuo().getMails().add(edit);
		} else {
			if (email.compareTo("") == 0 || email == null) {
				colaborador.getIndividuo().setMails(null);
			} else {
				// Email mai = new Email(null, email, null);
				// Emails mails = new Emails();
				// mails.setIdEmails(null);
				// mails.setIndividuoEvaluacion(colaborador.getIndividuo());
				// mails.setEmail(mai);
				// colaborador.getIndividuo().setMails(new HashSet());
				// colaborador.getIndividuo().getMails().add(mails);
			}
		}
	}


	public void armarVerifYPromotores() {
		if (esPromotor) {
			log.info("Además del individuo se graba promotor");
			Promotor promot = colaborador.getPromotor();
			// promot.getApellido();
			// promot.getNombre();
			// if (alta) {
			if (colaborador.getPromotor() == null) {
				promot = new Promotor();
				promot.setIdPromotor(null);
				colaborador.setPromotor(promot);
			}
			// }
			promot.setApellido(colaborador.getIndividuo().getApellido());
			promot.setNombre(colaborador.getIndividuo().getNombres());
		} else {
			colaborador.setPromotor(null);
		}
		if (esCobrador) {
			log.info("Además del individuo se graba cobrador");
			Cobrador cobra = colaborador.getCobrador();
			// cobra.getApellido();
			// cobra.getNombre();
			// if (alta) {
			if (colaborador.getCobrador() == null) {
				cobra = new Cobrador();
				cobra.setIdCobrador(null);
				colaborador.setCobrador(cobra);
			}
			// }
			cobra.setApellido(colaborador.getIndividuo().getApellido());
			cobra.setNombre(colaborador.getIndividuo().getNombres());
		} else {
			colaborador.setCobrador(null);
		}
		if (esVerificador) {
			log.info("Además del individuo se graba verificador");
			Verificador verif = colaborador.getVerificador();

			if (colaborador.getVerificador() == null) {
				verif = new Verificador();
				verif.setIdVerificador(null);
				colaborador.setVerificador(verif);
			}

			verif.setApellido(colaborador.getIndividuo().getApellido());
			verif.setNombre(colaborador.getIndividuo().getNombres());
			try {
				if (idPartidoSeleccionado.longValue() != 0) {
					Partido partido = service.getPartidoService().leerPartido(idPartidoSeleccionado);
					colaborador.getVerificador().setPartido(partido);
				} else {
					error.agregar("Debe especifivar un partido para el verificador.");
				}
			} catch (PartidoException e) {
				error.agregar("Debe Seleccionar un partido para el verificador");
				e.printStackTrace();
			}
		} else {
			colaborador.setVerificador(null);
		}

		if (esAbogado) {
			log.info("Le seteamos al colaborador que es abogado");
			colaborador.setEsAbogado("S");
		} else {
			colaborador.setEsAbogado(null);
		}

		if (esCajero) {
			log.info("Le seteamos al colaborador que es cajero");
			colaborador.setFuncion("CAJERO");
		} else {
			colaborador.setFuncion(null);
		}
	}


	public void armarIndividuo() {
		Operador oper = Session.getOperador();
		colaborador.getIndividuo().setIdOperador(oper.getId());
		colaborador.getIndividuo().setTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		List listaDocumentos = generalService.getTipoDocumentoDao().listarTodos(new Filtro());
		Iterator iterDocuTipo = listaDocumentos.iterator();
		while (iterDocuTipo.hasNext()) {
			TipoDocumento tipDoc = (TipoDocumento) iterDocuTipo.next();
			if (tipDoc.getIdTipoDocumento().equals(colaborador.getIndividuo().getTipoDocumento().getIdTipoDocumento())) {
				colaborador.getIndividuo().setTipoDocumento(tipDoc);
				break;
			}
		}
		grabarMail();
		grabarTelefonos();
		IndividuoEvaluacion ind = colaborador.getIndividuo();
		if (ind.getDomicilio() == null || ind.getDomicilio().equals(new Domicilio())) {
			ind.setDomicilio(null);
		}
		if (ind.getIdIndividuo() == null) {
			ind.setCbu(null);
			ind.setHijosCargo(null);
			ind.setActividad(null);
			ind.setDiaPago(null);
			ind.setDomicilioDoc(null);
			ind.setEducacion(null);
			ind.setEstadoCivil(null);
			ind.setProfesion(null);
			ind.setVinculo(null);
			ind.setNroCuenta(null);
			ind.setObservacion(null);
			ind.setSexo(null);
			ind.setTipoCuenta(null);
			ind.setVehiculoPropio(null);
			ind.setDomicilios(null);
			ind.setTelefonos(null);
			ind.setBancos(null);
			ind.setVehiculos(null);
			// ind.setMails(null);
			ind.setTarjetas(null);
			ind.setDocAdjuntos(null);
		}
	}


	public void armarColaborador() {
		armarIndividuo();
		armarVerifYPromotores();
		try {
			colaborador.validar();
		} catch (ColaboradorException e) {
			error.agregar(e.getMessage());
			e.printStackTrace();
		}
	}


	public String grabar() {
		// IndividuoEvaluacion in = colaborador.getIndividuo();
		// setFechaAlta(fechaAlta)
		try {
			if (validar()) {
				// Inicio los servis que voy a usar
				ColaboradorService colaboradorService = transaccionesService.getColaboradorService();
				colaborador.setSucursal((SucursalFiel) Util.buscarElemento(sucursalList, new SucursalFiel(idSucursalSeleccionada)));
				if (alta) {
					log.info("Guardando nuevo individuo");
					colaborador.setFechaAlta(new Timestamp(Calendar.getInstance().getTimeInMillis()));
					colaboradorService.grabarColaborador(colaborador);
				} else {
					log.info("Actualizando el individuo");
					colaboradorService.actualizarColaborador(colaborador);
				}
				popup.setPopup(popup.ICONO_OK, "El Colaborador ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ColaboradorDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Colaborador.");
			e1.printStackTrace();
		} catch (ColaboradorException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Colaborador.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Colaborador.");
			e3.printStackTrace();
		}
		// colaborador.setIndividuo(in);
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de Colaborador";
		popup.borrar();
		individuoCargado = false;
		listTipoDni = new ArrayList();
		sucursalList = new ArrayList();
		colaborador = new Colaborador();
		colaboradorList = new ArrayList();
		listTelefono = new ArrayList();
		fechaNacimiento = null;
		esPromotor = false;
		esVerificador = false;
		esCobrador = false;
		esAbogado = false;
		esCajero = false;
		email = "";
		descripcion = "";
		partidos = new HtmlSelectOneMenu();
		paises = new HtmlSelectOneMenu();
		provincias = new HtmlSelectOneMenu();

		idPartidoSeleccionado = new Long(0);
		cargarItems();
	}


	public boolean isEsAbogado() {
		return esAbogado;
	}


	public void setEsAbogado(boolean esAbogado) {
		this.esAbogado = esAbogado;
	}


	public boolean isEsCajero() {
		return esCajero;
	}


	public void setEsCajero(boolean esCajero) {
		this.esCajero = esCajero;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		armarColaborador();
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoColaborador() {
		return inicializar();
	}


	public String irAModificarColaborador() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Colaborador";
		log.info("Entre al irAModificarColaborador");
		return null;
	}


	public String irAListarColaborador() {
		borrar();
		tituloCorto = "Listado de Colaborador";
		cargarItems();
		Session.redirect("/tarjetafiel/transacciones/listarColaborador.jsf");
		return "";
	}


	public String habilitarCarga() {
		// controlar sea numero de documento valido
		if (colaborador.getIndividuo().getTipoDocumento().getIdTipoDocumento().longValue() == 0
				|| (colaborador.getIndividuo().getNroDocumento() != null && colaborador.getIndividuo().getNroDocumento().length() < 7)) {
			error.agregar("Debe especificar un tipo y numero de documento válido");
		} else {
			try {
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("nroDocumento", Filtro.LIKEXS, colaborador.getIndividuo().getNroDocumento());
				filtro.agregarCampoOperValor("tipoDocumento.idTipoDocumento", Filtro.IGUAL, colaborador.getIndividuo().getTipoDocumento()
						.getIdTipoDocumento());
				List listaIndividuo = evaluacionService.getIndividuoEvaluacionService().getIndividuo(filtro);
				Iterator iter = listaIndividuo.iterator();
				while (iter.hasNext()) {
					IndividuoEvaluacion ind = (IndividuoEvaluacion) iter.next();
					ind.getTelefonos();
					Iterator telefonos = ind.getTelefonos().iterator();
					while (telefonos.hasNext()) {
						Telefonos tel = (Telefonos) telefonos.next();
						tel.getTelefono().getTipo().getDescripcion();
						tel.getTelefono().getNroTlefono();
						listTelefono.add(tel);

					}
					ind.getMails();
					Iterator mails = ind.getMails().iterator();
					while (mails.hasNext()) {
						Emails em = (Emails) mails.next();
						email = em.getEmail().getEmail();
						descripcion = em.getEmail().getDescripcion();
						break;
					}
					ind.getDomicilio();
					ind.getTipoDocumento().getDescripcion();
				}
				if (!listaIndividuo.isEmpty()) {
					log.info("El individuo ya existe");
					IndividuoEvaluacion indEdit = (IndividuoEvaluacion) listaIndividuo.get(0);
					Filtro filtrin = new Filtro();
					filtrin.agregarCampoOperValor("individuo", Filtro.IGUAL, indEdit.getIdIndividuo());
					List colabora = transaccionesService.getColaboradorService().getColaborador(filtrin);
					if (!colabora.isEmpty()) {
						alta = false;
						colaborador = (Colaborador) colabora.get(0);
						// if (colaborador.getPromotor()!=null);
						// if (colaborador.getVerificador()!=null) esVerificador = true;
						cargarDatosPromotorVerificador();
					} else {
						alta = true;
					}
					colaborador.setIndividuo(indEdit);

					// aca todolo relacionado a traer el individuo
				} else {
					String nroDeDocumentoCargado = colaborador.getIndividuo().getNroDocumento();
					Long tipoDocuCargado = colaborador.getIndividuo().getTipoDocumento().getIdTipoDocumento();
					colaborador.setIndividuo(new IndividuoEvaluacion());
					colaborador.getIndividuo().setNroDocumento(nroDeDocumentoCargado);
					colaborador.getIndividuo().setTipoDocumento(new TipoDocumento());
					colaborador.getIndividuo().getTipoDocumento().setIdTipoDocumento(tipoDocuCargado);
					colaborador.getIndividuo().setMails(new HashSet());
					colaborador.getIndividuo().setTelefonos(new HashSet());
					colaborador.getIndividuo().setDomicilio(new Domicilio());
					log.info("Se dará de alta un nuevo individuo");
				}
			} catch (IndividuoEvaluacionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ColaboradorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			individuoCargado = true;
		}
		return null;
	}


	public String habilitarNuevaCarga() {
		log.info("Se habilitará nueva");
		borrar();
		individuoCargado = false;
		return null;
	}


	public String agregarDomicilioPopup() {
		log.info("Ir a agrega nuevo domicilio al Colaborador!!!");
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		if (colaborador.getIndividuo().getDomicilio() == null) {
			colaborador.getIndividuo().setDomicilio(new Domicilio());
		}
		bean.inicializar(DomicilioBean.COLABORADOR, colaborador.getIndividuo().getDomicilio());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	public String listarColaborador() {
		colaboradorList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();

			if (promotorFiltro || verificadorFiltro || cobradorFiltro || abogadoFiltro || cajeroFiltro) {
				if (promotorFiltro) {
					filtro.agregarCampoComparacionNulo("promotor", Filtro.NOTNULL);
				} else {
					filtro.agregarCampoComparacionNulo("promotor", Filtro.NULL);
				}
				if (verificadorFiltro) {
					filtro.agregarCampoComparacionNulo("verificador", Filtro.NOTNULL);
				} else {
					filtro.agregarCampoComparacionNulo("verificador", Filtro.NULL);
				}
				if (cobradorFiltro) {
					filtro.agregarCampoComparacionNulo("cobrador", Filtro.NOTNULL);
				} else {
					filtro.agregarCampoComparacionNulo("cobrador", Filtro.NULL);
				}

				if (abogadoFiltro) {
					filtro.agregarCampoComparacionNulo("esAbogado", Filtro.NOTNULL);
				} else {
					filtro.agregarCampoComparacionNulo("esAbogado", Filtro.NULL);
				}

				if (cajeroFiltro) {
					filtro.agregarCampoOperValor("funcion", Filtro.LIKE, "CAJERO");
				}
			}
			filtro.agregarCampoOperValor("estado", Filtro.LIKE, estadoFiltro);
			filtro.agregarCampoOperValor("sucursal", Filtro.IGUAL, idSucursalSeleccionada);
			if (legajoFiltro != null && legajoFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("nroLegajo", Filtro.LIKE, legajoFiltro);
			}
			if (incluyeFechasFiltro) {
				filtro.agregarCampoOperValor("fechaAlta", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(new Timestamp(fechaAltaFiltro.getTime())));
				filtro.agregarCampoOperValor("fechaBaja", Filtro.MENOR_IGUAL, Filtro.getTO_DATE(new Timestamp(fechaBajaFiltro.getTime())));
			}
			colaboradorList = transaccionesService.getColaboradorService().getColaborador(filtro);
			Iterator iter = colaboradorList.iterator();
			while (iter.hasNext())
			{
				Colaborador element = (Colaborador) iter.next();
				element.getIndividuo().getApellido();
				element.getIndividuo().getCuil();
				element.getIndividuo().getLabel();
				element.getSucursal().getLabel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/listarColaborador.jsf");
		return null;
	}


	public List getListTipoDni() {
		return listTipoDni;
	}


	public void setListTipoDni(List listTipoDni) {
		this.listTipoDni = listTipoDni;
	}


	public boolean isIndividuoCargado() {
		return individuoCargado;
	}


	public void setIndividuoCargado(boolean individuoCargado) {
		this.individuoCargado = individuoCargado;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List getListTelefono() {
		return listTelefono;
	}


	public void setListTelefono(List listTelefono) {
		this.listTelefono = listTelefono;
	}


	/*
	 * este metodo se utiliza para agregar un telefono al individuo
	 */
	public String agregarTelefono() {
		log.info("Ejecutando ==> agragrTelefono()");
		TelefonoBean bean = (TelefonoBean) Session.getBean("TelefonoBean");
		bean.inicializar(TelefonoBean.COLABORADOR, new Telefono());
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
		IndividuoEvaluacionUtil.eliminarTelefonoSet(colaborador.getIndividuo().getTelefonos(), idTelefono);
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
		Telefono telefono = IndividuoEvaluacionUtil.buscarTelefono(listTelefono, idTelefono);
		bean.inicializar(TelefonoBean.COLABORADOR, telefono);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");
		return null;
	}


	public boolean isEsPromotor() {
		return esPromotor;
	}


	public void setEsPromotor(boolean esPromotor) {
		this.esPromotor = esPromotor;
	}


	public boolean isEsVerificador() {
		return esVerificador;
	}


	public void setEsVerificador(boolean esVerificador) {
		this.esVerificador = esVerificador;
	}


	public List getListTipoSexo() {
		return listTipoSexo;
	}


	public void setListTipoSexo(List listTipoSexo) {
		this.listTipoSexo = listTipoSexo;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getIdTipoSexoSeleccionado() {
		return idTipoSexoSeleccionado;
	}


	public void setIdTipoSexoSeleccionado(String idTipoSexoSeleccionado) {
		this.idTipoSexoSeleccionado = idTipoSexoSeleccionado;
	}


	public List getListEstadoColaborador() {
		return listEstadoColaborador;
	}


	public void setListEstadoColaborador(List listEstadoColaborador) {
		this.listEstadoColaborador = listEstadoColaborador;
	}


	public Date getFechaAlta() {
		return colaborador.getFechaAlta();
	}


	public boolean isVerFechaAlta() {
		return colaborador.getFechaAlta() != null;
	}


	public boolean isVerFechaBaja() {
		return colaborador.getFechaBaja() != null;
	}


	public void setFechaAlta(Date fechaAlta) {
		colaborador.setFechaAlta(new Timestamp(fechaAlta.getTime()));
	}


	public Date getFechaBaja() {
		return colaborador.getFechaBaja();
	}


	public void setFechaBaja(Date fechaBaja) {

		colaborador.setFechaBaja(new Timestamp(fechaBaja.getTime()));
	}


	public String getEstadoFiltro() {
		return estadoFiltro;
	}


	public void setEstadoFiltro(String estadoFiltro) {
		this.estadoFiltro = estadoFiltro;
	}


	public Date getFechaAltaFiltro() {
		return fechaAltaFiltro;
	}


	public void setFechaAltaFiltro(Date fechaAltaFiltro) {
		this.fechaAltaFiltro = fechaAltaFiltro;
	}


	public Date getFechaBajaFiltro() {
		return fechaBajaFiltro;
	}


	public void setFechaBajaFiltro(Date fechaBajaFiltro) {
		this.fechaBajaFiltro = fechaBajaFiltro;
	}


	public String getLegajoFiltro() {
		return legajoFiltro;
	}


	public void setLegajoFiltro(String legajoFiltro) {
		this.legajoFiltro = legajoFiltro;
	}


	public boolean isPromotorFiltro() {
		return promotorFiltro;
	}


	public void setPromotorFiltro(boolean promotorFiltro) {
		this.promotorFiltro = promotorFiltro;
	}


	public boolean isVerificadorFiltro() {
		return verificadorFiltro;
	}


	public void setVerificadorFiltro(boolean verificadorFiltro) {
		this.verificadorFiltro = verificadorFiltro;
	}


	public boolean isIncluyeFechasFiltro() {
		return incluyeFechasFiltro;
	}


	public void setIncluyeFechasFiltro(boolean incluyeFechasFiltro) {
		this.incluyeFechasFiltro = incluyeFechasFiltro;
	}


	public Long getIdPaisSeleccionado() {
		return idPaisSeleccionado;
	}


	public void setIdPaisSeleccionado(Long idPaisSeleccionado) {
		this.idPaisSeleccionado = idPaisSeleccionado;
	}


	public Long getIdPartidoSeleccionado() {
		return idPartidoSeleccionado;
	}


	public void setIdPartidoSeleccionado(Long idPartidoSeleccionado) {
		this.idPartidoSeleccionado = idPartidoSeleccionado;
	}


	public List getListaPaises() {
		return listaPaises;
	}


	public void setListaPaises(List listaPaises) {
		this.listaPaises = listaPaises;
	}


	public List getListaPartidos() {
		return listaPartidos;
	}


	public void setListaPartidos(List listaPartidos) {
		this.listaPartidos = listaPartidos;
	}


	public List getListaProvincias() {
		return listaProvincias;
	}


	public void setListaProvincias(List listaProvincias) {
		this.listaProvincias = listaProvincias;
	}


	public HtmlSelectOneMenu getPaises() {
		return paises;
	}


	public void setPaises(HtmlSelectOneMenu paises) {
		this.paises = paises;
	}


	public HtmlSelectOneMenu getPartidos() {
		return partidos;
	}


	public void setPartidos(HtmlSelectOneMenu partidos) {
		this.partidos = partidos;
	}


	public HtmlSelectOneMenu getProvincias() {
		return provincias;
	}


	public void setProvincias(HtmlSelectOneMenu provincias) {
		this.provincias = provincias;
	}


	public Long getProvinciaSeleccionada() {
		return provinciaSeleccionada;
	}


	public void setProvinciaSeleccionada(Long provinciaSeleccionada) {
		this.provinciaSeleccionada = provinciaSeleccionada;
	}


	public boolean isEsCobrador() {
		return esCobrador;
	}


	public void setEsCobrador(boolean esCobrador) {
		this.esCobrador = esCobrador;
	}


	public boolean isCobradorFiltro() {
		return cobradorFiltro;
	}


	public void setCobradorFiltro(boolean cobradorFiltro) {
		this.cobradorFiltro = cobradorFiltro;
	}


	public boolean isAbogadoFiltro() {
		return abogadoFiltro;
	}


	public void setAbogadoFiltro(boolean abogadoFiltro) {
		this.abogadoFiltro = abogadoFiltro;
	}


	public boolean isCajeroFiltro() {
		return cajeroFiltro;
	}


	public void setCajeroFiltro(boolean cajeroFiltro) {
		this.cajeroFiltro = cajeroFiltro;
	}
}
