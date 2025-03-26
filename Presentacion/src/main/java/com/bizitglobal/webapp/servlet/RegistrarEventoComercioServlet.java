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


import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.negocio.Eventos;

import com.bizitglobal.tarjetafiel.transacciones.negocio.CuotaCuantificador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.EventosComercio;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.EventosComercioServiceImpl;




public class RegistrarEventoComercioServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(RegistrarEventoComercioServlet.class);

	public RegistrarEventoComercioServlet() {
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
			  EventosComercioServiceImpl eventosComercioService = (EventosComercioServiceImpl) appContext.getBean("eventosComercioServiceTarget");
			  
			  resp.setHeader("Access-Control-Allow-Origin", "*");
			  resp.setHeader("Access-Control-Allow-Methods", "POST");
			  resp.setContentType("application/json");
			  
			  StringBuilder jb = new StringBuilder();
			  String line = null;
			  BufferedReader reader = req.getReader();
			  while ((line = reader.readLine()) != null){
			      jb.append(line);
			  }
			  SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
			  //Date fecha = formato.parse("23/11/2015");
			  JSONObject obj = new JSONObject(jb.toString());
			  
			  String comentario = obj.get("comentario").toString();
			  String descripcion = obj.get("descripcion").toString();
			  
			  System.out.println("comentario " + comentario);
			  
			  
			  Date fechaDesde = formato.parse(obj.get("fechaDesde").toString());
			  Date fechaHasta = formato.parse(obj.get("fechaHasta").toString());
			  
			  System.out.println("fechaDesde " + formato.format(fechaDesde));
			  System.out.println("fechaHasta " + formato.format(fechaHasta));
			  
			  long idComercio= Long.parseLong(obj.get("idComercio").toString());
			  
//			  Date fechaDesde = formato.parse(obj.get("fechaDesde").toString());
//			  Date fechaHasta = formato.parse(obj.get("fechaHasta").toString());
//			  long idComercio= Long.parseLong(obj.get("idComercio").toString());
			  
			  EventosComercio eventosComercio = new  EventosComercio();
			  Eventos evento = new  Eventos();
			  
			  eventosComercio.setIdComercio(idComercio);
			  evento.setComentario(comentario.trim());
			  evento.setDescripcion(descripcion.trim());			  
			  evento.setFechaEvento(new Date());
			  eventosComercio.setEvento(evento);
			  
			  
			  	  
//			  List<EventosComercio> eventosComercios  = new ArrayList<EventosComercio>();
			  
			 // getEventosComerciosFlex(final Date fechaDesde, final Date fechaHasta, final Long idComercio)
				  
			  EventosComercio nuevoEventosComercio =  eventosComercioService.grabarEventoComercio(eventosComercio);
			  
			  List<EventosComercio> eventosComercios  = new ArrayList<EventosComercio>();
			  
				 // getEventosComerciosFlex(final Date fechaDesde, final Date fechaHasta, final Long idComercio)
					  
				  eventosComercios =  eventosComercioService.getEventosComerciosFlex(fechaDesde,fechaHasta,idComercio);
				  Iterator<EventosComercio> iter = eventosComercios.iterator();
				  
				  int size = eventosComercios.size();
				  int cantidad = 1;
				  
				  resp.getWriter().println("{\"setEventosComercios\":[");
				  while(iter.hasNext()){
					  EventosComercio eventosComercio1 = iter.next();
					  JSONObject eventosComercioJson = new JSONObject(eventosComercio1); 
					  if (size == cantidad) {
						  resp.getWriter().println( eventosComercioJson.toString());
						  
					  } else {
						  resp.getWriter().println( eventosComercioJson.toString()+",");
						  
					  }	
					  cantidad++;
				  }
				  
				  resp.getWriter().println("]}");
			    
			           	  
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



