package com.bizitglobal.webapp.faces.beans.transacciones;

import org.apache.log4j.Logger;

import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;


public class PagoFacilBean extends BaseBean {
	private static final Logger log = Logger.getLogger(PagoFacilBean.class);


	public PagoFacilBean() {
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
		tituloCorto = "Pago Facil";
		popup.borrar();
	}


	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}

		return "pagoFacil";
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
