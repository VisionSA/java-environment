package org.java.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PruebaManejadores implements Serializable{

	private static final long serialVersionUID = 124669864877088240L;

	
	/***** PRINCIPAL *****/
	public static void main( String[] args ){			
		
		ManejoCalendario  formatCalendar  =  new ManejoCalendario();		
		ManejoNumeros     numeros         =  new ManejoNumeros();
		
		//MANDA A IMPRIMIR
		imprimiendo( formatCalendar, numeros );
	}
	
	/***** IMPRIMIENDO *****/
	public static void imprimiendo( ManejoCalendario formatCalendar, ManejoNumeros numeros ){
		 
		int              diaPruebaInicio   = 10;
		int              mesPruebaInicio   = 3;
		int              anoPruebaInicio   = 2007;
		
		Integer          redondeoAñoFecha  = 1900;     //Redonde de Año.
		Integer          redondeoMesFecha  = 1;        //Redonde de Mes.
		
		Date             fecha             =  new Date();		
		Date             fechaInicio       =  new Date( (anoPruebaInicio - redondeoAñoFecha), (mesPruebaInicio - redondeoMesFecha), diaPruebaInicio );	
		Date             fechaFin          =  new Date();			
		
		//Logger  logger  =  Logger.getLogger( demo_Manejadores.class );
		
		int  			 numInt_01    	   =  10; 
		int    			 numInt_02   	   =  50; 
		double 			 numDouble_01	   =  10.7; 
		double 			 numDouble_02	   =  50.5; 
		long   			 numLong_01  	   =  123456789;
		long   			 numLong_02  	   =  987654321;
		
		
		Calendar fechaInicial = Calendar.getInstance();
		Date fechaComparativa_01 = new Date( (2009 - redondeoAñoFecha), (4 - redondeoMesFecha), 10 );  
		fechaInicial.setTime( fechaComparativa_01 );
		
		Calendar fechaFinal = Calendar.getInstance();
		Date fechaComparativa_02 = new Date( (2009 - redondeoAñoFecha), (8 - redondeoMesFecha), 15 );  
		fechaFinal.setTime( fechaComparativa_02 );
		
		List<Date> listaDias = new ArrayList<Date>();
		listaDias = formatCalendar.getDiasEntreDosFechas( fechaInicial, fechaFinal );
		
		List<Date> listaMeses = new ArrayList<Date>();
		listaMeses = formatCalendar.getMesesEntreDosFechas( fechaInicial, fechaFinal );
		
		Calendar zonaHoraria = formatCalendar.getZonaHoraria( "Default" );   //"Europe/Madrid"
		
		String  formato_01  =  (String)formatCalendar.getFechaFormatDATEconSeparador( fecha, '/');	  //SEPARADOR '/' 	
		String  formato_02  =  (String)formatCalendar.getFechaFormatDATE( fecha);		
		String  formato_03  =  (String)formatCalendar.getFechaFormatDiaMesAnoGuion();
		String  formato_04  =  (String)formatCalendar.getFechaFormatDiaMesAnoLargoEspanolFormato01( fecha );
		String  formato_05  =  (String)formatCalendar.getFechaFormatDATETIME( fecha );
		String  formato_06  =  (String)formatCalendar.getFechaFormatDATETIMEjtable( fecha );
     	String  formato_07  =  (String)formatCalendar.getFechaFormatDiaMesConSeparador( fecha, '+' );  //SEPARADOR '+' 	
		String  formato_08  =  (String)formatCalendar.getFechaFormatMesDiaAnoGuion( fecha );
		String  formato_09  =  (String)formatCalendar.getFechaFormatDiaMesAnoLargoInglesFormato01( fecha );
		
		String  formato_10  =  (String)formatCalendar.getHora( fecha );
		String  formato_11  =  (String)formatCalendar.getHora_actual();
		
		String  formato_12  =  (String)formatCalendar.getNombreDiaEspanol( 6 );  //DEVUELVE EL DIA 6 
		String  formato_13  =  (String)formatCalendar.getNombreMesEspanol( 5 );  //DEVUELVE EL MES 6 
		
		String  formato_14  =  (String)formatCalendar.getNombreDiaIngles( 6 );   //DEVUELVE EL DIA 6 
		String  formato_15  =  (String)formatCalendar.getNombreMesIngles( 6 );   //DEVUELVE EL MES 6 
		
		String  formato_16  =  String.valueOf( formatCalendar.getAno( fecha ) );	
		String  formato_17  =  String.valueOf( formatCalendar.getAnoActual() );	
		
		String  formato_18  =  (String)formatCalendar.getNumHoras();
		
		String  formato_19  =  String.valueOf(formatCalendar.getNumeroDiasEntreDosFechas(  fechaInicio, fechaFin ) );   //SI SON LA MISMA FECHA DEVUELVE "0" 
		String  formato_20  =  String.valueOf(formatCalendar.getNumeroMesesEntreDosFechas( fechaInicio, fechaFin ) );   //SI SON LA MISMA FECHA DEVUELVE "0" 
		String  formato_21  =  String.valueOf(formatCalendar.getNumeroAnosEntreDosFechas(  fechaInicio , fechaFin ) );  //SI SON LA MISMA FECHA DEVUELVE "0" 
		
		String  numero_01   =  String.valueOf( numeros.getAleatorio( numInt_01,     numInt_02 ) );                      //PARA 'int'
		String  numero_02   =  String.valueOf( numeros.getAleatorio( numDouble_01,  numDouble_02 ) );                   //PARA 'double'
		String  numero_03   =  String.valueOf( numeros.getAleatorio( numLong_01,    numLong_02 ) );                     //PARA 'long'
		
		System.out.println(" " );
		System.out.println(" IMPRIMIENDO FORMATOS DE FECHAS" );
		System.out.println(" ******************************" );
		System.out.println(" FORMATO FECHA #1: "                +   formato_01 );
		System.out.println(" FORMATO FECHA #2: "                +   formato_02 );
		System.out.println(" FORMATO FECHA #3: "                +   formato_03 );
		System.out.println(" FORMATO FECHA #4: "                +   formato_04 );
		System.out.println(" FORMATO FECHA #5: "                +   formato_05 );
		System.out.println(" FORMATO FECHA #6: "                +   formato_06 );
		System.out.println(" FORMATO FECHA #7: "                +   formato_07 );
		System.out.println(" FORMATO FECHA #8: "                +   formato_08 );
		System.out.println(" FORMATO FECHA #9: "                +   formato_09 );
		System.out.println(" " );
		System.out.println(" HORA SEGUN FECHA: "                +   formato_10 );
		System.out.println(" HORA ACTUAL: "                     +   formato_11 );
		
		System.out.println(" DIA SEGUN PARAMETRO (Español): "   +   formato_12 );
		System.out.println(" MES SEGUN PARAMETRO (Español): "   +   formato_13 );
		
		System.out.println(" DIA SEGUN PARAMETRO (Ingles): "    +   formato_14 );
		System.out.println(" MES SEGUN PARAMETRO (Ingles): "    +   formato_15 );
				
		System.out.println(" AÑO SEGUN FECHA: "                 +   formato_16 );
		System.out.println(" AÑO ACTUAL: "                      +   formato_17 );
		System.out.println(" FORMATO CALENDARIO: "              +   formato_18 );

		System.out.println(" FECHA #1: "  +   fechaInicio );
		System.out.println(" FECHA #2: "  +   fechaFin    );
		
		System.out.println(" NUMERO DE DIAS  ENTRO 2 FECHAS: "  +   formato_19 );
		System.out.println(" NUMERO DE MESES ENTRO 2 FECHAS: "  +   formato_20 );		
		System.out.println(" NUMERO DE AÑOS  ENTRO 2 FECHAS: "  +   formato_21 );		
		System.out.println(" " );
		System.out.println(" IMPRIMIENDO MANEJADORES DE NUMEROS" );
		System.out.println(" **********************************" );
		System.out.println(" ALETARIO DE 'int': "               +   numero_01 );
		System.out.println(" ALETARIO DE 'double': "            +   numero_02 );
		System.out.println(" ALETARIO DE 'long': "              +   numero_03 );
		System.out.println(" " );
		System.out.println(" **********************************" );	
		System.out.println(" " );	
		System.out.println(" ZONA HORARIA: " + zonaHoraria );	
		System.out.println(" " );	
		for( int i=0; i<listaDias.size(); i++ ){
		     System.out.println( "Obteniendo Dias: " + listaDias.get(i) );	
		}	
		System.out.println(" Tamaño Lista Dias: " + listaDias.size() );		
		System.out.println("");		
		for( int j=0; j<listaMeses.size(); j++ ){
		     System.out.println( "Obteniendo Meses: " + listaMeses.get(j) );	
		}
		System.out.println(" Tamaño Lista Meses: " + listaMeses.size() );
		System.out.println("");
	}
	
 }
