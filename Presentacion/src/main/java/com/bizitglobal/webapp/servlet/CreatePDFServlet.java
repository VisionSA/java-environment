package com.bizitglobal.webapp.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;

import java.io.*;
import java.util.Calendar;


public class CreatePDFServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		// doGet(req, resp);
		resp.setContentType("application/x-java-serialized-object");
		ServletContext sc = this.getServletContext();
		try
		{

			Calendar calendar = Calendar.getInstance();
			int mes1 = calendar.get(Calendar.MONTH) + 1;
			String mes = ("00" + mes1).substring(("00" + mes1).length() - 2);
			String fileName = calendar.get(Calendar.YEAR) + "" + File.separator + mes + File.separator + calendar.getTime().getTime() + "-"
					+ "itexttest.pdf";

			// String fileName = "itexttest.pdf";
			String webAppHome = System.getProperty("catalina.home") + File.separator + "webapps";
			PropertieFile propertieFile = new PropertieFile(webAppHome + File.separator + "contexto.properties");
			String pathArchivos = webAppHome + propertieFile.getProperties("directorio.proveedores");

			// String path = sc.getRealPath(File.separator)+fileName;
			// String path = sc.getRealPath(File.separator)+fileName;

			// f = new File(key +"/" + prop.getProperties("directorioArchivos") + "/" + "FAC3377."+fechaArchivo);
			File dir = new File(pathArchivos + File.separator + calendar.get(Calendar.YEAR) + "" + File.separator + mes);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File yourFile = new File(pathArchivos + File.separator + fileName);
			FileOutputStream toFile = new FileOutputStream(yourFile);
			DataInputStream fromClient = new DataInputStream(req.getInputStream());

			byte[] buff = new byte[1024];
			int cnt = 0;
			while ((cnt = fromClient.read(buff)) > -1) {
				toFile.write(buff, 0, cnt);
			}
			toFile.flush();
			toFile.close();
			fromClient.close();

			// Calendar calendar = Calendar.getInstance();
			// String nameImage = calendar.getTime().getTime() + "-" + fileName;

			OutputStream outputStream = resp.getOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(fileName);
			objectOutputStream.flush();
			objectOutputStream.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		doPost(req, resp);
		// int i = 0;
		// int k = 0;
		// int maxLength = req.getContentLength();
		// byte[] bytes = new byte[maxLength];
		// String method = req.getParameter("method");
		// String name = req.getParameter("name");
		// ServletInputStream si = req.getInputStream();
		//
		// String webAppHome = System.getProperty("catalina.home") + File.separator + "webapps";
		// PropertieFile propertieFile = new PropertieFile(webAppHome + File.separator + "contexto.properties");
		// String pathArchivos = webAppHome + propertieFile.getProperties("directorio.reclamo");
		//
		// while (true)
		// {
		// k = si.read(bytes,i,maxLength);
		// i += k;
		// if (k <= 0)
		// break;
		// }
		// if (bytes != null)
		// {
		// ServletOutputStream stream = resp.getOutputStream();
		// resp.setContentType("application/pdf");
		// resp.setContentLength(bytes.length);
		// resp.setHeader("Content-Disposition",method + ";filename=" + pathArchivos+ File.separator +name);
		// stream.write(bytes);
		// stream.flush();
		// stream.close();
		// }
		// else
		// {
		// resp.setContentType("text");
		// resp.getWriter().write("bytes is null");
		// }
	}
}