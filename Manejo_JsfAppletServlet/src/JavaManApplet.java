
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class JavaManApplet extends Applet implements ActionListener, Serializable{

	private static final long serialVersionUID = 7686683183129818137L;
	
	private JTextField txtHttpText, 
	                   txtHttpObject;
	private JButton    btnProcesarHttpText, 
	                   btnProcesarHttpTextII;
	private JButton    btnProcesarHttpObject, 
	                   btnProcesarHttpObjectII;
	private JLabel     lblTitulo, 
	                   lblTexto_01, 
	                   lblTexto_02;
	private JPanel     pnlInferior, 
	                   pnlCentro, 
	                   pnlIzquierda, 
	                   pnlSuperior;
	private ImagePanel pnlImagenes;
	
	/**
     * init() creacion de GUI. 
     */	
	public void init(){
        this.setSize( 800, 250 );
        this.setLayout( new BorderLayout() );
        this.setBackground( Color.BLACK );
        
		/**** CONSTRUYENGO 'GUI' ****/	
		//this.pnlSuperior
		this.pnlSuperior = new JPanel();
		this.pnlSuperior.setLayout( new GridLayout( 1, 1 ) );
		this.pnlSuperior.setBackground( Color.BLACK );
		
		//this.pnlCentro
		this.pnlCentro = new JPanel();
		this.pnlCentro.setLayout( new GridLayout( 5, 1 ) );
		this.pnlCentro.setBackground( Color.BLACK );
		
		//this.pnlInferior
		this.pnlInferior = new JPanel();
		this.pnlInferior.setLayout( new GridLayout( 1, 1 ) );
		this.pnlInferior.setBackground( Color.BLACK );
		
		//this.pnlIzquierda
		this.pnlIzquierda = new JPanel();
		this.pnlIzquierda.setLayout( new GridLayout( 5, 1 ) );
		this.pnlIzquierda.setBackground( Color.BLACK );
		
		//this.lblTexto_01
		this.lblTexto_01 = new JLabel( "Mètodo HTTP Text: ", JLabel.LEFT );
		this.lblTexto_01.setForeground( Color.WHITE );
		
		//this.lblTexto_02
		this.lblTexto_02 = new JLabel( "Mètodo HTTP Object: ", JLabel.LEFT );
		this.lblTexto_02.setForeground( Color.WHITE );
		
		//this.lblTitulo
		this.lblTitulo = new JLabel( " ", JLabel.CENTER );
		//this.lblTitulo = new JLabel( "CONEXION APPLET-SERVLET", JLabel.CENTER );
		//this.lblTitulo.setForeground( Color.RED );
		//this.lblTitulo.setFont( new Font( "Arial", 1, 20 ) );
		
		//this.txtHttpText
		this.txtHttpText = new JTextField();
		this.txtHttpText.setEditable( false );

		//this.txtHttpObject
		this.txtHttpObject = new JTextField();
		this.txtHttpObject.setEditable( false );
		
		//this.btnProcesarHttpText
		this.btnProcesarHttpText = new JButton( "Modo Envìo: 'HttpObject'" );
		this.btnProcesarHttpText.addActionListener( this );
		this.btnProcesarHttpText.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		
		//this.btnProcesarHttpTextII
		this.btnProcesarHttpTextII = new JButton( "Generar Imagen" );
		this.btnProcesarHttpTextII.addActionListener( this );
		this.btnProcesarHttpTextII.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );		
		
		//this.btnProcesarHttpObject
		this.btnProcesarHttpObject = new JButton( "Modo Envìo: 'HttpObject'" );
		this.btnProcesarHttpObject.addActionListener( this );
		this.btnProcesarHttpObject.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		
		//this.btnProcesarHttpObjectII
		this.btnProcesarHttpObjectII = new JButton( "Modo Envìo: 'HttpObject II'" );
		this.btnProcesarHttpObjectII.addActionListener( this );
		this.btnProcesarHttpObjectII.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		
		
		/**** AGREGANDO CONTROLES A PANELES ****/		
		//this.pnlSuperior
		this.pnlSuperior.add( this.lblTitulo );
		
		//this.pnlIzquierda
		this.pnlIzquierda.add( this.lblTexto_01 );
		this.pnlIzquierda.add( this.lblTexto_02 );
		
		//this.pnlCentro
		this.pnlCentro.add( this.txtHttpText );
		this.pnlCentro.add( this.txtHttpObject );
		
		//this.pnlInferior
		this.pnlInferior.add( this.btnProcesarHttpText     );
		this.pnlInferior.add( this.btnProcesarHttpObject   );
		this.pnlInferior.add( this.btnProcesarHttpObjectII );
		this.pnlInferior.add( this.btnProcesarHttpTextII   );

    	//---- 'pnlImagenes' ----//
    	this.pnlImagenes = new ImagePanel();

    	
		/**** AGREGANDO PANELES AL CONTENEDOR ****/
		this.add( this.pnlIzquierda, new BorderLayout().WEST   );
		this.add( this.pnlSuperior,  new BorderLayout().NORTH  );	
		this.add( this.pnlCentro,    new BorderLayout().CENTER );
		this.add( this.pnlInferior,  new BorderLayout().SOUTH  );
	}
	
	/**
	 * click_btnProcesarHttpText.
	 */
	public String click_btnProcesarHttpText(){
		String mensajeRetorno = this.getDataUsingHttpText(); 
		this.txtHttpText.setText( mensajeRetorno );                      //Seteando en 'Applet-GUI'.
		return mensajeRetorno;                                           //Retorna Mensaje
	}

	/**
	 * click_btnProcesarHttpTextII.
	 */
	public void click_btnProcesarHttpTextII(){
		this.getImageUsingHttpText(); 
	}

	/**
	 * click_btnProcesarHttpObject.
	 */
	public String click_btnProcesarHttpObject(){
		String mensajeRetorno = this.getDataUsingHttpObject(); 
		this.txtHttpObject.setText( this.getDataUsingHttpObject() );     //Seteando en 'Applet-GUI'.
		return mensajeRetorno;                                           //Retorna Mensaje
	}
	
	/**
	 * click_btnProcesarHttpObjectII.
	 */
	public String click_btnProcesarHttpObjectII(){
		String mensajeRetorno = this.getDataUsingHttpObjectII(); 
		this.txtHttpObject.setText( this.getDataUsingHttpObjectII() );  //Seteando en 'Applet-GUI'.
		return mensajeRetorno;                                          //Retorna Mensaje
	}	
	
	/**
	 * click_btnLimpiar.
	 */
	public void click_btnLimpiar(){
		
		this.muestraGUI();
		
		this.txtHttpText.setText(   "" );
		this.txtHttpObject.setText( "" );
		
		JOptionPane.showMessageDialog( null, "Datos Limpiados", "Informaciòn Importante", JOptionPane.INFORMATION_MESSAGE );
	}
	
	/**
	 * getDataUsingHttpText enviado de datos a base de 'HttpText'.
	 */
	private String getDataUsingHttpText(){
		System.out.println("DENTRO DE 'getDateUsingHttpText' ");

		String cadenaRetorno = null;

		try{			
			String url_AppletServlet = (String)this.getParameter( "URL_AppletServlet" );
			String nombreApplet      = (String)this.getParameter( "NombreApplet" );
			String apellidoApplet    = (String)this.getParameter( "ApellidoApplet" );
			String codigoApplet      = (String)this.getParameter( "CodigoApplet" );
	        String tipoConexion      = "HttpText";   

			System.out.println( "URL_AppletServlet: " + url_AppletServlet );
			System.out.println( "NombreApplet: "      + nombreApplet );
			System.out.println( "ApellidoApplet: "    + apellidoApplet );
			System.out.println( "CodigoApplet: "      + codigoApplet );
			System.out.println( "tipoConexion: "      + tipoConexion );

			System.out.println( "this.getCodeBase(): " + this.getCodeBase() );
            
			//Construye una 'URL' para conexion con el 'Servlet'.
			//Se quita la palabra '/applet' de la 'URL'.
			String url    = url_AppletServlet.replace( "/applet", "" );
			URL    newUrl = new URL( url + "?nombreApplet="   + nombreApplet   + "&" +   
										    "apellidoApplet=" + apellidoApplet + "&" + 
											"codigoApplet="   + codigoApplet   + "&" +
											"tipoConexion="   + tipoConexion );
			System.out.println( "New URL: " + newUrl );

			//Crea un 'HttpMessage' para la Comunicacion con la URL.
			HttpMessage mensaje = new HttpMessage( newUrl );

			//Envia en Mensaje al Servlet y Obtiene la Respuesta en un 'InputStream'
			InputStream entrada = mensaje.sendGetMessage();

			//Wrap el InputStream con un DataInputStream.
			BufferedInputStream  buffered  = new BufferedInputStream( entrada );
			DataInputStream      resultado = new DataInputStream( buffered );

			//Lee la Respuesta de llegada desde el Servlet.
			String nombreCompleto = resultado.readLine();

			//Cierra el 'InputStream'.
			entrada.close();

			cadenaRetorno = nombreCompleto;
		} 
		catch( Exception e ){
			e.printStackTrace();
		}

		//Retorna un String al Applet.
		return cadenaRetorno;
	}
	
	/**
	 * getImageUsingHttpText obtiene Imagen del Servlet.
	 */
	private void getImageUsingHttpText(){
		
		try{
			String url_AppletServlet = (String)this.getParameter( "URL_AppletServlet" );
			String nombreApplet      = (String)this.getParameter( "NombreApplet" );
			String apellidoApplet    = (String)this.getParameter( "ApellidoApplet" );
			String codigoApplet      = (String)this.getParameter( "CodigoApplet" );
	        String tipoConexion      = "HttpTextII";   
	
			System.out.println( "URL_AppletServlet: " + url_AppletServlet );
			System.out.println( "NombreApplet: "      + nombreApplet );
			System.out.println( "ApellidoApplet: "    + apellidoApplet );
			System.out.println( "CodigoApplet: "      + codigoApplet );
			System.out.println( "tipoConexion: "      + tipoConexion );
	
			System.out.println( "this.getCodeBase(): " + this.getCodeBase() );
	        
			//Construye una 'URL' para conexion con el 'Servlet'.
			//Se quita la palabra '/applet' de la 'URL'.
			String url  = url_AppletServlet.replace( "/applet", "" );
	
			URL    newUrl = new URL( url + "?nombreApplet="   + nombreApplet   + "&" +   
										    "apellidoApplet=" + apellidoApplet + "&" + 
											"codigoApplet="   + codigoApplet   + "&" +
											"tipoConexion="   + tipoConexion );
			
			System.out.println( "newUrl: " + newUrl );
									
			Image imagen = Toolkit.getDefaultToolkit().getImage( newUrl );
			
			System.out.println( "OBJECTO IMAGEN: " + imagen );
						
			this.ocultaGUI();
			
			//------ MUESTRA IMAGEN 'GUI' ------//
			this.pnlImagenes.setImage( imagen, 150, 150 );			
			this.pnlImagenes.setBackground( Color.BLACK );
			
			this.pnlCentro.add( this.pnlImagenes );
			this.pnlCentro.setVisible( true );
			//----------------------------------//
		}
		catch( Exception e ){
			e.printStackTrace();
		}
	}
	
	/**
	 * getDataUsingHttpObject enviado de datos a base de 'HttpObject'.
	 */
	private String getDataUsingHttpObject(){
		System.out.println("DENTRO DE 'getDateUsingHttpObject' ");

		String cadenaRetorno = null;

		try{			
			String url_AppletServlet = (String)this.getParameter( "URL_AppletServlet" );
			String nombreApplet      = (String)this.getParameter( "NombreApplet" );
			String apellidoApplet    = (String)this.getParameter( "ApellidoApplet" );
			String codigoApplet      = (String)this.getParameter( "CodigoApplet" );
	        String tipoConexion      = "HttpObject";   

			System.out.println( "URL_AppletServlet: " + url_AppletServlet );
			System.out.println( "NombreApplet: "      + nombreApplet );
			System.out.println( "ApellidoApplet: "    + apellidoApplet );
			System.out.println( "CodigoApplet: "      + codigoApplet );
			System.out.println( "tipoConexion: "      + tipoConexion );

			System.out.println( "this.getCodeBase(): " + this.getCodeBase() );

			//Construct a URL referring to the servlet
			String url    = url_AppletServlet.replace( "/applet", "" );
			URL    newUrl = new URL( url );

			System.out.println( "New URL: " + newUrl );

			//Crea a HttpMessage para la Comunicacion con la URL.
			HttpMessage mensaje = new HttpMessage( newUrl );
			
			//Construye una 'Properties' para la asignacion de los Parametros a enviar al 'Servlet'.
			Properties propiedad = new Properties();
			propiedad.put( "nombreApplet",   nombreApplet   );
			propiedad.put( "apellidoApplet", apellidoApplet );
			propiedad.put( "codigoApplet",   codigoApplet   );
			propiedad.put( "tipoConexion",   tipoConexion   );
			
			//Envia un message al 'Servlet', pasando el "Properties" como una Query String.
			InputStream entrada = mensaje.sendGetMessage( propiedad );

			//Obtiene la Respuesta del 'Servlet' por medio del 'ObjectInputStream'.
			ObjectInputStream resultado = new ObjectInputStream( entrada );

			//Lee el Objeto que llega y lo parsea.
			String nombreCompleto = (String)resultado.readObject();

			cadenaRetorno = nombreCompleto;
		} 
		catch( Exception e ){
			e.printStackTrace();
		}

		//Retorna un String al Applet.
		return cadenaRetorno;
	}	
	
	/**
	 * getDataUsingHttpObjectII enviado de datos a base de 'HttpObject'.
	 */
	private String getDataUsingHttpObjectII(){
		System.out.println( "DENTRO DE 'getDataUsingHttpObjectII'" );
		
		String cadenaRetorno = "";
		
		try{ 		
			Vector<String> listEnvioServlet = new Vector<String>();
			listEnvioServlet.addElement( "Ricardo Guerra" );
			listEnvioServlet.addElement( "Carlos Vera"    );
			listEnvioServlet.addElement( "Mario Cardenas" );
			
			//Envia datos Obtenidos al Servlet
			URLConnection urlConexion = this.getConexionServlet();
			OutputStream salida = urlConexion.getOutputStream();
			
			ObjectOutputStream objetoSalida = new ObjectOutputStream( salida );
			objetoSalida.writeObject( Integer.parseInt( "123456" ) );
			objetoSalida.writeObject( "JavaMan" );
			objetoSalida.writeObject( new Date() );
			objetoSalida.writeObject( listEnvioServlet );
			objetoSalida.flush();
			objetoSalida.close();

			//Recibe datos del Servlet.
			InputStream        entrada       = urlConexion.getInputStream();
			ObjectInputStream  objetoEntrada = new ObjectInputStream( entrada );
			
			Vector<String> listaRegresoServlet = (Vector<String>)objetoEntrada.readObject();
			objetoEntrada.close();
			entrada.close();
						
			//Muestra Resultados.
			for( int i=0; i<listaRegresoServlet.size(); i++ ){
				 String cadena = (String)listaRegresoServlet.get( i ); 
				
				 System.out.println( "Imprimiendo: " + cadena );
				 
				 if( cadena.equalsIgnoreCase( "Ricardo Guerra" ) ){
				 	 cadenaRetorno = cadena;
				 }				
			}
		} 
		catch( Exception e ){
			e.printStackTrace();
		}
		
		return cadenaRetorno;
	}
	
	/**
	 * getConexionServlet() consigue una conexion con el Servlet.
	 */
	private URLConnection getConexionServlet() throws MalformedURLException, IOException{
		System.out.println( "DENTRO DE 'getConexionServlet'" );
		
		//Obteniendo Parametros...
		String url_AppletServlet = (String)this.getParameter( "URL_AppletServlet" );
		String nombreApplet      = (String)this.getParameter( "NombreApplet" );
		String apellidoApplet    = (String)this.getParameter( "ApellidoApplet" );
		String codigoApplet      = (String)this.getParameter( "CodigoApplet" );
        String tipoConexion      = "HttpObjectII";   
		
		System.out.println( "URL_AppletServlet: " + url_AppletServlet );
		System.out.println( "NombreApplet: "      + nombreApplet );
		System.out.println( "ApellidoApplet: "    + apellidoApplet );
		System.out.println( "CodigoApplet: "      + codigoApplet );
		
		//JUGADA PARA AQUITAR EL: '../applet' DE LA URL DEL NAVEGADOR... 
		String newUrl = url_AppletServlet.replace( "applet/", "" );
		System.out.println( "NEW URL: " + newUrl );
		
		//Connection zum Servlet öffnen
		URL urlServlet = new URL( newUrl + "?nombreApplet="   + nombreApplet   + "&" +   
										    "apellidoApplet=" + apellidoApplet + "&" + 
											"codigoApplet="   + codigoApplet   + "&" +
											"tipoConexion="   + tipoConexion );		
		
		System.out.println("URL SERVLET: " + urlServlet );		
		URLConnection conexion = urlServlet.openConnection();

		conexion.setDoInput(   true  );
		conexion.setDoOutput(  true  );
		conexion.setUseCaches( false );
		conexion.setRequestProperty( "Content-Type", "application/x-java-serialized-object" );

		return conexion;
	}
	
	/**
	 * actionPerformed manejo de eventos de los botones.
	 */
	public void actionPerformed( ActionEvent e ){
		if( e.getSource().equals( this.getBtnProcesarHttpText() ) ){
			this.click_btnProcesarHttpText();
		}
		if( e.getSource().equals( this.getBtnProcesarHttpTextII() ) ){
			this.click_btnProcesarHttpTextII();
		}		
		if( e.getSource().equals( this.getBtnProcesarHttpObject() ) ){
			this.click_btnProcesarHttpObject();
		}		
		if( e.getSource().equals( this.getBtnProcesarHttpObjectII() ) ){
			this.click_btnProcesarHttpObjectII();
		}
	}
	
	/**
     * getBtnProcesarHttpObjectII(). 
     */	
	public JButton getBtnProcesarHttpObjectII() {
		return btnProcesarHttpObjectII;
	}
	
	/**
     * getBtnProcesarHttpText(). 
     */	
	public JButton getBtnProcesarHttpText(){
		return btnProcesarHttpText;
	}
	
	/**
     * getBtnProcesarHttpObject(). 
     */	
	public JButton getBtnProcesarHttpObject(){
		return btnProcesarHttpObject;
	}
	
	/**
     * getBtnProcesarHttpTextII(). 
     */	
	public JButton getBtnProcesarHttpTextII() {
		return btnProcesarHttpTextII;
	}  
	
	/**
     * getSerialVersionUID(). 
     */	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
     * ocultaGUI(). 
     */	
    public void ocultaGUI(){
	  
      this.txtHttpText.setVisible(   false );
	  this.txtHttpObject.setVisible( false );
	  
	  this.lblTexto_01.setVisible( false );
	  this.lblTexto_02.setVisible( false );
    }
    
	/**
     * muestraGUI(). 
     */	
    public void muestraGUI(){
      
  	  this.txtHttpText.setVisible(   true );
  	  this.txtHttpObject.setVisible( true );
  	  
  	  this.lblTexto_01.setVisible( true );
  	  this.lblTexto_02.setVisible( true );
    } 
    
}

/*************************************************/
/********************  CLASES ********************/
/*************************************************/

 class ImagePanel extends JPanel implements Serializable{

	private static final long serialVersionUID = -2602476963553627568L;
	
	public Image imagen = null;
	
	public ImagePanel(){
		this.setLayout( null );
		this.setSize( 100, 100 );
	}
	
	public void setImage( Image img ){
		this.imagen = img;
		this.repaint();
	}
	
	public void setImage( Image img, int ancho, int alto ){
		this.imagen = img;
		this.setSize( ancho, alto );
		this.repaint();
	}
	
	public void paint( Graphics g ){
		if( imagen != null ){
			g.drawImage( this.imagen, 0, 0, this );
		}
	}	
 }

