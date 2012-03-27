/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import sun.tools.tree.NewArrayExpression;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {


    private ArrayList<NameSurferEntry> chartedItems = new ArrayList<NameSurferEntry>();
    // TODO: Create setColor Function private Color color = new Color(0,0,0);

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        //	 You fill in the rest //
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        chartedItems.clear();
        this.update();
    }

    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {

        // Check if the entry exists into the HashMap

        if (!chartedItems.contains(entry)) {
            // Add to a new hashMap the charted names
            chartedItems.add(entry);
        }
    }

    /* Method: deleteEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void deleteName(String name) {

        // Add to a new hashMap the charted names
        int index = -1;

        for(int i = 0; i < chartedItems.size(); i++) {
             if (chartedItems.get(i).getName() == name ) {
                 index = i;
                 break;
             }
        }
        chartedItems.remove(index);
        update();
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {


        // Removes all the graphics painted into canvas
        this.removeAll();

        // Draws the margin lines
        drawMarginLines();

        // Draws the decade lines
        drawDecadeLines();

        // Draws the decade labels
        drawDecadeLabels();

        // Draws all the names
        drawDecadeValues();

    }

    /** drawDecadeValues() */
    /**
     * Draws decade values for each name typed
     */

    private void drawDecadeValues() {

        int k = 0;
        int minY = GRAPH_MARGIN_SIZE;
        int maxY = getHeight() - (BOTTOM_MARGIN_SIZE * GRAPH_MARGIN_SIZE);
        int maxG = getHeight() - GRAPH_MARGIN_SIZE;

        for (int i = 0; i < chartedItems.size(); i++) {


            int x1 = 0;
            int x2 = getWidth() / NDECADES;

            int y1;
            int y2;

            int currentRank;
            int nextRank;

            // Set the new line Color
            // TODO: Set the Color size like FontSize
            k = (k < colours.length) ? k : 0;
            Color color = colours[k++];

            NameSurferEntry item = chartedItems.get(i);

            int j = 0;

            do  {

                // To generate graphics lines it is necessary to know the current rank and the next one
                currentRank = item.getRank(j);
                nextRank = item.getRank(j + 1);

                // If the rank is not between the firsts on thousand it must be at the bottom of the graphic
                // It is necessary scale the rank value into the graphic
                y1 = (currentRank == 0) ? maxG : minY + ((maxY * currentRank) / MAX_RANK);
                y2 = (nextRank == 0) ? maxG : minY + ((maxY * nextRank) / MAX_RANK);

                // Puts the labels using the decade's name
                String labelText = " " + item.getName() + ((currentRank == 0) ? " *" : " " + currentRank);

                GLabel label = new GLabel(labelText, x1, y1);
                label.setFont(setFontSize());
                label.setColor(color);
                add(label);

                // Draws the line
                GLine line = new GLine(x1, y1, x2, y2);
                line.setColor(color);
                add(line);

                // TODO: Set the point size like FontSize
                GOval point = new GOval(x1, y1, POINT_SIZE, POINT_SIZE);
                point.setColor(color);
                point.setFillColor(color);
                point.setFilled(true);
                add(point);

                // Calculates the new x-coordinate offset
                x1 += getWidth() / NDECADES;
                x2 += getWidth() / NDECADES;

                j++;

            }  while (j < (NDECADES - 1));

            // Draws the last label
            String labelText = " " + item.getName() + ((nextRank == 0) ? " *" : " " + nextRank);
            GLabel labelNext = new GLabel(labelText, x1, y2);
            labelNext.setFont(setFontSize());
            labelNext.setColor(color);
            add(labelNext);

            GOval point = new GOval(x1, y2, POINT_SIZE, POINT_SIZE);
            point.setColor(color);
            point.setFillColor(color);
            point.setFilled(true);
            add(point);

        }


    }


    /**
     * drawMarginLines()
     */
    /*
     * Draws the Top and bottom lines into canvas
     */
    private void drawMarginLines() {

        int x1 = 0;
        int y1 = GRAPH_MARGIN_SIZE;
        int x2 = getWidth();

        GLine hLine = new GLine(x1, y1, x2, y1);
        add(hLine);

        y1 = getHeight() - GRAPH_MARGIN_SIZE;
        hLine = new GLine(x1, y1, x2, y1);
        add(hLine);

    }

    /**
     * drawDecadeLines()
     */
    /*
     * Draws the decade lines into canvas
     */
    private void drawDecadeLines() {

        int y1 = 0;
        int y2 = getHeight();

        int offset = getWidth() / NDECADES;
        int x1 = offset;

        // Draws the Decades Lines
        for (int i = 0; i < NDECADES; i++) {

            GLine verticalLine = new GLine(x1, y1, x1, y2);
            add(verticalLine);
            x1 += offset;

        }
    }

    /**
     * drawDecadeLabels()
     */
    /*
     * Draws the decade Labels into canvas
     */
    private void drawDecadeLabels() {

        int offset = getWidth() / NDECADES;
        int x = LEFT_MARGIN_SIZE;
        int y = getHeight() - (GRAPH_MARGIN_SIZE / BOTTOM_MARGIN_SIZE);
        int decade = START_DECADE;

        for (int i = 0; i < NDECADES; i++) {

            // Puts the new label with the decade's number
            GLabel label = new GLabel("" + decade, x, y);
            label.setFont(setFontSize());
            add(label);

            // increase x coordinate and decade
            x += offset;
            decade += 10;

        }
    }

    /**
     * Set the better font size according to screen width
     * @return Adecuate font size
     */
    private String setFontSize() {
        double screenWidth = getWidth();
        int fontSize =  MEDIUM_FONT_SIZE;

        if (screenWidth < VERY_SMALL ) {
            fontSize = VERY_SMALL_FONT_SIZE;
        } if ((screenWidth > VERY_SMALL ) & (screenWidth <= SMALL_WIDTH )) {
            fontSize = SMALL_FONT_SIZE;
        } if ((screenWidth > SMALL_WIDTH ) & (screenWidth <= MEDIUM_WIDTH )) {
            fontSize = MEDIUM_FONT_SIZE;
        } if ((screenWidth > MEDIUM_WIDTH ) & (screenWidth <= LARGE_WIDTH )) {
            fontSize = LARGE_FONT_SIZE;
        }  if ((screenWidth > LARGE_WIDTH ) & (screenWidth <= VERY_LARGE_WIDTH )) {
            fontSize = VERY_LARGE_FONT_SIZE;
        }

        return new String("SansSerif-" + fontSize) ;
    }



    private Color setColor() {

      return new Color(0,0,0);


    }
    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
