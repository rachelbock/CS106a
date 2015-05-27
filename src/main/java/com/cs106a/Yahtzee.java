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
        for (int j = 0; j < N_SCORING_CATEGORIES; j++) {
            for (int i = 1; i <= nPlayers; i++) {
                int player = i;
                display.printMessage("Click on Roll Dice to begin");
                display.waitForPlayerToClickRoll(i);
                firstRoll();
                nextRolls(player);

            }
        }
    }


    public void firstRoll() {

//        for (int j = 0; j < dice.length; j++) {
//            int diceNumber = rgen.nextInt(1, 6);
//            dice[j] = diceNumber;
//        }
        display.displayDice(dice);
    }


    public void nextRolls(int player) {
        for (int j = 0; j < 2; j++) {
            display.waitForPlayerToSelectDice();

            for (int k = 0; k < dice.length; k++) {
                if (display.isDieSelected(k) == true) {
                    dice[k] = rgen.nextInt(1, 6);
                } else {
                }
            }
            display.displayDice(dice);
        }
        int category = display.waitForPlayerToSelectCategory();

        display.updateScorecard(category, player, scoring(category));

    }



    public int scoring(int category) {
        int score = 0;
        if (validation(category)) {
            if (category <= 6) {
                int sum = 0;
                for (int i = 0; i < dice.length; i++) {
                    if (category == dice[i]) {
                        sum = sum + dice[i];
                    }
                }
                score = sum;
            }

//            if (category == THREE_OF_A_KIND) {
//                int num = 0;
//                int counterArray[] = new int[7];
//                for (int i = 0; i < dice.length; i++) {
//                counterArray[dice[i]] = counterArray[dice[i]] + 1;
//            }
//                for (int j = 0; j < counterArray.length; j++) {
//                    if (counterArray[j] >=3) {
//                        num = j * 3;
//                    }
//                }
//                score = num;
//            }

            if (category == THREE_OF_A_KIND) {
                int sum = 0;
                for (int i = 0; i < dice.length; i++) {
                    sum = sum + dice[i];
                }
                score = sum;
            }

            if (category == FOUR_OF_A_KIND) {
                int sum = 0;
                for (int i = 0; i < dice.length; i++) {
                    sum = sum + dice[i];
                }
                score = sum;
            }

            if (category == FULL_HOUSE) {
                score = score + 25;
            }
            if (category == SMALL_STRAIGHT) {
                score = score + 30;
            }
            if (category == LARGE_STRAIGHT) {
                score = score + 40;
            }
            if (category == YAHTZEE) {
                score = score + 50;
            }
            if (category == CHANCE) {
                int sum = 0;
                for (int i = 0; i < dice.length; i++) {
                    sum = sum + dice[i];
                }
                score = sum;
            }

        }
        return score;
    }

    public boolean validation(int category) {
        boolean valid = false;
        boolean threeTheSame = false;
        boolean twoTheSame = false;
        int diceCounter = 0;


// Single Number Categories
        for (int i = 0; i < dice.length; i++) {
            for (int j = 1; j <= 6; j++) {
                if (category == j && dice[i] == j) {
                    valid = true;
                }
            }
        }

// Three of a Kind & Four of a Kind
        for (int j = 1; j <= 6; j++) {
            for (int i = 0; i < dice.length; i++) {
                if (dice[i] == j) {
                    diceCounter = diceCounter + 1;
                }
            }
            if (diceCounter >= 3 && category == THREE_OF_A_KIND) {
                valid = true;
            }
            if (diceCounter >= 4 && category == FOUR_OF_A_KIND) {
                valid = true;
            }
            diceCounter = 0;
        }

// Full House

        for (int j = 1; j <= 6; j++) {
            for (int i = 0; i < dice.length; i++) {
                if (dice[i] == j) {
                    diceCounter = diceCounter + 1;
                }

            }
            if (diceCounter == 3) {
                threeTheSame = true;
            }

            if (diceCounter == 2) {
                twoTheSame = true;
            }
            diceCounter = 0;
        }
        if (threeTheSame && twoTheSame && category == FULL_HOUSE) {
            valid = true;
        }

        int min = 0;
        int max = 0;
        boolean duplicates = false;


//Small Straight
        int counterArray[] = new int[7];
        boolean isGood = true;
        int maxCounter = 4;
        if (category == SMALL_STRAIGHT) {

            for (int i = 0; i < dice.length; i++) {
                counterArray[dice[i]] = counterArray[dice[i]] + 1;
            }
            for (int k = 0; k < 3; k++) {
                for (int j = k + 1; j <= maxCounter; j++) {
                    if (counterArray[j] == 0) {
                        isGood = false;
                    }
                }
                maxCounter = maxCounter + 1;
                if (isGood) {
                    valid = true;
                }
                isGood = true;
            }

        }

//Large Straight
        if (category == LARGE_STRAIGHT) {

            for (int j = 0; j < dice.length; j++) {

                if (dice[j] == 1) {
                    min = 1;
                }
                if (dice[j] == 6) {
                    max = 6;
                }

                for (int i = j + 1; i < dice.length; i++) {
                    if (dice[i] == dice[j]) {
                        duplicates = true;
                    }
                }
            }
            if (duplicates == false && min == 1 && max != 6) {
                valid = true;
            }
            if (duplicates == false && min != 1 && max == 6) {
                valid = true;
            }
        }

//Yahtzee
        int sameNum = 0;
        if (category == YAHTZEE) {
            for (int j = 1; j <= 6; j++) {
                for (int i = 0; i < dice.length; i++) {
                    if (dice[i] == j) {
                        sameNum = sameNum + 1;
                    }
                    if (sameNum == 5) {
                        valid = true;
                    }

                }
                sameNum = 0;
            }
        }

//Chance
        if (category == CHANCE) {
            valid = true;
        }

        return valid;
    }


    /* Private instance variables */
    private int nPlayers;
    private String[] playerNames;
    private YahtzeeDisplay display;
    private RandomGenerator rgen = new RandomGenerator();
    private int dice[] = new int[]{5, 5, 5, 5, 4};


}
