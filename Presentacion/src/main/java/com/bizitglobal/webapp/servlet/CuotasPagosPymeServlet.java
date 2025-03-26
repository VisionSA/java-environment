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

import com.bizitglobal.tarjetafiel.transacciones.service.impl.CalculoCuotaServicesImpl;
import com.visionis.transaccionador.negocio.Cuota;



public class CuotasPagosPymeServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(CalcularRepactacionServlet.class);

	public CuotasPagosPymeServlet() {
		super();
	}

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	  try{
		  ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		  CalculoCuotaServicesImpl calculocuotaservice= (CalculoCuotaServicesImpl) appContext.getBean("calculoCuotaServicesTarget");
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
		  
		 
		  
		  long listaPrecio = Long.parseLong(obj.get("listaPrecio").toString());
		    double montoTotal = Double.parseDouble((obj.get("montoTotal")).toString());
		  int diaPago= Integer.parseInt(obj.get("diaPago").toString()); 
		 
		 
		
				  List<Cuota> cuotasAux =  calculocuotaservice.calculoCuotaConsumo(montoTotal, listaPrecio, diaPago);
		 // String[] parts = calcCuotas.split(",");
		  int index = 0;
		  Iterator<Cuota> iter = cuotasAux.iterator();
		  
		  Locale locale = new Locale("es","AR"); // Argentina
		  NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		  
		  resp.getWriter().println("{\"set_cuotas\":[");
		  while(iter.hasNext()){
			  Cuota cuota = iter.next();
			  double montoCuota = cuota.getCapital() + cuota.getInteres();
			  resp.getWriter().println( "{" +
											"\"cantidad\":\"" + cuota.getCantidadCuota() + "\","+
											"\"monto\":\"" + nf.format(montoCuota) + "\""+
										"},");
			  index++;
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
