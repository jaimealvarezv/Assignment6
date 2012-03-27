/*
 * File: NameSurferConstants.java
 * ------------------------------
 * This file declares several constants that are shared by the
 * different modules in the NameSurfer application.  Any class
 * that implements this interface can use these constants.
 */

import java.awt.*;

public interface NameSurferConstants {

/** The width of the application window */
	public static final int APPLICATION_WIDTH = 800;

/** The height of the application window */
	public static final int APPLICATION_HEIGHT = 600;

/** The name of the file containing the data */
	public static final String NAMES_DATA_FILE = "names-data.txt";

/** The first decade in the database */
	public static final int START_DECADE = 1900;

/** The number of decades */
	public static final int NDECADES = 11;

/** The maximum rank in the database */
	public static final int MAX_RANK = 1000;

/** The number of pixels to reserve at the top and bottom */
	public static final int GRAPH_MARGIN_SIZE = 20;

/** The number of pixels to reserve at the left of the graphic */
    public static final int LEFT_MARGIN_SIZE = 2;

/** The number of pixels to reserve at the bottom of the graphic */
    public static final int BOTTOM_MARGIN_SIZE = 3;

/** Font Sizes */
    public static final int VERY_SMALL_FONT_SIZE = 6;
    public static final int SMALL_FONT_SIZE = 8;
    public static final int MEDIUM_FONT_SIZE = 10;
    public static final int LARGE_FONT_SIZE = 12;
    public static final int VERY_LARGE_FONT_SIZE = 14;

    /** Resolution Sizes */
    public static final int VERY_SMALL = 600;
    public static final int SMALL_WIDTH = 800;
    public static final int MEDIUM_WIDTH = 1024;
    public static final int LARGE_WIDTH = 1090;
    public static final int VERY_LARGE_WIDTH = 2010;

    /** Point Size */
    public static final double POINT_SIZE = 3.0;

/** The Colors for graphics */
    public static final Color[] colours = {
        Color.BLACK, Color.RED, Color.GREEN, Color.ORANGE, Color.BLUE};
}
