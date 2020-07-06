package ru.orgunit.backend.dto;

import java.util.UUID;

public class ComboboxResponseDto {

    private UUID id;
    private String value;

    public ComboboxResponseDto() {
    }

    public ComboboxResponseDto(UUID id, String value) {
        this.id = id;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
