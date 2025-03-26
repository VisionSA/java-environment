package com.bizitglobal.webapp.faces.beans.general;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoDomicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Barrio;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Localidad;
import com.bizitglobal.tarjetafiel.general.negocio.PropietarioVivienda;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDomicilio;
import com.bizitglobal.tarjetafiel.general.negocio.TipoVivienda;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorDomicilio;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.IndividuoEvaluacionBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.ModificacionEstructuraCuentaBean;
import com.bizitglobal.webapp.faces.beans.proveedores.ProveedorBean;
import com.bizitglobal.webapp.faces.beans.transacciones.popup.IndividuoPopupBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Validador;



@SuppressWarnings({"rawtypes","unchecked"})
public class DomicilioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(DomicilioBean.class);
	private String focoHidden;

	// Domicilio que contiene las propiedades para el bean.
	private Domicilio domicilio;
	private Domicilio domicilioInicial;
	// propiedades para las listas desplegables.
	private List listaTipoViviendas;
	private List listaTipoDomicilio;
	private List listaBarrios;
	private List listaLocalidades;
	private List listaPartidos;
	private List listaProvincias;
	private List listaPaises;
	private List listaPropViviendas;
	private List listaOrientacion;
	private static List listaAuxProvincias;
	private static List listaAuxPartidos;
	private static List listaAuxLocalidades;
	private static List listaAuxBarrios;
	private static List listaAuxTipoDomicilio;
	private static List listaAuxPaises;
	private static List listaAuxTipoViviendas;
	private static List listaAuxPropViviendas;

	// Objetos para inicializar desde distintos origenes
	private int origen = 0;
	public static final int PROVEEDOR = 1;
	public static final int TITULAR = 2;
	public static final int ADICIONAL = 3;
	public static final int GARANTE = 4;
	public static final int VERIFICADOR = 5;
	public static final int PROMOTOR = 6;
	public static final int GARANTIA = 7;
	public static final int SUCURSAL = 8;
	public static final int INFORME_PARTICULAR = 9;
	public static final int SUC_EMPRESA = 10;
	public static final int COLABORADOR = 11;
	public static final int CODCOMERCIO = 12;
	public static final int INDIVIDUO_POPUP = 13;
	public static final int INFORME_LABORAL = 14;
	public static final int GARANTIA_MODIFICAR_CUENTA = 15;
	public static final int TITULAR_MODIFICAR_CUENTA = 16;

	// Booleanos para controlar lo que se muestra o no.
	private boolean boolTDireccion;
	private boolean boolLatitud;
	private boolean boolLongitud;
	private boolean boolPropVivi;
	private boolean boolTipVivi;
	private boolean boolCuotAlq;
	private boolean boolAntiguedad;
	private boolean boolCuota;
	private boolean boolEditar;

	private Long idPaisSeleccionado;
	private Long idProvinciaSeleccionada;
	private Long idPartidoSeleccionado;
	private Long idLocalidadSeleccionada;
	private Long idBarrioSeleccionado;
	private Long propViviSeleccionado;
	private Long tipoViviendaSeleccionado;
	private Long tipoDomicilioSeleccionado;

	// binding para objetos jsf
	HtmlSelectOneMenu partidos;
	HtmlSelectOneMenu localidades;
	HtmlSelectOneMenu paises;
	HtmlSelectOneMenu barrios;
	HtmlSelectOneMenu provincias;

	HtmlInputText codigoPostal = new HtmlInputText();

	HtmlSelectOneMenu propietarioVivienda;


	public DomicilioBean() {
		domicilio = new Domicilio();
		domicilio.setCuotaAlquiler(null);
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public List getListaOrientacion() {
		return listaOrientacion;
	}


	public void setListaOrientacion(List listaOrientacion) {
		this.listaOrientacion = listaOrientacion;
	}


	public boolean isBoolAntiguedad() {
		return boolAntiguedad;
	}


	public void setBoolAntiguedad(boolean boolAntiguedad) {
		this.boolAntiguedad = boolAntiguedad;
	}


	public boolean isBoolCuotAlq() {
		return boolCuotAlq;
	}


	public void setBoolCuotAlq(boolean boolCuotAlq) {
		this.boolCuotAlq = boolCuotAlq;
	}


	public boolean isBoolLatitud() {
		return boolLatitud;
	}


	public void setBoolLatitud(boolean boolLatitud) {
		this.boolLatitud = boolLatitud;
	}


	public boolean isBoolLongitud() {
		return boolLongitud;
	}


	public void setBoolLongitud(boolean boolLongitud) {
		this.boolLongitud = boolLongitud;
	}


	public boolean isBoolPropVivi() {
		return boolPropVivi;
	}


	public void setBoolPropVivi(boolean boolPropVivi) {
		this.boolPropVivi = boolPropVivi;
	}


	public boolean isBoolTDireccion() {
		return boolTDireccion;
	}


	public void setBoolTDireccion(boolean boolTDireccion) {
		this.boolTDireccion = boolTDireccion;
	}


	public boolean isBoolTipVivi() {
		return boolTipVivi;
	}


	public void setBoolTipVivi(boolean boolTipVivi) {
		this.boolTipVivi = boolTipVivi;
	}


	public Long getBarrioSeleccionado() {
		return idBarrioSeleccionado;
	}


	public void setBarrioSeleccionado(Long barrioSeleccionado) {
		domicilio.getBarrio().setIdBarrio(barrioSeleccionado);
		domicilio.getBarrio().setDescripcion(DomicilioUtil.getBarrioDeLista(listaBarrios, barrioSeleccionado));
	}


	public Domicilio getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}


	public List getListaBarrios() {
		return listaBarrios;
	}


	public void setListaBarrios(List listaBarrios) {
		this.listaBarrios = listaBarrios;
	}


	public List getListaLocalidades() {
		return listaLocalidades;
	}


	public void setListaLocalidades(List listaLocalidades) {
		this.listaLocalidades = listaLocalidades;
	}


	public List getListaPaises() {
		return listaPaises;
	}


	public void setListaPaises(List listaPaises) {
		this.listaPaises = listaPaises;
	}


	public List getListaProvincias() {
		return listaProvincias;
	}


	public void setListaProvincias(List listaProvincias) {
		this.listaProvincias = listaProvincias;
	}


	public List getListaTipoViviendas() {
		return listaTipoViviendas;
	}


	public void setListaTipoViviendas(List listaTipoViviendas) {
		this.listaTipoViviendas = listaTipoViviendas;
	}


	public Long getLocalidadSeleccionada() {
		return idLocalidadSeleccionada;
	}


	public void setLocalidadSeleccionada(Long localidadSeleccionada) {
		domicilio.getLocalidad().setIdLocalidad(localidadSeleccionada);
		domicilio.getLocalidad().setNombre(DomicilioUtil.getLocalidadDeLista(listaLocalidades, localidadSeleccionada));
	}


	public Long getPaisSeleccionado() {
		return new Long(paises.getValue().toString());
	}


	public boolean isBoolEditar() {
		return boolEditar;
	}


	public void setBoolEditar(boolean boolEditar) {
		this.boolEditar = boolEditar;
	}


	public Long getOrientSeleccionada() {
		if (domicilio.getOrientacion() != null) {
			// log.info("Orientacion: "+ domicilio.getOrientacion());
			switch (domicilio.getOrientacion().charValue()) {
			case '-':
				return new Long(1);
			case 'N':
				return new Long(2);
			case 'S':
				return new Long(3);
			case 'E':
				return new Long(4);
			case 'O':
				return new Long(5);
			default:
				return new Long(0);
			}
		} else {
			return new Long(1);
		}
	}


	public void setOrientSeleccionada(Long orientSeleccionada) {
		// log.info("ID orientacion: " + orientSeleccionada);
		switch (orientSeleccionada.intValue()) {
		case 1:
			domicilio.setOrientacion(new Character('-'));
			break;
		case 2:
			domicilio.setOrientacion(new Character('N'));
			break;
		case 3:
			domicilio.setOrientacion(new Character('S'));
			break;
		case 4:
			domicilio.setOrientacion(new Character('E'));
			break;
		case 5:
			domicilio.setOrientacion(new Character('O'));
			break;
		}
	}


	public List getListaPropViviendas() {
		return listaPropViviendas;
	}


	public void setListaPropViviendas(List listaPropViviendas) {
		this.listaPropViviendas = listaPropViviendas;
	}


	public Long getPropViviSeleccionado() {
		return propViviSeleccionado;
		// if(domicilio.getPropVivienda() == null)
		// return new Long(0);
		// else
		// return domicilio.getPropVivienda().getIdPropVivienda();
	}


	public void setPropViviSeleccionado(Long propViviSeleccionado) {
		this.propViviSeleccionado = propViviSeleccionado;
		// domicilio.getPropVivienda().setIdPropVivienda(propViviSeleccionado);
	}


	public Long getProvinciaSeleccionada() {
		return new Long(provincias.getValue().toString());
	}


	public Long getTipoViviendaSeleccionado() {
		return tipoViviendaSeleccionado;
	}


	public void setTipoViviendaSeleccionado(Long tipoViviendaSeleccionado) {
		this.tipoViviendaSeleccionado = tipoViviendaSeleccionado;
	}


	public List getListaTipoDomicilio() {
		return listaTipoDomicilio;
	}


	public void setListaTipoDomicilio(List listaTipoDomicilio) {
		this.listaTipoDomicilio = listaTipoDomicilio;
	}


	public Long getTipoDomicilioSeleccionado() {
		return tipoDomicilioSeleccionado;
	}


	public void setTipoDomicilioSeleccionado(Long tipoDomicilioSeleccionado) {
		this.tipoDomicilioSeleccionado = tipoDomicilioSeleccionado;
	}


	public List getListaPartidos() {
		return listaPartidos;
	}


	public void setListaPartidos(List listaPartidos) {
		this.listaPartidos = listaPartidos;
	}


	public Long getPartidoSeleccionado() {
		return new Long(partidos.getValue().toString());
	}


	public HtmlSelectOneMenu getPaises() {
		return paises;
	}


	public void setPaises(HtmlSelectOneMenu paises) {
		this.paises = paises;
	}


	public HtmlSelectOneMenu getBarrios() {
		return barrios;
	}


	public void setBarrios(HtmlSelectOneMenu barrios) {
		this.barrios = barrios;
	}


	public HtmlSelectOneMenu getLocalidades() {
		return localidades;
	}


	public void setLocalidades(HtmlSelectOneMenu localidades) {
		this.localidades = localidades;
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


	public HtmlInputText getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(HtmlInputText codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	public String getCodPostalCompleto() {
		String cp1, cp2, cp3;
		cp1 = (domicilio.getCpa1() == null) ? "" : domicilio.getCpa1();
		cp2 = (domicilio.getCpa2() == null) ? "" : domicilio.getCpa2().toString();
		cp3 = (domicilio.getCpa3() == null) ? "" : domicilio.getCpa3();
		domicilio.setCodigoPostal(cp1 + cp2 + cp3);
		return domicilio.getCodigoPostal();
	}


	/*
	 * Acciones para el bean de domicilios.
	 */

	public String agregarDomicilioPopup() {
		if (this.domicilio.getCalleNumero().equals("") || this.domicilio.getCalleNumero() == null) {
			domicilio.setCalleNumero("s/n");
		}
		if (domicilio.getIdDomicilio() == null) {
			domicilio.setTimestamp(new Timestamp(new Date().getTime()));
			domicilio.setOperador(Session.getOperador());
		}
		domicilio.setTipoVivienda((TipoVivienda) Util.buscarElemento(listaAuxTipoViviendas, new TipoVivienda(tipoViviendaSeleccionado)));
		domicilio.setTipoDomicilio((TipoDomicilio) Util.buscarElemento(listaAuxTipoDomicilio, new TipoDomicilio(tipoDomicilioSeleccionado)));
		domicilio.setPropVivienda((PropietarioVivienda) Util.buscarElemento(listaAuxPropViviendas, new PropietarioVivienda(propViviSeleccionado)));
		domicilio.setLocalidad((Localidad) Util.buscarElemento(listaAuxLocalidades, new Localidad(idLocalidadSeleccionada)));
		domicilio.setBarrio((Barrio) Util.buscarElemento(listaAuxBarrios, new Barrio(idBarrioSeleccionado)));
		switch (origen) {
		case PROVEEDOR:
			ProveedorBean bean = (ProveedorBean) Session.getBean("ProveedorBean");
			if (domicilio.getIdDomicilio() == null) {
				// Seteamos el hash code para reconocer el objeto en la presentacion.
				// domicilio.setIdDomicilio(new Long(domicilio.hashCode()+""));
				ProveedorDomicilio proveedorDomicilio = new ProveedorDomicilio();
				proveedorDomicilio.setDomicilio(this.domicilio);
				bean.getTablaDeDomicilios().add(proveedorDomicilio);
			}
			// else {
			// bean.setTablaDeDomicilios(ProveedorUtil.eliminarDomicilio(
			// bean.getTablaDeDomicilios(), domicilio.getIdDomicilio()));
			// log.info("Se elimino el domicilio id: " + domicilio.getIdDomicilio());
			// domicilio.setIdDomicilio(new Long(domicilio.hashCode()+""));
			// }
			break;

		case TITULAR:
			IndividuoEvaluacionBean titular = (IndividuoEvaluacionBean) Session.getBean("IndividuoEvaluacionBean");

			if (domicilio.getIdDomicilio() == null || domicilio.getIdDomicilio().equals(new Long(0))) {
				domicilio.setIdDomicilio(new Long(-1));
			}
			SelectItem item = new SelectItem(domicilio.getIdDomicilio(), domicilio.getCalleNombre() + " " + domicilio.getCalleNumero());
			if (!titular.getListDomicilioPago().contains(item)) {
				titular.getListDomicilioPago().add(item);
			}
			titular.setFocoHidden("agregarTelefonoLink");
			// return "amIndividuo";
			break;
		case ADICIONAL:
			IndividuoEvaluacionBean adicional = (IndividuoEvaluacionBean) Session.getBean("IndividuoEvaluacionBean");
			adicional.setFocoHidden("agregarTelefonoLink");
			break;

		case GARANTE:
			IndividuoEvaluacionBean garante = (IndividuoEvaluacionBean) Session.getBean("IndividuoEvaluacionBean");
			if (domicilio.getIdDomicilio() == null) {
				garante.setFocoHidden("agregarTelefonoLink");
			}
			break;
		case VERIFICADOR:
			break;
		case PROMOTOR:
			break;
		case COLABORADOR:
			break;
		case GARANTIA:
			IndividuoEvaluacionBean iBean = (IndividuoEvaluacionBean) Session.getBean("IndividuoEvaluacionBean");
			domicilio.getTipoDomicilio().setIdTipoDomicilio(getTipoDomicilioSeleccionado());
			if (domicilio.getIdDomicilio() == null) {
				IndividuoDomicilio indDomicilio = new IndividuoDomicilio();
				indDomicilio.setIdIndivDomicilio(null);
				indDomicilio.setDomicilio(domicilio);
				iBean.getListDomicilioInmueble().add(indDomicilio);
				indDomicilio.setIndividuoEvaluacion(iBean.getIndividuoEvaluacion());
				domicilio.setIdDomicilio(new Long(indDomicilio.hashCode()));
			}
			iBean.setFocoHidden("inMarca");
			break;

		case SUCURSAL:
			SucursalBean sucursalBean = (SucursalBean) Session.getBean("SucursalBean");
			sucursalBean.getSucursal().setDomicilio(domicilio);
			break;

		case SUC_EMPRESA:
			if (!boolEditar) {
				EmpresaBean empresaBean = (EmpresaBean) Session.getBean("EmpresaBean");
				empresaBean.agregarDomicilioWrapper(domicilio);
			}
			break;
		case INFORME_LABORAL:
			break;
		case INFORME_PARTICULAR:
			break;
		case CODCOMERCIO:
			break;
		case INDIVIDUO_POPUP:
			/* @I13 */// if (domicilio.getIdDomicilio()==null || domicilio.getIdDomicilio().equals(new Long(0))) {
			// domicilio.setIdDomicilio(new Long(-1));
			// }

			IndividuoPopupBean titularAct = (IndividuoPopupBean) Session.getBean("IndividuoPopupBean");

			if (domicilio.getIdDomicilio() == null || domicilio.getIdDomicilio().equals(new Long(0))) {
				domicilio.setIdDomicilio(new Long(-1));
			}
			SelectItem itemAct = new SelectItem(domicilio.getIdDomicilio(), domicilio.getCalleNombre() + " " + domicilio.getCalleNumero());
			if (!titularAct.getListDomicilioPago().contains(itemAct)) {
				titularAct.getListDomicilioPago().add(itemAct);
			}
			titularAct.setFocoHidden("agregarTelefonoLink");
			/* @F13 */break;

		case GARANTIA_MODIFICAR_CUENTA:
			ModificacionEstructuraCuentaBean iBeanMod = (ModificacionEstructuraCuentaBean) Session.getBean("ModificacionEstructuraCuentaBean");
			domicilio.getTipoDomicilio().setIdTipoDomicilio(getTipoDomicilioSeleccionado());
			if (domicilio.getIdDomicilio() == null) {
				IndividuoDomicilio indDomicilio = new IndividuoDomicilio();
				indDomicilio.setIdIndivDomicilio(null);
				indDomicilio.setDomicilio(domicilio);
				iBeanMod.getListDomicilioInmueble().add(indDomicilio);
				indDomicilio.setIndividuoEvaluacion(iBeanMod.getIndividuoEvaluacion());
				domicilio.setIdDomicilio(new Long(indDomicilio.hashCode()));
			}
			iBeanMod.setFocoHidden("inMarca");

			break;

		case TITULAR_MODIFICAR_CUENTA:
			ModificacionEstructuraCuentaBean titularMod = (ModificacionEstructuraCuentaBean) Session.getBean("ModificacionEstructuraCuentaBean");

			if (domicilio.getIdDomicilio() == null || domicilio.getIdDomicilio().equals(new Long(0))) {
				domicilio.setIdDomicilio(new Long(-1));
			}
			SelectItem itemMod = new SelectItem(domicilio.getIdDomicilio(), domicilio.getCalleNombre() + " " + domicilio.getCalleNumero());
			if (!titularMod.getListDomicilioPago().contains(itemMod)) {
				titularMod.getListDomicilioPago().add(itemMod);
			}
			titularMod.setFocoHidden("agregarTelefonoLink");
			break;
		}
		error.borrar(); // Borrar los errores una vez que se puedo grabar.
		borrar(); // borra el bean completo.
		return null;
	}


	public String cancelar() {
		borrar();
		restaurarDomicilioInicial();
		return null;
	}


	private void restaurarDomicilioInicial() {
		domicilio.asignarDomicilio(domicilioInicial);// preguntar a hernan

	}


	public void recargarYCerrarPopup(ActionEvent event) {
		if (validar()) {
			agregarDomicilioPopup();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar();window.close();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		}
	}


	public void borrar() {
		focoHidden = "lstTipoDireccion";
		domicilio = new Domicilio();

		// limpiar los binding para objetos jsf
		partidos = new HtmlSelectOneMenu();
		localidades = new HtmlSelectOneMenu();
		paises = new HtmlSelectOneMenu();
		barrios = new HtmlSelectOneMenu();
		provincias = new HtmlSelectOneMenu();
		propietarioVivienda = new HtmlSelectOneMenu();

		Long longCero = new Long(0);
		idPaisSeleccionado = longCero;
		idProvinciaSeleccionada = longCero;
		idPartidoSeleccionado = longCero;
		idLocalidadSeleccionada = longCero;
		idBarrioSeleccionado = longCero;
		propViviSeleccionado = longCero;
		tipoViviendaSeleccionado = longCero;
		tipoDomicilioSeleccionado = longCero;
		borrarBoolean();
	}


	private void borrarListas() {
		listaBarrios = new ArrayList();
		listaLocalidades = new ArrayList();
		listaPaises = new ArrayList();
		listaPartidos = new ArrayList();
		listaProvincias = new ArrayList();
		listaPropViviendas = new ArrayList();
		listaOrientacion = new ArrayList();
		listaTipoDomicilio = new ArrayList();
		listaTipoViviendas = new ArrayList();
	}


	public void borrarBoolean() {
		boolTDireccion = false;
		boolLatitud = false;
		boolLongitud = false;
		boolPropVivi = false;
		boolTipVivi = false;
		boolCuotAlq = false;
		boolAntiguedad = false;
		boolCuota = true;
	}


	/*
	 * Change Listeners para los cambios en las listas desplegables.
	 */

	public void cambiarCodigoPostal(ValueChangeEvent event) {
		Long valorSeleccionado = new Long(barrios.getValue().toString());

		if (valorSeleccionado.intValue() != 0) {
			Barrio barrio = generalService.getBarrioDao().buscarBarrio(new Long(valorSeleccionado.longValue()));
			try {
				codigoPostal.setValue(new Integer(barrio.getLocalidad().getCodigoPostal()));
			} catch (NumberFormatException e) {
				// e.printStackTrace();
			}
		}
	}


	public void cambiarProvincias(ValueChangeEvent event) {
		Long idPaisSeleccionado = new Long(paises.getValue().toString());
		provincias.setValue(new Long(0));
		listaProvincias.clear();
		listaProvincias.add(new SelectItem(new Long(0), "Seleccione una Provincia"));
		partidos.setValue(new Long(0));
		listaPartidos.clear();
		listaPartidos.add(new SelectItem(new Long(0), "Seleccione un partido"));
		localidades.setValue(new Long(0));
		listaLocalidades.clear();
		listaLocalidades.add(new SelectItem(new Long(0), "Seleccione una Localidad"));
		barrios.setValue(new Long(0));
		listaBarrios.clear();
		listaBarrios.add(new SelectItem(new Long(0), "Seleccione un Barrio"));
		if (!idPaisSeleccionado.equals(new Long(0))) {
			listaProvincias.addAll(DomicilioUtil.filtrarProvincias(listaAuxProvincias, idPaisSeleccionado));
		}
	}


	public void cambiarPartidos(ValueChangeEvent event) {
		Long idProvinciaSeleccionada = new Long(provincias.getValue().toString());
		partidos.setValue(new Long(0));
		listaPartidos.clear();
		listaPartidos.add(new SelectItem(new Long(0), "Seleccione un Partido"));
		listaLocalidades.clear();
		listaLocalidades.add(new SelectItem(new Long(0), "Seleccione una Localidad"));
		localidades.setValue(new Long(0));
		listaBarrios.clear();
		listaBarrios.add(new SelectItem(new Long(0), "Seleccione un Barrio"));
		barrios.setValue(new Long(0));
		if (!idProvinciaSeleccionada.equals(new Long(0))) {
			listaPartidos.addAll(DomicilioUtil.filtrarPartidos(listaAuxPartidos, idProvinciaSeleccionada));
		}
	}


	public void cambiarLocalidades(ValueChangeEvent event) {
		Long idPartidoSeleccionado = new Long(partidos.getValue().toString());
		localidades.setValue(new Long(0));
		listaLocalidades.clear();
		listaLocalidades.add(new SelectItem(new Long(0), "Seleccione una Localidad"));
		barrios.setValue(new Long(0));
		listaBarrios.clear();
		listaBarrios.add(new SelectItem(new Long(0), "Seleccione un Barrio"));
		if (!idPartidoSeleccionado.equals(new Long(0))) {
			listaLocalidades.addAll(DomicilioUtil.filtrarLocalidades(listaAuxLocalidades, idPartidoSeleccionado));
		}
	}


	public void cambiarBarrios(ValueChangeEvent event) {
		Long idLocalidadSeleccionada = new Long(localidades.getValue().toString());
		barrios.setValue(new Long(0));
		listaBarrios.clear();
		listaBarrios.add(new SelectItem(new Long(0), "Seleccione un Barrio"));
		if (!idLocalidadSeleccionada.equals(new Long(0))) {
			listaBarrios.addAll(DomicilioUtil.filtrarBarrios(listaAuxBarrios, idLocalidadSeleccionada));
		}
	}


	public void habilitarCuota(ValueChangeEvent event) {
		propViviSeleccionado = new Long(propietarioVivienda.getValue().toString());
		domicilio.setPropVivienda((PropietarioVivienda)
				Util.buscarElemento(listaAuxPropViviendas, new PropietarioVivienda(propViviSeleccionado)));
		if (propViviSeleccionado.equals(new Long(6))) {
			boolCuota = false;
		} else {
			boolCuota = true;
		}
	}


	private void setearListasPorDefecto() {
		paises.setValue(new Long(0));
		localidades.setValue(new Long(0));
		partidos.setValue(new Long(0));
		provincias.setValue(new Long(0));
		barrios.setValue(new Long(0));
	}


	public void setPartidoSeleccionado(Long partidoSeleccionado) {
		try {
			domicilio.getLocalidad().getPartido().setIdPartido(partidoSeleccionado);
		} catch (org.hibernate.LazyInitializationException e) {
			e.printStackTrace();
		}
	}


	public void setProvinciaSeleccionada(Long provinciaSeleccionada) {
		try {
			domicilio.getLocalidad().getProvincia().setIdProvincia(provinciaSeleccionada);
		} catch (org.hibernate.LazyInitializationException e) {
			e.printStackTrace();
		}
	}


	public void setPaisSeleccionado(Long paisSeleccionado) {
		try {
			domicilio.getLocalidad().getProvincia().getPais().setIdPais(paisSeleccionado);
		} catch (org.hibernate.LazyInitializationException e) {
			e.printStackTrace();
		}
	}


	public boolean validar() {
		error.borrar();
		switch (origen) {
		case PROVEEDOR:
			if (getTipoDomicilioSeleccionado().intValue() == 0) {
				error.agregar(Error.DOMICILIO_TIPODOMICILIO_REQUERIDO);
			}
			validarGeneral();
			break;
		case TITULAR:
			validarGeneral();
			if (getPropViviSeleccionado().equals(new Long(0))) {
				error.agregar(Error.DOMICILIO_PROP_VIVI_REQUERIDO);
			}

			if (getTipoDomicilioSeleccionado().equals(new Long(0))) {
				error.agregar(Error.DOMICILIO_TIPODOMICILIO_REQUERIDO);
			}
			break;
		case ADICIONAL:
			validarGeneral();
			break;
		case GARANTE:
			if (getPropViviSeleccionado().equals(new Long(0))) {
				error.agregar(Error.DOMICILIO_PROP_VIVI_REQUERIDO);
			}
			validarGeneral();
			break;
		case GARANTIA:
			validarGeneral();
			if (getPropViviSeleccionado().equals(new Long(0))) {
				error.agregar(Error.DOMICILIO_PROP_VIVI_REQUERIDO);
			}

			if (getTipoViviendaSeleccionado().equals(new Long(0))) {
				error.agregar(Error.DOMICILIO_TIPOVIVIENDA_REQUERIDO);
			}

			if (getTipoDomicilioSeleccionado().equals(new Long(0))) {
				error.agregar(Error.DOMICILIO_TIPODOMICILIO_REQUERIDO);
			}
			break;
		case VERIFICADOR:
			validarGeneral();
			break;
		case PROMOTOR:
			validarGeneral();
			break;
		case SUCURSAL:
			validarGeneral();
			break;
		case SUC_EMPRESA:
			validarGeneral();
			break;
		case INDIVIDUO_POPUP:
			validarGeneral();
			if (getPropViviSeleccionado().equals(new Long(0))) {
				error.agregar(Error.DOMICILIO_PROP_VIVI_REQUERIDO);
			}

			// if(getTipoViviendaSeleccionado().equals(new Long(0))) {
			// error.agregar(Error.DOMICILIO_TIPOVIVIENDA_REQUERIDO);
			// }

			if (getTipoDomicilioSeleccionado().equals(new Long(0))) {
				error.agregar(Error.DOMICILIO_TIPODOMICILIO_REQUERIDO);
			}
			break;
		case CODCOMERCIO:
			validarGeneral();
			break;
		case COLABORADOR:
			validarGeneral();
			break;

		}
		return (error.cantidad() == 0) ? true : false;
	}


	private void validarGeneral() {
		if (Validador.esNulo(domicilio.getCalleNombre()) || domicilio.getCalleNombre().equals("")) {
			error.agregar(Error.DOMICILIO_CALLE_REQUERIDA);
		}

		if (getLocalidadSeleccionada().intValue() == 0) {
			error.agregar(Error.DOMICILIO_LOCALIDAD_REQUERIDA);
		}

		if (getBarrioSeleccionado().intValue() == 0) {
			error.agregar(Error.DOMICILIO_BARRIO_REQUERIDO);
		}

		if (Validador.esNulo(domicilio.getCpa2()) || domicilio.getCpa2().equals(new Integer(0))) {
			error.agregar(Error.DOMICILIO_CP_REQUERIDO);
		}

		if (idPaisSeleccionado == null || idPaisSeleccionado.equals(new Long(0))) {
			error.agregar(Error.DOMICILIO_PAIS_REQUERIDO);
		}
	}


	public String inicializar() {
		log.info("Ejecutando ==> Carga de listas");
		Filtro filtroVacio = new Filtro();
		try {
			if (listaAuxPaises == null || listaAuxPaises.size() != generalService.getPaisService().countPais(filtroVacio))
				listaAuxPaises = generalService.getPaisService().getPais(filtroVacio);
			if (listaAuxBarrios == null || listaAuxBarrios.size() != generalService.getBarrioService().countBarrio(filtroVacio))
				listaAuxBarrios = generalService.getBarrioService().getBarrio(filtroVacio);
			if (listaAuxLocalidades == null || listaAuxLocalidades.size() != generalService.getLocalidadService().countLocalidad(filtroVacio))
				listaAuxLocalidades = generalService.getLocalidadService().getLocalidad(filtroVacio);
			if (listaAuxPartidos == null || listaAuxPartidos.size() != generalService.getPartidoService().countPartido(filtroVacio))
				listaAuxPartidos = generalService.getPartidoService().getPartido(filtroVacio);
			if (listaAuxProvincias == null || listaAuxProvincias.size() != generalService.getProvinciaService().countProvincia(filtroVacio))
				listaAuxProvincias = generalService.getProvinciaService().getProvincia(filtroVacio);
			// Las siguientes listas son mas estaticas, por eso no les pongo el count para cotrolar los cambos
			if (listaAuxTipoDomicilio == null)
				listaAuxTipoDomicilio = generalService.getTipoDomicilioService().getTipoDomicilio(filtroVacio);
			if (listaAuxTipoViviendas == null)
				listaAuxTipoViviendas = generalService.getTipoViviendaService().getTipoViviendas();
			if (listaAuxPropViviendas == null)
				listaAuxPropViviendas = generalService.getPropietarioViviendaService().getPropietarioVivienda(filtroVacio);
		} catch (Exception e) {
			error.agregar("Ocurrio un error al leer las listas.");
			e.printStackTrace();
		}
		return null;
	}


	public String inicializar(int origen, Domicilio domi, boolean editar) {
		this.boolEditar = editar;
		return this.inicializar(origen, domi);
	}


	public String inicializar(int origen, Domicilio domi) {
		log.info("Ejecutando ==> inicializa Domicilio");
		Long longCero = new Long(0);
		borrar();
		inicializar();
		this.origen = origen;
		domicilio = domi;
		// Creo una del domicilio inicial en esta variable, la cual no debe ser modificada, ya q, si el usuario decea cancelar los cambios en el
		// domicilio sera esta variable la que pisara al domicilio original
		domicilioInicial = new Domicilio(domi);
		error.borrar();
		// domicilio.setCuotaAlquiler(null);
		cargarListas();
		setearBoolean();
		if (domicilio.getBarrio() != null && domicilio.getBarrio().getIdBarrio() != null && !domicilio.getBarrio().getIdBarrio().equals(new Long(0))) {
			if (domicilio.getPropVivienda() != null) {
				propViviSeleccionado = domicilio.getPropVivienda().getIdPropVivienda();
				propietarioVivienda.setValue(propViviSeleccionado);
				if (propViviSeleccionado.equals(new Long(6))) {
					boolCuota = false;
				} else {
					boolCuota = true;
				}
			}
			if (domicilio.getTipoVivienda() != null)
				tipoViviendaSeleccionado = domicilio.getTipoVivienda().getIdTipoVivienda();
			if (domicilio.getTipoDomicilio() != null)
				tipoDomicilioSeleccionado = domicilio.getTipoDomicilio().getIdTipoDomicilio();

			idPaisSeleccionado = domicilio.getBarrio().getLocalidad().getPartido().getProvincia().getPais().getIdPais();
			if (!idPaisSeleccionado.equals(longCero)) {
				paises.setValue(idPaisSeleccionado);
				listaProvincias = DomicilioUtil.filtrarProvincias(listaAuxProvincias, idPaisSeleccionado);
			} else {
				setearListasPorDefecto();
			}
			idProvinciaSeleccionada = domicilio.getBarrio().getLocalidad().getPartido().getProvincia().getIdProvincia();
			if (!idProvinciaSeleccionada.equals(longCero)) {
				provincias.setValue(idProvinciaSeleccionada);
				listaPartidos.addAll(DomicilioUtil.filtrarPartidos(listaAuxPartidos, idProvinciaSeleccionada));
			} else {
				setearListasPorDefecto();
			}
			idPartidoSeleccionado = domicilio.getBarrio().getLocalidad().getPartido().getIdPartido();
			if (!idPartidoSeleccionado.equals(longCero)) {
				partidos.setValue(idPartidoSeleccionado);
				listaLocalidades.addAll(DomicilioUtil.filtrarLocalidades(listaAuxLocalidades, idPartidoSeleccionado));
				barrios.setValue(longCero);
			} else {
				setearListasPorDefecto();
			}
			idLocalidadSeleccionada = domicilio.getBarrio().getLocalidad().getIdLocalidad();
			if (!idLocalidadSeleccionada.equals(longCero)) {
				localidades.setValue(idLocalidadSeleccionada);
				listaBarrios.addAll(DomicilioUtil.filtrarBarrios(listaAuxBarrios, idLocalidadSeleccionada));
			} else {
				setearListasPorDefecto();
			}
			barrios.setValue(domicilio.getBarrio().getIdBarrio());
		}
		return null;
	}


	private void cargarListas() {

		borrarListas();
		setearListasPorDefecto();
		log.info("Cargando listas del bean domicilio");
		listaBarrios.add(new SelectItem(new Long(0), "Seleccionar Barrio"));
		listaLocalidades.add(new SelectItem(new Long(0), "Seleccionar Localidad"));
		listaPartidos.add(new SelectItem(new Long(0), "Seleccionar Partido"));
		// esto lo seteo asi para que aparezca preseleccionada la provincia de san junan
		listaPaises.add(new SelectItem(new Long(0), "Seleccionar País"));
		listaPaises.addAll(DomicilioUtil.cargarListaPaises(listaAuxPaises));
		idPaisSeleccionado = new Long(1);
		paises.setValue(idPaisSeleccionado);
		listaProvincias.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
		listaProvincias.addAll(DomicilioUtil.filtrarProvincias(listaAuxProvincias, idPaisSeleccionado));
		idProvinciaSeleccionada = new Long(1);
		provincias.setValue(idProvinciaSeleccionada);
		listaPartidos.addAll(DomicilioUtil.filtrarPartidos(listaAuxPartidos, idProvinciaSeleccionada));

		listaTipoDomicilio.add(new SelectItem(new Long(0), "Seleccionar Tipo Domicilio"));
		listaTipoDomicilio.addAll(Util.cargarSelectItem(listaAuxTipoDomicilio));

		listaTipoViviendas.add(new SelectItem(new Long(0), "Seleccionar Tipo Vivienda"));
		listaTipoViviendas.addAll(Util.cargarSelectItem(listaAuxTipoViviendas));

		listaPropViviendas.add(new SelectItem(new Long(0), "Seleccionar Propietario Vivienda"));
		listaPropViviendas.addAll(Util.cargarSelectItem(listaAuxPropViviendas));

		listaOrientacion = DomicilioUtil.cargarListaOrientacion();

	}


	public void setearBoolean() {

		switch (origen) {

		case PROVEEDOR:

			boolTDireccion = true;
			boolLatitud = false;
			boolLongitud = false;
			boolPropVivi = false;
			boolTipVivi = false;
			boolCuotAlq = false;
			boolAntiguedad = false;
			break;

		case TITULAR:

			boolTDireccion = true;
			boolLatitud = false;
			boolLongitud = false;
			boolPropVivi = true;
			boolTipVivi = false;
			boolCuotAlq = true;
			boolAntiguedad = true;
			break;

		case ADICIONAL:

			boolTDireccion = false;
			boolLatitud = false;
			boolLongitud = false;
			boolPropVivi = false;
			boolTipVivi = false;
			boolCuotAlq = false;
			boolAntiguedad = false;
			break;

		case GARANTE:

			boolTDireccion = false;
			boolLatitud = false;
			boolLongitud = false;
			boolPropVivi = true;
			boolTipVivi = false;
			boolCuotAlq = false;
			boolAntiguedad = false;
			break;

		case VERIFICADOR:

			boolTDireccion = false;
			boolLatitud = false;
			boolLongitud = false;
			boolPropVivi = false;
			boolTipVivi = false;
			boolCuotAlq = false;
			boolAntiguedad = false;
			break;

		case PROMOTOR:

			boolTDireccion = false;
			boolLatitud = false;
			boolLongitud = false;
			boolPropVivi = false;
			boolTipVivi = false;
			boolCuotAlq = false;
			boolAntiguedad = false;
			break;

		case GARANTIA:

			boolTDireccion = true;
			boolLatitud = false;
			boolLongitud = false;
			boolPropVivi = true;
			boolTipVivi = true;
			boolCuotAlq = true;
			boolAntiguedad = true;
			break;

		case SUCURSAL:

			boolTDireccion = false;
			boolLatitud = false;
			boolLongitud = false;
			boolPropVivi = false;
			boolTipVivi = false;
			boolCuotAlq = false;
			boolAntiguedad = false;
			break;
		case SUC_EMPRESA:
			boolTDireccion = false;
			boolLatitud = false;
			boolLongitud = false;
			boolPropVivi = false;
			boolTipVivi = false;
			boolCuotAlq = false;
			boolAntiguedad = false;
			break;
		case INDIVIDUO_POPUP:
			boolTDireccion = true;
			boolLatitud = false;
			boolLongitud = false;
			boolPropVivi = true;
			boolTipVivi = true;
			boolCuotAlq = true;
			boolAntiguedad = true;
			break;

		}
	}


	public HtmlSelectOneMenu getPropietarioVivienda() {
		return propietarioVivienda;
	}


	public void setPropietarioVivienda(HtmlSelectOneMenu propietarioVivienda) {
		this.propietarioVivienda = propietarioVivienda;
	}


	public boolean isBoolCuota() {
		return boolCuota;
	}


	public void setBoolCuota(boolean boolCuota) {
		this.boolCuota = boolCuota;
	}


	public Long getIdPaisSeleccionado() {
		if (idPaisSeleccionado.equals(new Long(0))) {
			cargarListas();
		}
		return idPaisSeleccionado;
	}


	public void setIdPaisSeleccionado(Long idPaisSeleccionado) {
		this.idPaisSeleccionado = idPaisSeleccionado;
	}


	public void levantarListaPais(Long idPais, Long idProvincia, Long idPartido, Long idLocalidad, Long idBarrio) {

		log.info("Pais Seleccionado: " + idPais);
		log.info("Provincia Seleccionada: " + idProvincia);
		log.info("Partido Seleccionada}o: " + idPartido);
		log.info("Localidad Seleccionada: " + idLocalidad);
		log.info("Barrio Seleccionada: " + idBarrio);

		listaProvincias.addAll(DomicilioUtil.filtrarProvincias(listaAuxProvincias, idPais));
		listaPartidos.addAll(DomicilioUtil.filtrarPartidos(listaAuxPartidos, idProvincia));
		listaLocalidades.addAll(DomicilioUtil.filtrarLocalidades(listaAuxLocalidades, idPartido));
		listaBarrios.addAll(DomicilioUtil.filtrarBarrios(listaAuxBarrios, idLocalidad));

		paises.setValue(idPais);
		provincias.setValue(idProvincia);
		partidos.setValue(idPartido);
		localidades.setValue(idLocalidad);
		barrios.setValue(idBarrio);
	}


	public Long getIdBarrioSeleccionado() {
		return idBarrioSeleccionado;
	}


	public void setIdBarrioSeleccionado(Long idBarrioSeleccionado) {
		this.idBarrioSeleccionado = idBarrioSeleccionado;
	}


	public Long getIdPartidoSeleccionado() {
		return idPartidoSeleccionado;
	}


	public void setIdPartidoSeleccionado(Long idPartidoSeleccionado) {
		this.idPartidoSeleccionado = idPartidoSeleccionado;
	}


	public Long getIdProvinciaSeleccionada() {
		return idProvinciaSeleccionada;
	}


	public void setIdProvinciaSeleccionada(Long idProvinciaSeleccionada) {
		this.idProvinciaSeleccionada = idProvinciaSeleccionada;
	}


	public Long getIdLocalidadSeleccionada() {
		return idLocalidadSeleccionada;
	}


	public void setIdLocalidadSeleccionada(Long idLocalidadSeleccionada) {
		this.idLocalidadSeleccionada = idLocalidadSeleccionada;
	}
}
