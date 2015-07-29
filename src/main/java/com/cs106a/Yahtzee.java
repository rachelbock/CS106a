/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import com.cs106a.ScoreResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
        scoreArray = new int[nPlayers + 1][YahtzeeConstants.N_CATEGORIES];
        for (int j = 0; j < nPlayers + 1; j++) {
            for (int i = 0; i < YahtzeeConstants.N_CATEGORIES; i++) {
                scoreArray[j][i] = -1;
            }
        }

        playGame();

    }

    private void playGame() {
        //for (int j = 0; j < YahtzeeConstants.N_SCORING_CATEGORIES; j++) {
        for (int j = 0; j < 3; j++) {
            for (int i = 1; i <= nPlayers; i++) {
                int player = i;
                display.printMessage(playerNames[i - 1] + " click on Roll Dice to begin your turn");
                display.waitForPlayerToClickRoll(i);
                firstRoll();
                nextRolls(player);

            }
        }
        totalScore();
        ScoreResult result = totalScore();
        display.printMessage(playerNames[result.getPlayerHighScore() - 1] + " is the winner " + result.getHighestScore() + " is the score");

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> scores = new ArrayList<Integer>();
        readHighScores("src/main/resources/YahtzeeScores", names, scores);
        insertNewScore(names, scores, playerNames[result.getPlayerHighScore() - 1], result.getHighestScore());
        writeHighScores("src/main/resources/YahtzeeScores", names, scores);

    }


    private void firstRoll() {

        for (int j = 0; j < dice.length; j++) {
            int diceNumber = rgen.nextInt(1, 6);
            dice[j] = diceNumber;
        }
        display.displayDice(dice);
    }


    private void nextRolls(int player) {

        for (int j = 0; j < 2; j++) {
            display.printMessage("Select which dice to re-roll or select none and select Roll Dice");
            display.waitForPlayerToSelectDice();

            for (int k = 0; k < dice.length; k++) {
                if (display.isDieSelected(k) == true) {
                    dice[k] = rgen.nextInt(1, 6);
                } else {
                }
            }
            display.displayDice(dice);
        }
        display.printMessage("Select an unused category");
        while (true) {
            int category = display.waitForPlayerToSelectCategory();
            if (scoreArray[player][category] == -1) {
                scoring(category, player);
                display.updateScorecard(category, player, scoreArray[player][category]);
                break;
            } else {
                display.printMessage("Category already used, please select another category.");
            }
        }
    }


    private void scoring(int category, int player) {

        if (validation(category)) {
            if (category <= 6) {
                int sum = 0;
                for (int i = 0; i < dice.length; i++) {
                    if (category == dice[i]) {
                        sum = sum + dice[i];
                    }
                }
                scoreArray[player][category] = sum;

            }


            if (category == YahtzeeConstants.THREE_OF_A_KIND) {
                int sum = 0;
                for (int i = 0; i < dice.length; i++) {
                    sum = sum + dice[i];
                }
                scoreArray[player][category] = sum;

            }

            if (category == YahtzeeConstants.FOUR_OF_A_KIND) {
                int sum = 0;
                for (int i = 0; i < dice.length; i++) {
                    sum = sum + dice[i];
                }
                scoreArray[player][category] = sum;
            }

            if (category == YahtzeeConstants.FULL_HOUSE) {
                scoreArray[player][category] = 25;
            }
            if (category == YahtzeeConstants.SMALL_STRAIGHT) {
                scoreArray[player][category] = 30;
            }
            if (category == YahtzeeConstants.LARGE_STRAIGHT) {
                scoreArray[player][category] = 40;
            }
            if (category == YahtzeeConstants.YAHTZEE) {
                scoreArray[player][category] = 50;
            }
            if (category == YahtzeeConstants.CHANCE) {
                int sum = 0;
                for (int i = 0; i < dice.length; i++) {
                    sum = sum + dice[i];
                }
                scoreArray[player][category] = sum;
            }

        } else {
            scoreArray[player][category] = 0;
        }

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
            if (diceCounter >= 3 && category == YahtzeeConstants.THREE_OF_A_KIND) {
                valid = true;
            }
            if (diceCounter >= 4 && category == YahtzeeConstants.FOUR_OF_A_KIND) {
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
        if (threeTheSame && twoTheSame && category == YahtzeeConstants.FULL_HOUSE) {
            valid = true;
        }

        int min = 0;
        int max = 0;
        boolean duplicates = false;


//Small Straight
        int counterArray[] = new int[7];
        boolean isGood = true;
        int maxCounter = 4;
        if (category == YahtzeeConstants.SMALL_STRAIGHT) {

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
        if (category == YahtzeeConstants.LARGE_STRAIGHT) {

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
        if (category == YahtzeeConstants.YAHTZEE) {
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
        if (category == YahtzeeConstants.CHANCE) {
            valid = true;
        }

        return valid;
    }

    private ScoreResult totalScore() {
        int upperScore = 0;
        int lowerScore = 0;
        int upperBonus = 0;
        int totalScores = 0;
        int HighestScore = 0;
        int playerHighScore = 0;
        for (int j = 1; j <= nPlayers; j++) {
            for (int i = 1; i < YahtzeeConstants.N_CATEGORIES; i++) {
                if (scoreArray[j][i] != -1 && i <= 6) {
                    upperScore = upperScore + scoreArray[j][i];
                }
                if (scoreArray[j][i] != -1 && i >= 9) {
                    lowerScore = lowerScore + scoreArray[j][i];
                }
            }
            display.updateScorecard(YahtzeeConstants.UPPER_SCORE, j, upperScore);
            if (upperScore >= 63) {
                upperBonus = upperBonus + 35;
                scoreArray[j][YahtzeeConstants.UPPER_BONUS] = upperBonus;
                display.updateScorecard(YahtzeeConstants.UPPER_BONUS, j, upperBonus);
            } else {
                display.updateScorecard(YahtzeeConstants.UPPER_BONUS, j, upperBonus);
            }
            display.updateScorecard(YahtzeeConstants.LOWER_SCORE, j, lowerScore);
            totalScores = upperScore + lowerScore + upperBonus;
            display.updateScorecard(YahtzeeConstants.TOTAL, j, totalScores);
            upperScore = 0;
            lowerScore = 0;
            upperBonus = 0;
            if (totalScores >= HighestScore) {
                HighestScore = totalScores;
                playerHighScore = j;
            }
        }

        ScoreResult result = new ScoreResult(HighestScore, playerHighScore);
        return result;


    }

    public void readHighScores(String path, ArrayList<String> names, ArrayList<Integer> scores) {
        try {

            FileReader reader = new FileReader(path);
            BufferedReader rd = new BufferedReader(reader);
            while (true) {
                String str = rd.readLine();
                if (str == null) {
                    break;
                }


                String[] splitString = str.split(" ");
                names.add(splitString[0]);

                String scoreStr = splitString[1];
                int score = Integer.parseInt(scoreStr);
                scores.add(score);

            }

        } catch (IOException e)

        {
            System.out.println("file doesn't exist");
        }


    }

    public void writeHighScores(String path, ArrayList<String> names, ArrayList<Integer> scores) {

        try {

            FileWriter fWriter = new FileWriter(path);
            BufferedWriter writer = new BufferedWriter(fWriter);

            for (int i = 0; i < names.size(); i++) {
                writer.write(names.get(i) + " " + scores.get(i) + "\n");
            }

            writer.close();
            fWriter.close();


        } catch (IOException e) {
            System.out.println("File doesn't exist");
        }
    }

    public void insertNewScore(ArrayList<String> names, ArrayList<Integer> scores, String newName, int newScore) {

        for (int i = 0; i < scores.size(); i++) {
            if (newScore >= scores.get(i)) {
                scores.add(i, newScore);
                names.add(i, newName);
                break;
            }

        }

        if (names.size() == 0) {
            names.add(0, newName);
            scores.add(0, newScore);
        } else {
            if (scores.get(scores.size() - 1) >= newScore) {
                scores.add(scores.size(), newScore);
                names.add(names.size(), newName);
            }

        }
    }


    /* Private instance variables */
    private int nPlayers;
    private String[] playerNames;
    private YahtzeeDisplay display;
    private RandomGenerator rgen = new RandomGenerator();
    private int dice[] = new int[5];
    private int scoreArray[][];

    @Test
    public void readHighScoresTest() {
        ArrayList<String> strList = new ArrayList<String>();
        ArrayList<Integer> intList = new ArrayList<Integer>();

        readHighScores("src/main/resources/HighScores.txt", strList, intList);

        Assert.assertEquals(2, strList.size());
        Assert.assertEquals("Rachel", strList.get(0));
        Assert.assertEquals(10, intList.get(1).intValue());

        insertNewScore(strList, intList, "Jim", 50);
        writeHighScores("src/main/resources/newHighScores.txt", strList, intList);

        Assert.assertEquals(50, intList.get(1).intValue());

    }

    @Test
    public void readEmptyTest() {
        ArrayList<String> strList = new ArrayList<String>();
        ArrayList<Integer> intList = new ArrayList<Integer>();
        readHighScores("src/main/resources/blergh.text", strList, intList);

        Assert.assertEquals(0, strList.size());
        Assert.assertEquals(0, intList.size());

        insertNewScore(strList, intList, "Jim", 50);

        Assert.assertEquals(50, intList.get(0).intValue());
        Assert.assertEquals(1, strList.size());
    }

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
