package com.ubs.opsit.interviews.controller.dto;

import lombok.Data;

/**
 * @author foxy
 * @since 20.05.17.
 */

@Data
public class BerlinClockTimeDTO {
    private String berlinClockTime;

    public BerlinClockTimeDTO(String berlinClockTime) {
        this.berlinClockTime = berlinClockTime;
    }
}
