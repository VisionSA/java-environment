package org.java.util;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * Autor:  Ricardo Guerra .A.
 * 
 */

public class ManejoImagenes extends Panel implements Serializable{
	
	private static final long serialVersionUID = 9155250341909802458L;
	
	private ManejoConvertidores manejoConvertidores	= null;
	
   /** 
    * Constructor.
    */
	public ManejoImagenes(){
		this.manejoConvertidores = new ManejoConvertidores();
	}
	
    /**
     * redimensionaImagen redimensiona el tamaño de una imagen.
     * @param anchoImagen
     * @param formatoImagen
     * @param rutaImagenEntrada
     * @param rutaImagenSalida
     */
	public void redimensionaImagen( int anchoImagen, String formatoImagen, String rutaImagenEntrada, String rutaImagenSalida ){

		try{
			BufferedImage imagenEntrada = ImageIO.read( new File( rutaImagenEntrada ) );

			int ancho = imagenEntrada.getWidth(); 
			int alto  = imagenEntrada.getHeight();

			BufferedImage imagenSalida = new BufferedImage( (anchoImagen * ancho) / ancho, (anchoImagen * alto) / ancho, BufferedImage.TYPE_3BYTE_BGR );   

			Graphics2D grafico = imagenSalida.createGraphics();

			grafico.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			grafico.drawImage( imagenEntrada, 0, 0, (anchoImagen * ancho) / ancho, (anchoImagen * alto) / ancho, null );

			ImageIO.write( imagenSalida, formatoImagen, new File( rutaImagenSalida ) );

		} 
		catch( Exception e ){
			   e.printStackTrace();
		}
	}
	
    /**
     * redimensionaImagenReturn redimensiona el tamaño de una imagen.
     * @param  anchoImagen
     * @param  formatoImagen
     * @param  rutaImagenEntrada
     * @param  rutaImagenSalida
     * @return BufferedImage
     */
	public BufferedImage redimensionaImagenReturn( int anchoImagen, String formatoImagen, String rutaImagenEntrada, String rutaImagenSalida ){

		BufferedImage imagenEntrada = null;
		BufferedImage imagenSalida  = null;
		
		try{
			imagenEntrada = ImageIO.read( new File( rutaImagenEntrada ) );
			
			int ancho = imagenEntrada.getWidth(); 
			int alto  = imagenEntrada.getHeight();

			imagenSalida  = new BufferedImage( (anchoImagen * ancho) / ancho, (anchoImagen * alto) / ancho, BufferedImage.TYPE_3BYTE_BGR );   
			
			Graphics2D grafico = imagenSalida.createGraphics();

			grafico.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			grafico.drawImage( imagenEntrada, 0, 0, (anchoImagen * ancho) / ancho, (anchoImagen * alto) / ancho, null );

			ImageIO.write( imagenSalida, formatoImagen, new File( rutaImagenSalida ) );

		} 
		catch( Exception e ){
			   e.printStackTrace();
		}
        
		return imagenSalida;
	}

    /**
     * redimensionaImageReturn redimensiona el tamaño de una imagen.
     * @param  anchoImagen
     * @param  imagen
     * @return BufferedImage
     */
	public BufferedImage redimensionaImageReturn( int anchoImagen, Image imagen ){

		BufferedImage imagenEntrada = null;
		BufferedImage imagenSalida  = null;
		
		try{
			imagenEntrada = (BufferedImage)this.manejoConvertidores.imageToBufferedImage( imagen );
					
			int ancho = imagenEntrada.getWidth(); 
			int alto  = imagenEntrada.getHeight();

			imagenSalida  = new BufferedImage( (anchoImagen * ancho) / ancho, (anchoImagen * alto) / ancho, BufferedImage.TYPE_3BYTE_BGR );   
			
			Graphics2D grafico = imagenSalida.createGraphics();

			grafico.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			grafico.drawImage( imagenEntrada, 0, 0, (anchoImagen * ancho) / ancho, (anchoImagen * alto) / ancho, null );

		} 
		catch( Exception e ){
			   e.printStackTrace();
		}
        
		return imagenSalida;
	}
	
    /**
     * redimensionaBufferedImageReturn redimensiona el tamaño de una imagen.
     * @param  anchoImagen
     * @param  altoImagen
     * @param  bufferedImage     
     * @return BufferedImage
     */
	public BufferedImage redimensionaBufferedImageReturn( int anchoImagen, int altoImagen, BufferedImage bufferedImage ){

		BufferedImage imagenSalida  = null;
		
		try{
			Image tmpImage = bufferedImage.getScaledInstance((int)anchoImagen,(int)altoImagen,Image.SCALE_AREA_AVERAGING);

			BufferedImage resizeImage = new BufferedImage((int)anchoImagen,(int)altoImagen,  bufferedImage.getType());

			Graphics2D resizeImageGraphics = resizeImage.createGraphics();
			resizeImageGraphics.drawImage( tmpImage,0,0,null );

		} 
		catch( Exception e ){
			   e.printStackTrace();
		}
        
		return imagenSalida;
	}	
	 
	public static long getSerialVersionUID(){
		return serialVersionUID;
	} 
	
 }
