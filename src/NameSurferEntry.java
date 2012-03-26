/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;

import javax.swing.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

    private String personName = "";
    private int[] popularityRanks = new int [NDECADES];

    /** Constructor: NameSurferEntry(line) */
    /*
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */

    public NameSurferEntry(String line) {

        // Get the person's name
        personName = line.substring(0, line.indexOf(" "));

        // Get the ranks string and convert it to un array of integers
        String ranks = line.substring(line.indexOf(" ") + 1, line.length());
        StringTokenizer tokenizer = new StringTokenizer(ranks, " ");

        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken(); // Get next Token
            int rank = Integer.parseInt(token);   // Convert the item to Integer
            popularityRanks[i++] = rank;          // Fill the array with the correct values
        }

    }


    /** Method: getName() */
    /*
     * Returns the name associated with this entry.
     */
    public String getName() {

        return personName;
    }


    /** Method: getRank(decade) */
    /*
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return popularityRanks[decade];
    }

    /** Method: toString() */
    /*
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */

    public String toString() {
        
        String entry = personName + " [";

        for ( int i=0; i < popularityRanks.length; i++) {
            // Concatenate the rank values into a variable
            entry +=  popularityRanks[i];

            // Add a trailer space to the string if is not the last item
            entry += (i < popularityRanks.length - 1)? " ": "";
        }

        return (entry += "]");
    }
}

