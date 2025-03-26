package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.dao.SucursalFielDao;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.impuestos.dao.CategoriaDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisdiccionActividadDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisdiccionDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.TipoImpuestoDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisdiccionActividad;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TipoImpuesto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercioActividad;
import com.bizitglobal.webapp.faces.util.Convertidores;
import com.bizitglobal.webapp.faces.util.ImpuestoEditable;
import com.bizitglobal.webapp.faces.util.Validador;




@SuppressWarnings({"rawtypes","unchecked"})
public class CodComercioUtil {
	private Logger log = Logger.getLogger(CodComercioUtil.class);


	public static List generarListaImpuestos(TipoImpuestoDao tipoImpuestoDao, JurisdiccionActividadDao actividadDao) {

		List result = new ArrayList();
		Filtro filtro = new Filtro();
		List listaTiposImpuestos = tipoImpuestoDao.listarTodos(filtro);
		// List activiList = actividadDao.listarTodos(new Filtro());
		// if (!activiList.isEmpty()) {
		// Iterator iterator = activiList.iterator();
		// while (iterator.hasNext()) {
		// JurisdiccionActividad element = (JurisdiccionActividad) iterator.next();
		// // element.getActividad().getDescripcion();
		// element.getJurisdiccion().getDescripcion();
		// }
		// }
		if (!listaTiposImpuestos.isEmpty()) {
			Iterator iter = listaTiposImpuestos.iterator();
			while (iter.hasNext()) {
				TipoImpuesto aux = (TipoImpuesto) iter.next();
				// List categorias = new ArrayList();
				ImpuestoEditable impuesto = new ImpuestoEditable(aux, actividadDao, "cli");

				// impuesto.setTipo(aux.getDescripcion());
				// impuesto.setJurisdiccion(aux.getJurisdiccion().getDescripcion());
				// impuesto.setCategorias(Convertidores.setToList(aux.getCategorias()));

				result.add(impuesto);
			}
		}

		return result;
	}


	public static List marcarListaDesdeCodComercio(Set setDeActividadesCodComercio, List listaDeImpuestos) {
		// log.info("	generando lista de impuestos y marcando categorias...");
		List result = new ArrayList();

		if (!setDeActividadesCodComercio.isEmpty()) {
			List actividadesList = Convertidores.setToList(setDeActividadesCodComercio);
			Iterator actividades = actividadesList.iterator();
			while (actividades.hasNext()) {
				CodComercioActividad codComercioActividad = (CodComercioActividad) actividades.next();
				Categoria categoria = codComercioActividad.getJurisdiccionActividad().getCategoria();
				JurisdiccionActividad jActividad = codComercioActividad.getJurisdiccionActividad();

				if (!listaDeImpuestos.isEmpty()) {
					Iterator impuestos = listaDeImpuestos.iterator();
					while (impuestos.hasNext()) {
						ImpuestoEditable aux = (ImpuestoEditable) impuestos.next();
						if (aux.tieneCategoria(categoria)) {
							aux.setCategoriaSeleccionada(categoria.getIdCategoria());
							aux.setCodComercioActividad(codComercioActividad);
							aux.setJurisSeleccionada(jActividad.getJurisdiccion().getIdJurisdiccion());
							aux.buscarActividadesComercio();
							aux.setJurisActividadSeleccionada(jActividad.getIdJurisdiccionActividad());
						}
					}
				}
			}
		}
		result = listaDeImpuestos;
		return result;
	}


	public static Set cargarImpuestos(List tablaDeImpuestos, CodComercio codComercio,
			CategoriaDao categoriaDao, JurisdiccionActividadDao jurisdiccionActividadDao) {
		Set result = new HashSet();
		Iterator iterImpuestos = tablaDeImpuestos.iterator();
		while (iterImpuestos.hasNext()) {
			ImpuestoEditable aux = (ImpuestoEditable) iterImpuestos.next();
			// ProveedorCategoria cat = new ProveedorCategoria();
			if (!Validador.esNulo(aux.getJurisActividadSeleccionada()) && !aux.getJurisActividadSeleccionada().equals(new Long(0))) {
				CodComercioActividad act = aux.getCodComercioActividad();
				act.setJurisdiccionActividad(jurisdiccionActividadDao.buscarJurisdiccionActividad(aux.getJurisActividadSeleccionada()));
				act.setCodComercio(codComercio);
				// cat.setIdProvCategoria(aux.getIdProveedorCategoria());
				act.setActivo("S");
				result.add(act);
			}
		}

		return result;
	}


	public static List cargarListaJurisdicciones(JurisdiccionDao jurisdiccionDao) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccione"));
		List jurisList = jurisdiccionDao.listarTodos(new Filtro());
		if (!jurisList.isEmpty()) {
			Iterator iterJur = jurisList.iterator();
			while (iterJur.hasNext()) {
				Jurisdiccion aux = (Jurisdiccion) iterJur.next();
				result.add(new SelectItem(aux.getIdJurisdiccion(), aux.getDescripcion()));
			}
		}

		return result;
	}


	/**
	 * Retorna una lista de objetos select item, para mostrarlos en la lista desplegable
	 * 
	 * @param sucursalFielDao
	 *            , objeto dao para acceso a datos.
	 * @return Una lista de select item.
	 */
	public static List cargarListaSucursalesFiel(SucursalFielDao sucursalFielDao) {
		List result = new ArrayList();
		List sucursalesFielList = sucursalFielDao.listarTodos();
		if (!sucursalesFielList.isEmpty()) {
			Iterator sucursales = sucursalesFielList.iterator();
			while (sucursales.hasNext()) {
				SelectItem item = new SelectItem();
				SucursalFiel aux = (SucursalFiel) sucursales.next();
				item.setValue(aux.getIdSucursal());
				item.setLabel(aux.getNombre());
				result.add(item);
			}
		}

		return result;
	}

	/*
	 * public static boolean liquidaPorCuit(EmpresaDao empresaDao , Long idEmpresa){ Filtro filtro= new Filtro();
	 * filtro.agregarCampoOperValor("idEmpresa",Filtro.IGUAL ,idEmpresa); Empresa empresa = empresaDao.buscarEmpresa(idEmpresa); if(empresa != null){
	 * //CU:Cuit ; CO:Comerico if(empresa.getTipoLiquidacion()!=null && empresa.getTipoLiquidacion().compareTo("CO")==0) return false; }
	 * 
	 * return true;
	 * 
	 * }
	 */

}
