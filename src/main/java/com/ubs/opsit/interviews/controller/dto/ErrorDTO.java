package com.ubs.opsit.interviews.controller.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author foxy
 * @since 21.05.17.
 */

@Getter
@Setter
public class ErrorDTO {

    private final String message;
    private final String description;

    public ErrorDTO(String message) {
        this(message, null);
    }

    public ErrorDTO(String message, String description) {
        this.message = message;
        this.description = description;
    }
}
