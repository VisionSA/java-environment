package com.bizitglobal.webapp.servlet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import com.bizitglobal.webapp.service.impl.ArchivoUnionPDFServiceImpl;




public class ActivarPlasticosNewServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(ActivarPlasticosNewServlet.class);

	public ActivarPlasticosNewServlet() {
		super();
	}

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	 
  }
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	  	try{
		  
			  ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			  			  
			  ArchivoUnionPDFServiceImpl archivoUnionPDFService = (ArchivoUnionPDFServiceImpl) appContext
						.getBean("archivoUnionPDFServiceTarget");
			  
			  
			  resp.setHeader("Access-Control-Allow-Origin", "*");
			  resp.setHeader("Access-Control-Allow-Methods", "POST");
			  resp.setContentType("application/json");
			  
			  StringBuilder jb = new StringBuilder();
			  String line = null;
			  BufferedReader reader = req.getReader();
			  while ((line = reader.readLine()) != null){
			      jb.append(line);
			  }
			  JSONObject obj = new JSONObject(jb.toString());			  
			  
//			  Integer idCliente = new Integer(obj.get("idCliente").toString());
//			  String canal = obj.get("canal").toString();
			  
			  String codCuenta = obj.get("codCuenta").toString();
			  String codLote = obj.get("codLote").toString();
			  String codOperacion = obj.get("codOperacion").toString();
			  String documento = obj.get("documento").toString();
			  String apellido = obj.get("apellido").toString();
			  String nombre = obj.get("nombre").toString();
			  Long operador = new Long(obj.get("operador").toString());
			  String parentesco = obj.get("parentesco").toString();
			  String fechaEntrega = obj.get("fechaEntrega").toString();
			  String lineaCredito = obj.get("lineaCredito").toString();
			  String observacion = obj.get("observacion").toString();
			  String observacionTexto = obj.get("observacionTexto").toString();
			  
			  String nombreCompleto = obj.get("nombreCompleto").toString();
			  String idIndividuo = obj.get("idIndividuo").toString();
			  String ingreso = "";
			  String empleador = "";
			  String msj1 = "";
		
			  
			  Boolean activoPlastico = archivoUnionPDFService.activarPlasticoTramite( codCuenta,  codLote,  codOperacion,  documento,  
					  apellido,
						 nombre,  operador,  parentesco,  fechaEntrega,
						 lineaCredito,  observacion,  observacionTexto);
			  
			
						log.info("activoPlastico " + activoPlastico);	
						String resultado = "";
						
						if (activoPlastico) {
							
							if (lineaCredito.equals("SI")) {
								try{
								  
								  String msj = "" +
											"{" +
												"\"documentos\" : [" + msj1 + "]" +
												",\"idCliente\" : \"" + codCuenta + "\"," +
												"\"idIndividuo\" : \"" + idIndividuo+ "\"," +
												"\"nombre\" : \"" + nombreCompleto+ "\"," +
												"\"dni\" : \"" + documento+ "\"," +
												"\"ingresos\" : \"" + ingreso + "\"," +
												"\"empleador\" : \"" + empleador + "\"," +
												"\"observaciones\" : \"" + observacionTexto + "\"" +
											"}";

								  log.info("msj " + msj);
								  
								  // 1. URL
								  URL url = new URL("http://192.168.0.7:8080/Presentacion/RevisionLineaCreditoServlet");
								  
//								  URL url = new URL("http://192.168.0.68:8080/Presentacion/RevisionLineaCreditoServlet");
								  
								  // 2. Open connection
								  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
								  
								  // 3. Specify POST method
								  conn.setRequestMethod("POST");
								  
								  // 4. Set the headers
								  conn.setRequestProperty("Content-Type", "application/json");
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
								  log.info("\nSending 'POST' request to URL : " + url);
								  log.info("Response Code : " + responseCode);
						
								  if (responseCode == 200) {
									  BufferedReader in = new BufferedReader(
											  new InputStreamReader(conn.getInputStream()));
									  String inputLine;
									  StringBuffer response = new StringBuffer();
						
									  while ((inputLine = in.readLine()) != null) {
										  response.append(inputLine);
										  }
									  in.close();
						
									  // 7. Print result
									  log.info(response.toString());						
								  }
								  else{
									//  return  "Hubo un error al acreditar el pago";
									  
									  log.info("Linea de Credito no generada cliente  " + codCuenta);	
								  }
							  }
							  catch (Exception e) {
								  e.printStackTrace();
								 // return "Error de procesamiento";
							  }
								
							}
							
							resp.setStatus(200);
							resultado = "OK";
							resp.getWriter().println("{" + "\"resultado\":\"" + resultado + "\"" +"}");
							
						} else {							
							resp.setStatus(400);
							resultado = "ERROR";
							resp.getWriter().println("{" + "\"resultado\":\"" + resultado + "\"" +"}");							
						}
				   	  
		  } 
		  catch (Exception e) {
			  e.printStackTrace();
			  resp.getWriter().println("{}");
			  resp.setStatus(500);
		  }
		  
	  
	 
  }
  
  
  @Override
  public void doOptions(HttpServletRequest req, HttpServletResponse resp)
	  throws IOException {
	  try{
		  resp.setHeader("Access-Control-Allow-Origin", "*");
		  resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		  resp.setHeader("Access-Control-Allow-Methods", "OPTIONS");
		  resp.setContentType("application/json");
	  } 
	  catch (Exception e) {
		  resp.getWriter().println("{}");
		  resp.setStatus(401);
	  }
  }
  

}




