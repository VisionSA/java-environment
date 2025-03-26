package com.bizitglobal.tarjetafiel.operador;

import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Context {
	private static ApplicationContext context = null;
	
	public static ApplicationContext getContext() {
		if(context == null) {
			context = new FileSystemXmlApplicationContext("operadorContext.xml");
		}

		return context;
	}
		
}
