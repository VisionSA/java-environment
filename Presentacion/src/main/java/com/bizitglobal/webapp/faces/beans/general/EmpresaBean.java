package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.bizitglobal.tarjetafiel.commons.exception.CuitNoValidoException;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.CuitValido;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ActividadEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ActividadEvaluacion;
import com.bizitglobal.tarjetafiel.general.exception.ActividadRubroException;
import com.bizitglobal.tarjetafiel.general.exception.AutonomoException;
import com.bizitglobal.tarjetafiel.general.exception.EmpresaException;
import com.bizitglobal.tarjetafiel.general.exception.RubroException;
import com.bizitglobal.tarjetafiel.general.exception.TamEmpresaException;
import com.bizitglobal.tarjetafiel.general.negocio.ActividadSucursal;
import com.bizitglobal.tarjetafiel.general.negocio.Autonomo;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Email;
import com.bizitglobal.tarjetafiel.general.negocio.Empresa;
import com.bizitglobal.tarjetafiel.general.negocio.Rubro;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmail;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresasXDomicilio;
import com.bizitglobal.tarjetafiel.general.negocio.SucTelefono;
import com.bizitglobal.tarjetafiel.general.negocio.TamEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.general.util.EmpresaUtil;
import com.bizitglobal.webapp.faces.beans.general.wrapper.EmailWrapper;
import com.bizitglobal.webapp.faces.beans.transacciones.CodComercioBean;
import com.bizitglobal.webapp.faces.beans.transacciones.popup.IndividuoPopupBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.service.general.GeneralServiceFaces;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Validador;



@SuppressWarnings({"rawtypes","unchecked"})
public class EmpresaBean extends BaseBean {

	private static final Logger log = Logger.getLogger(EmpresaBean.class);
	private GeneralServiceFaces service = generalService;
	private Long idEmpresaHidden; // id oculto para eliminar o editar empresas.
	private String cuitEmpresa; // para el filtro buscador de empresas.
	private String cuitEditado;
	private String razonSocialEmpresa; // para el filtro buscador de empresas.
	private List empresaList; // una lista de empresas existentes en la base de datos.
	private int indiceTabla = 0;
	private int indiceTablaEmails = 0;
	private int indiceTablaDomicilios = 0;
	private Empresa empresa; // el objeto empresa.
	private Sucursal sucursalEmpresa; // el objeto SucEmpresa necesario para saber que sucursal editar.
	private List sucEmpresasXDomicilio;
	private List sucursales; // una lista con objetos sucursales adentro.
	private boolean esRiesgosa;
	private boolean muestroLista; // alterna entre la lista y la edicion de sucursales.
	private List listaRubrosAux;
	private List listaTamaniosAux;
	private List listaAutonomosAux;
	private List listaTamanios;
	private List listaAutonomos;
	/**** Emiliano *****/
	private List domPrincipalList = new ArrayList();
	private List domPrincipalItems = new ArrayList();
	private Long idDomPrincipal;
	/*** \Emiliano *****/
	private boolean altaSucursal; // un booleano para saber si edito o grabo una sucursal nueva.
	private int origen = 0;
	public static final int CODCOMERCIO = 1;
	public static final int INDIVIDUO = 2;
	private List rubroList = new ArrayList();
	private List rubroItems = new ArrayList();
	// Objetos Relacionados.
	private HtmlSelectOneMenu rubro = new HtmlSelectOneMenu();
	private HtmlSelectOneMenu actividad = new HtmlSelectOneMenu();
	private boolean altaNavegacion = false;


	public void cargardomPrincipalList() {

		domPrincipalList.clear();
		domPrincipalItems.clear();
		domPrincipalItems.add(new SelectItem(new Long(0), "Seleccione Domicilio"));
		Iterator iterSucEmpresa = sucursales.iterator();
		while (iterSucEmpresa.hasNext()) {
			Sucursal sucursal = (Sucursal) iterSucEmpresa.next();
			List sucDomicilios = sucursal.getLstDomicilios();
			;
			Iterator domicilioIterator = sucDomicilios.iterator();
			while (domicilioIterator.hasNext()) {
				DomicilioWrapper domicilioWrapper = (DomicilioWrapper) domicilioIterator.next();
				domicilioWrapper.getDomicilio().getLocalidad();
				domPrincipalList.add(domicilioWrapper);
				domPrincipalItems.add(new SelectItem(new Long(domicilioWrapper.getCodigo()),
						sucursal.getSucEmpresa().getLabel() + " - " + domicilioWrapper.getDomicilio().getLocalidad().getLabel() + " - "
								+ domicilioWrapper.getDomicilio().getCalleNombre() + " - " + domicilioWrapper.getDomicilio().getCalleNumero()));
			}

		}
	}


	public EmpresaBean() {

		// Borrar el bean de errores para poder validar desde cero.
		error.borrar();
		tituloCorto = "";
		cargarListas();
		inicializar();
		try {
			setRubroList(generalService.getActividadRubroService().getActividadRubro(new Filtro()));
			int x = 0;
		} catch (ActividadRubroException e1) {
			e1.printStackTrace();
		}
	}


	/*
	 * Leo las listas de la base de datos.
	 */
	public void cargarListas() {
		try {
			listaRubrosAux = new ArrayList();
			listaTamaniosAux = new ArrayList();
			listaAutonomosAux = new ArrayList();
			listaRubrosAux = service.getRubroService().getRubro(new Filtro());
			Iterator i = listaRubrosAux.iterator();
			while (i.hasNext()) {
				Rubro rub = (Rubro) i.next();
				rub.getDescripcion();
				rub.getIdRubro();
			}
			listaTamaniosAux = service.getTamanioEmpresaService().getTamEmpresa(new Filtro());
			listaAutonomosAux = service.getAutonomoService().getAutonomo(new Filtro());
			listaTamanios = TamEmpresaUtil.cargarListaTamEmpresa(listaTamaniosAux);
			listaAutonomos = AutonomoUtil.cargarListaAutonomos(listaAutonomosAux);
		} catch (RubroException e1) {
			e1.printStackTrace();
		} catch (TamEmpresaException e2) {
			e2.printStackTrace();
		} catch (AutonomoException e3) {
			e3.printStackTrace();
		} catch (Exception e4) {
			e4.printStackTrace();
		}
	}


