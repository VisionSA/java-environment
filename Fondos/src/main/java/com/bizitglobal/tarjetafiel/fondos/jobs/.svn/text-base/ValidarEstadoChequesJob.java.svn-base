package com.bizitglobal.tarjetafiel.fondos.jobs;

import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.ui.web.QuartzAppContext;

import org.springframework.context.ApplicationContext;
import com.bizitglobal.tarjetafiel.fondos.service.ChequeHistorialService;

public class ValidarEstadoChequesJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Calendar hoy = Calendar.getInstance();
//		Calendar cal = Calendar.getInstance();
//		cal.set(2009, Calendar.NOVEMBER, 23);
//		ApplicationContext app = QuartzAppContext.getInstance().getApplicationContext();
//		ChequeHistorialService chequeHistorialService = (ChequeHistorialService)app.getBean("chequeHistorialService");
//		while (cal.compareTo(hoy) < 0) {
//			chequeHistorialService.validarEstadoChequesPropios(cal.getTime());
//			cal.add(Calendar.DATE,1);
//		}
		// codigo a reestablecer
		  ApplicationContext app = QuartzAppContext.getInstance().getApplicationContext();
		  ChequeHistorialService chequeHistorialService = (ChequeHistorialService)app.getBean("chequeHistorialService");
		  chequeHistorialService.validarEstadoChequesPropios(hoy.getTime()); 
		 
	}

}
