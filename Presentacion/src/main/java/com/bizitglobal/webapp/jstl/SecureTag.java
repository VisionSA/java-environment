package com.bizitglobal.webapp.jstl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.operador.exeption.RolPaginaPermisoException;
import com.bizitglobal.tarjetafiel.operador.negocio.Permiso;
import com.bizitglobal.webapp.faces.service.operador.ABMOperadorServiceFaces;
import com.bizitglobal.webapp.faces.util.Session;

public class SecureTag extends TagSupport{
	private static final long serialVersionUID = -4345246917115414684L;
	private static Logger log = Logger.getLogger(SecureTag.class);
	private static final ABMOperadorServiceFaces operadorService = new ABMOperadorServiceFaces();
	private List permisos = new ArrayList();
	private String paginaAnterior = ""; 
	public static boolean contains(List unaLista, String unaCadena) {
		return unaLista.contains(unaCadena);
	}
	
	/**
	 * El metodo containsPage controla si el usuario tiene el permiso requerido 'unaCadena' para la pagina 'page'
	 * @param page El nombre de la pagina jsf virtual a la cual se quiere acceder, sin el .jsf. Solo el nombre
	 * @param unaCadena El nombre del permiso requerido. Son opciones posibles: alta, edicion, 
	 * */
	public static boolean containsPage(String page, String unaCadena) {
		boolean resul = false;
		String pagina = page+ "." + "jsf";
		List permisosExternos = null;
		try {
			permisosExternos = operadorService.getRolPaginaService().getPermisosPorPaginaYRol(pagina, Session.getOperador().getRol().getIdRol());
		} catch (RolPaginaPermisoException e) {
			e.printStackTrace();
			Session.redirect("/login.jsf");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			Session.redirect("/login.jsf");
			return false;
		}
		Iterator ite = permisosExternos.iterator();
		while (ite.hasNext()) {
			Permiso permi = (Permiso)ite.next();
			if (permi.getNombre().compareTo(unaCadena)==0) {
				resul = true;
				break;
			}
		}
		return resul;
	}
	
	public int doStartTag() throws JspException {
		StringTokenizer tokens = new StringTokenizer(pageContext.getPage().getClass().getSimpleName(),"_");
		String pagina = tokens.nextToken() + "." + "jsf";
		if (!pagina.equals(paginaAnterior) || permisos.isEmpty()) {
			paginaAnterior = pagina;
			permisos.clear();
			
			try {
				permisos = operadorService.getRolPaginaService().getPermisosPorPaginaYRol(pagina, Session.getOperador().getRol().getIdRol());
			} catch (RolPaginaPermisoException e) {
				e.printStackTrace();
				Session.redirect("/login.jsf");
			} catch (Exception e) {
				e.printStackTrace();
				Session.redirect("/login.jsf");
				return TagSupport.SKIP_BODY;
			}
		}
		
		if(!permisos.isEmpty()) {
			Session.setAttribute("permisos", permisos);
			if (!permisos.contains(new Permiso(new Integer(1)))) {
				Session.redirect("/login.jsf");
			}
		} else {
			Session.redirect("/login.jsf");
		}
		
		return TagSupport.SKIP_BODY;
	}

}
