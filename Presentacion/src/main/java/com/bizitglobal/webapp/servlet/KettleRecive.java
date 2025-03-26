package com.bizitglobal.webapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.workflow.service.DetalleTramiteService;


/**
 * Servlet implementation class for Servlet: KettleRecive
 * 
 */
public class KettleRecive extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = -9162216935178731214L;

	private static final String DETALLE_TRAMITE_SERVICE_NAME = "detalleTramiteService";
	private DetalleTramiteService detalleTramiteService = null;


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public KettleRecive() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String result = null;

		try {
			out.print("Test!!!<br>");

			ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			detalleTramiteService = (DetalleTramiteService) appContext.getBean(DETALLE_TRAMITE_SERVICE_NAME);

			if (request.getParameter("idDetalleTramite") != null) {
				Long idDetalleTramite = new Long(request.getParameter("idDetalleTramite"));
				int finOk = (detalleTramiteService.finalizarDetalleTramite(idDetalleTramite)) ? 1 : 0;
				result = "Result: <b>" + finOk + "</b>";
			} else {
				result = "Result: <b>0</b>";
			}

			out.print(result);
		} catch (Exception e) {
			out.print("Error Exception!!!");
		} finally {
			out.close();
		}

	}

}