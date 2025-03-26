package com.bizitglobal.webapp.faces.beans.login;

import java.util.ArrayList;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import weborb.config.ORBConfig;

import com.bizitglobal.tarjetafiel.operador.exeption.OperadorException;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.webapp.faces.beans.operador.OperadorBean;
import com.bizitglobal.webapp.faces.beans.operador.PermisoBean;
import com.bizitglobal.webapp.faces.beans.transacciones.AutorizacionTelefonicaBean;
import com.bizitglobal.webapp.faces.beans.transacciones.ConsultaBCRABean;
import com.bizitglobal.webapp.faces.beans.util.MenuBean;
import com.bizitglobal.webapp.faces.beans.workflow.EscritorioBean;
import com.bizitglobal.webapp.faces.service.operador.ABMOperadorServiceFaces;
import com.bizitglobal.webapp.faces.util.Session;


public class LoginBean {
	private static final Logger log = Logger.getLogger(LoginBean.class);

	private String username;
	private String clave;
	private boolean estado; // Su estado no le permite ingresar, consulte con el administrador del sistema.
	private boolean estadoDos; // La clave es incorrecta. A los tres intentos se bloqueará su cuenta.
	private boolean estadoTres; // El nombre de operador ingresado no existe.
	private boolean estadoCuatro; // Su cuenta se encuentra bloqueada. Contacte a el administrador del sistema.
	private boolean estadoCinco; // Falló la conección con la base. Espere unos minutos o contacte a el administrador del sistema.
	private int intentos;

	ABMOperadorServiceFaces service = new ABMOperadorServiceFaces();


	public LoginBean() {
		this(null, null);
	}


	public LoginBean(String username, String clave) {
		super();
		this.username = username;
		this.clave = clave;
		this.estado = false;
		this.estadoDos = false;
		this.estadoTres = false;
		this.estadoCuatro = false;
		this.estadoCinco = false;
		intentos = 0;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public String ingreso() {
		this.estado = false;
		this.estadoDos = false;
		this.estadoTres = false;
		this.estadoCuatro = false;
		this.estadoCinco = false;
		String result = "falla_ingreso";

		Operador operador = null;
		try {
			System.out.println("Entrando a login");
			// recupero el operador
			operador = service.getOperadorService().login(username);
			log.info("Se leyo el operador");
		} catch (Exception e) {
			this.estadoCinco = true;
			e.printStackTrace();
			return null;
		}
		if (operador != null && !operador.getClave().equals(clave)) {
			estadoDos = true;
			intentos++;
			if (intentos > 2) {
				// bloqueo el operador
				operador.setEstado("B");
				try {
					log.info(operador.toString());
					service.getOperadorService().actualizarOperador(operador);
				} catch (OperadorException e) {
					log.info("Se intento bloquear el usuario, pero ha fallado.");
					e.printStackTrace();
				}
			}
		} else {
			if (operador == null)
				estadoTres = true;
			if (operador != null) {
				if (operador.getEstado().equals(new String("B"))) {
					estadoCuatro = true;
				} else {
					if (operador.getEstado().equals(new String("A"))) {

						result = "ingreso";
						Session.setOperador(operador);

						/*
						 * List menus = new ArrayList(); Set rolMenus = operador.getRol().getMenuItems(); if(!rolMenus.isEmpty()) { Iterator rolMenusI
						 * = rolMenus.iterator(); while(rolMenusI.hasNext()) { menus.add(((RolMenuItem)rolMenusI.next()).getMenuItem()); } }
						 * 
						 * Collections.sort(menus);
						 * 
						 * ((MenuBean)Session.getBean("MenuBean")).reset(); ((MenuBean)Session.getBean("MenuBean")).setMenu(menus);
						 */

						Session.setAttribute("permisos", new ArrayList());
						((MenuBean) Session.getBean("MenuBean")).crear();

						borrar();
						log.info("Operador ingresa al sistema->" + operador);
					} else {

						estado = true;
					}
				}
			}
		}

		return result;
	}


	public String callEscritorio() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		if (!(codigo.equals("23"))) {
			EscritorioBean escritorioBean = (EscritorioBean) Session.getBean("EscritorioBean");
			return escritorioBean.inicializar();
		}
		return "";
	}


