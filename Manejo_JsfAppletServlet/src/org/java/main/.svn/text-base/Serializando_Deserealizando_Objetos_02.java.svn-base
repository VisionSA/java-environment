package org.java.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import org.java.bean.BeanSerializable;

/**
 * En esta clase estoy mostrando el manejo de Serializacion y Deserealizaciòn
 * de datos utilizando un Objeto Bean de apoyo.
 *
 * @version 1.2 - 29 Marzo 2009.
 * @author 	Ricardo Guerra.
 */

public class Serializando_Deserealizando_Objetos_02 implements Serializable{

	private static final long serialVersionUID = -7282739136711013954L;

	
	public static void main( String[] args ){
		
		try{
			BeanSerializable objeto = new BeanSerializable();
			
			/***************************************/
			/**** ENVIAN OBJETOS 'Serializados' ****/
			/***************************************/
			FileOutputStream salida    = new FileOutputStream( "objTemporal" );  //objTemporal, reconoce el nombre del Objeto de la Memoria.
			ObjectOutput     objSalida = new ObjectOutputStream(salida);
			
			objeto.setCodigo( 123456 );
			objeto.setNombre( "Cesar Ricardo" );
			objeto.setApellido( "Guerra Arnaiz" );
			objeto.setFecha(  new Date() );
			
			objSalida.writeObject( objeto );
			
			objSalida.flush();
			objSalida.close();

			
			/********************************************/
			/**** RECUPERAN OBJETOS 'Deserializados' ****/
			/********************************************/
			FileInputStream   entrada    = new FileInputStream( "objTemporal" );  //objTemporal, reconoce el nombre del Objeto de la Memoria.  
			ObjectInputStream objEntrada = new ObjectInputStream( entrada );
			
			BeanSerializable objetoBean = (BeanSerializable)objEntrada.readObject();
			
			System.out.println( "Objeto: " + objetoBean );
			
			if( objetoBean instanceof BeanSerializable ){		            
            	System.out.println( "Si es Instanciado al Objeto" );
            }
            else{
            	System.out.println( "No es Instanciado al Objeto" );
            }
			
			objEntrada.close();		
			
			System.out.println( "Objeto Deserealizado #1: " + objetoBean.getCodigo()   );
			System.out.println( "Objeto Deserealizado #2: " + objetoBean.getNombre()   );
			System.out.println( "Objeto Deserealizado #3: " + objetoBean.getApellido() );
			System.out.println( "Objeto Deserealizado #4: " + objetoBean.getFecha()    );			
		} 
		catch( Exception e ){
			e.printStackTrace();
		}
	}	
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}

}
