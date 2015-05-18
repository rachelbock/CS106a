package com.cs106a;

/**
 * Created with IntelliJ IDEA.
 * User: nvt
 * Date: 5/4/15
 * Time: 8:26 PM
 */


import java.util.*;
import java.io.*;


import acm.program.ConsoleProgram;

public class Histograms extends ConsoleProgram {
    public void run() {

        try {
            FileReader reader = new FileReader("midtermscores.txt");
            BufferedReader rd = new BufferedReader(reader);

            ArrayList<String> list = new ArrayList<String>();

            for (int i = 0; i < 11; i++) {
                if (i != 10) {
             list.add("" + (i*10) + "-" + (i*10 + 9) + ": ");
                }
                else {
                 list.add("  100: ");
                }
                }

            while (true) {
                String str = rd.readLine();

                if (str == null) {
                    break;
                }

                int score = Integer.parseInt(str);

                int scoreIndex = score / 10;

                String currentString = list.get(scoreIndex);
                list.set(scoreIndex, currentString + "*");

            }

            for (int i = 0; i < list.size(); i++) {
            println (list.get(i));
            }

        } catch (IOException e) {
            System.out.println("file doesn't exist");

        }

    }

    public static void main(String[] args) {
        new Histograms().start();
    }
}
