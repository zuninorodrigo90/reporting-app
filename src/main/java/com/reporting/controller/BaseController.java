package com.reporting.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class BaseController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionManager(HttpServletRequest httpServletRequest, Exception exception) {
        try {
            throw exception;
        } catch (IllegalArgumentException e) {
            log.error("Bad request: [{}]", httpServletRequest.getRequestURI());
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (EntityNotFoundException e) {
            log.error("Entity not found: [{}]", httpServletRequest.getRequestURI());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } catch (Exception e) {
            log.error("Server internal error: [{}]", httpServletRequest.getRequestURI());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}
