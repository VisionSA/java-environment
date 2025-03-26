package com.bizitglobal.webapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
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

import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoDetalle;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqClienteRepactacion;
import com.bizitglobal.tarjetafiel.transacciones.service.ClienteTransaccionService;
import com.bizitglobal.tarjetafiel.transacciones.service.ConceptoService;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CalculoCuotaServicesImpl;



public class CalcularRepactacionServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(CalcularRepactacionServlet.class);

	public CalcularRepactacionServlet() {
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
		  long idCliente = Long.parseLong(obj.get("id_cliente").toString());
		  double saldoPendiente = Double.parseDouble(obj.get("saldo_pendiente").toString())/100;
		  double montoPago = Double.parseDouble((obj.get("monto_pago")).toString())/100;
		  int diaPago= Integer.parseInt(obj.get("dia_pago").toString()); 
		  String calcCuotas= obj.getString("calc_cuotas").toString();
		  long id_concepto= Long.parseLong(obj.get("id_concepto").toString());
		  
		  ClienteTransaccionService clienteTransaccionService = (ClienteTransaccionService) appContext
					.getBean("clienteTransaccionService");
		  ConceptoService conceptosService = (ConceptoService) appContext
					.getBean("conceptoServiceTarget");
		  
		  Concepto concepto = conceptosService.leerConcepto(id_concepto);
		  
		  
		  ClienteTransaccion clienteTransaccion = clienteTransaccionService
					.leerCliente(idCliente);
		
//		  List<BigDecimal> setRepactacion =  calculocuotaservice.calculoRepactacionWs(saldoPendiente, montoPago, calcCuotas, id_concepto , idCliente, diaPago);
		  
//		  List<BigDecimal> setRepactacion =  calculocuotaservice.calculoRepactacionWs(saldoPendiente, montoPago, calcCuotas,
//				  id_concepto , idCliente, diaPago);
		  
		  Object setRepactacion =  calculocuotaservice.simuladorCuotasIntereses(saldoPendiente, montoPago,  concepto, 
				   clienteTransaccion,0L); 
		  
		  

		  
		  String[] parts = calcCuotas.split(",");
		  int index = 0;
//		  Iterator<LiqClienteRepactacion> iter = convertObjectToList(setRepactacion).iterator();
		  
		  Locale locale = new Locale("es","AR"); // Argentina
		  NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		  
//		  Iterator<LiqClienteRepactacion> iter = convertObjectToList(setRepactacion).iterator();

		  Object[] cuota = convertObjectToList(setRepactacion).toArray();
		  Arrays.sort(cuota, new CompareCuotarepactacion());
		  resp.getWriter().println("{\"set_repactacion\":[");
		  for (int h = 0; h < cuota.length; h++) {
			  
			  LiqClienteRepactacion conceptoDetalle = (LiqClienteRepactacion) cuota[h];
			  
			  resp.getWriter().println( "{" +
						"\"cantidad\":\"" + conceptoDetalle.getNroCuota() + "\","+
						"\"monto\":\"" + nf.format(conceptoDetalle.getMontoCuota()) + "\""+
					"},");
			  
		  }
		  resp.getWriter().println("]}");
		  
		  
//		  resp.getWriter().println("{\"set_repactacion\":[");
//		  while(iter.hasNext()){
//			  LiqClienteRepactacion cuota = iter.next();
//			  resp.getWriter().println( "{" +
//											"\"cantidad\":\"" + parts[index] + "\","+
//											"\"monto\":\"" + nf.format(cuota.getMontoCuota()) + "\""+
//										"},");
//			  index++;
//		  }
//		  
//		  resp.getWriter().println("]}");
		  
	  } 
	  catch (Exception e) {
		  e.printStackTrace();
		  resp.getWriter().println("{}");
		  resp.setStatus(500);
	  }
  }
  
  class CompareCuotarepactacion implements Comparator {

		public int compare(Object arg0, Object arg1) {
			return ((LiqClienteRepactacion) arg0).getNroCuota().compareTo(((LiqClienteRepactacion) arg1).getNroCuota());
		}

	}
  

  public static List<LiqClienteRepactacion> convertObjectToList(Object obj) {
	    List<LiqClienteRepactacion> list = new ArrayList<LiqClienteRepactacion>();
	    if (obj.getClass().isArray()) {
	        list = Arrays.asList((LiqClienteRepactacion[])obj);
	    } else if (obj instanceof Collection) {
	        list = new ArrayList<LiqClienteRepactacion>((Collection<LiqClienteRepactacion>)obj);
	    }
	    return list;
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