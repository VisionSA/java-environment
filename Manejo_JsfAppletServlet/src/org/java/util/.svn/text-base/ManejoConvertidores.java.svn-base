package org.java.util;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Autor:  Ricardo Guerra .A.
 * 
 */

public class ManejoConvertidores implements Serializable{

	private static final long serialVersionUID = -2454627055596665003L;

    /**
     * bufferedImageToImage convierte un dato de tipo 'BufferedImage' a 'Image'.
     * @return Image
     */
	public Image bufferedImageToImage( BufferedImage grafico ){
		Image  imagen = Toolkit.getDefaultToolkit().createImage( grafico.getSource() );
		return imagen;
	}

    /**
     * arrayBytesToImage convierte un dato de tipo 'arrayBytes' a 'Image'.
     * @return Image
     */
	public Image arrayBytesToImage( byte[] grafico ){
		Image  imagen = Toolkit.getDefaultToolkit().createImage( grafico );
		return imagen;
	}
	
    /**
     * arrayBytesToBufferedImage convierte un dato de tipo 'byte[]' a 'BufferedImage'.
     * @return BufferedImage
     */
	public BufferedImage arrayBytesToBufferedImage( byte[] imagenBytes ){
		
		BufferedImage bufferedImage = null;
		
		try{
			if( (imagenBytes != null) && (imagenBytes.length > 0) ){
				bufferedImage = ImageIO.read( new ByteArrayInputStream( imagenBytes ) );
			}
		} 
		catch( Exception e ){
			e.printStackTrace();
		}
		
		return bufferedImage;
	}
	
    /**
     * inputStreamToBufferedImage convierte un dato de tipo 'InputStream' a 'BufferedImage'.
     * @return BufferedImage
     */
	public BufferedImage inputStreamToBufferedImage( InputStream inputStream ){
        
		BufferedImage bufferedImage = null;
		
		try{
			bufferedImage = javax.imageio.ImageIO.read( inputStream );
		}
		catch( Exception e ){
			e.printStackTrace();
		}
        return bufferedImage;
	}
	
    /**
     * byteArrayOutputStreamToBufferedImage convierte un dato de tipo 'ByteArrayOutputStream' a 'BufferedImage'.
     * @return BufferedImage
     */
	public BufferedImage byteArrayOutputStreamToBufferedImage( ByteArrayOutputStream baos ){
		
		BufferedImage bufferedImage = null;
			
		try{
			byte[] bytes = baos.toByteArray();
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream( bytes );
			bufferedImage = ImageIO.read( byteArrayInputStream );

		}
		catch( Exception e ){
			e.printStackTrace();
		}
		
		return bufferedImage;
	}
		
