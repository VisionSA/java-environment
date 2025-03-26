package com.bizitglobal.tarjetafiel.commons.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;


public class Fecha {
	private static Logger log = Logger.getLogger(Fecha.class);
	private static Calendar fecha = Calendar.getInstance();
		
	
	public Fecha() {
		this.fecha = Calendar.getInstance();
	}

	public Fecha(Calendar fecha) {
		super();
		this.fecha = fecha;
	}
	
	/**
	 * Suma los dias indicados sin tener en cuenta si es feriado o fin de semana.
	 * @param fechaBase, fecha a partir el calculo.
	 * @param dias, cantidad de dias a sumar.
	 * @return fecha sumada.
	 */

	public static Date addDias(Date fechaBase, int dias) {
		fecha.setTime(fechaBase);
		fecha.add(Calendar.DATE, dias);
		return fecha.getTime();
	}
//	public static Timestamp addDias(Timestamp fechaBase, int dias){
//		return new Timestamp(addDias(fechaBase, dias).getTime());
//	}
	
	/**
	 * @id: 4655
	 * Method: addMeses
	 * Description: Suma los meses indicado a cierta fecha
	 * @param fechaBase
	 * @param meses
	 * @return
	 */
	public static Date addMeses(Date fechaBase, int meses){
		fecha.setTime(fechaBase);
		fecha.add(Calendar.MONTH, meses);
		return fecha.getTime();
	}
	
	public static Calendar addDiasLaborable(Timestamp fechaBase, int dias){
		Calendar nuevaFecha = Calendar.getInstance();
		nuevaFecha.setTime(addDias(fechaBase, dias));
		if (nuevaFecha.get(Calendar.DAY_OF_WEEK) == 1) {
			log.info("Era Domingo");
			nuevaFecha.add(Calendar.DATE, 1);
		}else {
			if (nuevaFecha.get(Calendar.DAY_OF_WEEK) == 7) {
				log.info("Era Sabado");
				nuevaFecha.add(Calendar.DATE, 2);
			}
		}
		return nuevaFecha;
	}
	
	/*
	 * Este metodo se utiliza para calcular la fecha final donde se le pasa la fecha actual
	 * y la cantidad de horas que se debe sumar para calcular la fecha final.
	 */
	public static Calendar calcularFechaFin(Timestamp fechaBase, Long horas){
		fecha.setTime(new Date(fechaBase.getTime()));
		fecha.add(Calendar.HOUR, horas.intValue());		
		return fecha; 
	}

	
	public static int calcularDiferenciaDias(Timestamp unaFecha, Timestamp otraFecha){		
		long time = unaFecha.getTime() - otraFecha.getTime();
		time *= Long.signum(time);
		
		time = time/(3600*24*1000);
		
		return (int) time;			
	}
	
	public static int calcularDiferenciaDias(Date unaFecha, Date otraFecha){		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			unaFecha = simpleDateFormat.parse(simpleDateFormat.format(unaFecha));
			otraFecha = simpleDateFormat.parse(simpleDateFormat.format(otraFecha));
		} catch (ParseException e) {
			log.error(e,e);
		}
		
		long time = unaFecha.getTime() - otraFecha.getTime();
		time *= Long.signum(time);
		 
		Double timeDouble = new Double(time/(3600*24*1000));
		return timeDouble.intValue();		
	}
	
	public static String getNombreMes(){
		fecha = Calendar.getInstance();
		switch (fecha.get(Calendar.MONTH)) {
		case Calendar.JANUARY:
			return "Enero";
		case Calendar.FEBRUARY:
			return "Febrero";
		case Calendar.MARCH:
			return "Marzo";
		case Calendar.APRIL:
			return "Abril";
		case Calendar.MAY:
			return "Mayo";
		case Calendar.JUNE:
			return "Junio";
		case Calendar.JULY:
			return "Julio";
		case Calendar.AUGUST:
			return "Agosto";
		case Calendar.SEPTEMBER:
			return "Septiembre";
		case Calendar.OCTOBER:
			return "Octubre";
		case Calendar.NOVEMBER:
			return "Noviembre";
		case Calendar.DECEMBER:
			return "Diciembre";
		default:
			return null;
		}
		
	}
	
	/**
	 * @id: 4877
	 * Method: getFechaDia
	 * Description: Devuelve el dia actual 
	 * @return
	 */
	public static String getFechaDia(){
		fecha = Calendar.getInstance();
		return String.valueOf(fecha.get(Calendar.DATE));
	}
	
	/**
	 * @id: 4877
	 * Method: getFechaAnio
	 * Description: Devuelve el año actual 
	 * @return
	 */
	public static String getFechaAnio(){
		fecha = Calendar.getInstance();
		return String.valueOf(fecha.get(Calendar.YEAR));
	}
	
	public static String getFechaFormatoEspanol(){
		fecha = Calendar.getInstance();
		return fecha.get(Calendar.DATE)+" de "+getNombreMes()+" de "+fecha.get(Calendar.YEAR);
	}
	
	
	/**
	 * @id: 5248
	 * Method: getFechaPago
	 * Description: devuelve la proxima n-esima fecha de liquidacion si iteracion > 0
	 * o n-esima fecha anterior de cierre si iteracion < 0, o la fecha actual del sistema si iteracion = 0.
	 * Este metodo se deberia utilizar en todo el sistema completo. 
	 * Hay lugares en lo que se detectaron el uso de otro método, por lo que habría que modificarlos.
	 * Liquidación, ver conciliación, transaccionador: consumo y debito, y seguir viendo..... 
	 * @param diaPago
	 * @param iteracion
	 * @return
	 */
	public static Timestamp getFechaPago(int diaPago, int iteracion) {
		Calendar cal= Calendar.getInstance();
		if (iteracion >= 0) {
			for(int i=1;i<=iteracion;i++){
				cal = proximaFechaLiquidacion(diaPago,cal);
				if (i<iteracion) cal.add(Calendar.DATE,1);		//sumo un dia solo si tengo que seguir iterando, por que la funcion auxiliar es inclusiva en la fecha de liq.
			}
		}else{ 
			for(int i=-1;i>=iteracion;i--){
				cal = anteriorFechaLiquidacion(diaPago,cal);
			}
		}
		return new Timestamp(cal.getTime().getTime());


	}
	
	public static Calendar proximaFechaLiquidacion(int diaPago, Calendar cal){
		//funcion auxiliar,devuelve la fecha de liquidacion mas proxima(inclusiva)  con respecto a la fecha cal
		int diaActual = cal.get(Calendar.DAY_OF_MONTH);
		
		if (diaActual > diaPago ){
			cal.add(Calendar.MONTH, 1);
		}
		if(cal.getActualMaximum(Calendar.DAY_OF_MONTH) < diaPago ){
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		}else{
			cal.set(Calendar.DAY_OF_MONTH,diaPago);
		}
		return cal;
	}
	
	private static Calendar anteriorFechaLiquidacion(int diaPago, Calendar cal){
		//funcion auxiliar, devuelve la fecha de liquidacion anterior(exclusiva) con respecto a la fecha cal
		int diaActual = cal.get(Calendar.DAY_OF_MONTH);
	
		if (diaPago < diaActual ){
			cal.set(Calendar.DAY_OF_MONTH, diaPago);
		}else{
			cal.add(Calendar.MONTH, -1);
			if(cal.getActualMaximum(Calendar.DAY_OF_MONTH) < diaPago ){
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			}else{
				cal.set(Calendar.DAY_OF_MONTH,diaPago);
			}
		}
		return cal;
		
	}

}
