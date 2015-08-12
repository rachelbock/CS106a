package com.cs106a;

import acm.program.ConsoleProgram;
import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.util.ArrayList;

/**
 *
 */
public class FlightPlanner extends ConsoleProgram {

    public void run() {

        startText();
        flightPlanning();

    }


    public void startText() {
        println("Welcome to Flight Planner!");
        println("Here's a list of all the cities in our database:");
        data = new FlightPlannerDatabase("src/main/resources/flights.txt");
        str = new ArrayList<String>();
        for (int i = 0; i < data.entryList.size(); i++) {
            String city = data.entryList.get(i).getStartCity();
            if (!str.contains(city)) {
                str.add(city);
            }

        }
        for (int i = 0; i < str.size(); i++) {
            println(str.get(i));
        }
        println("Let's plan a round-trip route!");
    }

    public void flightPlanning() {
        String city = "";
        String startCity = "";
        while (true) {
            String inputStartCityName = readLine("Enter the starting city: ");
            city = inputStartCityName;
            startCity = inputStartCityName;
            boolean nameThere = false;
            for (int i = 0; i < data.entryList.size(); i++) {
                if (inputStartCityName.equals(data.entryList.get(i).getStartCity())) {
                    nameThere = true;
                }

            }
            if (nameThere == false) {
                println("You can't get to that city by a direct flight");
                println("Here's a list of all the cities in our database:");
                for (int i = 0; i < str.size(); i++) {
                    println(str.get(i));
                }
                continue;
            }

            if (nameThere == true) {
                break;
            }
        }
        inputCities.add(startCity);


        cityReturn(startCity);
        for (int i = 0; i < strArray.size(); i++) {
            println(strArray.get(i));
        }


        while (true) {


            String inputNextCityName = readLine("Where do you want to go from " + city + "? ");
            boolean nameThere = false;
            for (int i = 0; i < strArray.size(); i++) {
                if (inputNextCityName.equals(strArray.get(i))) {
                    nameThere = true;
                } else {

                }
            }
            if (nameThere == false) {
                println("You can't get to that city by a direct flight");
                println("From " + city + " you can fly directly to:");
                for (int i = 0; i < strArray.size(); i++) {
                    println(strArray.get(i));
                }
                continue;
            }

            city = inputNextCityName;

            inputCities.add(inputNextCityName);
            if (inputNextCityName.equals(startCity)) {
                println("The route you've chosen is:");
                for (int i = 0; i < inputCities.size(); i++) {
                    print(inputCities.get(i));
                    if (i != inputCities.size() - 1) {
                        print(" -> ");
                    }
                }
                break;
            }
            cityReturn(inputNextCityName);
            for (int i = 0; i < strArray.size(); i++) {
                println(strArray.get(i));
            }
        }
    }

    public ArrayList<String> cityReturn(String str) {
        strArray.clear();
        println("From " + str + " you can fly directly to:");

        for (int i = 0; i < data.entryList.size(); i++) {
            if (str.equals(data.entryList.get(i).getStartCity())) {
                strArray.add(data.entryList.get(i).getEndCity());

            }
        }
        return strArray;
    }

    //Instance Variables

    private FlightPlannerDatabase data;
    private ArrayList<String> strArray = new ArrayList<String>();
    private ArrayList<String> inputCities = new ArrayList<String>();
    private ArrayList<String> str;
    // Main Function

    public static void main(String[] args) {
        new FlightPlanner().start();
    }

}
