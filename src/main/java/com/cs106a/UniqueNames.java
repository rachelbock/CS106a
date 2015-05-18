package com.cs106a;

/**
 * Created with IntelliJ IDEA.
 * User: nvt
 * Date: 5/4/15
 * Time: 7:57 PM
 */

import acm.util.*;

import java.util.*;
import java.io.*;

import acm.program.ConsoleProgram;

public class UniqueNames extends ConsoleProgram {

    public void run() {
        ArrayList<String> words = new ArrayList<String>();

        while (true) {
            String names = readLine("Enter Name: ");


            if (names.equals("")) {
                break;
            }

            if (!words.contains(names)) {
                words.add(names);
            }
        }
        println("Unique Name List");

        for (int i = 0; i < words.size(); i++) {
            String newName = words.get(i);
            println(newName);
        }
    }

    public static void main(String[] args) {
        new UniqueNames().start();
    }
}
