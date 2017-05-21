package com.ubs.opsit.interviews.tests;

import com.ubs.opsit.interviews.services.utils.TimeParser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @author foxy
 * @since 21.05.17.
 */
public class TimeParserTest extends BaseSpringTest {

    @Autowired
    private TimeParser timeParser;

    @Test(expected = NumberFormatException.class)
    public void testInvalidTimeWithoutSeconds() {
        String invalidTime = "10:19";
        timeParser.parseTime(invalidTime);
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidTime() {
        String invalidTime = ":00:";
        timeParser.parseTime(invalidTime);
    }

    @Test(expected = NumberFormatException.class)
    public void testEmptyTime() throws Exception {
        String invalidTime = "";
        timeParser.parseTime(invalidTime);
    }

    @Test
    public void testHours() throws Exception {
        String time = "12:00:00";
        int expectedHours = 12;
        timeParser.parseTime(time);
        int actualHours = timeParser.getHours();
        assertEquals(expectedHours, actualHours);
    }

    @Test
    public void testMinutes() throws Exception {
        String time = "00:15:00";
        int expectedMinutes = 15;
        timeParser.parseTime(time);
        int actualMinutes = timeParser.getMinutes();
        assertEquals(expectedMinutes, actualMinutes);
    }

    @Test
    public void testSeconds() throws Exception {
        String time = "00:00:16";
        int expectedSeconds = 16;
        timeParser.parseTime(time);
        int actualSeconds = timeParser.getSeconds();
        assertEquals(expectedSeconds, actualSeconds);
    }
}
