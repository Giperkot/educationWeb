package ru.orgunit.backend.exceptions;

public enum SirErrorStatus {

    UNKNOWN(0, "Неизвестно"),
    USER_EXISTS(1, "Пользователь существует"),
    USER_NOT_EXISTS(2, "Пользователь не существует"),
    VALIDATION_ERROR(3, "Переданы не верные данные"),
    WRONG_LOGIN_OR_PASSWORD(4, "Не верный логин или пароль")
    ;

    private int id;
    private String description;

    SirErrorStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