	public void callCaja() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		if (!(codigo.equals("23"))) {
			ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
			ejecutarJavaScript("popup('" + "CajaFiel.swf?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");
		}
	}


	public void callReclamosFiel() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		if (!(codigo.equals("23"))) {
			ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
			ejecutarJavaScript("popup('" + "ReclamosVerFiel.swf?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");
		}

	}


	public void callPlasticosFiel() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		String rol = Session.getOperador().getRol().getIdRol().toString().trim();
		if (!(codigo.equals("23")) && !(rol.equals("20"))) {
			ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
			ejecutarJavaScript("popup('" + "PlasticosVerFiel.swf?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");
		//	ejecutarJavaScript("popup('" + "plasticos/index.html" + "',window.screen.width,window.screen.height);");
		}

	}


	public void callCajaJudicialFiel() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		if (!(codigo.equals("23"))) {
			ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
			ejecutarJavaScript("popup('" + "CajaJudicialFiel.swf?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");
		}

	}


	public void callConsultasFlex() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
		ejecutarJavaScript("popup('" + "ConsultaFiel.swf?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");		
	}
	
	public void callConsultasPruebaComercio() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);	
		System.out.println("codigo " + codigo);
		ejecutarJavaScript("popup('" + "tarjetafiel/transacciones/ConsultaComercio/index.html?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");
	}
	
	public void callConsultaFielAnt() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);	
		System.out.println("codigo " + codigo);
		ejecutarJavaScript("popup('" + "PagosExternos.swf?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");
	}
	
	


	public void callConsultaCobradoresFlex() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		if (codigo.equals("23")) {
			ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
			ejecutarJavaScript("popup('" + "ConsultaCobradoresFiel.swf?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");
		}
	}

	public String callAutorizacion() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		if (!(codigo.equals("23"))) {
			AutorizacionTelefonicaBean autorizacionTelefonicaBean = (AutorizacionTelefonicaBean) Session.getBean("AutorizacionTelefonicaBean");
			autorizacionTelefonicaBean.borrar();
			ejecutarJavaScript("popup('" + "tarjetafiel/transacciones/autorizacionTelefonica.jsf" + "',window.screen.width,window.screen.height);");
		}
		return "";
	}

	public void callAutorizacionFlex() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		if (!(codigo.equals("23"))) {
			ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
			ejecutarJavaScript("popup('" + "AutorizacionTelefonica.swf?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");
		}
	}
	
	public String callConsultaBCRA() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		if (!(codigo.equals("23"))) {
			ConsultaBCRABean consultaBCRABean = (ConsultaBCRABean) Session.getBean("ConsultaBCRABean");
			return consultaBCRABean.inicializar();
		}
		return "";
	}


	public void callVariosFlex() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		if (!(codigo.equals("23"))) {
			ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
			ejecutarJavaScript("popup('" + "VariosFiel.swf?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");
		}
	}


	public void callCobranzasFlex() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		if (!(codigo.equals("23"))) {
			ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
			ejecutarJavaScript("popup('" + "Cobranzas.swf?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");
		}
	}


	public void callBizTracker() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		if (!(codigo.equals("23"))) {
			ejecutarJavaScript("window.open('http://190.210.15.218/biztracker/');");
		}
	}


	public void callGestionCliente() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		String rol = Session.getOperador().getRol().getIdRol().toString().trim();
		if (!(codigo.equals("23")) && !(rol.equals("20"))) {
			ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
			ejecutarJavaScript("popup('" + "GestionClienteFiel.swf?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");
		}
	}


	public void callEmailFiel() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		ejecutarJavaScript("window.open('https://www.google.com/a/tarjetafiel.com/ServiceLogin');");
	}


	public void callConciliacionBancariaFlex() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		if (!(codigo.equals("23"))) {
			ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
			ejecutarJavaScript("popup('" + "ConciliacionBancaria.swf?codigoOperador=" + codigo + "',window.screen.width,window.screen.height);");
		}
	}


	public void ejecutarJavaScript(String script) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		AddResource addResource = AddResourceFactory.getInstance(facesContext);
		addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, script);
	}


	public String salir() {
		OperadorBean operadorBean = (OperadorBean) Session.getBean("OperadorBean");
		PermisoBean permisoBean = (PermisoBean) Session.getBean("PermisoBean");

		if (operadorBean != null) {
			operadorBean.borrar();
		}

		if (permisoBean != null) {
			permisoBean.borrar();
		}

		log.info("Operador sale del sistema->" + Session.getOperador());
		Session.removeOperador();
		// Session.getRequest().getSession().invalidate();
		return "login";
	}


	public void borrar() {
		this.username = null;
		this.clave = null;
		this.estado = false;
		this.estadoDos = false;
		this.estadoTres = false;
		this.estadoCuatro = false;
		this.estadoCinco = false;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public boolean getEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public boolean isEstadoDos() {
		return estadoDos;
	}


	public void setEstadoDos(boolean estadoDos) {
		this.estadoDos = estadoDos;
	}


	public boolean isEstadoTres() {
		return estadoTres;
	}


	public void setEstadoTres(boolean estadoTres) {
		this.estadoTres = estadoTres;
	}


	public boolean isEstadoCuatro() {
		return estadoCuatro;
	}


	public void setEstadoCuatro(boolean estadoCuatro) {
		this.estadoCuatro = estadoCuatro;
	}


	public boolean isEstadoCinco() {
		return estadoCinco;
	}


	public void setEstadoCinco(boolean estadoCinco) {
		this.estadoCinco = estadoCinco;
	}
	
}
