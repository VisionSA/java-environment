package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoFondos;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoItem;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoDetalleGen;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;

public class PersistenciaCuentaUnica {
	
	private static Logger logger = Logger.getLogger(PersistenciaCuentaUnica.class);
	
	public PersistenciaCuentaUnica(String codigoConceptoFondo, Movimiento movimiento,
			HibernateTemplate hibernateTemplate) {
		inserarAsientoCuentaUnica(codigoConceptoFondo, movimiento, hibernateTemplate);
	}

	public void inserarAsientoCuentaUnica(final String codigoConceptoFondo, Movimiento movimiento, HibernateTemplate hibernateTemplate) {
		
		final ConceptoGen conceptoGen = (ConceptoGen)
		hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				String hql = "Select obj From ConceptoGen obj Where obj.codigoConcepto = " + codigoConceptoFondo;
				Query query = arg0.createQuery(hql);
				List<ConceptoGen> list = query.list();
				return list.get(0);
			}
		});
		
		if(conceptoGen != null){
			
			Object [] conceptoDetalleGens = conceptoGen.getConceptoDetalleSet().toArray();
			ConceptoDetalleGen conceptoDetalleGen = null;
			for(Object con : conceptoDetalleGens){
				if(((ConceptoDetalleGen)con).getOrden() == 0){
					conceptoDetalleGen = ((ConceptoDetalleGen)con);						
				}
			}
			
			if(conceptoDetalleGen != null){
				PlanCuentaDos planCuentaDos = (PlanCuentaDos)hibernateTemplate.get(PlanCuentaDos.class,conceptoDetalleGen.getCtacontable());
				
				Object [] movimientoMPs = movimiento.getMovimientosMP().toArray();				
				AsientoFondos asiento = ((MovimientoMP)movimientoMPs[0]).getAsientoItem().getAsiento();
				asiento.setFecha(new Date());
				AsientoItem asientoItem = new AsientoItem();
				asientoItem.setAsiento(asiento);
				asientoItem.setNroRenglon(1);
				asientoItem.setImporte(movimiento.getImporte());
				asientoItem.setLeyenda(planCuentaDos.getTitulo());
				asientoItem.setIdPlanCuenta(planCuentaDos.getIdPlanCuenta());
				asientoItem.setSigno(conceptoDetalleGen.getSigno());
				
				hibernateTemplate.save(asientoItem);
			}
		}
					
	}	
	
}
