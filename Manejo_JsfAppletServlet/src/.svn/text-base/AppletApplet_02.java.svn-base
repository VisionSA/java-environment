
import java.applet.Applet;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AppletApplet_02 extends Applet{

	private static final long serialVersionUID = -2010238412918114453L;
	
	private JLabel     lblMensaje;
	private TextField  txtMensaje;
	private JPanel     pnlContenedor;
	
	/**
     * init() creacion de GUI. 
     */	
	public void init(){
		this.setLayout( new FlowLayout() );
		this.setBackground( Color.BLACK ); 	
		
		this.pnlContenedor = new JPanel();
		this.pnlContenedor.setBackground( Color.BLACK ); 
		
		this.lblMensaje = new JLabel( "Mensaje Obtenido 1er Applet: " );
		this.lblMensaje.setForeground( Color.WHITE );
		
		this.txtMensaje  = new TextField( 12 );
		
		this.pnlContenedor.add( this.lblMensaje);
		this.pnlContenedor.add( this.txtMensaje );

		this.add( this.pnlContenedor );
				
		this.manipulaUrl();
	}
	
	/**
     * manipulaUrl obtiene los datos mandados en la URL, los trabaja y muestra. 
     */	
	public void manipulaUrl(){
		//Obtiene la 'URL'.
		String  url = this.getDocumentBase().toString();
		System.out.println( "IMPRIME #1: " + url );
				
		//Extrae del 'Search'
		String mensaje1 =  url.substring( url.indexOf( '?' ) + 1 );
		System.out.println( "IMPRIME #2: " + mensaje1 );
		
		//Remueve el Mensaje de la 'URL'.
		mensaje1 = mensaje1.substring( "mensaje1=".length() );
		System.out.println( "IMPRIME #3: " + mensaje1 );
		
		//Muestra el Mensaje en el 2do 'Applet'.
		this.txtMensaje.setText( mensaje1 );
	}
	
 }
