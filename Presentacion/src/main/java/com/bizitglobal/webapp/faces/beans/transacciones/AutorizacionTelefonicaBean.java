package com.bizitglobal.webapp.faces.beans.transacciones;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.webapp.faces.beans.BaseBean;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.bizitglobal.webapp.faces.util.Session;
import com.visionis.transaccionador.negocio.Cuota;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CuotaSimulada;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Comercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.MovimientoAnulacion;



public class AutorizacionTelefonicaBean extends BaseBean{
	private static final Logger log = Logger.getLogger(AutorizacionTelefonicaBean.class);
	
	//parametros
	private String nroTarjeta;
	private String cvv;
		
	private String codComercio;
		
	private String monto;
	private String cuotas;
	private String nroCupon;
	private List<CuotaSimulada> cuotaSimulada;
	
	
	//Datos Cliente
	private String nroCuenta;
	private String nombreTitular;
	private String nombreAdicional;
	private String nombreTarjeta;
	private String dni;
	private String vtoPlastico;
	private String estadoPlastico;
	private String situacionComercial;
	private String situacionCobranza;
	private String consumoHabilitado;
	private String lineaCredito;
	private String saldo;
	private String disponible;
	private boolean consumoHabilitadoBool;
	private boolean plasticoVencido;
	private Integer diaPago;
			
			
	//Datos Comercio
	private String empresa;
	private String sucursal;
	private String localidad;
	private String direccion;
	private String telefono;
	private String planCuotas;
	private Long idListaPrecio;
	private String cuotasDisponibles;
	private String parametroBusqueda;
		
	private List<Comercio> listaComercios;
	private List<MovimientoAnulacion> movimientosPendientes;
	private Long idTransaccionSeleccionada;
	
	private Operador operador;
	
	//Mostrar/Ocultar paneles
	private boolean panelCliente;
	private boolean panelComercio;
	private boolean datosComercio;
	private boolean panelOperacion;
		
		
	// 1- Compras
	// 2- Anulaciones
	// 3- Devoluciones
	private int selectedTab;
	
	private boolean error;
	private String msjError;
	private boolean modalAprobada;
	
		
	private String codAutorizacion;
	private String mensaje;
	
	private boolean modalBusquedaComercio;
	private boolean modalSimularCuotas;
	
	
	private boolean sesionVencida;
	
	private PropertieFile prop;
	private String ipTransaccionador;
	private int puerto;
	private boolean transaccionadorOk;
	
	
	public AutorizacionTelefonicaBean() {
		borrar();
	}
	
	
	@Override
	public boolean validar() {
		return false;
	}

