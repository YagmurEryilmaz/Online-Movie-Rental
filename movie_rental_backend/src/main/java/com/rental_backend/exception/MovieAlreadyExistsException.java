package com.rental_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MovieAlreadyExistsException extends Throwable {
    public MovieAlreadyExistsException(String s) {
        super(s);
    }
}
