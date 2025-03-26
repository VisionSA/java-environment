package com.bizitglobal.webapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.bizitglobal.tarjetafiel.transacciones.service.GestionarClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.ArchivoCuponesServiceImpl;



public class SirtacReporteServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(GenerarTxtLiqComercioServlet.class);

	public SirtacReporteServlet() {
		super();
	}

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	  try{
		  
		  ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		  GestionarClienteService sirtacReporte = (GestionarClienteService) appContext.getBean("gestionarClienteService");
		  
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
		  
          String fechaDesde = obj.get("fechaDesde").toString();
          
          String fechaHasta = obj.get("fechaHasta").toString();
          
          String reporte = sirtacReporte.generarArchivo(fechaDesde, fechaHasta);
          
          OutputStream outputStream = (OutputStream)resp.getOutputStream();
          
          reporte = reporte.replace("#*", "\n");
          
          String reporteFinal = reporte.replace(",",".");
          outputStream.write(reporteFinal.getBytes());
          outputStream.flush();
          outputStream.close();
		  	    
		  //resp.getWriter().println("{\"reporte\":\""+reporte+"\"}");
		 
		  
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