package com.bizitglobal.webapp.servlet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.transacciones.service.impl.CalculoCuotaServicesImpl;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.PagosClienteServiceImpl;
import com.visionis.transaccionador.negocio.Cuota;


import org.apache.log4j.Logger;
import org.json.JSONObject;


public class TransferenciasInmediatasCuotaServlet extends HttpServlet implements javax.servlet.Servlet {
	
	private static Logger logger = Logger.getLogger(TransferenciasInmediatasCuotaServlet.class);
	private static final long serialVersionUID = -9166416935188731214L;

	private static final String PAGOS_CLIENTE_SERVICE_NAME = "pagosClienteServiceTarget";



	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public TransferenciasInmediatasCuotaServlet() {
		super();
	}


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//PAGO A TRAVES DEL BOTON DE PAGO - PEI LINK
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			logger.info("PROCESANDO Transferencia inmediate Cuotas ");

			ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			PagosClienteServiceImpl pagoClienteService = (PagosClienteServiceImpl) appContext.getBean(PAGOS_CLIENTE_SERVICE_NAME);
			CalculoCuotaServicesImpl calculocuotaservice= (CalculoCuotaServicesImpl) appContext.getBean("calculoCuotaServicesTarget");
			
			StringBuilder jb = new StringBuilder();
			String line = null;
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null){
				jb.append(line);
			}
			logger.info("jb.toString() "+ jb.toString());
			
			JSONObject obj = new JSONObject(jb.toString());
			String cbuOrigen = (obj.get("cbuOrigen").toString());
			String cbuDestino = (obj.get("cbuDestino").toString());
			Double importe = new Double(obj.get("importe").toString())/100;
			
			Long cliente = new Long(obj.get("cliente").toString());
			Long clienteAdicional = new Long(obj.get("clienteAdicional").toString());
			String razonSocial = (obj.get("razonSocial").toString());
			
			String numeroReferenciaBancaria = (obj.get("numeroReferenciaBancaria").toString());
			String numeroConciliacionBancaria = (obj.get("numeroConciliacionBancaria").toString());
			String fechaOperacion = (obj.get("fechaOperacion").toString());
			String codigoComercio = (obj.get("codigoComercio").toString());			
			String alias = (obj.get("alias").toString());
			int diaCierre= Integer.parseInt(obj.get("diaCierre").toString()); 
			  long idConcepto= Long.parseLong(obj.get("idConcepto").toString());
			  long listaPrecioCompras= Long.parseLong(obj.get("listaPrecioCompras").toString());
			  int cantidadCuotas= Integer.parseInt(obj.get("cantidadCuotas").toString());
			
//			List<Cuota> cuotas = calculocuotaservice.calcularCuotas(importe,cantidadCuotas, cliente, diaCierre, idConcepto);
			List<Cuota> cuotas = calculocuotaservice.calcularCuotasTrans(importe,cantidadCuotas, cliente, diaCierre, idConcepto,listaPrecioCompras);
			
//			String result = pagoClienteService.registrarTransferenciaInmed(cbuOrigen,cbuDestino,importe, cliente,clienteAdicional,
//					razonSocial,numeroReferenciaBancaria
//					,numeroConciliacionBancaria,fechaOperacion,codigoComercio,alias);
			
			String result = pagoClienteService.registrarTransfInmedCuota(cbuOrigen,cbuDestino,importe, cliente,clienteAdicional,
					razonSocial,numeroReferenciaBancaria
					,numeroConciliacionBancaria,fechaOperacion,codigoComercio,alias,idConcepto,cuotas,listaPrecioCompras);
			
			if(result.equals("OK")){
				
/////////////////////////////////////////////////////
/// CODIGO PARA ENVIO DE NOTIFICACION
/////////////////////////////////////////////////
Locale locale = new Locale("es", "AR");      
NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
String msj = "" +
"{" +							
"\"client\" : " +
"{" +
"\"id\":"+ new Long(obj.get("cliente").toString()) + " " +								
"}," +
					
"\"commerce\" : " +
"{" +
"\"id\":"+  new Long(obj.get("codigoComercio").toString()) + "," +
"\"name\":\"" + razonSocial.trim() + "\"" +
"}," +
					
"\"purchase\" : " +
"{" +
"\"ammount\":\""+ currencyFormatter.format(new Double(obj.get("importe").toString())/100) + "\"," +
"\"quotas\":"+ cantidadCuotas + " " +	
"}" +
" }";


logger.info("msj : " + msj);


// 1. URL
//URL url = new URL("http://tarjetafiel.com/FielWebCliente/appCliente/PlanificadorNotificacionWebServlet");

URL url = new URL("http://192.168.79:3000/send/compra");

// 2. Open connection
HttpURLConnection conn = (HttpURLConnection) url.openConnection();

// 3. Specify POST method
conn.setRequestMethod("POST");

// 4. Set the headers
conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//conn.setRequestProperty("Authorization", "key=" + apiKey);

conn.setDoOutput(true);

// 5. Add JSON data into POST request body
DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
wr.writeBytes(msj);

// 5.1 Send the request
wr.flush();

// 5.2 close
wr.close();

// 6. Get the response
int responseCode = conn.getResponseCode();
logger.info("\nSending 'POST' request to URL send compra : " + url);
logger.info("Response Code : " + responseCode);

if (responseCode == 200) {

} else {

}
/////////////////////////////////////////////////////
/// HASTA AQUI CODIGO PARA ENVIO DE NOTIFICACION
/////////////////////////////////////////////////////
				
				
				response.setStatus(200);
				response.getWriter().println("ACREDITADO");
			}
			else{
				response.setStatus(400);
				response.getWriter().println("ERROR");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			response.getWriter().println("ERROR");
		} 
		
	}
	
	
	public void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try{
			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
			resp.setHeader("Access-Control-Allow-Methods", "OPTIONS");
			resp.setContentType("application/json");
		} 
		catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().println(emptyResponse());
		}
	}
		  
		  
	private String emptyResponse(){
		return "[]";
	}
	  

}

