package com.bizitglobal.webapp.xml;

import org.apache.log4j.Logger;


public class MenuAdmin {
	private static Menu menu = null;
	private static final Logger log = Logger.getLogger(MenuAdmin.class);


	public static Menu getInstance() {
		if (menu == null) {
			log.info("Generando menu...");
			menu = new Menu();
		}

		return menu;
	}
}
