package com.ubs.opsit.interviews.tests;

import com.ubs.opsit.interviews.services.utils.TimeValidator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author foxy
 * @since 21.05.17.
 */
public class TimeValidatorTest extends BaseSpringTest {

    @Autowired
    private TimeValidator timeValidator;

    @Test
    public void testInvalidHours() throws Exception {
        String time = "24:00:00";
        boolean actualResult = timeValidator.isTimeValid(time);
        assertFalse(actualResult);
    }

    @Test
    public void testInvalidMinutes() throws Exception {
        String time = "00:60:00";
        boolean actualResult = timeValidator.isTimeValid(time);
        assertFalse(actualResult);
    }

    @Test
    public void testInvalidSeconds() throws Exception {
        String time = "00:00:60";
        boolean actualResult = timeValidator.isTimeValid(time);
        assertFalse(actualResult);
    }

    @Test
    public void testEmptyTime() throws Exception {
        String time = "";
        boolean actualResult = timeValidator.isTimeValid(time);
        assertFalse(actualResult);
    }

    @Test
    public void testInvalidTime() throws Exception {
        String time = "Wed Oct 12, 23:55:02";
        boolean actualResult = timeValidator.isTimeValid(time);
        assertFalse(actualResult);
    }

    @Test
    public void testValidTime() throws Exception {
        String time = "00:00:00";
        boolean actualResult = timeValidator.isTimeValid(time);
        assertTrue(actualResult);
    }

    @Test
    public void testValidHours() throws Exception {
        String time = "01:00:00";
        boolean actualResult = timeValidator.isTimeValid(time);
        assertTrue(actualResult);
    }

    @Test
    public void testValidMinutes() throws Exception {
        String time = "00:01:00";
        boolean actualResult = timeValidator.isTimeValid(time);
        assertTrue(actualResult);
    }

    @Test
    public void testValidSeconds() throws Exception {
        String time = "00:00:01";
        boolean actualResult = timeValidator.isTimeValid(time);
        assertTrue(actualResult);
    }

    @Test
    public void testTopValidHours() throws Exception {
        String time = "23:00:00";
        boolean actualResult = timeValidator.isTimeValid(time);
        assertTrue(actualResult);
    }

    @Test
    public void testTopValidMinutes() throws Exception {
        String time = "00:59:00";
        boolean actualResult = timeValidator.isTimeValid(time);
        assertTrue(actualResult);
    }

    @Test
    public void testTopValidSeconds() throws Exception {
        String time = "00:00:59";
        boolean actualResult = timeValidator.isTimeValid(time);
        assertTrue(actualResult);
    }
}
