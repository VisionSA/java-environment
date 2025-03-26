
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
import netscape.javascript.*;
 
/**
 * A simple Java applet that demonstrates invoking a Javascript method
 */
public class SimpleApplet extends JApplet {
    private JTextField textA = new JTextField(5);
    private JTextField textB = new JTextField(5);
    private JButton button = new JButton("Sum");
 
    public void init() {
        // constructs the GUI
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(textA);
        getContentPane().add(textB);
        getContentPane().add(button);
 
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
    }
 
    public void buttonActionPerformed(ActionEvent evt) {
        int numberA = Integer.parseInt(textA.getText());
        int numberB = Integer.parseInt(textB.getText());
 
        try {
            JSObject jsObj = JSObject.getWindow(this);
 
            // calls Javascript method and gets return  value
            Object result = jsObj.call("add", new Integer[] {numberA, numberB});
 
            JOptionPane.showMessageDialog(this, "Sum is " + result.toString());
        } catch (JSException ex) {
            ex.printStackTrace();
        }
    }
}