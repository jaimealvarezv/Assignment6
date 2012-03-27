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
    private JComboBox chartedNames;

    private NameSurferGraph graph = new NameSurferGraph();
    private NameSurferDataBase database =  new NameSurferDataBase("src/names-data.txt");


    

/** Method: init()  */
 /* This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */

	public void init() {

        // Set the default window size
        setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);

        labelName = new JLabel("Name:");
        add(labelName, SOUTH);

        // Adds the Input Text Box
        name = new JTextField(25);
        add(name, SOUTH);

        // Adds the Button Graph
        buttonGraph = new JButton("Graph");
        add(buttonGraph, SOUTH);

        // Adds the ComboBox to delete graphs
        chartedNames = new JComboBox();
        chartedNames.addItem("-- Clear Graph by Name --");
        chartedNames.setEditable(false);

        // Add the actions listener for the comboBox
        chartedNames.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteGraph();
            }
        });
        add(chartedNames, SOUTH);

        // Adds The Clear Button
        buttonClear = new JButton("Clear");
        add(buttonClear, SOUTH);


        // Adds the Canvas Object
        add(graph);

        // Add other action Listeners
        addActionListeners();
        
	}

    /**  deleteGraph()
     *
     * Delete the selected graph from canvas.
     */

    private void deleteGraph() {

        if (chartedNames.getSelectedIndex()!= 0 ) {
            int selectedItem = chartedNames.getSelectedIndex();
            graph.deleteName((String) chartedNames.getSelectedItem());
            chartedNames.removeItemAt(selectedItem);
        }


    }


/** Method: actionPerformed(e) */
/*
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Clear") ) {
            // Clear all the graphs
            graph.clear();

        }  else if (e.getActionCommand().equals("Graph") ) {

            NameSurferEntry ns =  database.findEntry(name.getText());

            if (ns == null) {
                JOptionPane.showMessageDialog(rootPane, "[" + name.getText()+ "] doesn't exist in database! ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                // Checks if the name exists in the ComboBox else add it.
                if (!nameExists(ns.getName(), chartedNames)) {
                    chartedNames.addItem(ns.getName());
                }
                // Adds the Entry to list and update the graph.
                graph.addEntry(ns);
                graph.update();
            }
        }
	}

    /** TODO: Create a new LocalUtils Class to add this function
     * Check if a string exists into ComboBox
     * @param str The string to find
     * @param comboBox The combobox to find into
     * @return true if str exists into combobox else false.
     */

    private boolean nameExists(String str, JComboBox comboBox) {

        boolean exists = false;

        for (int index = 0; index < comboBox.getItemCount() && !exists; index++) {
            if ( str.equals(comboBox.getItemAt(index)) ){
                exists = true;
            }
        }
        return exists;

    }

    public static void main(String[] args) {
        new NameSurfer().start(args);
    }


}
