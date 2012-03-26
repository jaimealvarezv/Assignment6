/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;


public class NameSurfer extends Program implements NameSurferConstants {

    
    private JLabel labelName;
    private JTextField name;
    private JButton buttonGraph;
    private JButton buttonClear;
    

/** Method: init()  */
 /* This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */

	public void init() {

        // Set the default window size
        setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);

        labelName = new JLabel("Name:");
        add(labelName, SOUTH);

        name = new JTextField(25);
        add(name, SOUTH);

        buttonGraph = new JButton("Graph");
        add(buttonGraph, SOUTH);

        buttonClear = new JButton("Clear");
        add(buttonClear, SOUTH);

        addActionListeners();
        
	}


/** Method: actionPerformed(e) */
/*
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {


		if (e.getActionCommand().equals("Clear") ) {
            String msg = "Clear\n"+"Name: "+name.getText();
            JOptionPane.showMessageDialog(rootPane, msg , "Information", JOptionPane.INFORMATION_MESSAGE);

        }  else if (e.getActionCommand().equals("Graph") ) {
            
            //String line = "Sam 58 69 0 131 168 236 278 380 467 408 466";
            //NameSurferEntry ns = new NameSurferEntry(line);


            NameSurferEntry ns =  new NameSurferDataBase("src/names-data.txt").findEntry(name.getText());
            String msg = ns.toString();
            JOptionPane.showMessageDialog(rootPane, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
        }
	}


    public static void main(String[] args) {
        new NameSurfer().start(args);
    }


}
