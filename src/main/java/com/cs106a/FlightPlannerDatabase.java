package com.cs106a;

import acm.util.ErrorException;
import com.sun.tools.javac.code.Attribute;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public class FlightPlannerDatabase {

    public FlightPlannerDatabase(String filename) {
        try {
            BufferedReader rd = new BufferedReader(new FileReader(filename));

            while (true) {
                String line = rd.readLine();
                if (line == null) {
                    break;
                }

                if (!line.equals("")) {
                    FlightPlannerEntry entry = new FlightPlannerEntry(line);
                    entryList.add(entry);
                }
            }
            rd.close();
        } catch (IOException ex) {
            throw new ErrorException(ex);
        }
    }

    public ArrayList<FlightPlannerEntry> cityReturnList(String str) {
        ArrayList<FlightPlannerEntry> startCityEntryList = new ArrayList<FlightPlannerEntry>();
        for (int i = 0; i < entryList.size(); i++) {
            String startCity = entryList.get(i).getStartCity();
            if (str.equals(startCity)) {
                startCityEntryList.add(entryList.get(i));
            }
        }
        return startCityEntryList;
    }

    ArrayList<FlightPlannerEntry> entryList = new ArrayList<FlightPlannerEntry>();

}

