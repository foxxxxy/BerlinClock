package com.ubs.opsit.interviews.services.utils.impl;

import com.ubs.opsit.interviews.services.utils.TimeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * @author foxy
 * @since 20.05.17.
 */

@Service
public class TimeParserImpl implements TimeParser{

    private static final String TIME_SEPARATOR = ":";

    private final TimeValidatorImpl timeValidator;

    private int hours;
    private int minutes;
    private int seconds;

    @Autowired
    public TimeParserImpl(TimeValidatorImpl timeValidator) {
        this.timeValidator = timeValidator;
    }

    @Override
    public void parseTime(String time) {
        if (!timeValidator.isTimeValid(time)){
            throw new NumberFormatException("Time: " + time + " is not valid");
        }
        int[] timeParts = Stream.of(time.split(TIME_SEPARATOR))
                .mapToInt(Integer::parseInt).toArray();
        this.hours = timeParts[0];
        this.minutes = timeParts[1];
        this.seconds = timeParts[2];
    }

    @Override
    public int getHours() {
        return this.hours;
    }

    @Override
    public int getMinutes() {
        return this.minutes;
    }

    @Override
    public int getSeconds() {
        return this.seconds;
    }
}
