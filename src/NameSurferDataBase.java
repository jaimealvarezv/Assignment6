/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */
import java.io.*;
import java.util.ArrayList;

import acm.util.*;

public class NameSurferDataBase implements NameSurferConstants {

    private ArrayList<String> dataFile;



/* Constructor: NameSurferDataBase(filename) */
/*
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		readFile(filename);
	}

 /**  Method: readFile(name) */
 /*
 * Reads the data from file and store it into a variable
  */
    private void readFile(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader surferDataBaseFile = new BufferedReader(fr);

            String line = surferDataBaseFile.readLine();

            while (line != null) {
                dataFile.add(line);
                line = surferDataBaseFile.readLine();
            }
            surferDataBaseFile.close();

        } catch (IOException ex) {
            throw new ErrorException(ex);
        }

    }

/** Method: findEntry(name) */
/*
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		return null;
	}
}

