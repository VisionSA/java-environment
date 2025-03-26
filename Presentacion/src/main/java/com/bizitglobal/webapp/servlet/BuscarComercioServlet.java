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
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.SirtacAliCuota;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioBusqueda;
import com.bizitglobal.tarjetafiel.transacciones.service.CodComercioService;
import com.bizitglobal.tarjetafiel.transacciones.service.GestionarClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CodComercioServiceImpl;




public class BuscarComercioServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(GenerarTxtLiqComercioServlet.class);

	public BuscarComercioServlet() {
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
			  String valor = obj.get("valor").toString(); 
			  	  
			  Filtro filtro  = new Filtro();
			  
			  if(tipo.equals("cod_posnet")){
				  
				  filtro.getCampos().add("codigoPosnet");
				  filtro.getOperadores().add(Filtro.IGUAL);
				  filtro.getValores().add(valor);
				  
				  CodComercio codComercio =  codComercioService.buscarComercioConsultaFlex(filtro);
				  JSONObject jsonObject = new JSONObject(codComercio);
				  String myJson = jsonObject.toString();
				  if(codComercio != null){
					  resp.getWriter().println(myJson);		  
				  }
				  else{	  
					  resp.getWriter().println("{}");
					  resp.setStatus(400);
				  }
				  
			  }   else if(tipo.equals("cuit")){
				  
				  String  buscarPor = "sucEmpresa.empresa.cuit";
					Paginador paginador = new Paginador();
					
					paginador.setCantidadRegistros(25);
					paginador.setPagina(0);
					paginador.setCantidadPaginas(0);			
					//paginador.set
					
					  
					  filtro.setFuncion("Where UPPER(" + buscarPor + ") like '%" + valor + "%'");
					  
					  Paginador listaComercios =  codComercioService.getCodComercioFlex(filtro,paginador);
					  log.info(listaComercios);
					  JSONObject jsonObject = new JSONObject(listaComercios);
					  String myJson = jsonObject.toString();
					  if(listaComercios != null){
						  resp.getWriter().println(myJson);		  
					  }
					  else{	  
						  resp.getWriter().println("{}");
						  resp.setStatus(400);
					  }
			  }
			  
			  else if(tipo.equals("razon_social")){
				  
				String  buscarPor = "sucEmpresa.empresa.razonSocial";
				Paginador paginador = new Paginador();
				
				paginador.setCantidadRegistros(25);
				paginador.setPagina(0);
				paginador.setCantidadPaginas(0);			
				//paginador.set
				
				  
				  filtro.setFuncion("Where UPPER(" + buscarPor + ") like '%" + valor + "%'");
				  
				  Paginador listaComercios =  codComercioService.getCodComercioFlex(filtro,paginador);
				  log.info(listaComercios);
				  JSONObject jsonObject = new JSONObject(listaComercios);
				  String myJson = jsonObject.toString();
				  if(listaComercios != null){
					  resp.getWriter().println(myJson);		  
				  }
				  else{	  
					  resp.getWriter().println("{}");
					  resp.setStatus(400);
				  }
				  		  
			  }
			  else if(tipo.equals("nombre_fantasia")){
				  
					String  buscarPor = "sucEmpresa.descripcion";
					Paginador paginador = new Paginador();
					
					paginador.setCantidadRegistros(25);
					paginador.setPagina(0);
					paginador.setCantidadPaginas(0);			
					//paginador.set
					
					  
					  filtro.setFuncion("Where UPPER(" + buscarPor + ") like '%" + valor + "%'");
					  					
					  
					  Paginador listaComercios =  codComercioService.getCodComercioFlex(filtro,paginador);
					  log.info(listaComercios);
					  JSONObject jsonObject = new JSONObject(listaComercios);
					  String myJson = jsonObject.toString();
					  if(listaComercios != null){
						  resp.getWriter().println(myJson);		  
					  }
					  else{	  
						  resp.getWriter().println("{}");
						  resp.setStatus(400);
					  }
					  		  
				  } else if(tipo.equals("generico")){ 
					  
					  		/// COMERCIO GENERICO BUSCA POR MAIL O POR TELEFONO
						  
							//String  buscarPor = "sucEmpresa.descripcion";
							Paginador paginador = new Paginador();
							
							paginador.setCantidadRegistros(25);
							paginador.setPagina(0);
							paginador.setCantidadPaginas(0);			
							//paginador.set
							
							  
							 // filtro.setFuncion("Where UPPER(" + buscarPor + ") like '%" + valor + "%'");
							  
							 filtro.setFuncion (" where (obj.sucEmpresa.idSucEmpresa in (select obj1.sucEmpresa from SucEmail obj1 inner join obj1.email gmails where upper(gmails.email)  LIKE '%" + valor + "%')) " +
					     	 " or (obj.sucEmpresa.idSucEmpresa in (select obj1.sucEmpresa from SucTelefono obj1 inner join obj1.telefono gtelefonos  where gtelefonos.nroTlefono  LIKE '%" + valor + "%' )) ");
										
							  					
							  
							  Paginador listaComercios =  codComercioService.getCodComercioFlex(filtro,paginador);
							  log.info(listaComercios);
							  JSONObject jsonObject = new JSONObject(listaComercios);
							  String myJson = jsonObject.toString();
							  if(listaComercios != null){
								  resp.getWriter().println(myJson);		  
							  }
							  else{	  
								  resp.getWriter().println("{}");
								  resp.setStatus(400);
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