package org.java.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JavaManServlet_01 extends HttpServlet implements Serializable{

	private static final long serialVersionUID = -6804061006800531987L;

	
	public void init( ServletConfig configuracion ) throws ServletException{
		super.init( configuracion );
	}
	
    /**
     * procesarServlet ...
     */ 
	public void procesarServlet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, ClassNotFoundException{ 
		
		System.out.println(" DENTRO DE 'procesarServlet' ");	
		
		String tipoConexion   = (String)request.getParameter( "tipoConexion" );
		String nombreApplet   = (String)request.getParameter( "nombreApplet" );
		String apellidoApplet = (String)request.getParameter( "apellidoApplet" );
		String codigoApplet   = (String)request.getParameter( "codigoApplet" );
		
		System.out.println( "" );
		System.out.println( "Tipo CONEXION: "            + tipoConexion );
		System.out.println( "NombreApplet 'SERVLET': "   + nombreApplet );
		System.out.println( "ApellidoApplet 'SERVLET': " + apellidoApplet );
		System.out.println( "CodigoApplet 'SERVLET': "   + codigoApplet ); 
		System.out.println( "" );
		
		String nombreValidacion = ( nombreApplet + " " + apellidoApplet );
        
		//Opcion de Conexion 'HttpText'.
		if( tipoConexion.equalsIgnoreCase( "HttpText" ) ){
			PrintWriter salida = response.getWriter();
			
			//----- Aqui aplicar acciones en el 'Servidor' y regresar lo deseado al 'Applet' -----// 
			//this.guardarFoto();
			//------------------------------------------------------------------------------------//
			
			//Reenviando Parametro de Respuesta.
			salida.println( nombreValidacion );			
		}
		//Opcion de Conexion 'HttpText'.
		if( tipoConexion.equalsIgnoreCase( "HttpTextII" ) ){
			
			response.setContentType( "image/jpeg" );
			
			//----- Aqui aplicar acciones en el 'Servidor' y regresar lo deseado al 'Applet' -----// 
			BufferedImage imagen = new BufferedImage( 100, 100, BufferedImage.TYPE_INT_RGB );

			//Hacemos el Dibujo
			Graphics grafico = imagen.getGraphics();
			grafico.setColor( Color.red );
			grafico.fillRect( 50, 50, 100, 100 );
			grafico.setColor( Color.green );
			grafico.fillRect( 0, 0, 50, 50 );
			grafico.setColor( Color.yellow );
			grafico.fillOval( 25, 25, 50, 50 );    	    	
			
			System.out.println( "Imagen: " + imagen );	
			
			SerializableBufferedImage serializaImagen = new SerializableBufferedImage();		    		
			byte[] imagenSerializada = serializaImagen.toByteArray( imagen );
			//------------------------------------------------------------------------------------//
			
			//Reenviando Parametro de Respuesta.
	        ServletOutputStream  respuesta = response.getOutputStream();
	        respuesta.write( imagenSerializada );		
		}		
		//Opcion de Conexion 'HttpObject'.
		else if( tipoConexion.equalsIgnoreCase( "HttpObject" ) ){
			ObjectOutputStream salida = new ObjectOutputStream( response.getOutputStream() );
			
			//----- Aqui aplicar acciones en el 'Servidor' y regresar lo deseado al 'Applet' -----// 
			this.guardarFoto();
			//------------------------------------------------------------------------------------//
			
			//Reenviando Parametro de Respuesta.
			salida.writeObject( nombreValidacion );
		}
		else if( tipoConexion.equalsIgnoreCase( "HttpObjectII" ) ){
			
			response.setContentType( "application/x-java-serialized-object");
						
			//Obtiene datos Serializados.
			InputStream        entrada    = request.getInputStream();
            ObjectInputStream  objEntrada = new ObjectInputStream( entrada );			
			
            Integer        codigo    = (Integer)objEntrada.readObject();
            String         nombre    = (String)objEntrada.readObject();
            Date           fecha     = (Date)objEntrada.readObject();
            Vector<String> listEnvioApplet = (Vector<String>)objEntrada.readObject(); 

			System.out.println( "ObjDesealizado 'Codigo': "    + codigo    );
			System.out.println( "ObjDesealizado 'nombre': "    + nombre    );
			System.out.println( "ObjDesealizado 'Fecha': "     + fecha     );
			System.out.println( "ObjDesealizado 'listaTemp': " + listEnvioApplet );
        
			//----- Aqui aplicar acciones en el 'Servidor' y regresar lo deseado al 'Applet' -----// 
			//this.guardarFoto();
			//------------------------------------------------------------------------------------//
						
			//Manda al Applet.
			OutputStream        salida       = response.getOutputStream();
			ObjectOutputStream  objetoSalida = new ObjectOutputStream( salida );
			
			objetoSalida.writeObject( listEnvioApplet );
			objetoSalida.flush();
			objetoSalida.close();
		}
	}
	
	/**
     * guardarFoto
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
        String  directorioDestino  =  "C:\\FOTOS\\MyFoto.jpg";
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
     * doGet ...
     */ 
	public void doGet( HttpServletRequest request, HttpServletResponse response ){
		System.out.println(" DENTRO DE 'doGet' ");
		try{
		    this.procesarServlet( request, response );
		}
		catch( Exception e ){
			e.printStackTrace();
		}
	}
	
    /**
     * doPost ...
     */ 
	public void doPost( HttpServletRequest request, HttpServletResponse response ){
		System.out.println(" DENTRO DE 'doPost' ");
		try{
		    this.procesarServlet( request, response );
		}
		catch( Exception e ){
			e.printStackTrace();
		}
	}
	
    /**
     * destroy ...
     */ 
	public void destroy(){
		super.destroy();
	}
	
 }