	public void grabarEmpresa() {
		if (validar()) {

			if (esRiesgosa)
				empresa.setEsRiesgoza(new Character('S'));
			else
				empresa.setEsRiesgoza(new Character('N'));

			if (alta) {

				empresa.setGenerarPDF(new Character('N'));
				empresa.setEnviarMail(new Character('N'));
				empresa.setImprimirLiquidacion(new Character('N'));
				empresa.setTipoLiquidacion(new Long(2));
				log.info("viene todo bien");
				// seteo el Tamanio empresa
				Iterator iterTam = listaTamaniosAux.iterator();
				while (iterTam.hasNext()) {
					TamEmpresa element = (TamEmpresa) iterTam.next();
					empresa.setTamEmpresa(element);
					break;
				}
			}
			try {
				if (alta) {
					empresa.setSucEmpresas(new HashSet());
					alta = false;
				} else {
					// seteo el Rubro

					// seteo el Tamanio empresa
					Iterator iterTam = listaTamaniosAux.iterator();
					while (iterTam.hasNext()) {
						TamEmpresa element = (TamEmpresa) iterTam.next();
						empresa.setTamEmpresa(element);
						break;
					}
				}
				// seteo las sucursales que tiene
				empresa.setRubro(null);
				empresa.getSucEmpresas().clear();
				Iterator it = sucursales.iterator();
				while (it.hasNext()) {
					SucEmpresa sucEmp = ((Sucursal) it.next()).getSucEmpresa();
					sucEmp.setEmpresa(empresa);
					sucEmp.setAutonomo(null);
					empresa.getSucEmpresas().add(sucEmp);
				}
				// empresa.setTipoError;
				// Liquidacion(new Long(1));

				empresa.setDomicilioLegal(EmpresaUtil.buscarDomicilio(domPrincipalList, idDomPrincipal.intValue()));
				if (empresa.getIdEmpresa() == null) {
					generalService.getEmpresaService().grabarEmpresa(empresa);
				} else {
					generalService.getEmpresaService().actualizarEmpresa(empresa);
				}
				if (origen == CODCOMERCIO) {
					CodComercioBean beanComercio = (CodComercioBean) Session.getBean("CodComercioBean");
					beanComercio.setEmpresa(empresa);
					beanComercio.setAltaNavegacion(altaNavegacion);
					if (altaNavegacion)
						beanComercio.setEdicion(false);
					else
					{
						/* @F5013 */// beanComercio.setSucEmpresa(null);
						beanComercio.setEdicion(true);
					}
					beanComercio.habilitarCargaDesdePopup();
					// Iterator iterator = empresa.getSucEmpresas().iterator();
					// while (iterator.hasNext()) {
					// SucEmpresa element = (SucEmpresa) iterator.next();
					// SelectItem item = new SelectItem();
					//
					// item.setValue(element.getIdSucEmpresa());
					// item.setLabel(element.getDescripcion());
					// beanComercio.getListSucEmp().add(item);
					// }

				}

				// ***********Si viene desde el IndividuoPopupBean -> IndividuoPopup.jsp*************
				if (origen == INDIVIDUO) {
					IndividuoPopupBean beanIndividuo = (IndividuoPopupBean) Session.getBean("IndividuoPopupBean");
					beanIndividuo.setNroCuit(empresa.getCuit().toString());
					beanIndividuo.buscarEmpresa();
				}

				if (origen != CODCOMERCIO)
					popup.setPopup(popup.ICONO_OK, "La empresa ha sido almacenada exitosamente.");

			} catch (EmpresaException e) {
				log.info("Error: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}


	public String eliminarEmpresa() {
		error.borrar();
		try {
			List actividadesExistentes = evaluacionService.getActividadEvaluacionService().getActividad(new Filtro());
			Iterator iterActividades = actividadesExistentes.iterator();
			while (iterActividades.hasNext()) {
				ActividadEvaluacion act = (ActividadEvaluacion) iterActividades.next();
				if (act.getSucEmpresa() != null && act.getSucEmpresa().getEmpresa() != null && act.getSucEmpresa().getEmpresa() != null
						&& act.getSucEmpresa().getEmpresa().getIdEmpresa() != null
						&& act.getSucEmpresa().getEmpresa().getIdEmpresa().equals(idEmpresaHidden)) {
					error.agregar(Error.NO_SE_PUEDE_BORRAR_EMPRESA);
					break;
				}
			}
		} catch (ActividadEvaluacionException e) {
			e.printStackTrace();
		}
		if (error.cantidad() == 0) {
			try {
				generalService.getEmpresaService().borrarEmpresa(idEmpresaHidden);
			} catch (EmpresaException e1) {
				error.agregar(e1.getMessage());
			} catch (ConstraintViolationException e2) {
				error.agregar("No se puede eliminar la empresa, ya que esta siendo utilizado en otras relaciones.");
			} catch (Exception e4) {
				error.agregar("A ocurrido un error al intentar eliminar la empresa.");
				e4.printStackTrace();
			}
		}
		listarEmpresas();
		return null;
	}


	public String editarEmpresa() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		altaSucursal = false;
		tituloCorto = "Modificar Empresa";
		try {
			empresa = generalService.getEmpresaService().leerEmpresa(idEmpresaHidden);
			cuitEditado = empresa.getCuit().toString();
			empresa.getRubro();
			empresa.getTamEmpresa();
			empresa.getEsRiesgoza();
			Iterator iter = empresa.getSucEmpresas().iterator();
			while (iter.hasNext()) {
				SucEmpresa sucEmpresa = (SucEmpresa) iter.next();
				sucEmpresa.getIdSucEmpresa();
				log.info("La sucEmpresa seleccionada tiene id: " + sucEmpresa.getIdSucEmpresa());
				sucEmpresa.getDomicilio().getBarrio().getDescripcion();
				sucEmpresa.getDomicilio().getLocalidad().getNombre();
				sucEmpresa.getAutonomo();
				Iterator itDom = sucEmpresa.getSucEmpresaXDomicilio().iterator();
				while (itDom.hasNext()) {
					SucEmpresasXDomicilio sucXDom = (SucEmpresasXDomicilio) itDom.next();
					sucXDom.getDomicilio().getBarrio().getDescripcion();
					sucXDom.getDomicilio().getLocalidad().getNombre();
				}
				Iterator it = sucEmpresa.getSucTelefonos().iterator();
				while (it.hasNext()) {
					SucTelefono tel = (SucTelefono) it.next();
					tel.getTelefono().getTipo().getDescripcion();
					tel.getSucEmpresa();
					tel.getTelefono().getDescripcion();
					tel.getTelefono().getEsCelular();
					tel.getTelefono().getEsCelularBoolean();
					tel.getTelefono().getEsFax();
					tel.getTelefono().getEsFaxBoolean();
					tel.getTelefono().getHorarios();
					tel.getTelefono().getNroTlefono();
				}

				Iterator itDos = sucEmpresa.getSucEmails().iterator();
				while (itDos.hasNext()) {
					SucEmail email = (SucEmail) itDos.next();
					email.getEmail().getEmail();
					email.getIdSucEmail();
					email.getSucEmpresa();
				}
			}
			// Aca agregar la carga de el list de domicilios principales

			if (empresa != null)
				result = inicializar(empresa);
		} catch (EmpresaException e1) {
			error.agregar("Ocurrio un error al intentar editar la empresa");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarEmpresa.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar la empresa");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarEmpresa.jsf");
		}
		return result;
	}


	public String irANuevaEmpresa() {
		return inicializar();
	}


	public String irAModificarEmpresa() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Empresa";
		return null;
	}


	public String irAListarEmpresas() {
		borrar();
		tituloCorto = "Listado de Empresas";
		empresaList = new ArrayList();
		Session.redirect("/tarjetafiel/general/listarEmpresa.jsf");
		return "";
	}


	public String irAListarProvincia() {
		borrar();
		tituloCorto = "Listado de Provincias";
		cargarItems();
		Session.redirect("/tarjetafiel/general/listarProvincia.jsf");
		return "";
	}


	public String listarEmpresas() {
		empresaList = new ArrayList();
		try {
			Filtro filtro = new Filtro();
			if (cuitEmpresa != null && !cuitEmpresa.equals(""))
				filtro.agregarCampoOperValor("cuit", Filtro.IGUAL, new Long(cuitEmpresa));
			if (razonSocialEmpresa != null && !razonSocialEmpresa.equals(""))
				filtro.agregarCampoOperValor("razonSocial", Filtro.LIKE, razonSocialEmpresa);
			empresaList = generalService.getEmpresaService().getEmpresa(filtro);
			Iterator iter = empresaList.iterator();
			while (iter.hasNext())
			{
				Empresa element = (Empresa) iter.next();
				element.getSucEmpresas();
				// element.getRubro().getDescripcion();
				element.getTamEmpresa().getDescripcion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarEmpresa.jsf");
		return null;
	}


	public void ocultarSucursal() {
		sucursalEmpresa = null;
		muestroLista = true;
	}


	public void mostrarSucursal() {
		muestroLista = false;
	}


	public boolean validarDatosSucursal() {
		
		CodComercioBean beanComercio = (CodComercioBean) Session.getBean("CodComercioBean");
		
				
			
		
		
		if (sucursalEmpresa.getSucEmpresa().getDescripcion() == "" || sucursalEmpresa.getSucEmpresa().getDescripcion() == null) {
			error.agregar(Error.SUCURSAL_DESCRIPCION_REQUERIDA);
		}
		// if (sucursalEmpresa.getAutonomoSeleccionado()==null||sucursalEmpresa.getAutonomoSeleccionado().equals(new Long(0))) {
		// error.agregar(Error.SUCURSAL_AUTONOMO_REQUERIDO);
		// }
		/******* Emiliano ********/
		
		if (alta 
				&& (sucursalEmpresa.getLstDomicilios() == null || sucursalEmpresa.getLstDomicilios().size() == 0)) {
			error.agregar(Error.SUCURSAL_DOMICILIO_REQUERIDO);
		}


		if ((!alta && beanComercio.transaccionesService.getCodComercioService()
				.compraAliasCodigoPosnet(beanComercio.getCodComercio().getIdCodComercio().toString()) == null)
				&& (sucursalEmpresa.getLstDomicilios() == null || sucursalEmpresa.getLstDomicilios().size() == 0)) {
			log.info("empresa compraAliasCodigoPosnet "+ beanComercio.transaccionesService.getCodComercioService()
					.compraAliasCodigoPosnet(beanComercio.getCodComercio().getIdCodComercio().toString()) );
			error.agregar(Error.SUCURSAL_DOMICILIO_REQUERIDO);
		}
			
		// Validacion del domicilio central requerido.
		if (origen == CODCOMERCIO || origen == INDIVIDUO) {
			Iterator iterDomicilio = sucursalEmpresa.getLstDomicilios().iterator();
			boolean validarDomicilio = false;
			DomicilioWrapper domicilioWrapper;
			for (int i = 0; iterDomicilio.hasNext(); i++) {
				domicilioWrapper = (DomicilioWrapper) iterDomicilio.next();
				if (domicilioWrapper == null || domicilioWrapper.isBoolCentral()) {
					validarDomicilio = true;
				}
			}
			
			if (alta 
					&&!validarDomicilio) {
				error.agregar(Error.DOMICILIO_CENTRAL_REQUERIDA);
			}
			
			if (!alta && (beanComercio.transaccionesService.getCodComercioService()
					.compraAliasCodigoPosnet(beanComercio.getCodComercio().getIdCodComercio().toString()) == null)
					&&!validarDomicilio) {
				error.agregar(Error.DOMICILIO_CENTRAL_REQUERIDA);
			}
		}
		/****** \Emiliano *********/

		if (sucursalEmpresa.email != null && !sucursalEmpresa.email.isEmpty()) {
			Iterator i = sucursalEmpresa.email.iterator();
			while (i.hasNext()) {
				EmailWrapper em = (EmailWrapper) i.next();
				if (em.getSucEmail() != null && em.getSucEmail().getEmail() != null && !em.getSucEmail().getEmail().equals("")
						&& !Validador.checkEmail(em.getSucEmail().getEmail().getEmail())) {
					error.agregar(Error.EVA_INDIVIDUO_EMAIL_INCORRECTO);
					break;
				}
			}

		}
		log.info("Intentando agregar la sucursal, hay " + error.cantidad() + " errores.");
		return (error.cantidad() == 0) ? true : false;
	}


	public void agregarSucursal() {
		tituloCorto = "Alta de Sucursal";
		altaSucursal = true;
		sucursalEmpresa = new Sucursal();
		sucursalEmpresa.setSucEmpresa(new SucEmpresa());
		sucursalEmpresa.getSucEmpresa().setDomicilio(new Domicilio());
		sucursalEmpresa.email = new ArrayList();
		sucursalEmpresa.telefonos = new ArrayList();
		sucursalEmpresa.setAutonomo(new Autonomo());
		// sucursalEmpresa.setAutonomoSeleccionado(new Long(0));
		sucursalEmpresa.getSucEmpresa().setEmpresa(empresa);
		muestroLista = false;
		// sucursales.add(sucursalEmpresa);
	}


	// Metodos para administrar emails
	public String agregarEmail() {
		log.info("Agregando email!!!");
		if (!sucursalEmpresa.getEmail().equals(null)) {
			EmailWrapper ultimo;
			SucEmail sucEma = new SucEmail();
			sucEma.setSucEmpresa(sucursalEmpresa.getSucEmpresa());
			sucEma.setIdSucEmail(null);
			sucEma.setEmail(new Email());
			ultimo = new EmailWrapper(indiceTablaEmails++, sucEma);
			sucursalEmpresa.getEmail().add(ultimo);
		}
		log.info("Long: " + sucursalEmpresa.getEmail().size());
		return null;
	}


	public String eliminarEmail() {
		int codigo = (new Long(Session.getRequestParameter("idEmailAElim"))).intValue();
		if (sucursalEmpresa.getEmail().size() > 0) {
			sucursalEmpresa.setEmail(EmpresaUtil.eliminarEmail(sucursalEmpresa.getEmail(), codigo));
		}
		return null;
	}


	// Este metodo asigna una sucursal a la empresa.
	public void grabarSucursal() {
		if (validarDatosSucursal()) {
			// Iterator iterator = listaAutonomosAux.iterator();
			// while (iterator.hasNext()) {
			// Autonomo element = (Autonomo) iterator.next();
			// if(element.getIdAutonomo().equals(sucursalEmpresa.getAutonomoSeleccionado())) {
			// sucursalEmpresa.setAutonomo(element);
			// sucursalEmpresa.getSucEmpresa().setAutonomo(element);
			// }
			// }

			if (altaSucursal) {
				sucursalEmpresa.setIdSucursalTabla(indiceTabla++);
				sucursalEmpresa.getSucEmpresa().setEmpresa(empresa);
				sucursalEmpresa.getSucEmpresa().setIdSucEmpresa(null);
				ActividadSucursal auxiAct = new ActividadSucursal(sucursalEmpresa.getIdActividadSeleccionada());
				ActividadSucursal acti = (ActividadSucursal) Util.buscarElemento(sucursalEmpresa.getActividadesList(), auxiAct);
				sucursalEmpresa.getSucEmpresa().setActividadSucursal(acti);
				// sucursalEmpresa.getSucEmpresa().setDomicilio(null);
				// sucursalEmpresa.setAutonomo(null);
				sucursales.add(sucursalEmpresa);
			} else {
				// if (sucursalEmpresa.actividadEditada !=null &&
				// sucursalEmpresa.actividadEditada.getIdActividadSucursal().compareTo(sucursalEmpresa.getIdActividadSeleccionada())!=0)
				sucursalEmpresa.sucEmpresa.setActividadSucursal((ActividadSucursal) Util.buscarElemento(sucursalEmpresa.getActividadesList(),
						new ActividadSucursal(sucursalEmpresa.getIdActividadSeleccionada())));
			}

			// convierto el Wrapper de telefonos en lista de Telefonos
			// List listaTel = new ArrayList();
			Iterator it = sucursalEmpresa.getTelefonos().iterator();
			while (it.hasNext()) {
				TelefonoWrapper elemento = (TelefonoWrapper) it.next();
				if (elemento.getSucTelefono() != null && elemento.getSucTelefono().getIdSucTelefono() == null) {
					// if (elemento.getSucTelefono().getTelefono().getIdTelefono().equals(new Long(elemento.getSucTelefono().hashCode()))) {
					// elemento.getSucTelefono().getTelefono().setIdTelefono(null);
					// }
					elemento.getSucTelefono().getTelefono().setIdTelefono(null);
					sucursalEmpresa.getSucEmpresa().getSucTelefonos().add(elemento.getSucTelefono());
					// listaTel.add(elemento.getSucTelefono());
				}
			}
			Iterator ite = sucursalEmpresa.getEmail().iterator();
			sucursalEmpresa.getSucEmpresa().setSucEmails(new HashSet());
			while (ite.hasNext()) {
				EmailWrapper elemento = (EmailWrapper) ite.next();
				if (true) {
					sucursalEmpresa.getSucEmpresa().getSucEmails().add(elemento.getSucEmail());
				}
			}

			/***** Emiliano ******/
			Iterator iterDomicilio = sucursalEmpresa.getLstDomicilios().iterator();

			DomicilioWrapper domicilioWrapper;
			SucEmpresasXDomicilio sucEmprXDomicilio;
			while (iterDomicilio.hasNext()) {
				domicilioWrapper = (DomicilioWrapper) iterDomicilio.next();
				if (domicilioWrapper.boolCentral) {
					sucursalEmpresa.getSucEmpresa().setDomicilio(domicilioWrapper.getDomicilio());
				}
				if (domicilioWrapper.getDomicilio().getIdDomicilio() == null) {
					sucEmprXDomicilio = new SucEmpresasXDomicilio();
					sucEmprXDomicilio.setSucEmpresa(sucursalEmpresa.sucEmpresa);
					sucEmprXDomicilio.setDomicilio(domicilioWrapper.domicilio);
					sucursalEmpresa.getSucEmpresa().getSucEmpresaXDomicilio().add(sucEmprXDomicilio);
				}
			}
			/***** Emiliano ******/
			this.cargardomPrincipalList();
			sucursalEmpresa.setIdSucursalTabla(++indiceTabla);
			altaSucursal = false;
			sucursalEmpresa = null;
			muestroLista = true;
		}
	}


	public void editarSucursal() {

		tituloCorto = "Edicion de Sucursal";
		log.info("Ejecutando ==> editarSucursal()");
		int id = (new Long(Session.getRequestParameter("idSucAEdit"))).intValue();
		sucursalEmpresa = EmpresaUtil.buscarSucursal(sucursales, id);
		sucursalEmpresa.editarSucursal();
		muestroLista = false;
		altaSucursal = false;
	}


	public void verSucursal() {
		muestroLista = false;
		tituloCorto = "Datos de Sucursal";
		log.info("Ejecutando ==> verSucursal()");
		int idTelefono = (new Long(Session.getRequestParameter("idSucAEdit"))).intValue();
		sucursalEmpresa = EmpresaUtil.buscarSucursal(sucursales, idTelefono);
		muestroLista = false;
	}


	public void borrarSucursal() {
		tituloCorto = "";
		log.info("Ejecutando ==> borrarSucursal()");
		int idSucu = (new Long(Session.getRequestParameter("idSucAElim"))).intValue();
		sucursalEmpresa = EmpresaUtil.buscarSucursal(sucursales, idSucu);
		if (sucursales.size() > 1) {
			sucursales.remove(sucursalEmpresa);
			empresa.getSucEmpresas().remove(sucursalEmpresa.getSucEmpresa());
			sucursalEmpresa = null;
		}
		sucursalEmpresa = null;
	}


	private void cargarItems() {

		if (rubroItems.size() != rubroList.size()) {
			rubroItems = new ArrayList();
			rubroItems.add(new SelectItem(new Long(0), "Seleccionar País"));
			rubroItems.addAll(Util.cargarSelectItem(rubroList));
		}
	}


	public Long getIdRubro() {
		return empresa.getRubro().getIdRubro();
	}


	public void setIdRubro(Long id) {
		empresa.getRubro().setIdRubro(id);
	}


	public Long getIdTamanio() {
		return empresa.getTamEmpresa().getIdTamanioEmp();
	}


	public void setIdTamanio(Long id) {
		empresa.getTamEmpresa().setIdTamanioEmp(id);
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public List getListaTamanios() {
		return listaTamanios;
	}


	public void setListaTamanios(List listaTamanios) {
		this.listaTamanios = listaTamanios;
	}


	public Sucursal getSucursalEmpresa() {
		return sucursalEmpresa;
	}


	public void setSucursal(Sucursal sucEmpresa) {
		sucursalEmpresa = sucEmpresa;
	}


	public List getSucursales() {
		return sucursales;
	}


	public void setSucursales(List sucursales) {
		this.sucursales = sucursales;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public String inicializarNuevaEmpresa(Long cuit, int origen) {
		borrar();
		this.origen = origen;
		empresa.setCuit(cuit);
		alta = true;
		altaSucursal = true;
		altaNavegacion = true;
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/empresa/empresaPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',900,700), 'titlebar=no';");
		return null;
	}


	public void borrar() {
		tituloLargo = "TARJETA FIEL - Alta de Empresa";
		tituloCorto = "";
		popup.borrar();
		alta = true;
		origen = 0;
		altaSucursal = true;
		error.borrar();
		empresa = new Empresa();
		empresa.setCuit(new Long(0));
		empresa.setRubro(new Rubro());
		empresa.setTamEmpresa(new TamEmpresa(new Long(0)));
		empresa.getRubro().setIdRubro(new Long(0));
		empresa.setSucEmpresas(new HashSet());
		empresaList = new ArrayList();
		sucursalEmpresa = null;
		sucursales = new ArrayList();
		sucEmpresasXDomicilio = new ArrayList();
		muestroLista = true;
		esRiesgosa = false;
		idDomPrincipal = new Long(0);
		domPrincipalItems = new ArrayList();
		domPrincipalList = new ArrayList();
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		if (validar()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar();window.close();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		}
	}


	/*
	 * Change Listeners para los cambios en las listas desplegables.
	 */

	public boolean validar() {
		error.borrar();
		
		CodComercioBean beanComercio = (CodComercioBean) Session.getBean("CodComercioBean");
		
		if (empresa.getRazonSocial() == null || empresa.getRazonSocial() == "") {
			error.agregar(Error.RAZON_SOCIAL_REQUERIDA);
		}

		// if (this.getIdTamanio().equals(null)||this.getIdTamanio().equals(new Long(0))) {
		// error.agregar(Error.TAMANIO_EMPRESA_REQUERIDO);
		// }
		if (sucursales == null || sucursales.size() < 1) {
			error.agregar(Error.SUCURSAL_REQUERIDA);
		}
		
		if (alta  && idDomPrincipal.equals(new Long(0)))
		{
			error.agregar(Error.DOMICILIO_LEGAL_REQUERIDO);
		}
		
		if (!alta
				&& beanComercio.transaccionesService.getCodComercioService()
				.compraAliasCodigoPosnet(beanComercio.getCodComercio().getIdCodComercio().toString()) == null
				&& idDomPrincipal.equals(new Long(0)))
		{
			error.agregar(Error.DOMICILIO_LEGAL_REQUERIDO);
		}
		
		try {
			CuitValido cuitValido = new CuitValido(empresa.getCuit().toString());
		} catch (CuitNoValidoException e) {
			error.agregar(Error.NUMERO_DE_CUIT_NO_VALIDO);
			e.printStackTrace();
		} catch (StringIndexOutOfBoundsException ae) {
			error.agregar(Error.NUMERO_DE_CUIT_NO_VALIDO);
		}
		// en lo siguiente, controlamos si es un alta que la empresa no exista, si es una edicion que no le ponga el cuit de una empresa ya existente.
		try {
			if (alta) {
				List empresasExistentes = generalService.getEmpresaService().getEmpresa(new Filtro("cuit", Filtro.IGUAL, empresa.getCuit()));
				Iterator iterEmpresas = empresasExistentes.iterator();
				while (iterEmpresas.hasNext()) {
					Empresa empre = (Empresa) iterEmpresas.next();
					if (empre.getCuit().equals(empresa.getCuit())) {
						error.agregar(Error.EMPRESA_REPETIDA);
					}
					break;
				}
			} else {
				Filtro filtro = new Filtro();
				filtro = new Filtro("idEmpresa", Filtro.NOTIN, empresa.getIdEmpresa());
				filtro.agregarCampoOperValor("cuit", Filtro.IGUAL, empresa.getCuit());
				List empresasExistentes = generalService.getEmpresaService().getEmpresa(filtro);
				Iterator iterEmpresas = empresasExistentes.iterator();
				while (iterEmpresas.hasNext()) {
					Empresa empre = (Empresa) iterEmpresas.next();
					if (empre.getCuit().equals(empresa.getCuit()) && empre.getIdEmpresa().equals(empresa.getIdEmpresa())) {
						error.agregar(Error.EMPRESA_REPETIDA);
					}
					break;
				}
			}
		} catch (EmpresaException ee) {
			log.info("Ocurrio un error al intentar recuperar las empresas de la base de datos");
			ee.printStackTrace();
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String inicializarDesdePopup(Empresa empres) {
		alta = false;
		altaSucursal = false;
		if (empres.getEsRiesgoza().charValue() == 'S')
			esRiesgosa = true;
		else
			esRiesgosa = false;

		// seteo las sucursales que tiene
		empresa = empres;
		
		
		
		sucursales = new ArrayList();
		Iterator it = empres.getSucEmpresas().iterator();
		while (it.hasNext()) {
			SucEmpresa empre = (SucEmpresa) it.next();
			Sucursal suc = new Sucursal();
			suc.setIdSucursalTabla(indiceTabla++);
			suc.setSucEmpresa(empre);
			suc.cargarDomicilios();
			suc.editarSucursal();
			sucursales.add(suc);
		}
		this.cargardomPrincipalList();
		return null;
	}


	public String inicializar(Empresa empres) {
		if (empres.getEsRiesgoza().charValue() == 'S')
			esRiesgosa = true;
		else
			esRiesgosa = false;

		// seteo las sucursales que tiene
		sucursales = new ArrayList();
		sucEmpresasXDomicilio = new ArrayList();
		Iterator it = empres.getSucEmpresas().iterator();
		while (it.hasNext()) {
			SucEmpresa empre = (SucEmpresa) it.next();
			Sucursal suc = new Sucursal();
			suc.setIdSucursalTabla(indiceTabla++);
			suc.setSucEmpresa(empre);
			suc.editarSucursal();
			sucursales.add(suc);
		}
		return "amEmpresa";
	}


	/**
	 * A continuacion los metodos para agregar, editar y borrar un telefono a la sucursal
	 */
	public String agregarTelefono() {
		log.info("Ejecutando ==> agregarTelefono()");
		TelefonoBean bean = (TelefonoBean) Session.getBean("TelefonoBean");
		bean.inicializar(TelefonoBean.SUC_EMPRESA, new Telefono());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");

		return null;
	}


	/********** Emiliano ************/

	public String agregarLstDomicilio() {
		log.info("Ir a agrega nuevo domicilio a la lista de Domicilios!!!");
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		bean.inicializar(DomicilioBean.SUC_EMPRESA, new Domicilio(), false);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");

		return null;
	}


	public void agregarDomicilioWrapper(Domicilio dom) {
		sucursalEmpresa.getLstDomicilios().add(new DomicilioWrapper(dom));
	}


	/********* \Emiliano ************/

	// Si el domicilio es null agrega uno, si no lo edita.

	public String agregarDomicilioPopup() {
		log.info("Ir a agrega nuevo domicilio a la sucursal!!!");
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		if (sucursalEmpresa.getSucEmpresa().getDomicilio() == null)
			sucursalEmpresa.getSucEmpresa().setDomicilio(new Domicilio());
		bean.inicializar(DomicilioBean.SUC_EMPRESA, sucursalEmpresa.getSucEmpresa().getDomicilio());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	public boolean getMuestroLista() {
		return muestroLista;
	}


	public void setMuestroLista(boolean v) {
		muestroLista = v;
	}


	public boolean isEsRiesgosa() {
		return esRiesgosa;
	}


	public void setEsRiesgosa(boolean esRiesgosa) {
		this.esRiesgosa = esRiesgosa;
	}


	public List getListaAutonomos() {
		return listaAutonomos;
	}


	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amEmpresa";

	}


	public String getCuitEmpresa() {
		return cuitEmpresa;
	}


	public void setCuitEmpresa(String cuitEmpresa) {
		this.cuitEmpresa = cuitEmpresa;
	}


	public String getRazonSocialEmpresa() {
		return razonSocialEmpresa;
	}


	public void setRazonSocialEmpresa(String razonSocialEmpresa) {
		this.razonSocialEmpresa = razonSocialEmpresa;
	}


	public List getEmpresaList() {
		return empresaList;
	}


	public void setEmpresaList(List empresaList) {
		this.empresaList = empresaList;
	}


	public Long getIdEmpresaHidden() {
		return idEmpresaHidden;
	}


	public void setIdEmpresaHidden(Long idEmpresaHidden) {
		this.idEmpresaHidden = idEmpresaHidden;
	}


	public boolean isAltaSucursal() {
		return altaSucursal;
	}


	public void setAltaSucursal(boolean altaSucursal) {
		this.altaSucursal = altaSucursal;
	}


	public String getCuitEditado() {
		return cuitEditado;
	}


	public void setCuitEditado(String cuitEditado) {
		this.cuitEditado = cuitEditado;
	}


	public int getOrigen() {
		return origen;
	}


	public void setOrigen(int origen) {
		this.origen = origen;
	}


	public List getRubroItems() {
		return rubroItems;
	}


	public void setRubroItems(List rubroItems) {
		this.rubroItems = rubroItems;
	}


	public List getRubroList() {
		return rubroList;
	}


	public void setRubroList(List rubroList) {
		this.rubroList = rubroList;
	}


	public HtmlSelectOneMenu getRubro() {
		return rubro;
	}


	public void setRubro(HtmlSelectOneMenu rubro) {
		this.rubro = rubro;
	}


	public HtmlSelectOneMenu getActividad() {
		return actividad;
	}


	public void setActividad(HtmlSelectOneMenu actividad) {
		this.actividad = actividad;
	}


	public List getDomPrincipalItems() {
		return domPrincipalItems;
	}


	public void setDomPrincipalItems(List domPrincipalItems) {
		this.domPrincipalItems = domPrincipalItems;
	}


	public Long getIdDomPrincipal() {
		return idDomPrincipal;
	}


	public void setIdDomPrincipal(Long idDomPrincipal) {
		this.idDomPrincipal = idDomPrincipal;
	}


	public boolean validarDomicilioPrincipal()
	{
		if (origen == CODCOMERCIO) {
			Iterator iterDomicilio = sucursalEmpresa.getLstDomicilios().iterator();
			boolean validarDomicilio = false;
			DomicilioWrapper domicilioWrapper;
			for (int i = 0; iterDomicilio.hasNext(); i++) {
				domicilioWrapper = (DomicilioWrapper) iterDomicilio.next();
				if (domicilioWrapper == null || domicilioWrapper.isBoolCentral()) {
					validarDomicilio = true;
				}
			}
			if (!validarDomicilio) {
				error.agregar(Error.DOMICILIO_CENTRAL_REQUERIDA);
			}
		}
		log.info("Intentando agregar la sucursal, hay " + error.cantidad() + " errores.");
		return (error.cantidad() == 0);
	}


	public void setAltaNavegacion(boolean altaNavegacion) {
		this.altaNavegacion = altaNavegacion;
	}


	public boolean isAltaNavegacion() {
		return altaNavegacion;
	}

	public class DomicilioWrapper {
		private int codigo;
		private boolean boolCentral;
		private Domicilio domicilio;
		private boolean boolLegal;


		public DomicilioWrapper(Domicilio dom) {
			this.codigo = ++indiceTablaDomicilios;
			this.boolCentral = false;
			this.domicilio = dom;
			this.boolLegal = false;
		}


		public int getCodigo() {
			return codigo;
		}


		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}


		public Domicilio getDomicilio() {
			return domicilio;
		}


		public void setDomicilio(Domicilio domicilio) {
			this.domicilio = domicilio;
		}


		public boolean isBoolCentral() {
			return boolCentral;
		}


		public void setBoolCentral(boolean boolCentral) {
			this.boolCentral = boolCentral;
		}


		public String editarLstDomicilio() {
			log.info("id a buscar: " + codigo);
			DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
			bean.inicializar(DomicilioBean.SUC_EMPRESA, this.domicilio, true);
			String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
			ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
			return null;
		}


		public String eliminarLstDomicilio() {
			/*
			 * sucursalEmpresa.getLstDomicilios().remove(this); Session.redirect("/tarjetafiel/general/empresa/empresaPopup.jsf"); return null;
			 */
			if (!this.boolCentral)
			{
				if (!this.boolLegal)
				{
					Domicilio domicilioNegocio = EmpresaUtil.buscarDomicilio(sucursalEmpresa.getLstDomicilios(), this.codigo);
					sucursalEmpresa.setLstDomicilios(EmpresaUtil.eliminarDomicilio(sucursalEmpresa.getLstDomicilios(), this.codigo));
					Iterator iterator = sucursalEmpresa.getSucEmpresa().getSucEmpresaXDomicilio().iterator();
					SucEmpresasXDomicilio sucEmpXDomicilio = null;
					while (iterator.hasNext()) {
						sucEmpXDomicilio = (SucEmpresasXDomicilio) iterator.next();
						if (sucEmpXDomicilio.getDomicilio().getIdDomicilio() == domicilioNegocio.getIdDomicilio()) {
							break;
						}
					}
					sucursalEmpresa.getSucEmpresa().getSucEmpresaXDomicilio().remove(sucEmpXDomicilio);
					Session.redirect("/tarjetafiel/general/empresa/empresaPopup.jsf");
				}
				else
				{
					error.agregar(Error.DOMICILIO_LEGAL_NO_ELIMINAR);
					log.info("Intentando editar la sucursal, hay " + error.cantidad() + " errores.");
				}
			}
			else
			{
				error.agregar(Error.DOMICILIO_CENTRAL_REQUERIDA);
				log.info("Intentando editar la sucursal, hay " + error.cantidad() + " errores.");
			}
			return null;
		}


		private void setBoolLegal(boolean boolLegal) {
			this.boolLegal = boolLegal;
		}


		private boolean isBoolLegal() {
			return boolLegal;
		}

	}

	public class Sucursal {
		private int idSucursalTabla = 0;
		private SucEmpresa sucEmpresa;
		private List telefonos;
		private List email;
		private List lstDomicilios;
		private Long autonomoSeleccionado;
		private Autonomo autonomo;
		private Long idRubroSeleccionado;
		private Long idActividadSeleccionada;
		private List rubroItem;
		private List actividadItem;
		private List actividadesList;
		private ActividadSucursal actividadEditada;
		private int tamanioLstDomicilio;


		public Sucursal() {
			sucEmpresa = new SucEmpresa();
			sucEmpresa.setDomicilio(new Domicilio());
			telefonos = new ArrayList();
			lstDomicilios = new ArrayList();
			email = new ArrayList();
			rubroItem = new ArrayList();
			actividadItem = new ArrayList();
			actividadesList = new ArrayList();
			actividadEditada = null;
			tamanioLstDomicilio = 0;
			cargarListas();
		}


		private void cargarListas() {
			try {
				rubroItem.clear();
				// paisItem.add(new SelectItem(new Long(0), "Seleccionar País"));
				rubroItem.addAll(Util.cargarSelectItem(generalService.getActividadRubroService().getActividadRubro(new Filtro())));
				idRubroSeleccionado = new Long(1);
				rubro.setValue(idRubroSeleccionado);
				actividadItem.clear();
				actividadItem.add(new SelectItem(new Long(0), "Seleccionar Actividad"));
				// provinciaItem.addAll(Util.cargarSelectItem(provinciaList));
				actividadItem.addAll(Util.cargarSelectItem(generalService.getActividadSucursalService().getActividadSucursal(
						new Filtro("actividadRubro.idActividadRubro", Filtro.IGUAL, idRubroSeleccionado))));
				idActividadSeleccionada = new Long(1);
				actividad.setValue(idActividadSeleccionada);
				actividadesList.clear();
				actividadesList = generalService.getActividadSucursalService().getActividadSucursal(new Filtro());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		public void cargarDomicilios() {
			/******* Emiliano ********/
			SucEmpresasXDomicilio sucEmpresasXDomicilioAux;
			DomicilioWrapper domicilioWrapper;
			lstDomicilios.clear();
			Iterator iteratorSucursalXDomicilio = sucEmpresa.getSucEmpresaXDomicilio().iterator();
			while (iteratorSucursalXDomicilio.hasNext()) {
				sucEmpresasXDomicilioAux = (SucEmpresasXDomicilio) iteratorSucursalXDomicilio.next();
				domicilioWrapper = new DomicilioWrapper(sucEmpresasXDomicilioAux.getDomicilio());
				if (sucEmpresa.getDomicilio().getIdDomicilio().equals(sucEmpresasXDomicilioAux.getDomicilio().getIdDomicilio())) {
					domicilioWrapper.setBoolCentral(true);
				}

				if (empresa.getDomicilioLegal() != null
						&& empresa.getDomicilioLegal().getIdDomicilio().equals(sucEmpresasXDomicilioAux.getDomicilio().getIdDomicilio())) {
					idDomPrincipal = empresa.getDomicilioLegal().getIdDomicilio();
					domicilioWrapper.setBoolLegal(true);
					idDomPrincipal = new Long(domicilioWrapper.getCodigo());
				}
				lstDomicilios.add(domicilioWrapper);
			}
			/******* Emiliano ********/
		}


		public void editarSucursal() {
			autonomo = sucEmpresa.getAutonomo();
			// autonomoSeleccionado = autonomo.getIdAutonomo();
			// sucEmpresa.getDomicilio().getBarrio().getLocalidad().getPartido().getProvincia();

			telefonos = new ArrayList();
			Iterator iterador = sucEmpresa.getSucTelefonos().iterator();
			while (iterador.hasNext()) {
				SucTelefono tel = (SucTelefono) iterador.next();
				telefonos.add(new TelefonoWrapper(tel));
			}
			email = new ArrayList();
			Iterator iteradorDos = sucEmpresa.getSucEmails().iterator();
			while (iteradorDos.hasNext()) {
				SucEmail mai = (SucEmail) iteradorDos.next();
				EmailWrapper elemDos = new EmailWrapper(indiceTablaEmails++, mai);
				email.add(elemDos);
			}
			actividadEditada = sucEmpresa.getActividadSucursal();
			if (sucEmpresa.getActividadSucursal() != null) {
				idRubroSeleccionado = sucEmpresa.getActividadSucursal().getActividadRubro().getIdActividadRubro();
				rubro.setValue(idRubroSeleccionado);
				cambiarActividadesSuc(null);
				idActividadSeleccionada = sucEmpresa.getActividadSucursal().getIdActividadSucursal();
				actividad.setValue(idActividadSeleccionada);
			}
			// ActividadSucursal actSucursal= sucEmpresa.getActividadSucursal();
		}


		public void cambiarActividadesSuc(ValueChangeEvent event) {
			Long rubroSeleccionado = new Long(rubro.getValue().toString());
			try {
				actividadItem.clear();
				actividadItem.add(new SelectItem(new Long(0), "Seleccionar Actividad"));
				actividadItem.addAll(Util.cargarSelectItem(generalService.getActividadSucursalService().getActividadSucursal(
						new Filtro("actividadRubro.idActividadRubro", Filtro.IGUAL, rubroSeleccionado))));
				idActividadSeleccionada = new Long(0);
				actividad.setValue(idActividadSeleccionada);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		public Autonomo getAutonomo() {
			return autonomo;
		}


		public int getTamanioLstDomicilio() {
			if (this.lstDomicilios != null) {
				return this.lstDomicilios.size();
			} else {
				return 0;
			}
		}


		public void setTamanioLstDomicilio(int tamanioLstDomicilio) {
			this.tamanioLstDomicilio = tamanioLstDomicilio;
		}


		public void setAutonomo(Autonomo autonomo) {
			this.autonomo = autonomo;
		}


		public Long getAutonomoSeleccionado() {
			return autonomoSeleccionado;
		}


		public void setAutonomoSeleccionado(Long id) {
			this.autonomoSeleccionado = id;
		}


		public List getEmail() {
			return email;
		}


		public void setEmail(List email) {
			this.email = email;
		}


		public SucEmpresa getSucEmpresa() {
			return sucEmpresa;
		}


		public void setSucEmpresa(SucEmpresa sucEmpresa) {
			this.sucEmpresa = sucEmpresa;
		}


		public List getTelefonos() {
			return telefonos;
		}


		public void setTelefonos(List telefonos) {
			this.telefonos = telefonos;
		}


		public int getIdSucursalTabla() {
			return idSucursalTabla;
		}


		public void setIdSucursalTabla(int idSucursalTabla) {
			this.idSucursalTabla = idSucursalTabla;
		}


		public Long getIdRubroSeleccionado() {
			return idRubroSeleccionado;
		}


		public void setIdRubroSeleccionado(Long idRubroSeleccionado) {
			this.idRubroSeleccionado = idRubroSeleccionado;
		}


		public Long getIdActividadSeleccionada() {
			return idActividadSeleccionada;
		}


		public void setIdActividadSeleccionada(Long idActividadSeleccionada) {
			this.idActividadSeleccionada = idActividadSeleccionada;
		}


		public List getRubroItem() {
			return rubroItem;
		}


		public void setRubroItem(List rubroItem) {
			this.rubroItem = rubroItem;
		}


		public List getActividadItem() {
			return actividadItem;
		}


		public void setActividadItem(List actividadItem) {
			this.actividadItem = actividadItem;
		}


		public List getActividadesList() {
			return actividadesList;
		}


		public void setActividadesList(List actividadesList) {
			this.actividadesList = actividadesList;
		}


		public ActividadSucursal getActividadEditada() {
			return actividadEditada;
		}


		public void setActividadEditada(ActividadSucursal actividadEditada) {
			this.actividadEditada = actividadEditada;
		}


		public List getLstDomicilios() {
			return lstDomicilios;
		}


		public void setLstDomicilios(List lstDomicilios) {
			this.lstDomicilios = lstDomicilios;
		}
	}

	public class TelefonoWrapper {

		private SucTelefono sucTelefono;


		public TelefonoWrapper(SucTelefono sucTelefono) {
			this.sucTelefono = sucTelefono;
		}


		public SucTelefono getSucTelefono() {
			return sucTelefono;
		}


		public void setSucTelefono(SucTelefono sucTelefono) {
			this.sucTelefono = sucTelefono;
		}


		/*
		 * Este metodo se utiliza para editar el telefono.
		 */
		public String editarTelefono() {
			TelefonoBean bean = (TelefonoBean) Session.getBean("TelefonoBean");
			bean.inicializar(TelefonoBean.SUC_EMPRESA, this.sucTelefono.getTelefono());
			String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
			ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");
			return null;
		}


		/*
		 * Este metodo se utiliza para poder eliminar un telefono de la lista de telefonos
		 */
		public String eliminarTelefono() {
			sucursalEmpresa.getSucEmpresa().getSucTelefonos().remove(this.sucTelefono);
			sucursalEmpresa.getTelefonos().remove(this);
			mostrarSucursal();
			return null;
		}

	}
}
