package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.evaluacion.negocio.DiaPago;
import com.bizitglobal.tarjetafiel.general.dao.BarrioDao;
import com.bizitglobal.tarjetafiel.general.dao.LocalidadDao;
import com.bizitglobal.tarjetafiel.general.dao.PaisDao;
import com.bizitglobal.tarjetafiel.general.dao.PartidoDao;
import com.bizitglobal.tarjetafiel.general.dao.PropietarioViviendaDao;
import com.bizitglobal.tarjetafiel.general.dao.ProvinciaDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoDomicilioDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoViviendaDao;
import com.bizitglobal.tarjetafiel.general.negocio.Barrio;
import com.bizitglobal.tarjetafiel.general.negocio.Localidad;
import com.bizitglobal.tarjetafiel.general.negocio.Pais;
import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.general.negocio.PropietarioVivienda;
import com.bizitglobal.tarjetafiel.general.negocio.Provincia;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDomicilio;
import com.bizitglobal.tarjetafiel.general.negocio.TipoVivienda;
import com.bizitglobal.webapp.faces.util.Util;



@SuppressWarnings({"rawtypes","unchecked"})
public class DomicilioUtil {
	private static final Logger log = Logger.getLogger(DomicilioUtil.class);


	public static List cargarListaLocalidades(LocalidadDao localidadDao) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Seleccione"));
		List localidadesList = localidadDao.listarTodos();
		if (!localidadesList.isEmpty()) {
			Iterator localidades = localidadesList.iterator();
			while (localidades.hasNext()) {
				SelectItem item = new SelectItem();
				Localidad aux = (Localidad) localidades.next();
				item.setValue(aux.getIdLocalidad());
				item.setLabel(aux.getNombre());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarListaLocalidades(List localidadList) {
		List result = new ArrayList();
		if (!localidadList.isEmpty()) {
			Iterator localidades = localidadList.iterator();
			while (localidades.hasNext()) {
				Localidad aux = (Localidad) localidades.next();
				result.add(new SelectItem(aux.getId(), aux.getLabel()));
			}
		}
		return result;
	}


	public static List cargarListaPartidos(PartidoDao partidoDao) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Seleccione"));
		List partidosList = partidoDao.listarTodos();
		if (!partidosList.isEmpty()) {
			Iterator partidos = partidosList.iterator();
			while (partidos.hasNext()) {
				SelectItem item = new SelectItem();
				Partido aux = (Partido) partidos.next();
				item.setValue(aux.getIdPartido());
				item.setLabel(aux.getDescripcion());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarListaPartidos(List partidoList) {
		List result = new ArrayList();

		if (!partidoList.isEmpty()) {
			Iterator partidos = partidoList.iterator();
			while (partidos.hasNext()) {
				Partido aux = (Partido) partidos.next();
				result.add(new SelectItem(aux.getId(), aux.getLabel()));
			}
		}
		return result;
	}


	public static List cargarListaProvincias(ProvinciaDao provinciaDao) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Seleccione"));
		List provinciasList = provinciaDao.listarTodos();
		if (!provinciasList.isEmpty()) {
			Iterator provincias = provinciasList.iterator();
			while (provincias.hasNext()) {
				SelectItem item = new SelectItem();
				Provincia aux = (Provincia) provincias.next();
				item.setValue(aux.getIdProvincia());
				item.setLabel(aux.getNombre());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarListaProvincias(List provinciaList) {
		List result = new ArrayList();

		if (!provinciaList.isEmpty()) {
			Iterator provincias = provinciaList.iterator();
			while (provincias.hasNext()) {
				Provincia aux = (Provincia) provincias.next();
				result.add(new SelectItem(aux.getId(), aux.getLabel()));
			}
		}
		return result;
	}


	public static List cargarListaPaises(PaisDao paisDao) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Seleccione"));
		List paisesList = paisDao.listarTodos();
		if (!paisesList.isEmpty()) {
			Iterator paises = paisesList.iterator();
			while (paises.hasNext()) {
				SelectItem item = new SelectItem();
				Pais aux = (Pais) paises.next();
				item.setValue(aux.getIdPais());
				item.setLabel(aux.getNombre());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarListaPaises(List paisList) {
		List result = new ArrayList();

		if (!paisList.isEmpty()) {
			Iterator paises = paisList.iterator();
			while (paises.hasNext()) {
				Pais aux = (Pais) paises.next();
				result.add(new SelectItem(aux.getId(), aux.getLabel()));
			}
		}

		return result;
	}


	public static List cargarListaPropVivienda(PropietarioViviendaDao propViviendaDao) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Seleccione"));
		List propList = propViviendaDao.listarTodos();
		if (!propList.isEmpty()) {
			Iterator propViv = propList.iterator();
			while (propViv.hasNext()) {
				SelectItem item = new SelectItem();
				PropietarioVivienda aux = (PropietarioVivienda) propViv.next();
				item.setValue(aux.getIdPropVivienda());
				item.setLabel(aux.getDescripcion());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarListaBarrios(BarrioDao barrioDao) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Seleccione"));
		List barriosList = barrioDao.listarTodos();
		if (!barriosList.isEmpty()) {
			Iterator barrios = barriosList.iterator();
			while (barrios.hasNext()) {
				SelectItem item = new SelectItem();
				Barrio aux = (Barrio) barrios.next();
				item.setValue(aux.getIdBarrio());
				item.setLabel(aux.getDescripcion());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarListaBarrios(List barrioList) {
		List result = new ArrayList();

		if (!barrioList.isEmpty()) {
			Iterator barrios = barrioList.iterator();
			while (barrios.hasNext()) {
				Barrio aux = (Barrio) barrios.next();
				result.add(new SelectItem(aux.getId(), aux.getLabel()));
			}
		}
		return result;
	}


	public static List cargarListaTipoVivienda(TipoViviendaDao tipoViviendaDao) {
		List result = new ArrayList();
		List tipoViviendaList = tipoViviendaDao.listarTodos();
		if (!tipoViviendaList.isEmpty()) {
			Iterator tiposViviendas = tipoViviendaList.iterator();
			while (tiposViviendas.hasNext()) {
				SelectItem item = new SelectItem();
				TipoVivienda aux = (TipoVivienda) tiposViviendas.next();
				item.setValue(aux.getIdTipoVivienda());
				item.setLabel(aux.getDescripcion());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarListaTipoDomicilio(TipoDomicilioDao tipoDomicilioDao) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Seleccione"));
		List tipoDomicilioList = tipoDomicilioDao.listarTodos();
		if (!tipoDomicilioList.isEmpty()) {
			Iterator tiposDomicilios = tipoDomicilioList.iterator();
			while (tiposDomicilios.hasNext()) {
				SelectItem item = new SelectItem();
				TipoDomicilio aux = (TipoDomicilio) tiposDomicilios.next();
				item.setValue(aux.getIdTipoDomicilio());
				item.setLabel(aux.getDescripcion());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarListaOrientacion() {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "-"));
		result.add(new SelectItem(new Long(1), "-"));
		result.add(new SelectItem(new Long(2), "N"));
		result.add(new SelectItem(new Long(3), "S"));
		result.add(new SelectItem(new Long(4), "E"));
		result.add(new SelectItem(new Long(5), "O"));
		return result;
	}


	public static String getLocalidadDeLista(List listaDeLocalidades, Long idLocalidad) {
		String result = null;
		boolean encontrado = false;
		if (!listaDeLocalidades.isEmpty()) {
			Iterator iter = listaDeLocalidades.iterator();
			while (iter.hasNext() && !encontrado) {
				SelectItem item = (SelectItem) iter.next();
				if (item.getValue().equals(idLocalidad)) {
					result = item.getLabel();
					encontrado = true;
				}
			}
		}

		return result;
	}


	public static String getBarrioDeLista(List listaDeBarrios, Long idBarrio) {
		String result = null;
		boolean encontrado = false;
		if (!listaDeBarrios.isEmpty()) {
			Iterator iter = listaDeBarrios.iterator();
			while (iter.hasNext() && !encontrado) {
				SelectItem item = (SelectItem) iter.next();
				if (item.getValue().equals(idBarrio)) {
					result = item.getLabel();
					encontrado = true;
				}
			}
		}

		return result;
	}


	public static List cargarSelectItemDiaPago(List listDiaPago, Long id) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Día de Pago"));

		if (!listDiaPago.isEmpty()) {
			Iterator iterator = listDiaPago.iterator();
			while (iterator.hasNext()) {
				DiaPago element = (DiaPago) iterator.next();

				if (element.getPartido().getIdPartido().equals(id))
					result.add(new SelectItem(element.getIdDiaPago(), element.getDiaPago().toString()));
			}
		}
		return result;
	}


	public static List filtrarProvincias(List listaProvincia, Long idPais) {
		List result = new ArrayList();
		if (!listaProvincia.isEmpty()) {
			Iterator iterator = listaProvincia.iterator();
			while (iterator.hasNext()) {
				Provincia element = (Provincia) iterator.next();
				if (element.getPais().getIdPais().equals(idPais))
					result.add(new SelectItem(element.getIdProvincia(), element.getNombre()));
			}
		}
		return result;
	}


	public static List filtrarPartidos(List listaPartidos, Long idProvinciaSeleccionada) {
		List result = new ArrayList();
		if (!listaPartidos.isEmpty()) {
			Iterator iterator = listaPartidos.iterator();
			while (iterator.hasNext()) {
				Partido element = (Partido) iterator.next();
				if (element.getProvincia().getIdProvincia().equals(idProvinciaSeleccionada))
					result.add(new SelectItem(element.getIdPartido(), element.getDescripcion()));
			}
		}
		return result;
	}


	public static List filtrarLocalidades(List listaLocalidades, Long idPartidoSeleccionado) {
		List result = new ArrayList();

		if (!listaLocalidades.isEmpty()) {
			Iterator iterator = listaLocalidades.iterator();
			while (iterator.hasNext()) {
				Localidad element = (Localidad) iterator.next();

				if (element.getPartido().getIdPartido().equals(idPartidoSeleccionado))
					result.add(new SelectItem(element.getIdLocalidad(), element.getNombre()));
			}
		}
		return result;
	}


	public static List filtrarBarrios(List listaBarrios, Long idLocalidadSeleccionada) {
		List result = new ArrayList();
		if (!listaBarrios.isEmpty()) {
			Iterator iterator = listaBarrios.iterator();
			while (iterator.hasNext()) {
				Barrio element = (Barrio) iterator.next();
				if (element.getLocalidad().getIdLocalidad().equals(idLocalidadSeleccionada))
					result.add(new SelectItem(element.getIdBarrio(), element.getDescripcion()));
			}
		}
		return result;
	}


	public static List filtrarLocalidadesProvincia(List listaLocalidades, Long idProvinciaSeleccionada) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Localidad"));

		if (!listaLocalidades.isEmpty()) {
			Iterator iterator = listaLocalidades.iterator();
			while (iterator.hasNext()) {
				Localidad element = (Localidad) iterator.next();

				if (element.getProvincia().getIdProvincia().equals(idProvinciaSeleccionada))
					result.add(new SelectItem(element.getIdLocalidad(), element.getNombre()));
			}
		}
		return result;
	}

}
