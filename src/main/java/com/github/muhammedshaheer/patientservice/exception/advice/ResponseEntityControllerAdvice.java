package com.github.muhammedshaheer.patientservice.exception.advice;

import com.github.muhammedshaheer.patientservice.exception.PatientServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResponseEntityControllerAdvice {

    @ExceptionHandler(PatientServiceException.class)
    public ModelAndView patientServiceException(HttpServletRequest httpServletRequest, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
