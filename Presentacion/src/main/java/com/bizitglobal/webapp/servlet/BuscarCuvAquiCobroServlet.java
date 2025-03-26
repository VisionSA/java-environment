package com.bizitglobal.webapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
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

import com.bizitglobal.tarjetafiel.transacciones.service.GestorLiquidacionClienteService;




public class BuscarCuvAquiCobroServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(BuscarCuvAquiCobroServlet.class);

	public BuscarCuvAquiCobroServlet() {
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
			  			  
			  GestorLiquidacionClienteService gestorLiquidacionClienteService = (GestorLiquidacionClienteService) appContext
						.getBean("gestorLiquidacionClienteService");
			  
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
			  
			  
			  Integer idCliente = new Integer(obj.get("idCliente").toString());
			  String canal = obj.get("canal").toString();
			  
			  String codigoBarraTres = null;
			  
			// TRABAJAMOS CON EL CODIGOBARRATRES CON EL CVU
				codigoBarraTres = gestorLiquidacionClienteService.buscarCVUdelCliente(idCliente.intValue());
								
				
				if (codigoBarraTres == null ) {
					codigoBarraTres = gestorLiquidacionClienteService.buscarNuevoCVUdelCliente(idCliente.intValue());
					
					if (codigoBarraTres == null ) {
						log.info(" no existe cvu corto para asignar " );
						
						resp.getWriter().println("{}");	
						resp.setStatus(400);
						
						
					} else {
						log.info(" codigoBarraTres nuevo " + codigoBarraTres);	
						gestorLiquidacionClienteService.actualizarClienteEnCVU(idCliente.intValue(),codigoBarraTres,canal);
						resp.getWriter().println("{" + "\"cvu\":\"" + codigoBarraTres + "\"" +"}");	
						resp.setStatus(200);
					}
					
					
					
				} else {
					
					//gestorLiquidacionClienteService.UpdateClienteEnCVU(idCliente.intValue(),codigoBarraTres);
					
					log.info(" codigoBarraTres encontrado " + codigoBarraTres);
					resp.getWriter().println("{" + "\"cvu\":\"" + codigoBarraTres + "\"" +"}");	
					resp.setStatus(200);
					
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

