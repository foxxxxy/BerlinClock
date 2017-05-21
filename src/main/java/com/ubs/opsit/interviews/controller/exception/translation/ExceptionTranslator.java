package com.ubs.opsit.interviews.controller.exception.translation;

import com.ubs.opsit.interviews.controller.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author foxy
 * @since 21.05.17.
 */

@ControllerAdvice
public class ExceptionTranslator {

    private static final String ERR_METHOD_NOT_SUPPORTED = "error.methodNotSupported";
    private static final String ERR_VALIDATION = "error.validation";

    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO timeNotValidException(NumberFormatException exception) {
        return new ErrorDTO(ERR_VALIDATION, exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorDTO processMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return new ErrorDTO(ERR_METHOD_NOT_SUPPORTED, exception.getMessage());
    }
}
