package com.cs106a;

/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;

import java.util.*;
import java.io.*;

public class HangmanLexicon {

    ArrayList<String> strList = new ArrayList<String>();

    public HangmanLexicon() {

        try {

            FileReader reader = new FileReader("HangmanLexicon.txt");
            BufferedReader rd = new BufferedReader(reader);


            while (true) {
                String str = rd.readLine();
                if (str == null) {
                    break;
                }
                strList.add(str);
            }
        } catch (IOException e) {
            System.out.println("file doesn't exist");
        }
    }


    /**
     * Returns the number of words in the lexicon.
     */
    public int getWordCount() {
        return strList.size();
    }

    /**
     * Returns the word at the specified index.
     */
    public String getWord(int index) {
        return strList.get(index);

    }


}