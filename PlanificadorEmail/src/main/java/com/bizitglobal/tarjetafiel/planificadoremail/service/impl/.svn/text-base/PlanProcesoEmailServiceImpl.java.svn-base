package com.bizitglobal.tarjetafiel.planificadoremail.service.impl;

import java.util.List;

import com.bizitglobal.tarjetafiel.planificadoremail.dao.PlanProcesoEmailDao;
import com.bizitglobal.tarjetafiel.planificadoremail.exception.ImagenEmailException;
import com.bizitglobal.tarjetafiel.planificadoremail.exception.PlanProcesoEmailException;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.ImagenEmail;
import com.bizitglobal.tarjetafiel.planificadoremail.service.PlanProcesoEmailService;


/***** @Id:6958 ******/
public class PlanProcesoEmailServiceImpl implements PlanProcesoEmailService {

	private PlanProcesoEmailDao planProcesoEmailDao;


	@Override
	public List findAll() throws PlanProcesoEmailException {
		// TODO Auto-generated method stub
		return planProcesoEmailDao.findAll();
	}


	public PlanProcesoEmailDao getPlanProcesoEmailDao() {
		return planProcesoEmailDao;
	}


	public void setPlanProcesoEmailDao(PlanProcesoEmailDao planProcesoEmailDao) {
		this.planProcesoEmailDao = planProcesoEmailDao;
	}


	public Boolean grabarImagenEmail(ImagenEmail imagenEmail) throws ImagenEmailException {
		Boolean resultado = true;

		planProcesoEmailDao.grabarImagenEmail(imagenEmail);
		return resultado;
	}

}
