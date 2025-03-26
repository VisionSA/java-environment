package com.bizitglobal.webapp.faces.beans.fondos;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
//import org.apache.myfaces.custom.tree2.Tree;
//import org.apache.myfaces.custom.tree2.TreeNode;
//import org.apache.myfaces.custom.tree2.TreeNodeBase;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


public class FondosSaldosBean extends BaseBean {
	private static final Logger log = Logger.getLogger(FondosSaldosBean.class);
	private Date fechaHasta;

	private String tipoCuentas;
	private String tipoSaldo;
	private String cuentaDesde;
	private String cuentaHasta;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");


	public FondosSaldosBean() {
		super();
		fechaHasta = null;
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public void borrar() {
		fechaHasta = new Timestamp(new java.util.Date().getTime());
		popupReport = new String("");
		tituloLargo = "";
		tituloCorto = "";
		error.borrar();
		cuentaDesde = "";
		cuentaHasta = "";
		tipoCuentas = "";
		tipoSaldo = "";
	}


	public String inicializar() {
		borrar();
		tituloLargo = "FONDOS";
		tituloCorto = "Reporte Saldos Fondos";
		return "fondosSaldos";
	}


	public boolean validar() {
		if (cuentaDesde != null && !cuentaDesde.equals("") && cuentaHasta != null && !cuentaHasta.equals("")) {
			if (Integer.valueOf(cuentaDesde) > Integer.valueOf(cuentaHasta)) {
				error.agregar(Error.FON_SALDOS_CUENTA_DESDE_HASTA);
			}
		}
		if (tipoCuentas == null || tipoCuentas.equals("")) {
			error.agregar(Error.FON_SALDOS_TIPO_CUENTA_REQUERIDA);
		}
		if (tipoSaldo == null || tipoSaldo.equals("")) {
			error.agregar(Error.FON_SALDOS_TIPO_SALDO_REQUERIDO);
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String generar() {

		if (validar()) {

			HttpServletRequest request = Session.getRequest();
			error.borrar();
			popupReport = new String("");
			String pCuentaDesde = "";
			String pCuentaHasta = "";
			String pTipoCuenta = "";

			if (cuentaDesde.equals("")) {
				cuentaDesde = "0";
			} else {
				pCuentaDesde = cuentaDesde;
			}
			if (cuentaHasta.equals("")) {
				cuentaHasta = "99999";
			} else {
				pCuentaHasta = cuentaHasta;
			}
			if (tipoCuentas.equals("S")) {
				pTipoCuenta = "Mostrando solo cuentas de fondos";
			}
			else {
				if (tipoCuentas.equals("N")) {
					pTipoCuenta = "Exceptuando cuentas de fondos";
				} else {
					pTipoCuenta = "Mostrando todas las Cuentas";
				}
			}

			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?fecha_hasta=" + dateFormat.format(fechaHasta);
			String p2 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p3 = "&tipo_cuenta=" + tipoCuentas;
			String p4 = "&cuenta_desde=" + cuentaDesde;
			String p5 = "&cuenta_hasta=" + cuentaHasta;
			String p6 = "&tipo_saldo=" + tipoSaldo;
			String p7 = "&tipo_cuenta_title=" + pTipoCuenta;
			String p8 = "&cuenta_desde_title=" + pCuentaDesde;
			String p9 = "&cuenta_hasta_title=" + pCuentaHasta;
			String page = request.getContextPath() + "/report/FondosSaldos.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + "',1000,600)";
			log.info(popupReport);
			if (cuentaDesde.compareTo("0") == 0) {
				cuentaDesde = "";
			}
			if (cuentaDesde.compareTo("99999") == 0) {
				cuentaHasta = "";
			}
		}
		return null;
	}


	public String generarAExcel() {

		if (validar()) {

			HttpServletRequest request = Session.getRequest();
			error.borrar();
			popupReport = new String("");
			String pCuentaDesde = "";
			String pCuentaHasta = "";
			String pTipoCuenta = "";

			if (cuentaDesde.equals("")) {
				cuentaDesde = "0";
			} else {
				pCuentaDesde = cuentaDesde;
			}
			if (cuentaHasta.equals("")) {
				cuentaHasta = "99999";
			} else {
				pCuentaHasta = cuentaHasta;
			}
			if (tipoCuentas.equals("S")) {
				pTipoCuenta = "Mostrando solo cuentas de fondos";
			}
			else {
				if (tipoCuentas.equals("N")) {
					pTipoCuenta = "Exceptuando cuentas de fondos";
				} else {
					pTipoCuenta = "Mostrando todas las Cuentas";
				}
			}

			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?fecha_hasta=" + dateFormat.format(fechaHasta);
			String p2 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p3 = "&tipo_cuenta=" + tipoCuentas;
			String p4 = "&cuenta_desde=" + cuentaDesde;
			String p5 = "&cuenta_hasta=" + cuentaHasta;
			String p6 = "&tipo_saldo=" + tipoSaldo;
			String p7 = "&tipo_cuenta_title=" + pTipoCuenta;
			String p8 = "&cuenta_desde_title=" + pCuentaDesde;
			String p9 = "&cuenta_hasta_title=" + pCuentaHasta;
			String p10 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/FondosSaldos.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + "',1000,600)";
			log.info(popupReport);
			cuentaDesde = "";
			cuentaHasta = "";
		}

		return null;
	}


	public String getTipoCuentas() {
		return tipoCuentas;
	}


	public void setTipoCuentas(String tipoCuentas) {
		this.tipoCuentas = tipoCuentas;
	}


	public String getTipoSaldo() {
		return tipoSaldo;
	}


	public void setTipoSaldo(String tipoSaldo) {
		this.tipoSaldo = tipoSaldo;
	}


	public String getCuentaDesde() {
		return cuentaDesde;
	}


	public void setCuentaDesde(String cuentaDesde) {
		this.cuentaDesde = cuentaDesde;
	}


	public String getCuentaHasta() {
		return cuentaHasta;
	}


	public void setCuentaHasta(String cuentaHasta) {
		this.cuentaHasta = cuentaHasta;
	}

}
