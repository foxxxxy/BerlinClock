package com.ubs.opsit.interviews.services.utils;

/**
 * @author foxy
 * @since 20.05.17.
 */
public interface TimeParser {

    void parseTime(String time);
    int getHours();
    int getMinutes();
    int getSeconds();
}
