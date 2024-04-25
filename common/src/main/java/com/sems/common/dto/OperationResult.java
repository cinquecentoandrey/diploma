package com.sems.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationResult<T> {

    private int code;
    private String message;
    private Error error;
    private T data;

    public OperationResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> OperationResult<T> error(int code, String message) {
        return new OperationResult<>(code, message, null);
    }
}
