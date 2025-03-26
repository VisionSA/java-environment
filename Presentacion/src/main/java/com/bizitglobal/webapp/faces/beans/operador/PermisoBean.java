package com.bizitglobal.webapp.faces.beans.operador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.navmenu.NavigationMenuItem;
import org.apache.myfaces.custom.navmenu.jscookmenu.HtmlCommandJSCookMenu;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.operador.exeption.MenuItemException;
import com.bizitglobal.tarjetafiel.operador.exeption.PaginaException;
import com.bizitglobal.tarjetafiel.operador.exeption.RolException;
import com.bizitglobal.tarjetafiel.operador.exeption.RolMenuItemException;
import com.bizitglobal.tarjetafiel.operador.exeption.RolPaginaPermisoException;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItem;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItemRelacion;
import com.bizitglobal.tarjetafiel.operador.negocio.Pagina;
import com.bizitglobal.tarjetafiel.operador.negocio.Permiso;
import com.bizitglobal.tarjetafiel.operador.negocio.Rol;
import com.bizitglobal.tarjetafiel.operador.negocio.RolMenuItem;
import com.bizitglobal.tarjetafiel.operador.negocio.RolPaginaPermiso;
import com.bizitglobal.tarjetafiel.operador.negocio.TipoPermiso;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.MenuItemSeleccionable;
import com.bizitglobal.webapp.faces.util.PaginaSeleccionable;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;




@SuppressWarnings({"rawtypes","unchecked"})
public class PermisoBean extends BaseBean {
	private static Logger log = Logger.getLogger(PermisoBean.class);

	// Contiene una lista de objetos SelectItem, usados para construir un selectOneMenu.
	private List roles;
	private List objPor;
	private boolean origen = true;

	// Contiene el rol seleccionado.
	private Long rolSeleccionado;

	// Contiene la opcion seleccionada.
	private Integer opcionSeleccionada;

	// Contiene una lista de la tabla relacional entre los roles y los menuitems.
	private List rolMenusItems;

	// Contiene la lista completa de menus items.
	private List menusItems;
	private List menuPermitido;
	private List menu;

	// Contiene los menuItemSeleccionables
	private List menuItemSeleccionables;

	// Contiene los objetos paginaSeleccionable que se mostraran en la lista.
	private List paginasSeleccionables;

	// Habilita la alta o edicion del rol
	private boolean verRol;

	// Indica si mostrar o no el menu.
	private boolean mostrarMenu;

	// Contiene las lista de pagina y permisos leidos desde la base de datos.
	private List paginas;
	private List paginasPermisos;

	private Rol rol;

	private String paginaBuscada;
	private boolean selColumnas;


	// private String tituloLargo = "Tarjeta Fiel - Permisos";
	// private String tituloCorto = "Otorgar Permisos";

	public PermisoBean() {
		super();
		borrar();
		// tituloLargo = "Tarjeta Fiel - Permisos";
		// tituloCorto = "Otorgar Permisos";
		super.setTituloLargo("Tarjeta Fiel - Permisos");
		super.setTituloCorto("Otorgar Permisos");

		roles = OperadorUtil.cargarRoles(operadorService.getRolService());
		rolSeleccionado = null;
		menuItemSeleccionables = new ArrayList();
		opcionSeleccionada = null;
		paginasSeleccionables = new ArrayList();
		mostrarMenu = false;

		objPor = new ArrayList();
		objPor.add(new SelectItem(new Integer(1), "Menú"));
		objPor.add(new SelectItem(new Integer(2), "Página"));
		verRol = false;

	}


	public List getMenuSegunRol() {
		List result = new ArrayList();
		if (!menu.isEmpty()) {
			Object[] menus = menu.toArray();
			for (int i = 0; i < menus.length; i++) {
				MenuItem item = (MenuItem) menus[i];
				NavigationMenuItem padre = new NavigationMenuItem(item.getLabel() + "(" + item.getIdMenuItem() + ")", "#", item.getIcon(), false);
				padre.setActionListener("#{PermisoBean.click}");
				result.add(cargarHijos(padre, Convertidores.setToList(item.getHijos())));
			}
		}

		return result;
	}


