package com.cs106a;

import acm.program.ConsoleProgram;

import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: nvt
 * Date: 3/30/15
 * Time: 8:12 PM
 */
public class CS106a extends ConsoleProgram {

// Exercise Character Arithmetic
// public char toHexDigit (int n) {
//
//        if (n >= 0 && n <=9) {
//            return (char) ('0' + n);
//        }
//        else if (n >= 10 && n <= 15) {
//            return (char) ('A' + n -10);
//        }
//        else {
//            return '?';
//        }
//
//    }
//
//    public void run () {
//       println(toHexDigit(13));
//    }
//
//    public void run () {
//        println ("This program reverses a string.");
//        String str = readLine ("Enter a string: ");
//        String rev = reverseString (str);
//        println (str + " spelled backwards is: " + rev);
//    }
//
//    private String reverseString (String str) {
//        String newstr = "";
//        for (int i = str.length() - 1; i >= 0; i--) {
//        newstr = newstr + str.charAt(i);
//        }
//    return newstr;
//    }
//
//    public void run () {
//        println ("This program translates a line into Pig Latin.");
//        String line = readLine("Enter a line: ");
//        println (translateLine(line));
//
//    }
//    private String translateLine (String line) {
//        String result = "";
//        StringTokenizer tokenizer = new StringTokenizer (line, DELIMITERS, true);
//        while (tokenizer.hasMoreTokens()) {
//            String token = tokenizer.nextToken();
//            if (isWord (token)) {
//                token = translateWord(token);
//            }
//            result += token;
//        }
//        return result;
//
//    }
//
//    private String translateWord(String word) {
//        int vp = findFirstVowel(word);
//        if (vp == -1) {
//            return word;
//        }
//        else if (vp == 0) {
//            return word + "way";
//        }
//        else {
//            String head = word.substring(0, vp);
//            String tail = word.substring(vp);
//            return tail + head + "ay";
//        }
//    }
//
//    private int findFirstVowel(String word) {
//        for (int i = 0; i < word.length(); i++) {
//            if (isEnglishVowel(word.charAt(i))) return i;
//        }
//        return -1;
//    }
//
//    private boolean isWord(String token) {
//        for (int i = 0; i < token.length(); i++) {
//            char ch = token.charAt(i);
//            if (!Character.isLetter(ch)) return false;
//        }
//        return true;
//    }
//
//
//
//    private boolean isEnglishVowel(char ch) {
//        switch (Character.toLowerCase(ch)) {
//            case 'a': case 'e': case 'i': case 'o': case 'u':
//                return true;
//            default:
//                return false;
//        }
//    }
//
//    private static final String DELIMITERS = ("!,.?" );



    public static void main(String[] args) {
        new CS106a().start();
    }
}

