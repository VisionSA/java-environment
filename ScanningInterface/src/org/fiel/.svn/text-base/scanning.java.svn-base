package org.fiel;


/*
Modified and Integrated cropping to scanner plugin for ImageJ which uses the free mm's computing java library 
(available at http://www.mms-computing.co.uk/)

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.*/

import ij.IJ;
import ij.ImagePlus;
import ij.gui.ImageCanvas;
import ij.gui.Roi;
import ij.plugin.PlugIn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import java.awt.Point;

import java.awt.RenderingHints;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import netscape.javascript.*;

import java.applet.Applet; 


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import java.applet.*;

import uk.co.mmscomputing.device.scanner.Scanner;
import uk.co.mmscomputing.device.scanner.ScannerDevice;
import uk.co.mmscomputing.device.scanner.ScannerIOException;
import uk.co.mmscomputing.device.scanner.ScannerIOMetadata;
import uk.co.mmscomputing.device.scanner.ScannerListener;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;
import uk.co.mmscomputing.device.twain.TwainSource; 
import uk.co.mmscomputing.device.twain.TwainSourceManager;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;



public class scanning extends JApplet implements PlugIn, ScannerListener 
{

	private JToolBar jtoolbar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
	ImagePanel ipanel;
	java.awt.Image im =null;
	BufferedImage imageforCrop;
	ImagePlus imp=null;
	int imageWidth;
    int imageHeight;
	private static final long serialVersionUID = 1L;
	Container   content = null;
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	JCheckBox clipBox = null;
	JPanel crpdpanel=null;
	 JPanel cpanel=null;
	private Scanner scanner=null;
	String cuit = " ";
	 TwainSource ts ;	
	 JSObject window;
	 JButton upload;
	 
	 //Applet applet = new Applet();
	 
	 List<ByteArrayOutputStream> imagenes = new  ArrayList<ByteArrayOutputStream>();

	ImagePanel imagePanel,imagePanel2 ;
	
	
	
	

	public static void main(String[] args) {
		new scanning().setVisible(true);
	}

	public void run(String arg0) {

		new scanning().setVisible(false);
		repaint();
	}

	/**
	 * This is the default constructor
	 */
	public scanning() {
		super();
		init();
		try {
			scanner = Scanner.getDevice();
			scanner.addListener(this);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void init() 
	{
//		try {
	//	Applet applet = new Applet();
			// String  inputStr = getParameter("paramStr");
		 
		
		//window = new JSObject();
		this.setSize(1200, 600);
		this.setLayout(null);
		//this.revalidate();
	this.setContentPane(getJContentPane());
	//	this.this.setContentPane(getJContentPane());(getJContentPane());
//		}  catch (Exception e1) {
//			JOptionPane.showMessageDialog(null, "mensaje " + e1.getMessage() + " clase " + e1.getClass().toString() );
//		}
	}
	
	
	public void stop()
	{
		
	}
	
	public void destroy()
	{
		
	}
	
	
	public void start()
	{
		
		
		
	}
	
	
	private JToolBar getJToolBar() 
	{
		jtoolbar.add(getJButton1());
		jtoolbar.add(getJButton());
		
		
		jtoolbar.setName("My Toolbar");   
		jtoolbar.addSeparator();
		java.awt.Rectangle r=new java.awt.Rectangle(0, 0,1024, 30 );
		jtoolbar.setBounds(r);
		jtoolbar.setBackground(Color.white);
		return jtoolbar;
	}
	
	private JPanel getJContentPane() 
	{
		if (jContentPane == null) 
		{
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(Color.white);
			jContentPane.add(getJToolBar());
		}
		return jContentPane;
	}

	
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new java.awt.Rectangle(4, 16, 131, 42));
			jButton.setText("Seleccione Scanner");
			jButton.setBackground(Color.white);
			
			
		//	 cuit = this.getParameter("cuit");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (scanner.isBusy() == false) {
						
						
						selectDevice();
					}
					
					
					
					//JOptionPane.showMessageDialog(null, "Valor " + cuit);

				}
			});
		}
		return jButton;
	}
	
	
	/* Select the twain source! */
	public void selectDevice() {

		try {
			
//			window = JSObject.getWindow(this);
			scanner.select();
		} catch (ScannerIOException e1) {
			IJ.error(e1.toString());
		}

	}
	
	private JButton getJButton1() 
	{
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new java.awt.Rectangle(35,0, 30, 30));
			jButton1.setText("Scanner");
			jButton1.setBackground(Color.white);
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					getScan();
					
