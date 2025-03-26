package com.bizitglobal.webapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.general.negocio.Empresa;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.TamEmpresa;
import com.bizitglobal.tarjetafiel.general.service.impl.EmpresaServiceImpl;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.TransferenciaBancaria;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CodComercioServiceImpl;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.PagosClienteServiceImpl;

public class TransferenciasBancariasServlet extends HttpServlet implements javax.servlet.Servlet {
	
	private static Logger logger = Logger.getLogger(TransferenciasBancariasServlet.class);
	private static final long serialVersionUID = -9166416935188731214L;

	private static final String PAGOS_CLIENTE_SERVICE_NAME = "pagosClienteServiceTarget";



	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public TransferenciasBancariasServlet() {
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
			logger.info("PROCESANDO Transferencia Bancaria ");

			ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			PagosClienteServiceImpl pagoClienteService = (PagosClienteServiceImpl) appContext.getBean(PAGOS_CLIENTE_SERVICE_NAME);
			CodComercioServiceImpl codComercioService = (CodComercioServiceImpl) appContext.getBean("codComercioServiceTarget");
			EmpresaServiceImpl empresaService = (EmpresaServiceImpl) appContext.getBean("empresaServiceTarget");
			
			
			StringBuilder jb = new StringBuilder();
			String line = null;
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null){
				jb.append(line);
			}
			logger.info("jb.toString() "+ jb.toString());
			
			JSONObject obj = new JSONObject(jb.toString());
			
			TransferenciaBancaria transferenciaBancaria = new TransferenciaBancaria();
			
			transferenciaBancaria.setCbuOrigen(obj.get("cbuOrigen").toString());
			transferenciaBancaria.setCvuDestino(obj.get("cbuDestino").toString());
			transferenciaBancaria.setImporte(new BigDecimal( new Double(obj.get("importe").toString())/100));
			transferenciaBancaria.setTitular(obj.get("titular").toString());
			transferenciaBancaria.setCuit(obj.get("cuit").toString());
			transferenciaBancaria.setDescripcion(obj.get("descripcion").toString());
			transferenciaBancaria.setMoneda(obj.get("moneda").toString());
			transferenciaBancaria.setConcepto(obj.get("concepto").toString());
			transferenciaBancaria.setEstado(obj.get("estado").toString());
			transferenciaBancaria.setIdBancario(obj.get("id").toString());
			transferenciaBancaria.setFechaOperacion(obj.get("fechaOperacion").toString());	
			
			transferenciaBancaria.setCliente( new Long(obj.get("cliente").toString()));
			transferenciaBancaria.setIdAdicional( new Long(obj.get("clienteAdicional").toString()));
			
			transferenciaBancaria.setIdComprobante( new Long(obj.get("idComprobante").toString()));	
			transferenciaBancaria.setCodigoComercio(obj.get("codigoComercio").toString());
			transferenciaBancaria.setAlias(obj.get("alias").toString());
			
			if (transferenciaBancaria.getEstado().equals("ACREDITADO")) {	
			
			String result = pagoClienteService.registrarTransferenciaBancaria(transferenciaBancaria);
			
			if(result != null && result.equals("OK")){
				response.setStatus(200);
				response.getWriter().println("OK");				
			}
			else{
				response.setStatus(400);
				response.getWriter().println("ERROR");
				
			}
			
			} else {
			String resultado =	pagoClienteService.grabarSoloTransferenciaBancaria(transferenciaBancaria);
			
			if (resultado == null) {
				response.setStatus(400);
				response.getWriter().println(transferenciaBancaria.getEstado());				
			} else {
				response.setStatus(200);
				response.getWriter().println(transferenciaBancaria.getEstado());
			}
				
			
				
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
