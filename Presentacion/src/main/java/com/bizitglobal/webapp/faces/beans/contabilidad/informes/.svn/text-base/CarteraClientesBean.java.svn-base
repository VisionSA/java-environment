package com.bizitglobal.webapp.faces.beans.contabilidad.informes;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.general.exception.ParametroSistemaDetalleException;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistemaDetalle;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;

public class CarteraClientesBean extends BaseBean{
	private static final Logger log = Logger.getLogger(CarteraClientesBean.class);
	
	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");
	private boolean panelProcesar;
	private boolean panelGenerar;
	private Date fecha;
	private ParametroSistemaDetalle paramSis;
	private String ultimaFecha;
	
	public String getUltimaFecha() {
		return ultimaFecha;
	}

	public void setUltimaFecha(String ultimaFecha) {
		this.ultimaFecha = ultimaFecha;
	}

	public ParametroSistemaDetalle getParamSis() {
		return paramSis;
	}

	public void setParamSis(ParametroSistemaDetalle paramSis) {
		this.paramSis = paramSis;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isPanelProcesar() {
		return panelProcesar;
	}

	public void setPanelProcesar(boolean panelProcesar) {
		this.panelProcesar = panelProcesar;
	}

	public boolean isPanelGenerar() {
		return panelGenerar;
	}

	public void setPanelGenerar(boolean panelGenerar) {
		this.panelGenerar = panelGenerar;
	}

	public CarteraClientesBean () {
		super();
	}

	public String getPopupReport() {
			return popupReport;
		}

	public void setPopupReport(String popupReport) {
			this.popupReport = popupReport;
		}

	
	@Override
	public void borrar() {
		Calendar f = Calendar.getInstance();
		f.add(Calendar.DAY_OF_MONTH, -1);
		fecha = f.getTime();
		cargarVista();
		popupReport = new String("");
	}
	
	@Override
	public String inicializar() {
		borrar();

		tituloLargo = "INFORMES";
		tituloCorto = "Cartera de Cliente";
		cargarVista();

		return "carteraClientes";
	}

	@Override
	public boolean validar() {
		error.borrar();
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public String procesarCarteraClientes(ActionEvent event){
		try{
			Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			paramSis.setValor(dateFormat.format(fecha));
			transaccionesService.getCtaCteClienteService().procesarCarteraClientes(fecha);
			log.info("Cartera de clientes procesada con éxito.");
			generalService.getParametroSistemaDetalleService().actualizarParametroSistemaDetalle(paramSis);
			panelProcesar = false;
			panelGenerar = true;
		} catch (ParametroSistemaDetalleException e) {
			paramSis = null;
			e.printStackTrace();
		}
		return "carteraClientes";
	}
	
	
	public void generarReporteCarteraClientes(ActionEvent event){
		popupReport = new String("");

		HttpServletRequest request = Session.getRequest();
		
		String p1 = "";
		
		p1 = "?AExcel=excel";

		String page = request.getContextPath() + "/report/Cartera_clientes.jrxml";

		popupReport = "popup('" + page + p1 + "',1000,600)";
		log.info(popupReport);
		
		
	}
	
	public void cargarVista(){
		try {
			//Este parametro del sistema contiene la ultima fecha en la que se procesá la cartera de clientes.
			paramSis = generalService.getParametroSistemaDetalleService().leerParametroSistemaDetalle(32L);
			ultimaFecha = paramSis.getValor();
			char[] fechaAux = ultimaFecha.toCharArray();
			Date date = new Date();
			int mesProcesamiento = Integer.parseInt("" + fechaAux[3] + fechaAux[4]);
			int mesActual = date.getMonth() + 1; //getMonth devuelve un nro de 0 a 11. Le sumo 1 para comparar con el mes obtenido de la tabla, que va de 1 a 12.
			if((mesProcesamiento+1 == mesActual) || (mesProcesamiento==12 && mesActual == 1)){
				panelProcesar = false;
				panelGenerar = true;
			}
			else{
				ultimaFecha = null;
				panelProcesar = true;
				panelGenerar = false;
			}
			
		} catch (ParametroSistemaDetalleException e) {
			paramSis = null;
			panelProcesar = true;
			panelGenerar = false;
			e.printStackTrace();
		}
	}

}
