package com.ubs.opsit.interviews.services.convertor.impl;

import com.ubs.opsit.interviews.services.convertor.TimeConverter;
import com.ubs.opsit.interviews.services.utils.TimeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ubs.opsit.interviews.services.convertor.impl.TimeConverterConstants.*;

/**
 * @author foxy
 * @since 20.05.17.
 */

@Service
public class TimeConverterImpl implements TimeConverter {

    private final TimeParser timeParser;

    @Autowired
    public TimeConverterImpl(TimeParser timeParser) {
        this.timeParser = timeParser;
    }

    @Override
    public String convertTime(String aTime) {
        timeParser.parseTime(aTime);
        return getSeconds(timeParser.getSeconds()).concat(LINE_SEPARATOR)
                .concat(getTopHours(timeParser.getHours()).concat(LINE_SEPARATOR)
                        .concat(getBottomHours(timeParser.getHours())).concat(LINE_SEPARATOR)
                        .concat(getTopMinutes(timeParser.getMinutes())).concat(LINE_SEPARATOR)
                        .concat(getBottomMinutes(timeParser.getMinutes())));
    }

    private String getSeconds(int seconds) {
        return seconds % 2 == 0 ? YELLOW_LAMP : LAMP_OFF;
    }

    private String getTopHours(int hours) {
        return getOnOff(HOURS_LAMPS_COUNT, getTopNumberOfOnSigns(hours));
    }

    private String getBottomHours(int hours) {
        return getOnOff(HOURS_LAMPS_COUNT, hours % COUNT_TIME_IN_ONE_LAMP_AND_PANEL);
    }

    private String getTopMinutes(int minutes) {
        return getOnOff(COUNT_MINUTES_IN_LAMPS_TOP_PANEL, getTopNumberOfOnSigns(minutes), YELLOW_LAMP)
                .replaceAll(INVALID_FIFTEEN_MINUTES_PANEL, FIFTEEN_MINUTES_PANEL);
    }

    private String getBottomMinutes(int minutes) {
        return getOnOff(MINUTES_LAMPS_COUNT, minutes % COUNT_TIME_IN_ONE_LAMP_AND_PANEL, YELLOW_LAMP);
    }

    private String getOnOff(int lamps, int onSigns) {
        return getOnOff(lamps, onSigns, RED_LAMP);
    }

    private String getOnOff(int lamps, int onSigns, String onSign) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < onSigns; i++) {
            out.append(onSign);
        }
        for (int i = 0; i < (lamps - onSigns); i++) {
            out.append(LAMP_OFF);
        }
        return out.toString();
    }

    private int getTopNumberOfOnSigns(int number) {
        return (number - (number % COUNT_TIME_IN_ONE_LAMP_AND_PANEL)) / COUNT_TIME_IN_ONE_LAMP_AND_PANEL;
    }
}
