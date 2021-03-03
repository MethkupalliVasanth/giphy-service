package com.example.giphyservice.controller;

import com.example.giphyservice.exception.ISCRestAPIGeneralException;
import com.example.giphyservice.exception.ISCRestAPINotFoundException;
import com.example.giphyservice.exception.ISCRestAPITooManyRequesException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class BaseController {

    @ExceptionHandler(ISCRestAPINotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(ISCRestAPINotFoundException e) { return createModelAndView(e.getMessage()); }

    @ExceptionHandler(ISCRestAPIGeneralException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleGeneralException(ISCRestAPIGeneralException e) {
        return createModelAndView(e.getMessage());
    }

    @ExceptionHandler(ISCRestAPITooManyRequesException.class)
    @ResponseStatus(value = HttpStatus.TOO_MANY_REQUESTS)
    public ModelAndView handleTooManyRequests(ISCRestAPITooManyRequesException e) {
        return createModelAndView(e.getMessage());
    }

    private ModelAndView createModelAndView(String message) {
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
