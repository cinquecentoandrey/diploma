package com.sems.newsservice.controller;

import com.sems.common.dto.OperationResult;
import com.sems.common.enums.ErrorMessage;
import com.sems.common.exception.EntityNotFoundException;
import com.sems.newsservice.exception.DuplicatedNewsNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
@Slf4j
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleException(EntityNotFoundException e) {
        return buildErrorResult(ErrorMessage.ENTITY_NOT_FOUND, HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(value = {DuplicatedNewsNameException.class})
    protected ResponseEntity<Object> handleException(DuplicatedNewsNameException e) {
        return buildErrorResult(ErrorMessage.NEWS_ALREADY_EXSITS, HttpStatus.BAD_REQUEST, e);
    }

    private ResponseEntity<Object> buildErrorResult(ErrorMessage message, HttpStatus status, Exception ex) {
        if (ex != null) {
            log.debug(ex.getMessage(), ex);
        }

        return new ResponseEntity<>(OperationResult.error(message.getCode(), message.getMessage()), status);
    }

}
