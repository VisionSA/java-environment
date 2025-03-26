package com.bizitglobal.webapp.faces.beans.transacciones;

import org.apache.log4j.Logger;

import weborb.config.ORBConfig;

import com.bizitglobal.tarjetafiel.operador.exeption.OperadorException;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;


public class RevertirPagoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(RevertirPagoBean.class);


	public RevertirPagoBean() {
		super();
		borrar();
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void borrar() {
		error.borrar();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Revertir Pago";
		popup.borrar();
	}


	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
			try {
				callConsultasFlex();
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OperadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	public void callConsultasFlex() throws RuntimeException, OperadorException {
		String codigo = Session.getOperador().getCodigo().toString();
		ORBConfig.getServletContext().setAttribute(codigo.toString(), codigo);
		ejecutarJavaScript("popup('" + "ReversionPagos.swf?codigoOperador=" + codigo + "',1000,800);");
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}

}
