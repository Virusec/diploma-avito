package com.example.diploma.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class Advice {
    @ExceptionHandler(FindNoEntityException.class)
    public void handleException(FindNoEntityException e) {
        log.warn("Обращение к несуществующей записи:" + e.getMessage());
    }
}
