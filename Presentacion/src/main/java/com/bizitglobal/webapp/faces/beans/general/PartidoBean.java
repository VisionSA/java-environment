package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.DiaPago;
import com.bizitglobal.tarjetafiel.general.exception.PaisException;
import com.bizitglobal.tarjetafiel.general.exception.PartidoDuplicateException;
import com.bizitglobal.tarjetafiel.general.exception.PartidoException;
import com.bizitglobal.tarjetafiel.general.exception.ProvinciaException;
import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.general.negocio.Provincia;
import com.bizitglobal.tarjetafiel.general.service.PartidoService;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;



@SuppressWarnings({"rawtypes","unchecked"})
public class PartidoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(PartidoBean.class);
	private Partido partido;
	private String nombreFiltro;
	private Long idPartidoHidden;
	private String idPartido;
	private boolean informeAmbiental;
	private boolean lejano;

	// definicion de un list del objeto base
	private List partidoList;

	// Listas para la presentacion(HtmlSelectItems).
	private List provinciaList = new ArrayList();
	private List provinciaItems = new ArrayList();

	private List paisItems = new ArrayList();

	// lista para controlar que no graben objetos duplicados
	private List unPartido;
	// Objetos Relacionados.
	private Long idProvinciaSeleccionada;
	private Long idPaisSeleccionado;

	private HtmlSelectOneMenu pais = new HtmlSelectOneMenu();

	private String focoHidden;


	public PartidoBean() {
		super();
		borrar();
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public Partido getPartido() {
		return partido;
	}


	public void setPartido(Partido partido) {
		this.partido = partido;
	}


	public Long getIdPartidoHidden() {
		return idPartidoHidden;
	}


	public void setIdPartidoHidden(Long idPartidoHidden) {
		this.idPartidoHidden = idPartidoHidden;
	}


	public List getProvinciaItems() {
		return provinciaItems;
	}


	public void setProvinciaItems(List provinciaItems) {
		this.provinciaItems = provinciaItems;
	}


	public Long getIdProvinciaSeleccionada() {
		return idProvinciaSeleccionada;
	}


	public void setIdProvinciaSeleccionada(Long idProvinciaSeleccionada) {
		this.idProvinciaSeleccionada = idProvinciaSeleccionada;
	}


	public List getPartidoList() {
		return partidoList;
	}


	public void setPartidoList(List object) {
		this.partidoList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public List getPaisItems() {
		return paisItems;
	}


	public void setPaisItems(List paisItems) {
		this.paisItems = paisItems;
	}


	public Long getIdPaisSeleccionado() {
		return idPaisSeleccionado;
	}


	public void setIdPaisSeleccionado(Long idPaisSeleccionado) {
		this.idPaisSeleccionado = idPaisSeleccionado;
	}


	public String getIdPartido() {
		return idPartido;
	}


	public void setIdPartido(String idPartido) {
		this.idPartido = idPartido;
	}


	public boolean isInformeAmbiental() {
		return informeAmbiental;
	}


	public void setInformeAmbiental(boolean informeAmbiental) {
		this.informeAmbiental = informeAmbiental;
	}


	public boolean isLejano() {
		return lejano;
	}


	public void setLejano(boolean lejano) {
		this.lejano = lejano;
	}


	public HtmlSelectOneMenu getPais() {
		return pais;
	}


	public void setPais(HtmlSelectOneMenu pais) {
		this.pais = pais;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE PARTIDO
	 ************************************************************************/

	public String inicializar() {
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}

		return "amPartido";
	}


	public String editarPartido() {
		String result = null;
		borrar();
		alta = false;
		tituloCorto = "Modificar Partido";
		try {

			partido = generalService.getPartidoService().leerPartido(idPartidoHidden);
			idPaisSeleccionado = partido.getProvincia().getPais().getIdPais();
			pais.setValue(idPaisSeleccionado);
			cambiarProvincias();
			idProvinciaSeleccionada = partido.getProvincia().getIdProvincia();

			if (partido.getInformeAmbiental().equals(new Character('S')))
				informeAmbiental = true;

			if (partido.getEsLejano().equals("S"))
				lejano = true;

			result = "amPartido";
		} catch (PartidoException e1) {
			error.agregar("Ocurrio un error al intentar editar el partido");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarPartido.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el partido");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/general/listarPartido.jsf");
		}
		return result;
	}


	public String eliminarPartido() {
		try {
			generalService.getPartidoService().borrarPartido(idPartidoHidden);
			partidoList.remove(new Partido(idPartidoHidden));
		} catch (PartidoException e1) {
			error.agregar("Imposible borrar el partido. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar el partido");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarPartido.jsf");
		return null;
	}


	public String grabar() {
		try {
			partido.setDescripcion(partido.getDescripcion().trim());
			PartidoService partidoService = generalService.getPartidoService();
			partido.setProvincia((Provincia) Util.buscarElemento(provinciaList, new Provincia(idProvinciaSeleccionada)));
			if (informeAmbiental)
				partido.setInformeAmbiental(new Character('S'));
			else
				partido.setInformeAmbiental(new Character('N'));
			if (lejano)
				partido.setEsLejano("S");
			else
				partido.setEsLejano("N");
			if (validar()) {
				if (alta) {
					partidoService.grabarPartido(partido);

					// Esto es momentaneo, para que al dar de alta ya queden asignados los dias de pago a los partidos.
					evaluacionService.getDiaPagoService().grabarDiaPago(new DiaPago(null, new Integer(5), partido, null));
					evaluacionService.getDiaPagoService().grabarDiaPago(new DiaPago(null, new Integer(10), partido, null));
					evaluacionService.getDiaPagoService().grabarDiaPago(new DiaPago(null, new Integer(15), partido, null));
					evaluacionService.getDiaPagoService().grabarDiaPago(new DiaPago(null, new Integer(20), partido, null));
					evaluacionService.getDiaPagoService().grabarDiaPago(new DiaPago(null, new Integer(25), partido, null));
					evaluacionService.getDiaPagoService().grabarDiaPago(new DiaPago(null, new Integer(30), partido, null));
					evaluacionService.getDiaPagoService().grabarDiaPago(new DiaPago(null, new Integer(22), partido, null));
					evaluacionService.getDiaPagoService().grabarDiaPago(new DiaPago(null, new Integer(17), partido, null));
					evaluacionService.getDiaPagoService().grabarDiaPago(new DiaPago(null, new Integer(12), partido, null));
				}
				else {
					partidoService.actualizarPartido(partido);
				}
				popup.setPopup(popup.ICONO_OK, "El Partido ha sido almacenado exitosamente.");
			}
		} catch (PartidoDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Partido.");
			e1.printStackTrace();
		} catch (PartidoException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Partido.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del Partido.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		borrarBaseBean();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Partido";

		idPartido = "";
		informeAmbiental = false;
		lejano = false;
		idPaisSeleccionado = new Long(0);
		pais.setValue(idPaisSeleccionado);
		provinciaItems.clear();
		provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
		idProvinciaSeleccionada = new Long(0);
		try {
			unPartido = generalService.getPartidoService().getPartido(new Filtro());
			Iterator part = unPartido.iterator();
			while (part.hasNext()) {
				Partido partidito = (Partido) part.next();
				partidito.getProvincia();
			}
		} catch (PartidoException e) {
			e.printStackTrace();
		}

		try {
			paisItems.clear();
			paisItems.add(new SelectItem(new Long(0), "Seleccionar País"));
			paisItems.addAll(Util.cargarSelectItem(generalService.getPaisService().getPais(new Filtro())));
			provinciaList = generalService.getProvinciaService().getProvincia(new Filtro());
		} catch (PaisException e) {
			e.printStackTrace();
		} catch (ProvinciaException e) {
			e.printStackTrace();
		}

		partido = new Partido();
		partidoList = new ArrayList();
	}


	public String cancelar() {
		return irAListarPartido();
	}


	public boolean validar() {
		error.borrar();
		if (partido.getDescripcion() == null || partido.getDescripcion().equals(""))
			error.agregar(Error.AMPARTIDO_DESCRIPCION_REQUERIDA);

		if (idProvinciaSeleccionada == null || idProvinciaSeleccionada.equals(new Long(0)))
			error.agregar(Error.AMPARTIDO_PROVINCIA_REQUERIDA);

		if (alta) {
			if (!unPartido.isEmpty()) {
				Iterator iterator = unPartido.iterator();
				while (iterator.hasNext()) {
					Partido element = (Partido) iterator.next();
					if (element.getDescripcion().compareTo(partido.getDescripcion()) == 0) {
						if (element.getProvincia().getIdProvincia().equals(idProvinciaSeleccionada))
							error.agregar(Error.AMPARTIDO_PARTIDO_REPETIDO);
					}
				}
			}
		} else {
			if (!unPartido.isEmpty()) {
				Iterator iterator = unPartido.iterator();
				while (iterator.hasNext()) {
					Partido element = (Partido) iterator.next();
					if (element.getDescripcion().compareTo(partido.getDescripcion()) == 0 && !element.getIdPartido().equals(idPartidoHidden)) {
						if (element.getProvincia().getIdProvincia().equals(idProvinciaSeleccionada))
							error.agregar(Error.AMPARTIDO_PARTIDO_REPETIDO);
					}
				}
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoPartido() {
		pais.setValue(new Long(0));

		provinciaItems.clear();
		provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
		idProvinciaSeleccionada = new Long(0);
		return inicializar();
	}


	public String irAModificarPartido() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Partido";
		// pais.setValue(new Long(0));

		// provinciaItems.clear();
		// provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
		return null;
	}


	public String irAListarPartido() {
		borrar();
		tituloCorto = "Listado de Partidos";
		Session.redirect("/tarjetafiel/general/listarPartido.jsf");
		return "";
	}


	public String listarPartido() {
		partidoList = new ArrayList();
		try {
			Filtro filtro = new Filtro();
			if (idPartido != null && !idPartido.equals(""))
				filtro.agregarCampoOperValor("idPartido", Filtro.IGUAL, new Long(idPartido));
			if (partido.getDescripcion() != null && !partido.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, partido.getDescripcion());
			if (idProvinciaSeleccionada != null && !idProvinciaSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("provincia.idProvincia", Filtro.IGUAL, idProvinciaSeleccionada);

			partidoList = generalService.getPartidoService().getPartido(filtro);
			Iterator iter = partidoList.iterator();
			while (iter.hasNext())
			{
				Partido element = (Partido) iter.next();
				element.getProvincia().getLabel();
			}
			informeAmbiental = false;
			lejano = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/general/listarPartido.jsf");
		return null;
	}


	public void cambiarProvincias(ValueChangeEvent event) {
		Long paisSeleccionado = new Long(pais.getValue().toString());

		provinciaItems.clear();
		provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
		provinciaItems.addAll(PartidoUtil.filtrarProvincias(provinciaList, paisSeleccionado));
	}


	public void cambiarProvincias() {
		Long paisSeleccionado = new Long(pais.getValue().toString());
		provinciaItems.clear();
		provinciaItems.add(new SelectItem(new Long(0), "Seleccionar Provincia"));
		provinciaItems.addAll(PartidoUtil.filtrarProvincias(provinciaList, paisSeleccionado));
	}

}
