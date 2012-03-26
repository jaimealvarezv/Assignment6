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
import java.util.HashMap;

import acm.util.*;

public class NameSurferDataBase implements NameSurferConstants {


    private HashMap<String, NameSurferEntry> ranksMap = new HashMap<String, NameSurferEntry>();



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
 * Reads the data from file and store it into a HashMap
  */
    private void readFile(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader surferDataBaseFile = new BufferedReader(fr);

            String line = surferDataBaseFile.readLine();

            while (line != null) {
                // Puts the line and ranks into hashmap
                NameSurferEntry ns = new NameSurferEntry(line);
                ranksMap.put(ns.getName(), ns);

                // Reads the next line from the file
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

        // Capitalize the first char of the name to find it into the hash Map

        char[] stringArray = name.toCharArray();
        stringArray[0] = Character.toUpperCase(stringArray[0]);

        // Variable i must start in 1, remember that the position 0 is in Uppercase
        for (int i = 1; i < stringArray.length; i++ ) {
            stringArray[i] = Character.toLowerCase(stringArray[i]);
        }
        String key =  new String(stringArray);

        if (ranksMap.containsKey(key)) {
            NameSurferEntry value = ranksMap.get(key);
            return value;
        } else {
         	return null;
        }
	}



}

