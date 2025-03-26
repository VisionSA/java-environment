package com.bizitglobal.webapp.faces.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import weborb.util.Paths;

import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;


public class Contexto implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}


	public void contextInitialized(ServletContextEvent arg0) {
		final Logger log = Logger.getLogger(Contexto.class);
		String key;
		key = "catalina.home";
		key = System.getProperty(key);
		log.info("Estamos parados en el direcctorio: ");
		log.info(key + "/webapps/contexto.properties");
		String appString = Paths.getWebORBPath();
		appString = appString.replace("classes/", "");
		PropertieFile prop = new PropertieFile(key
				+ "/webapps/contexto.properties");

		// aca creo el archivo xml

		Element root = new Element("beans");
		// Creamos un hijo para el root
		Element articulo = new Element("bean");
		articulo.setAttribute(new Attribute("id", "dataSource"));
		articulo.setAttribute(new Attribute("class",
				"org.apache.commons.dbcp.BasicDataSource"));
		articulo.setAttribute(new Attribute("destroy-method", "close"));

		Element artiHijoUno = new Element("hijoUno");
		artiHijoUno.setName("property");
		artiHijoUno.setAttribute("name", "driverClassName");
		Element val = new Element("value");
		try {
			val.setText(prop.getProperties("driverClassName"));
		} catch (IOException e4) {
			e4.printStackTrace();
		}
		artiHijoUno.addContent(val);

		Element artiHijoDos = new Element("hijoDos");
		artiHijoDos.setName("property");
		artiHijoDos.setAttribute("name", "url");
		Element valDos = new Element("value");
		try {
			valDos.setText(prop.getProperties("url"));
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		artiHijoDos.addContent(valDos);

		Element artiHijoTres = new Element("hijoTres");
		artiHijoTres.setName("property");
		artiHijoTres.setAttribute("name", "username");
		Element valTres = new Element("value");
		try {
			valTres.setText(prop.getProperties("username"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		artiHijoTres.addContent(valTres);

		Element artiHijoCuatro = new Element("hijoCuatro");
		artiHijoCuatro.setName("property");
		artiHijoCuatro.setAttribute("name", "password");
		Element valCuatro = new Element("value");
		try {
			valCuatro.setText(prop.getProperties("password"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		artiHijoCuatro.addContent(valCuatro);

		Element artiHijoCinco = new Element("hijoCinco");
		artiHijoCinco.setName("property");
		artiHijoCinco.setAttribute("name", "initialSize");
		Element valCinco = new Element("value");
		try {
			valCinco.setText(prop.getProperties("initialSize"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		artiHijoCinco.addContent(valCinco);

		Element artiHijoSeis = new Element("hijoSeis");
		artiHijoSeis.setName("property");
		artiHijoSeis.setAttribute("name", "maxActive");
		Element valSeis = new Element("value");
		try {
			valSeis.setText(prop.getProperties("maxActive"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		artiHijoSeis.addContent(valSeis);

		Element artiHijoSiete = new Element("hijoSiete");
		artiHijoSiete.setName("property");
		artiHijoSiete.setAttribute("name", "minIdle");
		Element valSiete = new Element("value");
		try {
			valSiete.setText(prop.getProperties("minIdle"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		artiHijoSiete.addContent(valSiete);

		Element artiHijoOcho = new Element("hijoOcho");
		artiHijoOcho.setName("property");
		artiHijoOcho.setAttribute("name", "maxWait");
		Element valOcho = new Element("value");
		try {
			valOcho.setText(prop.getProperties("maxWait"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		artiHijoOcho.addContent(valOcho);

		Element artiHijoNueve = new Element("hijoNueve");
		artiHijoNueve.setName("property");
		artiHijoNueve.setAttribute("name", "testWhileIdle");
		Element valNueve = new Element("value");
		try {
			valNueve.setText(prop.getProperties("testWhileIdle"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		artiHijoNueve.addContent(valNueve);

		Element artiHijoDiez = new Element("hijoDiez");
		artiHijoDiez.setName("property");
		artiHijoDiez.setAttribute("name", "validationQuery");
		Element valDiez = new Element("value");
		try {
			valDiez.setText(prop.getProperties("validationQuery"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		artiHijoDiez.addContent(valDiez);

		Element artiHijoOnce = new Element("hijoOnce");
		artiHijoOnce.setName("property");
		artiHijoOnce.setAttribute("name", "defaultAutoCommit");
		Element valOnce = new Element("value");
		try {
			valOnce.setText(prop.getProperties("defaultAutoCommit"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		artiHijoOnce.addContent(valOnce);

		articulo.addContent(artiHijoUno);
		articulo.addContent(artiHijoDos);
		articulo.addContent(artiHijoTres);
		articulo.addContent(artiHijoCuatro);
		articulo.addContent(artiHijoCinco);
		articulo.addContent(artiHijoSeis);
		articulo.addContent(artiHijoSiete);
		articulo.addContent(artiHijoOcho);
		articulo.addContent(artiHijoNueve);
		articulo.addContent(artiHijoDiez);
		articulo.addContent(artiHijoOnce);
		root.addContent(articulo);

		Document doc = new Document(root);// Creamos el documento
		doc.setDocType(new DocType("beans", "-//SPRING//DTD BEAN//EN", "http://www.springframework.org/dtd/spring-beans.dtd"));

		// Vamos a almacenarlo en un fichero y ademas lo sacaremos por pantalla
		try {
			XMLOutputter out = new XMLOutputter();
			XMLOutputter outDos = new XMLOutputter();
			File fiUno = new File(appString);
			if (fiUno.exists()) {
				FileOutputStream file = new FileOutputStream(appString + "/WEB-INF/dataSource.xml");
				out.output(doc, file);
				file.flush();
				file.close();
			}
			File fiDos = new File(key + "/webapps/CapaPresentacion/WEB-INF");
			if (fiDos.exists()) {
				FileOutputStream file2 = new FileOutputStream(key
						+ "/webapps/CapaPresentacion/WEB-INF/dataSource.xml");
				outDos.output(doc, file2);
				file2.flush();
				file2.close();
			}
			out.output(doc, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
