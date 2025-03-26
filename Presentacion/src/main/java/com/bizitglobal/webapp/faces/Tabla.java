package com.bizitglobal.webapp.faces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.bizitglobal.webapp.faces.util.Session;


public class Tabla {
	private static Logger log = Logger.getLogger(Tabla.class);
	private List tabla = new ArrayList();
	private Date date;


	/** Creates a new instance of Tabla */
	public Tabla() {
		log.info("Construyendo el bean de clientes.");

		date = new Date();
		tabla.add(new Cliente(1, "aaa", "bbb", "ccc", "ddd"));
		tabla.add(new Cliente(2, "AAA", "BBB", "CCC", "DDD"));
		tabla.add(new Cliente(3, "aaa", "bbb", "ccc", "ddd"));
		tabla.add(new Cliente(4, "AAA", "BBB", "CCC", "DDD"));
		tabla.add(new Cliente(5, "aaa", "bbb", "ccc", "ddd"));
		tabla.add(new Cliente(6, "AAA", "BBB", "CCC", "DDD"));
		tabla.add(new Cliente(7, "aaa", "bbb", "ccc", "ddd"));
		tabla.add(new Cliente(8, "AAA", "BBB", "CCC", "DDD"));
		tabla.add(new Cliente(9, "aaa", "bbb", "ccc", "ddd"));
		tabla.add(new Cliente(10, "AAA", "BBB", "CCC", "DDD"));
		tabla.add(new Cliente(11, "aaa", "bbb", "ccc", "ddd"));
		tabla.add(new Cliente(12, "AAA", "BBB", "CCC", "DDD"));
	}


	public List getTabla() {
		return tabla;
	}


	public void setTabla(List unaTabla) {
		tabla = unaTabla;
	}


	public String aceptar() {
		Vector check = Session.getCheckboxVector("seleccionar");

		for (int i = 0; i < check.size(); i++) {
			log.info("Valores = " + check.get(i));
		}

		return null;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

}
