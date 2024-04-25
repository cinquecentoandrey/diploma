package com.sems.common.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    FILE_SAVING_ERROR(-1, "Ошибка при сохранении файла"),
    FILE_UPLOADING_ERROR(-2, "Ошибка при загрузке файла"),
    FILE_DOWNLOADING_ERROR(-3, "Ошибка при загрузке файла"),
    ENTITY_NOT_FOUND(-4, "Сущность не найдена");

    private int code;
    private String message;

    ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
