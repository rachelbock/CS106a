package com.cs106a;

import acm.graphics.GImage;
import acm.program.*;

import java.awt.event.MouseEvent;


/**
 * Created with IntelliJ IDEA.
 * User: nvt
 * Date: 3/31/15
 * Time: 8:00 PM
 */
public class Midterm extends GraphicsProgram {
//    public void run() {

//        println ("This program finds the two largest integers in a list. Enter values, one per line, using a 0 to signal the end of the list.");
//
//        int num = -1;
//        int largeNum = 0;
//        int secondlargeNum = 0;
//
//
//        while (num !=0) {
//        num = readInt("?");
//        if (num > largeNum) {
//            secondlargeNum = largeNum;
//            largeNum = num;
//        }
//            if (num < largeNum && num > secondlargeNum) {
//                secondlargeNum = num;
//            }
//    }
//
//    println ("The largest value is: " + largeNum);
//        println ("The second largest value is: " + secondlargeNum);
//
//    }
//
//        String str = readLine("Give me a String ");
//        String newstr = removeDoubledLetters(str);
//
//        println("Here is the string with no doubled letters: " + newstr);
//    }
//
//    public String removeDoubledLetters(String str) {
//
//        String newstr = "";
//        char previousLetter = '0';
//
//        for (int i = 0; i < str.length(); i++) {
//            char ch = str.charAt(i);
//            if (ch != previousLetter) {
//                newstr = newstr + ch;
//            }
//            previousLetter = ch;
//        }
//
//        return newstr;
//    }

    private static final int SQSIZE = 75;
    private static final int NCOLS = 7;
    private static final int NROWS = 3;
    private static final int APPLICATION_WIDTH = NCOLS * SQSIZE;
    private static final int APPLICATION_HEIGHT = NROWS * SQSIZE;

    public void run() {

        addMouseListeners();

        setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);

        frog.setSize(SQSIZE - 20, SQSIZE - 20);
        int frogX = (int) ((getWidth() - frog.getWidth()) / 2);
        int frogY = (int) (getHeight() - frog.getHeight());
        frog.setLocation(frogX, frogY);
        add(frog);

    }

    public void mouseClicked(MouseEvent e) {
        if (e.getX() > frog.getX() && e.getX() < frog.getX() + SQSIZE) {
            if (e.getY() < frog.getY()) {
                frog.move(0, -SQSIZE);
            } else if (e.getY() > frog.getY()+SQSIZE) {
                frog.move(0, SQSIZE);
            }
        }
        if (e.getX() > frog.getX() + SQSIZE && frog.getX() < APPLICATION_WIDTH-SQSIZE) {
            frog.move(SQSIZE, 0);
        }
        if (e.getX() < frog.getX() && frog.getX() > 0) {
            frog.move(-SQSIZE, 0);
        }
    }


    GImage frog = new GImage("frog.png");


    public static void main(String[] args) {
        new Midterm().start();
    }
}
