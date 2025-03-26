package com.bizitglobal.webapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;


import com.bizitglobal.tarjetafiel.transacciones.negocio.AsistenciaPersonal;
import com.bizitglobal.tarjetafiel.transacciones.negocio.OperadorAsistencia;
import com.bizitglobal.tarjetafiel.transacciones.negocio.SirtacAliCuota;
import com.bizitglobal.tarjetafiel.transacciones.negocio.SolicitudWeb;
import com.bizitglobal.tarjetafiel.transacciones.service.GestionarClienteService;




public class AsistenciaPersonalServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(GenerarTxtLiqComercioServlet.class);

	public AsistenciaPersonalServlet() {
		super();
	}

  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	  	try{
		  
			  ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			  GestionarClienteService ControlAsistencia = (GestionarClienteService) appContext.getBean("gestionarClienteService");
			  
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
			  
			  String tipo = obj.get("tipo").toString();
			  Long operador = Long.parseLong(obj.get("idOperador").toString());
			  Long dia = Long.parseLong(obj.get("dia").toString());
			  Long mes = Long.parseLong(obj.get("mes").toString());
			  Long anio = Long.parseLong(obj.get("anio").toString());
			  
			  String horaManEnt = obj.get("horaManEnt").toString();
			  String horaManSal = obj.get("horaManSal").toString();
			  String horaTarEnt = obj.get("horaTarEnt").toString();
			  String horaTarSal = obj.get("horaTarSal").toString();
			  String observacion = obj.get("observacion").toString();
			  Long id = Long.parseLong(obj.get("id").toString());
			  
			  
			  
			  
			  //RETORNO LOS HORARIOS DEL MES ACTUAL PARA MOSTRARLOS EN LA TABLA
			  if(tipo.equals("buscar")){
				  
				  List<AsistenciaPersonal> asistencia =  ControlAsistencia.getAsistencia(operador,mes,anio);
				  
				  if(asistencia != null){
					resp.getWriter().println("[");
					Iterator<AsistenciaPersonal> iter = asistencia.iterator();
						  while(iter.hasNext()){
							  AsistenciaPersonal asist = iter.next();
							  resp.getWriter().println(asist.json());
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
			  //INSERTO UN NUEVO HORARIO EN UN NUEVO DIA
			  else if(tipo.equals("insertar")){
				  
				  String result = ControlAsistencia.insertarAsistencia(operador,dia,mes,anio,horaManEnt,horaManSal,horaTarEnt,horaTarSal,observacion);

				  if(result != "error"){
					  
					  resp.getWriter().println("{" + "\"id\":\"" + result + "\"" +"}");
			  
				  }
				  
				  
			  }
			  //INSERTA UN NUEVO HORARIO EN EL MISMO DIA
			  else if(tipo.equals("actualizar")){
				 			  			  
				  String result = ControlAsistencia.actualizarAsistencia(id, horaManEnt, horaManSal, horaTarEnt, horaTarSal,observacion);

				  if(result.equals("ok")){
					  
					  resp.getWriter().println("{" + "\"result\":\"" + result + "\"" +"}");
			  
				  }				  
			  }
	          	  
		  } 
		  catch (Exception e) {
			  e.printStackTrace();
			  resp.getWriter().println("{}");
			  resp.setStatus(500);
		  }
 
  }
  
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	  try{
		  
		  ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		  GestionarClienteService operadores = (GestionarClienteService) appContext.getBean("gestionarClienteService");
		  
		  resp.setHeader("Access-Control-Allow-Origin", "*");
		  resp.setHeader("Access-Control-Allow-Methods", "GET");
		  resp.setContentType("application/json");
	  
		  
		  
		  List<OperadorAsistencia> Operadores =  operadores.getOperadores();
		  
		  if(Operadores != null){
			  
			  JSONArray jsonArray = new JSONArray(Operadores);
			  String myJson = jsonArray.toString();
						 
			  resp.getWriter().println(myJson);	  
		  }
		  else{
			  resp.getWriter().println("[]");
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