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
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.TamEmpresa;
import com.bizitglobal.tarjetafiel.general.service.impl.EmpresaServiceImpl;
import com.bizitglobal.tarjetafiel.general.service.impl.SucEmpresaServiceImpl;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioFormaPago;
import com.bizitglobal.tarjetafiel.transacciones.negocio.TransferenciaBancaria;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.CodComercioServiceImpl;
import com.bizitglobal.tarjetafiel.transacciones.service.impl.PagosClienteServiceImpl;

public class GenerarCodComercioServlet extends HttpServlet implements javax.servlet.Servlet {
	
	private static Logger logger = Logger.getLogger(GenerarCodComercioServlet.class);
	private static final long serialVersionUID = -9166416935188731214L;

	private static final String PAGOS_CLIENTE_SERVICE_NAME = "pagosClienteServiceTarget";



	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GenerarCodComercioServlet() {
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
			logger.info("PROCESANDO GENERACION CODIGO  COMERCIO ");

			ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			PagosClienteServiceImpl pagoClienteService = (PagosClienteServiceImpl) appContext.getBean(PAGOS_CLIENTE_SERVICE_NAME);
			CodComercioServiceImpl codComercioService = (CodComercioServiceImpl) appContext.getBean("codComercioServiceTarget");
			EmpresaServiceImpl empresaService = (EmpresaServiceImpl) appContext.getBean("empresaServiceTarget");
			SucEmpresaServiceImpl sucEmpresaService = (SucEmpresaServiceImpl) appContext.getBean("sucEmpresaServiceTarget"); 
			
			StringBuilder jb = new StringBuilder();
			String line = null;
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null){
				jb.append(line);
			}
			logger.info("jb.toString() "+ jb.toString());
			
			JSONObject obj = new JSONObject(jb.toString());
			
						
			String cvu = obj.get("cvu").toString();
			String titular = obj.get("titular").toString();
			String tributo = obj.get("tributo").toString();
			
			logger.info("tributo "+ tributo);
			
			ComercioFormaPago comercioFormaPago = new ComercioFormaPago();
			FormaPago formaPago = new FormaPago();
			formaPago.setIdFormaPago(3L);
			comercioFormaPago.setFormaPago(formaPago);
			comercioFormaPago.setCbu(cvu);
			
			logger.info("paso1 ");
			
			SucEmpresa sucEmpresa = new SucEmpresa();
			Empresa empresa = new Empresa();
			CodComercio codComercio = new  CodComercio();
			TamEmpresa tamEmpresa = new TamEmpresa();
			Jurisdiccion jurisdiccion = new Jurisdiccion();
			tamEmpresa.setIdTamanioEmp(3L);
			tamEmpresa.setDescripcion("Pequeña");
			
			logger.info("paso2 ");
			
			jurisdiccion.setIdJurisdiccion(1L);
			jurisdiccion.setDescripcion("San Juan");
			
			logger.info("Empresa entro ");
			
			Empresa empresaEnc = empresaService.buscarEmpresaPorCuit(tributo);
			
			logger.info("Empresa buscar ");
			
			sucEmpresa.setDescripcion(titular);
			sucEmpresa.setAutonomo(null);
			sucEmpresa.setActividadSucursal(null);
			empresa.setCuit(new Long(tributo));
			empresa.setRazonSocial(titular);
			empresa.setEsRiesgoza('N');
			empresa.setEnviarMail('S');
			empresa.setGenerarPDF('N');
			empresa.setImprimirLiquidacion('N');
			empresa.setTamEmpresa(tamEmpresa);
			empresa.setTipoLiquidacion(2L);
			empresa.setDomicilioLegal(null);
			empresa.setRubro(null);
			empresa.setSucEmpresa(null);
			sucEmpresa.setEmpresa(empresa);
			empresa.setSucEmpresas(new HashSet());
			empresa.getSucEmpresas().add(sucEmpresa);
			
			logger.info("Empresa buscar1 ");
			
			if (empresaEnc == null) {
				empresaService.grabarEmpresa(empresa);
				comercioFormaPago.setEmpresa(empresa);
				logger.info("nueva empresa "+ sucEmpresa.getIdSucEmpresa());
				
				
			} else {
			
				sucEmpresa.setEmpresa(empresaEnc);
				sucEmpresaService.grabarSucEmpresa(sucEmpresa);
				comercioFormaPago.setEmpresa(empresaEnc);
				logger.info("modificar empresa "+ sucEmpresa.getIdSucEmpresa());	
				
			}
			
			
			//empresa.setSucEmpresa(sucEmpresa);			
			
				
			
			
			comercioFormaPago.setCodComercio(null);
			
			

			codComercio.setSucEmpresa(sucEmpresa);
			codComercio.setEstado("H");
			codComercio.setExcluyeCargoPago("S");
			//codComercio.setIdSucEmpresa(2L);
			codComercio.setCodigoPosnet(codComercioService.sequenciaCodigoPosnet());
			codComercio.setJurisdiccion(jurisdiccion);
			codComercio.setSucursalFiel(null);
			codComercio.setDebitoAutomatico('N');
			codComercio.setPresentaCupones('S');
			codComercio.setComercioFormaPagoSet(new HashSet());
			codComercio.getComercioFormaPagoSet().add(comercioFormaPago);
			codComercioService.grabarCodComercio(codComercio);
			logger.info("codComercio.getIdCodComercio() "+ codComercio.getIdCodComercio());
			
				response.setStatus(200);
				response.getWriter().println("{\"result\":\"" + codComercio.getIdCodComercio() + "\"}");			
			

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			response.getWriter().println("{\"result\": \"Error\"}");
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



