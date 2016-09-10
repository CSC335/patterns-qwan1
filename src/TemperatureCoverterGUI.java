//Author:Qiming Wan
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TemperatureCoverterGUI  extends JFrame {
	
	  public static void main(String[] args) {
		  TemperatureCoverterGUI g = new TemperatureCoverterGUI();
		    g.setVisible(true);
		  }

	
		  public static final int width = 200;
		  public static final int height = 100;

		  public TemperatureCoverterGUI() {
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setSize(width, height);
		    this.setLocation(100, 40);
		    this.setTitle("CtoF FtoC");

		    setupCF();
		  }
		  
		 private  JLabel aLabel1;
		 private   JTextField textEditor1;
		    
		 private   JLabel aLabel2;
		 private  JTextField textEditor2;
		 
		 
	  private void setupCF(){
		  JPanel panel = new JPanel();
		    int size = 2;
		    panel.setLayout(new GridLayout(size, size, 5, 5));
		    ButtonListener aListener = new ButtonListener(); 
		 //   Font myFont = new Font("Arial", Font.TRUETYPE_FONT, 40);
		    
		     aLabel1 = new JLabel("Celsius"); 
		     textEditor1 = new JTextField(""); 
		    
		     aLabel2=new JLabel("Fahrenheit");
		     textEditor2 = new JTextField(""); 
		    
		    textEditor1.addActionListener(aListener);
		    textEditor2.addActionListener(aListener);
		    		    
		    panel.add(aLabel1);
		    panel.add(textEditor1);
			  
		    panel.add(aLabel2);
		    panel.add(textEditor2);
			  
		   add(panel); //add to Jframe
		  
	  }
	  private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			  
			 JTextField inputText = (JTextField) e.getSource();// TODO Auto-generated method stub
			if (inputText==textEditor1) {
				String text=textEditor1.getText();
				double temperature=Double.parseDouble(text);
				temperature=TemperatureConverter.CtoF(temperature);
				textEditor2.setText(""+temperature);
			}
			if (inputText==textEditor2){
				String text=textEditor2.getText();
				double temperature=Double.parseDouble(text);
				temperature=TemperatureConverter.FtoC(temperature);
				textEditor1.setText(""+temperature);
				
			}
			 
		}
		  
		  
	  }
	     // clickMeButton will call this method once registered (below) public void actionPerformed(ActionEvent anActionEvent) { // Show contents of the JTextArea JOptionPane.showMessageDialog(null, textEditor.getText()); } }
		// In the constructor of 
		  

	
}
