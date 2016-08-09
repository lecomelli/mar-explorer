package br.com.leandrojacomelli.marsexplorer.common.controller;


import br.com.leandrojacomelli.marsexplorer.common.exception.ExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseController {

    private final Logger log = LoggerFactory.getLogger(BaseController.class);


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ExceptionMessage exceptionHandler(HttpServletRequest req, Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ExceptionMessage(exception.getMessage());
    }

}