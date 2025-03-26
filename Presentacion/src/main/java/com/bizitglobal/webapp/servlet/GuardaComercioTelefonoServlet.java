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
import com.bizitglobal.tarjetafiel.commons.paginacion.Paginador;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.tarjetafiel.general.negocio.TipoTelefono;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.operador.service.OperadorService;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.SirtacAliCuota;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioBusqueda;
import com.bizitglobal.tarjetafiel.transacciones.service.CodComercioService;
import com.bizitglobal.tarjetafiel.transacciones.service.GestionarClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CodComercioServiceImpl;




public class GuardaComercioTelefonoServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(GuardaComercioTelefonoServlet.class);

	public GuardaComercioTelefonoServlet() {
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
			  CodComercioServiceImpl codComercioService = (CodComercioServiceImpl) appContext.getBean("codComercioServiceTarget");
			  Operador operadorTransferencia = ((OperadorService) appContext.getBean("operadorService")).leerOperador(1283l);
			  
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
			  
			  String codigo = null;
			  String numero = null;
			  
			  String nroTlefono = obj.get("numero").toString();
			  Long idSucEmpresa = new Long(obj.get("idSucEmpresa").toString());
			  
			  if (nroTlefono.trim().length() > 10) {
				   codigo = nroTlefono.substring(0, 4);
				   numero = nroTlefono.substring(4);				  
			  } else {
				  codigo = nroTlefono.substring(0, 3);
				   numero = nroTlefono.substring(3);
				  
			  }
				  
			  
			  
			  Telefono telefono = new  Telefono();
			  TipoTelefono tipoTelefono = new TipoTelefono();
			  
			  tipoTelefono.setIdTipoTelefono(2L);
			  
			  telefono.setEsCelularBoolean(true);
			  telefono.setEsCelular("S");
			  telefono.setEsFax("N");
			  telefono.setEsFaxBoolean(false);
			  telefono.setNroTlefono(numero);
			  telefono.setCodArea(codigo);
			  telefono.setTipo(tipoTelefono);
			  telefono.setDescripcion("Agregado por Transferencia");
			  telefono.setOperador(operadorTransferencia);
			  
			  
			  List telefonos = codComercioService.agregarTelefono(telefono, idSucEmpresa);
			  String result = "OK";
			  
			  if (telefonos != null) {
				  resp.getWriter().println("{" + "\"result\":\"" + result + "\"" +"}");
				  
			  } else {
			  	
								  resp.getWriter().println("{}");
								  resp.setStatus(400);
							  
			  }
			           	  
		  } 
		  catch (Exception e) {
			  e.printStackTrace();
			  resp.getWriter().println("{}");
			  resp.setStatus(400);
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


