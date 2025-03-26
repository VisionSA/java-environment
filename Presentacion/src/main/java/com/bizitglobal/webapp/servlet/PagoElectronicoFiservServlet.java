package com.bizitglobal.webapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.transacciones.service.impl.PagosClienteServiceImpl;


import org.apache.log4j.Logger;
import org.json.JSONObject;


public class PagoElectronicoFiservServlet extends HttpServlet implements javax.servlet.Servlet {
	
	private static Logger logger = Logger.getLogger(PagoElectronicoFiservServlet.class);
	private static final long serialVersionUID = -9166416935188731214L;

	private static final String PAGOS_CLIENTE_SERVICE_NAME = "pagosClienteServiceTarget";



	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public PagoElectronicoFiservServlet() {
		super();
	}


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//PAGO A TRAVES DEL BOTON DE PAGO - PEI LINK
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			logger.info("PROCESANDO PAGO ELECTRONICO FISERV");

			ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			PagosClienteServiceImpl pagoClienteService = (PagosClienteServiceImpl) appContext.getBean(PAGOS_CLIENTE_SERVICE_NAME);
			
			StringBuilder jb = new StringBuilder();
			String line = null;
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null){
				jb.append(line);
			}
			JSONObject obj = new JSONObject(jb.toString());
			Long idCliente = new Long(obj.get("id_cliente").toString());
			Double importe = new Double(obj.get("importe").toString());
			boolean repacta = false;
			if(obj.get("repacta").toString().equals("S")){
				repacta = true;
			}
			int cuotas = Integer.parseInt(obj.get("cuotas").toString());
			Double saldoRepactacion = new Double(obj.get("saldo_repactacion").toString());
			
			String judicial = obj.get("judicial").toString();
			
			
			
			String result = pagoClienteService.registrarPagoClienteFiserv(importe, idCliente, repacta, cuotas, saldoRepactacion);
			if(result.equals("OK")){
				response.getWriter().println("{\"result\": \"OK\"}");
			}
			else{
				response.setStatus(400);
				response.getWriter().println("{\"result\": \"Error al procesar el pago\"}");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			response.getWriter().println("{\"result\": \"Error al enviar requerimiento de pago\"}");
		} 
		
	}
	
	
	public void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try{
			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
			resp.setHeader("Access-Control-Allow-Methods", "OPTIONS");
			resp.setContentType("application/json");
		} 
		catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().println(emptyResponse());
		}
	}
		  
		  
	private String emptyResponse(){
		return "[]";
	}
	  

}


