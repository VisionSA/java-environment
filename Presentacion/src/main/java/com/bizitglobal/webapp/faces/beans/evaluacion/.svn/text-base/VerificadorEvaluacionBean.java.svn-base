package com.bizitglobal.webapp.faces.beans.evaluacion;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.evaluacion.exception.VerificadorDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.VerificadorException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Verificador;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.VerificadorTelefono;
import com.bizitglobal.tarjetafiel.general.exception.PartidoException;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Email;
import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.Util.VerificadorEvaluacionUtil;
import com.bizitglobal.webapp.faces.beans.general.DomicilioBean;
import com.bizitglobal.webapp.faces.beans.general.DomicilioUtil;
import com.bizitglobal.webapp.faces.beans.general.TelefonoBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.service.general.GeneralServiceFaces;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


/**
 * @author Administrator
 * 
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class VerificadorEvaluacionBean extends BaseBean {
	private static final Logger log = Logger.getLogger(VerificadorEvaluacionBean.class);
	private GeneralServiceFaces service = generalService;

	private Long idVerificadorHidden;
	private Verificador verificador;
	private List listaVerificadores;
	private List listTelefonos;
	private List listaEstados;

	// Atributos usados por el filtro
	private String idVerificador;
	private String apellido;

	// binding para objetos jsf
	HtmlSelectOneMenu partidos;
	HtmlSelectOneMenu paises;
	HtmlSelectOneMenu provincias;

	// propiedades para las listas desplegables.
	private List listaPartidos;
	private List listaProvincias;
	private List listaPaises;
	private List listaAuxProvincias;
	private List listaAuxPartidos;
	private List listaAuxPaises;
	private Long idPartidoSeleccionado;
	private Long idPaisSeleccionado;


	public VerificadorEvaluacionBean() {
		listaAuxPartidos = service.getPartidoDao().listarTodos();
		listaAuxProvincias = service.getProvinciaDao().listarTodos();
		listaAuxPaises = service.getPaisDao().listarTodos();
	}


	public String irANuevoVerificador() {
		return inicializar();
	}


	public String irAModificarVerificador() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Verificador";
		try {
			verificador = evaluacionService.getVerificadorService().leerVerificador(verificador.getId());
			verificador.getPartido().getProvincia().getPais().getIdPais();
		} catch (VerificadorException e) {
			e.printStackTrace();
		}
		cargarListas();

		return null;
	}


	public String irAListarVerificador() {
		borrar();
		tituloCorto = "Listado de Verificadores";
		Session.redirect("/tarjetafiel/evaluacion/listarVerificadores.jsf");
		return "";
	}


	public String listarVerificadores() {
		listaVerificadores = new ArrayList();
		try {
			Filtro filtro = new Filtro();
			if (idVerificador != null && !idVerificador.equals(""))
				filtro.agregarCampoOperValor("idVerificador", Filtro.IGUAL, new Long(this.idVerificador));
			if (apellido != null && !getApellido().equals(""))
				filtro.agregarCampoOperValor("apellido", Filtro.LIKE, this.apellido);
			listaVerificadores = evaluacionService.getVerificadorService().getVerificador(filtro);
			Iterator iter = listaVerificadores.iterator();
			while (iter.hasNext()) {
				Verificador element = (Verificador) iter.next();
				try {
					if (element.getDomicilio() != null)
						element.getDomicilio().getBarrio().getLocalidad().getPartido().getProvincia().getPais();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			idVerificador = "";
			apellido = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/evaluacion/listarVerificadores.jsf");
		return null;
	}


	/*
	 * este metodo se utiliza para limpiar todos los atributos de la clase. (non-Javadoc)
	 * 
	 * @see com.bizitglobal.webapp.faces.beans.BaseBean#borrar()
	 */
	public void borrar() {
		borrarBaseBean();
		listaVerificadores = new ArrayList();
		verificador = new Verificador();
		verificador.setIdVerificador(null);
		verificador.setDomicilio(new Domicilio());
		verificador.setEmail(new Email());
		verificador.setPartido(new Partido());
		listTelefonos = new ArrayList();
		// limpiar los binding para objetos jsf
		partidos = new HtmlSelectOneMenu();
		paises = new HtmlSelectOneMenu();
		provincias = new HtmlSelectOneMenu();
		idPaisSeleccionado = new Long(0);
		alta = true;
		idPartidoSeleccionado = new Long(0);
	}


	public String agregarDomicilioPopup() {
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		bean.inicializar(DomicilioBean.VERIFICADOR, verificador.getDomicilio());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/domicilio/domicilioPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");

		return null;
	}


	public String grabar() {
		try {
			Partido partido = service.getPartidoService().leerPartido(idPartidoSeleccionado);
			verificador.setPartido(partido);
		} catch (PartidoException e) {
			e.printStackTrace();
		}
		try {
			if (validar()) {
				verificador.setVerifTelefonos(new HashSet());
				Iterator i = listTelefonos.iterator();
				while (i.hasNext()) {
					VerificadorTelefono verifTel = (VerificadorTelefono) i.next();
					if (verifTel.getIdVerifTelefono().equals(new Long(verifTel.getTelefono().hashCode()))) {
						verifTel.setIdVerifTelefono(null);
					}
					verificador.getVerifTelefonos().add(verifTel);
				}
				if (verificador.getEstado() != null && verificador.getEstado().equals("B")) {
					verificador.setFechaBaja(new Timestamp(Calendar.getInstance().getTime().getTime()));
				}
				if (alta) {
					evaluacionService.getVerificadorService().grabarVerificador(verificador);
				} else {
					evaluacionService.getVerificadorService().actualizarVerificador(verificador);
				}
				popup.setPopup(popup.ICONO_OK, "El Verificador ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (VerificadorDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, e1.getMessage());
			e1.printStackTrace();
		} catch (VerificadorException e2) {
			popup.setPopup(popup.ICONO_FALLA, e2.getMessage());
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta del Verificador.");
			e3.printStackTrace();
		}
		return "";
	}


	public String cancelar() {
		Session.redirect("/tarjetafiel/evaluacion/listarVerificadores.jsf");
		return "";
	}


	/*
	 * este metodo se llama para inicializar los componetes del bean (non-Javadoc)
	 * 
	 * @see com.bizitglobal.webapp.faces.beans.BaseBean#inicializar()
	 */
	public String inicializar() {
		borrar();
		cargarListas();
		tituloCorto = "Alta Verificador";
		tituloLargo = "Tarjeta Fiel - Evaluación";
		return "amVerificadores";
	}


	public String editarVerificador() {
		String result = null;
		borrar();
		alta = false;
		tituloCorto = "Modificar Verificador";
		try {
			verificador = evaluacionService.getVerificadorService().leerVerificador(idVerificadorHidden);
			Iterator it = verificador.getVerifTelefonos().iterator();
			while (it.hasNext()) {
				VerificadorTelefono vT = (VerificadorTelefono) it.next();
				vT.getTelefono().getTipo().getDescripcion();
				vT.getVerificador().getPartido();
			}
			listTelefonos = Convertidores.setToList(verificador.getVerifTelefonos());
			if (verificador.getEmail() == null) {
				verificador.setEmail(new Email());
			} else {
				verificador.getEmail().getEmail();
			}
			cargarListas();
			result = "amVerificadores";
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el Verificador");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/evaluacion/listarVerificadores.jsf");
		}
		return result;
	}


	public String eliminarVerificador() {
		String result = null;
		try {
			verificador = evaluacionService.getVerificadorService().leerVerificador(idVerificadorHidden);
			evaluacionService.getVerificadorService().borrarVerificador(verificador);
			listarVerificadores();
		} catch (VerificadorException e) {
			error.agregar("Ocurrio un error al intentar eliminar el Verificador");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarVerificadores.jsf");
		}
		return result;
	}


	/*
	 * Este metodo se utiliza para agregar o editar el domicilio del Verificador.
	 */
	public String asignarDomicilio() {
		DomicilioBean bean = (DomicilioBean) Session.getBean("DomicilioBean");
		if (verificador.getDomicilio() == null) {
			bean.inicializar(DomicilioBean.VERIFICADOR, new Domicilio());
		} else {
			bean.inicializar(DomicilioBean.VERIFICADOR, verificador.getDomicilio());
		}
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
		verificador.setDomicilio(null);
		return null;
	}


	/*
	 * este metodo se utiliza para agregar un telefono al Verificador
	 */
	public String agregarTelefono() {
		TelefonoBean bean = (TelefonoBean) Session.getBean("TelefonoBean");
		bean.inicializar(TelefonoBean.VERIFICADOR, new Telefono());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");
		return null;
	}


	/*
	 * Este metodo se utiliza para poder eliminar un telefono de la lista de telefonos
	 */
	public String eliminarTelefono() {
		Long idTelefono = new Long(Session.getRequestParameter("idTelefonoVeri"));
		listTelefonos = VerificadorEvaluacionUtil.eliminarVerificadorTelefono(listTelefonos, idTelefono);
		return null;
	}


	/*
	 * Este metodo se utiliza para editar el telefono.
	 */
	public String editarTelefono() {
		Long idTelefono = new Long(Session.getRequestParameter("idTelEdiVeri"));
		log.info("id a buscar: " + idTelefono);
		TelefonoBean bean = (TelefonoBean) Session.getBean("TelefonoBean");
		VerificadorTelefono verAux = VerificadorEvaluacionUtil.buscarVerificadorTelefono(listTelefonos, idTelefono);
		bean.inicializar(TelefonoBean.VERIFICADOR, verAux.getTelefono());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/general/telefono/telefonoPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',650,250), 'titlebar=no';");
		return null;
	}


	public List getListTelefonos() {
		return listTelefonos;
	}


	public void setListTelefonos(List listTelefonos) {
		this.listTelefonos = listTelefonos;
	}


	public Verificador getVerificador() {
		return verificador;
	}


	public void setVerificador(Verificador verificador) {
		this.verificador = verificador;
	}


	private void borrarListas() {
		listaAuxPaises = new ArrayList();
		listaAuxPartidos = new ArrayList();
		listaAuxProvincias = new ArrayList();
		listaPaises = new ArrayList();
		listaPartidos = new ArrayList();
		listaProvincias = new ArrayList();
		listaEstados = new ArrayList();
	}


	public void cambiarProvincias(ValueChangeEvent event) {
		log.info("Ejecutando ==> cambiarProvincias");
		idPaisSeleccionado = new Long(paises.getValue().toString());
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


	/*
	 * public void cambiarProvincias(){ log.info("Ejecutando ==> cambiarProvincias"); idPaisSeleccionado = new Long(paises.getValue().toString());
	 * //if(!idPaisSeleccionado.equals(new Long(0))){ listaProvincias.clear(); listaProvincias.add(new SelectItem(new Long(0), "Seleccione Partido"));
	 * listaProvincias.addAll(DomicilioUtil.filtrarProvincias(listaAuxProvincias, idPaisSeleccionado)); partidos.setValue(new Long(0)); }
	 */

	public void cambiarPartidos(ValueChangeEvent event) {
		Long idProvinciaSeleccionada = new Long(provincias.getValue().toString());
		if (!idProvinciaSeleccionada.equals(new Long(0))) {
			listaPartidos.clear();
			listaPartidos.add(new SelectItem(new Long(0), "Seleccione Partido"));
			listaPartidos.addAll(DomicilioUtil.filtrarPartidos(listaAuxPartidos, idProvinciaSeleccionada));
		} else {
			setearListasPorDefecto();
		}
	}


	//
	// public void cambiarPartidos(){
	// Long idProvinciaSeleccionada = new Long(provincias.getValue().toString());
	// //if(!idProvinciaSeleccionada.equals(new Long(0))){
	// //// listaPartidos.clear();
	// listaPartidos.clear();
	// listaPartidos.add(new SelectItem(new Long(0), "Seleccione Partido"));
	// listaPartidos.addAll(DomicilioUtil.filtrarPartidos(listaAuxPartidos, idProvinciaSeleccionada));
	// /*}else{
	// setearListasPorDefecto();
	// } */
	// }

	private void setearListasPorDefecto() {
		paises.setValue(new Long(0));
		partidos.setValue(new Long(0));
		provincias.setValue(new Long(0));
	}


	public Long getProvinciaSeleccionada() {
		try {
			return verificador.getDomicilio().getLocalidad().getProvincia().getIdProvincia();
		} catch (Exception e) {
			return new Long(0);
		}
	}


	public void setProvinciaSeleccionada(Long provinciaSeleccionada) {
		verificador.getDomicilio().getLocalidad().getProvincia().setIdProvincia(provinciaSeleccionada);
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


	private void cargarListas() {
		borrarListas();
		listaEstados.clear();
		listaEstados.add(new SelectItem("A", "Activo"));
		listaEstados.add(new SelectItem("I", "Inactivo"));
		listaEstados.add(new SelectItem("B", "Baja"));
		listaAuxPartidos = service.getPartidoDao().listarTodos();
		listaAuxProvincias = service.getProvinciaDao().listarTodos();
		listaAuxPaises = service.getPaisDao().listarTodos();
		listaPartidos.add(new SelectItem(new Long(0), "Seleccionar Partido"));
		listaProvincias.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
		listaPaises.add(new SelectItem(new Long(0), "Seleccionar País"));
		listaPaises.addAll(DomicilioUtil.cargarListaPaises(listaAuxPaises));
		provincias.setValue(new Long(0));
		paises.setValue(new Long(0));
		if (!alta && verificador.getPartido() != null) {
			idPaisSeleccionado = verificador.getPartido().getProvincia().getPais().getIdPais();
			paises.setValue(idPaisSeleccionado);
			// cambiarProvincias();
			// cambiarPartidos();
			listaProvincias.addAll(DomicilioUtil.filtrarProvincias(listaAuxProvincias, idPaisSeleccionado));
			provincias.setValue(getProvinciaSeleccionada());
			// listaPartidos.clear();
			listaPartidos.addAll(DomicilioUtil.filtrarPartidos(listaAuxPartidos, getProvinciaSeleccionada()));
			idPartidoSeleccionado = verificador.getPartido().getIdPartido();
			partidos.setValue(idPartidoSeleccionado);
		}
	}


	public void levantarListaPais(Long idPais, Long idProvincia, Long idPartido, Long idLocalidad, Long idBarrio) {

		log.info("Pais Seleccionado: " + idPais);
		log.info("Provincia Seleccionada: " + idProvincia);
		log.info("Partido Seleccionada}o: " + idPartido);
		log.info("Localidad Seleccionada: " + idLocalidad);
		log.info("Barrio Seleccionada: " + idBarrio);

		listaProvincias.addAll(DomicilioUtil.filtrarProvincias(listaAuxProvincias, idPais));
		listaPartidos.addAll(DomicilioUtil.filtrarPartidos(listaAuxPartidos, idProvincia));

		paises.setValue(idPais);
		provincias.setValue(idProvincia);
		partidos.setValue(idPartido);
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


	public Long getIdPaisSeleccionado() {
		return idPaisSeleccionado;
	}


	public void setIdPaisSeleccionado(Long idPaisSeleccionado) {
		this.idPaisSeleccionado = idPaisSeleccionado;
	}


	public boolean validar() {
		error.borrar();
		if (verificador.getNombre() == null || verificador.getNombre().equals(""))
			error.agregar(Error.AMVERIFICADOR_NOMBRE_REQUERIDO);
		if (verificador.getApellido() == null || verificador.getApellido().equals(""))
			error.agregar(Error.AMVERIFICADOR_APELLIDO_REQUERIDO);
		if (verificador.getDomicilio() == null)
			error.agregar(Error.AMVERIFICADOR_DOMICILIO_REQUERIDO);
		if (verificador.getPartido() == null)
			error.agregar(Error.AMVERIFICADOR_PARTIDO_REQUERIDO);

		if (verificador.getEmail().getEmail() == null || verificador.getEmail().getEmail().equals(""))
			error.agregar(Error.AMVERIFICADOR_MAIL_REQUERIDO);
		else {
			if (!Validador.checkEmail(verificador.getEmail().getEmail()))
				error.agregar(Error.AMVERIFICADOR_MAIL_INCORRECTO);
		}

		// Filtro filtroVerif = new Filtro("nombre", Filtro.LIKEXS, verificador.getNombre());
		// if (alta) {
		// filtroVerif.agregarCampoOperValor("apellido", Filtro.LIKEXS, verificador.getApellido());
		// try {
		//
		// List unVerificador = evaluacionService.getVerificadorService().getVerificador(filtroVerif);
		// if(unVerificador!=null&!unVerificador.isEmpty())
		// error.agregar(Error.AMVERIFICADOR_VERIFICADOR_REPETIDA);
		// }
		// catch (VerificadorException e) {
		// e.printStackTrace();
		// }
		// }
		return (error.cantidad() == 0) ? true : false;
	}


	public Long getIdVerificadorHidden() {
		return idVerificadorHidden;
	}


	public void setIdVerificadorHidden(Long idVerificadorHidden) {
		this.idVerificadorHidden = idVerificadorHidden;
	}


	public List getListaVerificadores() {
		return listaVerificadores;
	}


	public void setListaVerificadores(List listaVerificadores) {
		this.listaVerificadores = listaVerificadores;
	}


	public String getIdVerificador() {
		return idVerificador;
	}


	public void setIdVerificador(String idVerificador) {
		this.idVerificador = idVerificador;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public List getListaEstados() {
		return listaEstados;
	}


	public void setListaEstados(List listaEstados) {
		this.listaEstados = listaEstados;
	}


	public Date getFechaBaja() {
		return verificador.getFechaBaja();
	}


	public void setFechaBaja(Date fechaBaja) {
		verificador.setFechaBaja(new Timestamp(fechaBaja.getTime()));
	}


	public String getEstadoSeleccionado() {
		if (verificador.getEstado() == null) {
			return "I";
		} else {
			return verificador.getEstado();
		}
	}


	public void setEstadoSeleccionado(String estadoSeleccionado) {
		verificador.setEstado(estadoSeleccionado);
	}

}