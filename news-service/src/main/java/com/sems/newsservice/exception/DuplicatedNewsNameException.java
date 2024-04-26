package com.sems.newsservice.exception;

import com.sems.common.exception.BaseException;

public class DuplicatedNewsNameException extends BaseException {
    public DuplicatedNewsNameException(String message) {
        super(message);
    }
}
