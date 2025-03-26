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
import com.bizitglobal.tarjetafiel.transacciones.negocio.SolicitudWeb;
import com.bizitglobal.tarjetafiel.transacciones.service.GestionarClienteService;




public class SolicitudesWebServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(GenerarTxtLiqComercioServlet.class);

	public SolicitudesWebServlet() {
		super();
	}

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	  try{
		  
		  ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		  GestionarClienteService SolicitudesWeb = (GestionarClienteService) appContext.getBean("gestionarClienteService");
		  
		  resp.setHeader("Access-Control-Allow-Origin", "*");
		  resp.setHeader("Access-Control-Allow-Methods", "GET");
		  resp.setContentType("application/json");
	  
		  
		  
		  List<SolicitudWeb> solicitudes =  SolicitudesWeb.getSolicitudes();
		  
		  if(solicitudes!=null){			  
			  resp.getWriter().println("[");
			  Iterator<SolicitudWeb> iter = solicitudes.iterator();
			  while(iter.hasNext()){
				  SolicitudWeb solicitud = iter.next();
				  resp.getWriter().println(solicitud.json());
				  if(iter.hasNext()){
					  resp.getWriter().println(",");
				  }
			  }
			  resp.getWriter().println("]");
		  }
		  else{
			  resp.getWriter().println("[]");
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
	  
	  	try{
		  
			  ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			  GestionarClienteService solicitudesWeb = (GestionarClienteService) appContext.getBean("gestionarClienteService");
			  
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
			  
			  
			  Long id = Long.parseLong(obj.get("id").toString());
			  String estado = obj.get("estado").toString();
			  String observacion = obj.get("observacion").toString(); 
			  
			  String nombre = obj.get("nombre").toString(); 
			  String apellido = obj.get("apellido").toString(); 
			  String telefono = obj.get("telefono").toString(); 
			  String domicilio = obj.get("domicilio").toString(); 
			  String email = obj.get("email").toString(); 
		
			  String fechaNacimiento = obj.get("fechaNacimiento").toString(); 
			  String estadoCivil = obj.get("estadoCivil").toString(); 
			  String hijos = obj.get("hijos").toString(); 
			  String actividad = obj.get("actividad").toString(); 
			  String vencimiento = obj.get("vencimiento").toString();
			  String nroSolicitud = obj.get("nroSolicitud").toString();
			
			  String respuesta =  solicitudesWeb.actualizarSoliciutd(id,estado,observacion,nombre,apellido,telefono,domicilio,email,fechaNacimiento,estadoCivil,hijos,actividad,vencimiento,nroSolicitud);
			  
			  if(respuesta.equals("ok")){
				  
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