    /**
     * imageToBufferedImage convierte un dato de tipo 'Image' a 'BufferedImage'.
     * @return BufferedImage
     */
	public BufferedImage imageToBufferedImage( Image image ){

		if( image instanceof BufferedImage ){
			return (BufferedImage)image;
		}

		image = new ImageIcon(image).getImage();

		boolean validator = this.getValidator(image);

		BufferedImage bufferedImage = null; 
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		try{
			int transparency = Transparency.OPAQUE;

			if( validator ){
				transparency = Transparency.BITMASK;
			}

			GraphicsDevice        gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bufferedImage = gc.createCompatibleImage( image.getWidth(null), image.getHeight(null), transparency );  
		} 
		catch( Exception e ){
			e.printStackTrace();
		}

		if( bufferedImage == null ){
			
			int type = BufferedImage.TYPE_INT_RGB;

			if( validator ){
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bufferedImage = new BufferedImage( image.getWidth(null), image.getHeight(null), type );
		}

		Graphics g = bufferedImage.createGraphics();

		g.drawImage( image, 0, 0, null );
		g.dispose();

		return bufferedImage;
	}
	
    /**
     * getValidator valida un dao 'image'.
     * @return boolean
     */
	private boolean getValidator( Image image ){

		if( image instanceof BufferedImage ){
			BufferedImage bimage = (BufferedImage) image;
			return bimage.getColorModel().hasAlpha();
		}

		PixelGrabber pg = new PixelGrabber( image, 0, 0, 1, 1, false );

		try{
			pg.grabPixels();
		} 
		catch( InterruptedException e ){
			e.printStackTrace();
		}

		ColorModel cm = pg.getColorModel();
		
		return cm.hasAlpha();
	}
	
    /**
     * bufferedImageToArrayBytes convierte un dato de tipo 'bufferedImage' a 'byte[]'.
     * @return byte[]
     */
	public byte[] bufferedImageToArrayBytes( BufferedImage bufferedImage ){
		
		try{
			if( bufferedImage != null ){
				BufferedImage          image = bufferedImage;
				ByteArrayOutputStream  baos  = new ByteArrayOutputStream();
				
				try{
					ImageIO.write( image, "jpg", baos );
				} 
				catch( IOException e ){
					throw new IllegalStateException(e.toString());
				}
				byte[] b = baos.toByteArray();
				
				return b;
			} 
		}	
		catch( Exception e ){
			e.printStackTrace();
		}
		
		return new byte[0];
	}
	
    /**
     * inputStreamToArrayBytes convierte un dato de tipo 'inputStream' a 'byte[]'.
     * @return byte[]
     */
	public byte[] inputStreamToArrayBytes( InputStream inputStream ){
        
		System.out.println( "DENTRO...!!!" );
		
		ByteArrayOutputStream out= null;
		
		try{
			out = new ByteArrayOutputStream(1024);
			byte[] buffer = new byte[1024];
			int len;
	
			while((len = inputStream.read(buffer)) >= 0)
			out.write(buffer, 0, len);
	
			inputStream.close();
			out.close();
		}	
		catch( Exception e ){
			e.printStackTrace();
		}
		
		return out.toByteArray();
	} 
	
    /**
     * intToArrayBytes convierte un dato de tipo 'int' a 'byte[]'.
     * @return byte[]
     */
	public byte[] intToArrayBytes( int numero ){
        byte[] arrayBytes = new byte[4];
        
        for( int i=0; i<4; i++ ){
             int offset = (arrayBytes.length - 1 - i) * 8;
             arrayBytes[i] = (byte) ((numero >>> offset) & 0xFF);
        }
        
        return arrayBytes;
    }
	
    /**
     * stringToArrayBytes convierte un dato de tipo 'String' a 'byte[]'.
     * @return byte[]
     */
	public byte[] stringToArrayBytes( String cadena ){
	   
		byte[] arrayBytes = null;
	    
	    try{
		    arrayBytes = cadena.getBytes();
		    //arrayBytes = cadena.getBytes( "UTF-8" );
	    } 
	    catch( Exception e ){
	        e.printStackTrace();
	    }
	    
		return arrayBytes;
    }
	
    /**
     * objectToArrayBytes convierte un dato de tipo 'Object' a 'byte[]'.
     * @return byte[]
     */
	public byte[] objectToArrayBytes( Object objeto ){
		
		byte[] data = null;
		
		try{
	        ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
	        ObjectOutputStream    oos = new ObjectOutputStream( bos );  
	      
	        oos.writeObject( objeto );
	        oos.flush();
	        
	        oos.close(); 
	        bos.close();
	      
	        data = bos.toByteArray();
		}
		catch( Exception e ){
		  e.printStackTrace();
		}
		
	    return data;
	}
	
    /**
     * inputStreamToOutputStream convierte un dato de tipo 'InputStream' a 'OutputStream'.
     * @return OutputStream
     */
	public OutputStream inputStreamToOutputStream( InputStream inputStream ){
		
		OutputStream outputStream = null;
		
		try{
			byte[] streamBytes = new byte[1024];
			int    ch          = 0;
			while( (ch = inputStream.read( streamBytes ) ) != -1 ){
			    outputStream.write( streamBytes, 0, ch );
			}
			outputStream.flush();
		}
		catch( Exception e ){
			e.printStackTrace();
		}
		
		return outputStream;	
	}
	
    /**
     * bufferedImageToByteArrayInputStream convierte un dato de tipo 'BufferedImage' a 'InputStream'.
     * @return InputStream
     */
	public InputStream bufferedImageToByteArrayInputStream( BufferedImage bufferedImage ){
		
		ByteArrayInputStream byteArrayInputStream = null;
		
		try{
			String formatName = "jpeg";
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write( bufferedImage, formatName, byteArrayOutputStream );
			byte[] imageBytes = byteArrayOutputStream.toByteArray();
			byteArrayInputStream = new ByteArrayInputStream( imageBytes );
		}
		catch( Exception e ){
			e.printStackTrace();
		}
		return byteArrayInputStream;
	}
		
	
	public long arrayBytesToLong( byte[] arrayBytes ){
	    long l = 0;
	    l |= arrayBytes[0] & 0xFF;
	    l <<= 8;
	    l |= arrayBytes[1] & 0xFF;
	    l <<= 8;
	    l |= arrayBytes[2] & 0xFF;
	    l <<= 8;
	    l |= arrayBytes[3] & 0xFF;
	    
	    return l;
	}

	public Integer arrayBytesToInteger( byte[] arrayBytes ){
		Integer i = 0;
	    i |= arrayBytes[0] & 0xFF;
	    i <<= 8;
	    i |= arrayBytes[1] & 0xFF;
	    return i;
	}
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}
	
 }
