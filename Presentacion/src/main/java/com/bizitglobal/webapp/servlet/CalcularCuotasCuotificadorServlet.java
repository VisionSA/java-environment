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
import com.bizitglobal.tarjetafiel.transacciones.negocio.CuotaCuantificador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiqClienteRepactacion;
import com.bizitglobal.tarjetafiel.transacciones.service.ClienteTransaccionService;
import com.bizitglobal.tarjetafiel.transacciones.service.ConceptoService;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CalculoCuotaServicesImpl;
import com.visionis.transaccionador.negocio.Cuota;



public class CalcularCuotasCuotificadorServlet extends HttpServlet implements javax.servlet.Servlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = -9166416935188731214L;
	private Logger log = Logger.getLogger(CalcularRepactacionServlet.class);

	public CalcularCuotasCuotificadorServlet() {
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
		  
		  String comprasConAlias = "NO";
		  long listaPrecioCompras = 0L;
		  
		  
		  JSONObject obj = new JSONObject(jb.toString());
		  long idCliente = Long.parseLong(obj.get("idCliente").toString());
		  double montoCompra = Double.parseDouble(obj.get("montoCompra").toString())/100;
		  int diaCierre= Integer.parseInt(obj.get("diaCierre").toString()); 
		  long idConcepto= Long.parseLong(obj.get("idConcepto").toString());
		  
		  try {
			  listaPrecioCompras = Long.parseLong(obj.get("listaPrecioCompras").toString());				   
			  comprasConAlias = "SI";
		  } catch (Exception e) {
			  comprasConAlias = "NO";
			}
		  
		  ClienteTransaccionService clienteTransaccionService = (ClienteTransaccionService) appContext
					.getBean("clienteTransaccionService");
		  ConceptoService conceptosService = (ConceptoService) appContext
					.getBean("conceptoServiceTarget");
		  
		  Concepto concepto = conceptosService.leerConcepto(idConcepto);	  
		  
		  ClienteTransaccion clienteTransaccion = clienteTransaccionService
					.leerCliente(idCliente);
		  
//		  List<CuotaCuantificador> cuotas = null;
		  
//		  if ( comprasConAlias.equals("NO")) {
//			  
//			   cuotas = calculocuotaservice.calculoCuotasCuotificador(montoCompra, idCliente, diaCierre, idConcepto);
//			  
//		  } else {
//			   cuotas = calculocuotaservice.calculoCuotasComprasEnCuotas(montoCompra, idCliente, diaCierre, idConcepto,listaPrecioCompras);
//		  }
		  
		  Object setRepactacion =  calculocuotaservice.simuladorCuotasIntereses(montoCompra, 0D,  concepto, 
				   clienteTransaccion,listaPrecioCompras);
		  
		  Locale locale = new Locale("es","AR"); // Argentina
		  NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		 
		  Object[] cuota = convertObjectToList(setRepactacion).toArray();
		  Arrays.sort(cuota, new CompareCuotarepactacion());
//		  resp.getWriter().println("{\"set_repactacion\":[");
		  resp.getWriter().println("{\"setCuotas\":[");
		  for (int h = 0; h < cuota.length; h++) {
			  
			  LiqClienteRepactacion conceptoDetalle = (LiqClienteRepactacion) cuota[h];
			  
			  resp.getWriter().println( "{" +
						"\"cantidad\":\"" + conceptoDetalle.getNroCuota() + "\","+
						"\"monto\":\"" + nf.format(conceptoDetalle.getMontoCuota()) + "\""+
					"},");
			  
		  }
		  resp.getWriter().println("]}");
		  
	  } 
	  catch (Exception e) {
		  e.printStackTrace();
		  resp.getWriter().println("{}");
		  resp.setStatus(500);
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
  
  class CompareCuotarepactacion implements Comparator {

		public int compare(Object arg0, Object arg1) {
			return ((LiqClienteRepactacion) arg0).getNroCuota().compareTo(((LiqClienteRepactacion) arg1).getNroCuota());
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