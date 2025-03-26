package com.bizitglobal.webapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.transacciones.service.ArchivoCuponesService;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.ArchivoCuponesServiceImpl;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CalculoCuotaServicesImpl;



public class GenerarTxtLiqComercioServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(GenerarTxtLiqComercioServlet.class);

	public GenerarTxtLiqComercioServlet() {
		super();
	}

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	  try{
		  ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		  ArchivoCuponesServiceImpl archivoCuponesService= (ArchivoCuponesServiceImpl) appContext.getBean("archivoCuponesServiceTarget");
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
		  long id = Long.parseLong(obj.get("id").toString());		  
		
		  String archivo =  archivoCuponesService.generarArchivo(id);
		  resp.getWriter().println("{\"arcvhivo\":\""+archivo+"\"}");
		 
		  
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