package com.cs106a;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class NameSurferDatabaseTest {

    @Test
    public void nameTest () {
        NameSurferDataBase dataTest = new NameSurferDataBase("src/main/resources/names-data.txt");
        NameSurferEntry entry = dataTest.findEntry("Rachel");

        Assert.assertEquals("Rachel", entry.getName());
        Assert.assertEquals(159, entry.getRank(2));

        NameSurferEntry absentEntry = dataTest.findEntry("Blergh");

        Assert.assertEquals(null, absentEntry);
    }
}
