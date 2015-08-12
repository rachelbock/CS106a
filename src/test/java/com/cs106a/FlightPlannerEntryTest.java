package com.cs106a;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class FlightPlannerEntryTest {

    @Test
    public void testParsing() {
        FlightPlannerEntry entry = new FlightPlannerEntry("New York -> Honolulu");

        Assert.assertEquals("New York", entry.getStartCity());
        Assert.assertEquals("Honolulu", entry.getEndCity());
    }

}
