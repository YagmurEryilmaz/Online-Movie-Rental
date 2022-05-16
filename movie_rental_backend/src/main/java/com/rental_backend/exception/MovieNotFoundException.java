package com.rental_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String s) {
        super(s);
    }
}
