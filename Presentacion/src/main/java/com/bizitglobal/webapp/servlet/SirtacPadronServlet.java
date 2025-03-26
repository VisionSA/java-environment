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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.transacciones.negocio.SirtacAliCuota;
import com.bizitglobal.tarjetafiel.transacciones.service.GestionarClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.ArchivoCuponesServiceImpl;



public class SirtacPadronServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(GenerarTxtLiqComercioServlet.class);

	public SirtacPadronServlet() {
		super();
	}

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	  try{
		  
		  ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		  GestionarClienteService sirtacPadron = (GestionarClienteService) appContext.getBean("gestionarClienteService");
		  
		  resp.setHeader("Access-Control-Allow-Origin", "*");
		  resp.setHeader("Access-Control-Allow-Methods", "GET");
		  resp.setContentType("application/json");
	  
		    
		  
		  String respuesta =  sirtacPadron.subirPadron();
		  
		  if(respuesta.equals("OK")){
			  
			  resp.getWriter().println("{" + "\"result\":\"" + respuesta + "\"" +"}");
			  
		  }
		  else{
			  resp.getWriter().println("{}");
			  resp.setStatus(400);
		  }
		 
		
	  } 

	  catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().println("{}");
			resp.setStatus(500);
	  }
	  
	 
  }
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
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