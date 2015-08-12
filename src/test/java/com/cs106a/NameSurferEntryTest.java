package com.cs106a;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class NameSurferEntryTest {

    @Test
    public void testParsing() {

        NameSurferEntry entry = new NameSurferEntry("Gregoria 790 711 658 718 0 0 0 0 0 0 0");

        Assert.assertEquals(718, entry.getRank(3));
        Assert.assertEquals(0, entry.getRank(9));
        Assert.assertEquals("Gregoria", entry.getName());
        System.out.println(entry);
    }
}
