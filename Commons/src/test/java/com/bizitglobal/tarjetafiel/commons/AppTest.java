package com.bizitglobal.tarjetafiel.commons;

import java.io.*;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.NumeroALetraConvertidor;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    /**
     * Rigourous Test :-)
     */
    public static void testApp()
    {


//		System.out.println("***************************");
//		System.out.println("***** TEST DE COMMONS *****");
//		System.out.println("***************************");
//		
//		Filtro filtro = new Filtro("nombre", Filtro.LIKE, "Hernan");
//		System.out.println("Con el constructor: " + filtro.getHQL());
//		
//		filtro.agregarCampoOperValor("codigo", Filtro.IGUAL, new Integer(222));
//		System.out.println("Con 1 elemento: " + filtro.getHQL());
//		
//		filtro.agregarCampoOperValor("codigo", Filtro.DISTINTO, new Integer(222));
//		System.out.println("Con 2 elementos: " + filtro.getHQL());
//		
//		filtro.agregarCampoOperValor("codigo", Filtro.MAYOR, new Integer(222));
//		System.out.println("Con 3 elementos: " + filtro.getHQL());
//		
//		filtro.agregarCampoOperValor("codigo", Filtro.MENOR_IGUAL, new Integer(222));
//		System.out.println("Con 4 elementos: " + filtro.getHQL());
//		
//		
//		System.out.println("TODO OK!!!");
    	
    }
    
    public static void main (String[] args)	{

		System.out.println("***************************");
		System.out.println("***** TEST DE COMMONS *****");
		System.out.println("***************************");
		
//		Filtro filtro = new Filtro("nombre", Filtro.LIKE, "Hernan");
//		System.out.println("Con el constructor: " + filtro.getHQL());
//		
//		filtro.agregarCampoOperValor("codigo", Filtro.IGUAL, new Integer(222));
//		System.out.println("Con 1 elemento: " + filtro.getHQL());
//		
//		filtro.agregarCampoOperValor("codigo", Filtro.DISTINTO, new Integer(222));
//		System.out.println("Con 2 elementos: " + filtro.getHQL());
//		
//		filtro.agregarCampoOperValor("codigo", Filtro.MAYOR, new Integer(222));
//		System.out.println("Con 3 elementos: " + filtro.getHQL());
//		
//		filtro.agregarCampoOperValor("codigo", Filtro.MENOR_IGUAL, new Integer(222));
//		System.out.println("Con 4 elementos: " + filtro.getHQL());
//		
//		filtro.agregarOrderBy("codigo");
//		
//		System.out.println("Con ORDER BY: " + filtro.getSQL());
//		
//		System.out.println("TODO OK!!!");
		double d = 9273405;
		System.out.println("Numero "+ d +": " + NumeroALetraConvertidor.convertNumberToLetter(d));
		
	}
}
