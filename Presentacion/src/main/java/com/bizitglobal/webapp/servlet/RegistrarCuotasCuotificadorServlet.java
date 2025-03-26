package com.bizitglobal.webapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
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

import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CuotaCuantificador;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CalculoCuotaServicesImpl;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.NotaCreditoDebitoServiceImpl;
import com.visionis.transaccionador.negocio.Cuota;



public class RegistrarCuotasCuotificadorServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(RegistrarCuotasCuotificadorServlet.class);

	public RegistrarCuotasCuotificadorServlet() {
		super();
	}

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	  try{
		  ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		  CalculoCuotaServicesImpl calculocuotaservice= (CalculoCuotaServicesImpl) appContext.getBean("calculoCuotaServicesTarget");
		  NotaCreditoDebitoServiceImpl notaCreditoDebitoService= (NotaCreditoDebitoServiceImpl) appContext.getBean("notaCreditoDebitoServiceTarget");
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
		  long idCliente = Long.parseLong(obj.get("idCliente").toString());
		  double montoCompra = Double.parseDouble(obj.get("montoCompra").toString())/100;
		  int diaCierre= Integer.parseInt(obj.get("diaCierre").toString()); 
		  long idConcepto= Long.parseLong(obj.get("idConcepto").toString());
		  long idTransaccion= Long.parseLong(obj.get("idTransaccion").toString());
		  int cantidadCuotas= Integer.parseInt(obj.get("cantidadCuotas").toString());
		  
		  String estadoImpacto = "C";
		  
		  // calcularCuotas(double montoTotal,  int cantidadCuotas,  long idCliente, int diaCierre,long idConcepto)
//		   List<Cuota> cuotas = calculocuotaservice.calcularCuotas(montoCompra,cantidadCuotas, idCliente, diaCierre, idConcepto);
		   List<Cuota> cuotas = calculocuotaservice.calcularCuotasTrans(montoCompra,
					cantidadCuotas,idCliente, diaCierre, idConcepto,0L);
		   notaCreditoDebitoService.notaCreditoMultiCuota( idCliente, idTransaccion, estadoImpacto, cuotas,idConcepto,montoCompra);
		
		 
		   
		   //notaCreditoMultiCuota(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;)
		  
//		  for (int i=0; i<cuotas.size() ; i++){
//			  log.info("cuota: " + i + 1 + "   parts: " + cuotas.get(i));
//		  }
		  
		   resp.getWriter().println("{\"result\": \"OK\"}");
		  
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
		  resp.getWriter().println("{\"result\": \"Error al registrar cuotas cuantificadas\"}");
		  resp.setStatus(401);
	  }
  }
  

}