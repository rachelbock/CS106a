package com.cs106a;
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

    private static final int NGUESSES = 8;

    public void init() {
        canvas = new HangmanCanvas();
        add(canvas);
    }

    public void run() {

        canvas.reset();
        println("Welcome to Hangman!");
        String nextGuess = "";
        for (int i = 0; i < targetWord.length(); i++) {
            nextGuess = nextGuess + "-";
        }

        while (playGame) {

            String afterGuess = gamePlay(nextGuess);

            if (wrongGuess == true) {
                guessesLeft = guessesLeft - 1;
            }

            if (playGame && guessesLeft !=0) {
                println("The word now looks like this: " + afterGuess);
                canvas.displayWord(afterGuess);

                if (guessesLeft == 1) {
                    println("You have only one guess left.");
                } else if (guessesLeft == 0) {
                } else {
                    println("You have " + guessesLeft + " guesses left.");
                }
//                println(targetWord);
            }

            nextGuess = afterGuess;

            firstRun = false;
            if (guessesLeft == 0) {
                println("You're Completely Hung");
                playGame = false;
                println("You Lose");
            }
            wrongGuess = false;

        }

    }


    public String gamePlay(String str) {

        String newstr = "";

        if (firstRun) {
            newstr = str;
        } else {
            if (firstRun == false) {
                char letter = readChar("Your guess: ");
                char UpperLetter = Character.toUpperCase(letter);
                for (int i = 0; i < str.length(); i++) {

                    if (str.charAt(i) == '-') {
                        if (UpperLetter == targetWord.charAt(i)) {
                            newstr = newstr + UpperLetter;

                        } else if (UpperLetter != targetWord.charAt(i)) {
                            newstr = newstr + "-";
                        }

                    } else {
                        newstr = newstr + str.charAt(i);
                    }
                }

                wrongGuess = true;
                for (int i = 0; i < targetWord.length(); i++) {
                    if (UpperLetter == targetWord.charAt(i)){
                    wrongGuess = false;
                    }
                }

                if (wrongGuess == true) {
                    println("There are no " + "\"" + UpperLetter + "'s\"" + "in the word.");

                } else {
                    println("That guess is correct");
                }
                if (newstr.equals(targetWord)) {
                    println("You guessed the word: " + newstr);
                    println("You Win");
                    playGame = false;
                }

            }
        }

        return newstr;

    }


    /*
    This function randomly selects the target word that the player is trying to guess.
     */

    public String Word() {
        int wordIndex = rgen.nextInt(0, words.getWordCount() - 1);
        String word = words.getWord(wordIndex);
        return word;
    }


    HangmanLexicon words = new HangmanLexicon();
    RandomGenerator rgen = new RandomGenerator();
    int guessesLeft = NGUESSES;
    String targetWord = Word();
    String lettersGuessed = "";
    boolean playGame = true;
    boolean firstRun = true;
    boolean wrongGuess = false;
    private HangmanCanvas canvas;

    public static void main(String[] args) {
        new Hangman().start();
    }

}
