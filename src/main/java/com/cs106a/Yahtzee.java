/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

    public static void main(String[] args) {
        new Yahtzee().start(args);
    }

    public void run() {
        IODialog dialog = getDialog();
        nPlayers = dialog.readInt("Enter number of players");
        playerNames = new String[nPlayers];
        for (int i = 1; i <= nPlayers; i++) {
            playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
        }
        display = new YahtzeeDisplay(getGCanvas(), playerNames);

        playGame();

    }

    private void playGame() {

        int dice[] = new int[5];

        for (int i = 1; i < nPlayers; i++) {
            display.waitForPlayerToClickRoll(i);

            for (int j = 0; j < dice.length; j++) {
                int diceNumber = rgen.nextInt(1, 6);
                dice[j] = diceNumber;
            }
            display.displayDice(dice);


            for (int j = 0; j <= 2; j++) {
            display.waitForPlayerToSelectDice();

            for (int k = 0; k < dice.length; k++) {
                if (display.isDieSelected(k) == true) {
                    dice[k] = rgen.nextInt(1, 6);
                } else {
                }
            }
            display.displayDice(dice);
            }

        }
    }

    /* Private instance variables */
    private int nPlayers;
    private String[] playerNames;
    private YahtzeeDisplay display;
    private RandomGenerator rgen = new RandomGenerator();

}
