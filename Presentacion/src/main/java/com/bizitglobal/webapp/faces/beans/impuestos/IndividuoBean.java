package com.bizitglobal.webapp.faces.beans.impuestos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.IndividuoException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Exclusion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Individuo;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TipoImpuesto;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuitNoValidoException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuitValido;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.impuestos.wrappers.ImpuestoTabla;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class IndividuoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(IndividuoBean.class);

	// Propiedad que define los datos que se quieren grabar, leer, modificar.
	private Individuo individuo;

	// Obtiene si se selecciono integrante de sociedad o no(1= si, 2= no).
	private boolean integranteSociedad;

	// Obtiene si se selecciono empleador o no(1= si, 2= no).
	private boolean empleador;

	// Propiedades utilizadas para cambiar entre el alta y la modificacion.
	private String tituloLargo;
	private String tituloCorto;

	// Indica si la página ya valido el cuit o no.
	private boolean validado;

	// Muestra un mensaje si el cuit no es valido.
	private String cuitInvalido;

	// Porciones del cuit para ser mostrado en la página.
	private String cuitDni;
	private String cuitVerificador;
	private String cuitIdentificador;

	// Tablas necesarias para el ingreso de informacion.
	private List tablaDeImpuestos;

	// Lista de todas las categorias de la base de datos.
	private TipoImpuesto tipoImpuestoSeleccionado;

	// Indica si estamos en un alta o baja de individuos.
	private boolean alta;

	// Lista que contiene los individuos a mostrar(en el listado)
	private List individuos;

	// Filtros para el listado de individuos.
	private String cuitFiltro;
	private String denominacionFiltro;

	// Individuo hidden, que sirve para borrar o editar el individuo.
	private Long idIndividuoHidden;

	// Lista de las exclusiones para el individuo.
	private List exclusiones;

	// Contienen las listas de categorias y de exclusiones que han sido leidas desde la base de datos.
	private List categoriasLeidas;
	private List exclusionesLeidas;

	private boolean mostrar;
	private Long idImpuesto;


	public IndividuoBean() {
		log.info("Construyendo IndividuoBean!!!");
		// try {
		// listaCategorias = impuestoService.getCategoriaService().getCategorias(new Filtro());
		// } catch (CategoriaException e) {
		// e.printStackTrace();
		// }
		limpiarBean();
	}


	public Individuo getIndividuo() {
		return individuo;
	}


	public void setIndividuo(Individuo individuo) {
		this.individuo = individuo;
	}


	public boolean isMostrar() {
		return mostrar;
	}


	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
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


	public boolean isValidado() {
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


	public boolean getEmpleador() {
		return empleador;
	}


	public void setEmpleador(boolean empleador) {
		this.empleador = empleador;
	}


	public boolean getIntegranteSociedad() {
		return integranteSociedad;
	}


	public void setIntegranteSociedad(boolean integranteSociedad) {
		this.integranteSociedad = integranteSociedad;
	}


	public List getTablaDeImpuestos() {
		return tablaDeImpuestos;
	}


	public void setTablaDeImpuestos(List tablaDeImpuestos) {
		this.tablaDeImpuestos = tablaDeImpuestos;
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public String getCuitFiltro() {
		return cuitFiltro;
	}


	public void setCuitFiltro(String cuitFiltro) {
		this.cuitFiltro = cuitFiltro;
	}


	public String getDenominacionFiltro() {
		return denominacionFiltro;
	}


	public void setDenominacionFiltro(String denominacionFiltro) {
		this.denominacionFiltro = denominacionFiltro;
	}


	public List getIndividuos() {
		return individuos;
	}


	public void setIndividuos(List individuos) {
		this.individuos = individuos;
	}


	public Long getIdIndividuoHidden() {
		return idIndividuoHidden;
	}


	public void setIdIndividuoHidden(Long idIndividuoHidden) {
		this.idIndividuoHidden = idIndividuoHidden;
	}


	public List getExclusiones() {
		return exclusiones;
	}


	public void setExclusiones(List exclusiones) {
		this.exclusiones = exclusiones;
	}


	/*
	 * ACCIONES DEL BEAN DE INDIVIDUOS
	 */

	public String grabar() {
		if (validar()) {
			Long idIndividuo = null;
			try {
				individuo.setIntegranteSoc((integranteSociedad) ? new Character('S') : new Character('N'));
				individuo.setEmpleador((empleador) ? new Character('S') : new Character('N'));

				if (alta) {
					idIndividuo = impuestoService.getIndividuoService().grabarIndividuo(individuo);
					individuo.setIdIndividuo(idIndividuo);
				} else {
					impuestoService.getIndividuoService().actualizarIndividuo(individuo);
				}

				IndividuoUtil.abImpuestos(tablaDeImpuestos, categoriasLeidas, exclusionesLeidas, individuo, impuestoService);
				// IndividuoUtil.abExclusiones(exclusiones,exclusionesLeidas, individuo.getIdIndividuo(), impuestoService.getExclusionService());

				// Sincronizamos las categorias y exclusiones en memoria
				categoriasLeidas = new ArrayList();
				exclusionesLeidas = new ArrayList();
				if (!tablaDeImpuestos.isEmpty()) {
					Iterator iterImp = tablaDeImpuestos.iterator();
					while (iterImp.hasNext()) {
						ImpuestoTabla temp = (ImpuestoTabla) iterImp.next();
						if (!temp.getImpuestoSeleccionado().equals(new Long(0))) {
							categoriasLeidas.add(temp.getImpuestoSeleccionado());
						}
						if (!temp.getListaExclusiones().isEmpty()) {
							Iterator iter = temp.getListaExclusiones().iterator();
							while (iter.hasNext()) {
								Exclusion temp1 = (Exclusion) iter.next();
								exclusionesLeidas.add(temp1.getIdExclusion());
							}
						}
					}
				}

				// // Sincronizamos las exclusiones en memoria
				// if(!exclusiones.isEmpty()) {
				// Iterator iter = exclusiones.iterator();
				// while(iter.hasNext()) {
				// Exclusion temp = (Exclusion)iter.next();
				// exclusionesLeidas.add(temp.getIdExclusion());
				// }
				// }
				//

				popup.setPopup(popup.ICONO_OK, "El individuo ha sido almacenado exitosamente.");
			} catch (IndividuoException e) {
				popup.setPopup(popup.ICONO_FALLA, "Falla el alta del individuo.");
				e.printStackTrace();
			}
		}

		return null;
	}


	public String cancelar() {
		return "inicio";
	}


	// public String eliminarImpuesto() {
	// log.info("Eliminar impuesto!!!");
	// if(tablaDeImpuestos.size() > 1) {
	// Long idImpuesto = new Long(Session.getRequestParameter("idImpuesto"));
	// tablaDeImpuestos = IndividuoUtil.borrarImpuesto(tablaDeImpuestos, idImpuesto);
	// }
	//
	// return null;
	// }

	// public String agregarImpuesto() {
	// log.info("Agregando impuesto!!!");
	// ImpuestoTabla aux = IndividuoUtil.listaImpuestoTabla(listaCategorias);
	// aux.setIdImpuestoTabla(new Long(""+aux.hashCode()));
	// log.info("Id del impuesto: "+aux.getIdImpuestoTabla());
	// tablaDeImpuestos.add(aux);
	//
	// return null;
	// }

	public void validarCuit(ActionEvent event) {
		if (individuo.getCuit() != null && !individuo.getCuit().equals("")) {
			try {
				CuitValido cuitValido = new CuitValido(individuo.getCuit());
				cuitDni = cuitValido.getDni();
				cuitIdentificador = cuitValido.getIdentificador();
				cuitVerificador = cuitValido.getVerificador();

				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("cuit", Filtro.LIKE, individuo.getCuit());
				List list = impuestoService.getIndividuoService().getIndividuos(filtro);

				if (list == null || list.isEmpty()) {
					validado = true;
					tablaDeImpuestos = IndividuoUtil.generarListaImpuestos(
							impuestoService.getTipoImpuestoDao(), individuo, impuestoService.getExclusionDao());
				} else {
					cuitInvalido = "El número de CUIT ya está registrado.";
				}

			} catch (CuitNoValidoException e1) {
				cuitInvalido = "El número de CUIT es invalido.";
				e1.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
				cuitInvalido = "Error al convertir.";
			}
		} else {
			error.agregar("Debe ingresar un Cuil para Continuar");
		}
	}


	public String verExclusiones() {
		log.info("Ver Exclusiones!!!");
		idImpuesto = new Long(Session.getRequestParameter("idImpuesto"));
		tipoImpuestoSeleccionado = impuestoService.getTipoImpuestoDao().buscarTipoImpuesto(idImpuesto);
		exclusiones = IndividuoUtil.cargarExclusiones(tablaDeImpuestos, idImpuesto);
		mostrar = true;
		return null;
	}


	public String agragarExclusion() {
		if (tipoImpuestoSeleccionado != null) {
			ExclusionBean exclusionBean = (ExclusionBean) Session.getBean("ExclusionBean");
			exclusionBean.inicializar(tipoImpuestoSeleccionado);
			String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += "/tarjetafiel/impuestos/individuos/popup/exclusionPopup.jsf";
			log.info("popup a elecutar -> " + path);
			ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		}
		mostrar = false;
		return null;
	}


	public String irANuevoIndividuo() {
		return inicializar();
	}


	public String irAModificarIndividuo() {
		alta = false;
		tituloCorto = "Modificación de individuo";
		tituloLargo = "Tarjeta Fiel - Individuo";
		popup.borrar();
		return "altaIndividuo";
	}


	public String irAListarIndividuos() {
		return listarIndividuos();
	}


	public String listarIndividuos() {
		limpiarBean();
		tituloCorto = "Listado de individuos";
		tituloLargo = "Tarjeta Fiel - Individuo";
		Session.redirect("/tarjetafiel/impuestos/individuos/listarIndividuo.jsf");
		return null;
	}


	public void limpiarBean() {
		individuo = new Individuo();
		tituloLargo = "Tarjeta Fiel - Individuo";
		tituloCorto = "Alta de individuos";
		validado = false;
		cuitInvalido = null;
		cuitDni = null;
		cuitVerificador = null;
		cuitIdentificador = null;
		integranteSociedad = false;
		empleador = false;
		alta = true;
		cuitFiltro = null;
		denominacionFiltro = null;
		individuos = null;
		idIndividuoHidden = null;
		tipoImpuestoSeleccionado = null;
		exclusiones = new ArrayList();
		tablaDeImpuestos = new ArrayList();
		categoriasLeidas = new ArrayList();
		exclusionesLeidas = new ArrayList();
		mostrar = false;
		popup.borrar();
	}


	public String filtrarIndividuos() {
		error.borrar();
		try {
			Filtro filtro = new Filtro();
			boolean seleccion = false;
			if (cuitFiltro != null && !cuitFiltro.equals("")) {
				filtro.agregarCampoOperValor("cuit", Filtro.LIKE, cuitFiltro);
				seleccion = true;
			}
			if (denominacionFiltro != null && !denominacionFiltro.equals("")) {
				filtro.agregarCampoOperValor("denominacion", Filtro.LIKE, denominacionFiltro);
				seleccion = true;
			}
			if (seleccion) {
				individuos = impuestoService.getIndividuoService().getIndividuos(filtro);
			} else {
				error.agregar("Debe ingresar algún filtro para realizar la búsqueda");
			}
		} catch (IndividuoException e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/impuestos/individuos/listarIndividuo.jsf");
		return "";
	}


	public String editarIndividuo() {
		log.info("Entrando a editar individuo!!!");
		String result = null;

		try {
			individuo = impuestoService.getIndividuoService().leerIndividuo(idIndividuoHidden);
			// exclusiones = Convertidores.setToList(individuo.getExclusion());
			tablaDeImpuestos = IndividuoUtil.generarListaImpuestos(
					impuestoService.getTipoImpuestoDao(), individuo, impuestoService.getExclusionDao());
			IndividuoUtil.cargarCategorias(individuo.getIndividuoCategorias(), tablaDeImpuestos);

			categoriasLeidas = IndividuoUtil.idsCategoriasLeidas(individuo.getIndividuoCategorias());
			exclusionesLeidas = IndividuoUtil.idsExclusionesLeidas(individuo.getExclusion());

			CuitValido cuitValido = new CuitValido(individuo.getCuit());
			cuitDni = cuitValido.getDni();
			cuitIdentificador = cuitValido.getIdentificador();
			cuitVerificador = cuitValido.getVerificador();

			integranteSociedad = ((individuo.getIntegranteSoc().equals(new Character('S'))) ? true : false);
			empleador = ((individuo.getEmpleador().equals(new Character('S'))) ? true : false);

			alta = false;
			validado = true;
			tituloCorto = "Modificación de individuo";
			tituloLargo = "Tarjeta Fiel - Individuo";
			result = "altaIndividuo";
		} catch (IndividuoException e) {
			e.printStackTrace();
		} catch (CuitNoValidoException e2) {
			e2.printStackTrace();
		}

		return result;
	}


	public String eliminarIndividuo() {
		try {
			individuo = impuestoService.getIndividuoService().leerIndividuo(idIndividuoHidden);
			IndividuoUtil.borrarCategorias(individuo.getIndividuoCategorias(), impuestoService.getImpuestosIndividuoService());
			IndividuoUtil.borrarExclusiones(individuo.getExclusion(), impuestoService.getExclusionService());
			impuestoService.getIndividuoService().borrarIndividuo(individuo);
		} catch (IndividuoException e) {
			e.printStackTrace();
		}

		return filtrarIndividuos();
	}


	public String eliminarExclusion() {
		Long idExclusion = new Long(Session.getRequestParameter("idExclusion"));
		exclusiones = IndividuoUtil.borrarExclusion(exclusiones, idExclusion);
		return null;
	}


	public void borrar() {
		limpiarBean();
	}


	public String inicializar() {
		limpiarBean();
		// agregarImpuesto();
		return "altaIndividuo";
	}


	public boolean validar() {
		error.borrar();

		if (individuo.getDenominacion() == null || individuo.getDenominacion() == "") {
			error.agregar(Error.INDIVIDUO_DENOMINACION_REQUERIDA);
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public List getCategoriasLeidas() {
		return categoriasLeidas;
	}


	public void setCategoriasLeidas(List categoriasLeidas) {
		this.categoriasLeidas = categoriasLeidas;
	}


	public List getExclusionesLeidas() {
		return exclusionesLeidas;
	}


	public void setExclusionesLeidas(List exclusionesLeidas) {
		this.exclusionesLeidas = exclusionesLeidas;
	}

}
