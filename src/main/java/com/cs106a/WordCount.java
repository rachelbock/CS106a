package com.cs106a;

import acm.util.*;

import java.util.*;
import java.io.*;

import acm.program.ConsoleProgram;

/**
 * Created with IntelliJ IDEA.
 * User: nvt
 * Date: 4/26/15
 * Time: 5:29 PM
 */
public class WordCount extends ConsoleProgram {

    public void run() {
        ArrayList<String> words = new ArrayList<String>();

        try {

            FileReader reader = new FileReader("Lear.txt");
            BufferedReader rd = new BufferedReader(reader);

            int numLines = 0;
            int numChars = 0;
            int numWords = 0;
            while (true) {
                String lines = rd.readLine();


                if (lines == null) {
                    break;
                }
                int wordCount = NumWords(lines);
                numLines = numLines + 1;
                numChars = numChars + lines.length();
                numWords = numWords + wordCount;
            }

            println(numLines);
            println(numChars);
            println(numWords);
        } catch (IOException e) {
            System.out.println("file doesn't exist");
        }
    }

    public int NumWords(String line) {

        int words = 0;

        int i = 0;

        while (i < line.length()) {

            while (i < line.length () && Character.isLetterOrDigit(line.charAt(i))) {
             i = i + 1;
            }

            while (i < line.length () && Character.isLetterOrDigit(line.charAt(i)) == false) {
             i = i + 1;
            }

            words = words + 1;
        }
        return words;
    }

    public static void main(String[] args) {
        new WordCount().start();
    }
}


