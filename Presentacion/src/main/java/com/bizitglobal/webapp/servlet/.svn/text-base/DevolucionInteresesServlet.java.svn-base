package com.bizitglobal.webapp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.transacciones.service.CtaCteClienteService;
import com.visionis.transaccionador.service.TransaccionService;

public class DevolucionInteresesServlet extends HttpServlet implements
javax.servlet.Servlet{
private static final long serialVersionUID = 1L;
    
	private static final Logger log = Logger.getLogger(DevolucionInteresesServlet.class);
	private FileWriter fw = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DevolucionInteresesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
			ApplicationContext appContext = WebApplicationContextUtils
					.getRequiredWebApplicationContext(getServletContext());
			
			
			//TESTEO DE ANULACION DE VENTAS (nada que ver aqui, pero es lo que hay jejejeje)
			/*
			List<String> params = new ArrayList<String>();
			
			params.add("1"); // id origen
			params.add("0032"); // nro cupon
			params.add("861"); // tipo registro
			params.add("5049063383558582"); // nor tatrjeta
			params.add("03"); // cantidad cuotas
			params.add("000000906220"); // importe
			params.add(""); // codigo autorizacion
			params.add("032"); // codigo moneda
			params.add("131317"); // hora real
			params.add("1"); // utiliza pin
			params.add("0035"); // nro cupon original
			params.add("00"); // plan cuotas
			params.add("0"); // tipo cuota
			params.add("3"); // forma ingreso tarjeta
			params.add("00202150200020030103013201600000000000100000000020320000504906460402618500022030 01        00000000031609999          02843410301020160115049064604026185=2203                390479190017020000000000031210301020160000000312");
			params.add("000000000316"); // rnn
			params.add("23542319"); // terminal
			params.add("031907"); // trace
			params.add("0014"); // lote terminal
			params.add("906"); // cvv
			params.add("002"); // version
			params.add("000000000157"); // rnn original
			
			
			Timestamp f1 = new Timestamp(new Date().getTime());
			Timestamp f2 = new Timestamp(new Date().getTime());
			Timestamp f3 = new Timestamp(new Date().getTime());
			
			TransaccionService transaccionService = (TransaccionService) appContext.getBean("transaccionServiceTarget1");
			transaccionService.devolucionTransaccion(params, f1,f2,f3);
			*/
			
			
			CtaCteClienteService ctaCteClienteService = (CtaCteClienteService) appContext
					.getBean("ctaCteClienteService");
			
			int cantDias = Integer.parseInt(request.getParameter("dias"));
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, - cantDias);
			Date fecha = calendar.getTime();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String fechaPagos = format.format(fecha);
			
			String archivo = ctaCteClienteService.devolverIntereses(fechaPagos);
			
			log.info("Finalizó el proceso de devolución de intereses!");
			
			try {
				File f = null;
				try {
					f = new File(System.getProperty("catalina.home") + File.separator
							+ "/webapps/archivos/" + "/DevolucionIntereses/dev_intereses_" + fechaPagos + ".txt");
					f.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				fw = new FileWriter(System.getProperty("catalina.home") + File.separator
						+ "/webapps/archivos/" + "/DevolucionIntereses/dev_intereses_" + fechaPagos + ".txt");
				fw.write(archivo);
				fw.close();
				
			} catch (Exception e) {
				e.getMessage();
			}
			
		}
		catch (Exception e) {
			log.info("Ocurrió un error en el proceso de devolución de intereses.");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
}
