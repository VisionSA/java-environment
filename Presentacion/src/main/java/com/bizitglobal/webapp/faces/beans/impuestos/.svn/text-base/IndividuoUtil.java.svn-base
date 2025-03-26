package com.bizitglobal.webapp.faces.beans.impuestos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.ExclusionDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.TipoImpuestoDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.ExclusionException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestosIndividuoException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Exclusion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.ImpuestosIndividuo;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Individuo;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TipoImpuesto;
import com.bizitglobal.tarjetafiel.impuestos.service.ExclusionService;
import com.bizitglobal.tarjetafiel.impuestos.service.ImpuestosIndividuoService;
import com.bizitglobal.webapp.faces.beans.impuestos.wrappers.ImpuestoTabla;
import com.bizitglobal.webapp.faces.service.impuestos.ImpuestoServiceFaces;


@SuppressWarnings({"rawtypes","unchecked"})
public class IndividuoUtil {

	public static List generarListaImpuestos(TipoImpuestoDao tipoImpuestoDao, Individuo individuo, ExclusionDao exclusionDao) {
		List result = new ArrayList();
		Filtro filtro = new Filtro();
		List listaTiposImpuestos = tipoImpuestoDao.listarTodos(filtro);
		if (!listaTiposImpuestos.isEmpty()) {
			Iterator iter = listaTiposImpuestos.iterator();
			while (iter.hasNext()) {
				List exclusionImp = new ArrayList();
				TipoImpuesto aux = (TipoImpuesto) iter.next();
				if (individuo.getIdIndividuo() != null) {
					filtro.reset();
					filtro.agregarCampoOperValor("individuo.idIndividuo", Filtro.IGUAL, individuo.getIdIndividuo());
					filtro.agregarCampoOperValor("tipoImpuesto.idTipoImpuesto", Filtro.IGUAL, aux.getIdTipoImpuesto());
					exclusionImp = exclusionDao.listarTodos(filtro);
				}
				ImpuestoTabla impuesto = new ImpuestoTabla(aux, exclusionImp);
				result.add(impuesto);
			}
		}

		return result;
	}


	public static void cargarCategorias(Set individuosCategorias, List tablaDeImpuestos) {
		// List result = new ArrayList();
		if (!individuosCategorias.isEmpty() && !tablaDeImpuestos.isEmpty()) {
			Iterator iterCateg = individuosCategorias.iterator();
			while (iterCateg.hasNext()) {
				ImpuestosIndividuo categ = (ImpuestosIndividuo) iterCateg.next();
				Iterator iterImp = tablaDeImpuestos.iterator();
				while (iterImp.hasNext()) {
					ImpuestoTabla impTab = (ImpuestoTabla) iterImp.next();
					if (impTab.getTipoImpuesto().equals(categ.getCategoria().getTipoImpuesto())) {
						impTab.setImpuestoSeleccionado(categ.getCategoria().getIdCategoria());
					}
				}
				// ImpuestoTabla tabla = listaImpuestoTabla(listaCategorias);
				// tabla.setIdImpuestoTabla(imp.getIdIndividuoImpuesto());
				// tabla.setImpuestoSeleccionado(imp.getCategoria().getIdCategoria());
				// result.add(tabla);
			}
		}

		// return result;
	}


	// public static ImpuestoTabla listaImpuestoTabla(List listaCategorias) {
	// ImpuestoTabla result = null;
	// if(!listaCategorias.isEmpty()) {
	// Iterator iter = listaCategorias.iterator();
	// result = new ImpuestoTabla();
	// while(iter.hasNext()) {
	// Categoria categoria = (Categoria)iter.next();
	// SelectItem item = new SelectItem();
	// item.setValue(categoria.getIdCategoria());
	// item.setLabel(categoria.getDescripcion()+"-"+categoria.getCodCategoria());
	// result.getListaImpuestos().add(item);
	// }
	// }
	//
	// return result;
	// }

	// public static List cargarCategorias(Set individuosCategorias, List listaCategorias) {
	// List result = new ArrayList();
	// if(!individuosCategorias.isEmpty()) {
	// Iterator iter = individuosCategorias.iterator();
	// while(iter.hasNext()) {
	// IndividuoCategoria imp = (IndividuoCategoria)iter.next();
	// ImpuestoTabla tabla = listaImpuestoTabla(listaCategorias);
	// tabla.setIdImpuestoTabla(imp.getIdIndividuoImpuesto());
	// tabla.setImpuestoSeleccionado(imp.getCategoria().getIdCategoria());
	// result.add(tabla);
	// }
	// }
	//
	// return result;
	// }

	public static List cargarExclusiones(List listaImpuesto, Long idImpuesto) {
		if (!listaImpuesto.isEmpty()) {
			Iterator iter = listaImpuesto.iterator();
			while (iter.hasNext()) {
				ImpuestoTabla temp = (ImpuestoTabla) iter.next();
				if (temp.getIdImpuestoTabla().equals(idImpuesto)) {
					return temp.getListaExclusiones();
				}
			}
		}

		return new ArrayList();
	}


	public static List borrarExclusion(List exclusiones, Long idExclusion) {
		boolean encontrado = false;

		if (!exclusiones.isEmpty()) {
			Iterator iter = exclusiones.iterator();
			while (iter.hasNext() && !encontrado) {
				Exclusion temp = (Exclusion) iter.next();
				if (temp.getIdExclusion().equals(idExclusion)) {
					exclusiones.remove(temp);
					encontrado = true;
				}
			}
		}

		return exclusiones;
	}


