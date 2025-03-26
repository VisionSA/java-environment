package org.java.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.java.bean.Usuario;

/**
 * En esta clase estoy mostrando el manejo de Serializacion y Deserealizaciòn
 * de datos utilizando un Objeto Bean de apoyo.
 *
 * @version 1.2 - 29 Marzo 2009.
 * @author 	Ricardo Guerra.
 */

public class Serializando_Deserealizando_Objetos_03 implements Serializable{

	private static final long serialVersionUID = -7282739136711013954L;

	
	public static void main( String[] args ){
		
		List<Usuario> listUsuarios = new ArrayList<Usuario>();
		
		Usuario usuario1 = new Usuario();
		usuario1.setNombres(   "Ricardo" );
		usuario1.setApellidos( "Guerra" );
		
		Usuario usuario2 = new Usuario();
		usuario2.setNombres(   "Catherine" );
		usuario2.setApellidos( "Cotrina" );
		
		listUsuarios.add( usuario1 );		
		listUsuarios.add( usuario2 );
		
		try{
			Serializando_Deserealizando_Objetos_03 serializador = new Serializando_Deserealizando_Objetos_03();
			serializador.serealizandoObjeto(   usuario1 );
			serializador.deserealizandoObjeto( usuario1 );
			serializador.serealizandoListaObjetos( listUsuarios );
			serializador.deserealizandoObjetoListaObjetos( listUsuarios );
		} 
		catch( Exception e ){
			e.printStackTrace();
		}
	}	
	
	public void serealizandoObjeto( Usuario usuario ) throws IOException{
		
		FileOutputStream   stream = new FileOutputStream( "ObjetoSerialTemp" ); 
		ObjectOutputStream salida = new ObjectOutputStream( stream ); 
		
		salida.writeObject( usuario ); 
		salida.close();
		
		System.out.println( "Objeto Serialiado" );
	}
	
	public void deserealizandoObjeto( Usuario usuario ) throws IOException, ClassNotFoundException{
		
		FileInputStream   entrada = new FileInputStream( "ObjetoSerialTemp" ); 
		ObjectInputStream salida  = new ObjectInputStream( entrada ); 
		Usuario user = (Usuario)salida.readObject(); 
		
		System.out.println( user.getNombres() ); 
		
		salida.close();   
	}
	
	public void serealizandoListaObjetos( List<Usuario> listUsuario ) throws IOException{
		
		FileOutputStream   stream = new FileOutputStream( "ObjetoSerialTemp" ); 
		ObjectOutputStream salida = new ObjectOutputStream( stream ); 
		
		salida.writeObject( listUsuario ); 
		salida.close();
		
		System.out.println( "Objeto Serialiado" );
	}
	
	public void deserealizandoObjetoListaObjetos( List<Usuario> listUsuario ) throws IOException, ClassNotFoundException{
		
		FileInputStream   entrada = new FileInputStream( "ObjetoSerialTemp" ); 
		ObjectInputStream salida  = new ObjectInputStream( entrada ); 
		
		List<Usuario> lista = (List<Usuario>)salida.readObject(); 
		
		for( int i=0; i<lista.size(); i++ ){
			 Usuario user = (Usuario)listUsuario.get( i );
			 System.out.println( user.getNombres() );
			 System.out.println( user.getApellidos() );
		}
	
		salida.close(); 
	}
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}

}
