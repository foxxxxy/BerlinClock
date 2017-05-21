package com.ubs.opsit.interviews.tests;

import com.ubs.opsit.interviews.services.convertor.TimeConverter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @author foxy
 * @since 21.05.17.
 */
public class TimeConverterTest extends BaseSpringTest {

    @Autowired
    private TimeConverter timeConverter;

    @Test
    public void testMidnight() throws Exception {
        String midnightString = "00:00:00";
        String expectedTime = "Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO";
        String actualTime = timeConverter.convertTime(midnightString);
        assertEquals(expectedTime, actualTime);
    }

    @Test
    public void testMiddleOfTheAfternoon() throws Exception {
        String middleOfTheAfternoon = "13:17:01";
        String expectedTime = "O\nRROO\nRRRO\nYYROOOOOOOO\nYYOO";
        String actualTime = timeConverter.convertTime(middleOfTheAfternoon);
        assertEquals(expectedTime, actualTime);
    }

    @Test
    public void testJustBeforeMidnight() throws Exception {
        String justBeforeMidnight = "23:59:59";
        String expectedTime = "O\nRRRR\nRRRO\nYYRYYRYYRYY\nYYYY";
        String actualTime = timeConverter.convertTime(justBeforeMidnight);
        assertEquals(expectedTime, actualTime);
    }

    @Test(expected = NumberFormatException.class)
    public void testMidnightWith24Format() throws Exception {
        String midnightWith24FormatString = "24:00:00";
        timeConverter.convertTime(midnightWith24FormatString);
    }
}
