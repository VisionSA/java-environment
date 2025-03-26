package com.bizitglobal.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.transacciones.service.RegenerarMovimientoCtaCteTransaccionService;


public class TransaccionadorServlet extends HttpServlet implements javax.servlet.Servlet {

	private static final Logger log = Logger.getLogger(CobranzasServlet.class);


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		RegenerarMovimientoCtaCteTransaccionService regenerarMovimientoCtaCteTransaccionService = (RegenerarMovimientoCtaCteTransaccionService) appContext
				.getBean("regenerarMovimientoCtaCteTransaccionService");
		regenerarMovimientoCtaCteTransaccionService.regenerarTransaccionesNoImputadas(appContext);

	}

}
