package com.bizitglobal.webapp.faces.beans.evaluacion;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.evaluacion.exception.PromotorDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.PromotorException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Promotor;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.PromotorTelefono;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Email;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.Util.PromotorEvaluacionUtil;
import com.bizitglobal.webapp.faces.beans.general.DomicilioBean;
import com.bizitglobal.webapp.faces.beans.general.TelefonoBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class PromotorEvaluacionBean extends BaseBean {

	private static final Logger log = Logger.getLogger(PromotorEvaluacionBean.class);
	// Promotor que contiene las propiedades para el bean.
	private Promotor promotor;
	private Domicilio domicilio;
	// Listas del bean
	private List listaTelefonosPromotor;

	private List listTelefono; // contiene objetos PromoTelefonos
	private List listaPromotores;
	private Long idPromotorHidden;
	private String idPromotor;
	private String apellido;
	private List listaEstados;


	public PromotorEvaluacionBean() {

		super();
		borrar();

	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public Promotor getPromotor() {
		return promotor;
	}


	public void setPromotor(Promotor promotor) {
		this.promotor = promotor;
	}


	public List getListaPromotores() {
		return listaPromotores;
	}


	public void setListaPromotores(List listaPromotores) {
		this.listaPromotores = listaPromotores;
	}


	public Domicilio getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}


	public Long getIdPromotorHidden() {
		return idPromotorHidden;
	}


	public void setIdPromotorHidden(Long idPromotorHidden) {
		this.idPromotorHidden = idPromotorHidden;
	}


	public List getListaTelefonosPromotor() {
		return listaTelefonosPromotor;
	}


	public void setListaTelefonosPromotor(List object) {
		this.listaTelefonosPromotor = object;
	}


	public List getListaEstados() {
		return listaEstados;
	}


	public void setListaEstados(List listaEstados) {
		this.listaEstados = listaEstados;
	}


	public Date getFechaBaja() {
		return promotor.getFechaBaja();
	}


	public void setFechaBaja(Date fechaBaja) {
		promotor.setFechaBaja(new Timestamp(fechaBaja.getTime()));
	}


	public String getEstadoSeleccionado() {
		if (promotor.getEstado() == null) {
			return "I";
		} else {
			return promotor.getEstado();
		}
	}


	public void setEstadoSeleccionado(String estadoSeleccionado) {
		promotor.setEstado(estadoSeleccionado);
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE PROMOTORES
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		cargarListas();
		return "amPromotores";
	}


	private void cargarListas() {
		listaEstados.clear();
		listaEstados.add(new SelectItem("A", "Activo"));
		listaEstados.add(new SelectItem("I", "Inactivo"));
		listaEstados.add(new SelectItem("B", "Baja"));
	}


	public String editarPromotor() {
		String result = null;
		borrarBaseBean();
		borrar();
		cargarListas();
		alta = false;
		tituloCorto = "Modificar Promotor";
		try {
			promotor = evaluacionService.getPromotorService().leerPromotor(idPromotorHidden);
			if (promotor.getDomicilio() != null)
				promotor.getDomicilio().getBarrio().getLocalidad().getPartido().getProvincia().getPais();
			else
				promotor.setDomicilio(new Domicilio());
			if (promotor.getEmail() != null)
				promotor.getEmail().getEmail();
			else
				promotor.setEmail(new Email());
			promotor.getEmail().getEmail();
			Iterator it = promotor.getPromoTelefonos().iterator();
			while (it.hasNext()) {
				PromotorTelefono pT = (PromotorTelefono) it.next();
				pT.getTelefono().getTipo();
			}
			listTelefono = Convertidores.setToList(promotor.getPromoTelefonos());
			result = "amPromotores";
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el Promotor");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/evaluacion/listarPromotores.jsf");
		}
		return result;
	}


	public String eliminarPromotor() {
		try {
			evaluacionService.getPromotorService().borrarPromotor(idPromotorHidden);
		} catch (PromotorException e1) {
			error.agregar(e1.getMessage());
		} catch (ConstraintViolationException e2) {
			error.agregar("No se puede eliminar el promotor, ya que esta siendo utilizado en otras relaciones.");
		} catch (Exception e4) {
			error.agregar("A ocurrido un error al intentar eliminar el promotor.");
			e4.printStackTrace();
		}
		listarPromotores();
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				promotor.setPromoTelefonos(new HashSet());
				Iterator i = listTelefono.iterator();
				while (i.hasNext()) {
					PromotorTelefono promTel = (PromotorTelefono) i.next();
					if (promTel.getIdPromotorTelefono().equals(new Long(promTel.getTelefono().hashCode()))) {
						promTel.getTelefono().setIdTelefono(null);
						promTel.setIdPromotorTelefono(null);
					}
					promotor.getPromoTelefonos().add(promTel);
				}
				if (promotor.getEstado() != null && promotor.getEstado().equals("B")) {
					promotor.setFechaBaja(new Timestamp(Calendar.getInstance().getTime().getTime()));
				}
				if (alta) {
					evaluacionService.getPromotorService().grabarPromotor(promotor);
				} else {
					evaluacionService.getPromotorService().actualizarPromotor(promotor);
				}
				popup.setPopup(popup.ICONO_OK, "El Promotor ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (PromotorDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, e1.getMessage());
			e1.printStackTrace();
		} catch (PromotorException e2) {
			popup.setPopup(popup.ICONO_FALLA, e2.getMessage());
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del Promotor.");
			e3.printStackTrace();
		}
		apellido = "";
		idPromotor = "";
		return "listarPromotores";
	}


	public void borrar() {
		borrarBaseBean();
		apellido = "";
		idPromotor = "";
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Promotor";
		promotor = new Promotor();
		promotor.setEmail(new Email());
		promotor.setPromoTelefonos(new HashSet());
		promotor.setDomicilio(new Domicilio());
		promotor.setEstado("A");
		listTelefono = new ArrayList();
		listaPromotores = new ArrayList();
		listaEstados = new ArrayList();
	}


	public String agregarDomicilioPopup() {
		log.info("Ir a agrega nuevo domicilio al Promotor!!!");
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		bean.inicializar(DomicilioBean.PROMOTOR, promotor.getDomicilio());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	public String cancelar() {
		Session.redirect("/tarjetafiel/evaluacion/listarPromotores.jsf");
		return "";
	}


	public boolean validar() {
		error.borrar();
		if (promotor.getNombre() == null || promotor.getNombre().equals(""))
			error.agregar(Error.AMPROMOTOR_NOMBRE_REQUERIDO);
		if (promotor.getApellido() == null || promotor.getApellido().equals(""))
			error.agregar(Error.AMPROMOTOR_APELLIDO_REQUERIDO);
		if (promotor.getDomicilio() == null || promotor.getDomicilio().equals(new Domicilio()))
			error.agregar(Error.AMPROMOTOR_DOMICILIO_REQUERIDO);

		if (promotor.getEmail().getEmail() == null || promotor.getEmail().getEmail().equals(""))
			error.agregar(Error.AMPROMOTOR_MAIL_REQUERIDO);
		else {
			if (!Validador.checkEmail(promotor.getEmail().getEmail()))
				error.agregar(Error.AMPROMOTOR_MAIL_INCORRECTO);
		}

		Filtro filtro = new Filtro("nombre", Filtro.LIKEXS, promotor.getNombre());
		if (alta) {
			filtro.agregarCampoOperValor("apellido", Filtro.LIKEXS, promotor.getApellido());
			try {
				List unPromotor = evaluacionService.getPromotorService().getPromotor(filtro);
				if (!unPromotor.isEmpty())
					error.agregar(Error.AMPROMOTOR_PROMOTOR_REPETIDA);
			} catch (PromotorException e) {
				e.printStackTrace();
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoPromotor() {
		return inicializar();
	}


	public String irAModificarPromotor() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Promotor";
		return null;
	}


	public String irAListarPromotor() {
		borrar();
		tituloCorto = "Listado de Promotores";
		Session.redirect("/tarjetafiel/evaluacion/listarPromotores.jsf");
		return "";
	}


	public String listarPromotores() {
		listaPromotores = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (idPromotor != null && !idPromotor.equals(""))
				filtro.agregarCampoOperValor("idPromotor", Filtro.IGUAL, new Long(this.idPromotor));
			if (apellido != null && !apellido.equals(""))
				filtro.agregarCampoOperValor("apellido", Filtro.LIKE, apellido);
			listaPromotores = evaluacionService.getPromotorService().getPromotor(filtro);
			Iterator iter = listaPromotores.iterator();
			while (iter.hasNext())
			{
				Promotor element = (Promotor) iter.next();
				try {
					//element.getDomicilio();// .getBarrio().getLocalidad(); //.getPartido().getProvincia().getPais();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			idPromotor = "";
			promotor.setNombre("");
			apellido = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/evaluacion/listarPromotores.jsf");
		return null;
	}


	public String getIdPromotor() {
		return idPromotor;
	}


	public void setIdPromotor(String idPromotor) {
		this.idPromotor = idPromotor;
	}


	public String agregarTelefono() {
		log.info("Ejecutando ==> agragrTelefono()");

		TelefonoBean bean = (TelefonoBean) Session.getBean("TelefonoBean");
		bean.inicializar(TelefonoBean.PROMOTOR, new Telefono());

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");

		return null;
	}


	/*
	 * Este metodo se utiliza para poder eliminar un telefono de la lista de telefonos
	 */
	public String eliminarTelefono() {
		Long idTelefonoPro = new Long(Session.getRequestParameter("idTelefono"));
		listTelefono = PromotorEvaluacionUtil.eliminarPromotorTelefono(listTelefono, idTelefonoPro);
		return null;
	}


	/*
	 * Este metodo se utiliza para editar el telefono.
	 */
	public String editarTelefono() {
		Long idTelefonoPro = new Long(Session.getRequestParameter("idTelEdi"));
		log.info("id a buscar: " + idTelefonoPro);
		TelefonoBean bean = (TelefonoBean) Session.getBean("TelefonoBean");
		PromotorTelefono promotorTelefono = PromotorEvaluacionUtil.buscarPromotorTelefono(listTelefono, idTelefonoPro);
		bean.inicializar(TelefonoBean.PROMOTOR, promotorTelefono.getTelefono());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");
		return null;
	}


	public List getListTelefono() {
		return listTelefono;
	}


	public void setListTelefono(List listTelefono) {
		this.listTelefono = listTelefono;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}