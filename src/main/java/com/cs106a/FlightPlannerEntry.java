package com.cs106a;

/**
 *
 */
public class FlightPlannerEntry {

    public FlightPlannerEntry (String line) {
        String [] sections = line.split(" -> ");
        startCityName = sections[0];
        endCityName = sections[1];
    }

    public String getStartCity () {
    return startCityName;
    }

    public String getEndCity () {
    return endCityName;
    }
private String startCityName;
private String endCityName;
}
