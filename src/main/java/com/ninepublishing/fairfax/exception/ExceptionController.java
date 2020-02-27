package com.ninepublishing.fairfax.exception;

import com.ninepublishing.fairfax.dtos.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;
import java.util.Arrays;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<ExceptionDTO> exception(ValidationException exception) {
        return new ResponseEntity<>(
                ExceptionDTO.builder()
                .errorCode(400)
                .errorList(Arrays.asList(
                        exception.getMessage()
                                .replace("FOLLOWING FIELDS ARE INVALID :","")
                                .split(";"))
                )
                .build(), HttpStatus.BAD_REQUEST);
    }
}
