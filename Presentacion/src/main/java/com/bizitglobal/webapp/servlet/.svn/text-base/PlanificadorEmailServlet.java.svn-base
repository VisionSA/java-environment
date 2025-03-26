package com.bizitglobal.webapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.planificadoremail.negocio.PlanProcesoEmail;
import com.bizitglobal.tarjetafiel.planificadoremail.service.EnvioEmailService;
import com.bizitglobal.tarjetafiel.planificadoremail.service.PlanProcesoEmailService;


public class PlanificadorEmailServlet extends HttpServlet implements javax.servlet.Servlet {
	/***** @Id:I6958 ******/
	private static final long serialVersionUID = -9166416935188731214L;

	private static final String ENVIO_EMAIL_SERVICE_NAME = "envioEmailService";
	private static final String PLAN_PROCESO_SERVICE_NAME = "planProcesoEmailService";
	private EnvioEmailService envioEmailService = null;
	private PlanProcesoEmailService planProcesoEmailService = null;


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public PlanificadorEmailServlet() {
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
			envioEmailService = (EnvioEmailService) appContext.getBean(ENVIO_EMAIL_SERVICE_NAME);

			planProcesoEmailService = (PlanProcesoEmailService) appContext.getBean(PLAN_PROCESO_SERVICE_NAME);
			List<PlanProcesoEmail> planes = planProcesoEmailService.findAll();
			for (PlanProcesoEmail planProcesoEmail : planes) {
				envioEmailService.correrPlanEnviarLiqByEmail(planProcesoEmail.getIdPlan());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

	}

}
