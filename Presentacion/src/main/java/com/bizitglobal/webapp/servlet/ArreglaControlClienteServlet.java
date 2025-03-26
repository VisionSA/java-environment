package com.bizitglobal.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.planificadoremail.service.ArreglaControlClientesService;


public class ArreglaControlClienteServlet extends HttpServlet implements javax.servlet.Servlet {
	/***** @Id:I6958 ******/
	private static final long serialVersionUID = -9166416935188731214L;

	private static final String ENVIO_EMAIL_SERVICE_NAME = "envioEmailService";
	private static final String PLAN_PROCESO_SERVICE_NAME = "planProcesoEmailService";
	private ArreglaControlClientesService arrelaClienteService = null;


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ArreglaControlClienteServlet() {
		super();
	}


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}


	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = null;

		try {

			ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			arrelaClienteService = (ArreglaControlClientesService) appContext.getBean("arrelaClienteService");

			arrelaClienteService.arreglaControlClientes();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

	}

}
