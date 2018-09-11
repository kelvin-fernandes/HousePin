package org.jacared.housepin.controllers;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErroController {

    @ExceptionHandler(BindException.class)
    public @ResponseBody String bindError(BindException e, HttpServletRequest request, HttpServletResponse response) {
        e.printStackTrace();
        return "BindException: " + e.getMessage() + "\r\n";
    }

    @ExceptionHandler(TemplateInputException.class)
    public @ResponseBody String templateInputException(TemplateInputException e, HttpServletRequest request, HttpServletResponse response) {
        e.printStackTrace();
        return "TemplateInputException: " + e.getMessage() + "\r\n";
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody String templateException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        e.printStackTrace();
        return "TemplateInputException: " + e.getMessage() + "\r\n";
    }
}