	@Override
	public String inicializar() {
		borrar();
		return "autorizacionTelefonica";
	}
	
	
	public void borrar() {
		
		nroTarjeta = null;
		cvv = null;
		

		//datos cliente
		nroCuenta = null;
		nombreTitular = null;
		nombreAdicional = null;
		nombreTarjeta = null;
		dni = null;
		vtoPlastico = null;
		estadoPlastico = null;
		situacionComercial = null;
		situacionCobranza = null;
		consumoHabilitado = null;
		lineaCredito = null;
		saldo = null;
		disponible = null;
		consumoHabilitadoBool = false;
		plasticoVencido = false;
		diaPago = null;
		
		panelCliente = false;
		panelComercio = false;
		selectedTab = 1; //Por defecto Tab de Compras
		
		codAutorizacion = null;
		mensaje = null;
		
		limpiarComercio();
	
		transaccionadorOk = false;
		prop = new PropertieFile(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator
				+ "contexto.properties");
		try {
			ipTransaccionador = prop.getProperties("ipTransaccionador");
			transaccionadorOk = true;
			puerto = 5000;
			log.info("IP Transaccionador: " + ipTransaccionador);
			log.info("Puerto: " + puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void limpiarComercio(){

		//datos comercio
		codComercio = null;
		empresa = null;
		sucursal = null;
		localidad = null;
		direccion = null;
		telefono = null;
		planCuotas = null;
		idListaPrecio = null;
		cuotasDisponibles = null;
		modalBusquedaComercio = false;
		modalSimularCuotas = false;
		
		parametroBusqueda = null;
			
		datosComercio = false;
		panelOperacion = false;
				
		error = false;
		msjError = null;
		modalAprobada = false;
				
		monto = null;
		cuotas = null;
		nroCupon = null;
		cuotaSimulada = null;
				
		listaComercios = null;
		movimientosPendientes = null;
		idTransaccionSeleccionada = 0L;
		
		controlarSesion();
	}
	
	
	public void limpiarTab(int tab){
		borrar();
		selectedTab = tab;
	}


	public void controlarSesion(){
		error = false;
		modalBusquedaComercio = false;
		modalSimularCuotas = false;
		sesionVencida = false;
		try{
			Long operador = Session.getOperador().getCodigo();
			if(operador == null || operador == 0){
				sesionVencida = true;
			}
		}
		catch(Exception e){
			sesionVencida = true;
		}
	}
	
	public String buscarClienteCompra(){
		return buscarCliente(1);
	}
	public String buscarClienteAnulacion(){
		return buscarCliente(2);
	}
	public String buscarClienteDevolucion(){
		return buscarCliente(3);
	}
	
	
	public String buscarCliente(int currentTab){
		String auxCvv = this.cvv;
		String auxNroTarjeta = this.nroTarjeta;
		limpiarTab(currentTab);
		cvv = auxCvv;
		nroTarjeta = auxNroTarjeta;
		
		if(sesionVencida){
			limpiarTab(selectedTab);
			return "autorizacionTelefonica";
		}
		if(!transaccionadorOk){
			limpiarTab(selectedTab);
			return "autorizacionTelefonica";
		}
		
		List<String> datosCli = transaccionesService.getTransaccionService().getDatosCliente(nroTarjeta, cvv);
		if(datosCli!=null){
			if(datosCli.get(0).equals("Error")){
				limpiarTab(selectedTab);
				error = true;
				msjError = datosCli.get(1);
			}
			else{ 
				error = false;
				if(datosCli.get(0)!=null){		
					nroCuenta = datosCli.get(0);
				}
				if(datosCli.get(1)!=null){
					nombreTitular = datosCli.get(1);
				}
				if(datosCli.get(2)!=null){
					nombreAdicional = datosCli.get(2);
				}
				if(datosCli.get(3)!=null){
					dni = datosCli.get(3);
				}
				if(datosCli.get(4)!=null){
					vtoPlastico = datosCli.get(4);
				}
				if(datosCli.get(5)!=null){
					estadoPlastico = datosCli.get(5);
				}
				if(datosCli.get(6)!=null){
					situacionComercial = datosCli.get(6);
				}
				if(datosCli.get(7)!=null){
					situacionCobranza = datosCli.get(7);
				}
				if(datosCli.get(8)!=null){
					String aux = datosCli.get(8);
					if(aux.equals("H")) {
						consumoHabilitado = "Si";
						consumoHabilitadoBool = true;
					}
					if(aux.equals("D")) {
						consumoHabilitado = "No";
					}
				}
				if(datosCli.get(9)!=null){
					lineaCredito = datosCli.get(9);
				}
				if(datosCli.get(10)!=null){
					saldo = datosCli.get(10);
				}
				if(datosCli.get(11)!=null){
					disponible = datosCli.get(11);
				}
				if(datosCli.get(12)!=null){
					diaPago = new Integer(datosCli.get(12));
				}
			}
		}
		
		if(nombreTitular != null){
			panelCliente = true;
			panelComercio = true;
			if(nombreAdicional != null){
				nombreTarjeta = nombreAdicional;
			}
			else{
				nombreTarjeta = nombreTitular;
			}
			if(!consumoHabilitadoBool){
				panelComercio = false;
				error = true;
				msjError = "El cliente tiene consumo deshabilitado";
			}
			Date hoy = new Date();
			int dia = Integer.parseInt(vtoPlastico.substring(0, 2));
			int mes = Integer.parseInt(vtoPlastico.substring(3, 5));
			int year = Integer.parseInt(vtoPlastico.substring(6, 8));
			Date vto = new Date(year+100,mes-1,dia);
			if(vto.before(hoy)){
				plasticoVencido=true;
				panelComercio = false;
				error = true;
				msjError = "Tarjeta Vencida";
			}
		}
		
		return "autorizacionTelefonica";
	}
	
	
	public String recargarCliente(){
		
		if(sesionVencida){
			limpiarTab(selectedTab);
			return "autorizacionTelefonica";
		}
		if(!transaccionadorOk){
			limpiarTab(selectedTab);
			return "autorizacionTelefonica";
		}
		
		List<String> datosCli = transaccionesService.getTransaccionService().getDatosCliente(nroTarjeta, cvv);
		if(datosCli!=null){
			if(datosCli.get(0).equals("Error")){
				limpiarTab(selectedTab);
				error = true;
				msjError = datosCli.get(1);
			}
			else{ 
				error = false;
				
				if(datosCli.get(5)!=null){
					estadoPlastico = datosCli.get(5);
				}
				if(datosCli.get(6)!=null){
					situacionComercial = datosCli.get(6);
				}
				if(datosCli.get(7)!=null){
					situacionCobranza = datosCli.get(7);
				}
				if(datosCli.get(8)!=null){
					String aux = datosCli.get(8);
					if(aux.equals("H")) {
						consumoHabilitado = "Si";
						consumoHabilitadoBool = true;
					}
					if(aux.equals("D")) {
						consumoHabilitado = "No";
					}
				}
				if(datosCli.get(9)!=null){
					lineaCredito = datosCli.get(9);
				}
				if(datosCli.get(10)!=null){
					saldo = datosCli.get(10);
				}
				if(datosCli.get(11)!=null){
					disponible = datosCli.get(11);
				}
			}
		}
		
		if(nombreTitular != null){
			panelCliente = true;
			panelComercio = true;
			if(nombreAdicional != null){
				nombreTarjeta = nombreAdicional;
			}
			else{
				nombreTarjeta = nombreTitular;
			}
			if(!consumoHabilitadoBool){
				panelComercio = false;
				error = true;
				msjError = "El cliente tiene consumo deshabilitado";
			}
			Date hoy = new Date();
			int dia = Integer.parseInt(vtoPlastico.substring(0, 2));
			int mes = Integer.parseInt(vtoPlastico.substring(3, 5));
			int year = Integer.parseInt(vtoPlastico.substring(6, 8));
			Date vto = new Date(year+100,mes-1,dia);
			if(vto.before(hoy)){
				plasticoVencido=true;
				panelComercio = false;
				error = true;
				msjError = "Tarjeta Vencida";
			}
		}
		
		return "autorizacionTelefonica";
	}
	
	
	public String buscarComercio(){
		String codComercioAux = codComercio;
		limpiarComercio();
		codComercio = codComercioAux;
		
		if(sesionVencida){
			limpiarTab(selectedTab);
			return "autorizacionTelefonica";
		}
		if(!panelCliente){
			limpiarTab(selectedTab);
			return "autorizacionTelefonica";
		}
		
		List<String> listDatosComercio = transaccionesService.getTransaccionService().getDatosComercio(codComercio);
		if(listDatosComercio!=null){
			if(listDatosComercio.get(0).equals("Error")){
				limpiarComercio();
				error = true;
				msjError = listDatosComercio.get(1);
			}
			else{
				if(listDatosComercio.get(0)!=null){		
					empresa = listDatosComercio.get(0);
				}
				if(listDatosComercio.get(1)!=null){
					sucursal = listDatosComercio.get(1);
				}
				if(listDatosComercio.get(2)!=null){
					localidad = listDatosComercio.get(2);
				}
				if(listDatosComercio.get(3)!=null){
					direccion = listDatosComercio.get(3);
				}
				if(listDatosComercio.get(4)!=null){
					telefono = listDatosComercio.get(4);
				}
				if(listDatosComercio.get(5)!=null){
					planCuotas = listDatosComercio.get(5);
				}
				if(listDatosComercio.get(6)!=null){
					cuotasDisponibles = listDatosComercio.get(6);
				}
				if(listDatosComercio.get(7)!=null){
					idListaPrecio = new Long(listDatosComercio.get(7));
				}
				
				String estadoComercio = "";
				if(listDatosComercio.get(8)!=null){
					estadoComercio = listDatosComercio.get(8);
				}
				
				if(estadoComercio.equals("H")){
					error = false;
				}
				else{
					error = true;
					msjError = "Comercio Deshabilitado";
				}
			}
		}
		
		if(empresa!=null){
			datosComercio = true;
		}
		if(!error){
			panelOperacion = true;
		}
		
		return "autorizacionTelefonica";
	}
	
	public String busquedaComercio(){
		String parametroAux = this.parametroBusqueda;
		limpiarComercio();
		parametroBusqueda = parametroAux;
		listaComercios = transaccionesService.getTransaccionService().buscarComercios(parametroBusqueda);
		modalBusquedaComercio = true;
		return "autorizacionTelefonica";
	}
	
	public String simularCuotas(){
		controlarSesion();
		String montoAux = monto.replace(",",".");
		double montoTotal = new Double(montoAux);

		try{
//			List<Cuota> cuotasAux = transaccionesService.getCalculoCuotaServicesTarget().calculoCuotaConsumo(montoTotal, idListaPrecio, diaPago);
			List<Cuota> cuotasAux = transaccionesService.getCalculoCuotaServicesTarget().calculoCuotaSimulador(montoTotal, idListaPrecio, diaPago);
			
			int cantMaxCuotas = 0;
			for(int i=0; i<cuotasAux.size(); i++){
				if(cuotasAux.get(i).getCantidadCuota() > cantMaxCuotas){
					cantMaxCuotas = cuotasAux.get(i).getCantidadCuota();
				}
			}
			
			Cuota[] cuotasArray = new Cuota[cantMaxCuotas];
			for(int i=0; i<cantMaxCuotas; i++){
				cuotasArray[i] = null;
			}
			
			for(int i=0; i<cuotasAux.size(); i++){
				cuotasArray[cuotasAux.get(i).getCantidadCuota()-1] = cuotasAux.get(i);
			}
			
			Locale locale = new Locale("es", "AR");      
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
			
			cuotaSimulada = new ArrayList<CuotaSimulada>(); 
			
			for(int i=0; i<cantMaxCuotas; i++){
				if(cuotasArray[i]!=null){
					double montoCuota = cuotasArray[i].getCapital() + cuotasArray[i].getInteres();
					
					CuotaSimulada cuotaSim  = new CuotaSimulada();
					cuotaSim.setCantidadCuotas(cuotasArray[i].getCantidadCuota());
					cuotaSim.setMonto(currencyFormatter.format(montoCuota));
					
					cuotaSimulada.add(cuotaSim);
				}
			}
			
			modalSimularCuotas = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return "autorizacionTelefonica";
	}
	
	
	
	public String aceptarConsumo(){
		controlarSesion();
		if(sesionVencida){
			limpiarTab(selectedTab);
			return "autorizacionTelefonica";
		}
		if(!(panelCliente||datosComercio)){
			limpiarTab(selectedTab);
			return "autorizacionTelefonica";
		}
		
		monto = String.format("%.2f", new Double(monto));
		monto = monto.replace(".","");
		monto = monto.replace(",","");
		
		if(cuotaInvalida()){
			error = true;
			msjError = "Cantidad de cuotas incorrecta.";
			Double aux = new Double(monto)/100;
			monto = String.format("%.2f", aux);
			monto = monto.replace(",",".");
			return null;
		}
		if(importeInvalido()){
			error = true;
			msjError = "El importe supera el disponible.";
			Double aux = new Double(monto)/100;
			monto = String.format("%.2f", aux);
			monto = monto.replace(",",".");
			return null;
		}
		
		
		
		try{
			String token = generarToken();
			log.info("Token: " + token);
			HashMap<String, String> respuesta = transaccionesService.getTransaccionService().enviarTokenAutTelefonica(token, ipTransaccionador, puerto);
			
			modalAprobada = true;
			codAutorizacion = respuesta.get("codigoAutorizacion");
			mensaje = respuesta.get("mensaje");
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
			msjError = "Error al procesar la transacción.";
			Double aux = new Double(monto)/100;
			monto = String.format("%.2f", aux);
			return "autorizacionTelefonica";
		}
		
		Double aux = new Double(monto)/100;
		monto = String.format("%.2f", aux);

		return "autorizacionTelefonica";
	}
	
	
	
	private String generarToken(){

		Long operador = Session.getOperador().getCodigo();
		
		String token = "";

		SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
		
		String comercio = "0000000000000"; comercio = completarCampo(comercio,codComercio);
		String carRes = "00000000000"; 
		String cupon = "0000"; cupon = completarCampo(cupon,nroCupon);
		String tipoReg = "861"; 
		String tarjeta = "00000000000000000000";
		String tarjAux = nroTarjeta + tarjeta;
		tarjAux = tarjAux.substring(0,tarjeta.length());
		tarjeta = tarjAux;
		String fecha = format.format(new Date());
		String cuotas = "00"; cuotas = completarCampo(cuotas,this.cuotas);
		String importe = "000000000000000"; importe = completarCampo(importe,monto);
		String importeDesc = "000000000000000";
		String codAut = "00000000"; codAut = completarCampo(codAut,operador.toString());
		String codMoneda = "032";
		String hora = "000000";
		String pin = "0";
		String track2 = "0000000000000000000000000000000000000000";
		String cuponOrigDev = "0000";
		String fechaOrigDev = "0000";
		String indCinting = "0";
		String anulacion = "0";
		String codRespuesta = "000";
		String planCuotas = "00";
		String acctTyp = "0";
		String acctSource = "0";
		String termTyp = "0";
		String privatee = "0000";
		
		token += 
				comercio +
				carRes +
				cupon +
				tipoReg +
				tarjeta +
				fecha +
				cuotas +
				importe +
				importeDesc +
				codAut +
				codMoneda +
				hora +
				pin +
				track2 +
				cuponOrigDev +
				fechaOrigDev +
				indCinting +
				anulacion +
				codRespuesta +
				planCuotas +
				acctTyp +
				acctSource +
				termTyp +
				privatee; 			
			
		token = rellenarToken(token);
		token = "111" + "0200" + token; //Version 111, Operacion 0200(Compra)
		
		Double aux = new Double(monto)/100;
		monto = String.format("%.2f", aux);
		monto = monto.replace(",",".");
		
		return token;
	}
	
	private String completarCampo(String campo,String valor){
		String aux = campo + valor;
		aux = aux.substring(valor.length());
		return aux;
	}
	
	private String rellenarToken(String token) {
		int i = 200 - token.length();
		String relleno  = "00000000000400C00000000000000000";
	    token += relleno.substring(relleno.length()-i);
	    return token;
	}
	
	
	
	private boolean cuotaInvalida(){
        String cuotasAux = cuotasDisponibles.substring(1,cuotasDisponibles.length()-1);
        String[] cuotasArry = cuotasAux.split("-");
        for(int i=0; i<cuotasArry.length; i++){
        	if(cuotasArry[i].equals(cuotas)){
        		return false;
        	}
        }
        return true;
	}
	
	private boolean importeInvalido(){
        String disponibleAux = disponible;
        disponibleAux = disponibleAux.replace("$", "");
        disponibleAux = disponibleAux.replace(".", "");
        disponibleAux = disponibleAux.replace(",", "");
        
        Double disponibleD = new Double(disponibleAux);
        Double montoD = new Double(monto);
        
        if(montoD>disponibleD){
        	return true;
        }
        
        return false;
	}
	
	
	
	public String buscarComercioAnulacion(){
		String codComercioAux = codComercio;
		limpiarComercio();
		codComercio = codComercioAux;
		selectedTab = 2;
		
		if(sesionVencida){
			limpiarTab(selectedTab);
			return "autorizacionTelefonica";
		}
		
		List<String> listDatosComercio = transaccionesService.getTransaccionService().getDatosComercio(codComercio);
		if(listDatosComercio!=null){
			if(listDatosComercio.get(0).equals("Error")){
				limpiarComercio();
				error = true;
				msjError = listDatosComercio.get(1);
			}
			else{
				if(listDatosComercio.get(0)!=null){		
					empresa = listDatosComercio.get(0);
				}
				if(listDatosComercio.get(1)!=null){
					sucursal = listDatosComercio.get(1);
				}
				if(listDatosComercio.get(2)!=null){
					localidad = listDatosComercio.get(2);
				}
				if(listDatosComercio.get(3)!=null){
					direccion = listDatosComercio.get(3);
				}
				if(listDatosComercio.get(4)!=null){
					telefono = listDatosComercio.get(4);
				}
				if(listDatosComercio.get(5)!=null){
					planCuotas = listDatosComercio.get(5);
				}
				if(listDatosComercio.get(6)!=null){
					cuotasDisponibles = listDatosComercio.get(6);
				}
				if(listDatosComercio.get(7)!=null){
					idListaPrecio = new Long(listDatosComercio.get(7));
				}
				
				String estadoComercio = "";
				if(listDatosComercio.get(8)!=null){
					estadoComercio = listDatosComercio.get(8);
				}
				
				if(estadoComercio.equals("H")){
					error = false;
				}
				else{
					error = true;
					msjError = "Comercio Deshabilitado";
				}
			}
		}
		
		if(empresa!=null){
			datosComercio = true;
		}
		if(!error){
			panelOperacion = true;
		}
		
		if(datosComercio){
			movimientosPendientes = transaccionesService.getTransaccionService().getMovimientosPendientesComercio(codComercio);
			if (movimientosPendientes.size()==0){
				error = true;
				msjError = "El comercio no tiene autorizaciones pendientes.";
			}
			else if(movimientosPendientes.size()==1){
				idTransaccionSeleccionada = movimientosPendientes.get(0).getIdTransaccion();
			}
			else idTransaccionSeleccionada = 0L;
		}
		
		return "autorizacionTelefonica";
	}
	
	
	
	public String anularConsumo(){
		controlarSesion();
		if(sesionVencida){
			limpiarTab(selectedTab);
			return "autorizacionTelefonica";
		}
		
		if(idTransaccionSeleccionada != 0L){
			try{
				Long operador = Session.getOperador().getCodigo();
				String token = transaccionesService.getTransaccionService().generarTokenAnulacion(idTransaccionSeleccionada,operador.toString());
				log.info("Token: " + token);
				HashMap<String, String> respuesta = transaccionesService.getTransaccionService().enviarTokenAutTelefonica(token, ipTransaccionador, puerto);
				
				modalAprobada = true;
				codAutorizacion = respuesta.get("codigoAutorizacion");
				mensaje = respuesta.get("mensaje");

				movimientosPendientes = transaccionesService.getTransaccionService().getMovimientosPendientesComercio(codComercio);
				if(movimientosPendientes.size()==1){
					idTransaccionSeleccionada = movimientosPendientes.get(0).getIdTransaccion();
				}
				else idTransaccionSeleccionada = 0L;
				
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
				msjError = "Error al procesar la transacción.";
				return "autorizacionTelefonica";
			}
		}
		return "autorizacionTelefonica";
	}

	
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCodComercio() {
		return codComercio;
	}

	public void setCodComercio(String codComercio) {
		this.codComercio = codComercio;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getCuotas() {
		return cuotas;
	}

	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}

	public String getNroCupon() {
		return nroCupon;
	}

	public void setNroCupon(String nroCupon) {
		this.nroCupon = nroCupon;
	}
	
	public List<CuotaSimulada> getCuotaSimulada() {
		return cuotaSimulada;
	}

	public void setCuotaSimulada(List<CuotaSimulada> cuotaSimulada) {
		this.cuotaSimulada = cuotaSimulada;
	}

	public String getNroCuenta(){
		return nroCuenta;
	}
	
	public void setNroCuenta(String nroCuenta){
		this.nroCuenta = nroCuenta;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getNombreAdicional() {
		return nombreAdicional;
	}

	public void setNombreAdicional(String nombreAdicional) {
		this.nombreAdicional = nombreAdicional;
	}
	
	public String getNombreTarjeta() {
		return nombreTarjeta;
	}

	public void setNombreTarjeta(String nombreTarjeta) {
		this.nombreTarjeta = nombreTarjeta;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getVtoPlastico() {
		return vtoPlastico;
	}

	public void setVtoPlastico(String vtoPlastico) {
		this.vtoPlastico = vtoPlastico;
	}

	public String getEstadoPlastico() {
		return estadoPlastico;
	}

	public void setEstadoPlastico(String estadoPlastico) {
		this.estadoPlastico = estadoPlastico;
	}

	public String getSituacionComercial() {
		return situacionComercial;
	}

	public void setSituacionComercial(String situacionComercial) {
		this.situacionComercial = situacionComercial;
	}

	public String getSituacionCobranza() {
		return situacionCobranza;
	}

	public void setSituacionCobranza(String situacionCobranza) {
		this.situacionCobranza = situacionCobranza;
	}

	public String getConsumoHabilitado() {
		return consumoHabilitado;
	}

	public void setConsumoHabilitado(String consumoHabilitado) {
		this.consumoHabilitado = consumoHabilitado;
	}

	public String getLineaCredito() {
		return lineaCredito;
	}

	public void setLineaCredito(String lineaCredito) {
		this.lineaCredito = lineaCredito;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getDisponible() {
		return disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
	
	public boolean isConsumoHabilitadoBool() {
		return consumoHabilitadoBool;
	}

	public void setConsumoHabilitadoBool(boolean consumoHabilitadoBool) {
		this.consumoHabilitadoBool = consumoHabilitadoBool;
	}

	public boolean isPlasticoVencido() {
		return plasticoVencido;
	}

	public void setPlasticoVencido(boolean plasticoVencido) {
		this.plasticoVencido = plasticoVencido;
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPlanCuotas() {
		return planCuotas;
	}

	public void setPlanCuotas(String planCuotas) {
		this.planCuotas = planCuotas;
	}
	
	public String getCuotasDisponibles() {
		return cuotasDisponibles;
	}

	public void setCuotasDisponibles(String cuotasDisponibles) {
		this.cuotasDisponibles = cuotasDisponibles;
	}
	
	public String getParametroBusqueda() {
		return parametroBusqueda;
	}

	public void setParametroBusqueda(String parametroBusqueda) {
		this.parametroBusqueda = parametroBusqueda;
	}

	public List<Comercio> getListaComercios() {
		return listaComercios;
	}


	public void setListaComercios(List<Comercio> listaComercios) {
		this.listaComercios = listaComercios;
	}


	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public boolean isPanelCliente() {
		return panelCliente;
	}

	public void setPanelCliente(boolean panelCliente) {
		this.panelCliente = panelCliente;
	}

	public boolean isPanelComercio() {
		return panelComercio;
	}

	public void setPanelComercio(boolean panelComercio) {
		this.panelComercio = panelComercio;
	}

	public boolean isDatosComercio() {
		return datosComercio;
	}

	public void setDatosComercio(boolean datosComercio) {
		this.datosComercio = datosComercio;
	}


	public boolean isPanelOperacion() {
		return panelOperacion;
	}

	public void setPanelOperacion(boolean panelOperacion) {
		this.panelOperacion = panelOperacion;
	}

	public int getSelectedTab(){
		return selectedTab;
	}
	
	public void setSelectedTab(int selectedTab){
		this.selectedTab = selectedTab;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMsjError() {
		return msjError;
	}

	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}
	
	public boolean isModalAprobada() {
		return modalAprobada;
	}

	public void setModalAprobada(boolean modalAprobada) {
		this.modalAprobada = modalAprobada;
	}

	public boolean isSesionVencida() {
		return sesionVencida;
	}

	public void setSesionVencida(boolean sesionVencida) {
		this.sesionVencida = sesionVencida;
	}

	public String getCodAutorizacion() {
		return codAutorizacion;
	}

	public void setCodAutorizacion(String codAutorizacion) {
		this.codAutorizacion = codAutorizacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isModalBusquedaComercio() {
		return modalBusquedaComercio;
	}

	public void setModalBusquedaComercio(boolean modalBusquedaComercio) {
		this.modalBusquedaComercio = modalBusquedaComercio;
	}

	public boolean isModalSimularCuotas() {
		return modalSimularCuotas;
	}

	public void setModalSimularCuotas(boolean modalSimularCuotas) {
		this.modalSimularCuotas = modalSimularCuotas;
	}

	public boolean isTransaccionadorOk() {
		return transaccionadorOk;
	}

	public void setTransaccionadorOk(boolean transaccionadorOk) {
		this.transaccionadorOk = transaccionadorOk;
	}

	public List<MovimientoAnulacion> getMovimientosPendientes() {
		return movimientosPendientes;
	}

	public void setMovimientosPedientes(List<MovimientoAnulacion> movimientosPendientes) {
		this.movimientosPendientes = movimientosPendientes;
	}

	public Long getIdTransaccionSeleccionada() {
		return idTransaccionSeleccionada;
	}

	public void setIdTransaccionSeleccionada(Long idTransaccionSeleccionada) {
		this.idTransaccionSeleccionada = idTransaccionSeleccionada;
	}
	
}

