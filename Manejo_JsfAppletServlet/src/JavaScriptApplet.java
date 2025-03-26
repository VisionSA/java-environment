
import java.awt.Color;
import javax.swing.*;

public class JavaScriptApplet extends JApplet{
	
	private JLabel lblMensaje = new JLabel( "Hola JAVAMAN" );
    private JPanel pnlGrafico = new JPanel(); 
	
	public void init(){
		this.creaGUI();
	}

	public void creaGUI(){
		this.lblMensaje.setForeground( Color.WHITE );
		
		this.pnlGrafico.setBackground( Color.BLACK );
		this.pnlGrafico.add( this.lblMensaje );
		
		this.add( this.pnlGrafico );
	}
	
	public void mensajeApplet(){
		this.lblMensaje.setText( "Adios JAVAMAN" );
	}
	
}