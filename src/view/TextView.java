//Author:Qiming Wan
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.OurObserver;
import model.ComputerPlayer;
import model.TicTacToeGame;


public class TextView extends JPanel implements OurObserver{
	 private TicTacToeGame theGame;
	  private JButton stateButton = new JButton("Click your move");
	  private JTextArea[][] buttons = null;
	  private JTextField jTextEditor1 = new JTextField();
	  private JTextField jTextEditor2 = new JTextField();
	  private JLabel row= new JLabel("row");
	  private JLabel column= new JLabel("column");
	  private int rowNum=-100;
	  private int colNum=-100;
			  
	  
	  private ComputerPlayer computerPlayer;
	  private int height, width;

	  public TextView(TicTacToeGame TicTacToeGame, int width, int height) {
	    theGame = TicTacToeGame;
	    this.height = height;
	    this.width = width;
	    computerPlayer = theGame.getComputerPlayer();
	    this.setBackground(Color.BLUE);
	    initializeButtonPanel();
	  }

	  // This method is called by OurObservable's notifyObservers()
	  public void update() {
	    if (theGame.maxMovesRemaining() == theGame.size() * theGame.size()) {
	    stateButton.setEnabled(true);
	      resetButtons(true);
	    }

	    if (!theGame.stillRunning()){
	    	   if (theGame.tied()) {
	   	        stateButton.setText("Tied");
	   	      
	   	      }
	   	      else if (theGame.didWin('X')) {
	   	        stateButton.setText("X wins");
	   	    
	   	   
	   	      }
	   	      else  {
	   	     
	   	          stateButton.setText("O wins");
	   	     
	   	        }
	   	        stateButton.setEnabled(false);
	   	        jTextEditor1.setEnabled(false);
	   	        jTextEditor2.setEnabled(false);
	    	updateButtons();
	      resetButtons(false);
	    }
	    else {
	      updateButtons();
	      stateButton.setText("Click your move");
	    }
	  }

	  private void initializeButtonPanel() {
		  
	      this.add(jTextEditor1);
	      jTextEditor1.setSize(50, 20);
	      jTextEditor1.setLocation(20, 10);
	      row.setLocation(80,10);
	      column.setLocation(80,35);
	      jTextEditor1.setFont(new Font("Arial", Font.PLAIN, 10));
	      this.add(jTextEditor2);
	      this.add(column);
	      this.add(row);
	      row.setSize(50, 20);
	      column.setSize(50,20);
	      
	      jTextEditor2.setLocation(20, 35);
	      jTextEditor2.setSize(50, 20);
	      jTextEditor2.setFont(new Font("Arial", Font.PLAIN, 10));
	      
		  
		  this.add(row);
		  this.add(column);
		    
		  stateButton.addActionListener(new ButtonListener());
		    stateButton.setSize(125, 20);
		    stateButton.setFont(new Font("Arial", Font.PLAIN, 10));
		    stateButton.setEnabled(true);
		    
		    stateButton.setLocation(140, 20);
		    this.add(stateButton);
	    JPanel buttonPanel = new JPanel();
	    int size = theGame.size();
	    buttonPanel.setLayout(new GridLayout(size, size, 0, 0));
	    
	    Font myFont = new Font("Courier", Font.BOLD, 36);
	    buttons = new JTextArea[size][size];
	    for (int i = 0; i < size; i++) {
	      for (int j = 0; j < size; j++) {
	        buttons[i][j] = new JTextArea();
	        buttons[i][j].setFont(myFont);
	        buttons[i][j].setEditable(false);	        
	        buttonPanel.add(buttons[i][j]);
	      }
	    }
	    this.setLayout(null);
	    buttonPanel.setLocation(40, 80);
	    buttonPanel.setSize(width - 90, height - 168);
	    this.add(buttonPanel);
	 
	    resetButtons(true);
	  }

