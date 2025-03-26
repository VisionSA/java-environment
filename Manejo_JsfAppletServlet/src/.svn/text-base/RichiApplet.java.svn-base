
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.JButton;
import javax.swing.JLabel;


public class RichiApplet extends Applet{
	
	private TextField txtEntrada  = new TextField( 14 );
	private TextField txtSalida   = new TextField( 20 );
	private TextArea  txtImprime  = new TextArea();

	private JButton   btnProcesar = new JButton( " Enviar " );;
	
	private JLabel    lblEntrada  = new JLabel( "Entrada Datos: ",       JLabel.LEFT );
	private JLabel    lblSalida   = new JLabel( "Salida Resultados: ",   JLabel.LEFT );
	private JLabel    lblImprime  = new JLabel( "Impresiòn Exception: ", JLabel.LEFT );
	
	/**
	 * Setup the GUI.
	 */
	public void init(){

		this.setLayout( new GridBagLayout() );
		this.setBackground( Color.BLACK );
		
		this.lblEntrada.setForeground( Color.WHITE );
		this.lblSalida.setForeground(  Color.WHITE );
		this.lblImprime.setForeground( Color.WHITE );
				
		this.txtSalida.setForeground(  Color.WHITE );
		this.txtSalida.setBackground(  Color.LIGHT_GRAY );
		this.txtImprime.setEditable( false );
		
		this.txtImprime.setForeground(  Color.GREEN );
		this.txtImprime.setBackground(  Color.LIGHT_GRAY );
		this.txtImprime.setEditable( false );
		
		GridBagConstraints c = new GridBagConstraints();

		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		this.add( this.lblEntrada,  c );
		
		c = new GridBagConstraints();
		c.fill    = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;		
		this.add( this.txtEntrada,  c );
		
		c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;		
		this.add( this.btnProcesar, c );
		
		
		this.btnProcesar.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarObtenerDatosServlet();
			}
		});

		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		this.add( this.lblSalida, c);
		
		c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		this.add( this.txtSalida, c );
		

		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		this.add( this.lblImprime, c );
		
		c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		this.add( this.txtImprime, c );	
	}

	/**
	 * Get a connection to the servlet.
	 */
	private URLConnection getServletConnection() throws MalformedURLException, IOException{
		System.out.println( "DENTRO DE 'getServletConnection'" );
		
		//Obteniendo Parametros...
		String  nombre         = this.getParameter( "nombre" );
		String  url            = this.getParameter( "URL_NombreAppletServlet" );
		String  servletContext = this.getParameter( "URL_ServletContext" );
		String  contextPath    = this.getParameter( "URL_ContextPath" );
		
		System.out.println("NOMBRE PARAMETRO: " + nombre );		
		System.out.println("NOMBRE URL COMPLETA: " + url );
		System.out.println("NOMBRE 'URL_ServletContext': " + servletContext );
		System.out.println("NOMBRE 'URL_ContextPath': "    + contextPath    );
		
		//JUGADA PARA AQUITAR EL: '../applet' DE LA URL DEL NAVEGADOR... 
		String newUrl = url.replace( "applet/", "" );
		System.out.println( "NEW URL: " + newUrl );
				
		//Connection zum Servlet öffnen
		URL urlServlet = new URL( this.getCodeBase(), newUrl );  
		
		System.out.println("URL SERVLET: " + urlServlet );		
		URLConnection conexion = urlServlet.openConnection();

		conexion.setDoInput( true );
		conexion.setDoOutput( true );
		conexion.setUseCaches( false );
		conexion.setRequestProperty( "Content-Type", "application/x-java-serialized-object" );

		return conexion;
	}

	/**
	 * Send the inputField data to the servlet and show the result in the outputField.
	 */
	private void enviarObtenerDatosServlet(){
		System.out.println( "DENTRO DE 'enviarObtenerDatosServlet'" );
		
		try{ 
			//Obtiene datos del JSP para enviar.			
			String datoEnvio = txtEntrada.getText();

			System.out.println( "DATOS A ENVIAR: " + datoEnvio );
			
			//Envia datos Obtenidos al Servlet
			URLConnection urlConexion = getServletConnection();
			OutputStream salida = urlConexion.getOutputStream();
			
			ObjectOutputStream objetoSalida = new ObjectOutputStream(salida);
			objetoSalida.writeObject( datoEnvio );
			objetoSalida.flush();
			objetoSalida.close();

			//Recibe datos del Servlet.
			InputStream        entrada       = urlConexion.getInputStream();
			ObjectInputStream  objetoEntrada = new ObjectInputStream( entrada );
			
			String resultado = (String)objetoEntrada.readObject();
			objetoEntrada.close();
			entrada.close();

			//Muestra Resultados
            this.txtSalida.setText(  resultado);
		} 
		catch( Exception e ){
			e.printStackTrace();
			this.txtImprime.setText( e.toString() );
		}
	}
}