	private NavigationMenuItem cargarHijos(NavigationMenuItem padre, List hijos) {
		Collections.sort(hijos);

		Iterator hijosI = hijos.iterator();
		while (hijosI.hasNext()) {
			MenuItem item = ((MenuItemRelacion) hijosI.next()).getHijo();
			if (menuPermitido.contains(item)) {
				NavigationMenuItem nItem = new NavigationMenuItem(item.getLabel() + "(" + item.getIdMenuItem() + ")", "#", item.getIcon(), false);
				nItem.setActionListener("#{PermisoBean.click}");
				padre.add(cargarHijos(nItem, Convertidores.setToList(item.getHijos())));
			}
		}

		return padre;
	}


	public String getTituloLargo() {
		// return tituloLargo;
		return super.getTituloLargo();
	}


	public void setTituloLargo(String tituloLargo) {
		// this.tituloLargo = tituloLargo;
		super.setTituloLargo(tituloLargo);
	}


	public boolean getSelColumnas() {
		return selColumnas;
	}


	public void setSelColumnas(boolean selColumnas) {
		this.selColumnas = selColumnas;
	}


	public String getTituloCorto() {
		// log.info("getTituloCorto: "+tituloCorto);
		// return tituloCorto;
		log.info("getTituloCorto: " + super.getTituloCorto());
		return super.getTituloCorto();
	}


	public void setTituloCorto(String tituloCorto) {
		// log.info("setTituloCorto: "+TituloCorto);
		// tituloCorto = TituloCorto;
		log.info("setTituloCorto: " + tituloCorto);
		super.setTituloCorto(tituloCorto);
	}


	public String getPaginaBuscada() {
		return paginaBuscada;
	}


	public void setPaginaBuscada(String paginaBuscada) {
		this.paginaBuscada = paginaBuscada;
	}


	public List getObjPor() {
		return objPor;
	}


	public void setObjPor(List objPor) {
		this.objPor = objPor;
	}


	public Long getRolSeleccionado() {
		return rolSeleccionado;
	}


	public void setRolSeleccionado(Long rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}


	public List getRoles() {
		return roles;
	}


	public void setRoles(List roles) {
		this.roles = roles;
	}


	public List getMenuItemSeleccionables() {
		return menuItemSeleccionables;
	}


	public void setMenuItemSeleccionables(List menuItemSeleccionables) {
		this.menuItemSeleccionables = menuItemSeleccionables;
	}


	public List getPaginasSeleccionables() {
		return paginasSeleccionables;
	}


	public void setPaginasSeleccionables(List paginasSeleccionables) {
		this.paginasSeleccionables = paginasSeleccionables;
	}


	public Integer getOpcionSeleccionada() {
		return opcionSeleccionada;
	}


	public void setOpcionSeleccionada(Integer opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}


	public boolean isMostrarMenu() {
		return mostrarMenu;
	}


	public void setMostrarMenu(boolean mostrarMenu) {
		this.mostrarMenu = mostrarMenu;
	}


	public boolean getVerRol() {
		return verRol;
	}


