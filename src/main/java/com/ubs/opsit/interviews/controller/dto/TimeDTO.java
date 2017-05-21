package com.ubs.opsit.interviews.controller.dto;

import lombok.Data;

/**
 * @author foxy
 * @since 20.05.17.
 */

@Data
public class TimeDTO {
    private String time;

    public TimeDTO() {
    }

    public TimeDTO(String time) {
        this.time = time;
    }
}
