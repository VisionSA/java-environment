package com.bizitglobal.webapp.faces.beans.operador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bizitglobal.tarjetafiel.operador.exeption.RolPaginaPermisoException;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItem;
import com.bizitglobal.tarjetafiel.operador.negocio.Pagina;
import com.bizitglobal.tarjetafiel.operador.negocio.RolMenuItem;
import com.bizitglobal.tarjetafiel.operador.negocio.RolPaginaPermiso;
import com.bizitglobal.tarjetafiel.operador.negocio.TipoPermiso;
import com.bizitglobal.webapp.faces.service.operador.ABMOperadorServiceFaces;
import com.bizitglobal.webapp.faces.util.PaginaSeleccionable;


@SuppressWarnings({"rawtypes","unchecked"})
public class PermisoUtil {

	public static boolean rolMarcado(List rolesMenusItems, Long idMenuItem) {
		boolean result = false;
		boolean encontrado = false;

		if (!rolesMenusItems.isEmpty()) {
			Iterator iter = rolesMenusItems.iterator();
			while (iter.hasNext() && !encontrado) {
				MenuItem item = ((RolMenuItem) iter.next()).getMenuItem();
				if (item.getIdMenuItem().equals(idMenuItem)) {
					result = true;
					encontrado = true;
				}
			}
		}

		return result;
	}


	public static MenuItem menuItemSegunId(List menus, Long id) {
		MenuItem result = null;
		boolean encontrado = false;

		if (!menus.isEmpty()) {
			Iterator iter = menus.iterator();
			while (iter.hasNext() && !encontrado) {
				MenuItem item = (MenuItem) iter.next();

				if (item.getIdMenuItem().equals(id)) {
					result = item;
					encontrado = true;
				}
			}
		}

		return result;
	}


	public static List paginasSeleccionables(List paginas, List paginasPermisos, Long rolSeleccionado) {
		List result = new ArrayList();

		if (!paginas.isEmpty()) {
			Iterator iter = paginas.iterator();
			while (iter.hasNext()) {
				Pagina pagina = (Pagina) iter.next();
				PaginaSeleccionable pSeleccionable = new PaginaSeleccionable();
				pSeleccionable.setPagina(pagina);
				List permisos = paginasPorRol(paginasPermisos, pagina);
				// permisos = permisos cargados en l a tabla para un pagina especifica

				pSeleccionable.setAcceso((permisos.contains(TipoPermiso.ACCESO)));
				pSeleccionable.setAlta((permisos.contains(TipoPermiso.ALTA)));
				pSeleccionable.setBaja((permisos.contains(TipoPermiso.BAJA)));
				pSeleccionable.setModificacion((permisos.contains(TipoPermiso.MODIFICACION)));
				pSeleccionable.setExportacion((permisos.contains(TipoPermiso.EXPORTACION)));

				result.add(pSeleccionable);
			}
		}

		return result;
	}


	public static List paginasPorRol(List paginas, Pagina pagina) {
		List result = new ArrayList();

		if (!paginas.isEmpty()) {
			Iterator iter = paginas.iterator();
			while (iter.hasNext()) {
				RolPaginaPermiso pPermiso = (RolPaginaPermiso) iter.next();
				if (pPermiso.getPagina().equals(pagina)) {
					result.add(pPermiso.getPermiso().getId());
				}
			}
		}

		return result;
	}


	public static Long idPagina(List paginas, Long idPagina, Integer idPermiso) {
		Long result = null;
		boolean encontrado = false;

		if (!paginas.isEmpty()) {
			Iterator iter = paginas.iterator();
			while (iter.hasNext() && !encontrado) {
				RolPaginaPermiso pPermiso = (RolPaginaPermiso) iter.next();
				if (pPermiso.getPagina().getIdPagina().equals(idPagina) && pPermiso.getPermiso().getId().equals(idPermiso)) {
					result = pPermiso.getIdRolPaginaPermiso();
					encontrado = true;
				}
			}
		}

		return result;
	}


	public static void grabarPermiso(ABMOperadorServiceFaces service, RolPaginaPermiso temp) {
		try {
			service.getRolPaginaService().grabarRolPaginaPermiso(temp);
		} catch (RolPaginaPermisoException e1) {
			e1.printStackTrace();
		}
	}


	public static void borrarPermiso(ABMOperadorServiceFaces service, Long idPermiso) {
		try {
			service.getRolPaginaService().borrarRolPaginaPermiso(idPermiso);
		} catch (RolPaginaPermisoException e1) {
			e1.printStackTrace();
		}
	}

}