	public void setVerRol(boolean verRol) {
		this.verRol = verRol;
	}


	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}


	/*********************************************************************************
	 * ACCIONES DEL BEAN DE PERMISOS
	 *********************************************************************************/

	public String inicializar() {
		borrar();
		// tituloLargo = "Tarjeta Fiel - Permisos";
		// tituloCorto = "Otorgar Permisos";
		super.setTituloLargo("Tarjeta Fiel - Permisos");
		super.setTituloCorto("Otorgar Permisos");
		return "otorgarPermisos";
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	public void borrar() {
		error.borrar();
		origen = true;
		rolMenusItems = null;
		menusItems = null;
		menuPermitido = null;
		menu = null;
		rolSeleccionado = null;
		paginas = new ArrayList();
		menuItemSeleccionables = new ArrayList();
		paginasSeleccionables = new ArrayList();
		opcionSeleccionada = null;
		mostrarMenu = false;
		verRol = false;
		rol = new Rol();
		paginaBuscada = null;
		selColumnas = false;
		// tituloLargo = "Tarjeta Fiel - Permisos";
		// tituloCorto = "Otorgar Permisos";
		super.setTituloLargo("Tarjeta Fiel - Permisos");
		super.setTituloCorto("Otorgar Permisos");
	}


	public String editarRol() {
		try {
			rol = operadorService.getRolService().leerRol(rolSeleccionado);
		} catch (RolException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		verRol = true;
		return null;
	}


	public String nuevoRol() {
		rol = new Rol();
		verRol = true;
		return null;
	}


	public String grabarRol() {
		error.borrar();
		if (Validador.esNuloVacio(rol.getDescripcion())) {
			error.agregar(Error.PERMISO_DESCRIPCION_ROL_REQUERIDO);
		} else {
			try {
				operadorService.getRolService().grabarOActualizarRol(rol);
			} catch (RolException ex) {
				ex.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			roles = OperadorUtil.cargarRoles(operadorService.getRolService());
			verRol = false;
		}
		return null;
	}


	public String cancelarRol() {
		verRol = false;
		return null;
	}


	public String cargarMenuSoloPagina() {
		paginas = new ArrayList();
		origen = false;
		paginasSeleccionables = new ArrayList();
		try {
			paginasPermisos = operadorService.getRolPaginaService().getPaginasPorRol(rolSeleccionado);
			System.out.println("Cantidad Paginas Permisos: " + paginasPermisos.size());
		} catch (RolPaginaPermisoException e) {
			e.printStackTrace();
		}
		try {
			Long pag = null;
			String cadena = Session.getRequestParameter("idPaginaHidden").toString();
			pag = new Long(cadena);
			log.info("El id de paginaSeleccionado es:" + pag);
			Pagina paginita = operadorService.getPaginaService().leerPagina(pag);
			paginas = new ArrayList();
			paginas.add(paginita);
			log.info("Paginas buscadas: " + paginaBuscada);
			System.out.println("Cantidad Paginas Permisos: " + paginas.size());
			paginasSeleccionables = PermisoUtil.paginasSeleccionables(paginas, paginasPermisos, rolSeleccionado);
			PaginaSeleccionable pSeleccionable = new PaginaSeleccionable();
			paginasSeleccionables.add(0, pSeleccionable);
		} catch (PaginaException e1) {
			e1.printStackTrace();
			paginasSeleccionables = new ArrayList();
		} catch (NullPointerException e) {
			log.info("NO hay una pagina asociada");
			paginasSeleccionables = new ArrayList();
		} catch (NumberFormatException e2) {
			log.info("NO hay una pagina asociada");
			paginasSeleccionables = new ArrayList();

		}
		Session.redirect("/tarjetafiel/permiso/otorgarPermisos.jsf");
		return null;
	}


	public String cargarMenu() {
		origen = true;
		paginas = new ArrayList(); // this
		menuItemSeleccionables = new ArrayList();
		paginasSeleccionables = new ArrayList();
		mostrarMenu = false;

		if (opcionSeleccionada.equals(new Integer(1))) {
			mostrarMenu = true;
			setSelColumnas(false);
			try {
				menusItems = operadorService.getMenuItemService().getMenuItems();
				rolMenusItems = operadorService.getRolMenuItemService().getRolMenuItems(rolSeleccionado);
				menuPermitido = new ArrayList();

				List menus = new ArrayList();
				if (!menusItems.isEmpty()) {
					Iterator rolMenusI = menusItems.iterator();
					while (rolMenusI.hasNext()) {
						MenuItem item = (MenuItem) rolMenusI.next();
						if (item.padre()) {
							menus.add(item);
						}
						menuPermitido.add(item);
					}
				}
				this.menu = menus;
				Collections.sort(this.menu);
			} catch (MenuItemException e) {
				e.printStackTrace();
			} catch (RolMenuItemException e1) {
				e1.printStackTrace();
			}
		}

		if (opcionSeleccionada.equals(new Integer(2))) {
			cargarTablaPermisos();
		}

		// tituloLargo = "Tarjeta Fiel - Permisos";
		// tituloCorto = "Otorgar Permisos";
		super.setTituloLargo("Tarjeta Fiel - Permisos");
		super.setTituloCorto("Otorgar Permisos");
		// Session.redirect("/tarjetafiel/permiso/otorgarPermisos.jsf");
		return null;
	}


	public void click(ActionEvent event) {
		menuItemSeleccionables = new ArrayList();

		HtmlCommandJSCookMenu command = (HtmlCommandJSCookMenu) event.getSource();
		String label = (String) command.getValue();
		StringTokenizer token01 = new StringTokenizer(label, "(");
		token01.nextToken();
		StringTokenizer token02 = new StringTokenizer(token01.nextToken(), ")");
		String id = token02.nextToken().toString();

		MenuItem buscado = PermisoUtil.menuItemSegunId(menuPermitido, new Long(id));
		generarTabla(buscado);
	}


	private void generarTabla(MenuItem buscado) {
		boolean markado = PermisoUtil.rolMarcado(rolMenusItems, buscado.getIdMenuItem());
		MenuItemSeleccionable menu = new MenuItemSeleccionable(buscado, markado);
		menuItemSeleccionables.add(menu);

		Set hijos = buscado.getHijos();
		if (!hijos.isEmpty()) {
			Iterator iter = hijos.iterator();
			while (iter.hasNext()) {
				MenuItem item = ((MenuItemRelacion) iter.next()).getHijo();
				generarTabla(item);
			}
		}
	}


	public void mortrarLaPagina() {

	}


	public String cancelar() {
		inicializar();
		return null;
	}


	public String grabar() {
		if (opcionSeleccionada.equals(new Integer(1))) {
			if (!menuItemSeleccionables.isEmpty()) {
				Iterator iter = menuItemSeleccionables.iterator();
				while (iter.hasNext()) {
					MenuItemSeleccionable item = (MenuItemSeleccionable) iter.next();
					try {
						List itemActual = operadorService.getRolMenuItemService()
								.getRolMenuItems(rolSeleccionado, item.getMenuItem().getIdMenuItem());

						if (item.getSeleccionado()) {
							if (itemActual.isEmpty()) {
								RolMenuItem temp = new RolMenuItem();
								temp.setMenuItem(item.getMenuItem());
								temp.setRol(new Rol(rolSeleccionado));
								operadorService.getRolMenuItemService().grabarRolMenuItem(temp);
							}
						} else {
							if (!itemActual.isEmpty()) {
								operadorService.getRolMenuItemService().borrarRolMenuItem(((RolMenuItem) itemActual.get(0)).getIdRolMenuItem());
							}
						}
					} catch (RolMenuItemException e) {
						e.printStackTrace();
					}
				}

				try {
					rolMenusItems = operadorService.getRolMenuItemService().getRolMenuItems(rolSeleccionado);
				} catch (RolMenuItemException e3) {
					e3.printStackTrace();
				}
			}
		}

		// if(opcionSeleccionada.equals(new Integer(2))) {
		if (!paginasSeleccionables.isEmpty()) {
			Iterator iter = paginasSeleccionables.iterator();
			while (iter.hasNext()) {
				PaginaSeleccionable paginaSeleccionable = (PaginaSeleccionable) iter.next();
				Pagina pagina = paginaSeleccionable.getPagina();
				List permisoParaPagina = PermisoUtil.paginasPorRol(paginasPermisos, pagina);

				RolPaginaPermiso temp = new RolPaginaPermiso();
				temp.setPagina(pagina);
				temp.setRol(new Rol(rolSeleccionado));

				if (paginaSeleccionable.getAcceso()) {
					if (!permisoParaPagina.contains(TipoPermiso.ACCESO)) {
						// RolPaginaPermiso t=temp;
						// if (temp.getPermiso() != null){
						// t = new RolPaginaPermiso();
						// t.setRol(new Rol(rolSeleccionado));
						// }
						temp.setPermiso(new Permiso(TipoPermiso.ACCESO, null, null));
						PermisoUtil.grabarPermiso(operadorService, temp);

						temp = new RolPaginaPermiso();
						temp.setPagina(pagina);
						temp.setRol(new Rol(rolSeleccionado));
					}
				} else {
					if (permisoParaPagina.contains(TipoPermiso.ACCESO)) {
						PermisoUtil.borrarPermiso(operadorService, PermisoUtil.idPagina(paginasPermisos, pagina.getIdPagina(), TipoPermiso.ACCESO));
					}
				}

				if (paginaSeleccionable.getAlta()) {
					if (!permisoParaPagina.contains(TipoPermiso.ALTA)) {
						temp.setPermiso(new Permiso(TipoPermiso.ALTA, null, null));
						PermisoUtil.grabarPermiso(operadorService, temp);

						temp = new RolPaginaPermiso();
						temp.setPagina(pagina);
						temp.setRol(new Rol(rolSeleccionado));
					}
				} else {
					if (permisoParaPagina.contains(TipoPermiso.ALTA)) {
						PermisoUtil.borrarPermiso(operadorService, PermisoUtil.idPagina(paginasPermisos, pagina.getIdPagina(), TipoPermiso.ALTA));
					}
				}

				if (paginaSeleccionable.getBaja()) {
					if (!permisoParaPagina.contains(TipoPermiso.BAJA)) {
						temp.setPermiso(new Permiso(TipoPermiso.BAJA, null, null));
						PermisoUtil.grabarPermiso(operadorService, temp);

						temp = new RolPaginaPermiso();
						temp.setPagina(pagina);
						temp.setRol(new Rol(rolSeleccionado));
					}
				} else {
					if (permisoParaPagina.contains(TipoPermiso.BAJA)) {
						PermisoUtil.borrarPermiso(operadorService, PermisoUtil.idPagina(paginasPermisos, pagina.getIdPagina(), TipoPermiso.BAJA));
					}
				}

				if (paginaSeleccionable.getModificacion()) {
					if (!permisoParaPagina.contains(TipoPermiso.MODIFICACION)) {
						temp.setPermiso(new Permiso(TipoPermiso.MODIFICACION, null, null));
						PermisoUtil.grabarPermiso(operadorService, temp);

						temp = new RolPaginaPermiso();
						temp.setPagina(pagina);
						temp.setRol(new Rol(rolSeleccionado));
					}
				} else {
					if (permisoParaPagina.contains(TipoPermiso.MODIFICACION)) {
						PermisoUtil.borrarPermiso(operadorService,
								PermisoUtil.idPagina(paginasPermisos, pagina.getIdPagina(), TipoPermiso.MODIFICACION));
					}
				}

				if (paginaSeleccionable.getExportacion()) {
					if (!permisoParaPagina.contains(TipoPermiso.EXPORTACION)) {
						temp.setPermiso(new Permiso(TipoPermiso.EXPORTACION, null, null));
						PermisoUtil.grabarPermiso(operadorService, temp);

						temp = new RolPaginaPermiso();
						temp.setPagina(pagina);
						temp.setRol(new Rol(rolSeleccionado));
					}
				} else {
					if (permisoParaPagina.contains(TipoPermiso.EXPORTACION)) {
						PermisoUtil.borrarPermiso(operadorService,
								PermisoUtil.idPagina(paginasPermisos, pagina.getIdPagina(), TipoPermiso.EXPORTACION));
					}
				}

			}

			if (opcionSeleccionada.equals(new Integer(2)))
				cargarTablaPermisos();
			// }

		}

		return null;
	}


	public String buscarPagina() {
		cargarMenu();
		return null;
	}


	private void cargarTablaPermisos() {
		try {
			paginasPermisos = operadorService.getRolPaginaService().getPaginasPorRol(rolSeleccionado);
			System.out.println("Cantidad Paginas Permisos: " + paginasPermisos.size());
		} catch (RolPaginaPermisoException e) {
			e.printStackTrace();
		}

		try {
			if (paginaBuscada != null && !paginaBuscada.equals("")) {
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("pagina", Filtro.LIKE, paginaBuscada);
				log.info(filtro.getHQL());
				paginas = operadorService.getPaginaService().getPaginas(filtro);
			} else {
				paginas = operadorService.getPaginaService().getPaginas();
			}
			log.info("Paginas buscadas: " + paginaBuscada);
			System.out.println("Cantidad Paginas Permisos: " + paginas.size());
		} catch (PaginaException e1) {
			e1.printStackTrace();
		}

		paginasSeleccionables = PermisoUtil.paginasSeleccionables(paginas, paginasPermisos, rolSeleccionado);
		// paginas = todas las paginas cargadas
		// paginasPermisos = todas las paginas cargadas con algun permiso especifico para un rol
		// paginasSeleccionables = todas las paginas cargadas (wrapeadas) con los permisos establecidos segun se lee de la base

		PaginaSeleccionable pSeleccionable = new PaginaSeleccionable();
		paginasSeleccionables.add(0, pSeleccionable);
	}


	public String marcarTodos() {
		if (!menuItemSeleccionables.isEmpty()) {

			boolean primero = ((MenuItemSeleccionable) menuItemSeleccionables.get(0)).getSeleccionado();

			Iterator iterPag = menuItemSeleccionables.iterator();
			while (iterPag.hasNext()) {
				MenuItemSeleccionable pSeleccionable = (MenuItemSeleccionable) iterPag.next();
				pSeleccionable.setSeleccionado(!primero);
			}
		}
		return null;
	}


	public String marcarL() {
		if (!paginasSeleccionables.isEmpty()) {
			boolean primero = ((PaginaSeleccionable) paginasSeleccionables.get(0)).getAcceso();
			Iterator iterPag = paginasSeleccionables.iterator();
			while (iterPag.hasNext()) {
				PaginaSeleccionable pSeleccionable = (PaginaSeleccionable) iterPag.next();
				pSeleccionable.setAcceso(!primero);
			}
		}
		return null;
	}


	public String marcarA() {
		if (!paginasSeleccionables.isEmpty()) {
			boolean primero = ((PaginaSeleccionable) paginasSeleccionables.get(0)).getAlta();
			Iterator iterPag = paginasSeleccionables.iterator();
			while (iterPag.hasNext()) {
				PaginaSeleccionable pSeleccionable = (PaginaSeleccionable) iterPag.next();
				pSeleccionable.setAlta(!primero);
			}
		}
		return null;
	}


	public String marcarB() {
		if (!paginasSeleccionables.isEmpty()) {
			boolean primero = ((PaginaSeleccionable) paginasSeleccionables.get(0)).getBaja();
			Iterator iterPag = paginasSeleccionables.iterator();
			while (iterPag.hasNext()) {
				PaginaSeleccionable pSeleccionable = (PaginaSeleccionable) iterPag.next();
				pSeleccionable.setBaja(!primero);
			}
		}
		return null;
	}


	public String marcarM() {
		if (!paginasSeleccionables.isEmpty()) {
			boolean primero = ((PaginaSeleccionable) paginasSeleccionables.get(0)).getModificacion();
			Iterator iterPag = paginasSeleccionables.iterator();
			while (iterPag.hasNext()) {
				PaginaSeleccionable pSeleccionable = (PaginaSeleccionable) iterPag.next();
				pSeleccionable.setModificacion(!primero);
			}
		}
		return null;
	}


	public String marcarE() {
		if (!paginasSeleccionables.isEmpty()) {
			boolean primero = ((PaginaSeleccionable) paginasSeleccionables.get(0)).getExportacion();
			Iterator iterPag = paginasSeleccionables.iterator();
			while (iterPag.hasNext()) {
				PaginaSeleccionable pSeleccionable = (PaginaSeleccionable) iterPag.next();
				pSeleccionable.setExportacion(!primero);
			}
		}
		return null;
	}


	public boolean isOrigen() {
		return origen;
	}


	public void setOrigen(boolean origen) {
		this.origen = origen;
	}

}