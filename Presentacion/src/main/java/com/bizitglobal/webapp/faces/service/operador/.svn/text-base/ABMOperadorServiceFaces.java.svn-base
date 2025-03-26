package com.bizitglobal.webapp.faces.service.operador;

import com.bizitglobal.tarjetafiel.operador.service.MenuItemRelacionService;
import com.bizitglobal.tarjetafiel.operador.service.MenuItemService;
import com.bizitglobal.tarjetafiel.operador.service.OperadorService;
import com.bizitglobal.tarjetafiel.operador.service.PaginaService;
import com.bizitglobal.tarjetafiel.operador.service.PermisoService;
import com.bizitglobal.tarjetafiel.operador.service.RolMenuItemService;
import com.bizitglobal.tarjetafiel.operador.service.RolPaginaPermisoService;
import com.bizitglobal.tarjetafiel.operador.service.RolService;
import com.bizitglobal.webapp.faces.service.BaseService;


public class ABMOperadorServiceFaces extends BaseService {
	private static final String OPERADOR_SERVICE_NAME = "operadorService";
	private static final String PERMISO_SERVICE_NAME = "permisoService";
	private static final String MENU_ITEM_RELACION_SERVICE_NAME = "menuItemRelacionService";
	private static final String ROL_PAGINA_SERVICE_NAME = "rolPaginaPermisoService";
	private static final String ROL_SERVICE_NAME = "rolService";
	private static final String MENU_ITEM_SERVICE_NAME = "menuItemService";
	private static final String ROL_MENU_ITEM_SERVICE_NAME = "rolMenuItemService";
	private static final String PAGINA_SERVICE_NAME = "paginaService";

	private OperadorService operadorService;
	private PermisoService permisoService;
	private MenuItemRelacionService menuItemRelacionService;
	private RolPaginaPermisoService rolPaginaService;
	private RolService rolService;
	private MenuItemService menuItemService;
	private RolMenuItemService rolMenuItemService;
	private PaginaService paginaService;


	public ABMOperadorServiceFaces() {
		super();
		this.operadorService = (OperadorService) this.lookupService(OPERADOR_SERVICE_NAME);
		this.permisoService = (PermisoService) this.lookupService(PERMISO_SERVICE_NAME);
		this.menuItemRelacionService = (MenuItemRelacionService) this.lookupService(MENU_ITEM_RELACION_SERVICE_NAME);
		this.rolPaginaService = (RolPaginaPermisoService) this.lookupService(ROL_PAGINA_SERVICE_NAME);
		this.rolService = (RolService) this.lookupService(ROL_SERVICE_NAME);
		this.menuItemService = (MenuItemService) this.lookupService(MENU_ITEM_SERVICE_NAME);
		this.rolMenuItemService = (RolMenuItemService) this.lookupService(ROL_MENU_ITEM_SERVICE_NAME);
		this.paginaService = (PaginaService) this.lookupService(PAGINA_SERVICE_NAME);
	}


	public OperadorService getOperadorService() {
		return this.operadorService;
	}


	public PermisoService getPermisoService() {
		return this.permisoService;
	}


	public MenuItemRelacionService getMenuItemRelacionService() {
		return menuItemRelacionService;
	}


	public RolPaginaPermisoService getRolPaginaService() {
		return rolPaginaService;
	}


	public RolService getRolService() {
		return rolService;
	}


	public MenuItemService getMenuItemService() {
		return menuItemService;
	}


	public RolMenuItemService getRolMenuItemService() {
		return rolMenuItemService;
	}


	public PaginaService getPaginaService() {
		return paginaService;
	}
}