//					try
//	                 {
//						
//						
//	                 File file = new File("C:" + File.separator  +  "instalador" + File.separator + "itexttest.pdf");
//	                
//	                 FileInputStream in = new FileInputStream(file);
//	                 byte[] buf=new byte[in.available()];
//	                 int bytesread = 0;
//	                
//	                
//	                 String toservlet = "http://192.168.0.13:8080/Presentacion/CreatePDFServlet";
//	                
//	                 URL servleturl = new URL(toservlet);
//	                 URLConnection servletconnection = servleturl.openConnection();
//	                 servletconnection.setDoInput(true);
//	                 servletconnection.setDoOutput(true);
//	                 servletconnection.setUseCaches(false);
//	                 servletconnection.setDefaultUseCaches(false);
//	                
//	                 DataOutputStream out=new DataOutputStream(servletconnection.getOutputStream());
//	                
//	                 while( (bytesread = in.read( buf )) > -1 )
//	                     {
//	                     out.write( buf, 0, bytesread );
//	                 }
//	                
//	                 out.flush();
//	                 out.close();
//	                 in.close();
//	                 
//	                 
//	                 // receive result from servlet
//	                 InputStream inputStream = servletconnection.getInputStream();
//	                 ObjectInputStream inputFromServlet = new ObjectInputStream(
//	                     inputStream);
//	                 String result = (String) inputFromServlet.readObject();
//	                 inputFromServlet.close();
//	                 inputStream.close();
//	                 
//	                 JOptionPane.showMessageDialog(null, "Resultado " + result);
//	                
//	                // DataInputStream inputFromClient = new DataInputStream(servletconnection.getInputStream());
//	                 //get what you want from servlet
//	                 //.......
//	              //   inputFromClient.close();
//	             }
//	             catch(Exception e2)
//	                 {
//	                 e2.printStackTrace();
//	             }
					
					
				}
			});
		}
		return jButton1;
	}
	
	public void getScan() 
	{
	
		try 
		{	
			
			window = JSObject.getWindow(this);
			scanner.acquire();
			
		} 
		catch (ScannerIOException e1) 
		{
			IJ.showMessage("Access denied! \nTwain dialog maybe already opened!");
			e1.printStackTrace();
		}
	}
	
	

	 public java.awt.Image getImage()
     { 
		 java.awt.Image image = imp.getImage();
		 return image;
     }
	
	 
	 
	public void update(ScannerIOMetadata.Type type, ScannerIOMetadata metadata) {

		
		
		if (type.equals(ScannerIOMetadata.ACQUIRED)) 
		{	
			
			
				
//	if(imp!=null)
//	{
//		jContentPane.remove(ipanel);
//		jContentPane.remove(cpanel);
//		jContentPane.remove(crpdpanel);
//	}
//	
			
//			jContentPane.remove(ipanel);
//			jContentPane.remove(cpanel);
//			jContentPane.remove(crpdpanel);
			
			if(imp!=null)
			{
				if (ipanel !=null) jContentPane.remove(ipanel);
				if (cpanel !=null) jContentPane.remove(cpanel);
				if (crpdpanel !=null) jContentPane.remove(crpdpanel);
				
				
		}
	
			 imp = new ImagePlus("Scan", metadata.getImage());
			
			 im = imp.getImage();
	
			 imagePanel = new ImagePanel(im);
			 imagePanel.updateUI();
		
			 imagePanel.repaint();
			 imagePanel.revalidate();
			 
			 ClipMover mover = new ClipMover(imagePanel);
			 imagePanel.addMouseListener(mover);
			 imagePanel.addMouseMotionListener(mover);
					 
			  ipanel = imagePanel.getPanel();
			 			 
			 ipanel.setBorder(new LineBorder(Color.blue,1));
			 ipanel.setBorder(BorderFactory.createTitledBorder("Scanned Image"));
			 ipanel.setBounds(0, 200,600, 600);
			 ipanel.repaint();
			 ipanel.revalidate();
			 ipanel.updateUI();
		//	 jContentPane.add(ipanel);
			 jContentPane.getRootPane().revalidate();
			 jContentPane.updateUI();
			 
			 cpanel = imagePanel.getUIPanel();
//			 cpanel.setBounds(700, 30,300, 150);
			 cpanel.setBounds(0, 30,300, 150);
			 cpanel.repaint();
			 cpanel.setBorder(new LineBorder(Color.blue,1));
			 cpanel.setBorder(BorderFactory.createTitledBorder(""));
			 cpanel.setBackground(Color.white);
			 jContentPane.add(cpanel);
			
			 
			 jContentPane.repaint();
			 jContentPane.revalidate();

			 

			
			metadata.setImage(null);
			try {
				new uk.co.mmscomputing.concurrent.Semaphore(0, true).tryAcquire(2000, null);
			} catch (InterruptedException e) {
				IJ.error(e.getMessage());
				
			}

		} 
		
		
		
	
		else if (type.equals(ScannerIOMetadata.NEGOTIATE)) {
			ScannerDevice device = metadata.getDevice();
			try {
				device.setResolution(100);
			} catch (ScannerIOException e) {
				IJ.error(e.getMessage());
			}
			
			  try{
				
			  device.setShowUserInterface(true);
			  device.setResolution(100); }catch(Exception e){
			  e.printStackTrace(); }
			
		}
	
		
		else if (type.equals(ScannerIOMetadata.STATECHANGE)) {
			System.out.println("Scanner State "+metadata.getStateStr());
			System.out.println("Scanner State "+metadata.getState());

			if ((metadata.getLastState() == 3) && (metadata.getState() == 4)){} 

		} else if (type.equals(ScannerIOMetadata.EXCEPTION)) {
			IJ.error(metadata.getException().toString());

		}

	}
	
	

	class ImagePanel extends JPanel
	{
		private java.awt.Image image;
		java.awt.Image cimg;
		int imageWidth;
		int imageHeight;
		Document doc = null;
		//List<ByteArrayOutputStream> imagenes;
		
		BufferedImage imageb;
		Dimension size;
		public java.awt.Rectangle clip;
		boolean showClip,clipedImg;
	
		public boolean isShowClip() {
			return showClip;
		}


		JSlider slider1 = new JSlider(SwingConstants.HORIZONTAL, 80, 180, 80);
		
		 ClipedPanel clipedPanel;
				
		ImagePanel(java.awt.Image image)
		{ 
			this.image=image;
			//imagenes = new  ArrayList<ByteArrayOutputStream>();
			this.imageWidth=image.getWidth(null);
			this.imageHeight=image.getHeight(null);
			this.imageb= (BufferedImage) image;
			size = new Dimension(imageb.getWidth(), imageb.getHeight());
		    showClip = true;
		    
           
		    
		    
		    
		    saveImg();
		    
					
		}
		
	  
		public java.awt.Image getImage()
		{ 
			return image;
		}
		
		public java.awt.Image getCimg()
		{ 
			return cimg;
		}
	
		
		
		protected void paintComponent(Graphics g)
	    {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D)g;	       
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                            RenderingHints.VALUE_ANTIALIAS_ON);
	        int x = (getWidth() - size.width)/2;
	        int y = (getHeight() - size.height)/2;
	        
	        
	        g2.drawImage(imageb, x, y, this);
	        if(showClip)
	        {
	            if(clip == null)
	            	createClip(80,80);
	            g2.setPaint(Color.red);
	            g2.draw(clip);
	        }
	    }

		 
		 public void setClip(int x, int y)
		    {

		        int x0 = (getWidth() - size.width)/2;
		        int y0 = (getHeight() - size.height)/2;
		        
		        if(x < x0 || x + clip.width  > x0 + size.width ||
		           y < y0 || y + clip.height > y0 + size.height)
		            return;
		        
		        clip.setLocation(x, y);
		        repaint();
		        
		        clipImage();
        		repaint();
		    }
		 
		    public Dimension getPreferredSize()
		    {
		        return size;
		    }
		 
		    private void createClip(int sx,int sy)
		    {
	            clip = new java.awt.Rectangle(sx, sy);
		     
		        clip.x = (getWidth() - clip.width)/2;
		        clip.y = (getHeight() - clip.height)/2;
		    }
		 
		    public JPanel getCroppedPanel()
		    {
		    	
		    	ClipedPanel cpanel = this.getClippedImg();
		    	 
		        
		        return cpanel;
		    }
		   
		    
		    private void clipImage()
		  
		    {
		        BufferedImage clipped = null;
		        try
		        {
		            int w = clip.width;
		            int h = clip.height;
		            int x0 = (getWidth()  - size.width)/2;
		            int y0 = (getHeight() - size.height)/2;
		            int x = clip.x - x0;
		            int y = clip.y - y0;
		            clipped = imageb.getSubimage(x, y, w, h);
		            
		  
		            cimg = clipped;
		            
		            clipedPanel = new  ClipedPanel( cimg);
		            
		            crpdpanel = imagePanel.getCroppedPanel();
		   	        	            
					crpdpanel.setBounds(700, 200,300, 300);
					crpdpanel.repaint();
					
					crpdpanel.setBorder(new LineBorder(Color.red,1));
					crpdpanel.setBorder(BorderFactory.createTitledBorder(""));
					crpdpanel.setBackground(Color.white);
					
					jContentPane.add(crpdpanel);
					jContentPane.repaint();
					jContentPane.revalidate();
				            
		        }
		        catch(RasterFormatException rfe)
		        {
		            System.out.println("raster format error: " + rfe.getMessage());
		        }
		          
		    }
		    
		    public ClipedPanel getClippedImg()
		    {
		    	return clipedPanel;
		    }
		 
		    
		    
		    public JPanel getUIPanel()
		    {
		    	
//		        JButton clip = new JButton("Crop image");
//		        clip.addActionListener(new ActionListener()
//		        {		            
//		            public void actionPerformed(ActionEvent e)
//		            {
//		            	repaint();
//	            		 if(getClippedImg()!=null)
//				                jContentPane.remove(getClippedImg());
//		            	if(showClip)
//		            	{
//		            		clipImage();
//		            		repaint();
//		            		clipedImg=true;
//		            		
//		            	}
//		            	else
//		            	{
//		                    		JOptionPane.showMessageDialog(null, "First Check Show clip Check Box!");
//		               	}
//		               
//		            }
//		        });
//		        
		       
		        
//		        slider1.setPaintLabels(true);
//		        slider1.setPaintTicks(true);
//		        
//		        slider1.addChangeListener(new ChangeListener()
//		        {
//		            public void stateChanged(ChangeEvent e)
//		            {
//		             repaint();
//		                
//		             int sx1 = slider1.getValue();
//		             int sy1=slider1.getValue();
//		             createClip( sx1, sy1);
//		             
//		             repaint();
//		             if(getClippedImg()!=null)
//			                jContentPane.remove(getClippedImg());
//		            
//		             if(isShowClip())
//		             {
//		            	 clipImage();
//		            	 repaint();
//		             }
//		             else
//		             {
//		            	 JOptionPane.showMessageDialog(null, "First Check Show clip Check Box!");
//		             }
//		             
//		            }
//		        });
				        
//		        JButton save = new JButton("Imagen a Pdf");
		         upload = new JButton("Finalizar Scanneo");
//		        JButton saveDoc = new JButton("Finalizar Scanneo");
		        
		        upload.addActionListener(new ActionListener()
		        
		        {

					public void actionPerformed(ActionEvent e) {
						
						try
		                 {
							enableTopButtons(false);
					//	byte[] archiovPdf =	saveDoc();
						saveDoc();
						
						imagenes = new  ArrayList<ByteArrayOutputStream>();
							
							File file = new File("C:" + File.separator  +  "instalador" + File.separator + "itexttest.pdf");
		                
		                 FileInputStream in = new FileInputStream(file);
		                 byte[] buf=new byte[in.available()];
		                 int bytesread = 0;
		                
		                
//		                 String toservlet = "http://192.168.0.13:8080/Presentacion/CreatePDFServlet";
//		                 String toservlet = "http://192.168.0.1:8080/Presentacion/CreatePDFServlet";
		                 String toservlet = "http://192.168.0.5:8083/Presentacion/CreatePDFServlet";
//		                 String toservlet = "http://192.168.0.7:8080/Presentacion/CreatePDFServlet";
		                
		                 URL servleturl = new URL(toservlet);
		                 URLConnection servletconnection = servleturl.openConnection();
		                 servletconnection.setDoInput(true);
		                 servletconnection.setDoOutput(true);
		                 servletconnection.setUseCaches(false);
		                 servletconnection.setDefaultUseCaches(false);
		                
		                 DataOutputStream out=new DataOutputStream(servletconnection.getOutputStream());
		                
		                 while( (bytesread = in.read( buf )) > -1 )
		                     {
		                     out.write( buf, 0, bytesread );
		                 }
		                
		                 out.flush();
		                 out.close();
		                 in.close();
		                 
		              // receive result from servlet
		                 InputStream inputStream = servletconnection.getInputStream();
		                 ObjectInputStream inputFromServlet = new ObjectInputStream(
		                     inputStream);
		                 String result = (String) inputFromServlet.readObject();
		                 inputFromServlet.close();
		                 inputStream.close();
		                 
		                 //JOptionPane.showMessageDialog(null, "Resultado " + result);
		                 
		                 window.call("writeImageValue", new String[] {result});
		                
		                 DataInputStream inputFromClient = new DataInputStream(servletconnection.getInputStream());
		                 //get what you want from servlet
		                 //.......
		                 inputFromClient.close();
		             }
		             catch(Exception e2)
		                 {
		            	 JOptionPane.showMessageDialog(null, " error  " + e2.getMessage() + " clase " + e2.getClass().toString());
		                // e2.printStackTrace();
		             }
						//JOptionPane.showMessageDialog(null, "Server Settings need to be Configured.", "Upload Image",1);

						}}
		        
		        );
		        
		        
//		        save.addActionListener(new ActionListener()
//		        {
//		            public void actionPerformed(ActionEvent e)
//		            {		            	
//		            	repaint();            		
//		            	
//		            	saveImg();
//	            		repaint();
//		            	
//		            		
//		            }
//		        });
		        
		        
		        
		        
//		        saveDoc.addActionListener(new ActionListener()
//		        {
//		            public void actionPerformed(ActionEvent e)
//		            {
//		            	repaint();
//	            		
//		            	
//		            		saveDoc();
//		            		repaint();
//		            		 
//		            	}
//		            	
//		           
//		        });
		        
		        
		        
		        
		        JPanel panel = new JPanel();
		        //panel.add(clipBox);
//		        panel.add(clip);
//		        panel.add(slider1);
		        slider1.setBackground(Color.white);
//		        panel.add(save);
		        panel.add(upload);
	//	        panel.add(saveDoc);
		        panel.revalidate();
		        return panel;
		    }
		 
		    
		    public void enableTopButtons(boolean enable) {
		    	upload.setEnabled(enable);
		        
		     }
		    

		    public void saveImg()
			 {

		    	try {
			    	java.awt.Image image=getImage();
				 
				 BufferedImage bufferedImage=new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
				 bufferedImage.createGraphics().drawImage(image, 0, 0, null);
				 
//				 if (doc == null){
//					  doc=new Document();
//					 com.itextpdf.text.Rectangle r= com.itextpdf.text.PageSize.A4;
//					  doc.setPageSize(r);
//					 
//				 }
				 
				 String ext="JPG";
				 
//				 File  file = new File("d:" + File.separator + "itexttest.pdf");
//					FileOutputStream fileout = new FileOutputStream(file);
//					PdfWriter.getInstance(doc, fileout);
				 
				 ByteArrayOutputStream bas=new ByteArrayOutputStream();
		    	  ImageIO.write(bufferedImage, ext, bas);
		    	  
		    	  imagenes.add(bas);
		    	  //create image from the image data stored in memory
//		    	 Image img= Image.getInstance(bas.toByteArray());
//		    	 img.setAbsolutePosition(0, 0);
//		    	 img.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
		    	 
		    	  //centrally align the image on the pdf page
		    	 // img.setAlignment(Element.ALIGN_CENTER);
		    	  //open document
//		    	  doc.open();
//		    	  doc.newPage();
//		    	//add image to the document
//		    	  doc.add(img);
		    	  //close the document
		    	  
//		    	  JOptionPane.showMessageDialog(null, "Convirtio imagen a Pdf!");
		    	  
//		    	  if (doc == null) {
//					   JOptionPane.showMessageDialog(null, "doc !"); 
//				   } else {
//					   JOptionPane.showMessageDialog(null, doc.getPageNumber() + " paginas");
//				   }
//		    	  
//		    	  doc.close();
						
		    	}		
						
		    	catch (Exception e1) 
				{
					
					JOptionPane.showMessageDialog(null, "No Convirtio imagen a Pdf " );
					e1.printStackTrace();
				} 
							
							
						
					}
			 
		    
		    
		    
		    
		    
		    
		    public void saveDoc()
			 {		    	
		    	try {		    	
		    		
		    		
		    		
		    		Document doc=new Document();
					 com.itextpdf.text.Rectangle r= com.itextpdf.text.PageSize.A4;
					  doc.setPageSize(r);
//					  ByteArrayOutputStream out1 = new ByteArrayOutputStream();
		    		
		    		if (imagenes.size() > 0) {
							  
		    			File file = new File("C:" + File.separator  +  "instalador" + File.separator + "itexttest.pdf");
		    			
								FileOutputStream fileout = new FileOutputStream(file);
								PdfWriter.getInstance(doc, fileout);
							 
							
					    	  doc.open();
					    	  
					    	  for (ByteArrayOutputStream image : imagenes) {
					    		 Image img= Image.getInstance(image.toByteArray());
							     img.setAbsolutePosition(0, 0);
							     img.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
							     doc.newPage();
							    //add image to the document
							     doc.add(img);
							    //  close the document
					    	  }
					    	
					    	  //close the document
					    	  doc.close();
					    	  
//					    	  return out1.toByteArray();
//					    	  JOptionPane.showMessageDialog(null, "Grabo Archivo!");
		    		} else {
//		    			return null;
		    		}
		    		

						} catch (Exception e1) 
						{
							
							JOptionPane.showMessageDialog(null, "No Grabo Archivo! " + e1.getMessage() + " ddd " + e1.getClass().toString());
//							return null;
						} 
						
					}
		   
		    
		
		public ImagePanel getPanel()	{			return this;	}
	}
	 

	class ClipMover extends MouseInputAdapter
	{
		ImagePanel cropping;
	    Point offset;
	    boolean dragging;
	       
	    
	    public ClipMover(ImagePanel c)
	    {
	        cropping = c;
	        offset = new Point();
	        dragging = false;
	    }
	 
	    public void mousePressed(MouseEvent e)
	    {
	    	
	        Point p = e.getPoint();
	       
	        
	        if(cropping.clip.contains(p))
	        {
	            offset.x = p.x - cropping.clip.x;
	            offset.y = p.y - cropping.clip.y;
	            dragging = true;
	        }
	    }
	 
	    public void mouseReleased(MouseEvent e)
	    {
	        dragging = false;
	    }
	 
	    public void mouseDragged(MouseEvent e)
	    {
	    	
	        if(dragging)
	        {
	        	 ClipedPanel xcpanel = cropping.getClippedImg(); 

				 if(xcpanel!=null)
				 {
		                jContentPane.remove(xcpanel);
				 }
	        
	        	
	            int x = e.getX() - offset.x;
	            int y = e.getY() - offset.y;
	            
	            
	            
	            if( cropping.isShowClip())
	            	            cropping.setClip(x, y);
	         
	       }
	    }
	    
	    
	}


	
	

} 

class ClipedPanel extends JPanel
{
	private java.awt.Image image;
	int imageWidth;
	int imageHeight;
	
	BufferedImage imageb;
	Dimension size;
	java.awt.Rectangle clip;
	boolean showClip;

	ClipedPanel(java.awt.Image image)
	{ 
		this.image=image;
		this.imageWidth=image.getWidth(null);
		this.imageHeight=image.getHeight(null);

		this.imageb= (BufferedImage) image;
		size = new Dimension(imageb.getWidth(), imageb.getHeight());
	    showClip = false;
	}
	
  	
	
	protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int x = (getWidth() - size.width)/2;
        int y = (getHeight() - size.height)/2;
        
       
        
        
        g2.drawImage(imageb, x, y, this);
        
    }

	 		

	public ClipedPanel getPanel()	{			return this;	}
}
 
 	

