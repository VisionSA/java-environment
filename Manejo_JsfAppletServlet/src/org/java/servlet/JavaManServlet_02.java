package org.java.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class JavaManServlet_02 extends HttpServlet implements Serializable{

	private static final long serialVersionUID = 8423013812334112270L;

	/**
     * procesarServlet ... 
     */	
	public void procesarServlet( HttpServletRequest request, HttpServletResponse response ){
		System.out.println(" ******** DENTRO DE 'procesarServlet' ******** ");
		
		try{
			response.setContentType( "application/x-java-serialized-object");

			//----- Aqui aplicar acciones en el 'Servidor' y regresar lo deseado al 'Applet' -----// 
			//this.guardarFoto();
			//------------------------------------------------------------------------------------//
					
			InputStream        entrada       = request.getInputStream();
			ObjectInputStream  objetoEntrada = new ObjectInputStream( entrada );
			String             parametros    = (String)objetoEntrada.readObject();

			//Manda al Applet.
			OutputStream        salida       = response.getOutputStream();
			ObjectOutputStream  objetoSalida = new ObjectOutputStream( salida );
			
			objetoSalida.writeObject( parametros );
			objetoSalida.flush();
			objetoSalida.close();
		} 
		catch( Exception e ){
			e.printStackTrace();
		}		
	}	
	
    /**
     * guardarFoto ...
     */ 
    public void guardarFoto() throws IOException{
    	
		BufferedImage imagen = new BufferedImage( 100, 100, BufferedImage.TYPE_INT_RGB);

		//Hacemos el dibujo
		Graphics grafico = imagen.getGraphics();
		grafico.setColor( Color.red );
		grafico.fillRect( 50, 50, 100, 100 );
		grafico.setColor( Color.green );
		grafico.fillRect( 0, 0, 50, 50 );
		grafico.setColor( Color.yellow );
		grafico.fillOval( 25, 25, 50, 50 );
    	    	
        //Guardando en Disco.
        String  directorioDestino  =  "C:\\CACFotos\\MyFoto.jpg";
        File    directorio         =  null;
        String  formato            =  "jpg";
        
		//ITERAMOS EL 'directorio' PARA CADA ARCHIVO ENVIADO.
		directorio = new File( directorioDestino );
				
		if( !(directorio.exists()) ){   
			System.out.println("Existe Directorio: 'NO'...'Se Procede a Crear' ");
			directorio.mkdirs();
		}
		else{
			System.out.println("Existe Directorio: 'SI'...");
		}

		ImageIO.write( imagen, formato, directorio );
    }
    
    /**
     * doPost ...
     */ 
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{		
		System.out.println(" ******** DENTRO DE 'doPost' ******** ");
		this.procesarServlet( request, response );
	}
	
    /**
     * doGet ...
     */ 
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		System.out.println(" ******** DENTRO DE 'doGet' ******** ");
		this.procesarServlet( request, response );
    }

}
