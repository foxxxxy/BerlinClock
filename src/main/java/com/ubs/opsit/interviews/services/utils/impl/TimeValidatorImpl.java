package com.ubs.opsit.interviews.services.utils.impl;

import com.ubs.opsit.interviews.services.utils.TimeValidator;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author foxy
 * @since 20.05.17.
 */
@Service
public class TimeValidatorImpl implements TimeValidator{

    private static final String TIME_PATTERN =
            "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

    private static Pattern pattern = Pattern.compile(TIME_PATTERN);

    @Override
    public boolean isTimeValid(String time) {
        return pattern.matcher(time).matches();
    }
}
