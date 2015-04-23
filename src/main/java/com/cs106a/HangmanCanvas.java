package com.cs106a;

/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

    /**
     * Resets the display so that only the scaffold appears
     */
    public void reset() {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int scaffoldY1 = centerY + centerY / 2;
        int scaffoldY2 = scaffoldY1 - SCAFFOLD_HEIGHT;

        GLine scaffold = new GLine(centerX - BEAM_LENGTH, scaffoldY1, centerX - BEAM_LENGTH, scaffoldY2);
        GLine beam = new GLine(centerX - BEAM_LENGTH, scaffoldY2, centerX, scaffoldY2);
        GLine rope = new GLine(centerX, scaffoldY2, centerX, scaffoldY2 + ROPE_LENGTH);
        add(scaffold);
        add(beam);
        add(rope);
    }

    /**
     * Updates the word on the screen to correspond to the current
     * state of the game.  The argument string shows what letters have
     * been guessed so far; unguessed letters are indicated by hyphens.
     */
    public void displayWord(String word) {
        guessWord.setLabel(word);
        guessWord.setLocation(20, 450);
        guessWord.setFont("SansSerif-bold-20");
        add(guessWord);
    }

    /**
     * Updates the display to correspond to an incorrect guess by the
     * user.  Calling this method causes the next body part to appear
     * on the scaffold and adds the letter to the list of incorrect
     * guesses that appears at the bottom of the window.
     */
    public void noteIncorrectGuess(char letter) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int quarter = centerY + centerY / 2;
        int HeadY = (quarter - SCAFFOLD_HEIGHT);
        int BodyY = HeadY + ROPE_LENGTH + HEAD_RADIUS * 2;
        int LegY2 = BodyY + BODY_LENGTH + LEG_LENGTH;

        GRect wrongGuessBox = new GRect (250, 420, 87, 50);
        add (wrongGuessBox);

        incorrectGuesses = incorrectGuesses + letter;
        incorrectGuessBox.setLabel(incorrectGuesses);
        incorrectGuessBox.setLocation(260, 450);
        add(incorrectGuessBox);

        countIncorrectGuesses = countIncorrectGuesses + 1;

        if (countIncorrectGuesses == 1) {
            GOval Head = new GOval(centerX - HEAD_RADIUS, HeadY + ROPE_LENGTH, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
            add(Head);
        }
        if (countIncorrectGuesses == 2) {
            GLine Body = new GLine(centerX, BodyY, centerX, BodyY + BODY_LENGTH);
            add(Body);
        }
        if (countIncorrectGuesses == 3) {
            GLine RUArm = new GLine(centerX, BodyY + ARM_OFFSET_FROM_HEAD, centerX - UPPER_ARM_LENGTH, BodyY + ARM_OFFSET_FROM_HEAD);
            add(RUArm);
            GLine RLArm = new GLine(centerX - UPPER_ARM_LENGTH, BodyY + ARM_OFFSET_FROM_HEAD, centerX - UPPER_ARM_LENGTH, BodyY + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
            add(RLArm);
        }
        if (countIncorrectGuesses == 4) {
            GLine LUArm = new GLine(centerX, BodyY + ARM_OFFSET_FROM_HEAD, centerX + UPPER_ARM_LENGTH, BodyY + ARM_OFFSET_FROM_HEAD);
            add(LUArm);
            GLine LLArm = new GLine(centerX + UPPER_ARM_LENGTH, BodyY + ARM_OFFSET_FROM_HEAD, centerX + UPPER_ARM_LENGTH, BodyY + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
            add(LLArm);
        }
        if (countIncorrectGuesses == 5) {
            GLine LHip = new GLine (centerX, BodyY + BODY_LENGTH, centerX - HIP_WIDTH, BodyY + BODY_LENGTH);
            add(LHip);
            GLine LLeg = new GLine (centerX - HIP_WIDTH, BodyY + BODY_LENGTH, centerX - HIP_WIDTH, LegY2);
            add(LLeg);

        }
        if (countIncorrectGuesses == 6) {
            GLine RHip = new GLine (centerX, BodyY + BODY_LENGTH, centerX + HIP_WIDTH, BodyY + BODY_LENGTH);
            add(RHip);
            GLine RLeg = new GLine (centerX + HIP_WIDTH, BodyY + BODY_LENGTH, centerX + HIP_WIDTH, LegY2);
            add (RLeg);
        }
        if (countIncorrectGuesses == 7) {
            GLine LFoot = new GLine (centerX - HIP_WIDTH, LegY2, centerX - HIP_WIDTH - FOOT_LENGTH, LegY2);
            add(LFoot);
        }
        if (countIncorrectGuesses == 8) {
            GLine RFoot = new GLine (centerX + HIP_WIDTH, LegY2, centerX + HIP_WIDTH + FOOT_LENGTH, LegY2);
            add(RFoot);
        }

    }


    String incorrectGuesses = "";
    GLabel incorrectGuessBox = new GLabel(incorrectGuesses);
    int countIncorrectGuesses = 0;



    GLabel guessWord = new GLabel("");

    /* Constants for the simple version of the picture (in pixels) */
    private static final int SCAFFOLD_HEIGHT = 360;
    private static final int BEAM_LENGTH = 144;
    private static final int ROPE_LENGTH = 18;
    private static final int HEAD_RADIUS = 36;
    private static final int BODY_LENGTH = 144;
    private static final int ARM_OFFSET_FROM_HEAD = 28;
    private static final int UPPER_ARM_LENGTH = 72;
    private static final int LOWER_ARM_LENGTH = 44;
    private static final int HIP_WIDTH = 36;
    private static final int LEG_LENGTH = 108;
    private static final int FOOT_LENGTH = 28;

}
