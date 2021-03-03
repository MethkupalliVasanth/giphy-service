package com.example.giphyservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ISCRestAPINotFoundException extends RuntimeException {

    public ISCRestAPINotFoundException(String message) { super(message); }

}
