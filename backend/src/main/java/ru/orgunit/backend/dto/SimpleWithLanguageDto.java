package ru.orgunit.backend.dto;

public class SimpleWithLanguageDto extends LanguageDto {

    private long specializationId;

    public long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(long specializationId) {
        this.specializationId = specializationId;
    }
}