	public static void abImpuestos(List tablaDeImpuestos, List categorias, List exclusiones, Individuo individuo, ImpuestoServiceFaces service) {
		List listaIds = new ArrayList();
		List listaExclusiones = new ArrayList();

		// Grabar si cambiaron las listas.
		if (!tablaDeImpuestos.isEmpty()) {
			Iterator iter = tablaDeImpuestos.iterator();
			while (iter.hasNext()) {
				ImpuestoTabla imp = (ImpuestoTabla) iter.next();
				listaExclusiones.addAll(imp.getListaExclusiones()); // Guardo las exclusiones para grabar
				if (!imp.getImpuestoSeleccionado().equals(new Long(0))) {
					ImpuestosIndividuo ind = new ImpuestosIndividuo();
					ind.setIndividuo(individuo);
					ind.setCategoria(service.getCategoriaDao().buscarCategoria(imp.getImpuestoSeleccionado()));
					listaIds.add(imp.getImpuestoSeleccionado()); // Agregamos el id a una lista para borrar.
					try {
						if (categorias == null || !categorias.contains(imp.getImpuestoSeleccionado())) {
							service.getImpuestosIndividuoService().grabarImpuestosIndividuo(ind);
						}
					} catch (ImpuestosIndividuoException e) {
						e.printStackTrace();
					}
				}
			}
		}

		// Borrar si cambiaron las listas
		if (categorias != null && !categorias.isEmpty()) {
			Iterator iter2 = categorias.iterator();
			while (iter2.hasNext()) {
				Long id = (Long) iter2.next();

				try {
					if (!listaIds.contains(id)) {
						service.getImpuestosIndividuoService().borrarImpuestosIndividuo(id);
					}
				} catch (ImpuestosIndividuoException e) {
					e.printStackTrace();
				}
			}
		}
		// ************** EXCLUSIONES ****************
		listaIds = new ArrayList();
		// Grabar si cambiaron las listas
		if (!listaExclusiones.isEmpty()) {
			Iterator iter = listaExclusiones.iterator();
			while (iter.hasNext()) {
				Exclusion exc = (Exclusion) iter.next();
				exc.setIndividuo(individuo);
				listaIds.add(exc.getIdExclusion());
				try {
					if ((exclusiones == null) || !(exclusiones.contains(exc.getIdExclusion()))) {
						exc.setIdExclusion(null);
						service.getExclusionService().grabarExclusion(exc);
					}
				} catch (ExclusionException e) {
					e.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}

		// Borrar si cambiaron las listas
		if (exclusiones != null && !exclusiones.isEmpty()) {
			Iterator iter2 = exclusiones.iterator();
			while (iter2.hasNext()) {
				Long id = (Long) iter2.next();

				try {
					if (!listaIds.contains(id)) {
						service.getExclusionService().borrarExclusion(id);
					}
				} catch (ExclusionException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static void abExclusiones(List listaExclusiones, List exclusiones, Long idIndividuo, ExclusionService service) {
		List listaIds = new ArrayList();

		// Grabar si cambiaron las listas
		if (!listaExclusiones.isEmpty()) {
			Iterator iter = listaExclusiones.iterator();
			while (iter.hasNext()) {
				Exclusion imp = (Exclusion) iter.next();
				imp.setIndividuo(new Individuo(idIndividuo, null, null, null, null));
				listaIds.add(imp.getIdExclusion());
				try {
					if ((exclusiones == null) || !(exclusiones.contains(imp.getIdExclusion()))) {
						service.grabarExclusion(imp);
					}
				} catch (ExclusionException e) {
					e.printStackTrace();
				}
			}
		}

		// Borrar si cambiaron las listas
		if (exclusiones != null && !exclusiones.isEmpty()) {
			Iterator iter2 = exclusiones.iterator();
			while (iter2.hasNext()) {
				Long id = (Long) iter2.next();

				try {
					if (!listaIds.contains(id)) {
						service.borrarExclusion(id);
					}
				} catch (ExclusionException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static List idsCategoriasLeidas(Set lista) {
		List result = new ArrayList();
		if (!lista.isEmpty()) {
			Iterator iter = lista.iterator();
			while (iter.hasNext()) {
				ImpuestosIndividuo temp = (ImpuestosIndividuo) iter.next();
				result.add(temp.getCategoria().getIdCategoria());
			}
		}

		return result;
	}


	public static List idsExclusionesLeidas(Set lista) {
		List result = new ArrayList();
		if (!lista.isEmpty()) {
			Iterator iter = lista.iterator();
			while (iter.hasNext()) {
				Exclusion temp = (Exclusion) iter.next();
				result.add(temp.getIdExclusion());
			}
		}

		return result;
	}


	public static void borrarCategorias(Set lista, ImpuestosIndividuoService service) {
		if (!lista.isEmpty()) {
			Iterator iter = lista.iterator();
			while (iter.hasNext()) {
				ImpuestosIndividuo temp = (ImpuestosIndividuo) iter.next();
				try {
					service.borrarImpuestosIndividuo(temp);
				} catch (ImpuestosIndividuoException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static void borrarExclusiones(Set lista, ExclusionService service) {
		if (!lista.isEmpty()) {
			Iterator iter = lista.iterator();
			while (iter.hasNext()) {
				Exclusion temp = (Exclusion) iter.next();
				try {
					service.borrarExclusion(temp);
				} catch (ExclusionException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
