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
import com.fasterxml.jackson.databind.ObjectMapper;


import org.apache.log4j.Logger;
import org.json.JSONObject;


public class VerazInformacionServlet extends HttpServlet implements javax.servlet.Servlet {
	
	private static Logger logger = Logger.getLogger(VerazInformacionServlet.class);
	private static final long serialVersionUID = -9166416935188731214L;

	private static final String PAGOS_CLIENTE_SERVICE_NAME = "pagosClienteServiceTarget";



	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public VerazInformacionServlet() {
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
			logger.info("PROCESANDO Transferencia inmediate ");

			ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			PagosClienteServiceImpl pagoClienteService = (PagosClienteServiceImpl) appContext.getBean(PAGOS_CLIENTE_SERVICE_NAME);
			
			StringBuilder jb = new StringBuilder();
			String line = null;
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null){
				jb.append(line);
			}
			logger.info("jb.toString() "+ jb.toString());
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			JSONObject obj = new JSONObject(jb.toString());
			String cbuOrigen = (obj.get("cbuOrigen").toString());
			String cbuDestino = (obj.get("cbuDestino").toString());
			Double importe = new Double(obj.get("importe").toString())/100;
			
			Long cliente = new Long(obj.get("cliente").toString());
			Long clienteAdicional = new Long(obj.get("clienteAdicional").toString());
			String razonSocial = (obj.get("razonSocial").toString());
			
			String numeroReferenciaBancaria = (obj.get("numeroReferenciaBancaria").toString());
			String numeroConciliacionBancaria = (obj.get("numeroConciliacionBancaria").toString());
			String fechaOperacion = (obj.get("fechaOperacion").toString());
			String codigoComercio = (obj.get("codigoComercio").toString());
			String alias = (obj.get("alias").toString());
			
			
			String result = pagoClienteService.registrarTransferenciaInmed(cbuOrigen,cbuDestino,importe, cliente,clienteAdicional,
					razonSocial,numeroReferenciaBancaria
					,numeroConciliacionBancaria,fechaOperacion,codigoComercio,alias);
			if(result.equals("OK")){
				response.setStatus(200);
				response.getWriter().println("ACREDITADO");
			}
			else{
				response.setStatus(400);
				response.getWriter().println("ERROR");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			response.getWriter().println("ERROR");
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




