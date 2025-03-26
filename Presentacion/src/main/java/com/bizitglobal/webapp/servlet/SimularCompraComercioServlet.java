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

import com.bizitglobal.tarjetafiel.transacciones.negocio.CuotaCuantificador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CuotaSimulada;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CalculoCuotaServicesImpl;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.TransaccionServiceImpl;
import com.visionis.transaccionador.negocio.Cuota;



public class SimularCompraComercioServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(SimularCompraComercioServlet.class);

	public SimularCompraComercioServlet() {
		super();
	}

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	  try{
		  ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		  CalculoCuotaServicesImpl calculocuotaservice= (CalculoCuotaServicesImpl) appContext.getBean("calculoCuotaServicesTarget");
		  TransaccionServiceImpl transaccionService = (TransaccionServiceImpl) appContext.getBean("transaccionServiceTarget");
		  
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
		  String nroTarjeta = obj.get("nroTarjeta").toString();
		  String cvv = obj.get("cvv").toString();
		  String monto = obj.get("monto").toString();
		  Long idListaPrecio = new Long(obj.get("idListaPrecio").toString());
		  
		  
		  
		  String idCliente = null;
		  String montoAux = monto.replace(",",".");
		  double montoTotal = new Double(montoAux); 
		  Integer diaPago = 0;
		  
			List<String> datosCli = transaccionService.getDatosCliente(nroTarjeta, cvv);
		
					
					
					if(datosCli.get(12)!=null){
						diaPago = new Integer(datosCli.get(12));
					}
			
		  log.info("diaPago "+ diaPago);
		  
		 
//					List<Cuota> cuotasAux = calculocuotaservice.calculoCuotaConsumo(montoTotal, idListaPrecio, diaPago);
					
					List<Cuota> cuotasAux = calculocuotaservice.calculoCuotaSimulador(montoTotal, idListaPrecio, diaPago);
					
					
		  
//		  for (int i=0; i<cuotas.size() ; i++){
//			  log.info("cuota: " + i + 1 + "   parts: " + cuotas.get(i));
//		  }
		  
		  int index = 0;
//		  Iterator<Cuota> iter = cuotas.iterator();	 
//		  Locale locale = new Locale("es","AR"); // Argentina
//		  NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		  
		  
		  int cantMaxCuotas = 0;
			for(int i=0; i<cuotasAux.size(); i++){
				if(cuotasAux.get(i).getCantidadCuota() > cantMaxCuotas){
					cantMaxCuotas = cuotasAux.get(i).getCantidadCuota();
				}
			}
			
			Cuota[] cuotasArray = new Cuota[cantMaxCuotas];
			for(int i=0; i<cantMaxCuotas; i++){
				cuotasArray[i] = null;
			}
			
			for(int i=0; i<cuotasAux.size(); i++){
				cuotasArray[cuotasAux.get(i).getCantidadCuota()-1] = cuotasAux.get(i);
			}
			
			Locale locale = new Locale("es", "AR");      
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
			
			List<CuotaSimulada> cuotaSimulada = new ArrayList<CuotaSimulada>(); 
			
			for(int i=0; i<cantMaxCuotas; i++){
				if(cuotasArray[i]!=null){
					double montoCuota = cuotasArray[i].getCapital() + cuotasArray[i].getInteres();
					
					CuotaSimulada cuotaSim  = new CuotaSimulada();
					cuotaSim.setCantidadCuotas(cuotasArray[i].getCantidadCuota());
					cuotaSim.setMonto(currencyFormatter.format(montoCuota));
					
					cuotaSimulada.add(cuotaSim);
				}
			}
			
			Iterator<CuotaSimulada> iter = cuotaSimulada.iterator();
		  
		  
		  
		  
		  
		  resp.getWriter().println("[");
		//  resp.getWriter().println("{");
		  while(iter.hasNext()){
			  CuotaSimulada cuota = iter.next();
			  resp.getWriter().println( "{" +
											"\"cantidad\":\"" + cuota.getCantidadCuotas() + "\","+
											"\"monto\":\"" + cuota.getMonto() + "\""+
										"},");
			  index++;
		  }
		  
		//  resp.getWriter().println("}");
		  resp.getWriter().println("]");
		  
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


