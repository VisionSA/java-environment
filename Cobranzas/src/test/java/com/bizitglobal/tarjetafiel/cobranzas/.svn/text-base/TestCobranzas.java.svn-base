package com.bizitglobal.tarjetafiel.cobranzas;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.bizitglobal.tarjetafiel.cobranzas.negocio.Accion;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.AccionVersion;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.CambioEstadoMora;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EnvioCarta;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EtapaVersion;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Plan;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.PlanVersion;
import com.bizitglobal.tarjetafiel.cobranzas.service.AccionService;
import com.bizitglobal.tarjetafiel.cobranzas.service.PlanService;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;



@ContextConfiguration
public class TestCobranzas extends AbstractTransactionalJUnit4SpringContextTests  {
	

    @Test
	public void test() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("com/bizitglobal/tarjetafiel/cobranzas/cobranzasTest.xml" );
		
//		Accion acc = new EnvioCarta();
//		acc.setDescripcion("Enviar Carta D");
//		
//		Accion acc2 = new CambioEstadoMora();
//		acc2.setDescripcion("Cambio de m");
//		AccionService accionService = (AccionService)applicationContext.getBean("accionService");
//		System.out.println(accionService);
//		accionService.grabarAccion(acc);
//		accionService.grabarAccion(acc2);
		PlanService planService = (PlanService)applicationContext.getBean("planService");
		AccionService accionService = (AccionService)applicationContext.getBean("accionService");
		List accionesDisponibles = accionService.getAccion(new Filtro());
		AccionVersion accVer = new AccionVersion();
		accVer.setAccion((Accion)accionesDisponibles.get(0));
		accVer.setDias(7);
		
		Plan miPlanDeMora = planService.crearPlan();
		Iterator<PlanVersion> ite = miPlanDeMora.getPlanesVersion().iterator();
		PlanVersion p = null;
		while (ite.hasNext()) {
			p = ite.next();
			break;
		}
		EtapaVersion ev = null;
		Iterator<EtapaVersion> i = p.getEtapasVersion().iterator();
		while (i.hasNext()) {
			ev = i.next();
			break;
		}
		ev.getAccionesVersion().add(accVer);
		accVer.setEtapaVersion(ev);
		try {
			p.validarConsistenciaPlan();
			planService.grabarPlan(miPlanDeMora);
		} catch(Exception e) {
			e.printStackTrace();
		}
		p.armarHashAccionesPlan();
		Set<Integer> claves = p.getAccionesXDia().keySet();
		Iterator<Integer> iteInt = claves.iterator();
		while (iteInt.hasNext()) {
			Integer numDia = iteInt.next();
			List<AccionVersion> accionesAEjecutar = p.getAccionesXDia().get(numDia);
			Iterator<AccionVersion> in = accionesAEjecutar.iterator();
			while (in.hasNext()) {
				AccionVersion a = in.next();
				System.out.println("Dia: "+ numDia +", Accion:" + a.getAccion().getDescripcion() + " de la etapa " + a.getEtapaVersion().getEtapa().getDescripcion());
			}
		}
		planService.borrarPlan(miPlanDeMora);
	}
	
	
	
		
}