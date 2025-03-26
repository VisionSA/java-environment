
import java.applet.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.*;
import java.net.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AppletApplet_01 extends Applet implements ActionListener{

	private static final long serialVersionUID = 1899226273624269567L;
	
	private JLabel      lblMensaje;
	private JButton     btnProcesar;
	private TextField   txtMensaje;
	private JPanel      pnlContenedor;

	/**
     * init() creacion de GUI. 
     */	
	public void init(){
		this.setLayout( new FlowLayout() );
		this.setBackground( Color.BLACK ); 	
		
		this.pnlContenedor = new JPanel();
		this.pnlContenedor.setBackground( Color.BLACK ); 			
		
		this.lblMensaje  = new JLabel( "Mensaje Enviar 2do Applet: " );	
		this.lblMensaje.setForeground( Color.WHITE );
		
		this.txtMensaje  = new TextField( 12 );
		
		this.btnProcesar = new JButton( " Procesar " ); 
		this.btnProcesar.addActionListener( this );
		
		this.pnlContenedor.add( this.lblMensaje );
		this.pnlContenedor.add( this.txtMensaje );
		this.pnlContenedor.add( this.btnProcesar );
		
		this.add( this.pnlContenedor );
	}
	
	/**
     * actionPerformed manejo de eventos de los botones. 
     */	
	public void actionPerformed( ActionEvent event ){
		
		if( event.getSource() == this.getBtnProcesar() ){
			
			try{
				String urlApplet     = this.getParameter( "urlApplet2do" );
				String mensajeApplet = this.getParameter( "mensajeApplet" );
				
				System.out.println("PARAMETRO 'urlApplet': "     + urlApplet );	
				System.out.println("PARAMETRO 'mensajeApplet': " + mensajeApplet );	;	
				
				//JUGADA PARA AQUITAR EL: '../applet' DE LA URL DEL NAVEGADOR... 
				String newUrl = urlApplet.toString().replace( "applet/", "" );
				System.out.println( "NEW URL: " + newUrl );
			
				//URL urlServlet = new URL( newUrl + "?mensaje1=" + mensajeApplet + "&mensaje2=" + this.txtMensaje.getText().trim() );
				URL urlServlet = new URL( newUrl + "?mensaje1=" + this.txtMensaje.getText().trim() );
				
				System.out.println( "urlServlet: " + urlServlet );

				this.getAppletContext().showDocument( urlServlet );
			} 
			catch( Exception e ){
				e.printStackTrace();
			}
		}		
	 }
	
	/**
     * getBtnProcesar ... 
     */	
	public JButton getBtnProcesar(){
		return btnProcesar;
	}
	
 }
