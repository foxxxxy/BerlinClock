package com.ubs.opsit.interviews.controller.rest;

import com.ubs.opsit.interviews.controller.dto.BerlinClockTimeDTO;
import com.ubs.opsit.interviews.controller.dto.TimeDTO;
import com.ubs.opsit.interviews.services.convertor.TimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author foxy
 * @since 20.05.17.
 */

@RestController
@RequestMapping("/api")
public class BerlinClockController {

    private final TimeConverter timeConverter;

    @Autowired
    public BerlinClockController(TimeConverter timeConverter) {
        this.timeConverter = timeConverter;
    }

    @RequestMapping(value = "/convert", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BerlinClockTimeDTO> convertTime(@RequestBody TimeDTO time) {
        final String berlinClockTime = timeConverter.convertTime(time.getTime());
        return new ResponseEntity<>(new BerlinClockTimeDTO(berlinClockTime), HttpStatus.OK);
    }
}