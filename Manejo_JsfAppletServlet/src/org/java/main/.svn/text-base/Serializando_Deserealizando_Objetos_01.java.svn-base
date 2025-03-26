package org.java.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * En esta clase estoy mostrando el manejo de Serializacion y Deserealizaciòn
 * de datos en modo 'HardCode'.
 *
 * @version 1.2 - 29 Marzo 2009.
 * @author 	Ricardo Guerra.
 */

public class Serializando_Deserealizando_Objetos_01 implements Serializable{

	private static final long serialVersionUID = -2071812553599487256L;


	public static void main( String[] args ){
		
		try{			
			/***************************************/
			/**** ENVIAN OBJETOS 'Serializados' ****/
			/***************************************/
			FileOutputStream salida    = new FileOutputStream( "objTemporal" );  //objTemporal, reconoce el nombre del Objeto de la Memoria.
			ObjectOutput     objSalida = new ObjectOutputStream(salida);
			
			objSalida.writeObject( 123456 );
			objSalida.writeObject( "Cesar Ricardo" );
			objSalida.writeObject( "Guerra Arnaiz" );
			objSalida.writeObject( new Date() );
			
			objSalida.flush();
			objSalida.close();

			
			/********************************************/
			/**** RECUPERAN OBJETOS 'Deserializados' ****/
			/********************************************/
			FileInputStream   entrada    = new FileInputStream( "objTemporal" );  //objTemporal, reconoce el nombre del Objeto de la Memoria.  
			ObjectInputStream objEntrada = new ObjectInputStream( entrada );
			
			Integer codigo   = (Integer)objEntrada.readObject();
			String  nombre   = (String)objEntrada.readObject();
			String  apellido = (String)objEntrada.readObject();
			Date    fecha    = (Date)objEntrada.readObject();
			
			objEntrada.close();		
			
			System.out.println( "Objeto Deserealizado #01: " + codigo   );
			System.out.println( "Objeto Deserealizado #02: " + nombre   );
			System.out.println( "Objeto Deserealizado #02: " + apellido );
			System.out.println( "Objeto Deserealizado #03: " + fecha    );
		} 
		catch( Exception e ){
			e.printStackTrace();
		}
	}	
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
