package com.sems.filestorageservice.controller;

import com.sems.common.dto.OperationResult;
import com.sems.common.enums.ErrorMessage;
import com.sems.common.exception.EntityNotFoundException;
import com.sems.filestorageservice.exception.ByteDownloadException;
import com.sems.filestorageservice.exception.FileStorageException;
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

    @ExceptionHandler(value = {FileStorageException.class})
    protected ResponseEntity<Object> handleException(FileStorageException e) {
        return buildErrorResult(ErrorMessage.FILE_SAVING_ERROR, HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleException(EntityNotFoundException e) {
        return buildErrorResult(ErrorMessage.ENTITY_NOT_FOUND, HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(value = {ByteDownloadException.class})
    protected ResponseEntity<Object> handleException(ByteDownloadException e) {
        return buildErrorResult(ErrorMessage.FILE_DOWNLOADING_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity<Object> buildErrorResult(ErrorMessage message, HttpStatus status, Exception ex) {
        if (ex != null) {
            log.debug(ex.getMessage(), ex);
        }

        return new ResponseEntity<>(OperationResult.error(message.getCode(), message.getMessage()), status);
    }

}