	  // Mark each selected square with an X or an O and prevent
	  // selection of the selected squares with seDisabled(true)
	  public void updateButtons() {
	    char[][] temp = theGame.getTicTacToeBoard();
	    for (int i = 0; i < temp.length; i++) {
	      for (int j = 0; j < temp[i].length; j++) {
	        String text = "" + temp[i][j];
	        if (text.equals("X") || text.equals("O")) {
	          buttons[i][j].setText("  "+text);
	        }
	      }
	    }
	  }

	  private void resetButtons(boolean enable) {
	    for (int i = 0; i < theGame.size(); i++) {
	      for (int j = 0; j < theGame.size(); j++) {
	    	  if (enable==true) {   
	   	        jTextEditor1.setEnabled(true);
	   	        jTextEditor2.setEnabled(true);
	        buttons[i][j].setText("  _");   
	    	  }
	    	  if (enable==false) {
	    	stateButton.setEnabled(false);	       /////////////////////
	    	  }
	      }
	    }
	  }

	  private class ButtonListener implements ActionListener {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
	    	
	      boolean normal=true;
	      
	     if (!jTextEditor1.getText().trim().equals("")) 
	    	  rowNum=Integer.parseInt(jTextEditor1.getText().trim());
	     if (!jTextEditor2.getText().trim().equals(""))
	    	  colNum=Integer.parseInt(jTextEditor2.getText().trim());
	      
	    	if (colNum==-100 && rowNum==-100)		{
	      JOptionPane.showMessageDialog(new JFrame(), "It is empty inputs");
	    	normal=false;
	    	}
	    	else if (colNum==-100 && rowNum==-100)		{
	    		normal=false;
	    		JOptionPane.showMessageDialog(new JFrame(), "One input is empty");	
	    	}
	       
	    	
	      
	    	else if (rowNum <0 || rowNum>=buttons.length || colNum <0 || colNum>=buttons[0].length){
	    		normal=false;
	    		 JOptionPane.showMessageDialog(new JFrame(), "Selection is not available");
	   	      
	    	}
	 	      
	        else{
	        	char[][] temp = theGame.getTicTacToeBoard();
	          if (temp[rowNum][colNum]!='X' && temp[rowNum][colNum]!='O') {
	            theGame.choose(rowNum, colNum);
	            jTextEditor1.setText("");
	            jTextEditor2.setText("");
	          colNum=-100;
	          rowNum=-100;
	          }
	          else {
	        	  JOptionPane.showMessageDialog(new JFrame(), "Selection are not available");
	        	  normal=false;
	        	  colNum=-100;
		          rowNum=-100;
	          }
	        }
	      

	      if (theGame.tied()) {
	        stateButton.setText("Tied");
	        jTextEditor1.setEnabled(false);
	        jTextEditor2.setEnabled(false);
	        stateButton.setEnabled(false);
	        updateButtons();
	      }
	      else if (theGame.didWin('X')) {
	        stateButton.setText("X wins");
	        stateButton.setEnabled(false);
	        jTextEditor1.setEnabled(false);
	        jTextEditor2.setEnabled(false);
	        updateButtons();
	      }
	      else  {
	        // If the game is not over, let the computer player choose
	        // This algorithm assumes the computer player always
	        // goes after the human player and is represented by 'O', not 'X'
	    	  if (normal==true) {
	        Point play = computerPlayer.desiredMove(theGame);
	        theGame.choose(play.x, play.y);
	        if (theGame.didWin('O')) {
	          stateButton.setText("O wins");
	          stateButton.setEnabled(false);
	          jTextEditor1.setEnabled(false);
		        jTextEditor2.setEnabled(false);
	          updateButtons();
	        }
	        }
	      }
	    }

//	    private void setButtonsDisabled() {
//	      for (int i = 0; i < buttons.length; i++)
//	        for (int j = 0; j < buttons[i].length; j++)
//	          buttons[i][j].setEnabled(false);
//	    }
	  }
}
