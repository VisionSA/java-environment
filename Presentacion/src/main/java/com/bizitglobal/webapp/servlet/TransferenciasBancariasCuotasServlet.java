package com.bizitglobal.webapp.servlet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

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
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CalculoCuotaServicesImpl;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CodComercioServiceImpl;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.PagosClienteServiceImpl;
import com.visionis.transaccionador.negocio.Cuota;

public class TransferenciasBancariasCuotasServlet extends HttpServlet implements javax.servlet.Servlet {
	
	private static Logger logger = Logger.getLogger(TransferenciasBancariasCuotasServlet.class);
	private static final long serialVersionUID = -9166416935188731214L;

	private static final String PAGOS_CLIENTE_SERVICE_NAME = "pagosClienteServiceTarget";



	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public TransferenciasBancariasCuotasServlet() {
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
			logger.info("PROCESANDO Transferencia Bancaria Cuotas");

			ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			PagosClienteServiceImpl pagoClienteService = (PagosClienteServiceImpl) appContext.getBean(PAGOS_CLIENTE_SERVICE_NAME);
			CodComercioServiceImpl codComercioService = (CodComercioServiceImpl) appContext.getBean("codComercioServiceTarget");
			EmpresaServiceImpl empresaService = (EmpresaServiceImpl) appContext.getBean("empresaServiceTarget");
			CalculoCuotaServicesImpl calculocuotaservice= (CalculoCuotaServicesImpl) appContext.getBean("calculoCuotaServicesTarget");
			
			
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
			transferenciaBancaria.setListaPrecio(obj.get("listaPrecioCompras").toString());
			
			int diaCierre= Integer.parseInt(obj.get("diaCierre").toString()); 
			  long idConcepto= Long.parseLong(obj.get("idConcepto").toString());
			  long listaPrecioCompras= Long.parseLong(obj.get("listaPrecioCompras").toString());
			  int cantidadCuotas= Integer.parseInt(obj.get("cantidadCuotas").toString());
			
			
			
			if (transferenciaBancaria.getEstado().equals("ACREDITADO")) {	
				
//			List<Cuota> cuotas = calculocuotaservice.calcularCuotas(new Double(obj.get("importe").toString())/100
//			,cantidadCuotas, transferenciaBancaria.getCliente(), diaCierre, idConcepto);
			
			List<Cuota> cuotas = calculocuotaservice.calcularCuotasTrans(new Double(obj.get("importe").toString())/100,
					cantidadCuotas, transferenciaBancaria.getCliente(), diaCierre, idConcepto,listaPrecioCompras);
			
			
			String result = pagoClienteService.registrarTransfCuotaBancaria(transferenciaBancaria,cuotas);
			
			if(result != null && result.equals("OK")){
				
				/////////////////////////////////////////////////////
				/// AGRAGO CODIGO QUE DESPUES SE DEBE BORRAR
				/////////////////////////////////////////////////
				Locale locale = new Locale("es", "AR");      
				NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
				String msj = "" +
						"{" +							
							"\"client\" : " +
							"{" +
								"\"id\":"+ new Long(obj.get("cliente").toString()) + " " +								
							"}," +
														
							"\"commerce\" : " +
							"{" +
								"\"id\":"+  new Long(obj.get("codigoComercio").toString()) + "," +
								"\"name\":\"" + transferenciaBancaria.getTitular().trim() + "\"" +
							"}," +
														
							"\"purchase\" : " +
							"{" +
								"\"ammount\":\""+ currencyFormatter.format(new Double(obj.get("importe").toString())/100) + "\"," +
								"\"quotas\":"+ cantidadCuotas + " " +	
								"}" +
						" }";
				
				
					logger.info("msj : " + msj);
				

					// 1. URL
					//URL url = new URL("http://tarjetafiel.com/FielWebCliente/appCliente/PlanificadorNotificacionWebServlet");
					
					URL url = new URL("http://192.168.79:3000/send/compra");

					// 2. Open connection
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					// 3. Specify POST method
					conn.setRequestMethod("POST");

					// 4. Set the headers
					conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
					//conn.setRequestProperty("Authorization", "key=" + apiKey);

					conn.setDoOutput(true);

					// 5. Add JSON data into POST request body
					DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
					wr.writeBytes(msj);

					// 5.1 Send the request
					wr.flush();

					// 5.2 close
					wr.close();

					// 6. Get the response
					int responseCode = conn.getResponseCode();
					logger.info("\nSending 'POST' request to URL send compra : " + url);
					logger.info("Response Code : " + responseCode);

					if (responseCode == 200) {
						
					} else {
						
					}
				/////////////////////////////////////////////////////
				/// HASTA AQUI EL CODIGO AGREGADO PARA BORRAR
				/////////////////////////////////////////////////////
					
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


