package com.cs106a;/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.ErrorException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        String [] sections = line.split(" ");
        name = sections[0];

        for (int i = 0; i < NDECADES; i ++ ) {
            rankByDecade[i] = Integer.parseInt(sections[i+1]);
        }

    }

/* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

/* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        int rank = rankByDecade[decade];

        return rank;
    }

/* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {

        String str = "";

        for (int i = 0; i < NDECADES; i ++) {
            str = str + rankByDecade[i] + " ";
        }

        return (name + " " + str);
    }

    private String name;
    private int [] rankByDecade = new int [NDECADES];
}

